/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.LevelProrateAMImpl;
import cux.oracle.apps.per.review.server.LevelProrateVOImpl;
import cux.oracle.apps.per.review.server.ReviewWorkflowAMImpl;

import cux.oracle.apps.per.review.server.ReviewWorkflowLevelVOImpl;

import java.io.Serializable;

import java.util.HashMap;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;

/**
 * Controller for ...
 */
public class LevelProrateCO extends OAControllerImpl {
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
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("LevelProrateVO1");
        if (tableBean == null) {
            MessageToken[] tokens = 
            { new MessageToken("OBJECT_NAME", "LevelProrateVO1") };
            throw new OAException("AK", "FWK_TBX_OBJECT_NOT_FOUND", tokens);
        }
        /*���������lov��readonly���Ե�
    if(pageContext.isFormSubmission())
    {

          OAApplicationModule am = pageContext.getApplicationModule(webBean);
          OAViewObject vo = (OAViewObject)am.findViewObject("LevelProrateVO1");
          OAMessageLovInputBean wfs = (OAMessageLovInputBean)webBean.findChildRecursive("WfStatus");
          wfs.setAttributeValue(READ_ONLY_ATTR,
               new OADataBoundValueViewObject(wfs,"ReadOnlyFlag","LevelProrateVO1"));
          while(vo.hasNext()){
                  Row row = vo.next();
              if(row.getAttribute("SelectIsDisable") != null && row.getAttribute("SelectIsDisable").equals("Y"))
                  row.setAttribute("ReadOnlyFlag", Boolean.TRUE);
               else
                   row.setAttribute("ReadOnlyFlag", Boolean.FALSE);
          }
    }
     */
        tableBean.setSelectionDisabledBindingAttr("SelectIsDisable");
        tableBean.prepareForRendering(pageContext);
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
        LevelProrateAMImpl am = 
            (LevelProrateAMImpl)pageContext.getApplicationModule(webBean);
        //AutoProrate
        if (pageContext.getParameter("AutoProrate") != null) {
            am.autoProrateService();
            LevelProrateVOImpl vo = 
                (LevelProrateVOImpl)am.getLevelProrateVO1();
            vo.executeQuery();
            /*
        String groupName = pageContext.getParameter("SearchGroupName");
        String orgName = pageContext.getParameter("SearchOrgName");
        String PeriodName = pageContext.getParameter("SearchPeriod");
        String AppraisalYear = pageContext.getParameter("SearchYear");
        Serializable parameters[] = {groupName,orgName,PeriodName,AppraisalYear};
        am.invokeMethod("initSearchQuery", parameters);*/

        }
        //detail
        if ("detail".equals(pageContext.getParameter("event"))) {
            String groupId = pageContext.getParameter("AppraisalGroupId");
            String packetNum = 
                pageContext.getParameter("PacketNum"); /*20090919 zs*/
            String periodTypeId = pageContext.getParameter("PeriodTypeId");
            String appraisalYear = pageContext.getParameter("AppraisalYear");
            Serializable parameters[] =
            /*20090919 zs*/ { groupId, packetNum, periodTypeId, 
                              appraisalYear };
            am.invokeMethod("initLevelProrateDetailsQuery", parameters);
            /*      HashMap hm = new HashMap(4);
         hm.put("groupId", groupId);
         pageContext.forwardImmediately();
         try{
            pageContext.sendRedirect("OA.jsp?page=/cux/oracle/apps/per/review/webui/LevelProrateDetailsPG");
         }            catch(Exception e){
                throw new OAException("sendRedirect","LevelProrateDetailsPG");
        }*/
            // Retain AM
            // Show breadcrumbs 
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/LevelProrateDetailsPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_YES, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);

        }
        if (pageContext.getParameter("Save") != null) {
            am.invokeMethod("saveWfStatus");
            LevelProrateVOImpl vo = 
                (LevelProrateVOImpl)am.getLevelProrateVO1();
            vo.executeQuery();
            //pageContext.setForwardURLToCurrentPage(null,true,OAWebBeanConstants.ADD_BREAD_CRUMB_NO,OAWebBeanConstants.IGNORE_MESSAGES);
        }
    }

}
