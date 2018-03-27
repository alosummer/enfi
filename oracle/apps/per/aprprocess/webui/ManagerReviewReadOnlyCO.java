/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AddListVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprAMImpl;
import cux.oracle.apps.per.aprprocess.server.AprAttendVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprManagerDetailVOImpl;

import cux.oracle.apps.per.aprprocess.server.AprScoreVisibleVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprSourceManualVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprSourceSystemVOImpl;
import cux.oracle.apps.per.aprprocess.server.AttendNoteVOImpl;
import cux.oracle.apps.per.aprprocess.server.BehaviourListVOImpl;
import cux.oracle.apps.per.aprprocess.server.SubListVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.cabo.ui.UIConstants;

/**
 * 绩效管理_经理自助，打分阶段，只读界面（审批未最终完成之前）
 * created by yang.gang,2016-11-10
 */
public class ManagerReviewReadOnlyCO extends OAControllerImpl {
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
        if (pageContext.getParameter("Cancel") != null) //返回
            this.BackMainPG(pageContext, webBean);
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

        if (!"REVIEW".equals(PhaseId) || !"Y".equals(IsPromisee)) {
            this.ShowMessage(pageContext, "绩效合同状态与页面权限不一致，请刷新页面后重试！", "error");
            return;
        }

        pageContext.putSessionValue("appraisalid", appraisalId);
        pageContext.putSessionValue("aprkpistatus", 
                                    "managerreviewreadonly"); //KpiRptPG返回时用来确定页面的标识
        this.SetTitle(pageContext, webBean);
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
        visiblevo.AddRowReadOnly(aprseq);

        AprSourceManualVOImpl manaualvo = am.getAprSourceManualVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !manaualvo.isExecuted())
            manaualvo.initQuery(iAprID);
        AprSourceSystemVOImpl systemvo = am.getAprSourceSystemVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !systemvo.isExecuted())
            systemvo.initQuery(iAprID);
        AprAttendVOImpl attvo = am.getAprAttendVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !attvo.isExecuted())
            attvo.initQuery(iAprID, "REVIEW");

        BehaviourListVOImpl behavvo = am.getBehaviourListVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !behavvo.isExecuted())
            behavvo.initQuery(iAprID);
        AddListVOImpl addvo = am.getAddListVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !addvo.isExecuted())
            addvo.initQuery(iAprID);
        SubListVOImpl subvo = am.getSubListVO1();
        if (!"kpireport".equals(kpireportBackFlag) || !subvo.isExecuted())
            subvo.initQuery(iAprID);

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
        this.CalculateWeight(pageContext, webBean, aprseq);
    }

    /* 表格显示VO数据 */

    private void AdvTableQueryData(OAPageContext pageContext, 
                                   OAWebBean webBean, String tablename) {
        OAAdvancedTableBean tableBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }
    /* 表格显示VO数据 */

    private void TableQueryData(OAPageContext pageContext, OAWebBean webBean, 
                                String tablename) {
        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }

    /* 计算分数 */

    private void CalculateWeight(OAPageContext pageContext, OAWebBean webBean, 
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

}
