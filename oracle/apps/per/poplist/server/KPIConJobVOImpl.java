package cux.oracle.apps.per.poplist.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class KPIConJobVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public KPIConJobVOImpl() {
    }

    public void initQuery(String type) {
        setWhereClause("epm_cj_type = :1");
        setWhereClauseParams(null); // Always reset
        setWhereClauseParam(0, type);
        executeQuery();
    }
}
