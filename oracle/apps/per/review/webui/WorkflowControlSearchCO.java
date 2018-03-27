/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.WorkflowControlAMImpl;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

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
import oracle.apps.fnd.framework.webui.beans.OAImageBean;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.bali.share.util.BooleanUtils;

/**
 * Controller for ...
 */
public class WorkflowControlSearchCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**1
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if (TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                              "UpdateTxn", 
                                                              false)) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, "UpdateTxn");
        }

        if (pageContext.getParameter("refreshflag") != null) {
            am.invokeMethod("initQuery");
        }
        /*20090926 zs  ��ЧרԱ ֻ���ύ���*/
        String role = pageContext.getParameter("role");
        if (role != null)
            pageContext.putSessionValue("role", role);
        else
            role = (String)pageContext.getSessionValue("role");
        if (role == null)
            role = "admin";
        pageContext.putSessionValue("role", role);

        //OAImageBean adjustBean = 
        //    (OAImageBean)webBean.findChildRecursive("Adjust");
        OAImageBean closeBean = 
            (OAImageBean)webBean.findChildRecursive("Close");
        //OAImageBean submitBean = 
        //   (OAImageBean)webBean.findChildRecursive("Submit");
        OAImageBean deleteBean = 
            (OAImageBean)webBean.findChildRecursive("Delete");

        //���������7ֵ���ʾ���
        if (role != null && role.equals("manager")) {
            //adjustBean.setRendered(false);
            closeBean.setRendered(false);
            //submitBean.setRendered(true);
            deleteBean.setRendered(false);
        } else /* if (role != null && role.equals("admin")) */ {
            //adjustBean.setRendered(true);
            closeBean.setRendered(true);
            //submitBean.setRendered(true);
            deleteBean.setRendered(true);
        }
        /*20090926 zs*/
        OAMessageChoiceBean msSearchGroupName = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("SearchGroupName");
        msSearchGroupName.setPickListCacheEnabled(false);
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
        WorkflowControlAMImpl am = 
            (WorkflowControlAMImpl)pageContext.getApplicationModule(webBean);
        String appraisalId = pageContext.getParameter("appraisalId");

        if (pageContext.getParameter("Apply") != null) {
            am.invokeMethod("commit");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_MAIN_ADJUST_WF_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlSearchPG&refreshflag=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // ��̵���
        if ("adjust".equals(pageContext.getParameter("event"))) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlAdjustPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // ��̹ر�
        if ("close".equals(pageContext.getParameter("event"))) {
            /*
             Serializable params[] = {appraisalId, "close"};
             String result = (String)am.invokeMethod("processWfApproval", params);
             if(result != null && result.equals("Y")){
               OAException confirmMessage = new OAException("CUX", "CUX_WF_CLOSE_CONF", null, OAException.CONFIRMATION, null);
               pageContext.putDialogMessage(confirmMessage);
             }else{
               OAException confirmMessage = new OAException("CUX", "CUX_WF_CLOSE_ERR", null, OAException.ERROR, null);
               pageContext.putDialogMessage(confirmMessage);
             }
             pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlSearchPG&refreshFlag=Y"
                                         , null, (byte)0, null, null, true, "N");
              */ // remark by fengling
            OAException mainMessage = 
                new OAException("CUX", "CUX_WF_ClOSE_ASK_CONF", null, 
                                OAException.WARNING, null);
            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("CloseYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(1);
            formParams.put("appraisalId", appraisalId);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("CloseYesButton") != null) {
            Serializable params[] = { appraisalId, "close" };
            String result = 
                (String)am.invokeMethod("processWfApproval", params);
            if (result != null && result.equals("Y")) {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_WF_CLOSE_CONF", null, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);
            } else {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_WF_CLOSE_ERR", null, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlSearchPG&refreshflag=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }


        // ����ύ����һ��
        if ("submit".equals(pageContext.getParameter("event"))) {
            //  Serializable params[] = {appraisalId, "submit"};
            //   String result = (String)am.invokeMethod("processWfApproval", params);
            //    if(result != null && result.equals("Y")){

            // update by fengling 
            Serializable[] parameters = { appraisalId };
            am.invokeMethod("validateScore", parameters);
            OAException mainMessage = 
                new OAException("CUX", "CUX_WF_SUBMIT_ASK_CONF", null, 
                                OAException.WARNING, null);
            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("SubmitYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(1);
            formParams.put("appraisalId", appraisalId);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);

            // OAException confirmMessage = new OAException("CUX", "CUX_WF_JUMP_CONF", null, OAException.CONFIRMATION, null);
            // pageContext.putDialogMessage(confirmMessage);
            //   }else{
            //     OAException confirmMessage = new OAException("CUX", "CUX_WF_JUMP_ERR", null, OAException.ERROR, null);
            //     pageContext.putDialogMessage(confirmMessage);
            //   }
            //   pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlSearchPG"
            //                             , null, (byte)0, null, null, true, "N");
        } else if (pageContext.getParameter("SubmitYesButton") != null) {
            Serializable params[] = { appraisalId, "submit" };
            String result = 
                (String)am.invokeMethod("processWfApproval", params);
            if (result != null && result.equals("Y")) {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_WF_JUMP_CONF", null, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);
            } else {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_WF_JUMP_ERR", null, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlSearchPG&refreshflag=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");

        }

        // ���ɾ��
        if ("delete".equals(pageContext.getParameter("event"))) {
            Serializable params[] = { appraisalId };
            String delFlag = (String)am.invokeMethod("canAddApprover", params);
            if (delFlag != null && !delFlag.equals("COMPLETED")) {
                throw new OAException("CUX", "CUX_WF_DEL_WARNING", null, 
                                      OAException.WARNING, null);
            } else {
                OAException mainMessage = 
                    new OAException("CUX", "CUX_WF_DEL_ASK_CONF", null, 
                                    OAException.WARNING, null);
                OADialogPage dialogPage = 
                    new OADialogPage(OAException.WARNING, mainMessage, null, 
                                     "", "");

                dialogPage.setOkButtonItemName("DeleteYesButton");
                dialogPage.setOkButtonToPost(true);
                dialogPage.setNoButtonToPost(true);
                dialogPage.setPostToCallingPage(true);
                java.util.Hashtable formParams = new java.util.Hashtable(1);
                formParams.put("appraisalId", appraisalId);
                dialogPage.setFormParameters(formParams);
                pageContext.redirectToDialogPage(dialogPage);
            }

        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            Serializable params[] = { appraisalId, "delete" };
            String result = 
                (String)am.invokeMethod("processWfApproval", params);
            if (result != null && result.equals("Y")) {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_WF_DEL_CONF", null, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);
            } else {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_WF_DEL_ERR", null, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }

            //pageContext.setForwardURLToCurrentPage(hm,true,"N",OAWebBeanConstants.IGNORE_MESSAGES);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlSearchPG&refreshflag=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }
        //add by fl handle manual search.20091116
        if (pageContext.getParameter("Search") != null) {
            OAQueryUtils.checkSelectiveSearchCriteria(pageContext, webBean);

            // Get the user's search criteria from the request.
            // String role = pageContext.getParameter("role");
            // if(role == null)
            //   role = (String)pageContext.getSessionValue("role");
            String groupName = pageContext.getParameter("SearchGroupName");
            String orgName = pageContext.getParameter("SearchOrgName");
            String phase = pageContext.getParameter("SearchPhase");
            String period = pageContext.getParameter("SearchPeriod");
            String year = pageContext.getParameter("SearchYear");
            String endYear = pageContext.getParameter("SearchEndYear");
            String status = pageContext.getParameter("SearchStatus");
            String empName = pageContext.getParameter("SearchEmpName");

            System.out.println("phase:" + phase);
            System.out.println("yearFrom:" + year);

            // OAApplicationModule am = pageContext.getApplicationModule(webBean);
            Boolean executeQuery = BooleanUtils.getBoolean(false);
            Serializable[] parameters = 
            { groupName, orgName, phase, period, year, endYear, status, 
              empName, executeQuery };

            Class[] paramTypes = 
            { String.class, String.class, String.class, String.class, 
              String.class, String.class, String.class, String.class, 
              Boolean.class };
            am.invokeMethod("initQuery", parameters, paramTypes);

            OATableBean table = 
                (OATableBean)webBean.findChildRecursive("WorkflowControlSearchVO1");
            table.queryData(pageContext, false);
        }

    }

}
