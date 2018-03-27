/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AprAMImpl;
import cux.oracle.apps.per.aprprocess.server.BehavAddSubVOImpl;
import cux.oracle.apps.per.aprprocess.server.KPILovVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

/**
 * 行为规范、加分项、减分项列表
 * created by yang.gang.2016-4-27
 */
public class BehavAddSubCO extends OAControllerImpl {
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
        String appraisalId = 
            this.GetParameterValue(pageContext, "appraisalId");
        String strClass = this.GetParameterValue(pageContext, "kpiclass");
        if ("".equals(appraisalId) || "".equals(strClass))
            return;

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        BehavAddSubVOImpl vo = am.getBehavAddSubVO1();
        if (!am.isInteger(appraisalId))
            return;

        Integer iAprID = Integer.valueOf(appraisalId);
        vo.initQuery(iAprID, strClass);

        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive("BehavAddSubVO1");
        tableBean.queryData(pageContext, false);

        this.SetTitle(webBean, strClass);
    }

    /* 设置页面标题 */

    private void SetTitle(OAWebBean webBean, String strClass) {
        String strTitle = "行为规范";

        if ("BEHAVIOUR".equals(strClass))
            strTitle = "行为规范";
        else if ("ADD".equals(strClass))
            strTitle = "加分项";
        else if ("SUBTRACT".equals(strClass))
            strTitle = "减分项";

        OAPageLayoutBean page = (OAPageLayoutBean)webBean;
        page.setTitle(strTitle);
        page.setWindowTitle(strTitle);
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


    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
    }

}
