package cux.oracle.apps.pa.mh.server;

import cux.oracle.apps.pa.util.ApplicationModuleUtil;
import cux.oracle.apps.pa.util.MhConstants;

import java.sql.SQLException;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class MHAssignmentAMImpl extends ApplicationModuleUtil implements MhConstants {
    /**This is the default constructor (do not remove)
     */
    public MHAssignmentAMImpl() {
    }

    public static void main(String[] args) {
        launchTester("cux.oracle.apps.pa.mh.server", "MHAssignmentAMLocal");
    }

    public ProjectRoleVOImpl getProjectRoleVO1() {
        return (ProjectRoleVOImpl)findViewObject("ProjectRoleVO1");
    }

    public MhAssignHeaderVOImpl getMhAssignHeaderVO1() {
        return (MhAssignHeaderVOImpl)findViewObject("MhAssignHeaderVO1");
    }

    public MhAssignLinesVOImpl getMhAssignLinesVO1() {
        return (MhAssignLinesVOImpl)findViewObject("MhAssignLinesVO1");
    }

    public MhCategoryVOImpl getMhCategoryVO1() {
        return (MhCategoryVOImpl)findViewObject("MhCategoryVO1");
    }

    public MhSummaryVOImpl getMhSummaryVO1() {
        return (MhSummaryVOImpl)findViewObject("MhSummaryVO1");
    }

    public void resetMhCategory(String roles) {
        MhCategoryVOImpl vo = getMhCategoryVO1();
        String sql = this.CATEGORY_SQL;
        vo.setFullSqlMode(vo.FULLSQL_MODE_AUGMENTATION);
        if ((roles != null) && (!"".equals(roles))) {
            sql += "AND project_role in (" + roles + ")";
            //vo.setWhereClause("project_role in (" + roles + ")");
        } else {
            sql += "AND 1=2";
            //vo.setWhereClause("1=2");
        }
        vo.setQuery(sql);
        vo.setWhereClause(null);
        vo.setMaxFetchSize(-1);
        //        if ((roles != null) && (!"".equals(roles))) {
        //            vo.setWhereClause("project_role in (" + roles + ")");
        //        } else {
        //            vo.setWhereClause("1=2");
        //        }
        vo.executeQuery();
    }

    public void resetTask(String projectId, String mhCategoryId) {
        String trackingMode = 
            (String)getOADBTransaction().getValue("TRACKING");
        TaskVOImpl taskVO = getTaskVO1();
        taskVO.setWhereClause(null);
        taskVO.setMaxFetchSize(-1);
        String sql = "";
        if ((projectId != null) && (!"".equals(projectId))) {
            //设计工作工时
            if ("11119".equals(mhCategoryId)) {
                if ("N".equals(trackingMode)) {
                    //"               ,ppevs.attribute2 || '-' || ppe.name NAME\n" + 
                    sql = 
"SELECT DISTINCT ppe.proj_element_id\n" + "               ,ppe.ELEMENT_NUMBER || ' ' || ppe.name || '-' ||cux_pa_util_pkg.get_zx(ppe.PROJ_ELEMENT_ID) NAME\n" + 
  "               ,pmt.project_id\n" + "               ,pmt.mh_category\n" + 
  "               ,ppe.ELEMENT_NUMBER\n" + 
  "FROM   cux_pa_mh_t                pmt\n" + 
  "      ,pa_proj_elements           ppe\n" + 
  "      ,pa_proj_element_versions   ppev\n" + 
  "      ,pa_proj_elem_ver_schedule  ppevs\n" + 
  "      ,pa_proj_elem_ver_structure stc\n" + 
  "      ,pa_proj_elements           ppe_top_task\n" + 
  "      ,pa_proj_element_versions   ppev_phase\n" + 
  "      ,pa_proj_elements           ppe_phase\n" + 
  "      ,pa_project_statuses        pps\n" + 
  "WHERE  ppe.proj_element_id = pmt.task_id\n" + 
  "AND    ppe.proj_element_id = ppev.proj_element_id\n" + 
  "AND    ppe.object_type = 'PA_TASKS'\n" + 
  "AND    ppev.project_id = ppevs.project_id(+)\n" + 
  "AND    ppev.element_version_id = ppevs.element_version_id(+)\n" + 
  "AND    ppev.parent_structure_version_id = stc.element_version_id\n" + 
  "AND    stc.latest_eff_published_flag = 'Y'\n" + 
  "AND    ppe_top_task.proj_element_id =\n" + 
  "       pa_proj_elements_utils.get_top_task_id(ppev.element_version_id)\n" + 
  "AND    ppe_top_task.phase_version_id = ppev_phase.element_version_id\n" + 
  "AND    ppev_phase.proj_element_id = ppe_phase.proj_element_id\n" + 
  "AND    pps.project_status_code = ppe_phase.phase_code\n" + 
  "AND    pps.project_status_name IN\n" + 
  "       ('预可研', '可研', '项目建议书', '方案', '初步设计', '基本设计', '专篇', '详细设计', '施工图设计','研发阶段','标准编制','投标报价','工程造价','报告')";
                } else {
                    sql = this.TRACKING_TASK_SQL;
                }
                //设计管理工时
            } else if ("11120".equals(mhCategoryId)) {
                if ("N".equals(trackingMode)) {
                    sql = 
"SELECT DISTINCT ppe_top_task.proj_element_id\n" + "               ,ppe_top_task.ELEMENT_NUMBER || ' ' || ppe_top_task.name || '-' ||cux_pa_util_pkg.get_zx(ppe_top_task.PROJ_ELEMENT_ID) NAME\n" + 
  "               ,pmt.project_id\n" + "               ,pmt.mh_category\n" + 
  "               ,ppe_top_task.ELEMENT_NUMBER\n" + 
  "FROM   cux_pa_mh_t              pmt\n" + 
  "      ,pa_proj_elements         ppe_top_task\n" + 
  "      ,pa_proj_element_versions ppev_phase\n" + 
  "      ,pa_proj_elements         ppe_phase\n" + 
  "      ,pa_project_statuses      pps\n" + 
  "WHERE  ppe_top_task.proj_element_id = pmt.task_id\n" + 
  "AND    ppe_top_task.phase_version_id = ppev_phase.element_version_id\n" + 
  "AND    ppev_phase.proj_element_id = ppe_phase.proj_element_id\n" + 
  "AND    pps.project_status_code = ppe_phase.phase_code\n" + 
  "AND    pps.project_status_name IN\n" + 
  "       ('预可研', '可研', '项目建议书', '方案', '初步设计', '基本设计', '专篇', '详细设计', '施工图设计')\n";
                } else {
                    sql = this.TRACKING_TASK_SQL;
                }
                //采购工作工时、采购专家工时    
            } else if ("11114".equals(mhCategoryId) || 
                       "11202".equals(mhCategoryId)) {
                if ("N".equals(trackingMode)) {
                    //"               ,ppevs.attribute2 || '-' || ppe.name NAME\n" + 
                    sql = 
"SELECT DISTINCT ppe.proj_element_id\n" + "               ,ppe.ELEMENT_NUMBER || ' ' || ppe.name || '-' ||cux_pa_util_pkg.get_zx(ppe.PROJ_ELEMENT_ID) NAME\n" + 
  "               ,pmt.project_id\n" + "               ,pmt.mh_category\n" + 
  "               ,ppe.ELEMENT_NUMBER\n" + 
  "FROM   cux_pa_mh_t                pmt\n" + 
  "      ,pa_proj_elements           ppe\n" + 
  "      ,pa_proj_element_versions   ppev\n" + 
  "      ,pa_proj_elem_ver_schedule  ppevs\n" + 
  "      ,pa_proj_elem_ver_structure stc\n" + 
  "      ,pa_proj_elements           ppe_top_task\n" + 
  "      ,pa_proj_element_versions   ppev_phase\n" + 
  "      ,pa_proj_elements           ppe_phase\n" + 
  "      ,pa_project_statuses        pps\n" + 
  "WHERE  ppe.proj_element_id = pmt.task_id\n" + 
  "AND    ppe.proj_element_id = ppev.proj_element_id\n" + 
  "AND    ppe.object_type = 'PA_TASKS'\n" + 
  "AND    ppev.project_id = ppevs.project_id(+)\n" + 
  "AND    ppev.element_version_id = ppevs.element_version_id(+)\n" + 
  "AND    ppev.parent_structure_version_id = stc.element_version_id\n" + 
  "AND    stc.latest_eff_published_flag = 'Y'\n" + 
  "AND    ppe_top_task.proj_element_id =\n" + 
  "       pa_proj_elements_utils.get_top_task_id(ppev.element_version_id)\n" + 
  "AND    ppe_top_task.phase_version_id = ppev_phase.element_version_id\n" + 
  "AND    ppev_phase.proj_element_id = ppe_phase.proj_element_id\n" + 
  "AND    pps.project_status_code = ppe_phase.phase_code\n" + 
  "AND    pps.project_status_name IN\n" + "       ('采购阶段')";
                } else {
                    sql = this.TRACKING_TASK_SQL;
                }
                //采购管理工时 
            } else if ("11115".equals(mhCategoryId)) {
                if ("N".equals(trackingMode)) {
                    sql = 
"SELECT DISTINCT ppe_top_task.proj_element_id\n" + "               ,ppe_top_task.ELEMENT_NUMBER || ' ' || ppe_top_task.name || '-' ||cux_pa_util_pkg.get_zx(ppe_top_task.PROJ_ELEMENT_ID) NAME\n" + 
  "               ,pmt.project_id\n" + "               ,pmt.mh_category\n" + 
  "               ,ppe_top_task.ELEMENT_NUMBER\n" + 
  "FROM   cux_pa_mh_t              pmt\n" + 
  "      ,pa_proj_elements         ppe_top_task\n" + 
  "      ,pa_proj_element_versions ppev_phase\n" + 
  "      ,pa_proj_elements         ppe_phase\n" + 
  "      ,pa_project_statuses      pps\n" + 
  "WHERE  ppe_top_task.proj_element_id = pmt.task_id\n" + 
  "AND    ppe_top_task.phase_version_id = ppev_phase.element_version_id\n" + 
  "AND    ppev_phase.proj_element_id = ppe_phase.proj_element_id\n" + 
  "AND    pps.project_status_code = ppe_phase.phase_code\n" + 
  "AND    pps.project_status_name IN\n" + "       ('采购阶段')\n";
                } else {
                    sql = this.TRACKING_TASK_SQL;
                }
                //施工工作工时、施工专家工时 
            } else if ("11122".equals(mhCategoryId) || 
                       "11201".equals(mhCategoryId)) {
                if ("N".equals(trackingMode)) {
                    //"               ,ppevs.attribute2 || '-' || ppe.name NAME\n" + 
                    sql = 
"SELECT DISTINCT ppe.proj_element_id\n" + "               ,ppe.ELEMENT_NUMBER || ' ' || ppe.name || '-' ||cux_pa_util_pkg.get_zx(ppe.PROJ_ELEMENT_ID) NAME\n" + 
  "               ,pmt.project_id\n" + "               ,pmt.mh_category\n" + 
  "               ,ppe.ELEMENT_NUMBER\n" + 
  "FROM   cux_pa_mh_t                pmt\n" + 
  "      ,pa_proj_elements           ppe\n" + 
  "      ,pa_proj_element_versions   ppev\n" + 
  "      ,pa_proj_elem_ver_schedule  ppevs\n" + 
  "      ,pa_proj_elem_ver_structure stc\n" + 
  "      ,pa_proj_elements           ppe_top_task\n" + 
  "      ,pa_proj_element_versions   ppev_phase\n" + 
  "      ,pa_proj_elements           ppe_phase\n" + 
  "      ,pa_project_statuses        pps\n" + 
  "WHERE  ppe.proj_element_id = pmt.task_id\n" + 
  "AND    ppe.proj_element_id = ppev.proj_element_id\n" + 
  "AND    ppe.object_type = 'PA_TASKS'\n" + 
  "AND    ppev.project_id = ppevs.project_id(+)\n" + 
  "AND    ppev.element_version_id = ppevs.element_version_id(+)\n" + 
  "AND    ppev.parent_structure_version_id = stc.element_version_id\n" + 
  "AND    stc.latest_eff_published_flag = 'Y'\n" + 
  "AND    ppe_top_task.proj_element_id =\n" + 
  "       pa_proj_elements_utils.get_top_task_id(ppev.element_version_id)\n" + 
  "AND    ppe_top_task.phase_version_id = ppev_phase.element_version_id\n" + 
  "AND    ppev_phase.proj_element_id = ppe_phase.proj_element_id\n" + 
  "AND    pps.project_status_code = ppe_phase.phase_code\n" + 
  "AND    pps.project_status_name IN\n" + "       ('施工阶段')";
                } else {
                    sql = this.TRACKING_TASK_SQL;
                }
                //施工管理工时
            } else if ("11123".equals(mhCategoryId)) {
                if ("N".equals(trackingMode)) {
                    sql = 
"SELECT DISTINCT ppe_top_task.proj_element_id\n" + "               ,ppe_top_task.ELEMENT_NUMBER || ' ' || ppe_top_task.name || '-' ||cux_pa_util_pkg.get_zx(ppe_top_task.PROJ_ELEMENT_ID) NAME\n" + 
  "               ,pmt.project_id\n" + "               ,pmt.mh_category\n" + 
  "               ,ppe_top_task.ELEMENT_NUMBER\n" + 
  "FROM   cux_pa_mh_t              pmt\n" + 
  "      ,pa_proj_elements         ppe_top_task\n" + 
  "      ,pa_proj_element_versions ppev_phase\n" + 
  "      ,pa_proj_elements         ppe_phase\n" + 
  "      ,pa_project_statuses      pps\n" + 
  "WHERE  ppe_top_task.proj_element_id = pmt.task_id\n" + 
  "AND    ppe_top_task.phase_version_id = ppev_phase.element_version_id\n" + 
  "AND    ppev_phase.proj_element_id = ppe_phase.proj_element_id\n" + 
  "AND    pps.project_status_code = ppe_phase.phase_code\n" + 
  "AND    pps.project_status_name IN\n" + "       ('施工阶段')\n";
                } else {
                    sql = this.TRACKING_TASK_SQL;
                }
                //项目管理工时
            } else if ("11125".equals(mhCategoryId)) {
                if ("N".equals(trackingMode)) {
                    sql = 
"SELECT DISTINCT ppe_top_task.proj_element_id\n" + "               ,ppe_top_task.ELEMENT_NUMBER || ' ' || ppe_top_task.name || '-' ||cux_pa_util_pkg.get_zx(ppe_top_task.PROJ_ELEMENT_ID) NAME\n" + 
  "               ,pmt.project_id\n" + "               ,pmt.mh_category\n" + 
  "               ,ppe_top_task.ELEMENT_NUMBER\n" + 
  "FROM   cux_pa_mh_t              pmt\n" + 
  "      ,pa_proj_elements         ppe_top_task\n" + 
  "      ,pa_proj_element_versions ppev_phase\n" + 
  "      ,pa_proj_elements         ppe_phase\n" + 
  "      ,pa_project_statuses      pps\n" + 
  "WHERE  ppe_top_task.proj_element_id = pmt.task_id\n" + 
  "AND    ppe_top_task.phase_version_id = ppev_phase.element_version_id\n" + 
  "AND    ppev_phase.proj_element_id = ppe_phase.proj_element_id\n" + 
  "AND    pps.project_status_code = ppe_phase.phase_code\n" + 
  "AND    pps.project_status_name IN\n" + "       ('项目管理')\n";
                } else {
                    sql = this.TRACKING_TASK_SQL;
                }
            }
            taskVO.setQuery(sql);
        }
        if ((projectId != null) && (!"".equals(projectId)) && 
            (mhCategoryId != null) && (!"".equals(mhCategoryId))) {
            if ("N".equals(trackingMode)) {
                taskVO.setWhereClause("project_id = " + projectId + 
                                      " AND mh_category = " + mhCategoryId);
            } else {
                taskVO.setWhereClause("project_id = " + projectId);
            }
        } else {
            taskVO.setWhereClause("1=2");
        }
        taskVO.executeQuery();
    }

    public void queryAssignHeader(String projectId, String mhCategoryId) {
        MhAssignHeaderVOImpl headerVO = getMhAssignHeaderVO1();
        headerVO.setWhereClause(null);
        headerVO.setMaxFetchSize(-1);
    }

    public TaskVOImpl getTaskVO1() {
        return (TaskVOImpl)findViewObject("TaskVO1");
    }

    public void initAssignHeader(String projectId, String mhCategoryId, 
                                 String taskId) {
        String trackingMode = 
            (String)getOADBTransaction().getValue("TRACKING");
        MhAssignedSummaryVOImpl summaryVO = getMhAssignedSummaryVO1();
        summaryVO.setWhereClause(null);
        summaryVO.setMaxFetchSize(-1);
        summaryVO.setWhereClause("project_id = " + projectId + 
                                 " AND mh_category = " + mhCategoryId);

        summaryVO.executeQuery();
        String specCode = null;
        String specName = null;
        String sql = null;
        if ("11119".equals(mhCategoryId)) {
            sql = getSpecCodeSQL(projectId);
            specCode = (String)getSqlValue(sql);
            sql = getSpecNameSQL(projectId);
            specName = (String)getSqlValue(sql);
        }

        MhAssignHeaderVOImpl assignHeader = getMhAssignHeaderVO1();
        assignHeader.clearCache();
        assignHeader.reset();
        assignHeader.setMaxFetchSize(-1);
        assignHeader.setWhereClause(null);
        if ((projectId != null) && (!"".equals(projectId)) && 
            (mhCategoryId != null) && (!"".equals(mhCategoryId)) && 
            (taskId != null) && (!"".equals(taskId))) {
            if ((specCode != null) && (!"".equals(specCode))) {
                assignHeader.setWhereClause("project_id = " + projectId + 
                                            " AND task_id = " + taskId + 
                                            " AND mh_category = " + 
                                            mhCategoryId + 
                                            " AND specialty = '" + specCode + 
                                            "'");
            } else {
                assignHeader.setWhereClause("project_id = " + projectId + 
                                            " AND task_id = " + taskId + 
                                            " AND mh_category = " + 
                                            mhCategoryId);
            }
        } else {
            assignHeader.setWhereClause("1=2");
        }
        assignHeader.executeQuery();
        if (assignHeader.getRowCount() == 0) {
            Row row = assignHeader.createRow();
            if ((taskId != null) && (!"".equals(taskId))) {
                Number taskIdNum = null;
                try {
                    taskIdNum = new Number(taskId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                row.setAttribute("TaskId", taskIdNum);
            }
            if ((projectId != null) && (!"".equals(projectId))) {
                Number projectIdNum = null;
                try {
                    projectIdNum = new Number(projectId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                row.setAttribute("ProjectId", projectIdNum);
            }
            if ((specCode != null) && (!"".equals(specCode))) {
                sql = 
getMhBudgetSqlWithSpec(projectId, mhCategoryId, taskId, specCode);
            } else {
                sql = 
getMhBudgetSqlWithoutSpec(projectId, mhCategoryId, taskId);
            }
            row.setAttribute("AssignId", 
                             getOADBTransaction().getSequenceValue("CUX_PA_MH_ASSIGN_HEADER_S"));

            row.setAttribute("MhCategory", mhCategoryId);
            row.setAttribute("Specialty", specCode);
            row.setAttribute("SpecialtyName", specName);
            row.setAttribute("ApproveStatus", "UNAPPROVED");
            row.setAttribute("ApproveStatusName", "未审批");
            row.setAttribute("MhBudget", getSqlValue(sql));
            row.setAttribute("Attribute1", trackingMode);
            row.setAttribute("RenderedFlag", Boolean.valueOf(false));
            row.setNewRowState(Row.STATUS_NEW);
            assignHeader.insertRow(row);
        }
        MhAssignLinesVOImpl lineVO = getMhAssignLinesVO1();
        lineVO.setWhereClause(null);
        lineVO.setMaxFetchSize(-1);
        lineVO.setWhereClause("assign_id = " + 
                              ((Number)assignHeader.first().getAttribute("AssignId")).intValue());

        lineVO.executeQuery();
    }

    private String getMhBudgetSqlWithoutSpec(String projectId, 
                                             String mhCategoryId, 
                                             String taskId) {
        return "SELECT mh_budget\n" + "FROM cux_pa_mh_t\n" + 
            "WHERE project_id = " + projectId + "\n" + "AND   mh_category = " + 
            mhCategoryId + "\n" + "AND   task_id = " + taskId + "\n";
    }

    private String getMhBudgetSqlWithSpec(String projectId, 
                                          String mhCategoryId, String taskId, 
                                          String specCode) {
        return "SELECT mh_budget\n" + "FROM cux_pa_mh_t\n" + 
            "WHERE project_id = " + projectId + "\n" + "AND   mh_category = " + 
            mhCategoryId + "\n" + "AND   task_id = " + taskId + "\n" + 
            "AND   specialty = '" + specCode + "'";
    }

    private String getSpecCodeSQL(String projectId) {
        return "SELECT prs.specialty_code\nFROM   pa_project_parties ppp\n      ,cux_pa_rep_sepc_t  prs\nWHERE  ppp.object_id = prs.object_id\nAND    ppp.object_type = prs.object_type\nAND    ppp.resource_type_id = prs.resource_type_id\nAND    ppp.resource_source_id = prs.resource_source_id\nAND    ppp.project_role_id = prs.project_role_id\nAND    ppp.start_date_active = prs.start_date_active\nAND    ppp.resource_source_id = fnd_global.employee_id\nAND    prs.specialty_code IS NOT NULL\nAND    ppp.project_id = " + 
            projectId;
    }

    private String getSpecNameSQL(String projectId) {
        return "SELECT cux_pa_util_pkg.get_lookup_meaning('ENFI_SPECIALITY',\n                                          prs.specialty_code)\nFROM   pa_project_parties ppp\n      ,cux_pa_rep_sepc_t  prs\nWHERE  ppp.object_id = prs.object_id\nAND    ppp.object_type = prs.object_type\nAND    ppp.resource_type_id = prs.resource_type_id\nAND    ppp.resource_source_id = prs.resource_source_id\nAND    ppp.project_role_id = prs.project_role_id\nAND    ppp.start_date_active = prs.start_date_active\nAND    ppp.resource_source_id = fnd_global.employee_id\nAND    prs.specialty_code IS NOT NULL\nAND    ppp.project_id = " + 
            projectId;
    }

    public void addAssignLine() {
        String trackingMode = 
            (String)getOADBTransaction().getValue("TRACKING");
        if ("N".equals(trackingMode)) {
            MhAssignHeaderVOImpl headerVO = getMhAssignHeaderVO1();
            Row headerRow = headerVO.getCurrentRow();
            Number mhbudget = (Number)headerRow.getAttribute("MhBudget");
            if (mhbudget == null) {
                throw new OAException("该专业无可分配的价值工时!", OAException.ERROR);
            }
        }
        MhAssignLinesVOImpl lineVO = getMhAssignLinesVO1();
        //lineVO.clearCache();
        lineVO.setMaxFetchSize(0);
        lineVO.executeQuery();
        int cacheCnt = lineVO.getRowCount();
        //        lineVO.setMaxFetchSize(-1);
        //        lineVO.executeQuery();
        //        int dbCnt =lineVO.getRowCount();

        MhAssignLinesVORowImpl row = 
            (MhAssignLinesVORowImpl)lineVO.createRow();

        row.setAssignId((Number)getMhAssignHeaderVO1().getCurrentRow().getAttribute("AssignId"));
        Number sortSeq = new Number(cacheCnt + 1);
        row.setSortSeq(sortSeq);
        row.setAssignLineId(getOADBTransaction().getSequenceValue("CUX_PA_MH_ASSIGN_LINES_S"));
        //lineVO.insertRowAtRangeIndex(0,row);
        lineVO.insertRow(row);
        row.setNewRowState(Row.STATUS_INITIALIZED);
    }

    public MhAssignedSummaryVOImpl getMhAssignedSummaryVO1() {
        return (MhAssignedSummaryVOImpl)findViewObject("MhAssignedSummaryVO1");
    }

    public void modifyAssign() {
        MhAssignLinesVOImpl lineVO = getMhAssignLinesVO1();
        for (int i = 0; i < lineVO.getRowCount(); i++) {
            MhAssignLinesVORowImpl lineRow = 
                (MhAssignLinesVORowImpl)lineVO.getRowAtRangeIndex(i);
            lineRow.setReadonlyFlag(false);
        }
    }

    public void deleteAssignLine(String rowRef) {
        Row row = findRowByRef(rowRef);
        row.remove();
    }

    public void init(String trackingMode) {
        if ("N".equals(trackingMode)) {
            TaskVOImpl taskVO = getTaskVO1();
            taskVO.setFullSqlMode(1);
            taskVO.setQuery(this.TASK_SQL);
            MhAssignedSummaryVOImpl summaryVO = getMhAssignedSummaryVO1();
            summaryVO.setFullSqlMode(1);
            summaryVO.setQuery(this.SUMMARY_SQL);
            MhAssignHeaderVOImpl headerVO = getMhAssignHeaderVO1();
            headerVO.setFullSqlMode(1);
            headerVO.setQuery(this.HEADER_SQL);
            MhAssignLinesVOImpl lineVO = this.getMhAssignLinesVO1();
            lineVO.setFullSqlMode(lineVO.FULLSQL_MODE_AUGMENTATION);
            lineVO.setQuery(this.LINE_SQL);
        } else if ("Y".equals(trackingMode)) {
            TaskVOImpl taskVO = getTaskVO1();
            taskVO.setFullSqlMode(1);
            taskVO.setQuery(this.TRACKING_TASK_SQL);
            MhAssignedSummaryVOImpl summaryVO = getMhAssignedSummaryVO1();
            summaryVO.setFullSqlMode(1);
            summaryVO.setQuery(this.TRACKING_SUMMARY_SQL);
            MhAssignHeaderVOImpl headerVO = getMhAssignHeaderVO1();
            headerVO.setFullSqlMode(1);
            headerVO.setQuery(this.TRACKING_HEADER_SQL);
            MhAssignLinesVOImpl lineVO = this.getMhAssignLinesVO1();
            lineVO.setFullSqlMode(lineVO.FULLSQL_MODE_AUGMENTATION);
            lineVO.setQuery(this.TRACKING_LINE_SQL);
        } else {
            TaskVOImpl taskVO = getTaskVO1();
            taskVO.setFullSqlMode(1);
            taskVO.setQuery(this.TRACKING_TASK_SQL);
            MhAssignedSummaryVOImpl summaryVO = getMhAssignedSummaryVO1();
            summaryVO.setFullSqlMode(1);
            summaryVO.setQuery(this.TRACKING_SUMMARY_SQL);
            MhAssignHeaderVOImpl headerVO = getMhAssignHeaderVO1();
            headerVO.setFullSqlMode(1);
            headerVO.setQuery(this.TRACKING_HEADER_SQL);
            MhAssignLinesVOImpl lineVO = this.getMhAssignLinesVO1();
            lineVO.setFullSqlMode(lineVO.FULLSQL_MODE_AUGMENTATION);
            lineVO.setQuery(this.TRACKING_LINE_SQL);
        }
    }
}
