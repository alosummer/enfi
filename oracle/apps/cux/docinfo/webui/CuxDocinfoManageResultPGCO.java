/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.docinfo.webui;

import cux.oracle.apps.cux.docinfo.server.CuxDocinfoManageAMImpl;
import cux.oracle.apps.cux.docinfo.server.CuxDocinfoManageResultVOImpl;
import cux.oracle.apps.cux.docinfo.server.CuxDocinfoManageResultVORowImpl;
import cux.oracle.apps.cux.qhse.quality.server.CuxProjectResultVOImpl;
import cux.oracle.apps.cux.qhse.quality.server.CuxQualityAMImpl;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyAMImpl;
import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyHeaderResultVOImpl;

import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyHeaderResultVORowImpl;
import cux.oracle.apps.cux.qhse.rectify.server.CuxRectifyNotifyLinesResultVORowImpl;

import java.io.StringReader;

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

import oracle.apps.xdo.oa.schema.server.TemplateHelper;

import oracle.cabo.ui.data.DataObject;

import oracle.jbo.RowIterator;
import oracle.jbo.RowSetIterator;

/**
 * Controller for ...
 */
public class CuxDocinfoManageResultPGCO extends OAControllerImpl {
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
        CuxDocinfoManageAMImpl pgAM = 
            (CuxDocinfoManageAMImpl)pageContext.getRootApplicationModule();
        String requeryFlag = pageContext.getParameter("RequeryFlag");
        if ("Y".equals(requeryFlag)) {
            CuxDocinfoManageResultVOImpl resultVO = 
                pgAM.getCuxDocinfoManageResultVO1();
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

        CuxDocinfoManageAMImpl pgAM = 
            (CuxDocinfoManageAMImpl)pageContext.getRootApplicationModule();
        CuxDocinfoManageResultVOImpl resultVO = 
            pgAM.getCuxDocinfoManageResultVO1();

        OAQueryBean querybean = 
            (OAQueryBean)webBean.findChildRecursive("QueryRN");

        if (pageContext.getParameter(querybean.getGoButtonName()) != null) {

            resultVO.setWhereClause(null);
            resultVO.setWhereClauseParams(null);

            String whereClause = "1 = 1 ";
            ArrayList paramers = new ArrayList();
            int paramIndex = 1;


            String searchDocType = pageContext.getParameter("SearchDocType");
            if (searchDocType != null && !"".equals(searchDocType)) {
                whereClause = 
                        whereClause + " and document_type_name = :" + paramIndex + 
                        " ";
                paramers.add(searchDocType);
                paramIndex++;
            }

            String searchCountryName = 
                pageContext.getParameter("SearchCountryName");
            if (searchCountryName != null && !"".equals(searchCountryName)) {
                whereClause = 
                        whereClause + " and country_name = :" + paramIndex + 
                        " ";
                paramers.add(searchCountryName);
                paramIndex++;
            }
            /*改为建立日期*/
            String searchArchiveDateStart = 
                pageContext.getParameter("SearchArchiveDateStart");
            if (searchArchiveDateStart != null && 
                !"".equals(searchArchiveDateStart)) {
                whereClause = 
                        whereClause + " and creation_date >= to_date(:" + paramIndex + 
                        " ,'YYYY-MM-DD') ";
                paramers.add(searchArchiveDateStart);
                paramIndex++;
            }
            /*改为建立日期*/
            String searchArchiveDateEnd = 
                pageContext.getParameter("SearchArchiveDateEnd");
            if (searchArchiveDateEnd != null && 
                !"".equals(searchArchiveDateEnd)) {
                whereClause = 
                        whereClause + " and creation_date <=to_date(:" + paramIndex + 
                        ",'YYYY-MM-DD') ";
                paramers.add(searchArchiveDateEnd);
                paramIndex++;
            }

            String searchProjectName = 
                pageContext.getParameter("SearchProjectName");
            if (searchProjectName != null && !"".equals(searchProjectName)) {
                whereClause = 
                        whereClause + " and project_name like :" + paramIndex + 
                        " ";
                paramers.add("%" + searchProjectName + "%");
                paramIndex++;
            }

            String searchRelateCorpName = 
                pageContext.getParameter("SearchRelateCorpName");
            if (searchRelateCorpName != null && 
                !"".equals(searchRelateCorpName)) {
                whereClause = 
                        whereClause + " and relation_corp_name like :" + paramIndex + 
                        " ";
                paramers.add("%" + searchRelateCorpName + "%");
                paramIndex++;
            }

            String searchLang = pageContext.getParameter("SearchLang");
            if (searchLang != null && !"".equals(searchLang)) {
                whereClause = 
                        whereClause + " and lang_name = :" + paramIndex + " ";
                paramers.add(searchLang);
                paramIndex++;
            }

            String searchIsSigned = pageContext.getParameter("SearchIsSigned");
            if (searchIsSigned != null && !"".equals(searchIsSigned)) {
                whereClause = 
                        whereClause + " and is_signed = :" + paramIndex + " ";
                paramers.add(searchIsSigned);
                paramIndex++;
            }
            String searchCopyType = pageContext.getParameter("SearchCopyType");
            if (searchCopyType != null && !"".equals(searchCopyType)) {
                whereClause = 
                        whereClause + " and doc_copy_name = :" + paramIndex + 
                        " ";
                paramers.add(searchCopyType);
                paramIndex++;
            }
            String searchSubmitPerson = 
                pageContext.getParameter("SearchSubmitPerson");
            if (searchSubmitPerson != null && !"".equals(searchSubmitPerson)) {
                whereClause = 
                        whereClause + " and submitter_person_name = :" + paramIndex + 
                        " ";
                paramers.add(searchSubmitPerson);
                paramIndex++;
            }

            String searchAreaAlias = 
                pageContext.getParameter("SearchAreaAlias");
            if (searchAreaAlias != null && !"".equals(searchAreaAlias)) {
                whereClause = 
                        whereClause + " and area_alias = :" + paramIndex + " ";
                paramers.add(searchAreaAlias);
                paramIndex++;
            }

            String searchOtherCorpName = 
                pageContext.getParameter("SearchOtherCorpName");
            if (searchOtherCorpName != null && 
                !"".equals(searchOtherCorpName)) {
                whereClause = 
                        whereClause + " and other_corp_name like :" + paramIndex + 
                        " ";
                paramers.add("%" + searchOtherCorpName + "%");
                paramIndex++;
            }

            String searchIsArchive = 
                pageContext.getParameter("SearchIsArchive");
            if (searchIsArchive != null && !"".equals(searchIsArchive)) {
                whereClause = 
                        whereClause + " and is_archive = :" + paramIndex + " ";
                paramers.add(searchIsArchive);
                paramIndex++;
            }

            String searchIsEndActive = 
                pageContext.getParameter("SearchIsEndActive");
            if (searchIsEndActive != null && !"".equals(searchIsEndActive)) {
                whereClause = 
                        whereClause + " and is_end_active = :" + paramIndex + 
                        " ";
                paramers.add(searchIsEndActive);
                paramIndex++;
            }

            String searchIsEvlSheet = 
                pageContext.getParameter("SearchIsEvlSheet");
            if (searchIsEvlSheet != null && !"".equals(searchIsEvlSheet)) {
                whereClause = 
                        whereClause + " and is_evl_sheet = :" + paramIndex + 
                        " ";
                paramers.add(searchIsEvlSheet);
                paramIndex++;
            }


            resultVO.setWhereClause(whereClause);
            resultVO.setWhereClauseParams(paramers.toArray());
            resultVO.executeQuery();

        }


        if (pageContext.getParameter("CreateDocinfoBTN") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/docinfo/webui/CuxDocinfoManageCreatePG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("ExportBTN") != null) {
            this.generatePDF(pageContext, webBean);
        } else if ("update".equals(eventParam)) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/docinfo/webui/CuxDocinfoManageCreatePG", 
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
        String filenName = "CUXDOCINFO.xls";
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
                                           "CUXDOCINFO", "ZH", "CN", 
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
        retXmlData = retXmlData + "<CUXDOCINFO>";
        CuxDocinfoManageAMImpl rootAM = 
            (CuxDocinfoManageAMImpl)paramOAPageContext.getRootApplicationModule();

        CuxDocinfoManageResultVOImpl resultVO = 
            rootAM.getCuxDocinfoManageResultVO1();
        int fetchedRowCount = resultVO.getRowCount();
        if (fetchedRowCount > 0) {
            RowSetIterator fetchInfoIter = 
                resultVO.createRowSetIterator("fetchInfoIter");
            fetchInfoIter.setRangeStart(0);
            fetchInfoIter.setRangeSize(fetchedRowCount);
            CuxDocinfoManageResultVORowImpl resultRow = null;
            for (int i = 0; i <= fetchedRowCount - 1; i++) {
                resultRow = 
                        (CuxDocinfoManageResultVORowImpl)fetchInfoIter.getRowAtRangeIndex(i);

                retXmlData = retXmlData + "<LIST_LINES>";
                retXmlData = 
                        retXmlData + "<UK>" + resultRow.getDocId() + "</UK>";
                retXmlData = 
                        retXmlData + "<DocNumber>" + (resultRow.getDocNumber() == 
                                                      null ? "" : 
                                                      resultRow.getDocNumber()) + 
                        "</DocNumber>";
                retXmlData = 
                        retXmlData + "<ProjectName>" + resultRow.getProjectName() + 
                        "</ProjectName>";
                retXmlData = 
                        retXmlData + "<RelationCorpName>" + resultRow.getRelationCorpName() + 
                        "</RelationCorpName>";
                retXmlData = 
                        retXmlData + "<LangName>" + resultRow.getLangName() + 
                        "</LangName>";
                retXmlData = 
                        retXmlData + "<DocumentTypeName>" + resultRow.getDocumentTypeName() + 
                        "</DocumentTypeName>";
                retXmlData = 
                        retXmlData + "<IsSigned>" + (resultRow.getIsSignedName() == 
                                                     null ? "" : 
                                                     resultRow.getIsSignedName()) + 
                        "</IsSigned>";
                retXmlData = 
                        retXmlData + "<DocCopyName>" + resultRow.getDocCopyName() + 
                        "</DocCopyName>";

                retXmlData = 
                        retXmlData + "<SubmitterPersonName>" + resultRow.getSubmitterPersonName() + 
                        "</SubmitterPersonName>";
                retXmlData = 
                        retXmlData + "<CreationDateName>" + resultRow.getCreationDateName() + 
                        "</CreationDateName>";


                retXmlData = 
                        retXmlData + "<Remark>" + (resultRow.getRemark() == 
                                                   null ? "" : 
                                                   resultRow.getRemark()) + 
                        "</Remark>";

                retXmlData = 
                        retXmlData + "<CountryName>" + resultRow.getCountryName() + 
                        "</CountryName>";
                retXmlData = 
                        retXmlData + "<OtherCorpName>" + (resultRow.getOtherCorpName() == 
                                                          null ? "" : 
                                                          resultRow.getOtherCorpName()) + 
                        "</OtherCorpName>";
                retXmlData = 
                        retXmlData + "<AreaAliasName>" + (resultRow.getAreaAliasName() == 
                                                          null ? "" : 
                                                          resultRow.getAreaAliasName()) + 
                        "</AreaAliasName>";
                retXmlData = 
                        retXmlData + "<IsArchive>" + resultRow.getIsArchive() + 
                        "</IsArchive>";

                retXmlData = 
                        retXmlData + "<ArchiveDate>" + (resultRow.getArchiveDate() == 
                                                        null ? "" : 
                                                        resultRow.getArchiveDate()) + 
                        "</ArchiveDate>";
                retXmlData = 
                        retXmlData + "<IsEndActive>" + resultRow.getIsEndActive() + 
                        "</IsEndActive>";
                retXmlData = 
                        retXmlData + "<EndActiveDate>" + (resultRow.getEndActiveDate() == 
                                                          null ? "" : 
                                                          resultRow.getEndActiveDate()) + 
                        "</EndActiveDate>";

                retXmlData = retXmlData + "</LIST_LINES>";
            }
            fetchInfoIter.closeRowSetIterator();
        }
        retXmlData = retXmlData + "</CUXDOCINFO>";
        System.out.println(retXmlData);
        return retXmlData;

    }

    private String getIsSign(String flag) {
        if ("Y".equals(flag)) {
            return "是";
        } else {
            return "否";
        }
    }
}
