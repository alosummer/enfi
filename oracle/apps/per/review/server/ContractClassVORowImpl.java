package cux.oracle.apps.per.review.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContractClassVORowImpl extends OAViewRowImpl {
    public static final int CLASSID = 0;
    public static final int APPRAISALID = 1;
    public static final int TMPLID = 2;
    public static final int KPICLASS = 3;
    public static final int KPICLASSMEANING = 4;
    public static final int KPIDESC = 5;
    public static final int KPICLASSTAG = 6;
    public static final int WEIGHT = 7;
    public static final int VALIDVALUE = 8;
    public static final int VALIDSCOREVALUE = 9;
    public static final int KPIVALUE = 10;
    public static final int ATTRIBUTE1 = 11;
    public static final int ATTRIBUTE2 = 12;
    public static final int ATTRIBUTE3 = 13;
    public static final int ATTRIBUTE4 = 14;
    public static final int ATTRIBUTE5 = 15;
    public static final int ATTRIBUTE6 = 16;
    public static final int ATTRIBUTE7 = 17;
    public static final int ATTRIBUTE8 = 18;
    public static final int ATTRIBUTE9 = 19;
    public static final int ATTRIBUTE10 = 20;
    public static final int LASTUPDATEDATE = 21;
    public static final int LASTUPDATEDBY = 22;
    public static final int LASTUPDATELOGIN = 23;
    public static final int CREATEDBY = 24;
    public static final int CREATIONDATE = 25;
    public static final int UPDATEIMAGE = 26;
    public static final int SHOWFLAG = 27;
    public static final int READONLYFLAG = 28;
    public static final int CONTRACTCONTENTVO = 29;

    /**This is the default constructor (do not remove)
     */
    public ContractClassVORowImpl() {
    }

    /**Gets ContractClassEO entity object.
     */
    public ContractClassEOImpl getContractClassEO() {
        return (ContractClassEOImpl)getEntity(0);
    }

    /**Gets the attribute value for CLASS_ID using the alias name ClassId
     */
    public Number getClassId() {
        return (Number)getAttributeInternal(CLASSID);
    }

    /**Sets <code>value</code> as attribute value for CLASS_ID using the alias name ClassId
     */
    public void setClassId(Number value) {
        setAttributeInternal(CLASSID, value);
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

    /**Gets the attribute value for TMPL_ID using the alias name TmplId
     */
    public Number getTmplId() {
        return (Number)getAttributeInternal(TMPLID);
    }

    /**Sets <code>value</code> as attribute value for TMPL_ID using the alias name TmplId
     */
    public void setTmplId(Number value) {
        setAttributeInternal(TMPLID, value);
    }

    /**Gets the attribute value for KPI_CLASS using the alias name KpiClass
     */
    public String getKpiClass() {
        return (String)getAttributeInternal(KPICLASS);
    }

    /**Sets <code>value</code> as attribute value for KPI_CLASS using the alias name KpiClass
     */
    public void setKpiClass(String value) {
        setAttributeInternal(KPICLASS, value);
    }

    /**Gets the attribute value for KPI_CLASS_MEANING using the alias name KpiClassMeaning
     */
    public String getKpiClassMeaning() {
        return (String)getAttributeInternal(KPICLASSMEANING);
    }

    /**Sets <code>value</code> as attribute value for KPI_CLASS_MEANING using the alias name KpiClassMeaning
     */
    public void setKpiClassMeaning(String value) {
        setAttributeInternal(KPICLASSMEANING, value);
    }

    /**Gets the attribute value for KPI_CLASS_TAG using the alias name KpiClassTag
     */
    public String getKpiClassTag() {
        return (String)getAttributeInternal(KPICLASSTAG);
    }

    /**Sets <code>value</code> as attribute value for KPI_CLASS_TAG using the alias name KpiClassTag
     */
    public void setKpiClassTag(String value) {
        setAttributeInternal(KPICLASSTAG, value);
    }

    /**Gets the attribute value for WEIGHT using the alias name Weight
     */
    public String getWeight() {
        return (String)getAttributeInternal(WEIGHT);
    }

    /**Sets <code>value</code> as attribute value for WEIGHT using the alias name Weight
     */
    public void setWeight(String value) {
        setAttributeInternal(WEIGHT, value);
    }

    /**Gets the attribute value for VALID_VALUE using the alias name ValidValue
     */
    public String getValidValue() {
        return (String)getAttributeInternal(VALIDVALUE);
    }

    /**Sets <code>value</code> as attribute value for VALID_VALUE using the alias name ValidValue
     */
    public void setValidValue(String value) {
        setAttributeInternal(VALIDVALUE, value);
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

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CLASSID:
            return getClassId();
        case APPRAISALID:
            return getAppraisalId();
        case TMPLID:
            return getTmplId();
        case KPICLASS:
            return getKpiClass();
        case KPICLASSMEANING:
            return getKpiClassMeaning();
        case KPIDESC:
            return getKpiDesc();
        case KPICLASSTAG:
            return getKpiClassTag();
        case WEIGHT:
            return getWeight();
        case VALIDVALUE:
            return getValidValue();
        case VALIDSCOREVALUE:
            return getValidScoreValue();
        case KPIVALUE:
            return getKpiValue();
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
        case UPDATEIMAGE:
            return getUpdateImage();
        case SHOWFLAG:
            return getShowFlag();
        case READONLYFLAG:
            return getReadOnlyFlag();
        case CONTRACTCONTENTVO:
            return getContractContentVO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CLASSID:
            setClassId((Number)value);
            return;
        case APPRAISALID:
            setAppraisalId((Number)value);
            return;
        case TMPLID:
            setTmplId((Number)value);
            return;
        case KPICLASS:
            setKpiClass((String)value);
            return;
        case KPICLASSMEANING:
            setKpiClassMeaning((String)value);
            return;
        case KPIDESC:
            setKpiDesc((String)value);
            return;
        case KPICLASSTAG:
            setKpiClassTag((String)value);
            return;
        case WEIGHT:
            setWeight((String)value);
            return;
        case VALIDVALUE:
            setValidValue((String)value);
            return;
        case VALIDSCOREVALUE:
            setValidScoreValue((Number)value);
            return;
        case KPIVALUE:
            setKpiValue((String)value);
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
        case UPDATEIMAGE:
            setUpdateImage((String)value);
            return;
        case SHOWFLAG:
            setShowFlag((String)value);
            return;
        case READONLYFLAG:
            setReadOnlyFlag((Boolean)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }


    /**Gets the attribute value for the calculated attribute ValidScoreValue
     */
    public Number getValidScoreValue() {
        return (Number)getAttributeInternal(VALIDSCOREVALUE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ValidScoreValue
     */
    public void setValidScoreValue(Number value) {
        setAttributeInternal(VALIDSCOREVALUE, value);
    }

    /**Gets the attribute value for the calculated attribute KpiValue
     */
    public String getKpiValue() {
        return (String)getAttributeInternal(KPIVALUE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute KpiValue
     */
    public void setKpiValue(String value) {
        setAttributeInternal(KPIVALUE, value);
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

    /**Gets the attribute value for the calculated attribute UpdateImage
     */
    public String getUpdateImage() {
        return (String)getAttributeInternal(UPDATEIMAGE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute UpdateImage
     */
    public void setUpdateImage(String value) {
        setAttributeInternal(UPDATEIMAGE, value);
    }

    /**Gets the attribute value for the calculated attribute ShowFlag
     */
    public String getShowFlag() {
        return (String)getAttributeInternal(SHOWFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ShowFlag
     */
    public void setShowFlag(String value) {
        setAttributeInternal(SHOWFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute KpiDesc
     */
    public String getKpiDesc() {
        return (String)getAttributeInternal(KPIDESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute KpiDesc
     */
    public void setKpiDesc(String value) {
        setAttributeInternal(KPIDESC, value);
    }

    /**Gets the associated <code>RowIterator</code> using master-detail link ContractContentVO
     */
    public RowIterator getContractContentVO() {
        return (RowIterator)getAttributeInternal(CONTRACTCONTENTVO);
    }

    /**Gets the attribute value for the calculated attribute ReadOnlyFlag
     */
    public Boolean getReadOnlyFlag() {
        return (Boolean)getAttributeInternal(READONLYFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ReadOnlyFlag
     */
    public void setReadOnlyFlag(Boolean value) {
        setAttributeInternal(READONLYFLAG, value);
    }
}