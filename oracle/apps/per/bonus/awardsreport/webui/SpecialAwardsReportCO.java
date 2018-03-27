/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.awardsreport.webui;

import cux.oracle.apps.per.bonus.awardsreport.server.AwardsReportAMImpl;

import cux.oracle.apps.per.bonus.awardsreport.server.DeptAwardsVOImpl;

import cux.oracle.apps.per.bonus.awardsreport.server.OtherPersonDistVOImpl;
import cux.oracle.apps.per.bonus.awardsreport.server.PersonAwardsVOImpl;

import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

/**
 * Controller for ...
 */
public class SpecialAwardsReportCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    private String queryYear = "";

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        //**设置页面缺省“年份”这个字段**
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String strYear = df.format(today);
        OAMessageChoiceBean yearBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("QueryYear");
        yearBean.setDefaultValue(strYear);

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        DeptAwardsVOImpl vo = 
            (DeptAwardsVOImpl)am.findViewObject("DeptAwardsVO1");
        if ("".equals(queryYear)) {
            queryYear = strYear;
        }
        vo.initSQL(queryYear);

        queryYear = "";
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
        queryYear = pageContext.getParameter("QueryYear");
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String event = pageContext.getParameter("event"); //event表示本次操作的事件名称

        if ("search".equals(event)) {
            DeptAwardsVOImpl vo = 
                (DeptAwardsVOImpl)am.findViewObject("DeptAwardsVO1");
            vo.initSQL(queryYear);
        }
        if ("goDetails".equals(event)) {
            pageContext.putSessionValue("queryYear", queryYear);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/awardsreport/webui/SpecialAwardsDetailsPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if ("queryPersonDist".equals(event)) {
            String deptName = pageContext.getParameter("selectDeptName");
            PersonAwardsVOImpl vo = 
                (PersonAwardsVOImpl)am.findViewObject("PersonAwardsVO1");
            vo.initSQL(deptName, queryYear);
            OtherPersonDistVOImpl oVo = 
                (OtherPersonDistVOImpl)am.findViewObject("OtherPersonDistVO1");
            oVo.initSQL(deptName, queryYear);
            OAMessageTextInputBean deptSelectedBean = 
                (OAMessageTextInputBean)webBean.findIndexedChildRecursive("DeptSelected");
            deptSelectedBean.setValue(pageContext, deptName);
        }
        if ("checkPersonDetails".equals(event)) {
            pageContext.putSessionValue("queryYear", queryYear);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/awardsreport/webui/AdvanceAwardsDetailsPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
    }

}
