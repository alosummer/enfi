package cux.oracle.apps.per.bonus.searchlist;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DeptOVRowImpl extends OAViewRowImpl {
    public static final int DEPNAME = 0;

    /**This is the default constructor (do not remove)
     */
    public DeptOVRowImpl() {
    }

    /**Gets the attribute value for the calculated attribute DepName
     */
    public String getDepName() {
        return (String)getAttributeInternal(DEPNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DepName
     */
    public void setDepName(String value) {
        setAttributeInternal(DEPNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case DEPNAME:
            return getDepName();
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