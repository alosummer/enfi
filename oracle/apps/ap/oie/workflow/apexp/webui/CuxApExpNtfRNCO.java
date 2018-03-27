/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.ap.oie.workflow.apexp.webui;

import cux.oracle.apps.ap.oie.workflow.apexp.server.CuxApExpNtfAMImpl;

import cux.oracle.apps.ap.oie.workflow.apexp.server.CuxNtfMessageNameVOImpl;

import java.util.Enumeration;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class CuxApExpNtfRNCO extends OAControllerImpl {
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
        Enumeration eu = pageContext.getParameterNames();
        while (eu.hasMoreElements()) {
            Object aa = eu.nextElement();
            System.out.println(aa);
        }
        String ntfId = pageContext.getParameter("NtfId");


        CuxApExpNtfAMImpl regionAM = 
            (CuxApExpNtfAMImpl)pageContext.getApplicationModule(webBean);
        CuxNtfMessageNameVOImpl ntfVO = regionAM.getCuxNtfMessageNameVO1();
        ntfVO.setWhereClause(null);
        ntfVO.setWhereClauseParams(null);
        ntfVO.setWhereClauseParams(new Object[] { ntfId });
        ntfVO.executeQuery();

        if ((Boolean)ntfVO.first().getAttribute("HideFlag")) {
            webBean.findChildRecursive("BudgetRN").setRendered(Boolean.FALSE);
        }

        //String reportHeaderId= "43258";
        String reportHeaderId = pageContext.getParameter("ReportHeaderId");
        String retValue = regionAM.initDisplayVO(reportHeaderId);
        if (CuxApExpNtfAMImpl.DISPLAY_NONE.equals(retValue)) {
            webBean.findChildRecursive("OutTravelRN").setRendered(Boolean.FALSE);
            webBean.findChildRecursive("InTravelRN").setRendered(Boolean.FALSE);
        } else if (CuxApExpNtfAMImpl.DISPLAY_IN.equals(retValue)) {
            webBean.findChildRecursive("OutTravelRN").setRendered(Boolean.FALSE);
        } else {
            webBean.findChildRecursive("InTravelRN").setRendered(Boolean.FALSE);
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
