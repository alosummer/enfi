/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.KPIConstraintVOImpl;

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
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class KPIConstraintCO extends OAControllerImpl {
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

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("KPIConstraintVO1");
        //OAMessageChoiceBean constraintType = 
        //  (OAMessageChoiceBean)tableBean.findIndexedChildRecursive("ConstraintType");
        //if (constraintType != null){
        //  constraintType.setPickListCacheEnabled(false);
        //}
        System.out.println("1x");
        /*
      OAMessageChoiceBean constraintObj =
        (OAMessageChoiceBean)tableBean.findIndexedChildRecursive("ConstraintObj");
      if (constraintObj != null){
        constraintObj.setListVOBoundContainerColumn(0, tableBean, "ConstraintType");
        constraintObj.setPickListCacheEnabled(false);
      }
      */

        //OAMessageChoiceBean constraintJob = 
        //  (OAMessageChoiceBean)tableBean.findIndexedChildRecursive("ConstraintJob");
        //if (constraintJob != null){
        //constraintJob.setListVOBoundContainerColumn(0, tableBean, "ConstraintType");
        //constraintJob.setPickListCacheEnabled(false);

        //}

        tableBean.queryData(pageContext);
        tableBean.prepareForRendering(pageContext);
        tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);

        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateKPIConstraintTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateKPIConstraintTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        String kpiId = pageContext.getParameter("kpiId");
        if ((kpiId == null || kpiId.trim().equals("")) && 
            pageContext.getSessionValue("kpiId") != null) { //fixed bug
            kpiId = pageContext.getSessionValue("kpiId").toString();
            //System.out.println("kpiId1 : " + kpiId);
        }
        String kpiName = pageContext.getParameter("kpiName");
        if (kpiId != null) { //fixed bug
            //System.out.println("kpiId2 : " + kpiId);
            pageContext.putSessionValue("kpiId", kpiId);
            if (kpiName != null)
                pageContext.putSessionValue("kpiName", kpiName);
            Serializable params[] = { kpiId };
            am.invokeMethod("initKpiConstraints", params);
        }

        if (pageContext.getParameter("refresh") != null) {
            KPIConstraintVOImpl vo = 
                (KPIConstraintVOImpl)am.findViewObject("KPIConstraintVO1");
            vo.executeQuery();
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
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("KPIConstraintVO1");
        System.out.println("2x");
        OAViewObject vo1 = (OAViewObject)am.findViewObject("KPIConstraintVO1");
        Row row1 = vo1.getCurrentRow();
        if (row1 != null) {

        }

        // Handle adding a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("KPIConstraintVO1");
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = vo.createRow();
            //Row row = vo.createAndInitRow(new NameValuePairs(new String[]{"KpiId"}, new Object[]{kpiId}));
            //vo.insertRowAtRangeIndex(vo.getRowCount(),row);
            //vo.insertRowAtRangeIndex(vo.getFetchedRowCount(),row);
            // ����ڶ�ҳ���������
            /*20100831 zs*/
            /*vo.insertRowAtRangeIndex(vo.getRangeSize(),row);*/
            vo.insertRow(row);
            /*20100831 zs*/
            row.setNewRowState(Row.STATUS_INITIALIZED);
            Number constraintId = 
                am.getOADBTransaction().getSequenceValue("CUX_KPI_CONSTRAINT_S");
            row.setAttribute("ConstraintId", constraintId);
            String kpiId = (String)pageContext.getSessionValue("kpiId");
            row.setAttribute("KpiId", kpiId);
            vo.setCurrentRow(row);
        }

        // Handle refresh LOV constraint object
        if ("refreshLov".equals(pageContext.getParameter("event"))) {
            OAMessageChoiceBean type = 
                (OAMessageChoiceBean)tableBean.findIndexedChildRecursive("ConstraintType");
            String constraintType = type.getSelectionValue(pageContext);
            Serializable[] parameters = { constraintType };
            am.invokeMethod("poplistConstraintObjs", parameters);
        }

        // Handle delete
        if ("delete".equals(pageContext.getParameter("event"))) {
            String constraintId = pageContext.getParameter("constraintId");
            Serializable params[] = { constraintId };
            am.invokeMethod("deleteKPIConstraint", params);

            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIConstraintPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        // Handle delete
        if ("detail".equals(pageContext.getParameter("event"))) {
            String kpiId = pageContext.getParameter("kpiId");
            String constraintId = pageContext.getParameter("constraintId");
            Serializable params[] = { constraintId };
            String result = 
                (String)am.invokeMethod("validateConstraint", params);

            if (result != null && result.equals("N")) {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_KPI_DIM_VALUE_WARNING", null, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            } else { // Retain AM
                // Show breadcrumbs 
                pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIDimensionValPG", 
                                          null, 
                                          OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                          null, null, true, 
                                          OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                          OAWebBeanConstants.IGNORE_MESSAGES);
            }
        }

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateKPIConstraintTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }
        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            am.getOADBTransaction().commit();
            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVED_KPI_CONSTRAINT", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle create constraint action
        if (pageContext.getParameter("CreateConstraint") != 
            null) { // Retain AM
            // Show breadcrumbs 
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPICreateConstraintPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }

    }
}
