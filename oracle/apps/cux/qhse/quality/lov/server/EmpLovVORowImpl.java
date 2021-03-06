package cux.oracle.apps.cux.qhse.quality.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmpLovVORowImpl extends OAViewRowImpl {
    public static final int EMPLOYEEID = 0;
    public static final int FULLNAME = 1;
    public static final int EMPLOYEENUM = 2;
    public static final int EMPFULLNAME = 3;

    /**This is the default constructor (do not remove)
     */
    public EmpLovVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute EmployeeId
     */
    public Number getEmployeeId() {
        return (Number)getAttributeInternal(EMPLOYEEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeeId
     */
    public void setEmployeeId(Number value) {
        setAttributeInternal(EMPLOYEEID, value);
    }

    /**Gets the attribute value for the calculated attribute FullName
     */
    public String getFullName() {
        return (String)getAttributeInternal(FULLNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FullName
     */
    public void setFullName(String value) {
        setAttributeInternal(FULLNAME, value);
    }

    /**Gets the attribute value for the calculated attribute EmployeeNum
     */
    public String getEmployeeNum() {
        return (String)getAttributeInternal(EMPLOYEENUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeeNum
     */
    public void setEmployeeNum(String value) {
        setAttributeInternal(EMPLOYEENUM, value);
    }

    /**Gets the attribute value for the calculated attribute EmpFullName
     */
    public String getEmpFullName() {
        return (String)getAttributeInternal(EMPFULLNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpFullName
     */
    public void setEmpFullName(String value) {
        setAttributeInternal(EMPFULLNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EMPLOYEEID:
            return getEmployeeId();
        case FULLNAME:
            return getFullName();
        case EMPLOYEENUM:
            return getEmployeeNum();
        case EMPFULLNAME:
            return getEmpFullName();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EMPLOYEEID:
            setEmployeeId((Number)value);
            return;
        case FULLNAME:
            setFullName((String)value);
            return;
        case EMPLOYEENUM:
            setEmployeeNum((String)value);
            return;
        case EMPFULLNAME:
            setEmpFullName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
