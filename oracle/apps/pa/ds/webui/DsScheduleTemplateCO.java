/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.ds.webui;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class DsScheduleTemplateCO extends OAControllerImpl {
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
        String projectId = pageContext.getParameter("projectId");
        String taskId = pageContext.getParameter("taskId");
        am.getOADBTransaction().putValue("ProjectId", projectId);
        am.getOADBTransaction().putValue("TaskId", taskId);
        //Serializable[] paras = { projectId, taskId };
        //am.invokeMethod("initTemplateVO", paras);
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
        String event = pageContext.getParameter(this.EVENT_PARAM);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OAQueryBean queryBean = 
            (OAQueryBean)webBean.findChildRecursive("region2");
        String currentPanel = queryBean.getCurrentSearchPanel();
        if ("Select".equals(event)) {
            am.invokeMethod("doCopyFromTemplate");
            HashMap parameters = new HashMap();
            parameters.put("projectId", pageContext.getParameter("projectId"));
            parameters.put("taskId", 
                           pageContext.getParameter("taskId")); // retain AM
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/ds/webui/DlvSchedulePG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, parameters, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if ("Back".equals(event)) {
            HashMap parameters = new HashMap();
            parameters.put("projectId", pageContext.getParameter("projectId"));
            parameters.put("taskId", 
                           pageContext.getParameter("taskId")); // retain AM
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/ds/webui/DlvSchedulePG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, parameters, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if (SEARCH.equals(currentPanel) && 
            pageContext.getParameter(queryBean.getGoButtonName()) != null) {
            String projectId = 
                (String)am.getOADBTransaction().getValue("ProjectId");
            Number ProjectId = null;
            try {
                ProjectId = new Number(projectId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String taskId = (String)am.getOADBTransaction().getValue("TaskId");
            Number TaskId = null;
            try {
                TaskId = new Number(taskId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ViewObject templateVO = am.findViewObject("DsTemplateVO1");
            templateVO.setWhereClauseParam(0, ProjectId);
            templateVO.setWhereClauseParam(1, TaskId);
        }
    }

}
