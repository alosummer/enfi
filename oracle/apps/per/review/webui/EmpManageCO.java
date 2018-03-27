/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

/**
 * Controller for ...
 */
public class EmpManageCO extends OAControllerImpl {
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
        // The following checks to see if the user navigated back to this page  
        // without taking an action that cleared an "in transaction" indicator.  
        // If so, we want to rollback any changes that she abandoned to ensure they  
        // aren't left lingering in the BC4J cache to cause problems with   
        // subsequent transactions.  For example, if the user navigates to the   
        // Create Employee page where you start a "create" transaction unit,   
        // then navigates back to this page using the browser Back button and  
        // selects the Create Employee button again, the OA Framework detects this  
        // Back button navigation and steps through processRequest() so this code  
        // is executed before you try to create another new employee.

        if (TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                              "UpdateTxn", 
                                                              false)) {
            am.invokeMethod("rollbackUpdateEmp");
            TransactionUnitHelper.endTransactionUnit(pageContext, "UpdateTxn");
        }
        if (pageContext.getParameter("flag") != null)
            am.invokeMethod("initQuery");
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
        if (pageContext.getParameter("Create") != null) {
            // Navigate to the "Create Employee" page while retaining the AM.
            // Note the use of KEEP_MENU_CONTEXT as opposed to GUESS_MENU_CONTEXT
            // since we know the current tab should remain highlighted.
            // Retain AM
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/EmpUpdatePG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        } else if ("update".equals(pageContext.getParameter(EVENT_PARAM))) {
            // The user has clicked an "Update" icon so we want to navigate
            // to the first step of the multistep "Update Employee" flow.
            // Retain AM
            // Do not display breadcrumbs
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/EmpUpdatePG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }
    }

}
