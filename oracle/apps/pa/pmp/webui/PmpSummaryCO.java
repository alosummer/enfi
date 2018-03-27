/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.pmp.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class PmpSummaryCO extends PmpBaseCO {
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
        if (pageContext.isLoggingEnabled(oracle.apps.fnd.framework.OAFwkConstants.PROCEDURE)) {
            pageContext.writeDiagnostics(this, "start processRequest", 
                                         oracle.apps.fnd.framework.OAFwkConstants.PROCEDURE);
        }
        String projectId = (String)pageContext.getSessionValue("ProjectId");
        String versionNum = (String)pageContext.getSessionValue("VersionNum");
        String pmpId = (String)pageContext.getSessionValue("PmpId");
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if(am.getOADBTransaction().isDirty()){
            am.getOADBTransaction().rollback();
        }
        Serializable[] paras = { projectId, versionNum };
        am.invokeMethod("initPmpSummary", paras);
        Serializable[] parasPmpStatus = { pmpId };
        am.invokeMethod("initPmpStatus", parasPmpStatus);
        Serializable[] parasInitApprsListQuery = 
        { pmpId, projectId + versionNum };
        am.invokeMethod("initApprsListQuery", parasInitApprsListQuery);
        Serializable[] parasActionHistory = { pmpId, projectId + versionNum };
        am.invokeMethod("initActionHistory", parasActionHistory);
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
