/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.ap.oie.entry.header.lov.webui;

import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOATravelDetailLovVOImpl;
import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOATravelDetailLovVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpAccomFeeVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpAccomFeeVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaLineVOImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaLineVORowImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTrafficReimbVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTrafficReimbVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTravelSubsidyVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTravelSubsidyVORowImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.RowIterator;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class CuxOATravelDetailLovRNCO extends OAControllerImpl {
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
        CuxApExpRelatedOaInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedOaInfoVORowImpl)relateHeaderVO.first();

        Number oaTravelProcessId = infoHeaderRow.getOaTravelProcessId();
        if (oaTravelProcessId == null) {
            throw new OAException("请先选择出差申请！");
        }
        CuxApExpRelatedOaLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedOaLineVOImpl)rootAM.findViewObject("CuxApExpRelatedOaLineVO1");
        CuxOATravelDetailLovVOImpl relateLineLOVVO = 
            (CuxOATravelDetailLovVOImpl)rootAM.findViewObject("CuxOATravelDetailLovVO1");

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
            relateLineLOVVO.setWhereClause(" emp_id  not in (" + whereClause + 
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
        OAApplicationModule rootAM = pageContext.getRootApplicationModule();
        CuxOATravelDetailLovVOImpl relateLineLOVVO = 
            (CuxOATravelDetailLovVOImpl)rootAM.findViewObject("CuxOATravelDetailLovVO1");
        CuxApExpRelatedOaLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedOaLineVOImpl)rootAM.findViewObject("CuxApExpRelatedOaLineVO1");


        CuxApExpTravelSubsidyVOImpl travelSubsidyVO = 
            (CuxApExpTravelSubsidyVOImpl)rootAM.findViewObject("CuxApExpTravelSubsidyVO1");
        CuxApExpTrafficReimbVOImpl trafficReimbVO = 
            (CuxApExpTrafficReimbVOImpl)rootAM.findViewObject("CuxApExpTrafficReimbVO1");
        CuxApExpAccomFeeVOImpl accomFeeVO = 
            (CuxApExpAccomFeeVOImpl)rootAM.findViewObject("CuxApExpAccomFeeVO1");

        CuxApExpRelatedOaInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedOaInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedOaInfoVO1");
        CuxApExpRelatedOaInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedOaInfoVORowImpl)relateHeaderVO.first();
        Number relationshipId = infoHeaderRow.getRelationshipId();

        if ("lovSelect".equals(pageContext.getParameter(EVENT_PARAM))) {
            int fetchedRowCount = relateLineLOVVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator selectIter = 
                    relateLineEOVO.createRowSetIterator("selectIter");
                selectIter.setRangeStart(0);
                selectIter.setRangeSize(fetchedRowCount);
                CuxOATravelDetailLovVORowImpl relateLineLovRow = null;
                for (int i = 0; i <= fetchedRowCount - 1; i++) {
                    relateLineLovRow = 
                            (CuxOATravelDetailLovVORowImpl)relateLineLOVVO.getRowAtRangeIndex(i);

                    String selectedFlag = 
                        (String)relateLineLovRow.getAttribute("lovSelectVoAttr");
                    Number maxLineNumber = null;
                    if ("Y".equals(selectedFlag)) {
                        maxLineNumber = relateLineEOVO.getMaxLineNumber();
                        relateLineLovRow.setRowNumber(maxLineNumber);
                        relateLineLovRow.setRelateOaTravelLineId(rootAM.getSequenceValue("CUX_AP_EXP_RELATED_OA_LINE_S"));
                        maxLineNumber = maxLineNumber.add(1);

                        /******************************1.补贴行**************************************/
                        CuxApExpTravelSubsidyVORowImpl autoCreateSubsidyRow = 
                            (CuxApExpTravelSubsidyVORowImpl)travelSubsidyVO.createRow();
                        autoCreateSubsidyRow.setRelationshipId(relationshipId);
                        autoCreateSubsidyRow.setSubsidyLineId(rootAM.getSequenceValue("CUX_AP_EXP_TRAVEL_SUBSIDY_S"));
                        autoCreateSubsidyRow.setEmpId(relateLineLovRow.getEmpId());
                        autoCreateSubsidyRow.setEmployeeNumber(relateLineLovRow.getEmpNumber());
                        autoCreateSubsidyRow.setEmpName(relateLineLovRow.getEmpName());
                        autoCreateSubsidyRow.setFeeType("国内差旅-补助费");
                        autoCreateSubsidyRow.setLineNumber(relateLineLovRow.getRowNumber());
                        //                        CuxOATravelDetailLovRNCO.insertRowAtLastIndex(new Number(30), 
                        //                                                                      travelSubsidyVO, 
                        //                                                                      autoCreateSubsidyRow);
                        travelSubsidyVO.insertRowAtRangeIndex(0, 
                                                              autoCreateSubsidyRow);

                        /******************************2.交通费报销**************************************/
                        for (int j = 1; j >= 0; j--) {
                            CuxApExpTrafficReimbVORowImpl autoCreateTrafficReimbRow = 
                                (CuxApExpTrafficReimbVORowImpl)trafficReimbVO.createRow();
                            autoCreateTrafficReimbRow.setRelationshipId(relationshipId);
                            autoCreateTrafficReimbRow.setTrafficReimbLineId(rootAM.getSequenceValue("CUX_AP_EXP_TRAFFIC_REIMB_S"));
                            autoCreateTrafficReimbRow.setEmpId(relateLineLovRow.getEmpId());
                            autoCreateTrafficReimbRow.setEmployeeNumber(relateLineLovRow.getEmpNumber());
                            autoCreateTrafficReimbRow.setEmpName(relateLineLovRow.getEmpName());
                            autoCreateTrafficReimbRow.setLineNumber(relateLineLovRow.getRowNumber().multiply(2).subtract(j));
                            //                            CuxOATravelDetailLovRNCO.insertRowAtLastIndex(new Number(30), 
                            //                                                                          trafficReimbVO, 
                            //                                                                          autoCreateTrafficReimbRow);

                            trafficReimbVO.insertRowAtRangeIndex(0, 
                                                                 autoCreateTrafficReimbRow);
                        }
                        /******************************3.住宿费报销**************************************/

                        CuxApExpAccomFeeVORowImpl autoCreateAccomFeeRow = 
                            (CuxApExpAccomFeeVORowImpl)accomFeeVO.createRow();
                        autoCreateAccomFeeRow.setRelationshipId(relationshipId);
                        autoCreateAccomFeeRow.setAccomFeeLineId(rootAM.getSequenceValue("CUX_AP_EXP_ACCOM_FEE_S"));
                        autoCreateAccomFeeRow.setEmpId(relateLineLovRow.getEmpId());
                        autoCreateAccomFeeRow.setFeeType("国内差旅-住宿费");
                        autoCreateAccomFeeRow.setEmployeeNumber(relateLineLovRow.getEmpNumber());
                        autoCreateAccomFeeRow.setEmpName(relateLineLovRow.getEmpName());
                        autoCreateAccomFeeRow.setLineNumber(relateLineLovRow.getRowNumber());
                        accomFeeVO.insertRowAtRangeIndex(0, 
                                                         autoCreateAccomFeeRow);

                    }
                }
                selectIter.closeRowSetIterator();
            }
        }
    }


    public static void insertRowAtLastIndex(Number numOfDisplay, 
                                            OAViewObject vo, OARow row) {
        RowSet localRowSet = vo.getRowSet();
        int i = 1;
        int j = numOfDisplay.intValue();
        int rangeStart = localRowSet.getRangeStart();
        int rangeSize = localRowSet.getRangeSize();
        int rowCountInRange = localRowSet.getRowCountInRange();
        int i1;
        if (rowCountInRange + i <= j)
            i1 = rowCountInRange;
        else {
            i1 = j - i;
        }

        if (rangeSize < i1) {
            rangeSize++;
            localRowSet.setRangeSize(rangeSize);
            localRowSet.setRangeStart(rangeStart);
        }
        localRowSet.insertRowAtRangeIndex(i1, row);
        localRowSet.setCurrentRowAtRangeIndex(i1);

    }


    public static void main(String[] args) {
        String whereClause = "1111,22222,33333,";
        System.out.println(whereClause.substring(0, whereClause.length() - 1));
    }
}
