/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.PerReviewAMImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class CreateGroupRangeCO extends OAControllerImpl {
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
                                                       "CreateGroupRangeTxn");
            if (!pageContext.isFormSubmission()) {
                String groupId = pageContext.getParameter("groupId");
                pageContext.putSessionValue("groupId", groupId);
                Serializable params[] = { groupId };
                OAApplicationModule am = 
                    pageContext.getApplicationModule(webBean);
                am.invokeMethod("initQuery", params);
                am.invokeMethod("initGroupRange", params);
                //am.invokeMethod("createGrpRange", null);
                //OAViewObject rgVO = (OAViewObject)am.findViewObject("PerReviewGroupVO1");
                //System.out.println("rowcount = "+rgVO.getRowCount());

                String companyId = pageContext.getParameter("companyId");
                pageContext.putSessionValue("companyId", companyId);
                Serializable param[] = { companyId, groupId };
                am.invokeMethod("initJobQuery", param);
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateGroupRangeTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("PerGroupRangeVO1");
        tableBean.prepareForRendering(pageContext);
        tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);

        OAMessageChoiceBean mcJob = 
            (OAMessageChoiceBean)webBean.findChildRecursive("JobName");
        mcJob.setPickListCacheEnabled(false);
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
        String groupId = (String)pageContext.getSessionValue("groupId");
        PerReviewAMImpl am = 
            (PerReviewAMImpl)pageContext.getApplicationModule(webBean);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("PerGroupRangeVO1");

        // Handle adding a new row
        if ((tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            Number rangeId = 
                am.getOADBTransaction().getSequenceValue("CUX_GROUP_RANGE_S");
            OAViewObject vo = am.getPerGrpRangeVO1();
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = 
                vo.createAndInitRow(new NameValuePairs(new String[] { "GroupId" }, 
                                                       new Object[] { groupId }));
            vo.insertRowAtRangeIndex(vo.getRowCount(), row);
            row.setNewRowState(Row.STATUS_INITIALIZED);
            row.setAttribute("GroupId", groupId);
            row.setAttribute("RangeId", rangeId);
            vo.setCurrentRow(row);
        }

        // Handle Apply
        if (pageContext.getParameter("Apply") != null) {
            am.getOADBTransaction().commit();
            //MessageToken tokens[] = {new MessageToken("GROUP", )};
            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_GRP_RANGE", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/PerReviewGroupPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle cancel
        if (pageContext.getParameter("Return") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateGroupRangeTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/PerReviewGroupPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle return
        /*
    if(pageContext.getParameter("Return") != null){
        pageContext.setForwardURL( "OA.jsp?page=/cux/oracle/apps/per/review/webui/PerReviewGroupPG"
                                 , null
                                 , OAWebBeanConstants.KEEP_MENU_CONTEXT
                                 , null
                                 , null
                                 , true // Retain AM
                                 , OAWebBeanConstants.ADD_BREAD_CRUMB_YES // Show breadcrumbs
                                 , OAWebBeanConstants.IGNORE_MESSAGES
                                 );
    }
    */

        // Handle delete
        if ("delete".equals(pageContext.getParameter("event"))) {
            String rangeId = pageContext.getParameter("rangeId");
            Serializable params[] = { rangeId };
            am.invokeMethod("deleteGrpRange", params);
        }

        // Create 
        if (pageContext.getParameter("CreateGrpRange") != null) { // Retain AM
            // Show breadcrumbs 
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/CreateGroupRangePG&groupId=" + 
                                      groupId, null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }

    }

}
