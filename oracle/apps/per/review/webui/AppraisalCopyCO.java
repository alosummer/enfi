/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.AppraisalCopyVORowImpl;
import cux.oracle.apps.per.review.server.TemplateVORowImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class AppraisalCopyCO extends OAControllerImpl {
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
            TransactionUnitHelper.startTransactionUnit(pageContext, "CopyTxn");

            // This test ensures that we don't try to create a new employee if
            // we had a JVM failover, or if a recyled application module
            // is activated after passivation. If this things happen, BC4J will
            // be able to find the row that you created so the user can resume
            // work.

            if (!pageContext.isFormSubmission()) {
                OAApplicationModule am = 
                    pageContext.getApplicationModule(webBean);
                String personId = pageContext.getParameter("personId");
                String nappraisalId = pageContext.getParameter("appraisalId");
                Serializable params[] = { personId, nappraisalId };
                // System.out.println(personId);
                am.invokeMethod("initCopy", params);
            }
        } else {
            if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                   "CopyTxn", 
                                                                   true)) {
                OADialogPage dialogPage = new OADialogPage(NAVIGATION_ERROR);
                pageContext.redirectToDialogPage(dialogPage);
            }
        }
        String appraisalId = pageContext.getParameter("appraisalId");
        System.out.println("appraisalId ..." + appraisalId);
        if (appraisalId != null) {
            pageContext.putSessionValue("appraisalId", appraisalId);
        } else
            appraisalId = (String)pageContext.getSessionValue("appraisalId");
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

        // Return to contract
        if (pageContext.getParameter("Return") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("AppraisalCopyVO1");
            int fetchedRowCount = vo.getFetchedRowCount();
            RowSetIterator iter = vo.createRowSetIterator("selectIter");
            AppraisalCopyVORowImpl row = null;
            String nappraisalId = 
                (String)pageContext.getSessionValue("appraisalId");
            Number oappraisalId = null;
            boolean appraisalSelected = false;
            if (fetchedRowCount > 0) {
                iter.setRangeStart(0);
                iter.setRangeSize(fetchedRowCount);
                for (int i = 0; i < fetchedRowCount; i++) {
                    row = (AppraisalCopyVORowImpl)iter.getRowAtRangeIndex(i);
                    if (row.getAttribute("Selected") != null && 
                        row.getAttribute("Selected").equals("Y")) {
                        oappraisalId = row.getAppraisalId();
                        appraisalSelected = true;
                        break;
                    }
                }
            }

            if (!appraisalSelected)
                throw new OAException("CUX", "CUX_COPY_CONTRACT_WARNING", null, 
                                      OAException.WARNING, null);
            else {
                String result = "";
                if (oappraisalId != null) {
                    System.out.println("oappraisalId" + oappraisalId);
                    System.out.println("nappraisalId" + nappraisalId);
                    Serializable params[] = 
                    { oappraisalId.toString(), nappraisalId };
                    result = (String)am.invokeMethod("processCopy", params);
                }

                OAException confirmMessage = null;
                if (result != null && result.equals("Y"))
                    confirmMessage = 
                            new OAException("CUX", "CUX_COPY_CONTRACT_CONF", 
                                            null, OAException.CONFIRMATION, 
                                            null);
                else
                    confirmMessage = 
                            new OAException("CUX", "CUX_COPY_CONTRACT_ERROR", 
                                            null, OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }


        }

    }

}
