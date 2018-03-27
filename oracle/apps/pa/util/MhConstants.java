package cux.oracle.apps.pa.util;

public interface MhConstants {
    public static final String TASK_SQL = 
        "SELECT DISTINCT ppe.proj_element_id\n" + 
        "               ,ppe.name\n" + "               ,pmt.project_id\n" + 
        "               ,pmt.mh_category\n" + 
        "               ,ppe.ELEMENT_NUMBER\n" + 
        "FROM   cux_pa_mh_t      pmt\n" + "      ,pa_proj_elements ppe\n" + 
        "WHERE  ppe.proj_element_id = pmt.task_id";
    public static final String TRACKING_TASK_SQL = 
        "SELECT b.proj_element_id\n" + "      ,b.name\n" + 
        "      ,b.project_id\n" + "      ,'-1' mh_category\n" + 
        "      ,b.ELEMENT_NUMBER" + "FROM   pa_projects_all            a\n" + 
        "      ,pa_proj_elements           b\n" + 
        "      ,pa_proj_element_versions   c\n" + 
        "      ,pa_proj_elem_ver_schedule  d\n" + 
        "      ,pa_proj_elem_ver_structure stc\n" + 
        "WHERE  a.project_id = b.project_id\n" + 
        "AND    b.project_id = c.project_id\n" + 
        "AND    b.proj_element_id = c.proj_element_id\n" + 
        "AND    b.object_type = 'PA_TASKS'\n" + 
        "AND    c.project_id = d.project_id(+)\n" + 
        "AND    c.element_version_id = d.element_version_id(+)\n" + 
        "AND    c.parent_structure_version_id = stc.element_version_id\n" + 
        "AND    stc.latest_eff_published_flag = 'Y'";
    public static final String SUMMARY_SQL = 
        "SELECT DISTINCT pmah.mh_category\n" + 
        "               ,pmah.project_id\n" + 
        "               ,cux_pa_util_pkg.get_expenditure_type_byid(pmah.mh_category) mh_category_name\n" + 
        "               ,cux_pa_util_pkg.get_task_name(pmah.task_id) task_name\n" + 
        "               ,cux_pa_mh_pkg.get_mh_budget(pmah.project_id,\n" + 
        "                                            pmah.mh_category, pmah.task_id) task_budget\n" + 
        "               ,cux_pa_mh_pkg.get_mh_assigned(pmah.project_id,\n" + 
        "                                              pmah.mh_category, pmah.task_id) mh_assigned\n" + 
        "               ,cux_common_pkg.get_lookup_meaning('CUX_PA_MH_STATUS',\n" + 
        "                                                  pmah.approve_status) status_name\n" + 
        "FROM   cux_pa_mh_assign_header_t pmah\n" + 
        "      ,cux_pa_mh_assign_lines_t  pmal\n" + 
        "      ,cux_pa_mh_t               pmt\n" + 
        "WHERE  pmah.assign_id = pmal.assign_id\n" + 
        "AND    pmah.project_id = pmt.project_id\n" + 
        "AND    pmah.mh_category = pmt.mh_category\n" + 
        "AND    pmah.task_id = pmt.task_id\n" + 
        "AND    nvl(pmah.specialty, 'N') = nvl(pmt.specialty, 'N')\n" + 
        "AND    pmah.attribute1 = 'N'";
    public static final String TRACKING_SUMMARY_SQL = 
        "SELECT DISTINCT pmah.mh_category\n" + 
        "               ,pmah.project_id\n" + 
        "               ,cux_pa_util_pkg.get_expenditure_type_byid(pmah.mh_category) mh_category_name\n" + 
        "               ,cux_pa_util_pkg.get_task_name(pmah.task_id) task_name\n" + 
        "               ,cux_pa_mh_pkg.get_mh_budget(pmah.project_id,\n" + 
        "                                            pmah.mh_category, pmah.task_id) task_budget\n" + 
        "               ,cux_pa_mh_pkg.get_mh_assigned(pmah.project_id,\n" + 
        "                                              pmah.mh_category, pmah.task_id) mh_assigned\n" + 
        "               ,cux_common_pkg.get_lookup_meaning('CUX_PA_MH_STATUS',\n" + 
        "                                                  pmah.approve_status) status_name\n" + 
        "               ,pmah.attribute1\n" + 
        "FROM   cux_pa_mh_assign_header_t pmah\n" + 
        "      ,cux_pa_mh_assign_lines_t  pmal\n" + 
        "WHERE  pmah.assign_id = pmal.assign_id\n" + 
        "AND    pmah.attribute1 = 'Y'";
    public static final String HEADER_SQL = 
        "SELECT mhassignheadereo.assign_id\n" + 
        "      ,mhassignheadereo.project_id\n" + 
        "      ,mhassignheadereo.task_id\n" + 
        "      ,cux_pa_util_pkg.get_task_name(p_task_id => mhassignheadereo.task_id) task_name\n" + 
        "      ,mhassignheadereo.specialty\n" + 
        "      ,cux_pa_util_pkg.get_lookup_meaning(p_lookup_type => 'ENFI_SPECIALITY',\n" + 
        "                                          p_lookup_code => mhassignheadereo.specialty) specialty_name\n" + 
        "      ,round(pmt.mh_budget,2) mh_budget\n" + 
        "      ,mhassignheadereo.approve_status\n" + 
        "      ,cux_pa_util_pkg.get_lookup_meaning(p_lookup_type => 'CUX_PA_MH_STATUS',\n" + 
        "                                          p_lookup_code => mhassignheadereo.approve_status) approve_status_name\n" + 
        "      ,mhassignheadereo.attribute1\n" + 
        "      ,mhassignheadereo.attribute2\n" + 
        "      ,mhassignheadereo.attribute3\n" + 
        "      ,mhassignheadereo.attribute4\n" + 
        "      ,mhassignheadereo.attribute5\n" + 
        "      ,mhassignheadereo.attribute6\n" + 
        "      ,mhassignheadereo.attribute7\n" + 
        "      ,mhassignheadereo.attribute8\n" + 
        "      ,mhassignheadereo.attribute9\n" + 
        "      ,mhassignheadereo.created_by\n" + 
        "      ,mhassignheadereo.creation_date\n" + 
        "      ,mhassignheadereo.last_updated_by\n" + 
        "      ,mhassignheadereo.last_update_date\n" + 
        "      ,mhassignheadereo.last_update_login\n" + 
        "      ,mhassignheadereo.mh_category\n" + 
        "      ,DECODE(mhassignheadereo.approve_status,'APPROVED',1,0) rendered_flag\n" + 
        "FROM   cux.cux_pa_mh_assign_header_t mhassignheadereo\n" + 
        "      ,cux_pa_mh_t                   pmt\n" + 
        "WHERE  mhassignheadereo.project_id = pmt.project_id(+)\n" + 
        "AND    mhassignheadereo.mh_category = pmt.mh_category(+)\n" + 
        "AND    mhassignheadereo.task_id = pmt.task_id(+)\n" + 
        "AND    mhassignheadereo.specialty = pmt.specialty(+)\n" + 
        "AND    mhassignheadereo.attribute1 = 'N'";
    public static final String TRACKING_HEADER_SQL = 
        "SELECT mhassignheadereo.assign_id\n" + 
        "      ,mhassignheadereo.project_id\n" + 
        "      ,mhassignheadereo.task_id\n" + 
        "      ,cux_pa_util_pkg.get_task_name(p_task_id => mhassignheadereo.task_id) task_name\n" + 
        "      ,mhassignheadereo.specialty\n" + 
        "      ,cux_pa_util_pkg.get_lookup_meaning(p_lookup_type => 'ENFI_SPECIALITY',\n" + 
        "                                          p_lookup_code => mhassignheadereo.specialty) specialty_name\n" + 
        "      ,mhassignheadereo.mh_budget\n" + 
        "      ,mhassignheadereo.approve_status\n" + 
        "      ,cux_pa_util_pkg.get_lookup_meaning(p_lookup_type => 'CUX_PA_MH_STATUS',\n" + 
        "                                          p_lookup_code => mhassignheadereo.approve_status) approve_status_name\n" + 
        "      ,mhassignheadereo.attribute1\n" + 
        "      ,mhassignheadereo.attribute2\n" + 
        "      ,mhassignheadereo.attribute3\n" + 
        "      ,mhassignheadereo.attribute4\n" + 
        "      ,mhassignheadereo.attribute5\n" + 
        "      ,mhassignheadereo.attribute6\n" + 
        "      ,mhassignheadereo.attribute7\n" + 
        "      ,mhassignheadereo.attribute8\n" + 
        "      ,mhassignheadereo.attribute9\n" + 
        "      ,mhassignheadereo.created_by\n" + 
        "      ,mhassignheadereo.creation_date\n" + 
        "      ,mhassignheadereo.last_updated_by\n" + 
        "      ,mhassignheadereo.last_update_date\n" + 
        "      ,mhassignheadereo.last_update_login\n" + 
        "      ,mhassignheadereo.mh_category\n" + 
        "      ,DECODE(mhassignheadereo.approve_status,'APPROVED',1,0) rendered_flag\n" + 
        "FROM   cux.cux_pa_mh_assign_header_t mhassignheadereo\n" + 
        "WHERE mhassignheadereo.attribute1 = 'Y'";
    public static final String LINE_SQL = 
        "SELECT mhassignlineseo.assign_line_id\n" + 
        "      ,mhassignlineseo.sort_seq\n" + 
        "      ,mhassignlineseo.person_id\n" + 
        "      ,cux_common_pkg.get_last_name_by_persionid(mhassignlineseo.person_id) person_name\n" + 
        "      ,mhassignlineseo.project_role project_role\n" + 
        "      ,prt.meaning project_role_name\n" + 
        "      ,mhassignlineseo.man_hours\n" + 
        "      ,mhassignlineseo.attribute1\n" + 
        "      ,mhassignlineseo.attribute2\n" + 
        "      ,mhassignlineseo.attribute3\n" + 
        "      ,mhassignlineseo.attribute4\n" + 
        "      ,mhassignlineseo.attribute5\n" + 
        "      ,mhassignlineseo.attribute6\n" + 
        "      ,mhassignlineseo.attribute7\n" + 
        "      ,mhassignlineseo.attribute8\n" + 
        "      ,mhassignlineseo.attribute9\n" + 
        "      ,mhassignlineseo.created_by\n" + 
        "      ,mhassignlineseo.creation_date\n" + 
        "      ,mhassignlineseo.last_updated_by\n" + 
        "      ,mhassignlineseo.last_update_date\n" + 
        "      ,mhassignlineseo.last_update_login\n" + 
        "      ,mhassignlineseo.assign_id\n" + 
        "      ,decode(pmah.APPROVE_STATUS, 'SUBMITED','DeleteDisabled','DeleteEnabled') delete_switcher\n" + 
        "      ,decode(pmah.APPROVE_STATUS, 'UNAPPROVED',0,1) readonly_flag\n" + 
        "FROM   cux.cux_pa_mh_assign_lines_t mhassignlineseo\n" + 
        "      ,cux_pa_mh_assign_header_t    pmah\n" + 
        "      ,pa_project_role_types        prt\n" + 
        "WHERE  mhassignlineseo.assign_id = pmah.assign_id\n" + 
        "AND    mhassignlineseo.project_role = prt.project_role_id(+)\n" + 
        "ORDER BY mhassignlineseo.sort_seq ASC";
    public static final String TRACKING_LINE_SQL = 
        "SELECT mhassignlineseo.assign_line_id\n" + 
        "      ,mhassignlineseo.sort_seq\n" + 
        "      ,mhassignlineseo.person_id\n" + 
        "      ,cux_common_pkg.get_last_name_by_persionid(mhassignlineseo.person_id) person_name\n" + 
        "      ,mhassignlineseo.project_role project_role\n" + 
        "      ,prt.meaning project_role_name\n" + 
        "      ,mhassignlineseo.man_hours\n" + 
        "      ,mhassignlineseo.attribute1\n" + 
        "      ,mhassignlineseo.attribute2\n" + 
        "      ,mhassignlineseo.attribute3\n" + 
        "      ,mhassignlineseo.attribute4\n" + 
        "      ,mhassignlineseo.attribute5\n" + 
        "      ,mhassignlineseo.attribute6\n" + 
        "      ,mhassignlineseo.attribute7\n" + 
        "      ,mhassignlineseo.attribute8\n" + 
        "      ,mhassignlineseo.attribute9\n" + 
        "      ,mhassignlineseo.created_by\n" + 
        "      ,mhassignlineseo.creation_date\n" + 
        "      ,mhassignlineseo.last_updated_by\n" + 
        "      ,mhassignlineseo.last_update_date\n" + 
        "      ,mhassignlineseo.last_update_login\n" + 
        "      ,mhassignlineseo.assign_id\n" + 
        "      ,decode(pmah.approve_status, 'SUBMITED', 'DeleteDisabled',\n" + 
        "              'DeleteEnabled') delete_switcher\n" + 
        "      ,decode(pmah.approve_status, 'UNAPPROVED', 0, 1) readonly_flag\n" + 
        "FROM   cux.cux_pa_mh_assign_lines_t mhassignlineseo\n" + 
        "      ,cux_pa_mh_assign_header_t    pmah\n" + 
        "      ,pa_project_role_types        prt\n" + 
        "WHERE  mhassignlineseo.assign_id = pmah.assign_id\n" + 
        "AND    mhassignlineseo.project_role = prt.project_role_id(+)\n" + 
        "AND    pmah.attribute1 = 'Y'\n" + 
        "ORDER  BY mhassignlineseo.sort_seq ASC\n";
    public static final String CATEGORY_SQL = 
        "SELECT DISTINCT pmc.expenditure_type_id\n" + 
        "      ,pmc.expenditure_type\n" + "      ,'' project_role\n" + 
        "FROM   cux_pa_mh_category_v pmc\n" + 
        "      ,(SELECT '项目经理' project_role\n" + "              ,'' rel\n" + 
        "        FROM   dual\n" + "        UNION ALL\n" + 
        "        SELECT '设计经理' project_role\n" + "              ,'设计' rel\n" + 
        "        FROM   dual\n" + "        UNION ALL\n" + 
        "        SELECT '专业负责人' project_role\n" + 
        "              ,'设计工作' rel\n" + "        FROM   dual\n" + 
        "        UNION ALL\n" + "        SELECT '采购经理' project_role\n" + 
        "              ,'采购' rel\n" + "        FROM   dual\n" + 
        "        UNION ALL\n" + "        SELECT '本部施工经理' project_role\n" + 
        "              ,'施工' rel\n" + "        FROM   dual) pr\n" + 
        "WHERE  pmc.expenditure_type LIKE '%' || pr.rel || '%'";
}
