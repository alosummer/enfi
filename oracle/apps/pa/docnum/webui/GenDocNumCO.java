/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.docnum.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

/**
 * Controller for ...
 */
public class GenDocNumCO extends OAControllerImpl {
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
        am.invokeMethod("init");
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String event = pageContext.getParameter(this.EVENT_PARAM);
        if ("Apply".equals(event)) {
            am.invokeMethod("apply");
            throw new OAException("成功:已为你指定的项目文档交付物给号!", 
                                  OAException.INFORMATION);
        }
        if ("reset".equals(event)) {
            resetWebBean(pageContext, webBean);
        }
    }

    public void resetWebBean(OAPageContext pageContext, OAWebBean webBean) {
        String[] beans = { "projectNum", "Desc" };
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
