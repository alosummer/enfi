package cux.oracle.apps.pa.docnum.server;

import cux.oracle.apps.pa.util.ApplicationModuleUtil;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;

import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAExceptionUtils;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DocNumReqAMImpl extends ApplicationModuleUtil {
    /**This is the default constructor (do not remove)
     */
    public DocNumReqAMImpl() {
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cux.oracle.apps.pa.docnum.server", 
                                             "DocNumReqAMLocal");
    }

    /**Container's getter for GenDocNumPVO1
     */
    public GenDocNumPVOImpl getGenDocNumPVO1() {
        return (GenDocNumPVOImpl)findViewObject("GenDocNumPVO1");
    }

    /**Container's getter for DocnumReqVO1
     */
    public DocnumReqVOImpl getDocnumReqVO1() {
        return (DocnumReqVOImpl)findViewObject("DocnumReqVO1");
    }

    public void init() {
        GenDocNumPVOImpl pvo = this.getGenDocNumPVO1();
        pvo.setMaxFetchSize(0);
        pvo.executeQuery();
        if (pvo.getFetchedRowCount() == 0) {
            Row row = pvo.createRow();
            row.setNewRowState(Row.STATUS_NEW);
            pvo.insertRow(row);
        }
    }

    public void apply() {
        String sql = "";
        GenDocNumPVOImpl pvo = this.getGenDocNumPVO1();
        GenDocNumPVORowImpl prow = (GenDocNumPVORowImpl)pvo.getCurrentRow();
        sql = 
"SELECT COUNT(0)\n" + "FROM   cux_pa_docnum_req_t pdr\n" + "WHERE  pdr.status NOT IN ('APPROVED','REJECTED')\n" + 
  "AND    pdr.project_id = " + prow.getProjectId().intValue();
        Number unapprovedCount = (Number)this.getSqlValue(sql);
        if (unapprovedCount.intValue() > 0) {
            throw new OAException("错误:你指定的项目已有未审核的批量要号申请，请等待该申请审核后，再新增批量要号申请!", 
                                  OAException.ERROR);
        }
        sql = 
"SELECT COUNT(0)\n" + "FROM   cux_pa_deliverable_t\n" + "WHERE  status = 'APPROVED'\n" + 
  "AND    doc_num IS NULL \n" + "AND    project_id = " + 
  prow.getProjectId().intValue();
        Number approvedDlvCount = (Number)this.getSqlValue(sql);
        if (approvedDlvCount.intValue() == 0) {
            throw new OAException("错误:你指定的项目没有需要要号的文档交付物!", OAException.ERROR);
        }
        //
        DocnumReqVOImpl reqVO = this.getDocnumReqVO1();
        DocnumReqVORowImpl reqRow = (DocnumReqVORowImpl)reqVO.createRow();
        reqRow.setDocnumReqId(this.getOADBTransaction().getSequenceValue("cux_pa_docnum_req_s"));
        reqRow.setReqDesc(prow.getDesc());
        reqRow.setStatus("UNAPPROVED");
        reqRow.setProjectId(prow.getProjectId());
        reqRow.setNewRowState(Row.STATUS_NEW);
        reqVO.insertRow(reqRow);
        getOADBTransaction().commit();
        //
        String applyStmt = 
            "BEGIN\n" + "  cux_pa_docnum_pkg.gen_dlv_doc_nums(p_docnum_req_id => :1);\n" + 
            "END;";
        OADBTransaction trxn = this.getOADBTransaction();
        CallableStatement cbStmt = trxn.createCallableStatement(applyStmt, 1);
        try {
            cbStmt.setInt(1, reqRow.getDocnumReqId().intValue());
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
    }

    /**Container's getter for DocNumStatusVO1
     */
    public DocNumStatusVOImpl getDocNumStatusVO1() {
        return (DocNumStatusVOImpl)findViewObject("DocNumStatusVO1");
    }

    /**Container's getter for DocnumSummaryVO1
     */
    public DocnumSummaryVOImpl getDocnumSummaryVO1() {
        return (DocnumSummaryVOImpl)findViewObject("DocnumSummaryVO1");
    }

    /**Container's getter for DocnumSummaryPVO1
     */
    public DocnumSummaryPVOImpl getDocnumSummaryPVO1() {
        return (DocnumSummaryPVOImpl)findViewObject("DocnumSummaryPVO1");
    }

    public void initSearchPVO() {
        DocnumSummaryPVOImpl pvo = this.getDocnumSummaryPVO1();
        pvo.setMaxFetchSize(0);
        pvo.executeQuery();
        if (pvo.getFetchedRowCount() == 0) {
            Row row = pvo.createRow();
            row.setNewRowState(Row.STATUS_NEW);
            pvo.insertRow(row);
            pvo.setCurrentRow(row);
        }
    }

    public String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate.dateValue());
        return dateString;
    }

    public void doSearch() {
        DocnumSummaryVOImpl summaryVO = this.getDocnumSummaryVO1();
        summaryVO.setWhereClause(null);
        summaryVO.setWhereClauseParams(null);
        DocnumSummaryPVOImpl pvo = this.getDocnumSummaryPVO1();
        DocnumSummaryPVORowImpl prow = 
            (DocnumSummaryPVORowImpl)pvo.getCurrentRow();
        summaryVO.setWhereClause("1=1");
        if (prow.getProjectId() != null) {
            summaryVO.addWhereClause(" AND project_id = " + 
                                     prow.getProjectId().intValue());
        }
        if (prow.getStatus() != null) {
            summaryVO.addWhereClause(" AND status = '" + prow.getStatus() + 
                                     "'");
        }
        if (prow.getDocName() != null) {
            summaryVO.addWhereClause(" AND doc_name = '" + prow.getDocName() + 
                                     "'");
        }
        if (prow.getRequesterID() != null) {
            summaryVO.addWhereClause(" AND created_by = " + 
                                     prow.getRequesterID().intValue());
        }
        if (prow.getReqStartDate() != null) {
            summaryVO.addWhereClause(" AND TRUNC(creation_date) >= TO_DATE('" + 
                                     dateToStr(prow.getReqStartDate()) + 
                                     "','yyyy-mm-dd')");
        }
        if (prow.getReqEndDate() != null) {
            summaryVO.addWhereClause(" AND TRUNC(creation_date) <= TO_DATE('" + 
                                     dateToStr(prow.getReqEndDate()) + 
                                     "','yyyy-mm-dd')");
        }
        summaryVO.setMaxFetchSize(-1);
        summaryVO.executeQuery();
    }

    /**Container's getter for DocnumDetailHeaderVO1
     */
    public DocnumDetailHeaderVOImpl getDocnumDetailHeaderVO1() {
        return (DocnumDetailHeaderVOImpl)findViewObject("DocnumDetailHeaderVO1");
    }

    /**Container's getter for DocnumDetailLineVO1
     */
    public DocnumDetailLineVOImpl getDocnumDetailLineVO1() {
        return (DocnumDetailLineVOImpl)findViewObject("DocnumDetailLineVO1");
    }

    public void initDetail(String reqBatchId) {
        DocnumDetailHeaderVOImpl headerVO = this.getDocnumDetailHeaderVO1();
        headerVO.setMaxFetchSize(-1);
        headerVO.setWhereClause(null);
        headerVO.setWhereClause("docnum_req_id = " + reqBatchId);
        headerVO.executeQuery();
        DocnumDetailLineVOImpl lineVO = this.getDocnumDetailLineVO1();
        lineVO.setMaxFetchSize(-1);
        lineVO.setWhereClause(null);
        lineVO.setWhereClause("docnum_req_id = " + reqBatchId);
        lineVO.executeQuery();
    }

    public void approve(String reqBatchId) {
        this.getOADBTransaction().commit();
        String applyStmt = 
            "BEGIN\n" + "  cux_pa_docnum_pkg.approve(p_docnum_req_id => :1);\n" + 
            "END;";
        OADBTransaction trxn = this.getOADBTransaction();
        CallableStatement cbStmt = trxn.createCallableStatement(applyStmt, 1);
        try {
            cbStmt.setString(1, reqBatchId);
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
    }

    public void reject(String reqBatchId) {
        String applyStmt = 
            "BEGIN\n" + "  cux_pa_docnum_pkg.reject(p_docnum_req_id => :1);\n" + 
            "END;";
        OADBTransaction trxn = this.getOADBTransaction();
        CallableStatement cbStmt = trxn.createCallableStatement(applyStmt, 1);
        try {
            cbStmt.setString(1, reqBatchId);
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
    }

    public void revert(String reqBatchId) {
        String applyStmt = 
            "BEGIN\n" + "  cux_pa_docnum_pkg.revert(p_docnum_req_id => :1);\n" + 
            "END;";
        OADBTransaction trxn = this.getOADBTransaction();
        CallableStatement cbStmt = trxn.createCallableStatement(applyStmt, 1);
        try {
            cbStmt.setString(1, reqBatchId);
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
    }
}
