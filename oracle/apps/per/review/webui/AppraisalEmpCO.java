/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.AppraisalEmpVOImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAQueryUtils;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.bali.share.util.BooleanUtils;

import oracle.jbo.Row;

/**
 * Controller for ...
 */
public class AppraisalEmpCO extends OAControllerImpl {
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

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String role = pageContext.getParameter("role");
        /*for test 20100209 zs role = "manager";*/
        if (role != null)
            pageContext.putSessionValue("role", role);
        else
            role = (String)pageContext.getSessionValue("role");
        if (role == null)
            // role = "manager"; // just for testing
            role = "selfservice";
        pageContext.putSessionValue("role", role);

        Serializable params[] = { role };
        /*20100209 zs start*/
        //从员工绩效合同界面返回时，backFromContractFlag会被设置为true
        String backFromContractFlag = 
            (String)pageContext.getSessionValue("backFromContract");
        // add by fl at 20091019
        //既不是从绩效合同界面返回，也不是提交某员工的绩效合同时（基本等价于初次进入），根据角色信息刷新列表
        if (!(pageContext.getParameter("ApproveYesButton") != null || 
              (backFromContractFlag != null && 
               backFromContractFlag.equals("Y")))) {
            am.invokeMethod("initContract", params);
        }
        //从员工绩效合同界面返回，根据选择的查询条件刷新列表
        else if (backFromContractFlag != null && 
                 backFromContractFlag.equals("Y")) {
            OAQueryUtils.checkSelectiveSearchCriteria(pageContext, webBean);

            if (role == null)
                role = (String)pageContext.getSessionValue("role");
            String phase = pageContext.getParameter("SearchPhase");
            if (phase == null)
                phase = (String)pageContext.getSessionValue("SearchPhase");
            String personId = pageContext.getParameter("SearchPersonId");
            if (personId == null)
                personId = 
                        (String)pageContext.getSessionValue("SearchPersonId");
            String orgId = pageContext.getParameter("SearchOrg");
            if (orgId == null)
                orgId = (String)pageContext.getSessionValue("SearchOrg");
            String yearFrom = pageContext.getParameter("SearchYearFrom");
            if (yearFrom == null)
                yearFrom = 
                        (String)pageContext.getSessionValue("SearchYearFrom");
            String yearTo = pageContext.getParameter("SearchYearTo");
            if (yearTo == null)
                yearTo = (String)pageContext.getSessionValue("SearchYearTo");
            String status = pageContext.getParameter("SearchStatus");
            if (status == null)
                status = (String)pageContext.getSessionValue("SearchStatus");
            String empName = pageContext.getParameter("SearchEmpName");
            if (empName == null)
                empName = (String)pageContext.getSessionValue("SearchEmpName");

            Boolean executeQuery = BooleanUtils.getBoolean(false);
            Serializable[] parameters = 
            { role, phase, personId, orgId, yearFrom, yearTo, status, empName, 
              executeQuery };

            Class[] paramTypes = 
            { String.class, String.class, String.class, String.class, 
              String.class, String.class, String.class, String.class, 
              Boolean.class };
            am.invokeMethod("initQuery", parameters, paramTypes);
        }
        /*20100209 zs end*/
        // am.invokeMethod("initContract", params);  remark by fl at 20091019

        //AppraisalEmpVOImpl vo = (AppraisalEmpVOImpl)am.findViewObject("AppraisalEmpVO1");
        //vo.initQuery();
        //20090907

        if (!pageContext.isBackNavigationFired(true)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "updateAppraisalEmpTxn");
            if (!pageContext.isFormSubmission()) {
                //am.invokeMethod("initContract");
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "updateAppraisalEmpTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AppraisalEmpVO1");
        //remark by fl at 20091019
        //tableBean.queryData(pageContext, false);
        //end remark by fl at 20091019

        // add by fl 20091019
        //if(pageContext.getParameter("ApproveYesButton") == null)
        if (!(pageContext.getParameter("ApproveYesButton") != null || 
              (backFromContractFlag != null && 
               backFromContractFlag.equals("Y")))) {
            tableBean.queryData(pageContext, false);
        }
        tableBean.setSelectionDisabledBindingAttr("IsPromisee");
        String result = am.invokeMethod("getDisplaySubmitFlag").toString();

        OASubmitButtonBean approveBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Approve");
        OASubmitButtonBean rejectBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Reject");
        //add by dl -- disabled
        //    if(rejectBtn != null) rejectBtn.setRendered(false);
        //      rejectBtn = null;
        // end dl
        // System.out.println(result +" and " + role);
        //经理自助并且可以显示提交按钮时，才显示提交按钮、退回按钮、多选框等控件；其他情况这3个控件都不显示
        if (result != null && result.equals("Y") && "manager".equals(role)) {
            if (approveBtn != null)
                approveBtn.setRendered(true);
            if (rejectBtn != null)
                rejectBtn.setRendered(true);
            tableBean.setSelectionDisplayed(true);
        } else {
            if (approveBtn != null)
                approveBtn.setRendered(false);
            if (rejectBtn != null)
                rejectBtn.setRendered(false);
            tableBean.setSelectionDisplayed(false);
        }
        tableBean.prepareForRendering(pageContext);

        // add by yang.gang, 2014-9-22,begin
        // 解决问题: 点击提交按钮后，在确定提交考核流程界面，点击“是”按钮，点击2次，间隔时间2-3秒，之后流程自动跳过后一个审批节点
        // 在提交按钮添加前台javascript代码，点击提交按钮后，要求用户确认
        // 用户点击确定后，首先页面的按钮状态修改为disabled，执行后台处理代码，这样就避免了用户两次点击改按钮，造成重复提交的结果
        // 退回 不需要用户确认，直接执行
        String javaScript = 
            "javascript:function CtlBtnStatus(obj)" + "{ var flag=window.confirm(\"确定要提交选择的考核流程吗？\");" + 
            "if(flag) { obj.disabled=true; return true; } else return false;" + 
            "}";

        pageContext.putJavaScriptFunction("CtlBtnStatus", javaScript);

        OASubmitButtonBean submitButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Approve");
        if (submitButton != null) {
            submitButton.setOnClick("javascript:return CtlBtnStatus(this);");
        }

        OASubmitButtonBean returnButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Reject");
        if (returnButton != null) {
            returnButton.setOnClick("javascript:this.disabled=true;return true;");
        }

        // add by yang.gang, 2014-9-22,end
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if (pageContext.getParameter("customizeSubmitButton") != null) {
            OAQueryBean queryBean = 
                (OAQueryBean)webBean.findChildRecursive("QueryAppraisalRN");
            String currentPanel = queryBean.getCurrentSearchPanel();
            // Handle the "Go" button click on the simple search panel
            if (SEARCH.equals(currentPanel) && 
                queryBean.getGoButtonName() != null) {
                AppraisalEmpVOImpl vo = 
                    (AppraisalEmpVOImpl)am.findViewObject("AppraisalEmpVO1");
                // retrieve your item and process it here.
                String role = pageContext.getParameter("role");

                if (role == null)
                    role = (String)pageContext.getSessionValue("role");
                if (role != null) {
                    Number personType = 0;
                    if (role.equals("manager"))
                        personType = 1;
                    else if (role.equals("selfservice"))
                        personType = 0;

                    vo.setWhereClauseParams(null);
                    vo.setWhereClauseParam(0, personType);
                }

                vo.setWhereClause(null);

                //String jobId = pageContext.getParameter("SearchJobId");
                //Serializable parameters[] = {groupName, orgId, jobId};
                //am.invokeMethod("initQuery", parameters);
            }
        }

        // Handle delete event
        if ("detail".equals(pageContext.getParameter("event"))) {
            String appraisalId = pageContext.getParameter("appraisalId");
            pageContext.putSessionValue("appraisalId", appraisalId);
            //zs
            String phaseId = pageContext.getParameter("phaseId");
            pageContext.putSessionValue("phaseId", phaseId);
            String statusId = pageContext.getParameter("statusId");
            pageContext.putSessionValue("statusId", statusId);
            //zs  
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }
        //add by dl handle batch options.edit by fl at 20091019
        if (pageContext.getParameter("Approve") != null) {
            //added by huangbo 2013-10-07 新增提交前的验证，当前用户是否为提交绩效合同的当前处理人
            AppraisalEmpVOImpl appraisalEmpVO = 
                (AppraisalEmpVOImpl)am.findViewObject("AppraisalEmpVO1");
            Row[] rows = appraisalEmpVO.getFilteredRows("SelectFlag", "Y");
            for (int i = 0; i < rows.length; ++i) {
                if (rows[i].getAttribute("IsPromisee") != null) {
                    String isPromise = 
                        rows[i].getAttribute("IsPromisee").toString();
                    if ("Y".equals(isPromise)) {
                        OAException message = 
                            new OAException("当前用户不是所选绩效合同的当前处理人，无法提交绩效合同！", 
                                            OAException.ERROR);
                        pageContext.putDialogMessage(message);
                        return;
                    }
                }
            }
            //added by huangbo 2013-10-07 新增提交前的验证，当前用户是否为提交绩效合同的当前处理人 -end-

            /* edit by yang.gang 2014-9-22
              * 通过 在提交按钮添加前台javascript代码，点击提交按钮后，要求用户确认
              * 用户点击确定后，首先页面的按钮状态修改为disabled，执行后台处理代码，这样就避免了用户两次点击改按钮，造成重复提交的结果。
              * 注释掉以前的
            OAException mainMessage =
                new OAException("CUX", "CUX_WF_SUBMIT_ASK_CONF", null,
                                OAException.WARNING, null);
            OADialogPage dialogPage =
                new OADialogPage(OAException.WARNING, mainMessage, null, "",
                                 "");
            dialogPage.setOkButtonItemName("ApproveYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("ApproveYesButton") != null) {
        */
            String msg = (String)am.invokeMethod("batchApprove");
            if (msg != null && msg.equals("CUX_JX_BATCH_APPROVE_SUCCESS")) {
                OAException confirmMessage = 
                    new OAException("CUX", msg, null, OAException.CONFIRMATION, 
                                    null);
                pageContext.putDialogMessage(confirmMessage);
            } else {
                OAException confirmMessage = 
                    new OAException("CUX", msg, null, OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }
        }
        if (pageContext.getParameter("Reject") != null) {
            String msg = (String)am.invokeMethod("batchReject");
            if (msg != null && msg.equals("CUX_JX_BATCH_REJECT_SUCCESS")) {
                OAException confirmMessage = 
                    new OAException("CUX", msg, null, OAException.CONFIRMATION, 
                                    null);
                pageContext.putDialogMessage(confirmMessage);
            } else {
                OAException confirmMessage = 
                    new OAException("CUX", msg, null, OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }
        }


        //end dl

        //add by fl handle manual search.20091106
        if (pageContext.getParameter("Search") != null) {

            OAQueryUtils.checkSelectiveSearchCriteria(pageContext, webBean);

            // Get the user's search criteria from the request.
            String role = pageContext.getParameter("role");
            if (role == null)
                role = (String)pageContext.getSessionValue("role");
            String phase = pageContext.getParameter("SearchPhase");
            if (phase != null)
                pageContext.putSessionValue("SearchPhase", phase);
            String personId = pageContext.getParameter("SearchPersonId");
            if (personId != null)
                pageContext.putSessionValue("SearchPersonId", personId);
            String orgId = pageContext.getParameter("SearchOrg");
            if (orgId != null)
                pageContext.putSessionValue("SearchOrg", orgId);
            String yearFrom = pageContext.getParameter("SearchYearFrom");
            if (yearFrom != null)
                pageContext.putSessionValue("SearchYearFrom", yearFrom);
            String yearTo = pageContext.getParameter("SearchYearTo");
            if (yearTo != null)
                pageContext.putSessionValue("SearchYearTo", yearTo);
            String status = pageContext.getParameter("SearchStatus");
            if (status != null)
                pageContext.putSessionValue("SearchStatus", status);
            String empName = pageContext.getParameter("SearchEmpName");
            if (empName != null)
                pageContext.putSessionValue("SearchEmpName", empName);

            // System.out.println("role:" +role);
            // System.out.println("phase:" +phase);
            // System.out.println("yearFrom:" +yearFrom);

            // OAApplicationModule am = pageContext.getApplicationModule(webBean);
            Boolean executeQuery = BooleanUtils.getBoolean(false);
            Serializable[] parameters = 
            { role, phase, personId, orgId, yearFrom, yearTo, status, empName, 
              executeQuery };

            Class[] paramTypes = 
            { String.class, String.class, String.class, String.class, 
              String.class, String.class, String.class, String.class, 
              Boolean.class };
            am.invokeMethod("initQuery", parameters, paramTypes);

            OATableBean table = 
                (OATableBean)webBean.findChildRecursive("AppraisalEmpVO1");
            table.queryData(pageContext, false);
        }

    }
}
