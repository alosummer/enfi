package cux.oracle.apps.cux.qhse.quality.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CustomerLovVORowImpl extends OAViewRowImpl {
    public static final int CUSTOMERID = 0;
    public static final int CUSTOMERNAME = 1;
    public static final int CUSTOMERNUMBER = 2;

    /**This is the default constructor (do not remove)
     */
    public CustomerLovVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute CustomerId
     */
    public Number getCustomerId() {
        return (Number)getAttributeInternal(CUSTOMERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CustomerId
     */
    public void setCustomerId(Number value) {
        setAttributeInternal(CUSTOMERID, value);
    }

    /**Gets the attribute value for the calculated attribute CustomerName
     */
    public String getCustomerName() {
        return (String)getAttributeInternal(CUSTOMERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CustomerName
     */
    public void setCustomerName(String value) {
        setAttributeInternal(CUSTOMERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute CustomerNumber
     */
    public String getCustomerNumber() {
        return (String)getAttributeInternal(CUSTOMERNUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CustomerNumber
     */
    public void setCustomerNumber(String value) {
        setAttributeInternal(CUSTOMERNUMBER, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CUSTOMERID:
            return getCustomerId();
        case CUSTOMERNAME:
            return getCustomerName();
        case CUSTOMERNUMBER:
            return getCustomerNumber();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CUSTOMERID:
            setCustomerId((Number)value);
            return;
        case CUSTOMERNAME:
            setCustomerName((String)value);
            return;
        case CUSTOMERNUMBER:
            setCustomerNumber((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}