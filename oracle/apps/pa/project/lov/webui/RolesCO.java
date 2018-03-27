/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.project.lov.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.pa.util.webui.PAControllerImpl;

/**
 * Controller for ...
 */
public class RolesCO extends PAControllerImpl {
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
        String str1 = 
            (String)pageContext.getTransactionValue("paOrganizationId");
        String str2 = (String)pageContext.getTransactionValue("searchQuery");
        String str3 = (String)pageContext.getTransactionValue("paProjectId");
        Serializable[] arrayOfSerializable = { str2, str3 };
        OAViewObject localOAViewObject = 
            (OAViewObject)pageContext.getApplicationModule(webBean).findViewObject("RolesVO");
        localOAViewObject.invokeMethod("initQuery", arrayOfSerializable);
        localOAViewObject.executeQuery();
    }

}
