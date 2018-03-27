/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AddListVOImpl;
import cux.oracle.apps.per.aprprocess.server.AddVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprAMImpl;

import cux.oracle.apps.per.aprprocess.server.AprAttendVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprKPIVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprManagerDetailVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprScoreVisibleVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprSourceManualVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprSourceSystemVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprTaskVOImpl;
import cux.oracle.apps.per.aprprocess.server.AttendNoteVOImpl;

import cux.oracle.apps.per.aprprocess.server.BehaviourListVOImpl;
import cux.oracle.apps.per.aprprocess.server.BehaviourVOImpl;
import cux.oracle.apps.per.aprprocess.server.SubListVOImpl;
import cux.oracle.apps.per.aprprocess.server.SubVOImpl;

import java.util.Hashtable;

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

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

/**
 * 绩效管理_经理自助，打分阶段，可修改界面（未批准之前）
 * created by yang.gang,2016-11-7
 */
public class ManagerReviewUpdateCO extends OAControllerImpl {
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
        String appraisalId = this.GetSessionValue(pageContext, "appraisalid");
        if (!am.isInteger(appraisalId))
            return;

        Integer iAprID = Integer.valueOf(appraisalId);
        String event = pageContext.getParameter(EVENT_PARAM);
        OAAdvancedTableBean behavBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("BehaviourListVO1");
        OAAdvancedTableBean addBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("AddListVO1");
        OAAdvancedTableBean subBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("SubListVO1");

        if (pageContext.getParameter("Save") != null) //保存数据
            this.SaveData(pageContext, webBean, iAprID);
        else if (pageContext.getParameter("Cancel") != null) //返回
            this.BackMainPG(pageContext, webBean);
        else if (pageContext.getParameter("Apply") != null) //提交，弹出转至提醒窗口
            this.SubmitApr(pageContext, webBean, iAprID);
        else if (pageContext.getParameter("SubmitAprWarnYesButton") != 
                 null) //提交，批准绩效合同
            this.SubmitAprConfirm(pageContext, webBean, iAprID);
        else if ((behavBean != null) && 
                 (behavBean.getName().equals(pageContext.getParameter("source"))) && 
                 ("addRows".equals(event)))
            this.AddBehavivourNewRow(pageContext, webBean, iAprID);
        else if ((addBean != null) && 
                 (addBean.getName().equals(pageContext.getParameter("source"))) && 
                 ("addRows".equals(event)))
            this.AddAddNewRow(pageContext, webBean, iAprID);
        else if ((subBean != null) && 
                 (subBean.getName().equals(pageContext.getParameter("source"))) && 
                 ("addRows".equals(event)))
            this.AddSubNewRow(pageContext, webBean, iAprID);
        else if ("behaviourdelete".equals(event)) //删除 行为规范
            this.DeleteBehaviour(pageContext, webBean);
        else if ("adddelete".equals(event)) //删除 加分项
            this.DeleteAdd(pageContext, webBean);
        else if ("subdelete".equals(event)) //删除 减分项
            this.DeleteSub(pageContext, webBean);
        else if (pageContext.getParameter("Return") != null) //退回，拒绝绩效合同
            this.RejectApr(pageContext, webBean, iAprID);

        am.restoreManagerReviewBehaviour();
        am.restoreManagerReviewAdd();
        am.restoreManagerReviewSub();
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
            this.GetSessionValue(pageContext, "appraisalid");
            this.ShowMessage(pageContext, "绩效合同ID号获取失败，请刷新页面后重试！", "error");
            return;
        }

        Integer iAprID = Integer.valueOf(appraisalId);
        AprManagerDetailVOImpl vo = am.getAprManagerDetailVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !vo.isExecuted())
            vo.initQuery(iAprID);
        String PhaseId = vo.GetAttrValue("PhaseId"); //阶段
        String IsPromisee = 
            vo.GetAttrValue("IsPromisee"); //是否操作者为本人,'N'是本人, 'Y'不是

        if (!"REVIEW".equals(PhaseId) || !"N".equals(IsPromisee)) {
            this.ShowMessage(pageContext, "绩效合同状态与页面权限不一致，请刷新页面后重试！", "error");
            this.HideElement(webBean);
            return;
        }

        pageContext.putSessionValue("appraisalid", appraisalId);
        pageContext.putSessionValue("aprkpistatus", 
                                    "managerreviewupdate"); //KpiRptPG返回时用来确定页面的标识
        this.SetTitle(pageContext, webBean);
        this.initWebButton(pageContext, webBean);
        this.initWebTable(pageContext, webBean, iAprID, kpireportBackFlag);
    }


    /* 权限异常时隐藏页面操作元素 */

    private void HideElement(OAWebBean webBean) {
        this.SetObjRendered(webBean, "Return", false);
        this.SetObjRendered(webBean, "Save", false);
        this.SetObjRendered(webBean, "Apply", false);
        this.SetObjRendered(webBean, "Cancel", false);
    }

    /* 设置页面标题 */

    private void SetTitle(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprManagerDetailVOImpl vo = am.getAprManagerDetailVO1();
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
                              Integer iAprID, String kpireportBackFlag) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        Integer aprseq = am.GetApprSeqOrderNo(iAprID);
        AprScoreVisibleVOImpl visiblevo = am.getAprScoreVisibleVO1();
        visiblevo.AddRow(aprseq);

        AprSourceManualVOImpl manaualvo = am.getAprSourceManualVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !manaualvo.isExecuted())
            manaualvo.initQuery(iAprID);
        AprSourceSystemVOImpl systemvo = am.getAprSourceSystemVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !systemvo.isExecuted())
            systemvo.initQuery(iAprID);
        BehaviourListVOImpl behavvo = am.getBehaviourListVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !behavvo.isExecuted())
            behavvo.initQuery(iAprID);
        AddListVOImpl addvo = am.getAddListVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !addvo.isExecuted())
            addvo.initQuery(iAprID);
        SubListVOImpl subvo = am.getSubListVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !subvo.isExecuted())
            subvo.initQuery(iAprID);

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
        this.AdvTableQueryData(pageContext, webBean, "BehaviourListVO1");
        this.AdvTableQueryData(pageContext, webBean, "AddListVO1");
        this.TableQueryData(pageContext, webBean, "AprAttendVO1");
        this.CalculateScore(pageContext, webBean, aprseq);
    }

    /* 计算分数 */

    private void CalculateScore(OAPageContext pageContext, OAWebBean webBean, 
                                Integer aprseq) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprSourceManualVOImpl manaualvo = am.getAprSourceManualVO1();
        AprSourceSystemVOImpl systemvo = am.getAprSourceSystemVO1();

        String strTitle = "";
        Float d = manaualvo.GetScoreTotal(aprseq);
        if (d > 0) {
            strTitle = "手工评分指标       得分：".concat(String.valueOf(d));
            if (".0".equals(strTitle.substring(strTitle.length() - 2, 
                                               strTitle.length())))
                strTitle = strTitle.substring(0, strTitle.length() - 2);
            OAHeaderBean kpi_header = 
                (OAHeaderBean)webBean.findIndexedChildRecursive("regionSourceManual");
            kpi_header.setText(pageContext, strTitle);
        }

        d = systemvo.GetScoreTotal();
        if (d > 0) {
            strTitle = "系统来源指标       得分：".concat(String.valueOf(d));
            if (".0".equals(strTitle.substring(strTitle.length() - 2, 
                                               strTitle.length())))
                strTitle = strTitle.substring(0, strTitle.length() - 2);
            OAHeaderBean kpi_header = 
                (OAHeaderBean)webBean.findIndexedChildRecursive("regionSourceSys");
            kpi_header.setText(pageContext, strTitle);
        }
    }

    /* 初始化页面Button
     // 解决问题: 点击提交按钮后，在确定提交考核流程界面，点击“是”按钮，点击2次，间隔时间2-3秒，之后流程自动跳过后一个审批节点
     // 在提交按钮添加前台javascript代码，点击提交按钮后，要求用户确认
     // 用户点击确定后，首先页面的按钮状态修改为disabled，执行后台处理代码，这样就避免了用户两次点击改按钮，造成重复提交的结果
     // 退回 不需要用户确认，直接执行
     * */

    private void initWebButton(OAPageContext pageContext, OAWebBean webBean) {
        String javaScript = 
            "javascript:function CtlBtnStatus(obj)" + "{ obj.disabled=true; return true; " + 
            "}";

        pageContext.putJavaScriptFunction("CtlBtnStatus", javaScript);
        OASubmitButtonBean submitButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Apply");
        if (submitButton != null)
            submitButton.setOnClick("javascript:return CtlBtnStatus(this);");
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

    /* 表格显示VO数据 */

    private void TableQueryData(OAPageContext pageContext, OAWebBean webBean, 
                                String tablename) {
        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }

    /* 设置页面元素隐藏 */

    private void SetObjRendered(OAWebBean webBean, String objName, 
                                boolean bvalue) {
        OAWebBean obj = webBean.findIndexedChildRecursive(objName);
        if (obj == null)
            return;
        obj.setRendered(bvalue);
    }

    /* 行为规范新增一行 */

    private void AddBehavivourNewRow(OAPageContext pageContext, 
                                     OAWebBean webBean, Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        BehaviourListVOImpl vo = am.getBehaviourListVO1();
        if (!vo.isPreparedForExecution())
            vo.executeQuery();
        vo.AddNewRow(iAprID);
    }

    /* 加分项 新增一行 */

    private void AddAddNewRow(OAPageContext pageContext, OAWebBean webBean, 
                              Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AddListVOImpl vo = am.getAddListVO1();
        if (!vo.isPreparedForExecution())
            vo.executeQuery();
        vo.AddNewRow(iAprID);
    }

    /* 减分项 新增一行 */

    private void AddSubNewRow(OAPageContext pageContext, OAWebBean webBean, 
                              Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        SubListVOImpl vo = am.getSubListVO1();
        if (!vo.isPreparedForExecution())
            vo.executeQuery();
        vo.AddNewRow(iAprID);
    }

    /* 保存数据 */

    private void SaveData(OAPageContext pageContext, OAWebBean webBean, 
                          Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String strRtn = am.checkManagerReviewSetEmpty(iAprID);
        if (!"s".equals(strRtn)) {
            this.ShowMessage(pageContext, "数据验证失败：".concat(strRtn), "error");
            return;
        }

        if (!am.commit()) {
            this.ShowMessage(pageContext, "没有数据修改需要保存！", "info");
            return;
        }

        this.ShowMessage(pageContext, "数据保存成功！", "info");
    }

    /* 返回绩效合同列表页面 */

    private void BackMainPG(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        am.rollback();
        TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                 "updateContractKPITxn"); //关闭事务

        pageContext.putSessionValue("backFromContract", "Y");
        String strUrl = 
            "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ManagerMainPG";
        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
    }

    /* 提交数据 */

    private void SubmitApr(OAPageContext pageContext, OAWebBean webBean, 
                           Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String strRtn = am.checkManagerReviewSet(iAprID);
        if (!"s".equals(strRtn)) {
            this.ShowMessage(pageContext, "数据验证失败：".concat(strRtn), "error");
            return;
        }

        String emptyKPI = this.GetEmptyKPIName(pageContext, webBean, iAprID);
        if (!"".equals(emptyKPI))
            emptyKPI = emptyKPI + "未评分，将以上一级审批人分数作为得分。";
        String addsubDesc = 
            this.getAddSubBehaviourWarnDesc(pageContext, webBean);
        String tips = emptyKPI + addsubDesc;
        if (!"".equals(tips))
            this.SubmitAprWarn(pageContext, webBean, tips);
        else
            this.SubmitAprConfirm(pageContext, webBean, iAprID);
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

    /* 表格显示VO数据 */

    private void AdvTableQueryData(OAPageContext pageContext, 
                                   OAWebBean webBean, String tablename) {
        OAAdvancedTableBean tableBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }

    /* 确认删除一条行为规范记录 */

    private void DeleteBehaviour(OAPageContext pageContext, 
                                 OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String contractId = pageContext.getParameter("ContractId");

        BehaviourListVOImpl vo = am.getBehaviourListVO1();
        Object obj[] = new Object[1];
        obj[0] = contractId;
        Key key = new Key(obj);
        Row row = vo.getRow(key);
        if (row != null) {
            row.remove();
        }

        this.ShowMessage(pageContext, "指标删除成功！", "info");
    }

    /* 确认删除一条加分项记录 */

    private void DeleteAdd(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String contractId = pageContext.getParameter("ContractId");

        AddListVOImpl vo = am.getAddListVO1();
        Object obj[] = new Object[1];
        obj[0] = contractId;
        Key key = new Key(obj);
        Row row = vo.getRow(key);
        if (row != null) {
            row.remove();
        }

        this.ShowMessage(pageContext, "指标删除成功！", "info");
    }

    /* 确认删除一条减分项记录 */

    private void DeleteSub(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String contractId = pageContext.getParameter("ContractId");

        SubListVOImpl vo = am.getSubListVO1();
        Object obj[] = new Object[1];
        obj[0] = contractId;
        Key key = new Key(obj);
        Row row = vo.getRow(key);
        if (row != null) {
            row.remove();
        }
        this.ShowMessage(pageContext, "指标删除成功！", "info");
    }

    /* 提交时提醒 */

    private void SubmitAprWarn(OAPageContext pageContext, OAWebBean webBean, 
                               String tips) {
        OAException mainMessage = new OAException(tips, OAException.WARNING);
        OADialogPage dialogPage = 
            new OADialogPage(OAException.WARNING, mainMessage, null, "", "");

        dialogPage.setOkButtonItemName("SubmitAprWarnYesButton");
        dialogPage.setOkButtonToPost(true);
        dialogPage.setNoButtonToPost(true);
        dialogPage.setPostToCallingPage(true);
        Hashtable formParams = new Hashtable(1);
        dialogPage.setFormParameters(formParams);
        pageContext.redirectToDialogPage(dialogPage);
    }

    private void SubmitAprConfirm(OAPageContext pageContext, OAWebBean webBean, 
                                  Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        am.updateManagerReviewManual(iAprID);
        am.commit();
        TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                 "updateContractKPITxn");

        am.CalRemarkInApprProcess(iAprID); //保存每级考核的中间分数，并计算最终分数
        String strRtn = am.submitForApproval(iAprID); //提交工作流
        if (!"Y".equals(strRtn)) { //提交失败
            this.ShowMessage(pageContext, "绩效合同审批提交失败，请联系系统管理员！", "error");
            return;
        }

        this.ShowMessage(pageContext, "绩效合同审批提交成功！");

        pageContext.putSessionValue("backFromContract", "Y");
        String strUrl = 
            "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ManagerMainPG";
        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
    }

    /* 获取未评分的手动打分指标
     * 返回 指标名称1、指标名称2
     * */

    private String GetEmptyKPIName(OAPageContext pageContext, 
                                   OAWebBean webBean, Integer iAprID) {
        String strRtn = "";
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        Integer aprseq = am.GetApprSeqOrderNo(iAprID); //审批顺序号
        if (aprseq == 1)
            return strRtn; //第一个节点的审批人，必须打分，在合法性判断时已经判断过        

        Integer j = 0;
        AprSourceManualVOImpl manaualvo = am.getAprSourceManualVO1();
        int rowCount = manaualvo.getRowCount();
        RowSetIterator deptPersonIter = 
            manaualvo.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String strScoreValue = this.getRowAttribute(pRow, "ScoreValue");
            if (!"".equals(strScoreValue))
                continue;

            if (j > 0)
                strRtn += "、";
            strRtn += this.getRowAttribute(pRow, "KpiName");
            j++;
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

    /* 获取加减分、行为规范项描述，在提交时显示提醒
     * 审批人添加加分项[指标名称]，加[得分]分；行为规范[指标名称]，扣[得分]分；减分项[指标名称]，扣[得分]分
     * */

    private String getAddSubBehaviourWarnDesc(OAPageContext pageContext, 
                                              OAWebBean webBean) {
        String strRtn = "";
        String strAdd = this.getAddListWarnDesc(pageContext, webBean);
        String strBehaviour = 
            this.getBehaviourListWarnDesc(pageContext, webBean);
        String strSub = this.getSubListWarnDesc(pageContext, webBean);
        if ("".equals(strAdd) && "".equals(strBehaviour) && "".equals(strSub))
            return "";
        if (!"".equals(strAdd))
            strRtn = strAdd;
        if (!"".equals(strBehaviour)) {
            if ("".equals(strRtn))
                strRtn = strBehaviour;
            else
                strRtn += "；" + strBehaviour;
        }
        if (!"".equals(strSub)) {
            if ("".equals(strRtn))
                strRtn = strSub;
            else
                strRtn += "；" + strSub;
        }
        return ("审批人添加" + strRtn);
    }

    /* 获取加分项描述，在提交时显示提醒
     * 加分项[指标名称]，加[得分]分；
     * */

    private String getAddListWarnDesc(OAPageContext pageContext, 
                                      OAWebBean webBean) {
        String strRtn = "";
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);

        Integer j = 0;
        AddListVOImpl kpivo = am.getAddListVO1();
        int rowCount = kpivo.getRowCount();
        RowSetIterator deptPersonIter = 
            kpivo.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String strKpiName = this.getRowAttribute(pRow, "KpiName");
            String strScoreValue = this.getRowAttribute(pRow, "ScoreValue");

            if (j > 0)
                strRtn += "；";
            strRtn += "加分项“" + strKpiName + "”，加" + strScoreValue + "分";
            j++;
        }
        deptPersonIter.closeRowSetIterator();
        return strRtn;
    }

    /* 获取加分项描述，在提交时显示提醒
     * 行为规范[指标名称]，扣[得分]分；
     * */

    private String getBehaviourListWarnDesc(OAPageContext pageContext, 
                                            OAWebBean webBean) {
        String strRtn = "";
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);

        Integer j = 0;
        BehaviourListVOImpl kpivo = am.getBehaviourListVO1();
        int rowCount = kpivo.getRowCount();
        RowSetIterator deptPersonIter = 
            kpivo.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String strKpiName = this.getRowAttribute(pRow, "KpiName");
            String strScoreValue = this.getRowAttribute(pRow, "ScoreValue");

            if (j > 0)
                strRtn += "；";
            strRtn += "行为规范“" + strKpiName + "”，扣" + strScoreValue + "分";
            j++;
        }
        deptPersonIter.closeRowSetIterator();
        return strRtn;
    }

    /* 获取减分项描述，在提交时显示提醒
     * 减分项[指标名称]，扣[得分]分；
     * */

    private String getSubListWarnDesc(OAPageContext pageContext, 
                                      OAWebBean webBean) {
        String strRtn = "";
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);

        Integer j = 0;
        SubListVOImpl kpivo = am.getSubListVO1();
        int rowCount = kpivo.getRowCount();
        RowSetIterator deptPersonIter = 
            kpivo.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String strKpiName = this.getRowAttribute(pRow, "KpiName");
            String strScoreValue = this.getRowAttribute(pRow, "ScoreValue");

            if (j > 0)
                strRtn += "；";
            strRtn += "减分项“" + strKpiName + "”，扣" + strScoreValue + "分";
            j++;
        }
        deptPersonIter.closeRowSetIterator();
        return strRtn;
    }

    /* 提交（退回）数据 */

    private void RejectApr(OAPageContext pageContext, OAWebBean webBean, 
                           Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String strRtn = am.checkManagerReviewSetEmpty(iAprID);
        if (!"s".equals(strRtn)) {
            this.ShowMessage(pageContext, "数据验证失败：".concat(strRtn), "error");
            return;
        }

        am.commit(); //数据保存

        strRtn = am.submitForReturn(iAprID);
        if ("Y".equals(strRtn)) { //提交成功，返回
            this.ShowMessage(pageContext, "绩效合同退回成功！");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "updateContractKPITxn");

            pageContext.putSessionValue("backFromContract", "Y");
            String strUrl = 
                "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ManagerMainPG";
            pageContext.forwardImmediately(strUrl, null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
            return;
        }

        this.ShowMessage(pageContext, "绩效合同审批退回失败，请联系系统管理员！");

    }

}
