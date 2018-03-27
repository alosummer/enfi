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

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 用户点提交按钮后，赛迪集团中管及以上（含集团、股份、上海）汇总页面，待用户点击确定以提交数据
 * created by yang.gang, 2015-5-19
 */
public class MidMgrSummaryCO extends OAControllerImpl {
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
        //      if( !strResponseName.equals("中冶赛迪_奖金管理_中管奖金发放") ) {  // 安全性验证      
        //          OASubmitButtonBean okBtn = (OASubmitButtonBean)webBean.findChildRecursive("OKButton");
        //          okBtn.setRendered(false);
        //          OASubmitButtonBean returnBtn = (OASubmitButtonBean)webBean.findChildRecursive("ReturnButton");
        //          returnBtn.setRendered(false);          
        //          return;
        //      }

        int deptOrgID = -2;
        String deptName = "中国恩菲中管及以上";

        String str_date = this.GetSessionValue(pageContext, "distDate");
        if (str_date.length() < 6) {
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }
        String str_year = str_date.substring(0, 4);
        String str_month = str_date.substring(5);
        if (!str_month.equals("12"))
            webBean.findChildRecursive("DPDecYearBonusCol").setRendered(false);
        else
            webBean.findChildRecursive("DPDecYearBonusCol").setRendered(true);

        OAApplicationModule am = pageContext.getApplicationModule(webBean);

        OAHeaderBean header = 
            (OAHeaderBean)webBean.findIndexedChildRecursive("SummaryTableHdr");
        header.setText(deptName + "---" + str_year + "年" + str_month + 
                       "月员工奖金发放汇总");

        AdvAwardsSummaryVOImpl vo1 = 
            (AdvAwardsSummaryVOImpl)am.findViewObject("AdvAwardsSummaryVO1");
        vo1.initSQL(deptOrgID, str_date, "SUMMARY");
        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("SummaryTable");
        table.queryData(pageContext);
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
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/MidMgrDisPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        } else if (pageContext.getParameter("OKButton") != null) {
            AdvanceAwardsAMImpl amImpl = 
                (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
            int irtn = amImpl.submitDistMidMgrData();

            String strMessage = "提交成功！共更新了 " + String.valueOf(irtn) + " 条记录";
            OAException confirmMessage = 
                new OAException(strMessage, OAException.INFORMATION);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/MidMgrQueryPG", 
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

}
