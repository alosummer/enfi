/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.offlinelist.webui;

import cux.oracle.apps.per.bonus.offlinelist.server.OfflineListAMImpl;

import java.sql.CallableStatement;

import java.sql.Types;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

import oracle.jbo.Row;

/**
 * Controller for ...
 */
public class OfflineListAdminCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");
    private static int addPeopleNumber = 0;

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAAdvancedTableBean offlineTable = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("OfflineListTable");
        offlineTable.queryData(pageContext);
        addPeopleNumber = 0;
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
        String event = pageContext.getParameter("event");
        OfflineListAMImpl amImpl = 
            (OfflineListAMImpl)pageContext.getApplicationModule(webBean);

        if (pageContext.getParameter("AddRowButton") != null) {
            createAnotherRow(pageContext, webBean, null);
        }

        if (pageContext.getParameter("SaveButton") != null) {
            amImpl.saveOfflineList();
            OAAdvancedTableBean offlineTable = 
                (OAAdvancedTableBean)webBean.findIndexedChildRecursive("OfflineListTable");
            offlineTable.queryData(pageContext);
            OAException message = 
                new OAException("保存成功！", OAException.INFORMATION);
            pageContext.putDialogMessage(message);
        }

        if ("delete".equals(event)) {
            String deleteEmployeeNumber = 
                pageContext.getParameter("deleteEmpNumber");
            amImpl.deleteOfflineList(deleteEmployeeNumber);
            OAAdvancedTableBean offlineTable = 
                (OAAdvancedTableBean)webBean.findIndexedChildRecursive("OfflineListTable");
            offlineTable.queryData(pageContext);
            OAException message = 
                new OAException("删除成功！", OAException.INFORMATION);
            pageContext.putDialogMessage(message);
        }
    }

    private void createAnotherRow(OAPageContext pageContext, OAWebBean webBean, 
                                  Row row) {
        OAApplicationModuleImpl am = 
            (OAApplicationModuleImpl)pageContext.getApplicationModule(webBean);
        OAViewObject vo = (OAViewObject)am.findViewObject("OfflineListVO1");
        if (vo != null) {
            Row row1 = vo.createRow();
            int index = vo.getRangeIndexOf(vo.last());
            vo.insertRowAtRangeIndex(index + 1, row1);

            addPeopleNumber += 1;
            int count = 0;
            CallableStatement cs = null;
            OADBTransaction transaction = am.getOADBTransaction();
            cs = 
 transaction.createCallableStatement("call apps.cux_hr_bonus.GET_OFFLINE_LIST_COUNT(?)", 
                                     1);
            try {
                cs.registerOutParameter(1, Types.INTEGER);
                cs.executeUpdate();
                count = cs.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            count += 100000;
            count += addPeopleNumber;
            row1.setAttribute("EmployeeNumber", "W" + count);
            row1.setAttribute("DeptName", "线外");
            row1.setAttribute("Attribute1", "N");
            row = row1;
        }
    }

}
