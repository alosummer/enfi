package cux.oracle.apps.per.review.server;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ReviewWorkflowVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public ReviewWorkflowVOImpl() {
    }

    /*
    public void insertRow(Row row)
    {
        super.insertRow(row);
        OAApplicationModule am = (OAApplicationModule)getApplicationModule();
        OADBTransaction trx = am.getOADBTransaction();
        Number epmWorkflowId = trx.getSequenceValue("CUX_REVIEW_WORKFLOW_S");
        row.setAttribute("EpmWorkflowId", epmWorkflowId);
    }*/
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

    public void initQuery(String epmPeriodCode, String epmPhaseClassCode, 
                          Number groupId, Number orgId) {
        if (epmPeriodCode != null && epmPhaseClassCode != null && 
            groupId != null && orgId != null) {

            setWhereClause(" EPM_PERIOD_CODE = :1 AND EPM_PHASE_CLASS_CODE = :2 AND GROUP_ID = :3 AND ORG_ID = :4");
            setWhereClauseParams(null);
            setWhereClauseParam(0, epmPeriodCode);
            setWhereClauseParam(1, epmPhaseClassCode);
            setWhereClauseParam(2, groupId);
            setWhereClauseParam(3, orgId);
            executeQuery();
        }
    }
    /*20100205 zs*/

    public void initQuery(String epmPeriodCode, String epmPhaseClassCode, 
                          Number groupId, Number orgId, String isAtended) {
        if (epmPeriodCode != null && epmPhaseClassCode != null && 
            groupId != null && orgId != null && isAtended != null) {

            setWhereClause(" EPM_PERIOD_CODE = :1 AND EPM_PHASE_CLASS_CODE = :2 AND GROUP_ID = :3 AND ORG_ID = :4 AND IS_ATTENDED = :5");
            setWhereClauseParams(null);
            setWhereClauseParam(0, epmPeriodCode);
            setWhereClauseParam(1, epmPhaseClassCode);
            setWhereClauseParam(2, groupId);
            setWhereClauseParam(3, orgId);
            setWhereClauseParam(4, isAtended);
            executeQuery();
        }
    }
    /*20100205 zs*/
}
