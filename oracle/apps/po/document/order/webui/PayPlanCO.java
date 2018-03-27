/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.po.document.order.webui;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;

import oracle.jbo.domain.Number;

import java.text.DecimalFormat;

import javax.servlet.jsp.PageContext;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAImageBean;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.form.OATextInputBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAddTableRowBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableFooterBean;
import oracle.apps.po.tax.server.ManageTaxPoHeadersVOImpl;
import oracle.apps.po.tax.server.ManageTaxPoHeadersVORowImpl;

import oracle.cabo.ui.validate.DecimalValidater;

import oracle.jbo.Row;

/**
 * Controller for ...
 */
public class PayPlanCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void setHeader(OAPageContext pageContext, OAWebBean webBean, 
                          OAApplicationModule am) {
        String poNum = (String)pageContext.getTransactionValue("PoNum");
        String desc = (String)pageContext.getTransactionValue("Description");
        String totalStr = (String)pageContext.getTransactionValue("Total");
        String poHdrId = (String)pageContext.getTransactionValue("poHdrId");
        DecimalFormat df = new DecimalFormat("0.00");
        double total;
        if ("".equals(totalStr) || totalStr == null) {
            total = 0;
        } else {
            total = Double.parseDouble(totalStr);
        }
        //get the tax total
        double taxTotal;


        /*//???????????
        OADBTransaction transaction = pageContext.getRootApplicationModule().getOADBTransaction();
        OAApplicationModule taxAm;
        // Look to see if the AM has already been created in this transaction. If not,
        // create it.


        taxAm = (OAApplicationModule)transaction.findApplicationModule("TaxAM");
        if (taxAm == null)
        {
          taxAm =
          (OAApplicationModule)transaction.createApplicationModule("TaxAM",
             "oracle.apps.po.tax.server.TaxAM");
        }

        // Now, we use a lightweight "validation" view object to see if a supplier exists
        // with the given name.
        ManageTaxPoHeadersVOImpl taxVo = (ManageTaxPoHeadersVOImpl)taxAm.findViewObject("ManageTaxPoHeadersVO");
       try
       {
       taxVo.initQuery(new Number(poHdrId));
       ManageTaxPoHeadersVORowImpl taxRow = (ManageTaxPoHeadersVORowImpl)taxVo.first();
       taxTotal = Double.parseDouble(taxRow.getTotalTax().toString());
       if (Double.isNaN(taxTotal)){
           taxTotal = 0;
       }
       }
       catch(Exception e){taxTotal = 0;}
       //???????????
       */
        //
        //????????????
        String SpoHdrId = (String)pageContext.getTransactionValue("poHdrId");
        String Sresult = GetPoTax(am, SpoHdrId);
        //System.out.println("begin poHdrId="+poHdrId);
        taxTotal = Double.parseDouble(Sresult);
        //????????????

        double plannedTotal = (Double)am.invokeMethod("getPlannedTotal");
        if (Double.isNaN(plannedTotal)) {
            plannedTotal = 0;
        }
        if (Double.isNaN(plannedTotal)) {
            plannedTotal = 0;
        }
        double unplannedTotal = total + taxTotal - plannedTotal;
        double poSum = total + taxTotal;
        pageContext.putTransactionValue("PoSum", String.valueOf(poSum));
        //set title
        OAMessageStyledTextBean poNumBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PoNum");
        poNumBean.setText(pageContext, poNum);
        OAMessageStyledTextBean poDescBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PoDesc");
        poDescBean.setText(pageContext, desc);
        OAMessageStyledTextBean poTotalBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PoTotal");
        poTotalBean.setText(pageContext, df.format(total));
        OAMessageStyledTextBean poTaxTotalBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PoTaxTotal");
        poTaxTotalBean.setText(pageContext, df.format(taxTotal));
        OAMessageStyledTextBean poSumBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PoSum");
        poSumBean.setText(pageContext, df.format(poSum));
        OAMessageStyledTextBean plannedTotalBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PlannedAmt");
        plannedTotalBean.setText(pageContext, df.format(plannedTotal));
        OAMessageStyledTextBean unplannedTotalBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("UnplannedAmt");
        unplannedTotalBean.setText(pageContext, df.format(unplannedTotal));


    }

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);


        //2015��������Ӹ���ƻ�������λС��
        DecimalValidater formatter = new DecimalValidater();
        formatter.setMaxValue(10);
        formatter.setMinValue(2);
        webBean.findChildRecursive("NewAmtCol").setAttributeValue(ON_SUBMIT_VALIDATER_ATTR, 
                                                                  formatter);
        //2015��������Ӹ���ƻ�������λС��
        //String poHdrId = pageContext.getParameter("PoHeaderId");

        /*  String poHdrId =
            new String("8013"); //pageContext.getParameter("PoHeaderId");
        String poNum =
            new String("ENFI20170105002"); //pageContext.getParameter("PoNum");
        String desc = pageContext.getParameter("111");
        String totalStr =
            new String("46800"); //pageContext.getParameter("Total");
        */
        String poHdrId = pageContext.getParameter("PoHeaderId");
        String poNum = pageContext.getParameter("PoNum");
        String desc = pageContext.getParameter("Description");
        String totalStr = pageContext.getParameter("Total");

        if (poHdrId != null) {
            pageContext.putTransactionValue("poHdrId", poHdrId);
            pageContext.putTransactionValue("PoNum", poNum);
            pageContext.putTransactionValue("Description", desc);
            pageContext.putTransactionValue("Total", totalStr);
        }
        //
        Serializable[] paras = { poHdrId };
        am.invokeMethod("initPayPlan", paras);
        setHeader(pageContext, webBean, am);
        //
        String secGroup = pageContext.getProfile("SECURITYGROUP");
        //      String secGroup = "ALL";//pageContext.getProfile("SECURITYGROUP");      
        if (secGroup != null && "ALL".equals(secGroup.trim())) {
            OAMessageChoiceBean payType = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("PayType");
            payType.setAttributeValue(OAMessageChoiceBean.READ_ONLY_ATTR, 
                                      Boolean.TRUE);
            OAMessageTextInputBean initAmt = 
                (OAMessageTextInputBean)webBean.findIndexedChildRecursive("InitAmt");
            initAmt.setAttributeValue(OAMessageTextInputBean.READ_ONLY_ATTR, 
                                      Boolean.TRUE);
            OAMessageDateFieldBean initPayDate = 
                (OAMessageDateFieldBean)webBean.findIndexedChildRecursive("InitPayDate");
            initPayDate.setAttributeValue(OAMessageDateFieldBean.READ_ONLY_ATTR, 
                                          Boolean.TRUE);
            OAMessageTextInputBean newAmt = 
                (OAMessageTextInputBean)webBean.findIndexedChildRecursive("NewAmt");
            newAmt.setAttributeValue(OAMessageTextInputBean.READ_ONLY_ATTR, 
                                     Boolean.TRUE);
            OAMessageDateFieldBean newPayDate = 
                (OAMessageDateFieldBean)webBean.findIndexedChildRecursive("NewPayDate");
            newPayDate.setAttributeValue(OAMessageDateFieldBean.READ_ONLY_ATTR, 
                                         Boolean.TRUE);
            OAMessageTextInputBean memo = 
                (OAMessageTextInputBean)webBean.findIndexedChildRecursive("Memo");
            memo.setAttributeValue(OAMessageTextInputBean.READ_ONLY_ATTR, 
                                   Boolean.TRUE);
            OAImageBean del = 
                (OAImageBean)webBean.findIndexedChildRecursive("Delete");
            del.setAttributeValue(OAImageBean.RENDERED_ATTR, Boolean.FALSE);
            OAAdvancedTableBean advTable = 
                (OAAdvancedTableBean)webBean.findIndexedChildRecursive("MainRN");
            OATableFooterBean tableFooter = 
                (OATableFooterBean)advTable.getFooter();
            OAAddTableRowBean addRow = 
                (OAAddTableRowBean)tableFooter.findIndexedChildRecursive("addTableRow1");
            addRow.setAttributeValue(OAAddTableRowBean.RENDERED_ATTR, 
                                     Boolean.FALSE);
            OASubmitButtonBean addNewVersion = 
                (OASubmitButtonBean)tableFooter.findIndexedChildRecursive("AddNewVersion");
            addNewVersion.setAttributeValue(OASubmitButtonBean.RENDERED_ATTR, 
                                            Boolean.FALSE);
            OASubmitButtonBean apply = 
                (OASubmitButtonBean)tableFooter.findIndexedChildRecursive("Apply");
            apply.setAttributeValue(OASubmitButtonBean.RENDERED_ATTR, 
                                    Boolean.FALSE);
            OASubmitButtonBean calculate = 
                (OASubmitButtonBean)tableFooter.findIndexedChildRecursive("CalculateBTN");
            calculate.setAttributeValue(OASubmitButtonBean.RENDERED_ATTR, 
                                        Boolean.FALSE);
            OASubmitButtonBean cancel = 
                (OASubmitButtonBean)tableFooter.findIndexedChildRecursive("Cancel");
            cancel.setAttributeValue(OASubmitButtonBean.RENDERED_ATTR, 
                                     Boolean.FALSE);

        }
        //2009-08-01??????????????????????
        int pay_check = 1;
        int ou_id = -1;
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_payplan_check_pkg.check_delete(?,?)", 
                                                1);
        try {
            cs.setString(1, poHdrId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeQuery();
            pay_check = cs.getInt(2);
            ou_id = pageContext.getOrgId();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //if(pay_check == 0&&ou_id == 1076){
        if (pay_check == 0) {
            OAImageBean del = 
                (OAImageBean)webBean.findIndexedChildRecursive("Delete");

            del.setAttributeValue(OAImageBean.DISABLED_ATTR, Boolean.TRUE);
        }
        //end
    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public

    void processFormRequest(OAPageContext pageContext, OAWebBean webBean) {

        super.processFormRequest(pageContext, webBean);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);


        if (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM))) {
            String poHdrId = 
                (String)pageContext.getTransactionValue("poHdrId");
            Serializable[] paras = { poHdrId };
            am.invokeMethod("createPayPlan", paras);
            am.invokeMethod("setLineNum");
            am.invokeMethod("setVersionId");
            am.invokeMethod("setPoHdrId", paras);
        } else if ("delete".equals(pageContext.getParameter(EVENT_PARAM))) {
            //?????��??????? 
            //OAViewObject vo = (OAViewObject)am.findViewObject("PayPlanVO1");
            // double paidamt=0; 

            // OAImageBean del = (OAImageBean)webBean.findIndexedChildRecursive("Delete");
            //int index=del.SELECTED_KEY.;
            //log("index="+index);

            String lineId = pageContext.getParameter("LineId");

            log("lineId=" + lineId);
            String a10 = "";
            // a10 =get_pay_amounts(am,lineId);
            a10 = isPayMatchExists(am, lineId);
            double paidamt = Double.parseDouble(a10);
            log("a10=" + a10);
            if (paidamt > 0) //????????
            {
                String poHeaderId = "";
                MessageToken[] tokens = 
                { new MessageToken("EMP_NAME", poHeaderId), 
                  new MessageToken("EMP_NUMBER", poHeaderId) };
                OAException confirmMessage = 
                    new OAException("FND", "PO_PAY_PLAN_DELETE_WARN", tokens, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
                return;
            }

            //?????��???????


            String lineNum = pageContext.getParameter("LineNum");

            OAException mainMessage = 
                new OAException("FND", "AM_PARAMREGSTRY_DELETE_WARN");

            // Note that even though we're going to make our Yes/No buttons submit a
            // form, we still need some non-null value in the constructor's Yes/No 
            // URL parameters for the buttons to render, so we just pass empty 
            // Strings for this.
            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");

            // Always use Message Dictionary for any Strings you want to display.
            String yes = pageContext.getMessage("AK", "FWK_TBX_T_YES", null);
            String no = pageContext.getMessage("AK", "FWK_TBX_T_NO", null);

            // We set this value so the code that handles this button press is 
            // descriptive.
            dialogPage.setOkButtonItemName("DeleteYesButton");

            // The following configures the Yes/No buttons to be submit buttons,
            // and makes sure that we handle the form submit in the originating
            // page (the "Employee" summary) so we can handle the "Yes"
            // button selection in this controller.
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);

            // Now set our Yes/No labels instead of the default OK/Cancel.
            dialogPage.setOkButtonLabel(yes);
            dialogPage.setNoButtonLabel(no);

            // We need to keep hold of the employeeNumber and employeeName.
            // The OADialogPage gives us a convenient means 
            // of doing this. Note that the use of the Hashtable is  
            // most appropriate for passing multiple parameters. See the OADialogPage 
            // javadoc for an alternative when dealing with a single parameter.
            java.util.Hashtable formParams = new java.util.Hashtable(1);
            formParams.put("LineId", lineId);
            formParams.put("LineNum", lineNum);
            dialogPage.setFormParameters(formParams);

            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            // User has confirmed that she wants to delete this employee.
            // Invoke a method on the AM to set the current row in the VO and 
            // call remove() on this row. 
            String lineId = (String)pageContext.getParameter("LineId");
            String lineNum = pageContext.getParameter("LineNum");
            Serializable[] paras = { lineId };
            am.invokeMethod("deletePayPlanLine", paras);


            // Now, redisplay the page with a confirmation message at the top. Note
            // that the deleteEmployee() method in the AM commits, and our code
            // won't get this far if any exceptions are thrown.

            OAException message = 
                new OAException("FND", "AM_PARAMREGSTRY_DELETE_CONFIRM", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(message);
            setHeader(pageContext, webBean, am);
        }
        //???ˮ?
        if (pageContext.getParameter("Apply") != null) {
            //200903?????��???
            String poHdrId = 
                (String)pageContext.getTransactionValue("poHdrId");
            String result = GetPoPrice(am, poHdrId);
            //System.out.println("begin poHdrId="+poHdrId);
            String[] resultgroup = result.split("@");
            double Totalprice1 = Double.parseDouble(resultgroup[0]);
            double Totalprice2 = Double.parseDouble(resultgroup[1]);
            double v_initamt = 0;
            double v_newamt = 0;
            int apply_check = 0;
            int ou_id = -1;

            String totalStr01 = 
                (String)pageContext.getTransactionValue("PoSum");
            Serializable[] paras01 = { totalStr01 };
            //Comment by Wei Yi at 2015/09/29
            //calculate only when user click calculate button
            //am.invokeMethod("calculate",paras01);    
            //end

            OAViewObject vo = (OAViewObject)am.findViewObject("PayPlanVO1");
            if (vo != null) {
                int ii = 0;
                ii = vo.getRowCount();
                for (int nn = 0; nn < ii; nn++) {
                    Row row = vo.getRowAtRangeIndex(nn);
                    Object a4 = row.getAttribute("NewAmt");
                    if (null == a4 || "".equals(a4)) {
                        throw new OAException("额度不能为空，可以点击计算按钮进行计算分配。");
                    }

                    Number paidAmount = (Number)row.getAttribute("PaidAmt");
                    Number amount = (Number)row.getAttribute("NewAmt");
                    if (null == paidAmount) {
                        ;
                    } else {
                        if (amount.compareTo(paidAmount) < 0) {
                            throw new OAException("额度不能比已付金额小。");
                        }
                    }

                }
            }


            double plannedTotal = 0;
            double money = 0;
            double paidamt = 0; //2010-07-28?????��?????????????��??????????
            if (vo != null) { //if(vo!=null)begin
                System.out.println("vo is not null!");
                int ii = 0;
                //vo.executeQuery();//??????
                System.out.println(vo.getRowCount()); //
                ii = vo.getRowCount();
                for (int nn = 0; nn < ii; nn++) {
                    Row row = vo.getRowAtRangeIndex(nn);
                    //Row row = (Row)vo.getCurrentRow();
                    Object[] a = row.getAttributeValues();
                    //String a5 = row.getAttribute("InitAmt").toString();

                    String a8 = row.getAttribute("NewAmt").toString();
                    log("a8=" + a8);
                    //2010??7??27????????????��
                    String a1 = "0";
                    try {
                        a1 = row.getAttribute("LineId").toString();
                        //???catch??????????
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    log("a1=" + a1);
                    String a10 = "";
                    a10 = get_pay_amounts(am, a1);
                    paidamt = Double.parseDouble(a10);
                    log("a10=" + a10);
                    //paidamt=1000;
                    money = 
                            Double.parseDouble(a8); //2010??7??27?????????a5???a8
                    log("money=" + money);
                    log("paidamt=" + paidamt);
                    if (money < paidamt) {
                        log("money<paidamt");
                        String poHeaderId = "";
                        MessageToken[] tokens = 
                        { new MessageToken("EMP_NAME", poHeaderId), 
                          new MessageToken("EMP_NUMBER", poHeaderId) };
                        //OAException confirmMessage = new OAException("FND", "PO_PAY_PLAN_NOTMATCH_WARN", tokens,
                        OAException confirmMessage = 
                            new OAException("FND", "PO_PAY_PLAN_PAIDMATCH_WARN", 
                                            tokens, OAException.ERROR, null);
                        pageContext.putDialogMessage(confirmMessage);
                        return;
                    }
                    //2010??7??27????????????��

                    //v_initamt = Double.parseDouble(a5);
                    v_newamt = Double.parseDouble(a8);
                    log("v_newamt" + v_newamt);
                    //System.out.println("a5 = "+a5);//
                    //System.out.println("a8 = "+a8);//
                    //log("money="+money);//
                    plannedTotal = plannedTotal + money;
                    log("plannedTotal" + plannedTotal);
                    //log("plannedTotal="+plannedTotal);// 

                    //System.out.println("money="+money);//
                    //System.out.println("plannedTotal="+plannedTotal);//

                    //2009-08-01????
                    String v_lineid = "";
                    double v_result = 0;

                    try {
                        v_lineid = row.getAttribute("LineId").toString();
                        //System.out.println("v_lineid = "+v_lineid);//
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                    ou_id = pageContext.getOrgId();
                    log("ou_id" + ou_id);
                    OADBTransaction transaction = am.getOADBTransaction();
                    CallableStatement ca = 
                        transaction.createCallableStatement("call cux_payplan_check_pkg.check_apply(?,?,?)", 
                                                            1);
                    try {
                        //System.out.println("begin try!");
                        ca.setString(1, poHdrId);
                        ca.setString(2, v_lineid);
                        ca.registerOutParameter(3, Types.DOUBLE);
                        ca.executeQuery();
                        v_result = ca.getDouble(3);
                        //System.out.println(v_result);
                        System.out.println("end try!");
                    } catch (Exception e) {
                        System.out.println("begin catch!");
                        System.out.println(e.toString());
                        //System.out.println("in catch!");
                    }

                } //for end


                if (plannedTotal >= Totalprice1 && 
                    plannedTotal <= Totalprice2 && apply_check == 0) {
                    //200903?????��????

                    String totalStr = 
                        (String)pageContext.getTransactionValue("PoSum");
                    System.out.println("totalStr!" + totalStr);
                    Serializable[] paras = { totalStr };
                    System.out.println("ok0");
                    am.invokeMethod("apply", paras);
                    System.out.println("ok1");
                    OAException message = 
                        new OAException("FND", "AM_PARAMREGSTRY_MODIFY_CONFIRM", 
                                        null, OAException.CONFIRMATION, null);
                    pageContext.putDialogMessage(message);
                    System.out.println("ok2");
                    setHeader(pageContext, webBean, am);
                    System.out.println("okokok!");
                } else {
                    System.out.println("begin else!");
                    System.out.println("plannedTotal=" + plannedTotal);
                    System.out.println("Totalprice1=" + Totalprice1);
                    System.out.println("Totalprice2=" + Totalprice2);
                    String poHeaderId = "";
                    MessageToken[] tokens = 
                    { new MessageToken("EMP_NAME", poHeaderId), 
                      new MessageToken("EMP_NUMBER", poHeaderId) };
                    OAException confirmMessage = 
                        new OAException("FND", "PO_PAY_PLAN_NOTMATCH_WARN", 
                                        tokens, OAException.ERROR, null);
                    pageContext.putDialogMessage(confirmMessage);
                }
            } //if(vo!=null)end
        }
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
        }
        if (pageContext.getParameter("AddNewVersion") != null) {
            am.invokeMethod("createNewVersion");
        }
        if (pageContext.getParameter("CalculateBTN") != null) {
            String totalStr = (String)pageContext.getTransactionValue("PoSum");
            Serializable[] paras = { totalStr };
            am.invokeMethod("calculate", paras);
        }
    }
    // ??????20090305??

    public static void log(Object s) {
        System.out.println(s);
    }
    //????po_header_id?????????????

    public String GetPoPrice(OAApplicationModule am, String poHeaderId) {

        String result = "";
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_po_reports.getpopricebyheaderid(?,?,?)", 
                                                1);
        try {
            cs.setString(1, poHeaderId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeQuery();
            result = cs.getString(2);
            result = result + '@' + cs.getString(3);
            //System.out.println("first="+result);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        //System.out.println("result="+result);
        return result;

    }

    //????????????
    //????po_header_id??????????

    public String GetPoTax(OAApplicationModule am, String poHeaderId) {

        String result = "";
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_po_reports.get_po_tax(?,?)", 
                                                1);
        try {
            cs.setString(1, poHeaderId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeQuery();
            result = cs.getString(2);
            //System.out.println("first="+result);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        //System.out.println("result="+result);
        return result;
    }
    //????????????
    //????po_header_id???????????

    public String get_pay_amounts(OAApplicationModule am, String poHeaderId) {

        String result = "";
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_po_reports.get_pay_amounts(?,?)", 
                                                1);
        try {
            cs.setString(1, poHeaderId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeQuery();
            result = cs.getString(2);
            //System.out.println("first="+result);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("result=" + result);
        return result;
    }

    /**
     * @param OAApplicationModule am
     * @param String planLineId  
     */
    public String isPayMatchExists(OAApplicationModule am, String planLineId) {

        String result = "";
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_po_reports.IS_PAY_MATCH_EXISTS(?,?)", 
                                                1);
        try {
            cs.setString(1, planLineId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeQuery();
            result = cs.getString(2);
            //System.out.println("first="+result);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        //System.out.println("result="+result);
        return result;
    }

    public static void printViewObject(OAViewObject vo) {
        vo.executeQuery();
        while (vo.hasNext()) {
            Row row = vo.next();
            String rowDataStr = "";
            int numAttrs = vo.getAttributeCount();
            for (int columnNo = 0; columnNo < numAttrs; columnNo++) {
                // See also     
                Object attrData = row.getAttribute(columnNo);
                rowDataStr += (attrData + "\t");
            }
            System.out.println(rowDataStr);
        }
    }
    // ??????20090305??

    public static void main(String[] args) {
        Number a1 = new Number(5);
        Number a2 = null;
        System.out.println(a1.compareTo(a2));
    }
}
