package cux.oracle.apps.per.poplist.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class KPIConObjVORowImpl extends OAViewRowImpl {
    public static final int EPMCOCODE = 0;
    public static final int EPMCONAME = 1;
    public static final int EPMCOTYPE = 2;

    /**This is the default constructor (do not remove)
     */
    public KPIConObjVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute EpmCoCode
     */
    public String getEpmCoCode() {
        return (String)getAttributeInternal(EPMCOCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmCoCode
     */
    public void setEpmCoCode(String value) {
        setAttributeInternal(EPMCOCODE, value);
    }

    /**Gets the attribute value for the calculated attribute EpmCoName
     */
    public String getEpmCoName() {
        return (String)getAttributeInternal(EPMCONAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmCoName
     */
    public void setEpmCoName(String value) {
        setAttributeInternal(EPMCONAME, value);
    }

    /**Gets the attribute value for the calculated attribute EpmCoType
     */
    public String getEpmCoType() {
        return (String)getAttributeInternal(EPMCOTYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmCoType
     */
    public void setEpmCoType(String value) {
        setAttributeInternal(EPMCOTYPE, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EPMCOCODE:
            return getEpmCoCode();
        case EPMCONAME:
            return getEpmCoName();
        case EPMCOTYPE:
            return getEpmCoType();
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