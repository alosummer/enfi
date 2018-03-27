/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.quality.webui;

import com.sun.java.util.collections.HashMap;

import cux.oracle.apps.cux.qhse.quality.server.CuxProjectNameQueryVOImpl;
import cux.oracle.apps.cux.qhse.quality.server.CuxProjectNameQueryVORowImpl;
import cux.oracle.apps.cux.qhse.quality.server.CuxProjectResultVOImpl;
import cux.oracle.apps.cux.qhse.quality.server.CuxProjectWorkplanResultVOImpl;
import cux.oracle.apps.cux.qhse.quality.server.CuxQualityAMImpl;

import cux.oracle.apps.cux.qhse.quality.server.CuxQualityMagHeaderVOImpl;

import cux.oracle.apps.cux.qhse.quality.server.CuxQualityMagHeaderVORowImpl;

import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class CuxQualityWorkplanDetailPGCO extends OAControllerImpl {
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

        String projectId = pageContext.getParameter("ProjectId");
        CuxQualityAMImpl pgAM = 
            (CuxQualityAMImpl)pageContext.getRootApplicationModule();
        CuxProjectWorkplanResultVOImpl resultVO = 
            pgAM.getCuxProjectWorkplanResultVO1();
        resultVO.setWhereClauseParams(null);
        resultVO.setWhereClauseParams(new Object[] { projectId });
        resultVO.executeQuery();

        CuxQualityMagHeaderVOImpl headerVO = pgAM.getCuxQualityMagHeaderVO1();
        headerVO.setWhereClause(null);
        headerVO.setWhereClauseParams(null);
        headerVO.setWhereClause("PROJECT_ID = :1");
        headerVO.setWhereClauseParams(new Object[] { projectId });
        headerVO.executeQuery();

        Number projectIdNum = new Number(-1);
        try {
            projectIdNum = new Number(projectId);
        } catch (SQLException e) {
            // TODO
        }
        pgAM.initDetailPG(projectIdNum);

        String canEditFlag = "Y";
        String canCompleteFlag = "N";
        if (headerVO.getRowCount() == 0) {
            canEditFlag = "Y";
        } else {
            CuxQualityMagHeaderVORowImpl firstRow = 
                (CuxQualityMagHeaderVORowImpl)headerVO.first();
            if ("新建".equals(firstRow.getStatus())) {
                canEditFlag = "Y";
            } else {
                canEditFlag = "N";
            }

            if ("已批准".equals(firstRow.getStatus())) {
                canCompleteFlag = "Y";
            }
        }

        if ("N".equals(canEditFlag)) {
            webBean.findChildRecursive("SubmitBTN").setRendered(Boolean.FALSE);
            webBean.findChildRecursive("UPDATE_ENABLE").setRendered(Boolean.FALSE);
        } else {
            webBean.findChildRecursive("SubmitBTN").setRendered(Boolean.TRUE);
            webBean.findChildRecursive("UPDATE_ENABLE").setRendered(Boolean.TRUE);
        }

        if ("Y".equals(canCompleteFlag)) {
            webBean.findChildRecursive("COMPLETE_ENABLE").setRendered(Boolean.TRUE);
            webBean.findChildRecursive("CompleteBTN").setRendered(Boolean.TRUE);
        } else {
            webBean.findChildRecursive("COMPLETE_ENABLE").setRendered(Boolean.FALSE);
            webBean.findChildRecursive("CompleteBTN").setRendered(Boolean.FALSE);
        }

        CuxProjectNameQueryVOImpl nameVO = pgAM.getCuxProjectNameQueryVO1();
        nameVO.setWhereClauseParams(new Object[] { projectId });
        nameVO.executeQuery();
        CuxProjectNameQueryVORowImpl nameRow = 
            (CuxProjectNameQueryVORowImpl)nameVO.first();

        OATableBean table = 
            (OATableBean)webBean.findChildRecursive("CuxProjectWorkplanResultVO1");
        table.setText(table.getText() + "(" + nameRow.getSegment1() + "," + 
                      nameRow.getProjectName() + ")");

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
        String souceParam = pageContext.getParameter("source");
        if (souceParam == null) {
            souceParam = "THIS#IS#NULL#VALUE";
        }
        String eventParam = pageContext.getParameter(this.EVENT_PARAM);
        if ("UpdatePlanControl".equals(eventParam)) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/quality/webui/CuxQualityPlanControlDetailEditPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("SubmitBTN") != null) {

            CuxQualityAMImpl pgAM = 
                (CuxQualityAMImpl)pageContext.getRootApplicationModule();
            pgAM.initCreateHeader();
            pgAM.commit();
            CuxQualityMagHeaderVOImpl headerVO = 
                pgAM.getCuxQualityMagHeaderVO1();
            CuxQualityMagHeaderVORowImpl headerRow = 
                (CuxQualityMagHeaderVORowImpl)headerVO.first();
            pgAM.startWorkFlow(headerRow.getHeaderId());
            pgAM.commit();
            pageContext.putDialogMessage(new OAException("提交审批成功", 
                                                         OAException.INFORMATION));
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/quality/webui/CuxQualityProjectResultPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("CancelBTN") != null) {

            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/quality/webui/CuxQualityProjectResultPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("CompleteBTN") != null) {

            CuxQualityAMImpl pgAM = 
                (CuxQualityAMImpl)pageContext.getRootApplicationModule();

            CuxQualityMagHeaderVOImpl headerVO = 
                pgAM.getCuxQualityMagHeaderVO1();
            CuxQualityMagHeaderVORowImpl headerRow = 
                (CuxQualityMagHeaderVORowImpl)headerVO.first();
            headerRow.setStatus("已完成");
            pgAM.commit();
            pageContext.putDialogMessage(new OAException("已完成检验！", 
                                                         OAException.INFORMATION));
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/quality/webui/CuxQualityProjectResultPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
    }

}
