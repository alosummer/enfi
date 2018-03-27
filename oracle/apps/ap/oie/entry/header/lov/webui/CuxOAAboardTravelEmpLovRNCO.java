/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.ap.oie.entry.header.lov.webui;

import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelEmpLovVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardInfoVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardInfoVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardLineVOImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardLineVORowImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class CuxOAAboardTravelEmpLovRNCO extends OAControllerImpl {
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

        OAApplicationModule rootAM = pageContext.getRootApplicationModule();

        CuxApExpRelatedAboardInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedAboardInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardInfoVO1");

        CuxOAAboardTravelEmpLovVOImpl relateLineEmpLOVVO = 
            (CuxOAAboardTravelEmpLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelEmpLovVO1");

        CuxApExpRelatedAboardInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedAboardInfoVORowImpl)relateHeaderVO.first();

        Number oaOutseaTravelProcessId = 
            infoHeaderRow.getOaOutseaTravelProcessId();
        if (oaOutseaTravelProcessId == null) {
            throw new OAException("请先选择出差申请！");
        }

        CuxApExpRelatedAboardLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedAboardLineVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardLineVO1");

        int fetchedRowCount = relateLineEOVO.getRowCount();
        String whereClause = "";
        if (fetchedRowCount > 0) {
            RowSetIterator filterIter = 
                relateLineEOVO.createRowSetIterator("filterIter");
            filterIter.setRangeStart(0);
            filterIter.setRangeSize(fetchedRowCount);
            CuxApExpRelatedAboardLineVORowImpl relateLineEORow = null;
            for (int i = fetchedRowCount - 1; i >= 0; i--) {
                relateLineEORow = 
                        (CuxApExpRelatedAboardLineVORowImpl)relateLineEOVO.getRowAtRangeIndex(i);
                whereClause = 
                        whereClause + relateLineEORow.getEmpId().intValue() + 
                        ",";
            }
            filterIter.closeRowSetIterator();
        }

        if (!"".equals(whereClause)) {
            whereClause = whereClause.substring(0, whereClause.length() - 1);
            relateLineEmpLOVVO.setWhereClause(" emp_id   in (" + whereClause + 
                                              " )");
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
