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
public class AppraisalKPIVORowImpl extends OAViewRowImpl {
    public static final int CONTRACTID = 0;
    public static final int APPRAISALID = 1;
    public static final int TMPLID = 2;
    public static final int TARGETVALUE = 3;
    public static final int MINIMUMVALUE = 4;
    public static final int RATINGMETHOD = 5;
    public static final int SHOWSEQ = 6;
    public static final int KPIID = 7;
    public static final int KPIAREA = 8;
    public static final int KPINAME = 9;
    public static final int KPIDESC = 10;
    public static final int KPICLASS = 11;
    public static final int KPIDATASOURCE = 12;
    public static final int KPIDATADIMENSION = 13;
    public static final int KPISCORINGMETHOD = 14;
    public static final int ATTRIBUTE1 = 15;
    public static final int ATTRIBUTE2 = 16;
    public static final int ATTRIBUTE3 = 17;
    public static final int ATTRIBUTE4 = 18;
    public static final int ATTRIBUTE5 = 19;
    public static final int LASTUPDATEDATE = 20;
    public static final int LASTUPDATEDBY = 21;
    public static final int LASTUPDATELOGIN = 22;
    public static final int CREATEDBY = 23;
    public static final int CREATIONDATE = 24;
    public static final int ACTUALVALUE = 25;
    public static final int SELFEVALVALUE = 26;
    public static final int SHOWSCOREFLAG = 27;
    public static final int ACTUALVALUESHOW = 28;
    public static final int SCOREVALUE = 29;
    public static final int SCOREVALUESHOW = 30;
    public static final int PARENTCONTRACTID = 31;
    public static final int WEIGHT = 32;
    public static final int UPDATEIMAGE = 33;
    public static final int DELETEIMAGE = 34;
    public static final int ISPARENT = 35;
    public static final int READONLYFLAG = 36;
    public static final int SELFREADONLYFLAG = 37;
    public static final int ACREADONLYFLAG = 38;
    public static final int HALFINPUTFLAG = 39;
    public static final int LOCKKPIFLAG = 40;
    public static final int KPIDATASOURCEDISPLAY = 41;
    public static final int ACTUALVALUELINK = 42;
    public static final int SCOREDETAIL = 43;

    /**This is the default constructor (do not remove)
     */
    public AppraisalKPIVORowImpl() {
    }

    /**Gets ContractEO entity object.
     */
    public ContractEOImpl getContractEO() {
        return (ContractEOImpl)getEntity(0);
    }

    /**Gets the attribute value for CONTRACT_ID using the alias name ContractId
     */
    public Number getContractId() {
        return (Number)getAttributeInternal(CONTRACTID);
    }

    /**Sets <code>value</code> as attribute value for CONTRACT_ID using the alias name ContractId
     */
    public void setContractId(Number value) {
        setAttributeInternal(CONTRACTID, value);
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

    /**Gets the attribute value for TARGET_VALUE using the alias name TargetValue
     */
    public Number getTargetValue() {
        return (Number)getAttributeInternal(TARGETVALUE);
    }

    /**Sets <code>value</code> as attribute value for TARGET_VALUE using the alias name TargetValue
     */
    public void setTargetValue(Number value) {
        setAttributeInternal(TARGETVALUE, value);
    }

    /**Gets the attribute value for MINIMUM_VALUE using the alias name MinimumValue
     */
    public Number getMinimumValue() {
        return (Number)getAttributeInternal(MINIMUMVALUE);
    }

    /**Sets <code>value</code> as attribute value for MINIMUM_VALUE using the alias name MinimumValue
     */
    public void setMinimumValue(Number value) {
        setAttributeInternal(MINIMUMVALUE, value);
    }

    /**Gets the attribute value for RATING_METHOD using the alias name RatingMethod
     */
    public String getRatingMethod() {
        return (String)getAttributeInternal(RATINGMETHOD);
    }

    /**Sets <code>value</code> as attribute value for RATING_METHOD using the alias name RatingMethod
     */
    public void setRatingMethod(String value) {
        setAttributeInternal(RATINGMETHOD, value);
    }

    /**Gets the attribute value for SHOW_SEQ using the alias name ShowSeq
     */
    public Number getShowSeq() {
        return (Number)getAttributeInternal(SHOWSEQ);
    }

    /**Sets <code>value</code> as attribute value for SHOW_SEQ using the alias name ShowSeq
     */
    public void setShowSeq(Number value) {
        setAttributeInternal(SHOWSEQ, value);
    }

    /**Gets the attribute value for KPI_ID using the alias name KpiId
     */
    public Number getKpiId() {
        return (Number)getAttributeInternal(KPIID);
    }

    /**Sets <code>value</code> as attribute value for KPI_ID using the alias name KpiId
     */
    public void setKpiId(Number value) {
        setAttributeInternal(KPIID, value);
    }

    /**Gets the attribute value for KPI_AREA using the alias name KpiArea
     */
    public String getKpiArea() {
        return (String)getAttributeInternal(KPIAREA);
    }

    /**Sets <code>value</code> as attribute value for KPI_AREA using the alias name KpiArea
     */
    public void setKpiArea(String value) {
        setAttributeInternal(KPIAREA, value);
    }

    /**Gets the attribute value for KPI_NAME using the alias name KpiName
     */
    public String getKpiName() {
        return (String)getAttributeInternal(KPINAME);
    }

    /**Sets <code>value</code> as attribute value for KPI_NAME using the alias name KpiName
     */
    public void setKpiName(String value) {
        setAttributeInternal(KPINAME, value);
    }

    /**Gets the attribute value for KPI_DESC using the alias name KpiDesc
     */
    public String getKpiDesc() {
        return (String)getAttributeInternal(KPIDESC);
    }

    /**Sets <code>value</code> as attribute value for KPI_DESC using the alias name KpiDesc
     */
    public void setKpiDesc(String value) {
        setAttributeInternal(KPIDESC, value);
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

    /**Gets the attribute value for KPI_DATA_SOURCE using the alias name KpiDataSource
     */
    public String getKpiDataSource() {
        return (String)getAttributeInternal(KPIDATASOURCE);
    }

    /**Sets <code>value</code> as attribute value for KPI_DATA_SOURCE using the alias name KpiDataSource
     */
    public void setKpiDataSource(String value) {
        setAttributeInternal(KPIDATASOURCE, value);
    }

    /**Gets the attribute value for KPI_DATA_DIMENSION using the alias name KpiDataDimension
     */
    public String getKpiDataDimension() {
        return (String)getAttributeInternal(KPIDATADIMENSION);
    }

    /**Sets <code>value</code> as attribute value for KPI_DATA_DIMENSION using the alias name KpiDataDimension
     */
    public void setKpiDataDimension(String value) {
        setAttributeInternal(KPIDATADIMENSION, value);
    }

    /**Gets the attribute value for KPI_SCORING_METHOD using the alias name KpiScoringMethod
     */
    public String getKpiScoringMethod() {
        return (String)getAttributeInternal(KPISCORINGMETHOD);
    }

    /**Sets <code>value</code> as attribute value for KPI_SCORING_METHOD using the alias name KpiScoringMethod
     */
    public void setKpiScoringMethod(String value) {
        setAttributeInternal(KPISCORINGMETHOD, value);
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

    /**Gets the attribute value for ACTUAL_VALUE using the alias name ActualValue
     */
    public Number getActualValue() {
        return (Number)getAttributeInternal(ACTUALVALUE);
    }

    /**Sets <code>value</code> as attribute value for ACTUAL_VALUE using the alias name ActualValue
     */
    public void setActualValue(Number value) {
        setAttributeInternal(ACTUALVALUE, value);
        //updated by dl.wang 20091120
        if (this.getActualValueShow() != null) {
            setAttributeInternal(ACTUALVALUE, getActualValueShow());
        }
    }

    /**Gets the attribute value for SELF_EVAL_VALUE using the alias name SelfEvalValue
     */
    public Number getSelfEvalValue() {
        return (Number)getAttributeInternal(SELFEVALVALUE);
    }

    /**Sets <code>value</code> as attribute value for SELF_EVAL_VALUE using the alias name SelfEvalValue
     */
    public void setSelfEvalValue(Number value) {
        setAttributeInternal(SELFEVALVALUE, value);
    }

    /**Gets the attribute value for the calculated attribute ShowScoreFlag
     */
    public String getShowScoreFlag() {
        return (String)getAttributeInternal(SHOWSCOREFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ShowScoreFlag
     */
    public void setShowScoreFlag(String value) {
        setAttributeInternal(SHOWSCOREFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute ActualValueShow
     */
    public Number getActualValueShow() {
        return (Number)getAttributeInternal(ACTUALVALUESHOW);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ActualValueShow
     */
    public void setActualValueShow(Number value) {
        setAttributeInternal(ACTUALVALUESHOW, value);
    }

    /**Gets the attribute value for SCORE_VALUE using the alias name ScoreValue
     */
    public Number getScoreValue() {
        return (Number)getAttributeInternal(SCOREVALUE);
    }

    /**Sets <code>value</code> as attribute value for SCORE_VALUE using the alias name ScoreValue
     */
    public void setScoreValue(Number value) {
        setAttributeInternal(SCOREVALUE, value);

    }

    /**Gets the attribute value for the calculated attribute ScoreValueShow
     */
    public Number getScoreValueShow() {
        return (Number)getAttributeInternal(SCOREVALUESHOW);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ScoreValueShow
     */
    public void setScoreValueShow(Number value) {
        setAttributeInternal(SCOREVALUESHOW, value);
    }

    /**Gets the attribute value for PARENT_CONTRACT_ID using the alias name ParentContractId
     */
    public Number getParentContractId() {
        return (Number)getAttributeInternal(PARENTCONTRACTID);
    }

    /**Sets <code>value</code> as attribute value for PARENT_CONTRACT_ID using the alias name ParentContractId
     */
    public void setParentContractId(Number value) {
        setAttributeInternal(PARENTCONTRACTID, value);
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

    /**Gets the attribute value for the calculated attribute DeleteImage
     */
    public String getDeleteImage() {
        return (String)getAttributeInternal(DELETEIMAGE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DeleteImage
     */
    public void setDeleteImage(String value) {
        setAttributeInternal(DELETEIMAGE, value);
    }

    /**Gets the attribute value for the calculated attribute IsParent
     */
    public String getIsParent() {
        return (String)getAttributeInternal(ISPARENT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute IsParent
     */
    public void setIsParent(String value) {
        setAttributeInternal(ISPARENT, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CONTRACTID:
            return getContractId();
        case APPRAISALID:
            return getAppraisalId();
        case TMPLID:
            return getTmplId();
        case TARGETVALUE:
            return getTargetValue();
        case MINIMUMVALUE:
            return getMinimumValue();
        case RATINGMETHOD:
            return getRatingMethod();
        case SHOWSEQ:
            return getShowSeq();
        case KPIID:
            return getKpiId();
        case KPIAREA:
            return getKpiArea();
        case KPINAME:
            return getKpiName();
        case KPIDESC:
            return getKpiDesc();
        case KPICLASS:
            return getKpiClass();
        case KPIDATASOURCE:
            return getKpiDataSource();
        case KPIDATADIMENSION:
            return getKpiDataDimension();
        case KPISCORINGMETHOD:
            return getKpiScoringMethod();
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
        case ACTUALVALUE:
            return getActualValue();
        case SELFEVALVALUE:
            return getSelfEvalValue();
        case SHOWSCOREFLAG:
            return getShowScoreFlag();
        case ACTUALVALUESHOW:
            return getActualValueShow();
        case SCOREVALUE:
            return getScoreValue();
        case SCOREVALUESHOW:
            return getScoreValueShow();
        case PARENTCONTRACTID:
            return getParentContractId();
        case WEIGHT:
            return getWeight();
        case UPDATEIMAGE:
            return getUpdateImage();
        case DELETEIMAGE:
            return getDeleteImage();
        case ISPARENT:
            return getIsParent();
        case READONLYFLAG:
            return getReadOnlyFlag();
        case SELFREADONLYFLAG:
            return getSelfReadOnlyFlag();
        case ACREADONLYFLAG:
            return getAcReadOnlyFlag();
        case HALFINPUTFLAG:
            return getHalfInputFlag();
        case LOCKKPIFLAG:
            return getLockKpiFlag();
        case KPIDATASOURCEDISPLAY:
            return getKpiDataSourceDisplay();
        case ACTUALVALUELINK:
            return getActualValueLink();
        case SCOREDETAIL:
            return getScoreDetail();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case CONTRACTID:
            setContractId((Number)value);
            return;
        case APPRAISALID:
            setAppraisalId((Number)value);
            return;
        case TMPLID:
            setTmplId((Number)value);
            return;
        case TARGETVALUE:
            setTargetValue((Number)value);
            return;
        case MINIMUMVALUE:
            setMinimumValue((Number)value);
            return;
        case RATINGMETHOD:
            setRatingMethod((String)value);
            return;
        case SHOWSEQ:
            setShowSeq((Number)value);
            return;
        case KPIID:
            setKpiId((Number)value);
            return;
        case KPIAREA:
            setKpiArea((String)value);
            return;
        case KPINAME:
            setKpiName((String)value);
            return;
        case KPIDESC:
            setKpiDesc((String)value);
            return;
        case KPICLASS:
            setKpiClass((String)value);
            return;
        case KPIDATASOURCE:
            setKpiDataSource((String)value);
            return;
        case KPIDATADIMENSION:
            setKpiDataDimension((String)value);
            return;
        case KPISCORINGMETHOD:
            setKpiScoringMethod((String)value);
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
        case ACTUALVALUE:
            setActualValue((Number)value);
            return;
        case SELFEVALVALUE:
            setSelfEvalValue((Number)value);
            return;
        case SHOWSCOREFLAG:
            setShowScoreFlag((String)value);
            return;
        case ACTUALVALUESHOW:
            setActualValueShow((Number)value);
            return;
        case SCOREVALUE:
            setScoreValue((Number)value);
            return;
        case SCOREVALUESHOW:
            setScoreValueShow((Number)value);
            return;
        case PARENTCONTRACTID:
            setParentContractId((Number)value);
            return;
        case WEIGHT:
            setWeight((String)value);
            return;
        case UPDATEIMAGE:
            setUpdateImage((String)value);
            return;
        case DELETEIMAGE:
            setDeleteImage((String)value);
            return;
        case ISPARENT:
            setIsParent((String)value);
            return;
        case SELFREADONLYFLAG:
            setSelfReadOnlyFlag((Boolean)value);
            return;
        case ACREADONLYFLAG:
            setAcReadOnlyFlag((Boolean)value);
            return;
        case HALFINPUTFLAG:
            setHalfInputFlag((Boolean)value);
            return;
        case LOCKKPIFLAG:
            setLockKpiFlag((Boolean)value);
            return;
        case KPIDATASOURCEDISPLAY:
            setKpiDataSourceDisplay((String)value);
            return;
        case ACTUALVALUELINK:
            setActualValueLink((String)value);
            return;
        case SCOREDETAIL:
            setScoreDetail((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
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

    /**Gets the attribute value for the calculated attribute SelfReadOnlyFlag
     */
    public Boolean getSelfReadOnlyFlag() {
        return (Boolean)getAttributeInternal(SELFREADONLYFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SelfReadOnlyFlag
     */
    public void setSelfReadOnlyFlag(Boolean value) {
        setAttributeInternal(SELFREADONLYFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute AcReadOnlyFlag
     */
    public Boolean getAcReadOnlyFlag() {
        return (Boolean)getAttributeInternal(ACREADONLYFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AcReadOnlyFlag
     */
    public void setAcReadOnlyFlag(Boolean value) {
        setAttributeInternal(ACREADONLYFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute HalfInputFlag
     */
    public Boolean getHalfInputFlag() {
        return (Boolean)getAttributeInternal(HALFINPUTFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HalfInputFlag
     */
    public void setHalfInputFlag(Boolean value) {
        setAttributeInternal(HALFINPUTFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute LockKpiFlag
     */
    public Boolean getLockKpiFlag() {
        return (Boolean)getAttributeInternal(LOCKKPIFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LockKpiFlag
     */
    public void setLockKpiFlag(Boolean value) {
        setAttributeInternal(LOCKKPIFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute KpiDataSourceDisplay
     */
    public String getKpiDataSourceDisplay() {
        return (String)getAttributeInternal(KPIDATASOURCEDISPLAY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute KpiDataSourceDisplay
     */
    public void setKpiDataSourceDisplay(String value) {
        setAttributeInternal(KPIDATASOURCEDISPLAY, value);
    }

    /**Gets the attribute value for the calculated attribute ActualValueLink
     */
    public String getActualValueLink() {
        return (String)getAttributeInternal(ACTUALVALUELINK);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ActualValueLink
     */
    public void setActualValueLink(String value) {
        setAttributeInternal(ACTUALVALUELINK, value);
    }


    /**Gets the attribute value for the calculated attribute ScoreDetail
     */
    public String getScoreDetail() {
        return (String)getAttributeInternal(SCOREDETAIL);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ScoreDetail
     */
    public void setScoreDetail(String value) {
        setAttributeInternal(SCOREDETAIL, value);
    }
}
