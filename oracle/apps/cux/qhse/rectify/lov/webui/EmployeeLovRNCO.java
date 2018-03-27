/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.rectify.lov.webui;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyAMImpl;
import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyHeadersVOImpl;
import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyHeadersVORowImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class EmployeeLovRNCO extends OAControllerImpl {
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
        CuxRectifyAMImpl pgAM = 
            (CuxRectifyAMImpl)pageContext.getRootApplicationModule();
        CuxRectifyNotifyHeadersVOImpl headerVO = 
            pgAM.getCuxRectifyNotifyHeadersVO1();
        CuxRectifyNotifyHeadersVORowImpl headerRow = 
            (CuxRectifyNotifyHeadersVORowImpl)headerVO.getCurrentRow();
        String docType = headerRow.getDocType();
        Number orgId = headerRow.getManDeptId();

        pgAM.getEmployeeLovVO1().setWhereClause(null);
        pgAM.getEmployeeLovVO1().setWhereClauseParams(null);
        if (null == orgId) {
            orgId = new Number(-1);
        }
        pgAM.getEmployeeLovVO1().setWhereClauseParams(new Object[] { orgId, 
                                                                     docType, 
                                                                     docType });

        System.out.println(pgAM.getEmployeeLovVO1().getQuery());
        System.out.println(orgId + "  " + docType);

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
