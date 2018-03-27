package cux.oracle.apps.per.review.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AppraisalEmpVORowImpl extends OAViewRowImpl {
    public static final int APPRAISALID = 0;
    public static final int ORGANIZATIONID = 1;
    public static final int APPRAISALPERSONID = 2;
    public static final int PROMISERPERSONID = 3;
    public static final int WFITEMKEY = 4;
    public static final int EFFECTIVEDATE = 5;
    public static final int SIGNDATE = 6;
    public static final int APPRAISALGROUPID = 7;
    public static final int PHASEID = 8;
    public static final int PERIODTYPEID = 9;
    public static final int APPRAISALYEAR = 10;
    public static final int PERIODID = 11;
    public static final int STATUSID = 12;
    public static final int ENABLED = 13;
    public static final int APPROVALNUM = 14;
    public static final int CALMARK = 15;
    public static final int ORIMARK = 16;
    public static final int MODMARK = 17;
    public static final int FINALMARK = 18;
    public static final int ORILEVEL = 19;
    public static final int FINALLEVEL = 20;
    public static final int ATTRIBUTECATEGORY = 21;
    public static final int ATTRIBUTE1 = 22;
    public static final int ATTRIBUTE2 = 23;
    public static final int ATTRIBUTE3 = 24;
    public static final int ATTRIBUTE4 = 25;
    public static final int ATTRIBUTE5 = 26;
    public static final int LASTUPDATEDATE = 27;
    public static final int LASTUPDATEDBY = 28;
    public static final int LASTUPDATELOGIN = 29;
    public static final int CREATEDBY = 30;
    public static final int CREATIONDATE = 31;
    public static final int DEPTNAME = 32;
    public static final int PERIODNAME = 33;
    public static final int PHASENAME = 34;
    public static final int STATUSNAME = 35;
    public static final int EMPNAME = 36;
    public static final int EMPLOYEENUMBER = 37;
    public static final int MANAGERNAME = 38;
    public static final int EMPPOSITION = 39;
    public static final int MANAGERPOSITION = 40;
    public static final int EMPGROUP = 41;
    public static final int CURRENTPERFORMER = 42;
    public static final int ISPROMISEE = 43;
    public static final int APPRAISALDATE = 44;
    public static final int YEAR = 45;
    public static final int COMPANYID = 46;
    public static final int SELECTFLAG = 47;

    /**This is the default constructor (do not remove)
     */
    public AppraisalEmpVORowImpl() {
    }

    /**Gets AppraisalEO entity object.
     */
    public AppraisalEOImpl getAppraisalEO() {
        return (AppraisalEOImpl)getEntity(0);
    }

    /**Gets the attribute value for APPRAISAL_ID using the alias name AppraisalId
     */
    public Number getAppraisalId() {
        return (Number)getAttributeInternal(APPRAISALID);
    }

    /**Sets <code>value</code> as attribute value for APPRAISAL_ID using the alias name AppraisalId
     */
    public void setAppraisalId(Number value) {
        setAttributeInternal(APPRAISALID, value);
    }

    /**Gets the attribute value for ORGANIZATION_ID using the alias name OrganizationId
     */
    public Number getOrganizationId() {
        return (Number)getAttributeInternal(ORGANIZATIONID);
    }

    /**Sets <code>value</code> as attribute value for ORGANIZATION_ID using the alias name OrganizationId
     */
    public void setOrganizationId(Number value) {
        setAttributeInternal(ORGANIZATIONID, value);
    }

    /**Gets the attribute value for APPRAISAL_PERSON_ID using the alias name AppraisalPersonId
     */
    public Number getAppraisalPersonId() {
        return (Number)getAttributeInternal(APPRAISALPERSONID);
    }

    /**Sets <code>value</code> as attribute value for APPRAISAL_PERSON_ID using the alias name AppraisalPersonId
     */
    public void setAppraisalPersonId(Number value) {
        setAttributeInternal(APPRAISALPERSONID, value);
    }


    /**Gets the attribute value for PROMISER_PERSON_ID using the alias name PromiserPersonId
     */
    public Number getPromiserPersonId() {
        return (Number)getAttributeInternal(PROMISERPERSONID);
    }

    /**Sets <code>value</code> as attribute value for PROMISER_PERSON_ID using the alias name PromiserPersonId
     */
    public void setPromiserPersonId(Number value) {
        setAttributeInternal(PROMISERPERSONID, value);
    }

    /**Gets the attribute value for WF_ITEM_KEY using the alias name WfItemKey
     */
    public String getWfItemKey() {
        return (String)getAttributeInternal(WFITEMKEY);
    }

    /**Sets <code>value</code> as attribute value for WF_ITEM_KEY using the alias name WfItemKey
     */
    public void setWfItemKey(String value) {
        setAttributeInternal(WFITEMKEY, value);
    }

    /**Gets the attribute value for EFFECTIVE_DATE using the alias name EffectiveDate
     */
    public Date getEffectiveDate() {
        return (Date)getAttributeInternal(EFFECTIVEDATE);
    }

    /**Sets <code>value</code> as attribute value for EFFECTIVE_DATE using the alias name EffectiveDate
     */
    public void setEffectiveDate(Date value) {
        setAttributeInternal(EFFECTIVEDATE, value);
    }

    /**Gets the attribute value for SIGN_DATE using the alias name SignDate
     */
    public Date getSignDate() {
        return (Date)getAttributeInternal(SIGNDATE);
    }

    /**Sets <code>value</code> as attribute value for SIGN_DATE using the alias name SignDate
     */
    public void setSignDate(Date value) {
        setAttributeInternal(SIGNDATE, value);
    }

    /**Gets the attribute value for APPRAISAL_GROUP_ID using the alias name AppraisalGroupId
     */
    public String getAppraisalGroupId() {
        return (String)getAttributeInternal(APPRAISALGROUPID);
    }

    /**Sets <code>value</code> as attribute value for APPRAISAL_GROUP_ID using the alias name AppraisalGroupId
     */
    public void setAppraisalGroupId(String value) {
        setAttributeInternal(APPRAISALGROUPID, value);
    }

    /**Gets the attribute value for PHASE_ID using the alias name PhaseId
     */
    public String getPhaseId() {
        return (String)getAttributeInternal(PHASEID);
    }

    /**Sets <code>value</code> as attribute value for PHASE_ID using the alias name PhaseId
     */
    public void setPhaseId(String value) {
        setAttributeInternal(PHASEID, value);
    }

    /**Gets the attribute value for PERIOD_TYPE_ID using the alias name PeriodTypeId
     */
    public String getPeriodTypeId() {
        return (String)getAttributeInternal(PERIODTYPEID);
    }

    /**Sets <code>value</code> as attribute value for PERIOD_TYPE_ID using the alias name PeriodTypeId
     */
    public void setPeriodTypeId(String value) {
        setAttributeInternal(PERIODTYPEID, value);
    }

    /**Gets the attribute value for APPRAISAL_YEAR using the alias name AppraisalYear
     */
    public String getAppraisalYear() {
        return (String)getAttributeInternal(APPRAISALYEAR);
    }

    /**Sets <code>value</code> as attribute value for APPRAISAL_YEAR using the alias name AppraisalYear
     */
    public void setAppraisalYear(String value) {
        setAttributeInternal(APPRAISALYEAR, value);
    }

    /**Gets the attribute value for PERIOD_ID using the alias name PeriodId
     */
    public String getPeriodId() {
        return (String)getAttributeInternal(PERIODID);
    }

    /**Sets <code>value</code> as attribute value for PERIOD_ID using the alias name PeriodId
     */
    public void setPeriodId(String value) {
        setAttributeInternal(PERIODID, value);
    }

    /**Gets the attribute value for STATUS_ID using the alias name StatusId
     */
    public String getStatusId() {
        return (String)getAttributeInternal(STATUSID);
    }

    /**Sets <code>value</code> as attribute value for STATUS_ID using the alias name StatusId
     */
    public void setStatusId(String value) {
        setAttributeInternal(STATUSID, value);
    }

    /**Gets the attribute value for ENABLED using the alias name Enabled
     */
    public String getEnabled() {
        return (String)getAttributeInternal(ENABLED);
    }

    /**Sets <code>value</code> as attribute value for ENABLED using the alias name Enabled
     */
    public void setEnabled(String value) {
        setAttributeInternal(ENABLED, value);
    }

    /**Gets the attribute value for APPROVAL_NUM using the alias name ApprovalNum
     */
    public Number getApprovalNum() {
        return (Number)getAttributeInternal(APPROVALNUM);
    }

    /**Sets <code>value</code> as attribute value for APPROVAL_NUM using the alias name ApprovalNum
     */
    public void setApprovalNum(Number value) {
        setAttributeInternal(APPROVALNUM, value);
    }

    /**Gets the attribute value for CAL_MARK using the alias name CalMark
     */
    public Number getCalMark() {
        return (Number)getAttributeInternal(CALMARK);
    }

    /**Sets <code>value</code> as attribute value for CAL_MARK using the alias name CalMark
     */
    public void setCalMark(Number value) {
        setAttributeInternal(CALMARK, value);
    }

    /**Gets the attribute value for ORI_MARK using the alias name OriMark
     */
    public Number getOriMark() {
        return (Number)getAttributeInternal(ORIMARK);
    }

    /**Sets <code>value</code> as attribute value for ORI_MARK using the alias name OriMark
     */
    public void setOriMark(Number value) {
        setAttributeInternal(ORIMARK, value);
    }

    /**Gets the attribute value for MOD_MARK using the alias name ModMark
     */
    public Number getModMark() {
        return (Number)getAttributeInternal(MODMARK);
    }

    /**Sets <code>value</code> as attribute value for MOD_MARK using the alias name ModMark
     */
    public void setModMark(Number value) {
        setAttributeInternal(MODMARK, value);
    }

    /**Gets the attribute value for FINAL_MARK using the alias name FinalMark
     */
    public Number getFinalMark() {
        return (Number)getAttributeInternal(FINALMARK);
    }

    /**Sets <code>value</code> as attribute value for FINAL_MARK using the alias name FinalMark
     */
    public void setFinalMark(Number value) {
        setAttributeInternal(FINALMARK, value);
    }

    /**Gets the attribute value for ORI_LEVEL using the alias name OriLevel
     */
    public String getOriLevel() {
        return (String)getAttributeInternal(ORILEVEL);
    }

    /**Sets <code>value</code> as attribute value for ORI_LEVEL using the alias name OriLevel
     */
    public void setOriLevel(String value) {
        setAttributeInternal(ORILEVEL, value);
    }

    /**Gets the attribute value for FINAL_LEVEL using the alias name FinalLevel
     */
    public String getFinalLevel() {
        return (String)getAttributeInternal(FINALLEVEL);
    }

    /**Sets <code>value</code> as attribute value for FINAL_LEVEL using the alias name FinalLevel
     */
    public void setFinalLevel(String value) {
        setAttributeInternal(FINALLEVEL, value);
    }

    /**Gets the attribute value for ATTRIBUTE_CATEGORY using the alias name AttributeCategory
     */
    public String getAttributeCategory() {
        return (String)getAttributeInternal(ATTRIBUTECATEGORY);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE_CATEGORY using the alias name AttributeCategory
     */
    public void setAttributeCategory(String value) {
        setAttributeInternal(ATTRIBUTECATEGORY, value);
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

    /**Gets the attribute value for the calculated attribute DeptName
     */
    public String getDeptName() {
        return (String)getAttributeInternal(DEPTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DeptName
     */
    public void setDeptName(String value) {
        setAttributeInternal(DEPTNAME, value);
    }

    /**Gets the attribute value for the calculated attribute PeriodName
     */
    public String getPeriodName() {
        return (String)getAttributeInternal(PERIODNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PeriodName
     */
    public void setPeriodName(String value) {
        setAttributeInternal(PERIODNAME, value);
    }

    /**Gets the attribute value for the calculated attribute PhaseName
     */
    public String getPhaseName() {
        return (String)getAttributeInternal(PHASENAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PhaseName
     */
    public void setPhaseName(String value) {
        setAttributeInternal(PHASENAME, value);
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

    /**Gets the attribute value for the calculated attribute EmpName
     */
    public String getEmpName() {
        return (String)getAttributeInternal(EMPNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpName
     */
    public void setEmpName(String value) {
        setAttributeInternal(EMPNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ManagerName
     */
    public String getManagerName() {
        return (String)getAttributeInternal(MANAGERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ManagerName
     */
    public void setManagerName(String value) {
        setAttributeInternal(MANAGERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ManagerPosition
     */
    public String getManagerPosition() {
        return (String)getAttributeInternal(MANAGERPOSITION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ManagerPosition
     */
    public void setManagerPosition(String value) {
        setAttributeInternal(MANAGERPOSITION, value);
    }

    /**Gets the attribute value for the calculated attribute EmpPosition
     */
    public String getEmpPosition() {
        return (String)getAttributeInternal(EMPPOSITION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpPosition
     */
    public void setEmpPosition(String value) {
        setAttributeInternal(EMPPOSITION, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case APPRAISALID:
            return getAppraisalId();
        case ORGANIZATIONID:
            return getOrganizationId();
        case APPRAISALPERSONID:
            return getAppraisalPersonId();
        case PROMISERPERSONID:
            return getPromiserPersonId();
        case WFITEMKEY:
            return getWfItemKey();
        case EFFECTIVEDATE:
            return getEffectiveDate();
        case SIGNDATE:
            return getSignDate();
        case APPRAISALGROUPID:
            return getAppraisalGroupId();
        case PHASEID:
            return getPhaseId();
        case PERIODTYPEID:
            return getPeriodTypeId();
        case APPRAISALYEAR:
            return getAppraisalYear();
        case PERIODID:
            return getPeriodId();
        case STATUSID:
            return getStatusId();
        case ENABLED:
            return getEnabled();
        case APPROVALNUM:
            return getApprovalNum();
        case CALMARK:
            return getCalMark();
        case ORIMARK:
            return getOriMark();
        case MODMARK:
            return getModMark();
        case FINALMARK:
            return getFinalMark();
        case ORILEVEL:
            return getOriLevel();
        case FINALLEVEL:
            return getFinalLevel();
        case ATTRIBUTECATEGORY:
            return getAttributeCategory();
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
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case DEPTNAME:
            return getDeptName();
        case PERIODNAME:
            return getPeriodName();
        case PHASENAME:
            return getPhaseName();
        case STATUSNAME:
            return getStatusName();
        case EMPNAME:
            return getEmpName();
        case EMPLOYEENUMBER:
            return getEmployeeNumber();
        case MANAGERNAME:
            return getManagerName();
        case EMPPOSITION:
            return getEmpPosition();
        case MANAGERPOSITION:
            return getManagerPosition();
        case EMPGROUP:
            return getEmpGroup();
        case CURRENTPERFORMER:
            return getCurrentPerformer();
        case ISPROMISEE:
            return getIsPromisee();
        case APPRAISALDATE:
            return getAppraisalDate();
        case YEAR:
            return getYear();
        case COMPANYID:
            return getCompanyId();
        case SELECTFLAG:
            return getSelectFlag();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case APPRAISALID:
            setAppraisalId((Number)value);
            return;
        case ORGANIZATIONID:
            setOrganizationId((Number)value);
            return;
        case APPRAISALPERSONID:
            setAppraisalPersonId((Number)value);
            return;
        case PROMISERPERSONID:
            setPromiserPersonId((Number)value);
            return;
        case WFITEMKEY:
            setWfItemKey((String)value);
            return;
        case EFFECTIVEDATE:
            setEffectiveDate((Date)value);
            return;
        case SIGNDATE:
            setSignDate((Date)value);
            return;
        case APPRAISALGROUPID:
            setAppraisalGroupId((String)value);
            return;
        case PHASEID:
            setPhaseId((String)value);
            return;
        case PERIODTYPEID:
            setPeriodTypeId((String)value);
            return;
        case APPRAISALYEAR:
            setAppraisalYear((String)value);
            return;
        case PERIODID:
            setPeriodId((String)value);
            return;
        case STATUSID:
            setStatusId((String)value);
            return;
        case ENABLED:
            setEnabled((String)value);
            return;
        case APPROVALNUM:
            setApprovalNum((Number)value);
            return;
        case CALMARK:
            setCalMark((Number)value);
            return;
        case ORIMARK:
            setOriMark((Number)value);
            return;
        case MODMARK:
            setModMark((Number)value);
            return;
        case FINALMARK:
            setFinalMark((Number)value);
            return;
        case ORILEVEL:
            setOriLevel((String)value);
            return;
        case FINALLEVEL:
            setFinalLevel((String)value);
            return;
        case ATTRIBUTECATEGORY:
            setAttributeCategory((String)value);
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
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case DEPTNAME:
            setDeptName((String)value);
            return;
        case PERIODNAME:
            setPeriodName((String)value);
            return;
        case PHASENAME:
            setPhaseName((String)value);
            return;
        case STATUSNAME:
            setStatusName((String)value);
            return;
        case EMPNAME:
            setEmpName((String)value);
            return;
        case EMPLOYEENUMBER:
            setEmployeeNumber((String)value);
            return;
        case MANAGERNAME:
            setManagerName((String)value);
            return;
        case EMPPOSITION:
            setEmpPosition((String)value);
            return;
        case MANAGERPOSITION:
            setManagerPosition((String)value);
            return;
        case EMPGROUP:
            setEmpGroup((String)value);
            return;
        case CURRENTPERFORMER:
            setCurrentPerformer((String)value);
            return;
        case ISPROMISEE:
            setIsPromisee((String)value);
            return;
        case APPRAISALDATE:
            setAppraisalDate((Date)value);
            return;
        case YEAR:
            setYear((String)value);
            return;
        case COMPANYID:
            setCompanyId((Number)value);
            return;
        case SELECTFLAG:
            setSelectFlag((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute CurrentPerformer
     */
    public String getCurrentPerformer() {
        return (String)getAttributeInternal(CURRENTPERFORMER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CurrentPerformer
     */
    public void setCurrentPerformer(String value) {
        setAttributeInternal(CURRENTPERFORMER, value);
    }

    /**Gets the attribute value for the calculated attribute IsPromisee
     */
    public String getIsPromisee() {
        return (String)getAttributeInternal(ISPROMISEE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute IsPromisee
     */
    public void setIsPromisee(String value) {
        setAttributeInternal(ISPROMISEE, value);
    }


    /**Gets the attribute value for the calculated attribute SelectFlag
     */
    public String getSelectFlag() {
        return (String)getAttributeInternal(SELECTFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SelectFlag
     */
    public void setSelectFlag(String value) {
        setAttributeInternal(SELECTFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute AppraisalDate
     */
    public Date getAppraisalDate() {
        return (Date)getAttributeInternal(APPRAISALDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AppraisalDate
     */
    public void setAppraisalDate(Date value) {
        setAttributeInternal(APPRAISALDATE, value);
    }

    /**Gets the attribute value for the calculated attribute Year
     */
    public String getYear() {
        return (String)getAttributeInternal(YEAR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Year
     */
    public void setYear(String value) {
        setAttributeInternal(YEAR, value);
    }

    /**Gets the attribute value for the calculated attribute CompanyId
     */
    public Number getCompanyId() {
        return (Number)getAttributeInternal(COMPANYID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CompanyId
     */
    public void setCompanyId(Number value) {
        setAttributeInternal(COMPANYID, value);
    }

    /**Gets the attribute value for the calculated attribute EmployeeNumber
     */
    public String getEmployeeNumber() {
        return (String)getAttributeInternal(EMPLOYEENUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeeNumber
     */
    public void setEmployeeNumber(String value) {
        setAttributeInternal(EMPLOYEENUMBER, value);
    }

    /**Gets the attribute value for the calculated attribute EmpGroup
     */
    public String getEmpGroup() {
        return (String)getAttributeInternal(EMPGROUP);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpGroup
     */
    public void setEmpGroup(String value) {
        setAttributeInternal(EMPGROUP, value);
    }
}