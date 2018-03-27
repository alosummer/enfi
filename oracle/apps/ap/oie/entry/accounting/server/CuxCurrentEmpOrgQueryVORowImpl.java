package cux.oracle.apps.ap.oie.entry.accounting.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxCurrentEmpOrgQueryVORowImpl extends OAViewRowImpl {
    public static final int ORGANIZATIONID = 0;
    public static final int ORGNAME = 1;

    /**This is the default constructor (do not remove)
     */
    public CuxCurrentEmpOrgQueryVORowImpl() {
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

    /**Gets the attribute value for the calculated attribute OrgName
     */
    public String getOrgName() {
        return (String)getAttributeInternal(ORGNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrgName
     */
    public void setOrgName(String value) {
        setAttributeInternal(ORGNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ORGANIZATIONID:
            return getOrganizationId();
        case ORGNAME:
            return getOrgName();
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
        case ORGNAME:
            setOrgName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}