/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 |  7-DEC-2016 Wei Yi Created                                               |
 +===========================================================================*/
package cux.oracle.apps.pa.project.webui;

import cux.oracle.apps.pa.project.server.CuxAddProjectPartiesVORowImpl;
import cux.oracle.apps.pa.project.server.RepSepcVOImpl;

import cux.oracle.apps.pa.project.server.RepSepcVORowImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Enumeration;
import java.util.Hashtable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARowValException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.server.OAExceptionUtils;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.pa.project.server.AddProjectPartiesVOImpl;
import oracle.apps.pa.project.server.AddProjectPartiesVORowImpl;
import oracle.apps.pa.project.webui.AddProjectMembersTopCO;

import oracle.dss.rules.discriminator.NumberValueDiscriminator;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import oracle.jbo.server.EntityImpl;

/**
 * Controller for ...
 */
public class CuxAddProjectMembersTopCO extends AddProjectMembersTopCO {
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
        //        ViewObject vo = am.findViewObject("AddNewProjectPartiesVO");
        //        AttributeDef[] ads =  vo.getAttributeDefs();
        //        int i = 0;
        //        for (AttributeDef ad:ads) {
        //            if ("SPEC".equals(ad.getName())) {
        //                i ++;
        //            }
        //        }
        //        if (i == 0) {
        //            vo.addDynamicAttribute("SPEC");
        //        }
        //        
        //        ViewObject addedVO = am.findViewObject("AddedProjectPartiesVO");
        //        AttributeDef[] adads =  addedVO.getAttributeDefs();
        //        int j = 0;
        //        for (AttributeDef ad:adads) {
        //            if ("SPEC".equals(ad.getName())) {
        //                j ++;
        //            }
        //        }
        //        if (j == 0) {
        //            addedVO.addDynamicAttribute("SPEC");
        //        }        
        String respName = pageContext.getResponsibilityName();
        if (respName.indexOf("项目管理_项目管理员") > 0) {
            OAWebBean roleNameLov = webBean.findChildRecursive("RoleNameLov");
            roleNameLov.setAttributeValue(this.RENDERED_ATTR, true);
            OAWebBean roleListNames = 
                webBean.findChildRecursive("RoleListNames");
            roleListNames.setAttributeValue(this.RENDERED_ATTR, false);
            OAWebBean projectRoleLov = 
                webBean.findChildRecursive("projectRoleLov");
            projectRoleLov.setAttributeValue(this.RENDERED_ATTR, false);
        } else {
            OAWebBean roleNameLov = webBean.findChildRecursive("RoleNameLov");
            roleNameLov.setAttributeValue(this.RENDERED_ATTR, false);
            OAWebBean roleListNames = 
                webBean.findChildRecursive("RoleListNames");
            roleListNames.setAttributeValue(this.RENDERED_ATTR, false);
            OAWebBean projectRoleLov = 
                webBean.findChildRecursive("projectRoleLov");
            projectRoleLov.setAttributeValue(this.RENDERED_ATTR, true);
        }

        if (pageContext.getParameter("rep_sepc") != null && 
            pageContext.getParameter("AddAnother") != null) {
            String sql = 
                "select cux_common_pkg.get_lookup_meaning('ENFI_SPECIALITY','" + 
                pageContext.getParameter("rep_sepc") + "') from  dual";
            String sepcName = (String)this.getSqlValue(am, sql);
            ViewObject addVO = am.findViewObject("AddedProjectPartiesVO");
            CuxAddProjectPartiesVORowImpl row = 
                (CuxAddProjectPartiesVORowImpl)addVO.getCurrentRow();
            if (row != null) {
                row.setSpecName(sepcName);
                //row.setAttribute("SpecName",sepcName);
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
        if (pageContext.getParameter("Apply") != null || 
            pageContext.getParameter("AddAnother") != null) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            Object addTeamMemeber = 
                am.getOADBTransaction().getValue("AddTeamMemberRadioBeanValue");
            String repSepc = pageContext.getParameter("rep_sepc");
            String flag = "";
            if (addTeamMemeber != null && 
                addTeamMemeber.toString().equals("Checked")) {
                if (am.findViewObject("RepSepcVO") != null) {
                    am.findViewObject("RepSepcVO").remove();
                }
                if (am.findViewObject("SpecCheckVO") != null) {
                    am.findViewObject("SpecCheckVO").remove();
                }
                AddProjectPartiesVOImpl vo = 
                    (AddProjectPartiesVOImpl)am.findViewObject("AddNewProjectPartiesVO");
                AddProjectPartiesVORowImpl row = 
                    (AddProjectPartiesVORowImpl)vo.first();
                //create check vo to check is specialty needed
                String sql = 
                    "SELECT attribute1\n" + "FROM pa_project_role_types_b\n" + 
                    "WHERE project_role_id = " + row.getProjectRoleId();
                ViewObject specCheckVO = 
                    am.createViewObjectFromQueryStmt("SpecCheckVO", sql);
                specCheckVO.setMaxFetchSize(-1);
                specCheckVO.executeQuery();
                if (specCheckVO.getRowCount() > 0) {
                    Row checkRow = specCheckVO.first();
                    flag = (String)checkRow.getAttribute("ATTRIBUTE1");
                    if ("Y".equals(flag) && 
                        (pageContext.getParameter("rep_sepc") == null || 
                         "".equals(pageContext.getParameter("rep_sepc")))) {
                        MessageToken[] tokens = 
                        { new MessageToken("REP", pageContext.getParameter("RoleNameLov")) };
                        throw new OAException("CUX", "CUX-PM-E005", tokens, 
                                              OAException.ERROR, null);
                    }
                }
                //create responsibility specialty ralation viewobject
                RepSepcVOImpl repSepcVO = 
                    (RepSepcVOImpl)am.createViewObject("RepSepcVO", 
                                                       "cux.oracle.apps.pa.project.server.RepSepcVO");
                repSepcVO.setWhereClause("1=2");
                repSepcVO.setMaxFetchSize(-1);
                repSepcVO.executeQuery();
                RepSepcVORowImpl repSepcRow = 
                    (RepSepcVORowImpl)repSepcVO.createRow();
                Number objectId = null;
                try {
                    objectId = 
                            new Number((String)am.getOADBTransaction().getValue("paProjectId"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                repSepcRow.setObjectId(objectId);
                repSepcRow.setObjectType("PA_PROJECTS");
                Number resourceTypeId = null;
                try {
                    resourceTypeId = new Number(row.getResourceTypeId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Number resouceId = null;
                try {
                    resouceId = new Number(row.getResourceId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Number projectRoleId = null;
                try {
                    projectRoleId = new Number(row.getProjectRoleId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                repSepcRow.setAtrribute1(am.getOADBTransaction().getSequenceValue("CUX_PA_REP_SEPC_S").stringValue());
                repSepcRow.setResourceTypeId(resourceTypeId);
                repSepcRow.setResourceSourceId(resouceId);
                repSepcRow.setProjectRoleId(projectRoleId);
                repSepcRow.setStartDateActive(row.getStartDateActive());
                repSepcRow.setSpecialtyCode(repSepc);
                repSepcRow.setNewRowState(Row.STATUS_NEW);
                repSepcVO.insertRow(repSepcRow);
            }
            ViewObject addVO = am.findViewObject("AddedProjectPartiesVO");
            ViewObject newVO = am.findViewObject("AddNewProjectPartiesVO");
            System.out.println(addVO.getRowCount());
            System.out.println(newVO.getRowCount());
        }
        super.processFormRequest(pageContext, webBean);
        String source = pageContext.getParameter(this.SOURCE_PARAM);
        String event = pageContext.getParameter(this.EVENT_PARAM);
        if ("PersonName".equals(source) && 
            ("lovUpdate".equals(event) || "lovValidate".equals(event))) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            String lovInputSourceId = pageContext.getLovInputSourceId();
            Hashtable lovResults = 
                pageContext.getLovResultsFromSession(lovInputSourceId);
            if (lovResults != null) {
                String resourceId = (String)lovResults.get("ResourceId");
                String sql = 
                    "SELECT cux_common_pkg.get_lookup_code('ENFI_SPECIALITY',\n" + 
                    "                                      cux_common_pkg.get_person_spec(" + 
                    resourceId + "))\n" + "FROM   dual";
                String spec = (String)this.getSqlValue(am, sql);
                ViewObject vo = am.findViewObject("AddNewProjectPartiesVO");
                CuxAddProjectPartiesVORowImpl row = 
                    (CuxAddProjectPartiesVORowImpl)vo.getCurrentRow();
                row.setSPEC(spec);
                //row.setAttribute("SPEC",spec);
            } else {
                ViewObject vo = am.findViewObject("AddNewProjectPartiesVO");
                CuxAddProjectPartiesVORowImpl row = 
                    (CuxAddProjectPartiesVORowImpl)vo.getCurrentRow();
                row.setSPEC(null);
                //row.setAttribute("SPEC",null);
            }
        }
    }

    public Object getSqlValue(OAApplicationModule am, String sql) {
        ViewObject vo = am.findViewObject("QueryVO");
        Object result = null;
        if (vo != null) {
            vo.remove();
        }
        vo = am.createViewObjectFromQueryStmt("QueryVO", sql);
        vo.setMaxFetchSize(-1);
        vo.executeQuery();
        if (vo.hasNext()) {
            result = vo.next().getAttribute(0);
        }
        return result;
    }
}
