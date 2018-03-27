package cux.oracle.apps.pa.structure.webui;

import java.sql.CallableStatement;
import java.sql.Types;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAHGridQueriedRowEnumerator;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.table.OAHGridBean;
import oracle.apps.pa.structure.webui.EditTasksLayoutCO;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class CUXEditTasksLayoutCO extends EditTasksLayoutCO {
    public CUXEditTasksLayoutCO() {
    }

    /**
     * @param paramOAPageContext
     * @param paramOAWebBean
     */
    @Override
    public void processFormRequest(OAPageContext paramOAPageContext, 
                                   OAWebBean paramOAWebBean) {
        if (paramOAPageContext.getParameter("CreateTasks") != null) {
            this.checkOpMain(paramOAPageContext, paramOAWebBean, "TaskHGrid", 
                             "CREATETASKS");
        } else {
            if (paramOAPageContext.getParameter("Indent") != null) {
                this.checkOpMain(paramOAPageContext, paramOAWebBean, 
                                 "TaskHGrid", "INDENT");
            } else if (paramOAPageContext.getParameter("Outdent") != null) {

                this.checkOpMain(paramOAPageContext, paramOAWebBean, 
                                 "TaskHGrid", "OUTDENT");
            } else if (paramOAPageContext.getParameter("MoveTasks") != null) {

                this.checkOpMain(paramOAPageContext, paramOAWebBean, 
                                 "TaskHGrid", "MOVETASKS");
            } else if (paramOAPageContext.getParameter("Remove") != null) {
                this.checkOpMain(paramOAPageContext, paramOAWebBean, 
                                 "TaskHGrid", "REMOVE");
            }
        }
        super.processFormRequest(paramOAPageContext, paramOAWebBean);
    }

    /**
     * 
     * @param paramOAWebBean
     * @param paramOAPageContext
     * @param paramString
     */
    private void checkOpMain(OAPageContext paramOAPageContext, 
                             OAWebBean paramOAWebBean, String paramString, 
                             String operation) {
        OAHGridBean localOAHGridBean = 
            (OAHGridBean)paramOAWebBean.findIndexedChildRecursive(paramString);
        OAHGridQueriedRowEnumerator localOAHGridQueriedRowEnumerator = 
            new OAHGridQueriedRowEnumerator(paramOAPageContext, 
                                            localOAHGridBean);
        while (localOAHGridQueriedRowEnumerator.hasMoreElements()) {
            Row localRow = (Row)localOAHGridQueriedRowEnumerator.nextElement();
            if ("Y".equals((String)localRow.getAttribute("SelectFlag"))) {
                this.checkOperation(paramOAPageContext, paramOAWebBean, 
                                    operation, 
                                    (Number)localRow.getAttribute("ProjectId"), 
                                    (Number)localRow.getAttribute("ProjElementId"), 
                                    (Number)localRow.getAttribute("ElementVersionId"));
            }

        }
    }


    /**
     * 判断操作是否合法，则无事，如果不合法，则报出错误警告
     * @param operation
     * @param projectId
     * @param elementsId
     * @param elementsVersionId
     * @return
     */
    private void checkOperation(OAPageContext paramOAPageContext, 
                                OAWebBean paramOAWebBean, String operation, 
                                Number projectId, Number elementsId, 
                                Number elementsVersionId) {
        OAApplicationModule localOAApplicationModule = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        String retValue = null;
        OADBTransaction tx = localOAApplicationModule.getOADBTransaction();
        CallableStatement cs = 
            tx.createCallableStatement("begin\n" + "  :1 := cux_pa_wbs_control_pkg.check_operation(p_operation => :2,\n" + 
                                       "                                                 p_project_id => :3,\n" + 
                                       "                                                 p_elements_id => :4,\n" + 
                                       "                                                 p_elements_version_id => :5);\n" + 
                                       "end;", 1);
        try {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, operation);
            cs.setInt(3, projectId.intValue());
            cs.setInt(4, elementsId.intValue());
            cs.setInt(5, elementsVersionId.intValue());
            cs.execute();
            retValue = cs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!"SUCCESS".equals(retValue)) {
            throw new OAException(retValue);
        }
        //        return retValue;
    }
}
