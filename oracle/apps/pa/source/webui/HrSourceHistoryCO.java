/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.source.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.nav.OALinkBean;

/**
 * Controller for ...
 */
public class HrSourceHistoryCO extends OAControllerImpl {
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String personId = pageContext.getParameter("personId");
        String hrSourceType = pageContext.getParameter("HrSourceType");
        Serializable paras[] = { personId, hrSourceType };
        am.invokeMethod("initHistory", paras);
        OALinkBean returnBean = 
            (OALinkBean)webBean.findChildRecursive("item1");
        if ("M".equals(hrSourceType)) {
            returnBean.setText("返回项目管理人才库");
            returnBean.setDestination("OA.jsp?page=/cux/oracle/apps/pa/source/webui/HrSourcePG&retainAM=Y&HrSourceType=M");
            OAWebBean gw = webBean.findChildRecursive("xlgm_gw");
            gw.setAttributeValue(this.RENDERED_ATTR, true);
            OAWebBean zj = webBean.findChildRecursive("xlgm_zj");
            zj.setAttributeValue(this.RENDERED_ATTR, true);
        } else if ("D".equals(hrSourceType)) {
            returnBean.setText("返回设计人员人才库");
            returnBean.setDestination("OA.jsp?page=/cux/oracle/apps/pa/source/webui/HrSourcePG&retainAM=Y&HrSourceType=D");
            OAWebBean evaluateJobLevel = 
                webBean.findChildRecursive("EvaluateJobLevel");
            evaluateJobLevel.setAttributeValue(this.RENDERED_ATTR, true);
        }
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

}
