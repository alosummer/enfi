/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AprAMImpl;

import cux.oracle.apps.per.aprprocess.server.AprEmpSelfListVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

/**
 * 绩效管理_员工自助，入口界面
 * created by yang.gang,2016-4-22 
 */
public class EmpSelfMainCO extends OAControllerImpl {
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
        String event = pageContext.getParameter(EVENT_PARAM);
        if (pageContext.getParameter("Search") != null) {
            this.OnSeachButtonClick(pageContext, webBean);
        } else if ("detail".equals(event)) {
            this.OnDetailClick(pageContext);
        }
    }

    /* 初始化页面
   * 查看是否有backFromContract Session值，有该参数：页面是从绩效合同页面返回，执行先前的查询
   * */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        String backFromContractFlag = 
            this.GetSessionValue(pageContext, "backFromContract");
        if ("Y".equals(backFromContractFlag)) {
            String queryFlag = this.GetSessionValue(pageContext, "queryFlag");
            if ("Y".equals(queryFlag)) { //继续按上次的查询条件进行查询
                String strYearFrom = 
                    this.GetSessionValue(pageContext, "YearFrom");
                String strYearTo = this.GetSessionValue(pageContext, "YearTo");

                AprAMImpl am = 
                    (AprAMImpl)pageContext.getApplicationModule(webBean);
                am.InitAprEmpSelftListVO(strYearFrom, strYearTo);

                OATableBean tableBean = 
                    (OATableBean)webBean.findIndexedChildRecursive("AprEmpSelfListVO1");
                tableBean.queryData(pageContext, false);
            } else { // 默认显示当年的绩效合同
                this.initWebDefaultYear(pageContext, webBean);
            }
        } else { // 默认显示当年的绩效合同
            this.initWebDefaultYear(pageContext, webBean);
        }
    }

    /* 显示当年的绩效合同 */

    private void initWebDefaultYear(OAPageContext pageContext, 
                                    OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        am.InitAprEmpSelftListVO();

        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprEmpSelfListVO1");
        tableBean.queryData(pageContext, false);
    }

    //  查询按钮响应事件

    private void OnSeachButtonClick(OAPageContext pageContext, 
                                    OAWebBean webBean) {
        String strYearFrom = 
            this.GetParameterValue(pageContext, "SearchYearFrom");
        String strYearTo = this.GetParameterValue(pageContext, "SearchYearTo");

        pageContext.putSessionValue("queryFlag", "Y");
        pageContext.putSessionValue("YearFrom", strYearFrom);
        pageContext.putSessionValue("YearTo", strYearTo);

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        am.InitAprEmpSelftListVO(strYearFrom, strYearTo);

        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("AprEmpSelfListVO1");
        tableBean.queryData(pageContext, false);
    }

    /* 绩效合同列表 详细信息按钮 */

    private void OnDetailClick(OAPageContext pageContext) {
        String appraisalId = 
            this.GetParameterValue(pageContext, "appraisalid");

        String phaseId = this.GetParameterValue(pageContext, "phaseid");
        String statusId = this.GetParameterValue(pageContext, "statusid");
        String IsPromisee = 
            this.GetParameterValue(pageContext, "ispromisee"); //'N',本人是当前操作者, 'Y',本人不是当前操作者

        String strUrl = "";
        if ("GOAL".equals(phaseId) && "WAITING".equals(statusId) && 
            "N".equals(IsPromisee))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfGoalUpdatePG&appraisalid=" + 
                    appraisalId;
        else if ("GOAL".equals(phaseId) && "Y".equals(IsPromisee))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfGoalReadOnlyPG&appraisalid=" + 
                    appraisalId;
        else if ("REVIEW".equals(phaseId) && "COMPLETED".equals(statusId))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/ReviewFinishPG&appraisalid=" + 
                    appraisalId;
        else if ("REVIEW".equals(phaseId) && "WAITING".equals(statusId) && 
                 "N".equals(IsPromisee))
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfReviewUpdatePG&appraisalid=" + 
                    appraisalId;
        else if ("REVIEW".equals(phaseId) && "Y".equals(IsPromisee)) {
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfReviewReadOnlyPG&appraisalid=" + 
                    appraisalId;
        }

        pageContext.forwardImmediately(strUrl, null, 
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

}
