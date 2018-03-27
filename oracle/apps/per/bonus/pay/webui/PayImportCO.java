/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.pay.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsDeptPersonVOImpl;

import java.io.Serializable;

import cux.oracle.apps.per.bonus.pay.server.PayAMImpl;

import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import com.sun.java.util.collections.HashMap;

import java.text.SimpleDateFormat;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAQueryUtils;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OAMultipleSelectionBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.bali.share.util.BooleanUtils;

/**
 * 奖金审批页面，通常由拥有以下职责的人员进行操作
 * 赛迪股份_人力资源_薪酬（奖金）
 * 集团总部_人力资源_薪酬（奖金） 
 * 赛迪上海_人力资源_薪酬（奖金）
 */
public class PayImportCO extends OAControllerImpl {
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

        OAMessageChoiceBean msPayperiod = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("Payperiod");
        OAMessageChoiceBean msDepperiod = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("period");
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM");
        String time = tempDate.format(new java.util.Date());
        String per2 = (String)pageContext.getSessionValue("SUCCESS");
        if (msPayperiod != null && !"N".equals(per2)) {
            msPayperiod.setValue(pageContext, time);
            msDepperiod.setValue(pageContext, time);
        }

        OAAdvancedTableBean tableBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("PersonDisTable");
        tableBean.setSelectionDisabledBindingAttr("IsStatus");

        pageContext.putSessionValue("SUCCESS", "Y");
        webBean.findChildRecursive("Export").setViewUsageName("PersonDisVo1");

        int iOrgID = this.GetCurrentResOrgID(pageContext, webBean);
        OAMessageChoiceBean msDisOrg = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DisOrg");
        OAMessageChoiceBean msDeptDisOrg = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DeptDisOrg");
        OAMessageChoiceBean msExtEmpOrg = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("ExtEmpOrg");
        OAMessageChoiceBean msIntEmpOrg = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("IntEmpOrg");
        msDisOrg.setPickListCacheEnabled(false);
        msDeptDisOrg.setPickListCacheEnabled(false);
        msExtEmpOrg.setPickListCacheEnabled(false);
        msExtEmpOrg.setBindingParams(new Object[] { iOrgID });
        msIntEmpOrg.setPickListCacheEnabled(false);
        msIntEmpOrg.setBindingParams(new Object[] { iOrgID });

        String strPayrollFlag = 
            (String)pageContext.getSessionValue("PAYROLL_CHANGE_FLAG"); //页面是否从选择指定工资单页面返回
        if ("Y".equals(strPayrollFlag)) { //是返回，且发生了数值变化
            this.ExtDistQueryDefault(pageContext, webBean);
            return;
        }
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
        String event = pageContext.getParameter("event");

        if (pageContext.getParameter("Approve") != null) // 提交按钮
            this.ApproveDis(pageContext, webBean);
        else if (pageContext.getParameter("ApproveYesButton") != null) //确定提交
            this.ApproveDisConfirm(pageContext, webBean);
        else if (pageContext.getParameter("Reject") != null)
            this.RejectDis(pageContext, webBean);
        else if (pageContext.getParameter("Search") != null)
            this.ExtDistQuery(pageContext, webBean);
        else if (pageContext.getParameter("DeptQuery") != null)
            this.DeptQuery(pageContext, webBean);
        else if (event.equals("lovPrepare")) //选择人员工号Lov，弹出之前，传选择的日期和部门值  
            this.LovPrepare(pageContext);
        else if ("updatePayroll".equals(event)) //指定工资单
            this.OnUpdatePayrollImageClick(pageContext);
    } // end processFormRequest

    /* 获取当前用户对于职责 的公司id */

    private int GetCurrentResOrgID(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);
        return am.GetCurrentRespOrgID();
    }

    /* 提交奖金记录 */

    private void ApproveDis(OAPageContext pageContext, OAWebBean webBean) {
        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);
        int select_count = am.GetSelectRowCount();
        if (select_count == 0) {
            OAException confirmMessage = 
                new OAException("CUX", "CUX_ELEMENT_NO_SELECTED", null, 
                                OAException.ERROR, null);
            pageContext.putDialogMessage(confirmMessage);
            return;
        }
        String strPayRoll = am.ValidateSelectRowPayroll(); //验证多工资单的情况
        if (!"S".equals(strPayRoll)) {
            this.ShowMessage(pageContext, strPayRoll);
            return;
        }

        OAException mainMessage = 
            new OAException("CUX", "CUX_ELEMENT_IMPORT_ASK_CONF", null, 
                            OAException.WARNING, null);
        OADialogPage dialogPage = 
            new OADialogPage(OAException.WARNING, mainMessage, null, "", "");
        dialogPage.setOkButtonItemName("ApproveYesButton");
        dialogPage.setOkButtonToPost(true);
        dialogPage.setNoButtonToPost(true);
        dialogPage.setPostToCallingPage(true);

        pageContext.putSessionValue("SUCCESS", "N");
        pageContext.redirectToDialogPage(dialogPage);
    }

    /* 确认提交奖金记录 */

    private void ApproveDisConfirm(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);
        String msg = am.batchApprove();
        if (msg != null && msg.equals("CUX_ELEMENT_IMPORT_SUCCESS")) {
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

    /* 退回奖金记录 */

    private void RejectDis(OAPageContext pageContext, OAWebBean webBean) {
        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);
        int select_count = am.GetSelectRowCount();
        if (select_count == 0) {
            OAException confirmMessage = 
                new OAException("CUX", "CUX_ELEMENT_NO_SELECTED", null, 
                                OAException.ERROR, null);
            pageContext.putDialogMessage(confirmMessage);
            return;
        }
        String Note = 
            (String)((OAMessageTextInputBean)webBean.findChildRecursive("Note")).getValue(pageContext);
        if (Note == null) {
            Note = "-99";
        }
        ;
        pageContext.putSessionValue("SUCCESS", "N");
        String msg = am.batchReject(Note);
        if (msg != null && msg.equals("CUX_ELEMENT_REJECT_SUCCESS")) {
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

    /* 员工奖金审批，查询 */

    private void ExtDistQuery(OAPageContext pageContext, OAWebBean webBean) {
        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);
        OAQueryUtils.checkSelectiveSearchCriteria(pageContext, webBean);

        String period = pageContext.getParameter("Payperiod");
        String orgid = pageContext.getParameter("DisOrg");
        int iOrgID = 0;
        if (!"".equals(orgid))
            iOrgID = Integer.parseInt(orgid);

        int iEmpOrgID = 0;
        String strEmpOrgid = pageContext.getParameter("ExtEmpOrg");
        if (strEmpOrgid != null && !"".equals(strEmpOrgid))
            iEmpOrgID = Integer.parseInt(strEmpOrgid);

        String employee = pageContext.getParameter("Employee");
        String status = pageContext.getParameter("Status");

        pageContext.putSessionValue("QueryPeriod", 
                                    period); //保存查询条件，供返回时，若工资单发生了变化，则重新刷新一次
        pageContext.putSessionValue("QueryOrgID", orgid);
        pageContext.putSessionValue("QueryEmpOrgID", strEmpOrgid);
        pageContext.putSessionValue("QueryEmp", employee);
        pageContext.putSessionValue("QueryStatus", status);

        am.initQuery(period, iOrgID, iEmpOrgID, employee, status);

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PersonDisTable");
        table.queryData(pageContext, false);
    }

    /* 员工奖金审批，查询 */

    private void ExtDistQueryDefault(OAPageContext pageContext, 
                                     OAWebBean webBean) {
        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);

        String period = this.GetSessionValue(pageContext, "QueryPeriod");
        String orgid = this.GetSessionValue(pageContext, "QueryOrgID");
        int iOrgID = 0;
        if (!"".equals(orgid))
            iOrgID = Integer.parseInt(orgid);

        int iEmpOrgID = 0;
        String strEmpOrgid = 
            this.GetSessionValue(pageContext, "QueryEmpOrgID");
        if (!"".equals(strEmpOrgid))
            iEmpOrgID = Integer.parseInt(strEmpOrgid);

        String employee = this.GetSessionValue(pageContext, "QueryEmp");
        String status = this.GetSessionValue(pageContext, "QueryStatus");

        am.initQuery(period, iOrgID, iEmpOrgID, employee, status);

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PersonDisTable");
        table.queryData(pageContext, false);
    }

    /* 计奖部门奖金查询（已提交的奖金记录），查询给本部门人员发放数据 */

    private void DeptQuery(OAPageContext pageContext, OAWebBean webBean) {
        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);
        String period = pageContext.getParameter("Period").toString();

        int iOrgID = 0;
        if (pageContext.getParameter("DeptDisOrg") != null && 
            !"".equals(pageContext.getParameter("DeptDisOrg").toString())) {
            String orgid = pageContext.getParameter("DeptDisOrg").toString();
            iOrgID = Integer.parseInt(orgid);
        }

        int iEmpOrgID = 0;
        String strEmpOrgid = pageContext.getParameter("IntEmpOrg");
        if (strEmpOrgid != null && !"".equals(strEmpOrgid))
            iEmpOrgID = Integer.parseInt(strEmpOrgid);

        am.initOtherQuery(period, iOrgID, iEmpOrgID);
        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("OtherPersonDisTable");
        table.queryData(pageContext, false);
    }

    /* 根据选择的日期，部门条件初始化人员列表 */

    private void LovPrepare(OAPageContext pageContext) {
        String period = pageContext.getParameter("Payperiod");
        pageContext.putSessionValue("Payperiod", period);
        String strOrgid = pageContext.getParameter("DisOrg");
        if (strOrgid != null && !"".equals(strOrgid))
            pageContext.putSessionValue("PayOrgID", strOrgid);
        else
            pageContext.putSessionValue("PayOrgID", "0");

        String strEmpOrgid = pageContext.getParameter("ExtEmpOrg");
        if (strEmpOrgid != null && !"".equals(strEmpOrgid))
            pageContext.putSessionValue("EmpOrgID", strOrgid);
        else
            pageContext.putSessionValue("EmpOrgID", "0");
    }

    /* 指定工资单页面 */

    private void OnUpdatePayrollImageClick(OAPageContext pageContext) {
        String strPerDisID = this.GetParameterValue(pageContext, "dist_id");
        String strEmpName = this.GetParameterValue(pageContext, "emp_name");
        pageContext.putSessionValue("PerDistID", strPerDisID);
        pageContext.putSessionValue("EmpName", strEmpName);
        pageContext.putSessionValue("PAYROLL_CHANGE_FLAG", 
                                    "N"); //在返回时判断工资单是否已经发生了变化
        pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/pay/webui/AssignPayrollPG", 
                                       null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
    }

    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
    }

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

    private String GetSessionValue(OAPageContext pageContext, 
                                   String strSessionName) {
        String strRtn = "";
        if (pageContext.getSessionValue(strSessionName) != null && 
            !pageContext.getSessionValue(strSessionName).toString().equals("")) {
            strRtn = pageContext.getSessionValue(strSessionName).toString();
        }
        return strRtn;
    }

}
