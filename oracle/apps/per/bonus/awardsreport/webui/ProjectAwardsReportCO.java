/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.awardsreport.webui;

import cux.oracle.apps.per.bonus.awardsreport.server.PersonDistDetailsVOImpl;

import cux.oracle.apps.per.bonus.awardsreport.server.ProjectAwardsVOImpl;

import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

/**
 * Controller for ...
 */
public class ProjectAwardsReportCO extends OAControllerImpl {
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

        //**设置页面缺省“年份”这个字段**
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String strYear = df.format(today);
        OAMessageChoiceBean yearBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("QueryYear");
        yearBean.setDefaultValue(strYear);
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String queryYear = pageContext.getParameter("QueryYear");
        String event = pageContext.getParameter("event"); //event表示本次操作的事件名称
        if (pageContext.getParameter("SearchButton") != null) {
            String projectNumber = pageContext.getParameter("ProjectNumber");

            ProjectAwardsVOImpl vo = 
                (ProjectAwardsVOImpl)am.findViewObject("ProjectAwardsVO1");
            vo.initSQL(queryYear, projectNumber);
        }

        if ("goPersonDetails".equals(event)) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/awardsreport/webui/ProjectAwardsDetailsPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
    }

}
