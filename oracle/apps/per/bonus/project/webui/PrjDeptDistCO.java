/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.pay.server.PayAMImpl;

import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAQueryUtils;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 集团、股份、上海，采购、财务部门部长分配项目管理奖页面
 * created by yang.gang, 2015-7-28
 */
public class PrjDeptDistCO extends OAControllerImpl {
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

        if (pageContext.getParameter("Search") != null) // 查询按钮
            this.OnSearchClick(pageContext, webBean);
        else if ("detail".equals(pageContext.getParameter("event")))
            this.OnDetailClick(pageContext);
    }

    /* 初始化页面
   * 查看是否有prjid参数，有该参数：页面是从工作流通知过来的
   * 查看是否有backflag参数,有该参数：页面是从详细页面返回，按先前的查询条件进行查询
   * */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        String Prjid = pageContext.getParameter("prjid");
        String backflag = pageContext.getParameter("backflag");
        if (Prjid != null && !"".equals(Prjid)) {
            Integer iPrjid = Integer.valueOf(Prjid);
            this.initWebFromWorkFlow(pageContext, webBean, iPrjid);
        } else if (backflag != null && !"".equals(backflag) && 
                   "y".equals(backflag)) {
            this.initWebFromDetailWeb(pageContext, webBean);
        } else
            this.initWebDefault(pageContext, webBean);
    }

    /* 初始化页面,页面是从工作流通知过来的, 查询该项目对应的记录 */

    private void initWebFromWorkFlow(OAPageContext pageContext, 
                                     OAWebBean webBean, Integer iPrjid) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);

        String strPrjName = am.GetPrjName(iPrjid);
        if ("".equals(strPrjName))
            return;

        OAMessageTextInputBean PrjNameBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("txtPrjName");
        PrjNameBean.setValue(pageContext, strPrjName);

        OAMessageChoiceBean statusBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistStatus");
        statusBean.setDefaultValue("0"); //全部      

        pageContext.putSessionValue("QueryProjectName", strPrjName);
        pageContext.putSessionValue("QueryStatus", "0");

        am.initDeptPrjQuery(strPrjName, "0");
        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("DeptPrjTable");
        table.queryData(pageContext, false);
    }

    /* 初始化页面,页面是从工作流通知过来的, 查询该项目对应的记录 */

    private void initWebFromDetailWeb(OAPageContext pageContext, 
                                      OAWebBean webBean) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);

        String strPrjName = 
            this.GetSessionValue(pageContext, "QueryProjectName");
        String strStatus = this.GetSessionValue(pageContext, "QueryStatus");

        if ("".equals(strPrjName)) {
            strPrjName = "%";
            pageContext.putSessionValue("QueryProjectName", strPrjName);
        }
        if ("".equals(strStatus)) {
            strStatus = "0";
            pageContext.putSessionValue("QueryStatus", strStatus);
        }

        am.initDeptPrjQuery(strPrjName, strStatus);
        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("DeptPrjTable");
        table.queryData(pageContext, false);
    }

    /* 初始化页面,默认 */

    private void initWebDefault(OAPageContext pageContext, OAWebBean webBean) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);

        String strPrjName = "%";
        pageContext.putSessionValue("QueryProjectName", strPrjName);

        String strStatus = "0";
        pageContext.putSessionValue("QueryStatus", strStatus);

        OAMessageChoiceBean statusBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistStatus");
        statusBean.setDefaultValue("1"); //未完成

        am.initDeptPrjQuery(strPrjName, strStatus);
        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("DeptPrjTable");
        table.queryData(pageContext, false);
    }

    /* 查询按钮响应事件 */

    private void OnSearchClick(OAPageContext pageContext, OAWebBean webBean) {
        //OAQueryUtils.checkSelectiveSearchCriteria(pageContext, webBean);
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        String PrjName = pageContext.getParameter("txtPrjName");
        if (PrjName == null || "".equals(PrjName))
            PrjName = "%";
        String status = "1";
        if (pageContext.getParameter("DistStatus") != null && 
            !"".equals(pageContext.getParameter("DistStatus").toString())) {
            status = pageContext.getParameter("DistStatus").toString();
        }

        pageContext.putSessionValue("QueryProjectName", PrjName);
        pageContext.putSessionValue("QueryStatus", status);

        am.initDeptPrjQuery(PrjName, status);

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("DeptPrjTable");
        table.queryData(pageContext, false);
    }

    /* 单个项目详细信息按钮 */

    private void OnDetailClick(OAPageContext pageContext) {
        String ProjectDistributionId = 
            pageContext.getParameter("ProjectDistributionId");
        pageContext.putSessionValue("ProjectDistributionId", 
                                    ProjectDistributionId);
        String ProjectNum = pageContext.getParameter("ProjectNum");
        pageContext.putSessionValue("ProjectNum", ProjectNum);
        String ProjectName = pageContext.getParameter("ProjectName");
        pageContext.putSessionValue("ProjectName", ProjectName);
        String ApprovedDate = pageContext.getParameter("ApprovedDate");
        pageContext.putSessionValue("ApprovedDate", ApprovedDate);
        String DistributionAmount = 
            pageContext.getParameter("DistributionAmount");
        pageContext.putSessionValue("DistributionAmount", DistributionAmount);
        String DistAmount = pageContext.getParameter("DistAmount");
        pageContext.putSessionValue("DistAmount", DistAmount);
        String StatusId = pageContext.getParameter("StatusId");
        pageContext.putSessionValue("StatusId", StatusId);
        String OrgName = pageContext.getParameter("OrgName");
        pageContext.putSessionValue("OrgName", OrgName);

        Integer iDistributionAmount = Integer.valueOf(DistributionAmount);
        Integer iDistAmount = Integer.valueOf(DistAmount);

        String strUrl = 
            "OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDeptDistDetailPG";
        if (iDistAmount >= iDistributionAmount) { //只读页面
            strUrl = 
                    "OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDeptDistDetlReadOnlyPG";
        }

        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
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
