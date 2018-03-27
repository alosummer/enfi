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
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsOtherLovVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsOtherPerVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsOtherPerVORowImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;

import cux.oracle.apps.per.bonus.advanceawards.server.BonusItem;

import cux.oracle.apps.per.bonus.advanceawards.server.CuxBonusOfficeDistributionVOImpl;

import cux.oracle.apps.per.bonus.advanceawards.server.CuxBonusOfficePersonDistVOImpl;


import java.sql.CallableStatement;
import java.sql.Types;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Hashtable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
//import oracle.apps.hxc.selfservice.common.util.GlobalUtilities;
import oracle.jbo.Row;

import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;

import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;


/**
 * 部门人员，奖金发放
 */
public class AdvanceAwardsOfficeCO extends OAControllerImpl {
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
        if (!pageContext.isBackNavigationFired(false)) {
            if (!pageContext.isFormSubmission()) {
                this.initWeb(pageContext, webBean);
            }
        }
        this.SetTableColAttr(webBean);
    }

    /* 初始化页面数据 */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        OAMessageChoiceBean dateBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
        OAMessageChoiceBean bonusBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("BonusType");
        OAMessageChoiceBean otherBonusBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("OtherBonusType");
        OAMessageChoiceBean officeBonusTypeBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("BonusType1");
        String strdeptOrgID = GetSessionValue(pageContext, "distDeptOrgID");
        String strDeptName = GetSessionValue(pageContext, "distDeptName");
        if ("".equals(strdeptOrgID)) {
            bonusBean.setReadOnly(true);
            otherBonusBean.setReadOnly(true);
            dateBean.setReadOnly(true);
            ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }
        int deptOrgID = Integer.parseInt(strdeptOrgID);

        String strDate = GetSessionValue(pageContext, "distDate");
        if (strDate.equals("")) {
            Date today = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            strDate = df.format(today);
            pageContext.putSessionValue("distDate", strDate);
        }
        dateBean.setDefaultValue(strDate);

        String strBonusType = GetSessionValue(pageContext, "bonusType");
        if (strBonusType.equals("")) {
            strBonusType = "绩效奖金";
            pageContext.putSessionValue("bonusType", strBonusType);
        }
        bonusBean.setDefaultValue(strBonusType);


        pageSearchData(pageContext, webBean, deptOrgID, strDeptName, strDate, 
                       strBonusType);


    }

    /* 设置表格列只读属性 */

    private void SetTableColAttr(OAWebBean webBean) {
        //部门其他人员，只读设置，已批准的记录不能再进行编辑, edit by yang.gang, 2015-5-18
        //        SetTableColumnReadOnly("Text", "DeptPersonAwards", "ReadOnlyFlag", 
        //                               "AdvAwardsDeptPersonVO1", webBean);
        //        SetTableColumnReadOnly("Text", "DeptPersonNote", "ReadOnlyFlag", 
        //                               "AdvAwardsDeptPersonVO1", webBean);
        //        SetTableColumnReadOnly("Text", "OtherPerAmount", "ReadOnlyFlag", 
        //                               "AdvAwardsOtherPerVO1", webBean);
        //        SetTableColumnReadOnly("Text", "OtherPerNote", "ReadOnlyFlag", 
        //                               "AdvAwardsOtherPerVO1", webBean);
        //        SetTableColumnReadOnly("Lov", "OtherPerNumber", "ReadOnlyFlag", 
        //                               "AdvAwardsOtherPerVO1", webBean);
        //        SetTableColumnReadOnly("Lov", "OtherPerName", "ReadOnlyFlag", 
        //                               "AdvAwardsOtherPerVO1", webBean);
        //        SetTableColumnReadOnly("Lov", "OtherPerDept", "ReadOnlyFlag", 
        //                               "AdvAwardsOtherPerVO1", webBean);
    }


    //查询页面需要的数据，包括当前部门、可发放余额等信息

    public void pageSearchData(OAPageContext pageContext, OAWebBean webBean, 
                               int deptOrgID, String strDeptName, 
                               String bonusDistDate, String bonusType) {
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);

        CuxBonusOfficePersonDistVOImpl deptPersonVO = 
            (CuxBonusOfficePersonDistVOImpl)am.findViewObject("CuxBonusOfficePersonDistVO1");
        deptPersonVO.initQuery(bonusType, bonusDistDate, deptOrgID);
        deptPersonVO.executeQuery();
        if (deptPersonVO.getRowCount() == 0) {
            CallableStatement cs = null;
            OADBTransaction transaction = amImpl.getOADBTransaction();
            cs = 
 transaction.createCallableStatement("begin CUX_HR_BONUS.init_office_per_data(?, ?, ?);end;", 
                                     1);
            try {
                cs.setString(1, bonusType);
                cs.setString(2, bonusDistDate);
                cs.setInt(3, deptOrgID);
                cs.executeUpdate();
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        deptPersonVO.executeQuery();

        double totalBalance = 
            amImpl.getOfficeDistInfo(deptOrgID, bonusDistDate, bonusType);

        OAMessageTextInputBean AvailAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("AvailAmount");
        AvailAmountBean.setValue(pageContext, totalBalance);
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
        String source = pageContext.getParameter("source");
        String event = pageContext.getParameter("event");
        AdvanceAwardsAMImpl am = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        String strdeptName = this.GetSessionValue(pageContext, "distDeptName");
        String strdeptOrgID = 
            this.GetSessionValue(pageContext, "distDeptOrgID");
        if (strdeptOrgID.equals("")) {
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }
        int deptOrgID = Integer.parseInt(strdeptOrgID);
        String bonusType = this.GetSessionValue(pageContext, "bonusType");
        String distDate = this.GetSessionValue(pageContext, "distDate");

        //其他人员，添加一行    
        if (source.equals("OtherPerDistTable") && "addRows".equals(event))
            this.createAnotherOtherPerRow(pageContext, webBean, null);
        else if ("deptPerSearch".equals(event)) //本部门人员，奖金类型
            this.OndeptPerSearchClick(pageContext, webBean, distDate, 
                                      deptOrgID);
        else if ("search".equals(event)) { //月份发生变化
            OAMessageChoiceBean distDateBean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
            distDate = distDateBean.getSelectionValue(pageContext);
            pageContext.putSessionValue("distDate", distDate);
            pageSearchData(pageContext, webBean, deptOrgID, strdeptName, 
                           distDate, bonusType);
        } else if (pageContext.getParameter("SaveButton") != null) {
            int result = 
                saveDistData(pageContext, webBean, distDate, bonusType, 
                             deptOrgID);
            if (result == 0) {
                pageSearchData(pageContext, webBean, deptOrgID, strdeptName, 
                               distDate, bonusType);
                this.ShowMessage(pageContext, "数据保存成功！");
            }
        } else if (pageContext.getParameter("ReturnButton") != null)
            this.OnReturnButtonClick(pageContext);
        else if (pageContext.getParameter("SubmitButton") != 
                 null) { // 提交数据    
            this.submitDistData(pageContext, webBean, distDate, bonusType, 
                                deptOrgID);
        }
    }

    /* 提交数据 */

    private void submitDistData(OAPageContext pageContext, OAWebBean webBean, 
                                String distDate, String bonusType, 
                                int deptOrgID) {
        AdvanceAwardsAMImpl am = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        int result = 
            saveDistData(pageContext, webBean, distDate, bonusType, deptOrgID);
        if (result != 0) {
            this.ShowMessage(pageContext, "保存数据出错！");
            return;
        }

        // 更新VO，在提交页面事件触发时会读取vo的内容
        am.submitOfficeData(deptOrgID, distDate, bonusType);


        String strURL = 
            "OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/OfficeQueryPG";
        pageContext.putDialogMessage(new OAException("提交预分配金额成功", 
                                                     OAException.INFORMATION));
        pageContext.forwardImmediately(strURL, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, 
                                       OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
    }

    private int saveDistData(OAPageContext pageContext, OAWebBean webBean, 
                             String bonusDistDate, String bonusTypeName, 
                             int deptOrgID) {
        OAMessageTextInputBean AvailAmountBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("AvailAmount");
        double availAmount = 
            Double.parseDouble(AvailAmountBean.getValue(pageContext).toString());

        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        int officeResultCheck = amImpl.checkOfficePerData(availAmount);
        if (officeResultCheck == 1) {
            OAException message = 
                new OAException("科室预分配金额超过了部长给定的限额，请检查！", OAException.ERROR);
            pageContext.putDialogMessage(message);
            return 1;
        } else {
            amImpl.getOADBTransaction().commit();
            return 0;
        }
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
        if (result2 == 1) {
            OAException message = 
                new OAException("其他人员发放表中存在错误的人员信息，请检查修改后再保存", 
                                OAException.ERROR);
            pageContext.putDialogMessage(message);
        } else if (result2 == 2) {
            OAException message = 
                new OAException("其他人员发放表中每一项奖金必须大于0元，请检查修改后再保存", 
                                OAException.ERROR);
            pageContext.putDialogMessage(message);
        }

    }

    /* 其他人员表格，添加一行 */

    public void createAnotherOtherPerRow(OAPageContext pageContext, 
                                         OAWebBean webBean, Row row) {
        OAApplicationModuleImpl am = 
            (OAApplicationModuleImpl)pageContext.getApplicationModule(webBean);
        OAViewObject vo = 
            (OAViewObject)am.findViewObject("AdvAwardsOtherPerVO1");
        if (vo != null) {
            Row row1 = vo.createRow();
            int index = vo.getRangeIndexOf(vo.last());
            vo.insertRowAtRangeIndex(index + 1, row1);
            row1.setAttribute("Attribute1", "N");
            row1.setAttribute("OtherDelete", "otherDelEnabled");
            row1.setAttribute("RowSeq", index + 2);
            row = row1;
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


    /* 本部门人员，奖金类型 */

    private void OndeptPerSearchClick(OAPageContext pageContext, 
                                      OAWebBean webBean, String distDate, 
                                      int deptOrgID) {
        AdvanceAwardsAMImpl am = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        OAMessageChoiceBean bonusTypeBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("BonusType");
        String bonusType = bonusTypeBean.getSelectionValue(pageContext);
        pageContext.putSessionValue("bonusType", bonusType);

        CuxBonusOfficePersonDistVOImpl deptPersonVO = 
            (CuxBonusOfficePersonDistVOImpl)am.findViewObject("CuxBonusOfficePersonDistVO1");
        deptPersonVO.initQuery(bonusType, distDate, deptOrgID);
        deptPersonVO.executeQuery();

        if (deptPersonVO.getRowCount() == 0) {
            CallableStatement cs = null;
            OADBTransaction transaction = am.getOADBTransaction();
            cs = 
 transaction.createCallableStatement("begin CUX_HR_BONUS.init_office_per_data(?, ?, ?);end;", 
                                     1);
            try {
                cs.setString(1, bonusType);
                cs.setString(2, distDate);
                cs.setInt(3, deptOrgID);
                cs.executeUpdate();
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        deptPersonVO.executeQuery();


        OAAdvancedTableBean deptPersonTable = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("DeptPersonTable");
        deptPersonTable.queryData(pageContext);
    }


    /* 返回按钮 */

    private void OnReturnButtonClick(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/advanceawards/webui/OfficeQueryPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 其他人员表格，删除行事件 */

    private void OnotherPerDeleteClick(OAPageContext pageContext, 
                                       OAWebBean webBean) {
        String deletePerName = pageContext.getParameter("otherPerDeleteName");
        String deletePerId = pageContext.getParameter("otherPerDeleteId");
        String deleteEmployeeNumber = 
            pageContext.getParameter("otherPerDeleteEmplyeeNum");

        if ("".equals(deleteEmployeeNumber) && 
            "".equals(deletePerId)) { //如果工号和ID都为空，证明用户未操作该列，则直接删除
            this.OnotherPerDeleteConfirm(pageContext, webBean);
            return;
        }

        String tips = "提示信息: 是否确定删除 " + deletePerName + " 该条记录";
        OAException mainMessage = new OAException(tips, OAException.WARNING);

        OADialogPage dialogPage = 
            new OADialogPage(OAException.WARNING, mainMessage, null, "", "");

        dialogPage.setOkButtonItemName("otherPerDeleteYesButton");
        dialogPage.setOkButtonToPost(true);
        dialogPage.setNoButtonToPost(true);
        dialogPage.setPostToCallingPage(true);
        Hashtable formParams = new Hashtable(1);
        formParams.put("otherPerDeleteEmplyeeNum", deleteEmployeeNumber);
        formParams.put("otherPerDeleteId", deletePerId);
        dialogPage.setFormParameters(formParams);
        pageContext.redirectToDialogPage(dialogPage);
    }

    /* 确认删除一条其他人员记录 */

    private void OnotherPerDeleteConfirm(OAPageContext pageContext, 
                                         OAWebBean webBean) {
        String deleteEmpNum = 
            pageContext.getParameter("otherPerDeleteEmplyeeNum");
        String deletePersonId = pageContext.getParameter("otherPerDeleteId");

        if (!"".equals(deletePersonId)) {
            AdvanceAwardsAMImpl amImpl = 
                (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
            amImpl.deleteOtherPerData(deletePersonId, deleteEmpNum);
        } else {
            this.otherPerDeleteVO(pageContext, webBean, deleteEmpNum);
        }

        this.ShowMessage(pageContext, "删除记录成功！");
    }

    /* 删除VO里面一条记录 */

    private void otherPerDeleteVO(OAPageContext pageContext, OAWebBean webBean, 
                                  String selectedEmpNum) {
        AdvanceAwardsAMImpl am = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        AdvAwardsOtherPerVOImpl vo = am.getAdvAwardsOtherPerVO1();
        AdvAwardsOtherPerVORowImpl row = null;
        int fetchedRowCount = vo.getFetchedRowCount();
        if (fetchedRowCount == 0)
            return;
        RowSetIterator deleteIter = vo.createRowSetIterator("deleteIter");
        deleteIter.setRangeStart(0);
        deleteIter.setRangeSize(fetchedRowCount);
        for (int i = 0; i < fetchedRowCount; ++i) {
            row = (AdvAwardsOtherPerVORowImpl)deleteIter.getRowAtRangeIndex(i);
            String deleteEmpNum = "";
            if (row.getEmployeeNumber() != null) {
                deleteEmpNum = row.getEmployeeNumber();
            }
            if (deleteEmpNum.equals(selectedEmpNum)) {
                row.remove();
                break;
            }
        }
    }

}
