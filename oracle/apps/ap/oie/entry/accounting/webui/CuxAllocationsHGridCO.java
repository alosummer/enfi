package cux.oracle.apps.ap.oie.entry.accounting.webui;

import com.sun.java.util.collections.ArrayList;

import cux.oracle.apps.ap.oie.entry.accounting.server.CuxCurrentEmpOrgQueryVOImpl;
import cux.oracle.apps.ap.oie.entry.accounting.server.CuxCurrentEmpOrgQueryVORowImpl;
import cux.oracle.apps.ap.oie.entry.accounting.server.CuxProjectsQueryVOImpl;
import cux.oracle.apps.ap.oie.entry.accounting.server.CuxProjectsQueryVORowImpl;
import cux.oracle.apps.ap.oie.entry.accounting.server.CuxTasksQueryVOImpl;
import cux.oracle.apps.ap.oie.entry.accounting.server.CuxTasksQueryVORowImpl;
import cux.oracle.apps.ap.oie.entry.header.server.RenderVOImpl;

import oracle.apps.ap.oie.entry.accounting.server.AllocationsHeaderVOImpl;
import oracle.apps.ap.oie.entry.accounting.server.AllocationsHeaderVORowImpl;
import oracle.apps.ap.oie.entry.accounting.server.AllocationsLinesVORowImpl;
import oracle.apps.ap.oie.entry.accounting.server.ExpenseAllocationsAMImpl;
import oracle.apps.ap.oie.entry.accounting.server.ExpenseAllocationsVOImpl;
import oracle.apps.ap.oie.entry.accounting.webui.AllocationsHGridCO;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAHGridQueriedRowEnumerator;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.table.OAHGridBean;

import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

public class CuxAllocationsHGridCO extends AllocationsHGridCO {
    public CuxAllocationsHGridCO() {
    }

    @Override
    public void processRequest(OAPageContext paramOAPageContext, 
                               OAWebBean paramOAWebBean) {


        super.processRequest(paramOAPageContext, paramOAWebBean);
        //TODO
        String jsFunction = "function del(){\n" + "    alert(\"OK\");\n" + "}";

        paramOAWebBean.findChildRecursive("OIENavBar");

        OAApplicationModule rootAM = 
            paramOAPageContext.getRootApplicationModule();

        OAViewObject headerVO = 
            (OAViewObject)rootAM.findViewObject("ExpenseReportHeadersVO");
        OARow headerRow = (OARow)headerVO.getCurrentRow();
        if (headerRow == null) {
            headerRow = (OARow)headerVO.first();
        }


        String projectNum = (String)headerRow.getAttribute("Attribute1");

        if (!"".equals(projectNum) && projectNum != null) {
            String taskNum = "99";
            CuxProjectsQueryVOImpl projectVO = 
                (CuxProjectsQueryVOImpl)rootAM.findViewObject("CuxProjectsQueryVO1");
            if (projectVO == null) {
                projectVO = 
                        (CuxProjectsQueryVOImpl)rootAM.createViewObject("CuxProjectsQueryVO1", 
                                                                        "cux.oracle.apps.ap.oie.entry.accounting.server.CuxProjectsQueryVO");
            }
            projectVO.setWhereClauseParams(null);
            projectVO.setWhereClauseParams(new Object[] { projectNum });
            projectVO.executeQuery();

            CuxProjectsQueryVORowImpl projectInfoRow = 
                (CuxProjectsQueryVORowImpl)projectVO.first();
            String projectName = null;
            Number projectId = null;
            if (projectInfoRow != null) {
                projectName = projectInfoRow.getProjectName();
                projectId = projectInfoRow.getProjectId();

                CuxTasksQueryVOImpl taskVO = 
                    (CuxTasksQueryVOImpl)rootAM.findViewObject("CuxTasksQueryVO1");
                if (taskVO == null) {
                    taskVO = 
                            (CuxTasksQueryVOImpl)rootAM.createViewObject("CuxTasksQueryVO1", 
                                                                         "cux.oracle.apps.ap.oie.entry.accounting.server.CuxTasksQueryVO");
                }
                taskVO.setWhereClauseParams(null);
                taskVO.setWhereClauseParams(new Object[] { taskNum, 
                                                           projectId });
                taskVO.executeQuery();

                String taskName = null;
                Number taskId = null;
                CuxTasksQueryVORowImpl taskInfoRow = 
                    (CuxTasksQueryVORowImpl)taskVO.first();

                if (taskInfoRow != null) {
                    taskName = taskInfoRow.getTaskName();
                    taskId = taskInfoRow.getTaskId();
                    ExpenseAllocationsAMImpl am = 
                        (ExpenseAllocationsAMImpl)paramOAPageContext.getApplicationModule(paramOAWebBean);
                    AllocationsHeaderVOImpl vo = am.getAllocationsHeaderVO();
                    int fetchedRowCount = vo.getRowCount();
                    OARow row = null;
                    RowSetIterator setIter = 
                        vo.createRowSetIterator("setIter");
                    if (fetchedRowCount > 0) {
                        setIter.setRangeStart(0);
                        setIter.setRangeSize(fetchedRowCount);
                        for (int i = 0; i < fetchedRowCount; i++) {
                            row = (OARow)setIter.getRowAtRangeIndex(i);

                            RowSet localRowSet = 
                                (RowSet)((AllocationsHeaderVORowImpl)row).getLinesAccessor();
                            localRowSet.reset();

                            AllocationsLinesVORowImpl localAllocationsLinesVORowImpl = 
                                null;
                            while (localRowSet.hasNext()) {
                                localAllocationsLinesVORowImpl = 
                                        (AllocationsLinesVORowImpl)localRowSet.next();
                                if ("Updateable".equals(localAllocationsLinesVORowImpl.getRenderProject())) {
                                    localAllocationsLinesVORowImpl.setAttribute("ProjectNumber", 
                                                                                projectNum);
                                    localAllocationsLinesVORowImpl.setAttribute("ProjectName", 
                                                                                projectName);
                                    localAllocationsLinesVORowImpl.setAttribute("ProjectId", 
                                                                                projectId);

                                    localAllocationsLinesVORowImpl.setAttribute("TaskNumber", 
                                                                                taskNum);
                                    localAllocationsLinesVORowImpl.setAttribute("TaskName", 
                                                                                taskName);
                                    localAllocationsLinesVORowImpl.setAttribute("TaskId", 
                                                                                taskId);
                                }
                            }
                        }
                        // Always close the iterator when you're done.
                        setIter.closeRowSetIterator();

                    }
                } //if (taskInfoRow != null) {


                CuxCurrentEmpOrgQueryVOImpl empOrgVO = 
                    (CuxCurrentEmpOrgQueryVOImpl)rootAM.findViewObject("CuxCurrentEmpOrgQueryVO1");
                if (empOrgVO == null) {
                    empOrgVO = 
                            (CuxCurrentEmpOrgQueryVOImpl)rootAM.createViewObject("CuxCurrentEmpOrgQueryVO1", 
                                                                                 "cux.oracle.apps.ap.oie.entry.accounting.server.CuxCurrentEmpOrgQueryVO");
                }
                empOrgVO.executeQuery();

                String orgName = null;
                Number orgId = null;
                CuxCurrentEmpOrgQueryVORowImpl empOrgRow = 
                    (CuxCurrentEmpOrgQueryVORowImpl)empOrgVO.first();

                if (empOrgRow != null) {
                    orgName = empOrgRow.getOrgName();
                    orgId = empOrgRow.getOrganizationId();
                    ExpenseAllocationsAMImpl am = 
                        (ExpenseAllocationsAMImpl)paramOAPageContext.getApplicationModule(paramOAWebBean);
                    AllocationsHeaderVOImpl vo = am.getAllocationsHeaderVO();
                    int fetchedRowCount = vo.getRowCount();
                    OARow row = null;
                    RowSetIterator setIter = 
                        vo.createRowSetIterator("setIter");
                    if (fetchedRowCount > 0) {
                        setIter.setRangeStart(0);
                        setIter.setRangeSize(fetchedRowCount);
                        for (int i = 0; i < fetchedRowCount; i++) {
                            row = (OARow)setIter.getRowAtRangeIndex(i);

                            RowSet localRowSet = 
                                (RowSet)((AllocationsHeaderVORowImpl)row).getLinesAccessor();
                            localRowSet.reset();

                            AllocationsLinesVORowImpl localAllocationsLinesVORowImpl = 
                                null;
                            while (localRowSet.hasNext()) {
                                localAllocationsLinesVORowImpl = 
                                        (AllocationsLinesVORowImpl)localRowSet.next();
                                if ("Updateable".equals(localAllocationsLinesVORowImpl.getRenderProject())) {
                                    localAllocationsLinesVORowImpl.setLineExpenditureOrganizationId(orgId);
                                    localAllocationsLinesVORowImpl.setProjectExpendOrgName(orgName);
                                }
                            }
                        }
                        // Always close the iterator when you're done.
                        setIter.closeRowSetIterator();

                    }
                } //if (taskInfoRow != null) {

            } //end of if (projectInfoRow != null) {
        } //end of if (!"".equals(projectNum) && projectNum != null) {
    }
}
