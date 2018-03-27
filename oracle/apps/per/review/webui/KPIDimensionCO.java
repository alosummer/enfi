/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.KPIDimensionVOImpl;
import cux.oracle.apps.per.review.server.KeyPIVOImpl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

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
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class KPIDimensionCO extends OAControllerImpl {
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
        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateDimensionTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateDimensionTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OAPageLayoutBean page = (OAPageLayoutBean)webBean;
        String kpiId = pageContext.getParameter("kpiId");
        String kpiName = pageContext.getParameter("kpiName");

        String title = "";
        String prefix = "ά�ȶ���( ";
        String suffix = " )";
        try {
            prefix = new String(prefix.getBytes("ISO-8859-1"), "GB2312");
            suffix = new String(suffix.getBytes("ISO-8859-1"), "GB2312");
        } catch (UnsupportedEncodingException e) {
            ;
        }

        title = prefix + kpiName + suffix;
        page.setTitle(title);

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        //KPIDimensionVOImpl vo = (KPIDimensionVOImpl)am.findViewObject("KPIDimensionVO1");
        if (kpiId != null) {
            Serializable params[] = { kpiId };
            am.invokeMethod("initKPIDimension", params);
            pageContext.putSessionValue("kpiId", kpiId);
        }

        if (pageContext.getParameter("refresh") != null) {
            KPIDimensionVOImpl vo = 
                (KPIDimensionVOImpl)am.findViewObject("KPIDimensionVO1");
            vo.executeQuery();
        }

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("KPIDimensionVO1");
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
            (OATableBean)webBean.findChildRecursive("KPIDimensionVO1");
        // Handle adding a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("KPIDimensionVO1");
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = vo.createRow();
            vo.insertRowAtRangeIndex(vo.getRowCount(), row);
            row.setNewRowState(Row.STATUS_INITIALIZED);
            Number dimensionId = 
                am.getOADBTransaction().getSequenceValue("CUX_KPI_DIMENSION_S");
            row.setAttribute("DimensionId", dimensionId);
            String kpiId = (String)pageContext.getSessionValue("kpiId");
            row.setAttribute("KpiId", kpiId);
            vo.setCurrentRow(row);
        }

        // Handle Delete action
        if ("delete".equals(pageContext.getParameter("event"))) {
            String dimensionId = pageContext.getParameter("dimensionId");
            String dimensionName = pageContext.getParameter("dimensionName");
            MessageToken tokens[] = 
            { new MessageToken("DIMENSION_NAME", dimensionName) };
            OAException mainMessage = 
                new OAException("CUX", "CUX_DEL_DIMENSION_WARN", tokens, 
                                OAException.WARNING, null);

            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("DeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(2);
            formParams.put("dimensionId", dimensionId);
            formParams.put("dimensionName", dimensionName);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);

        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String result = "";
            String dimensionId = pageContext.getParameter("dimensionId");
            String dimensionName = pageContext.getParameter("dimensionName");
            Serializable[] params = { dimensionId };
            result = (String)am.invokeMethod("delKPIDimension", params);
            MessageToken tokens[] = 
            { new MessageToken("DIMENSION_NAME", dimensionName) };
            OAException confirmMessage = null;
            confirmMessage = 
                    new OAException("CUX", "CUX_DEL_DIMENSION_CONF", tokens, 
                                    OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIDimensionPG&refresh=Y", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateDimensionTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            am.invokeMethod("commit");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_KPI_DIMENSION_SAVED", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG&refresh=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }
    }

}
