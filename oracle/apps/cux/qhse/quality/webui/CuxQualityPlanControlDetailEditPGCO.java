/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.quality.webui;

import cux.oracle.apps.cux.qhse.quality.server.CuxQualityAMImpl;

import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import com.sun.java.util.collections.HashMap;

/**
 * Controller for ...
 */
public class CuxQualityPlanControlDetailEditPGCO extends OAControllerImpl {
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
        CuxQualityAMImpl pgAM = 
            (CuxQualityAMImpl)pageContext.getRootApplicationModule();
        String taskId = pageContext.getParameter("TaskID");
        String projectId = pageContext.getParameter("ProjectId");
        Number taskIdNum = new Number(-1);
        Number projectIdNum = new Number(-1);
        try {
            taskIdNum = new Number(taskId);
            projectIdNum = new Number(projectId);
        } catch (SQLException e) {
            // TODO
        }
        pgAM.initEditPG(taskIdNum, projectIdNum);

        String completeFlag = pageContext.getParameter("CompleteFlag");
        if ("Y".equals(completeFlag)) {
            webBean.findChildRecursive("Result").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                   Boolean.FALSE);
            webBean.findChildRecursive("IsConfirm").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                      Boolean.FALSE);
            webBean.findChildRecursive("Result").setAttributeValue(this.REQUIRED_ATTR, 
                                                                   "yes");
            webBean.findChildRecursive("IsConfirm").setAttributeValue(this.REQUIRED_ATTR, 
                                                                      "yes");
            webBean.findChildRecursive("sortableHeader9").setAttributeValue(this.REQUIRED_ATTR, 
                                                                            "yes");
            webBean.findChildRecursive("sortableHeader10").setAttributeValue(this.REQUIRED_ATTR, 
                                                                             "yes");
            webBean.findChildRecursive("Remark").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                   Boolean.FALSE);

            webBean.findChildRecursive("TargetClassCode").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.TRUE);
            webBean.findChildRecursive("PlanControlName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.TRUE);
            webBean.findChildRecursive("IsDeliverables").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                           Boolean.TRUE);
            webBean.findChildRecursive("TargetValue").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                        Boolean.TRUE);
            webBean.findChildRecursive("PlanDate").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                     Boolean.TRUE);
            //            webBean.findChildRecursive("ApproveDate").setAttributeValue(this.READ_ONLY_ATTR, 
            //                                                                        Boolean.TRUE);
            webBean.findChildRecursive("DutePersonName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                           Boolean.TRUE);
            webBean.findChildRecursive("DutePersonName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                           Boolean.TRUE);

            webBean.findChildRecursive("column12").setAttributeValue(this.RENDERED_ATTR, 
                                                                     Boolean.FALSE);
            webBean.findChildRecursive("AddOneRowBTN").setAttributeValue(this.RENDERED_ATTR, 
                                                                         Boolean.FALSE);
        } else {
            webBean.findChildRecursive("Result").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                   Boolean.TRUE);
            webBean.findChildRecursive("IsConfirm").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                      Boolean.TRUE);
            webBean.findChildRecursive("Remark").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                   Boolean.TRUE);

            webBean.findChildRecursive("TargetClassCode").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.FALSE);
            webBean.findChildRecursive("PlanControlName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.FALSE);
            //            webBean.findChildRecursive("IsDeliverables").setAttributeValue(this.READ_ONLY_ATTR, 
            //                                                                           Boolean.FALSE);
            //            webBean.findChildRecursive("TargetValue").setAttributeValue(this.READ_ONLY_ATTR, 
            //                                                                        Boolean.FALSE);
            webBean.findChildRecursive("PlanDate").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                     Boolean.FALSE);
            //            webBean.findChildRecursive("ApproveDate").setAttributeValue(this.READ_ONLY_ATTR, 
            //                                                                        Boolean.FALSE);
            webBean.findChildRecursive("DutePersonName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                           Boolean.FALSE);
            webBean.findChildRecursive("DutePersonName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                           Boolean.FALSE);

            webBean.findChildRecursive("column12").setAttributeValue(this.RENDERED_ATTR, 
                                                                     Boolean.TRUE);
            webBean.findChildRecursive("AddOneRowBTN").setAttributeValue(this.RENDERED_ATTR, 
                                                                         Boolean.TRUE);
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
        String souceParam = pageContext.getParameter("source");
        if (souceParam == null) {
            souceParam = "THIS#IS#NULL#VALUE";
        }
        String eventParam = pageContext.getParameter(this.EVENT_PARAM);
        CuxQualityAMImpl pgAM = 
            (CuxQualityAMImpl)pageContext.getRootApplicationModule();

        if ("CreateLineRow".equals(eventParam)) {
            pgAM.createLineRow();
        } else if ("DeleteCurrentLineRow".equals(eventParam)) {
            Row r = 
                pgAM.findRowByRef(pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE));
            r.remove();
        } else if (pageContext.getParameter("SaveBTN") != null) {
            pgAM.commit();
            HashMap params = new HashMap(1);
            params.put("ProjectId", pgAM.getEditProjectId());
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/quality/webui/CuxQualityWorkplanDetailPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, params, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("CancelBTN") != null) {
            pgAM.rollback();
            HashMap params = new HashMap(1);
            params.put("ProjectId", pgAM.getEditProjectId());
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/quality/webui/CuxQualityWorkplanDetailPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, params, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
    }

}
