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
import cux.oracle.apps.per.bonus.advanceawards.server.BonusItem;

import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 赛迪集团中管及以上（含集团、股份、上海）奖金发放情况查询页面
 * created by yang.gang,2015-5-18
 */
public class MidMgrQueryCO extends OAControllerImpl {
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
        OAMessageChoiceBean dateBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
        /*if( !strResponseName.equals("中冶赛迪_奖金管理_中管奖金发放") )   {  // 安全性验证
          OASubmitButtonBean subBtn = (OASubmitButtonBean)webBean.findChildRecursive("SubmitButton");
          subBtn.setRendered(false);
          dateBean.setReadOnly(true);
          this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
          return;
      }*/
        pageContext.putSessionValue("distDeptOrgID", "-2");
        pageContext.putSessionValue("distDeptName", "中国恩菲中管及以上");

        int deptOrgID = -2;
        String strDeptName = "中国恩菲中管及以上";
        String strDate = this.GetSessionValue(pageContext, "distDate");
        ;
        if (strDate.equals("")) {
            Date today = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            strDate = df.format(today);
        }
        dateBean.setDefaultValue(strDate);

        pageSearchData(pageContext, webBean, deptOrgID, strDeptName, strDate);
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
        String event = pageContext.getParameter("event");

        int deptOrgID = -2;
        String strdeptName = "中国恩菲中管及以上";

        if ("search".equals(event)) {
            OAMessageChoiceBean distDateBean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
            String distDate = distDateBean.getSelectionValue(pageContext);
            pageContext.putSessionValue("distDate", distDate);
            pageSearchData(pageContext, webBean, deptOrgID, strdeptName, 
                           distDate);
        } else if (pageContext.getParameter("SubmitButton") != null) {
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/MidMgrDisPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }
    }

    //查询页面需要的数据，包括当前部门、可发放余额等信息

    public void pageSearchData(OAPageContext pageContext, OAWebBean webBean, 
                               int deptOrgID, String strDeptName, 
                               String bonusDistDate) {
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);

        BonusItem item = amImpl.getDistInfo(deptOrgID, bonusDistDate, "");
        double totalBalance = item.totalBalance;
        double monthBalance = item.monthBalance;
        double deptPerNum = item.deptPerNum;
        double otherPerNum = item.otherPerNum;
        double totalDist = item.totalDist;
        OAMessageTextInputBean currentDeptBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("CurrentDept");
        currentDeptBean.setValue(pageContext, strDeptName);
        OAMessageTextInputBean balanceAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("BalanceAmount");
        balanceAmountBean.setValue(pageContext, totalBalance);
        OAMessageTextInputBean monthAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("MonthAmount");
        monthAmountBean.setValue(pageContext, monthBalance);
        OAMessageTextInputBean AvailAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("AvailAmount");
        AvailAmountBean.setValue(pageContext, totalBalance + monthBalance);
        OAMessageTextInputBean DeptPerAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("DeptPersonAmount");
        DeptPerAmountBean.setValue(pageContext, deptPerNum);
        OAMessageTextInputBean DistPerAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("DistPersonAmount");
        DistPerAmountBean.setValue(pageContext, otherPerNum);
        OAMessageTextInputBean totalDistBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("DistAmount");
        totalDistBean.setValue(pageContext, totalDist);

        AdvAwardsSummaryVOImpl vo1 = 
            (AdvAwardsSummaryVOImpl)am.findViewObject("AdvAwardsSummaryVO1");
        vo1.initSQL(deptOrgID, bonusDistDate, "QUERY");

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("SummaryTable");
        table.queryData(pageContext);
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
