package cux.oracle.apps.pa.project.webui;


import java.sql.CallableStatement;
import java.sql.SQLException;

import java.util.Enumeration;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAExceptionUtils;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.pa.project.webui.ProjectMembersTopCO;

import oracle.jbo.Row;

/**
 * Controller for ...
 */
public class CuxProjectMembersTopCO extends ProjectMembersTopCO {
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
        String event = pageContext.getParameter(this.EVENT_PARAM);
        if (pageContext.getParameter("FndDecline") != null) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            am.getOADBTransaction().rollback();
        }
        if ("DeleteTeam".equals(event)) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            String rowReference = 
                pageContext.getParameter(EVENT_SOURCE_ROW_REFERENCE);
            String deleteStmt = 
                "BEGIN\n" + "  cux_pa_dlv_pkg.delete_res_spec(p_object_id => :1, p_object_type => :2,\n" + 
                "                                 p_resource_type_id => :3,\n" + 
                "                                 p_resource_source_id => :4,\n" + 
                "                                 p_project_role_id => :5,\n" + 
                "                                 p_start_date_active => :6);\n" + 
                "END;\n";
            Row row = am.findRowByRef(rowReference);
            OADBTransaction trxn = am.getOADBTransaction();
            CallableStatement cbStmt = 
                trxn.createCallableStatement(deleteStmt, 1);
            try {
                row.getAttribute("ObjectId");
                row.getAttribute("ObjectType");
                row.getAttribute("ResourceTypeId");
                row.getAttribute("ResourceSourceId");
                row.getAttribute("ProjectRoleId");
                row.getAttribute("StartDateActive");
                cbStmt.setInt(1, 
                              ((oracle.jbo.domain.Number)row.getAttribute("ObjectId")).intValue());
                cbStmt.setString(2, (String)row.getAttribute("ObjectType"));
                cbStmt.setInt(3, 
                              ((oracle.jbo.domain.Number)row.getAttribute("ResourceTypeId")).intValue());
                cbStmt.setInt(4, 
                              ((oracle.jbo.domain.Number)row.getAttribute("ResourceSourceId")).intValue());
                cbStmt.setInt(5, 
                              ((oracle.jbo.domain.Number)row.getAttribute("ProjectRoleId")).intValue());
                cbStmt.setDate(6, 
                               ((oracle.jbo.domain.Date)row.getAttribute("StartDateActive")).dateValue());
                //cbStmt.setString(1,GUID);
                cbStmt.execute();
                cbStmt.close();
                OAExceptionUtils.checkErrors(trxn);
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                }
                throw OAException.wrapperException(sqle);
            }
        }
        super.processFormRequest(pageContext, webBean);
    }
}
