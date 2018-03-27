/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AprAMImpl;

import cux.oracle.apps.per.aprprocess.server.AprEmpSelfListVOImpl;

import cux.oracle.apps.per.aprprocess.server.AprManagerListVOImpl;

import cux.oracle.apps.per.review.server.AppraisalEmpVORowImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

/**
 * 绩效管理_经理自助，入口界面
 * created by yang.gang,2016-4-27 
 */
public class ManagerMainCO extends OAControllerImpl {
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

        this.initWeb(pageContext, webBean);
        this.initWebButton(pageContext, webBean);
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
        String event = pageContext.getParameter(EVENT_PARAM);
        if (pageContext.getParameter("Search") != null)
            this.OnSeachButtonClick(pageContext, webBean);
        else if (pageContext.getParameter("Approve") != null)
            this.OnApproveButtonClick(pageContext, webBean);
        else if (pageContext.getParameter("Reject") != null)
            this.OnRejectButtonClick(pageContext, webBean);
        else if ("detail".equals(event)) {
            this.OnDetailClick(pageContext);
        }
    }

    /* 初始化页面
   * 查看是否有backFromContract Session值，有该参数：页面是从绩效合同页面返回，执行先前的查询
   * */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprManagerListVO1");
        tableBean.setSelectionDisabledBindingAttr("IsPromisee");

        this.RefreshData(pageContext, webBean);
    }

    /* 重新检索数据 */

    private void RefreshData(OAPageContext pageContext, OAWebBean webBean) {
        String backFromContractFlag = 
            this.GetSessionValue(pageContext, "backFromContract");

        if (!"Y".equals(backFromContractFlag)) { // 默认显示当年的绩效合同，且为待处理的
            this.initWebDefaultYear(pageContext, webBean);
            return;
        }

        String queryFlag = this.GetSessionValue(pageContext, "queryFlag");
        if (!"Y".equals(queryFlag)) { //用户没进行查询，默认显示当年的绩效合同，且为待处理的
            this.initWebDefaultYear(pageContext, webBean);
            return;
        }

        //继续按上次的查询条件进行查询     
        String SearchPhase = this.GetSessionValue(pageContext, "SearchPhase");
        String SearchEmpName = 
            this.GetSessionValue(pageContext, "SearchEmpName");
        String SearchOrg = this.GetSessionValue(pageContext, "SearchOrg");
        String strYearFrom = this.GetSessionValue(pageContext, "YearFrom");
        String strYearTo = this.GetSessionValue(pageContext, "YearTo");
        String SearchStatus = 
            this.GetSessionValue(pageContext, "SearchStatus");
        String SearchPerformer = 
            this.GetSessionValue(pageContext, "SearchPerformer");

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprManagerListVOImpl vo = am.getAprManagerListVO1();
        vo.initQuery(SearchPhase, SearchEmpName, SearchOrg, strYearFrom, 
                     strYearTo, SearchStatus, SearchPerformer);
        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprManagerListVO1");
        tableBean.queryData(pageContext, false);
    }

    /* 显示当年的绩效合同 */

    private void initWebDefaultYear(OAPageContext pageContext, 
                                    OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprManagerListVOImpl vo = am.getAprManagerListVO1();
        vo.initQuery();

        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprManagerListVO1");
        tableBean.queryData(pageContext, false);
    }

    //  查询按钮响应事件

    private void OnSeachButtonClick(OAPageContext pageContext, 
                                    OAWebBean webBean) {
        String SearchPhase = 
            this.GetParameterValue(pageContext, "SearchPhase");
        String SearchEmpName = 
            this.GetParameterValue(pageContext, "SearchEmpName");
        String SearchOrg = this.GetParameterValue(pageContext, "SearchOrg");
        String strYearFrom = 
            this.GetParameterValue(pageContext, "SearchYearFrom");
        String strYearTo = this.GetParameterValue(pageContext, "SearchYearTo");
        String SearchStatus = 
            this.GetParameterValue(pageContext, "SearchStatus");
        String SearchPerformer = 
            this.GetParameterValue(pageContext, "SearchPerformer");

        pageContext.putSessionValue("queryFlag", "Y");
        pageContext.putSessionValue("SearchPhase", SearchPhase);
        pageContext.putSessionValue("SearchEmpName", SearchEmpName);
        pageContext.putSessionValue("SearchOrg", SearchOrg);
        pageContext.putSessionValue("YearFrom", strYearFrom);
        pageContext.putSessionValue("YearTo", strYearTo);
        pageContext.putSessionValue("SearchStatus", SearchStatus);
        pageContext.putSessionValue("SearchPerformer", SearchPerformer);

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprManagerListVOImpl vo = am.getAprManagerListVO1();
        vo.initQuery(SearchPhase, SearchEmpName, SearchOrg, strYearFrom, 
                     strYearTo, SearchStatus, SearchPerformer);

        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprManagerListVO1");
        tableBean.queryData(pageContext, false);
    }


    /*  批准按钮响应事件 */

    private void OnApproveButtonClick(OAPageContext pageContext, 
                                      OAWebBean webBean) {
        String strRtn = this.checkSelectedRow(pageContext, webBean);
        if ("N".equals(strRtn)) {
            this.ShowMessage(pageContext, "未选择绩效合同！", "error");
            return;
        }

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String str = am.checkManagerReviewBatchApprove();
        if (!"s".equals(str)) {
            this.ShowMessage(pageContext, str, "error");
            return;
        }

        strRtn = this.batchApprove(pageContext, webBean);
        if ("Y".equals(strRtn))
            this.ShowMessage(pageContext, "绩效合同审批提交成功！");
        else
            this.ShowMessage(pageContext, "绩效合同审批提交失败，请联系系统管理员！", "error");

        this.RefreshData(pageContext, webBean);
    }

    /* 检查用户是否选中了行
    * 返回 'Y'  选中了
    *      'N' 没选
    * */

    private String checkSelectedRow(OAPageContext pageContext, 
                                    OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprManagerListVOImpl vo = am.getAprManagerListVO1();
        int fetchedRowCount = vo.getFetchedRowCount();
        if (fetchedRowCount <= 0)
            return "N";

        RowSetIterator selectIter = vo.createRowSetIterator("selectIter");
        selectIter.setRangeStart(0);
        selectIter.setRangeSize(fetchedRowCount);
        for (int i = 0; i < fetchedRowCount; i++) {
            Row pRow = selectIter.getRowAtRangeIndex(0);
            String selectFlag = this.getRowAttribute(pRow, "SelectFlag");
            if ("Y".equals(selectFlag))
                return "Y";
        }

        selectIter.closeRowSetIterator();
        return "N";
    }

    /* 批量批准
    * 返回 'Y'  提交成功
    *      'N'  处理失败
    * */

    private String batchApprove(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);

        AprManagerListVOImpl vo = am.getAprManagerListVO1();
        int fetchedRowCount = vo.getFetchedRowCount();
        int successCount = 0;
        int failCount = 0;

        RowSetIterator selectIter = vo.createRowSetIterator("selectIter");
        selectIter.setRangeStart(0);
        selectIter.setRangeSize(fetchedRowCount);
        for (int i = 0; i < fetchedRowCount; i++) {
            Row pRow = selectIter.getRowAtRangeIndex(0);
            String selectFlag = this.getRowAttribute(pRow, "SelectFlag");
            if (!"Y".equals(selectFlag))
                continue;
            String appraisalId = this.getRowAttribute(pRow, "AppraisalId");
            if (!am.isInteger(appraisalId))
                continue;

            Integer iAprID = Integer.valueOf(appraisalId);
            am.CalRemarkInBatchAppr(iAprID); //保存每级考核的中间分数，并计算最终分数
            String strRtn = am.submitForApproval(iAprID);
            if ("Y".equals(strRtn))
                successCount = successCount + 1;
            else
                failCount = failCount + 1;
        }

        selectIter.closeRowSetIterator();
        if (failCount == 0)
            return "Y";
        else
            return "N";
    }

    /*  退回按钮响应事件 */

    private void OnRejectButtonClick(OAPageContext pageContext, 
                                     OAWebBean webBean) {
        String strRtn = this.checkSelectedRow(pageContext, webBean);
        if ("N".equals(strRtn)) {
            this.ShowMessage(pageContext, "未选择绩效合同！");
            return;
        }

        strRtn = this.batchReject(pageContext, webBean);
        if ("Y".equals(strRtn))
            this.ShowMessage(pageContext, "绩效合同审批退回成功！");
        else
            this.ShowMessage(pageContext, "绩效合同审批退回失败，请联系系统管理员！");

        this.RefreshData(pageContext, webBean);
    }

    /* 批量退回
     * 返回 'Y'  提交成功
     *      'N'  处理失败
     * */

    private String batchReject(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprManagerListVOImpl vo = am.getAprManagerListVO1();
        int fetchedRowCount = vo.getFetchedRowCount();
        int successCount = 0;
        int failCount = 0;

        RowSetIterator selectIter = vo.createRowSetIterator("selectIter");
        selectIter.setRangeStart(0);
        selectIter.setRangeSize(fetchedRowCount);
        for (int i = 0; i < fetchedRowCount; i++) {
            Row pRow = selectIter.getRowAtRangeIndex(0);
            String selectFlag = this.getRowAttribute(pRow, "SelectFlag");
            if (!"Y".equals(selectFlag))
                continue;
            String appraisalId = this.getRowAttribute(pRow, "AppraisalId");
            if (!am.isInteger(appraisalId))
                continue;

            Integer iAprID = Integer.valueOf(appraisalId);
            String strRtn = am.submitForReturn(iAprID);
            if ("Y".equals(strRtn))
                successCount = successCount + 1;
            else
                failCount = failCount + 1;
        }

        selectIter.closeRowSetIterator();
        if (failCount == 0)
            return "Y";
        else
            return "N";
    }


    /* 绩效合同列表 详细信息按钮 */

    private void OnDetailClick(OAPageContext pageContext) {
        String appraisalId = 
            this.GetParameterValue(pageContext, "appraisalid");
        String phaseId = this.GetParameterValue(pageContext, "phaseid");
        String statusId = this.GetParameterValue(pageContext, "statusid");
        String IsPromisee = 
            this.GetParameterValue(pageContext, "ispromisee"); // 'N'，当前操作者为本人

        String strUrl = "";
        if ("GOAL".equals(phaseId) && "WAITING".equals(statusId) && 
            "N".equals(IsPromisee))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ManagerGoalUpdatePG&appraisalid=" + 
                    appraisalId;
        else if ("GOAL".equals(phaseId) && "Y".equals(IsPromisee))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ManagerGOALReadOnlyPG&appraisalid=" + 
                    appraisalId;
        else if ("REVIEW".equals(phaseId) && "COMPLETED".equals(statusId))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ReviewFinishPG&appraisalid=" + 
                    appraisalId;
        else if ("REVIEW".equals(phaseId) && !"COMPLETED".equals(statusId) && 
                 "N".equals(IsPromisee))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ManagerReviewUpdatePG&appraisalid=" + 
                    appraisalId;
        else if ("REVIEW".equals(phaseId) && !"COMPLETED".equals(statusId) && 
                 "Y".equals(IsPromisee))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ManagerReviewReadOnlyPG&appraisalid=" + 
                    appraisalId;
        else { //暂时导向以前的页面
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG";
            pageContext.putSessionValue("appraisalId", appraisalId);
            pageContext.putSessionValue("phaseId", phaseId);
            pageContext.putSessionValue("statusId", statusId);
            pageContext.putSessionValue("role", "manager");
        }

        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
    }

    //  session 获取值

    private String GetSessionValue(OAPageContext pageContext, 
                                   String strSessionName) {
        String strRtn = "";
        if (pageContext.getSessionValue(strSessionName) != null && 
            !pageContext.getSessionValue(strSessionName).toString().equals("")) {
            strRtn = pageContext.getSessionValue(strSessionName).toString();
        }
        return strRtn;
    }

    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
    }

    /* 初始化页面Button
     // 解决问题: 点击提交按钮后，在确定提交考核流程界面，点击“是”按钮，点击2次，间隔时间2-3秒，之后流程自动跳过后一个审批节点
     // 在提交按钮添加前台javascript代码，点击提交按钮后，要求用户确认
     // 用户点击确定后，首先页面的按钮状态修改为disabled，执行后台处理代码，这样就避免了用户两次点击改按钮，造成重复提交的结果
     // 退回 不需要用户确认，直接执行
     * */

    private void initWebButton(OAPageContext pageContext, OAWebBean webBean) {
        String javaScript = 
            "javascript:function CtlBtnStatus(obj)" + "{ var flag=window.confirm(\"确定要提交选择的考核流程吗？\");" + 
            "if(flag) { obj.disabled=true; return true; } else return false;" + 
            "}";

        pageContext.putJavaScriptFunction("CtlBtnStatus", javaScript);

        OASubmitButtonBean submitButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Approve");
        if (submitButton != null)
            submitButton.setOnClick("javascript:return CtlBtnStatus(this);");
        OASubmitButtonBean returnButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Reject");
        if (returnButton != null)
            returnButton.setOnClick("javascript:this.disabled=true;return true;");
    }

    private String getRowAttribute(Row pRow, String strAttrName) {
        if (pRow.getAttribute(strAttrName) != null) {
            return pRow.getAttribute(strAttrName).toString();
        } else
            return "";
    }

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

    // exp:error - OAException.ERROR,  info - OAException.INFORMATION

    private void ShowMessage(OAPageContext pageContext, String strMessage, 
                             String exp) {
        if ("info".equals(exp)) {
            OAException tipMessage = 
                new OAException(strMessage, OAException.INFORMATION);
            pageContext.putDialogMessage(tipMessage);
        } else if ("error".equals(exp)) {
            OAException tipMessage = 
                new OAException(strMessage, OAException.ERROR);
            pageContext.putDialogMessage(tipMessage);
        }
    }

}
