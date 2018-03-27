/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.pa.source.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

/**
 * Controller for ...
 */
public class HrSourceCO extends OAControllerImpl {
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

        String hrSourceType = pageContext.getParameter("HrSourceType");
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if (hrSourceType != null && !"".equals(hrSourceType)) {
            am.getOADBTransaction().putValue("HrSourceType", hrSourceType);
        }
        initLayout(hrSourceType, webBean);
        if ("M".equals(hrSourceType)) {
            pageContext.getPageLayoutBean().setWindowTitle("项目管理人才库");
            OAWebBean WorkExperience = 
                webBean.findChildRecursive("WorkExperience");
            WorkExperience.setAttributeValue(this.PROMPT_ATTR, "项目管理年限");
            OAHeaderBean queryHeader = 
                (OAHeaderBean)webBean.findChildRecursive("QueryRN");
            queryHeader.setText(pageContext, "项目管理人才库查询");
            OAMessageStyledTextBean PersonName = 
                (OAMessageStyledTextBean)webBean.findChildRecursive("PersonName");
            PersonName.setDestination("OA.jsp?page=/cux/oracle/apps/pa/source/webui/EmployeeProjectListPG&personId={@PersonId}&retainAM=Y&HrSourceType=M");
        } else if ("D".equals(hrSourceType)) {
            pageContext.getPageLayoutBean().setWindowTitle("设计人员人才库");
            OAWebBean WorkExperience = 
                webBean.findChildRecursive("WorkExperience");
            WorkExperience.setAttributeValue(this.PROMPT_ATTR, "设计工作年限");
            OAHeaderBean queryHeader = 
                (OAHeaderBean)webBean.findChildRecursive("QueryRN");
            queryHeader.setText(pageContext, "设计人员人才库查询");
            OAMessageStyledTextBean PersonName = 
                (OAMessageStyledTextBean)webBean.findChildRecursive("PersonName");
            PersonName.setDestination("OA.jsp?page=/cux/oracle/apps/pa/source/webui/EmployeeProjectListPG&personId={@PersonId}&retainAM=Y&HrSourceType=D");
        }
        am.invokeMethod("initSearchPVO");
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        String event = pageContext.getParameter(EVENT_PARAM);
        if ("search".equals(event)) {
            am.invokeMethod("doSearch");
        }
        if ("Detail".equals(event)) {
            HashMap parameters = new HashMap();
            parameters.put("personId", pageContext.getParameter("personId"));
            parameters.put("HrSourceType", 
                           am.getOADBTransaction().getValue("HrSourceType"));
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/pa/source/webui/HrSourceHistoryPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, parameters, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        if ("Delete".equals(event)) {
            String rowRef = 
                pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE);
            Row row = am.findRowByRef(rowRef);
            row.remove();
            am.getOADBTransaction().commit();
        }
    }

    public String[] getManagerTableBeanNames() {
        String[] beans = 
        { "gw", "zj", "PersonName", "PersonNum", "DeptName", "DateOfBirth", 
          "Education", "College", "GraduationDate", "FirstWorkingTime", 
          "WorkExperience", "Speciality", "Zc", "Prrq", "JobName", 
          "CurrentYearGrade", "LastYearGrade", "YearBeforeLastGrade", "Zg", 
          "XmglGw", "XmglZj", "ZzQz", "HrsourceDate", "Detail" };
        return beans;
    }

    public String[] getDesignerTableBeanNames() {
        String[] beans = 
        { "Job", "PersonName", "PersonNum", "DeptName", "WorkExperience", 
          "Speciality", "Education", "Zc", "Zg", "PositionName", "JobName", 
          "EvaluateJobLevel", "Zyfzr", "Ztr", "Sjr", "Jhr", "Shr", "Sdr", 
          "Ylgdzz", "Zcly", "HrsourceDate", "Detail", "CurrentYearGrade", 
          "LastYearGrade", "YearBeforeLastGrade" };
        return beans;
    }

    public void initLayout(String hrSourceType, OAWebBean webBean) {
        String[] beans = null;
        if ("M".equals(hrSourceType)) {
            beans = getManagerTableBeanNames();
        } else if ("D".equals(hrSourceType)) {
            beans = getDesignerTableBeanNames();
        }
        for (String bean: beans) {
            OAWebBean sourceBean = webBean.findChildRecursive(bean);
            sourceBean.setAttributeValue(this.RENDERED_ATTR, true);
        }
    }
}
