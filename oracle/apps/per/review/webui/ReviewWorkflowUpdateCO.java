/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.ReviewWorkflowAMImpl;

import cux.oracle.apps.per.review.server.ReviewWorkflowLevelVOImpl;
import cux.oracle.apps.per.review.server.ReviewWorkflowLevelVORowImpl;
import cux.oracle.apps.per.review.server.ReviewWorkflowVOImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.Transaction;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class ReviewWorkflowUpdateCO extends OAControllerImpl {
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
                                                       "CreateReviewWorkflowTxn");
            if (!pageContext.isFormSubmission()) {
                ReviewWorkflowAMImpl am = 
                    (ReviewWorkflowAMImpl)pageContext.getApplicationModule(webBean);
                String epmWorkflowId = 
                    pageContext.getParameter("EpmWorkflowId");
                if (epmWorkflowId != null) {
                    pageContext.putSessionValue("EpmWorkflowId", 
                                                epmWorkflowId);
                } else {
                    epmWorkflowId = 
                            (String)pageContext.getSessionValue("EpmWorkflowId");
                }
                if (epmWorkflowId != null && !epmWorkflowId.equals("")) {
                    Serializable parameters[] = { epmWorkflowId };
                    am.invokeMethod("initReviewWorkflowVO1", parameters);

                    am.invokeMethod("initReviewWorkflowLevelVO1", parameters);
                    pageContext.putSessionValue("EpmWorkflowId", 
                                                epmWorkflowId);
                    /*
                  ReviewWorkflowLevelVOImpl vo = (ReviewWorkflowLevelVOImpl)am.getReviewWorkflowLevelVO1();
                  vo.initQuery(epmWorkflowId);
                  */
                }
            }

        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateReviewWorkflowTxn", 
                                                                      true)) {
            OADialogPage dp = new OADialogPage(NAVIGATION_ERROR);
            pageContext.redirectToDialogPage(dp);
        }

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("ReviewWorkflowLevelVO1");
        tableBean.prepareForRendering(pageContext);
        tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);

        OAMessageChoiceBean msGroupName = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("GroupName");
        msGroupName.setPickListCacheEnabled(false);
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
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("ReviewWorkflowLevelVO1");
        String epmWorkflowId = 
            (String)pageContext.getSessionValue("EpmWorkflowId");
        if (epmWorkflowId == null || epmWorkflowId.equals(""))
            throw new OAException("CUX", "CUX_WF_ID_WAR");
        // Handle adding a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            ReviewWorkflowLevelVOImpl vo = 
                (ReviewWorkflowLevelVOImpl)am.getReviewWorkflowLevelVO1();
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = vo.createRow();
            int rowCount = vo.getFetchedRowCount(); //dataCount
            String rowCount_str = 
                (String)pageContext.getSessionValue("rowCount");
            if (rowCount_str != null && !rowCount_str.equals("")) {
                rowCount = Integer.parseInt(rowCount_str);
            }
            if (rowCount >= 4)
                throw new OAException("CUX", "CUX_WF_LEVEL_4WAR");

            vo.insertRowAtRangeIndex(rowCount, row);
            //vo.insertRow(row);
            row.setNewRowState(Row.STATUS_INITIALIZED);
            Number epmApproveId = 
                am.getOADBTransaction().getSequenceValue("CUX_REVIEW_WORKFLOW_LEVEL_S");
            row.setAttribute("EpmApproveId", epmApproveId);
            row.setAttribute("EpmApproveLevel", rowCount + 1);
            row.setAttribute("EpmWorkflowId", epmWorkflowId);
            vo.setCurrentRow(row);
            pageContext.putSessionValue("rowCount", 
                                        String.valueOf(rowCount + 1));
        }
        if (pageContext.getParameter("Apply") != null) {
            am.invokeMethod("apply");

            pageContext.removeSessionValue("EpmWorkflowId");
            pageContext.removeSessionValue("rowCount");

            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateReviewWorkflowTxn");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_CRE_WF_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            //am.invokeMethod("init");
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("ReviewWorkflowFilVO1");
            vo.executeQuery(); // retain AM  
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ReviewWorkflowPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        } else if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollbackReviewWorkflow");
            pageContext.removeSessionValue("EpmWorkflowId");
            pageContext.removeSessionValue("rowCount"); // retain AM  
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ReviewWorkflowPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        } else if ("delChildLevel".equals(pageContext.getParameter("event"))) {
            ReviewWorkflowLevelVOImpl vo = 
                (ReviewWorkflowLevelVOImpl)am.getReviewWorkflowLevelVO1();
            int rowCount = vo.getFetchedRowCount(); //dataCount

            String epmApproveId = 
                (String)pageContext.getParameter("EpmApproveId");
            Serializable[] params = { epmApproveId };
            String result = "";
            result = 
                    am.invokeMethod("deleteWorkflowForLevel", params).toString();

            String rowCount_str = 
                (String)pageContext.getSessionValue("rowCount");
            if (rowCount_str != null && !rowCount_str.equals("")) {
                rowCount = Integer.parseInt(rowCount_str);
            }
            pageContext.putSessionValue("rowCount", 
                                        String.valueOf(rowCount - 1));

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
                    new OAException("CUX", "CUX_DEL_WF_CONF", tokens, 
                                    OAException.WARNING, null);
                pageContext.putDialogMessage(confirmMessage);
            }

            /*  pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ReviewWorkflowCreatePG"
                                        , null, OAWebBeanConstants.KEEP_MENU_CONTEXT, null, null, true, "N");*/
        }
        if (tableBean != null && 
            ("changeApproveType".equals(pageContext.getParameter(EVENT_PARAM)))) {
            String rowReference = 
                pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE);
            //ReviewWorkflowLevelVOImpl vo = (ReviewWorkflowLevelVOImpl)am.getReviewWorkflowLevelVO1();
            ReviewWorkflowLevelVORowImpl row = 
                (ReviewWorkflowLevelVORowImpl)am.findRowByRef(rowReference);
            if ("FIXED".equals(row.getEpmApproveTypeCode())) {
                row.setUpdatableFlag(Boolean.FALSE);
            } else {
                row.setUpdatableFlag(Boolean.TRUE);
            }
        }

    }

}
