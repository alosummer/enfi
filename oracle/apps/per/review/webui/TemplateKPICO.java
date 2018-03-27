/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.TemplateContentVOImpl;

import cux.oracle.apps.per.review.server.TemplateKPIVOImpl;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class TemplateKPICO extends OAControllerImpl {
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
        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "updateTmplKPITxn");
            if (!pageContext.isFormSubmission()) {
                String tmplId = pageContext.getParameter("tmplId");
                String kpiClass = pageContext.getParameter("kpiClass");
                //              System.out.println("tmplId=" + tmplId);
                //              System.out.println("kpiClass=" + kpiClass);

                if (tmplId != null) {
                    pageContext.putSessionValue("tmplId", tmplId);
                    Serializable params[] = { tmplId };
                    am.invokeMethod("queryTmplKpiClasses", params);
                }

                if (kpiClass != null) {
                    pageContext.putSessionValue("kpiClass", kpiClass);
                }

                Serializable parameters[] = { tmplId, kpiClass };
                am.invokeMethod("queryTmplKpi", parameters);
                /*20090927 zs*/
                String kpiWeightCheckMethod = 
                    pageContext.getParameter("kpiWeightCheckMethod");
                if (kpiWeightCheckMethod == null || 
                    kpiWeightCheckMethod.equals("")) {
                    kpiWeightCheckMethod = 
                            (String)pageContext.getSessionValue("kpiWeightCheckMethod");
                }
                System.out.println("kpiWeightCheckMethod=" + 
                                   kpiWeightCheckMethod);
                pageContext.putSessionValue("kpiWeightCheckMethod", 
                                            kpiWeightCheckMethod);

                String kpiClassWeight = 
                    pageContext.getParameter("kpiClassWeight");
                if (kpiClassWeight == null || kpiClassWeight.equals("")) {
                    kpiClassWeight = 
                            (String)pageContext.getSessionValue("kpiClassWeight");
                }
                System.out.println("kpiClassWeight=" + kpiClassWeight);
                pageContext.putSessionValue("kpiClassWeight", kpiClassWeight);
                /*20090927 zs*/
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "updateTmplKPITxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }


        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("TemplateContentVO1");
        tableBean.prepareForRendering(pageContext);
        tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);

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
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("TemplateContentVO1");

        System.out.println("event=" + pageContext.getParameter("event"));
        System.out.println("source=" + pageContext.getParameter("source"));
        // Handle add a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("TemplateKPIVO1");
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = vo.createRow();
            String kpiClass = (String)pageContext.getSessionValue("kpiClass");
            //Row row = vo.createAndInitRow(new NameValuePairs(new String[]{"KpiClass"}, new Object[]{kpiClass}));
            //vo.insertRowAtRangeIndex(vo.getFetchedRowCount(),row);
            /*20100827 zs*/
            vo.insertRow(row);
            /*20100827 zs*/
            row.setNewRowState(Row.STATUS_INITIALIZED);
            Number contentId = 
                am.getOADBTransaction().getSequenceValue("CUX_TMPL_CONTENT_S");
            row.setAttribute("ContentId", contentId);
            String tmplId = (String)pageContext.getSessionValue("tmplId");
            row.setAttribute("TmplId", tmplId);
            row.setAttribute("KpiClass", kpiClass);
            vo.setCurrentRow(row);
        }

        // Handle queryTmplKpi action
        if ("queryTmplKpi".equals(pageContext.getParameter("event"))) {
            String tmplId = pageContext.getParameter("TemplateId");
            String kpiClass = pageContext.getParameter("ChooseKpiClass");
            if (kpiClass != null && tmplId != null) {
                pageContext.putSessionValue("kpiClass", kpiClass);
                pageContext.putSessionValue("tmplId", tmplId);
                Serializable parameters[] = { tmplId, kpiClass };
                am.invokeMethod("queryTmplKpi", parameters);
            }
        }

        // Handle delete event
        if ("delete".equals(pageContext.getParameter("event"))) {
            String contentId = pageContext.getParameter("contentId");
            Serializable params[] = { contentId };
            am.invokeMethod("deleteTmplContent", params);
            // 2014-9-3, edit by yang.gang,begin,删除一条考核指标后，重定向的页面应为 TemplateKPIPG --> TemplateKPIEnterPG
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateKPIEnterPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
            // 2014-9-3, edit by yang.gang,end
        }

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "updateTmplKPITxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateContentPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            /*20090927 zs*/
            //am.invokeMethod("validateTmplKpiWeight");
            String kpiWeightCheckMethod = 
                (String)pageContext.getSessionValue("kpiWeightCheckMethod");
            String kpiClassWeight = 
                (String)pageContext.getSessionValue("kpiClassWeight");
            Serializable para[] = { kpiWeightCheckMethod, kpiClassWeight };
            ///*20090928 zs*/am.invokeMethod("validateTmplKpiWeightByMethod",para);
            /*20090927 zs*/
            am.invokeMethod("commit");

            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_TMPL_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);

            HashMap param = new HashMap(1);
            String tmplId = pageContext.getParameter("TmplId");
            param.put("tmplId", tmplId);
            try {
                pageContext.sendRedirect("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateContentPG");
            } catch (Exception e) {
                ;
            }

            //pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateContentPG&refresh=Y"
            //                              , null, (byte)0, null, param, true, "N");
        }
    }
}
