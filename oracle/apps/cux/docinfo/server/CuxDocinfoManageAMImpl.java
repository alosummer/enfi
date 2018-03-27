package cux.oracle.apps.cux.docinfo.server;

import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;

import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxDocinfoManageAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public CuxDocinfoManageAMImpl() {
    }

    /**Container's getter for CuxDocinfoManageTVO1
     */
    public CuxDocinfoManageTVOImpl getCuxDocinfoManageTVO1() {
        return (CuxDocinfoManageTVOImpl)findViewObject("CuxDocinfoManageTVO1");
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cux.oracle.apps.cux.docinfo.server", 
                                             "CuxDocinfoManageAMLocal");
    }

    /**Container's getter for CuxDocinfoManageResultVO1
     */
    public CuxDocinfoManageResultVOImpl getCuxDocinfoManageResultVO1() {
        return (CuxDocinfoManageResultVOImpl)findViewObject("CuxDocinfoManageResultVO1");
    }

    /**
     * 
     * @param docId
     */
    public void initCreatePG(String docId) {
        CuxDocinfoManageTVOImpl vo = this.getCuxDocinfoManageTVO1();
        vo.setWhereClause(null);
        vo.setWhereClauseParams(null);
        vo.setWhereClause("doc_id = :1");
        vo.setWhereClauseParams(new Object[] { docId });
        vo.executeQuery();
    }


    public void initCreatePG() {
        CuxDocinfoManageTVOImpl vo = this.getCuxDocinfoManageTVO1();
        vo.setWhereClause(null);
        vo.setWhereClauseParams(null);
        vo.setWhereClause("1 = 2");
        vo.executeQuery();
        CuxDocinfoManageTVORowImpl row = 
            (CuxDocinfoManageTVORowImpl)vo.createRow();
        row.setDocId(this.getSequenceValue("CUX_DOCINFO_MANAGE_T_S"));
        row.setCreationDate((Date)Date.getCurrentDate());
        vo.insertRowAtRangeIndex(0, row);
    }

    public void commit() {
        this.getOADBTransaction().commit();
    }

    public void rollback() {
        this.getOADBTransaction().rollback();
    }
}