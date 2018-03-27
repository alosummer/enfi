/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.advanceawards.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

/**
 * Controller for ...
 */
public class AdvAwardsWarningCO extends OAControllerImpl {
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
        String deletePerName = pageContext.getParameter("otherPerDeleteName");
        String deletePerId = pageContext.getParameter("otherPerDeleteId");
        String deleteEmployeeNumber = 
            pageContext.getParameter("otherPerDeleteEmplyeeNum");

        String message = "提示信息: 是否确定删除 " + deletePerName + " 该条记录";
        OAMessageTextInputBean warningBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("Warning");
        warningBean.setValue(pageContext, message);
        if (deletePerId != null && deletePerName != null) {
            pageContext.putSessionValue("aaDeletePerId", deletePerId);
            pageContext.putSessionValue("aaDeleteEmpNum", 
                                        deleteEmployeeNumber);
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
            AdvanceAwardsAMImpl amImpl = 
                (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
            String deletePersonId = "";
            String deleteEmpNum = "";
            if (pageContext.getSessionValue("aaDeletePerId") != null && 
                pageContext.getSessionValue("aaDeleteEmpNum") != null) {
                deletePersonId = 
                        pageContext.getSessionValue("aaDeletePerId").toString();
                deleteEmpNum = 
                        pageContext.getSessionValue("aaDeleteEmpNum").toString();
            }
            amImpl.deleteOtherPerData(deletePersonId, deleteEmpNum);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/AdvanceAwardsPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if (pageContext.getParameter("NoButton") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/AdvanceAwardsPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
    }

}
