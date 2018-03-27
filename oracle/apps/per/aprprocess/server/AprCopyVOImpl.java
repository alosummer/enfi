package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    员工自助, 绩效合同，拷贝历史绩效合同
// ---    Created by yang.gang, 2016-4-26
// ---------------------------------------------------------------------
public class AprCopyVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprCopyVOImpl() {
    }

    public void initQuery(Integer iPersonID, Integer iAprID) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, iPersonID);
        setWhereClauseParam(1, iAprID);
        executeQuery();
    }
}
