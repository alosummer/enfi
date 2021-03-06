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
public class DsSummaryVORowImpl extends OAViewRowImpl {
    public static final int PROJECTID = 0;
    public static final int TASKID = 1;
    public static final int PROJECTNUM = 2;
    public static final int PROJECTNAME = 3;
    public static final int TASKNUM = 4;
    public static final int TASKNAME = 5;
    public static final int SCHEDULEDSTARTDATE = 6;
    public static final int SCHEDULEDFINISHDATE = 7;
    public static final int ACTUALSTARTDATE = 8;
    public static final int ACTUALCOMPLETIONDATE = 9;
    public static final int FIRSTRELEASEDATE = 10;
    public static final int LASTUPDATEDATE = 11;
    public static final int UPDATECOUNT = 12;
    public static final int SCHEDULEDESC = 13;
    public static final int PHASE = 14;
    public static final int STATUS = 15;
    public static final int STATUSNAME = 16;
    public static final int STATUSREADONLY = 17;

    /**This is the default constructor (do not remove)
     */
    public DsSummaryVORowImpl() {
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

    /**Gets the attribute value for the calculated attribute ActualStartDate
     */
    public Date getActualStartDate() {
        return (Date)getAttributeInternal(ACTUALSTARTDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ActualStartDate
     */
    public void setActualStartDate(Date value) {
        setAttributeInternal(ACTUALSTARTDATE, value);
    }

    /**Gets the attribute value for the calculated attribute ActualCompletionDate
     */
    public Date getActualCompletionDate() {
        return (Date)getAttributeInternal(ACTUALCOMPLETIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ActualCompletionDate
     */
    public void setActualCompletionDate(Date value) {
        setAttributeInternal(ACTUALCOMPLETIONDATE, value);
    }

    /**Gets the attribute value for the calculated attribute FirstReleaseDate
     */
    public Date getFirstReleaseDate() {
        return (Date)getAttributeInternal(FIRSTRELEASEDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FirstReleaseDate
     */
    public void setFirstReleaseDate(Date value) {
        setAttributeInternal(FIRSTRELEASEDATE, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for the calculated attribute UpdateCount
     */
    public Number getUpdateCount() {
        return (Number)getAttributeInternal(UPDATECOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute UpdateCount
     */
    public void setUpdateCount(Number value) {
        setAttributeInternal(UPDATECOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute ScheduleDesc
     */
    public String getScheduleDesc() {
        return (String)getAttributeInternal(SCHEDULEDESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ScheduleDesc
     */
    public void setScheduleDesc(String value) {
        setAttributeInternal(SCHEDULEDESC, value);
    }

    /**Gets the attribute value for the calculated attribute Phase
     */
    public String getPhase() {
        return (String)getAttributeInternal(PHASE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Phase
     */
    public void setPhase(String value) {
        setAttributeInternal(PHASE, value);
    }

    /**Gets the attribute value for the calculated attribute Status
     */
    public String getStatus() {
        return (String)getAttributeInternal(STATUS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**Gets the attribute value for the calculated attribute StatusName
     */
    public String getStatusName() {
        return (String)getAttributeInternal(STATUSNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusName
     */
    public void setStatusName(String value) {
        setAttributeInternal(STATUSNAME, value);
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
        case TASKNUM:
            return getTaskNum();
        case TASKNAME:
            return getTaskName();
        case SCHEDULEDSTARTDATE:
            return getScheduledStartDate();
        case SCHEDULEDFINISHDATE:
            return getScheduledFinishDate();
        case ACTUALSTARTDATE:
            return getActualStartDate();
        case ACTUALCOMPLETIONDATE:
            return getActualCompletionDate();
        case FIRSTRELEASEDATE:
            return getFirstReleaseDate();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case UPDATECOUNT:
            return getUpdateCount();
        case SCHEDULEDESC:
            return getScheduleDesc();
        case PHASE:
            return getPhase();
        case STATUS:
            return getStatus();
        case STATUSNAME:
            return getStatusName();
        case STATUSREADONLY:
            return getStatusReadonly();
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
        case ACTUALSTARTDATE:
            setActualStartDate((Date)value);
            return;
        case ACTUALCOMPLETIONDATE:
            setActualCompletionDate((Date)value);
            return;
        case FIRSTRELEASEDATE:
            setFirstReleaseDate((Date)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case UPDATECOUNT:
            setUpdateCount((Number)value);
            return;
        case SCHEDULEDESC:
            setScheduleDesc((String)value);
            return;
        case PHASE:
            setPhase((String)value);
            return;
        case STATUS:
            setStatus((String)value);
            return;
        case STATUSNAME:
            setStatusName((String)value);
            return;
        case STATUSREADONLY:
            setStatusReadonly((Boolean)value);
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

    /**Gets DsSummaryEO entity object.
     */
    public DsSummaryEOImpl getDsSummaryEO() {
        return (DsSummaryEOImpl)getEntity(0);
    }

    /**Gets the attribute value for the calculated attribute StatusReadonly
     */
    public Boolean getStatusReadonly() {
        return (Boolean)getAttributeInternal(STATUSREADONLY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusReadonly
     */
    public void setStatusReadonly(Boolean value) {
        setAttributeInternal(STATUSREADONLY, value);
    }
}
