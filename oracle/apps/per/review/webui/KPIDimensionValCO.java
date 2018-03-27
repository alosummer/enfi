/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.KPIDimensionVOImpl;

import cux.oracle.apps.per.review.server.KpiCtrValueVOImpl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for ...
 */
public class KPIDimensionValCO extends OAControllerImpl {
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
                                                       "CreateDimensionValueTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateDimensionValueTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OAPageLayoutBean page = (OAPageLayoutBean)webBean;
        String kpiId = pageContext.getParameter("kpiId");
        String constraintId = pageContext.getParameter("constraintId");
        String kpiName = (String)pageContext.getSessionValue("kpiName");

        String title = "";
        String prefix = "��Լ����ά��ȡֵ( ";
        String suffix = " )";
        try {
            prefix = new String(prefix.getBytes("ISO-8859-1"), "GB2312");
            suffix = new String(suffix.getBytes("ISO-8859-1"), "GB2312");
        } catch (UnsupportedEncodingException e) {
            ;
        }

        title = prefix + kpiName + suffix;
        page.setTitle(title);

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        //KPIDimensionVOImpl vo = (KPIDimensionVOImpl)am.findViewObject("KPIDimensionVO1");
        if (kpiId != null && constraintId != null) {
            Serializable params[] = { kpiId, constraintId };
            am.invokeMethod("initKPIDimensionValue", params);
            am.invokeMethod("queryKPIDimensionValue", params);
            pageContext.putSessionValue("kpiId", kpiId);
            pageContext.putSessionValue("constraintId", constraintId);
        }

        if (pageContext.getParameter("refresh") != null) {
            KpiCtrValueVOImpl vo = 
                (KpiCtrValueVOImpl)am.findViewObject("KpiCtrValueVO1");
            vo.executeQuery();
        }

        //KpiCtrValueVO1
    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        System.out.println("KPIDimensionValCO : processFormRequest is running ");
        super.processFormRequest(pageContext, webBean);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        Enumeration en = pageContext.getParameterNames();
        String lSource = null;
        Map map = new HashMap();
        while (en.hasMoreElements()) {
            String b = (String)en.nextElement();
            System.out.println(b + " || " + pageContext.getParameterValues(b) + 
                               " || " + pageContext.getParameter(b));
            if (b.contains("source")) {
                if (pageContext.getParameter(b) != null) {
                    lSource = pageContext.getParameter(b);
                    if (lSource.length() > 0)
                        lSource = 
                                lSource.substring(lSource.length() - 1); //source || [Ljava.lang.String;@8fdcd1 || N3:DimensionValue:1
                    System.out.println("source " + lSource);
                }


            }


            if (b.contains("ValuesetName")) {
                //pageContext.putSessionValue("ValuesetName",pageContext.getParameter(b));
                //System.out.println("put session value");
                //index number   as N3:ValuesetName:0
                map.put(b.substring(b.length() - 1), 
                        pageContext.getParameter(b));
            }
        }
        if (!map.isEmpty() && lSource != null && map.containsKey(lSource)) {
            System.out.println("put session value");
            pageContext.putSessionValue("ValuesetName", 
                                        map.get(lSource).toString());
            map.clear();
        }


        /*
        if(pageContext.isLovEvent())
        {
              String LovSourceID=pageContext.getLovInputSourceId();
              System.out.println("LovSourceID : " + LovSourceID);
        }
*/

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateDimensionValueTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIConstraintPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            am.invokeMethod("commit");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_KPI_DIM_VALUE_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/KPIConstraintPG&refresh=Y", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }
    }

}
