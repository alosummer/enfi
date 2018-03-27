package cux.oracle.apps.pa.mh.lov.server;

import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class MHAssignLovAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public MHAssignLovAMImpl() {
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cux.oracle.apps.pa.mh.lov.server", 
                                             "MHAssignLovAMLocal");
    }

    /**Container's getter for ProjectLovVO1
     */
    public ProjectLovVOImpl getProjectLovVO1() {
        return (ProjectLovVOImpl)findViewObject("ProjectLovVO1");
    }

    /**Container's getter for PersonLovVO1
     */
    public PersonLovVOImpl getPersonLovVO1() {
        return (PersonLovVOImpl)findViewObject("PersonLovVO1");
    }

    /**Container's getter for ProjectRoleLovVO1
     */
    public ProjectRoleLovVOImpl getProjectRoleLovVO1() {
        return (ProjectRoleLovVOImpl)findViewObject("ProjectRoleLovVO1");
    }
}