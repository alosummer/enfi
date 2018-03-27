/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.po.deliver.webui;

import com.sun.java.util.collections.HashMap;

import cux.oracle.apps.po.deliver.server.CuxDeliverAMImpl;
import cux.oracle.apps.po.deliver.server.CuxPoDeliStatusTVOImpl;
import cux.oracle.apps.po.deliver.server.CuxPoDeliStatusTVORowImpl;
import cux.oracle.apps.po.deliver.server.CuxPoSupEvlConfVOImpl;
import cux.oracle.apps.po.deliver.server.CuxPoSupEvlConfVORowImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;


/**
 * Controller for ...
 */
public class SingleDeliverStautsPGCO extends OAControllerImpl {
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

        //               String deliId = "1201";
        String deliId = pageContext.getParameter("DeliID");
        CuxDeliverAMImpl am = 
            (CuxDeliverAMImpl)pageContext.getApplicationModule(webBean);
        Serializable[] paras = { deliId };
        am.invokeMethod("initSingleDeliverStatus", paras);

        CuxPoDeliStatusTVOImpl vo = am.getCuxPoDeliStatusTVO2();
        CuxPoDeliStatusTVORowImpl row = (CuxPoDeliStatusTVORowImpl)vo.first();
        Number uniqSeq = row.getUniqSeq();

        CuxPoSupEvlConfVOImpl confVO = am.getCuxPoSupEvlConfVO1();
        Boolean displayRN = Boolean.FALSE;
        for (int i = 1; i <= 6; i++) {
            String itemName = "Attribute" + i;
            confVO.query(uniqSeq, i);
            CuxPoSupEvlConfVORowImpl confRow = 
                (CuxPoSupEvlConfVORowImpl)confVO.first();
            if (null == confRow) {
                webBean.findChildRecursive(itemName).setAttributeValue(this.RENDERED_ATTR, 
                                                                       Boolean.FALSE);
            } else {
                String renderFlag = confRow.getRenderFlag();
                if ("Y".equals(renderFlag)) {
                    webBean.findChildRecursive(itemName).setAttributeValue(this.RENDERED_ATTR, 
                                                                           Boolean.TRUE);
                    displayRN = Boolean.TRUE;
                } else {
                    webBean.findChildRecursive(itemName).setAttributeValue(this.RENDERED_ATTR, 
                                                                           Boolean.FALSE);
                }
                String requiredFlag = confRow.getRequiredFlag();
                if ("Y".equals(requiredFlag)) {
                    webBean.findChildRecursive(itemName).setAttributeValue(this.REQUIRED_ATTR, 
                                                                           this.REQUIRED_YES);
                } else {
                    webBean.findChildRecursive(itemName).setAttributeValue(this.REQUIRED_ATTR, 
                                                                           this.REQUIRED_NO);
                }
            }
        }

        webBean.findChildRecursive("SupEvl").setRendered(displayRN);

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
        CuxDeliverAMImpl am = 
            (CuxDeliverAMImpl)pageContext.getApplicationModule(webBean);
        if (pageContext.getParameter("ApplyBTN") != null) {
            am.invokeMethod("commit");
            CuxPoDeliStatusTVOImpl vo = am.getCuxPoDeliStatusTVO2();
            CuxPoDeliStatusTVORowImpl row = 
                (CuxPoDeliStatusTVORowImpl)vo.first();
            HashMap params = new HashMap(1);
            params.put("PoHeaderId", row.getPoHeaderId());
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/po/deliver/webui/MoreDeliverStatusPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, params, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
        if (pageContext.getParameter("CancelBTN") != null) {
            am.invokeMethod("rollback");
            CuxPoDeliStatusTVOImpl vo = am.getCuxPoDeliStatusTVO2();
            CuxPoDeliStatusTVORowImpl row = 
                (CuxPoDeliStatusTVORowImpl)vo.first();
            HashMap params = new HashMap(1);
            params.put("PoHeaderId", row.getPoHeaderId());
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/po/deliver/webui/MoreDeliverStatusPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, params, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
    }

}
