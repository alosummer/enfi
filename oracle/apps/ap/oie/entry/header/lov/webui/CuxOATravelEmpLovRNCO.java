/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.ap.oie.entry.header.lov.webui;

import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOATravelerEmpLovVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVORowImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaLineVOImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaLineVORowImpl;

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
public class CuxOATravelEmpLovRNCO extends OAControllerImpl {
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
        CuxApExpRelatedOaInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedOaInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedOaInfoVO1");
        CuxOATravelerEmpLovVOImpl relateLineEmpLOVVO = 
            (CuxOATravelerEmpLovVOImpl)rootAM.findViewObject("CuxOATravelerEmpLovVO1");

        CuxApExpRelatedOaInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedOaInfoVORowImpl)relateHeaderVO.first();

        Number oaTravelProcessId = infoHeaderRow.getOaTravelProcessId();
        if (oaTravelProcessId == null) {
            throw new OAException("请先选择出差申请！");
        }
        CuxApExpRelatedOaLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedOaLineVOImpl)rootAM.findViewObject("CuxApExpRelatedOaLineVO1");
        int fetchedRowCount = relateLineEOVO.getRowCount();
        String whereClause = "";
        if (fetchedRowCount > 0) {
            RowSetIterator filterIter = 
                relateLineEOVO.createRowSetIterator("filterIter");
            filterIter.setRangeStart(0);
            filterIter.setRangeSize(fetchedRowCount);
            CuxApExpRelatedOaLineVORowImpl relateLineEORow = null;
            for (int i = fetchedRowCount - 1; i >= 0; i--) {
                relateLineEORow = 
                        (CuxApExpRelatedOaLineVORowImpl)relateLineEOVO.getRowAtRangeIndex(i);
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
