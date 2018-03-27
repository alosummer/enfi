package cux.oracle.apps.ap.oie.webui;

import java.sql.Types;

import oracle.apps.ap.oie.server.ExpenseReportHeadersVOImpl;
import oracle.apps.ap.oie.server.ExpenseReportHeadersVORowImpl;
import oracle.apps.ap.oie.webui.ButtonsCO;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;

import java.io.StringReader;

import java.sql.CallableStatement;

import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.common.AppsContext;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.xdo.oa.schema.server.TemplateHelper;

import oracle.cabo.ui.data.DataObject;

import oracle.jbo.domain.ClobDomain;

import oracle.sql.CLOB;

import oracle.jbo.domain.Number;

public class CUXButtonsCO extends ButtonsCO {
    public CUXButtonsCO() {
    }

    @Override
    public void processRequest(OAPageContext oapagecontext, 
                               OAWebBean oawebbean) {
        super.processRequest(oapagecontext, oawebbean);
        Boolean displayedFlag = 
            (Boolean)oawebbean.findChildRecursive("IcxPrintablePageButton").getAttributeValue(oapagecontext.getRenderingContext(), 
                                                                                              this.RENDERED_ATTR);

        oawebbean.findChildRecursive("CUX_BUTTON").setAttributeValue(this.RENDERED_ATTR, 
                                                                     displayedFlag);
        oawebbean.findChildRecursive("IcxPrintablePageButton").setAttributeValue(this.RENDERED_ATTR, 
                                                                                 Boolean.FALSE);
    }

    @Override
    public void processFormRequest(OAPageContext oapagecontext, 
                                   OAWebBean oawebbean) {
        preSubmitCheck(oapagecontext, oawebbean);

        super.processFormRequest(oapagecontext, oawebbean);
        if (oapagecontext.getParameter("CUX_BUTTON") != null) {
            this.generatePDF(oapagecontext, oawebbean);
            return;
        }
    }


    private void generatePDF(OAPageContext paramOAPageContext, 
                             OAWebBean paramOAWebBean) {
        OAApplicationModule am = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        OADBTransactionImpl localOADBTransaction = 
            (OADBTransactionImpl)am.getOADBTransaction();
        AppsContext localAppsContext = localOADBTransaction.getAppsContext();
        Locale paramLocal = localOADBTransaction.getUserLocale();
        DataObject localDataObject = 
            paramOAPageContext.getNamedDataObject("_SessionParameters");
        HttpServletResponse localHttpServletResponse = 
            (HttpServletResponse)localDataObject.selectValue(null, 
                                                             "HttpServletResponse");
        OAViewObject headerVO = 
            (OAViewObject)am.findViewObject("ExpenseReportHeadersVO");
        ExpenseReportHeadersVORowImpl row = 
            (ExpenseReportHeadersVORowImpl)headerVO.getCurrentRow();
        String filenName = row.getInvoiceNum() + ".pdf";
        String expType = row.getAttribute2();
        String templateName = null;
        if ("国外出差报销".equals(expType)) {
            templateName = "CUX_AP_EXP_OUT_TEMP";
        } else if ("国内出差报销".equals(expType)) {
            templateName = "CUX_AP_EXP_IN_TEMP";
        } else {
            templateName = "CUX_AP_EXP_NOR_TEMP";
        }


        try {
            ServletOutputStream localServletOutputStream;
            localServletOutputStream = 
                    localHttpServletResponse.getOutputStream();
            localHttpServletResponse.setContentType("application/pdf");
            localHttpServletResponse.setHeader("Content-Disposition", 
                                               "Attachment; Filename=" + 
                                               filenName);
            CLOB localCLOB = 
                this.generateXML(paramOAPageContext, paramOAWebBean);
            ClobDomain localClobDomain = new ClobDomain(localCLOB);
            String xml = localClobDomain.toString();
            //            xml = xml.substring(21, xml.length() - 1);
            //            xml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xml;
            localOADBTransaction.writeDiagnostics(this.getClass() + "xml data", 
                                                  xml, 1);
            TemplateHelper.processTemplate(localAppsContext, "CUX", 
                                           templateName, "ZH", "CN", 
                                           new StringReader(xml), 
                                           TemplateHelper.OUTPUT_TYPE_PDF, 
                                           null, localServletOutputStream);
            localServletOutputStream.flush();
            localServletOutputStream.close();
        } catch (Exception e) {
            OAException.wrapperException(e);
        }
    }

    private CLOB generateXML(OAPageContext paramOAPageContext, 
                             OAWebBean paramOAWebBean) {
        //   GENERATE_EXP_REPORT
        CallableStatement localCallableStatement = null;
        OAApplicationModule am = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        OADBTransactionImpl localOADBTransaction = 
            (OADBTransactionImpl)am.getOADBTransaction();
        OAViewObject headerVO = 
            (OAViewObject)am.findViewObject("ExpenseReportHeadersVO");
        OARow row = (OARow)headerVO.getCurrentRow();
        if (row == null) {
            row = (OARow)headerVO.first();
        }
        Number reportHeaderId = (Number)row.getAttribute("ReportHeaderId");
        CLOB localCLOB = null;
        try {
            localCallableStatement = 
                    localOADBTransaction.createCallableStatement("begin cux_ap_exp_pkg.generate_exp_report(:1,:2); end; ", 
                                                                 1);
            localCallableStatement.setInt(1, reportHeaderId.intValue());
            localCallableStatement.registerOutParameter(2, 2005);
            localCallableStatement.execute();
            localCLOB = (CLOB)localCallableStatement.getObject(2);

        } catch (Exception e) {
            OAException.wrapperException(e);
        } finally {
            try {
                if (localCallableStatement != null)
                    localCallableStatement.close();
            } catch (Exception localException10) {
                ;
            }
        }
        return localCLOB;
    }

    /**
     * 对submit时进行预判断，需要判断项目类型等
     * @param oapagecontext
     * @param oawebbean
     */
    public void preSubmitCheck(OAPageContext oapagecontext, 
                               OAWebBean oawebbean) {
        String reportHeaderId;
        OAApplicationModule am = oapagecontext.getRootApplicationModule();

        if (oapagecontext.getParameter("OIESubmit") != null) {
            reportHeaderId = 
                    am.findViewObject("ExpenseReportHeadersVO").first().getAttribute("ReportHeaderId") + 
                    "";
            OADBTransaction tx = am.getOADBTransaction();
            String retcode = "S";
            String errbuf = null;
            String retStr = new String();
            CallableStatement cs = 
                tx.createCallableStatement("call cux_ap_exp_pkg.check_expense_report(?,?,?)", 
                                           1);
            try {
                cs.setString(1, reportHeaderId);
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.registerOutParameter(3, Types.VARCHAR);
                cs.execute();
                retcode = cs.getString(2);
                errbuf = cs.getString(3);
            } catch (Exception e) {
                System.out.println(e.toString());
                retStr = 
                        (new StringBuilder()).append(retStr).append("; ").append(e.toString()).toString();
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            if ("E".equals(retcode)) {
                throw new OAException(errbuf);
            } else if ("W".equals(retcode)) {
                oapagecontext.putDialogMessage(new OAException(errbuf, 
                                                               OAException.INFORMATION));
            }

        }

    }
}
