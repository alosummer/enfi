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

import oracle.jbo.Row;

/**
 * Controller for ...
 */
public class PmpCoordinationCO extends PmpBaseCO {
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
        String projectId = (String)pageContext.getSessionValue("ProjectId");
        String versionNum = (String)pageContext.getSessionValue("VersionNum");
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if(am.getOADBTransaction().isDirty()){
            am.getOADBTransaction().rollback();
        }
        Serializable paras[] = { projectId, versionNum };
        am.invokeMethod("initPmpCoordination", paras);
        OAWebBean Delete = webBean.findChildRecursive("Delete");
        Delete.setAttributeValue(WARN_ABOUT_CHANGES, Boolean.FALSE);
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
        String projectId = (String)pageContext.getSessionValue("ProjectId");
        String versionNum = (String)pageContext.getSessionValue("VersionNum");
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String event = pageContext.getParameter(this.EVENT_PARAM);
        Serializable paras[] = { projectId, versionNum };
        if ("Add".equals(event)) {
            am.invokeMethod("addCoordination", paras);
        }
        if ("Delete".equals(event)) {
            String rowRef = 
                pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE);
            Row row = am.findRowByRef(rowRef);
            row.remove();
        }
    }

}
