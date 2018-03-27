/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.advanceawards.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.NtfSuspendWarnVOImpl;
import cux.oracle.apps.per.bonus.project.server.PrjNtfSuspendWarnVOImpl;
import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import java.text.SimpleDateFormat;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 部门奖金提醒(暂停分配人员发放记录)
 * 每个月2号，运行“cux.月度奖金提醒(暂停分配)”请求，通知有奖金职责的人员
 * added by yang.gang,2016-5-29
 */
public class NtfSuspendWarnCO extends OAControllerImpl {
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
        this.initWeb(pageContext, webBean);
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
        if (pageContext.getParameter("Search") != null)
            this.ExtDistQuery(pageContext, webBean);
    }

    /* 初始化页面
     * */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        String strMonth = pageContext.getParameter("month");
        if (strMonth == null || "".equals(strMonth))
            return;

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM");
        String time = tempDate.format(new java.util.Date());

        OAMessageChoiceBean msDate = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("QueryPeriod");
        if (msDate != null)
            msDate.setValue(pageContext, time);

        String strOrgID = pageContext.getParameter("org_id");
        if (strOrgID == null || "".equals(strOrgID))
            return;
        pageContext.putSessionValue("org_id", strOrgID);
        Integer iOrgID = Integer.valueOf(strOrgID);

        AdvanceAwardsAMImpl am = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        String strOrgName = am.GetOrgName(iOrgID);
        String title = strOrgName + strMonth + " 部门奖金提醒信息(暂停分配人员发放记录)";
        OAPageLayoutBean page = (OAPageLayoutBean)webBean;
        page.setTitle(title);

        NtfSuspendWarnVOImpl vo = am.getNtfSuspendWarnVO1();
        vo.initQuery(strMonth, iOrgID);
        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PersonDisTable");
        if (table != null)
            table.queryData(pageContext);
    }

    /* 查询 */

    private void ExtDistQuery(OAPageContext pageContext, OAWebBean webBean) {
        AdvanceAwardsAMImpl am = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        String period = pageContext.getParameter("QueryPeriod");

        String strOrgID = this.GetSessionValue(pageContext, "org_id");
        if (strOrgID == null || "".equals(strOrgID))
            return;
        Integer iOrgID = Integer.valueOf(strOrgID);

        NtfSuspendWarnVOImpl vo = am.getNtfSuspendWarnVO1();
        vo.initQuery(period, iOrgID);

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PersonDisTable");
        if (table != null)
            table.queryData(pageContext);
    }

    //  session 获取值

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
