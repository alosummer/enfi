/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.quality.webui;

import com.sun.java.util.collections.HashMap;

import cux.oracle.apps.cux.qhse.quality.server.CuxQualityAMImpl;

import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class CuxQualityPlanControlDetailViewPGCO extends OAControllerImpl {
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
        pgAM.initViewPG(taskIdNum, projectIdNum);

        String fromPG = pageContext.getParameter("fromPG");
        if ("StructEditTasksPG".equals(fromPG)) {
            webBean.findChildRecursive("CancelBTN").setRendered(false);
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
        CuxQualityAMImpl pgAM = 
            (CuxQualityAMImpl)pageContext.getRootApplicationModule();

        if (pageContext.getParameter("CancelBTN") != null) {
            HashMap params = new HashMap(1);
            params.put("ProjectId", pgAM.getViewProjectId());
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/quality/webui/CuxQualityWorkplanDetailPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, params, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
    }

}
