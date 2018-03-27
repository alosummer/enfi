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
public class CuxApExpRelatedOaInfoVORowImpl extends OAViewRowImpl {
    public static final int RELATIONSHIPID = 0;
    public static final int REPORTHEADERID = 1;
    public static final int OATRAVELPROCESSID = 2;
    public static final int OAOUTSEATRAVELPROCESSID = 3;
    public static final int CREATIONDATE = 4;
    public static final int CREATEDBY = 5;
    public static final int LASTUPDATEDATE = 6;
    public static final int LASTUPDATEDBY = 7;
    public static final int LASTUPDATELOGIN = 8;
    public static final int ATTRIBUTECATEGORY = 9;
    public static final int ATTRIBUTE1 = 10;
    public static final int ATTRIBUTE2 = 11;
    public static final int ATTRIBUTE3 = 12;
    public static final int ATTRIBUTE4 = 13;
    public static final int ATTRIBUTE5 = 14;
    public static final int ATTRIBUTE6 = 15;
    public static final int ATTRIBUTE7 = 16;
    public static final int ATTRIBUTE8 = 17;
    public static final int ATTRIBUTE9 = 18;
    public static final int ATTRIBUTE10 = 19;
    public static final int ATTRIBUTE11 = 20;
    public static final int ATTRIBUTE12 = 21;
    public static final int ATTRIBUTE13 = 22;
    public static final int ATTRIBUTE14 = 23;
    public static final int ATTRIBUTE15 = 24;
    public static final int OATRAVELHEADERNAME = 25;
    public static final int ABOURDSTARTDATE = 26;
    public static final int ABOURDENDDATE = 27;

    /**This is the default constructor (do not remove)
     */
    public CuxApExpRelatedOaInfoVORowImpl() {
    }

    /**Gets CuxApExpRelatedOaInfoEO entity object.
     */
    public CuxApExpRelatedOaInfoEOImpl getCuxApExpRelatedOaInfoEO() {
        return (CuxApExpRelatedOaInfoEOImpl)getEntity(0);
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

    /**Gets the attribute value for REPORT_HEADER_ID using the alias name ReportHeaderId
     */
    public Number getReportHeaderId() {
        return (Number)getAttributeInternal(REPORTHEADERID);
    }

    /**Sets <code>value</code> as attribute value for REPORT_HEADER_ID using the alias name ReportHeaderId
     */
    public void setReportHeaderId(Number value) {
        setAttributeInternal(REPORTHEADERID, value);
    }

    /**Gets the attribute value for OA_TRAVEL_PROCESS_ID using the alias name OaTravelProcessId
     */
    public Number getOaTravelProcessId() {
        return (Number)getAttributeInternal(OATRAVELPROCESSID);
    }

    /**Sets <code>value</code> as attribute value for OA_TRAVEL_PROCESS_ID using the alias name OaTravelProcessId
     */
    public void setOaTravelProcessId(Number value) {
        setAttributeInternal(OATRAVELPROCESSID, value);
    }

    /**Gets the attribute value for OA_OUTSEA_TRAVEL_PROCESS_ID using the alias name OaOutseaTravelProcessId
     */
    public Number getOaOutseaTravelProcessId() {
        return (Number)getAttributeInternal(OAOUTSEATRAVELPROCESSID);
    }

    /**Sets <code>value</code> as attribute value for OA_OUTSEA_TRAVEL_PROCESS_ID using the alias name OaOutseaTravelProcessId
     */
    public void setOaOutseaTravelProcessId(Number value) {
        setAttributeInternal(OAOUTSEATRAVELPROCESSID, value);
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

    /**Gets the attribute value for the calculated attribute OaTravelHeaderName
     */
    public String getOaTravelHeaderName() {
        return (String)getAttributeInternal(OATRAVELHEADERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OaTravelHeaderName
     */
    public void setOaTravelHeaderName(String value) {
        setAttributeInternal(OATRAVELHEADERNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case RELATIONSHIPID:
            return getRelationshipId();
        case REPORTHEADERID:
            return getReportHeaderId();
        case OATRAVELPROCESSID:
            return getOaTravelProcessId();
        case OAOUTSEATRAVELPROCESSID:
            return getOaOutseaTravelProcessId();
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
        case OATRAVELHEADERNAME:
            return getOaTravelHeaderName();
        case ABOURDSTARTDATE:
            return getAbourdStartDate();
        case ABOURDENDDATE:
            return getAbourdEndDate();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case RELATIONSHIPID:
            setRelationshipId((Number)value);
            return;
        case REPORTHEADERID:
            setReportHeaderId((Number)value);
            return;
        case OATRAVELPROCESSID:
            setOaTravelProcessId((Number)value);
            return;
        case OAOUTSEATRAVELPROCESSID:
            setOaOutseaTravelProcessId((Number)value);
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
        case OATRAVELHEADERNAME:
            setOaTravelHeaderName((String)value);
            return;
        case ABOURDSTARTDATE:
            setAbourdStartDate((Date)value);
            return;
        case ABOURDENDDATE:
            setAbourdEndDate((Date)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute AbourdStartDate
     */
    public Date getAbourdStartDate() {
        return (Date)getAttributeInternal(ABOURDSTARTDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AbourdStartDate
     */
    public void setAbourdStartDate(Date value) {
        setAttributeInternal(ABOURDSTARTDATE, value);
    }

    /**Gets the attribute value for the calculated attribute AbourdEndDate
     */
    public Date getAbourdEndDate() {
        return (Date)getAttributeInternal(ABOURDENDDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AbourdEndDate
     */
    public void setAbourdEndDate(Date value) {
        setAttributeInternal(ABOURDENDDATE, value);
    }
}
