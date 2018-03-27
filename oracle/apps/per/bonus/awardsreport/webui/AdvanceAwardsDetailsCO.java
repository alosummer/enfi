/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.awardsreport.webui;

import cux.oracle.apps.per.bonus.awardsreport.server.DeptDetailsVOImpl;
import cux.oracle.apps.per.bonus.awardsreport.server.DeptDistDetailsVOImpl;

import cux.oracle.apps.per.bonus.awardsreport.server.PersonDistDetailsVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

/**
 * Controller for ...
 */
public class AdvanceAwardsDetailsCO extends OAControllerImpl {
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
        String queryYear = pageContext.getSessionValue("queryYear").toString();
        String personNumber = pageContext.getParameter("queryPersonNumber");
        String personName = pageContext.getParameter("queryPersonName");
        OAMessageTextInputBean personNumberBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("PersonNumber");
        OAMessageTextInputBean personNameBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("PersonName");
        OAMessageTextInputBean yearBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("QueryYear");
        personNumberBean.setValue(pageContext, personNumber);
        personNameBean.setValue(pageContext, personName);
        yearBean.setValue(pageContext, queryYear);

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        PersonDistDetailsVOImpl personVO = 
            (PersonDistDetailsVOImpl)am.findViewObject("PersonDistDetailsVO1");
        personVO.initSQL(personNumber, queryYear);
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
        if (pageContext.getParameter("ReturnButton") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/awardsreport/webui/SpecialAwardsReportPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
    }

}
