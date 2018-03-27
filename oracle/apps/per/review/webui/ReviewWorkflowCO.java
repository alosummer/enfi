/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.ReviewWorkflowAMImpl;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;


import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class ReviewWorkflowCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);

        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateWorkflowTxn");
            if (!pageContext.isFormSubmission()) {
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateWorkflowTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "UpdateWorkflowTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OAMessageChoiceBean msSearchGroupName = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("SearchGroupName");
        msSearchGroupName.setPickListCacheEnabled(false);
    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        ReviewWorkflowAMImpl am = 
            (ReviewWorkflowAMImpl)pageContext.getApplicationModule(webBean);
        /*    ReviewWorkflowAMImpl am = (ReviewWorkflowAMImpl)pageContext.getApplicationModule(webBean);
      OATableBean tableBean = (OATableBean)webBean.findChildRecursive("ReviewWorkflowVO1");
          //add a new row
          if (tableBean != null &&
                  (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) &&
                  (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))){
                  am.invokeMethod("create");
            }
          //Cancel
          if(pageContext.getParameter("Cancel") != null){
              am.invokeMethod("rollback");
              TransactionUnitHelper.endTransactionUnit(pageContext,"CreateWorkflowTxn");
              pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ReviewWorkflowPG", null, (byte)0, null, null, true, "N");
          }
          //Apply
          if(pageContext.getParameter("Apply") != null){
              am.invokeMethod("approveWorkflow", null);
          }
          */
        //CreateReviewWorkflow
        if (pageContext.getParameter("CreateReviewWorkflow") != null) {
            /*HashMap params = new HashMap(2);
               params.put("epmWorkflowId", "-1"); */
            // Retain AM
            // Show breadcrumbs 
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ReviewWorkflowCreatePG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        } else if ("updateReviewWorkflow".equals(pageContext.getParameter(EVENT_PARAM))) { // Retain AM
            // Show breadcrumbs 
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ReviewWorkflowUpdatePG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }
        // edit by fl at 20091019
        else if ("delReviewWorkflow".equals(pageContext.getParameter(EVENT_PARAM))) {
            String epmWorkflowId = 
                (String)pageContext.getParameter("EpmWorkflowId");
            OAException mainMessage = 
                new OAException("CUX", "CUX_DEL_WF_WARN", null, 
                                OAException.WARNING, null);
            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("DeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(1);
            formParams.put("EpmWorkflowId", epmWorkflowId);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String epmWorkflowId = 
                (String)pageContext.getParameter("EpmWorkflowId");
            Serializable[] params = { epmWorkflowId };
            String result = "";
            result = 
                    am.invokeMethod("deleteReviewWorkflow", params).toString();

            if (result.equals("Y")) {
                MessageToken tokens[] = 
                { new MessageToken("WF", epmWorkflowId) };
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_DEL_WF_CONF", tokens, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);
            } else {
                MessageToken tokens[] = 
                { new MessageToken("WF", epmWorkflowId) };
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_DEL_WF_ERRO", tokens, 
                                    OAException.WARNING, null);
                pageContext.putDialogMessage(confirmMessage);
            }
            am.invokeMethod("init");
        }
        //end edit by fl at 20091019
        /*       else if(pageContext.getParameter("customizeSubmitButton") != null){
               String groupName = pageContext.getParameter("SearchGroupName");
               String orgId = pageContext.getParameter("SearchOrgName");
               String epmPhaseClassCode = pageContext.getParameter("SearchPhase");
               Serializable parameters[] = {groupName,orgId,epmPhaseClassCode};
               am.invokeMethod("initSearchQuery", parameters);
           }*/
    }

}
