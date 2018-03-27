/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.bali.share.util.BooleanUtils;

/**
 * Controller for ...
 */
public class WorkflowUpdateCO extends OAControllerImpl {
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
        //查询按钮
        if (pageContext.getParameter("QueryButton") != null) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);

            String strGroup = pageContext.getParameter("GroupQuery");
            String strOrganization = 
                pageContext.getParameter("OrganizationQuery");
            String strPhase = pageContext.getParameter("PhaseQuery");
            String strPeriod = pageContext.getParameter("PeriodQuery");
            String strBeginYear = pageContext.getParameter("BeginYearQuery");
            String strEndYear = pageContext.getParameter("EndYearQuery");
            String strStatus = pageContext.getParameter("StatusQuery");
            String strEmpName = pageContext.getParameter("EmpNameQuery");
            Boolean bQuery = BooleanUtils.getBoolean(true);

            Serializable[] params = 
            { strGroup, strOrganization, strPhase, strPeriod, strBeginYear, 
              strEndYear, strStatus, strEmpName, bQuery };
            Class[] paramTypes = 
            { String.class, String.class, String.class, String.class, 
              String.class, String.class, String.class, String.class, 
              Boolean.class };
            am.invokeMethod("initWFInfoVO", params, paramTypes);
        }
    }

}
