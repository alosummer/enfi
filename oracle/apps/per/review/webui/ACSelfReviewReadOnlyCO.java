/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.AppraisalAMImpl;
import cux.oracle.apps.per.review.server.KPIDetailsVOImpl;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

/**
 * 绩效管理_员工自助, 经理自助
 * 绩效合同页面 - 查看
 * add by yang.gang, 2015-12-17
 */
public class ACSelfReviewReadOnlyCO extends OAControllerImpl {
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

        String appraisalId = this.GetSessionValue(pageContext, "appraisalId");
        String kpiClass = this.GetSessionValue(pageContext, "kpiClass");
        String role = this.GetSessionValue(pageContext, "role");
        String kpiClassWeight = 
            this.GetSessionValue(pageContext, "kpiClassWeight");
        String phaseId = this.GetSessionValue(pageContext, "phaseId");

        if ("".equals(appraisalId) || "".equals(kpiClass) || "".equals(role) || 
            !"REVIEW".equals(phaseId)) {
            return;
        }

        this.SetWeightShow(webBean, kpiClassWeight);
        this.SetData(pageContext, webBean, appraisalId, kpiClass);
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
        if (pageContext.getParameter("Cancel") != null) {
            this.GoBack(pageContext);
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

    /* 返回合同页面 */

    private void GoBack(OAPageContext pageContext) {
        String appraisalId = this.GetSessionValue(pageContext, "appraisalId");
        pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG&appraisalId=" + 
                                       appraisalId, null, (byte)0, null, null, 
                                       true, "N");
    }

    /* 设置权重显示 */

    private void SetWeightShow(OAWebBean webBean, String kpiClassWeight) {
        OAMessageTextInputBean weight_pa = 
            (OAMessageTextInputBean)webBean.findChildRecursive("Weight");
        String weightStr = "权重(" + kpiClassWeight + "%)";
        weight_pa.setLabel(weightStr);
    }

    /* 设置数据显示 */

    private void SetData(OAPageContext pageContext, OAWebBean webBean, 
                         String appraisalId, String kpiClass) {
        AppraisalAMImpl amImpl = 
            (AppraisalAMImpl)pageContext.getApplicationModule(webBean);
        KPIDetailsVOImpl kpiDetailsVO = 
            (KPIDetailsVOImpl)amImpl.findViewObject("KPIDetailsVO1");
        kpiDetailsVO.setWhereClauseParam(0, appraisalId);
        kpiDetailsVO.setWhereClauseParam(1, kpiClass);
        kpiDetailsVO.executeQuery();

        amImpl.initKpiArea(kpiClass);
        amImpl.queryAppKpi(appraisalId, kpiClass);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("AppraisalKPIVO1");
        tableBean.prepareForRendering(pageContext);

        this.FinalMarkBean(pageContext, webBean);
    }

    /* 设置最终得分项 */

    private void FinalMarkBean(OAPageContext pageContext, OAWebBean webBean) {
        String statusId = this.GetSessionValue(pageContext, "statusId");

        OAMessageStyledTextBean FinalMarkBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("FinalMark");

        if (!"COMPLETED".equals(statusId) && !"PUBLISHED".equals(statusId) && 
            !"FREEZED".equals(statusId)) {
            FinalMarkBean.setValue(pageContext, null);
        }
    }

}
