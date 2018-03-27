/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.rectify.webui;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class QhseRectifyDetailPGCO extends OAControllerImpl {
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
        String headerId = pageContext.getParameter("HeaderId");
        CuxRectifyAMImpl pgAM = 
            (CuxRectifyAMImpl)pageContext.getRootApplicationModule();
        pgAM.getCuxRectifyNotifyHeaderResultVO2().setWhereClause(null);
        pgAM.getCuxRectifyNotifyHeaderResultVO2().setWhereClauseParams(null);
        pgAM.getCuxRectifyNotifyHeaderResultVO2().setWhereClause("HEADER_ID = :2");
        pgAM.getCuxRectifyNotifyHeaderResultVO2().setWhereClauseParams(new Object[] { "N", 
                                                                                      headerId });
        pgAM.getCuxRectifyNotifyHeaderResultVO2().executeQuery();
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

        if (pageContext.getParameter("CancelBTN") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/rectify/webui/QhseRectifySearchPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
    }

}
