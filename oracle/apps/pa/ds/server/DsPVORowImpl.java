package cux.oracle.apps.pa.ds.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DsPVORowImpl extends OAViewRowImpl {
    public static final int PROJECTID = 0;
    public static final int TASKID = 1;
    public static final int PROJECTNUM = 2;
    public static final int PROJECTNAME = 3;
    public static final int PROJECTDEPT = 4;
    public static final int TASKNUM = 5;
    public static final int TASKNAME = 6;
    public static final int SCHEDULEDSTARTDATE = 7;
    public static final int SCHEDULEDFINISHDATE = 8;

    /**This is the default constructor (do not remove)
     */
    public DsPVORowImpl() {
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

    /**Gets the attribute value for the calculated attribute TaskId
     */
    public Number getTaskId() {
        return (Number)getAttributeInternal(TASKID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TaskId
     */
    public void setTaskId(Number value) {
        setAttributeInternal(TASKID, value);
    }

    /**Gets the attribute value for the calculated attribute ProjectNum
     */
    public String getProjectNum() {
        return (String)getAttributeInternal(PROJECTNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProjectNum
     */
    public void setProjectNum(String value) {
        setAttributeInternal(PROJECTNUM, value);
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

    /**Gets the attribute value for the calculated attribute ProjectDept
     */
    public String getProjectDept() {
        return (String)getAttributeInternal(PROJECTDEPT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProjectDept
     */
    public void setProjectDept(String value) {
        setAttributeInternal(PROJECTDEPT, value);
    }

    /**Gets the attribute value for the calculated attribute TaskNum
     */
    public String getTaskNum() {
        return (String)getAttributeInternal(TASKNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TaskNum
     */
    public void setTaskNum(String value) {
        setAttributeInternal(TASKNUM, value);
    }

    /**Gets the attribute value for the calculated attribute TaskName
     */
    public String getTaskName() {
        return (String)getAttributeInternal(TASKNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TaskName
     */
    public void setTaskName(String value) {
        setAttributeInternal(TASKNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTID:
            return getProjectId();
        case TASKID:
            return getTaskId();
        case PROJECTNUM:
            return getProjectNum();
        case PROJECTNAME:
            return getProjectName();
        case PROJECTDEPT:
            return getProjectDept();
        case TASKNUM:
            return getTaskNum();
        case TASKNAME:
            return getTaskName();
        case SCHEDULEDSTARTDATE:
            return getScheduledStartDate();
        case SCHEDULEDFINISHDATE:
            return getScheduledFinishDate();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTID:
            setProjectId((Number)value);
            return;
        case TASKID:
            setTaskId((Number)value);
            return;
        case PROJECTNUM:
            setProjectNum((String)value);
            return;
        case PROJECTNAME:
            setProjectName((String)value);
            return;
        case PROJECTDEPT:
            setProjectDept((String)value);
            return;
        case TASKNUM:
            setTaskNum((String)value);
            return;
        case TASKNAME:
            setTaskName((String)value);
            return;
        case SCHEDULEDSTARTDATE:
            setScheduledStartDate((Date)value);
            return;
        case SCHEDULEDFINISHDATE:
            setScheduledFinishDate((Date)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute ScheduledStartDate
     */
    public Date getScheduledStartDate() {
        return (Date)getAttributeInternal(SCHEDULEDSTARTDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ScheduledStartDate
     */
    public void setScheduledStartDate(Date value) {
        setAttributeInternal(SCHEDULEDSTARTDATE, value);
    }

    /**Gets the attribute value for the calculated attribute ScheduledFinishDate
     */
    public Date getScheduledFinishDate() {
        return (Date)getAttributeInternal(SCHEDULEDFINISHDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ScheduledFinishDate
     */
    public void setScheduledFinishDate(Date value) {
        setAttributeInternal(SCHEDULEDFINISHDATE, value);
    }
}