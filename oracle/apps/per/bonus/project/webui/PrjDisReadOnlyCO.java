/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.project.server.PrjBudgetItem;
import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 奖金发放，只读页面，显示项目奖金数据
 */
public class PrjDisReadOnlyCO extends OAControllerImpl {
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
            this.ShowMessage(pageContext, "页面超时！请重新进入本页面");
            return;
        }

        Integer iPrjID = 0;
        Integer iProjectManagerId = 0; //项目经理ID
        Integer iProjectSupervisorId = 0; //主管领导ID
        iPrjID = Integer.valueOf(strPrjID);
        if (!strPrjManagerId.equals(""))
            iProjectManagerId = Integer.valueOf(strPrjManagerId);
        if (!strPrjSupervisorId.equals(""))
            iProjectSupervisorId = Integer.valueOf(strPrjSupervisorId);

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        Integer iUserId = am.getLoginPersonId();

        if (!iUserId.equals(iProjectManagerId) && 
            !iUserId.equals(iProjectSupervisorId))
            return; //验证当前人员为项目经理或者主管领导

        OAMessageStyledTextBean PrjNumBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjNum");
        PrjNumBean.setValue(pageContext, strPrjNum);
        OAMessageStyledTextBean PrjNameBean = 
            (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjName");
        PrjNameBean.setValue(pageContext, strPrjName);
        this.SetBudgetInfo(iPrjID, pageContext, webBean);
        am.initQuery1(iPrjID);

        OAAdvancedTableBean outerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjLotTable");
        OAAdvancedTableBean innerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjDisTable");
        if (outerTable != null)
            outerTable.queryData(pageContext);
        if (innerTable != null)
            innerTable.prepareForRendering(pageContext);
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
            pageContext.removeSessionValue("BonusPrjId");
            pageContext.removeSessionValue("BonusPrjNum");
            pageContext.removeSessionValue("BonusPrjName");
            pageContext.removeSessionValue("BonusPrjManagerId"); //项目经理ID
            pageContext.removeSessionValue("BonusPrjSupervisorId"); //主管领导ID
            pageContext.removeSessionValue("BonusPrjStatusCode");

            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjQueryPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }
    }

    /* 设置预算信息 */

    private void SetBudgetInfo(Integer iPrjID, OAPageContext pageContext, 
                               OAWebBean webBean) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        PrjBudgetItem item = am.getBudgetInfo(iPrjID);

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
