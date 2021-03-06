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
public class AppraisalAttendVORowImpl extends OAViewRowImpl {
    public static final int ATTENDID = 0;
    public static final int APPRAISALID = 1;
    public static final int PERSONID = 2;
    public static final int EMPNAME = 3;
    public static final int APPSEQ = 4;
    public static final int PERSONTYPE = 5;
    public static final int ENABLEED = 6;
    public static final int OPINIONDATE = 7;
    public static final int OPINIONRESULT = 8;
    public static final int DESCRIPTION = 9;
    public static final int MARKDATE = 10;
    public static final int MARK = 11;
    public static final int LEVELID = 12;
    public static final int MARKLEVEL = 13;
    public static final int ATTRIBUTECATEGORY = 14;
    public static final int ATTRIBUTE1 = 15;
    public static final int ATTRIBUTE2 = 16;
    public static final int ATTRIBUTE3 = 17;
    public static final int ATTRIBUTE4 = 18;
    public static final int ATTRIBUTE5 = 19;
    public static final int ATTRIBUTE6 = 20;
    public static final int ATTRIBUTE7 = 21;
    public static final int ATTRIBUTE8 = 22;
    public static final int ATTRIBUTE9 = 23;
    public static final int ATTRIBUTE10 = 24;
    public static final int LASTUPDATEDATE = 25;
    public static final int LASTUPDATEDBY = 26;
    public static final int LASTUPDATELOGIN = 27;
    public static final int CREATEDBY = 28;
    public static final int CREATIONDATE = 29;

    /**This is the default constructor (do not remove)
     */
    public AppraisalAttendVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute AttendId
     */
    public Number getAttendId() {
        return (Number)getAttributeInternal(ATTENDID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AttendId
     */
    public void setAttendId(Number value) {
        setAttributeInternal(ATTENDID, value);
    }

    /**Gets the attribute value for the calculated attribute AppraisalId
     */
    public Number getAppraisalId() {
        return (Number)getAttributeInternal(APPRAISALID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AppraisalId
     */
    public void setAppraisalId(Number value) {
        setAttributeInternal(APPRAISALID, value);
    }

    /**Gets the attribute value for the calculated attribute PersonId
     */
    public Number getPersonId() {
        return (Number)getAttributeInternal(PERSONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PersonId
     */
    public void setPersonId(Number value) {
        setAttributeInternal(PERSONID, value);
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

    /**Gets the attribute value for the calculated attribute AppSeq
     */
    public Number getAppSeq() {
        return (Number)getAttributeInternal(APPSEQ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AppSeq
     */
    public void setAppSeq(Number value) {
        setAttributeInternal(APPSEQ, value);
    }

    /**Gets the attribute value for the calculated attribute PersonType
     */
    public Number getPersonType() {
        return (Number)getAttributeInternal(PERSONTYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PersonType
     */
    public void setPersonType(Number value) {
        setAttributeInternal(PERSONTYPE, value);
    }

    /**Gets the attribute value for the calculated attribute Enableed
     */
    public Number getEnableed() {
        return (Number)getAttributeInternal(ENABLEED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Enableed
     */
    public void setEnableed(Number value) {
        setAttributeInternal(ENABLEED, value);
    }

    /**Gets the attribute value for the calculated attribute OpinionDate
     */
    public Date getOpinionDate() {
        return (Date)getAttributeInternal(OPINIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OpinionDate
     */
    public void setOpinionDate(Date value) {
        setAttributeInternal(OPINIONDATE, value);
    }

    /**Gets the attribute value for the calculated attribute OpinionResult
     */
    public String getOpinionResult() {
        return (String)getAttributeInternal(OPINIONRESULT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OpinionResult
     */
    public void setOpinionResult(String value) {
        setAttributeInternal(OPINIONRESULT, value);
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

    /**Gets the attribute value for the calculated attribute MarkDate
     */
    public Date getMarkDate() {
        return (Date)getAttributeInternal(MARKDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MarkDate
     */
    public void setMarkDate(Date value) {
        setAttributeInternal(MARKDATE, value);
    }

    /**Gets the attribute value for the calculated attribute Mark
     */
    public Number getMark() {
        return (Number)getAttributeInternal(MARK);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Mark
     */
    public void setMark(Number value) {
        setAttributeInternal(MARK, value);
    }

    /**Gets the attribute value for the calculated attribute LevelId
     */
    public String getLevelId() {
        return (String)getAttributeInternal(LEVELID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LevelId
     */
    public void setLevelId(String value) {
        setAttributeInternal(LEVELID, value);
    }

    /**Gets the attribute value for the calculated attribute MarkLevel
     */
    public String getMarkLevel() {
        return (String)getAttributeInternal(MARKLEVEL);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MarkLevel
     */
    public void setMarkLevel(String value) {
        setAttributeInternal(MARKLEVEL, value);
    }

    /**Gets the attribute value for the calculated attribute AttributeCategory
     */
    public String getAttributeCategory() {
        return (String)getAttributeInternal(ATTRIBUTECATEGORY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AttributeCategory
     */
    public void setAttributeCategory(String value) {
        setAttributeInternal(ATTRIBUTECATEGORY, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute1
     */
    public String getAttribute1() {
        return (String)getAttributeInternal(ATTRIBUTE1);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute2
     */
    public String getAttribute2() {
        return (String)getAttributeInternal(ATTRIBUTE2);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute3
     */
    public String getAttribute3() {
        return (String)getAttributeInternal(ATTRIBUTE3);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute4
     */
    public String getAttribute4() {
        return (String)getAttributeInternal(ATTRIBUTE4);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute5
     */
    public String getAttribute5() {
        return (String)getAttributeInternal(ATTRIBUTE5);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute6
     */
    public String getAttribute6() {
        return (String)getAttributeInternal(ATTRIBUTE6);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute6
     */
    public void setAttribute6(String value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute7
     */
    public String getAttribute7() {
        return (String)getAttributeInternal(ATTRIBUTE7);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute7
     */
    public void setAttribute7(String value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute8
     */
    public String getAttribute8() {
        return (String)getAttributeInternal(ATTRIBUTE8);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute8
     */
    public void setAttribute8(String value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute9
     */
    public String getAttribute9() {
        return (String)getAttributeInternal(ATTRIBUTE9);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute9
     */
    public void setAttribute9(String value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute10
     */
    public String getAttribute10() {
        return (String)getAttributeInternal(ATTRIBUTE10);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute10
     */
    public void setAttribute10(String value) {
        setAttributeInternal(ATTRIBUTE10, value);
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

    /**Gets the attribute value for the calculated attribute LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**Gets the attribute value for the calculated attribute CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for the calculated attribute CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ATTENDID:
            return getAttendId();
        case APPRAISALID:
            return getAppraisalId();
        case PERSONID:
            return getPersonId();
        case EMPNAME:
            return getEmpName();
        case APPSEQ:
            return getAppSeq();
        case PERSONTYPE:
            return getPersonType();
        case ENABLEED:
            return getEnableed();
        case OPINIONDATE:
            return getOpinionDate();
        case OPINIONRESULT:
            return getOpinionResult();
        case DESCRIPTION:
            return getDescription();
        case MARKDATE:
            return getMarkDate();
        case MARK:
            return getMark();
        case LEVELID:
            return getLevelId();
        case MARKLEVEL:
            return getMarkLevel();
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
        case ATTRIBUTE6:
            return getAttribute6();
        case ATTRIBUTE7:
            return getAttribute7();
        case ATTRIBUTE8:
            return getAttribute8();
        case ATTRIBUTE9:
            return getAttribute9();
        case ATTRIBUTE10:
            return getAttribute10();
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
