package cux.oracle.apps.per.review.server;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class KPIConstraintVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public KPIConstraintVOImpl() {
    }

    public void insertRow(Row row) {
        super.insertRow(row);
        OAApplicationModule am = (OAApplicationModule)getApplicationModule();
        OADBTransaction trx = am.getOADBTransaction();
        Number constraintId = trx.getSequenceValue("CUX_KPI_CONSTRAINT_S");
        row.setAttribute("ConstraintId", constraintId);
    }
}
