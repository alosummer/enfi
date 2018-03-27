/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.searchlist.webui;

import cux.oracle.apps.per.bonus.reports.server.BonusReportsAMImpl;

import cux.oracle.apps.per.bonus.searchlist.MonthAndDeptAMImpl;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.OAApplicationModule;

import oracle.apps.fnd.framework.OAException;

import oracle.jbo.domain.DataCreationException;
import oracle.jbo.domain.Date;

/**
 * Controller for ...
 */
public class StaffBonusDetailCO extends OAControllerImpl {
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

        //设置当前默认月份
        OAMessageChoiceBean dateChoice = 
            (OAMessageChoiceBean)webBean.findChildRecursive("monthMC");
        String currentMonth = Date.getCurrentDate().toString().substring(0, 7);
        // dateChoice.setSelectedValue(currentMonth);
        dateChoice.setDefaultValue(currentMonth);
        //按月份查询
        //OAApplicationModule am = pageContext.getApplicationModule(webBean);
        //Serializable[] parameters = { currentMonth };
        //am.invokeMethod("initQuery", parameters);

        /*
      OAMessageChoiceBean deptChoice  = (OAMessageChoiceBean)webBean.findChildRecursive("deptMC");
      String currentDept= (String)deptChoice.getValue(pageContext);*/
        //部门信息读取不出

        /*
      MonthAndDeptAMImpl am =(MonthAndDeptAMImpl) pageContext.getApplicationModule(webBean);
      am.initQuery(currentMonth,currentDept);*/

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
        String event = pageContext.getParameter("event");
        if (event.equals("search")) {
            OAMessageChoiceBean monthChoice = 
                (OAMessageChoiceBean)webBean.findChildRecursive("monthMC");
            OAMessageChoiceBean deptChoice = 
                (OAMessageChoiceBean)webBean.findChildRecursive("deptMC");
            String month = (String)monthChoice.getValue(pageContext);
            String dept = (String)deptChoice.getValue(pageContext);

            MonthAndDeptAMImpl am = 
                (MonthAndDeptAMImpl)pageContext.getApplicationModule(webBean);

            am.search(month, dept);
            //am.searchByDept(dept);
            //am.initQuery(month);
        }


    }

}
