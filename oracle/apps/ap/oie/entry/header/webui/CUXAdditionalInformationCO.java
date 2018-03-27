package cux.oracle.apps.ap.oie.entry.header.webui;

import java.sql.CallableStatement;

import oracle.apps.ap.oie.entry.header.webui.AdditionalInformationCO;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;

public class CUXAdditionalInformationCO extends AdditionalInformationCO {
    public CUXAdditionalInformationCO() {
    }

    /**
     * @param paramOAPageContext
     * @param paramOAWebBean
     * @author yaoyuan
     */
    public void processFormRequest(OAPageContext paramOAPageContext, 
                                   OAWebBean paramOAWebBean) {
        super.processFormRequest(paramOAPageContext, paramOAWebBean);
        OAApplicationModule am = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        String souceParam = paramOAPageContext.getParameter("source");
        if (souceParam == null) {
            souceParam = "THIS#IS#NULL#VALUE";
        }
        String eventParam = paramOAPageContext.getParameter(this.EVENT_PARAM);
        if ("OIENavBar".startsWith(souceParam) && "goto".equals(eventParam)) {
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("ExpenseReportHeadersVO");
            OARow row = (OARow)vo.getCurrentRow();
            if (row == null) {
                row = (OARow)vo.first();
            }
            /**********************校验逻辑*************************/
            String isTempPurchase = (String)row.getAttribute("Attribute15");
            if ("Y".equals(isTempPurchase)) {
                String supplier = (String)row.getAttribute("Attribute14");
                if (supplier == null || "".equals(supplier)) {
                    throw new OAException("请选择供应商名称");
                }
                String supplierSite = (String)row.getAttribute("Attribute13");
                if (supplierSite == null || "".equals(supplierSite)) {
                    throw new OAException("请选择供应商地点");
                }
            }
            /**********************校验逻辑*************************/

            String isTravelExpense = (String)row.getAttribute("Attribute2");
            if ("国内出差报销".equals(isTravelExpense)) {
                paramOAPageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/ap/oie/entry/header/webui/CUXOieDetailEntryPG", 
                                                      null, 
                                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                                      null, null, true, 
                                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
            } else if ("国外出差报销".equals(isTravelExpense)) {
                paramOAPageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/ap/oie/entry/header/webui/CUXOieAbroadDetailEntryPG", 
                                                      null, 
                                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                                      null, null, true, 
                                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
            } else {
                this.clearAllCustomerData(paramOAPageContext, paramOAWebBean);
                paramOAPageContext.forwardImmediately("OA.jsp?page=/oracle/apps/ap/oie/entry/lines/webui/CashAndOtherLinesPG", 
                                                      null, 
                                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                                      null, null, true, 
                                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
            }
        }
    }

    /**
     * 清除所有客户化出差填写的数据
     * 
     * @param pageContext
     * @param webBean
     */
    private void clearAllCustomerData(OAPageContext pageContext, 
                                      OAWebBean webBean) {
        OAApplicationModuleImpl rootAM = 
            (OAApplicationModuleImpl)pageContext.getRootApplicationModule();

        OAViewObject headerVO = 
            (OAViewObject)rootAM.findViewObject("ExpenseReportHeadersVO");
        OARow headerRow = (OARow)headerVO.getCurrentRow();
        if (headerRow == null) {
            headerRow = (OARow)headerVO.first();
        }
        Number reportHeaderId = 
            (Number)headerRow.getAttribute("ReportHeaderId");

        OADBTransaction tx = rootAM.getOADBTransaction();
        CallableStatement cs = 
            tx.createCallableStatement("begin cux_ap_exp_pkg.clear_all_customer_travel_date(:1);end;", 
                                       1);
        try {
            cs.setInt(1, reportHeaderId.intValue());
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
