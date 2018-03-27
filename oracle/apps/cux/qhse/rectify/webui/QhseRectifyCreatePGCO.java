/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.rectify.webui;

import com.sun.java.util.collections.HashMap;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyAMImpl;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyHeadersVOImpl;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyHeadersVORowImpl;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyLinesVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class QhseRectifyCreatePGCO extends OAControllerImpl {
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
        CuxRectifyAMImpl pgAM = 
            (CuxRectifyAMImpl)pageContext.getRootApplicationModule();
        String submitHeaderId = pageContext.getParameter("SubmitHeaderId");
        if (submitHeaderId == null || "".equals(submitHeaderId)) {
            pgAM.initCreatePG(pageContext.getOrgId());
        } else {
            pgAM.initCreatePG(pageContext.getOrgId(), submitHeaderId);
        }
        CuxRectifyNotifyHeadersVOImpl headerVO = 
            pgAM.getCuxRectifyNotifyHeadersVO1();
        CuxRectifyNotifyHeadersVORowImpl headerRow = 
            (CuxRectifyNotifyHeadersVORowImpl)headerVO.getCurrentRow();
        OAMessageLovInputBean lovInput = 
            (OAMessageLovInputBean)webBean.findChildRecursive("DutyPersonName");

        lovInput.removeLovRelations(pageContext, "ManDeptId", "OrganizationId", 
                                    LOV_CRITERIA);
        if ("PROJECT".equals(headerRow.getDocType())) {
            headerRow.setProjectReadonlyFlag(Boolean.FALSE);
            headerRow.setProjectRequiredFlag("yes");

        } else {
            headerRow.setProjectReadonlyFlag(Boolean.TRUE);
            headerRow.setProjectRequiredFlag("no");

            lovInput.addLovRelations(pageContext, "ManDeptId", 
                                     "OrganizationId", LOV_CRITERIA, 
                                     LOV_REQUIRED_YES);
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
        CuxRectifyAMImpl pgAM = 
            (CuxRectifyAMImpl)pageContext.getRootApplicationModule();


        String eventParam = pageContext.getParameter(this.EVENT_PARAM);

        String souceParam = pageContext.getParameter("source");
        if (souceParam == null) {
            souceParam = "THIS#IS#NULL#VALUE";
        }

        if ("CreateLineRow".equals(eventParam)) {
            pgAM.createLineRow();
        } else if ("DeleteCurrentLineRow".equals(eventParam)) {
            Row r = 
                pgAM.findRowByRef(pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE));
            r.remove();
        } else if (pageContext.getParameter("SaveBTN") != null) {
            pgAM.commit();
            pageContext.putDialogMessage(new OAException("保存成功", 
                                                         OAException.INFORMATION));
            HashMap params = new HashMap(1);
            params.put("RequeryFlag", "Y");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/rectify/webui/QhseRectifySearchPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, params, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("CancelBTN") != null) {
            pgAM.rollback();
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/rectify/webui/QhseRectifySearchPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("SubmitBTN") != null) {
            CuxRectifyNotifyLinesVOImpl lineVo = 
                pgAM.getCuxRectifyNotifyLinesVO1();
            if (lineVo.getRowCount() == 0) {
                throw new OAException("没有创建问题描述行，不允许提交审批");
            }

            CuxRectifyNotifyHeadersVOImpl headerVO = 
                pgAM.getCuxRectifyNotifyHeadersVO1();
            CuxRectifyNotifyHeadersVORowImpl headerRow = 
                (CuxRectifyNotifyHeadersVORowImpl)headerVO.getCurrentRow();
            pgAM.startWorkFlow(headerRow.getHeaderId());
            pgAM.commit();
            pageContext.putDialogMessage(new OAException("提交审批成功", 
                                                         OAException.INFORMATION));
            HashMap params = new HashMap(1);
            params.put("RequeryFlag", "Y");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/rectify/webui/QhseRectifySearchPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, params, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if ("ChangeDocType".equals(eventParam)) {
            CuxRectifyNotifyHeadersVOImpl headerVO = 
                pgAM.getCuxRectifyNotifyHeadersVO1();
            CuxRectifyNotifyHeadersVORowImpl headerRow = 
                (CuxRectifyNotifyHeadersVORowImpl)headerVO.getCurrentRow();
            System.out.println(headerRow.getDocType());

            OAMessageLovInputBean lovInput = 
                (OAMessageLovInputBean)webBean.findChildRecursive("DutyPersonName");
            if ("PROJECT".equals(headerRow.getDocType())) {
                headerRow.setProjectReadonlyFlag(Boolean.FALSE);
                headerRow.setProjectRequiredFlag("yes");
                headerRow.setProjectId(null);

                headerRow.setDutyPersonId(null);
                headerRow.setDutyPersonName(null);
                lovInput.removeLovRelations(pageContext, "ManDeptId", 
                                            "OrganizationId", LOV_CRITERIA);
            } else {
                headerRow.setProjectReadonlyFlag(Boolean.TRUE);
                headerRow.setProjectRequiredFlag("no");
                headerRow.setProjectId(new Number(-1));
                headerRow.setProjectName(null);
                headerRow.setProjectNumber(null);
                headerRow.setProjectOrgName(null);
                headerRow.setTaskId(null);
                headerRow.setTaskName(null);
                headerRow.setDutyPersonId(null);
                headerRow.setDutyPersonName(null);

                lovInput.addLovRelations(pageContext, "ManDeptId", 
                                         "OrganizationId", LOV_CRITERIA, 
                                         LOV_REQUIRED_YES);
            }
            lovInput.getLovRelations();
        }
    }

}
