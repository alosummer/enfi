package cux.oracle.apps.per.poplist.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PerLevelModeVORowImpl extends OAViewRowImpl {
    public static final int EPMCODE = 0;
    public static final int EPMMEANING = 1;

    /**This is the default constructor (do not remove)
     */
    public PerLevelModeVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute EpmCode
     */
    public String getEpmCode() {
        return (String)getAttributeInternal(EPMCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmCode
     */
    public void setEpmCode(String value) {
        setAttributeInternal(EPMCODE, value);
    }

    /**Gets the attribute value for the calculated attribute EpmMeaning
     */
    public String getEpmMeaning() {
        return (String)getAttributeInternal(EPMMEANING);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmMeaning
     */
    public void setEpmMeaning(String value) {
        setAttributeInternal(EPMMEANING, value);
    }


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EPMCODE:
            return getEpmCode();
        case EPMMEANING:
            return getEpmMeaning();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
