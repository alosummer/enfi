package cux.oracle.apps.ap.oie.entry.header.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxOATravelerEmpLovVORowImpl extends OAViewRowImpl {
    public static final int OATRAVELHEADERID = 0;
    public static final int OATRAVELLINEID = 1;
    public static final int EMPID = 2;
    public static final int EMPNUMBER = 3;
    public static final int EMPNAME = 4;
    public static final int EMPDEPT = 5;

    /**This is the default constructor (do not remove)
     */
    public CuxOATravelerEmpLovVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute OaTravelHeaderId
     */
    public Number getOaTravelHeaderId() {
        return (Number)getAttributeInternal(OATRAVELHEADERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OaTravelHeaderId
     */
    public void setOaTravelHeaderId(Number value) {
        setAttributeInternal(OATRAVELHEADERID, value);
    }

    /**Gets the attribute value for the calculated attribute OaTravelLineId
     */
    public Number getOaTravelLineId() {
        return (Number)getAttributeInternal(OATRAVELLINEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OaTravelLineId
     */
    public void setOaTravelLineId(Number value) {
        setAttributeInternal(OATRAVELLINEID, value);
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

    /**Gets the attribute value for the calculated attribute EmpNumber
     */
    public String getEmpNumber() {
        return (String)getAttributeInternal(EMPNUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpNumber
     */
    public void setEmpNumber(String value) {
        setAttributeInternal(EMPNUMBER, value);
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

    /**Gets the attribute value for the calculated attribute EmpDept
     */
    public String getEmpDept() {
        return (String)getAttributeInternal(EMPDEPT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpDept
     */
    public void setEmpDept(String value) {
        setAttributeInternal(EMPDEPT, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case OATRAVELHEADERID:
            return getOaTravelHeaderId();
        case OATRAVELLINEID:
            return getOaTravelLineId();
        case EMPID:
            return getEmpId();
        case EMPNUMBER:
            return getEmpNumber();
        case EMPNAME:
            return getEmpName();
        case EMPDEPT:
            return getEmpDept();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case OATRAVELHEADERID:
            setOaTravelHeaderId((Number)value);
            return;
        case OATRAVELLINEID:
            setOaTravelLineId((Number)value);
            return;
        case EMPID:
            setEmpId((Number)value);
            return;
        case EMPNUMBER:
            setEmpNumber((String)value);
            return;
        case EMPNAME:
            setEmpName((String)value);
            return;
        case EMPDEPT:
            setEmpDept((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}