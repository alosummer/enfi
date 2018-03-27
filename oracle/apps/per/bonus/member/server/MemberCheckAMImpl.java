/*    */package cux.oracle.apps.per.bonus.member.server;
/*    */
/*    */import oracle.apps.fnd.framework.OAViewObject;
/*    */
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
/*    */
/*    */public class MemberCheckAMImpl extends OAApplicationModuleImpl {
    /*    */

    public static void main(String[] args) {
        /* 19 */launchTester("cux.oracle.apps.per.bonus.member.server", 
                             "MemberCheckAMLocal");
        /*    */
    }
    /*    */
    /*    */

    public ProjectInfoVOImpl getProjectInfoVO1() {
        /* 27 */return (ProjectInfoVOImpl)findViewObject("ProjectInfoVO1");
        /*    */
    }
    /*    */
    /*    */

    public void createProjectInfo(String p_Project_Id) {
        /* 32 */OAViewObject vo = (OAViewObject)getProjectInfoVO1();
        /* 33 */String whereStr = "project_id = " + p_Project_Id;
        /* 34 */vo.setWhereClause(whereStr);
        /* 35 */vo.executeQuery();
        /*    */
    }
    /*    */
    /*    */

    public void createApproved(String p_Project_Id) {
        /* 42 */OAViewObject vo = (OAViewObject)getApprovedVO1();
        /* 43 */String whereStr = "project_id = " + p_Project_Id;
        /* 44 */vo.setWhereClause(whereStr);
        /* 45 */vo.executeQuery();
        /*    */
    }
    /*    */
    /*    */

    public void createSubmit(String p_Project_Id) {
        /* 53 */OAViewObject vo = (OAViewObject)getNewVO1();
        /* 54 */String whereStr = "project_id = " + p_Project_Id;
        /* 55 */vo.setWhereClause(whereStr);
        /* 56 */vo.executeQuery();
        /*    */
    }
    /*    */
    /*    */

    public ApprovedVOImpl getApprovedVO1() {
        /* 65 */return (ApprovedVOImpl)findViewObject("ApprovedVO1");
        /*    */
    }
    /*    */
    /*    */

    public NewVOImpl getNewVO1() {
        /* 71 */return (NewVOImpl)findViewObject("NewVO1");
        /*    */
    }
    /*    */
    /*    */

    public MemberCheckBudgetVOImpl getMemberCheckBudgetVO1() {
        /* 78 */return (MemberCheckBudgetVOImpl)findViewObject("MemberCheckBudgetVO1");
        /*    */
    }
    /*    */
    /*    */

    public ProcesingVOImpl getProcesingVO1() {
        /* 84 */return (ProcesingVOImpl)findViewObject("ProcesingVO1");
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\member\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.member.server.MemberCheckAMImpl
 * JD-Core Version:    0.6.1
 */