/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.PerReviewAMImpl;
import cux.oracle.apps.per.review.server.PerReviewGroupVOImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAQueryUtils;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.bali.share.util.BooleanUtils;

/**
 * Controller for ...
 */
public class PerReviewGroupCO extends OAControllerImpl {
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
        PerReviewAMImpl am = 
            (PerReviewAMImpl)pageContext.getApplicationModule(webBean);
        PerReviewGroupVOImpl vo = am.getPerReviewGroupVO1();
        if (pageContext.getParameter("refresh") != null)
            vo.initQuery();

        OAMessageChoiceBean msOrgName = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("SearchOrgName");
        msOrgName.setPickListCacheEnabled(false);

        /*
    String groupName = (String)pageContext.getSessionValue("groupName");
    String orgId = (String)pageContext.getSessionValue("orgId");
    Serializable parameters[] = {groupName,orgId};
    am.invokeMethod("initQuery", parameters);
    */
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
        if (pageContext.getParameter("CreateReviewGroup") != 
            null) { // Retain AM
            // Show breadcrumbs 
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/CreateReviewGroupPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);

        } else if ("updateGrpRange".equals(pageContext.getParameter("event"))) { // Retain AM
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/PerGroupRangePG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);

        } else if ("delReviewGrp".equals(pageContext.getParameter("event"))) {
            String groupId = pageContext.getParameter("groupId");
            String groupName = pageContext.getParameter("groupName");
            MessageToken tokens[] = 
            { new MessageToken("GROUP_NAME", groupName) };
            OAException mainMessage = 
                new OAException("CUX", "CUX_DEL_REVIEW_GROUP_WARN", tokens, 
                                OAException.WARNING, null);

            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            dialogPage.setOkButtonItemName("DeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            java.util.Hashtable formParams = new java.util.Hashtable(2);
            formParams.put("groupId", groupId);
            formParams.put("groupName", groupName);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String result = "";
            String groupId = pageContext.getParameter("groupId");
            String groupName = pageContext.getParameter("groupName");
            PerReviewAMImpl am = 
                (PerReviewAMImpl)pageContext.getApplicationModule(webBean);
            result = am.delReviewGrp(groupId);
            MessageToken tokens[] = { new MessageToken("GROUP", groupName) };
            OAException confirmMessage = null;
            if (result != null && 
                result.equals("Y")) // Y means delete successfully 
            {
                confirmMessage = 
                        new OAException("CUX", "CUX_DEL_GRP_CONF", tokens, 
                                        OAException.CONFIRMATION, null);
            } else if (result != null && 
                       result.equals("E")) { // E means exception happended
                confirmMessage = 
                        new OAException("CUX", "CUX_DEL_REVIEW_GROUP_EXCEPTION", 
                                        tokens, OAException.ERROR, null);
            } else if (result != null && 
                       result.equals("N")) { // N means cannot be deleted
                confirmMessage = 
                        new OAException("CUX", "CUX_DEL_REVIEW_GROUP_ERR", 
                                        tokens, OAException.ERROR, null);
            }
            pageContext.putDialogMessage(confirmMessage);

            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/PerReviewGroupPG&refresh=Y", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        } else if ("updateReviewGrp".equals(pageContext.getParameter("event"))) { // Retain AM
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/UpdateReviewGroupPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);

        } else if (pageContext.getParameter("customizeSubmitButton") != null) {
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            OAQueryBean queryBean = 
                (OAQueryBean)webBean.findChildRecursive("GroupSearchRN");
            String currentPanel = queryBean.getCurrentSearchPanel();
            // Handle the "Go" button click on the simple search panel
            if (SEARCH.equals(currentPanel) && 
                queryBean.getGoButtonName() != null) {
                // retrieve your item and process it here.
                String groupName = pageContext.getParameter("SearchGroupName");
                String orgId = pageContext.getParameter("SearchOrgName");
                String jobId = pageContext.getParameter("SearchJobId");
                //Serializable parameters[] = {groupName, orgId, jobId};
                //am.invokeMethod("initQuery", parameters);
            }
        }

        if (pageContext.getParameter("Search") != null) {

            OAQueryUtils.checkSelectiveSearchCriteria(pageContext, webBean);

            // Get the user's search criteria from the request.
            String groupName = pageContext.getParameter("SearchGroupName");
            String orgId = pageContext.getParameter("SearchOrgName");
            String jobId = pageContext.getParameter("SearchJobId");

            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            Boolean executeQuery = BooleanUtils.getBoolean(false);
            Serializable[] parameters = 
            { groupName, orgId, jobId, executeQuery };

            Class[] paramTypes = 
            { String.class, String.class, String.class, Boolean.class };
            am.invokeMethod("initQuery", parameters, paramTypes);

            OATableBean table = 
                (OATableBean)webBean.findChildRecursive("PerReviewGroupVO1");
            table.queryData(pageContext, false);
        }
    }
}
