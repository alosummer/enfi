/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.KeyPIAMImpl;

import cux.oracle.apps.per.review.server.ProrateManageAMImpl;

import cux.oracle.apps.per.review.server.ProrateManageVORowImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.bali.share.util.BooleanUtils;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class ProrateManageCO extends OAControllerImpl {
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
        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateProrateTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateProrateTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }
        //add by fl at 20091019
        if (pageContext.getParameter("flag") != null) {
            ProrateManageAMImpl am = 
                (ProrateManageAMImpl)pageContext.getApplicationModule(webBean);
            am.invokeMethod("initQuery");
        }
        //end add by fl at 20091019

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("ProrateManageVO1");
        tableBean.prepareForRendering(pageContext);
        tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);
        OAMessageTextInputBean packetNum = 
            (OAMessageTextInputBean)webBean.findChildRecursive("PacketNum");
        packetNum.setReadOnly(Boolean.TRUE);

        OAMessageChoiceBean msSearchGroupName = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("SearchGroupName");
        msSearchGroupName.setPickListCacheEnabled(false);
        OAMessageChoiceBean msGroupName = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("GroupName");
        msGroupName.setPickListCacheEnabled(false);
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
        ProrateManageAMImpl am = 
            (ProrateManageAMImpl)pageContext.getApplicationModule(webBean);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("ProrateManageVO1");
        // Handle adding a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            OAViewObject vo = am.getProrateManageVO1();
            if (!vo.isPreparedForExecution()) {
                vo.executeQuery();
            }
            //vo.setNamedWhereClauseParam();
            vo.last();

            Row row = vo.createRow();

            int i = 0;
            i = vo.getRowCount();
            if (i == 0) {
                vo.insertRow(row);
            } else {
                vo.insertRowAtRangeIndex(vo.getRangeSize(), row);
            }
            row.setNewRowState(Row.STATUS_INITIALIZED);
            Number prorateId = 
                am.getOADBTransaction().getSequenceValue("CUX_PRORATE_MANAGE_S");
            Number packtNum = 
                am.getOADBTransaction().getSequenceValue("CUX_PRORATE_MANAGE_S1");
            row.setAttribute("ProrateId", prorateId);
            //Add by Wei Yi at 2015/11/03
            //序号由序列产生，不能手动输入
            row.setAttribute("PacketNum", packtNum);
            //end
            vo.setCurrentRow(row);
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            // am.invokeMethod("approve", null);
            System.out.println("Apply");
            am.getOADBTransaction().commit();
            OAException confirmMessage = 
                new OAException("CUX", "CUX_PRORATE_SAVES_OK", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ProrateManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        //if(pageContext.getParameter("customizeSubmitButton") != null)
        if (pageContext.getParameter("Search") != null) {
            //OAApplicationModule am = pageContext.getApplicationModule(webBean);
            System.out.println("search");
            String pOrg = pageContext.getParameter("SearchOrg");
            String flag = pageContext.getParameter("SearchFlag");
            String groupId = pageContext.getParameter("SearchGroupName");
            Boolean executeQuery = BooleanUtils.getBoolean(false);

            Serializable params[] = { pOrg, flag, groupId, executeQuery };
            Class[] paramTypes = 
            { String.class, String.class, String.class, Boolean.class };
            am.invokeMethod("initQuery", params, paramTypes);

            OATableBean table = 
                (OATableBean)webBean.findChildRecursive("ProrateManageVO1");
            table.queryData(pageContext, false);
        }


        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateProrateTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ProrateManagePG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle delete .edit by fl at 20091019
        if ("deleteProrate".equals(pageContext.getParameter("event"))) {
            String prorateId = pageContext.getParameter("ProrateId");
            OAException mainMessage = 
                new OAException("CUX", "CUX_PRORATE_DELETE_ASK_CONF", null, 
                                OAException.WARNING, null);
            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("DeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(1);
            formParams.put("ProrateId", prorateId);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String result = "";
            String prorateId = pageContext.getParameter("ProrateId");
            Serializable[] params = { prorateId };
            //remark by fl at 20091019
            // result = "Y";//am.invokeMethod("canDelProrate", params).toString(); 

            //add by fl at 20091019
            result = am.invokeMethod("canDelProrate", params).toString();
            if (result.equals("Y")) {
                // OAViewObject vo = null;
                // vo = am.getProrateManageVO1();
                // vo.executeQuery();
                // Object obj[] = new Object[1];
                // obj[0] = prorateId;
                //  Key key = new Key(obj);
                //  Row row = vo.getRow(key);
                //  if(row != null){
                //     row.remove();
                //     am.getOADBTransaction().commit();
                // }  
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_PRORATE_DELETE", null, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);

            } else {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_PRORATE_DELETE_ERRO", null, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }

            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ProrateManagePG&flag=1", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }
    }

}
