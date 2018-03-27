package cux.oracle.apps.cux.qhse.rectify.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxRectifyNotifyLinesVORowImpl extends OAViewRowImpl {
    public static final int HEADERID = 0;
    public static final int LINEID = 1;
    public static final int PROBLEMNAME = 2;
    public static final int PROBLEMDESC = 3;
    public static final int STATUSCODE = 4;
    public static final int STATUSNAME = 5;
    public static final int PROBLEMCATEGORY = 6;
    public static final int PRODUTYPERSONID = 7;
    public static final int PRODUTYPERSONNAME = 8;
    public static final int COMPLETEDATE = 9;
    public static final int CHECKDATE = 10;
    public static final int CHECKRANGE = 11;
    public static final int CREATIONDATE = 12;
    public static final int CREATEDBY = 13;
    public static final int LASTUPDATEDATE = 14;
    public static final int LASTUPDATEDBY = 15;
    public static final int LASTUPDATELOGIN = 16;
    public static final int ATTRIBUTECATEGORY = 17;
    public static final int ATTRIBUTE1 = 18;
    public static final int ATTRIBUTE2 = 19;
    public static final int ATTRIBUTE3 = 20;
    public static final int ATTRIBUTE4 = 21;
    public static final int ATTRIBUTE5 = 22;

    /**This is the default constructor (do not remove)
     */
    public CuxRectifyNotifyLinesVORowImpl() {
    }

    /**Gets CuxRectifyNotifyLinesEO entity object.
     */
    public CuxRectifyNotifyLinesEOImpl getCuxRectifyNotifyLinesEO() {
        return (CuxRectifyNotifyLinesEOImpl)getEntity(0);
    }

    /**Gets the attribute value for HEADER_ID using the alias name HeaderId
     */
    public Number getHeaderId() {
        return (Number)getAttributeInternal(HEADERID);
    }

    /**Sets <code>value</code> as attribute value for HEADER_ID using the alias name HeaderId
     */
    public void setHeaderId(Number value) {
        setAttributeInternal(HEADERID, value);
    }

    /**Gets the attribute value for LINE_ID using the alias name LineId
     */
    public Number getLineId() {
        return (Number)getAttributeInternal(LINEID);
    }

    /**Sets <code>value</code> as attribute value for LINE_ID using the alias name LineId
     */
    public void setLineId(Number value) {
        setAttributeInternal(LINEID, value);
    }

    /**Gets the attribute value for PROBLEM_NAME using the alias name ProblemName
     */
    public String getProblemName() {
        return (String)getAttributeInternal(PROBLEMNAME);
    }

    /**Sets <code>value</code> as attribute value for PROBLEM_NAME using the alias name ProblemName
     */
    public void setProblemName(String value) {
        setAttributeInternal(PROBLEMNAME, value);
    }

    /**Gets the attribute value for PROBLEM_DESC using the alias name ProblemDesc
     */
    public String getProblemDesc() {
        return (String)getAttributeInternal(PROBLEMDESC);
    }

    /**Sets <code>value</code> as attribute value for PROBLEM_DESC using the alias name ProblemDesc
     */
    public void setProblemDesc(String value) {
        setAttributeInternal(PROBLEMDESC, value);
    }

    /**Gets the attribute value for STATUS_CODE using the alias name StatusCode
     */
    public String getStatusCode() {
        return (String)getAttributeInternal(STATUSCODE);
    }

    /**Sets <code>value</code> as attribute value for STATUS_CODE using the alias name StatusCode
     */
    public void setStatusCode(String value) {
        setAttributeInternal(STATUSCODE, value);
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

    /**Gets the attribute value for PROBLEM_CATEGORY using the alias name ProblemCategory
     */
    public String getProblemCategory() {
        return (String)getAttributeInternal(PROBLEMCATEGORY);
    }

    /**Sets <code>value</code> as attribute value for PROBLEM_CATEGORY using the alias name ProblemCategory
     */
    public void setProblemCategory(String value) {
        setAttributeInternal(PROBLEMCATEGORY, value);
    }

    /**Gets the attribute value for PRO_DUTY_PERSON_ID using the alias name ProDutyPersonId
     */
    public Number getProDutyPersonId() {
        return (Number)getAttributeInternal(PRODUTYPERSONID);
    }

    /**Sets <code>value</code> as attribute value for PRO_DUTY_PERSON_ID using the alias name ProDutyPersonId
     */
    public void setProDutyPersonId(Number value) {
        setAttributeInternal(PRODUTYPERSONID, value);
    }

    /**Gets the attribute value for the calculated attribute ProDutyPersonName
     */
    public String getProDutyPersonName() {
        return (String)getAttributeInternal(PRODUTYPERSONNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProDutyPersonName
     */
    public void setProDutyPersonName(String value) {
        setAttributeInternal(PRODUTYPERSONNAME, value);
    }

    /**Gets the attribute value for COMPLETE_DATE using the alias name CompleteDate
     */
    public Date getCompleteDate() {
        return (Date)getAttributeInternal(COMPLETEDATE);
    }

    /**Sets <code>value</code> as attribute value for COMPLETE_DATE using the alias name CompleteDate
     */
    public void setCompleteDate(Date value) {
        setAttributeInternal(COMPLETEDATE, value);
    }

    /**Gets the attribute value for CHECK_DATE using the alias name CheckDate
     */
    public Date getCheckDate() {
        return (Date)getAttributeInternal(CHECKDATE);
    }

    /**Sets <code>value</code> as attribute value for CHECK_DATE using the alias name CheckDate
     */
    public void setCheckDate(Date value) {
        setAttributeInternal(CHECKDATE, value);
    }

    /**Gets the attribute value for CHECK_RANGE using the alias name CheckRange
     */
    public String getCheckRange() {
        return (String)getAttributeInternal(CHECKRANGE);
    }

    /**Sets <code>value</code> as attribute value for CHECK_RANGE using the alias name CheckRange
     */
    public void setCheckRange(String value) {
        setAttributeInternal(CHECKRANGE, value);
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

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case HEADERID:
            return getHeaderId();
        case LINEID:
            return getLineId();
        case PROBLEMNAME:
            return getProblemName();
        case PROBLEMDESC:
            return getProblemDesc();
        case STATUSCODE:
            return getStatusCode();
        case STATUSNAME:
            return getStatusName();
        case PROBLEMCATEGORY:
            return getProblemCategory();
        case PRODUTYPERSONID:
            return getProDutyPersonId();
        case PRODUTYPERSONNAME:
            return getProDutyPersonName();
        case COMPLETEDATE:
            return getCompleteDate();
        case CHECKDATE:
            return getCheckDate();
        case CHECKRANGE:
            return getCheckRange();
        case CREATIONDATE:
            return getCreationDate();
        case CREATEDBY:
            return getCreatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
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
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case HEADERID:
            setHeaderId((Number)value);
            return;
        case LINEID:
            setLineId((Number)value);
            return;
        case PROBLEMNAME:
            setProblemName((String)value);
            return;
        case PROBLEMDESC:
            setProblemDesc((String)value);
            return;
        case STATUSCODE:
            setStatusCode((String)value);
            return;
        case STATUSNAME:
            setStatusName((String)value);
            return;
        case PROBLEMCATEGORY:
            setProblemCategory((String)value);
            return;
        case PRODUTYPERSONID:
            setProDutyPersonId((Number)value);
            return;
        case PRODUTYPERSONNAME:
            setProDutyPersonName((String)value);
            return;
        case COMPLETEDATE:
            setCompleteDate((Date)value);
            return;
        case CHECKDATE:
            setCheckDate((Date)value);
            return;
        case CHECKRANGE:
            setCheckRange((String)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
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
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}