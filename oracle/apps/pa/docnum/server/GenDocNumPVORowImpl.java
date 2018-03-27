package cux.oracle.apps.pa.docnum.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class GenDocNumPVORowImpl extends OAViewRowImpl {
    public static final int PROJECTNUM = 0;
    public static final int DESC = 1;
    public static final int PROJECTID = 2;

    /**This is the default constructor (do not remove)
     */
    public GenDocNumPVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute projectNum
     */
    public String getprojectNum() {
        return (String)getAttributeInternal(PROJECTNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute projectNum
     */
    public void setprojectNum(String value) {
        setAttributeInternal(PROJECTNUM, value);
    }

    /**Gets the attribute value for the calculated attribute Desc
     */
    public String getDesc() {
        return (String)getAttributeInternal(DESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Desc
     */
    public void setDesc(String value) {
        setAttributeInternal(DESC, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTNUM:
            return getprojectNum();
        case DESC:
            return getDesc();
        case PROJECTID:
            return getProjectId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTNUM:
            setprojectNum((String)value);
            return;
        case DESC:
            setDesc((String)value);
            return;
        case PROJECTID:
            setProjectId((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
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
}
