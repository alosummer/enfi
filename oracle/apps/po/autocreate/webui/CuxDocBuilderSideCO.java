/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.po.autocreate.webui;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.sql.Types;

import java.util.Enumeration;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAExceptionUtils;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class CuxDocBuilderSideCO extends oracle.apps.po.autocreate.webui.DocBuilderSideCO {
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
        String poNum = "";
        if (pageContext.getParameter("Create") != null) {
            String purType = pageContext.getParameter("PurTypeCode");
            String poClass = pageContext.getParameter("PoClassCode");
            String mainPoNum = pageContext.getParameter("MainPoNum");
            String bcxy = pageContext.getParameter("bcxy");
            String projectNum = 
                (String)pageContext.getSessionValue("ProjectNum");
            if ("4".equals(purType) && 
                (poClass == null || "".equals(poClass))) {
                throw new OAException("请输入合同小类!", OAException.ERROR);
            }
            if ("Y".equals(bcxy) && 
                (mainPoNum == null || "".equals(mainPoNum))) {
                throw new OAException("请输入主合同编号!", OAException.ERROR);
            }
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            OADBTransaction trxn = am.getOADBTransaction();
            String sql = 
                "BEGIN " + " :1 := cux_gennum_hub_pkg.gen_po_num(:2,:3,:4,:5,:6); " + 
                "END;";
            CallableStatement cbStmt = trxn.createCallableStatement(sql, 1);

            try {
                cbStmt.registerOutParameter(1, Types.VARCHAR);
                cbStmt.setInt(2, pageContext.getOrgId());
                cbStmt.setString(3, purType);
                cbStmt.setString(4, projectNum);
                cbStmt.setString(5, poClass);
                cbStmt.setString(6, mainPoNum);
                cbStmt.execute();
                poNum = cbStmt.getString(1);
                cbStmt.close();
                OAExceptionUtils.checkErrors(trxn);
                am.findViewObject("AutocreateHeaderPVO").first().setAttribute("NewPo", 
                                                                              poNum);
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw OAException.wrapperException(sqle);
            }
            sql = 
"BEGIN " + " cux_po_autocreate_pkg.set_po_addition(:1,:2,:3,:4,:5); " + "END;";
            cbStmt = trxn.createCallableStatement(sql, 1);
            try {
                cbStmt.setString(1, poNum);
                cbStmt.setString(2, purType);
                cbStmt.setString(3, bcxy);
                cbStmt.setString(4, mainPoNum);
                cbStmt.setString(5, poClass);
                cbStmt.execute();
                cbStmt.close();
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw OAException.wrapperException(sqle);
            }
        }
        super.processFormRequest(pageContext, webBean);
    }

}
