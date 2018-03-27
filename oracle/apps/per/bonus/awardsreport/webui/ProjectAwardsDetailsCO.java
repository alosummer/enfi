/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.awardsreport.webui;

import cux.oracle.apps.per.bonus.awardsreport.server.PersonDistDetailsVOImpl;

import cux.oracle.apps.per.bonus.awardsreport.server.ProjectAwardsDetailsVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class ProjectAwardsDetailsCO extends OAControllerImpl {
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
        String queryProjectNumber = 
            pageContext.getParameter("queryProjectNumber");
        String queryDistDate = pageContext.getParameter("queryDistDate");

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        ProjectAwardsDetailsVOImpl vo = 
            (ProjectAwardsDetailsVOImpl)am.findViewObject("ProjectAwardsDetailsVO1");
        vo.initSQL(queryProjectNumber, queryDistDate);
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
        if (pageContext.getParameter("ReturnButton") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/awardsreport/webui/ProjectAwardsReportPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
    }

}
