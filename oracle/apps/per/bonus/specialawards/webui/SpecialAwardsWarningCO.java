/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.specialawards.webui;

import cux.oracle.apps.per.bonus.specialawards.server.SpecialAwardsAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

/**
 * Controller for ...
 */
public class SpecialAwardsWarningCO extends OAControllerImpl {
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
        String flag = pageContext.getSessionValue("saDelObj").toString();
        String deleteId = "";
        String deleteName = "";
        if ("delDept".equals(flag)) {
            deleteId = pageContext.getParameter("deptDistId");
            deleteName = pageContext.getParameter("deleteDeptName");
        }
        if ("delPerson".equals(flag)) {
            deleteId = pageContext.getParameter("personDistId");
            deleteName = pageContext.getParameter("deletePersonName");
        }
        String message = "提示信息: 是否确定删除 " + deleteName + " 该条记录";
        OAMessageTextInputBean warningBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("Warning");
        warningBean.setValue(pageContext, message);
        if (deleteId != null && deleteName != null) {
            pageContext.putSessionValue("saDeleteId", deleteId);
            pageContext.putSessionValue("saDeleteName", deleteName);
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
        if (pageContext.getParameter("YesButton") != null) {
            SpecialAwardsAMImpl amImpl = 
                (SpecialAwardsAMImpl)pageContext.getApplicationModule(webBean);
            String deleteId = "";
            String deleteName = "";
            if (pageContext.getSessionValue("saDeleteId") != null && 
                pageContext.getSessionValue("saDeleteName") != null) {
                deleteId = 
                        pageContext.getSessionValue("saDeleteId").toString();
                deleteName = 
                        pageContext.getSessionValue("saDeleteName").toString();
            }
            String flag = pageContext.getSessionValue("saDelObj").toString();
            System.out.println("当前删除:" + deleteId + "@" + deleteName + "@" + 
                               flag);
            if ("delDept".equals(flag)) {
                amImpl.DeleteDeptLine(deleteId, deleteName);
            }
            if ("delPerson".equals(flag)) {
                amImpl.DeletePersonLine(deleteId, deleteName);
            }
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/specialawards/webui/SpecialAwardsPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if (pageContext.getParameter("NoButton") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/specialawards/webui/SpecialAwardsPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
    }

}
