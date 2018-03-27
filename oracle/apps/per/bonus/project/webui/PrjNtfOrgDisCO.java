/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import java.util.ArrayList;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 项目管理奖 发放通知详细页面
 * created by yang.gang, 2015-12-1
 * 每个月12号，运行“cux.项目管理奖金发放通知”请求，通知股份、集团、上海、冶金有奖金职责的人员
 * 显示本月发放人员在各子公司，且项目归口部门与奖金被发放人员不在同一个公司、或者被发放人员未挂工资单的发放记录
 * 上月11日～本月10日批准的项目管理奖，纳入本月工资一并发放。
 * 本月11日～下月10日批准的现场奖，纳入下月工资发放 
 * 通知本身只显示10条以内的记录，本页面显示全部的记录
 */
public class PrjNtfOrgDisCO extends OAControllerImpl {
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

    /* 初始化页面
     * */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        String strMonth = pageContext.getParameter("month");
        if (strMonth == null || "".equals(strMonth))
            return;

        String strOrgID = pageContext.getParameter("org_id");
        if (strOrgID == null || "".equals(strOrgID))
            return;
        Integer iOrgID = Integer.valueOf(strOrgID);

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        String strOrgName = am.GetOrgName(iOrgID);
        String title = strOrgName + strMonth + " 项目管理奖发放详细信息";
        OAPageLayoutBean page = (OAPageLayoutBean)webBean;
        page.setTitle(title);

        am.initNtfOrgDisQuery(strMonth, iOrgID);

        OAAdvancedTableBean outerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjNtfGroupOrgTable");
        OAAdvancedTableBean innerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjNtfGroupPrjTable");
        if (outerTable != null)
            outerTable.queryData(pageContext);
        if (innerTable != null)
            innerTable.prepareForRendering(pageContext);

        OAAdvancedTableBean distTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("NtfOrgDisTable");
        if (distTable != null)
            distTable.queryData(pageContext);
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
    }

}
