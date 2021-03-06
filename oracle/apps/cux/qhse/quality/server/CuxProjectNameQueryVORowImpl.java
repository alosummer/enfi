package cux.oracle.apps.cux.qhse.quality.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxProjectNameQueryVORowImpl extends OAViewRowImpl {
    public static final int SEGMENT1 = 0;
    public static final int PROJECTNAME = 1;

    /**This is the default constructor (do not remove)
     */
    public CuxProjectNameQueryVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute Segment1
     */
    public String getSegment1() {
        return (String)getAttributeInternal(SEGMENT1);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Segment1
     */
    public void setSegment1(String value) {
        setAttributeInternal(SEGMENT1, value);
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
        case SEGMENT1:
            return getSegment1();
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
        case SEGMENT1:
            setSegment1((String)value);
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
