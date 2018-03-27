/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.mh.lov.webui;

import cux.oracle.apps.pa.mh.lov.server.ProjectLovVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class ProjectLovCO extends OAControllerImpl {
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
        /*
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String trackingMode = (String)pageContext.getSessionValue("TRACKING");
        if ("Y".equals(trackingMode)) {
            String sql = "SELECT DISTINCT ppa.project_id\n" +
            "               ,ppa.segment1   project_num\n" +
            "               ,ppa.name       project_name\n" +
            "FROM   pa_projects_all       ppa\n" +
            "      ,pa_project_parties    ppp\n" +
            "      ,pa_project_role_types prt\n" +
            "      ,pa_project_classes_v  ppc\n" +
            "WHERE  ppa.template_flag = 'N'\n" +
            "AND    ppa.project_id = ppp.project_id\n" +
            "AND    ppp.project_role_id = prt.project_role_id\n" +
            "AND    prt.meaning IN （ '专业负责人', '设计经理', '采购经理', '施工经理', '项目经理'\n" +
            " ）\n" +
            "AND    trunc(SYSDATE) BETWEEN trunc(nvl(ppp.start_date_active, SYSDATE)) AND\n" +
            "       trunc(nvl(ppp.end_date_active, SYSDATE + 1))\n" +
            "AND    ppp.resource_source_id = fnd_global.employee_id\n" +
            "AND    ppa.project_id = ppc.project_id\n" +
            "AND    ppc.class_category = '项目细分类'\n" +
            "AND    ppc.class_code = '前期跟踪'\n";
            ProjectLovVOImpl projectLovVO =
                (ProjectLovVOImpl)am.findViewObject("ProjectLovVO1");
            projectLovVO.setQuery(sql);
        } else {
            String sql = "SELECT DISTINCT ppa.project_id\n" +
            "               ,ppa.segment1   project_num\n" +
            "               ,ppa.name       project_name\n" +
            "FROM   pa_projects_all       ppa\n" +
            "      ,pa_project_parties    ppp\n" +
            "      ,pa_project_role_types prt\n" +
            "      ,pa_project_classes_v  ppc\n" +
            "WHERE  ppa.template_flag = 'N'\n" +
            "AND    ppa.project_id = ppp.project_id\n" +
            "AND    ppp.project_role_id = prt.project_role_id\n" +
            "AND    prt.meaning IN （ '专业负责人', '设计经理', '采购经理', '施工经理', '项目经理'\n" +
            " ）\n" +
            "AND    trunc(SYSDATE) BETWEEN trunc(nvl(ppp.start_date_active, SYSDATE)) AND\n" +
            "       trunc(nvl(ppp.end_date_active, SYSDATE + 1))\n" +
            "AND    ppp.resource_source_id = fnd_global.employee_id\n" +
            "AND    ppa.project_id = ppc.project_id\n" +
            "AND    ppc.class_category = '项目细分类'\n" +
            "AND    ppc.class_code NOT IN ('前期跟踪', '部门管理')\n";
            ProjectLovVOImpl projectLovVO =
                (ProjectLovVOImpl)am.findViewObject("ProjectLovVO1");
            projectLovVO.setQuery(sql);
        }
        */
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
