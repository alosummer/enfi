/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.TmplDetailVORowImpl;

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
public class AppraisalChooseTmplCO extends OAControllerImpl {
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
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        am.invokeMethod("queryTemplates");
        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "ChooseTmplTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "ChooseTmplTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

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
        if (pageContext.getParameter("ApplyTmpl") != null) {
            OAViewObject vo = (OAViewObject)am.findViewObject("TmplDetailVO1");
            int fetchedRowCount = vo.getFetchedRowCount();
            RowSetIterator iter = vo.createRowSetIterator("selectIter");
            TmplDetailVORowImpl row = null;
            Number tmplId = null;
            boolean tmplSelected = false;
            if (fetchedRowCount > 0) {
                iter.setRangeStart(0);
                iter.setRangeSize(fetchedRowCount);
                for (int i = 0; i < fetchedRowCount; i++) {
                    row = (TmplDetailVORowImpl)iter.getRowAtRangeIndex(i);
                    if (row.getAttribute("Selected") != null && 
                        row.getAttribute("Selected").equals("Y")) {
                        tmplId = row.getTmplId();
                        //System.out.println("Selected Template ID = "+ tmplId);
                        tmplSelected = true;
                        break;
                    }
                }
            }

            if (!tmplSelected)
                throw new OAException("CUX", "CUX_APPLY_TMPL_WARNING", null, 
                                      OAException.WARNING, null);
            else {
                String appraisalId = pageContext.getParameter("AppraisalId");
                if (appraisalId == null) {
                    appraisalId = 
                            (String)pageContext.getSessionValue("appraisalId");
                    System.out.println("appraisalId : " + appraisalId + 
                                       " tmplId " + tmplId);
                }
                String result = "";
                if (appraisalId != null && tmplId != null) {
                    System.out.println("start ");
                    Serializable params[] = { tmplId.toString(), appraisalId };
                    result = 
                            (String)am.invokeMethod("applyContractTemplate", params);
                    System.out.println("result");
                }
                if (result != null && result.equals("Y"))
                    throw new OAException("CUX", "CUX_APPLY_TMPL_CONF", null, 
                                          OAException.CONFIRMATION, null);
                else
                    throw new OAException("CUX", "CUX_APPLY_TMPL_ERROR", null, 
                                          OAException.ERROR, null);
            }
        }

        // Return to contract
        if (pageContext.getParameter("Return") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }
    }

}
