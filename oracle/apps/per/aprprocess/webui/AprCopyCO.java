/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AprAMImpl;

import cux.oracle.apps.per.aprprocess.server.AprCopyVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

/**
 * 拷贝往年的绩效合同
 * created by yang.gang. 2016-4-26
 */
public class AprCopyCO extends OAControllerImpl {
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
    }

    /* 初始化页面数据 */

    private void initWeb(OAPageContext pageContext, OAWebBean webBean) {
        //从地址栏获取参数
        String appraisalId = 
            this.GetParameterValue(pageContext, "appraisalid");
        String aprPersonId = 
            this.GetParameterValue(pageContext, "appraisalpersonid");

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);

        Integer iAprId = 0;
        Integer iPersonId = 0;
        if (am.isInteger(appraisalId))
            iAprId = Integer.parseInt(appraisalId);
        if (am.isInteger(aprPersonId))
            iPersonId = Integer.parseInt(aprPersonId);

        if (iAprId == 0 || iPersonId == 0)
            return;
        pageContext.putSessionValue("appraisalId", appraisalId);

        AprCopyVOImpl vo = am.getAprCopyVO1();
        vo.initQuery(iPersonId, iAprId);
        this.TableQueryData(pageContext, webBean, "AprCopyVO1");
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
        if (pageContext.getParameter("Return") != null) {
            this.BackContractPG(pageContext);
        } else if (pageContext.getParameter("Apply") != null) {
            this.CopyContract(pageContext, webBean);
        }
    }

    /* 拷贝绩效合同 */

    private void CopyContract(OAPageContext pageContext, OAWebBean webBean) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        Integer iSelectAprID = am.checkCopySelectApr();
        if (iSelectAprID == 0) {
            this.ShowMessage(pageContext, "请选择绩效合同！");
            return;
        }

        String appraisalId = this.GetSessionValue(pageContext, "appraisalId");
        Integer iCurAprID = 0;
        if (am.isInteger(appraisalId))
            iCurAprID = Integer.valueOf(appraisalId);
        String result = am.processCopy(iSelectAprID, iCurAprID);
        if (!"Y".equals(result)) {
            this.ShowMessage(pageContext, "绩效合同复制失败！请联系系统管理员。");
            return;
        }
        this.ShowMessage(pageContext, "绩效合同复制成功！");
        this.BackContractPG(pageContext);
    }

    /* 返回绩效合同列表页面 */

    private void BackContractPG(OAPageContext pageContext) {
        String appraisalId = this.GetSessionValue(pageContext, "appraisalId");

        String strUrl = 
            "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/EmpSelfGoalUpdatePG&appraisalId=".concat(appraisalId);
        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "N");
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

    /* 表格显示VO数据 */

    private void TableQueryData(OAPageContext pageContext, OAWebBean webBean, 
                                String tablename) {
        OATableBean tableBean = 
            (OATableBean)webBean.findIndexedChildRecursive(tablename);
        tableBean.queryData(pageContext, false);
    }

    //  session 获取值

    private String GetSessionValue(OAPageContext pageContext, 
                                   String strSessionName) {
        String strRtn = "";
        if (pageContext.getSessionValue(strSessionName) != null && 
            !pageContext.getSessionValue(strSessionName).toString().equals("")) {
            strRtn = pageContext.getSessionValue(strSessionName).toString();
        }
        return strRtn;
    }

}
