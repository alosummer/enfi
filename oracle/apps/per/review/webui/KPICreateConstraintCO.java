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
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAMessageComponentLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

/**
 * Controller for ...
 */
public class KPICreateConstraintCO extends OAControllerImpl {
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

        OAMessageComponentLayoutBean container = 
            (OAMessageComponentLayoutBean)webBean.findIndexedChildRecursive("MainRN");
        OAMessageChoiceBean constraintObj = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("ConstraintObj");

        if (constraintObj != null) {
            constraintObj.setListVOBoundContainerColumn(0, container, 
                                                        "ConstraintType");
            constraintObj.setPickListCacheEnabled(false);
        }

        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateConstraintTxn");
            if (!pageContext.isFormSubmission()) {
                OAApplicationModule am = 
                    pageContext.getApplicationModule(webBean);
                String kpiId = pageContext.getParameter("kpiId");
                Serializable params[] = { kpiId };
                am.invokeMethod("createKPIConstraint", params);
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateConstraintTxn", 
                                                                      true)) {
            OADialogPage dp = new OADialogPage(NAVIGATION_ERROR);
            pageContext.redirectToDialogPage(dp);
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);

        //Process apply action
        if (pageContext.getParameter("Apply") != null) {
            am.getOADBTransaction().commit();
            //MessageToken tokens[] = { new MessageToken("CONF", "KPIԼ���ѱ���!")};
            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_KPI_CONSTRAINTS", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIConstraintPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        //Process cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateConstraintTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIConstraintPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        // Handle refresh LOV constraint object
        if ("refreshLov".equals(pageContext.getParameter("event"))) {
            OAMessageChoiceBean type = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("ConstraintType");
            String constraintType = type.getSelectionValue(pageContext);
            Serializable[] parameters = { constraintType };
            am.invokeMethod("poplistConstraintObjs", parameters);

            OAMessageChoiceBean obj = 
                (OAMessageChoiceBean)webBean.findChildRecursive("ConstraintObj");
            //System.out.println("1: view usage = "+obj.getPickListViewUsageName());
            //System.out.println("2:" + obj.getViewAttributeName());
        }


    }

}
