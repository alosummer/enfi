/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.docinfo.webui;

import com.sun.java.util.collections.HashMap;

import cux.oracle.apps.cux.docinfo.server.CuxDocinfoManageAMImpl;

import cux.oracle.apps.cux.docinfo.server.CuxDocinfoManageTVOImpl;

import cux.oracle.apps.cux.docinfo.server.CuxDocinfoManageTVORowImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class CuxDocinfoManageCreatePGCO extends OAControllerImpl {
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
        CuxDocinfoManageAMImpl pgAM = 
            (CuxDocinfoManageAMImpl)pageContext.getRootApplicationModule();
        String docId = pageContext.getParameter("UpdateDocId");
        if (docId == null || "".equals(docId)) {
            pgAM.initCreatePG();
            webBean.findChildRecursive("DocumentTypeName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                             Boolean.FALSE);
            webBean.findChildRecursive("CountryName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                        Boolean.FALSE);
            webBean.findChildRecursive("DocNumber").setAttributeValue(this.RENDERED_ATTR, 
                                                                      Boolean.FALSE);
            webBean.findChildRecursive("IsArchive").setAttributeValue(this.RENDERED_ATTR, 
                                                                      Boolean.FALSE);
            webBean.findChildRecursive("IsEndActive").setAttributeValue(this.RENDERED_ATTR, 
                                                                        Boolean.FALSE);
        } else {
            pgAM.initCreatePG(docId);
            webBean.findChildRecursive("IsArchive").setAttributeValue(this.RENDERED_ATTR, 
                                                                      Boolean.TRUE);
            webBean.findChildRecursive("IsEndActive").setAttributeValue(this.RENDERED_ATTR, 
                                                                        Boolean.TRUE);
            webBean.findChildRecursive("DocumentTypeName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                             Boolean.TRUE);
            webBean.findChildRecursive("CountryName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                        Boolean.TRUE);
            webBean.findChildRecursive("DocNumber").setAttributeValue(this.RENDERED_ATTR, 
                                                                      Boolean.TRUE);

            CuxDocinfoManageTVOImpl vo = pgAM.getCuxDocinfoManageTVO1();
            CuxDocinfoManageTVORowImpl row = 
                (CuxDocinfoManageTVORowImpl)vo.first();

            if ("CON".equals(row.getDocTypeCode())) {
                webBean.findChildRecursive("DocNumber").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                          Boolean.FALSE);
            }

            /*归档后仅能失效或者取消归档*/
            if ("Y".equals(row.getIsArchive())) {
                webBean.findChildRecursive("DocNumber").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                          Boolean.TRUE);
                webBean.findChildRecursive("AreaAlias").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                          Boolean.TRUE);
                webBean.findChildRecursive("ProjectName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.TRUE);
                webBean.findChildRecursive("RelationCorpName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                                 Boolean.TRUE);
                webBean.findChildRecursive("OtherCorpName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                              Boolean.TRUE);
                webBean.findChildRecursive("LangName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                         Boolean.TRUE);
                webBean.findChildRecursive("IsSigned").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                         Boolean.TRUE);
                webBean.findChildRecursive("DocCopyName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.TRUE);
                webBean.findChildRecursive("IsEvlSheet").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                           Boolean.TRUE);
                webBean.findChildRecursive("SubmitterPersonName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                                    Boolean.TRUE);
                webBean.findChildRecursive("Remark").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                       Boolean.TRUE);

            }
            if ("Y".equals(row.getIsEndActive())) {
                webBean.findChildRecursive("DocNumber").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                          Boolean.TRUE);
                webBean.findChildRecursive("AreaAlias").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                          Boolean.TRUE);
                webBean.findChildRecursive("ProjectName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.TRUE);
                webBean.findChildRecursive("RelationCorpName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                                 Boolean.TRUE);
                webBean.findChildRecursive("OtherCorpName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                              Boolean.TRUE);
                webBean.findChildRecursive("LangName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                         Boolean.TRUE);
                webBean.findChildRecursive("IsSigned").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                         Boolean.TRUE);
                webBean.findChildRecursive("DocCopyName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.TRUE);
                webBean.findChildRecursive("IsEvlSheet").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                           Boolean.TRUE);
                webBean.findChildRecursive("SubmitterPersonName").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                                    Boolean.TRUE);
                webBean.findChildRecursive("Remark").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                       Boolean.TRUE);
                webBean.findChildRecursive("IsArchive").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                          Boolean.TRUE);
                webBean.findChildRecursive("IsEndActive").setAttributeValue(this.READ_ONLY_ATTR, 
                                                                            Boolean.TRUE);

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
        CuxDocinfoManageAMImpl pgAM = 
            (CuxDocinfoManageAMImpl)pageContext.getRootApplicationModule();
        if (pageContext.getParameter("SaveBTN") != null) {
            pgAM.commit();
            pageContext.putDialogMessage(new OAException("保存成功", 
                                                         OAException.INFORMATION));
            HashMap params = new HashMap(1);
            params.put("RequeryFlag", "Y");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/docinfo/webui/CuxDocinfoManageResultPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, params, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        } else if (pageContext.getParameter("CancelBTN") != null) {
            pgAM.rollback();
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/cux/docinfo/webui/CuxDocinfoManageResultPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_YES);
        }
    }

}
