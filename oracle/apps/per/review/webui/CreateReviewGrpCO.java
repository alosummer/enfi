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

import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class CreateReviewGrpCO extends OAControllerImpl {
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
                                                       "CreateReviewGrpTxn");
            if (!pageContext.isFormSubmission()) {
                OAApplicationModule am = 
                    pageContext.getApplicationModule(webBean);
                am.invokeMethod("createReviewGrp", null);
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateReviewGrpTxn", 
                                                                      true)) {
            OADialogPage dp = new OADialogPage(NAVIGATION_ERROR);
            pageContext.redirectToDialogPage(dp);
        }

        OAMessageChoiceBean msOrgID = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("OrgId");
        msOrgID.setPickListCacheEnabled(false);

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

        //Process apply action
        if (pageContext.getParameter("Apply") != null) {
            String orgId = pageContext.getParameter("OrgId");
            String groupName = pageContext.getParameter("GroupName");
            String levelMode = pageContext.getParameter("LevelMode");
            Serializable[] params = { groupName, orgId, null };
            String result = 
                am.invokeMethod("validateReviewGrp", params).toString();

            OAException confirmMessage = null;
            if (result != null && result.equals("N")) {
                am.getOADBTransaction().commit();
                MessageToken tokens[] = 
                { new MessageToken("GROUP_NAME", groupName) };
                confirmMessage = 
                        new OAException("CUX", "CUX_REVIEW_GRP_CREATED_CONF", 
                                        tokens, OAException.CONFIRMATION, 
                                        null);
            } else if (result != null && result.equals("Y")) {
                am.getOADBTransaction().rollback();
                MessageToken tokens[] = 
                { new MessageToken("GROUP_NAME", groupName) };
                confirmMessage = 
                        new OAException("CUX", "CUX_REVIEW_GRP_DUPLICATED_WARN", 
                                        tokens, OAException.WARNING, null);
            } else {
                am.getOADBTransaction().rollback();
                MessageToken tokens[] = 
                { new MessageToken("GROUP_NAME", groupName) };
                confirmMessage = 
                        new OAException("CUX", "CUX_REVIEW_GRP_ERROR", tokens, 
                                        OAException.WARNING, null);
            }

            pageContext.putDialogMessage(confirmMessage);
            if (result != null && result.equals("N"))
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/PerReviewGroupPG&refresh=Y", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
        }

        //Process cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateReviewGrpTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/PerReviewGroupPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

    } //end of processFormRequest


}
