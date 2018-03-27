/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.AppraisalAMImpl;
import cux.oracle.apps.per.review.server.ContractContentVOImpl;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.sql.CallableStatement;
import java.sql.Types;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAInlineDatePickerBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAFlowLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.nav.OALinkBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.Row;
import oracle.jbo.Transaction;

/**
 * Controller for ...
 */
public class AppraisalContractCO extends OAControllerImpl {
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

        //初始化合同有效期和签署日期的控件显示
        OAInlineDatePickerBean inlineDatePicker = 
            (OAInlineDatePickerBean)createWebBean(pageContext, 
                                                  INLINE_DATEPICKER_BEAN);
        inlineDatePicker.setID("DatePicker");
        OAMessageDateFieldBean dateField = 
            (OAMessageDateFieldBean)webBean.findIndexedChildRecursive("EffectiveDate");
        dateField.setPickerId("DatePicker");
        OAMessageDateFieldBean dateField1 = 
            (OAMessageDateFieldBean)webBean.findIndexedChildRecursive("SignDate");
        dateField1.setPickerId("DatePicker");

        String canChange = "";
        String canAppeal = "";
        String appealStatus = "";
        String isCurrentPerformer = "";

        //String role = (String)pageContext.getSessionValue("role");
        String role = pageContext.getParameter("role");
        if (role != null)
            pageContext.putSessionValue("role", role);
        else
            role = (String)pageContext.getSessionValue("role");
        if (role == null || role.equals(""))
            role = "manager";

        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateContractContentTxn");
            if (!pageContext.isFormSubmission()) {
                ;
            }
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "CreateContractContentTxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        String appraisalId = pageContext.getParameter("appraisalId");
        if (appraisalId != null) {
            pageContext.putSessionValue("appraisalId", appraisalId);
        } else
            appraisalId = (String)pageContext.getSessionValue("appraisalId");
        if (appraisalId == null)
            System.out.println("appraisalId is null");
        if (appraisalId != null) {
            Serializable params[] = { appraisalId };

            if (pageContext.getParameter("from") != null && 
                pageContext.getParameter("from").equals("report")) {
                System.out.println("report back();");
            } else {
                am.invokeMethod("initAppraisalClass", params);
            }

            //根据角色初始化绩效合同基本信息部分的数据显示
            if (role != null) {
                String personType = "0";
                if (role.equals("manager"))
                    personType = "1";
                if (role.equals("selfservice"))
                    personType = "0";
                /*20090926 zs*/
                if (role.equals("admin"))
                    personType = "2";
                /*20090926 zs*/
                Serializable p[] = { appraisalId, personType };
                am.invokeMethod("initAppraisalEmp", p);
            }

            am.invokeMethod("initAppraisalContract", params);
            am.invokeMethod("initAppraisalComments", params);
            am.invokeMethod("initAppraisalAttendList", params);
            am.invokeMethod("initAppraisalAttendNote", params);

            //是否是当前处理人
            canChange = (String)am.invokeMethod("isCurrentPerformer", params);
            //是否可以申诉
            canAppeal = (String)am.invokeMethod("canAppeal", params);
            //申诉状态
            appealStatus = (String)am.invokeMethod("getAppealStatus", params);
            //是否是当前处理人
            isCurrentPerformer = 
                    (String)am.invokeMethod("isCurrentPerformer", params);
        }

        String phaseId = pageContext.getParameter("phaseId");
        if (phaseId != null) {
            pageContext.putSessionValue("phaseId", phaseId);
        } else {
            phaseId = (String)pageContext.getSessionValue("phaseId");
        }

        //fch 20090908
        String statusId = pageContext.getParameter("statusId");
        if (statusId != null) {
            pageContext.putSessionValue("statusId", statusId);
        } else {
            statusId = (String)pageContext.getSessionValue("statusId");
        }
        //数据来源
        OAMessageStyledTextBean Datasource = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("Datasource");
        //实际值
        OAMessageStyledTextBean actualValueBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("ActualValue");
        //得分
        OAMessageStyledTextBean scoreValueBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("ScoreValue");
        //自评分
        OAMessageStyledTextBean selfEvalValueBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("SelfEvalValue");

        //目标设定和目标调整阶段
        if (phaseId != null && (phaseId.equals("GOAL")) || 
            phaseId.equals("UPDATE")) {
            //已完成状态，显示系统计算的得分和实际值
            if (statusId != null && statusId.equals("COMPLETED")) {
                scoreValueBean.setViewAttributeName("ScoreValueShow");
                actualValueBean.setViewAttributeName("ActualValueShow");
            }
        }
        //考核评分阶段
        else if (phaseId != null && phaseId.equals("REVIEW")) {
            //自评分，且未完成、未冻结、未发布的情况下，显示系统计算的得分和实际值
            if (role != null && role.equals("selfservice") && 
                statusId != null && !statusId.equals("COMPLETED") && 
                !statusId.equals("FREEZED") && !statusId.equals("PUBLISHED")) {
                scoreValueBean.setViewAttributeName("ScoreValueShow");
                actualValueBean.setViewAttributeName("ActualValueShow");
                //added by dely.wang 20091121 
                //scoreValueBean.setRendered(false); 
                //end added �÷���״̬���أ��μ����״̬����/�������󣬶����޸ģ�(ϵͳ�ֿ��Կ������ֹ���ֲ����Կ���)��
            }
            //updated by huangbo 2013-05-28 第一级审批人第一次打分前员工得分显示0的BUG
            else if (role != null && role.equals("manager") && 
                     "Y".equals(isFirstApprover(pageContext, webBean, 
                                                appraisalId))) {
                if ("N".equals(isContractFirstScored(pageContext, webBean, 
                                                     appraisalId))) {
                    scoreValueBean.setViewAttributeName("ScoreValueShow");
                } else {
                    scoreValueBean.setViewAttributeName("ScoreValue");
                }
            }
            //updated by huangbo 2013-05-28 -end-
        }
        //其余情况
        else {
            scoreValueBean.setViewAttributeName("ScoreValue");
            actualValueBean.setViewAttributeName("ActualValue");
        }

        //考核评分阶段不允许修改合同有效期和签署日期，
        if (phaseId != null && phaseId.equals("REVIEW")) {
            selfEvalValueBean.setRendered(true);
            //added by dl 20091215
            if (dateField1 != null)
                dateField1.setReadOnly(true);
            if (dateField != null)
                dateField.setReadOnly(true);
            //end added                
        }
        //非考核评分阶段，隐藏自评分
        else {
            selfEvalValueBean.setRendered(false);
        }
        //added by dlwang@20091120
        actualValueBean.setAttributeValue(oracle.cabo.ui.UIConstants.DESTINATION_ATTR, 
                                          new OADataBoundValueViewObject(actualValueBean, 
                                                                         "ActualValueLink", 
                                                                         "ContractContentVO1"));
        //end added
        if (phaseId != null && statusId != null) {
            if (phaseId.equals("GOAL") || phaseId.equals("UPDATE")) {
                if (!statusId.trim().equals("COMPLETED")) {
                    actualValueBean.setAttributeValue(oracle.cabo.ui.UIConstants.DESTINATION_ATTR, 
                                                      null);
                }
            }
            /*if (role != null) {
                if (phaseId.equals("REVIEW")) {
                    if (!statusId.trim().equals("COMPLETED") &&
                        role.equals("selfservice")) {
                        actualValueBean.setAttributeValue(oracle.cabo.ui.UIConstants.DESTINATION_ATTR,
                                                          null);

                    }
                }
            }*/
        }

        Serializable ps[] = { appraisalId };
        //是否显示等级
        String finalLevelFlag = 
            (String)am.invokeMethod("getFinalLevelFlag", ps);
        OAMessageStyledTextBean finalLevel = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("FinalLevel");
        if (finalLevelFlag == null || finalLevelFlag.equals("N")) {
            finalLevel.setText(null);
        }

        OAMessageTextInputBean weightInputBean = 
            (OAMessageTextInputBean)webBean.findChildRecursive("Weight");
        if (phaseId != null && phaseId.equals("GOAL")) {
            /*20090927 zs*/
            //weightInputBean.setReadOnly(false);
            weightInputBean.setAttributeValue(READ_ONLY_ATTR, 
                                              new OADataBoundValueViewObject(weightInputBean, 
                                                                             "ReadOnlyFlag", 
                                                                             "ContractClassVO1"));
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("ContractClassVO1");
            while (vo.hasNext()) {
                Row row = vo.next();
                if (row.getAttribute("UpdateImage") != null && 
                    row.getAttribute("UpdateImage").equals("UpdateEnabled1"))
                    row.setAttribute("ReadOnlyFlag", Boolean.FALSE);
                else {
                    row.setAttribute("ReadOnlyFlag", Boolean.TRUE);
                }
            }
            /*20090927 zs*/
        } else {
            weightInputBean.setReadOnly(true);
        }
        //ʹ���ύ��ť
        OASubmitButtonBean saveBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Save");
        OASubmitButtonBean submitBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Submit");
        OASubmitButtonBean chooseTemplateBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("ChooseTemplate");
        //add by fl at 20090908
        OASubmitButtonBean copyPreviousBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("CopyPrevious");
        //end fl


        //add by dl -- disabled function
        if (chooseTemplateBtn != null)
            chooseTemplateBtn.setRendered(false);
        chooseTemplateBtn = null;
        //end dl
        //add by dl at 2009.10.08
        OASubmitButtonBean ReturnBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Return");
        OAFlowLayoutBean NoteRN = 
            (OAFlowLayoutBean)webBean.findChildRecursive("NoteRN");
        //end dl


        OASubmitButtonBean appealBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Appeal");
        OATableBean commentsTbl = 
            (OATableBean)webBean.findChildRecursive("AppraisalAttendVO1");
        OAFlowLayoutBean appealRN = 
            (OAFlowLayoutBean)webBean.findChildRecursive("AppealRN");
        OASubmitButtonBean rejectAppealBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("RejectAppeal");
        OASubmitButtonBean agreeAppealBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("AgreeAppeal");
        OAMessageTextInputBean appealDesc = 
            (OAMessageTextInputBean)webBean.findChildRecursive("AppealDesc");
        if (copyPreviousBtn != null) {
            copyPreviousBtn.setRendered(false);
        }

        if (canChange != null && canChange.equals("Y")) {
            if (saveBtn != null)
                saveBtn.setRendered(true);
            if (submitBtn != null)
                submitBtn.setRendered(true);

            //add by fl at 20090908
            if (phaseId != null && 
                phaseId.equals("GOAL")) //UPDATED BY DL 2009.12.3 ֻ����GOAL�׶β������ô˹���
            {
                if (copyPreviousBtn != null) {
                    copyPreviousBtn.setRendered(true);
                    copyPreviousBtn.setRendered(false); //add by zs 20100825 ���ظ�����ʷ��ͬģ��
                }

                if (chooseTemplateBtn != null)
                    chooseTemplateBtn.setRendered(true);
            }
            //end fl
            //fch 20090903
            //add by dl at 20091009
            if (ReturnBtn != null && !role.equals("selfservice"))
                ReturnBtn.setRendered(true);
            if (NoteRN != null)
                NoteRN.setRendered(true);
            //end dl
            if (appealDesc != null) {
                appealDesc.setRendered(true);
                String promopt = "申诉内容获取错误，请联系系统管理员";
                try {
                    promopt = 
                            new String(promopt.getBytes("ISO-8859-1"), "GB2312");
                } catch (UnsupportedEncodingException e) {
                    ;
                }
                appealDesc.setPrompt(promopt);
            }
            //fch
        } else {
            if (saveBtn != null)
                saveBtn.setRendered(false);
            if (submitBtn != null)
                submitBtn.setRendered(false);
            if (chooseTemplateBtn != null)
                chooseTemplateBtn.setRendered(false);

            //add by fl at 20090908
            if (copyPreviousBtn != null)
                copyPreviousBtn.setRendered(false);
            //end fl
            //add by dl at 20091009
            if (ReturnBtn != null)
                ReturnBtn.setRendered(false);
            if (NoteRN != null)
                NoteRN.setRendered(false);
            //end dl            

            //fch 20090903
            if (appealDesc != null)
                appealDesc.setRendered(false);
            //fch 20090903
        }

        if (appealStatus != null && appealStatus.equals("Y")) {
            if (saveBtn != null)
                saveBtn.setRendered(false);
            if (submitBtn != null)
                submitBtn.setRendered(false);

            //add by dl at 20091009
            if (ReturnBtn != null)
                ReturnBtn.setRendered(false);
            if (NoteRN != null)
                NoteRN.setRendered(false);
            //end dl                 
            if (isCurrentPerformer != null && isCurrentPerformer.equals("Y")) {
                if (rejectAppealBtn != null)
                    rejectAppealBtn.setRendered(true);
                if (agreeAppealBtn != null)
                    agreeAppealBtn.setRendered(true);
                //fch 20090903
                if (appealDesc != null) {
                    appealDesc.setRendered(true);
                    String promopt = "申诉内容获取错误，请联系系统管理员";
                    try {
                        promopt = 
                                new String(promopt.getBytes("ISO-8859-1"), "GB2312");
                    } catch (UnsupportedEncodingException e) {
                        ;
                    }
                    appealDesc.setPrompt(promopt);
                }
                //fch
            } else {
                if (rejectAppealBtn != null)
                    rejectAppealBtn.setRendered(false);
                if (agreeAppealBtn != null)
                    agreeAppealBtn.setRendered(false);
                //fch 20090903
                if (appealDesc != null)
                    appealDesc.setRendered(false);
                //fch
            }
        } else {
            //if (isCurrentPerformer != null && isCurrentPerformer.equals("N")) {
            if (rejectAppealBtn != null)
                rejectAppealBtn.setRendered(false);
            if (agreeAppealBtn != null)
                agreeAppealBtn.setRendered(false);
            if (appealDesc != null)
                appealDesc.setRendered(false);
            //}
        }

        //
        if (canAppeal != null && canAppeal.equals("Y")) {
            if (appealBtn != null)
                appealBtn.setRendered(true);
            if (appealDesc != null)
                appealDesc.setRendered(true);
        } else {
            if (appealBtn != null)
                appealBtn.setRendered(false);
            //if (rejectAppealBtn != null)
            //    rejectAppealBtn.setRendered(false);
            //if (agreeAppealBtn != null)
            //    agreeAppealBtn.setRendered(false);
            //if (appealDesc != null)
            //    appealDesc.setRendered(false);

            //fch 20090903
            //OAMessageTextInputBean appealDesc = (OAMessageTextInputBean)webBean.findChildRecursive("AppealDesc");
            //if(appealDesc != null) appealDesc.setText("�������");
            //if(appealStatus == null || appealStatus.equals("N")) appealRN.setRendered(false);
            //if(rejectAppealBtn != null) rejectAppealBtn.setRendered(false);
            //if(agreeAppealBtn != null) agreeAppealBtn.setRendered(false);
            //fch 20090903
        }

        // Link the outerTable and innerTable
        OAAdvancedTableBean outerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("ContractClassVO1");
        OAAdvancedTableBean innerTable = 
            (OAAdvancedTableBean)webBean.findChildRecursive("ContractContentVO1");
        if (outerTable != null) {
            outerTable.setAttributeValue(CHILD_VIEW_ATTRIBUTE_NAME, 
                                         "KpiClass");
            outerTable.setAttributeValue(VIEW_LINK_NAME, 
                                         "ContractClassContentVL1");
        }

        if (innerTable != null) {
            innerTable.setAttributeValue(CHILD_VIEW_ATTRIBUTE_NAME, 
                                         "KpiClass1");
            innerTable.setAttributeValue(VIEW_LINK_NAME, 
                                         "ContractClassContentVL1");
            innerTable.prepareForRendering(pageContext);
        }

        OAMessageStyledTextBean FinalMarkBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("FinalMark");
        OAMessageStyledTextBean ValidValueBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("ValidValue");
        if (phaseId != null && phaseId.equals("REVIEW") && statusId != null && 
            !statusId.trim().equals("COMPLETED") && 
            !statusId.trim().equals("FREEZED") && 
            !statusId.trim().equals("PUBLISHED") && role != null && 
            role.equals("selfservice")) {
            if (FinalMarkBean != null) {
                FinalMarkBean.setValue(pageContext, null);
            }
            if (ValidValueBean != null) {
                ValidValueBean.setRendered(false);
            }
        }
        //end dl
        if (phaseId != null && !phaseId.equals("GOAL")) {
            if (Datasource != null)
                Datasource.setViewAttributeName("KpiDataSourceDisplay");
        }

        // add by yang.gang, 2014-9-22,begin
        // 解决问题: 点击提交按钮后，在确定提交考核流程界面，点击“是”按钮，点击2次，间隔时间2-3秒，之后流程自动跳过后一个审批节点
        // 在提交按钮添加前台javascript代码，点击提交按钮后，要求用户确认
        // 用户点击确定后，首先页面的按钮状态修改为disabled，执行后台处理代码，这样就避免了用户两次点击改按钮，造成重复提交的结果
        // 退回 不需要用户确认，直接执行
        String javaScript = 
            "javascript:function CtlBtnStatus(obj)" + "{ var flag=window.confirm(\"确定要提交该考核流程吗？\");" + 
            "if(flag) { obj.disabled=true; return true; } else return false;" + 
            "}";

        pageContext.putJavaScriptFunction("CtlBtnStatus", javaScript);

        OASubmitButtonBean submitButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Submit");
        if (submitButton != null) {
            submitButton.setOnClick("javascript:return CtlBtnStatus(this);");
        }

        OASubmitButtonBean returnButton = 
            (OASubmitButtonBean)webBean.findChildRecursive("Return");
        if (returnButton != null) {
            returnButton.setOnClick("javascript:this.disabled=true;return true;");
        }
        // add by yang.gang, 2014-9-22,end

        // add by yang.gang, 2016-2-4,begin        
        copyPreviousBtn = 
                (OASubmitButtonBean)webBean.findChildRecursive("CopyPrevious");
        copyPreviousBtn.setRendered(true);
        // add by yang.gang, 2016-2-4,end
    } //processRequest

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

        //zs
        String phaseId = (String)pageContext.getSessionValue("phaseId");
        //zs
        // Handle choose template action
        if (pageContext.getParameter("ChooseTemplate") != null) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalChooseTmplPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }
        //add by fl at 20090908
        if ("copy".equals(pageContext.getParameter(EVENT_PARAM))) {
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalCopyPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        } // end fl


        // Handle Cancel action
        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "CreateTmplContentTxn");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalEmpPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Cancel action
        if (pageContext.getParameter("Back") != 
            null) { // Return -> Back  by dl 2009.10.9
            /*20090927 zs*/
            String role = (String)pageContext.getSessionValue("role");
            if (role != null && role.equals("admin")) {
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/LevelProrateDetailsPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
            } else {
                /*20090927 zs*/
                try {
                    pageContext.putSessionValue("backFromContract", "Y");
                    //pageContext.sendRedirect("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalEmpPG");

                    pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalEmpPG", 
                                                   null, 
                                                   OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                                   null, null, true, "N");
                } catch (Exception e) {
                    ;
                }
            }
        }

        if (pageContext.getParameter("Appeal") != null) {
            am.invokeMethod("commit");
            String appraisalId = 
                (String)pageContext.getSessionValue("appraisalId");
            Serializable params[] = { appraisalId };
            String result = (String)am.invokeMethod("submitAppeal", params);
            OAException confirmMessage = null;
            if (result != null && result.equals("Y")) {
                confirmMessage = 
                        new OAException("CUX", "CUX_APPEAL_SUBMITTED_CONF", 
                                        null, OAException.CONFIRMATION, null);
            } else {
                confirmMessage = 
                        new OAException("CUX", "CUX_APPEAL_SUBMITTED_ERR", 
                                        null, OAException.ERROR, null);
            }
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalEmpPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        if (pageContext.getParameter("AgreeAppeal") != null) {
            am.invokeMethod("commit");
            String appraisalId = 
                (String)pageContext.getSessionValue("appraisalId");
            Serializable params[] = { appraisalId };
            String result = (String)am.invokeMethod("approveAppeal", params);
            OAException confirmMessage = null;
            if (result != null && result.equals("Y")) {
                confirmMessage = 
                        new OAException("CUX", "CUX_APPROVE_APPEAL_CONF", null, 
                                        OAException.CONFIRMATION, null);
            } else {
                confirmMessage = 
                        new OAException("CUX", "CUX_APPROVE_APPEAL_ERR", null, 
                                        OAException.ERROR, null);
            }
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalEmpPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        if (pageContext.getParameter("RejectAppeal") != null) {
            String appraisalId = 
                (String)pageContext.getSessionValue("appraisalId");
            Serializable params[] = { appraisalId };
            String result = (String)am.invokeMethod("rejectAppeal", params);
            OAException confirmMessage = null;
            if (result != null && result.equals("Y")) {
                confirmMessage = 
                        new OAException("CUX", "CUX_REJECT_APPEAL_CONF", null, 
                                        OAException.CONFIRMATION, null);
            } else {
                confirmMessage = 
                        new OAException("CUX", "CUX_REJECT_APPEAL_ERR", null, 
                                        OAException.ERROR, null);
            }
            pageContext.putDialogMessage(confirmMessage);
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalEmpPG", 
                                           null, (byte)0, null, null, true, 
                                           "N");
        }

        // Handle Save action
        if (pageContext.getParameter("Save") != null) {
            //zs
            String appraisalId = 
                (String)pageContext.getSessionValue("appraisalId");
            if (appraisalId != null && phaseId != null && 
                phaseId.equals("REVIEW")) {
                //�����ܵ÷�
                Serializable params[] = { appraisalId };
                am.invokeMethod("computeTotalMark", params);
            }

            if (appraisalId != null && phaseId != null && 
                phaseId.equals("GOAL")) {
                am.invokeMethod("checkKpiStdWeight");
            }

            //zs
            am.invokeMethod("commit");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_CONTRACT_CONF", null, 
                                OAException.CONFIRMATION, null);
            pageContext.putDialogMessage(confirmMessage);
            //pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG", null, (byte)0, null, null, true, "N");
            try {
                pageContext.sendRedirect("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG");
            } catch (Exception e) {
                ;
            }
        }

        // Handle update contract line details
        if ("updateContractChild".equals(pageContext.getParameter("event"))) {
            String kpiClass = pageContext.getParameter("kpiClass");
            String appraisalId = pageContext.getParameter("appraisalId");
            Serializable parameters[] = { appraisalId, kpiClass };
            String kpiClassType = 
                (String)am.invokeMethod("getKpiClassType", parameters);
            if (kpiClassType != null && kpiClassType.equals("Y"))
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ContractKPIChildPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
            else
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ContractKPIChildEnterPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
        }

        // Handle update contract 
        if ("updateContract".equals(pageContext.getParameter("event"))) {
            String kpiClassType = pageContext.getParameter("kpiClassType");
            String appraisalId = pageContext.getParameter("appraisalId");
            String kpiClass = pageContext.getParameter("kpiClass");
            String signDate = pageContext.getParameter("SignDate");
            if (appraisalId != null)
                pageContext.putSessionValue("appraisalId", appraisalId);
            if (kpiClass != null)
                pageContext.putSessionValue("kpiClass", kpiClass);
            if (signDate != null)
                pageContext.putSessionValue("signDate", signDate);
            String kpiClassWeight = pageContext.getParameter("kpiClassWeight");
            if (kpiClassWeight != null && !kpiClassWeight.equals("")) {
                pageContext.putSessionValue("kpiClassWeight", kpiClassWeight);
            } else {
                throw new OAException("CUX", "CUX_KPI_WEIGHT_NEED_WAR", null, 
                                      OAException.ERROR, null);
            }
            if (appraisalId != null && !appraisalId.equals("") && 
                (kpiClass != null && !kpiClass.equals(""))) {
                String kpiClassWeight_Flag = "N";
                Serializable pa[] = { appraisalId, kpiClass };
                kpiClassWeight_Flag = 
                        (String)am.invokeMethod("kpiClassWeightIsNullMethod", 
                                                pa);
                if (kpiClassWeight_Flag != null && 
                    kpiClassWeight_Flag.equals("Y"))
                    throw new OAException("CUX", "CUX_KPI_WEIGHT_NEED_WAR");
            }
            /*20090930 zs*/

            /*20091129 zs */
            if (kpiClassWeight != null && !kpiClassWeight.equals("") && 
                (kpiClass != null && !kpiClass.equals(""))) {
                /*
                  String kpiClassWeightIsChange = "N";
                  Serializable pa[] = { appraisalId, kpiClass, kpiClassWeight};
                  kpiClassWeightIsChange = (String)am.invokeMethod("kpiClassWeightIsChangeMethod",pa);
                  if(kpiClassWeightIsChange != null && kpiClassWeightIsChange.equals("Y"))
                      throw new OAException("CUX", "CUX_KPI_kpiClassWeightIsChange_WAR");
              */
                Transaction txn = am.getTransaction();
                if (txn.isDirty())
                    throw new OAException("CUX", "CUX_KPI_DATAISCHANGE_WAR");
            }
            /*20091129 zs */
            if (kpiClassType != null && kpiClassType.trim().equals("Y")) {
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractKPIPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
            } else {
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractKPIEnterPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, "N");
            }
        }
        //Handle return action v0.1 add by dl 2009.10.9
        if (pageContext.getParameter("Return") != null) {
            //zs
            String appraisalId = 
                (String)pageContext.getSessionValue("appraisalId");
            if (appraisalId != null && phaseId != null && 
                phaseId.equals("REVIEW")) {
                Serializable params[] = { appraisalId };
                am.invokeMethod("computeTotalMark", params);
            }

            Serializable params[] = { appraisalId };
            am.invokeMethod("checkKpiStdWeight");

            am.invokeMethod("commit");
            /*20090928 zs*/
            if (appraisalId != null && phaseId != null && 
                phaseId.equals("GOAL")) {
                String checkResultFlag = "R";
                Serializable pa[] = { appraisalId };
                checkResultFlag = 
                        (String)am.invokeMethod("checkResultFlagMethod", pa);
                if (!checkResultFlag.equals("R"))
                    throw new OAException("CUX", "CUX_KPI_checkResult_WAR");
            }
            /*20090928 zs*/

            //String result = (String)am.invokeMethod("selfSubmitForApproval", params);submitForApproval
            String result = (String)am.invokeMethod("submitForReturn", params);
            if (result != null && result.equals("Y")) {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_RETURN_CONTRACT_CONF", null, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);
                HashMap parameter = new HashMap(1);
                parameter.put("appraisalId", appraisalId);
                //pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG", null, (byte)0, null, null, true, "N");
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, parameter, true, 
                                               OAWebBeanConstants.ADD_BREAD_CRUMB_NO);

            } else {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_RETURN_CONTRACT_ERR", null, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }
        }
        //end added

        //Handle Submit action  edit by fl 20091019
        if (pageContext.getParameter("Submit") != null) {
            String appraisalId = 
                (String)pageContext.getSessionValue("appraisalId");
            //updated by huangbo 2013-10-18 验证是否为当前处理人，不是的话不能提交，给予错误提示
            String isPromise = 
                isCurrentOperator(pageContext, webBean, appraisalId);
            if ("Y".equals(isPromise)) {
                OAException message = 
                    new OAException("当前用户不是所选绩效合同的当前处理人，无法提交绩效合同！", 
                                    OAException.ERROR);
                pageContext.putDialogMessage(message);
                return;
            }
            //updated by huangbo 2013-10-18 -end-
            String role = pageContext.getSessionValue("role").toString();
            //updated by huangbo 2013-05-23
            if (phaseId.equals("REVIEW") && role.equals("selfservice")) {
                String checkSV = 
                    checkSelfValueInvalid(pageContext, webBean, appraisalId);
                if (!"true".equals(checkSV)) {
                    OAException errMessage = 
                        new OAException(checkSV, OAException.ERROR);
                    pageContext.putDialogMessage(errMessage);
                    return;
                }
            }
            //updated by huangbo 2013-05-23 -end-


            if (phaseId.equals("REVIEW") && role.equals("manager") && 
                "Y".equals(isFirstApprover(pageContext, webBean, 
                                           appraisalId))) {
                String checkScoreValue = 
                    checkScoreValueInvalid(pageContext, webBean, appraisalId);
                if (!"true".equals(checkScoreValue)) {
                    OAException errMessage = 
                        new OAException(checkScoreValue, OAException.ERROR);
                    pageContext.putDialogMessage(errMessage);
                    return;
                }
            }

            if (appraisalId != null && phaseId != null && 
                phaseId.equals("REVIEW")) {
                Serializable params[] = { appraisalId };
                am.invokeMethod("computeTotalMark", params);
            }
            Serializable params[] = { appraisalId };
            am.invokeMethod("checkKpiStdWeight");

            am.invokeMethod("commit");
            /*20090928 zs*/
            if (appraisalId != null && phaseId != null && 
                phaseId.equals("GOAL")) {
                String checkResultFlag = "R";
                Serializable pa[] = { appraisalId };
                checkResultFlag = 
                        (String)am.invokeMethod("checkResultFlagMethod", pa);
                if (!checkResultFlag.equals("R"))
                    throw new OAException("CUX", "CUX_KPI_checkResult_WAR");
            }
            /*20090928 zs*/
            AppraisalAMImpl amImpl = 
                (AppraisalAMImpl)pageContext.getApplicationModule(webBean);
            String result = amImpl.submitForApproval(appraisalId);
            if (result != null && result.equals("Y")) {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_SUBMIT_CONTRACT_CONF", null, 
                                    OAException.CONFIRMATION, null);
                pageContext.putDialogMessage(confirmMessage);
                HashMap parameter = new HashMap(1);
                parameter.put("appraisalId", appraisalId);
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, parameter, true, 
                                               OAWebBeanConstants.ADD_BREAD_CRUMB_NO);

            } else {
                OAException confirmMessage = 
                    new OAException("CUX", "CUX_SUBMIT_CONTRACT_ERR", null, 
                                    OAException.ERROR, null);
                pageContext.putDialogMessage(confirmMessage);
            }
        }
        OAViewObject vo = 
            (OAViewObject)am.findViewObject("ContractContentVO1");
        while (vo.hasNext() && vo != null) {
            Row row = vo.next();
            System.out.println(row.getAttribute("ActualValueShow"));

        }
    } // processFormRequest
    //updated by huangbo 2013-05-31

    private String checkScoreValueInvalid(OAPageContext pageContext, 
                                          OAWebBean webBean, 
                                          String appraisalId) {
        String checkResult = "";
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        CallableStatement cs = null;
        OADBTransaction transaction = am.getOADBTransaction();
        cs = 
 transaction.createCallableStatement("CALL CUX_JX_HB.CHECK_SCOREVALUE_INVALID(?, ?)", 
                                     1);
        try {
            cs.setObject(1, appraisalId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            checkResult = cs.getString(2);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return checkResult;
    }
    //updated by huangbo 2013-05-31 -end-
    //updated by huangbo 2013-05-23

    private String checkSelfValueInvalid(OAPageContext pageContext, 
                                         OAWebBean webBean, 
                                         String appraisalId) {
        String checkResult = "";
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        CallableStatement cs = null;
        OADBTransaction transaction = am.getOADBTransaction();
        cs = 
 transaction.createCallableStatement("CALL CUX_JX_HB.CHECK_SELFVALUE_INVALID(?, ?)", 
                                     1);
        try {
            cs.setObject(1, appraisalId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            checkResult = cs.getString(2);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return checkResult;
    }
    //updated by huangbo 2013-05-23 -end-

    //updated by huangbo 2013-05-28

    private String isFirstApprover(OAPageContext pageContext, 
                                   OAWebBean webBean, String appraisalId) {
        String checkResult = "";
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        CallableStatement cs = null;
        OADBTransaction transaction = am.getOADBTransaction();
        cs = 
 transaction.createCallableStatement("CALL CUX_JX_HB.IS_FIRST_APPROVER(?, ?)", 
                                     1);
        try {
            cs.setObject(1, appraisalId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            checkResult = cs.getString(2);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            checkResult = "N";
        }
        return checkResult;
    }
    //updated by huangbo 2013-05-28 -end-
    //updated by huangbo 2013-05-28

    private String isContractFirstScored(OAPageContext pageContext, 
                                         OAWebBean webBean, 
                                         String appraisalId) {
        String checkResult = "";
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        CallableStatement cs = null;
        OADBTransaction transaction = am.getOADBTransaction();
        cs = 
 transaction.createCallableStatement("CALL CUX_JX_HB.CHECK_CONTRACT_FIRST_SCORED(?, ?)", 
                                     1);
        try {
            cs.setObject(1, appraisalId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            checkResult = cs.getString(2);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            checkResult = "N";
        }
        return checkResult;
    }
    //updated by huangbo 2013-05-28 -end-

    //updated by huangbo 2013-10-18 验证是否为当前处理人，不是的话不能提交，给予错误提示

    private String isCurrentOperator(OAPageContext pageContext, 
                                     OAWebBean webBean, 
                                     String strAppraisalId) {
        String checkResult = "";
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        CallableStatement cs = null;
        OADBTransaction transaction = am.getOADBTransaction();
        cs = 
 transaction.createCallableStatement("BEGIN :1 := cux_jxkh_common_pkg.is_promiseer_role(:2)", 
                                     1);
        try {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, strAppraisalId);
            cs.execute();
            checkResult = cs.getString(1);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            checkResult = "N";
        }
        return checkResult;
    }
    //updated by huangbo 2013-10-18 -end-
}
