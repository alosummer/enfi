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
import cux.oracle.apps.per.aprprocess.server.AprFinishDetailVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprKPIVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprManagerDetailVOImpl;

import cux.oracle.apps.per.aprprocess.server.AprScoreVisibleVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprSourceManualVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprSourceSystemVOImpl;
import cux.oracle.apps.per.aprprocess.server.AprTaskVOImpl;
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
 * 绩效管理_经理自助/员工自助，评分阶段，只读界面（审批未最终完成后）
 * created by yang.gang,2016-11-10
 */
public class ReviewFinishCO extends OAControllerImpl {
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

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);

        if ("".equals(appraisalId) || !am.isInteger(appraisalId)) {
            this.GetSessionValue(pageContext, "appraisalid");
            this.ShowMessage(pageContext, "绩效合同ID号获取失败，请刷新页面后重试！", "error");
            return;
        }

        Integer iAprID = Integer.valueOf(appraisalId);
        AprFinishDetailVOImpl vo = am.getAprFinishDetailVO1();
        vo.initQuery(iAprID);
        String PhaseId = vo.GetAttrValue("PhaseId"); //阶段        
        if ("NO_DATA".equals(PhaseId)) {
            this.ShowMessage(pageContext, "绩效合同状态与页面权限不一致，请刷新页面后重试！", "error");
            return;
        }

        pageContext.putSessionValue("appraisalid", appraisalId);
        pageContext.putSessionValue("aprkpistatus", 
                                    "reviewfinish"); //KpiRptPG返回时用来确定页面的标识
        this.SetTitle(pageContext, webBean);
        this.initWebTable(pageContext, webBean, iAprID);
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
        AprFinishDetailVOImpl vo = am.getAprFinishDetailVO1();
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
        BehaviourListVOImpl behavvo = am.getBehaviourListVO1();
        behavvo.initQuery(iAprID);
        AddListVOImpl addvo = am.getAddListVO1();
        addvo.initQuery(iAprID);
        SubListVOImpl subvo = am.getSubListVO1();
        subvo.initQuery(iAprID);

        AprAttendVOImpl attvo = am.getAprAttendVO1();
        attvo.initQuery(iAprID, "REVIEW");

        //实际值
        OAMessageStyledTextBean actualValueBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("ActualValue");
        actualValueBean.setAttributeValue(UIConstants.DESTINATION_ATTR, 
                                          new OADataBoundValueViewObject(actualValueBean, 
                                                                         "ActualValueLink", 
                                                                         "AprKPIVO1"));

        this.TableQueryData(pageContext, webBean, "AprKPIVO1");
        this.TableQueryData(pageContext, webBean, "AprTaskVO1");
        this.AdvTableQueryData(pageContext, webBean, "BehaviourListVO1");
        this.AdvTableQueryData(pageContext, webBean, "AddListVO1");
        this.TableQueryData(pageContext, webBean, "AprAttendVO1");
        this.CalculateWeight(pageContext, webBean);
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

    /* 返回绩效合同列表页面 */

    private void BackMainPG(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprFinishDetailVOImpl vo = am.getAprFinishDetailVO1();
        String SelfFlag = vo.GetAttrValue("SelfFlag"); //是否考核人本人标识,'Y'考核人本人 

        pageContext.putSessionValue("backFromContract", "Y");
        String strUrl = "";
        if ("Y".equals(SelfFlag))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfMainPG";
        else
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ManagerMainPG";
        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
    }

}
