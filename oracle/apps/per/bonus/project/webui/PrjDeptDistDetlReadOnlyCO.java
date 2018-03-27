/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 采购、财务部门项目管理奖发放(只读页面)
 * added by yang.gang,2015-8-4
 */
public class PrjDeptDistDetlReadOnlyCO extends OAControllerImpl {
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
        this.InitPrjInfo(pageContext, webBean);
        this.RefreshDistData(pageContext, webBean);
    }

    /* 初始化页面显示的项目信息 */

    private void InitPrjInfo(OAPageContext pageContext, OAWebBean webBean) {
        String strPrjNum = this.GetSessionValue(pageContext, "ProjectNum");
        String strPrjName = this.GetSessionValue(pageContext, "ProjectName");
        String strApprovedDate = 
            this.GetSessionValue(pageContext, "ApprovedDate");
        String strDistributionAmount = 
            this.GetSessionValue(pageContext, "DistributionAmount");
        String strDistAmount = this.GetSessionValue(pageContext, "DistAmount");
        String strOrgName = this.GetSessionValue(pageContext, "OrgName");

        OAMessageStyledTextBean PrjNumBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjNum");
        PrjNumBean.setValue(pageContext, strPrjNum);
        OAMessageStyledTextBean PrjNameBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjName");
        PrjNameBean.setValue(pageContext, strPrjName);
        OAMessageStyledTextBean ApprovedDateBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjApprovedDate");
        ApprovedDateBean.setValue(pageContext, strApprovedDate);
        //可发放总额
        OAMessageStyledTextBean DistributionAmountBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjDistributionAmount");
        DistributionAmountBean.setValue(pageContext, strDistributionAmount);
        //已发放总额
        OAMessageStyledTextBean DistAmountBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjDistAmount");
        DistAmountBean.setValue(pageContext, strDistAmount);
        //分配部门
        OAMessageStyledTextBean OrgNametBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjOrgName");
        OrgNametBean.setValue(pageContext, strOrgName);
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

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        if (pageContext.getParameter("ReturnButton") != null)
            this.OnReturnButtonClick(pageContext);
    }

    /* 刷新页面数据显示 */

    private void RefreshDistData(OAPageContext pageContext, 
                                 OAWebBean webBean) {
        String strProjectDistributionId = 
            this.GetSessionValue(pageContext, "ProjectDistributionId");
        if ("".equals(strProjectDistributionId))
            return;
        int ProjectDistributionId = Integer.valueOf(strProjectDistributionId);

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        am.initDeptPrjDistDetailQuery(ProjectDistributionId);

        OAAdvancedTableBean currentTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjDistributionTable");
        currentTable.queryData(pageContext);
    }

    /* 返回采购、财务部长 项目管理奖查询页面 */

    private void OnReturnButtonClick(OAPageContext pageContext) {
        pageContext.removeSessionValue("ProjectDistributionId");
        pageContext.removeSessionValue("ProjectNum");
        pageContext.removeSessionValue("ProjectName");
        pageContext.removeSessionValue("ApprovedDate");
        pageContext.removeSessionValue("DistributionAmount");
        pageContext.removeSessionValue("DistAmount");
        pageContext.removeSessionValue("OrgName");

        HashMap params = new HashMap(1); //创建HashMap对象
        params.put("backflag", "y"); //给参数赋值
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDeptDistPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, params, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

}
