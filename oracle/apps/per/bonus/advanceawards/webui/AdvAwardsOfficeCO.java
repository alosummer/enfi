/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.advanceawards.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsOfficeLovVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

/**
 * 员工奖金发放，部门选择页面
 * 若是从职责 中冶赛迪_奖金管理_中管奖金发放 进来，则直接进中管奖金发放页面
 */
public class AdvAwardsOfficeCO extends OAControllerImpl {
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

        String strResponseName = pageContext.getResponsibilityName();

        if (strResponseName.equals("中国恩菲_奖金管理_中高管奖金发放")) {
            pageContext.putSessionValue("distDeptOrgID", "-2");
            pageContext.putSessionValue("distDeptName", "中国恩菲中管及以上");
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/MidMgrQueryPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
            return;
        }


        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        //根据当前操作人的行政级别来给予权限
        int personId = amImpl.getLoginPersonId();
        AdvAwardsOfficeLovVOImpl deptLovVO = 
            (AdvAwardsOfficeLovVOImpl)am.findViewObject("AdvAwardsOfficeLovVO1");
        if (personId != 0)
            deptLovVO.initQuery(personId);

        //判断是否只有1个部门，只有1个部门则直接跳转到下个页面
        String strRtn = deptLovVO.GetDeptInfo();
        if (strRtn.equals("") || strRtn.length() == 0)
            return;
        String s[] = strRtn.split("#=#");
        if (s.length != 2)
            return;
        pageContext.putSessionValue("distDeptOrgID", s[0]);
        pageContext.putSessionValue("distDeptName", s[1]);
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/OfficeQueryPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
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
        if (pageContext.getParameter("EnterButton") != null) {
            OAMessageChoiceBean deptNameBean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDept");
            String DeptOrgID = 
                deptNameBean.getSelectionValue(pageContext).toString();
            String DeptName = 
                deptNameBean.getSelectionText(pageContext).toString();
            pageContext.putSessionValue("distDeptOrgID", DeptOrgID);
            pageContext.putSessionValue("distDeptName", DeptName);
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/OfficeQueryPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }
    }

}
