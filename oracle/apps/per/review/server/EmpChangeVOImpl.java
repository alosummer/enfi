package cux.oracle.apps.per.review.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmpChangeVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public EmpChangeVOImpl() {
    }

    public void initChangeQuery(String personId) {
        setWhereClauseParams(null); // Always reset
        setWhereClauseParam(0, personId);
        executeQuery();
    }

}
