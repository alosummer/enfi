package cux.oracle.apps.po.document.order.server;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PayPlanHisVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public PayPlanHisVOImpl() {
    }

    public void initPayPlanHis(String poHdrId) {
        if (poHdrId != null && (!"".equals(poHdrId.trim()))) {
            Number poHeaderId = null;
            try {
                poHeaderId = new Number(poHdrId);
            } catch (Exception e) {
                throw new OAException("AK", "FWK_TBK_INVALID_EMP_NUMBER");
            }

            StringBuffer whereClause = 
                new StringBuffer(300); // where clause for ViewObjects.
            whereClause.append("PO_HEADER_ID = :1");
            setWhereClause(null);
            setWhereClauseParams(null);
            setWhereClause(whereClause.toString());
            setWhereClauseParam(0, poHeaderId);
            executeQuery();
        }
    }
}
