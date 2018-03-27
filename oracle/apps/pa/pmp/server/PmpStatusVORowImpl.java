package cux.oracle.apps.pa.pmp.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PmpStatusVORowImpl extends OAViewRowImpl {
    public static final int PMPID = 0;
    public static final int VERSIONNUM = 1;
    public static final int VERIFYSTATUS = 2;
    public static final int APPROVESTATUS = 3;
    public static final int VERIFYSTATUSNAME = 4;
    public static final int APPROVESTATUSNAME = 5;

    /**This is the default constructor (do not remove)
     */
    public PmpStatusVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute PmpId
     */
    public Number getPmpId() {
        return (Number)getAttributeInternal(PMPID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PmpId
     */
    public void setPmpId(Number value) {
        setAttributeInternal(PMPID, value);
    }

    /**Gets the attribute value for the calculated attribute VersionNum
     */
    public Number getVersionNum() {
        return (Number)getAttributeInternal(VERSIONNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute VersionNum
     */
    public void setVersionNum(Number value) {
        setAttributeInternal(VERSIONNUM, value);
    }


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PMPID:
            return getPmpId();
        case VERSIONNUM:
            return getVersionNum();
        case VERIFYSTATUS:
            return getVerifyStatus();
        case APPROVESTATUS:
            return getApproveStatus();
        case VERIFYSTATUSNAME:
            return getVerifyStatusName();
        case APPROVESTATUSNAME:
            return getApproveStatusName();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PMPID:
            setPmpId((Number)value);
            return;
        case VERSIONNUM:
            setVersionNum((Number)value);
            return;
        case VERIFYSTATUS:
            setVerifyStatus((String)value);
            return;
        case APPROVESTATUS:
            setApproveStatus((String)value);
            return;
        case VERIFYSTATUSNAME:
            setVerifyStatusName((String)value);
            return;
        case APPROVESTATUSNAME:
            setApproveStatusName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute VerifyStatusName
     */
    public String getVerifyStatusName() {
        return (String)getAttributeInternal(VERIFYSTATUSNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute VerifyStatusName
     */
    public void setVerifyStatusName(String value) {
        setAttributeInternal(VERIFYSTATUSNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ApproveStatusName
     */
    public String getApproveStatusName() {
        return (String)getAttributeInternal(APPROVESTATUSNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproveStatusName
     */
    public void setApproveStatusName(String value) {
        setAttributeInternal(APPROVESTATUSNAME, value);
    }

    /**Gets the attribute value for the calculated attribute VerifyStatus
     */
    public String getVerifyStatus() {
        return (String)getAttributeInternal(VERIFYSTATUS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute VerifyStatus
     */
    public void setVerifyStatus(String value) {
        setAttributeInternal(VERIFYSTATUS, value);
    }

    /**Gets the attribute value for the calculated attribute ApproveStatus
     */
    public String getApproveStatus() {
        return (String)getAttributeInternal(APPROVESTATUS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproveStatus
     */
    public void setApproveStatus(String value) {
        setAttributeInternal(APPROVESTATUS, value);
    }
}
