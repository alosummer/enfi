/*    */package cux.oracle.apps.per.bonus.member.server;
/*    */
/*    */import oracle.apps.fnd.framework.OAViewObject;
/*    */
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
/*    */
/*    */public class CuxMemberGrantDetailsAMImpl extends OAApplicationModuleImpl {
    /*    */

    public static void main(String[] args) {
        /* 19 */launchTester("cux.oracle.apps.per.bonus.member.server", 
                             "CuxMemberGrantDetailsAMLocal");
        /*    */
    }
    /*    */
    /*    */

    public CuxMemberGrantDetailsVOImpl getCuxMemberGrantDetailsVO1() {
        /* 27 */return (CuxMemberGrantDetailsVOImpl)findViewObject("CuxMemberGrantDetailsVO1");
        /*    */
    }
    /*    */
    /*    */

    public void execQuery(String lotId) {
        /* 30 */OAViewObject vo = null;
        /* 31 */vo = (OAViewObject)getCuxMemberGrantDetailsVO1();
        /*    */
        /* 33 */String whereClause = "LOT_ID =  '" + lotId + "'";
        /*    */
        /* 35 */vo.setWhereClause(null);
        /* 36 */vo.setWhereClause(whereClause);
        /* 37 */vo.executeQuery();
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\member\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.member.server.CuxMemberGrantDetailsAMImpl
 * JD-Core Version:    0.6.1
 */