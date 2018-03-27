package cux.oracle.apps.per.poplist.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EpmStatusVORowImpl extends OAViewRowImpl {
    public static final int EPMFSCODE = 0;
    public static final int EPMFSNAME = 1;
    public static final int EPMFSDESC = 2;

    /**This is the default constructor (do not remove)
     */
    public EpmStatusVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute EpmFsCode
     */
    public String getEpmFsCode() {
        return (String)getAttributeInternal(EPMFSCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmFsCode
     */
    public void setEpmFsCode(String value) {
        setAttributeInternal(EPMFSCODE, value);
    }

    /**Gets the attribute value for the calculated attribute EpmFsName
     */
    public String getEpmFsName() {
        return (String)getAttributeInternal(EPMFSNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmFsName
     */
    public void setEpmFsName(String value) {
        setAttributeInternal(EPMFSNAME, value);
    }

    /**Gets the attribute value for the calculated attribute EpmFsDesc
     */
    public String getEpmFsDesc() {
        return (String)getAttributeInternal(EPMFSDESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmFsDesc
     */
    public void setEpmFsDesc(String value) {
        setAttributeInternal(EPMFSDESC, value);
    }


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EPMFSCODE:
            return getEpmFsCode();
        case EPMFSNAME:
            return getEpmFsName();
        case EPMFSDESC:
            return getEpmFsDesc();
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