/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.po.document.common.webui;

import java.sql.CallableStatement;

import java.sql.SQLException;
import java.sql.Types;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.po.document.common.webui.ControlsPoplistCO;

/**
 * Controller for ...
 */
public class CuxControlsPoplistCO extends /*OAControllerImpl*/ControlsPoplistCO {
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
        String poNum = pageContext.getParameter("PoNum");
        String poStatus = pageContext.getParameter("PoStatus");
        String poHeaderId = pageContext.getParameter("PoHeaderId");
        if ("PayPlan".equals(pageContext.getParameter(EVENT_PARAM)) && 
            poNum != null && !"".equals(poNum)) {
            // Navigate to the "Create Payplan" page while retaining the AM.
            // Note the use of KEEP_MENU_CONTEXT as opposed to GUESS_MENU_CONTEXT
            // since we know the current tab should remain highlighted.
            // Retain AM
            pageContext.setForwardURL("OA.jsp?OAFunc=CUXPOPAYPLAN", null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        } else if ("MileStone".equals(pageContext.getParameter(EVENT_PARAM)) && 
                   poNum != null && !"".equals(poNum) && poStatus != null && 
                   "APPROVED".equals(poStatus)) { // Retain AM
            pageContext.setForwardURL("OA.jsp?OAFunc=CUXPOMILESTONES", null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        } else if ("Tradition".equals(pageContext.getParameter(EVENT_PARAM)) && 
                   poNum != null && !"".equals(poNum)) {
            //pageContext.setForwardURL("OA.jsp?OAFunc=CUXPOPAYPLAN",
            // Retain AM
            pageContext.setForwardURL("OA.jsp?OAFunc=CUXPOTRADITION", null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);

        } else if ("SetItem".equals(pageContext.getParameter(EVENT_PARAM)) && 
                   poNum != null && !"".equals(poNum)) {
            System.out.println("into");
            OADBTransaction txn = 
                pageContext.getRootApplicationModule().getOADBTransaction();
            String appShortName = txn.getApplicationShortName();
            int resID = txn.getResponsibilityId();


            String sql = 
                "BEGIN  SELECT b.RESPONSIBILITY_KEY INTO :1 FROM FND_RESPONSIBILITY_VL b WHERE b.RESPONSIBILITY_ID = :2; END;";
            CallableStatement cs = txn.createCallableStatement(sql, 1);
            System.out.println(sql);
            String messageBuffer = "";

            try {
                cs.setInt(2, resID);
                cs.registerOutParameter(1, Types.VARCHAR);
                //(OracleCallableStatement)cs.reregisterOutParameter(1, Types.VARCHAR);
                cs.execute();
                messageBuffer = cs.getString(1);
                System.out.println(messageBuffer);
                cs.close();
            } catch (SQLException sqle) {
                //try { cs.close } catch (Exception(e) {}
                throw OAException.wrapperException(sqle);
            }

            String destination = 
                "form:" + appShortName + ":" + messageBuffer + ":STANDARD:CUXAPSTP:DOC_NUM=".concat(poNum);
            System.out.println(destination);
            pageContext.forwardImmediatelyToForm(destination);
        }
    }

}
