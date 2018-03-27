/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AprAMImpl;
import cux.oracle.apps.per.aprprocess.server.KPILovVOImpl;
import cux.oracle.apps.per.bonus.project.server.ProjectListVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * 选择 KPI 指标，目标设定界面
 * created by yang.gang.2016-4-25
 */
public class KPILovCO extends OAControllerImpl {
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

        //  初始化，根据当前页面的appraisalId限定指标范围
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        KPILovVOImpl vo = am.getKPILovVO1();
        String appraisalId = this.GetSessionValue(pageContext, "appraisalId");
        Integer iAprID = Integer.valueOf(appraisalId);
        if (vo.IsQuery && vo.AppraisalId.equals(iAprID))
            return;
        vo.initQuery(iAprID);
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
