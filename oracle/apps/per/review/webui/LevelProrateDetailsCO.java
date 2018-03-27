/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.LevelProrateAMImpl;

import cux.oracle.apps.per.review.server.LevelProrateVOImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

/**
 * Controller for ...
 */
public class LevelProrateDetailsCO extends OAControllerImpl {
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

        /*20090909 zs*/
        /*���Ʊ��水ť*/
        String wfStatusCode = pageContext.getParameter("wfStatusCode");
        if (wfStatusCode == null || wfStatusCode.equals("")) {
            wfStatusCode = (String)pageContext.getSessionValue("wfStatusCode");
        }
        pageContext.putSessionValue("wfStatusCode", wfStatusCode);
        OASubmitButtonBean saveBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Save");
        if (wfStatusCode != null && 
            (wfStatusCode.equals("COMPLETED") || wfStatusCode.equals("PUBLISHED") || 
             wfStatusCode.equals("FREEZED"))) {
            saveBtn.setRendered(true);
        } else {
            saveBtn.setRendered(false);
        }
        /*20110301 fl ���Ƶȼ�����֮���ܵ���ȼ�(���յȼ��лҵ�)*/
        OAMessageChoiceBean fl = 
            (OAMessageChoiceBean)webBean.findChildRecursive("FinalLevel");
        if (wfStatusCode != null && (wfStatusCode.equals("PUBLISHED"))) {
            fl.setReadOnly(true);
        }
        /*����õ�autoProrateFlag*/
        String uiLevelA = pageContext.getParameter("uiLevelA");
        if (uiLevelA == null || uiLevelA.equals("")) {
            uiLevelA = (String)pageContext.getSessionValue("uiLevelA");
        }
        pageContext.putSessionValue("uiLevelA", uiLevelA);

        String uiLevelB = pageContext.getParameter("uiLevelB");
        if (uiLevelB == null || uiLevelB.equals("")) {
            uiLevelB = (String)pageContext.getSessionValue("uiLevelB");
        }
        pageContext.putSessionValue("uiLevelB", uiLevelB);

        String uiLevelC = pageContext.getParameter("uiLevelC");
        if (uiLevelC == null || uiLevelC.equals("")) {
            uiLevelC = (String)pageContext.getSessionValue("uiLevelC");
        }
        pageContext.putSessionValue("uiLevelC", uiLevelC);

        String uiLevelD = pageContext.getParameter("uiLevelD");
        if (uiLevelD == null || uiLevelD.equals("")) {
            uiLevelD = (String)pageContext.getSessionValue("uiLevelD");
        }
        pageContext.putSessionValue("uiLevelD", uiLevelD);

        String uiLevelE = pageContext.getParameter("uiLevelE");
        if (uiLevelE == null || uiLevelE.equals("")) {
            uiLevelE = (String)pageContext.getSessionValue("uiLevelE");
        }
        pageContext.putSessionValue("uiLevelE", uiLevelE);

        String autoProrateFlag = "Y";
        if (Double.parseDouble(uiLevelA) + Double.parseDouble(uiLevelB) + 
            Double.parseDouble(uiLevelC) + Double.parseDouble(uiLevelD) + 
            Double.parseDouble(uiLevelE) == 0) {
            /*2009-12-25 zs*/
            //autoProrateFlag = "N";
            autoProrateFlag = "Y";
            /*2009-12-25 zs*/
        }
        pageContext.putSessionValue("autoProrateFlag", autoProrateFlag);
        /*��ȡ��Ĭ�ϱ���ֲ��������Ҫ�Ĳ���*/
        String groupId = pageContext.getParameter("AppraisalGroupId");
        if (groupId == null || groupId.equals("")) {
            groupId = (String)pageContext.getSessionValue("groupId");
        }
        pageContext.putSessionValue("groupId", groupId);
        /*20090919 zs*/
        String packetNum = pageContext.getParameter("PacketNum");
        if (packetNum == null || packetNum.equals("")) {
            packetNum = (String)pageContext.getSessionValue("packetNum");
        }
        pageContext.putSessionValue("packetNum", packetNum);
        /*20090919 zs*/
        String perNum = pageContext.getParameter("perNum");
        if (perNum == null || perNum.equals("")) {
            perNum = (String)pageContext.getSessionValue("perNum");
        }
        pageContext.putSessionValue("perNum", perNum);
        //initLevelProrateDetailsQuery
        /* LevelProrateAMImpl am = (LevelProrateAMImpl)pageContext.getApplicationModule(webBean);
      String periodTypeId = pageContext.getParameter("PeriodTypeId");
      String appraisalYear = pageContext.getParameter("AppraisalYear");
      Serializable parameters[] = {groupId,orgId,periodTypeId,appraisalYear};
      am.invokeMethod("initLevelProrateDetailsQuery", parameters);
      */
        /*20090909 zs*/
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
        if (pageContext.getParameter("Save") != null) {
            /*20090909 zs*/
            String autoProrateFlag = 
                (String)pageContext.getSessionValue("autoProrateFlag");
            String groupId = (String)pageContext.getSessionValue("groupId");
            /*20090919 zs*/
            //String orgId = (String)pageContext.getSessionValue("orgId");
            String packetNum = 
                (String)pageContext.getSessionValue("packetNum");
            /*20090919 zs*/
            String perNum = (String)pageContext.getSessionValue("perNum");
            //int perProrateNum = Integer.parseInt(perNum);
            Serializable params[] = 
            { autoProrateFlag, groupId, packetNum, perNum };
            am.invokeMethod("saveFinalLevel", params);
            /*20090909 zs*/
            //am.invokeMethod("apply");
        }
        if (pageContext.getParameter("Return") != null) {
            /*LevelProrateVOImpl vo = (LevelProrateVOImpl)am.getLevelProrateVO1();
          vo.setWhereClauseParams(null); // Always reset
          vo.executeQuery();
          pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/LevelProratePG",
                                             null,
                                            OAWebBeanConstants.KEEP_MENU_CONTEXT,
                                            null,
                                            null,
                                            true, // retain AM
                                            "N");*/
            try {
                pageContext.sendRedirect("OA.jsp?page=/cux/oracle/apps/per/review/webui/LevelProratePG");
            } catch (Exception e) {
                ;
            }

        }
        if ("detail".equals(pageContext.getParameter("event"))) {
            /*  pageContext.setForwardURL( "OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalEmpPG"
                                   , null
                                   , OAWebBeanConstants.KEEP_MENU_CONTEXT
                                   , null
                                   , null
                                   , true // Retain AM
                                   , OAWebBeanConstants.ADD_BREAD_CRUMB_YES // Show breadcrumbs
                                   , OAWebBeanConstants.IGNORE_MESSAGES
                                   );
          */
            String appraisalId = pageContext.getParameter("appraisalId");
            pageContext.putSessionValue("appraisalId", appraisalId);
            //zs
            String phaseId = pageContext.getParameter("phaseId");
            pageContext.putSessionValue("phaseId", phaseId);
            String statusId = pageContext.getParameter("statusId");
            pageContext.putSessionValue("statusId", statusId);
            //zs
            /*20090926 zs*/
            String role = "admin";
            pageContext.putSessionValue("role", role);
            /*20090926 zs*/
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG", 
                                           null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, true, "N");
        }
    }

}
