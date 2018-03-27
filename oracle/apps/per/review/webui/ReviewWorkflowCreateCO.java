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
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.Transaction;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class ReviewWorkflowCreateCO extends OAControllerImpl {
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
                    (String)pageContext.getSessionValue("EpmWorkflowId");
                /*�������id:epmWorkflowIdΪ��ʱ���½�һ�����*/
                if (epmWorkflowId == null || epmWorkflowId.equals("")) {
                    epmWorkflowId = 
                            (String)am.invokeMethod("createReviewWorkflow", 
                                                    null);
                    /*ȡ�ÿ������id:epmWorkflowId�����������session�����Ա��Ժ����*/
                    pageContext.putSessionValue("EpmWorkflowId", 
                                                epmWorkflowId);

                    // add by yang.gang,2014-8-21,begin,系统自动带出的组织名称,不重新选择组织则无法将值传入验证 
                    OAMessageLovInputBean lovBean = 
                        (OAMessageLovInputBean)webBean.findChildRecursive("OrgName");
                    String OrgName = lovBean.getText(pageContext);

                    OAFormValueBean formBean = 
                        (OAFormValueBean)webBean.findChildRecursive("OrgId");
                    if (OrgName != null && OrgName != "" && 
                        formBean.getValue(pageContext) == null) {
                        Serializable params[] = { OrgName };
                        Number OrgID = 
                            (Number)am.invokeMethod("GetOrgID", params);
                        if (OrgID != (new Number(0)))
                            formBean.setValue(pageContext, OrgID);
                    }
                    // add by yang.gang, 2014-8-21,  end

                    /*��ʼ���������LEVEL��һ��4˵����vo��rowΪ��*/
                    ReviewWorkflowLevelVOImpl vo = 
                        (ReviewWorkflowLevelVOImpl)am.getReviewWorkflowLevelVO1();
                    vo.initQuery(epmWorkflowId);
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
        /*ȡ��sessionֵ�������id:epmWorkflowId*/
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
            /*����Ҫִ��vo.executeQuery()�������ڵ�ǰ��whereclause�´�db��ȡ��ݣ������ǵ�cahe�е�vo*/
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = vo.createRow();
            /*rowCount��?ǰ���������level�ĸ���add��һ��:rowCount=0,add������rowCountȡsessionֵ*/
            int rowCount = 0;
            String rowCount_str = 
                (String)pageContext.getSessionValue("rowCount");
            if (rowCount_str != null && !rowCount_str.equals("")) {
                rowCount = Integer.parseInt(rowCount_str);
            }
            /*���������level�ĸ������Ϊ4*/
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
            /*��rowCount������ֵд��session*/
            pageContext.putSessionValue("rowCount", 
                                        String.valueOf(rowCount + 1));
        }
        if (pageContext.getParameter("Apply") != null) {
            am.invokeMethod("apply");
            /*��sessionע���*/
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
            /*��sessionע���*/
            pageContext.removeSessionValue("EpmWorkflowId");
            pageContext.removeSessionValue("rowCount"); // retain AM  
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ReviewWorkflowPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        } else if ("delChildLevel".equals(pageContext.getParameter("event"))) {
            String epmApproveId = 
                (String)pageContext.getParameter("EpmApproveId");
            Serializable[] params = { epmApproveId };
            String result = "";
            result = 
                    am.invokeMethod("deleteWorkflowForLevel", params).toString();
            /*rowCount��?ǰ���������level�ĸ���rowCountȡsessionֵ*/
            int rowCount = 0;
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
