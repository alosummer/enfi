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

import cux.oracle.apps.per.aprprocess.server.BehaviourVOImpl;
import cux.oracle.apps.per.aprprocess.server.SubVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

/**
 * 绩效合同，目标设定阶段，只读页面（已提交之后）
 * created by yang.gang.2016-4-26
 */
public class EmpSelfGoalReadOnlyCO extends OAControllerImpl {
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
            if (!pageContext.isFormSubmission()) {
                this.initWeb(pageContext, webBean);
            }
        }
    }

    /* 初始化页面数据 */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        //从地址栏获取参数
        String appraisalId = 
            this.GetParameterValue(pageContext, "appraisalid");
        if ("".equals(appraisalId)) {
            this.GetSessionValue(pageContext, "appraisalId");
        }

        // 若地址栏参数为空，则取Session
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

        if (!"GOAL".equals(PhaseId) || !"Y".equals(IsPromisee)) {
            this.ShowMessage(pageContext, "绩效合同状态与页面权限不一致，请刷新页面后重试！");
            return;
        }

        pageContext.putSessionValue("appraisalId", appraisalId);
        this.SetTitle(pageContext, webBean);
        this.initWebTable(pageContext, webBean, iAprID);
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

        this.TableQueryData(pageContext, webBean, "AprKPIVO1");
        this.TableQueryData(pageContext, webBean, "AprTaskVO1");
        this.TableQueryData(pageContext, webBean, "AprAttendVO1");

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

        if (pageContext.getParameter("Cancel") != null)
            this.BackMainPG(pageContext);
        else if ("show".equals(event)) {
            AprAMImpl am = 
                (AprAMImpl)pageContext.getApplicationModule(webBean);
            String appraisalId = 
                this.GetSessionValue(pageContext, "appraisalId");
            if (!am.isInteger(appraisalId))
                return;

            Integer iAprID = Integer.valueOf(appraisalId);
            this.ShowBehaviourAddSub(pageContext, webBean, iAprID);
        }
    }

    /* 返回绩效合同列表页面 */

    private void BackMainPG(OAPageContext pageContext) {
        pageContext.putSessionValue("backFromContract", "Y");
        String strUrl = 
            "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfMainPG";
        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
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

    /* 表格显示VO数据 */

    private void TableQueryData(OAPageContext pageContext, OAWebBean webBean, 
                                String tablename) {
        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }


}
