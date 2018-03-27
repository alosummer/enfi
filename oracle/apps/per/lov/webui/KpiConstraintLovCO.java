/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.lov.webui;

import cux.oracle.apps.per.poplist.server.KPIConJobVOImpl;
import cux.oracle.apps.per.lov.server.PerLovAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.OAApplicationModule;

/**
 * Controller for ...
 */
public class KpiConstraintLovCO extends OAControllerImpl {
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
        /*OAApplicationModule am = pageContext.getApplicationModule(webBean);
    String lConstraintType = null;
    if(pageContext.getParameter("ConstraintType")!= null) {
        lConstraintType = pageContext.getParameter("ConstraintType");
        System.out.println("get value : "+ lConstraintType);

    }
    else
    {
        lConstraintType = "DEPARTMENT";
    }
      KPIConJobVOImpl vo = (KPIConJobVOImpl)am.findViewObject("KpiConJobVO1");

      vo.setWhereClauseParam(0, lConstraintType);
      vo.executeQuery();   */
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
