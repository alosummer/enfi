/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

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

import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class EmpUpdateCO extends OAControllerImpl {
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
        // If isBackNavigationFired = false, we're here after a valid navigation
        // (the user selected the Create Empoyee button) and we should proceed 
        // normally and initialize a new employee.

        if (!pageContext.isBackNavigationFired(false)) {
            // We indicate that we are starting the create transaction (this 
            // is used to ensure correct Back button behavior).
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "UpdateTxn");

            // This test ensures that we don't try to create a new employee if
            // we had a JVM failover, or if a recyled application module
            // is activated after passivation. If this things happen, BC4J will
            // be able to find the row that you created so the user can resume
            // work.

            if (!pageContext.isFormSubmission()) {
                OAApplicationModule am = 
                    pageContext.getApplicationModule(webBean);
                String personId = pageContext.getParameter("personId");
                Serializable params[] = { personId };
                am.invokeMethod("CreateChange", params);
            }
        } else {
            if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                   "UpdateTxn", 
                                                                   true)) {
                // We got here through some use of the browser "Back" button, so we    
                // want to display a stale data error and disallow access to the page.
                // If this were a real application, we would probably display a more
                // context-specific message telling the user she can't use the browser
                // "Back" button and the "Create" page. Instead, we wanted to illustrate
                // how to display the Applications standard NAVIGATION ERROR message.

                OADialogPage dialogPage = new OADialogPage(NAVIGATION_ERROR);
                pageContext.redirectToDialogPage(dialogPage);
            }
        }

        OAMessageChoiceBean msChangeGroup = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("ChangeGroup");
        msChangeGroup.setPickListCacheEnabled(false);
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

        // Pressing the "Apply" button means the transaction should be validated
        // and committed.
        if (pageContext.getParameter("Apply") != null) {
            // Generally in the tutorial application and the labs, we've illustrated
            // all BC4J interaction on the server (except for the AMs, of course). Here,
            // we're dealing with the VO directly so the comments about the reasons
            // why we're obtaining values from the VO and not the request make sense
            // in context. 
            OAViewObject vo = (OAViewObject)am.findViewObject("EmpChangeVO1");

            // Note that we have to get this value from the VO because the EO will
            // assemble it during its validation cycle.
            Number personId = 
                (Number)vo.getCurrentRow().getAttribute("PersonId");
            String changeGroup = 
                (String)vo.getCurrentRow().getAttribute("ChangeGroup");
            Number groupId = 
                (Number)vo.getCurrentRow().getAttribute("GroupId");
            String changeOrg = 
                (String)vo.getCurrentRow().getAttribute("ChangeOrg");
            Number orgId = (Number)vo.getCurrentRow().getAttribute("OrgId");
            String changeSupervisor = 
                (String)vo.getCurrentRow().getAttribute("SupervisorName");
            Number supervisorId = 
                (Number)vo.getCurrentRow().getAttribute("SupervisorId");
            String changePosition = 
                (String)vo.getCurrentRow().getAttribute("ChangePosition");
            String positionId = 
                (String)vo.getCurrentRow().getAttribute("Attribute2");
            System.out.println("changePosition+positionId=" + changePosition + 
                               "+" + positionId);

            //  Number employeeNumber = (Number)vo.getCurrentRow().getAttribute("EmployeeId");
            //System.out.println(personId);
            // System.out.println(groupId);
            //System.out.println(orgId);   
            //System.out.println(supervisorId); 
            Serializable[] params = 
            { personId.toString(), (groupId == null ? null : 
                                    groupId.toString()), 
              (orgId == null ? null : orgId.toString()), 
              (supervisorId == null ? null : supervisorId.toString()), 
              positionId };
            String result = 
                am.invokeMethod("validateChangeEmp", params).toString();
            String message;
            byte msgType = OAException.CONFIRMATION;
            OAException confirmMessage = null;
            if (result != null && result.equals("N")) {
                am.getOADBTransaction().commit();
                confirmMessage = 
                        new OAException("CUX", "CUX_UPDATE_OK", null, msgType, 
                                        null);
            } else if (result != null && result.equals("Y")) {
                am.getOADBTransaction().rollback();
                msgType = OAException.WARNING;
                confirmMessage = 
                        new OAException("CUX", "CUX_UPDATE_WARNIG", null, 
                                        msgType, null);

            } else {
                am.getOADBTransaction().rollback();
                msgType = OAException.ERROR;
                confirmMessage = 
                        new OAException("CUX", "CUX_SYSDATE_EXCEPTION_ERROR", 
                                        null, msgType, null);
            }
            pageContext.putDialogMessage(confirmMessage);

            // retain AM
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/EmpManagePG&flag=1", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        } else if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollbackUpdateEmp");
            // Indicate that the Create transaction is complete.
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "empUpdateTxn");

            // retain AM
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/EmpManagePG&flag=1", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }

        else if (pageContext.getParameter("Delete") != null) {
            // String personId = pageContext.getParameter("personId");
            // Serializable params[] = {personId};
            OAViewObject vo = (OAViewObject)am.findViewObject("EmpChangeVO1");
            Number Id = (Number)vo.getCurrentRow().getAttribute("PersonId");
            System.out.println(Id);
            String personId = Id.toString();
            MessageToken[] tokens = 
            { new MessageToken("PERSON_ID", personId) };
            OAException mainMessage = 
                new OAException("CUX", "CUX_CHANGE_DELETE_WARN", tokens);
            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");
            // Always use Message Dictionary for any Strings you want to display.
            String yes = pageContext.getMessage("AK", "FWK_TBX_T_YES", null);
            String no = pageContext.getMessage("AK", "FWK_TBX_T_NO", null);
            // We set this value so the code that handles this button press is 
            // descriptive.
            dialogPage.setOkButtonItemName("DeleteYesButton");

            // The following configures the Yes/No buttons to be submit buttons,
            // and makes sure that we handle the form submit in the originating
            // page (the "Employee" summary) so we can handle the "Yes"
            // button selection in this controller.
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);

            // Now set our Yes/No labels instead of the default OK/Cancel.
            dialogPage.setOkButtonLabel(yes);
            dialogPage.setNoButtonLabel(no);

            // We need to keep hold of the employeeNumber, and the OADialogPage gives us a
            // convenient means of doing this. Note that the use of the Hashtable is
            // really more appropriate for passing multiple parameters, but we've used
            // it here for illustration purposes. See the OADialogPage javadoc for an
            // alternative when dealing with a single parameter.
            java.util.Hashtable formParams = new java.util.Hashtable(1);
            formParams.put("personId", personId);
            System.out.println("formParams " + personId);
            dialogPage.setFormParameters(formParams);

            pageContext.redirectToDialogPage(dialogPage);
        }

        else if (pageContext.getParameter("DeleteYesButton") != null) {

            // User has confirmed that she wants to delete this employee.
            // Invoke a method on the AM to set the current row in the VO and 
            // call remove() on this row. 
            OAViewObject vo = (OAViewObject)am.findViewObject("EmpChangeVO1");
            Number Id = (Number)vo.getCurrentRow().getAttribute("PersonId");
            String personId = Id.toString();

            Serializable[] parameters = { personId };
            System.out.println("delete" + personId);

            am.invokeMethod("DeleteChange", parameters);

            // Now, redisplay the page with a confirmation message at the top. Note
            // that the deleteEmployee() method in the AM commits, and our code
            // won't get this far if any exceptions are thrown.

            MessageToken[] tokens = 
            { new MessageToken("PERSON_ID", personId) };
            OAException message = 
                new OAException("CUX", "CUX_CHANGE_DELETE_CONFIRM", tokens, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(message); // retain AM
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/EmpUpdatePG&flag=1", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);


        }

    }

}
