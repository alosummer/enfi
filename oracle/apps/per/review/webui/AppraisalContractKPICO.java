package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.bonus.advanceawards.server.AdvanceAwardsAMImpl;
import cux.oracle.apps.per.review.server.AppraisalAMImpl;
import cux.oracle.apps.per.review.server.KPIDetailsVOImpl;
import cux.oracle.apps.per.review.server.PerKPILovVOImpl;

import java.io.PrintStream;
import java.io.Serializable;

import java.util.Hashtable;

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
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.cabo.ui.UIConstants;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class AppraisalContractKPICO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        String appraisalId = pageContext.getParameter("appraisalId");
        if (appraisalId != null)
            pageContext.putSessionValue("appraisalId", appraisalId);
        else
            appraisalId = (String)pageContext.getSessionValue("appraisalId");

        String kpiClass = pageContext.getParameter("kpiClass");
        if (kpiClass != null)
            pageContext.putSessionValue("kpiClass", kpiClass);
        else
            kpiClass = (String)pageContext.getSessionValue("kpiClass");

        String kpiClassType = pageContext.getParameter("kpiClassType");
        if (kpiClassType != null)
            pageContext.putSessionValue("kpiClassType", kpiClassType);

        String role = (String)pageContext.getSessionValue("role");

        String kpiClassStdAdd = pageContext.getParameter("kpiClassStdAdd");
        if ((kpiClassStdAdd == null) || (kpiClassStdAdd.equals(""))) {
            kpiClassStdAdd = 
                    (String)pageContext.getSessionValue("kpiClassStdAdd");
        }
        pageContext.putSessionValue("kpiClassStdAdd", kpiClassStdAdd);

        String kpiComputeAddSub = pageContext.getParameter("kpiComputeAddSub");
        if ((kpiComputeAddSub == null) || (kpiComputeAddSub.equals(""))) {
            kpiComputeAddSub = 
                    (String)pageContext.getSessionValue("kpiComputeAddSub");
        }
        pageContext.putSessionValue("kpiComputeAddSub", kpiComputeAddSub);
        String kpiStdAddWeightMethod = 
            pageContext.getParameter("kpiStdAddWeightMethod");

        if ((kpiStdAddWeightMethod == null) || 
            (kpiStdAddWeightMethod.equals(""))) {
            kpiStdAddWeightMethod = 
                    (String)pageContext.getSessionValue("kpiStdAddWeightMethod");
        } else
            pageContext.putSessionValue("kpiStdAddWeightMethod", 
                                        kpiStdAddWeightMethod);

        String kpiClassWeight = pageContext.getParameter("kpiClassWeight");
        if ((kpiClassWeight == null) || (kpiClassWeight.equals(""))) {
            kpiClassWeight = 
                    (String)pageContext.getSessionValue("kpiClassWeight");
        }
        pageContext.putSessionValue("kpiClassWeight", kpiClassWeight);

        String phaseId = (String)pageContext.getSessionValue("phaseId");

        AppraisalAMImpl amImpl = 
            (AppraisalAMImpl)pageContext.getApplicationModule(webBean);
        String canChange = amImpl.isCurrentPerformer(appraisalId);
        if (canChange == null || "".equals(canChange))
            canChange = "N";


        int irtn = 
            this.GoToWeb(pageContext, role, phaseId, kpiClass, canChange);
        if (irtn != 0)
            return;

        //判断是否能增加新行的标识位，绩效评分阶段，关键绩效指标，工作任务，不能新增行
        String updateFlag = amImpl.getUpdateFlag(appraisalId, kpiClass);
        OAMessageTextInputBean weight_pa = 
            (OAMessageTextInputBean)webBean.findChildRecursive("Weight");
        String weightStr = "权重(" + kpiClassWeight + "%)";
        weight_pa.setLabel(weightStr);

        OASubmitButtonBean applyBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Apply");
        OASubmitButtonBean saveBtn = 
            (OASubmitButtonBean)webBean.findChildRecursive("Save");

        amImpl.queryAppKpiClasses(appraisalId);

        if ("Y".equals(canChange)) {
            applyBtn.setRendered(true);
            saveBtn.setRendered(true);
        } else {
            applyBtn.setRendered(false);
            saveBtn.setRendered(false);
        }

        //added by huangbo 2014-05-20 初始化绩效指标详细信息表
        KPIDetailsVOImpl kpiDetailsVO = 
            (KPIDetailsVOImpl)amImpl.findViewObject("KPIDetailsVO1");
        kpiDetailsVO.setWhereClauseParam(0, appraisalId);
        kpiDetailsVO.setWhereClauseParam(1, kpiClass);
        kpiDetailsVO.executeQuery();

        //   2014-8-21, edit by yang.gang, begin, 修改setWhereClause里面的K.KPI_CLASS 和 k.kpi_id, 去掉K.前缀
        //   2014-8-27, edit by yang.gang, begin, setWhereClause修改为拼接形式,因为PerKPILovVO1被AppraisalContractKPIPG.xml里的
        //              AppraisalKPIVO1,KpiName列的LOV调用，根据lovMap1映射，会传递查询参数到该VO，两次传递参数系统会报错，怀疑为控件的bug
        PerKPILovVOImpl perKPIVO = 
            (PerKPILovVOImpl)amImpl.findViewObject("PerKPILovVO1");
        if ((kpiClass != null) && (appraisalId != null)) {
            String strWhereClause = 
                " KPI_CLASS = '" + kpiClass + "' AND cux_jxkh_common_pkg.get_constraint_desc(kpi_id," + 
                appraisalId + ") != 'NOTMATCH' ";
            perKPIVO.setWhereClause(strWhereClause);
            perKPIVO.executeQuery();
        }
        //   2014-8-21, edit by yang.gang, end
        //   2014-8-27, edit by yang.gang, end
        amImpl.initKpiArea(kpiClass);

        if ((appraisalId != null) && (kpiClass != null)) {
            amImpl.queryAppKpi(appraisalId, kpiClass);
        }

        if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "updateContractKPITxn");

            if (pageContext.isFormSubmission())
                ;
        } else if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, 
                                                                      "updateContractKPITxn", 
                                                                      true)) {
            pageContext.redirectToDialogPage(new OADialogPage(NAVIGATION_ERROR));
        }

        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("AppraisalKPIVO1");

        tableBean.prepareForRendering(pageContext);


        if ((canChange != null) && (canChange.equals("Y"))) {
            if ((updateFlag != null) && (updateFlag.equals("Y")))
                tableBean.setInsertable(true);
            else
                tableBean.setInsertable(false);
        } else
            tableBean.setInsertable(false);
        tableBean.setAutoInsertion(false);

        String statusId = (String)pageContext.getSessionValue("statusId");
        OAMessageTextInputBean acv = 
            (OAMessageTextInputBean)webBean.findChildRecursive("ActualValue");

        OAMessageTextInputBean srv = 
            (OAMessageTextInputBean)webBean.findChildRecursive("ScoreValue");

        OAMessageTextInputBean sev = 
            (OAMessageTextInputBean)webBean.findChildRecursive("SelfEvalValue");

        acv.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                              new OADataBoundValueViewObject(acv, 
                                                             "AcReadOnlyFlag", 
                                                             "AppraisalKPIVO1"));

        srv.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                              new OADataBoundValueViewObject(srv, 
                                                             "ReadOnlyFlag", 
                                                             "AppraisalKPIVO1"));

        sev.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                              new OADataBoundValueViewObject(sev, 
                                                             "SelfReadOnlyFlag", 
                                                             "AppraisalKPIVO1"));

        acv.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                              new OADataBoundValueViewObject(sev, 
                                                             "SelfReadOnlyFlag", 
                                                             "AppraisalKPIVO1"));

        if ((phaseId != null) && (phaseId.equals("REVIEW")))
            sev.setRendered(true);
        else {
            sev.setRendered(false);
        }

        if (((phaseId != null) && (phaseId.equals("GOAL"))) || 
            (phaseId.equals("UPDATE"))) {
            if ((statusId != null) && (statusId.equals("COMPLETED"))) {
                srv.setViewAttributeName("ScoreValueShow");
                acv.setViewAttributeName("ActualValueShow");
            }
        } else if ((phaseId != null) && (phaseId.equals("REVIEW"))) {
            if ((role != null) && (role.equals("selfservice")) && 
                (statusId != null) && (!statusId.equals("COMPLETED")) && 
                (!statusId.equals("PUBLISHED")) && 
                (!statusId.equals("FREEZED"))) {
                srv.setViewAttributeName("ScoreValueShow");
                srv.setReadOnly(true);

                acv.setViewAttributeName("ActualValue");
            }

        } else {
            srv.setViewAttributeName("ScoreValue");
            acv.setViewAttributeName("ActualValue");
        }

        OAMessageChoiceBean kpiDataSource = 
            (OAMessageChoiceBean)webBean.findChildRecursive("KpiDataSource");
        OAMessageTextInputBean weight = 
            (OAMessageTextInputBean)webBean.findChildRecursive("Weight");

        OAMessageTextInputBean targetValue = 
            (OAMessageTextInputBean)webBean.findChildRecursive("TargetValue");

        OAMessageTextInputBean minimumValue = 
            (OAMessageTextInputBean)webBean.findChildRecursive("MinimumValue");

        OAMessageTextInputBean actualValue = 
            (OAMessageTextInputBean)webBean.findChildRecursive("ActualValue");

        OAMessageTextInputBean kpiDesc = 
            (OAMessageTextInputBean)webBean.findChildRecursive("KpiDesc");

        OAMessageTextInputBean attribute5 = 
            (OAMessageTextInputBean)webBean.findChildRecursive("Attribute5");

        OAMessageChoiceBean kpiArea = 
            (OAMessageChoiceBean)webBean.findChildRecursive("KpiArea");

        OAWebBean kpiName_tmp = webBean.findChildRecursive("KpiName");
        String tmp = kpiName_tmp.getLocalName();
        OAMessageLovInputBean kpiName = null;
        OAMessageTextInputBean kpiName_tx = null;
        if (tmp.equals("messageLovInput")) {
            kpiName = 
                    (OAMessageLovInputBean)webBean.findChildRecursive("KpiName");
        } else {
            kpiName_tx = 
                    (OAMessageTextInputBean)webBean.findChildRecursive("KpiName");
        }

        if ((phaseId.equals("REVIEW")) && (updateFlag != null) && 
            (updateFlag.equals("N"))) {
            if (tmp.equals("messageLovInput"))
                kpiName.setReadOnly(true);
            else {
                kpiName_tx.setReadOnly(true);
            }

            attribute5.setReadOnly(true);
            kpiArea.setReadOnly(true);
            weight.setReadOnly(true);
            targetValue.setReadOnly(true);
            minimumValue.setReadOnly(true);
        } else if ((phaseId.equals("GOAL")) || (phaseId.equals("UPDATE"))) {
            actualValue.setReadOnly(true);

            OAMessageTextInputBean scoreValue = 
                (OAMessageTextInputBean)webBean.findChildRecursive("ScoreValue");

            scoreValue.setReadOnly(true);
        }

        if (((!phaseId.equals("GOAL")) && (!phaseId.equals("UPDATE"))) || 
            (kpiClassType == null) || (!kpiClassType.trim().equals("N"))) {
            kpiDesc.setReadOnly(true);
        }

        String finalLevelFlag = amImpl.getFinalLevelFlag(appraisalId);

        OAMessageStyledTextBean finalLevel = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("FinalLevel");

        if ((finalLevelFlag == null) || (finalLevelFlag.equals("N"))) {
            finalLevel.setMessage(null);
        }

        if ((phaseId.equals("GOAL")) || (!phaseId.equals("UPDATE"))) {
            saveBtn.setRendered(false);
        }

        if ((kpiClass != null) && 
            ((kpiClass.equals("BEHAVIOUR")) || (kpiClass.equals("SUBTRACT")) || 
             (kpiClass.equals("ADD")))) {
            weight.setRendered(false);
            targetValue.setRendered(false);
            minimumValue.setRendered(false);
            actualValue.setRendered(false);

            acv.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                  new OADataBoundValueViewObject(acv, 
                                                                 "LockKpiFlag", 
                                                                 "AppraisalKPIVO1"));

            srv.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                  new OADataBoundValueViewObject(srv, 
                                                                 "LockKpiFlag", 
                                                                 "AppraisalKPIVO1"));

            sev.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                  new OADataBoundValueViewObject(sev, 
                                                                 "LockKpiFlag", 
                                                                 "AppraisalKPIVO1"));

            actualValue.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                          new OADataBoundValueViewObject(actualValue, 
                                                                         "LockKpiFlag", 
                                                                         "AppraisalKPIVO1"));

            kpiDesc.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                      new OADataBoundValueViewObject(kpiDesc, 
                                                                     "LockKpiFlag", 
                                                                     "AppraisalKPIVO1"));

            attribute5.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                         new OADataBoundValueViewObject(attribute5, 
                                                                        "LockKpiFlag", 
                                                                        "AppraisalKPIVO1"));

            kpiArea.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                      new OADataBoundValueViewObject(kpiArea, 
                                                                     "LockKpiFlag", 
                                                                     "AppraisalKPIVO1"));

            if (tmp.equals("messageLovInput")) {
                kpiName.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                          new OADataBoundValueViewObject(kpiName, 
                                                                         "LockKpiFlag", 
                                                                         "AppraisalKPIVO1"));
            } else {
                kpiName_tx.setAttributeValue(UIConstants.READ_ONLY_ATTR, 
                                             new OADataBoundValueViewObject(kpiName_tx, 
                                                                            "LockKpiFlag", 
                                                                            "AppraisalKPIVO1"));
            }

            if ((canChange != null) && (canChange.equals("N"))) {
                acv.setReadOnly(true);
                srv.setReadOnly(true);
                sev.setReadOnly(true);
                actualValue.setReadOnly(true);
                kpiDesc.setReadOnly(true);
                attribute5.setReadOnly(true);
                kpiArea.setReadOnly(true);
                if (tmp.equals("messageLovInput"))
                    kpiName.setReadOnly(true);
                else {
                    kpiName_tx.setReadOnly(true);
                }
            }

            if ((phaseId != null) && (phaseId.equals("REVIEW")) && 
                (role != null) && (role.equals("selfservice"))) {
                if (applyBtn != null) {
                    applyBtn.setRendered(false);
                }
                if (saveBtn != null) {
                    saveBtn.setRendered(false);
                }
                if (tableBean != null) {
                    tableBean.setInsertable(false);
                }
                acv.setReadOnly(true);
                srv.setReadOnly(true);
                sev.setReadOnly(true);
                actualValue.setReadOnly(true);
                kpiDesc.setReadOnly(true);
                attribute5.setReadOnly(true);
                kpiArea.setReadOnly(true);
                if (tmp.equals("messageLovInput"))
                    kpiName.setReadOnly(true);
                else {
                    kpiName_tx.setReadOnly(true);
                }
            }
        }

        OAMessageStyledTextBean FinalMarkBean = 
            (OAMessageStyledTextBean)webBean.findChildRecursive("FinalMark");

        if ((phaseId != null) && (phaseId.equals("REVIEW")) && 
            (role != null) && (role.equals("selfservice"))) {
            srv.setReadOnly(true);

            if ((statusId != null) && (!statusId.equals("COMPLETED")) && 
                (!statusId.equals("PUBLISHED")) && 
                (!statusId.equals("FREEZED")) && (FinalMarkBean != null)) {
                FinalMarkBean.setValue(pageContext, null);
            }
        }

        if ((phaseId != null) && (!phaseId.equals("GOAL")) && 
            (kpiClass != null) && (kpiClass.equals("KPI"))) {
            if (kpiDataSource != null) {
                kpiDataSource.setViewAttributeName("KpiDataSourceDisplay");
            }
        }
        if ((kpiClass != null) && (kpiClass.equals("KPI"))) {
            OAMessageStyledTextBean ScoreDetail = 
                (OAMessageStyledTextBean)webBean.findChildRecursive("ScoreDetail");

            if (ScoreDetail != null) {
                ScoreDetail.setAttributeValue(UIConstants.DESTINATION_ATTR, 
                                              new OADataBoundValueViewObject(acv, 
                                                                             "ActualValueLink", 
                                                                             "AppraisalKPIVO1"));

                if ((phaseId != null) && (statusId != null) && 
                    ((phaseId.equals("GOAL")) || (phaseId.equals("UPDATE"))) && 
                    (!statusId.trim().equals("COMPLETED"))) {
                    ScoreDetail.setAttributeValue(UIConstants.DESTINATION_ATTR, 
                                                  null);
                }

                ScoreDetail.setRendered(false);
            }
        }
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OATableBean tableBean = 
            (OATableBean)webBean.findChildRecursive("AppraisalKPIVO1");

        String appraisalId = 
            (String)pageContext.getSessionValue("appraisalId");
        String kpiClass = (String)pageContext.getSessionValue("kpiClass");
        String role = (String)pageContext.getSessionValue("role");

        if ((tableBean != null) && 
            (tableBean.getName().equals(pageContext.getParameter("source"))) && 
            ("addRows".equals(pageContext.getParameter("event")))) {
            this.AddNewRow(pageContext, webBean, appraisalId, kpiClass);
        }

        if ("delete".equals(pageContext.getParameter("event"))) {
            String contractId = pageContext.getParameter("contractId");
            OAException mainMessage = 
                new OAException("CUX", "CUX_DELETE_KPI_WARN", null, 
                                OAException.WARNING, null);

            OADialogPage dialogPage = 
                new OADialogPage(OAException.WARNING, mainMessage, null, "", 
                                 "");

            dialogPage.setOkButtonItemName("DeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            Hashtable formParams = new Hashtable(1);
            formParams.put("contractId", contractId);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);
        } else if (pageContext.getParameter("DeleteYesButton") != null) {
            String contractId = pageContext.getParameter("contractId");
            Serializable[] parameters = { contractId };
            am.invokeMethod("delContractContent", parameters);
            OAException message = 
                new OAException("CUX", "CUX_DELETE_KPI_CONFIRM", null, 
                                OAException.CONFIRMATION, null);

            pageContext.putDialogMessage(message);

            pageContext.forwardImmediatelyToCurrentPage(null, true, "N");
        }

        if (pageContext.getParameter("Cancel") != null) {
            am.invokeMethod("rollback");
            TransactionUnitHelper.endTransactionUnit(pageContext, 
                                                     "updateContractKPITxn");

            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG&appraisalId=" + 
                                           appraisalId, null, (byte)0, null, 
                                           null, true, "N");
        }

        if (pageContext.getParameter("Apply") != null) {
            String phaseId = (String)pageContext.getSessionValue("phaseId");
            String kpiClassStdAdd = 
                (String)pageContext.getSessionValue("kpiClassStdAdd");

            String kpiComputeAddSub = 
                (String)pageContext.getSessionValue("kpiComputeAddSub");

            String kpiStdAddWeightMethod = 
                (String)pageContext.getSessionValue("kpiStdAddWeightMethod");

            String kpiClassWeight = 
                (String)pageContext.getSessionValue("kpiClassWeight");

            if ((phaseId != null) && (phaseId.equals("REVIEW"))) {
                if ((role != null) && (role.equals("selfservice")))
                    am.invokeMethod("validateManualKpiSelfScore");
                else if ((role != null) && (role.equals("manager"))) {
                    am.invokeMethod("validateManualKpiScore");
                }
            }
            if ((phaseId != null) && (kpiClassStdAdd != null) && 
                (kpiComputeAddSub != null)) {
                if ((kpiClassStdAdd.equals("STANDARD")) && 
                    (kpiComputeAddSub.equals("ADD"))) {
                    if (phaseId.equals("REVIEW")) {
                        am.invokeMethod("computeStdKPIMark");
                    }

                    if ((kpiStdAddWeightMethod != null) && 
                        (kpiClassWeight != null) && 
                        (!kpiClassWeight.equals(""))) {
                        if ((phaseId.equals("GOAL")) || 
                            (phaseId.equals("UPDATE"))) {
                            Serializable[] params = 
                            { kpiStdAddWeightMethod, kpiClassWeight };

                            am.invokeMethod("checkKpiWeight", params);
                        }
                    } else {
                        throw new OAException("CUX", 
                                              "CUX_KPI_WEIGHT_METHOD_WAR", 
                                              null, OAException.ERROR, null);
                    }

                } else if (phaseId.equals("REVIEW")) {
                    if ((role != null) && (role.equals("manager"))) {
                        am.invokeMethod("computeAddKPIMark");
                    }
                }

            }

            am.invokeMethod("commit");
            OAException confirmMessage = 
                new OAException("CUX", "CUX_SAVE_CONTRACT_CONF", null, 
                                OAException.CONFIRMATION, null);

            pageContext.putDialogMessage(confirmMessage);
            try {
                pageContext.sendRedirect("OA.jsp?page=/cux/oracle/apps/per/review/webui/AppraisalContractPG");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (pageContext.getParameter("Save") != null) {
            am.invokeMethod("commit");

            OAViewObject vo = 
                (OAViewObject)am.findViewObject("AppraisalKPIVO1");

            vo.executeQuery();
        }

        if ("updateContractChild".equals(pageContext.getParameter("event"))) {
            appraisalId = pageContext.getParameter("appraisalId");
            Serializable[] parameters = { appraisalId, kpiClass };
            String kpiClassType = 
                (String)am.invokeMethod("getKpiClassType", parameters);

            if ((kpiClassType != null) && (kpiClassType.equals("Y"))) {
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ContractKPIChildPG", 
                                               null, (byte)0, null, null, true, 
                                               "N");
            } else {
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/review/webui/ContractKPIChildEnterPG", 
                                               null, (byte)0, null, null, true, 
                                               "N");
            }
        }
    }
    /* add new row */

    private void AddNewRow(OAPageContext pageContext, OAWebBean webBean, 
                           String appraisalId, String kpiClass) {
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        OAViewObject vo = (OAViewObject)am.findViewObject("AppraisalKPIVO1");

        if (!vo.isPreparedForExecution())
            vo.executeQuery();

        Row row = vo.createRow();
        int index = vo.getRangeIndexOf(vo.last());
        vo.insertRowAtRangeIndex(index + 1, row);

        row.setNewRowState(Row.STATUS_INITIALIZED);
        Number contractId = 
            am.getOADBTransaction().getSequenceValue("CUX_CONTRACT_S");

        row.setAttribute("ContractId", contractId);
        row.setAttribute("AppraisalId", appraisalId);
        row.setAttribute("KpiClass", kpiClass);
        vo.setCurrentRow(row);
    }

    /* 根据人员身份和绩效合同状态进入不同的页面
     * role: selfservice, manager
     * kpiClass: KPI	  关键绩效指标
                 "TASK"   工作任务
                 SUBTRACT 减分项
                 ADD	  加分项
                "BEHAVIOUR"	行为规范
     * phaseId: GOAL,REVIEW
     * canChange "Y", 操作者为工作流中当前人员; "N", 不是
     * */

    private int GoToWeb(OAPageContext pageContext, String role, String phaseId, 
                        String kpiClass, String canChange) {
        if (!"REVIEW".equals(phaseId))
            return 0;
        if ("".equals(canChange) || "N".equals(canChange)) {
            this.GoToReadOnlyPG(pageContext);
            return 1;
        } else if ("KPI".equals(kpiClass) && "selfservice".equals(role)) {
            this.GoToSelfReivewUpdKPIPG(pageContext);
            return 1;
        } else if ("TASK".equals(kpiClass) && "selfservice".equals(role)) {
            this.GoToSelfReivewUpdTaskPG(pageContext);
            return 1;
        } else if (("SUBTRACT".equals(kpiClass) || "ADD".equals(kpiClass) || 
                    "BEHAVIOUR".equals(kpiClass)) && 
                   "selfservice".equals(role)) {
            this.GoToReadOnlyAddSubPG(pageContext);
            return 1;
        } else if ("KPI".equals(kpiClass) && "manager".equals(role)) {
            this.GoToManagerReivewUpdKPIPG(pageContext);
            return 1;
        } else if ("TASK".equals(kpiClass) && "manager".equals(role)) {
            this.GoToManagerReivewUpdTaskPG(pageContext);
            return 1;
        } else if (("SUBTRACT".equals(kpiClass) || "ADD".equals(kpiClass) || 
                    "BEHAVIOUR".equals(kpiClass)) && "manager".equals(role)) {
            this.GoToManagerReviewUpdAddSubPG(pageContext);
            return 1;
        }
        return 0;
    }

    /* 只读页面 */

    private void GoToReadOnlyPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ACSelfReviewReadOnlyPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 只读页面,加、减分项 */

    private void GoToReadOnlyAddSubPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ACSelfReviewReadOnlyAddSubPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 更新页面,员工自助,关键绩效指标,不能进行新增行  */

    private void GoToSelfReivewUpdKPIPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ACSelfReviewUptKPIPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 更新页面,员工自助,工作任务,不能进行新增行  */

    private void GoToSelfReivewUpdTaskPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ACSelfReviewUptTaskPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 更新页面,经理自助,行为规范，加分项，减分项目,可以进行新增行  */

    private void GoToManagerReviewUpdAddSubPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ACManagerReviewUptAddSubPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 更新页面,经理自助,关键绩效指标,不能进行新增行  */

    private void GoToManagerReivewUpdKPIPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ACManagerReviewUptKPIPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /* 更新页面,员工自助,工作任务,不能进行新增行  */

    private void GoToManagerReivewUpdTaskPG(OAPageContext pageContext) {
        pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/review/webui/ACManagerReviewUptTaskPG", 
                                  null, OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                  null, null, true, 
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

}
