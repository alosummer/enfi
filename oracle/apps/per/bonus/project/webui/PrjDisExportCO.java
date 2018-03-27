/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.project.server.PrjDistributionVOImpl;
import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import java.util.ArrayList;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

/**
 * 导出数据页面
 */
public class PrjDisExportCO extends OAControllerImpl {
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
        String strPrjID = this.GetSessionValue(pageContext, "BonusPrjId");

        if (strPrjID.equals("")) {
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }

        Integer iPrjID = 0;
        iPrjID = Integer.valueOf(strPrjID);

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        am.initExportQuery(iPrjID);

        OATableBean exportTable = 
            (OATableBean)webBean.findChildRecursive("PrjDisExportTable");
        if (exportTable != null)
            exportTable.queryData(pageContext);
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
        if (pageContext.getParameter("ReturnButton") != null)
            this.GoToPrjDistPG(pageContext, webBean);
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

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

    /* 返回项目发放页面 */

    private void GoToPrjDistPG(OAPageContext pageContext, OAWebBean webBean) {
        String strPrjID = this.GetSessionValue(pageContext, "BonusPrjId");

        if (strPrjID.equals("")) {
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        Integer iPrjID = 0;
        iPrjID = Integer.valueOf(strPrjID);

        ArrayList list = am.GetPrjManagerUserID(iPrjID);
        if (list.size() != 2)
            return;

        int iManager = Integer.valueOf(list.get(0).toString());
        int iSupervisorId = Integer.valueOf(list.get(1).toString());
        int iUserId = am.getLoginPersonId();

        String strURL = "";
        if (iManager == iUserId)
            strURL = 
                    "OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjMemberPG";
        else if (iSupervisorId == iUserId)
            strURL = 
                    "OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjSupervisorPG";
        else
            return;
        pageContext.setForwardURL(strURL, null, 
                                  OAWebBeanConstants.KEEP_MENU_CONTEXT, null, 
                                  null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

}
