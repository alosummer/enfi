package cux.oracle.apps.pa.dlv.server;

import oracle.apps.fnd.framework.OAAttrValException;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DeliverableVORowImpl extends OAViewRowImpl {
    public static final int CUXDLVID = 0;
    public static final int DELIVERABLEID = 1;
    public static final int PROJECTID = 2;
    public static final int PROJECTNAME = 3;
    public static final int STATUS = 4;
    public static final int STATUSNAME = 5;
    public static final int DOCTYPE = 6;
    public static final int DOCTYPENAME = 7;
    public static final int TASKID = 8;
    public static final int TASKNAME = 9;
    public static final int DESIGNNODE = 10;
    public static final int ACTUALCOUNTERSIGNDATE = 11;
    public static final int ACTUALRELEASEDATE = 12;
    public static final int HQD = 13;
    public static final int HQDNAME = 14;
    public static final int JTD = 15;
    public static final int JTDNAME = 16;
    public static final int WJHZD = 17;
    public static final int WJHZDNAME = 18;
    public static final int DOCNAME = 19;
    public static final int DOCNUM = 20;
    public static final int DESIGNER = 21;
    public static final int DESIGNERNAME = 22;
    public static final int CHECKER = 23;
    public static final int CHECKERNAME = 24;
    public static final int VERIFIER = 25;
    public static final int VERIFIERNAME = 26;
    public static final int AUTHORIZER = 27;
    public static final int AUTHORIZERNAME = 28;
    public static final int WEIGHTS = 29;
    public static final int SCHEDULECOMPLETEDDATE = 30;
    public static final int ACTUALCOMPLETEDDATE = 31;
    public static final int RELEASEDEPT = 32;
    public static final int RELEASEDEPTNAME = 33;
    public static final int RELEASESPECIALTY = 34;
    public static final int RELEASESPECIALTYNAME = 35;
    public static final int ACCEPTSPECIALTY = 36;
    public static final int ACCEPTSPECIALTYNAME = 37;
    public static final int ATTRIBUTE1 = 38;
    public static final int ATTRIBUTE2 = 39;
    public static final int ATTRIBUTE3 = 40;
    public static final int ATTRIBUTE4 = 41;
    public static final int ATTRIBUTE5 = 42;
    public static final int ATTRIBUTE6 = 43;
    public static final int ATTRIBUTE7 = 44;
    public static final int ATTRIBUTE8 = 45;
    public static final int ATTRIBUTE9 = 46;
    public static final int CREATEDBY = 47;
    public static final int CREATIONDATE = 48;
    public static final int LASTUPDATEDBY = 49;
    public static final int LASTUPDATEDATE = 50;
    public static final int LASTUPDATELOGIN = 51;
    public static final int WFITEMTYPE = 52;
    public static final int WFITEMKEY = 53;
    public static final int CHECKED = 54;
    public static final int UPDATEALLOWED = 55;
    public static final int AUPDATEALLOWED = 56;
    public static final int BUPDATEALLOWED = 57;
    public static final int HQJTENABLED = 58;
    public static final int WJHZENABLED = 59;
    public static final int ACCESSLEVEL = 60;

    /**This is the default constructor (do not remove)
     */
    public DeliverableVORowImpl() {
    }

    /**Gets DeliverableEO entity object.
     */
    public DeliverableEOImpl getDeliverableEO() {
        return (DeliverableEOImpl)getEntity(0);
    }

    /**Gets the attribute value for CUX_DLV_ID using the alias name CuxDlvId
     */
    public Number getCuxDlvId() {
        return (Number)getAttributeInternal(CUXDLVID);
    }

    /**Sets <code>value</code> as attribute value for CUX_DLV_ID using the alias name CuxDlvId
     */
    public void setCuxDlvId(Number value) {
        setAttributeInternal(CUXDLVID, value);
    }

    /**Gets the attribute value for DELIVERABLE_ID using the alias name DeliverableId
     */
    public Number getDeliverableId() {
        return (Number)getAttributeInternal(DELIVERABLEID);
    }

    /**Sets <code>value</code> as attribute value for DELIVERABLE_ID using the alias name DeliverableId
     */
    public void setDeliverableId(Number value) {
        setAttributeInternal(DELIVERABLEID, value);
    }

    /**Gets the attribute value for PROJECT_ID using the alias name ProjectId
     */
    public Number getProjectId() {
        return (Number)getAttributeInternal(PROJECTID);
    }

    /**Sets <code>value</code> as attribute value for PROJECT_ID using the alias name ProjectId
     */
    public void setProjectId(Number value) {
        setAttributeInternal(PROJECTID, value);
    }

    /**Gets the attribute value for STATUS using the alias name Status
     */
    public String getStatus() {
        return (String)getAttributeInternal(STATUS);
    }

    /**Sets <code>value</code> as attribute value for STATUS using the alias name Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**Gets the attribute value for DOC_TYPE using the alias name DocType
     */
    public String getDocType() {
        return (String)getAttributeInternal(DOCTYPE);
    }

    /**Sets <code>value</code> as attribute value for DOC_TYPE using the alias name DocType
     */
    public void setDocType(String value) {
        setAttributeInternal(DOCTYPE, value);
    }

    /**Gets the attribute value for TASK_ID using the alias name TaskId
     */
    public Number getTaskId() {
        return (Number)getAttributeInternal(TASKID);
    }

    /**Sets <code>value</code> as attribute value for TASK_ID using the alias name TaskId
     */
    public void setTaskId(Number value) {
        setAttributeInternal(TASKID, value);
    }

    /**Gets the attribute value for DOC_NAME using the alias name DocName
     */
    public String getDocName() {
        return (String)getAttributeInternal(DOCNAME);
    }

    /**Sets <code>value</code> as attribute value for DOC_NAME using the alias name DocName
     */
    public void setDocName(String value) {
        setAttributeInternal(DOCNAME, value);
    }

    /**Gets the attribute value for DOC_NUM using the alias name DocNum
     */
    public String getDocNum() {
        return (String)getAttributeInternal(DOCNUM);
    }

    /**Sets <code>value</code> as attribute value for DOC_NUM using the alias name DocNum
     */
    public void setDocNum(String value) {
        setAttributeInternal(DOCNUM, value);
    }

    /**Gets the attribute value for DESIGNER using the alias name Designer
     */
    public Number getDesigner() {
        return (Number)getAttributeInternal(DESIGNER);
    }

    /**Sets <code>value</code> as attribute value for DESIGNER using the alias name Designer
     */
    public void setDesigner(Number value) {
        setAttributeInternal(DESIGNER, value);
    }

    /**Gets the attribute value for CHECKER using the alias name Checker
     */
    public Number getChecker() {
        return (Number)getAttributeInternal(CHECKER);
    }

    /**Sets <code>value</code> as attribute value for CHECKER using the alias name Checker
     */
    public void setChecker(Number value) {
        setAttributeInternal(CHECKER, value);
    }

    /**Gets the attribute value for VERIFIER using the alias name Verifier
     */
    public Number getVerifier() {
        return (Number)getAttributeInternal(VERIFIER);
    }

    /**Sets <code>value</code> as attribute value for VERIFIER using the alias name Verifier
     */
    public void setVerifier(Number value) {
        setAttributeInternal(VERIFIER, value);
    }

    /**Gets the attribute value for AUTHORIZER using the alias name Authorizer
     */
    public Number getAuthorizer() {
        return (Number)getAttributeInternal(AUTHORIZER);
    }

    /**Sets <code>value</code> as attribute value for AUTHORIZER using the alias name Authorizer
     */
    public void setAuthorizer(Number value) {
        setAttributeInternal(AUTHORIZER, value);
    }

    /**Gets the attribute value for WEIGHTS using the alias name Weights
     */
    public Number getWeights() {
        return (Number)getAttributeInternal(WEIGHTS);
    }

    /**Sets <code>value</code> as attribute value for WEIGHTS using the alias name Weights
     */
    public void setWeights(Number value) {
        setAttributeInternal(WEIGHTS, value);
    }

    /**Gets the attribute value for SCHEDULE_COMPLETED_DATE using the alias name ScheduleCompletedDate
     */
    public Date getScheduleCompletedDate() {
        return (Date)getAttributeInternal(SCHEDULECOMPLETEDDATE);
    }

    /**Sets <code>value</code> as attribute value for SCHEDULE_COMPLETED_DATE using the alias name ScheduleCompletedDate
     */
    public void setScheduleCompletedDate(Date value) {
        //    throw new OAAttrValException (
        //                                OAException.TYP_VIEW_OBJECT, // indicates VO row source
        //                                getViewObject().getFullName(), //View Object full usage name
        //                                getKey(), // row primary key
        //                                "Description", //attribute name
        //                                value, // bad attribute value 
        //                                "CUX", //message application short name
        //                                "CUX-PM-E022"); // message name  
        setAttributeInternal(SCHEDULECOMPLETEDDATE, value);
    }

    /**Gets the attribute value for ACTUAL_COMPLETED_DATE using the alias name ActualCompletedDate
     */
    public Date getActualCompletedDate() {
        return (Date)getAttributeInternal(ACTUALCOMPLETEDDATE);
    }

    /**Sets <code>value</code> as attribute value for ACTUAL_COMPLETED_DATE using the alias name ActualCompletedDate
     */
    public void setActualCompletedDate(Date value) {
        setAttributeInternal(ACTUALCOMPLETEDDATE, value);
    }


    /**Gets the attribute value for RELEASE_SPECIALTY using the alias name ReleaseSpecialty
     */
    public String getReleaseSpecialty() {
        return (String)getAttributeInternal(RELEASESPECIALTY);
    }

    /**Sets <code>value</code> as attribute value for RELEASE_SPECIALTY using the alias name ReleaseSpecialty
     */
    public void setReleaseSpecialty(String value) {
        setAttributeInternal(RELEASESPECIALTY, value);
    }

    /**Gets the attribute value for ACCEPT_SPECIALTY using the alias name AcceptSpecialty
     */
    public String getAcceptSpecialty() {
        return (String)getAttributeInternal(ACCEPTSPECIALTY);
    }

    /**Sets <code>value</code> as attribute value for ACCEPT_SPECIALTY using the alias name AcceptSpecialty
     */
    public void setAcceptSpecialty(String value) {
        setAttributeInternal(ACCEPTSPECIALTY, value);
    }

    /**Gets the attribute value for ATTRIBUTE1 using the alias name Attribute1
     */
    public String getAttribute1() {
        return (String)getAttributeInternal(ATTRIBUTE1);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE1 using the alias name Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**Gets the attribute value for ATTRIBUTE2 using the alias name Attribute2
     */
    public String getAttribute2() {
        return (String)getAttributeInternal(ATTRIBUTE2);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE2 using the alias name Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**Gets the attribute value for ATTRIBUTE3 using the alias name Attribute3
     */
    public String getAttribute3() {
        return (String)getAttributeInternal(ATTRIBUTE3);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE3 using the alias name Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**Gets the attribute value for ATTRIBUTE4 using the alias name Attribute4
     */
    public String getAttribute4() {
        return (String)getAttributeInternal(ATTRIBUTE4);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE4 using the alias name Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**Gets the attribute value for ATTRIBUTE5 using the alias name Attribute5
     */
    public String getAttribute5() {
        return (String)getAttributeInternal(ATTRIBUTE5);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE5 using the alias name Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**Gets the attribute value for ATTRIBUTE6 using the alias name Attribute6
     */
    public String getAttribute6() {
        return (String)getAttributeInternal(ATTRIBUTE6);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE6 using the alias name Attribute6
     */
    public void setAttribute6(String value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**Gets the attribute value for ATTRIBUTE7 using the alias name Attribute7
     */
    public String getAttribute7() {
        return (String)getAttributeInternal(ATTRIBUTE7);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE7 using the alias name Attribute7
     */
    public void setAttribute7(String value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**Gets the attribute value for ATTRIBUTE8 using the alias name Attribute8
     */
    public String getAttribute8() {
        return (String)getAttributeInternal(ATTRIBUTE8);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE8 using the alias name Attribute8
     */
    public void setAttribute8(String value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**Gets the attribute value for ATTRIBUTE9 using the alias name Attribute9
     */
    public String getAttribute9() {
        return (String)getAttributeInternal(ATTRIBUTE9);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE9 using the alias name Attribute9
     */
    public void setAttribute9(String value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**Gets the attribute value for CREATED_BY using the alias name CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as attribute value for CREATED_BY using the alias name CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for CREATION_DATE using the alias name CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as attribute value for CREATION_DATE using the alias name CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }


    /**Gets the attribute value for LAST_UPDATE_LOGIN using the alias name LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as attribute value for LAST_UPDATE_LOGIN using the alias name LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CUXDLVID:
            return getCuxDlvId();
        case DELIVERABLEID:
            return getDeliverableId();
        case PROJECTID:
            return getProjectId();
        case PROJECTNAME:
            return getProjectName();
        case STATUS:
            return getStatus();
        case STATUSNAME:
            return getStatusName();
        case DOCTYPE:
            return getDocType();
        case DOCTYPENAME:
            return getDocTypeName();
        case TASKID:
            return getTaskId();
        case TASKNAME:
            return getTaskName();
        case DESIGNNODE:
            return getDesignNode();
        case ACTUALCOUNTERSIGNDATE:
            return getActualCountersignDate();
        case ACTUALRELEASEDATE:
            return getActualReleaseDate();
        case HQD:
            return getHqd();
        case HQDNAME:
            return getHqdName();
        case JTD:
            return getJtd();
        case JTDNAME:
            return getJtdName();
        case WJHZD:
            return getWjhzd();
        case WJHZDNAME:
            return getWjhzdName();
        case DOCNAME:
            return getDocName();
        case DOCNUM:
            return getDocNum();
        case DESIGNER:
            return getDesigner();
        case DESIGNERNAME:
            return getDesignerName();
        case CHECKER:
            return getChecker();
        case CHECKERNAME:
            return getCheckerName();
        case VERIFIER:
            return getVerifier();
        case VERIFIERNAME:
            return getVerifierName();
        case AUTHORIZER:
            return getAuthorizer();
        case AUTHORIZERNAME:
            return getAuthorizerName();
        case WEIGHTS:
            return getWeights();
        case SCHEDULECOMPLETEDDATE:
            return getScheduleCompletedDate();
        case ACTUALCOMPLETEDDATE:
            return getActualCompletedDate();
        case RELEASEDEPT:
            return getReleaseDept();
        case RELEASEDEPTNAME:
            return getReleaseDeptName();
        case RELEASESPECIALTY:
            return getReleaseSpecialty();
        case RELEASESPECIALTYNAME:
            return getReleaseSpecialtyName();
        case ACCEPTSPECIALTY:
            return getAcceptSpecialty();
        case ACCEPTSPECIALTYNAME:
            return getAcceptSpecialtyName();
        case ATTRIBUTE1:
            return getAttribute1();
        case ATTRIBUTE2:
            return getAttribute2();
        case ATTRIBUTE3:
            return getAttribute3();
        case ATTRIBUTE4:
            return getAttribute4();
        case ATTRIBUTE5:
            return getAttribute5();
        case ATTRIBUTE6:
            return getAttribute6();
        case ATTRIBUTE7:
            return getAttribute7();
        case ATTRIBUTE8:
            return getAttribute8();
        case ATTRIBUTE9:
            return getAttribute9();
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case WFITEMTYPE:
            return getWfItemtype();
        case WFITEMKEY:
            return getWfItemkey();
        case CHECKED:
            return getChecked();
        case UPDATEALLOWED:
            return getUpdateAllowed();
        case AUPDATEALLOWED:
            return getAUpdateAllowed();
        case BUPDATEALLOWED:
            return getBUpdateAllowed();
        case HQJTENABLED:
            return getHqjtEnabled();
        case WJHZENABLED:
            return getWjhzEnabled();
        case ACCESSLEVEL:
            return getAccessLevel();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CUXDLVID:
            setCuxDlvId((Number)value);
            return;
        case DELIVERABLEID:
            setDeliverableId((Number)value);
            return;
        case PROJECTID:
            setProjectId((Number)value);
            return;
        case PROJECTNAME:
            setProjectName((String)value);
            return;
        case STATUS:
            setStatus((String)value);
            return;
        case STATUSNAME:
            setStatusName((String)value);
            return;
        case DOCTYPE:
            setDocType((String)value);
            return;
        case DOCTYPENAME:
            setDocTypeName((String)value);
            return;
        case TASKID:
            setTaskId((Number)value);
            return;
        case TASKNAME:
            setTaskName((String)value);
            return;
        case DESIGNNODE:
            setDesignNode((String)value);
            return;
        case ACTUALCOUNTERSIGNDATE:
            setActualCountersignDate((Date)value);
            return;
        case ACTUALRELEASEDATE:
            setActualReleaseDate((Date)value);
            return;
        case HQD:
            setHqd((String)value);
            return;
        case HQDNAME:
            setHqdName((String)value);
            return;
        case JTD:
            setJtd((String)value);
            return;
        case JTDNAME:
            setJtdName((String)value);
            return;
        case WJHZD:
            setWjhzd((String)value);
            return;
        case WJHZDNAME:
            setWjhzdName((String)value);
            return;
        case DOCNAME:
            setDocName((String)value);
            return;
        case DOCNUM:
            setDocNum((String)value);
            return;
        case DESIGNER:
            setDesigner((Number)value);
            return;
        case DESIGNERNAME:
            setDesignerName((String)value);
            return;
        case CHECKER:
            setChecker((Number)value);
            return;
        case CHECKERNAME:
            setCheckerName((String)value);
            return;
        case VERIFIER:
            setVerifier((Number)value);
            return;
        case VERIFIERNAME:
            setVerifierName((String)value);
            return;
        case AUTHORIZER:
            setAuthorizer((Number)value);
            return;
        case AUTHORIZERNAME:
            setAuthorizerName((String)value);
            return;
        case WEIGHTS:
            setWeights((Number)value);
            return;
        case SCHEDULECOMPLETEDDATE:
            setScheduleCompletedDate((Date)value);
            return;
        case ACTUALCOMPLETEDDATE:
            setActualCompletedDate((Date)value);
            return;
        case RELEASEDEPT:
            setReleaseDept((Number)value);
            return;
        case RELEASEDEPTNAME:
            setReleaseDeptName((String)value);
            return;
        case RELEASESPECIALTY:
            setReleaseSpecialty((String)value);
            return;
        case RELEASESPECIALTYNAME:
            setReleaseSpecialtyName((String)value);
            return;
        case ACCEPTSPECIALTY:
            setAcceptSpecialty((String)value);
            return;
        case ACCEPTSPECIALTYNAME:
            setAcceptSpecialtyName((String)value);
            return;
        case ATTRIBUTE1:
            setAttribute1((String)value);
            return;
        case ATTRIBUTE2:
            setAttribute2((String)value);
            return;
        case ATTRIBUTE3:
            setAttribute3((String)value);
            return;
        case ATTRIBUTE4:
            setAttribute4((String)value);
            return;
        case ATTRIBUTE5:
            setAttribute5((String)value);
            return;
        case ATTRIBUTE6:
            setAttribute6((String)value);
            return;
        case ATTRIBUTE7:
            setAttribute7((String)value);
            return;
        case ATTRIBUTE8:
            setAttribute8((String)value);
            return;
        case ATTRIBUTE9:
            setAttribute9((String)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        case WFITEMTYPE:
            setWfItemtype((String)value);
            return;
        case WFITEMKEY:
            setWfItemkey((String)value);
            return;
        case CHECKED:
            setChecked((String)value);
            return;
        case UPDATEALLOWED:
            setUpdateAllowed((Boolean)value);
            return;
        case AUPDATEALLOWED:
            setAUpdateAllowed((Boolean)value);
            return;
        case BUPDATEALLOWED:
            setBUpdateAllowed((Boolean)value);
            return;
        case HQJTENABLED:
            setHqjtEnabled((Boolean)value);
            return;
        case WJHZENABLED:
            setWjhzEnabled((Boolean)value);
            return;
        case ACCESSLEVEL:
            setAccessLevel((Boolean)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for RELEASE_DEPT using the alias name ReleaseDept
     */
    public Number getReleaseDept() {
        return (Number)getAttributeInternal(RELEASEDEPT);
    }

    /**Sets <code>value</code> as attribute value for RELEASE_DEPT using the alias name ReleaseDept
     */
    public void setReleaseDept(Number value) {
        setAttributeInternal(RELEASEDEPT, value);
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

    /**Gets the attribute value for the calculated attribute DocTypeName
     */
    public String getDocTypeName() {
        return (String)getAttributeInternal(DOCTYPENAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DocTypeName
     */
    public void setDocTypeName(String value) {
        setAttributeInternal(DOCTYPENAME, value);
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

    /**Gets the attribute value for the calculated attribute DesignerName
     */
    public String getDesignerName() {
        return (String)getAttributeInternal(DESIGNERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DesignerName
     */
    public void setDesignerName(String value) {
        setAttributeInternal(DESIGNERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute CheckerName
     */
    public String getCheckerName() {
        return (String)getAttributeInternal(CHECKERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CheckerName
     */
    public void setCheckerName(String value) {
        setAttributeInternal(CHECKERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute VerifierName
     */
    public String getVerifierName() {
        return (String)getAttributeInternal(VERIFIERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute VerifierName
     */
    public void setVerifierName(String value) {
        setAttributeInternal(VERIFIERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute AuthorizerName
     */
    public String getAuthorizerName() {
        return (String)getAttributeInternal(AUTHORIZERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AuthorizerName
     */
    public void setAuthorizerName(String value) {
        setAttributeInternal(AUTHORIZERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ReleaseDeptName
     */
    public String getReleaseDeptName() {
        return (String)getAttributeInternal(RELEASEDEPTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ReleaseDeptName
     */
    public void setReleaseDeptName(String value) {
        setAttributeInternal(RELEASEDEPTNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ReleaseSpecialtyName
     */
    public String getReleaseSpecialtyName() {
        return (String)getAttributeInternal(RELEASESPECIALTYNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ReleaseSpecialtyName
     */
    public void setReleaseSpecialtyName(String value) {
        setAttributeInternal(RELEASESPECIALTYNAME, value);
    }

    /**Gets the attribute value for the calculated attribute AcceptSpecialtyName
     */
    public String getAcceptSpecialtyName() {
        return (String)getAttributeInternal(ACCEPTSPECIALTYNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AcceptSpecialtyName
     */
    public void setAcceptSpecialtyName(String value) {
        setAttributeInternal(ACCEPTSPECIALTYNAME, value);
    }

    /**Gets the attribute value for LAST_UPDATED_BY using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as attribute value for LAST_UPDATED_BY using the alias name LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for LAST_UPDATE_DATE using the alias name LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as attribute value for LAST_UPDATE_DATE using the alias name LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }


    /**Gets the attribute value for the calculated attribute Checked
     */
    public String getChecked() {
        return (String)getAttributeInternal(CHECKED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Checked
     */
    public void setChecked(String value) {
        setAttributeInternal(CHECKED, value);
    }

    /**Gets the attribute value for the calculated attribute UpdateAllowed
     */
    public Boolean getUpdateAllowed() {
        return (Boolean)getAttributeInternal(UPDATEALLOWED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute UpdateAllowed
     */
    public void setUpdateAllowed(Boolean value) {
        setAttributeInternal(UPDATEALLOWED, value);
    }

    /**Gets the attribute value for WF_ITEMKEY using the alias name WfItemkey
     */
    public String getWfItemkey() {
        return (String)getAttributeInternal(WFITEMKEY);
    }

    /**Sets <code>value</code> as attribute value for WF_ITEMKEY using the alias name WfItemkey
     */
    public void setWfItemkey(String value) {
        setAttributeInternal(WFITEMKEY, value);
    }

    /**Gets the attribute value for WF_ITEMTYPE using the alias name WfItemtype
     */
    public String getWfItemtype() {
        return (String)getAttributeInternal(WFITEMTYPE);
    }

    /**Sets <code>value</code> as attribute value for WF_ITEMTYPE using the alias name WfItemtype
     */
    public void setWfItemtype(String value) {
        setAttributeInternal(WFITEMTYPE, value);
    }

    /**Gets the attribute value for the calculated attribute AccessLevel
     */
    public Boolean getAccessLevel() {
        return (Boolean)getAttributeInternal(ACCESSLEVEL);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AccessLevel
     */
    public void setAccessLevel(Boolean value) {
        setAttributeInternal(ACCESSLEVEL, value);
    }

    /**Gets the attribute value for the calculated attribute DesignNode
     */
    public String getDesignNode() {
        return (String)getAttributeInternal(DESIGNNODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DesignNode
     */
    public void setDesignNode(String value) {
        setAttributeInternal(DESIGNNODE, value);
    }

    /**Gets the attribute value for the calculated attribute ActualCountersignDate
     */
    public Date getActualCountersignDate() {
        return (Date)getAttributeInternal(ACTUALCOUNTERSIGNDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ActualCountersignDate
     */
    public void setActualCountersignDate(Date value) {
        setAttributeInternal(ACTUALCOUNTERSIGNDATE, value);
    }

    /**Gets the attribute value for the calculated attribute ActualReleaseDate
     */
    public Date getActualReleaseDate() {
        return (Date)getAttributeInternal(ACTUALRELEASEDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ActualReleaseDate
     */
    public void setActualReleaseDate(Date value) {
        setAttributeInternal(ACTUALRELEASEDATE, value);
    }

    /**Gets the attribute value for the calculated attribute Hqd
     */
    public String getHqd() {
        return (String)getAttributeInternal(HQD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Hqd
     */
    public void setHqd(String value) {
        setAttributeInternal(HQD, value);
    }

    /**Gets the attribute value for the calculated attribute Jtd
     */
    public String getJtd() {
        return (String)getAttributeInternal(JTD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Jtd
     */
    public void setJtd(String value) {
        setAttributeInternal(JTD, value);
    }

    /**Gets the attribute value for the calculated attribute Wjhzd
     */
    public String getWjhzd() {
        return (String)getAttributeInternal(WJHZD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Wjhzd
     */
    public void setWjhzd(String value) {
        setAttributeInternal(WJHZD, value);
    }

    /**Gets the attribute value for the calculated attribute HqjtEnabled
     */
    public Boolean getHqjtEnabled() {
        return (Boolean)getAttributeInternal(HQJTENABLED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HqjtEnabled
     */
    public void setHqjtEnabled(Boolean value) {
        setAttributeInternal(HQJTENABLED, value);
    }

    /**Gets the attribute value for the calculated attribute WjhzEnabled
     */
    public Boolean getWjhzEnabled() {
        return (Boolean)getAttributeInternal(WJHZENABLED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute WjhzEnabled
     */
    public void setWjhzEnabled(Boolean value) {
        setAttributeInternal(WJHZENABLED, value);
    }

    /**Gets the attribute value for the calculated attribute AUpdateAllowed
     */
    public Boolean getAUpdateAllowed() {
        return (Boolean)getAttributeInternal(AUPDATEALLOWED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AUpdateAllowed
     */
    public void setAUpdateAllowed(Boolean value) {
        setAttributeInternal(AUPDATEALLOWED, value);
    }

    /**Gets the attribute value for the calculated attribute BUpdateAllowed
     */
    public Boolean getBUpdateAllowed() {
        return (Boolean)getAttributeInternal(BUPDATEALLOWED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute BUpdateAllowed
     */
    public void setBUpdateAllowed(Boolean value) {
        setAttributeInternal(BUPDATEALLOWED, value);
    }

    /**Gets the attribute value for the calculated attribute HqdName
     */
    public String getHqdName() {
        return (String)getAttributeInternal(HQDNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HqdName
     */
    public void setHqdName(String value) {
        setAttributeInternal(HQDNAME, value);
    }

    /**Gets the attribute value for the calculated attribute JtdName
     */
    public String getJtdName() {
        return (String)getAttributeInternal(JTDNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute JtdName
     */
    public void setJtdName(String value) {
        setAttributeInternal(JTDNAME, value);
    }

    /**Gets the attribute value for the calculated attribute WjhzdName
     */
    public String getWjhzdName() {
        return (String)getAttributeInternal(WJHZDNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute WjhzdName
     */
    public void setWjhzdName(String value) {
        setAttributeInternal(WJHZDNAME, value);
    }
}
