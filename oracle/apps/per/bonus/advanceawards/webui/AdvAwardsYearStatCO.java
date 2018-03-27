/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.advanceawards.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvAwardsDeptLovVOImpl;
import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;

import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OATextInputBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

/**
 * 年度奖金查询页面 
 * added by yang.gang,2015-12-28
 * 导出功能暂时未实现，由于导出的列标题问题（只能显示下面的一行，不能显示上面的月份）
 * edited by yang.gang,2016-3-28
 */
public class AdvAwardsYearStatCO extends OAControllerImpl {
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);

        int personId = amImpl.getLoginPersonId();
        AdvAwardsDeptLovVOImpl deptLovVO = 
            (AdvAwardsDeptLovVOImpl)am.findViewObject("AdvAwardsDeptLovVO1");
        if (personId != 0) {
            deptLovVO.initQuery(personId);
        }
        OAMessageTextInputBean yearBean = 
            (OAMessageTextInputBean)webBean.findChildRecursive("QueryYear");
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String strDate = df.format(today);
        yearBean.setValue(pageContext, strDate);
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
        if (pageContext.getParameter("Search") == null)
            return;

        OAMessageChoiceBean deptNameBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDept");
        if (deptNameBean.getValue(pageContext) == null || 
            "".equals(deptNameBean.getValue(pageContext).toString())) {
            OAException message = 
                new OAException("没有可供选择的部门，不能继续操作！", OAException.ERROR);
            pageContext.putDialogMessage(message);
            return;
        }

        OAMessageTextInputBean yearBean = 
            (OAMessageTextInputBean)webBean.findChildRecursive("QueryYear");
        if (yearBean.getValue(pageContext) == null || 
            "".equals(yearBean.getValue(pageContext))) {
            OAException message = 
                new OAException("请选择查询年度！", OAException.ERROR);
            pageContext.putDialogMessage(message);
            return;
        }

        String DeptOrgID = 
            deptNameBean.getSelectionValue(pageContext).toString();
        String year = yearBean.getValue(pageContext).toString();
        int iOrgID = Integer.parseInt(DeptOrgID);

        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)pageContext.getApplicationModule(webBean);
        amImpl.initYearStat(year, iOrgID);

        OAAdvancedTableBean currentTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("SummaryTable");
        currentTable.queryData(pageContext);
    }

}
