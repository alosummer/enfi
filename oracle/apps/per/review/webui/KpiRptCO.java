/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.cabo.ui.beans.layout.TableLayoutBean;

import oracle.jbo.ViewObject;

/**
 * Controller for ...
 */
public class KpiRptCO extends OAControllerImpl {
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        ArrayList list = null;

        String arg1 = pageContext.getParameter("APPRAISAL_ID");
        String arg2 = 
            pageContext.getParameter("KPI_ID"); // "select organization_id IDX from mtl_parameters ";
        //testing
        // arg1 = "407";
        // arg2 = "200";
        //testing end
        Serializable[] parameters = { arg1, arg2 };
        ViewObject vo1 = am.findViewObject("RefObjectLovVO");
        list = (ArrayList)am.invokeMethod("dynamicVO", parameters);
        if (list == null) {
            //---
            //----no query sql,no data query
            OAWebBean NoDataInfo = 
                (OAWebBean)webBean.findChildRecursive("NoDataInfo");
            NoDataInfo.setRendered(false); //hidden no data found text        
        } else {
            OAWebBean NoDataInfo = 
                (OAWebBean)webBean.findChildRecursive("NoDataInfo");
            NoDataInfo.setRendered(false); //hidden no data found text
            OATableBean tableBean = 
                (OATableBean)this.createWebBean(pageContext, 
                                                OATableBean.TABLE_BEAN, null, 
                                                "tableBeanRN");
            tableBean.setNumberOfRowsDisplayed(300);
            tableBean.setTableNavigationDisplayed(true);
            webBean.addIndexedChild(tableBean);
            Iterator it = list.iterator();
            int i = 1;
            String label = "";
            while (it.hasNext()) {
                OAMessageStyledTextBean stb = 
                    (OAMessageStyledTextBean)this.createWebBean(pageContext, 
                                                                OAMessageStyledTextBean.MESSAGE_STYLED_TEXT_BEAN, 
                                                                null, 
                                                                "Text" + i);
                label = it.next().toString();
                stb.setViewAttributeName(label);
                stb.setViewUsageName("RefObjectLovVO");
                stb.setLabel(label);
                tableBean.addIndexedChild(stb);
                i++;
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
        if (pageContext.getParameter("Return") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG&from=report", 
                                           null, (byte)0, null, null, true, 
                                           "Y");


        }

    }

}
