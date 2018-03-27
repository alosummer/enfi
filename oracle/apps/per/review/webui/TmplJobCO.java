/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.KeyPIVOImpl;

import cux.oracle.apps.per.review.server.TmplJobVOImpl;

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
public class TmplJobCO extends OAControllerImpl {
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
                                                       "CreateTmplJobTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateTmplJobTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        TmplJobVOImpl vo = (TmplJobVOImpl)am.findViewObject("TmplJobVO1");
        if (pageContext.getParameter("refresh") != null) {
            vo.executeQuery();
        }

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("TmplJobVO1");
        tableBean.prepareForRendering(pageContext);
        tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);

        OAPageLayoutBean page = (OAPageLayoutBean)webBean;
        String tmplId = pageContext.getParameter("tmplId");
        String tmplName = pageContext.getParameter("tmplName");

        String title = "";
        String prefix = "����ְ��( ";
        String suffix = " )";
        try {
            prefix = new String(prefix.getBytes("ISO-8859-1"), "GB2312");
            suffix = new String(suffix.getBytes("ISO-8859-1"), "GB2312");
        } catch (UnsupportedEncodingException e) {
            ;
        }

        title = prefix + tmplName + suffix;
        page.setTitle(title);

        if (tmplId != null) {
            Serializable params[] = { tmplId };
            am.invokeMethod("initTemplateJob", params);
            pageContext.putSessionValue("tmplId", tmplId);
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
            (OATableBean)webBean.findChildRecursive("TmplJobVO1");
        // Handle adding a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            OAViewObject vo = (OAViewObject)am.findViewObject("TmplJobVO1");
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = vo.createRow();
            vo.insertRowAtRangeIndex(vo.getFetchedRowCount(), row);
            row.setNewRowState(Row.STATUS_INITIALIZED);
            String tmplId = (String)pageContext.getSessionValue("tmplId");
            row.setAttribute("TmplId", tmplId);
            vo.setCurrentRow(row);
        }

        // Handle Delete action
        if ("delete".equals(pageContext.getParameter("event"))) {
            String tmplId = pageContext.getParameter("tmplId");
            String jobId = pageContext.getParameter("jobId");
            OAException mainMessage = 
                new OAException("CUX", "CUX_DEL_TMPL_JOB_WARN", null, 
                                OAException.WARNING, null);

            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("DeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(2);
            formParams.put("tmplId", tmplId);
            formParams.put("jobId", jobId);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);

        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String result = "";
            String tmplId = pageContext.getParameter("tmplId");
            String jobId = pageContext.getParameter("jobId");
            Serializable[] params = { tmplId, jobId };
            result = (String)am.invokeMethod("delTemplateJob", params);
            OAException confirmMessage = 
                new OAException("CUX", "CUX_DEL_TMPL_JOB_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TmplJobPG&refresh=Y", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateDimensionTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            am.invokeMethod("commit");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_TMPL_JOB_SAVED", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateManagePG&refresh=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }


    }

}
