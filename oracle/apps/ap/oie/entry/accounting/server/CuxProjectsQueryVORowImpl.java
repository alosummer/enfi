package cux.oracle.apps.ap.oie.entry.accounting.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxProjectsQueryVORowImpl extends OAViewRowImpl {
    public static final int PROJECTID = 0;
    public static final int PROJECTNUMBER = 1;
    public static final int PROJECTNAME = 2;

    /**This is the default constructor (do not remove)
     */
    public CuxProjectsQueryVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute ProjectId
     */
    public Number getProjectId() {
        return (Number)getAttributeInternal(PROJECTID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProjectId
     */
    public void setProjectId(Number value) {
        setAttributeInternal(PROJECTID, value);
    }

    /**Gets the attribute value for the calculated attribute ProjectNumber
     */
    public String getProjectNumber() {
        return (String)getAttributeInternal(PROJECTNUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProjectNumber
     */
    public void setProjectNumber(String value) {
        setAttributeInternal(PROJECTNUMBER, value);
    }

    /**Gets the attribute value for the calculated attribute ProjectName
     */
    public String getProjectName() {
        return (String)getAttributeInternal(PROJECTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProjectName
     */
    public void setProjectName(String value) {
        setAttributeInternal(PROJECTNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTID:
            return getProjectId();
        case PROJECTNUMBER:
            return getProjectNumber();
        case PROJECTNAME:
            return getProjectName();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTID:
            setProjectId((Number)value);
            return;
        case PROJECTNUMBER:
            setProjectNumber((String)value);
            return;
        case PROJECTNAME:
            setProjectName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}