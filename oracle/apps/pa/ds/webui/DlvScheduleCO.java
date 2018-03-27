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

import java.util.Hashtable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OARawTextBean;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.nav.OALovActionButtonBean;

import oracle.cabo.ui.beans.ScriptBean;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;


/**
 * Controller for ...
 */
public class DlvScheduleCO extends ControllerUtil {
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
        String projectId = pageContext.getParameter("ProjectId");
        String taskId = pageContext.getParameter("TaskId");
        String sercurity = pageContext.getParameter("Sercurity");
        if (projectId != null && !"".equals(projectId) && taskId != null && 
            !"".equals(taskId) && "Search".equals(sercurity)) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            Serializable[] paras = { projectId, taskId };
            am.invokeMethod("initDSFromSearch", paras);
            this.initQueryOnlyPage(pageContext, webBean);
        } else {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            am.invokeMethod("init");
            ScriptBean splitJs = new ScriptBean();
            String splitStrJs = 
                "function split( val ) {\n" + "  return val.split( /;\\s*/ );\n" + 
                "}";
            splitJs.setText(splitStrJs);
            webBean.addIndexedChild(0, splitJs);
            ScriptBean extractLastJs = new ScriptBean();
            String extractLastStrJs = 
                "function extractLast( term ) {\n" + "  return split( term ).pop();\n" + 
                "}";
            extractLastJs.setText(extractLastStrJs);
            webBean.addIndexedChild(1, extractLastJs);
            pageContext.putJavaScriptLibrary("jquery", "Jquery.js");
            pageContext.putJavaScriptLibrary("jqueryui", "JqueryUI.js");
            String specSql = 
                "SELECT DISTINCT meaning\n" + "FROM   fnd_lookup_values_vl flvv\n" + 
                "WHERE  flvv.lookup_type = 'ENFI_SPECIALITY'\n";
            String deptSql = 
                "SELECT DISTINCT child_org_name\n" + "FROM   cux_hr_hierarchy\n" + 
                "WHERE  org_level = 2";
            OARawTextBean rawText = 
                (OARawTextBean)webBean.findChildRecursive("rawText");
            String specs = (String)pageContext.getSessionValue("specs");
            if (specs == null || "".equals(specs)) {
                specs = this.getWrappedSqlValue(am, specSql);
                pageContext.putSessionValue("specs", specs);
            }
            /*String depts = (String)pageContext.getSessionValue("depts");
            if (depts == null || "".equals(depts)) {
                depts = this.getWrappedSqlValue(am, deptSql);
                pageContext.putSessionValue("depts", depts);
            }*/
            StringBuilder sb = new StringBuilder();
            sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"jquery-ui.css\"/>\n");
            sb.append("<script type=\"text/javascript\">\n");
            sb.append("$(document).ready(function(){\n");
            sb.append("var specs = [" + specs + "];\n");
            sb.append(wrapInputAutocomplete("AcceptSpecialty", "specs"));
            sb.append(wrapInputAutocomplete("releaseSpecialty1", "specs"));
            //sb.append("var depts = [" + depts + "];\n");
            //sb.append(wrapInputAutocomplete("ReleaseDept", "depts"));
            sb.append("})\n</script>\n");
            rawText.setText(sb.toString());
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String event = pageContext.getParameter(this.EVENT_PARAM);
        String source = pageContext.getParameter(this.SOURCE_PARAM);
        if ("ok".equals(event)) {
            String projectId = pageContext.getParameter("projectId");
            String taskId = pageContext.getParameter("taskId");
            if (projectId == null || "".equals(projectId) || taskId == null || 
                "".equals(taskId)) {

            } else {
                //am.getOADBTransaction().clearEntityCache(null);
                String specSql = 
                    "SELECT cux_common_pkg.get_lookup_meaning('ENFI_SPECIALITY',release_specialty)\n" + 
                    "FROM   cux_pa_dlv_schedule_spec_t\n" + 
                    "WHERE  project_id =" + projectId + "\n" + 
                    "AND    task_id = " + taskId;
                String specs = this.getWrappedSqlValue(am, specSql);
                pageContext.putSessionValue("specs", specs);
                Serializable[] paras = { projectId, taskId };
                am.invokeMethod("buildScheduleSpec", paras);
                am.invokeMethod("initDlvScheduleVO", 
                                paras); // no parameters to pass
                // retain the AM
                pageContext.setForwardURLToCurrentPage(null, true, 
                                                       OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                       OAWebBeanConstants.IGNORE_MESSAGES);
            }
        }
        if ("updateSpec".equals(event)) {
            am.invokeMethod("updateSpec");
        }

        if ("addSpec".equals(event)) {
            String projectId = pageContext.getParameter("projectId");
            String taskId = pageContext.getParameter("taskId");
            Serializable[] paras = { projectId, taskId };
            am.invokeMethod("addSpec", paras);
        }
        if ("deleteSpec".equals(event)) {
            am.invokeMethod("deleteSpec");
        }
        if ("lovUpdate".equals(event) && 
            ("projectNum".equals(source) || "projectName".equals(source))) {
            Hashtable lovResults = 
                pageContext.getLovResultsFromSession("projectNum");
            String projectDept = (String)lovResults.get("projectDept");
            ViewObject vo = am.findViewObject("DsPVO1");
            Row row = vo.getCurrentRow();
            row.setAttribute("ProjectDept", projectDept);
        }
        if ("lovUpdate".equals(event) && "taskNum".equals(source)) {
            Hashtable lovResults = 
                pageContext.getLovResultsFromSession("taskNum");
            String projectDept = (String)lovResults.get("taskName");
            //pageContext.getOANLSServices().stringToDate((String)lovResults.get("ScheduledStartDate"))
            //pageContext.getOANLSServices().string
            java.sql.Date scheduleStartSqlDate = 
                pageContext.getOANLSServices().stringToDate((String)lovResults.get("ScheduledStartDate"));
            java.sql.Date scheduleFinishSqlDate = 
                pageContext.getOANLSServices().stringToDate((String)lovResults.get("ScheduledFinishDate"));
            Date scheduleStartDate = new Date(scheduleStartSqlDate);
            Date scheduleFinishDate = new Date(scheduleFinishSqlDate);
            ViewObject vo = am.findViewObject("DsPVO1");
            Row row = vo.getCurrentRow();
            row.setAttribute("TaskName", projectDept);
            row.setAttribute("ScheduledStartDate", scheduleStartDate);
            row.setAttribute("ScheduledFinishDate", scheduleFinishDate);
            //row.setAttribute("",);
        }
        if ("copyFromTemplate".equals(event)) {
            String projectId = pageContext.getParameter("projectId");
            String taskId = pageContext.getParameter("taskId");
            if (projectId != null && !"".equals(projectId) && taskId != null && 
                !"".equals(taskId)) {
                String sql = 
                    "SELECT COUNT(0)\n" + "FROM   cux_pa_dlv_schedule_t\n" + 
                    "WHERE  project_id = " + projectId + "\n" + 
                    "AND    task_id = " + taskId;
                Number dsCount = (Number)this.getSqlValue(am, sql);
                if (dsCount.intValue() > 0) {
                    throw new OAException("该项目已添加交付物计划，不允许再从模板中选择!");
                } else {
                    HashMap parameters = new HashMap();
                    parameters.put("projectId", 
                                   pageContext.getParameter("projectId"));
                    parameters.put("taskId", 
                                   pageContext.getParameter("taskId")); // retain AM
                    pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/ds/webui/DlvScheduleTemplatePG", 
                                                   null, 
                                                   OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                                   null, parameters, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
                }
            }
        }
        if ("UpdateSchedule".equals(event)) {
            am.invokeMethod("doUpdateSchedule");
        }
        if ("DeleteSchedule".equals(event)) {
            am.invokeMethod("doDeleteSchedule");
        }
        if ("submitVerify".equals(event)) {
            am.invokeMethod("submitVerify");
        }
        if ("submitApprove".equals(event)) {
            am.invokeMethod("submitApprove");
        }
        if ("AddSchedule".equals(event)) {
            String projectId = pageContext.getParameter("projectId");
            String taskId = pageContext.getParameter("taskId");
            if (projectId != null && !"".equals(projectId) && taskId != null && 
                !"".equals(taskId)) {
                Serializable[] paras = { projectId, taskId };
                am.invokeMethod("doAddSchedule", paras);
            }
        }
        /*
        if ("ReleaseSpecialityChange".equals(event)) {
            //String specReleaseSpecialty = pageContext.getParameter("SpecReleaseSpecialty");
            String eventRow = pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE);
            Row row = am.findRowByRef(eventRow);
            ViewObject vo = am.findViewObject("DlvScheduleSpecVO1");
            Row[] rows = vo.getFilteredRows("ReleaseSpecialty",row.getAttribute("ReleaseSpecialty"));
            if (rows.length > 1) {
                row.setAttribute("ReleaseSpecialty",null);
                throw new OAException("该专业已配置！",OAException.ERROR);
            }
        }
        */
        if ("save".equals(event)) {
            am.getOADBTransaction().commit();
            throw new OAException("保存成功!", OAException.INFORMATION);
        }
        if ("Back".equals(event)) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/ds/webui/DsSearchPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);

        }
        if (pageContext.isLovEvent()) {
            String lovInputSourceId = pageContext.getLovInputSourceId();
            if ("SpecialtyManagerName".equals(lovInputSourceId) && 
                "lovUpdate".equals(event)) {
                String eventRow = 
                    pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE);
                Row row = am.findRowByRef(eventRow);
                Number SpecialtyManager = 
                    (Number)row.getAttribute("SpecialtyManager");
                String sql = 
                    "SELECT cux_common_pkg.get_org_name_byid(cux_common_pkg.get_person_primary_deptid(" + 
                    SpecialtyManager.intValue() + "))\n" + "FROM   dual";
                String deptName = (String)this.getSqlValue(am, sql);
                row.setAttribute("DeptName", deptName);
            }
            if ("ReleaseSpecialtyName".equals(lovInputSourceId) && 
                ("lovUpdate".equals(event) || "lovValidate".equals(event))) {
                String eventRow = 
                    pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE);
                Row row = am.findRowByRef(eventRow);
                ViewObject vo = am.findViewObject("DlvScheduleSpecVO1");
                Row[] rows = 
                    vo.getFilteredRows("ReleaseSpecialty", row.getAttribute("ReleaseSpecialty"));
                if (rows.length > 1) {
                    row.setAttribute("ReleaseSpecialty", null);
                    row.setAttribute("ReleaseSpecialtyName", null);
                    throw new OAException("该专业已配置！", OAException.ERROR);
                }
            }
        }
    }

    public String getWrappedSqlValue(OAApplicationModule am, String sql) {
        ViewObject vo = am.findViewObject("QueryVO");
        String result = "";
        if (vo != null) {
            vo.remove();
        }
        vo = am.createViewObjectFromQueryStmt("QueryVO", sql);
        vo.setMaxFetchSize(-1);
        vo.executeQuery();
        while (vo.hasNext()) {
            if (result == "") {
                result += "\"" + (String)vo.next().getAttribute(0) + "\"";
            } else {
                result += ",\n\"" + (String)vo.next().getAttribute(0) + "\"";
            }
        }
        return result;
    }

    public String wrapInputAutocomplete(String idSelector, String sourceName) {
        String jsStr = 
            "$( \"input[id*='" + idSelector + "']\" )\n" + "  // don't navigate away from the field on tab when selecting an item\n" + 
            "  .on( \"keydown\", function( event ) {\n" + 
            "	if ( event.keyCode === $.ui.keyCode.TAB &&\n" + 
            "		$( this ).autocomplete( \"instance\" ).menu.active ) {\n" + 
            "	  event.preventDefault();\n" + "	}\n" + "  })\n" + 
            "  .autocomplete({\n" + "	minLength: 0,\n" + 
            "	source: function( request, response ) {\n" + 
            "	  // delegate back to autocomplete, but extract the last term\n" + 
            "	  response( $.ui.autocomplete.filter(\n" + "		" + sourceName + 
            ", extractLast( request.term ) ) );\n" + "	},\n" + 
            "	focus: function() {\n" + 
            "	  // prevent value inserted on focus\n" + "	  return false;\n" + 
            "	},\n" + "	select: function( event, ui ) {\n" + 
            "	  var terms = split( this.value );\n" + 
            "	  // remove the current input\n" + "	  terms.pop();\n" + 
            "	  // add the selected item\n" + 
            "	  terms.push( ui.item.value );\n" + 
            "	  this.value = terms.join(';');\n" + "	  return false;\n" + 
            "	}\n" + "  });";
        return jsStr;
    }

    public void initQueryOnlyPage(OAPageContext pageContext, 
                                  OAWebBean webBean) {
        String[] disRenderBeanNames = 
        { "save", "submitVerify", "submitApprove", "OK", "copyFromTemplate", 
          "updateSpec", "addSpec", "UpdateSchedule", "DeleteSchedule", 
          "AddSchedule", "deleteSpec" };
        for (String beanName: disRenderBeanNames) {
            OAWebBean bean = webBean.findChildRecursive(beanName);
            bean.setAttributeValue(this.RENDERED_ATTR, false);
        }
        String[] readonlyBeanNames = 
        { "projectNum", "projectName", "taskNum", "multipleSelection1", 
          "multipleSelection2" };
        for (String beanName: readonlyBeanNames) {
            OAWebBean bean = webBean.findChildRecursive(beanName);
            bean.setAttributeValue(this.READ_ONLY_ATTR, true);
        }
        String[] renderBeanNames = { "back" };
        for (String beanName: renderBeanNames) {
            OAWebBean bean = webBean.findChildRecursive(beanName);
            bean.setAttributeValue(this.RENDERED_ATTR, true);
        }
    }
}
