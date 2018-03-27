package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    绩效合同页面，填写审批意见
// ---    created by yang.gang,2016-4-27
// ---------------------------------------------------------------------
public class AttendNoteVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AttendNoteVOImpl() {
    }

    /* phrase_id: 阶段 GOAL, REVIEW */

    public void initQuery(Integer iAprID, String phrase_id) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, phrase_id);
        setWhereClauseParam(1, iAprID);
        executeQuery();
    }
}
