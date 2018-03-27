/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.rectify.webui;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyAMImpl;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyHeaderResultVOImpl;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyHeaderResultVORowImpl;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyLinesResultVORowImpl;

import java.io.StringReader;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.common.AppsContext;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;

import oracle.apps.frm.publisher.xdo.XDOException;
import oracle.apps.xdo.oa.schema.server.TemplateHelper;

import oracle.cabo.ui.data.DataObject;

import oracle.jbo.RowIterator;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class QhseRectifySearchPGCO extends OAControllerImpl {
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
        CuxRectifyAMImpl pgAM = 
            (CuxRectifyAMImpl)pageContext.getRootApplicationModule();
        String requeryFlag = pageContext.getParameter("RequeryFlag");
        if ("Y".equals(requeryFlag)) {
            CuxRectifyNotifyHeaderResultVOImpl resultVO = 
                pgAM.getCuxRectifyNotifyHeaderResultVO1();
            if (resultVO.isExecuted()) {
                resultVO.executeQuery();
            }
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
        String souceParam = pageContext.getParameter("source");
        if (souceParam == null) {
            souceParam = "THIS#IS#NULL#VALUE";
        }
        String eventParam = pageContext.getParameter(this.EVENT_PARAM);

        CuxRectifyAMImpl pgAM = 
            (CuxRectifyAMImpl)pageContext.getRootApplicationModule();
        CuxRectifyNotifyHeaderResultVOImpl resultVO = 
            pgAM.getCuxRectifyNotifyHeaderResultVO1();
        OAQueryBean querybean = 
            (OAQueryBean)webBean.findChildRecursive("QueryRN");

        if (pageContext.getParameter(querybean.getGoButtonName()) != null) {

            resultVO.setWhereClause(null);
            resultVO.setWhereClauseParams(null);

            String whereClause = "1 = 1 ";
            ArrayList paramers = new ArrayList();
            int paramIndex = 1;

            String searchShowDetail = 
                pageContext.getParameter("SearchShowDetail");
            if (searchShowDetail != null && !"".equals(searchShowDetail)) {
                paramers.add(searchShowDetail);
                paramIndex++;
            } else {
                paramers.add("N");
                paramIndex++;
            }

            String searchDocType = pageContext.getParameter("SearchDocType");
            if (searchDocType != null && !"".equals(searchDocType)) {
                whereClause = 
                        whereClause + " and doc_type = :" + paramIndex + " ";
                paramers.add(searchDocType);
                paramIndex++;
            }

            String searchProjectNum = 
                pageContext.getParameter("SearchProjectNum");
            if (searchProjectNum != null && !"".equals(searchProjectNum)) {
                whereClause = 
                        whereClause + " and project_number = :" + paramIndex + 
                        " ";
                paramers.add(searchProjectNum);
                paramIndex++;
            }

            String searchCiType = pageContext.getParameter("SearchCiType");
            if (searchCiType != null && !"".equals(searchCiType)) {
                whereClause = 
                        whereClause + " and ci_type_id = :" + paramIndex + " ";
                paramers.add(searchCiType);
                paramIndex++;
            }

            String searchWorkLevel = 
                pageContext.getParameter("SearchWorkLevel");
            if (searchWorkLevel != null && !"".equals(searchWorkLevel)) {
                whereClause = 
                        whereClause + " and work_level = :" + paramIndex + " ";
                paramers.add(searchWorkLevel);
                paramIndex++;
            }

            String searchSourceType = 
                pageContext.getParameter("SearchSourceType");
            if (searchSourceType != null && !"".equals(searchSourceType)) {
                whereClause = 
                        whereClause + " and sourec_type = :" + paramIndex + 
                        " ";
                paramers.add(searchSourceType);
                paramIndex++;
            }

            String searchProblemCategory = 
                pageContext.getParameter("SearchProblemCategory");
            if (searchProblemCategory != null && 
                !"".equals(searchProblemCategory)) {
                whereClause = 
                        whereClause + " and problem_category_id = :" + paramIndex + 
                        " ";
                paramers.add(searchProblemCategory);
                paramIndex++;
            }

            String searchDutePerson = 
                pageContext.getParameter("SearchDutePerson");
            if (searchDutePerson != null && !"".equals(searchDutePerson)) {
                whereClause = 
                        whereClause + " and duty_person_name = :" + paramIndex + 
                        " ";
                paramers.add(searchDutePerson);
                paramIndex++;
            }

            String searchCreationDateStart = 
                pageContext.getParameter("SearchCreationDateStart");
            if (searchCreationDateStart != null && 
                !"".equals(searchCreationDateStart)) {
                whereClause = 
                        whereClause + " and creation_date >=to_date(:" + paramIndex + 
                        ",'YYYY-MM-DD') ";
                paramers.add(searchCreationDateStart);
                paramIndex++;
            }

            String searchCreationDateEnd = 
                pageContext.getParameter("SearchCreationDateEnd");
            if (searchCreationDateEnd != null && 
                !"".equals(searchCreationDateEnd)) {
                whereClause = 
                        whereClause + " and creation_date <=to_date(:" + paramIndex + 
                        ",'YYYY-MM-DD') ";
                paramers.add(searchCreationDateEnd);
                paramIndex++;
            }

            String searchCompleteDateStart = 
                pageContext.getParameter("SearchCompleteDateStart");
            if (searchCompleteDateStart != null && 
                !"".equals(searchCompleteDateStart)) {
                whereClause = 
                        whereClause + " and complete_date >=to_date(:" + paramIndex + 
                        ",'YYYY-MM-DD') ";
                paramers.add(searchCompleteDateStart);
                paramIndex++;
            }

            String searchCompleteDateEnd = 
                pageContext.getParameter("SearchCompleteDateEnd");
            if (searchCompleteDateEnd != null && 
                !"".equals(searchCompleteDateEnd)) {
                whereClause = 
                        whereClause + " and complete_date <=to_date(:" + paramIndex + 
                        ",'YYYY-MM-DD') ";
                paramers.add(searchCompleteDateEnd);
                paramIndex++;
            }

            String searchDetailCategory = 
                pageContext.getParameter("SearchDetailCategory");
            if (searchDetailCategory != null && 
                !"".equals(searchDetailCategory)) {
                whereClause = 
                        whereClause + "  AND header_id IN\n" + "       (SELECT cux_rectify_notify_lines.header_id\n" + 
                        "          FROM cux_rectify_notify_lines\n" + 
                        "         WHERE cux_rectify_notify_lines.problem_category = :" + 
                        paramIndex + ") ";
                paramers.add(searchDetailCategory);
                paramIndex++;
            }

            resultVO.setWhereClause(whereClause);
            resultVO.setWhereClauseParams(paramers.toArray());
            resultVO.executeQuery();
            //   System.out.println(resultVO.getWhereClause());
        } //end of if (pageContext.getParameter(querybean.getGoButtonName()) != null) {

        if (pageContext.getParameter("CreateRectifyBTN") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/rectify/webui/QhseRectifyCreatePG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("ExportBTN") != null) {
            this.generatePDF(pageContext, webBean);
        } else if ("Submit".equals(eventParam)) {
            //            String headerId = pageContext.getParameter("SubmitHeaderId");
            //            Number headerIdNumric = new Number(-1);
            //            try {
            //                headerIdNumric = new Number(headerId);
            //            } catch (SQLException e) {
            //                // TODO
            //            }
            //            if(!new Number(-1).equals(headerIdNumric)){
            //                pgAM.startWorkFlow(headerIdNumric);
            //                pgAM.commit();
            //                
            //            }
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/qhse/rectify/webui/QhseRectifyCreatePG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }

    }

    /**
     * 导出excel
     * @param paramOAPageContext
     * @param paramOAWebBean
     */
    private void generatePDF(OAPageContext paramOAPageContext, 
                             OAWebBean paramOAWebBean) {
        OAApplicationModule am = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        OADBTransactionImpl localOADBTransaction = 
            (OADBTransactionImpl)am.getOADBTransaction();
        AppsContext localAppsContext = (localOADBTransaction).getAppsContext();
        Locale paramLocal = localOADBTransaction.getUserLocale();
        DataObject localDataObject = 
            paramOAPageContext.getNamedDataObject("_SessionParameters");
        HttpServletResponse localHttpServletResponse = 
            (HttpServletResponse)localDataObject.selectValue(null, 
                                                             "HttpServletResponse");
        String filenName = "CUXRECITFYRPT.xls";
        try {
            ServletOutputStream localServletOutputStream;
            localServletOutputStream = 
                    localHttpServletResponse.getOutputStream();
            localHttpServletResponse.setContentType("application/vnd.ms-excel");
            localHttpServletResponse.setHeader("Content-Disposition", 
                                               "Attachment; Filename=" + 
                                               filenName);

            String xml = this.generateXml(paramOAPageContext, paramOAWebBean);
            System.out.println(xml);
            localOADBTransaction.writeDiagnostics(this.getClass() + "xml data", 
                                                  xml, 1);
            TemplateHelper.processTemplate(localAppsContext, "CUX", 
                                           "CUXRECITFYRPT", "ZH", "CN", 
                                           new StringReader(xml), 
                                           TemplateHelper.OUTPUT_TYPE_EXCEL, 
                                           null, localServletOutputStream);
            localServletOutputStream.flush();
            localServletOutputStream.close();
        } catch (Exception e) {
            OAException.wrapperException(e);
        }
    }

    /**
     * 生成xml数据源
     * @param paramOAPageContext
     * @param paramOAWebBean
     * @return
     */
    private String generateXml(OAPageContext paramOAPageContext, 
                               OAWebBean paramOAWebBean) {
        String retXmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        retXmlData = retXmlData + "<CUXRECTIFYRPT>";
        CuxRectifyAMImpl rootAM = 
            (CuxRectifyAMImpl)paramOAPageContext.getRootApplicationModule();

        CuxRectifyNotifyHeaderResultVOImpl headerVO = 
            rootAM.getCuxRectifyNotifyHeaderResultVO1();
        int fetchedRowCount = headerVO.getRowCount();
        if (fetchedRowCount > 0) {
            RowSetIterator fetchInfoIter = 
                headerVO.createRowSetIterator("fetchInfoIter");
            fetchInfoIter.setRangeStart(0);
            fetchInfoIter.setRangeSize(fetchedRowCount);
            CuxRectifyNotifyHeaderResultVORowImpl headerRow = null;
            for (int i = 0; i <= fetchedRowCount - 1; i++) {
                headerRow = 
                        (CuxRectifyNotifyHeaderResultVORowImpl)fetchInfoIter.getRowAtRangeIndex(i);
                RowIterator linesIterator = 
                    headerRow.getCuxRectifyNotifyLinesResultVO();
                if (linesIterator != null) {
                    CuxRectifyNotifyLinesResultVORowImpl lineRow = null;

                    while (linesIterator.hasNext()) {
                        lineRow = 
                                (CuxRectifyNotifyLinesResultVORowImpl)linesIterator.next();
                        retXmlData = retXmlData + "<LIST_LINES>";
                        retXmlData = 
                                retXmlData + "<UK>" + headerRow.getHeaderId() + 
                                "_" + lineRow.getLineId() + "</UK>";
                        retXmlData = 
                                retXmlData + "<ProjectOrgName>" + headerRow.getProjectOrgName() + 
                                "</ProjectOrgName>";
                        retXmlData = 
                                retXmlData + "<ProjectNumber>" + headerRow.getProjectNumber() + 
                                "</ProjectNumber>";
                        retXmlData = 
                                retXmlData + "<ProjectName>" + headerRow.getProjectName() + 
                                "</ProjectName>";
                        retXmlData = 
                                retXmlData + "<DutyPersonName>" + headerRow.getDutyPersonName() + 
                                "</DutyPersonName>";
                        retXmlData = 
                                retXmlData + "<ProblemCategoryDis>" + headerRow.getProblemCategoryDis() + 
                                "</ProblemCategoryDis>";
                        retXmlData = 
                                retXmlData + "<StatusDis>" + headerRow.getStatusDis() + 
                                "</StatusDis>";
                        retXmlData = 
                                retXmlData + "<WorkLevelDis>" + headerRow.getWorkLevelDis() + 
                                "</WorkLevelDis>";
                        retXmlData = 
                                retXmlData + "<SourceTypeDis>" + headerRow.getSourceTypeDis() + 
                                "</SourceTypeDis>";
                        retXmlData = 
                                retXmlData + "<CompleteDate>" + headerRow.getCompleteDate() + 
                                "</CompleteDate>";

                        retXmlData = 
                                retXmlData + "<ProblemName>" + lineRow.getProblemName() + 
                                "</ProblemName>";
                        retXmlData = 
                                retXmlData + "<ProblemDesc>" + lineRow.getProblemDesc() + 
                                "</ProblemDesc>";
                        retXmlData = 
                                retXmlData + "<ProDutyPersonName>" + lineRow.getProDutyPersonName() + 
                                "</ProDutyPersonName>";
                        retXmlData = 
                                retXmlData + "<ProblemCategory>" + lineRow.getProblemCategory() + 
                                "</ProblemCategory>";
                        retXmlData = 
                                retXmlData + "<StatusName>" + lineRow.getStatusName() + 
                                "</StatusName>";
                        retXmlData = 
                                retXmlData + "<ProblemCompleteDate>" + lineRow.getCompleteDate() + 
                                "</ProblemCompleteDate>";
                        retXmlData = 
                                retXmlData + "<CheckDate>" + lineRow.getCheckDate() + 
                                "</CheckDate>";
                        retXmlData = 
                                retXmlData + "<CheckRange>" + lineRow.getCheckRange() + 
                                "</CheckRange>";

                        retXmlData = retXmlData + "</LIST_LINES>";
                    }
                }
            }
            fetchInfoIter.closeRowSetIterator();
        }
        retXmlData = retXmlData + "</CUXRECTIFYRPT>";
        return retXmlData;
    }
}
