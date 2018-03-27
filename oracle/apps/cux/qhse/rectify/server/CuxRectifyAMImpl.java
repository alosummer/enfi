package cux.oracle.apps.cux.qhse.rectify.server;

import cux.oracle.apps.cux.qhse.rectify.lov.server.EmployeeLovVOImpl;
import cux.oracle.apps.cux.qhse.rectify.lov.server.ProjectLovVOImpl;

import java.sql.CallableStatement;

import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;

import oracle.apps.fnd.framework.server.OADBTransaction;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewLinkImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxRectifyAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public CuxRectifyAMImpl() {
    }

    /**Container's getter for CuxRectifyNotifyHeadersVO1
     */
    public CuxRectifyNotifyHeadersVOImpl getCuxRectifyNotifyHeadersVO1() {
        return (CuxRectifyNotifyHeadersVOImpl)findViewObject("CuxRectifyNotifyHeadersVO1");
    }

    /**Container's getter for CuxRectifyNotifyLinesVO1
     */
    public CuxRectifyNotifyLinesVOImpl getCuxRectifyNotifyLinesVO1() {
        return (CuxRectifyNotifyLinesVOImpl)findViewObject("CuxRectifyNotifyLinesVO1");
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cux.oracle.apps.cux.qhse.rectify.server", 
                                             "CuxRectifyAMLocal");
    }

    /**
     * 
     * @param headerId
     */
    public void startWorkFlow(Number headerId) {

        OADBTransaction tx = this.getOADBTransaction();
        CallableStatement cs = 
            tx.createCallableStatement("begin cux_recti_approval_pkg.start_rectify_process(:1);end;", 
                                       1);
        try {
            cs.setInt(1, headerId.intValue());
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 
     * @param orgId
     */
    public void initCreatePG(int orgId) {
        CuxRectifyNotifyHeadersVOImpl headerVO = 
            this.getCuxRectifyNotifyHeadersVO1();
        headerVO.setWhereClause("1 = 2");
        headerVO.executeQuery();

        PersonQueryVOImpl createrNameVO = this.getPersonQueryVO1();
        createrNameVO.executeQuery();
        PersonQueryVORowImpl personRow = 
            (PersonQueryVORowImpl)createrNameVO.first();


        CuxRectifyNotifyHeadersVORowImpl headerRow = 
            (CuxRectifyNotifyHeadersVORowImpl)headerVO.createRow();
        headerRow.setHeaderId(this.getSequenceValue("CUX_RECTIFY_NOTIFY_HEADERS_S"));
        headerRow.setCreatePerson(personRow.getCreatePersonName());
        headerRow.setOrgId(new Number(orgId));
        headerRow.setDocType("PROJECT");
        headerRow.setProjectReadonlyFlag(Boolean.FALSE);
        headerRow.setProjectRequiredFlag("yes");

        headerVO.insertRow(headerRow);
        headerVO.setCurrentRow(headerRow);


        CuxRectifyNotifyLinesVOImpl lineVO = 
            this.getCuxRectifyNotifyLinesVO1();
        lineVO.setWhereClause("1 = 2");
        lineVO.executeQuery();

    }

    /**
     * 
     * @param orgId
     * @param submitHeaderId
     */
    public void initCreatePG(int orgId, String submitHeaderId) {
        CuxRectifyNotifyHeadersVOImpl headerVO = 
            this.getCuxRectifyNotifyHeadersVO1();
        headerVO.setWhereClause(null);
        headerVO.setWhereClauseParams(null);
        headerVO.setWhereClause("header_id = :1");
        headerVO.setWhereClauseParams(new Object[] { submitHeaderId });
        headerVO.executeQuery();

        CuxRectifyNotifyHeadersVORowImpl headerRow = 
            (CuxRectifyNotifyHeadersVORowImpl)headerVO.first();
        headerVO.setCurrentRow(headerRow);


        CuxRectifyNotifyLinesVOImpl lineVO = 
            this.getCuxRectifyNotifyLinesVO1();
        lineVO.setWhereClause(null);
        lineVO.setWhereClauseParams(null);
        lineVO.setWhereClause("header_id = :1");
        lineVO.setWhereClauseParams(new Object[] { submitHeaderId });
        lineVO.executeQuery();

    }

    public void createLineRow() {
        CuxRectifyNotifyHeadersVOImpl headerVO = 
            this.getCuxRectifyNotifyHeadersVO1();
        CuxRectifyNotifyHeadersVORowImpl headerRow = 
            (CuxRectifyNotifyHeadersVORowImpl)headerVO.first();

        this.getProjectLovVO_ForQuery().setWhereClause(null);
        this.getProjectLovVO_ForQuery().setWhereClauseParams(null);
        this.getProjectLovVO_ForQuery().setWhereClause("project_id = :1");
        this.getProjectLovVO_ForQuery().setWhereClauseParams(new Object[] { headerRow.getProjectId() });
        this.getProjectLovVO_ForQuery().executeQuery();


        CuxRectifyNotifyLinesVOImpl lineVO = 
            this.getCuxRectifyNotifyLinesVO1();
        CuxRectifyNotifyLinesVORowImpl lineRow = 
            (CuxRectifyNotifyLinesVORowImpl)lineVO.createRow();
        lineRow.setLineId(this.getSequenceValue("CUX_RECTIFY_NOTIFY_LINES_S"));
        lineRow.setHeaderId(headerRow.getHeaderId());
        Row prjRow = this.getProjectLovVO_ForQuery().first();
        if (prjRow != null) {
            lineRow.setProDutyPersonId((Number)this.getProjectLovVO_ForQuery().first().getAttribute("ProjectManagerId"));
            lineRow.setProDutyPersonName((String)this.getProjectLovVO_ForQuery().first().getAttribute("ProjectManagerName"));
        }

        lineVO.insertRowAtRangeIndex(0, lineRow);
    }

    public void commit() {
        this.getOADBTransaction().commit();
    }

    public void rollback() {
        this.getOADBTransaction().rollback();
    }

    /**Container's getter for PersonQueryVO1
     */
    public PersonQueryVOImpl getPersonQueryVO1() {
        return (PersonQueryVOImpl)findViewObject("PersonQueryVO1");
    }


    /**Container's getter for CuxRectifyNotifyHeaderResultVO1
     */
    public CuxRectifyNotifyHeaderResultVOImpl getCuxRectifyNotifyHeaderResultVO1() {
        return (CuxRectifyNotifyHeaderResultVOImpl)findViewObject("CuxRectifyNotifyHeaderResultVO1");
    }

    /**Container's getter for CuxRectifyNotifyLinesResultVO1
     */
    public CuxRectifyNotifyLinesResultVOImpl getCuxRectifyNotifyLinesResultVO1() {
        return (CuxRectifyNotifyLinesResultVOImpl)findViewObject("CuxRectifyNotifyLinesResultVO1");
    }

    /**Container's getter for HeaderResultTOLineVL1
     */
    public ViewLinkImpl getHeaderResultTOLineVL1() {
        return (ViewLinkImpl)findViewLink("HeaderResultTOLineVL1");
    }

    /**Container's getter for CuxRectifyNotifyHeaderResultVO2
     */
    public CuxRectifyNotifyHeaderResultVOImpl getCuxRectifyNotifyHeaderResultVO2() {
        return (CuxRectifyNotifyHeaderResultVOImpl)findViewObject("CuxRectifyNotifyHeaderResultVO2");
    }

    /**Container's getter for CuxRectifyNotifyLinesResultVO2
     */
    public CuxRectifyNotifyLinesResultVOImpl getCuxRectifyNotifyLinesResultVO2() {
        return (CuxRectifyNotifyLinesResultVOImpl)findViewObject("CuxRectifyNotifyLinesResultVO2");
    }

    /**Container's getter for HeaderResultTOLineVL2
     */
    public ViewLinkImpl getHeaderResultTOLineVL2() {
        return (ViewLinkImpl)findViewLink("HeaderResultTOLineVL2");
    }

    /**Container's getter for ProjectLovVO_ForQuery
     */
    public ProjectLovVOImpl getProjectLovVO_ForQuery() {
        return (ProjectLovVOImpl)findViewObject("ProjectLovVO_ForQuery");
    }

    /**Container's getter for EmployeeLovVO1
     */
    public EmployeeLovVOImpl getEmployeeLovVO1() {
        return (EmployeeLovVOImpl)findViewObject("EmployeeLovVO1");
    }
}