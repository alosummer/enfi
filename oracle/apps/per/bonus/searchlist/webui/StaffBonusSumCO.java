/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.searchlist.webui;

import cux.oracle.apps.per.bonus.searchlist.MonthAndDeptAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

import oracle.jbo.domain.Date;

/**
 * Controller for ...
 */
public class StaffBonusSumCO extends OAControllerImpl {
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

        //设置当前默认年份
        OAMessageChoiceBean yearChoice = 
            (OAMessageChoiceBean)webBean.findChildRecursive("yearMC");
        String currentYear = Date.getCurrentDate().toString().substring(0, 4);
        //  yearChoice.setSelectedValue(currentYear);
        yearChoice.setDefaultValue(currentYear);
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

        if (pageContext.isFormSubmission()) {
            OAMessageChoiceBean yearChoice = 
                (OAMessageChoiceBean)webBean.findChildRecursive("yearMC");
            OAMessageChoiceBean deptChoice = 
                (OAMessageChoiceBean)webBean.findChildRecursive("deptMC");
            String year = (String)yearChoice.getValue(pageContext);
            String dept = (String)deptChoice.getValue(pageContext);

            MonthAndDeptAMImpl am = 
                (MonthAndDeptAMImpl)pageContext.getApplicationModule(webBean);

            if (deptChoice.getValue(pageContext) == null) {
                am.searchDeptBonusByYear(year);
            } else {
                am.searchDeptBonus(year, dept);
            }
        }

    }

}
