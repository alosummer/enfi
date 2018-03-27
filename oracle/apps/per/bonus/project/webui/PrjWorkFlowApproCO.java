/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.project.server.PrjBudgetItem;
import cux.oracle.apps.per.bonus.project.server.PrjDistributionVOImpl;
import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import java.util.ArrayList;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 项目主管领导，股份董事长审批项目管理奖时，点击“项目管理奖发放详细信息”弹出的页面
 * 避免发放人数过多时，存储过程里面拼凑HTML页面时，长度超长报错
 * created by yang.gang,2015.8.31
 */
public class PrjWorkFlowApproCO extends OAControllerImpl {
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
        this.initWeb(pageContext, webBean);
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
    }

    /* 初始化页面
     * 点击工作量通知按钮会带prjguid参数
     * */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        String guid = pageContext.getParameter("prjguid");
        if (guid == null || "".equals(guid))
            return;

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        int iPrjID = am.getPrjIDFromGUID(guid); //项目ID
        if (iPrjID == 0)
            return;

        int iUserId = am.getLoginPersonId(); // 当前登陆人员
        int iChairMan = am.getChairmanPersonId(); //赛迪股份董事长Persion ID
        int iSupervisorId = 0; //项目主管领导

        ArrayList list = am.GetPrjManagerUserID(iPrjID);
        if (list.size() == 2) {
            iSupervisorId = Integer.valueOf(list.get(1).toString());
        }

        if (iUserId != iChairMan && iUserId != iSupervisorId)
            return; //安全性认证，只有项目主管领导和股份董事长能看到该页面

        ArrayList list1 = am.GetPrjNameNum(iPrjID);
        if (list1.size() == 2) {
            OAMessageStyledTextBean PrjNumBean = 
                (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjNum");
            PrjNumBean.setValue(pageContext, list1.get(0).toString());
            OAMessageStyledTextBean PrjNameBean = 
                (OAMessageStyledTextBean)webBean.findIndexedChildRecursive("PrjName");
            PrjNameBean.setValue(pageContext, list1.get(1).toString());
        }
        this.SetBudgetInfo(iPrjID, pageContext, webBean);
        am.initQueryWorkFlowWeb(iPrjID, guid);

        OAAdvancedTableBean outerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjLotTable");
        OAAdvancedTableBean innerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjDisTable");
        if (outerTable != null)
            outerTable.queryData(pageContext);
        if (innerTable != null)
            innerTable.prepareForRendering(pageContext);

        OAAdvancedTableBean currentTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("PrjDistributionTable");
        if (currentTable != null)
            currentTable.queryData(pageContext);

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

}
