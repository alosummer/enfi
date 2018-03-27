/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.docnum.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

/**
 * Controller for ...
 */
public class DocNumSearchCO extends OAControllerImpl {
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
        String security = pageContext.getParameter("Security");
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        pageContext.putSessionValue("Security", security);
        am.invokeMethod("initSearchPVO");
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
        String event = pageContext.getParameter(this.EVENT_PARAM);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if ("reset".equals(event)) {
            resetWebBean(pageContext, webBean);
        }
        if ("search".equals(event)) {
            am.invokeMethod("doSearch");
        }
        if ("detail".equals(event)) {
            HashMap parameters = new HashMap();
            parameters.put("ReqBatchId", 
                           pageContext.getParameter("ReqBatchId"));
            parameters.put("Security", 
                           pageContext.getSessionValue("Security")); // retain AM
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/docnum/webui/DocNumDetailPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, parameters, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
    }

    public void resetWebBean(OAPageContext pageContext, OAWebBean webBean) {
        String[] beans =
        /*,"personId","projectId"*/ { "projectNum", "projectName", "status", 
                                      "DocName", "Requster", "ReqStartDate", 
                                      "ReqEndDate" };
        for (int i = 0; i < beans.length; i++) {
            OAWebBean bean = webBean.findChildRecursive(beans[i]);
            if (bean instanceof OAMessageLovInputBean) {
                OAMessageLovInputBean b = (OAMessageLovInputBean)bean;
                b.setText(pageContext, null);
            }
            if (bean instanceof OAMessageChoiceBean) {
                OAMessageChoiceBean b = (OAMessageChoiceBean)bean;
                b.setText(pageContext, null);
            }
            if (bean instanceof OAFormValueBean) {
                OAFormValueBean b = (OAFormValueBean)bean;
                b.setText(pageContext, null);
            }
            if (bean instanceof OAMessageTextInputBean) {
                OAMessageTextInputBean b = (OAMessageTextInputBean)bean;
                b.setText(pageContext, null);
            }
            if (bean instanceof OAMessageDateFieldBean) {
                OAMessageDateFieldBean b = (OAMessageDateFieldBean)bean;
                b.setValue(null);
            }
        }
    }
}
