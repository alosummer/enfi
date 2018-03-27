/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.dlv.lov.webui;

import com.sun.java.util.collections.ArrayList;

import cux.oracle.apps.pa.dlv.server.EmpProjectSpecVOImpl;
import cux.oracle.apps.pa.dlv.server.EmpProjectSpecVORowImpl;

import java.util.Dictionary;
import java.util.Enumeration;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.ViewObject;

/**
 * Controller for ...
 */
public class SpecialityLOVCO extends OAControllerImpl {
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
        ViewObject vo = am.findViewObject("SpecialityVO1");
        vo.setWhereClause(null);
        String projectId = pageContext.getSessionValue("ProjectId") + "";
        String lovSourceId = pageContext.getLovInputSourceId();
        if ("ReleaseSpecialtyName".equals(lovSourceId)) {
            StringBuilder criteria = new StringBuilder();
            criteria.append("1=1");
            EmpProjectSpecVOImpl specVO = 
                (EmpProjectSpecVOImpl)am.findViewObject("EmpProjectSpecVO1");
            if (specVO.getWhereClause() != null) {
                specVO.setWhereClause(null);
            }
            specVO.setWhereClause("project_id = " + projectId);
            specVO.setMaxFetchSize(-1);
            specVO.executeQuery();
            ArrayList specs = new ArrayList();
            while (specVO.hasNext()) {
                EmpProjectSpecVORowImpl specRow = 
                    (EmpProjectSpecVORowImpl)specVO.next();
                if (specRow.getSpecialtyCode() != null) {
                    specs.add(specRow.getSpecialtyCode());
                }
            }
            if (specs.size() == 0) {
                criteria.append(" AND 1 = 2");
            } else {
                String specStmt = "(";
                for (int i = 0; i < specs.size(); i++) {
                    if (i > 0) {
                        specStmt += ",";
                    }
                    specStmt = specStmt + "'" + specs.get(i).toString() + "'";
                }
                specStmt += ")";
                criteria.append(" AND lookup_code IN " + specStmt);
            }
            vo.setWhereClause(criteria.toString());
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
