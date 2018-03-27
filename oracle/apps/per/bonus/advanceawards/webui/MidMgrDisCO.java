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
import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.BonusItem;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Hashtable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 赛迪集团中管及以上（含集团、股份、上海）奖金发放情况发放页面
 * created by yang.gang,2015-5-18
 */
public class MidMgrDisCO extends OAControllerImpl {
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
        OAMessageChoiceBean yearBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
        OAMessageChoiceBean bonusBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("BonusType");

        //      if( !strResponseName.equals("中冶赛迪_奖金管理_中管奖金发放") ) {  // 安全性验证                 
        //           bonusBean.setReadOnly(true);           
        //           yearBean.setReadOnly(true);
        //           OASubmitButtonBean subBtn = (OASubmitButtonBean)webBean.findChildRecursive("SubmitButton");
        //           subBtn.setRendered(false);
        //           OASubmitButtonBean saveBtn = (OASubmitButtonBean)webBean.findChildRecursive("SaveButton");
        //           saveBtn.setRendered(false);
        //           this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
        //           return;
        //      }     

        String distDate = this.GetSessionValue(pageContext, "distDate");
        if (distDate.equals("")) {
            Date today = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            distDate = df.format(today);
            pageContext.putSessionValue("distDate", distDate);
        }
        yearBean.setDefaultValue(distDate);

        String strBonusType = this.GetSessionValue(pageContext, "bonusType");
        if (strBonusType.equals("")) {
            strBonusType = "绩效奖金";
            pageContext.putSessionValue("bonusType", strBonusType);
        }
        bonusBean.setDefaultValue(strBonusType);

        //只读设置，已批准的记录不能再进行编辑, edit by yang.gang, 2015-5-18
        SetTableColumnReadOnly("Text", "DeptPersonAwards", "ReadOnlyFlag", 
                               "AdvAwardsDeptPersonVO1", webBean);
        SetTableColumnReadOnly("Text", "DeptPersonNote", "ReadOnlyFlag", 
                               "AdvAwardsDeptPersonVO1", webBean);

        int ideptOrgID = -2;
        String strDeptName = "中国恩菲中管及以上";

        pageSearchData(pageContext, webBean, ideptOrgID, strDeptName, distDate, 
                       strBonusType);
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
        String event = pageContext.getParameter("event");

        int deptOrgID = -2;
        String strdeptName = "中国恩菲中管及以上";
        String bonusType = this.GetSessionValue(pageContext, "bonusType");
        String distDate = this.GetSessionValue(pageContext, "distDate");

        if ("search".equals(event)) {
            OAMessageChoiceBean bonusTypeBean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("BonusType");
            bonusType = bonusTypeBean.getSelectionValue(pageContext);
            pageContext.putSessionValue("bonusType", bonusType);
            OAMessageChoiceBean distDateBean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
            distDate = distDateBean.getSelectionValue(pageContext);
            pageContext.putSessionValue("distDate", distDate);

            AdvAwardsDeptPersonVOImpl deptPersonVO = 
                (AdvAwardsDeptPersonVOImpl)am.findViewObject("AdvAwardsDeptPersonVO1");
            deptPersonVO.initQuery(bonusType, distDate, deptOrgID);

            pageSearchData(pageContext, webBean, deptOrgID, strdeptName, 
                           distDate, bonusType);
        } else if (pageContext.getParameter("SaveButton") != null) {
            //根据发放日期、奖金类型和操作人员所在部门来保存当前页面的发放数据
            int result = 
                saveDistData(pageContext, webBean, distDate, bonusType, 
                             deptOrgID);
            //数据保存成功，则刷新页面数据显示
            if (result == 0) {
                pageSearchData(pageContext, webBean, deptOrgID, strdeptName, 
                               distDate, bonusType);
            }
        } else if (pageContext.getParameter("ReturnButton") != null) {
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/MidMgrQueryPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        } else if (pageContext.getParameter("SubmitButton") != null) {
            //提交操作先执行页面数据保存
            int result = 
                saveDistData(pageContext, webBean, distDate, bonusType, 
                             deptOrgID);
            AdvAwardsDeptPersonVOImpl deptPersonVO = 
                (AdvAwardsDeptPersonVOImpl)am.findViewObject("AdvAwardsDeptPersonVO1");
            deptPersonVO.initQuery(bonusType, distDate, deptOrgID);
            if (result == 0) {
                pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/MidMgrSummaryPG", 
                                          null, 
                                          OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                          null, null, true, 
                                          OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                          OAWebBeanConstants.IGNORE_MESSAGES);
            } else
                this.ShowMessage(pageContext, "保存数据出错！");
        }
    }

    //查询页面需要的数据，包括当前部门、可发放余额等信息

    public void pageSearchData(OAPageContext pageContext, OAWebBean webBean, 
                               int deptOrgID, String strDeptName, 
                               String bonusDistDate, String bonusType) {
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);

        AdvAwardsDeptPersonVOImpl deptPersonVO = 
            (AdvAwardsDeptPersonVOImpl)am.findViewObject("AdvAwardsDeptPersonVO1");
        deptPersonVO.initQuery(bonusType, bonusDistDate, deptOrgID);

        OAAdvancedTableBean deptPersonTable = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("DeptPersonTable");
        deptPersonTable.queryData(pageContext);

        BonusItem item = 
            amImpl.getDistInfo(deptOrgID, bonusDistDate, bonusType);
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
    }

    private int saveDistData(OAPageContext pageContext, OAWebBean webBean, 
                             String bonusDistDate, String bonusTypeName, 
                             int deptOrgID) {
        OAMessageTextInputBean AvailAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("AvailAmount");
        double availAmount = 
            Double.parseDouble(AvailAmountBean.getValue(pageContext).toString());
        OAMessageTextInputBean DistAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("DistAmount");
        double distAmount = 
            Double.parseDouble(DistAmountBean.getValue(pageContext).toString());
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);

        //检查发放数据是否合法（人员信息）
        int result = amImpl.checkDistDataMidMgr();
        //根据检查结果给予提示信息
        if (result == 1) {
            checkResult(1, 0, pageContext);
        } else if (result == 2) {
            checkResult(2, 0, pageContext);
        } else if (result == 3) {
            checkResult(0, 1, pageContext);
        } else if (result == 4) {
            checkResult(0, 2, pageContext);
        } else if (result == 0) {
            //检查发放奖金额度信息
            boolean checkResult = 
                amImpl.checkDistAmount(availAmount - distAmount, 
                                       bonusTypeName);
            //如果额度未超，则保存各个列表数据并给予操作成功的提示
            if (checkResult) {
                amImpl.saveDeptPersonDist(bonusDistDate, bonusTypeName, 
                                          deptOrgID);
                checkResult(0, 0, pageContext);
                return 0;
            }
            //发放超额，给予错误警告并不保存数据
            else {
                OAException message = 
                    new OAException("发放额度超过了部门总额度，请检查！", OAException.ERROR);
                pageContext.putDialogMessage(message);
                return 1;
            }
        } else {
            checkResult(3, 3, pageContext);
        }
        return 1;
    }

    private void checkResult(int result1, int result2, 
                             OAPageContext pageContext) {
        if (result1 == 0 && result2 == 0) {
            OAException message = 
                new OAException("操作成功！", OAException.CONFIRMATION);
            pageContext.putDialogMessage(message);
            return;
        }
        if (result1 == 3 || result2 == 3) {
            OAException message = 
                new OAException("系统出现非常规错误，请联系管理员！", OAException.ERROR);
            pageContext.putDialogMessage(message);
            return;
        }
        if (result1 == 1) {
            OAException message = 
                new OAException("部门人员发放表中存在错误的人员信息，请检查修改后再保存", 
                                OAException.ERROR);
            pageContext.putDialogMessage(message);
        } else if (result1 == 2) {
            OAException message = 
                new OAException("部门人员发放表中每一项奖金必须大于0元，请检查修改后再保存", 
                                OAException.ERROR);
            pageContext.putDialogMessage(message);
        }

    }

    //  设置表的列只读（根据标识判读只读属性）

    public void SetTableColumnReadOnly(String ColStyle, String ColName, 
                                       String FlagName, String VoName, 
                                       OAWebBean webBean) {
        if (ColStyle.equals("Text")) {
            OAMessageTextInputBean obj = 
                (OAMessageTextInputBean)webBean.findChildRecursive(ColName);
            obj.setAttributeValue(oracle.cabo.ui.UIConstants.READ_ONLY_ATTR, 
                                  new OADataBoundValueViewObject(obj, FlagName, 
                                                                 VoName)); // 1, 只读, 0, 读写
        } else if (ColStyle.equals("Lov")) {
            OAMessageLovInputBean obj = 
                (OAMessageLovInputBean)webBean.findChildRecursive(ColName);
            obj.setAttributeValue(oracle.cabo.ui.UIConstants.READ_ONLY_ATTR, 
                                  new OADataBoundValueViewObject(obj, FlagName, 
                                                                 VoName));
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
