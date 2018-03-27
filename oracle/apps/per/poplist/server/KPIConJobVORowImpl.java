package cux.oracle.apps.per.poplist.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class KPIConJobVORowImpl extends OAViewRowImpl {
    public static final int EPMCJCODE = 0;
    public static final int ORGANIZATIONID = 1;
    public static final int EPMCJNAME = 2;
    public static final int EPMCJTYPE = 3;

    /**This is the default constructor (do not remove)
     */
    public KPIConJobVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute EpmCjCode
     */
    public Number getEpmCjCode() {
        return (Number)getAttributeInternal(EPMCJCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmCjCode
     */
    public void setEpmCjCode(Number value) {
        setAttributeInternal(EPMCJCODE, value);
    }

    /**Gets the attribute value for the calculated attribute EpmCjName
     */
    public String getEpmCjName() {
        return (String)getAttributeInternal(EPMCJNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmCjName
     */
    public void setEpmCjName(String value) {
        setAttributeInternal(EPMCJNAME, value);
    }

    /**Gets the attribute value for the calculated attribute EpmCjType
     */
    public String getEpmCjType() {
        return (String)getAttributeInternal(EPMCJTYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EpmCjType
     */
    public void setEpmCjType(String value) {
        setAttributeInternal(EPMCJTYPE, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EPMCJCODE:
            return getEpmCjCode();
        case ORGANIZATIONID:
            return getOrganizationId();
        case EPMCJNAME:
            return getEpmCjName();
        case EPMCJTYPE:
            return getEpmCjType();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ORGANIZATIONID:
            setOrganizationId((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute OrganizationId
     */
    public Number getOrganizationId() {
        return (Number)getAttributeInternal(ORGANIZATIONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrganizationId
     */
    public void setOrganizationId(Number value) {
        setAttributeInternal(ORGANIZATIONID, value);
    }
}
