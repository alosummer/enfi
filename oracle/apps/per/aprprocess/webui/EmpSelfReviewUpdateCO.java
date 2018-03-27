/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AprAMImpl;
import cux.oracle.apps.per.aprprocess.server.AprAttendVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprEmpSelfDetailVOImpl;

import cux.oracle.apps.per.aprprocess.server.AprKPIVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprSourceManualVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprSourceSystemVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprTaskVOImpl;
import cux.oracle.apps.per.aprprocess.server.AttendNoteVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.cabo.ui.UIConstants;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

/**
 * 绩效管理_员工自助，评分阶段，可修改界面（未提交之前）
 * created by yang.gang,2016-4-23
 */
public class EmpSelfReviewUpdateCO extends OAControllerImpl {
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
                                                       "updateContractKPITxn");

            if (!pageContext.isFormSubmission()) {
                this.initWeb(pageContext, webBean);
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "updateContractKPITxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
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
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String appraisalId = this.GetSessionValue(pageContext, "appraisalId");
        if (!am.isInteger(appraisalId))
            return;

        Integer iAprID = Integer.valueOf(appraisalId);

        if (pageContext.getParameter("Save") != null) //保存数据
            this.SaveData(pageContext, webBean);
        else if (pageContext.getParameter("Cancel") != null) //返回
            this.BackMainPG(pageContext, webBean);
        else if (pageContext.getParameter("Apply") != null) //提交审批
            this.SubmitApr(pageContext, webBean, iAprID);
    }

    /* 初始化页面数据 */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        //从地址栏获取参数
        String appraisalId = 
            this.GetParameterValue(pageContext, "appraisalid");
        String kpireportBackFlag = 
            this.GetParameterValue(pageContext, "kpirpfrom");

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        if ("".equals(appraisalId) || !am.isInteger(appraisalId)) {
            this.ShowMessage(pageContext, "绩效合同ID号获取失败，请刷新页面后重试！", "error");
            return;
        }

        Integer iAprID = Integer.valueOf(appraisalId);
        AprEmpSelfDetailVOImpl vo = am.getAprEmpSelfDetailVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !vo.isExecuted())
            vo.initQuery(iAprID);

        String PhaseId = vo.GetAttrValue("PhaseId"); //阶段
        String IsPromisee = 
            vo.GetAttrValue("IsPromisee"); //是否操作者为本人,'N'是本人, 'Y'不是

        if ("NO_DATA".equals(PhaseId) || "NO_DATA".equals(IsPromisee)) {
            this.ShowMessage(pageContext, "绩效合同状态与页面权限不一致，请刷新页面后重试！");
            this.HideElement(webBean);
            return;
        } else if (!"REVIEW".equals(PhaseId)) {
            this.ShowMessage(pageContext, "绩效合同状态与页面权限不一致，请刷新页面后重试！");
            this.HideElement(webBean);
            return;
        } else if ("Y".equals(IsPromisee)) {
            String strUrl = 
                "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfReviewReadOnlyPG&appraisalid=".concat(appraisalId);
            pageContext.forwardImmediately(strUrl, null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
            return;
        }
        pageContext.putSessionValue("appraisalId", appraisalId);
        pageContext.putSessionValue("aprkpistatus", 
                                    "selfreviewupdate"); //KpiRptPG返回时用来确定页面的标识
        this.SetTitle(pageContext, webBean);
        this.initWebButton(pageContext, webBean);
        this.initWebTable(pageContext, webBean, iAprID, kpireportBackFlag);
    }

    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
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

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

    /* 权限异常时隐藏页面操作元素 */

    private void HideElement(OAWebBean webBean) {
        this.SetObjRendered(webBean, "Save", false);
        this.SetObjRendered(webBean, "Apply", false);
        this.SetObjRendered(webBean, "Cancel", false);
    }

    /* 设置页面元素隐藏 */

    private void SetObjRendered(OAWebBean webBean, String objName, 
                                boolean bvalue) {
        OAWebBean obj = webBean.findChildRecursive(objName);
        if (obj == null)
            return;
        obj.setRendered(bvalue);
    }

    /* 设置页面标题 */

    private void SetTitle(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprEmpSelfDetailVOImpl vo = am.getAprEmpSelfDetailVO1();
        String aprYear = vo.GetAttrValue("AppraisalYear");
        if (aprYear != null && !"".equals(aprYear)) {
            OAPageLayoutBean page = (OAPageLayoutBean)webBean;
            String strTitle = aprYear + "年度 绩效合同";
            page.setTitle(strTitle);
            page.setWindowTitle(strTitle);
        }
    }

    /* 初始化页面Button
       用户点击提交后，首先页面的按钮状态修改为disabled，执行后台处理代码，这样就避免了用户两次点击改按钮，造成重复提交的结果
     * */

    private void initWebButton(OAPageContext pageContext, OAWebBean webBean) {
        String javaScript = 
            "javascript:function CtlBtnStatus(obj)" + "{ obj.disabled=true; return true;" + 
            "}";

        pageContext.putJavaScriptFunction("CtlBtnStatus", javaScript);
        OASubmitButtonBean submitButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Apply");
        if (submitButton != null)
            submitButton.setOnClick("javascript:return CtlBtnStatus(this);");
    }

    /* 初始化页面Table */

    private void initWebTable(OAPageContext pageContext, OAWebBean webBean, 
                              Integer iAprID, String kpireportBackFlag) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprSourceManualVOImpl manaualvo = am.getAprSourceManualVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !manaualvo.isExecuted())
            manaualvo.initQuery(iAprID);
        AprSourceSystemVOImpl systemvo = am.getAprSourceSystemVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !systemvo.isExecuted())
            systemvo.initQuery(iAprID);
        AprAttendVOImpl attvo = am.getAprAttendVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !attvo.isExecuted())
            attvo.initQuery(iAprID, "REVIEW");
        AttendNoteVOImpl notevo = am.getAttendNoteVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !notevo.isExecuted())
            notevo.initQuery(iAprID, "REVIEW");

        //实际值
        OAMessageStyledTextBean actualValueBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("ActualValue3");
        actualValueBean.setAttributeValue(UIConstants.DESTINATION_ATTR, 
                                          new OADataBoundValueViewObject(actualValueBean, 
                                                                         "ActualValueLink", 
                                                                         "AprSourceSystemVO1"));

        this.AdvTableQueryData(pageContext, webBean, "AprSourceManualVO2");
        this.AdvTableQueryData(pageContext, webBean, "AprSourceSystemVO2");
        this.TableQueryData(pageContext, webBean, "AprAttendVO1");
        this.CalculateWeight(pageContext, webBean);
    }

    /* 表格显示VO数据 */

    private void TableQueryData(OAPageContext pageContext, OAWebBean webBean, 
                                String tablename) {
        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }

    /* 表格显示VO数据 */

    private void AdvTableQueryData(OAPageContext pageContext, 
                                   OAWebBean webBean, String tablename) {
        OAAdvancedTableBean tableBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }

    /* 计算权重（自动累计） */

    private void CalculateWeight(OAPageContext pageContext, 
                                 OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprSourceManualVOImpl manaualvo = am.getAprSourceManualVO1();
        AprSourceSystemVOImpl systemvo = am.getAprSourceSystemVO1();

        String strTitle = "";
        Float d = manaualvo.GetWeightTotal();
        if (d > 0) {
            strTitle = "手工评分指标       权重（自动累计）：".concat(String.valueOf(d));
            if (".0".equals(strTitle.substring(strTitle.length() - 2, 
                                               strTitle.length())))
                strTitle = strTitle.substring(0, strTitle.length() - 2);
            OAHeaderBean kpi_header = 
                (OAHeaderBean)webBean.findIndexedChildRecursive("regionSourceManual");
            kpi_header.setText(pageContext, strTitle);
        }

        d = systemvo.GetWeightTotal();
        if (d > 0) {
            strTitle = "系统来源指标       权重（自动累计）：".concat(String.valueOf(d));
            if (".0".equals(strTitle.substring(strTitle.length() - 2, 
                                               strTitle.length())))
                strTitle = strTitle.substring(0, strTitle.length() - 2);
            OAHeaderBean kpi_header = 
                (OAHeaderBean)webBean.findIndexedChildRecursive("regionSourceSys");
            kpi_header.setText(pageContext, strTitle);
        }
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

    /* 保存数据 */

    private void SaveData(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String strRtn = this.checkEmpInput(pageContext, webBean);
        if ("s".equals(strRtn)) {
            if (am.commit()) {
                this.ShowMessage(pageContext, "数据保存成功！");
            } else
                this.ShowMessage(pageContext, "没有数据修改需要保存！");
        } else
            this.ShowMessage(pageContext, "数据验证失败：".concat(strRtn), "error");
    }

    /* 检查用户输入 */

    private String checkEmpInput(OAPageContext pageContext, 
                                 OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprSourceManualVOImpl manaualvo = am.getAprSourceManualVO1();
        String strRtn = "s";

        int rowCount = manaualvo.getRowCount();
        RowSetIterator deptPersonIter = 
            manaualvo.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String SelfEvalValue = this.getRowAttribute(pRow, "SelfEvalValue");
            String str = this.checkEmpSelfEvalValue(SelfEvalValue);
            if (!"s".equals(str)) {
                strRtn = str;
                break;
            }
        }
        deptPersonIter.closeRowSetIterator();

        return strRtn;
    }

    private String getRowAttribute(Row pRow, String strAttrName) {
        if (pRow.getAttribute(strAttrName) != null) {
            return pRow.getAttribute(strAttrName).toString();
        } else
            return "";
    }

    /* 判断自评分的有效性  */

    private String checkEmpSelfEvalValue(String strWeight) {
        String strRtn = "s";
        if ("".equals(strWeight)) {
            strRtn = "自评分不能为空！";
        } else if (!this.isFloat(strWeight)) {
            strRtn = "自评分为0-100范围内的整数或者小数！";
        } else {
            Float fWeight = Float.parseFloat(strWeight);
            if (fWeight < 0 || fWeight > 100) {
                strRtn = "自评分为0-100范围内的整数或者小数！";
            }
        }

        return strRtn;
    }

    /* 判断字符串是否是浮点数 */

    private boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /* 返回绩效合同列表页面 */

    private void BackMainPG(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        am.rollback();
        TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                 "updateContractKPITxn"); //关闭事务

        pageContext.putSessionValue("backFromContract", "Y");
        String strUrl = 
            "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfMainPG";
        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
    }

    /* 提交数据 */

    private void SubmitApr(OAPageContext pageContext, OAWebBean webBean, 
                           Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String strRtn = this.checkEmpInput(pageContext, webBean);
        if (!"s".equals(strRtn)) {
            this.ShowMessage(pageContext, "数据验证失败：".concat(strRtn), "error");
            return;
        }

        boolean bflag = am.commit(); //数据保存
        TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                 "updateContractKPITxn");

        am.computeSelfScore(iAprID); //计算自评总得分
        strRtn = am.submitForApproval(iAprID);
        if ("Y".equals(strRtn)) { //提交成功，返回
            this.ShowMessage(pageContext, "绩效合同审批提交成功！");

            String strUrl = 
                "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfReviewReadOnlyPG&appraisalid=".concat(String.valueOf(iAprID));
            pageContext.forwardImmediately(strUrl, null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
            return;
        }

        this.ShowMessage(pageContext, "绩效合同审批提交失败，请联系系统管理员！", "error");

        // 虽然提交失败，但允许用户再次保存、提交
        if (bflag) {
            AprSourceManualVOImpl manaualvo = am.getAprSourceManualVO1();
            manaualvo.initQuery(iAprID);
            AprSourceSystemVOImpl systemvo = am.getAprSourceSystemVO1();
            systemvo.initQuery(iAprID);

            this.AdvTableQueryData(pageContext, webBean, "AprSourceManualVO2");
            this.AdvTableQueryData(pageContext, webBean, "AprSourceSystemVO2");
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "updateContractKPITxn"); //允许再次保存，所以再开启事务             
        }
    }

}
