/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.po.autocreate.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

/**
 * Controller for ...
 */
public class CuxReqSearchCO extends oracle.apps.po.autocreate.webui.ReqSearchCO {
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
    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        if (pageContext.getParameter("AddToDocumentBuilder") != null) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            ViewObject vo = am.findViewObject("ReqSearchVO");
            Row[] rows = vo.getFilteredRows("SelectFlag", "Y");
            if (rows.length > 0) {
                String projectNum = (String)rows[0].getAttribute("ProjectNum");
                if (projectNum != null && !"".equals(projectNum)) {
                    for (int i = 0; i < rows.length; i++) {
                        if (!projectNum.equals(rows[i].getAttribute("ProjectNum"))) {
                            throw new OAException("请选择同项目下的采购申请!", 
                                                  OAException.ERROR);
                        }
                    }
                }
                pageContext.putSessionValue("ProjectNum", projectNum);
            }
        }
        super.processFormRequest(pageContext, webBean);
    }

}
