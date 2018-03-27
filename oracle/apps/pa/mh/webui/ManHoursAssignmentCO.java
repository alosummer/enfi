/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.mh.webui;

import cux.oracle.apps.pa.mh.server.MhAssignHeaderVOImpl;
import cux.oracle.apps.pa.mh.server.MhAssignHeaderVORowImpl;
import cux.oracle.apps.pa.mh.server.MhAssignLinesVOImpl;
import cux.oracle.apps.pa.util.ControllerUtil;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAExceptionUtils;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class ManHoursAssignmentCO extends ControllerUtil {
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
        String trackingMode = pageContext.getParameter("TRACKING");
        pageContext.putSessionValue("TRACKING", trackingMode);
        am.getOADBTransaction().putValue("TRACKING", trackingMode);
        Serializable[] initParas = { trackingMode };
        am.invokeMethod("init", initParas);
        Serializable paras[] = { null };
        am.invokeMethod("resetMhCategory", paras);
        Serializable paras1[] = { null, null };
        am.invokeMethod("resetTask", paras1);
        OAWebBean specialtyName = 
            webBean.findIndexedChildRecursive("SpecialtyName");
        specialtyName.setAttributeValue(this.RENDERED_ATTR, false);
        OAWebBean mhTotal = webBean.findIndexedChildRecursive("MhTotal");
        OAWebBean mhBudget = webBean.findChildRecursive("MhBudget");
        OAWebBean taskBudget = webBean.findChildRecursive("TaskBudget");
        OAWebBean taskName = webBean.findChildRecursive("TaskName");
        OAWebBean taskName1 = webBean.findChildRecursive("TaskName1");
        mhTotal.setAttributeValue(this.READ_ONLY_ATTR, true);
        if ("Y".equals(trackingMode)) {
            mhTotal.setAttributeValue(this.RENDERED_ATTR, false);
            mhBudget.setAttributeValue(this.RENDERED_ATTR, false);
            taskBudget.setAttributeValue(this.RENDERED_ATTR, false);
            taskName.setAttributeValue(this.PROMPT_ATTR, "任务(前期任务)");
            taskName1.setAttributeValue(this.PROMPT_ATTR, "任务(前期任务)");
        }
        OAWebBean modifyAssignBtn = 
            webBean.findIndexedChildRecursive("modifyAssign");
        modifyAssignBtn.setAttributeValue(this.RENDERED_ATTR, false);
        OAButtonBean addButton = 
            (OAButtonBean)webBean.findChildRecursive("add");
        addButton.setRendered(false);
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
        String trackingMode = 
            (String)am.getOADBTransaction().getValue("TRACKING");
        if ("project".equals(pageContext.getParameter(this.SOURCE_PARAM)) && 
            ("lovUpdate".equals(event) || "lovValidate".equals(event))) {
            String projectId = 
                defaultNullValue(pageContext.getParameter("projectId"));
            String mhCategoryId = 
                defaultNullValue(pageContext.getParameter("MhCategory"));
            String sql = 
                "SELECT cux_pa_util_pkg.get_emp_project_roles_multi(" + 
                projectId + ",fnd_global.EMPLOYEE_ID) FROM dual";
            String roles = (String)this.getSqlValue(am, sql);
            Serializable paras[] = { roles };
            am.invokeMethod("resetMhCategory", paras);
            Serializable paras1[] = { projectId, mhCategoryId };
            am.invokeMethod("resetTask", paras1);
            restAddButton(pageContext, webBean);
        }
        if ("MhCategoryChange".equals(event)) {
            String mhCategoryId = 
                defaultNullValue(pageContext.getParameter("MhCategory"));
            String projectId = 
                defaultNullValue(pageContext.getParameter("projectId"));
            //设计工作工时
            if ("11119".equals(mhCategoryId)) {
                OAWebBean bean = 
                    webBean.findIndexedChildRecursive("SpecialtyName");
                bean.setAttributeValue(this.RENDERED_ATTR, true);
            } else {
                OAWebBean bean = 
                    webBean.findIndexedChildRecursive("SpecialtyName");
                bean.setAttributeValue(this.RENDERED_ATTR, false);
            }
            //预算总额
            if ("N".equals(trackingMode)) {
                String sql = 
                    "SELECT SUM(nvl(mh_budget, 0))\n" + "FROM   cux_pa_mh_t\n" + 
                    "WHERE  project_id = " + projectId + "\n" + 
                    "AND    mh_category = " + mhCategoryId;
                Number mhBudget = (Number)this.getSqlValue(am, sql);
                OAWebBean mhBudgetBean = webBean.findChildRecursive("MhTotal");
                if (mhBudget != null) {
                    mhBudgetBean.setAttributeValue(this.TEXT_ATTR, 
                                                   mhBudget.stringValue());
                } else {
                    mhBudgetBean.setAttributeValue(this.TEXT_ATTR, null);
                }
                sql = 
"SELECT ROUND(SUM(nvl(pmal.man_hours, 0)),2)\n" + "FROM   cux_pa_mh_assign_lines_t  pmal\n" + 
  "      ,cux_pa_mh_assign_header_t pmah\n" + 
  "      ,cux_pa_mh_t               pmt\n" + 
  "WHERE  pmah.assign_id = pmal.assign_id\n" + 
  "AND    pmah.project_id = pmt.project_id\n" + 
  "AND    pmah.mh_category = pmt.mh_category\n" + 
  "AND    pmah.task_id = pmt.task_id\n" + 
  "AND    nvl(pmah.specialty, 'N') = nvl(pmt.specialty, 'N')\n" + 
  "AND    pmah.project_id = " + projectId + "\n" + 
  "AND    pmah.mh_category =" + mhCategoryId + "\n";
                Number assignTotal = (Number)this.getSqlValue(am, sql);
                OAWebBean mhAssignTotal = 
                    webBean.findChildRecursive("MhAssignTotal");
                if (assignTotal != null) {
                    mhAssignTotal.setAttributeValue(this.TEXT_ATTR, 
                                                    assignTotal.stringValue());
                } else {
                    mhAssignTotal.setAttributeValue(this.TEXT_ATTR, null);
                }
            } else if ("Y".equals(trackingMode)) {
                String sql = 
                    "SELECT ROUND(SUM(nvl(pmal.man_hours, 0)),2)\n" + "FROM   cux_pa_mh_assign_lines_t  pmal\n" + 
                    "      ,cux_pa_mh_assign_header_t pmah\n" + 
                    "WHERE  pmah.assign_id = pmal.assign_id\n" + 
                    "AND    pmah.project_id = " + projectId + "\n" + 
                    "AND    pmah.mh_category =" + mhCategoryId + "\n";
                Number assignTotal = (Number)this.getSqlValue(am, sql);
                OAWebBean mhAssignTotal = 
                    webBean.findChildRecursive("MhAssignTotal");
                if (assignTotal != null) {
                    mhAssignTotal.setAttributeValue(this.TEXT_ATTR, 
                                                    assignTotal.stringValue());
                } else {
                    mhAssignTotal.setAttributeValue(this.TEXT_ATTR, null);
                }
            }
            Serializable paras1[] = { projectId, mhCategoryId };
            am.invokeMethod("resetTask", paras1);
            restAddButton(pageContext, webBean);
        }
        if ("OK".equals(event)) {
            String mhCategoryId = 
                defaultNullValue(pageContext.getParameter("MhCategory"));
            String projectId = 
                defaultNullValue(pageContext.getParameter("projectId"));
            Serializable paras[] = { projectId, mhCategoryId, null };
            MhAssignHeaderVOImpl headerVO = 
                (MhAssignHeaderVOImpl)am.findViewObject("MhAssignHeaderVO1");
            headerVO.setMaxFetchSize(0);
            headerVO.setWhereClause(null);
            headerVO.executeQuery();
            while (headerVO.hasNext()) {
                MhAssignHeaderVORowImpl row = 
                    (MhAssignHeaderVORowImpl)headerVO.next();
                MhAssignLinesVOImpl lineVO = 
                    (MhAssignLinesVOImpl)am.findViewObject("MhAssignLinesVO1");
                Row[] linerows = 
                    lineVO.getFilteredRows("AssignId", row.getAssignId());
                if (linerows.length == 0) {
                    row.remove();
                }
            }
            /*
            for (int i = 0; i < headerVO.getRowCount(); i++) {
                MhAssignHeaderVORowImpl row =
                    (MhAssignHeaderVORowImpl)headerVO.getRowAtRangeIndex(i);
                MhAssignLinesVOImpl lineVO =
                    (MhAssignLinesVOImpl)am.findViewObject("MhAssignLinesVO1");
                Row[] linerows =
                    lineVO.getFilteredRows("AssignId", row.getAssignId());
                if (linerows.length == 0) {
                    row.remove();
                }
            }
            */
            am.invokeMethod("initAssignHeader", paras);
        }
        if ("taskChange".equals(event)) {
            String mhCategoryId = 
                defaultNullValue(pageContext.getParameter("MhCategory"));
            String projectId = 
                defaultNullValue(pageContext.getParameter("projectId"));
            String taskid = 
                defaultNullValue(pageContext.getParameter("TaskName"));
            Serializable paras[] = { projectId, mhCategoryId, taskid };
            MhAssignHeaderVOImpl headerVO = 
                (MhAssignHeaderVOImpl)am.findViewObject("MhAssignHeaderVO1");
            headerVO.setMaxFetchSize(0);
            headerVO.setWhereClause(null);
            headerVO.executeQuery();
            while (headerVO.hasNext()) {
                MhAssignHeaderVORowImpl row = 
                    (MhAssignHeaderVORowImpl)headerVO.next();
                MhAssignLinesVOImpl lineVO = 
                    (MhAssignLinesVOImpl)am.findViewObject("MhAssignLinesVO1");
                Row[] linerows = 
                    lineVO.getFilteredRows("AssignId", row.getAssignId());
                if (linerows.length == 0) {
                    row.remove();
                }
            }
            /*
            for (int i = 0; i < headerVO.getRowCount(); i++) {
                MhAssignHeaderVORowImpl row =
                    (MhAssignHeaderVORowImpl)headerVO.getRowAtRangeIndex(i);
                MhAssignLinesVOImpl lineVO =
                    (MhAssignLinesVOImpl)am.findViewObject("MhAssignLinesVO1");
                Row[] linerows =
                    lineVO.getFilteredRows("AssignId", row.getAssignId());
                if (linerows.length == 0) {
                    row.remove();
                }
            }
            */
            am.invokeMethod("initAssignHeader", paras);
            if ((Boolean)headerVO.getCurrentRow().getAttribute("RenderedFlag")) {
                OAButtonBean midifyAssign = 
                    (OAButtonBean)webBean.findChildRecursive("modifyAssign");
                midifyAssign.setRendered(true);
            } else {
                OAButtonBean midifyAssign = 
                    (OAButtonBean)webBean.findChildRecursive("modifyAssign");
                midifyAssign.setRendered(false);
            }

        }
        if ("Add".equals(event)) {
            am.invokeMethod("addAssignLine");
        }
        if ("Save".equals(event)) {
            //            ViewObject lineVO = am.findViewObject("MhAssignLinesVO1");
            //            Row row = lineVO.getCurrentRow();
            //            row.getAttributeNames();
            //            row.getAttributeValues();
            am.getOADBTransaction().commit();
            throw new OAException("保存成功!", OAException.INFORMATION);
        }
        if ("modifyAssign".equals(event)) {
            am.invokeMethod("modifyAssign");
        }
        if ("DeleteLine".equals(event)) {
            String rowReference = 
                pageContext.getParameter(EVENT_SOURCE_ROW_REFERENCE);
            Serializable[] parameters = { rowReference };
            am.invokeMethod("deleteAssignLine", parameters);
        }
        if ("Submit".equals(event)) {
            if ("N".equals(trackingMode)) {
                am.getOADBTransaction().commit();
                ViewObject vo = am.findViewObject("MhAssignHeaderVO1");
                Row row = vo.getCurrentRow();
                Number assignId = (Number)row.getAttribute("AssignId");
                Number mhbudget = (Number)row.getAttribute("MhBudget");
                String mhsql = 
                    "SELECT SUM(man_hours)\n" + "FROM   cux_pa_mh_assign_lines_t\n" + 
                    "WHERE  assign_id = " + assignId.intValue();
                Number mhvalue = (Number)this.getSqlValue(am, mhsql);
                if (mhbudget.intValue() > mhvalue.intValue()) {
                    row.setAttribute("ApproveStatus", "APPROVED");
                    row.setAttribute("ApproveStatusName", "已批准");
                    am.getOADBTransaction().commit();
                    throw new OAException("请注意，价值工时未全部分配，系统将自动执行。", 
                                          OAException.WARNING);
                } else if (mhbudget.intValue() == mhvalue.intValue()) {
                    row.setAttribute("ApproveStatus", "APPROVED");
                    row.setAttribute("ApproveStatusName", "已批准");
                    am.getOADBTransaction().commit();
                    throw new OAException("价值工时以完成分配，系统将自动分配。", 
                                          OAException.INFORMATION);
                }
                String projectId = 
                    defaultNullValue(pageContext.getParameter("projectId"));

                String SMT_AME_STARTUP = 
                    "BEGIN\n" + "  cux_ame_wkf_pkg.startup_workflow(transaction_type => :1,\n" + 
                    "                                   transaction_id => :2,\n" + 
                    "                                   transaction_number => :3,\n" + 
                    "                                   mod_pkg_name => :4);\n" + 
                    "END;";
                OADBTransaction trxn = am.getOADBTransaction();
                CallableStatement cbStmt = 
                    trxn.createCallableStatement(SMT_AME_STARTUP, 1);
                try {
                    cbStmt.setString(1, "CUXPAMHAPPRV");
                    cbStmt.setString(2, assignId.stringValue());
                    cbStmt.setString(3, projectId + "" + assignId.intValue());
                    cbStmt.setString(4, "CUX_PA_MH_PKG");
                    cbStmt.execute();
                    cbStmt.close();
                    OAExceptionUtils.checkErrors(trxn);
                } catch (SQLException sqle) {
                    try {
                        cbStmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    throw OAException.wrapperException(sqle);
                }
                row.setAttribute("ApproveStatus", "SUBMITED");
                row.setAttribute("ApproveStatusName", "已提交");
                trxn.commit();
                OAWebBean submitD = webBean.findChildRecursive("Submit");
                submitD.setAttributeValue(ENABLED_ATTR, Boolean.FALSE);
                throw new OAException("价值工时分配超过预算，将进行审批。", 
                                      OAException.INFORMATION);
            } else if ("Y".equals(trackingMode)) {
                ViewObject vo = am.findViewObject("MhAssignHeaderVO1");
                Row row = vo.getCurrentRow();
                row.setAttribute("ApproveStatus", "APPROVED");
                row.setAttribute("ApproveStatusName", "已批准");
                am.getOADBTransaction().commit();
                throw new OAException("请确认分配的价值工时，系统将自动分配。", 
                                      OAException.INFORMATION);
            }
        }
    }

    public void restAddButton(OAPageContext pageContext, OAWebBean webBean) {
        String projectId = 
            defaultNullValue(pageContext.getParameter("projectId"));
        String mhCategoryId = 
            defaultNullValue(pageContext.getParameter("MhCategory"));
        OAButtonBean addButton = 
            (OAButtonBean)webBean.findChildRecursive("add");
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String sql = 
            "SELECT cux_pa_util_pkg.get_emp_project_roles_multi1(" + projectId + 
            ",fnd_global.EMPLOYEE_ID) FROM dual";
        String rl = (String)this.getSqlValue(am, sql);
        String[] rls = rl.split(",");
        List roles = new ArrayList();
        for (int i = 0; i < rls.length; i++) {
            roles.add(rls[i]);
        }
        //项目管理工时
        if ("11125".equals(mhCategoryId)) {
            if (!roles.contains("项目经理")) {
                addButton.setRendered(false);
            } else {
                addButton.setRendered(true);
            }
        }
        //设计管理工时
        if ("11120".equals(mhCategoryId)) {
            if (!roles.contains("设计经理")) {
                addButton.setRendered(false);
            } else {
                addButton.setRendered(true);
            }
        }
        //设计工作工时
        if ("11119".equals(mhCategoryId)) {
            if (!roles.contains("专业负责人")) {
                addButton.setRendered(false);
            } else {
                addButton.setRendered(true);
            }
        }
        //采购管理部分、采购工作部分、施工专家部分
        if ("11114".equals(mhCategoryId) || "11115".equals(mhCategoryId) || 
            "11202".equals(mhCategoryId)) {
            if (!roles.contains("采购经理")) {
                addButton.setRendered(false);
            } else {
                addButton.setRendered(true);
            }
        }
        //施工管理部分、施工工作部分、采购专家部分
        if ("11122".equals(mhCategoryId) || "11123".equals(mhCategoryId) || 
            "11201".equals(mhCategoryId)) {
            if (!roles.contains("本部施工经理")) {
                addButton.setRendered(false);
            } else {
                addButton.setRendered(true);
            }
        }
    }
}
