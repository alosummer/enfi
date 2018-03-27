/*    */package cux.oracle.apps.per.bonus.manager.server;
/*    */
/*    */import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
/*    */
/*    */public class ProjectManagerLovAMImpl extends OAApplicationModuleImpl {
    /*    */

    public static void main(String[] args) {
        /* 18 */launchTester("cux.oracle.apps.per.bonus.manager.server", 
                             "ProjectManagerLovAMLocal");
        /*    */
    }
    /*    */
    /*    */

    public ProjectManagerVOImpl getProjectManagerVO1() {
        /* 26 */return (ProjectManagerVOImpl)findViewObject("ProjectManagerVO1");
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.server.ProjectManagerLovAMImpl
 * JD-Core Version:    0.6.1
 */