/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.ap.oie.entry.header.lov.webui;

import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelDetailLovVOImpl;
import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelDetailLovVORowImpl;

import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelFeeLovVOImpl;
import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelFeeLovVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardFeeLineVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardFeeLineVORowImpl;
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
public class CuxOAAboardTravelDetailLovRNCO extends OAControllerImpl {
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

        CuxApExpRelatedAboardInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedAboardInfoVORowImpl)relateHeaderVO.first();

        Number oaAboardTravelProcessId = 
            infoHeaderRow.getOaOutseaTravelProcessId();
        if (oaAboardTravelProcessId == null) {
            throw new OAException("请先选择出国申请！");
        }
        CuxApExpRelatedAboardLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedAboardLineVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardLineVO1");


        CuxOAAboardTravelDetailLovVOImpl relateLineLOVVO = 
            (CuxOAAboardTravelDetailLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelDetailLovVO1");

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

        CuxOAAboardTravelDetailLovVOImpl relateLineLOVVO = 
            (CuxOAAboardTravelDetailLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelDetailLovVO1");

        CuxApExpRelatedAboardLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedAboardLineVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardLineVO1");

        CuxApExpRelatedAboardInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedAboardInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardInfoVO1");
        CuxOAAboardTravelFeeLovVOImpl relateFeeLovVO = 
            (CuxOAAboardTravelFeeLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelFeeLovVO1");
        CuxApExpRelAboardFeeLineVOImpl relateFeeEOVO = 
            (CuxApExpRelAboardFeeLineVOImpl)rootAM.findViewObject("CuxApExpRelAboardFeeLineVO1");


        CuxApExpRelatedAboardInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedAboardInfoVORowImpl)relateHeaderVO.first();

        Number relationshipId = infoHeaderRow.getRelationshipId();
        Number oaOutseaTravelProcessId = 
            infoHeaderRow.getOaOutseaTravelProcessId();
        Number travelDaysNumber = infoHeaderRow.getTravelDaysNumber();
        if ("lovSelect".equals(pageContext.getParameter(EVENT_PARAM))) {
            int fetchedRowCount = relateLineLOVVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator selectIter = 
                    relateLineEOVO.createRowSetIterator("selectIter");
                selectIter.setRangeStart(0);
                selectIter.setRangeSize(fetchedRowCount);
                CuxOAAboardTravelDetailLovVORowImpl relateLineLovRow = null;
                String whereClause = "";
                for (int i = 0; i <= fetchedRowCount - 1; i++) {
                    relateLineLovRow = 
                            (CuxOAAboardTravelDetailLovVORowImpl)relateLineLOVVO.getRowAtRangeIndex(i);

                    String selectedFlag = 
                        (String)relateLineLovRow.getAttribute("lovSelectVoAttr");

                    if ("Y".equals(selectedFlag)) {
                        Number maxLineNumber = null;
                        maxLineNumber = relateLineEOVO.getMaxLineNumber();
                        relateLineLovRow.setLineNun(maxLineNumber);
                        relateLineLovRow.setRelatedAboardLineId(rootAM.getSequenceValue("CUX_AP_EXP_RELATED_OA_LINE_S"));
                        whereClause = 
                                whereClause + relateLineLovRow.getEmpId().intValue() + 
                                ",";
                        maxLineNumber = maxLineNumber.add(1);
                    }
                }
                selectIter.closeRowSetIterator();


                /****************自动创建费用行*************************************/
                relateFeeLovVO.setWhereClauseParams(null);
                relateFeeLovVO.setWhereClauseParams(new Object[] { oaOutseaTravelProcessId });
                if (!"".equals(whereClause)) {
                    whereClause = 
                            whereClause.substring(0, whereClause.length() - 1);
                    relateFeeLovVO.setWhereClause(" emp_id in (" + 
                                                  whereClause + " )");

                } else {
                    relateFeeLovVO.setWhereClause(" emp_id in (" + -1 + " )");
                }
                relateFeeLovVO.executeQuery();

                int fetchedRowCount1 = relateFeeLovVO.getRowCount();
                if (fetchedRowCount1 > 0) {
                    RowSetIterator selectIter1 = 
                        relateFeeLovVO.createRowSetIterator("selectIter1");
                    selectIter1.setRangeStart(0);
                    selectIter1.setRangeSize(fetchedRowCount1);
                    CuxOAAboardTravelFeeLovVORowImpl relateFeeLovVORow = null;
                    for (int i = fetchedRowCount1 - 1; i >= 0; i--) {
                        relateFeeLovVORow = 
                                (CuxOAAboardTravelFeeLovVORowImpl)selectIter1.getRowAtRangeIndex(i);
                        CuxApExpRelAboardFeeLineVORowImpl createdRow = 
                            (CuxApExpRelAboardFeeLineVORowImpl)relateFeeEOVO.createRow();
                        createdRow.setRelatedAboardFeeLineId(rootAM.getSequenceValue("CUX_AP_EXP_REL_ABD_FEE_LINE_S"));
                        createdRow.setRelationshipId(relationshipId);

                        createdRow.setOaAbroadTravelHeaderId(relateFeeLovVORow.getOaAbroadTravelHeaderId());
                        createdRow.setOaAbroadTravelFeeLineId(relateFeeLovVORow.getOaAbroadTravelFeeLineId());
                        createdRow.setFeeType(relateFeeLovVORow.getFeeType());
                        createdRow.setPlanPerAmount(relateFeeLovVORow.getPlanPerAmount());
                        createdRow.setPlanDays(relateFeeLovVORow.getPlanDays());
                        createdRow.setCurrencyCode(relateFeeLovVORow.getCurrencyCode());
                        createdRow.setFeeSource(relateFeeLovVORow.getFeeSource());
                        createdRow.setFeeSourceRemark(relateFeeLovVORow.getFeeSourceRemark());
                        createdRow.setRate(relateFeeLovVORow.getRate());
                        createdRow.setEmpId(relateFeeLovVORow.getEmpId());
                        createdRow.setEmpName(relateFeeLovVORow.getEmpName());
                        createdRow.setEmpNumber(relateFeeLovVORow.getEmpNumber());

                        createdRow.setRealPersonCount(new Number(1));
                        if ("国外差旅-机票".equals(relateFeeLovVORow.getFeeType()) || 
                            "国外差旅-其他费用".equals(relateFeeLovVORow.getFeeType())) {
                            createdRow.setRealTravelDays(new Number(1));
                            //createdRow.setRealPerDaysReadonly(Boolean.TRUE);
                        } else if ("国外差旅-住宿费".equals(relateFeeLovVORow.getFeeType())) {
                            createdRow.setRealTravelDays(travelDaysNumber);
                            //createdRow.setRealPerDaysReadonly(Boolean.FALSE);
                        } else {
                            createdRow.setRealTravelDays(travelDaysNumber);
                            //createdRow.setRealPerDaysReadonly(Boolean.TRUE);
                        }

                        if ("国外差旅-机票".equals(relateFeeLovVORow.getFeeType()) || 
                            "国外差旅-住宿费".equals(relateFeeLovVORow.getFeeType()) || 
                            "国外差旅-其他费用".equals(relateFeeLovVORow.getFeeType())) {
                            createdRow.setRealPerAmountReadonly(Boolean.FALSE);
                        } else {
                            createdRow.setRealPerAmountReadonly(Boolean.TRUE);
                            createdRow.setRealPerAmount(relateFeeLovVORow.getPlanPerAmount());
                        }


                        relateFeeEOVO.insertRowAtRangeIndex(0, createdRow);

                    }
                    selectIter1.closeRowSetIterator();
                }
                /****************自动创建费用行*************************************/


            }
        }
    }

}
