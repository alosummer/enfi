/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.pay.webui;

import cux.oracle.apps.per.bonus.pay.server.PayAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 指定工资单页面
 * created by yang.gang,2016-3-16
 */
public class AssignPayrollCO extends OAControllerImpl {
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
        this.InitWebInfo(pageContext, webBean);
    }

    /* 初始化页面显示 */

    private void InitWebInfo(OAPageContext pageContext, OAWebBean webBean) {
        String strEmpName = this.GetSessionValue(pageContext, "EmpName");
        String strDistID = this.GetSessionValue(pageContext, "PerDistID");
        Integer iDistID = Integer.valueOf(strDistID);

        OAHeaderBean header = 
            (OAHeaderBean)webBean.findIndexedChildRecursive("SummaryTableHdr");
        header.setText(strEmpName + "工资单列表");

        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);
        am.initPayrollQuery(iDistID);

        OAAdvancedTableBean table = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PayrollTable");
        table.queryData(pageContext, false);
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
        if (pageContext.getParameter("SaveButton") != null) // 提交按钮
            this.SavePayroll(pageContext, webBean);
        else if (pageContext.getParameter("BackButton") != null) {
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/pay/webui/PayImportPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
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

    /* 保存 */

    private void SavePayroll(OAPageContext pageContext, OAWebBean webBean) {
        PayAMImpl am = (PayAMImpl)pageContext.getApplicationModule(webBean);
        int select_count = am.GetPayrollVOSelectRowCount();
        if (select_count == 0) {
            this.ShowMessage(pageContext, "请选择指定的工资单！");
            return;
        }
        String strRtn = am.SavePayrollSelected();
        if ("S".equals(strRtn)) {
            this.ShowMessage(pageContext, "数据保存成功！");
            pageContext.putSessionValue("PAYROLL_CHANGE_FLAG", 
                                        "Y"); //在返回时判断工资单是否已经发生了变化
        } else {
            this.ShowMessage(pageContext, "数据保存失败！");
        }

    }

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

}
