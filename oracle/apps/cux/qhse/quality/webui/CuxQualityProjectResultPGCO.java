/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.cux.qhse.quality.webui;

import cux.oracle.apps.cux.qhse.quality.server.CuxProjectResultVOImpl;
import cux.oracle.apps.cux.qhse.quality.server.CuxQualityAMImpl;


import java.util.ArrayList;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;

/**
 * Controller for ...
 */
public class CuxQualityProjectResultPGCO extends OAControllerImpl {
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

        String souceParam = pageContext.getParameter("source");
        if (souceParam == null) {
            souceParam = "THIS#IS#NULL#VALUE";
        }
        String eventParam = pageContext.getParameter(this.EVENT_PARAM);

        CuxQualityAMImpl pgAM = 
            (CuxQualityAMImpl)pageContext.getRootApplicationModule();
        CuxProjectResultVOImpl resultVO = pgAM.getCuxProjectResultVO1();
        OAQueryBean querybean = 
            (OAQueryBean)webBean.findChildRecursive("ProjectQuery");

        if (pageContext.getParameter(querybean.getGoButtonName()) != null) {

            resultVO.setWhereClause(null);
            resultVO.setWhereClauseParams(null);

            String whereClause = "1 = 1 ";
            ArrayList paramers = new ArrayList();
            int paramIndex = 1;


            String searchProjectOrg = 
                pageContext.getParameter("SearchProjectOrg");
            if (searchProjectOrg != null && !"".equals(searchProjectOrg)) {
                whereClause = 
                        whereClause + " and project_carrying_out_org_name = :" + 
                        paramIndex + " ";
                paramers.add(searchProjectOrg);
                paramIndex++;
            }

            String searchProjectNumber = 
                pageContext.getParameter("SearchProjectNumber");
            if (searchProjectNumber != null && 
                !"".equals(searchProjectNumber)) {
                whereClause = 
                        whereClause + " and project_number = :" + paramIndex + 
                        " ";
                paramers.add(searchProjectNumber);
                paramIndex++;
            }

            String searchProjectType = 
                pageContext.getParameter("SearchProjectType");
            if (searchProjectType != null && !"".equals(searchProjectType)) {
                whereClause = 
                        whereClause + " and project_type = :" + paramIndex + 
                        " ";
                paramers.add(searchProjectType);
                paramIndex++;
            }

            String searchProjectStatusName = 
                pageContext.getParameter("SearchProjectStatusName");
            if (searchProjectStatusName != null && 
                !"".equals(searchProjectStatusName)) {
                whereClause = 
                        whereClause + " and project_status_name = :" + paramIndex + 
                        " ";
                paramers.add(searchProjectStatusName);
                paramIndex++;
            }

            String searchProjectManagerName = 
                pageContext.getParameter("SearchProjectManagerName");
            if (searchProjectManagerName != null && 
                !"".equals(searchProjectManagerName)) {
                whereClause = 
                        whereClause + " and project_manager_name = :" + paramIndex + 
                        " ";
                paramers.add(searchProjectManagerName);
                paramIndex++;
            }

            String searchProjectCustomerName = 
                pageContext.getParameter("SearchProjectCustomerName");
            if (searchProjectCustomerName != null && 
                !"".equals(searchProjectCustomerName)) {
                whereClause = 
                        whereClause + " and project_customer_name = :" + paramIndex + 
                        " ";
                paramers.add(searchProjectCustomerName);
                paramIndex++;
            }

            String SearchProjectStartDate = 
                pageContext.getParameter("SearchProjectStartDate");
            if (SearchProjectStartDate != null && 
                !"".equals(SearchProjectStartDate)) {
                whereClause = 
                        whereClause + " and project_start_date = to_date(:" + 
                        paramIndex + " ,'YYYY-MM-DD') ";
                paramers.add(SearchProjectStartDate);
                paramIndex++;
            }

            String searchProjectEndDate = 
                pageContext.getParameter("SearchProjectEndDate");
            if (searchProjectEndDate != null && 
                !"".equals(searchProjectEndDate)) {
                whereClause = 
                        whereClause + " and project_end_date =to_date(:" + 
                        paramIndex + ",'YYYY-MM-DD') ";
                paramers.add(searchProjectEndDate);
                paramIndex++;
            }


            resultVO.setWhereClause(whereClause);
            resultVO.setWhereClauseParams(paramers.toArray());
            resultVO.executeQuery();
            //   System.out.println(resultVO.getWhereClause());
        } //end of if (pageContext.getParameter(querybean.getGoButtonName()) != null) {
    }

}
