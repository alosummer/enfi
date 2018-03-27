/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.WorkflowControlAMImpl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class WorkflowControlAdjustCO extends OAControllerImpl {
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
        String appraisalId = pageContext.getParameter("appraisalId");
        if (appraisalId == null)
            appraisalId = (String)pageContext.getSessionValue("appraisalId");
        else
            pageContext.putSessionValue("appraisalId", appraisalId);
        String phaseId = pageContext.getParameter("phaseId");
        if (phaseId == null)
            phaseId = (String)pageContext.getSessionValue("phaseId");
        else
            pageContext.putSessionValue("phaseId", phaseId);
        String canAddApprover = "";
        if (appraisalId != null) {
            if (pageContext.getParameter("phaseId") != null) {
                Serializable params[] = { appraisalId, phaseId };
                am.invokeMethod("queryAttendList", params);
            }
            Serializable parameters[] = { appraisalId };
            canAddApprover = 
                    (String)am.invokeMethod("canAddApprover", parameters);
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("WorkflowAttendVO1");

            OAMessageLovInputBean lov = 
                (OAMessageLovInputBean)webBean.findChildRecursive("EmpName");
            lov.setAttributeValue(READ_ONLY_ATTR, 
                                  new OADataBoundValueViewObject(lov, 
                                                                 "ReadOnlyFlag", 
                                                                 "WorkflowAttendVO1"));

            while (vo.hasNext()) {
                Row row = vo.next();
                if (canAddApprover != null && 
                    (canAddApprover.equals("PUBLISHED") || 
                     canAddApprover.equals("FREEZED") || 
                     canAddApprover.equals("COMPLETED") || 
                     canAddApprover.equals("CLOSED"))) {
                    row.setAttribute("ReadOnlyFlag", Boolean.TRUE);
                } else if (canAddApprover != null && 
                           (canAddApprover.equals("SUBMITED") || 
                            canAddApprover.equals("WAITING") || 
                            canAddApprover.equals("PROCESSING"))) {
                    if (row.getAttribute("UpdateImage") != null && 
                        row.getAttribute("UpdateImage").equals("UpdateDisabled")) {
                        row.setAttribute("ReadOnlyFlag", Boolean.TRUE);
                    } else {
                        row.setAttribute("ReadOnlyFlag", Boolean.FALSE);
                    }
                } else {
                    row.setAttribute("ReadOnlyFlag", Boolean.FALSE);
                }
            }

        }
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("WorkflowAttendVO1");
        tableBean.prepareForRendering(pageContext);
        OASubmitButtonBean btnApply = 
            (OASubmitButtonBean)webBean.findChildRecursive("Apply");
        if (canAddApprover != null && 
            (canAddApprover.equals("PROCESSING") || canAddApprover.equals("SUBMITED"))) {
            tableBean.setInsertable(true);
            btnApply.setRendered(true);
        } else {
            tableBean.setInsertable(false);
            btnApply.setRendered(false);
        }

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
        WorkflowControlAMImpl am = 
            (WorkflowControlAMImpl)pageContext.getApplicationModule(webBean);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("WorkflowAttendVO1");
        String appraisalId = 
            (String)pageContext.getSessionValue("appraisalId");
        String phaseId = (String)pageContext.getSessionValue("phaseId");
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("WorkflowAttendVO1");
            if (!vo.isPreparedForExecution()) {
                vo.executeQuery();
            }
            vo.last();
            Row lastRow = vo.last();
            Number appSeq = null;
            int newAppSeq = 0;
            if (lastRow != null && lastRow.getAttribute("AppSeq") != null) {
                appSeq = (Number)lastRow.getAttribute("AppSeq");
                if (appSeq != null)
                    newAppSeq = appSeq.intValue() + 1;
            }
            Row row = vo.createRow();
            vo.insertRowAtRangeIndex(vo.getRangeSize(), 
                                     row); //vo.getRowCount() ����������ֵ�bug�����Ƴ���һҳ�����ӵ�ʱ�򱨴?
            row.setNewRowState(Row.STATUS_INITIALIZED);
            row.setAttribute("AppraisalId", appraisalId);
            row.setAttribute("Attribute3", phaseId);
            row.setAttribute("AppSeq", newAppSeq);
            Number attendId = 
                am.getOADBTransaction().getSequenceValue("CUX_APPRAISAL_ATTEND_s");
            row.setAttribute("AttendId", attendId);
            row.setAttribute("PersonType", 1); // ������
            row.setAttribute("Enableed", 0); // δ����
            String desc = "�<���";
            try {
                desc = new String(desc.getBytes("ISO-8859-1"), "GB2312");
            } catch (UnsupportedEncodingException e) {
                // TODO
                ;
            }
            row.setAttribute("Attribute2", desc);
            row.setAttribute("Attribute4", "N");
            vo.setCurrentRow(row);
        }

        if ("delete".equals(pageContext.getParameter("event"))) {
            String attendId = pageContext.getParameter("attendId");
            String appId = pageContext.getParameter("appraisalId");
            int restPerformers = 0;
            try {
                restPerformers = 
                        Integer.parseInt((String)am.getRestPerformers(appId));
            } catch (Exception e) {
                ;
            }

            OAException mainMessage;
            if (restPerformers == 1) { // last approver
                mainMessage = 
                        new OAException("CUX", "CUX_DEL_LAST_APPROVER", null, 
                                        OAException.WARNING, null);
            } else if (restPerformers > 1) {
                mainMessage = 
                        new OAException("CUX", "CUX_DEL_APPROVER_WARN", null, 
                                        OAException.WARNING, null);
            } else {
                return;
            }

            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("DeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(2);
            formParams.put("appraisalId", appId);
            formParams.put("attendId", attendId);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String appId = pageContext.getParameter("appraisalId");
            String attendId = pageContext.getParameter("attendId");
            Serializable params[] = { appId, attendId };
            String result = (String)am.invokeMethod("deleteWfStep", params);
            if (result != null && result.equals("Y")) {
                //
                OAViewObject vo = 
                    (OAViewObject)am.findViewObject("WorkflowAttendVO1"); //add by dl 20091123
                vo.executeQuery();
                //
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_WF_DEL_APPROVER_CONF", null, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);
            } else {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_WF_DEL_APPROVER_ERR", null, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }

            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlAdjustPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlSearchPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            String result = (String)am.invokeMethod("commit");

            // 2014.9.2 add by yang.gang, begin, 重试 流程
            // 和在工作流管理员 Web（新） 页面，选择具体的流程，点击“重试”按钮 效果相同
            if (result == "Y") //用户进行了修改
            {
                Serializable params[] = { appraisalId };
                result = (String)am.invokeMethod("RetryWorkflow", params);
            }
            // 2014.9.2 add by yang.gang, end

            OAException confirmMessage = 
                new OAException("CUX", "CUX_ADJUST_WF_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/WorkflowControlSearchPG&refreshflag=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");
            //try{
            //    pageContext.sendRedirect("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG");
            //}catch(Exception e){;}
        }

    }

}
