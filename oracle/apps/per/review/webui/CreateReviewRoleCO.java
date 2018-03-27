/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

/**
 * Controller for ...
 */
public class CreateReviewRoleCO extends OAControllerImpl {
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

        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateReviewRoleTxn");
            if (!pageContext.isFormSubmission()) {
                OAApplicationModule am = 
                    pageContext.getApplicationModule(webBean);
                String roleId = pageContext.getParameter("roleId");
                if (roleId != null && roleId != "") {
                    Serializable parameters[] = { roleId };
                    am.invokeMethod("initReviewRoleforUpdate", parameters);
                } else {
                    // am.invokeMethod("init");
                    am.invokeMethod("createReviewRole", null);
                }
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateReviewRoleTxn", 
                                                                      true)) {
            OADialogPage dp = new OADialogPage(NAVIGATION_ERROR);
            pageContext.redirectToDialogPage(dp);
        }

        OAMessageChoiceBean msGroupId = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("GroupId");
        msGroupId.setPickListCacheEnabled(false);
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
        OAViewObject vo = 
            (OAViewObject)am.findViewObject("RoleManageCreateVO1");
        OADBTransaction trx = am.getOADBTransaction();

        //Process apply action
        if (pageContext.getParameter("Apply") != null) {
            //String orgName = pageContext.getParameter("OrgName");
            //String groupName = pageContext.getParameter("GroupName");
            String roleCode = pageContext.getParameter("RoleCode");
            am.getOADBTransaction().commit();
            MessageToken tokens[] = { new MessageToken("ROLECODE", roleCode) };
            OAException confirmMessage = 
                new OAException("CUX", "CUX_PER_REVIEW_CONF", tokens, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            /*   pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/RoleManagePG"
                                        , null, OAWebBeanConstants.KEEP_MENU_CONTEXT, null, null, true, "N");*/
            // String groupName = pageContext.getParameter("SearchGroupName");//û������
            // String orgId = pageContext.getParameter("SearchOrgName");//û������,����Ч������
            Serializable parameters1[] = { null, null };
            am.invokeMethod("initQuery", parameters1);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/RoleManagePG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        //Process cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateReviewRoleTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/RoleManagePG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

    } //end of processFormRequest
}

