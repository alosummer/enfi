/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.aprprocess.webui;

import cux.oracle.apps.per.aprprocess.server.AprAMImpl;

import cux.oracle.apps.per.aprprocess.server.AprEmpSelfDetailVOImpl;

import cux.oracle.apps.per.aprprocess.server.AprManagerDetailVOImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.ViewObject;

/**
 * KPI报表,拷贝/per/reivew/webui/KpiRtpPg
 * created by yang.gang. 2016-11-14
 */
public class KpiRptCO extends OAControllerImpl {
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
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String arg1 = this.GetParameterValue(pageContext, "appraisalid");
        String arg2 = this.GetParameterValue(pageContext, "kpiid");

        if ("".equals(arg1) || !am.isInteger(arg1)) {
            this.ShowMessage(pageContext, "绩效合同ID号获取失败，请刷新页面后重试！", "error");
            return;
        } else if ("".equals(arg2) || !am.isInteger(arg2)) {
            this.ShowMessage(pageContext, "KPI ID获取失败，请刷新页面后重试！", "error");
            return;
        }

        pageContext.putSessionValue("appraisalId", arg1);
        ArrayList list = am.dynamicVO(arg1, arg2);

        if (list == null) {
            OAWebBean NoDataInfo = 
                (OAWebBean)webBean.findChildRecursive("NoDataInfo");
            NoDataInfo.setRendered(false); //hidden no data found text        
        } else {
            OAWebBean NoDataInfo = 
                (OAWebBean)webBean.findChildRecursive("NoDataInfo");
            NoDataInfo.setRendered(false); //hidden no data found text
            OATableBean tableBean = 
                (OATableBean)this.createWebBean(pageContext, 
                                                OATableBean.TABLE_BEAN, null, 
                                                "tableBeanRN");
            tableBean.setNumberOfRowsDisplayed(300);
            tableBean.setTableNavigationDisplayed(true);
            webBean.addIndexedChild(tableBean);
            Iterator it = list.iterator();
            int i = 1;
            String label = "";
            while (it.hasNext()) {
                OAMessageStyledTextBean stb = 
                    (OAMessageStyledTextBean)this.createWebBean(pageContext, 
                                                                OAMessageStyledTextBean.MESSAGE_STYLED_TEXT_BEAN, 
                                                                null, 
                                                                "Text" + i);
                label = it.next().toString();
                stb.setViewAttributeName(label);
                stb.setViewUsageName("RefObjectLovVO");
                stb.setLabel(label);
                tableBean.addIndexedChild(stb);
                i++;
            }
        }

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
        if (pageContext.getParameter("Return") == null)
            return;

        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        String appraisalId = this.GetSessionValue(pageContext, "appraisalId");
        if (!am.isInteger(appraisalId))
            return;

        Integer iAprID = Integer.valueOf(appraisalId);
        String strUrl = "OA.jsp?page=/cux/oracle/apps/per/aprprocess/webui/";

        //selfreviewupdate,selfreviewreadonly,managerreviewreadonly,managerreviewupdate,reviewfinish
        String aprkpistatus = 
            this.GetSessionValue(pageContext, "aprkpistatus");

        if ("".equals(aprkpistatus)) {
            String strrtn = this.getPreviousWeb(pageContext, webBean, iAprID);
            if ("".equals(strrtn))
                return;
            strUrl += strrtn;
        } else if ("selfreviewupdate".equals(aprkpistatus))
            strUrl += "EmpSelfReviewUpdatePG";
        else if ("selfreviewreadonly".equals(aprkpistatus))
            strUrl += "EmpSelfReviewReadOnlyPG";
        else if ("managerreviewupdate".equals(aprkpistatus))
            strUrl += "ManagerReviewUpdatePG";
        else if ("managerreviewreadonly".equals(aprkpistatus))
            strUrl += "ManagerReviewReadOnlyPG";
        else if ("reviewfinish".equals(aprkpistatus))
            strUrl += "ReviewFinishPG";
        else
            return;

        strUrl += "&appraisalid=" + appraisalId + "&kpirpfrom=kpireport";

        pageContext.putSessionValue("aprkpistatus", "");
        pageContext.forwardImmediately(strUrl, null, 
                                       OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                       null, null, true, "Y");
    }

    private String GetParameterValue(OAPageContext pageContext, 
                                     String strParaName) {
        String strRtn = "";
        if (pageContext.getParameter(strParaName) != null) {
            strRtn = pageContext.getParameter(strParaName).toString();
        }
        return strRtn;
    }

    // exp:error - OAException.ERROR,  info - OAException.INFORMATION

    private void ShowMessage(OAPageContext pageContext, String strMessage, 
                             String exp) {
        if ("info".equals(exp)) {
            OAException tipMessage = 
                new OAException(strMessage, OAException.INFORMATION);
            pageContext.putDialogMessage(tipMessage);
        } else if ("error".equals(exp)) {
            OAException tipMessage = 
                new OAException(strMessage, OAException.ERROR);
            pageContext.putDialogMessage(tipMessage);
        }
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

    /* 根据当前用户、合同的状态，获取前一个页面的地址 */

    private String getPreviousWeb(OAPageContext pageContext, OAWebBean webBean, 
                                  Integer iAprID) {
        AprAMImpl am = (AprAMImpl)pageContext.getApplicationModule(webBean);
        AprEmpSelfDetailVOImpl selfvo = am.getAprEmpSelfDetailVO1();
        if (selfvo.isExecuted())
            selfvo.initQuery(iAprID);

        String PhaseId = selfvo.GetAttrValue("PhaseId"); //阶段
        String IsPromisee = 
            selfvo.GetAttrValue("IsPromisee"); //是否操作者为本人,'N'是本人, 'Y'不是
        String StatusId = selfvo.GetAttrValue("StatusId");
        if ("REVIEW".equals(PhaseId)) {
            if ("COMPLETED".equals(StatusId))
                return "ReviewFinishPG";
            else if ("N".equals(IsPromisee))
                return "EmpSelfReviewUpdatePG";
            else if ("Y".equals(IsPromisee))
                return "EmpSelfReviewReadOnlyPG";
        }

        AprManagerDetailVOImpl manvo = am.getAprManagerDetailVO1();
        PhaseId = manvo.GetAttrValue("PhaseId"); //阶段
        IsPromisee = manvo.GetAttrValue("IsPromisee"); //是否操作者为本人,'N'是本人, 'Y'不是
        StatusId = manvo.GetAttrValue("StatusId");
        if ("REVIEW".equals(PhaseId)) {
            if ("COMPLETED".equals(StatusId))
                return "ReviewFinishPG";
            else if ("N".equals(IsPromisee))
                return "ManagerReviewUpdatePG";
            else if ("Y".equals(IsPromisee))
                return "ManagerReviewReadOnlyPG";
        }

        return "";
    }

}
