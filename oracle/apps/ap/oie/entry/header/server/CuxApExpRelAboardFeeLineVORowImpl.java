package cux.oracle.apps.ap.oie.entry.header.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxApExpRelAboardFeeLineVORowImpl extends OAViewRowImpl {
    public static final int RELATEDABOARDFEELINEID = 0;
    public static final int RELATIONSHIPID = 1;
    public static final int OAABROADTRAVELHEADERID = 2;
    public static final int OAABROADTRAVELFEELINEID = 3;
    public static final int RATE = 4;
    public static final int REALTRAVELDAYS = 5;
    public static final int REALPERSONCOUNT = 6;
    public static final int REALPERAMOUNT = 7;
    public static final int REMARK = 8;
    public static final int CREATIONDATE = 9;
    public static final int CREATEDBY = 10;
    public static final int LASTUPDATEDATE = 11;
    public static final int LASTUPDATEDBY = 12;
    public static final int LASTUPDATELOGIN = 13;
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
    public static final int ATTRIBUTE11 = 25;
    public static final int ATTRIBUTE12 = 26;
    public static final int ATTRIBUTE13 = 27;
    public static final int ATTRIBUTE14 = 28;
    public static final int ATTRIBUTE15 = 29;
    public static final int LINENUM = 30;
    public static final int FEETYPE = 31;
    public static final int PLANPERAMOUNT = 32;
    public static final int PLANDAYS = 33;
    public static final int PLANPERSONCOUNT = 34;
    public static final int PLANTOTALAMOUNT = 35;
    public static final int EMPID = 36;
    public static final int EMPNUMBER = 37;
    public static final int EMPNAME = 38;
    public static final int CURRENCYCODE = 39;
    public static final int FEESOURCE = 40;
    public static final int FEESOURCEREMARK = 41;
    public static final int REALTOTALAMOUNT = 42;
    public static final int REALTOTALAMOUNTBEQ = 43;
    public static final int REALPERAMOUNTREADONLY = 44;
    public static final int REALPERDAYSREADONLY = 45;

    /**This is the default constructor (do not remove)
     */
    public CuxApExpRelAboardFeeLineVORowImpl() {
    }

    /**Gets CuxApExpRelAboardFeeLineEO entity object.
     */
    public CuxApExpRelAboardFeeLineEOImpl getCuxApExpRelAboardFeeLineEO() {
        return (CuxApExpRelAboardFeeLineEOImpl)getEntity(0);
    }

    /**Gets the attribute value for RELATED_ABOARD_FEE_LINE_ID using the alias name RelatedAboardFeeLineId
     */
    public Number getRelatedAboardFeeLineId() {
        return (Number)getAttributeInternal(RELATEDABOARDFEELINEID);
    }

    /**Sets <code>value</code> as attribute value for RELATED_ABOARD_FEE_LINE_ID using the alias name RelatedAboardFeeLineId
     */
    public void setRelatedAboardFeeLineId(Number value) {
        setAttributeInternal(RELATEDABOARDFEELINEID, value);
    }

    /**Gets the attribute value for RELATIONSHIP_ID using the alias name RelationshipId
     */
    public Number getRelationshipId() {
        return (Number)getAttributeInternal(RELATIONSHIPID);
    }

    /**Sets <code>value</code> as attribute value for RELATIONSHIP_ID using the alias name RelationshipId
     */
    public void setRelationshipId(Number value) {
        setAttributeInternal(RELATIONSHIPID, value);
    }

    /**Gets the attribute value for OA_ABROAD_TRAVEL_HEADER_ID using the alias name OaAbroadTravelHeaderId
     */
    public Number getOaAbroadTravelHeaderId() {
        return (Number)getAttributeInternal(OAABROADTRAVELHEADERID);
    }

    /**Sets <code>value</code> as attribute value for OA_ABROAD_TRAVEL_HEADER_ID using the alias name OaAbroadTravelHeaderId
     */
    public void setOaAbroadTravelHeaderId(Number value) {
        setAttributeInternal(OAABROADTRAVELHEADERID, value);
    }

    /**Gets the attribute value for OA_ABROAD_TRAVEL_FEE_LINE_ID using the alias name OaAbroadTravelFeeLineId
     */
    public Number getOaAbroadTravelFeeLineId() {
        return (Number)getAttributeInternal(OAABROADTRAVELFEELINEID);
    }

    /**Sets <code>value</code> as attribute value for OA_ABROAD_TRAVEL_FEE_LINE_ID using the alias name OaAbroadTravelFeeLineId
     */
    public void setOaAbroadTravelFeeLineId(Number value) {
        setAttributeInternal(OAABROADTRAVELFEELINEID, value);
    }

    /**Gets the attribute value for RATE using the alias name Rate
     */
    public Number getRate() {
        return (Number)getAttributeInternal(RATE);
    }

    /**Sets <code>value</code> as attribute value for RATE using the alias name Rate
     */
    public void setRate(Number value) {
        setAttributeInternal(RATE, value);
    }

    /**Gets the attribute value for REAL_TRAVEL_DAYS using the alias name RealTravelDays
     */
    public Number getRealTravelDays() {
        return (Number)getAttributeInternal(REALTRAVELDAYS);
    }

    /**Sets <code>value</code> as attribute value for REAL_TRAVEL_DAYS using the alias name RealTravelDays
     */
    public void setRealTravelDays(Number value) {
        setAttributeInternal(REALTRAVELDAYS, value);
    }

    /**Gets the attribute value for REAL_PERSON_COUNT using the alias name RealPersonCount
     */
    public Number getRealPersonCount() {
        return (Number)getAttributeInternal(REALPERSONCOUNT);
    }

    /**Sets <code>value</code> as attribute value for REAL_PERSON_COUNT using the alias name RealPersonCount
     */
    public void setRealPersonCount(Number value) {
        setAttributeInternal(REALPERSONCOUNT, value);
    }

    /**Gets the attribute value for REAL_PER_AMOUNT using the alias name RealPerAmount
     */
    public Number getRealPerAmount() {
        return (Number)getAttributeInternal(REALPERAMOUNT);
    }

    /**Sets <code>value</code> as attribute value for REAL_PER_AMOUNT using the alias name RealPerAmount
     */
    public void setRealPerAmount(Number value) {
        setAttributeInternal(REALPERAMOUNT, value);
    }

    /**Gets the attribute value for REMARK using the alias name Remark
     */
    public String getRemark() {
        return (String)getAttributeInternal(REMARK);
    }

    /**Sets <code>value</code> as attribute value for REMARK using the alias name Remark
     */
    public void setRemark(String value) {
        setAttributeInternal(REMARK, value);
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

    /**Gets the attribute value for ATTRIBUTE10 using the alias name Attribute10
     */
    public String getAttribute10() {
        return (String)getAttributeInternal(ATTRIBUTE10);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE10 using the alias name Attribute10
     */
    public void setAttribute10(String value) {
        setAttributeInternal(ATTRIBUTE10, value);
    }

    /**Gets the attribute value for ATTRIBUTE11 using the alias name Attribute11
     */
    public String getAttribute11() {
        return (String)getAttributeInternal(ATTRIBUTE11);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE11 using the alias name Attribute11
     */
    public void setAttribute11(String value) {
        setAttributeInternal(ATTRIBUTE11, value);
    }

    /**Gets the attribute value for ATTRIBUTE12 using the alias name Attribute12
     */
    public String getAttribute12() {
        return (String)getAttributeInternal(ATTRIBUTE12);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE12 using the alias name Attribute12
     */
    public void setAttribute12(String value) {
        setAttributeInternal(ATTRIBUTE12, value);
    }

    /**Gets the attribute value for ATTRIBUTE13 using the alias name Attribute13
     */
    public String getAttribute13() {
        return (String)getAttributeInternal(ATTRIBUTE13);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE13 using the alias name Attribute13
     */
    public void setAttribute13(String value) {
        setAttributeInternal(ATTRIBUTE13, value);
    }

    /**Gets the attribute value for ATTRIBUTE14 using the alias name Attribute14
     */
    public String getAttribute14() {
        return (String)getAttributeInternal(ATTRIBUTE14);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE14 using the alias name Attribute14
     */
    public void setAttribute14(String value) {
        setAttributeInternal(ATTRIBUTE14, value);
    }

    /**Gets the attribute value for ATTRIBUTE15 using the alias name Attribute15
     */
    public String getAttribute15() {
        return (String)getAttributeInternal(ATTRIBUTE15);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE15 using the alias name Attribute15
     */
    public void setAttribute15(String value) {
        setAttributeInternal(ATTRIBUTE15, value);
    }

    /**Gets the attribute value for LINE_NUM using the alias name LineNum
     */
    public Number getLineNum() {
        return (Number)getAttributeInternal(LINENUM);
    }

    /**Sets <code>value</code> as attribute value for LINE_NUM using the alias name LineNum
     */
    public void setLineNum(Number value) {
        setAttributeInternal(LINENUM, value);
    }

    /**Gets the attribute value for the calculated attribute FeeType
     */
    public String getFeeType() {
        return (String)getAttributeInternal(FEETYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FeeType
     */
    public void setFeeType(String value) {
        setAttributeInternal(FEETYPE, value);
    }

    /**Gets the attribute value for the calculated attribute PlanPerAmount
     */
    public Number getPlanPerAmount() {
        return (Number)getAttributeInternal(PLANPERAMOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlanPerAmount
     */
    public void setPlanPerAmount(Number value) {
        setAttributeInternal(PLANPERAMOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute PlanDays
     */
    public Number getPlanDays() {
        return (Number)getAttributeInternal(PLANDAYS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlanDays
     */
    public void setPlanDays(Number value) {
        setAttributeInternal(PLANDAYS, value);
    }

    /**Gets the attribute value for the calculated attribute PlanPersonCount
     */
    public Number getPlanPersonCount() {
        return (Number)getAttributeInternal(PLANPERSONCOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlanPersonCount
     */
    public void setPlanPersonCount(Number value) {
        setAttributeInternal(PLANPERSONCOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute PlanTotalAmount
     */
    public Number getPlanTotalAmount() {
        return (Number)getAttributeInternal(PLANTOTALAMOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlanTotalAmount
     */
    public void setPlanTotalAmount(Number value) {
        setAttributeInternal(PLANTOTALAMOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute CurrencyCode
     */
    public String getCurrencyCode() {
        return (String)getAttributeInternal(CURRENCYCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CurrencyCode
     */
    public void setCurrencyCode(String value) {
        setAttributeInternal(CURRENCYCODE, value);
    }

    /**Gets the attribute value for the calculated attribute FeeSource
     */
    public String getFeeSource() {
        return (String)getAttributeInternal(FEESOURCE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FeeSource
     */
    public void setFeeSource(String value) {
        setAttributeInternal(FEESOURCE, value);
    }

    /**Gets the attribute value for the calculated attribute FeeSourceRemark
     */
    public String getFeeSourceRemark() {
        return (String)getAttributeInternal(FEESOURCEREMARK);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FeeSourceRemark
     */
    public void setFeeSourceRemark(String value) {
        setAttributeInternal(FEESOURCEREMARK, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case RELATEDABOARDFEELINEID:
            return getRelatedAboardFeeLineId();
        case RELATIONSHIPID:
            return getRelationshipId();
        case OAABROADTRAVELHEADERID:
            return getOaAbroadTravelHeaderId();
        case OAABROADTRAVELFEELINEID:
            return getOaAbroadTravelFeeLineId();
        case RATE:
            return getRate();
        case REALTRAVELDAYS:
            return getRealTravelDays();
        case REALPERSONCOUNT:
            return getRealPersonCount();
        case REALPERAMOUNT:
            return getRealPerAmount();
        case REMARK:
            return getRemark();
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
        case ATTRIBUTE11:
            return getAttribute11();
        case ATTRIBUTE12:
            return getAttribute12();
        case ATTRIBUTE13:
            return getAttribute13();
        case ATTRIBUTE14:
            return getAttribute14();
        case ATTRIBUTE15:
            return getAttribute15();
        case LINENUM:
            return getLineNum();
        case FEETYPE:
            return getFeeType();
        case PLANPERAMOUNT:
            return getPlanPerAmount();
        case PLANDAYS:
            return getPlanDays();
        case PLANPERSONCOUNT:
            return getPlanPersonCount();
        case PLANTOTALAMOUNT:
            return getPlanTotalAmount();
        case EMPID:
            return getEmpId();
        case EMPNUMBER:
            return getEmpNumber();
        case EMPNAME:
            return getEmpName();
        case CURRENCYCODE:
            return getCurrencyCode();
        case FEESOURCE:
            return getFeeSource();
        case FEESOURCEREMARK:
            return getFeeSourceRemark();
        case REALTOTALAMOUNT:
            return getRealTotalAmount();
        case REALTOTALAMOUNTBEQ:
            return getRealTotalAmountBeq();
        case REALPERAMOUNTREADONLY:
            return getRealPerAmountReadonly();
        case REALPERDAYSREADONLY:
            return getRealPerDaysReadonly();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case RELATEDABOARDFEELINEID:
            setRelatedAboardFeeLineId((Number)value);
            return;
        case RELATIONSHIPID:
            setRelationshipId((Number)value);
            return;
        case OAABROADTRAVELHEADERID:
            setOaAbroadTravelHeaderId((Number)value);
            return;
        case OAABROADTRAVELFEELINEID:
            setOaAbroadTravelFeeLineId((Number)value);
            return;
        case RATE:
            setRate((Number)value);
            return;
        case REALTRAVELDAYS:
            setRealTravelDays((Number)value);
            return;
        case REALPERSONCOUNT:
            setRealPersonCount((Number)value);
            return;
        case REALPERAMOUNT:
            setRealPerAmount((Number)value);
            return;
        case REMARK:
            setRemark((String)value);
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
        case ATTRIBUTE10:
            setAttribute10((String)value);
            return;
        case ATTRIBUTE11:
            setAttribute11((String)value);
            return;
        case ATTRIBUTE12:
            setAttribute12((String)value);
            return;
        case ATTRIBUTE13:
            setAttribute13((String)value);
            return;
        case ATTRIBUTE14:
            setAttribute14((String)value);
            return;
        case ATTRIBUTE15:
            setAttribute15((String)value);
            return;
        case LINENUM:
            setLineNum((Number)value);
            return;
        case FEETYPE:
            setFeeType((String)value);
            return;
        case PLANPERAMOUNT:
            setPlanPerAmount((Number)value);
            return;
        case PLANDAYS:
            setPlanDays((Number)value);
            return;
        case PLANPERSONCOUNT:
            setPlanPersonCount((Number)value);
            return;
        case PLANTOTALAMOUNT:
            setPlanTotalAmount((Number)value);
            return;
        case EMPID:
            setEmpId((Number)value);
            return;
        case EMPNUMBER:
            setEmpNumber((String)value);
            return;
        case EMPNAME:
            setEmpName((String)value);
            return;
        case CURRENCYCODE:
            setCurrencyCode((String)value);
            return;
        case FEESOURCE:
            setFeeSource((String)value);
            return;
        case FEESOURCEREMARK:
            setFeeSourceRemark((String)value);
            return;
        case REALTOTALAMOUNT:
            setRealTotalAmount((Number)value);
            return;
        case REALTOTALAMOUNTBEQ:
            setRealTotalAmountBeq((Number)value);
            return;
        case REALPERAMOUNTREADONLY:
            setRealPerAmountReadonly((Boolean)value);
            return;
        case REALPERDAYSREADONLY:
            setRealPerDaysReadonly((Boolean)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute RealTotalAmount
     */
    public Number getRealTotalAmount() {
        return (Number)getAttributeInternal(REALTOTALAMOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RealTotalAmount
     */
    public void setRealTotalAmount(Number value) {
        setAttributeInternal(REALTOTALAMOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute RealTotalAmountBeq
     */
    public Number getRealTotalAmountBeq() {
        return (Number)getAttributeInternal(REALTOTALAMOUNTBEQ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RealTotalAmountBeq
     */
    public void setRealTotalAmountBeq(Number value) {
        setAttributeInternal(REALTOTALAMOUNTBEQ, value);
    }

    /**Gets the attribute value for the calculated attribute EmpId
     */
    public Number getEmpId() {
        return (Number)getAttributeInternal(EMPID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpId
     */
    public void setEmpId(Number value) {
        setAttributeInternal(EMPID, value);
    }

    /**Gets the attribute value for the calculated attribute EmpNumber
     */
    public String getEmpNumber() {
        return (String)getAttributeInternal(EMPNUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmpNumber
     */
    public void setEmpNumber(String value) {
        setAttributeInternal(EMPNUMBER, value);
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

    /**Gets the attribute value for the calculated attribute RealPerAmountReadonly
     */
    public Boolean getRealPerAmountReadonly() {
        return (Boolean)getAttributeInternal(REALPERAMOUNTREADONLY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RealPerAmountReadonly
     */
    public void setRealPerAmountReadonly(Boolean value) {
        setAttributeInternal(REALPERAMOUNTREADONLY, value);
    }

    /**Gets the attribute value for the calculated attribute RealPerDaysReadonly
     */
    public Boolean getRealPerDaysReadonly() {
        return (Boolean)getAttributeInternal(REALPERDAYSREADONLY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RealPerDaysReadonly
     */
    public void setRealPerDaysReadonly(Boolean value) {
        setAttributeInternal(REALPERDAYSREADONLY, value);
    }
}
