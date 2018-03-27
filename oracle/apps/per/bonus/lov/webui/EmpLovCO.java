/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.lov.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsDeptPersonVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;

import cux.oracle.apps.per.bonus.lov.server.EmpLovVOImpl;
import cux.oracle.apps.per.bonus.lov.server.EmployeeAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class EmpLovCO extends OAControllerImpl {
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

        String strPayperiod = this.GetSessionValue(pageContext, "Payperiod");
        String strOrgid = this.GetSessionValue(pageContext, "PayOrgID");
        String strEmpOrgid = this.GetSessionValue(pageContext, "EmpOrgID");

        int iOrgid = Integer.parseInt(strOrgid);
        int iEmpOrgid = Integer.parseInt(strEmpOrgid);

        EmployeeAMImpl amImpl = 
            (EmployeeAMImpl)pageContext.getApplicationModule(webBean);
        EmpLovVOImpl vo = (EmpLovVOImpl)amImpl.findViewObject("EmpLovVO1");
        vo.initQuery(iOrgid, iEmpOrgid, strPayperiod);
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

    private String GetSessionValue(OAPageContext pageContext, 
                                   String strSessionName) {
        String strRtn = "";
        if (pageContext.getSessionValue(strSessionName) != null && 
            !pageContext.getSessionValue(strSessionName).toString().equals("")) {
            strRtn = pageContext.getSessionValue(strSessionName).toString();
        }
        return strRtn;
    }

}
