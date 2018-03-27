/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.pmp.webui;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable; 

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.nav.OASideNavBean;

import oracle.bali.share.util.IntegerUtils;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class PmpSearchCO extends PmpBaseCO {
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
        int employeeId = pageContext.getEmployeeId();
        if(am.getOADBTransaction().isDirty()){
            am.getOADBTransaction().rollback();
        }
        if (employeeId == 
            258) { //Whether the person who landed is zhang xiaoming.
            OAWebBean save = webBean.findChildRecursive("save");
            save.setAttributeValue(RENDERED_ATTR, Boolean.TRUE);
            OAWebBean attribute1 = webBean.findChildRecursive("Attribute1");
            attribute1.setAttributeValue(RENDERED_ATTR, Boolean.TRUE);
            OAWebBean attribute2 = webBean.findChildRecursive("Attribute2");
            attribute2.setAttributeValue(RENDERED_ATTR, Boolean.TRUE);
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
        if ("Search".equals(event)) { 
            am.getOADBTransaction().rollback();
            String projectId = pageContext.getParameter("projectId");
            Serializable paras[] = { projectId };
            am.invokeMethod("searchPmp", paras);

        }
        if ("NewVersion".equals(event)) {
            String projectId = pageContext.getParameter("projectId");
            String[] pars = { projectId, projectId };
            Number flag = 
                (Number)this.getSqlValue(am, this.SQL_PMP_STATUS_VAL, pars);
            if (flag.intValue() > 0) { 
                throw new OAException("CUX", "CUX-PM-E021", null, 
                                      OAException.ERROR, null);
            }
            Serializable paras[] = { projectId, SMT_NEW_VERSION };
            am.invokeMethod("createPmpNewVerion", paras);
        }
        if ("Save".equals(event)) {
            am.getOADBTransaction().commit();
        }
        if ("Detail".equals(event)) {
            am.getOADBTransaction().rollback();
            HashMap parameters = new HashMap();
            parameters.put("ProjectId", pageContext.getParameter("ProjectId"));
            parameters.put("VersionNum", 
                           pageContext.getParameter("VersionNum"));
            parameters.put("PmpId", pageContext.getParameter("PmpId"));
            pageContext.forwardImmediately("CUXPMPSUMMARY", 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           "", parameters, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
        if ("Delete".equals(event)) {
            String projectId = pageContext.getParameter("ProjectId");
            String versionNum = pageContext.getParameter("VersionNum");
            Serializable paras[] = { projectId, versionNum };
            am.invokeMethod("deletePmpVersion", paras);
        }
    }

    public Object getSqlValue(OAApplicationModule am, String sql, 
                              String[] paras) {
        ViewObject vo = am.findViewObject("QueryVO");
        Object result = null;
        if (vo != null) {
            vo.remove();
        }
        vo = am.createViewObjectFromQueryStmt("QueryVO", sql);
        vo.setMaxFetchSize(-1);
        for (int i = 0; i < paras.length; i++) {
            vo.setWhereClauseParam(i, paras[i]);
        }
        vo.executeQuery();
        if (vo.hasNext()) {
            result = vo.next().getAttribute(0);
        }
        return result;
    }
}
