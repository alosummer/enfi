package cux.oracle.apps.per.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PerEmpVORowImpl extends OAViewRowImpl {
    public static final int EMPID = 0;
    public static final int EMPNUM = 1;
    public static final int EMPNAME = 2;

    /**This is the default constructor (do not remove)
     */
    public PerEmpVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute EmpId
     */
    public Number getEmpId() {
        return (Number)getAttributeInternal(EMPID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpId
     */
    public void setEmpId(Number value) {
        setAttributeInternal(EMPID, value);
    }

    /**Gets the attribute value for the calculated attribute EmpNum
     */
    public String getEmpNum() {
        return (String)getAttributeInternal(EMPNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpNum
     */
    public void setEmpNum(String value) {
        setAttributeInternal(EMPNUM, value);
    }

    /**Gets the attribute value for the calculated attribute EmpName
     */
    public String getEmpName() {
        return (String)getAttributeInternal(EMPNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpName
     */
    public void setEmpName(String value) {
        setAttributeInternal(EMPNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EMPID:
            return getEmpId();
        case EMPNUM:
            return getEmpNum();
        case EMPNAME:
            return getEmpName();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EMPID:
            setEmpId((Number)value);
            return;
        case EMPNUM:
            setEmpNum((String)value);
            return;
        case EMPNAME:
            setEmpName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}