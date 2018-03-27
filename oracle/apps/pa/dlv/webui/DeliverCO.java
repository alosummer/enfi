/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 |  25-NOV-2016 Wei Yi Created                                               |
 +===========================================================================*/
package cux.oracle.apps.pa.dlv.webui;

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
import oracle.apps.fnd.framework.webui.beans.table.OAAddTableRowBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableUtils;

import oracle.cabo.ui.beans.ScriptBean;
import oracle.cabo.ui.data.DictionaryData;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import oracle.oc4j.admin.management.mbeans.ClassLoader;

/**
 * Controller for ...
 */
public class DeliverCO extends ControllerUtil {
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
        if (pageContext.isLoggingEnabled(oracle.apps.fnd.framework.OAFwkConstants.PROCEDURE)) {
            pageContext.writeDiagnostics(this, "start processRequest", 
                                         oracle.apps.fnd.framework.OAFwkConstants.PROCEDURE);
        }
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OATableBean dlvTable = 
            (OATableBean)webBean.findChildRecursive("DelierableVO1");
        dlvTable.setInsertable(true);
        dlvTable.setAutoInsertion(false);
        dlvTable.setAttributeValue(OATableUtils.NEW_ROW_INITIALIZED, 
                                   Boolean.TRUE);
        dlvTable.prepareForRendering(pageContext);
        OAAddTableRowBean addRowBean = 
            (OAAddTableRowBean)dlvTable.getColumnFooter();
        if (addRowBean != null) {
            addRowBean.setText("添加5行");
            addRowBean.setRows(5);
        }
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
        OARawTextBean rawText = 
            (OARawTextBean)webBean.findChildRecursive("rawText");
        String specs = (String)pageContext.getSessionValue("specs");
        if (specs == null || "".equals(specs)) {
            specs = this.getWrappedSqlValue(am, specSql);
            pageContext.putSessionValue("specs", specs);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"jquery-ui.css\"/>\n");
        sb.append("<script type=\"text/javascript\">\n");
        sb.append("$(document).ready(function(){\n");
        sb.append("var specs = [" + specs + "];\n");
        sb.append(wrapInputAutocomplete("AcceptSpecialtyName", "specs"));
        sb.append("})\n</script>\n");
        rawText.setText(sb.toString());
        am.invokeMethod("init");
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
        if (pageContext.isLoggingEnabled(oracle.apps.fnd.framework.OAFwkConstants.PROCEDURE)) {
            pageContext.writeDiagnostics(this, "start processFormRequest", 
                                         oracle.apps.fnd.framework.OAFwkConstants.PROCEDURE);
        }
        String event = pageContext.getParameter(this.EVENT_PARAM);
        String source = pageContext.getParameter(this.SOURCE_PARAM);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OATableBean dlvTable = 
            (OATableBean)webBean.findChildRecursive("DelierableVO1");

        if ("Search".equals(event)) {
            String projectId = pageContext.getParameter("QProjectId");
            String docTypeCode = pageContext.getParameter("QDocTypeId");
            String docTypeName = pageContext.getParameter("QTaskId");

            Serializable paras[] = { projectId, docTypeCode, docTypeName };

            am.invokeMethod("doSearch", paras);
        }
        if ("Save".equals(event)) {
            am.invokeMethod("doSave");
            throw new OAException("保存成功!", OAException.INFORMATION);
        }
        if ("Submit".equals(event)) {
            am.invokeMethod("doSubmit");
        }
        if (dlvTable.getName(pageContext).equals(source) && 
            this.ADD_ROWS_EVENT.equals(event)) {
            Integer rowCount = 5;
            String projectId = pageContext.getParameter("QProjectId");
            String qTaskId = pageContext.getParameter("QTaskId");
            Serializable paras[] = { rowCount, projectId, qTaskId };
            Class[] paraTypes = { Integer.class, String.class, String.class };
            am.invokeMethod("addRows", paras, 
                            paraTypes); // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);

        }
        if ("Update".equals(event)) {
            am.invokeMethod("doUpdate"); // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
        if ("Delete".equals(event)) {
            am.invokeMethod("doDelete"); // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
        if (pageContext.isLovEvent()) {
            if ("QProjectName".equals(source) && 
                ("lovUpdate".equals(event) || "lovValidate".equals(event))) {
                if (pageContext.getSessionValue("ProjectId") != null) {
                    pageContext.removeSessionValue("ProjectId");
                }
                pageContext.putSessionValue("ProjectId", 
                                            pageContext.getParameter("QProjectId"));
            } // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
        if ("Copy".equals(event)) {
            am.invokeMethod("copy"); // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
        if (pageContext.isLovEvent()) {
            String lovInputSourceId = pageContext.getLovInputSourceId();
            if ("DocTypeName".equals(lovInputSourceId) && 
                "lovUpdate".equals(event)) {
                String rowRef = 
                    pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE);
                Row row = am.findRowByRef(rowRef);
                String docType = 
                    (String)row.getAttribute("DocType"); //pageContext.getParameter("DocType");
                if ("3.3.001".equals(docType) || "3.3.002".equals(docType) || 
                    "3.3.003".equals(docType) || "3.3.004".equals(docType) || 
                    "3.2.010".equals(docType)) {
                    row.setAttribute("HqjtEnabled", false);
                } else {
                    row.setAttribute("HqjtEnabled", true);
                }
                if ("3.2.001".equals(docType) || "3.2.002".equals(docType) || 
                    "3.2.003".equals(docType) || "3.2.004".equals(docType) || 
                    "3.2.005".equals(docType) || "3.2.006".equals(docType) || 
                    "3.2.008".equals(docType) || "3.2.009".equals(docType) || 
                    "3.1.032".equals(docType) || "3.1.033".equals(docType)) {
                    row.setAttribute("WjhzEnabled", false);
                } else {
                    row.setAttribute("WjhzEnabled", true);
                }
            } // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
        if ("QProjectName".equals(source) && "lovUpdate".equals(event)) {
            String projectId = pageContext.getParameter("QProjectId");
            String empId = pageContext.getEmployeeId() + "";
            Serializable[] paras = { projectId, empId };
            am.invokeMethod("initDlvCfg", paras); // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
        if (("QTaskName").equals(source) && 
            ("lovUpdate".equals(event) || "lovValidate".equals(event))) {
            Hashtable lovResults = 
                pageContext.getLovResultsFromSession("QTaskName");
            String totalWeight = (String)lovResults.get("UnassignedWeight");
            ViewObject vo = am.findViewObject("SearchPVO1");
            Row row = vo.first();
            row.setAttribute("TotalWeight", totalWeight);
        }
        if ("AddCfg".equals(event)) {
            String projectId = pageContext.getParameter("QProjectId");
            String empId = pageContext.getEmployeeId() + "";
            if (projectId == null || "".equals(projectId)) {
                throw new OAException("请选择项目,然后继续！", OAException.ERROR);
            } else {
                Serializable[] paras = { projectId, empId };
                am.invokeMethod("doAddCfg", paras);
            } // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
        if ("Assign".equals(event)) {
            am.invokeMethod("doAssign"); // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(null, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
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
            "   if ( event.keyCode === $.ui.keyCode.TAB &&\n" + 
            "           $( this ).autocomplete( \"instance\" ).menu.active ) {\n" + 
            "     event.preventDefault();\n" + "        }\n" + "  })\n" + 
            "  .autocomplete({\n" + "   minLength: 0,\n" + 
            "   source: function( request, response ) {\n" + 
            "     // delegate back to autocomplete, but extract the last term\n" + 
            "     response( $.ui.autocomplete.filter(\n" + "            " + 
            sourceName + ", extractLast( request.term ) ) );\n" + "  },\n" + 
            "   focus: function() {\n" + 
            "     // prevent value inserted on focus\n" + 
            "       return false;\n" + "   },\n" + 
            "       select: function( event, ui ) {\n" + 
            "     var terms = split( this.value );\n" + 
            "     // remove the current input\n" + "      terms.pop();\n" + 
            "     // add the selected item\n" + 
            "     terms.push( ui.item.value );\n" + 
            "     this.value = terms.join(';');\n" + "     return false;\n" + 
            "  }\n" + "  });";
        return jsStr;
    }

}
