/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.ContractKPIDetailVOImpl;

import cux.oracle.apps.per.review.server.ContractKPIDetailVORowImpl;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

/**
 * Controller for ...
 */
public class ContractKPIChildCO extends OAControllerImpl {
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
        //added by dl 2009.12.14 �ڼ�Ч��ͬ�����ָ����ϸ���涼������ڽ����Ҳҳ�棬����Ҫ��
        //�����, �����������ڷ���
        String sourcePage = null;
        if (pageContext.getParameter("sourcePage") != null) {
            sourcePage = pageContext.getParameter("sourcePage").toString();
            pageContext.putSessionValue("sourcePage", sourcePage);
        } else {
            sourcePage = (String)pageContext.getSessionValue("sourcePage");
        }
        //��ȡstatusId   
        String statusId = (String)pageContext.getSessionValue("statusId");
        //added end;
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("AppraisalKPIVO1");

        //tableBean.prepareForRendering(pageContext); marked by dl
        //tableBean.setInsertable(true);
        tableBean.setAutoInsertion(false);
        //tableBean.setNumberOfRowsDisplayed(300);
        String role = (String)pageContext.getSessionValue("role");
        String canChange = null;
        String appraisalId = pageContext.getParameter("appraisalId");
        if (appraisalId != null) {
            Serializable params[] = { appraisalId };
            //�жϵ�ǰ���Ƿ�ɱ༭��Ч��ͬ
            canChange = (String)am.invokeMethod("isCurrentPerformer", params);
            OASubmitButtonBean applyBtn = 
                (OASubmitButtonBean)webBean.findChildRecursive("Apply");
            /*20090930 zs  add Save*/
            //OASubmitButtonBean saveBtn = (OASubmitButtonBean)webBean.findChildRecursive("Save");
            /*20090930 zs*/
            if (canChange != null && canChange.equals("Y")) {
                applyBtn.setRendered(true);
                //saveBtn.setRendered(true);/*20090930 zs  add Save*/
                tableBean.setInsertable(true);
            } else {
                applyBtn.setRendered(false);
                //saveBtn.setRendered(false);/*20090930 zs  add Save*/
                tableBean.setInsertable(false);
            }

        }

        String contractId = pageContext.getParameter("contractId");
        String kpiClass = pageContext.getParameter("kpiClass");
        if (contractId != null) {
            Serializable params[] = { contractId };
            pageContext.putSessionValue("parentContractId", contractId);
            am.invokeMethod("queryChildContracts", params);
        }

        /*20090908 zs*/
        String kpiChildWeight = pageContext.getParameter("kpiChildWeight");
        if (kpiChildWeight != null) {
            pageContext.putSessionValue("kpiChildWeight", kpiChildWeight);
        } else {
            kpiChildWeight = 
                    (String)pageContext.getSessionValue("kpiChildWeight");
        }
        String kpiStdAddWeightMethod = "";
        if (kpiClass != null && !kpiClass.equals("")) {
            Serializable params[] = { kpiClass };
            kpiStdAddWeightMethod = 
                    (String)am.invokeMethod("getKpiStdAddWeightMethod", 
                                            params);
        }
        if (kpiStdAddWeightMethod == null || 
            kpiStdAddWeightMethod.equals("")) {
            kpiStdAddWeightMethod = 
                    (String)pageContext.getSessionValue("kpiStdAddWeightMethod");
        }
        pageContext.putSessionValue("kpiStdAddWeightMethod", 
                                    kpiStdAddWeightMethod);
        /*20090908 zs*/

        //zs
        String phaseId = pageContext.getParameter("phaseId");
        if (phaseId != null) {
            pageContext.putSessionValue("phaseId", phaseId);
        } else {
            phaseId = (String)pageContext.getSessionValue("phaseId");
        }

        //fch 090907
        OAMessageTextInputBean selfEvalValue = 
            (OAMessageTextInputBean)webBean.findChildRecursive("SelfEvalValue");
        OAMessageTextInputBean scoreValue = 
            (OAMessageTextInputBean)webBean.findChildRecursive("ScoreValue");
        OAMessageTextInputBean actualValue = 
            (OAMessageTextInputBean)webBean.findChildRecursive("ActualValue");
        //fch 090907

        if (phaseId.equals("REVIEW")) { //OAMessageLovInputBean
            OAWebBean kpiName_tmp = 
                (OAWebBean)webBean.findChildRecursive("KpiName");
            String tmp = kpiName_tmp.getLocalName();
            if (tmp.equals("messageLovInput")) {
                OAMessageLovInputBean kpiName = 
                    (OAMessageLovInputBean)webBean.findChildRecursive("KpiName");
                kpiName.setReadOnly(true);
            } else {
                OAMessageTextInputBean kpiName_tx = 
                    (OAMessageTextInputBean)webBean.findChildRecursive("KpiName");
                kpiName_tx.setReadOnly(true);
            }

            OAMessageChoiceBean kpiArea = 
                (OAMessageChoiceBean)webBean.findChildRecursive("KpiArea");
            kpiArea.setReadOnly(true);

            OAMessageTextInputBean kpiDesc = 
                (OAMessageTextInputBean)webBean.findChildRecursive("KpiDesc");
            kpiDesc.setReadOnly(true);

            OAMessageTextInputBean weight = 
                (OAMessageTextInputBean)webBean.findChildRecursive("Weight");
            weight.setReadOnly(true);

            OAMessageTextInputBean targetValue = 
                (OAMessageTextInputBean)webBean.findChildRecursive("TargetValue");
            targetValue.setReadOnly(true);

            OAMessageTextInputBean minimumValue = 
                (OAMessageTextInputBean)webBean.findChildRecursive("MinimumValue");
            minimumValue.setReadOnly(true);

            OAMessageChoiceBean kpiDataSource = 
                (OAMessageChoiceBean)webBean.findChildRecursive("KpiDataSource");
            kpiDataSource.setReadOnly(true);

            //����
            //OAImageBean deleteEnabled = (OAImageBean)webBean.findChildRecursive("DeleteEnabled");
            //deleteEnabled.;
            tableBean.setInsertable(false);

            //fch 20090904
            if (role != null) {
                if (role.equals("manager")) {
                    selfEvalValue.setReadOnly(true);
                } else if (role.equals("selfservice")) {
                    selfEvalValue.setReadOnly(false);
                    //added by dl 2009.12.05 ��ס�÷�
                    if (statusId != null && !statusId.equals("COMPLETED") && 
                        !statusId.equals("PUBLISHED") && 
                        !statusId.equals("FREEZED")) {
                        scoreValue.setRendered(false);
                    }
                    //end added
                }
            }
            //fch 20090904
        } else if (phaseId.equals("GOAL") || phaseId.equals("UPDATE")) {
            actualValue.setReadOnly(true);
            scoreValue.setReadOnly(true);
            scoreValue.setMessage(null);
        }

        //�����������ֵĿɲ�����
        if (phaseId != null && phaseId.equals("REVIEW")) {
            selfEvalValue.setRendered(true);
        } else {
            selfEvalValue.setRendered(false);
        }
        //add by dl 2009.12.14 ���ڱ༭����ʱ�򲻿��Ա༭��ȫ��ֻ�� 
        if (canChange != null && !canChange.equals("Y")) {
            actualValue.setReadOnly(true);
            scoreValue.setReadOnly(true);
            selfEvalValue.setReadOnly(true);
        }
        //end add

        /*20090930 zs add Save*/
        /* if(phaseId.equals("GOAL")||phaseId.equals("UPDATE")){
           ;
       }else{
           OASubmitButtonBean saveBtn = (OASubmitButtonBean)webBean.findChildRecursive("Save");
           saveBtn.setRendered(false);
       }*/
        /*20090930 zs add Save*/
        //
        /*
       OAViewObject vo = (OAViewObject)am.findViewObject("ContractKPIDetailVO1");
       while(vo.hasNext()){
           ContractKPIDetailVORowImpl row = (ContractKPIDetailVORowImpl)vo.next();
           String showScoreFlag = row.getShowScoreFlag();
           if(showScoreFlag != null){
              if(showScoreFlag.equals("Y")){
                  row.setAttribute("ReadOnlyFlag", Boolean.FALSE);
                  //selfEvalValue.setViewAttributeName("SelfEvalValue");
                  //scoreValue.setViewAttributeName("ScoreValue");
              }else if(showScoreFlag.equals("ROLE")){
                  if(role != null){
                    if(role.equals("manager")){
                        //selfEvalValue.setReadOnly(true);
                        row.setAttribute("ReadOnlyFlag", Boolean.TRUE);
                    }else if(role.equals("selfservice")){
                        //selfEvalValue.setReadOnly(false);
                        row.setAttribute("ReadOnlyFlag", Boolean.FALSE);
                    }
                  }
              }else if(showScoreFlag.equals("N")){
                //scoreValue.setMessage(null);
                //selfEvalValue.setMessage(null);
                row.setAttribute("ReadOnlyFlag", Boolean.TRUE);
              }
           }else{
              scoreValue.setMessage(null);
              selfEvalValue.setMessage(null);
           }

       }
       */
        //fch 20090904
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
        String role = (String)pageContext.getSessionValue("role");
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("AppraisalKPIVO1");
        //added by dl 2009.12.14 ��ȡ��һ��Դҳ��
        String sourcePage = null;
        if (pageContext.getSessionValue("sourcePage") != null)
            sourcePage = (String)pageContext.getSessionValue("sourcePage");
        if (sourcePage == null || sourcePage.equals("AppraisalContractPG"))
            sourcePage = "AppraisalContractPG&from=report";
        System.out.println("sourcePage ��" + sourcePage);

        // Handle add a new row
        if (tableBean != null && 
            (tableBean.getName().equals(pageContext.getParameter(SOURCE_PARAM))) && 
            (ADD_ROWS_EVENT.equals(pageContext.getParameter(EVENT_PARAM)))) {
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("ContractKPIDetailVO1");
            if (!vo.isPreparedForExecution())
                vo.executeQuery();
            vo.last();

            Row row = vo.createRow();
            vo.insertRowAtRangeIndex(vo.getRowCount(), row);
            row.setNewRowState(Row.STATUS_INITIALIZED);
            Number contractId = 
                am.getOADBTransaction().getSequenceValue("CUX_CONTRACT_S");
            row.setAttribute("ContractId", contractId);
            String parentContractId = 
                (String)pageContext.getSessionValue("parentContractId");
            row.setAttribute("ParentContractId", parentContractId);
            //String appraisalId = (String)pageContext.getSessionValue("appraisalId");
            //row.setAttribute("AppraisalId", appraisalId);
            //row.setAttribute("KpiClass", kpiClass);
            vo.setCurrentRow(row);
        }

        // Handle delete event
        if ("delete".equals(pageContext.getParameter("event"))) {
            String contractId = pageContext.getParameter("contractId");
            Serializable params[] = { contractId };
            am.invokeMethod("delContractDetail", params);
        }

        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            //TransactionUnitHelper.endTransactionUnit(pageContext, "updateContractKPITxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/" + 
                                           sourcePage, null, (byte)0, null, 
                                           null, true, "N");
        }

        // Handle Apply action
        if (pageContext.getParameter("Apply") != null) {
            String phaseId = (String)pageContext.getSessionValue("phaseId");
            /*20090908 zs*/
            String kpiChildWeight = 
                (String)pageContext.getSessionValue("kpiChildWeight");
            String kpiStdAddWeightMethod = 
                (String)pageContext.getSessionValue("kpiStdAddWeightMethod");
            /*20090908 zs*/
            if (phaseId != null && phaseId.equals("REVIEW")) {
                //add by dl 2009.12.14 ��֤������������
                if (role != null && role.equals("selfservice"))
                    am.invokeMethod("validateChildKpiSelfScore");
                else if (role != null && role.equals("manager"))
                    am.invokeMethod("validateChildKpiScore");

                //add end 
                //zs �������Child�÷�
                String contractId = 
                    (String)pageContext.getSessionValue("parentContractId");
                //if(contractId != null ){ //changed  by dl  2009.12.14
                if (contractId != null && role != null && 
                    role.equals("manager")) { //CHANGED END
                    Serializable params[] = { contractId };
                    pageContext.putSessionValue("parentContractId", 
                                                contractId);
                    am.invokeMethod("computeChildKPIMark", params);
                }
            }
            /*20090908 zs*/
            if (kpiStdAddWeightMethod != null && kpiChildWeight != null && 
                !kpiChildWeight.equals("")) {
                Serializable params1[] = 
                { kpiStdAddWeightMethod, kpiChildWeight };
                am.invokeMethod("checkKpiChildWeight", params1);
            } else {
                throw new OAException("CUX", "CUX_KPI_WEIGHT_METHOD_WAR", null, 
                                      OAException.ERROR, null);
            }
            //zs

            am.invokeMethod("commit");

            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_CONTRACT_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/" + 
                                           sourcePage, null, (byte)0, null, 
                                           null, true, "N");
            /*try{
              pageContext.sendRedirect("OA.jsp?page=/cux/oracle/apps/per/review/webui/"+sourcePage);
          }catch(Exception e){;}*/
        }

        /*20090930 zs  add Save*/
        /*if(pageContext.getParameter("Save") != null){
           am.invokeMethod("commit");
       }*/
        /*20090930 zs*/

    }

}
