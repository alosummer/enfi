/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AddVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprAMImpl;

import cux.oracle.apps.per.aprprocess.server.AprAttendVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprEmpSelfDetailVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprKPIVOImpl;

import cux.oracle.apps.per.aprprocess.server.AprTaskVOImpl;
import cux.oracle.apps.per.aprprocess.server.AttendNoteVOImpl;
import cux.oracle.apps.per.aprprocess.server.BehaviourVOImpl;
import cux.oracle.apps.per.aprprocess.server.KPILovVOImpl;

import cux.oracle.apps.per.aprprocess.server.SubVOImpl;

import java.io.Serializable;

import java.util.Enumeration;
import java.util.Hashtable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
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
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * 绩效管理_员工自助，目标设定阶段，可修改界面（未提交之前）
 * created by yang.gang,2016-4-23
 */
public class EmpSelfGoalUpdateCO extends OAControllerImpl {
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
            this.SetInsertButton(webBean);

            if (!pageContext.isFormSubmission()) {
                this.initWeb(pageContext, webBean);
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "updateContractKPITxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }
    }

    /* 初始化页面数据 */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        //从地址栏获取参数
        String appraisalId = 
            this.GetParameterValue(pageContext, "appraisalid");

        // 若地址栏参数为空，则可能是删除合同具体的KPI指标或者工作任务后返回
        if ("".equals(appraisalId)) {
            appraisalId = this.GetSessionValue(pageContext, "appraisalId");
        }

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);

        if ("".equals(appraisalId) || !am.isInteger(appraisalId)) {
            this.ShowMessage(pageContext, "绩效合同ID号获取失败，请刷新页面后重试！");
            return;
        }

        Integer iAprID = Integer.valueOf(appraisalId);
        AprEmpSelfDetailVOImpl vo = am.getAprEmpSelfDetailVO1();
        vo.initQuery(iAprID);

        String PhaseId = vo.GetAttrValue("PhaseId"); //阶段
        String IsPromisee = 
            vo.GetAttrValue("IsPromisee"); //是否操作者为本人,'N'是本人, 'Y'不是

        if ("NO_DATA".equals(PhaseId) || "NO_DATA".equals(IsPromisee)) {
            this.ShowMessage(pageContext, "绩效合同状态与页面权限不一致，请刷新页面后重试！");
            this.HideElement(webBean);
            return;
        } else if (!"GOAL".equals(PhaseId)) {
            this.ShowMessage(pageContext, "绩效合同状态与页面权限不一致，请刷新页面后重试！");
            this.HideElement(webBean);
            return;
        } else if ("Y".equals(IsPromisee)) {
            String strUrl = 
                "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfGoalReadOnlyPG&appraisalid=".concat(appraisalId);
            pageContext.forwardImmediately(strUrl, null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
            return;
        }
        pageContext.putSessionValue("appraisalId", appraisalId);
        this.SetTitle(pageContext, webBean);
        this.initWebButton(pageContext, webBean, appraisalId);
        this.initWebTable(pageContext, webBean, iAprID);
    }

    /* 权限异常时隐藏页面操作元素 */

    private void HideElement(OAWebBean webBean) {
        this.SetObjRendered(webBean, "CopyPrevious", false);
        this.SetObjRendered(webBean, "Save", false);
        this.SetObjRendered(webBean, "Apply", false);
        this.SetObjRendered(webBean, "Cancel", false);
        this.SetObjRendered(webBean, "rsBehaviour", false);
        this.SetObjRendered(webBean, "rsAdd", false);
        this.SetObjRendered(webBean, "rsSub", false);

        OATableBean kpi_tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprKPIVO1");
        kpi_tableBean.setInsertable(false);
        OATableBean task_tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprTaskVO1");
        task_tableBean.setInsertable(false);
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

    /* 初始化页面Table */

    private void initWebTable(OAPageContext pageContext, OAWebBean webBean, 
                              Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprKPIVOImpl kpivo = am.getAprKPIVO1();
        kpivo.initQuery(iAprID);
        AprTaskVOImpl taskvo = am.getAprTaskVO1();
        taskvo.initQuery(iAprID);
        AprAttendVOImpl attvo = am.getAprAttendVO1();
        attvo.initQuery(iAprID, "GOAL");
        AttendNoteVOImpl notevo = am.getAttendNoteVO1();
        notevo.initQuery(iAprID, "GOAL");

        this.TableQueryData(pageContext, webBean, "AprKPIVO1");
        this.TableQueryData(pageContext, webBean, "AprTaskVO1");
        this.TableQueryData(pageContext, webBean, "AprAttendVO1");
        this.TableQueryData(pageContext, webBean, "BehaviourVO1");
        this.TableQueryData(pageContext, webBean, "AddVO1");
        this.TableQueryData(pageContext, webBean, "SubVO1");
        this.CalculateWeight(pageContext, webBean);
    }

    // 设置页面表格的 添加另一行 按钮

    private void SetInsertButton(OAWebBean webBean) {
        OATableBean kpi_tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprKPIVO1");
        kpi_tableBean.setInsertable(true);
        kpi_tableBean.setAutoInsertion(false);

        OATableBean task_tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprTaskVO1");
        task_tableBean.setInsertable(true);
        task_tableBean.setAutoInsertion(false);
    }

    /* 计算权重（自动累计） */

    private void CalculateWeight(OAPageContext pageContext, 
                                 OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprKPIVOImpl kpivo = am.getAprKPIVO1();
        AprTaskVOImpl taskvo = am.getAprTaskVO1();

        String strTitle = "";
        Float d = kpivo.GetWeightTotal();
        if (d > 0) {
            strTitle = "KPI指标       权重（自动累计）：".concat(String.valueOf(d));
            if (".0".equals(strTitle.substring(strTitle.length() - 2, 
                                               strTitle.length())))
                strTitle = strTitle.substring(0, strTitle.length() - 2);
            OAHeaderBean kpi_header = 
                (OAHeaderBean)webBean.findIndexedChildRecursive("regionKPI");
            kpi_header.setText(pageContext, strTitle);
        }

        d = taskvo.GetWeightTotal();
        if (d > 0) {
            strTitle = "工作任务       权重（自动累计）：".concat(String.valueOf(d));
            if (".0".equals(strTitle.substring(strTitle.length() - 2, 
                                               strTitle.length())))
                strTitle = strTitle.substring(0, strTitle.length() - 2);
            OAHeaderBean kpi_header = 
                (OAHeaderBean)webBean.findIndexedChildRecursive("regionTask");
            kpi_header.setText(pageContext, strTitle);
        }
    }


    /* 初始化页面Button
     // 用户点击提交后，首先页面的按钮状态修改为disabled，执行后台处理代码，这样就避免了用户两次点击改按钮，造成重复提交的结果
     * */

    private void initWebButton(OAPageContext pageContext, OAWebBean webBean, 
                               String appraisalId) {
        String javaScript = 
            "javascript:function CtlBtnStatus(obj)" + "{ obj.disabled=true; return true;" + 
            "}";

        pageContext.putJavaScriptFunction("CtlBtnStatus", javaScript);
        OASubmitButtonBean submitButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Apply");
        if (submitButton != null)
            submitButton.setOnClick("javascript:return CtlBtnStatus(this);");
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
        String event = pageContext.getParameter(EVENT_PARAM);
        String source = pageContext.getParameter("source");

        OATableBean kpi_tableBean = 
            (OATableBean)webBean.findChildRecursive("AprKPIVO1");
        OATableBean taks_tableBean = 
            (OATableBean)webBean.findChildRecursive("AprTaskVO1");
        if (kpi_tableBean.getName().equals(source) && "addRows".equals(event))
            this.AddKPINewRow(pageContext, webBean, iAprID);
        else if (taks_tableBean.getName().equals(source) && 
                 "addRows".equals(event))
            this.AddTaskNewRow(pageContext, webBean, iAprID);
        else if (pageContext.getParameter("Save") != null) //保存数据
            this.SaveData(pageContext, webBean, iAprID);
        else if ("kpidelete".equals(event)) //删除KPI
            this.DeleteKPIWarn(pageContext, webBean);
        else if (pageContext.getParameter("KPIDeleteYesButton") != 
                 null) //确认删除KPI
            this.DeleteKPIConfirm(pageContext, webBean);
        else if ("taskdelete".equals(event)) //删除工作任务
            this.DeleteTaskWarn(pageContext, webBean);
        else if (pageContext.getParameter("TaskDeleteYesButton") != 
                 null) //确认删除工作任务 
            this.DeleteTaskConfirm(pageContext, webBean);
        else if (pageContext.getParameter("Cancel") != null) //返回
            this.BackMainPG(pageContext, webBean);
        else if (pageContext.getParameter("Apply") != null) //提交审批
            this.SubmitApr(pageContext, webBean, iAprID);
        else if ("copy".equals(event))
            this.GoToCopyPG(pageContext);
        else if ("show".equals(event)) {
            this.ShowBehaviourAddSub(pageContext, webBean, iAprID);
        }

        am.restoreKPIEmpSelfGoal(); //页面bug，当页面事件触发时，手动添加KPI行的描述、考核层面、数据来源就清空了
    }

    /* KPI指标新增一行 */

    private void AddKPINewRow(OAPageContext pageContext, OAWebBean webBean, 
                              Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprKPIVOImpl vo = am.getAprKPIVO1();
        if (!vo.isPreparedForExecution())
            vo.executeQuery();
        vo.AddNewRow(iAprID);
    }

    /* KPI指标新增一行 */

    private void AddTaskNewRow(OAPageContext pageContext, OAWebBean webBean, 
                               Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprTaskVOImpl vo = am.getAprTaskVO1();
        if (!vo.isPreparedForExecution())
            vo.executeQuery();
        vo.AddNewRow(iAprID);
    }

    /* 保存数据 */

    private void SaveData(OAPageContext pageContext, OAWebBean webBean, 
                          Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String strRtn = am.checkEmpSelfGoalSet();
        if ("s".equals(strRtn)) {
            if (am.commit()) {
                this.ShowMessage(pageContext, "数据保存成功！");
            } else
                this.ShowMessage(pageContext, "没有数据修改需要保存！");
        } else
            this.ShowMessage(pageContext, "数据验证失败：".concat(strRtn), "error");
    }

    /* 删除一条KPI记录 */

    private void DeleteKPIWarn(OAPageContext pageContext, OAWebBean webBean) {
        String contractId = pageContext.getParameter("contractId");
        String kpiName = pageContext.getParameter("kpiname");

        if ("".equals(kpiName)) { //如果KPI名字为空，证明用户未操作该列，则直接删除
            this.DeleteKPIConfirm(pageContext, webBean);
            return;
        }

        String tips = "确定删除指标 ：" + kpiName + "？";
        OAException mainMessage = new OAException(tips, OAException.WARNING);

        OADialogPage dialogPage = 
            new OADialogPage(OAException.WARNING, mainMessage, null, "", "");

        dialogPage.setOkButtonItemName("KPIDeleteYesButton");
        dialogPage.setOkButtonToPost(true);
        dialogPage.setNoButtonToPost(true);
        dialogPage.setPostToCallingPage(true);
        Hashtable formParams = new Hashtable(1);
        formParams.put("contractId", contractId);
        dialogPage.setFormParameters(formParams);
        pageContext.redirectToDialogPage(dialogPage);
    }

    /* 确认删除一条KPI记录 */

    private void DeleteKPIConfirm(OAPageContext pageContext, 
                                  OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String contractId = pageContext.getParameter("contractId");

        AprKPIVOImpl vo = am.getAprKPIVO1();
        Object obj[] = new Object[1];
        obj[0] = contractId;
        Key key = new Key(obj);
        Row row = vo.getRow(key);
        if (row != null) {
            row.remove();
        }

        OAException message = 
            new OAException("CUX", "CUX_DELETE_KPI_CONFIRM", null, 
                            OAException.CONFIRMATION, null);

        pageContext.putDialogMessage(message);
    }

    /* 删除一条工作任务记录 */

    private void DeleteTaskWarn(OAPageContext pageContext, OAWebBean webBean) {
        String contractId = pageContext.getParameter("contractId");
        String kpiName = pageContext.getParameter("kpiname");
        String tips = "确定删除工作任务 ：" + kpiName + "？";

        if ("".equals(kpiName)) { //如果KPI名字为空，证明用户未操作该列，则直接删除
            this.DeleteTaskConfirm(pageContext, webBean);
            return;
        }

        OAException mainMessage = new OAException(tips, OAException.WARNING);

        OADialogPage dialogPage = 
            new OADialogPage(OAException.WARNING, mainMessage, null, "", "");

        dialogPage.setOkButtonItemName("TaskDeleteYesButton");
        dialogPage.setOkButtonToPost(true);
        dialogPage.setNoButtonToPost(true);
        dialogPage.setPostToCallingPage(true);
        Hashtable formParams = new Hashtable(1);
        formParams.put("contractId", contractId);
        dialogPage.setFormParameters(formParams);
        pageContext.redirectToDialogPage(dialogPage);
    }

    /* 确认删除一条工作任务记录 */

    private void DeleteTaskConfirm(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String contractId = pageContext.getParameter("contractId");

        AprTaskVOImpl vo = am.getAprTaskVO1();
        Object obj[] = new Object[1];
        obj[0] = contractId;
        Key key = new Key(obj);
        Row row = vo.getRow(key);
        if (row != null) {
            row.remove();
        }

        OAException message = 
            new OAException("CUX", "CUX_DELETE_KPI_CONFIRM", null, 
                            OAException.CONFIRMATION, null);

        pageContext.putDialogMessage(message);
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

    /* 前往绩效合同拷贝页面 */

    private void GoToCopyPG(OAPageContext pageContext) {
        String strUrl = 
            "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/AprCopyPG";
        pageContext.forwardImmediately(strUrl, null, (byte)0, null, null, true, 
                                       "N");
    }

    /* 提交数据 */

    private void SubmitApr(OAPageContext pageContext, OAWebBean webBean, 
                           Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String strRtn = am.checkEmpSelfGoalSet();
        if (!"s".equals(strRtn)) {
            this.ShowMessage(pageContext, "数据验证失败：".concat(strRtn), "error");
            return;
        }

        strRtn = am.checkEmpSelfGoalSetWeight();
        if (!"s".equals(strRtn)) {
            this.ShowMessage(pageContext, "数据验证失败：".concat(strRtn), "error");
            return;
        }

        boolean bflag = am.commit(); //数据保存
        TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                 "updateContractKPITxn");

        strRtn = am.submitForApproval(iAprID);
        if ("Y".equals(strRtn)) { //提交成功，返回
            this.ShowMessage(pageContext, "绩效合同审批提交成功！");

            String strUrl = 
                "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfGoalReadOnlyPG&appraisalid=".concat(String.valueOf(iAprID));
            pageContext.forwardImmediately(strUrl, null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
            return;
        }

        this.ShowMessage(pageContext, "绩效合同审批提交失败，请联系系统管理员！", "error");

        // 虽然提交失败，但允许用户再次保存、提交
        if (bflag) {
            AprKPIVOImpl kpi_vo = am.getAprKPIVO1();
            kpi_vo.initQuery(iAprID);
            AprTaskVOImpl task_vo = am.getAprTaskVO1();
            task_vo.initQuery(iAprID);

            this.TableQueryData(pageContext, webBean, "AprKPIVO1");
            this.TableQueryData(pageContext, webBean, "AprTaskVO1");
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "updateContractKPITxn"); //允许再次保存，所以再开启事务
        }
    }

    /* 显示行为规范等数据 */

    private void ShowBehaviourAddSub(OAPageContext pageContext, 
                                     OAWebBean webBean, Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String source = pageContext.getParameter("source");
        if ("rsBehaviour".equals(source)) {
            BehaviourVOImpl behavo = am.getBehaviourVO1();
            if (behavo.IsQuery && behavo.AppraisalId == iAprID)
                return;
            behavo.initQuery(iAprID);
            this.TableQueryData(pageContext, webBean, "BehaviourVO1");
        } else if ("rsAdd".equals(source)) {
            AddVOImpl addvo = am.getAddVO1();
            if (addvo.IsQuery && addvo.AppraisalId == iAprID)
                return;
            addvo.initQuery(iAprID);
            this.TableQueryData(pageContext, webBean, "AddVO1");
        } else if ("rsSub".equals(source)) {
            SubVOImpl subvo = am.getSubVO1();
            if (subvo.IsQuery && subvo.AppraisalId == iAprID)
                return;
            subvo.initQuery(iAprID);
            this.TableQueryData(pageContext, webBean, "SubVO1");
        }
    }

    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
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

    /* 设置页面元素隐藏 */

    private void SetObjRendered(OAWebBean webBean, String objName, 
                                boolean bvalue) {
        OAWebBean obj = webBean.findChildRecursive(objName);
        if (obj == null)
            return;
        obj.setRendered(bvalue);
    }

    /* 表格显示VO数据 */

    private void TableQueryData(OAPageContext pageContext, OAWebBean webBean, 
                                String tablename) {
        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }

}
