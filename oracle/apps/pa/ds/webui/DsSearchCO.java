/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.ds.webui;

import com.sun.java.util.collections.HashMap;

import cux.oracle.apps.pa.util.ControllerUtil;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class DsSearchCO extends ControllerUtil {
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        //    Number isProjectRole = (Number)this.getSqlValue(am,"SELECT COUNT(0)\n" + 
        //    "FROM   pa_project_parties ppp\n" + 
        //    "WHERE  ppp.object_type = 'PA_PROJECTS'\n" + 
        //    "AND    ppp.resource_source_id = fnd_global.employee_id\n" + 
        //    "AND    ppp.project_id = rec.project_id\n" + 
        //    "AND    trunc(SYSDATE) BETWEEN\n" + 
        //    "       trunc(nvl(ppp.start_date_active, SYSDATE - 1)) AND\n" + 
        //    "       trunc(nvl(ppp.end_date_active, SYSDATE + 1))");
        //    if (isProjectRole.intValue() > 0) {
        //        String sql = "SELECT ppa.project_id\n" + 
        //        "      ,ppa.segment1\n" + 
        //        "      ,ppa.name project_name\n" + 
        //        "FROM   pa_projects_all       ppa\n" + 
        //        "      ,pa_project_role_types prt\n" + 
        //        "      ,pa_project_parties    ppp\n" + 
        //        "WHERE  ppa.template_flag = 'N'\n" + 
        //        "AND    ppa.project_id = ppp.project_id\n" + 
        //        "AND    ppp.project_role_id = prt.project_role_id\n" + 
        //        "AND    prt.meaning IN ('专业负责人', '项目经理', '设计经理')\n" + 
        //        "AND    ppp.resource_source_id = fnd_global.employee_id";
        //        Serializable[] paras = {sql};
        //        am.invokeMethod("initProjectLovVO",paras);
        //    } else {
        //        //室主任
        //        Number isSZR = (Number)this.getSqlValue(am,"SELECT COUNT(0)\n" + 
        //        "FROM   per_all_assignments_f paaf\n" + 
        //        "      ,per_grades            pg\n" + 
        //        "WHERE  paaf.person_id = fnd_global.employee_id\n" + 
        //        "AND    trunc(SYSDATE) BETWEEN\n" + 
        //        "       trunc(nvl(paaf.effective_start_date, SYSDATE - 1)) AND\n" + 
        //        "       trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
        //        "AND    paaf.grade_id = pg.grade_id\n" + 
        //        "AND    paaf.primary_flag = 'Y'\n" + 
        //        "AND    pg.name = '室主任'\n");
        //        if (isSZR.intValue() > 0) {
        //            String sql = "SELECT ppa.project_id\n" + 
        //            "      ,ppa.segment1\n" + 
        //            "      ,ppa.name project_name\n" + 
        //            "FROM   pa_projects_all ppa\n" + 
        //            "WHERE  ppa.template_flag = 'N'\n" + 
        //            "AND    ppa.project_id IN\n" + 
        //            "       (SELECT pdss.project_id\n" + 
        //            "         FROM   cux_pa_dlv_schedule_spec_t pdss\n" + 
        //            "         WHERE  pdss.specialty_manager IN\n" + 
        //            "                (SELECT paa.person_id\n" + 
        //            "                 FROM   per_all_assignments_f paa\n" + 
        //            "                 WHERE  trunc(SYSDATE) BETWEEN\n" + 
        //            "                        trunc(nvl(paa.effective_start_date, SYSDATE - 1)) AND\n" + 
        //            "                        trunc(nvl(paa.effective_end_date, SYSDATE + 1))\n" + 
        //            "                 AND    paa.primary_flag = 'Y'\n" + 
        //            "                 AND    paa.organization_id IN\n" + 
        //            "                        (SELECT paaf.organization_id\n" + 
        //            "                          FROM   per_all_assignments_f paaf\n" + 
        //            "                          WHERE  trunc(SYSDATE) BETWEEN\n" + 
        //            "                                 trunc(nvl(paaf.effective_start_date,\n" + 
        //            "                                           SYSDATE - 1)) AND\n" + 
        //            "                                 trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
        //            "                          AND    paaf.primary_flag = 'Y'\n" + 
        //            "                          AND    paaf.person_id = fnd_global.employee_id)))\n";
        //            Serializable[] paras = {sql};
        //            am.invokeMethod("initProjectLovVO",paras);
        //        }
        //专业负责人、项目经理、设计经理
        String sql = 
            "SELECT ppa.project_id\n" + "      ,ppa.segment1\n" + "      ,ppa.name project_name\n" + 
            "FROM   pa_projects_all       ppa\n" + 
            "      ,pa_project_role_types prt\n" + 
            "      ,pa_project_parties    ppp\n" + 
            "WHERE  ppa.template_flag = 'N'\n" + 
            "AND    ppa.project_id = ppp.project_id\n" + 
            "AND    ppp.project_role_id = prt.project_role_id\n" + 
            "AND    prt.meaning IN ('专业负责人', '项目经理', '设计经理','进度工程师')\n" + 
            "AND    ppp.resource_source_id = fnd_global.employee_id";
        Serializable[] paras = { sql };
        am.invokeMethod("initProjectLovVO", paras);
        //室主任
        Number isSZR = 
            (Number)this.getSqlValue(am, "SELECT COUNT(0)\n" + "FROM   per_all_assignments_f paaf\n" + 
                                     "      ,per_grades            pg\n" + 
                                     "WHERE  paaf.person_id = fnd_global.employee_id\n" + 
                                     "AND    trunc(SYSDATE) BETWEEN\n" + 
                                     "       trunc(nvl(paaf.effective_start_date, SYSDATE - 1)) AND\n" + 
                                     "       trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
                                     "AND    paaf.grade_id = pg.grade_id\n" + 
                                     "AND    paaf.primary_flag = 'Y'\n" + 
                                     "AND    pg.name = '室主任'\n");
        if (isSZR.intValue() > 0) {
            sql = 
"SELECT ppa.project_id\n" + "      ,ppa.segment1\n" + "      ,ppa.name project_name\n" + 
  "FROM   pa_projects_all ppa\n" + "WHERE  ppa.template_flag = 'N'\n" + 
  "AND    ppa.project_id IN\n" + "       (SELECT pdss.project_id\n" + 
  "         FROM   cux_pa_dlv_schedule_spec_t pdss\n" + 
  "         WHERE  pdss.specialty_manager IN\n" + 
  "                (SELECT paa.person_id\n" + 
  "                 FROM   per_all_assignments_f paa\n" + 
  "                 WHERE  trunc(SYSDATE) BETWEEN\n" + 
  "                        trunc(nvl(paa.effective_start_date, SYSDATE - 1)) AND\n" + 
  "                        trunc(nvl(paa.effective_end_date, SYSDATE + 1))\n" + 
  "                 AND    paa.primary_flag = 'Y'\n" + 
  "                 AND    paa.organization_id IN\n" + 
  "                        (SELECT paaf.organization_id\n" + 
  "                          FROM   per_all_assignments_f paaf\n" + 
  "                          WHERE  trunc(SYSDATE) BETWEEN\n" + 
  "                                 trunc(nvl(paaf.effective_start_date,\n" + 
  "                                           SYSDATE - 1)) AND\n" + 
  "                                 trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
  "                          AND    paaf.primary_flag = 'Y'\n" + 
  "                          AND    paaf.person_id = fnd_global.employee_id)))\n";
            Serializable[] paras0 = { sql };
            am.invokeMethod("initProjectLovVO", paras0);
        }
        //公司领导、项目管理人员
        Number isGSLD = 
            (Number)this.getSqlValue(am, "SELECT COUNT(0)\n" + "FROM   per_all_assignments_f paaf\n" + 
                                     "      ,per_grades            pg\n" + 
                                     "WHERE  paaf.person_id = fnd_global.employee_id\n" + 
                                     "AND    trunc(SYSDATE) BETWEEN\n" + 
                                     "       trunc(nvl(paaf.effective_start_date, SYSDATE - 1)) AND\n" + 
                                     "       trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
                                     "AND    paaf.grade_id = pg.grade_id\n" + 
                                     "AND    paaf.primary_flag = 'Y'\n" + 
                                     "AND    pg.name IN ('企业负责人正职', '企业负责人副职', '企业负责人其他')");
        Number isXMGLRY = 
            (Number)this.getSqlValue(am, "SELECT COUNT(0)\n" + "FROM   fnd_profile_options       fpo\n" + 
                                     "      ,fnd_profile_option_values fpov\n" + 
                                     "      ,fnd_profile_options_tl    fpot\n" + 
                                     "      ,fnd_user                  fu\n" + 
                                     "WHERE  fpo.profile_option_id = fpov.profile_option_id(+)\n" + 
                                     "AND    fpo.profile_option_name = fpot.profile_option_name\n" + 
                                     "AND    fu.user_id(+) = fpov.level_value\n" + 
                                     "AND    fpot.language = 'ZHS'\n" + 
                                     "AND    fpov.profile_option_value = 'Y'\n" + 
                                     "AND    fpo.profile_option_name = 'CUXPA_PROJECT_MANAGE_ROLE'\n" + 
                                     "AND    fu.employee_id = fnd_global.employee_id");
        if (isXMGLRY.intValue() > 0) {
            OAWebBean saveBtn = webBean.findChildRecursive("Save");
            saveBtn.setAttributeValue(this.RENDERED_ATTR, true);
            OAWebBean status = webBean.findChildRecursive("Status");
            status.setAttributeValue(this.READ_ONLY_ATTR, false);
            //        } else {
            //            OAWebBean saveBtn = webBean.findChildRecursive("Save");
            //            saveBtn.setAttributeValue(this.RENDERED_ATTR, false);
            //            OAWebBean status = webBean.findChildRecursive("Status");
            //            status.setAttributeValue(this.READ_ONLY_ATTR, true);
        }
        if (isGSLD.intValue() > 0 || isXMGLRY.intValue() > 0) {
            Serializable[] paras1 = { null };
            am.invokeMethod("initProjectLovVO", paras1);
        }
        Number sybld = 
            (Number)this.getSqlValue(am, "SELECT COUNT(0)\n" + "      FROM   per_all_assignments_f     paaf\n" + 
                                     "            ,per_grades                pg\n" + 
                                     "            ,hr_all_organization_units ou\n" + 
                                     "      WHERE  paaf.person_id = fnd_global.employee_id\n" + 
                                     "      AND    trunc(SYSDATE) BETWEEN\n" + 
                                     "             trunc(nvl(paaf.effective_start_date, SYSDATE - 1)) AND\n" + 
                                     "             trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
                                     "      AND    paaf.grade_id = pg.grade_id\n" + 
                                     "      AND    paaf.organization_id = ou.organization_id\n" + 
                                     "      AND    paaf.primary_flag = 'Y'\n" + 
                                     "      AND    pg.name IN ('中层管理人员正职')\n" + 
                                     "      AND    cux_common_pkg.get_lookup_meaning('ORG_TYPE', ou.type) = '业务部门'");
        if (sybld.intValue() > 0) {
            OAWebBean queryMode = webBean.findChildRecursive("QueryMode");
            queryMode.setAttributeValue(this.RENDERED_ATTR, true);
            sql = "NA";
            Serializable[] paras2 = { sql };
            am.invokeMethod("initProjectLovVO", paras2);
        } else {
            OAWebBean queryMode = webBean.findChildRecursive("QueryMode");
            queryMode.setAttributeValue(this.RENDERED_ATTR, false);
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
        String event = pageContext.getParameter(this.EVENT_PARAM);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if ("Search".equals(event)) {
            String projectId = pageContext.getParameter("projectId");
            String status = pageContext.getParameter("status");
            Serializable[] paras = { projectId, status };
            am.invokeMethod("searchSummary", paras);
        }
        if ("QueryModeChanged".equals(event)) {
            String queryMode = pageContext.getParameter("QueryMode");
            if ("01".equals(queryMode)) {
                String sql = 
                    "SELECT ppa.project_id\n" + "      ,ppa.segment1\n" + 
                    "      ,ppa.name project_name\n" + 
                    "FROM   pa_projects_all      ppa\n" + 
                    "      ,pa_project_classes_v ppc\n" + 
                    "WHERE  ppa.template_flag = 'N'\n" + 
                    "AND    ppa.project_id = ppc.project_id\n" + 
                    "AND    ppc.class_category = '项目所属部门'\n" + 
                    "AND    cux_hr_utility_pkg.get_org_id_by_orgname(ppc.code_description) IN\n" + 
                    "       (SELECT paaf.organization_id\n" + 
                    "         FROM   per_all_assignments_f paaf\n" + 
                    "         WHERE  trunc(SYSDATE) BETWEEN\n" + 
                    "                trunc(nvl(paaf.effective_start_date, SYSDATE - 1)) AND\n" + 
                    "                trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
                    "         AND    paaf.primary_flag = 'Y'\n" + 
                    "         AND    paaf.person_id = fnd_global.employee_id)\n";
                Serializable[] paras = { sql };
                am.invokeMethod("initProjectLovVO", paras);
            } else if ("02".equals(queryMode)) {
                String sql = 
                    "SELECT ppa.project_id\n" + "      ,ppa.segment1\n" + 
                    "      ,ppa.name project_name\n" + 
                    "FROM   pa_projects_all ppa\n" + 
                    "WHERE  ppa.template_flag = 'N'\n" + 
                    "AND    ppa.project_id IN\n" + 
                    "       (SELECT pdss.project_id\n" + 
                    "         FROM   cux_pa_dlv_schedule_spec_t pdss\n" + 
                    "         WHERE  pdss.specialty_manager IN\n" + 
                    "                (SELECT paa.person_id\n" + 
                    "                 FROM   per_all_assignments_f paa\n" + 
                    "                 WHERE  trunc(SYSDATE) BETWEEN\n" + 
                    "                        trunc(nvl(paa.effective_start_date, SYSDATE - 1)) AND\n" + 
                    "                        trunc(nvl(paa.effective_end_date, SYSDATE + 1))\n" + 
                    "                 AND    paa.primary_flag = 'Y'\n" + 
                    "                 AND    paa.organization_id IN\n" + 
                    "                        (SELECT paaf.organization_id\n" + 
                    "                          FROM   per_all_assignments_f paaf\n" + 
                    "                          WHERE  trunc(SYSDATE) BETWEEN\n" + 
                    "                                 trunc(nvl(paaf.effective_start_date,\n" + 
                    "                                           SYSDATE - 1)) AND\n" + 
                    "                                 trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
                    "                          AND    paaf.primary_flag = 'Y'\n" + 
                    "                          AND    paaf.person_id = fnd_global.employee_id)))\n";
                Serializable[] paras = { sql };
                am.invokeMethod("initProjectLovVO", paras);
            }
        }
        if ("Detail".equals(event)) {
            HashMap parameters = new HashMap();
            parameters.put("ProjectId", pageContext.getParameter("ProjectId"));
            parameters.put("TaskId", pageContext.getParameter("TaskId"));
            parameters.put("Sercurity", "Search"); // retain AM
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/ds/webui/DlvSchedulePG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, parameters, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if ("SearchTQ".equals(event)) {
            am.invokeMethod("doSearchTQ");
        }
        if ("SearchDQ".equals(event)) {
            am.invokeMethod("doSearchDQ");
        }
        if ("save".equals(event)) {
            am.getOADBTransaction().commit();
        }
    }

}
