/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.awardsreport.webui;

import cux.oracle.apps.per.bonus.awardsreport.server.DeptAwardsVOImpl;

import cux.oracle.apps.per.bonus.awardsreport.server.DeptDetailsVOImpl;

import cux.oracle.apps.per.bonus.awardsreport.server.DeptDistDetailsVOImpl;

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
public class SpecialAwardsDetailsCO extends OAControllerImpl {
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
        String deptName = pageContext.getParameter("queryDeptName");
        OAMessageTextInputBean deptBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("DeptName");
        OAMessageTextInputBean yearBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("QueryYear");
        deptBean.setValue(pageContext, deptName);
        yearBean.setValue(pageContext, queryYear);

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        DeptDetailsVOImpl quotaVO = 
            (DeptDetailsVOImpl)am.findViewObject("DeptDetailsVO1");
        quotaVO.initSQL(queryYear, deptName);
        DeptDistDetailsVOImpl distVO = 
            (DeptDistDetailsVOImpl)am.findViewObject("DeptDistDetailsVO1");
        distVO.initSQL(queryYear, deptName);
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
