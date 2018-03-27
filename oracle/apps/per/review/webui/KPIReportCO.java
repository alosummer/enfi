/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.KeyPIVOImpl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;


/**
 * Controller for ...
 */
public class KPIReportCO extends OAControllerImpl {
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
                                                       "CreateReportTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateReportTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OAPageLayoutBean page = (OAPageLayoutBean)webBean;
        String kpiId = pageContext.getParameter("kpiId");
        String kpiName = pageContext.getParameter("kpiName");

        String title = "";
        String prefix = "ά�ȶ���( ";
        String suffix = " )";
        try {
            prefix = new String(prefix.getBytes("ISO-8859-1"), "GB2312");
            suffix = new String(suffix.getBytes("ISO-8859-1"), "GB2312");
        } catch (UnsupportedEncodingException e) {
            ;
        }

        title = prefix + kpiName + suffix;
        page.setTitle(title);

        OAApplicationModule am = pageContext.getApplicationModule(webBean);

        if (pageContext.getParameter("refresh") != "Y") {
            KeyPIVOImpl vo = (KeyPIVOImpl)am.findViewObject("KeyPIVO2");
            vo.executeQuery();
        }

        if (kpiId != null) {
            Serializable params[] = { kpiId };
            am.invokeMethod("initKPILogic", params);
            pageContext.putSessionValue("kpiId", kpiId);
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

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateReportTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            am.invokeMethod("commit");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_KPI_REPORT_SAVED", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG&refresh=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }
    }

}
