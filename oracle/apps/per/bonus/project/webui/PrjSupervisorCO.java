/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.project.server.PrjBudgetItem;
import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import java.util.ArrayList;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.cabo.ui.validate.DecimalValidater;

import oracle.jbo.Row;

/**
 * 项目管理奖
 * 项目主管领导发放页面
 * 
 */
public class PrjSupervisorCO extends OAControllerImpl {
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
        String strPrjID = this.GetSessionValue(pageContext, "BonusPrjId");
        String strPrjNum = this.GetSessionValue(pageContext, "BonusPrjNum");
        String strPrjName = this.GetSessionValue(pageContext, "BonusPrjName");
        String strPrjManagerId = 
            this.GetSessionValue(pageContext, "BonusPrjManagerId");
        String strPrjSupervisorId = 
            this.GetSessionValue(pageContext, "BonusPrjSupervisorId");

        if (strPrjID.equals("")) {
            this.SetObjRendered(webBean, "PrjLotTable", false);
            this.SetObjRendered(webBean, "PrjDisTable", false);
            this.SetObjRendered(webBean, "PrjDistributionTable", false);
            OASubmitButtonBean saveBtn = 
                (OASubmitButtonBean)webBean.findChildRecursive("SaveButton");
            saveBtn.setAttributeValue(RENDERED_ATTR, false);
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }

        Integer iPrjID = 0;
        Integer iProjectManagerId = 0; //项目经理ID
        Integer iProjectSupervisorId = 0; //主管领导ID
        iPrjID = Integer.valueOf(strPrjID);
        if (!"".equals(strPrjManagerId))
            iProjectManagerId = Integer.valueOf(strPrjManagerId);
        if (!"".equals(strPrjSupervisorId))
            iProjectSupervisorId = Integer.valueOf(strPrjSupervisorId);

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        Integer iUserId = am.getLoginPersonId();

        if (!iUserId.equals(iProjectSupervisorId))
            return; //验证当前人员为项目主管领导
        boolean bIsForbid = am.IsPrjForbid(iPrjID);
        if (bIsForbid) {
            this.GotoReadOnlyPG(pageContext);
            return;
        }

        int iRtn = this.SetBudgetInfo(iPrjID, pageContext, webBean);
        if (iRtn == 0) {
            this.GotoReadOnlyPG(pageContext);
            return;
        }

        OAMessageStyledTextBean PrjNumBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjNum");
        PrjNumBean.setValue(pageContext, strPrjNum);
        OAMessageStyledTextBean PrjNameBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjName");
        PrjNameBean.setValue(pageContext, strPrjName);
        this.RefreshDistData(pageContext, webBean, iPrjID);

        this.InitObjAttr(webBean);
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

        String strPrjID = this.GetSessionValue(pageContext, "BonusPrjId");
        if ("".equals(strPrjID)) {
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }

        Integer iPrjID = Integer.valueOf(strPrjID);
        if (pageContext.getParameter("ReturnButton") != null)
            this.GoToPrjQueryPG(pageContext);
        else if (pageContext.getParameter("Export") != null)
            this.GoToExportPG(pageContext);
        else if ("PrjDistributionTable".equals(source) && 
                 "addRows".equals(event))
            createDisPersonRow(pageContext, webBean, iPrjID);
        else if (pageContext.getParameter("SaveButton") != null)
            this.SaveDistData(pageContext, webBean, iPrjID);
        else if ("DisDelete".equals(event)) //删除行        
            this.DeleteDistribution(pageContext, webBean, iPrjID);
        else if (pageContext.getParameter("SubmitButton") != null)
            this.SubmitDistData(pageContext, webBean, iPrjID);
    }

    /* 初始化空间属性 */

    private void InitObjAttr(OAWebBean webBean) {
        // 发放金额验证，不能小于0
        DecimalValidater formatter = new DecimalValidater();
        formatter.setMinValue(0);
        (webBean.findChildRecursive("CDistributionAmount")).setAttributeValue(ON_SUBMIT_VALIDATER_ATTR, 
                                                                              formatter);

        webBean.findChildRecursive("Export").setViewUsageName("PrjDisExportVO1");

        SetTableColumnReadOnly("Text", "CDistributionAmount", "ReadOnlyFlag", 
                               "PrjDistributionVO1", webBean);
        SetTableColumnReadOnly("Text", "CNote", "ReadOnlyFlag", 
                               "PrjDistributionVO1", webBean);
    }

    /* 设置预算信息
     * return 0, 可发放预算为0
     * */

    private int SetBudgetInfo(Integer iPrjID, OAPageContext pageContext, 
                              OAWebBean webBean) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        PrjBudgetItem item = am.getBudgetInfo(iPrjID);

        if (item.IssueTotal == 0.0)
            return 0;

        OAMessageStyledTextBean PrjBudgetBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjBudget");
        PrjBudgetBean.setValue(pageContext, String.valueOf(item.Budget));
        OAMessageStyledTextBean PrjIssueBudgetBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjIssueBudget");
        PrjIssueBudgetBean.setValue(pageContext, 
                                    String.valueOf(item.IssueBudget));
        OAMessageStyledTextBean PrjReleaseTotalBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjReleaseTotal");
        PrjReleaseTotalBean.setValue(pageContext, 
                                     String.valueOf(item.ReleaseTotal));
        OAMessageStyledTextBean PrjIssueTotalBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjIssueTotal");
        PrjIssueTotalBean.setValue(pageContext, 
                                   String.valueOf(item.IssueTotal));
        return 1;
    }

    /* 刷新页面数据显示 */

    private void RefreshDistData(OAPageContext pageContext, OAWebBean webBean, 
                                 int iPrjID) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        am.initQuery(iPrjID);

        OAAdvancedTableBean outerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjLotTable");
        OAAdvancedTableBean innerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjDisTable");
        OAAdvancedTableBean currentTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjDistributionTable");
        OATableBean exportTable = 
            (OATableBean)webBean.findChildRecursive("PrjDisExportTable");
        if (outerTable != null)
            outerTable.queryData(pageContext);
        if (innerTable != null)
            innerTable.prepareForRendering(pageContext);
        if (currentTable != null)
            currentTable.queryData(pageContext);
        if (exportTable != null)
            exportTable.queryData(pageContext);
    }

    private void createDisPersonRow(OAPageContext pageContext, 
                                    OAWebBean webBean, int iPrjID) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        OAViewObject vo = 
            (OAViewObject)am.findViewObject("PrjDistributionVO1");
        if (vo == null)
            return;
        Row row = vo.createRow();
        int index = vo.getRangeIndexOf(vo.last());
        vo.insertRowAtRangeIndex(index + 1, row);
        row.setAttribute("OtherDelete", "DelEnabled");
        ArrayList list = am.getManagerInfo(iPrjID);
        if (list.size() != 5)
            return;
        row.setAttribute("PersonId", Integer.valueOf(list.get(0).toString()));
        row.setAttribute("EmpNo", list.get(1).toString());
        row.setAttribute("EmpName", list.get(2).toString());
        row.setAttribute("OrgId", Integer.valueOf(list.get(3).toString()));
        row.setAttribute("OrgName", list.get(4).toString());
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

    private void SetObjRendered(OAWebBean webBean, String objName, 
                                boolean bvalue) {
        OAWebBean obj = webBean.findIndexedChildRecursive(objName);
        if (obj == null)
            return;
        obj.setRendered(bvalue);
    }

    private void GotoReadOnlyPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDisReadOnlyPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 返回项目查询页面 */

    private void GoToPrjQueryPG(OAPageContext pageContext) {
        pageContext.removeSessionValue("BonusPrjId");
        pageContext.removeSessionValue("BonusPrjNum");
        pageContext.removeSessionValue("BonusPrjName");
        pageContext.removeSessionValue("BonusPrjManagerId"); //项目经理ID
        pageContext.removeSessionValue("BonusPrjSupervisorId"); //主管领导ID
        pageContext.removeSessionValue("BonusPrjStatusCode");

        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjQueryPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 项目导出页面 */

    private void GoToExportPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDisExportPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 保存分配数据 */

    private void SaveDistData(OAPageContext pageContext, OAWebBean webBean, 
                              int iPrjID) {
        ProjectAMImpl amImpl = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        int iCheck = amImpl.checkDistData(iPrjID); // 数据合法性验证
        if (iCheck != 0) {
            String strMessage = amImpl.getDistWarningMessage(iCheck);
            this.ShowMessage(pageContext, strMessage);
            return;
        }
        String strResult = amImpl.saveDeptPersonDist(iPrjID);
        ;
        if (!"success".equals(strResult)) {
            this.ShowMessage(pageContext, "数据保存错误，错误信息：".concat(strResult));
        } else {
            this.ShowMessage(pageContext, "数据保存成功！");
            this.RefreshDistData(pageContext, webBean, iPrjID);
        }
    }

    /* 删除一条记录 */

    private void DeleteDistribution(OAPageContext pageContext, 
                                    OAWebBean webBean, int iPrjID) {
        String strPrjDisID = 
            this.GetParameterValue(pageContext, "DeletePrjDisId");
        String strEmpNo = this.GetParameterValue(pageContext, "DelEmpNo");
        String strOrgName = this.GetParameterValue(pageContext, "DelOrgName");
        String strDisAmount = 
            this.GetParameterValue(pageContext, "DelDisAmount");
        ProjectAMImpl amImpl = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        String strResult = 
            amImpl.DeleteDistribution(strPrjDisID, strEmpNo, strOrgName, 
                                      strDisAmount);
        if (!"success".equals(strResult)) {
            this.ShowMessage(pageContext, "数据删除错误，错误信息：".concat(strResult));
        } else {
            this.ShowMessage(pageContext, "数据删除成功！");
            this.RefreshDistData(pageContext, webBean, iPrjID);
        }
    }

    /* 提交分配数据 */

    private void SubmitDistData(OAPageContext pageContext, OAWebBean webBean, 
                                int iPrjID) {
        ProjectAMImpl amImpl = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        int iCheck = amImpl.checkDistData(iPrjID); // 数据合法性验证
        if (iCheck != 0) {
            String strMessage = amImpl.getDistWarningMessage(iCheck);
            this.ShowMessage(pageContext, strMessage);
            return;
        }
        String strResult = amImpl.saveDeptPersonDist(iPrjID);
        ;
        if (!"success".equals(strResult)) {
            this.ShowMessage(pageContext, "数据保存错误，错误信息：".concat(strResult));
            return;
        }

        this.RefreshDistData(pageContext, webBean, iPrjID);
        strResult = amImpl.SubmitDistData(iPrjID);
        ;
        if (!"success".equals(strResult)) {
            this.ShowMessage(pageContext, "数据提交错误，错误信息：".concat(strResult));
            return;
        }

        this.ShowMessage(pageContext, "数据提交成功！");
        this.SetBudgetInfo(iPrjID, pageContext, webBean);
        this.RefreshDistData(pageContext, webBean, iPrjID);
    }

    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
    }

    //  设置表的列只读（根据标识判读只读属性）

    private void SetTableColumnReadOnly(String ColStyle, String ColName, 
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

}
