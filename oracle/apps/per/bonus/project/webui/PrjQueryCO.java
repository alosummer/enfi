/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.project.webui;

import cux.oracle.apps.per.bonus.pay.server.PayAMImpl;

import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import java.util.ArrayList;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

/**
 * 项目经理，项目列表页面
 */
public class PrjQueryCO extends OAControllerImpl {
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

        OAMessageLovInputBean PrjNameBean = 
            (OAMessageLovInputBean)webBean.findIndexedChildRecursive("PrjName");
        PrjNameBean.setValue(pageContext, "");
        OAMessageLovInputBean PrjNumBean = 
            (OAMessageLovInputBean)webBean.findIndexedChildRecursive("ProjectNumTxt");
        PrjNumBean.setValue(pageContext, "");

        //判断当前用户是否为股份、集团、上海的采购、财务部部长
        this.IdentityJudge(pageContext, webBean);
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
        if (pageContext.getParameter("DeptButton") != null)
            this.GotoDeptDistPG(pageContext);
        if (pageContext.getParameter("OKButton") != null) // 确定按钮
            this.OnOKButtionClick(pageContext, webBean);
    }

    /* 项目 确定 按钮的点击事件 */

    private void OnOKButtionClick(OAPageContext pageContext, 
                                  OAWebBean webBean) {
        String strPrjNum = 
            this.GetParameterValue(pageContext, "ProjectNumTxt");
        String strPrjName = this.GetParameterValue(pageContext, "PrjName");
        if ("".equals(strPrjNum)) {
            String strMessage = "项目不能为空！";
            if (!"".equals(strPrjName))
                strMessage = "请选择正确的项目名称！";
            this.ShowMessage(pageContext, strMessage);
            return;
        }

        String strProjectId = this.GetParameterValue(pageContext, "ProjectId");
        String strProjectManagerId = 
            this.GetParameterValue(pageContext, "ProjectManagerId");
        String strProjectSupervisorId = 
            this.GetParameterValue(pageContext, "ProjectSupervisorId");
        String strPrjStatusCode = 
            this.GetParameterValue(pageContext, "ProjectStatusCode");
        Integer iPrjID = 0;
        Integer iProjectManagerId = 0;
        Integer iProjectSupervisorId = 0;
        if (!"".equals(strProjectId))
            iPrjID = Integer.valueOf(strProjectId);
        if (!"".equals(strProjectManagerId))
            iProjectManagerId = Integer.valueOf(strProjectManagerId);
        if (!"".equals(strProjectSupervisorId))
            iProjectSupervisorId = Integer.valueOf(strProjectSupervisorId);

        pageContext.putSessionValue("BonusPrjId", strProjectId);
        pageContext.putSessionValue("BonusPrjNum", strPrjNum);
        pageContext.putSessionValue("BonusPrjName", strPrjName);
        pageContext.putSessionValue("BonusPrjManagerId", 
                                    strProjectManagerId); //项目经理ID
        pageContext.putSessionValue("BonusPrjSupervisorId", 
                                    strProjectSupervisorId); //主管领导ID      

        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        Integer iUserId = am.getLoginPersonId();
        boolean bIsForbid = am.IsPrjForbid(iPrjID);
        // CLOSED 最终关闭, 1020 - 项目暂停  不能再发奖金
        if (bIsForbid || "CLOSED".equals(strPrjStatusCode) || 
            "1020".equals(strPrjStatusCode))
            this.GoToReadOnlyDisPG(pageContext);
        else if (iUserId.equals(iProjectManagerId)) //项目经理发放页面
            this.GoToMemberDisPG(pageContext);
        else if (iUserId.equals(iProjectSupervisorId)) //项目主管领导发放页面
            this.GoToSupervisorDisPG(pageContext);
    }


    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
    }

    private void ShowMessage(OAPageContext pageContext, String strMessage) {
        OAException tipMessage = 
            new OAException(strMessage, OAException.INFORMATION);
        pageContext.putDialogMessage(tipMessage);
    }

    /* 项目经理发放页面 */

    private void GoToMemberDisPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjMemberPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 只读页面 */

    private void GoToReadOnlyDisPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDisReadOnlyPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 主管领导发放页面 */

    private void GoToSupervisorDisPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjSupervisorPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 判断当前用户是否为股份、集团、上海的采购、财务部长 */

    private void IdentityJudge(OAPageContext pageContext, OAWebBean webBean) {
        ProjectAMImpl am = 
            (ProjectAMImpl)pageContext.getApplicationModule(webBean);
        ArrayList list = am.GetCurrentDeptInfo();
        if (list.size() != 2)
            return;
        int iOrgID = Integer.valueOf(list.get(0).toString());
        if (iOrgID == 0)
            return;
        String strOrgName = list.get(1).toString();

        OASubmitButtonBean DeptBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("DeptButton");
        DeptBtn.setRendered(true);
    }

    /* 采购、财务部门部长发放页面 */

    private void GotoDeptDistPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/project/webui/PrjDeptDistPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }
}
