/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.advanceawards.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsSummaryOtherVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsSummaryVOImpl;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;

/**
 * 用户点提交按钮后，普通部门汇总页面，待用户点击确定以提交数据
 */
public class AdvAwardsSummaryCO extends OAControllerImpl {
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
        String deptName = this.GetSessionValue(pageContext, "distDeptName");
        String strdeptOrgID = 
            this.GetSessionValue(pageContext, "distDeptOrgID");
        String str_date = this.GetSessionValue(pageContext, "distDate");
        if (strdeptOrgID.equals("") || str_date.length() < 6) {
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }

        String strYear = str_date.substring(0, 4);
        int deptOrgID = Integer.parseInt(strdeptOrgID);

        //2015-3-31,added by Yang.Gang    
        String str_month = str_date.substring(5);
        if (!str_month.equals("12")) {
            webBean.findChildRecursive("DPDecYearBonusCol").setRendered(false);
            webBean.findChildRecursive("DPDecYearBonusCol1").setRendered(false);
        } else {
            webBean.findChildRecursive("DPDecYearBonusCol").setRendered(true);
            webBean.findChildRecursive("DPDecYearBonusCol1").setRendered(true);
        }

        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OAHeaderBean header = 
            (OAHeaderBean)webBean.findIndexedChildRecursive("SummaryTableHdr");
        header.setText(deptName + "---" + strYear + "年" + str_month + 
                       "月员工奖金发放汇总");

        this.initWebButton(pageContext, webBean);
        AdvAwardsSummaryVOImpl vo1 = 
            (AdvAwardsSummaryVOImpl)am.findViewObject("AdvAwardsSummaryVO1");
        vo1.initSQL(deptOrgID, str_date, "SUMMARY");
        AdvAwardsSummaryOtherVOImpl vo2 = 
            (AdvAwardsSummaryOtherVOImpl)am.findViewObject("AdvAwardsSummaryOtherVO1");
        vo2.initSQL(deptOrgID, str_date, "SUMMARY");
        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("SummaryTable");
        table.queryData(pageContext);
        OAAdvancedTableBean table1 = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("SummaryTable1");
        table1.queryData(pageContext);
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

        if (pageContext.getParameter("ReturnButton") != null) {
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/AdvanceAwardsPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        } else if (pageContext.getParameter("OKButton") != null) {
            AdvanceAwardsAMImpl amImpl = 
                (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
            amImpl.submitDistData();
            OAException confirmMessage = 
                new OAException("提交成功！", OAException.INFORMATION);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/DeptQueryPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }
    }

    private String GetSessionValue(OAPageContext pageContext, 
                                   String strSessionName) {
        String strRtn = "";
        if (pageContext.getSessionValue(strSessionName) != null && 
            !pageContext.getSessionValue(strSessionName).toString().equals("")) {
            strRtn = pageContext.getSessionValue(strSessionName).toString();
        }
        return strRtn;
    }

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

    /* 初始化页面Button
       用户点击提交后，首先页面的按钮状态修改为disabled，执行后台处理代码，这样就避免了用户两次点击改按钮，造成重复提交的结果
     * */

    private void initWebButton(OAPageContext pageContext, OAWebBean webBean) {
        String javaScript = 
            "javascript:function CtlBtnStatus(obj)" + "{ obj.disabled=true; return true;" + 
            "}";

        pageContext.putJavaScriptFunction("CtlBtnStatus", javaScript);
        OASubmitButtonBean submitButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("OKButton");
        if (submitButton != null)
            submitButton.setOnClick("javascript:return CtlBtnStatus(this);");
    }

}
