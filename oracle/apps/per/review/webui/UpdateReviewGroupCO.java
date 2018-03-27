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
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import java.io.Serializable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

/**
 * Controller for ...
 */
public class UpdateReviewGroupCO extends OAControllerImpl {
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
                                                       "UpdateReviewGrpTxn");
            if (!pageContext.isFormSubmission()) {
                OAApplicationModule am = 
                    pageContext.getApplicationModule(webBean);
                String groupId = pageContext.getParameter("groupId");
                Serializable params[] = { groupId };
                am.invokeMethod("initQuery", params);
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "UpdateReviewGrpTxn", 
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
            String groupId = pageContext.getParameter("GroupId");
            String levelMode = pageContext.getParameter("LevelMode");
            Serializable[] params = { groupName, orgId, groupId };
            String result = 
                am.invokeMethod("validateReviewGrp", params).toString();
            String message;
            byte msgType = OAException.CONFIRMATION;
            if (result != null && result.equals("N")) {
                am.getOADBTransaction().commit();
                message = "��Ⱥ " + groupName + " �Ѵ����ɹ���";
            } else if (result != null && result.equals("Y")) {
                am.getOADBTransaction().rollback();
                message = "ͬһ��֯�¿�Ⱥ��������";
                msgType = OAException.WARNING;
            } else {
                am.getOADBTransaction().rollback();
                message = "ϵͳ�쳣�����Ժ����ԣ�";
                msgType = OAException.ERROR;
            }

            MessageToken tokens[] = { new MessageToken("CONF", message) };
            OAException confirmMessage = 
                new OAException("CUX", "CUX_PER_REVIEW_CONF", tokens, msgType, 
                                null);
            pageContext.putDialogMessage(confirmMessage);
            if (result != null && result.equals("N"))
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/PerReviewGroupPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
        }

        //Process cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "UpdateReviewGrpTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/PerReviewGroupPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

    }

}
