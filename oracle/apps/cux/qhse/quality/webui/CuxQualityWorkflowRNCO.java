/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.quality.webui;

import cux.oracle.apps.cux.qhse.quality.server.CuxQualityAMImpl;
import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class CuxQualityWorkflowRNCO extends OAControllerImpl {
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

        String headerId = pageContext.getParameter("HEADER_ID");
        CuxQualityAMImpl pgAM = 
            (CuxQualityAMImpl)pageContext.getApplicationModule(webBean);
        pgAM.getCuxProjectWorkplanForWFVO1().setWhereClause(null);
        pgAM.getCuxProjectWorkplanForWFVO1().setWhereClauseParams(null);
        pgAM.getCuxProjectWorkplanForWFVO1().setWhereClauseParams(new Object[] { headerId });
        pgAM.getCuxProjectWorkplanForWFVO1().executeQuery();

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
    }

}
