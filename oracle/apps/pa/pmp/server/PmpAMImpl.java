package cux.oracle.apps.pa.pmp.server;

import com.sun.java.util.collections.ArrayList;

import cux.oracle.apps.pa.dlv.server.DeliverableVOImpl;

import cux.oracle.apps.pa.pmp.webui.PmpConstants;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.util.UUID;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;

import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAExceptionUtils;

import oracle.jbo.Row;
import oracle.jbo.domain.NullValue;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewLinkImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PmpAMImpl extends OAApplicationModuleImpl implements PmpConstants {
    /**This is the default constructor (do not remove)
     */
    public PmpAMImpl() {
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cux.oracle.apps.pa.pmp.server", 
                                             "PmpAMLocal");
    }

    /**Container's getter for PmpSummaryVO1
     */
    public PmpSummaryVOImpl getPmpSummaryVO1() {
        return (PmpSummaryVOImpl)findViewObject("PmpSummaryVO1");
    }

    public void initPmpSummary(String projectId, String versionNum) {
        PmpSummaryVOImpl summaryVO = getPmpSummaryVO1();
        summaryVO.setWhereClause(null);
        summaryVO.setMaxFetchSize(-1);
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        summaryVO.setWhereClause(whereClause.toString());
        summaryVO.executeQuery();
        if (summaryVO.getRowCount() == 0) {
            Row row = summaryVO.createRow();
            row.setAttribute("ProjectId", projectId);
            row.setAttribute("VersionNum", versionNum);
            row.setNewRowState(Row.STATUS_INITIALIZED);
            summaryVO.insertRow(row);
        }
    }

    /**Container's getter for PmpCoordinationVO1
     */
    public PmpCoordinationVOImpl getPmpCoordinationVO1() {
        return (PmpCoordinationVOImpl)findViewObject("PmpCoordinationVO1");
    }

    public void initPmpCoordination(String projectId, String versionNum) {
        PmpCoordinationVOImpl coordinationVO = getPmpCoordinationVO1();
        coordinationVO.setWhereClause(null);
        coordinationVO.setMaxFetchSize(-1);
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        coordinationVO.setWhereClause(whereClause.toString());
        coordinationVO.executeQuery();
    }

    /**Container's getter for PmpVO1
     */
    public PmpVOImpl getPmpVO1() {
        return (PmpVOImpl)findViewObject("PmpVO1");
    }

    public void searchPmp(String projectId) {
        PmpVOImpl pmpVO = this.getPmpVO1();
        pmpVO.setWhereClause(null);
        pmpVO.setMaxFetchSize(-1);
        StringBuilder whereCaulse = new StringBuilder();
        whereCaulse.append(" 1 = 1");
        whereCaulse.append(" AND project_id = " + projectId);
        pmpVO.setWhereClause(whereCaulse.toString());
        pmpVO.executeQuery();
    } 

    public void createPmpNewVerion(String projectId, String statement) {
        OADBTransaction trxn = this.getOADBTransaction();
        
        CallableStatement cbStmt = trxn.createCallableStatement(statement, 1);
        try {
            cbStmt.setString(1, projectId);
            cbStmt.execute();
            cbStmt.close();
            OAExceptionUtils.checkErrors(trxn);
        } catch (SQLException sqle) {
            try {
                cbStmt.close();
            } catch (SQLException e) {
            }
            throw OAException.wrapperException(sqle);
        }
        trxn.commit();
        searchPmp(projectId);
    }


    /**Container's getter for PmpMeetingVO1
     */
    public PmpMeetingVOImpl getPmpMeetingVO1() {
        return (PmpMeetingVOImpl)findViewObject("PmpMeetingVO1");
    }

    /**Container's getter for PmpReportVO1
     */
    public PmpReportVOImpl getPmpReportVO1() {
        return (PmpReportVOImpl)findViewObject("PmpReportVO1");
    }

    public void initPmpAction(String projectId, String versionNum) {
        PmpMeetingVOImpl meetingVO = getPmpMeetingVO1();
        meetingVO.setWhereClause(null);
        meetingVO.setMaxFetchSize(-1);
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        meetingVO.setWhereClause(whereClause.toString());
        meetingVO.executeQuery();
        PmpReportVOImpl reportVO = getPmpReportVO1();
        reportVO.setWhereClause(null);
        reportVO.setMaxFetchSize(-1);
        reportVO.setWhereClause(whereClause.toString());
        reportVO.executeQuery();
    }
    
    public void addMeeting(String projectId, String versionNum) {
        PmpMeetingVOImpl pmpMeetingVO = this.getPmpMeetingVO1();
        Row row = pmpMeetingVO.createRow();
        row.setAttribute("ProjectId", projectId);
        row.setAttribute("VersionNum", versionNum);
        row.setAttribute("SortSeq",pmpMeetingVO.getRowCount()+1);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        pmpMeetingVO.insertRowAtRangeIndex(pmpMeetingVO.getRowCount(), row);
    }
    
    public void addReport(String projectId, String versionNum) {
        PmpReportVOImpl pmpReportVO = this.getPmpReportVO1();
        Row row = pmpReportVO.createRow();
        row.setAttribute("ProjectId", projectId);
        row.setAttribute("VersionNum", versionNum);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        pmpReportVO.insertRowAtRangeIndex(pmpReportVO.getRowCount(), row);
    }


    /**Container's getter for PmpConractFirstVO1
     */
    public PmpContactFirstVOImpl getPmpConractFirstVO1() {
        return (PmpContactFirstVOImpl)findViewObject("PmpConractFirstVO1");
    }

    /**Container's getter for PmpContractSecondVO1
     */
    public PmpContactSecondVOImpl getPmpContractSecondVO1() {
        return (PmpContactSecondVOImpl)findViewObject("PmpContractSecondVO1");
    }

    /**Container's getter for PmpContractSiteFirstVO1
     */
    public PmpContactSiteFirstVOImpl getPmpContractSiteFirstVO1() {
        return (PmpContactSiteFirstVOImpl)findViewObject("PmpContractSiteFirstVO1");
    }

    /**Container's getter for PmpContractSiteSecondVO1
     */
    public PmpContactSiteSecondVOImpl getPmpContractSiteSecondVO1() {
        return (PmpContactSiteSecondVOImpl)findViewObject("PmpContractSiteSecondVO1");
    }

    public void initPmpContractSite(String projectId, String versionNum) {
        PmpContactSiteFirstVOImpl firstVO = this.getPmpContractSiteFirstVO1();
        PmpContactSiteSecondVOImpl secondVO = 
            this.getPmpContractSiteSecondVO1();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        firstVO.setMaxFetchSize(-1);
        firstVO.setWhereClause(null);
        firstVO.setWhereClause(whereClause.toString());
        firstVO.executeQuery();
        /*
        if (firstVO.getFetchedRowCount() == 0) {
            Row row = firstVO.createRow();
            row.setAttribute("ProjectId",projectId);
            row.setAttribute("VersionNum",versionNum);
            row.setAttribute("SiteType","FIRST_PARTY");
            row.setNewRowState(Row.STATUS_INITIALIZED);
            firstVO.insertRow(row);
        }
        */
        secondVO.setMaxFetchSize(-1);
        secondVO.setWhereClause(null);
        secondVO.setWhereClause(whereClause.toString());
        secondVO.executeQuery();
        /*
        if (secondVO.getRowCount() == 0) {
            Row row = secondVO.createRow();
            row.setAttribute("ProjectId",projectId);
            row.setAttribute("VersionNum",versionNum);
            row.setAttribute("SiteType","SECOND_PARTY");
            row.setNewRowState(Row.STATUS_INITIALIZED);
            secondVO.insertRow(row);
        }
        */
    }

    public void addContactSiteFirst(String projectId, String versionNum) {
        PmpContactSiteFirstVOImpl firstVO = this.getPmpContractSiteFirstVO1();
        Row row = firstVO.createRow();
        row.setAttribute("ProjectId", projectId);
        row.setAttribute("VersionNum", versionNum);
        row.setAttribute("SiteType", "FIRST_PARTY");
        row.setNewRowState(Row.STATUS_INITIALIZED);
        firstVO.insertRow(row);
    }

    public void addContactSiteSecond(String projectId, String versionNum) {
        PmpContactSiteSecondVOImpl secondVO = 
            this.getPmpContractSiteSecondVO1();
        Row row = secondVO.createRow();
        row.setAttribute("ProjectId", projectId);
        row.setAttribute("VersionNum", versionNum);
        row.setAttribute("SiteType", "SECOND_PARTY");
        row.setNewRowState(Row.STATUS_INITIALIZED);
        secondVO.insertRow(row);
    }

    public void initPmpContract(String projectId, String versionNum) {
        PmpContactFirstVOImpl firstVO = this.getPmpConractFirstVO1();
        PmpContactSecondVOImpl secondVO = this.getPmpContractSecondVO1();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        firstVO.setMaxFetchSize(-1);
        firstVO.setWhereClause(null);
        firstVO.setWhereClause(whereClause.toString());
        firstVO.executeQuery();
        secondVO.setMaxFetchSize(-1);
        secondVO.setWhereClause(null);
        secondVO.setWhereClause(whereClause.toString());
        secondVO.executeQuery();
    }

    public void addFirstContactParty(String projectId, String versionNum) {
        PmpContactFirstVOImpl firstVO = this.getPmpConractFirstVO1();
        Row row = firstVO.createRow();
        row.setAttribute("ProjectId", projectId);
        row.setAttribute("VersionNum", versionNum);
        row.setAttribute("SeqNum", firstVO.getRowCount() + 1);
        row.setAttribute("ContractType", "FIRST_PARTY");
        row.setNewRowState(Row.STATUS_INITIALIZED);
        //firstVO.insertRow(row);
        firstVO.insertRowAtRangeIndex(firstVO.getRowCount(), row);
    }

    public void addSecondContactParty(String projectId, String versionNum) {
        PmpContactSecondVOImpl secondVO = this.getPmpContractSecondVO1();
        Row row = secondVO.createRow();
        row.setAttribute("ProjectId", projectId);
        row.setAttribute("VersionNum", versionNum);
        row.setAttribute("SeqNum", secondVO.getRowCount() + 1);
        row.setAttribute("ContractType", "SECOND_PARTY");
        row.setNewRowState(Row.STATUS_INITIALIZED);
        //secondVO.insertRow(row);        
        secondVO.insertRowAtRangeIndex(secondVO.getRowCount(), row);
    }

    /**Container's getter for PmpLogisticalVO1
     */
    public PmpLogisticalVOImpl getPmpLogisticalVO1() {
        return (PmpLogisticalVOImpl)findViewObject("PmpLogisticalVO1");
    }

    public void initPmpLogistical(String projectId, String versionNum) {
        PmpLogisticalVOImpl logisticalVO = this.getPmpLogisticalVO1();
        logisticalVO.setMaxFetchSize(-1);
        logisticalVO.setWhereClause(null);
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        logisticalVO.setWhereClause(whereClause.toString());
        logisticalVO.executeQuery();
    }

    public void addPmpLogistical(String projectId, String versionNum) {
        PmpLogisticalVOImpl logisticalVO = this.getPmpLogisticalVO1();
        Row row = logisticalVO.createRow();
        row.setAttribute("ProjectId", projectId);
        row.setAttribute("VersionNum", versionNum);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        logisticalVO.insertRowAtRangeIndex(logisticalVO.getRowCount(), row);
    }

    /**Container's getter for PmpQhseHseHeaderVO1
     */
    public PmpQhseHseHeaderVOImpl getPmpQhseHseHeaderVO1() {
        return (PmpQhseHseHeaderVOImpl)findViewObject("PmpQhseHseHeaderVO1");
    }

    /**Container's getter for PmpQhseHseLineVO1
     */
    public PmpQhseHseLineVOImpl getPmpQhseHseLineVO1() {
        return (PmpQhseHseLineVOImpl)findViewObject("PmpQhseHseLineVO1");
    }

    /**Container's getter for PmpQhseQualityHeaerVO1
     */
    public PmpQhseQualityHeaerVOImpl getPmpQhseQualityHeaerVO1() {
        return (PmpQhseQualityHeaerVOImpl)findViewObject("PmpQhseQualityHeaerVO1");
    }

    /**Container's getter for PmpQhseQualityLineVO1
     */
    public PmpQhseQualityLineVOImpl getPmpQhseQualityLineVO1() {
        return (PmpQhseQualityLineVOImpl)findViewObject("PmpQhseQualityLineVO1");
    }

    public void initPmpQhseQuality(String projectId, String versionNum) {
        PmpQhseQualityHeaerVOImpl qualityHeaderVO = 
            this.getPmpQhseQualityHeaerVO1();
        PmpQhseQualityLineVOImpl qualityLineVO = 
            this.getPmpQhseQualityLineVO1();
        qualityHeaderVO.setMaxFetchSize(-1);
        qualityHeaderVO.setWhereClause(null);
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        qualityHeaderVO.setWhereClause(whereClause.toString());
        qualityHeaderVO.executeQuery();
        qualityLineVO.setMaxFetchSize(-1);
        qualityLineVO.setWhereClause(null);
        qualityLineVO.setWhereClause(whereClause.toString());
        qualityLineVO.executeQuery();
    }

    public void initPmpQhseHse(String projectId, String versionNum) {
        PmpQhseHseHeaderVOImpl hseHeaderVO = this.getPmpQhseHseHeaderVO1();
        PmpQhseHseLineVOImpl hseLineVO = this.getPmpQhseHseLineVO1();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        hseHeaderVO.setMaxFetchSize(-1);
        hseHeaderVO.setWhereClause(null);
        hseHeaderVO.setWhereClause(whereClause.toString());
        hseHeaderVO.executeQuery();
        hseLineVO.setMaxFetchSize(-1);
        hseLineVO.setWhereClause(null);
        hseLineVO.setWhereClause(whereClause.toString());
        hseLineVO.executeQuery();
    }

    /**Container's getter for PmpRiskVO1
     */
    public PmpRiskVOImpl getPmpRiskVO1() {
        return (PmpRiskVOImpl)findViewObject("PmpRiskVO1");
    }

    public void initPmpRisk(String projectId, String versionNum) {
        PmpRiskVOImpl riskVO = this.getPmpRiskVO1();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        riskVO.setMaxFetchSize(-1);
        riskVO.setWhereClause(null);
        riskVO.setWhereClause(whereClause.toString());
        riskVO.executeQuery();
    }

    public void addPmpRisk(String projectId, String versionNum) {
        PmpRiskVOImpl riskVO = this.getPmpRiskVO1();
        Row row = riskVO.createRow();
        row.setAttribute("ProjectId", projectId);
        row.setAttribute("VersionNum", versionNum);
        row.setAttribute("SeqNum", riskVO.getRowCount() + 1);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        riskVO.insertRowAtRangeIndex(riskVO.getRowCount(), row);
    }

    /**Container's getter for PmpDocManagementVO1
     */
    public PmpDocManagementVOImpl getPmpDocManagementVO1() {
        return (PmpDocManagementVOImpl)findViewObject("PmpDocManagementVO1");
    }

    public void initPmpDocManagement(String projectId, String versionNum) {
        PmpDocManagementVOImpl docManagementVO = this.getPmpDocManagementVO1();
        docManagementVO.setMaxFetchSize(-1);
        docManagementVO.setWhereClause(null);
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        whereClause.append(" AND version_num = " + versionNum);
        docManagementVO.setWhereClause(whereClause.toString());
        docManagementVO.executeQuery();
    }

    /**Container's getter for PmpRoleVO1
     */
    public PmpRoleVOImpl getPmpRoleVO1() {
        return (PmpRoleVOImpl)findViewObject("PmpRoleVO1");
    }

    public void initPmpRole(String projectId) {
        PmpRoleVOImpl roleVO = this.getPmpRoleVO1();
        roleVO.setMaxFetchSize(-1);
        roleVO.setWhereClause(null);
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(" 1 = 1");
        whereClause.append(" AND project_id = " + projectId);
        roleVO.setWhereClause(whereClause.toString());
        roleVO.executeQuery();
    }

    /**Container's getter for AMEActionHistoryVO1
     */
    public AMEActionHistoryVOImpl getAMEActionHistoryVO1() {
        return (AMEActionHistoryVOImpl)findViewObject("AMEActionHistoryVO1");
    }

    /**Container's getter for AMEActionHistoryVO2
     */
    public AMEActionHistoryVOImpl getAMEActionHistoryVO2() {
        return (AMEActionHistoryVOImpl)findViewObject("AMEActionHistoryVO2");
    }

    /**Container's getter for PmpStatusVO1
     */
    public PmpStatusVOImpl getPmpStatusVO1() {
        return (PmpStatusVOImpl)findViewObject("PmpStatusVO1");
    }

    public void initApprsListQuery(String transactionId, 
                                   String transactionNumber) {
        PmpStatusVOImpl statusVO = this.getPmpStatusVO1();
        PmpStatusVORowImpl statusRow = (PmpStatusVORowImpl)statusVO.first();
        String verifyStatus = statusRow.getVerifyStatus();
        String approveStatus = statusRow.getApproveStatus();
        if ("UNVERIFIED".equals(verifyStatus) || 
            "VERIFYING".equals(verifyStatus)) {
            OADBTransaction trxn = getOADBTransaction();
            CallableStatement cbStmt = 
                trxn.createCallableStatement(SMT_AME_LISTQUERY, 1);
            try {
                cbStmt.setString(1, TRANSACTION_TYPE_VERIFY);
                cbStmt.setString(2, transactionId);
                cbStmt.setString(3, transactionNumber);
                cbStmt.execute();
                cbStmt.close();
                OAExceptionUtils.checkErrors(trxn);
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw OAException.wrapperException(sqle);
            }
            trxn.commit();
        } else {
            OADBTransaction trxn = getOADBTransaction();
            CallableStatement cbStmt = 
                trxn.createCallableStatement(SMT_AME_UPDATELISTQUERY, 1);
            try {
                cbStmt.setString(1, TRANSACTION_TYPE_VERIFY);
                cbStmt.setString(2, transactionId);
                cbStmt.setString(3, transactionNumber);
                cbStmt.execute();
                cbStmt.close();
                OAExceptionUtils.checkErrors(trxn);
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw OAException.wrapperException(sqle);
            }
            trxn.commit();
        }
        if ("UNAPPROVED".equals(approveStatus) || 
            "APPROVING".equals(approveStatus)) {
            OADBTransaction trxn = getOADBTransaction();
            CallableStatement cbStmt = 
                trxn.createCallableStatement(SMT_AME_LISTQUERY, 1);
            try {
                cbStmt.setString(1, TRANSACTION_TYPE_APPROVE);
                cbStmt.setString(2, transactionId);
                cbStmt.setString(3, transactionNumber);
                cbStmt.execute();
                cbStmt.close();
                OAExceptionUtils.checkErrors(trxn);
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw OAException.wrapperException(sqle);
            }
            trxn.commit();
        } else {
            OADBTransaction trxn = getOADBTransaction();
            CallableStatement cbStmt = 
                trxn.createCallableStatement(SMT_AME_UPDATELISTQUERY, 1);
            try {
                cbStmt.setString(1, TRANSACTION_TYPE_APPROVE);
                cbStmt.setString(2, transactionId);
                cbStmt.setString(3, transactionNumber);
                cbStmt.execute();
                cbStmt.close();
                OAExceptionUtils.checkErrors(trxn);
            } catch (SQLException sqle) {
                try {
                    cbStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw OAException.wrapperException(sqle);
            }
            trxn.commit();
        }
    }

    public void initPmpStatus(String pmpId) {
        PmpStatusVOImpl statusVO = this.getPmpStatusVO1();
        statusVO.setMaxFetchSize(-1);
        statusVO.setWhereClause(null);
        statusVO.setWhereClause(" PMP_ID = " + pmpId);
        statusVO.executeQuery();
    }

    public void initActionHistory(String transactionId, 
                                  String transactionNumber) {
        AMEActionHistoryVOImpl verifyActionVO = this.getAMEActionHistoryVO1();
        AMEActionHistoryVOImpl approveActionVO = this.getAMEActionHistoryVO2();
        verifyActionVO.setMaxFetchSize(-1);
        verifyActionVO.setWhereClause(null);
        verifyActionVO.setitemkey(this.TRANSACTION_TYPE_VERIFY + "-" + 
                                  transactionNumber + "(" + transactionId + 
                                  ")");
        verifyActionVO.executeQuery();
        approveActionVO.setMaxFetchSize(-1);
        approveActionVO.setWhereClause(null);
        approveActionVO.setitemkey(TRANSACTION_TYPE_APPROVE + "-" + 
                                   transactionNumber + "(" + transactionId + 
                                   ")");
        approveActionVO.executeQuery();
    }


    public void initProgress(String projectId, String versionNum) {
        PmpProgressVOImpl progressVO = this.getPmpProgressVO1();
        progressVO.setWhereClause(null);
        progressVO.setWhereClause("project_id = " + projectId);
        progressVO.setMaxFetchSize(-1);
        progressVO.executeQuery();
    }

    /**Container's getter for PmpProgressVO1
     */
    public PmpProgressVOImpl getPmpProgressVO1() {
        return (PmpProgressVOImpl)findViewObject("PmpProgressVO1");
    }

    /**Container's getter for PmpReceiptPlanVO1
     */
    public PmpReceiptPlanVOImpl getPmpReceiptPlanVO1() {
        return (PmpReceiptPlanVOImpl)findViewObject("PmpReceiptPlanVO1");
    }

    public void initReceiptPlan(String projectId, String versionNum) {
        PmpReceiptPlanVOImpl receiptPlanVO = this.getPmpReceiptPlanVO1();
        receiptPlanVO.setMaxFetchSize(-1);
        receiptPlanVO.setWhereClause(null);
        receiptPlanVO.setWhereClause("project_id = " + projectId);
        receiptPlanVO.executeQuery();
    }

    /**Container's getter for PmpManHourVO1
     */
    public PmpManHourVOImpl getPmpManHourVO1() {
        return (PmpManHourVOImpl)findViewObject("PmpManHourVO1");
    }

    public void initMainHour(String projectId, String versionNum) {
        PmpManHourVOImpl manhourVO = this.getPmpManHourVO1();
        manhourVO.setMaxFetchSize(-1);
        manhourVO.setWhereClause(null);
        manhourVO.setWhereClause("project_id = " + projectId + 
                                 " AND version_num = " + versionNum);
        manhourVO.executeQuery();
    }

    /**Container's getter for PmpBudgetDetailVO1
     */
    public PmpBudgetDetailVOImpl getPmpBudgetDetailVO1() {
        return (PmpBudgetDetailVOImpl)findViewObject("PmpBudgetDetailVO1");
    }

    public void initBudgetDetail(String projectId, String versionNum) {
        PmpBudgetDetailVOImpl budgetDetailVO = this.getPmpBudgetDetailVO1();
        budgetDetailVO.setMaxFetchSize(-1);
        budgetDetailVO.setWhereClause(null);
        budgetDetailVO.setWhereClause("project_id = " + projectId + 
                                      " AND version_num = " + versionNum);
        budgetDetailVO.executeQuery();
    }

    /**Container's getter for PmpProjectInfoVO1
     */
    public PmpProjectInfoVOImpl getPmpProjectInfoVO1() {
        return (PmpProjectInfoVOImpl)findViewObject("PmpProjectInfoVO1");
    }

    public void initProjectInfo(String projectId) {
        PmpProjectInfoVOImpl projectInfoVO = this.getPmpProjectInfoVO1();
        projectInfoVO.setMaxFetchSize(-1);
        projectInfoVO.setWhereClause(null);
        projectInfoVO.setWhereClause("project_id = " + projectId);
        projectInfoVO.executeQuery();
    }

    /**Container's getter for PmpAttatchmentVO1
     */
    public PmpAttatchmentVOImpl getPmpAttatchmentVO1() {
        return (PmpAttatchmentVOImpl)findViewObject("PmpAttatchmentVO1");
    }

    public void initPmpAttachment(String pmpId) {
        PmpAttatchmentVOImpl attachVO = this.getPmpAttatchmentVO1();
        attachVO.setMaxFetchSize(-1);
        attachVO.setWhereClause(null);
        attachVO.setWhereClause("pmp_id = " + pmpId);
        attachVO.executeQuery();
    } 

    public void deletePmpVersion(String projectId, String versionNum) {
        String statement = 
            "BEGIN " + " cux_pa_pmp_pkg.delete_pmp_version(p_project_id => :1,p_version_num => :2); " + 
            "END;";
        OADBTransaction trxn = this.getOADBTransaction();
        CallableStatement cbStmt = trxn.createCallableStatement(statement, 1);
        try {
            cbStmt.setString(1, projectId);
            cbStmt.setString(2, versionNum);
            cbStmt.execute();
            cbStmt.close();
            OAExceptionUtils.checkErrors(trxn);
        } catch (SQLException sqle) {
            try {
                cbStmt.close();
            } catch (SQLException e) {
            }
            throw OAException.wrapperException(sqle);
        }
        trxn.commit();
        searchPmp(projectId);
    }

    public void addCoordination(String projectId, String versionNum) {
        Number pid = null;
        try {
            pid = new Number(projectId);
        } catch (SQLException e) {
            // TODO
        }
        Number vnum = null;
        try {
            vnum = new Number(versionNum);
        } catch (SQLException e) {
            // TODO
        }
        PmpCoordinationVOImpl cvo = this.getPmpCoordinationVO1();
        Row row = cvo.createRow();
        row.setAttribute("ProjectId", pid);
        row.setAttribute("VersionNum", vnum);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        cvo.insertRow(row);
    }


}