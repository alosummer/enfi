package cux.oracle.apps.per.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmpNameVORowImpl extends OAViewRowImpl {
    public static final int EMPLOYEENAME = 0;
    public static final int EMPLOYEENUMBER = 1;

    /**This is the default constructor (do not remove)
     */
    public EmpNameVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute EmployeeName
     */
    public String getEmployeeName() {
        return (String)getAttributeInternal(EMPLOYEENAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeeName
     */
    public void setEmployeeName(String value) {
        setAttributeInternal(EMPLOYEENAME, value);
    }

    /**Gets the attribute value for the calculated attribute EmployeeNumber
     */
    public String getEmployeeNumber() {
        return (String)getAttributeInternal(EMPLOYEENUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeeNumber
     */
    public void setEmployeeNumber(String value) {
        setAttributeInternal(EMPLOYEENUMBER, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EMPLOYEENAME:
            return getEmployeeName();
        case EMPLOYEENUMBER:
            return getEmployeeNumber();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EMPLOYEENAME:
            setEmployeeName((String)value);
            return;
        case EMPLOYEENUMBER:
            setEmployeeNumber((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}