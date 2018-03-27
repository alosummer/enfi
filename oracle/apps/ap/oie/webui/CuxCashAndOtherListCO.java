package cux.oracle.apps.ap.oie.webui;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

import java.util.Enumeration;

import oracle.apps.ap.oie.webui.CashAndOtherListCO;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

public class CuxCashAndOtherListCO extends CashAndOtherListCO {
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
        //        OAApplicationModule am = pageContext.getRootApplicationModule();
        //        ViewObject headerVO = am.findViewObject("ExpenseReportHeadersVO");
        //        Row headerRow = headerVO.getCurrentRow();
        //        if (headerRow == null) {
        //            headerRow = headerVO.first();
        //        }
        //        String travelExpenseType = (String)headerRow.getAttribute("Attribute2");
        //        if ("国内出差报销".equals(travelExpenseType) || "国外出差报销".equals(travelExpenseType)) {
        //                    Enumeration enu =  webBean.getChildNames();
        //                    while (enu.hasMoreElements()) {
        //                        System.out.println(enu.nextElement());
        //                    }
        ////                    String[] beanNames = {"Date____1","ReceiptCurrencyAmount","WebParameterId____1","Justification____1"};
        ////                    for (String bean:beanNames) {
        ////                        OAWebBean lwebBean = webBean.findChildRecursive(bean);
        ////                        lwebBean.setAttributeValue(this.READ_ONLY_ATTR,true);
        ////                    }
        //        
        //        }        
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
    }
}
