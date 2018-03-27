/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

import oracle.cabo.ui.validate.DecimalValidater;

import oracle.jbo.Row;

/**
 * 采购、财务部门项目管理奖发放
 * added by yang.gang,2015-7-29
 */
public class PrjDeptDistDetailCO extends OAControllerImpl {
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

        this.InitPrjInfo(pageContext, webBean);
        this.InitObjAttr(webBean);
        this.RefreshDistData(pageContext, webBean);
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

        String strProjectDistributionId = 
            this.GetSessionValue(pageContext, "ProjectDistributionId");
        if ("".equals(strProjectDistributionId)) {
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }
        int ProjectDistributionId = Integer.valueOf(strProjectDistributionId);

        if (pageContext.getParameter("ReturnButton") != null)
            this.OnReturnButtonClick(pageContext);
        else if ("PrjDistributionTable".equals(source) && 
                 "addRows".equals(event))
            this.OnAddRowButtonClick(pageContext, webBean);
        else if (pageContext.getParameter("SaveButton") != null)
            this.OnSaveButtonClick(pageContext, webBean, 
                                   ProjectDistributionId);
        else if ("DisDelete".equals(event)) //删除行        
            this.OnDelButtionClick(pageContext, webBean);
        else if (pageContext.getParameter("SubmitButton") != null)
            this.OnSubmitButtonClick(pageContext, webBean, 
                                     ProjectDistributionId);
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

    /* 初始化页面显示的项目信息 */

    private void InitPrjInfo(OAPageContext pageContext, OAWebBean webBean) {
        String strPrjNum = this.GetSessionValue(pageContext, "ProjectNum");
        String strPrjName = this.GetSessionValue(pageContext, "ProjectName");
        String strApprovedDate = 
            this.GetSessionValue(pageContext, "ApprovedDate");
        String strDistributionAmount = 
            this.GetSessionValue(pageContext, "DistributionAmount");
        String strDistAmount = this.GetSessionValue(pageContext, "DistAmount");
        String strOrgName = this.GetSessionValue(pageContext, "OrgName");

        OAMessageStyledTextBean PrjNumBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjNum");
        PrjNumBean.setValue(pageContext, strPrjNum);
        OAMessageStyledTextBean PrjNameBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjName");
        PrjNameBean.setValue(pageContext, strPrjName);
        OAMessageStyledTextBean ApprovedDateBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjApprovedDate");
        ApprovedDateBean.setValue(pageContext, strApprovedDate);
        //可发放总额
        OAMessageStyledTextBean DistributionAmountBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjDistributionAmount");
        DistributionAmountBean.setValue(pageContext, strDistributionAmount);
        //已发放总额
        OAMessageStyledTextBean DistAmountBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjDistAmount");
        DistAmountBean.setValue(pageContext, strDistAmount);
        //分配部门
        OAMessageStyledTextBean OrgNametBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjOrgName");
        OrgNametBean.setValue(pageContext, strOrgName);
    }


    /* 初始化空间属性 */

    private void InitObjAttr(OAWebBean webBean) {
        // 发放金额验证，不能小于0
        DecimalValidater formatter = new DecimalValidater();
        formatter.setMinValue(0);
        (webBean.findChildRecursive("DistAmount")).setAttributeValue(ON_SUBMIT_VALIDATER_ATTR, 
                                                                     formatter);

        SetTableColumnReadOnly("Text", "DistAmount", "ReadOnlyFlag", 
                               "DeptDistributionVO1", webBean);
        SetTableColumnReadOnly("Lov", "EmpNo", "ReadOnlyFlag", 
                               "DeptDistributionVO1", webBean);
        SetTableColumnReadOnly("Lov", "EmpName", "ReadOnlyFlag", 
                               "DeptDistributionVO1", webBean);
        SetTableColumnReadOnly("Lov", "OrgName", "ReadOnlyFlag", 
                               "DeptDistributionVO1", webBean);
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


    /* 刷新页面数据显示 */

    private void RefreshDistData(OAPageContext pageContext, 
                                 OAWebBean webBean) {
        String strProjectDistributionId = 
            this.GetSessionValue(pageContext, "ProjectDistributionId");
        if ("".equals(strProjectDistributionId))
            return;
        int ProjectDistributionId = Integer.valueOf(strProjectDistributionId);

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        am.initDeptPrjDistDetailQuery(ProjectDistributionId);

        OAAdvancedTableBean currentTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjDistributionTable");
        currentTable.queryData(pageContext);
    }

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

    /* 返回采购、财务部长 项目管理奖查询页面 */

    private void OnReturnButtonClick(OAPageContext pageContext) {
        pageContext.removeSessionValue("ProjectDistributionId");
        pageContext.removeSessionValue("ProjectNum");
        pageContext.removeSessionValue("ProjectName");
        pageContext.removeSessionValue("ApprovedDate");
        pageContext.removeSessionValue("DistributionAmount");
        pageContext.removeSessionValue("DistAmount");
        pageContext.removeSessionValue("OrgName");

        HashMap params = new HashMap(1); //创建HashMap对象
        params.put("backflag", "y"); //给参数赋值
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDeptDistPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, params, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 添加一行发放数据 */

    private void OnAddRowButtonClick(OAPageContext pageContext, 
                                     OAWebBean webBean) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        OAViewObject vo = 
            (OAViewObject)am.findViewObject("DeptDistributionVO1");
        if (vo == null)
            return;
        Row row = vo.createRow();
        int index = vo.getRangeIndexOf(vo.last());
        vo.insertRowAtRangeIndex(index + 1, row);
        row.setAttribute("OtherDelete", "DelEnabled");
    }

    /* 保存分配数据 */

    private void OnSaveButtonClick(OAPageContext pageContext, 
                                   OAWebBean webBean, int iPrjDistId) {
        //可发放总额
        String strDistributionAmount = 
            this.GetSessionValue(pageContext, "DistributionAmount");
        double TotalAmount = 0;
        if (!"".equals(strDistributionAmount))
            TotalAmount = Double.parseDouble(strDistributionAmount);
        ProjectAMImpl amImpl = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        int iCheck = 
            amImpl.checkDeptPrjDistDetailData(iPrjDistId, TotalAmount); // 数据合法性验证
        if (iCheck != 0) {
            String strMessage = amImpl.getDistWarningMessage(iCheck);
            this.ShowMessage(pageContext, strMessage);
            return;
        }

        String strResult = amImpl.saveDeptPrjDistDetailData(iPrjDistId);
        ;
        if (!"success".equals(strResult)) {
            this.ShowMessage(pageContext, "数据保存错误，错误信息：".concat(strResult));
        } else {
            this.ShowMessage(pageContext, "数据保存成功！");
            this.RefreshDistData(pageContext, webBean);
        }
    }

    /* 删除一条分配记录 */

    private void OnDelButtionClick(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        String strPerDisID = 
            this.GetParameterValue(pageContext, "DelPerDistId");
        String strPersonId = 
            this.GetParameterValue(pageContext, "DelPersonId");
        String strOrgId = this.GetParameterValue(pageContext, "DelOrgId");
        String strDistAmount = 
            this.GetParameterValue(pageContext, "DelDistAmount");

        ProjectAMImpl amImpl = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        String strResult = 
            amImpl.DeleteDeptPrjDistData(strPerDisID, strPersonId, strOrgId, 
                                         strDistAmount);
        if (!"success".equals(strResult)) {
            this.ShowMessage(pageContext, "数据删除错误，错误信息：".concat(strResult));
        } else {
            this.ShowMessage(pageContext, "数据删除成功！");
            this.RefreshDistData(pageContext, webBean);
        }
    }

    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
    }

    /* 提交分配数据 */

    private void OnSubmitButtonClick(OAPageContext pageContext, 
                                     OAWebBean webBean, int iPrjDistId) {
        //可发放总额
        String strDistributionAmount = 
            this.GetSessionValue(pageContext, "DistributionAmount");
        double TotalAmount = 0;
        if (!"".equals(strDistributionAmount))
            TotalAmount = Double.parseDouble(strDistributionAmount);
        ProjectAMImpl amImpl = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        int iCheck = 
            amImpl.checkDeptPrjDistDetailData(iPrjDistId, TotalAmount); // 数据合法性验证
        if (iCheck != 0) {
            String strMessage = amImpl.getDistWarningMessage(iCheck);
            this.ShowMessage(pageContext, strMessage);
            return;
        }

        String strResult = amImpl.saveDeptPrjDistDetailData(iPrjDistId);
        ;
        if (!"success".equals(strResult)) {
            this.ShowMessage(pageContext, "数据提交错误，错误信息：".concat(strResult));
            return;
        }
        ;
        this.RefreshDistData(pageContext, webBean);

        strResult = amImpl.SubmitDeptPrjDistData();
        if (!"success".equals(strResult)) {
            this.ShowMessage(pageContext, "数据提交错误，错误信息：".concat(strResult));
            return;
        }

        this.ShowMessage(pageContext, "数据提交成功！");

        double DistAmount = amImpl.GetPrjDeptDistAmount(iPrjDistId); //已发放总额
        if (DistAmount >= TotalAmount) { //跳转到只读页面
            pageContext.putSessionValue("DistAmount", 
                                        String.valueOf(DistAmount));
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDeptDistDetlReadOnlyPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        } else {
            this.RefreshDistData(pageContext, webBean);
            OAMessageStyledTextBean DistAmountBean = 
                (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjDistAmount");
            DistAmountBean.setValue(pageContext, String.valueOf(DistAmount));
        }
    }

}
