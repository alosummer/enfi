package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    绩效合同页面，审批人信息表格VO
// ---    created by yang.gang.2016-4-26
// ---------------------------------------------------------------------
public class AprAttendVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprAttendVOImpl() {
    }

    /* phrase_id: 阶段 GOAL, REVIEW */

    public void initQuery(Integer iAprID, String phrase_id) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, iAprID);
        setWhereClauseParam(1, phrase_id);
        executeQuery();
    }
}
