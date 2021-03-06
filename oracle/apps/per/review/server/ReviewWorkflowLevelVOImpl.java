package cux.oracle.apps.per.review.server;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ReviewWorkflowLevelVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public ReviewWorkflowLevelVOImpl() {
    }
    //initQuery

    public void initQuery(String epmWorkflowId) {
        if ((epmWorkflowId != null) && (!("".equals(epmWorkflowId.trim())))) {
            Number epmWorkflowID = null;
            try {
                epmWorkflowID = new Number(epmWorkflowId);
            } catch (Exception e) {
                throw new OAException("z", "s");
            }
            setWhereClause("EPM_WORKFLOW_ID = :1");
            setWhereClauseParams(null);
            setWhereClauseParam(0, epmWorkflowID);
            executeQuery();
        }
    }
}
