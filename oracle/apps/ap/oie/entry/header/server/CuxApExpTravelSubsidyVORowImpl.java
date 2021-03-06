package cux.oracle.apps.ap.oie.entry.header.server;


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
public class CuxApExpTravelSubsidyVORowImpl extends OAViewRowImpl {
    public static final int SUBSIDYLINEID = 0;
    public static final int RELATIONSHIPID = 1;
    public static final int EMPID = 2;
    public static final int FEETYPE = 3;
    public static final int STARTDATE = 4;
    public static final int ENDDATE = 5;
    public static final int ISSEPCIALSITE = 6;
    public static final int CREATIONDATE = 7;
    public static final int CREATEDBY = 8;
    public static final int LASTUPDATEDATE = 9;
    public static final int LASTUPDATEDBY = 10;
    public static final int LASTUPDATELOGIN = 11;
    public static final int ATTRIBUTECATEGORY = 12;
    public static final int ATTRIBUTE1 = 13;
    public static final int ATTRIBUTE2 = 14;
    public static final int ATTRIBUTE3 = 15;
    public static final int ATTRIBUTE4 = 16;
    public static final int ATTRIBUTE5 = 17;
    public static final int ATTRIBUTE6 = 18;
    public static final int ATTRIBUTE7 = 19;
    public static final int ATTRIBUTE8 = 20;
    public static final int ATTRIBUTE9 = 21;
    public static final int ATTRIBUTE10 = 22;
    public static final int ATTRIBUTE11 = 23;
    public static final int ATTRIBUTE12 = 24;
    public static final int ATTRIBUTE13 = 25;
    public static final int ATTRIBUTE14 = 26;
    public static final int ATTRIBUTE15 = 27;
    public static final int LINENUMBER = 28;
    public static final int EMPNAME = 29;
    public static final int EMPLOYEENUMBER = 30;
    public static final int TRAVELDATESNUMBER = 31;
    public static final int FEEPERDAY = 32;
    public static final int TRAVELSUBSIDYTOTAL = 33;

    /**This is the default constructor (do not remove)
     */
    public CuxApExpTravelSubsidyVORowImpl() {
    }

    /**Gets CuxApExpTravelSubsidyEO entity object.
     */
    public CuxApExpTravelSubsidyEOImpl getCuxApExpTravelSubsidyEO() {
        return (CuxApExpTravelSubsidyEOImpl)getEntity(0);
    }

    /**Gets the attribute value for SUBSIDY_LINE_ID using the alias name SubsidyLineId
     */
    public Number getSubsidyLineId() {
        return (Number)getAttributeInternal(SUBSIDYLINEID);
    }

    /**Sets <code>value</code> as attribute value for SUBSIDY_LINE_ID using the alias name SubsidyLineId
     */
    public void setSubsidyLineId(Number value) {
        setAttributeInternal(SUBSIDYLINEID, value);
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

    /**Gets the attribute value for EMP_ID using the alias name EmpId
     */
    public Number getEmpId() {
        return (Number)getAttributeInternal(EMPID);
    }

    /**Sets <code>value</code> as attribute value for EMP_ID using the alias name EmpId
     */
    public void setEmpId(Number value) {
        setAttributeInternal(EMPID, value);
    }

    /**Gets the attribute value for FEE_TYPE using the alias name FeeType
     */
    public String getFeeType() {
        return (String)getAttributeInternal(FEETYPE);
    }

    /**Sets <code>value</code> as attribute value for FEE_TYPE using the alias name FeeType
     */
    public void setFeeType(String value) {
        setAttributeInternal(FEETYPE, value);
    }

    /**Gets the attribute value for START_DATE using the alias name StartDate
     */
    public Date getStartDate() {
        return (Date)getAttributeInternal(STARTDATE);
    }

    /**Sets <code>value</code> as attribute value for START_DATE using the alias name StartDate
     */
    public void setStartDate(Date value) {
        if (value != null && this.getEndDate() != null) {
            long diff = 
                this.getEndDate().getValue().getTime() - value.getValue().getTime();
            int days = (int)(diff / (1000 * 60 * 60 * 24)) + 1;

            this.setTravelDatesNumber(new Number(days));
        } else {
            this.setTravelDatesNumber(new Number(0));
        }
        setAttributeInternal(STARTDATE, value);

    }

    /**Gets the attribute value for END_DATE using the alias name EndDate
     */
    public Date getEndDate() {
        return (Date)getAttributeInternal(ENDDATE);
    }

    /**Sets <code>value</code> as attribute value for END_DATE using the alias name EndDate
     */
    public void setEndDate(Date value) {
        if (value != null && this.getStartDate() != null) {
            long diff = 
                value.getValue().getTime() - this.getStartDate().getValue().getTime();
            int days = (int)(diff / (1000 * 60 * 60 * 24)) + 1;
            //            if (value.compareTo(this.getStartDate()) < 0) {
            //                throw new OAException("出差补贴 行：" + "员工姓名:" + this.getEmpName() + 
            //                                      "  至(日期)：" + value + " 不能早于从(日期):" + 
            //                                      this.getStartDate());
            //            }
            this.setTravelDatesNumber(new Number(days));
        } else {
            this.setTravelDatesNumber(new Number(0));
        }
        setAttributeInternal(ENDDATE, value);


    }

    /**Gets the attribute value for IS_SEPCIAL_SITE using the alias name IsSepcialSite
     */
    public String getIsSepcialSite() {
        return (String)getAttributeInternal(ISSEPCIALSITE);
    }

    /**Sets <code>value</code> as attribute value for IS_SEPCIAL_SITE using the alias name IsSepcialSite
     */
    public void setIsSepcialSite(String value) {
        setAttributeInternal(ISSEPCIALSITE, value);
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

    /**Gets the attribute value for LINE_NUMBER using the alias name LineNumber
     */
    public Number getLineNumber() {
        return (Number)getAttributeInternal(LINENUMBER);
    }

    /**Sets <code>value</code> as attribute value for LINE_NUMBER using the alias name LineNumber
     */
    public void setLineNumber(Number value) {
        setAttributeInternal(LINENUMBER, value);
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

    /**Gets the attribute value for the calculated attribute TravelDatesNumber
     */
    public Number getTravelDatesNumber() {
        return (Number)getAttributeInternal(TRAVELDATESNUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TravelDatesNumber
     */
    public void setTravelDatesNumber(Number value) {
        setAttributeInternal(TRAVELDATESNUMBER, value);
    }

    /**Gets the attribute value for the calculated attribute FeePerDay
     */
    public Number getFeePerDay() {
        return (Number)getAttributeInternal(FEEPERDAY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FeePerDay
     */
    public void setFeePerDay(Number value) {
        setAttributeInternal(FEEPERDAY, value);
    }

    /**Gets the attribute value for the calculated attribute TravelSubsidyTotal
     */
    public Number getTravelSubsidyTotal() {
        return (Number)getAttributeInternal(TRAVELSUBSIDYTOTAL);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TravelSubsidyTotal
     */
    public void setTravelSubsidyTotal(Number value) {
        setAttributeInternal(TRAVELSUBSIDYTOTAL, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case SUBSIDYLINEID:
            return getSubsidyLineId();
        case RELATIONSHIPID:
            return getRelationshipId();
        case EMPID:
            return getEmpId();
        case FEETYPE:
            return getFeeType();
        case STARTDATE:
            return getStartDate();
        case ENDDATE:
            return getEndDate();
        case ISSEPCIALSITE:
            return getIsSepcialSite();
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
        case LINENUMBER:
            return getLineNumber();
        case EMPNAME:
            return getEmpName();
        case EMPLOYEENUMBER:
            return getEmployeeNumber();
        case TRAVELDATESNUMBER:
            return getTravelDatesNumber();
        case FEEPERDAY:
            return getFeePerDay();
        case TRAVELSUBSIDYTOTAL:
            return getTravelSubsidyTotal();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case SUBSIDYLINEID:
            setSubsidyLineId((Number)value);
            return;
        case RELATIONSHIPID:
            setRelationshipId((Number)value);
            return;
        case EMPID:
            setEmpId((Number)value);
            return;
        case FEETYPE:
            setFeeType((String)value);
            return;
        case STARTDATE:
            setStartDate((Date)value);
            return;
        case ENDDATE:
            setEndDate((Date)value);
            return;
        case ISSEPCIALSITE:
            setIsSepcialSite((String)value);
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
        case LINENUMBER:
            setLineNumber((Number)value);
            return;
        case EMPNAME:
            setEmpName((String)value);
            return;
        case EMPLOYEENUMBER:
            setEmployeeNumber((String)value);
            return;
        case TRAVELDATESNUMBER:
            setTravelDatesNumber((Number)value);
            return;
        case FEEPERDAY:
            setFeePerDay((Number)value);
            return;
        case TRAVELSUBSIDYTOTAL:
            setTravelSubsidyTotal((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
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

    public static void main(String[] args) {
        Date currentDate = (Date)Date.getCurrentDate();


        System.out.println(currentDate.getValue().getTime());

    }
}
