package cux.oracle.apps.pa.structure.webui;

import java.sql.CallableStatement;
import java.sql.Types;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAHGridQueriedRowEnumerator;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAPageButtonBarBean;
import oracle.apps.fnd.framework.webui.beans.table.OAHGridBean;
import oracle.apps.pa.structure.webui.TaskDetFooterCO;

import oracle.apps.pa.util.server.PAApplicationModuleImpl;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

public class CUXTaskDetFooterCO extends TaskDetFooterCO {
    public CUXTaskDetFooterCO() {
    }


    @Override
    public void processFormRequest(OAPageContext paramOAPageContext, 
                                   OAWebBean paramOAWebBean) {
        if (paramOAPageContext.getParameter("goButton") != null) {
            OAPageButtonBarBean localOAPageButtonBarBean = 
                (OAPageButtonBarBean)paramOAWebBean;

            if (localOAPageButtonBarBean == null) {
                return;
            }
            OAMessageChoiceBean localOAMessageChoiceBean = 
                (OAMessageChoiceBean)localOAPageButtonBarBean.findIndexedChildRecursive("ActionList");

            String str1 = "";
            if (localOAMessageChoiceBean != null) {
                str1 = 
(String)localOAMessageChoiceBean.getValue(paramOAPageContext);
            }

            if ("CREATE_TASK".equals(str1)) {
                this.checkOpMain(paramOAPageContext, paramOAWebBean, 
                                 "TaskHGrid", "CREATETASKS");
            } else {
                if ("MOVE_TASK".equals(str1)) {
                    this.checkOpMain(paramOAPageContext, paramOAWebBean, 
                                     "TaskHGrid", "MOVETASKS");
                }
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
        PAApplicationModuleImpl localPAApplicationModuleImpl = 
            (PAApplicationModuleImpl)paramOAPageContext.getApplicationModule(paramOAWebBean);
        OAViewObject localOAViewObject = 
            (OAViewObject)localPAApplicationModuleImpl.findViewObject("TaskDetailsVO");
        Row localRow = localOAViewObject.getCurrentRow();
        this.checkOperation(paramOAPageContext, paramOAWebBean, operation, 
                            (Number)localRow.getAttribute("ProjectId"), 
                            (Number)localRow.getAttribute("ProjElementId"), 
                            (Number)localRow.getAttribute("ElementVersionId"));

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


    @Override
    public void setActionPopList(OAPageContext paramOAPageContext, 
                                 OAWebBean paramOAWebBean, 
                                 boolean paramBoolean1, boolean paramBoolean2, 
                                 boolean paramBoolean3, boolean paramBoolean4, 
                                 String paramString, boolean paramBoolean5, 
                                 boolean paramBoolean6) {
        OAMessageChoiceBean localOAMessageChoiceBean = 
            (OAMessageChoiceBean)paramOAWebBean.findIndexedChildRecursive("ActionList");

        OAApplicationModule localOAApplicationModule = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);

        if (localOAMessageChoiceBean != null) {
            localOAMessageChoiceBean.setRequiredIcon("no");

            ViewObject localViewObject = 
                localOAApplicationModule.findViewObject("TaskDetActionsVO");
            if (localViewObject == null) {
                localViewObject = 
                        localOAApplicationModule.createViewObject("TaskDetActionsVO", 
                                                                  "oracle.apps.pa.util.server.PALookupsVO");
                if (localViewObject == null)
                    throw new OAException("PA", 
                                          "TaskDetailsFooterCO.setActionPopList:  'TaskDetActionsVO' is NULL");
            }
            StringBuffer localStringBuffer = 
                new StringBuffer(" trunc(sysdate) between start_date_active and nvl(end_date_active, trunc(sysdate))");

            if (!paramBoolean1)
                localStringBuffer.append(" and lookup_code not in ('UPD_PROG')");
            if (!paramBoolean2)
                localStringBuffer.append(" and lookup_code not in ('WF_MONITOR')");
            if (!paramBoolean3)
                localStringBuffer.append(" and lookup_code not in ('WF_RESTART')");
            if (!paramBoolean4)
                localStringBuffer.append(" and lookup_code not in ('WORKING_VER')");
            if (!paramBoolean6) {
                localStringBuffer.append(" and lookup_code not in ('COPY_TASK', 'MOVE_TASK', 'CREATE_TASK', 'CANCEL_TASK')");
            }

            if (!paramBoolean5) {
                localStringBuffer.append(" and lookup_code not in ('CANCEL_TASK')");
            }
            if (!"Y".equals(paramString)) {
                localStringBuffer.append(" and lookup_code not in ('WF_RESTART','COPY_TASK','CREATE_TASK','MOVE_TASK', 'CANCEL_TASK')");
            }

            localStringBuffer.append(" and lookup_code not in ('COPY_TASK')");

            localViewObject.setWhereClause(localStringBuffer.toString());
            localViewObject.setWhereClauseParam(0, "PA_TSK_DET_ACTION");
            localViewObject.setOrderByClause("Meaning");
            localOAMessageChoiceBean.setListViewObject(paramOAPageContext, 
                                                       localViewObject);
        }
    }
}
