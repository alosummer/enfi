/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.ap.oie.entry.header.webui;

import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVOImpl;
import cux.oracle.apps.ap.oie.entry.header.server.CuxApExpRelatedOaInfoVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.RenderVOImpl;

import cux.oracle.apps.ap.oie.entry.header.server.RenderVORowImpl;

import java.sql.CallableStatement;

import oracle.apps.ap.oie.server.GeneralInfoAMImpl;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class AdditionSupplierInfoRNCO extends OAControllerImpl {
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

        oracle.apps.ap.oie.server.GeneralInfoAMImpl am1 = null;
        try {
            am1 = (GeneralInfoAMImpl)pageContext.getApplicationModule(webBean);

        } catch (Exception e) {
            OAException errMessage = new OAException("获取AM失败", (byte)0);
            pageContext.putDialogMessage(errMessage);
            return;
        }

        RenderVOImpl renderVO = (RenderVOImpl)am1.findViewObject("RenderVO1");
        if (renderVO == null) {
            renderVO = 
                    (RenderVOImpl)am1.createViewObject("RenderVO1", "cux.oracle.apps.ap.oie.entry.header.server.RenderVO");
        }
        renderVO.executeQuery();
        RenderVORowImpl renderRow = (RenderVORowImpl)renderVO.first();


        OAViewObject vo = 
            (OAViewObject)am1.findViewObject("ExpenseReportHeadersVO");
        OARow row = (OARow)vo.getCurrentRow();
        if (row == null) {
            row = (OARow)vo.first();
        }
        String isTempPurchase = (String)row.getAttribute("Attribute15");
        if ("Y".equals(isTempPurchase)) {
            renderRow.setRequiredFlag("yes");
            renderRow.setReadonlyFlag(Boolean.FALSE);
        } else {
            renderRow.setRequiredFlag("no");
            renderRow.setReadonlyFlag(Boolean.TRUE);
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

        oracle.apps.ap.oie.server.GeneralInfoAMImpl am1 = null;
        try {
            am1 = (GeneralInfoAMImpl)pageContext.getApplicationModule(webBean);

        } catch (Exception e) {
            OAException errMessage = new OAException("获取AM失败", (byte)0);
            pageContext.putDialogMessage(errMessage);
            return;
        }
        OAViewObject vo = 
            (OAViewObject)am1.findViewObject("ExpenseReportHeadersVO");

        OARow row = (OARow)vo.getCurrentRow();
        if (row == null) {
            row = (OARow)vo.first();
        }

        RenderVOImpl renderVO = (RenderVOImpl)am1.findViewObject("RenderVO1");
        RenderVORowImpl renderRow = (RenderVORowImpl)renderVO.first();

        String souceParam = pageContext.getParameter("source");
        if (souceParam == null) {
            souceParam = "THIS#IS#NULL#VALUE";
        }
        String eventParam = pageContext.getParameter(this.EVENT_PARAM);
        if ("IsTempPurchase".equals(souceParam) && 
            "CuxchangeTheChoice".equals(eventParam)) {
            String isTempPurchase = (String)row.getAttribute("Attribute15");
            if ("Y".equals(isTempPurchase)) {
                renderRow.setRequiredFlag("yes");
                renderRow.setReadonlyFlag(Boolean.FALSE);
            } else {
                renderRow.setRequiredFlag("no");
                renderRow.setReadonlyFlag(Boolean.TRUE);
                row.setAttribute("Attribute14", null);
                row.setAttribute("Attribute13", null);
            }

        }
    }


}
