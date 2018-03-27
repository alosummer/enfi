/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.docnum.webui;

import com.sun.java.util.collections.HashMap;

import cux.oracle.apps.pa.util.ControllerUtil;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class DocNumDetailCO extends ControllerUtil {
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
        String reqBatchId = pageContext.getParameter("ReqBatchId");
        String security = pageContext.getParameter("Security");
        Serializable paras[] = { reqBatchId };
        am.invokeMethod("initDetail", paras);
        String sql = 
            "SELECT pdr.status\n" + "FROM   cux_pa_docnum_req_t pdr\n" + 
            "WHERE  pdr.docnum_req_id = " + reqBatchId;
        String status = (String)this.getSqlValue(am, sql);
        sql = 
"SELECT COUNT(0)\n" + "FROM   cux_pa_docnum_apprv_action_t pdaa\n" + 
  "WHERE  pdaa.approver_id = fnd_global.employee_id\n" + 
  "AND    pdaa.docnum_req_id = " + reqBatchId;
        Number isApprover = (Number)this.getSqlValue(am, sql);
        if ("ViewOnly".equals(security)) {
            OAWebBean approveBean = webBean.findChildRecursive("approve");
            approveBean.setAttributeValue(this.RENDERED_ATTR, false);
            OAWebBean rejectBean = webBean.findChildRecursive("reject");
            rejectBean.setAttributeValue(this.RENDERED_ATTR, false);
            OAWebBean docnumBean = webBean.findChildRecursive("DocNum");
            docnumBean.setAttributeValue(this.READ_ONLY_ATTR, true);
            OAWebBean lineDescBean = webBean.findChildRecursive("LineDesc");
            lineDescBean.setAttributeValue(this.READ_ONLY_ATTR, true);
            if ("APPROVED".equals(status) || "REJECTED".equals(status)) {
                OAWebBean revertBean = webBean.findChildRecursive("revert");
                revertBean.setAttributeValue(this.RENDERED_ATTR, false);
            }
        }
        if ("Approver".equals(security)) {
            if (isApprover.intValue() > 0) {
                if (!"APPROVED".equals(status)) {
                    if ("REJECTED".equals(status)) {
                        OAWebBean revertBean = 
                            webBean.findChildRecursive("revert");
                        revertBean.setAttributeValue(this.RENDERED_ATTR, 
                                                     false);
                        OAWebBean approveBean = 
                            webBean.findChildRecursive("approve");
                        approveBean.setAttributeValue(this.RENDERED_ATTR, 
                                                      false);
                        OAWebBean rejectBean = 
                            webBean.findChildRecursive("reject");
                        rejectBean.setAttributeValue(this.RENDERED_ATTR, 
                                                     false);
                        OAWebBean docnumBean = 
                            webBean.findChildRecursive("DocNum");
                        docnumBean.setAttributeValue(this.READ_ONLY_ATTR, 
                                                     true);
                        OAWebBean lineDescBean = 
                            webBean.findChildRecursive("LineDesc");
                        lineDescBean.setAttributeValue(this.READ_ONLY_ATTR, 
                                                       true);
                    } else {
                        sql = 
"SELECT COUNT(0)\n" + "FROM   cux_pa_docnum_apprv_action_t pdaa\n" + 
  "WHERE  pdaa.docnum_req_id =" + reqBatchId + "\n" + 
  "AND    pdaa.approver_id = fnd_global.employee_id\n" + 
  "AND    pdaa.action = 'APPROVED'";
                        Number isCurrentApproved = 
                            (Number)this.getSqlValue(am, sql);
                        if (isCurrentApproved.intValue() == 0) {
                            OAWebBean revertBean = 
                                webBean.findChildRecursive("revert");
                            revertBean.setAttributeValue(this.RENDERED_ATTR, 
                                                         false);
                            OAWebBean docnumBean = 
                                webBean.findChildRecursive("DocNum");
                            docnumBean.setAttributeValue(this.READ_ONLY_ATTR, 
                                                         new OADataBoundValueViewObject(docnumBean, 
                                                                                        "ReadonlyFlag", 
                                                                                        "DocnumDetailLineVO1"));
                            //                OAWebBean lineDescBean = webBean.findChildRecursive("LineDesc");
                            //                lineDescBean.setAttributeValue(this.READ_ONLY_ATTR, 
                            //                                               new OADataBoundValueViewObject(lineDescBean, 
                            //                                                                              "ReadonlyFlag", 
                            //                                                                              "DocnumDetailLineVO1"));
                        } else {
                            OAWebBean approveBean = 
                                webBean.findChildRecursive("approve");
                            approveBean.setAttributeValue(this.RENDERED_ATTR, 
                                                          false);
                            OAWebBean rejectBean = 
                                webBean.findChildRecursive("reject");
                            rejectBean.setAttributeValue(this.RENDERED_ATTR, 
                                                         false);
                            OAWebBean docnumBean = 
                                webBean.findChildRecursive("DocNum");
                            docnumBean.setAttributeValue(this.READ_ONLY_ATTR, 
                                                         true);
                            OAWebBean lineDescBean = 
                                webBean.findChildRecursive("LineDesc");
                            lineDescBean.setAttributeValue(this.READ_ONLY_ATTR, 
                                                           true);
                            OAWebBean revertBean = 
                                webBean.findChildRecursive("revert");
                            revertBean.setAttributeValue(this.RENDERED_ATTR, 
                                                         false);
                        }
                    }
                } else {
                    OAWebBean revertBean = 
                        webBean.findChildRecursive("revert");
                    revertBean.setAttributeValue(this.RENDERED_ATTR, false);
                    OAWebBean approveBean = 
                        webBean.findChildRecursive("approve");
                    approveBean.setAttributeValue(this.RENDERED_ATTR, false);
                    OAWebBean rejectBean = 
                        webBean.findChildRecursive("reject");
                    rejectBean.setAttributeValue(this.RENDERED_ATTR, false);
                    OAWebBean docnumBean = 
                        webBean.findChildRecursive("DocNum");
                    docnumBean.setAttributeValue(this.READ_ONLY_ATTR, true);
                    OAWebBean lineDescBean = 
                        webBean.findChildRecursive("LineDesc");
                    lineDescBean.setAttributeValue(this.READ_ONLY_ATTR, true);
                }
                //tag
            } else {
                OAWebBean approveBean = webBean.findChildRecursive("approve");
                approveBean.setAttributeValue(this.RENDERED_ATTR, false);
                OAWebBean rejectBean = webBean.findChildRecursive("reject");
                rejectBean.setAttributeValue(this.RENDERED_ATTR, false);
                OAWebBean docnumBean = webBean.findChildRecursive("DocNum");
                docnumBean.setAttributeValue(this.READ_ONLY_ATTR, true);
                OAWebBean lineDescBean = 
                    webBean.findChildRecursive("LineDesc");
                lineDescBean.setAttributeValue(this.READ_ONLY_ATTR, true);
                OAWebBean revertBean = webBean.findChildRecursive("revert");
                revertBean.setAttributeValue(this.RENDERED_ATTR, false);
            }
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
        if ("cancel".equals(event)) { // retain AM
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/docnum/webui/DocNumSearchPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if ("revert".equals(event)) {
            String reqBatchId = pageContext.getParameter("ReqBatchId");

            OAException mainMessage = 
                new OAException("你将要撤销批次为:" + reqBatchId + 
                                "的文档要号申请，此操作不可逆，请确认是否继续", OAException.WARNING);
            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");

            String yes = pageContext.getMessage("AK", "FWK_TBX_T_YES", null);
            String no = pageContext.getMessage("AK", "FWK_TBX_T_NO", null);

            dialogPage.setOkButtonItemName("DeleteYesButton");

            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);

            dialogPage.setOkButtonLabel(yes);
            dialogPage.setNoButtonLabel(no);

            java.util.Hashtable formParams = new java.util.Hashtable(1);
            formParams.put("ReqBatchId", reqBatchId);
            dialogPage.setFormParameters(formParams);

            pageContext.redirectToDialogPage(dialogPage);
        }
        if (pageContext.getParameter("DeleteYesButton") != null) {
            String reqBatchId = pageContext.getParameter("ReqBatchId");
            Serializable[] parameters = { reqBatchId };
            am.invokeMethod("revert", parameters);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/docnum/webui/DocNumSearchPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if ("approve".equals(event)) {
            String reqBatchId = pageContext.getParameter("ReqBatchId");
            Serializable[] parameters = { reqBatchId };
            am.invokeMethod("approve", parameters);
            String security = pageContext.getParameter("Security");
            HashMap params = new HashMap();
            params.put("ReqBatchId", reqBatchId);
            params.put("Security", security); // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(params, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
        if ("reject".equals(event)) {
            String reqBatchId = pageContext.getParameter("ReqBatchId");
            Serializable[] parameters = { reqBatchId };
            am.invokeMethod("reject", parameters);
            String security = pageContext.getParameter("Security");
            HashMap params = new HashMap();
            params.put("ReqBatchId", reqBatchId);
            params.put("Security", security); // no parameters to pass
            // retain the AM
            pageContext.setForwardURLToCurrentPage(params, true, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
        }
    }

}
