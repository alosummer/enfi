package cux.oracle.apps.pa.structure.webui;

import java.sql.CallableStatement;
import java.sql.Types;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAExceptionUtils;
import oracle.apps.fnd.framework.webui.OAHGridQueriedRowEnumerator;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAProcessingPage;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageRadioGroupBean;
import oracle.apps.fnd.framework.webui.beans.nav.OABreadCrumbsBean;
import oracle.apps.fnd.framework.webui.beans.table.OAHGridBean;
import oracle.apps.pa.util.StringUtils;
import oracle.apps.pa.util.webui.PAControllerImpl;
import oracle.apps.pa.util.webui.PaDataObjCache;

import oracle.jbo.Row;
import oracle.jbo.Transaction;
import oracle.jbo.domain.Number;

public class CUXMoveTopCO extends PAControllerImpl {
    public static final String RCS_ID = 
        "$Header: MoveTopCO.java 120.1 2006/05/23 20:42:53 sliburd noship $";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header: MoveTopCO.java 120.1 2006/05/23 20:42:53 sliburd noship $", 
                                       "oracle.apps.pa.structure.webui");

    public void processRequest(OAPageContext paramOAPageContext, 
                               OAWebBean paramOAWebBean) {
        super.processRequest(paramOAPageContext, paramOAWebBean);
        paCreateWebBean(paramOAPageContext, paramOAWebBean);
        if ("Y".equals(paramOAPageContext.getParameter("checkErrors"))) {
            paramOAPageContext.removeParameter("checkErrors");
            OAApplicationModule localOAApplicationModule = 
                paramOAPageContext.getRootApplicationModule();
            OADBTransaction localOADBTransaction = 
                (OADBTransaction)localOAApplicationModule.getTransaction();
            OAExceptionUtils.checkErrors(localOADBTransaction);
        }
    }

    public void paCreateWebBean(OAPageContext paramOAPageContext, 
                                OAWebBean paramOAWebBean) {
        OAApplicationModule localOAApplicationModule = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        OAViewObject localOAViewObject = 
            (OAViewObject)localOAApplicationModule.findViewObject("placementVO");
        if (localOAViewObject == null)
            localOAViewObject = 
                    (OAViewObject)localOAApplicationModule.createViewObject("placementVO", 
                                                                            "oracle.apps.pa.util.server.PALookupsVO");
        if (localOAViewObject == null)
            throw new OAException("TaskDetProgOptionsCO.setDerivMethodPoplist: placementVO is NULL");
        localOAViewObject.setWhereClauseParams(null);
        localOAViewObject.setWhereClauseParam(0, "PA_STRUCT_TASK_PLACEMENT");
        OAMessageRadioGroupBean localOAMessageRadioGroupBean = 
            (OAMessageRadioGroupBean)paramOAWebBean.findIndexedChildRecursive("PasteOptions");
        if (localOAMessageRadioGroupBean != null) {
            localOAMessageRadioGroupBean.setListViewObject(paramOAPageContext, 
                                                           localOAViewObject);
            localOAMessageRadioGroupBean.setValue(paramOAPageContext, 
                                                  "PA_PEER_TASK");
            localOAMessageRadioGroupBean.setVAlign("top");
        }
        OAHGridBean localOAHGridBean = 
            (OAHGridBean)paramOAWebBean.findIndexedChildRecursive("TaskHGrid");
        if (localOAHGridBean != null)
            localOAHGridBean.setSelectionDisabledBindingAttr("DisableSelectFlag");
    }

    public void processFormRequest(OAPageContext paramOAPageContext, 
                                   OAWebBean paramOAWebBean) {
        super.processFormRequest(paramOAPageContext, paramOAWebBean);
        String str1 = paramOAPageContext.getParameter("paStructureVersionId");
        OAApplicationModule localOAApplicationModule = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        if (paramOAPageContext.getParameter("Apply") != null) {
            this.checkMove2TaskMain(paramOAPageContext, paramOAWebBean, 
                                    "TaskHGrid");

            PaDataObjCache localPaDataObjCache = 
                (PaDataObjCache)paramOAPageContext.getSessionNamedDataObject("PaDataObjCache");
            if (localPaDataObjCache != null) {
                Object localObject1 = 
                    localPaDataObjCache.getElementIdsBuffer();
                paramOAPageContext.putTransactionTransientValue("PaDataObjCache", 
                                                                localObject1);
                Object localObject2 = localPaDataObjCache.getDelimiter();
                paramOAPageContext.putTransactionTransientValue("paTaskIdDelimiter", 
                                                                localObject2);
            } else {
                Object localObject1 = 
                    new OAException("PA", "PA_PS_NO_TASK_SELECTED", null, 
                                    (byte)2, null);
                ((OAException)localObject1).setApplicationModule(localOAApplicationModule);
                paramOAPageContext.putDialogMessage((OAException)localObject1);
                return;
            }
            Object localObject1 = 
                StringUtils.nullToEmpty(paramOAPageContext.getParameter("PasteOptions"));
            Object localObject2 = "";
            if (((String)localObject1).equals("PA_SUBTASK"))
                localObject2 = "SUB";
            else if (((String)localObject1).equals("PA_PEER_TASK"))
                localObject2 = "PEER";
            paramOAPageContext.putTransactionValue("PA_TASK_OPERATION", 
                                                   "MOVE");
            paramOAPageContext.putTransactionValue("PEER_OR_SUB", 
                                                   (String)localObject2);
            String str2 = 
                StringUtils.nullToEmpty(paramOAPageContext.getParameter("paCalledFrom"));
            if ((str2 == null) || ("".equals(str2)))
                str2 = "WORKPLAN";
            OAHGridBean localOAHGridBean = 
                (OAHGridBean)paramOAWebBean.findIndexedChildRecursive("TaskHGrid");
            localOAHGridBean.clearClientCache(paramOAPageContext);
            int i = 1;
            if (i != 0) {
                OAProcessingPage localOAProcessingPage = 
                    new OAProcessingPage("oracle.apps.pa.structure.webui.StructProcessingCO");
                localOAProcessingPage.setConciseMessage(paramOAPageContext.getMessage("PA", 
                                                                                      "PA_WBS_CHG_MOVE", 
                                                                                      null));
                if (str2.equals("WORKPLAN"))
                    localOAProcessingPage.setDetailedMessage(paramOAPageContext.getMessage("PA", 
                                                                                           "PA_WBS_CHG_PROG", 
                                                                                           null));
                else
                    localOAProcessingPage.setDetailedMessage(paramOAPageContext.getMessage("PA", 
                                                                                           "PA_FBS_CHG_PROG", 
                                                                                           null));
                localOAProcessingPage.setReusePageLayout(true);
                String str3 = 
                    getCallerPage(paramOAPageContext, paramOAWebBean);
                paramOAPageContext.putTransactionValue("PA_STRUCT_PROCESSING_RETURN_PAGE", 
                                                       str3);
                paramOAPageContext.putTransactionValue("PA_STRUCT_PROCESSING_ERROR_PAGE", 
                                                       paramOAPageContext.getCurrentUrl());
                paramOAPageContext.forwardToProcessingPage(localOAProcessingPage);
            } else {
                try {
                    localOAApplicationModule.getTransaction().commit();
                } catch (OAException localOAException) {
                    throw localOAException;
                }
                goBackToCallerPage(paramOAPageContext, paramOAWebBean);
            }
        }
    }

    public void goBackToCallerPage(OAPageContext paramOAPageContext, 
                                   OAWebBean paramOAWebBean) {
        String str = "";
        OABreadCrumbsBean localOABreadCrumbsBean = 
            (OABreadCrumbsBean)paramOAPageContext.getPageLayoutBean().getBreadCrumbsLocator();
        if (localOABreadCrumbsBean != null) {
            int i = localOABreadCrumbsBean.getLinkCount();
            if (i >= 2)
                str = localOABreadCrumbsBean.getLinkDestination(i - 2);
        }
        try {
            paramOAPageContext.sendRedirect(str);
        } catch (Exception localException) {
        }
    }

    public String getCallerPage(OAPageContext paramOAPageContext, 
                                OAWebBean paramOAWebBean) {
        String str = "";
        OABreadCrumbsBean localOABreadCrumbsBean = 
            (OABreadCrumbsBean)paramOAPageContext.getPageLayoutBean().getBreadCrumbsLocator();
        if (localOABreadCrumbsBean != null) {
            int i = localOABreadCrumbsBean.getLinkCount();
            if (i >= 2)
                str = localOABreadCrumbsBean.getLinkDestination(i - 2);
        }
        return str;
    }

    /**
     * 
     * @param paramOAWebBean
     * @param paramOAPageContext
     * @param paramString
     */
    private void checkMove2TaskMain(OAPageContext paramOAPageContext, 
                                    OAWebBean paramOAWebBean, 
                                    String paramString) {
        OAHGridBean localOAHGridBean = 
            (OAHGridBean)paramOAWebBean.findIndexedChildRecursive(paramString);
        OAHGridQueriedRowEnumerator localOAHGridQueriedRowEnumerator = 
            new OAHGridQueriedRowEnumerator(paramOAPageContext, 
                                            localOAHGridBean);
        while (localOAHGridQueriedRowEnumerator.hasMoreElements()) {
            Row localRow = (Row)localOAHGridQueriedRowEnumerator.nextElement();
            if ("Y".equals((String)localRow.getAttribute("SelectFlag"))) {
                this.checkMove2Task(paramOAPageContext, paramOAWebBean, 
                                    (Number)localRow.getAttribute("ProjectId"), 
                                    (Number)localRow.getAttribute("ProjElementId"), 
                                    (Number)localRow.getAttribute("ElementVersionId"));
            }

        }
    }

    /**
     * 判断操作是否合法，则无事，如果不合法，则报出错误警告
     * @param projectId
     * @param elementsId
     * @param elementsVersionId
     * @return
     */
    private void checkMove2Task(OAPageContext paramOAPageContext, 
                                OAWebBean paramOAWebBean, Number projectId, 
                                Number elementsId, Number elementsVersionId) {
        OAApplicationModule localOAApplicationModule = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        String retValue = null;
        OADBTransaction tx = localOAApplicationModule.getOADBTransaction();
        CallableStatement cs = 
            tx.createCallableStatement("begin\n" + "  :1 := cux_pa_wbs_control_pkg.check_move2task(" + 
                                       "                                                 p_project_id => :2,\n" + 
                                       "                                                 p_elements_id => :3,\n" + 
                                       "                                                 p_elements_version_id => :4);\n" + 
                                       "end;", 1);
        try {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setInt(2, projectId.intValue());
            cs.setInt(3, elementsId.intValue());
            cs.setInt(4, elementsVersionId.intValue());
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
