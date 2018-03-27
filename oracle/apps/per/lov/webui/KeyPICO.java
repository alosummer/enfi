/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.lov.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class KeyPICO extends OAControllerImpl {
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OAViewObject vo = (OAViewObject)am.findViewObject("PerKPIVO1");
        vo.setWhereClause(null);
        vo.setWhereClauseParams(null);
        /*20100830 zs*/
        String appraisalId = pageContext.getParameter("appraisalId");
        if (appraisalId == null || appraisalId.equals("")) {
            appraisalId = (String)pageContext.getSessionValue("appraisalId");
        }
        pageContext.putSessionValue("appraisalId", appraisalId);
        /* StringBuffer whereClause = new StringBuffer(500);
       whereClause.append(" KPI_DATA_SOURCE = -1 ");
       vo.setWhereClause(whereClause.toString());
       vo.executeQuery();*/
        /*20100830 zs*/
    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OAViewObject vo = (OAViewObject)am.findViewObject("PerKPIVO1");
        String kpiClass = (String)pageContext.getSessionValue("kpiClass");
        /*20100830 zs*/
        String appraisalId = 
            (String)pageContext.getSessionValue("appraisalId");
        /*20100830 zs*/
        if (kpiClass != null && appraisalId != null) {
            vo.setWhereClause(" KPI_CLASS = :1 AND cux_jxkh_common_pkg.get_constraint_desc(kpi_id,:2) != 'NOTMATCH' ");
            vo.setWhereClauseParams(null);
            vo.setWhereClauseParam(0, kpiClass);
            vo.setWhereClauseParam(1, appraisalId);
        }

        super.processFormRequest(pageContext, webBean);

    }

}
