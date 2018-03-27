package cux.oracle.apps.ap.oie.webui;

import com.sun.java.util.collections.ArrayList;
import com.sun.java.util.collections.List;


import java.io.Serializable;

import java.sql.CallableStatement;

import java.sql.Types;

import oracle.apps.ap.oie.server.ExpenseTypesVOImpl;
import oracle.apps.ap.oie.server.ReceiptBasedLinesVOImpl;
import oracle.apps.ap.oie.server.ReceiptBasedLinesVORowImpl;
import oracle.apps.ap.oie.server.WebExpensesAMImpl;
import oracle.apps.ap.oie.webui.CashAndOtherCO;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

public class CuxCashAndOtherCO extends CashAndOtherCO {
    public CuxCashAndOtherCO() {
    }

    @Override
    public void processRequest(OAPageContext paramOAPageContext, 
                               OAWebBean paramOAWebBean) {
        super.processRequest(paramOAPageContext, paramOAWebBean);
        WebExpensesAMImpl localOAApplicationModule = 
            (WebExpensesAMImpl)paramOAPageContext.getRootApplicationModule();
        OAViewObject headerVO = 
            (OAViewObject)localOAApplicationModule.findViewObject("ExpenseReportHeadersVO");
        OARow headerRow = (OARow)headerVO.getCurrentRow();
        if (headerRow == null) {
            headerRow = (OARow)headerVO.first();
        }


        String projectNum = (String)headerRow.getAttribute("Attribute1");

        ExpenseTypesVOImpl webParamVO = 
            localOAApplicationModule.getReceiptBasedExpTypesVO();
        String whereClause = webParamVO.getWhereClause();
        System.out.println();
        if (!"".equals(whereClause) && whereClause != null) {
            webParamVO.addWhereClause(" and  parameter_id in (SELECT ap_expense_report_params_all.parameter_id \n" + 
                                      "                                     FROM pa_projects_all               ppa, \n" + 
                                      "                                           pa_segment_value_lookup_sets,\n" + 
                                      "                                             pa_segment_value_lookups,\n" + 
                                      "                                            ap_expense_report_params_all\n" + 
                                      "                                      WHERE 1=1\n" + 
                                      "                                        AND ppa.attribute10 =\n" + 
                                      "                                            pa_segment_value_lookup_sets.segment_value_lookup_set_id\n" + 
                                      "                                         AND pa_segment_value_lookup_sets.segment_value_lookup_set_id =\n" + 
                                      "                                             pa_segment_value_lookups.segment_value_lookup_set_id\n" + 
                                      "                                         AND ap_expense_report_params_all.pa_expenditure_type = \n" + 
                                      "                                             pa_segment_value_lookups.segment_value_lookup\n" + 
                                      "                                         AND ppa.segment1 = '" + 
                                      projectNum + "')");
        } else {
            webParamVO.addWhereClause("  parameter_id in (SELECT ap_expense_report_params_all.parameter_id \n" + 
                                      "                                     FROM pa_projects_all               ppa, \n" + 
                                      "                                           pa_segment_value_lookup_sets,\n" + 
                                      "                                             pa_segment_value_lookups,\n" + 
                                      "                                            ap_expense_report_params_all\n" + 
                                      "                                      WHERE 1=1\n" + 
                                      "                                        AND ppa.attribute10 =\n" + 
                                      "                                            pa_segment_value_lookup_sets.segment_value_lookup_set_id\n" + 
                                      "                                         AND pa_segment_value_lookup_sets.segment_value_lookup_set_id =\n" + 
                                      "                                             pa_segment_value_lookups.segment_value_lookup_set_id\n" + 
                                      "                                         AND ap_expense_report_params_all.pa_expenditure_type = \n" + 
                                      "                                             pa_segment_value_lookups.segment_value_lookup\n" + 
                                      "                                         AND ppa.segment1 = '" + 
                                      projectNum + "')");
        }

        webParamVO.executeQuery();
    }

    @Override
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        String eventParam = pageContext.getParameter(this.EVENT_PARAM);
        String souceParam = pageContext.getParameter("source");
        if ("OIENavBar".startsWith(souceParam) && "goto".equals(eventParam)) {
            this.checkInputData(pageContext, webBean);
        }
    }


    private void checkInputData(OAPageContext pageContext, OAWebBean webBean) {
        Serializable[] arrayOfSerializable = 
            new Serializable[] { null, Boolean.FALSE };
        Class[] localObject3 = new Class[] { String.class, Boolean.class };
        pageContext.getRootApplicationModule().invokeMethod("saveExpenseReport", 
                                                            arrayOfSerializable, 
                                                            localObject3);

        OAApplicationModuleImpl rootAM = 
            (OAApplicationModuleImpl)pageContext.getRootApplicationModule();
        ReceiptBasedLinesVOImpl lineVO = 
            (ReceiptBasedLinesVOImpl)rootAM.findViewObject("CashAndOtherLinesVO");
        lineVO.removeEmptyRows();
        int fetchedRowCount = lineVO.getRowCount();
        if (fetchedRowCount > 0) {
            RowSetIterator autoLoopIter = 
                lineVO.createRowSetIterator("autoLoopIter");
            autoLoopIter.setRangeStart(0);
            autoLoopIter.setRangeSize(fetchedRowCount);
            ReceiptBasedLinesVORowImpl lineRow = null;
            List peerExceptions = new ArrayList();
            for (int i = fetchedRowCount - 1; i >= 0; i--) {
                lineRow = 
                        (ReceiptBasedLinesVORowImpl)autoLoopIter.getRowAtRangeIndex(i);
                String message = 
                    this.checkOneLines(pageContext, webBean, lineRow.getReportLineId());
                if (!"S".equals(message)) {
                    if (message != null && !message.startsWith("##")) {
                        if (lineRow.getAttribute14() == null || 
                            "".equals(lineRow.getAttribute14())) {
                            OAException e = 
                                new OAException("行" + lineRow.getStringLineNumber() + 
                                                ": " + message + 
                                                "请在超标说明里说明原因");
                            peerExceptions.add(e);
                        }
                    } else {
                        OAException e = 
                            new OAException("行" + lineRow.getStringLineNumber() + 
                                            ": " + message.substring(2));
                        peerExceptions.add(e);
                    }
                }
            }
            autoLoopIter.closeRowSetIterator();
            if (peerExceptions.size() != 0) {
                OAException.raiseBundledOAException(peerExceptions);
            }
        }
    }

    /**
     * 
     * @param pageContext
     * @param webBean
     * @param LineId
     * @return
     */
    private String checkOneLines(OAPageContext pageContext, OAWebBean webBean, 
                                 Number LineId) {
        OAApplicationModuleImpl rootAM = 
            (OAApplicationModuleImpl)pageContext.getRootApplicationModule();
        String retValue = null;
        OADBTransaction tx = rootAM.getOADBTransaction();
        CallableStatement cs = 
            tx.createCallableStatement("begin :1 := cux_ap_exp_pkg.validate_common_lines(p_exp_line_id => :2);end;", 
                                       1);
        try {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setInt(2, LineId.intValue());
            cs.execute();
            retValue = cs.getString(1);
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
        return retValue;
    }

    public static void main(String[] args) {
        String message = "##qiangzhi";
        if (!message.startsWith("##")) {
            System.out.println(11);
        } else {
            System.out.println(message.substring(2));
        }
    }
}
