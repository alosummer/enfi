/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.pmp.webui;

import com.sun.java.util.collections.HashMap;

import java.sql.CallableStatement;
import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAExceptionUtils;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

/**
 * Controller for ...
 */
public class PageButtonActionCO extends PmpBaseCO {
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
        
        if (pageContext.isLoggingEnabled(oracle.apps.fnd.framework.OAFwkConstants.PROCEDURE)) {
            pageContext.writeDiagnostics(this, "start processRequest", 
                                         oracle.apps.fnd.framework.OAFwkConstants.PROCEDURE);
        }
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        ViewObject vo = am.findViewObject("PmpVO1");
        vo.setMaxFetchSize(-1);
        vo.setWhereClause(null);
        vo.setWhereClause(" PMP_ID = " + pageContext.getSessionValue("PmpId"));
        vo.executeQuery();
        Row row = vo.first();
        String verifyStatus = (String)row.getAttribute("VerifyStatus");
        String approveStatus = (String)row.getAttribute("ApproveStatus");
        if ("UNVERIFIED".equals(verifyStatus) && 
            "UNAPPROVED".equals(approveStatus)) {
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean back = webBean.findChildRecursive("back");
            back.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
        }
        if ("BTVERIFYING".equals(verifyStatus) && 
            "UNAPPROVED".equals(approveStatus)) {
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean back = webBean.findChildRecursive("back");
            back.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
        }
        if ("MTVERIFYING".equals(verifyStatus) && 
            "UNAPPROVED".equals(approveStatus)) {
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean back = webBean.findChildRecursive("back");
            back.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
        }
        if ("MTREJECTED".equals(verifyStatus) && 
            "UNAPPROVED".equals(approveStatus)) {
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean back = webBean.findChildRecursive("back");
            back.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
        }
        if ("VERIFIED".equals(verifyStatus) && 
            "UNAPPROVED".equals(approveStatus)) {
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean back = webBean.findChildRecursive("back");
            back.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
        }
        if ("VERIFIED".equals(verifyStatus) && 
            "APPROVING".equals(approveStatus)) {
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean back = webBean.findChildRecursive("back");
            back.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
        }
        if ("VERIFIED".equals(verifyStatus) && 
            "APPROVED".equals(approveStatus)) {
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean back = webBean.findChildRecursive("back");
            back.setAttributeValue(DISABLED_ATTR, Boolean.FALSE);
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
        }
        if (pageContext.getSessionValue("NtfId") != null) {
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean back = webBean.findChildRecursive("back");
            back.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
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
        String event = pageContext.getParameter(EVENT_PARAM);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String pmpId = (String)pageContext.getSessionValue("PmpId");
        String projectId = (String)pageContext.getSessionValue("ProjectId");
        String versionNum = (String)pageContext.getSessionValue("VersionNum");
        if ("Save".equals(event)) {
            am.getOADBTransaction().commit();   
        }
        if ("Back".equals(event)) {
            pageContext.forwardImmediately("CUXPMPSEARCH", 
                                           OAWebBeanConstants.REMOVE_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
        if ("SubmitD".equals(event)) {
            OADBTransaction trxn = am.getOADBTransaction();
            CallableStatement cbStmt = 
                trxn.createCallableStatement(SMT_AME_STARTUP, 1);
            try {
                cbStmt.setString(1, TRANSACTION_TYPE_VERIFY);
                cbStmt.setString(2, pmpId);
                cbStmt.setString(3, projectId + "" + versionNum);
                cbStmt.setString(4, MOD_PKG_NAME);
                cbStmt.execute();
                cbStmt.close();
                OAExceptionUtils.checkErrors(trxn);
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw OAException.wrapperException(sqle);
            }
            trxn.commit();
            OAWebBean submitD = webBean.findChildRecursive("submitd");
            submitD.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            throw new OAException("CUX", "CUX-PM-M002", null, 
                                  OAException.INFORMATION, null);
        }
        if ("SubmitC".equals(event)) {
            OADBTransaction trxn = am.getOADBTransaction();
            CallableStatement cbStmt = 
                trxn.createCallableStatement(SMT_AME_STARTUP, 1);
            try {
                cbStmt.setString(1, TRANSACTION_TYPE_APPROVE);
                cbStmt.setString(2, pmpId);
                cbStmt.setString(3, projectId + "" + versionNum);
                cbStmt.setString(4, MOD_PKG_NAME);
                cbStmt.execute();
                cbStmt.close();
                OAExceptionUtils.checkErrors(trxn);
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw OAException.wrapperException(sqle);
            }
            trxn.commit();
            OAWebBean submitC = webBean.findChildRecursive("submitc");
            submitC.setAttributeValue(DISABLED_ATTR, Boolean.TRUE);
            throw new OAException("CUX", "CUX-PM-M002", null, 
                                  OAException.INFORMATION, null);
        }
    }

}
