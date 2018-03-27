package cux.oracle.apps.pa.mh.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ProjectRoleVORowImpl extends OAViewRowImpl {
    public static final int PROJECTROLEID = 0;
    public static final int PROJECTROLETYPE = 1;
    public static final int MEANING = 2;

    /**This is the default constructor (do not remove)
     */
    public ProjectRoleVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute ProjectRoleId
     */
    public Number getProjectRoleId() {
        return (Number)getAttributeInternal(PROJECTROLEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProjectRoleId
     */
    public void setProjectRoleId(Number value) {
        setAttributeInternal(PROJECTROLEID, value);
    }

    /**Gets the attribute value for the calculated attribute ProjectRoleType
     */
    public String getProjectRoleType() {
        return (String)getAttributeInternal(PROJECTROLETYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProjectRoleType
     */
    public void setProjectRoleType(String value) {
        setAttributeInternal(PROJECTROLETYPE, value);
    }

    /**Gets the attribute value for the calculated attribute Meaning
     */
    public String getMeaning() {
        return (String)getAttributeInternal(MEANING);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Meaning
     */
    public void setMeaning(String value) {
        setAttributeInternal(MEANING, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTROLEID:
            return getProjectRoleId();
        case PROJECTROLETYPE:
            return getProjectRoleType();
        case MEANING:
            return getMeaning();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTROLEID:
            setProjectRoleId((Number)value);
            return;
        case PROJECTROLETYPE:
            setProjectRoleType((String)value);
            return;
        case MEANING:
            setMeaning((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
