/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.advanceawards.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

/**
 * 提交部门其他人员时，警告页面
 * added by Yang.Gang,2015-6-15
 */
public class OtherWarningCO extends OAControllerImpl {
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
        AdvanceAwardsAMImpl am = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        String strOtherDistList = am.GetOtherDistList();
        String strMessage = "提示信息: 是否给其他部门员工 ";
        strMessage = strMessage.concat(strOtherDistList).concat(" 发放奖金？");
        OAMessageTextInputBean warningBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("Warning");
        warningBean.setValue(pageContext, strMessage);
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
        if (pageContext.getParameter("YesButton") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/AdvanceAwardsSummaryPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        } else if (pageContext.getParameter("NoButton") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/AdvanceAwardsPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
    }

}
