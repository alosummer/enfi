/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.lov.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class ValueSetDynCo extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        System.out.println("ValueSetDynCo : processRequest is running");
        super.processRequest(pageContext, webBean);
        String lValuesetName = null;
        boolean lRefresh = true;
        if (pageContext.getSessionValue("ValuesetName") != null) {
            System.out.println("get ValuesetName value: " + 
                               pageContext.getSessionValue("ValuesetName"));
            lValuesetName = 
                    pageContext.getSessionValue("ValuesetName").toString();
            if (pageContext.getSessionValue("ValuesetNameOld") != null) {
                if (pageContext.getSessionValue("ValuesetNameOld").equals(lValuesetName)) {
                    lRefresh = false;
                } else {
                    pageContext.putSessionValue("ValuesetNameOld", 
                                                lValuesetName);
                }
            } else {
                pageContext.putSessionValue("ValuesetNameOld", lValuesetName);
            }
            if (lRefresh) {
                System.out.println("Refreshing");
                OAApplicationModule am = 
                    pageContext.getApplicationModule(webBean);
                Serializable params[] = { lValuesetName };
                am.invokeMethod("dynamicVO", params);
            }

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
    }

}
