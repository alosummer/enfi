/*    */package cux.oracle.apps.per.bonus.manager.server;
/*    */
/*    */import oracle.apps.fnd.framework.OAViewObject;
/*    */
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
/*    */
/*    */public class ManagerCheckAMImpl extends OAApplicationModuleImpl {
    /*    */

    public static void main(String[] args) {
        /* 19 */launchTester("cux.oracle.apps.per.bonus.manager.server", 
                             "ManagerCheckAMLocal");
        /*    */
    }
    /*    */
    /*    */

    public ManagerVOImpl getManagerVO1() {
        /* 27 */return (ManagerVOImpl)findViewObject("ManagerVO1");
        /*    */
    }
    /*    */
    /*    */

    public void createManagerVO() {
        /* 31 */OAViewObject vo = (OAViewObject)getManagerVO1();
        /* 32 */vo.executeQuery();
        /*    */
    }
    /*    */
    /*    */

    public ManagerCheckBudgetVOImpl getManagerCheckBudgetVO1() {
        /* 39 */return (ManagerCheckBudgetVOImpl)findViewObject("ManagerCheckBudgetVO1");
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.server.ManagerCheckAMImpl
 * JD-Core Version:    0.6.1
 */