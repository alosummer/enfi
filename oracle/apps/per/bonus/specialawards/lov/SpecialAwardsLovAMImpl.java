package cux.oracle.apps.per.bonus.specialawards.lov;


import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SpecialAwardsLovAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public SpecialAwardsLovAMImpl() {
    }


    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cux.oracle.apps.per.bonus.specialawards.lov", 
                                             "SpecialAwardsLovAMLocal");
    }


    /**Container's getter for SpecialAwardsPersonLovVO1
     */
    public SpecialAwardsPersonLovVOImpl getSpecialAwardsPersonLovVO1() {
        return (SpecialAwardsPersonLovVOImpl)findViewObject("SpecialAwardsPersonLovVO1");
    }

    /**Container's getter for SpecialAwardsLovVO1
     */
    public SpecialAwardsLovVOImpl getSpecialAwardsLovVO1() {
        return (SpecialAwardsLovVOImpl)findViewObject("SpecialAwardsLovVO1");
    }
}
