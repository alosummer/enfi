package cux.oracle.apps.per.poplist.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PerJobVORowImpl extends OAViewRowImpl {
    public static final int JOBID = 0;
    public static final int JOBNAME = 1;

    /**This is the default constructor (do not remove)
     */
    public PerJobVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute JobId
     */
    public Number getJobId() {
        return (Number)getAttributeInternal(JOBID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute JobId
     */
    public void setJobId(Number value) {
        setAttributeInternal(JOBID, value);
    }

    /**Gets the attribute value for the calculated attribute JobName
     */
    public String getJobName() {
        return (String)getAttributeInternal(JOBNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute JobName
     */
    public void setJobName(String value) {
        setAttributeInternal(JOBNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case JOBID:
            return getJobId();
        case JOBNAME:
            return getJobName();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
