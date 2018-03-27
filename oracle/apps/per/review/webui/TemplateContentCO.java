/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.TemplateClassVOImpl;
import cux.oracle.apps.per.review.server.TemplateContentVOImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAInnerDataObjectEnumerator;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class TemplateContentCO extends OAControllerImpl {
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
        String tmplId = pageContext.getParameter("tmplId");
        if (tmplId != null) {
            pageContext.putSessionValue("tmplId", tmplId);
        } else
            tmplId = (String)pageContext.getSessionValue("tmplId");

        if (tmplId != null) {
            Serializable params[] = { tmplId, "pop" };
            String result = (String)am.invokeMethod("processTemplate", params);
            if (result != null && result.equals("N")) {
                throw new OAException("CUX", "CUX_INIT_TMPL_ERROR", null, 
                                      OAException.ERROR, null);
            }
            Serializable parameters[] = { tmplId };
            am.invokeMethod("queryTemplate", parameters);
            am.invokeMethod("queryTemplateClass", parameters);
            am.invokeMethod("initTmplContent", 
                            parameters); //find all KPIs related to current template
        }

        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateTmplContentTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateTmplContentTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        // Link the outerTable and innerTable
        OAAdvancedTableBean outerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("TemplateClassVO1");
        OAAdvancedTableBean innerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("TemplateContentVO1");
        if (outerTable != null) {
            outerTable.setAttributeValue(CHILD_VIEW_ATTRIBUTE_NAME, 
                                         "KpiClass");
            //outerTable.setAttributeValue(CHILD_VIEW_ATTRIBUTE_NAME,"TmplId"); 
            outerTable.setAttributeValue(VIEW_LINK_NAME, "TmplContentVL1");
        }

        if (innerTable != null) {
            innerTable.setAttributeValue(CHILD_VIEW_ATTRIBUTE_NAME, 
                                         "KpiClass1");
            //innerTable.setAttributeValue(CHILD_VIEW_ATTRIBUTE_NAME,"TmplId1"); 
            innerTable.setAttributeValue(VIEW_LINK_NAME, "TmplContentVL1");
            innerTable.prepareForRendering(pageContext);
            //innerTable.setInsertable(true);
            //innerTable.setAutoInsertion(false);
        }

        if (pageContext.getParameter("refresh") != null) {
            ;
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
        OAAdvancedTableBean tableBean = 
            (OAAdvancedTableBean)webBean.findChildRecursive("TemplateContentVO1");
        String tmplId = (String)pageContext.getSessionValue("tmplId");

        // Handle adding a new row
        if (tableBean != null && 
            ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM))) {
            //Serializable[] params = {tmplId};
            //am.invokeMethod("createTmplContent");
            //am.invokeMethod("setTmplforLine", params);

            OAViewObject vo = 
                (OAViewObject)am.findViewObject("TemplateClassVO1");
            Row p = vo.getCurrentRow();
            String kpiClass = null;
            if (p != null)
                kpiClass = (String)p.getAttribute("KpiClass");

            OAInnerDataObjectEnumerator odom = 
                new OAInnerDataObjectEnumerator(pageContext, tableBean);

            while (odom.hasMoreElements()) {
                RowSet innerRowSet = (RowSet)odom.nextElement();
                OAViewObject innerViewObject = 
                    (OAViewObject)innerRowSet.getViewObject();
                String newWhereClause = "KPI_CLASS = :1";
                innerViewObject.setWhereClause(newWhereClause);
                innerViewObject.setWhereClauseParam(0, kpiClass);
                innerViewObject.executeQuery();
                Row row = 
                    innerViewObject.createAndInitRow(new NameValuePairs(new String[] { "KpiClass" }, 
                                                                        new Object[] { kpiClass }));
                row.setNewRowState(Row.STATUS_INITIALIZED);
                Number contentId = 
                    am.getOADBTransaction().getSequenceValue("CUX_TMPL_CONTENT_S");
                row.setAttribute("ContentId", contentId);
                row.setAttribute("TmplId", tmplId);
                innerViewObject.insertRowAtRangeIndex(innerViewObject.getRowCount(), 
                                                      row);
                innerViewObject.setCurrentRow(row);
            }
        }

        //
        if (pageContext.getParameter("Update") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateKPIPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle delete
        if ("deleteTmplKpi".equals(pageContext.getParameter("event"))) {
            String contentId = pageContext.getParameter("contentId");
            Serializable params[] = { contentId };
            am.invokeMethod("deleteTmplContent", params);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateContentPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        // Handle 
        if ("updateTmplClass".equals(pageContext.getParameter("event"))) {
            //String kpiClass = pageContext.getParameter("kpiClass");
            //String templateId = pageContext.getParameter("tmplId");
            /*20100204 zs*/
            String kpiClassWeight = pageContext.getParameter("kpiClassWeight");
            if (kpiClassWeight != null && !kpiClassWeight.equals("")) {
                pageContext.putSessionValue("kpiClassWeight", kpiClassWeight);
            } else {
                throw new OAException("CUX", "CUX_KPI_WEIGHT_NEED_WAR", null, 
                                      OAException.ERROR, null);
            }
            /*20100204 zs*/
            String kpiClassType = pageContext.getParameter("kpiClassType");
            if (kpiClassType != null && kpiClassType.trim().equals("Y")) {
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateKPIPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
            } else {
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateKPIEnterPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
            }
        }

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateTmplContentTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            am.invokeMethod("validateTmplStdWeight");
            am.invokeMethod("commit");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_TMPL_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }


    }
}
