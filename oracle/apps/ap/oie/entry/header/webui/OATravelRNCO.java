/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.ap.oie.entry.header.webui;

import cux.oracle.apps.ap.oie.entry.entity.ToUpdateLineInfoformation;
import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOATravelerEmpLovVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpAccomFeeVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpAccomFeeVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpOtherFeeVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpOtherFeeVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVOImpl;


import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVORowImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaLineVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaLineVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTotalFeeVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTrafficReimbVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTrafficReimbVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTravelSubsidyVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTravelSubsidyVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOATravelDetailLovVOImpl;


import cux.oracle.apps.ap.oie.entry.header.server.CuxWebExpParamtersVOImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxWebExpParamtersVORowImpl;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


import oracle.apps.ap.oie.server.ExpenseReportHeadersVOImpl;
import oracle.apps.ap.oie.server.ExpenseReportHeadersVORowImpl;
import oracle.apps.ap.oie.server.ReceiptBasedLinesVOImpl;
import oracle.apps.ap.oie.server.ReceiptBasedLinesVORowImpl;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class OATravelRNCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");


    public static List<ToUpdateLineInfoformation> upList = null;

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAApplicationModule rootAM = pageContext.getRootApplicationModule();

        OAViewObject headerVO = 
            (OAViewObject)rootAM.findViewObject("ExpenseReportHeadersVO");
        OARow headerRow = (OARow)headerVO.getCurrentRow();
        if (headerRow == null) {
            headerRow = (OARow)headerVO.first();
        }
        Number reportHeaderId = 
            (Number)headerRow.getAttribute("ReportHeaderId");
        /***************0.初始化oa关联头****************/
        CuxApExpRelatedOaInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedOaInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedOaInfoVO1");
        if (relateHeaderVO == null) {
            relateHeaderVO = 
                    (CuxApExpRelatedOaInfoVOImpl)rootAM.createViewObject("CuxApExpRelatedOaInfoVO1", 
                                                                         "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVO");
        }

        /***************1.初始化oa关联行****************/
        CuxOATravelDetailLovVOImpl relateLineLOVVO = 
            (CuxOATravelDetailLovVOImpl)rootAM.findViewObject("CuxOATravelDetailLovVO1");
        if (relateLineLOVVO == null) {
            relateLineLOVVO = 
                    (CuxOATravelDetailLovVOImpl)rootAM.createViewObject("CuxOATravelDetailLovVO1", 
                                                                        "cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOATravelDetailLovVO");
        }
        /***************end of 1.初始化oa关联行****************/

        /***************1.2 初始化oa员工lov****************/
        CuxOATravelerEmpLovVOImpl relateLineEmpLOVVO = 
            (CuxOATravelerEmpLovVOImpl)rootAM.findViewObject("CuxOATravelerEmpLovVO1");
        if (relateLineEmpLOVVO == null) {
            relateLineEmpLOVVO = 
                    (CuxOATravelerEmpLovVOImpl)rootAM.createViewObject("CuxOATravelerEmpLovVO1", 
                                                                       "cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOATravelerEmpLovVO");
        }
        /***************end of 1.2初始化oa员工lov****************/

        /***************1.5 初始化oa关联明细行****************/
        CuxApExpRelatedOaLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedOaLineVOImpl)rootAM.findViewObject("CuxApExpRelatedOaLineVO1");
        if (relateLineEOVO == null) {
            relateLineEOVO = 
                    (CuxApExpRelatedOaLineVOImpl)rootAM.createViewObject("CuxApExpRelatedOaLineVO1", 
                                                                         "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaLineVO");
        }
        /***************end of 1.5 初始化oa关联明细行****************/

        /***************2.初始化出差补贴行****************/
        CuxApExpTravelSubsidyVOImpl travelSubsidyVO = 
            (CuxApExpTravelSubsidyVOImpl)rootAM.findViewObject("CuxApExpTravelSubsidyVO1");
        if (travelSubsidyVO == null) {
            travelSubsidyVO = 
                    (CuxApExpTravelSubsidyVOImpl)rootAM.createViewObject("CuxApExpTravelSubsidyVO1", 
                                                                         "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTravelSubsidyVO");
        }
        /***************end of 2.初始化出差补贴行****************/

        /***************3.初始化交通费报销****************/
        CuxApExpTrafficReimbVOImpl trafficReimbVO = 
            (CuxApExpTrafficReimbVOImpl)rootAM.findViewObject("CuxApExpTrafficReimbVO1");
        if (trafficReimbVO == null) {
            trafficReimbVO = 
                    (CuxApExpTrafficReimbVOImpl)rootAM.createViewObject("CuxApExpTrafficReimbVO1", 
                                                                        "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTrafficReimbVO");
        }
        /***************end of 3.初始化交通费报销****************/

        /***************4.初始化住宿费报销****************/
        CuxApExpAccomFeeVOImpl accomFeeVO = 
            (CuxApExpAccomFeeVOImpl)rootAM.findViewObject("CuxApExpAccomFeeVO1");
        if (accomFeeVO == null) {
            accomFeeVO = 
                    (CuxApExpAccomFeeVOImpl)rootAM.createViewObject("CuxApExpAccomFeeVO1", 
                                                                    "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpAccomFeeVO");
        }
        /***************end of 4.初始化住宿费报销****************/

        /***************5.初始化其他报销****************/
        CuxApExpOtherFeeVOImpl otherFeeVO = 
            (CuxApExpOtherFeeVOImpl)rootAM.findViewObject("CuxApExpOtherFeeVO1");
        if (otherFeeVO == null) {
            otherFeeVO = 
                    (CuxApExpOtherFeeVOImpl)rootAM.createViewObject("CuxApExpOtherFeeVO1", 
                                                                    "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpOtherFeeVO");
        }
        /***************end of 5.初始化其他报销****************/

        /***************6.初始化统计金额****************/
        CuxApExpTotalFeeVOImpl totalVO = 
            (CuxApExpTotalFeeVOImpl)rootAM.findViewObject("CuxApExpTotalFeeVO1");
        if (totalVO == null) {
            totalVO = 
                    (CuxApExpTotalFeeVOImpl)rootAM.createViewObject("CuxApExpTotalFeeVO1", 
                                                                    "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTotalFeeVO");
        }
        /***************end of 6.初始化统计金额***************/

        if (!relateHeaderVO.isExecuted()) {
            relateHeaderVO.setWhereClause(null);
            relateHeaderVO.setWhereClauseParams(null);
            relateHeaderVO.setWhereClause("report_header_id = :1");
            relateHeaderVO.setWhereClauseParams(new Object[] { reportHeaderId });
            relateHeaderVO.executeQuery();
        }

        CuxApExpRelatedOaInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedOaInfoVORowImpl)relateHeaderVO.first();
        if (infoHeaderRow == null) {
            infoHeaderRow = 
                    (CuxApExpRelatedOaInfoVORowImpl)relateHeaderVO.createRow();
            Number relatationshipId = 
                rootAM.getSequenceValue("cux_ap_exp_related_oa_info_s");
            infoHeaderRow.setRelationshipId(relatationshipId);
            infoHeaderRow.setReportHeaderId(reportHeaderId);
            relateHeaderVO.insertRow(infoHeaderRow);

            relateLineEOVO.setWhereClauseParams(new Object[] { new Number(-9999) });
            relateLineEOVO.executeQuery();

            travelSubsidyVO.setWhereClause("1 = 2");
            travelSubsidyVO.executeQuery();

            trafficReimbVO.setWhereClause("1 = 2");
            trafficReimbVO.executeQuery();

            accomFeeVO.setWhereClause("1 = 2");
            accomFeeVO.executeQuery();

            otherFeeVO.setWhereClause("1 = 2");
            otherFeeVO.executeQuery();
        } else {

            Number relationshipId = infoHeaderRow.getRelationshipId();
            Number oaTravelProcessId = infoHeaderRow.getOaTravelProcessId();

            relateLineLOVVO.setWhereClauseParams(null);
            relateLineLOVVO.setWhereClauseParams(new Object[] { relationshipId, 
                                                                oaTravelProcessId });

            relateLineEmpLOVVO.setWhereClauseParams(new Object[] { oaTravelProcessId });

            relateLineEOVO.setWhereClauseParams(new Object[] { relationshipId });
            relateLineEOVO.executeQuery();

            travelSubsidyVO.setWhereClause(null);
            travelSubsidyVO.setWhereClauseParams(null);
            travelSubsidyVO.setWhereClause("relationship_id = :1");
            travelSubsidyVO.setWhereClauseParams(new Object[] { relationshipId });
            travelSubsidyVO.executeQuery();


            trafficReimbVO.setWhereClause(null);
            trafficReimbVO.setWhereClauseParams(null);
            trafficReimbVO.setWhereClause("relationship_id = :1");
            trafficReimbVO.setWhereClauseParams(new Object[] { relationshipId });
            trafficReimbVO.executeQuery();

            accomFeeVO.setWhereClause(null);
            accomFeeVO.setWhereClauseParams(null);
            accomFeeVO.setWhereClause("relationship_id = :1");
            accomFeeVO.setWhereClauseParams(new Object[] { relationshipId });
            accomFeeVO.executeQuery();

            otherFeeVO.setWhereClause(null);
            otherFeeVO.setWhereClauseParams(null);
            otherFeeVO.setWhereClause("relationship_id = :1");
            otherFeeVO.setWhereClauseParams(new Object[] { relationshipId });
            otherFeeVO.executeQuery();


            totalVO.setWhereClause(null);
            totalVO.setWhereClauseParams(null);
            totalVO.setWhereClause("relationship_id = :1");
            totalVO.setWhereClauseParams(new Object[] { relationshipId });
            totalVO.executeQuery();

        }
        /***************end of 0.初始化oa关联头****************/

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
        String eventParam = pageContext.getParameter(this.EVENT_PARAM);
        String souceParam = pageContext.getParameter("source");
        if (souceParam == null) {
            souceParam = "THIS#IS#NULL#VALUE";
        }

        OAApplicationModule rootAM = pageContext.getRootApplicationModule();
        CuxApExpRelatedOaInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedOaInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedOaInfoVO1");

        CuxOATravelDetailLovVOImpl relateLineLOVVO = 
            (CuxOATravelDetailLovVOImpl)rootAM.findViewObject("CuxOATravelDetailLovVO1");
        CuxOATravelerEmpLovVOImpl relateLineEmpLOVVO = 
            (CuxOATravelerEmpLovVOImpl)rootAM.findViewObject("CuxOATravelerEmpLovVO1");

        CuxApExpRelatedOaLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedOaLineVOImpl)rootAM.findViewObject("CuxApExpRelatedOaLineVO1");

        CuxApExpTotalFeeVOImpl totalVO = 
            (CuxApExpTotalFeeVOImpl)rootAM.findViewObject("CuxApExpTotalFeeVO1");

        CuxApExpTravelSubsidyVOImpl travelSubsidyVO = 
            (CuxApExpTravelSubsidyVOImpl)rootAM.findViewObject("CuxApExpTravelSubsidyVO1");
        CuxApExpTrafficReimbVOImpl trafficReimbVO = 
            (CuxApExpTrafficReimbVOImpl)rootAM.findViewObject("CuxApExpTrafficReimbVO1");
        CuxApExpAccomFeeVOImpl accomFeeVO = 
            (CuxApExpAccomFeeVOImpl)rootAM.findViewObject("CuxApExpAccomFeeVO1");
        CuxApExpOtherFeeVOImpl otherFeeVO = 
            (CuxApExpOtherFeeVOImpl)rootAM.findViewObject("CuxApExpOtherFeeVO1");

        CuxApExpRelatedOaInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedOaInfoVORowImpl)relateHeaderVO.first();

        Number oaTravelProcessId = infoHeaderRow.getOaTravelProcessId();
        Number relationshipId = infoHeaderRow.getRelationshipId();
        /*****************************************处理选择出差申请**&*********************************/
        if ("TravelHeader".equals(pageContext.getLovInputSourceId())) {
            if ("lovPrepare".equals(pageContext.getParameter(EVENT_PARAM))) {
                relateLineLOVVO.setWhereClauseParams(null);
                relateLineLOVVO.setWhereClauseParams(new Object[] { relationshipId, 
                                                                    new Number(-9999) });
                //relateLineLOVVO.executeQuery();
                relateLineEmpLOVVO.setWhereClauseParams(null);
                relateLineEmpLOVVO.setWhereClauseParams(new Object[] { new Number(-9999) });

                totalVO.setWhereClause(null);
                totalVO.setWhereClauseParams(null);
                totalVO.setWhereClause("1 = 2");
                totalVO.executeQuery();

                /***********************0 人员行**********************************/
                int fetchedRowCount = relateLineEOVO.getRowCount();
                if (fetchedRowCount > 0) {
                    RowSetIterator deleteIter = 
                        relateLineEOVO.createRowSetIterator("deleteIter");
                    deleteIter.setRangeStart(0);
                    deleteIter.setRangeSize(fetchedRowCount);
                    CuxApExpRelatedOaLineVORowImpl relateLineEORow = null;
                    for (int i = fetchedRowCount - 1; i >= 0; i--) {
                        relateLineEORow = 
                                (CuxApExpRelatedOaLineVORowImpl)deleteIter.getRowAtRangeIndex(i);
                        relateLineEORow.remove();
                    }
                    deleteIter.closeRowSetIterator();
                }
                /***********************end of 0 人员行**********************************/


                /***********************1 清空补贴行**********************************/
                fetchedRowCount = travelSubsidyVO.getRowCount();
                if (fetchedRowCount > 0) {
                    RowSetIterator deleteIter = 
                        travelSubsidyVO.createRowSetIterator("deleteIter");
                    deleteIter.setRangeStart(0);
                    deleteIter.setRangeSize(fetchedRowCount);
                    CuxApExpTravelSubsidyVORowImpl travelSubsidyRow = null;
                    for (int i = fetchedRowCount - 1; i >= 0; i--) {
                        travelSubsidyRow = 
                                (CuxApExpTravelSubsidyVORowImpl)deleteIter.getRowAtRangeIndex(i);
                        travelSubsidyRow.remove();
                    }
                    deleteIter.closeRowSetIterator();
                }
                /***********************end of 1 清空补贴行**********************************/

                /***********************2.清空交通费报销**********************************/
                fetchedRowCount = trafficReimbVO.getRowCount();
                if (fetchedRowCount > 0) {
                    RowSetIterator deleteIter = 
                        trafficReimbVO.createRowSetIterator("deleteIter");
                    deleteIter.setRangeStart(0);
                    deleteIter.setRangeSize(fetchedRowCount);
                    CuxApExpTrafficReimbVORowImpl trafficReimbRow = null;
                    for (int i = fetchedRowCount - 1; i >= 0; i--) {
                        trafficReimbRow = 
                                (CuxApExpTrafficReimbVORowImpl)deleteIter.getRowAtRangeIndex(i);
                        trafficReimbRow.remove();
                    }
                    deleteIter.closeRowSetIterator();
                }
                /***********************end of 2 清空交通费报销**********************************/


                /***********************3.清空住宿费报销**********************************/
                fetchedRowCount = accomFeeVO.getRowCount();
                if (fetchedRowCount > 0) {
                    RowSetIterator deleteIter = 
                        accomFeeVO.createRowSetIterator("deleteIter");
                    deleteIter.setRangeStart(0);
                    deleteIter.setRangeSize(fetchedRowCount);
                    CuxApExpAccomFeeVORowImpl accomFeeRow = null;
                    for (int i = fetchedRowCount - 1; i >= 0; i--) {
                        accomFeeRow = 
                                (CuxApExpAccomFeeVORowImpl)deleteIter.getRowAtRangeIndex(i);
                        accomFeeRow.remove();
                    }
                    deleteIter.closeRowSetIterator();
                }
                /***********************end of 3 清空住宿费报销**********************************/

                /***********************4.清空其他报销**********************************/
                fetchedRowCount = otherFeeVO.getRowCount();
                if (fetchedRowCount > 0) {
                    RowSetIterator deleteIter = 
                        otherFeeVO.createRowSetIterator("deleteIter");
                    deleteIter.setRangeStart(0);
                    deleteIter.setRangeSize(fetchedRowCount);
                    CuxApExpOtherFeeVORowImpl otherFeeRow = null;
                    for (int i = fetchedRowCount - 1; i >= 0; i--) {
                        otherFeeRow = 
                                (CuxApExpOtherFeeVORowImpl)deleteIter.getRowAtRangeIndex(i);
                        otherFeeRow.remove();
                    }
                    deleteIter.closeRowSetIterator();
                }
                /***********************end of 4 清空其他报销**********************************/


            } else if ("lovUpdate".equals(pageContext.getParameter(EVENT_PARAM)) || 
                       "lovValidate".equals(pageContext.getParameter(EVENT_PARAM))) {

                relateLineLOVVO.setWhereClauseParams(null);
                relateLineLOVVO.setWhereClauseParams(new Object[] { relationshipId, 
                                                                    oaTravelProcessId });
                relateLineLOVVO.executeQuery();

                relateLineEmpLOVVO.setWhereClauseParams(null);
                relateLineEmpLOVVO.setWhereClauseParams(new Object[] { oaTravelProcessId });


                totalVO.setWhereClause(null);
                totalVO.setWhereClauseParams(null);
                totalVO.setWhereClause("relationship_id = :1");
                totalVO.setWhereClauseParams(new Object[] { relationshipId });
                totalVO.executeQuery();


            }
        }
        /*****************************************其他费用增加一行***********************************/
        else if (pageContext.getParameter("OtherTableAddOneRow") != null) {
            CuxApExpOtherFeeVORowImpl autoCreateOtherFeeRow = 
                (CuxApExpOtherFeeVORowImpl)otherFeeVO.createRow();
            autoCreateOtherFeeRow.setRelationshipId(relationshipId);
            autoCreateOtherFeeRow.setOtherFeeLineId(rootAM.getSequenceValue("CUX_AP_EXP_OTHER_FEE_S"));
            autoCreateOtherFeeRow.setLineNumber(otherFeeVO.getMaxLineNumber());
            autoCreateOtherFeeRow.setFeeType("国内差旅-其他差旅费");
            autoCreateOtherFeeRow.setAboardFlag("N");
            autoCreateOtherFeeRow.setCurrencyCode("CNY");
            autoCreateOtherFeeRow.setRate(new Number(1));
            //            CuxOATravelDetailLovRNCO.insertRowAtLastIndex(new Number(10),otherFeeVO,autoCreateOtherFeeRow); 
            otherFeeVO.insertRowAtRangeIndex(0, autoCreateOtherFeeRow);
        }
        /*****************************************出差补贴增加一行***********************************/
        else if (pageContext.getParameter("TravelSubsidyAddOneRow") != null) {
            CuxApExpTravelSubsidyVORowImpl autoCreateTravelSubsidyRow = 
                (CuxApExpTravelSubsidyVORowImpl)travelSubsidyVO.createRow();
            autoCreateTravelSubsidyRow.setRelationshipId(relationshipId);
            autoCreateTravelSubsidyRow.setSubsidyLineId(rootAM.getSequenceValue("CUX_AP_EXP_TRAVEL_SUBSIDY_S"));
            autoCreateTravelSubsidyRow.setFeeType("国内差旅-补助费");

            autoCreateTravelSubsidyRow.setLineNumber(travelSubsidyVO.getMaxLineNumber());
            travelSubsidyVO.insertRowAtRangeIndex(0, 
                                                  autoCreateTravelSubsidyRow);
        }
        /*****************************************交通费增加一行***********************************/
        else if (pageContext.getParameter("AccomFeeAddOneRow") != null) {
            CuxApExpAccomFeeVORowImpl autoCreateAccomFeeRow = 
                (CuxApExpAccomFeeVORowImpl)accomFeeVO.createRow();
            autoCreateAccomFeeRow.setRelationshipId(relationshipId);
            autoCreateAccomFeeRow.setAccomFeeLineId(rootAM.getSequenceValue("CUX_AP_EXP_ACCOM_FEE_S"));
            autoCreateAccomFeeRow.setFeeType("国内差旅-住宿费");
            autoCreateAccomFeeRow.setLineNumber(accomFeeVO.getMaxLineNumber());
            accomFeeVO.insertRowAtRangeIndex(0, autoCreateAccomFeeRow);
        }
        /*****************************************住宿费报销***********************************/
        else if (pageContext.getParameter("TrafficTableAddOneRow") != null) {
            CuxApExpTrafficReimbVORowImpl autoCreateTrafficReimbRow = 
                (CuxApExpTrafficReimbVORowImpl)trafficReimbVO.createRow();
            autoCreateTrafficReimbRow.setRelationshipId(relationshipId);
            autoCreateTrafficReimbRow.setTrafficReimbLineId(rootAM.getSequenceValue("CUX_AP_EXP_TRAFFIC_REIMB_S"));
            autoCreateTrafficReimbRow.setLineNumber(trafficReimbVO.getMaxLineNumber());
            trafficReimbVO.insertRowAtRangeIndex(0, autoCreateTrafficReimbRow);
        }
        /*****************************************交通费、其他费用删除一行***********************************/
        else if ("DeleteCurrentRow".equals(eventParam)) {
            Row r = 
                rootAM.findRowByRef(pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE));
            r.remove();
        }
        /*****************************************报销人删除一行***********************************/
        else if ("DeleteRelatedOALine".equals(eventParam)) {
            String empIdStr = pageContext.getParameter("EmpId");
            Number empIdNum = new Number(-1);
            try {
                empIdNum = new Number(empIdStr);
            } catch (SQLException e) {
            }


            /***********************1 删除补贴行**********************************/
            int fetchedRowCount = travelSubsidyVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator deleteIter = 
                    travelSubsidyVO.createRowSetIterator("deleteIter");
                deleteIter.setRangeStart(0);
                deleteIter.setRangeSize(fetchedRowCount);
                CuxApExpTravelSubsidyVORowImpl travelSubsidyRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    travelSubsidyRow = 
                            (CuxApExpTravelSubsidyVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    if (empIdNum.equals(travelSubsidyRow.getEmpId())) {
                        travelSubsidyRow.remove();
                    }
                }
                deleteIter.closeRowSetIterator();
            }
            /***********************end of 1 删除补贴行**********************************/

            /***********************2.删除交通费报销**********************************/
            fetchedRowCount = trafficReimbVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator deleteIter = 
                    trafficReimbVO.createRowSetIterator("deleteIter");
                deleteIter.setRangeStart(0);
                deleteIter.setRangeSize(fetchedRowCount);
                CuxApExpTrafficReimbVORowImpl trafficReimbRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    trafficReimbRow = 
                            (CuxApExpTrafficReimbVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    if (empIdNum.equals(trafficReimbRow.getEmpId())) {
                        trafficReimbRow.remove();
                    }
                }
                deleteIter.closeRowSetIterator();
            }
            /***********************end of 2 删除交通费报销**********************************/


            /***********************3.删除住宿费报销**********************************/
            fetchedRowCount = accomFeeVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator deleteIter = 
                    accomFeeVO.createRowSetIterator("deleteIter");
                deleteIter.setRangeStart(0);
                deleteIter.setRangeSize(fetchedRowCount);
                CuxApExpAccomFeeVORowImpl accomFeeRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    accomFeeRow = 
                            (CuxApExpAccomFeeVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    if (empIdNum.equals(accomFeeRow.getEmpId())) {
                        accomFeeRow.remove();
                    }
                }
                deleteIter.closeRowSetIterator();
            }
            /***********************end of 3 删除住宿费报销**********************************/

            /***********************4.删除其他报销**********************************/
            fetchedRowCount = otherFeeVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator deleteIter = 
                    otherFeeVO.createRowSetIterator("deleteIter");
                deleteIter.setRangeStart(0);
                deleteIter.setRangeSize(fetchedRowCount);
                CuxApExpOtherFeeVORowImpl otherFeeRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    otherFeeRow = 
                            (CuxApExpOtherFeeVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    if (empIdNum.equals(otherFeeRow.getEmpId())) {
                        otherFeeRow.remove();
                    }
                }
                deleteIter.closeRowSetIterator();
            }
            /***********************end of 4 删除其他报销**********************************/

            /***********************0.删除人员行**********************************/
            fetchedRowCount = relateLineEOVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator deleteIter = 
                    relateLineEOVO.createRowSetIterator("deleteIter");
                deleteIter.setRangeStart(0);
                deleteIter.setRangeSize(fetchedRowCount);
                CuxApExpRelatedOaLineVORowImpl relateLineEORow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    relateLineEORow = 
                            (CuxApExpRelatedOaLineVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    if (empIdNum.equals(relateLineEORow.getEmpId())) {
                        relateLineEORow.remove();
                    }
                }
                deleteIter.closeRowSetIterator();
            }
            /***********************end of  0.删除人员行**********************************/

        }
        //用于处理点击保存按钮时，需要清除掉不是国外出差的数据
        //
        if (pageContext.getParameter("OIESave") != null) {
            this.clearAllOverData(pageContext, webBean);
        }
        //点击下一步时
        //1.首先先提示本页面是否存在未保存的修改，如果存在提示保存，并且提示用户进行检查
        //2.进行下一个页面数据的创建
        else if ("OIENavBar".startsWith(souceParam) && 
                 "goto".equals(eventParam)) {
            this.checkIsDirty(pageContext, webBean);
            this.populateTheNextPageVoRows(pageContext, webBean);
        }
    }

    /**
     * 首先先提示本页面是否存在未保存的修改，如果存在提示保存，并且提示用户进行检查
     * @param pageContext
     * @param webBean
     */
    private void checkIsDirty(OAPageContext pageContext, OAWebBean webBean) {
        OAApplicationModuleImpl rootAM = 
            (OAApplicationModuleImpl)pageContext.getRootApplicationModule();
        CuxApExpRelatedOaInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedOaInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedOaInfoVO1");

        CuxApExpRelatedOaLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedOaLineVOImpl)rootAM.findViewObject("CuxApExpRelatedOaLineVO1");

        CuxApExpTravelSubsidyVOImpl travelSubsidyVO = 
            (CuxApExpTravelSubsidyVOImpl)rootAM.findViewObject("CuxApExpTravelSubsidyVO1");
        CuxApExpTrafficReimbVOImpl trafficReimbVO = 
            (CuxApExpTrafficReimbVOImpl)rootAM.findViewObject("CuxApExpTrafficReimbVO1");
        CuxApExpAccomFeeVOImpl accomFeeVO = 
            (CuxApExpAccomFeeVOImpl)rootAM.findViewObject("CuxApExpAccomFeeVO1");
        CuxApExpOtherFeeVOImpl otherFeeVO = 
            (CuxApExpOtherFeeVOImpl)rootAM.findViewObject("CuxApExpOtherFeeVO1");

        if (relateHeaderVO.isDirty() || relateLineEOVO.isDirty() || 
            travelSubsidyVO.isDirty() || otherFeeVO.isDirty() || 
            trafficReimbVO.isDirty() || accomFeeVO.isDirty()) {
            throw new OAException("请点击保存按钮，并且检查确认明细数据后，再点击进入下一步！");
        }

    }

    /**
     * 进行下一个页面数据的创建
     * @param pageContext
     * @param webBean
     */
    private void populateTheNextPageVoRows(OAPageContext pageContext, 
                                           OAWebBean webBean) {
        OAApplicationModuleImpl rootAM = 
            (OAApplicationModuleImpl)pageContext.getRootApplicationModule();
        /*****************************客户化vo***************************************/
        CuxApExpTravelSubsidyVOImpl travelSubsidyVO = 
            (CuxApExpTravelSubsidyVOImpl)rootAM.findViewObject("CuxApExpTravelSubsidyVO1");
        CuxApExpTrafficReimbVOImpl trafficReimbVO = 
            (CuxApExpTrafficReimbVOImpl)rootAM.findViewObject("CuxApExpTrafficReimbVO1");
        CuxApExpAccomFeeVOImpl accomFeeVO = 
            (CuxApExpAccomFeeVOImpl)rootAM.findViewObject("CuxApExpAccomFeeVO1");
        CuxApExpOtherFeeVOImpl otherFeeVO = 
            (CuxApExpOtherFeeVOImpl)rootAM.findViewObject("CuxApExpOtherFeeVO1");
        /*****************************客户化vo***************************************/

        ReceiptBasedLinesVOImpl lineVO = 
            (ReceiptBasedLinesVOImpl)rootAM.findViewObject("CashAndOtherLinesVO");
        ExpenseReportHeadersVOImpl vo = 
            (ExpenseReportHeadersVOImpl)rootAM.findViewObject("ExpenseReportHeadersVO");
        ExpenseReportHeadersVORowImpl row = 
            (ExpenseReportHeadersVORowImpl)vo.getCurrentRow();
        if (row == null) {
            row = (ExpenseReportHeadersVORowImpl)vo.first();
        }

        lineVO.setWhereClauseParam(0, row.getAttribute("ReportHeaderId"));
        lineVO.executeQuery();
        boolean needToCreateFinRow = true;
        lineVO.removeEmptyRows();
        int fetchedRowCount = lineVO.getRowCount();
        if (fetchedRowCount > 0) {
            while (lineVO.hasNext()) {
                Row lineRow = lineVO.next();
                lineRow.remove();
            }
        }
        /* --commnet by Wei Yi at 20171128 每次都重新生成数据
        if (fetchedRowCount > 0) {
            needToCreateFinRow = false;
        }
        */
        if (needToCreateFinRow) {
            CuxWebExpParamtersVOImpl paramVo = 
                (CuxWebExpParamtersVOImpl)rootAM.findViewObject("ParameterVO_forBT");
            if (paramVo == null) {
                paramVo = 
                        (CuxWebExpParamtersVOImpl)rootAM.createViewObject("ParameterVO_forBT", 
                                                                          "cux.oracle.apps.ap.oie.entry.header.server.CuxWebExpParamtersVO");
            }


            /****************自动计算报销行开始**********************/

            OATravelRNCO.upList = new ArrayList<ToUpdateLineInfoformation>();

            /***********************1 创建标准补贴行**********************************/
            fetchedRowCount = travelSubsidyVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator autoLoopIter = 
                    travelSubsidyVO.createRowSetIterator("autoLoopIter");
                autoLoopIter.setRangeStart(0);
                autoLoopIter.setRangeSize(fetchedRowCount);
                CuxApExpTravelSubsidyVORowImpl travelSubsidyRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    travelSubsidyRow = 
                            (CuxApExpTravelSubsidyVORowImpl)autoLoopIter.getRowAtRangeIndex(i);
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              travelSubsidyRow.getFeeType(), 
                                              travelSubsidyRow.getEmpName() + 
                                              "(" + 
                                              travelSubsidyRow.getEmployeeNumber() + 
                                              ")" + "的" + 
                                              travelSubsidyRow.getFeeType(), 
                                              travelSubsidyRow.getTravelSubsidyTotal(), 
                                              new Number(1), "CNY", 
                                              travelSubsidyRow.getStartDate(), 
                                              travelSubsidyRow.getEndDate(), 
                                              travelSubsidyRow.getEmployeeNumber());
                }
                autoLoopIter.closeRowSetIterator();
            }
            /***********************end of 1 创建标准补贴行**********************************/
            /***********************2.创建标准交通费报销**********************************/
            fetchedRowCount = trafficReimbVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator autoLoopIter = 
                    trafficReimbVO.createRowSetIterator("autoLoopIter");
                autoLoopIter.setRangeStart(0);
                autoLoopIter.setRangeSize(fetchedRowCount);
                CuxApExpTrafficReimbVORowImpl trafficReimbRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    trafficReimbRow = 
                            (CuxApExpTrafficReimbVORowImpl)autoLoopIter.getRowAtRangeIndex(i);
                    /**************2.1创建国内差旅-飞机等费用****************/
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              trafficReimbRow.getFeeType(), 
                                              trafficReimbRow.getEmpName() + 
                                              "(" + 
                                              trafficReimbRow.getEmployeeNumber() + 
                                              ")的" + 
                                              trafficReimbRow.getFeeType(), 
                                              trafficReimbRow.getTrafficFee(), 
                                              new Number(1), "CNY", 
                                              trafficReimbRow.getStartDate(), 
                                              trafficReimbRow.getEndDate(), 
                                              trafficReimbRow.getEmployeeNumber());
                    /**************2.1创建国内差旅-飞机等费用****************/
                    /**************2.2 创建国内差旅-补助费******************/
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              "国内差旅-补助费", 
                                              trafficReimbRow.getEmpName() + 
                                              "(" + 
                                              trafficReimbRow.getEmployeeNumber() + 
                                              ")的" + "交通补助", 
                                              trafficReimbRow.getLevelSubsidy().add(trafficReimbRow.getOvertimeSubsidy()), 
                                              new Number(1), "CNY", 
                                              trafficReimbRow.getStartDate(), 
                                              trafficReimbRow.getEndDate(), 
                                              trafficReimbRow.getEmployeeNumber());
                    /**************2.2 创建国内差旅-补助费*****************/
                    /**************2.2 创建国内差旅-市内交通费******************/
                    Number trafficFeePublicInjing = 
                        trafficReimbRow.getPublicTrafficFeeInJing() == null ? 
                        new Number(0) : 
                        trafficReimbRow.getPublicTrafficFeeInJing();
                    Number trafficFeeTaxiInjing = 
                        trafficReimbRow.getTaxiFeeInJing() == null ? 
                        new Number(0) : trafficReimbRow.getTaxiFeeInJing();
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              "国内差旅-市内交通费", 
                                              trafficReimbRow.getEmpName() + 
                                              "(" + 
                                              trafficReimbRow.getEmployeeNumber() + 
                                              ")的" + "国内差旅-市内交通费", 
                                              trafficFeePublicInjing.add(trafficFeeTaxiInjing), 
                                              new Number(1), "CNY", 
                                              trafficReimbRow.getStartDate(), 
                                              trafficReimbRow.getEndDate(), 
                                              trafficReimbRow.getEmployeeNumber());
                    /**************2.2 创建国内差旅-市内交通费*****************/

                    /**************2.3 创建国内差旅-市外交通费******************/
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              "国内差旅-市外交通费", 
                                              trafficReimbRow.getEmpName() + 
                                              "(" + 
                                              trafficReimbRow.getEmployeeNumber() + 
                                              ")的" + "国内差旅-市外交通费", 
                                              trafficReimbRow.getTotalTrafficFeeOutJing(), 
                                              new Number(1), "CNY", 
                                              trafficReimbRow.getStartDate(), 
                                              trafficReimbRow.getEndDate(), 
                                              trafficReimbRow.getEmployeeNumber());
                    /**************2.3 创建国内差旅-市外交通费*****************/

                    /**************2.4 创建国内差旅-其他费用(订退票费)******************/
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              "国内差旅-其他差旅费", 
                                              trafficReimbRow.getEmpName() + 
                                              "(" + 
                                              trafficReimbRow.getEmployeeNumber() + 
                                              ")的" + "国内差旅-其他差旅费", 
                                              trafficReimbRow.getBuyTicketFee(), 
                                              new Number(1), "CNY", 
                                              trafficReimbRow.getStartDate(), 
                                              trafficReimbRow.getEndDate(), 
                                              trafficReimbRow.getEmployeeNumber());
                    /**************2.4 创建国内差旅-其他费用(订退票费)*****************/

                }
                autoLoopIter.closeRowSetIterator();
            }
            /***********************end of 2 创建标准交通费报销**********************************/
            /***********************3.创建标准住宿费报销**********************************/
            fetchedRowCount = accomFeeVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator autoLoopIter = 
                    accomFeeVO.createRowSetIterator("autoLoopIter");
                autoLoopIter.setRangeStart(0);
                autoLoopIter.setRangeSize(fetchedRowCount);
                CuxApExpAccomFeeVORowImpl accomFeeRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    accomFeeRow = 
                            (CuxApExpAccomFeeVORowImpl)autoLoopIter.getRowAtRangeIndex(i);
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              accomFeeRow.getFeeType(), 
                                              accomFeeRow.getEmpName() + "(" + 
                                              accomFeeRow.getEmployeeNumber() + 
                                              ")的" + accomFeeRow.getFeeType(), 
                                              accomFeeRow.getFeePerDay(), 
                                              new Number(1), "CNY", 
                                              accomFeeRow.getStartDate(), 
                                              accomFeeRow.getEndDate(), 
                                              accomFeeRow.getEmployeeNumber());
                }
                autoLoopIter.closeRowSetIterator();
            }
            /***********************end of 3 创建标准住宿费报销**********************************/

            /***********************4.创建标准其他报销**********************************/
            fetchedRowCount = otherFeeVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator autoLoopIter = 
                    otherFeeVO.createRowSetIterator("autoLoopIter");
                autoLoopIter.setRangeStart(0);
                autoLoopIter.setRangeSize(fetchedRowCount);
                CuxApExpOtherFeeVORowImpl otherFeeRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    otherFeeRow = 
                            (CuxApExpOtherFeeVORowImpl)autoLoopIter.getRowAtRangeIndex(i);
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              otherFeeRow.getFeeType(), 
                                              otherFeeRow.getEmpName() + "(" + 
                                              otherFeeRow.getEmployeeNumber() + 
                                              ")的" + otherFeeRow.getFeeType(), 
                                              otherFeeRow.getFeeAmount(), 
                                              new Number(1), "CNY", 
                                              otherFeeRow.getFeeDate(), 
                                              otherFeeRow.getFeeDate(), 
                                              otherFeeRow.getEmployeeNumber());
                }
                autoLoopIter.closeRowSetIterator();
            }
            /***********************end of 4 创建标准其他报销**********************************/

            /****************end of 自动计算报销行开始**********************/

            Serializable[] arrayOfSerializable = 
                new Serializable[] { null, Boolean.FALSE };
            Class[] localObject3 = new Class[] { String.class, Boolean.class };
            pageContext.getRootApplicationModule().invokeMethod("saveExpenseReport", 
                                                                arrayOfSerializable, 
                                                                localObject3);
            OATravelRNCO.updateLineInfo((OAApplicationModuleImpl)pageContext.getRootApplicationModule());
            String preValue = row.getAttribute8();
            row.setAttribute8("AAA");
            pageContext.getRootApplicationModule().invokeMethod("saveExpenseReport", 
                                                                arrayOfSerializable, 
                                                                localObject3);
            row.setAttribute8(preValue);
            pageContext.getRootApplicationModule().invokeMethod("saveExpenseReport", 
                                                                arrayOfSerializable, 
                                                                localObject3);
            //            pageContext.getRootApplicationModule().getOADBTransaction().commit();

        } else {
            return;
        }


    }

    /**
     * 创建插入费用行
     * @param rootAM
     * @param lineVO
     * @param row
     * @param paramVo
     * @param webExpPromt
     * @param itemDesccription
     * @param amount
     * @param rate
     * @param currencyCode
     * @param btStartDate
     * @param btEndDate
     * @param empNumber
     */
    public static void insertLineVO(OAApplicationModuleImpl rootAM, 
                                    ReceiptBasedLinesVOImpl lineVO, 
                                    ExpenseReportHeadersVORowImpl row, 
                                    CuxWebExpParamtersVOImpl paramVo, 
                                    String webExpPromt, 
                                    String itemDesccription, Number amount, 
                                    Number rate, String currencyCode, 
                                    Date btStartDate, Date btEndDate, 
                                    String empNumber) {
        if (new Number(0).equals(amount) || amount == null) {
            return;
        }
        paramVo.setWhereClauseParams(null);
        paramVo.setWhereClauseParams(new Object[] { row.getExpenseReportId(), 
                                                    webExpPromt, 
                                                    row.getAttribute1() });
        paramVo.executeQuery();
        CuxWebExpParamtersVORowImpl paramRow = 
            (CuxWebExpParamtersVORowImpl)paramVo.first();
        if (paramRow == null) {
            throw new OAException("未配置的费用报销类型:" + webExpPromt);
        }
        ToUpdateLineInfoformation info = new ToUpdateLineInfoformation();
        ReceiptBasedLinesVORowImpl lineRow = 
            (ReceiptBasedLinesVORowImpl)lineVO.createRow();

        lineRow.setReportHeaderId(row.getReportHeaderId());

        info.setLineId(rootAM.getSequenceValue("ap_expense_report_lines_s"));
        lineRow.setReportLineId(info.getLineId());

        //lineRow.setItemDescription(itemDesccription);
        lineRow.setItemDescription(webExpPromt);
        lineRow.setExpenditureType(webExpPromt);
        lineRow.setSetOfBooksId(row.getSetOfBooksId());

        lineRow.setAmount(amount.multiply(rate));
        lineRow.setCurrencyCode("CNY");
        lineRow.setAmountIncludesTaxFlag("N");

        lineRow.setReceiptCurrencyAmount(amount);
        lineRow.setStringExpensedAmount(amount.doubleValue() + "");
        lineRow.setReceiptCurrencyCode(currencyCode);
        lineRow.setExchangeRate(rate);
        lineRow.setReceiptConversionRate(rate);
        lineRow.setStringReceiptConversionRate(rate.doubleValue() + "");

        lineRow.setLineTypeLookupCode("ITEM");
        lineRow.setOrgId(row.getOrgId());

        lineRow.setJustificationRequiredFlag("V");
        lineRow.setReceiptRequiredFlag("N");
        lineRow.setJustification(itemDesccription);
        //TODO
        lineRow.setStartExpenseDate(btStartDate);
        lineRow.setEndExpenseDate(btEndDate);

        lineRow.setWebParameterId(paramRow.getParameterId());
        lineRow.setCategoryCode(paramRow.getCategoryCode());

        lineRow.setSubmittedAmount(amount.multiply(rate));
        //        //add  by Wei Yi at 20171128  只读
        //        String travelExpenseType = (String)row.getAttribute("Attribute2");
        //        lineRow.setReadOnly(Boolean.FALSE);
        //        if ("国内出差报销".equals(travelExpenseType) || "国外出差报销".equals(travelExpenseType)) {
        //            lineRow.setItemizationParentId(new oracle.jbo.domain.Number(-1));
        //        }
        //        //add end

        info.setCategory(webExpPromt);
        lineRow.setAttributeCategory(webExpPromt);
        info.setAttibute13(empNumber);
        lineRow.setAttribute13(empNumber);
        upList.add(info);

        lineVO.insertRowAtRangeIndex(0, lineRow);
    }

    /**
     * 
     * @param rootAM
     */
    public static void updateLineInfo(OAApplicationModuleImpl rootAM) {

        for (ToUpdateLineInfoformation tInfo: upList) {
            OADBTransaction tx = rootAM.getOADBTransaction();
            CallableStatement cs = 
                tx.createCallableStatement("\n" + "BEGIN\n" + 
                                           "  UPDATE ap_expense_report_lines_all t\n" + 
                                           "     SET t.attribute_category = :1,\n" + 
                                           "         t.attribute13        = :2\n" + 
                                           "   WHERE t.report_line_id = :3;\n" + 
                                           "  :4 := SQL%ROWCOUNT;\n" + 
                                           "END;\n", 1);
            try {
                cs.setString(1, tInfo.getCategory());
                cs.setString(2, tInfo.getAttibute13());
                cs.setInt(3, tInfo.getLineId().intValue());
                cs.registerOutParameter(4, Types.INTEGER);

                cs.execute();
                System.out.println(cs.getInt(4));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 清除所有国内出差填写的数据
     * 
     * @param pageContext
     * @param webBean
     */
    private void clearAllOverData(OAPageContext pageContext, 
                                  OAWebBean webBean) {
        OAApplicationModuleImpl rootAM = 
            (OAApplicationModuleImpl)pageContext.getRootApplicationModule();
        CuxApExpRelatedOaInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedOaInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedOaInfoVO1");

        CuxApExpRelatedOaInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedOaInfoVORowImpl)relateHeaderVO.first();
        Number relationshipId = infoHeaderRow.getRelationshipId();

        infoHeaderRow.setOaOutseaTravelProcessId(null);
        infoHeaderRow.setAbourdEndDate(null);
        infoHeaderRow.setAbourdStartDate(null);

        OADBTransaction tx = rootAM.getOADBTransaction();
        CallableStatement cs = 
            tx.createCallableStatement("begin cux_ap_exp_pkg.clear_all_oversea_travel_data(:1);end;", 
                                       1);
        try {
            cs.setInt(1, relationshipId.intValue());
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
