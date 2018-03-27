package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    查看CUX_APPRAISAL_T表中某条记录的详细信息
// ---    created by yang.gang.2016-4-25
// ---------------------------------------------------------------------
public class AprDetailImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprDetailImpl() {
    }

    public void initQuery(Integer iAprID) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, iAprID);
        executeQuery();
    }
}
