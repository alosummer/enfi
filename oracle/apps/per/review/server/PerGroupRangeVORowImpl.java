package cux.oracle.apps.per.review.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PerGroupRangeVORowImpl extends OAViewRowImpl {
    public static final int RANGEID = 0;
    public static final int GROUPID = 1;
    public static final int JOBID = 2;
    public static final int GROUPNAME = 3;
    public static final int JOBNAME = 4;

    /**This is the default constructor (do not remove)
     */
    public PerGroupRangeVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute RangeId
     */
    public Number getRangeId() {
        return (Number)getAttributeInternal(RANGEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RangeId
     */
    public void setRangeId(Number value) {
        setAttributeInternal(RANGEID, value);
    }

    /**Gets the attribute value for the calculated attribute GroupId
     */
    public Number getGroupId() {
        return (Number)getAttributeInternal(GROUPID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute GroupId
     */
    public void setGroupId(Number value) {
        setAttributeInternal(GROUPID, value);
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

    /**Gets the attribute value for the calculated attribute GroupName
     */
    public String getGroupName() {
        return (String)getAttributeInternal(GROUPNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute GroupName
     */
    public void setGroupName(String value) {
        setAttributeInternal(GROUPNAME, value);
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
        case RANGEID:
            return getRangeId();
        case GROUPID:
            return getGroupId();
        case JOBID:
            return getJobId();
        case GROUPNAME:
            return getGroupName();
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
