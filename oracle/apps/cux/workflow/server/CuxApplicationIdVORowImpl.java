package cux.oracle.apps.cux.workflow.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxApplicationIdVORowImpl extends OAViewRowImpl {
    public static final int APPLICATIONID = 0;

    /**This is the default constructor (do not remove)
     */
    public CuxApplicationIdVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute ApplicationId
     */
    public Number getApplicationId() {
        return (Number)getAttributeInternal(APPLICATIONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApplicationId
     */
    public void setApplicationId(Number value) {
        setAttributeInternal(APPLICATIONID, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case APPLICATIONID:
            return getApplicationId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case APPLICATIONID:
            setApplicationId((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}