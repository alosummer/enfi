/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.pay.server.PayAMImpl;
import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import java.text.SimpleDateFormat;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAQueryUtils;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 项目管理奖发放详细信息（劳务派遣人员），只允许002038@cux.COM.CN，张缨缨 访问
 * Yang.Gang, 2015-9-7
 */
public class PrjDispatchCO extends OAControllerImpl {
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
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        if (!am.IsUserValidPrjDispatch()) {
            this.ShowMessage(pageContext, "当前用户没有对该页面的访问权限");
            return;
        }

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
        String strQueryDate = 
            pageContext.getParameter("approvedate"); // 格式: 2015-09
        String time = "";
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM");
        if (strQueryDate == null || "".equals(strQueryDate))
            time = tempDate.format(new java.util.Date());
        else
            time = strQueryDate;

        OAMessageChoiceBean msDate = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("QueryPeriod");
        if (msDate != null)
            msDate.setValue(pageContext, time);

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        am.initDispatchQuery(time);

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PersonDisTable");
        table.queryData(pageContext, false);
    }

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

    /* 查询 */

    private void ExtDistQuery(OAPageContext pageContext, OAWebBean webBean) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        String period = pageContext.getParameter("QueryPeriod");
        am.initDispatchQuery(period);

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PersonDisTable");
        table.queryData(pageContext, false);
    }

}
