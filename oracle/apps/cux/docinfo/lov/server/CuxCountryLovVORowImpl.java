package cux.oracle.apps.cux.docinfo.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxCountryLovVORowImpl extends OAViewRowImpl {
    public static final int TERRITORYCODE = 0;
    public static final int TERRITORYSHORTNAME = 1;
    public static final int DESCRIPTION = 2;

    /**This is the default constructor (do not remove)
     */
    public CuxCountryLovVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute TerritoryCode
     */
    public String getTerritoryCode() {
        return (String)getAttributeInternal(TERRITORYCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TerritoryCode
     */
    public void setTerritoryCode(String value) {
        setAttributeInternal(TERRITORYCODE, value);
    }

    /**Gets the attribute value for the calculated attribute TerritoryShortName
     */
    public String getTerritoryShortName() {
        return (String)getAttributeInternal(TERRITORYSHORTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TerritoryShortName
     */
    public void setTerritoryShortName(String value) {
        setAttributeInternal(TERRITORYSHORTNAME, value);
    }

    /**Gets the attribute value for the calculated attribute Description
     */
    public String getDescription() {
        return (String)getAttributeInternal(DESCRIPTION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Description
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case TERRITORYCODE:
            return getTerritoryCode();
        case TERRITORYSHORTNAME:
            return getTerritoryShortName();
        case DESCRIPTION:
            return getDescription();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case TERRITORYCODE:
            setTerritoryCode((String)value);
            return;
        case TERRITORYSHORTNAME:
            setTerritoryShortName((String)value);
            return;
        case DESCRIPTION:
            setDescription((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}