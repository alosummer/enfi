package cux.oracle.apps.ap.oie.workflow.apexp.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxNtfMessageNameVORowImpl extends OAViewRowImpl {


    public static final int HIDEFLAG = 0;

    /**This is the default constructor (do not remove)
     */
    public CuxNtfMessageNameVORowImpl() {
    }


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case HIDEFLAG:
            return getHideFlag();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case HIDEFLAG:
            setHideFlag((Boolean)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }


    /**Gets the attribute value for the calculated attribute HideFlag
     */
    public Boolean getHideFlag() {
        return (Boolean)getAttributeInternal(HIDEFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HideFlag
     */
    public void setHideFlag(Boolean value) {
        setAttributeInternal(HIDEFLAG, value);
    }
}
