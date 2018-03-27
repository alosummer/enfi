/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.ap.oie.entry.header.webui;

import cux.oracle.apps.ap.oie.entry.entity.ToUpdateLineInfoformation;
import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelDetailLovVOImpl;
import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelEmpLovVOImpl;
import cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelFeeLovVOImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardFeeLineVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardFeeLineVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardOtherFeeVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardOtherFeeVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardTotalFeeVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardInfoVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardInfoVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardLineVOImpl;


import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardLineVORowImpl;


import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpTotalFeeVOImpl;

import cux.oracle.apps.ap.oie.entry.header.server.CuxWebExpParamtersVOImpl;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import oracle.apps.ap.oie.server.ExpenseReportHeadersVOImpl;
import oracle.apps.ap.oie.server.ExpenseReportHeadersVORowImpl;
import oracle.apps.ap.oie.server.ReceiptBasedLinesVOImpl;
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
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class OAAboardHeaderRNCO extends OAControllerImpl {
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

        OAViewObject headerVO = 
            (OAViewObject)rootAM.findViewObject("ExpenseReportHeadersVO");
        OARow headerRow = (OARow)headerVO.getCurrentRow();
        if (headerRow == null) {
            headerRow = (OARow)headerVO.first();
        }
        Number reportHeaderId = 
            (Number)headerRow.getAttribute("ReportHeaderId");
        /***************0.初始化oa关联头****************/
        CuxApExpRelatedAboardInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedAboardInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardInfoVO1");
        if (relateHeaderVO == null) {
            relateHeaderVO = 
                    (CuxApExpRelatedAboardInfoVOImpl)rootAM.createViewObject("CuxApExpRelatedAboardInfoVO1", 
                                                                             "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardInfoVO");
        }

        /***************1.初始化oa关联行****************/
        CuxOAAboardTravelDetailLovVOImpl relateLineLOVVO = 
            (CuxOAAboardTravelDetailLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelDetailLovVO1");
        if (relateLineLOVVO == null) {
            relateLineLOVVO = 
                    (CuxOAAboardTravelDetailLovVOImpl)rootAM.createViewObject("CuxOAAboardTravelDetailLovVO1", 
                                                                              "cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelDetailLovVO");
        }
        /***************end of 1.初始化oa关联行****************/


        /***************1.2 初始化oa员工lov****************/
        CuxOAAboardTravelEmpLovVOImpl relateLineEmpLOVVO = 
            (CuxOAAboardTravelEmpLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelEmpLovVO1");
        if (relateLineEmpLOVVO == null) {
            relateLineEmpLOVVO = 
                    (CuxOAAboardTravelEmpLovVOImpl)rootAM.createViewObject("CuxOAAboardTravelEmpLovVO1", 
                                                                           "cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelEmpLovVO");
        }
        /***************end of 1.2初始化oa员工lov****************/

        /***************1.5 初始化oa关联明细行****************/
        CuxApExpRelatedAboardLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedAboardLineVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardLineVO1");
        if (relateLineEOVO == null) {
            relateLineEOVO = 
                    (CuxApExpRelatedAboardLineVOImpl)rootAM.createViewObject("CuxApExpRelatedAboardLineVO1", 
                                                                             "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedAboardLineVO");
        }
        /***************end of 1.5 初始化oa关联明细行****************/

        /***************2.0 初始化oa关联费用行****************/
        CuxApExpRelAboardFeeLineVOImpl relateFeeEOVO = 
            (CuxApExpRelAboardFeeLineVOImpl)rootAM.findViewObject("CuxApExpRelAboardFeeLineVO1");
        if (relateFeeEOVO == null) {
            relateFeeEOVO = 
                    (CuxApExpRelAboardFeeLineVOImpl)rootAM.createViewObject("CuxApExpRelAboardFeeLineVO1", 
                                                                            "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardFeeLineVO");
        }
        /***************end of 2.0 初始化oa关联费用行****************/

        /***************3.0 初始化oa费用行Lov****************/
        CuxOAAboardTravelFeeLovVOImpl relateFeeLovVO = 
            (CuxOAAboardTravelFeeLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelFeeLovVO1");
        if (relateFeeLovVO == null) {
            relateFeeLovVO = 
                    (CuxOAAboardTravelFeeLovVOImpl)rootAM.createViewObject("CuxOAAboardTravelFeeLovVO1", 
                                                                           "cux.oracle.apps.ap.oie.entry.header.lov.server.CuxOAAboardTravelFeeLovVO");
        }
        /***************end of 3.0 初始化oa费用行Lov****************/


        /***************5.初始化其他报销****************/
        CuxApExpRelAboardOtherFeeVOImpl otherFeeVO = 
            (CuxApExpRelAboardOtherFeeVOImpl)rootAM.findViewObject("CuxApExpRelAboardOtherFeeVO1");
        if (otherFeeVO == null) {
            otherFeeVO = 
                    (CuxApExpRelAboardOtherFeeVOImpl)rootAM.createViewObject("CuxApExpRelAboardOtherFeeVO1", 
                                                                             "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardOtherFeeVO");
        }
        /***************end of 5.初始化其他报销****************/

        /***************6.初始化统计金额****************/
        CuxApExpRelAboardTotalFeeVOImpl totalVO = 
            (CuxApExpRelAboardTotalFeeVOImpl)rootAM.findViewObject("CuxApExpRelAboardTotalFeeVO1");
        if (totalVO == null) {
            totalVO = 
                    (CuxApExpRelAboardTotalFeeVOImpl)rootAM.createViewObject("CuxApExpRelAboardTotalFeeVO1", 
                                                                             "cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelAboardTotalFeeVO");
        }
        /***************end of 6.初始化统计金额***************/

        if (!relateHeaderVO.isExecuted()) {
            relateHeaderVO.setWhereClause(null);
            relateHeaderVO.setWhereClauseParams(null);
            relateHeaderVO.setWhereClause("report_header_id = :1");
            relateHeaderVO.setWhereClauseParams(new Object[] { reportHeaderId });
            relateHeaderVO.executeQuery();
        }

        CuxApExpRelatedAboardInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedAboardInfoVORowImpl)relateHeaderVO.first();
        if (infoHeaderRow == null) {
            infoHeaderRow = 
                    (CuxApExpRelatedAboardInfoVORowImpl)relateHeaderVO.createRow();
            Number relatationshipId = 
                rootAM.getSequenceValue("cux_ap_exp_related_oa_info_s");
            infoHeaderRow.setRelationshipId(relatationshipId);
            infoHeaderRow.setReportHeaderId(reportHeaderId);
            relateHeaderVO.insertRow(infoHeaderRow);

            relateLineEOVO.setWhereClauseParams(new Object[] { new Number(-9999) });
            relateLineEOVO.executeQuery();

            relateFeeEOVO.setWhereClauseParams(new Object[] { new Number(-9999) });
            relateFeeEOVO.executeQuery();

            relateFeeLovVO.setWhereClauseParams(new Object[] { new Number(-9999) });
            relateFeeLovVO.executeQuery();

            otherFeeVO.setWhereClause("1 = 2");
            otherFeeVO.executeQuery();

        } else {

            Number relationshipId = infoHeaderRow.getRelationshipId();
            Number oaOutseaTravelProcessId = 
                infoHeaderRow.getOaOutseaTravelProcessId();
            relateLineLOVVO.setWhereClauseParams(null);
            relateLineLOVVO.setWhereClauseParams(new Object[] { relationshipId, 
                                                                oaOutseaTravelProcessId });

            relateLineEmpLOVVO.setWhereClauseParams(new Object[] { oaOutseaTravelProcessId });


            relateLineEOVO.setWhereClauseParams(new Object[] { relationshipId });
            relateLineEOVO.executeQuery();

            relateFeeEOVO.setWhereClauseParams(new Object[] { relationshipId });
            relateFeeEOVO.executeQuery();

            relateFeeLovVO.setWhereClauseParams(new Object[] { oaOutseaTravelProcessId });
            relateFeeLovVO.executeQuery();

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
        CuxApExpRelatedAboardInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedAboardInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardInfoVO1");

        CuxOAAboardTravelDetailLovVOImpl relateLineLOVVO = 
            (CuxOAAboardTravelDetailLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelDetailLovVO1");

        CuxApExpRelatedAboardLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedAboardLineVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardLineVO1");

        CuxApExpRelAboardFeeLineVOImpl relateFeeEOVO = 
            (CuxApExpRelAboardFeeLineVOImpl)rootAM.findViewObject("CuxApExpRelAboardFeeLineVO1");

        CuxOAAboardTravelFeeLovVOImpl relateFeeLovVO = 
            (CuxOAAboardTravelFeeLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelFeeLovVO1");

        CuxApExpRelAboardOtherFeeVOImpl otherFeeVO = 
            (CuxApExpRelAboardOtherFeeVOImpl)rootAM.findViewObject("CuxApExpRelAboardOtherFeeVO1");

        CuxOAAboardTravelEmpLovVOImpl relateLineEmpLOVVO = 
            (CuxOAAboardTravelEmpLovVOImpl)rootAM.findViewObject("CuxOAAboardTravelEmpLovVO1");
        CuxApExpRelAboardTotalFeeVOImpl totalVO = 
            (CuxApExpRelAboardTotalFeeVOImpl)rootAM.findViewObject("CuxApExpRelAboardTotalFeeVO1");

        CuxApExpRelatedAboardInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedAboardInfoVORowImpl)relateHeaderVO.first();

        Number oaOutseaTravelProcessId = 
            infoHeaderRow.getOaOutseaTravelProcessId();
        Number relationshipId = infoHeaderRow.getRelationshipId();

        if ("OaAbroadTravelHeaderName".equals(pageContext.getLovInputSourceId())) {
            if ("lovPrepare".equals(pageContext.getParameter(EVENT_PARAM))) {
                relateLineLOVVO.setWhereClauseParams(null);
                relateLineLOVVO.setWhereClauseParams(new Object[] { relationshipId, 
                                                                    new Number(-9999) });
                relateLineEmpLOVVO.setWhereClauseParams(null);
                relateLineEmpLOVVO.setWhereClauseParams(new Object[] { new Number(-9999) });

                totalVO.setWhereClause(null);
                totalVO.setWhereClauseParams(null);
                totalVO.setWhereClause("1 = 2");
                totalVO.executeQuery();

                relateFeeLovVO.setWhereClauseParams(null);
                relateFeeLovVO.setWhereClauseParams(new Object[] { new Number(-9999) });

                /***********************0 人员行**********************************/
                int fetchedRowCount = relateLineEOVO.getRowCount();
                if (fetchedRowCount > 0) {
                    RowSetIterator deleteIter = 
                        relateLineEOVO.createRowSetIterator("deleteIter");
                    deleteIter.setRangeStart(0);
                    deleteIter.setRangeSize(fetchedRowCount);
                    CuxApExpRelatedAboardLineVORowImpl relateLineEORow = null;
                    for (int i = fetchedRowCount - 1; i >= 0; i--) {
                        relateLineEORow = 
                                (CuxApExpRelatedAboardLineVORowImpl)deleteIter.getRowAtRangeIndex(i);
                        relateLineEORow.remove();
                    }
                    deleteIter.closeRowSetIterator();
                }
                /***********************end of 0 人员行**********************************/

                /***********************1 费用行**********************************/
                fetchedRowCount = relateFeeEOVO.getRowCount();
                if (fetchedRowCount > 0) {
                    RowSetIterator deleteIter = 
                        relateFeeEOVO.createRowSetIterator("deleteIter");
                    deleteIter.setRangeStart(0);
                    deleteIter.setRangeSize(fetchedRowCount);
                    CuxApExpRelAboardFeeLineVORowImpl relateFeeLineRow = null;
                    for (int i = fetchedRowCount - 1; i >= 0; i--) {
                        relateFeeLineRow = 
                                (CuxApExpRelAboardFeeLineVORowImpl)deleteIter.getRowAtRangeIndex(i);
                        relateFeeLineRow.remove();
                    }
                    deleteIter.closeRowSetIterator();
                }
                /***********************end of 1 费用行**********************************/

                /***********************4.清空其他报销**********************************/
                fetchedRowCount = otherFeeVO.getRowCount();
                if (fetchedRowCount > 0) {
                    RowSetIterator deleteIter = 
                        otherFeeVO.createRowSetIterator("deleteIter");
                    deleteIter.setRangeStart(0);
                    deleteIter.setRangeSize(fetchedRowCount);
                    CuxApExpRelAboardOtherFeeVORowImpl otherFeeRow = null;
                    for (int i = fetchedRowCount - 1; i >= 0; i--) {
                        otherFeeRow = 
                                (CuxApExpRelAboardOtherFeeVORowImpl)deleteIter.getRowAtRangeIndex(i);
                        otherFeeRow.remove();
                    }
                    deleteIter.closeRowSetIterator();
                }
                /***********************end of 4 清空其他报销**********************************/


            } else if ("lovUpdate".equals(pageContext.getParameter(EVENT_PARAM)) || 
                       "lovValidate".equals(pageContext.getParameter(EVENT_PARAM))) {

                relateLineLOVVO.setWhereClauseParams(null);
                relateLineLOVVO.setWhereClauseParams(new Object[] { relationshipId, 
                                                                    oaOutseaTravelProcessId });
                relateLineLOVVO.executeQuery();

                relateLineEmpLOVVO.setWhereClauseParams(null);
                relateLineEmpLOVVO.setWhereClauseParams(new Object[] { oaOutseaTravelProcessId });

                totalVO.setWhereClause(null);
                totalVO.setWhereClauseParams(null);
                totalVO.setWhereClause("relationship_id = :1");
                totalVO.setWhereClauseParams(new Object[] { relationshipId });
                totalVO.executeQuery();
            }

        } else if ("DeleteRelatedOALine".equals(eventParam)) {
            String empIdStr = pageContext.getParameter("EmpId");
            Number empIdNum = new Number(-1);
            try {
                empIdNum = new Number(empIdStr);
            } catch (SQLException e) {
            }


            /***********************1 删除费用明细行**********************************/
            int fetchedRowCount = relateFeeEOVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator deleteIter = 
                    relateFeeEOVO.createRowSetIterator("deleteIter");
                deleteIter.setRangeStart(0);
                deleteIter.setRangeSize(fetchedRowCount);
                CuxApExpRelAboardFeeLineVORowImpl feeLineRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    feeLineRow = 
                            (CuxApExpRelAboardFeeLineVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    if (empIdNum.equals(feeLineRow.getEmpId())) {
                        feeLineRow.remove();
                    }
                }
                deleteIter.closeRowSetIterator();
            }
            /***********************end of 1 删除费用明细行**********************************/

            /***********************4.删除其他报销**********************************/
            fetchedRowCount = otherFeeVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator deleteIter = 
                    otherFeeVO.createRowSetIterator("deleteIter");
                deleteIter.setRangeStart(0);
                deleteIter.setRangeSize(fetchedRowCount);
                CuxApExpRelAboardOtherFeeVORowImpl otherFeeRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    otherFeeRow = 
                            (CuxApExpRelAboardOtherFeeVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    if (empIdNum.equals(otherFeeRow.getEmpId())) {
                        otherFeeRow.remove();
                    }
                }
                deleteIter.closeRowSetIterator();
            }
            /***********************end of 4 删除其他报销**********************************/

            Row r = 
                rootAM.findRowByRef(pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE));
            r.remove();

        } else if (pageContext.getParameter("OtherTableAddOneRow") != null) {
            CuxApExpRelAboardOtherFeeVORowImpl autoCreateOtherFeeRow = 
                (CuxApExpRelAboardOtherFeeVORowImpl)otherFeeVO.createRow();
            autoCreateOtherFeeRow.setRelationshipId(relationshipId);
            autoCreateOtherFeeRow.setOtherFeeLineId(rootAM.getSequenceValue("CUX_AP_EXP_OTHER_FEE_S"));
            autoCreateOtherFeeRow.setLineNumber(otherFeeVO.getMaxLineNumber());
            autoCreateOtherFeeRow.setAboardFlag("Y");
            //  autoCreateOtherFeeRow.setFeeType("国内差旅-其他差旅费");
            //            CuxOATravelDetailLovRNCO.insertRowAtLastIndex(new Number(10),otherFeeVO,autoCreateOtherFeeRow); 
            otherFeeVO.insertRowAtRangeIndex(0, autoCreateOtherFeeRow);
        } else if ("DeleteCurrentRow".equals(eventParam)) {
            Row r = 
                rootAM.findRowByRef(pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE));
            r.remove();
        } else if ("UpdateDaysNumber".equals(eventParam)) {
            /***********************1 费用行**********************************/
            int fetchedRowCount = relateFeeEOVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator updateIter = 
                    relateFeeEOVO.createRowSetIterator("updateIter");
                updateIter.setRangeStart(0);
                updateIter.setRangeSize(fetchedRowCount);
                CuxApExpRelAboardFeeLineVORowImpl relateFeeLineRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    relateFeeLineRow = 
                            (CuxApExpRelAboardFeeLineVORowImpl)updateIter.getRowAtRangeIndex(i);
                    if ("国外差旅-机票".equals(relateFeeLineRow.getFeeType()) || 
                        "国外差旅-其他费用".equals(relateFeeLineRow.getFeeType())) {
                        ;
                    } else {
                        relateFeeLineRow.setRealTravelDays(infoHeaderRow.getTravelDaysNumber());
                    }
                }
                updateIter.closeRowSetIterator();
            }
            /***********************end of 1 费用行**********************************/
        }

        //用于处理点击保存按钮时，需要清除掉不是国外出差的数据
        //
        if (pageContext.getParameter("OIESave") != null) {
            this.clearAllInCountryData(pageContext, webBean);
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
        CuxApExpRelatedAboardInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedAboardInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardInfoVO1");

        CuxApExpRelatedAboardLineVOImpl relateLineEOVO = 
            (CuxApExpRelatedAboardLineVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardLineVO1");

        CuxApExpRelAboardFeeLineVOImpl relateFeeEOVO = 
            (CuxApExpRelAboardFeeLineVOImpl)rootAM.findViewObject("CuxApExpRelAboardFeeLineVO1");

        CuxApExpRelAboardOtherFeeVOImpl otherFeeVO = 
            (CuxApExpRelAboardOtherFeeVOImpl)rootAM.findViewObject("CuxApExpRelAboardOtherFeeVO1");

        if (relateHeaderVO.isDirty() || relateLineEOVO.isDirty() || 
            relateFeeEOVO.isDirty() || otherFeeVO.isDirty()) {
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

        CuxApExpRelatedAboardInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedAboardInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardInfoVO1");
        CuxApExpRelatedAboardInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedAboardInfoVORowImpl)relateHeaderVO.first();

        CuxApExpRelAboardFeeLineVOImpl relateFeeEOVO = 
            (CuxApExpRelAboardFeeLineVOImpl)rootAM.findViewObject("CuxApExpRelAboardFeeLineVO1");

        CuxApExpRelAboardOtherFeeVOImpl otherFeeVO = 
            (CuxApExpRelAboardOtherFeeVOImpl)rootAM.findViewObject("CuxApExpRelAboardOtherFeeVO1");
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


            /***********************1  创建标准费用行**********************************/
            fetchedRowCount = relateFeeEOVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator autoLoopIter = 
                    relateFeeEOVO.createRowSetIterator("autoLoopIter");
                autoLoopIter.setRangeStart(0);
                autoLoopIter.setRangeSize(fetchedRowCount);
                CuxApExpRelAboardFeeLineVORowImpl relateFeeLineRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    relateFeeLineRow = 
                            (CuxApExpRelAboardFeeLineVORowImpl)autoLoopIter.getRowAtRangeIndex(i);
                    String feeType = null;
                    if ("国外差旅-现场服务补助".equals(relateFeeLineRow.getFeeType())) {
                        feeType = "国外差旅-现场补助";
                    } else {
                        feeType = relateFeeLineRow.getFeeType();
                    }
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              feeType, 
                                              relateFeeLineRow.getEmpName() + 
                                              "(" + 
                                              relateFeeLineRow.getEmpNumber() + 
                                              ")" + "的" + feeType, 
                                              relateFeeLineRow.getRealTotalAmount(), 
                                              relateFeeLineRow.getRate(), 
                                              relateFeeLineRow.getCurrencyCode(), 
                                              infoHeaderRow.getAbourdStartDate(), 
                                              infoHeaderRow.getAbourdEndDate(), 
                                              relateFeeLineRow.getEmpNumber());
                }
                autoLoopIter.closeRowSetIterator();
            }
            /***********************end of 1  创建标准费用行**********************************/

            /***********************2. 创建标准其他报销**********************************/
            fetchedRowCount = otherFeeVO.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator autoLoopIter = 
                    otherFeeVO.createRowSetIterator("autoLoopIter");
                autoLoopIter.setRangeStart(0);
                autoLoopIter.setRangeSize(fetchedRowCount);
                CuxApExpRelAboardOtherFeeVORowImpl otherFeeRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    otherFeeRow = 
                            (CuxApExpRelAboardOtherFeeVORowImpl)autoLoopIter.getRowAtRangeIndex(i);
                    OATravelRNCO.insertLineVO(rootAM, lineVO, row, paramVo, 
                                              otherFeeRow.getFeeType(), 
                                              otherFeeRow.getEmpName() + "(" + 
                                              otherFeeRow.getEmployeeNumber() + 
                                              ")" + "的" + 
                                              otherFeeRow.getFeeType(), 
                                              otherFeeRow.getFeeAmount(), 
                                              otherFeeRow.getRate(), 
                                              otherFeeRow.getCurrencyCode(), 
                                              otherFeeRow.getFeeDate(), 
                                              otherFeeRow.getFeeDate(), 
                                              otherFeeRow.getEmployeeNumber());
                }
                autoLoopIter.closeRowSetIterator();
            }
            /***********************end of 2  创建标准其他报销**********************************/
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
        }


    }

    /**
     * 清除所有国内出差填写的数据
     * 
     * @param pageContext
     * @param webBean
     */
    private void clearAllInCountryData(OAPageContext pageContext, 
                                       OAWebBean webBean) {
        OAApplicationModuleImpl rootAM = 
            (OAApplicationModuleImpl)pageContext.getRootApplicationModule();
        CuxApExpRelatedAboardInfoVOImpl relateHeaderVO = 
            (CuxApExpRelatedAboardInfoVOImpl)rootAM.findViewObject("CuxApExpRelatedAboardInfoVO1");
        CuxApExpRelatedAboardInfoVORowImpl infoHeaderRow = 
            (CuxApExpRelatedAboardInfoVORowImpl)relateHeaderVO.first();
        Number relationshipId = infoHeaderRow.getRelationshipId();
        infoHeaderRow.setOaTravelProcessId(null);

        OADBTransaction tx = rootAM.getOADBTransaction();
        CallableStatement cs = 
            tx.createCallableStatement("begin cux_ap_exp_pkg.clear_all_in_travel_data(:1);end;", 
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
