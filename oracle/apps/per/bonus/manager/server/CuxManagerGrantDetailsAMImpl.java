/*    */package cux.oracle.apps.per.bonus.manager.server;
/*    */
/*    */import oracle.apps.fnd.framework.OAViewObject;
/*    */
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
/*    */
/*    */public class CuxManagerGrantDetailsAMImpl extends OAApplicationModuleImpl {
    /*    */

    public static void main(String[] args) {
        /* 11 */launchTester("cux.oracle.apps.per.bonus.manager.server", 
                             "CuxManagerGrantDetailsAMLocal");
        /*    */
    }
    /*    */
    /*    */

    public CuxManagerGrantDetailsVOImpl getCuxManagerGrantDetailsVO1() {
        /* 21 */return (CuxManagerGrantDetailsVOImpl)findViewObject("CuxManagerGrantDetailsVO1");
        /*    */
    }
    /*    */
    /*    */

    public void execQuery(String lotId) {
        /* 25 */OAViewObject vo = null;
        /* 26 */vo = (OAViewObject)getCuxManagerGrantDetailsVO1();
        /*    */
        /* 28 */String whereClause = "LOT_ID =  '" + lotId + "'";
        /*    */
        /* 30 */vo.setWhereClause(null);
        /* 31 */vo.setWhereClause(whereClause);
        /* 32 */vo.executeQuery();
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.server.CuxManagerGrantDetailsAMImpl
 * JD-Core Version:    0.6.1
 */