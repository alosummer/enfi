package cux.oracle.apps.ap.oie.entry.header.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxWebExpParamtersVORowImpl extends OAViewRowImpl {
    public static final int CATEGORYCODE = 0;
    public static final int PAEXPENDITURETYPE = 1;
    public static final int PARAMETERID = 2;

    /**This is the default constructor (do not remove)
     */
    public CuxWebExpParamtersVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute CategoryCode
     */
    public String getCategoryCode() {
        return (String)getAttributeInternal(CATEGORYCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CategoryCode
     */
    public void setCategoryCode(String value) {
        setAttributeInternal(CATEGORYCODE, value);
    }

    /**Gets the attribute value for the calculated attribute PaExpenditureType
     */
    public String getPaExpenditureType() {
        return (String)getAttributeInternal(PAEXPENDITURETYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PaExpenditureType
     */
    public void setPaExpenditureType(String value) {
        setAttributeInternal(PAEXPENDITURETYPE, value);
    }

    /**Gets the attribute value for the calculated attribute ParameterId
     */
    public Number getParameterId() {
        return (Number)getAttributeInternal(PARAMETERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ParameterId
     */
    public void setParameterId(Number value) {
        setAttributeInternal(PARAMETERID, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CATEGORYCODE:
            return getCategoryCode();
        case PAEXPENDITURETYPE:
            return getPaExpenditureType();
        case PARAMETERID:
            return getParameterId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CATEGORYCODE:
            setCategoryCode((String)value);
            return;
        case PAEXPENDITURETYPE:
            setPaExpenditureType((String)value);
            return;
        case PARAMETERID:
            setParameterId((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
