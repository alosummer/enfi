/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.advanceawards.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsAttachmentVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsDeptPerVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsDeptPersonVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsOtherPerVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsSummaryOtherVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsSummaryVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;

import cux.oracle.apps.per.bonus.advanceawards.server.BonusItem;

import cux.oracle.apps.per.bonus.advanceawards.server.RejDistVOImpl;

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

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;


/**
 * 部门奖金发放，查询页面
 */
public class DeptQueryCO extends OAControllerImpl {
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

        String strdeptOrgID = 
            this.GetSessionValue(pageContext, "distDeptOrgID");
        String strDeptName = this.GetSessionValue(pageContext, "distDeptName");

        OAMessageChoiceBean dateBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");

        if (strdeptOrgID.equals("")) {
            dateBean.setReadOnly(true);
            OASubmitButtonBean subBtn = 
                (OASubmitButtonBean)webBean.findChildRecursive("SubmitButton");
            subBtn.setRendered(false);
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }
        int deptOrgID = Integer.parseInt(strdeptOrgID);

        String strDate = this.GetSessionValue(pageContext, "distDate");
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

        String strdeptName = this.GetSessionValue(pageContext, "distDeptName");
        String strdeptOrgID = 
            this.GetSessionValue(pageContext, "distDeptOrgID");
        if (strdeptOrgID.equals("")) {
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }

        int deptOrgID = Integer.parseInt(strdeptOrgID);
        String event = pageContext.getParameter("event");
        if ("search".equals(event)) {
            OAMessageChoiceBean distDateBean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
            String distDate = distDateBean.getSelectionValue(pageContext);
            pageContext.putSessionValue("distDate", distDate);
            pageSearchData(pageContext, webBean, deptOrgID, strdeptName, 
                           distDate);
        } else if (pageContext.getParameter("SubmitButton") != null) {
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/AdvanceAwardsPG", 
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
        AdvAwardsSummaryOtherVOImpl vo2 = 
            (AdvAwardsSummaryOtherVOImpl)am.findViewObject("AdvAwardsSummaryOtherVO1");
        vo2.initSQL(deptOrgID, bonusDistDate, "QUERY");
        RejDistVOImpl vo3 = (RejDistVOImpl)am.findViewObject("RejDistVO1");
        vo3.initSQL(deptOrgID, bonusDistDate);

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("SummaryTable");
        table.queryData(pageContext);
        OAAdvancedTableBean table1 = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("SummaryTable1");
        table1.queryData(pageContext);
        OAAdvancedTableBean table2 = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("SummaryTable1");
        table2.queryData(pageContext);
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
