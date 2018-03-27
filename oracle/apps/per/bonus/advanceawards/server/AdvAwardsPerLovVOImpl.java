package cux.oracle.apps.per.bonus.advanceawards.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdvAwardsPerLovVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AdvAwardsPerLovVOImpl() {
    }

    public void initSQL(String deptName) {
        if (deptName != null && !"".equals(deptName)) {
            setWhereClause("deptname = '" + deptName + "'");
            //            setWhereClauseParams(null);
            //            setWhereClauseParam(0, deptName);
            //            String str = this.getQuery();
            //            System.out.println(str);
            executeQuery();
        }
    }
}