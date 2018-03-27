/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.TemplateVOImpl;

import cux.oracle.apps.per.review.server.TemplateVORowImpl;
import cux.oracle.apps.per.review.server.TmplDetailVORowImpl;

import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAQueryUtils;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.bali.share.util.BooleanUtils;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.Transaction;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class TemplateManageCO extends OAControllerImpl {
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
        OAApplicationModuleImpl am = 
            (OAApplicationModuleImpl)pageContext.getApplicationModule(webBean);
        TemplateVOImpl vo = (TemplateVOImpl)am.findViewObject("TemplateVO1");
        if (pageContext.getParameter("refresh") != null)
            vo.executeQuery();

        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateTmplTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateTmplTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("TemplateVO1");
        tableBean.prepareForRendering(pageContext);
        tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);

        OAMessageChoiceBean msSearchTmplGroup = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("SearchTmplGroup");
        msSearchTmplGroup.setPickListCacheEnabled(false);

        OAMessageChoiceBean msSearchTmplJob = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("SearchTmplJob");
        msSearchTmplJob.setPickListCacheEnabled(false);

        OAMessageChoiceBean msGroupId = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("GroupId");
        msGroupId.setPickListCacheEnabled(false);
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
        OAApplicationModuleImpl am = 
            (OAApplicationModuleImpl)pageContext.getApplicationModule(webBean);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("TemplateVO1");

        // Handle adding a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            OAViewObject vo = (OAViewObject)am.findViewObject("TemplateVO1");
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = vo.createRow();
            int i = 0;
            i = vo.getRowCount();
            if (i == 0) {
                vo.insertRow(row);
            } else {
                vo.insertRowAtRangeIndex(vo.getRangeSize(), row);
            }
            row.setNewRowState(Row.STATUS_INITIALIZED);
            Number tmplId = 
                am.getOADBTransaction().getSequenceValue("CUX_REVIEW_TMPL_S");
            row.setAttribute("TmplId", tmplId);
            vo.setCurrentRow(row);
        }

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateTmplTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        if (pageContext.getParameter("Copy") != null) {
            OAViewObject vo = (OAViewObject)am.findViewObject("TemplateVO1");
            int fetchedRowCount = vo.getFetchedRowCount();
            RowSetIterator iter = vo.createRowSetIterator("selectIter");
            TemplateVORowImpl row = null;
            Number tmplId = null;
            boolean tmplSelected = false;
            if (fetchedRowCount > 0) {
                iter.setRangeStart(0);
                iter.setRangeSize(fetchedRowCount);
                for (int i = 0; i < fetchedRowCount; i++) {
                    row = (TemplateVORowImpl)iter.getRowAtRangeIndex(i);
                    if (row.getAttribute("Selected") != null && 
                        row.getAttribute("Selected").equals("Y")) {
                        tmplId = row.getTmplId();
                        tmplSelected = true;
                        break;
                    }
                }
            }

            if (!tmplSelected)
                throw new OAException("CUX", "CUX_COPY_TMPL_WARNING", null, 
                                      OAException.WARNING, null);
            else {
                String result = "";
                if (tmplId != null) {
                    Serializable params[] = { tmplId.toString(), "copy" };
                    result = 
                            (String)am.invokeMethod("processTemplate", params);
                }

                OAException confirmMessage = null;
                if (result != null && result.equals("Y"))
                    confirmMessage = 
                            new OAException("CUX", "CUX_COPY_TMPL_CONF", null, 
                                            OAException.CONFIRMATION, null);
                else
                    confirmMessage = 
                            new OAException("CUX", "CUX_COPY_TMPL_ERROR", null, 
                                            OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }

            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateManagePG&refresh=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");

        }


        // Attach org to template
        if ("orgDetail".equals(pageContext.getParameter("event"))) {
            String tmplId = pageContext.getParameter("tmplId");
            Serializable params[] = { tmplId, "validate" };
            String existFlag = 
                (String)am.invokeMethod("processTemplate", params);
            if (existFlag != null && existFlag.equals("N")) {
                throw new OAException("CUX", "CUX_SAVE_TMPL_WARNING", null, 
                                      OAException.WARNING, null);
            } else // Retain AM
                pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/TmplOrgPG", 
                                          null, 
                                          OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                          null, null, true, 
                                          OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                          OAWebBeanConstants.IGNORE_MESSAGES);
        }

        // Attach job to template ,add by fl at 20090916
        if ("jobDetail".equals(pageContext.getParameter("event"))) {
            String tmplId = pageContext.getParameter("tmplId");
            Serializable params[] = { tmplId, "validate" };
            String existFlag = 
                (String)am.invokeMethod("processTemplate", params);
            if (existFlag != null && existFlag.equals("N")) {
                throw new OAException("CUX", "CUX_SAVE_TMPL_WARNING", null, 
                                      OAException.WARNING, null);
            } else // Retain AM
                pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/TmplJobPG", 
                                          null, 
                                          OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                          null, null, true, 
                                          OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                          OAWebBeanConstants.IGNORE_MESSAGES);
        }

        // Attach emp to template
        if ("empDetail".equals(pageContext.getParameter("event"))) {
            String tmplId = pageContext.getParameter("tmplId");
            Serializable params[] = { tmplId, "validate" };
            String existFlag = 
                (String)am.invokeMethod("processTemplate", params);
            if (existFlag != null && existFlag.equals("N")) {
                throw new OAException("CUX", "CUX_SAVE_TMPL_WARNING", null, 
                                      OAException.WARNING, null);
            } else // Retain AM
                pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/TmplEmpPG", 
                                          null, 
                                          OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                          null, null, true, 
                                          OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                          OAWebBeanConstants.IGNORE_MESSAGES);
        }

        // Handle content
        if ("updateContent".equals(pageContext.getParameter("event"))) {
            String tmplId = pageContext.getParameter("tmplId");
            if (tmplId != null && !tmplId.equals("")) {
                Serializable params[] = { tmplId, "validate" };
                String existFlag = 
                    (String)am.invokeMethod("processTemplate", params);
                if (existFlag != null && existFlag.equals("N")) {
                    throw new OAException("CUX", 
                                          "CUX_SAVE_TMPL_HEADER_WARNING", null, 
                                          OAException.WARNING, null);
                } else { // Retain AM
                    pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateContentPG", 
                                              null, 
                                              OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                              null, null, true, 
                                              OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                              OAWebBeanConstants.IGNORE_MESSAGES);
                }
            }
        }

        // Handle delete
        if ("deleteTmpl".equals(pageContext.getParameter("event"))) {
            String tmplId = pageContext.getParameter("tmplId");
            String tmplName = pageContext.getParameter("tmplName");
            MessageToken tokens[] = 
            { new MessageToken("TMPL_NAME", tmplName) };

            OAException mainMessage = 
                new OAException("CUX", "CUX_DEL_TMPL_WARN", tokens, 
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
            formParams.put("tmplName", tmplName);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String tmplId = pageContext.getParameter("tmplId");
            String tmplName = pageContext.getParameter("tmplName");
            String result = "";
            Serializable[] params = { tmplId, "delete" };
            result = (String)am.invokeMethod("processTemplate", params);

            if (result != null && result.equals("Y")) {
                MessageToken tokens[] = { new MessageToken("TMPL", tmplName) };
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_DEL_TMPL_CONF", tokens, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);
            } else {
                MessageToken tokens[] = { new MessageToken("TMPL", tmplName) };
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_DEL_TMPL_ERR", tokens, 
                                    OAException.WARNING, null);
                pageContext.putDialogMessage(confirmMessage);
            }

            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateManagePG&refresh=Y", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            am.getOADBTransaction().commit();
            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_TMPL_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/TemplateManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        if (pageContext.getParameter("Search") != null) {
            OAQueryUtils.checkSelectiveSearchCriteria(pageContext, webBean);
            String tmplName = pageContext.getParameter("SearchTmplName");
            String groupId = pageContext.getParameter("SearchTmplGroup");
            String orgName = pageContext.getParameter("SearchTmplOrg");
            String jobId = pageContext.getParameter("SearchTmplJob");
            String personId = pageContext.getParameter("SearchPersonId");
            // OAApplicationModule am = pageContext.getApplicationModule(webBean);
            Boolean executeQuery = BooleanUtils.getBoolean(false);
            Serializable[] parameters = 
            { tmplName, groupId, orgName, jobId, personId, executeQuery };

            Class[] paramTypes = 
            { String.class, String.class, String.class, String.class, 
              String.class, Boolean.class };
            am.invokeMethod("initTmplQuery", parameters, paramTypes);

            OATableBean table = 
                (OATableBean)webBean.findChildRecursive("TemplateVO1");
            table.queryData(pageContext, false);
        }
    } //end of processFormRequest

}
