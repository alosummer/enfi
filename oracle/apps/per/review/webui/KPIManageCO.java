/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.KeyPIAMImpl;
import cux.oracle.apps.per.review.server.KeyPIVOImpl;

import java.io.Serializable;

import java.sql.SQLException;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAImageBean;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAInlineDatePickerBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleStatement;

/**
 * Controller for ...
 */
public class KPIManageCO extends OAControllerImpl {
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
        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateKPITxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateKPITxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        KeyPIVOImpl vo = (KeyPIVOImpl)am.findViewObject("KeyPIVO1");
        if (pageContext.getParameter("refresh") != null) {
            vo.executeQuery();
        }

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("KeyPIVO1");

        //zs
        OAMessageChoiceBean kpiClass = 
            (OAMessageChoiceBean)tableBean.findIndexedChildRecursive("KpiClass");
        if (kpiClass != null) {
            kpiClass.setPickListCacheEnabled(false);
        }

        OAMessageChoiceBean kpiArea = 
            (OAMessageChoiceBean)tableBean.findIndexedChildRecursive("KpiArea");
        if (kpiArea != null) {
            kpiArea.setListVOBoundContainerColumn(0, tableBean, "KpiClass");
            kpiArea.setPickListCacheEnabled(false);
        }
        //tableBean.queryData(pageContext);
        //zs 

        tableBean.prepareForRendering(pageContext);
        tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);

        OAInlineDatePickerBean inlineDatePicker = 
            (OAInlineDatePickerBean)createWebBean(pageContext, 
                                                  INLINE_DATEPICKER_BEAN);
        inlineDatePicker.setID("DatePicker");

        OAMessageDateFieldBean startDateField = 
            (OAMessageDateFieldBean)webBean.findIndexedChildRecursive("StartDate");
        startDateField.setPickerId("DatePicker");

        OAMessageDateFieldBean endDateField = 
            (OAMessageDateFieldBean)webBean.findIndexedChildRecursive("EndDate");
        endDateField.setPickerId("DatePicker");
        OAImageBean image = 
            (OAImageBean)webBean.findIndexedChildRecursive("Dimension");
        image.setAttributeValue(this.RENDERED_ATTR, 
                                new OADataBoundValueViewObject(image, 
                                                               "Renderable"));
        String devMode = pageContext.getParameter("DeveloperMode");
        //devMode = "Yes";
        //如果为开发员模式，所有数据可看，可修改，可删除
        if (devMode != null && !"".equals(devMode) && "Yes".equals(devMode)) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT rownum,                                                                                                                    ");
            sql.append("       KeyPIEO.KPI_ID,                                                                                                            ");
            sql.append("       KeyPIEO.KPI_CLASS,                                                                                                         ");
            sql.append("       KeyPIEO.KPI_NAME,                                                                                                          ");
            sql.append("       KeyPIEO.KPI_DESC,                                                                                                          ");
            sql.append("       KeyPIEO.KPI_DATA_SOURCE,                                                                                                   ");
            sql.append("       KeyPIEO.KPI_DATA_DIMENSION,                                                                                                ");
            sql.append("       KeyPIEO.START_DATE,                                                                                                        ");
            sql.append("       KeyPIEO.END_DATE,                                                                                                          ");
            sql.append("       KeyPIEO.ATTRIBUTE1,                                                                                                        ");
            sql.append("       KeyPIEO.ATTRIBUTE2,                                                                                                        ");
            sql.append("       KeyPIEO.ATTRIBUTE3,                                                                                                        ");
            sql.append("       KeyPIEO.ATTRIBUTE4,                                                                                                        ");
            sql.append("       (SELECT ou.name FROM hr_all_organization_units ou WHERE ou.organization_id(+) = keyPIEO.Attribute5) organization_name,     ");
            sql.append("       KeyPIEO.ATTRIBUTE5,                                                                                                        ");
            sql.append("       KeyPIEO.LAST_UPDATE_DATE,                                                                                                  ");
            sql.append("       KeyPIEO.LAST_UPDATED_BY,                                                                                                   ");
            sql.append("       KeyPIEO.LAST_UPDATE_LOGIN,                                                                                                 ");
            sql.append("       KeyPIEO.CREATED_BY,                                                                                                        ");
            sql.append("       KeyPIEO.CREATION_DATE,                                                                                                     ");
            sql.append("       KeyPIEO.KPI_AREA,                                                                                                          ");
            sql.append("       KeyPIEO.KPI_SCORING_METHOD,                                                                                                ");
            sql.append("       KeyPIEO.CAL_TEXT,                                                                                                          ");
            sql.append("       KeyPIEO.CAL_TYPE,                                                                                                          ");
            sql.append("       KeyPIEO.REPORT_TEXT,                                                                                                       ");
            sql.append("       KeyPIEO.REPORT_TYPE,                                                                                                       ");
            sql.append("       0 updatable,                                                                                                               ");
            sql.append("       1 renderable                                                                                                               ");
            sql.append("FROM cux.CUX_KPI_T KeyPIEO                                                                                                        ");
            sql.append(" WHERE SYSDATE BETWEEN nvl(KeyPIEO.START_DATE,SYSDATE) AND nvl(KeyPIEO.END_DATE,SYSDATE)                                          ");
            KeyPIVOImpl kpiVO = (KeyPIVOImpl)am.findViewObject("KeyPIVO1");
            kpiVO.getViewDefinition().setQuery(sql.toString());
        } else {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT rownum,                                                                                                                            ");
            sql.append("       KeyPIEO.KPI_ID,                                                                                                                    ");
            sql.append("       KeyPIEO.KPI_CLASS,                                                                                                                 ");
            sql.append("       KeyPIEO.KPI_NAME,                                                                                                                  ");
            sql.append("       KeyPIEO.KPI_DESC,                                                                                                                  ");
            sql.append("       KeyPIEO.KPI_DATA_SOURCE,                                                                                                           ");
            sql.append("       KeyPIEO.KPI_DATA_DIMENSION,                                                                                                        ");
            sql.append("       KeyPIEO.START_DATE,                                                                                                                ");
            sql.append("       KeyPIEO.END_DATE,                                                                                                                  ");
            sql.append("       KeyPIEO.ATTRIBUTE1,                                                                                                                ");
            sql.append("       KeyPIEO.ATTRIBUTE2,                                                                                                                ");
            sql.append("       KeyPIEO.ATTRIBUTE3,                                                                                                                ");
            sql.append("       KeyPIEO.ATTRIBUTE4,                                                                                                                ");
            sql.append("       (SELECT ou.name FROM hr_all_organization_units ou WHERE ou.organization_id(+) = keyPIEO.Attribute5) organization_name,             ");
            sql.append("       KeyPIEO.ATTRIBUTE5,                                                                                                                ");
            sql.append("       KeyPIEO.LAST_UPDATE_DATE,                                                                                                          ");
            sql.append("       KeyPIEO.LAST_UPDATED_BY,                                                                                                           ");
            sql.append("       KeyPIEO.LAST_UPDATE_LOGIN,                                                                                                         ");
            sql.append("       KeyPIEO.CREATED_BY,                                                                                                                ");
            sql.append("       KeyPIEO.CREATION_DATE,                                                                                                             ");
            sql.append("       KeyPIEO.KPI_AREA,                                                                                                                  ");
            sql.append("       KeyPIEO.KPI_SCORING_METHOD,                                                                                                        ");
            sql.append("       KeyPIEO.CAL_TEXT,                                                                                                                  ");
            sql.append("       KeyPIEO.CAL_TYPE,                                                                                                                  ");
            sql.append("       KeyPIEO.REPORT_TEXT,                                                                                                               ");
            sql.append("       KeyPIEO.REPORT_TYPE,                                                                                                               ");
            sql.append("       decode(nvl(keypieo.attribute5,'81'),                                                                                               ");
            sql.append("              (SELECT pspv.organization_id                                                                                                ");
            sql.append("                 FROM per_security_profiles_v pspv                                                                                        ");
            sql.append("                WHERE pspv.security_profile_id =                                                                                          ");
            sql.append("                      fnd_profile.value('PER_SECURITY_PROFILE_ID')),                                                                      ");
            sql.append("              0,                                                                                                                          ");
            sql.append("              1) updatable,                                                                                                               ");
            sql.append("       decode(nvl(keypieo.attribute5,'81'),                                                                                               ");
            sql.append("              (SELECT pspv.organization_id                                                                                                ");
            sql.append("                 FROM per_security_profiles_v pspv                                                                                        ");
            sql.append("                WHERE pspv.security_profile_id =                                                                                          ");
            sql.append("                      fnd_profile.value('PER_SECURITY_PROFILE_ID')),                                                                      ");
            sql.append("              1,                                                                                                                          ");
            sql.append("              0) renderable                                                                                                               ");
            sql.append("FROM CUX_KPI_T KeyPIEO                                                                                                                    ");
            sql.append(" WHERE SYSDATE BETWEEN nvl(KeyPIEO.START_DATE,SYSDATE) AND nvl(KeyPIEO.END_DATE,SYSDATE)                                                  ");
            KeyPIVOImpl kpiVO = (KeyPIVOImpl)am.findViewObject("KeyPIVO1");
            kpiVO.getViewDefinition().setQuery(sql.toString());
        }

        OAMessageChoiceBean msSearchDataSource = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("SearchDataSource");
        msSearchDataSource.setPickListCacheEnabled(false);

        OAMessageChoiceBean msTableDataSource = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("KpiDataSource");
        msTableDataSource.setPickListCacheEnabled(false);
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
        KeyPIAMImpl am = 
            (KeyPIAMImpl)pageContext.getApplicationModule(webBean);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("KeyPIVO1");
        // Handle adding a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            this.AddRow(pageContext, webBean);
        }
        // Handle Cancel action
        else if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateKPITxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle 
        if ("dimension".equals(pageContext.getParameter("event"))) { // Retain AM
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIDimensionPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }

        // Handle calculation logic
        if ("logic".equals(pageContext.getParameter("event"))) { // Retain AM
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPICalculationPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }

        // Handle report
        if ("report".equals(pageContext.getParameter("event"))) { // Retain AM
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIReportPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }

        // Handle add constraint event 
        if ("updateConstraint".equals(pageContext.getParameter("event"))) {
            String kpiId = pageContext.getParameter("kpiId");
            if (kpiId != null && !kpiId.equals("")) {
                Serializable params[] = { kpiId, "validate" };
                String existFlag = 
                    (String)am.invokeMethod("processKPI", params);
                if (existFlag != null && existFlag.equals("N")) {
                    throw new OAException("CUX", "CUX_SAVE_KPI_HEADER_WARNING", 
                                          null, OAException.WARNING, null);
                } else { // Retain AM
                    pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIConstraintPG", 
                                              null, 
                                              OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                              null, null, true, 
                                              OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                              OAWebBeanConstants.IGNORE_MESSAGES);
                }
            }
        }

        // Handle delete
        if ("deleteKPI".equals(pageContext.getParameter("event"))) {
            String kpiId = pageContext.getParameter("kpiId");
            String kpiName = pageContext.getParameter("kpiName");
            MessageToken tokens[] = { new MessageToken("KPI_NAME", kpiName) };
            OAException mainMessage = 
                new OAException("CUX", "CUX_DEL_KPI_WARN", tokens, 
                                OAException.WARNING, null);

            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("DeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(2);
            formParams.put("kpiId", kpiId);
            formParams.put("kpiName", kpiName);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);

        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String result = "";
            String kpiId = pageContext.getParameter("kpiId");
            String kpiName = pageContext.getParameter("kpiName");
            Serializable[] params = { kpiId, "delete" };
            result = (String)am.invokeMethod("processKPI", params);
            MessageToken tokens[] = { new MessageToken("KPI", kpiName) };
            OAException confirmMessage = null;
            if (result != null && result.equals("Y")) {
                confirmMessage = 
                        new OAException("CUX", "CUX_DEL_KPI_CONF", tokens, 
                                        OAException.CONFIRMATION, null);
            } else if (result != null && result.equals("N")) {
                confirmMessage = 
                        new OAException("CUX", "CUX_DEL_KPI_USED", tokens, 
                                        OAException.WARNING, null);
            } else {
                confirmMessage = 
                        new OAException("CUX", "CUX_DEL_KPI_ERR", tokens, 
                                        OAException.ERROR, null);
            }
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG&refresh=Y", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) { /*20100909 zs*/
            //am.getOADBTransaction().commit();
            am.invokeMethod("commit");
            OAViewObject vo = (OAViewObject)am.findViewObject("KeyPIVO1");
            vo.executeQuery();
            /*20100909 zs*/
            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_KPI_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }
        //zs
        // Handle refresh LOV constraint object
        if ("refreshLov".equals(pageContext.getParameter("event"))) {
            /*OAMessageChoiceBean type =
            (OAMessageChoiceBean)tableBean.findIndexedChildRecursive("KpiClass");
          String kpiClass = type.getSelectionValue(pageContext);*/
            // kpiClass = pageContext.getParameter("kpiClass");
            //Serializable[] parameters = { kpiClass };
            am.invokeMethod("poplistConstraintClassArea");
        }
        //zs
    }

    /* add new row,added by yang.gang. 2016-10-27*/

    private void AddRow(OAPageContext pageContext, OAWebBean webBean) {
        KeyPIAMImpl am = 
            (KeyPIAMImpl)pageContext.getApplicationModule(webBean);
        OAViewObject vo = am.getKeyPIVO1();
        //vo.executeQuery(); 
        //vo.last();
        /*20090926 zs*/
        if (!vo.isPreparedForExecution()) {
            vo.executeQuery();
        }
        //vo.setNamedWhereClauseParam();
        vo.last();
        /*20090926 zs*/
        Row row = vo.createRow();
        //vo.insertRowAtRangeIndex(vo.getFetchedRowCount(),row);
        int index = vo.getRangeIndexOf(vo.last());
        vo.insertRowAtRangeIndex(index + 1, row);
        //vo.insertRow(row);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        Number kpiId = am.getOADBTransaction().getSequenceValue("CUX_KPI_S");
        //Add by Wei Yi at 2015/08/07
        //get and set organization info
        /*
        int orgId = -1;
        try {
            OracleConnection conn =
                (OracleConnection)am.getOADBTransaction().getJdbcConnection();
            OracleStatement stmt = (OracleStatement)conn.createStatement();
            StringBuffer sqlstmt = new StringBuffer();
            sqlstmt.append("SELECT pspv.organization_id ");
            sqlstmt.append("FROM per_security_profiles_v pspv ");
            sqlstmt.append("WHERE pspv.security_profile_id = fnd_profile.value('PER_SECURITY_PROFILE_ID') ");
            OracleResultSet rset =
                (OracleResultSet)stmt.executeQuery(sqlstmt.toString());
            while (rset.next()) {
                orgId = rset.getInt(1);
                break;
            }
            rset.close();
        } catch (SQLException e) {
            orgId = 0;
        }
        row.setAttribute("Attribute5", orgId);
        */
        //end
        row.setAttribute("KpiId", kpiId);

        vo.setCurrentRow(row);
    }

}
