/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class RoleManageCO extends OAControllerImpl {
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
        /*
      OAApplicationModule am = pageContext.getApplicationModule(webBean);
      String groupName = pageContext.getParameter("SearchGroupName");
      String orgId = pageContext.getParameter("SearchOrgName");
      Serializable parameters1[] = {groupName,orgId};
      am.invokeMethod("initQuery", parameters1);
      */
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

        if (pageContext.getParameter("CreateReviewRole") != 
            null) { // Retain AM
            // Show breadcrumbs 
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/CreateReviewRolePG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);

        } else if ("updateReviewRole".equals(pageContext.getParameter("event"))) { // Retain AM
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/CreateReviewRolePG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);

        } else if ("delReviewRole".equals(pageContext.getParameter("event"))) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            String roleId = pageContext.getParameter("roleId");
            Serializable parameters[] = { roleId };
            am.invokeMethod("deleteReviewRole", parameters);
            String groupName = pageContext.getParameter("SearchGroupName");
            String orgId = pageContext.getParameter("SearchOrgName");
            //  String empName = pageContext.getParameter("SearchEmpName");
            Serializable parameters1[] = { groupName, orgId };
            am.invokeMethod("initQuery", parameters1);

        } else if (pageContext.getParameter("customizeSubmitButton") != null) {
            /*
          OAApplicationModule am = pageContext.getApplicationModule(webBean);
          String groupName = pageContext.getParameter("SearchGroupName");
          String orgId = pageContext.getParameter("SearchOrgName");
          Serializable parameters[] = {groupName,orgId};
          am.invokeMethod("initQuery", parameters);
          */
        }
    }

}
