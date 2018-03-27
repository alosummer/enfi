package cux.oracle.apps.per.review.server;

import java.text.DecimalFormat;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAAttrValException;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContractEOImpl extends OAEntityImpl {
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
    public static final int SCOREVALUE = 26;
    public static final int PARENTCONTRACTID = 27;
    public static final int WEIGHT = 28;
    public static final int SELFEVALVALUE = 29;
    private static ContractEODefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public ContractEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (ContractEODefImpl)EntityDefImpl.findDefObject("cux.oracle.apps.per.review.server.ContractEO");
        }
        return mDefinitionObject;
    }

    /**Add attribute defaulting logic in this method.
     */
    public void create(AttributeList attributeList) {
        super.create(attributeList);
    }

    /**Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }

    /**Add Entity validation code in this method.
     */
    protected void validateEntity() {
        super.validateEntity();
        System.out.println("mini value : " + getMinimumValue() + 
                           " Target Value " + getTargetValue() + 
                           "Scoring method : " + this.getKpiScoringMethod());
        if ((getMinimumValue() != null && getTargetValue() != null && 
             getKpiScoringMethod() != null && 
             getMinimumValue().compareTo(getTargetValue()) >= 0 && 
             this.getKpiScoringMethod().equals("ASC_1")) || 
            (getMinimumValue() != null && getTargetValue() != null && 
             getKpiScoringMethod() != null && 
             getTargetValue().compareTo(getMinimumValue()) >= 0 && 
             this.getKpiScoringMethod().equals("DESC_1"))) {
            throw // EO name
                // EO PK
                // Attribute Name
                // Attribute value
                // Message product short name
                new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                       getEntityDef().getFullName(), 
                                       getPrimaryKey(), "ContractId", 
                                       getContractId(), "CUX", 
                                       "CUX_DESC_ASC_SET_WAR"); // Message name

        }
        //���������趨��KPI�������Ƿ񳬳�kpi�ϵķ�Χ
        if ((getMinimumValue() != null && getTargetValue() != null && 
             getKpiScoringMethod() != null && 
             getMinimumValue().compareTo(getTargetValue()) >= 0 && 
             this.getKpiScoringMethod().equals("DESC_1")) || 
            (getMinimumValue() != null && getTargetValue() != null && 
             getKpiScoringMethod() != null && 
             getTargetValue().compareTo(getMinimumValue()) >= 0 && 
             this.getKpiScoringMethod().equals("ASC_1"))) {
            OADBTransaction transaction = getOADBTransaction();
            OAApplicationModule vam;
            // Look to see if the VAM has already been created in this transaction. If not,
            // create it.
            vam = 
(OAApplicationModule)transaction.findApplicationModule("KeyPIAM");
            if (vam == null) {
                vam = 
(OAApplicationModule)transaction.createApplicationModule("KeyPIAM", 
                                                         "cux.oracle.apps.per.review.server.KeyPIAM");
            }
            KeyPIVOImpl valNameVo = 
                (KeyPIVOImpl)vam.findViewObject("KeyPIVO1");
            valNameVo.initQueryById(this.getKpiId());

            if (valNameVo.hasNext()) {
                KeyPIVORowImpl row = (KeyPIVORowImpl)valNameVo.next();
                Number Attribute1 = null;
                Number Attribute2 = null;
                String scope = null;
                try {
                    if (row.getAttribute1() != null)

                        Attribute1 = new Number(row.getAttribute1());
                    if (row.getAttribute2() != null)
                        Attribute2 = new Number(row.getAttribute2());
                } catch (Exception e) {
                    System.out.println(e.toString());
                    return;
                }

                if


                    ((Attribute1 != null && Attribute2 != null && 
                      this.getKpiScoringMethod().equals("DESC_1") && 
                      (Attribute1.compareTo(getTargetValue()) > 0 || 
                       Attribute2.compareTo(getMinimumValue()) < 0)) || 
                     (Attribute1 != null && Attribute2 != null && 
                      this.getKpiScoringMethod().equals("ASC_1") && 
                      (Attribute1.compareTo(getTargetValue()) < 0 || 
                       Attribute2.compareTo(getMinimumValue()) > 0))) {
                    System.out.println("Attribute1 " + Attribute1 + 
                                       "; Attribute2 " + Attribute2);
                    scope = 
                            Attribute1.toString() + "," + Attribute2.toString();
                    MessageToken[] tokens = 
                    { new MessageToken("KPI_NAME", this.getKpiName()), 
                      new MessageToken("SCOPE", scope) };

                    throw // EO name
                        // EO PK
                        // Attribute Name
                        // Attribute value
                        // Message application short name
                        new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                               getEntityDef().getFullName(), 
                                               getPrimaryKey(), "ContractId", 
                                               getContractId(), "CUX", 
                                               "CUX_EPM_TARGET_MINI_SCOPE", 
                                               tokens); // Message name
                }
            }


        }

    }

    /**Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**Custom DML update/insert/delete logic here.
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }

    /**Gets the attribute value for ContractId, using the alias name ContractId
     */
    public Number getContractId() {
        return (Number)getAttributeInternal(CONTRACTID);
    }

    /**Sets <code>value</code> as the attribute value for ContractId
     */
    public void setContractId(Number value) {
        setAttributeInternal(CONTRACTID, value);
    }

    /**Gets the attribute value for AppraisalId, using the alias name AppraisalId
     */
    public Number getAppraisalId() {
        return (Number)getAttributeInternal(APPRAISALID);
    }

    /**Sets <code>value</code> as the attribute value for AppraisalId
     */
    public void setAppraisalId(Number value) {
        setAttributeInternal(APPRAISALID, value);
    }

    /**Gets the attribute value for TmplId, using the alias name TmplId
     */
    public Number getTmplId() {
        return (Number)getAttributeInternal(TMPLID);
    }

    /**Sets <code>value</code> as the attribute value for TmplId
     */
    public void setTmplId(Number value) {
        setAttributeInternal(TMPLID, value);
    }

    /**Gets the attribute value for Weight, using the alias name Weight
     */
    public String getWeight() {
        return (String)getAttributeInternal(WEIGHT);
    }

    /**Sets <code>value</code> as the attribute value for Weight
     */
    public void setWeight(String value) {
        //Validate the value of weight
        if (value == null)
            throw // EO name
                // EO PK
                // Attribute Name
                // Attribute value
                // Message product short name
                new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                       getEntityDef().getFullName(), 
                                       getPrimaryKey(), "Weight", value, "CUX", 
                                       "CUX_TMPL_NULL_WEIGHT_ERROR");

        try {
            //int temp = Integer.parseInt(value); remark by fl
            // add by fl 20091010
            double temp = Double.parseDouble(value);
            DecimalFormat format = new DecimalFormat("#0.00");
            temp = Double.valueOf(format.format(temp));
            // end fl
            if (temp > 100.00 || temp < 0.00)
                throw // EO name
                    // EO PK
                    // Attribute Name
                    // Attribute value
                    // Message product short name
                    new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                           getEntityDef().getFullName(), 
                                           getPrimaryKey(), "Weight", value, 
                                           "CUX", 
                                           "CUX_KPI_CLASS_VALUE_VALID"); // Message name

        } catch (NumberFormatException e) {
            throw // EO name
                // EO PK
                // Attribute Name
                // Attribute value
                // Message product short name
                new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                       getEntityDef().getFullName(), 
                                       getPrimaryKey(), "Weight", value, "CUX", 
                                       "CUX_KPI_CLASS_VALUE_VALID"); // Message name
        }
        setAttributeInternal(WEIGHT, value);
    }

    /**Gets the attribute value for TargetValue, using the alias name TargetValue
     */
    public Number getTargetValue() {
        return (Number)getAttributeInternal(TARGETVALUE);
    }

    /**Sets <code>value</code> as the attribute value for TargetValue
     */
    public void setTargetValue(Number value) {
        setAttributeInternal(TARGETVALUE, value);
    }

    /**Gets the attribute value for MinimumValue, using the alias name MinimumValue
     */
    public Number getMinimumValue() {
        return (Number)getAttributeInternal(MINIMUMVALUE);
    }

    /**Sets <code>value</code> as the attribute value for MinimumValue
     */
    public void setMinimumValue(Number value) {
        setAttributeInternal(MINIMUMVALUE, value);
    }

    /**Gets the attribute value for RatingMethod, using the alias name RatingMethod
     */
    public String getRatingMethod() {
        return (String)getAttributeInternal(RATINGMETHOD);
    }

    /**Sets <code>value</code> as the attribute value for RatingMethod
     */
    public void setRatingMethod(String value) {
        setAttributeInternal(RATINGMETHOD, value);
    }

    /**Gets the attribute value for ShowSeq, using the alias name ShowSeq
     */
    public Number getShowSeq() {
        return (Number)getAttributeInternal(SHOWSEQ);
    }

    /**Sets <code>value</code> as the attribute value for ShowSeq
     */
    public void setShowSeq(Number value) {
        setAttributeInternal(SHOWSEQ, value);
    }

    /**Gets the attribute value for KpiId, using the alias name KpiId
     */
    public Number getKpiId() {
        return (Number)getAttributeInternal(KPIID);
    }

    /**Sets <code>value</code> as the attribute value for KpiId
     */
    public void setKpiId(Number value) {
        setAttributeInternal(KPIID, value);
    }

    /**Gets the attribute value for KpiArea, using the alias name KpiArea
     */
    public String getKpiArea() {
        return (String)getAttributeInternal(KPIAREA);
    }

    /**Sets <code>value</code> as the attribute value for KpiArea
     */
    public void setKpiArea(String value) {
        setAttributeInternal(KPIAREA, value);
    }

    /**Gets the attribute value for KpiName, using the alias name KpiName
     */
    public String getKpiName() {
        return (String)getAttributeInternal(KPINAME);
    }

    /**Sets <code>value</code> as the attribute value for KpiName
     */
    public void setKpiName(String value) {
        setAttributeInternal(KPINAME, value);
    }

    /**Gets the attribute value for KpiDesc, using the alias name KpiDesc
     */
    public String getKpiDesc() {
        return (String)getAttributeInternal(KPIDESC);
    }

    /**Sets <code>value</code> as the attribute value for KpiDesc
     */
    public void setKpiDesc(String value) {
        setAttributeInternal(KPIDESC, value);
    }

    /**Gets the attribute value for KpiClass, using the alias name KpiClass
     */
    public String getKpiClass() {
        return (String)getAttributeInternal(KPICLASS);
    }

    /**Sets <code>value</code> as the attribute value for KpiClass
     */
    public void setKpiClass(String value) {
        setAttributeInternal(KPICLASS, value);
    }

    /**Gets the attribute value for KpiDataSource, using the alias name KpiDataSource
     */
    public String getKpiDataSource() {
        return (String)getAttributeInternal(KPIDATASOURCE);
    }

    /**Sets <code>value</code> as the attribute value for KpiDataSource
     */
    public void setKpiDataSource(String value) {
        setAttributeInternal(KPIDATASOURCE, value);
    }

    /**Gets the attribute value for KpiDataDimension, using the alias name KpiDataDimension
     */
    public String getKpiDataDimension() {
        return (String)getAttributeInternal(KPIDATADIMENSION);
    }

    /**Sets <code>value</code> as the attribute value for KpiDataDimension
     */
    public void setKpiDataDimension(String value) {
        setAttributeInternal(KPIDATADIMENSION, value);
    }

    /**Gets the attribute value for KpiScoringMethod, using the alias name KpiScoringMethod
     */
    public String getKpiScoringMethod() {
        return (String)getAttributeInternal(KPISCORINGMETHOD);
    }

    /**Sets <code>value</code> as the attribute value for KpiScoringMethod
     */
    public void setKpiScoringMethod(String value) {
        setAttributeInternal(KPISCORINGMETHOD, value);
    }

    /**Gets the attribute value for Attribute1, using the alias name Attribute1
     */
    public String getAttribute1() {
        return (String)getAttributeInternal(ATTRIBUTE1);
    }

    /**Sets <code>value</code> as the attribute value for Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**Gets the attribute value for Attribute2, using the alias name Attribute2
     */
    public String getAttribute2() {
        return (String)getAttributeInternal(ATTRIBUTE2);
    }

    /**Sets <code>value</code> as the attribute value for Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**Gets the attribute value for Attribute3, using the alias name Attribute3
     */
    public String getAttribute3() {
        return (String)getAttributeInternal(ATTRIBUTE3);
    }

    /**Sets <code>value</code> as the attribute value for Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**Gets the attribute value for Attribute4, using the alias name Attribute4
     */
    public String getAttribute4() {
        return (String)getAttributeInternal(ATTRIBUTE4);
    }

    /**Sets <code>value</code> as the attribute value for Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**Gets the attribute value for Attribute5, using the alias name Attribute5
     */
    public String getAttribute5() {
        return (String)getAttributeInternal(ATTRIBUTE5);
    }

    /**Sets <code>value</code> as the attribute value for Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for LastUpdateLogin, using the alias name LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**Gets the attribute value for CreatedBy, using the alias name CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for CreationDate, using the alias name CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
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
        case SCOREVALUE:
            return getScoreValue();
        case PARENTCONTRACTID:
            return getParentContractId();
        case WEIGHT:
            return getWeight();
        case SELFEVALVALUE:
            return getSelfEvalValue();
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
        case SCOREVALUE:
            setScoreValue((Number)value);
            return;
        case PARENTCONTRACTID:
            setParentContractId((Number)value);
            return;
        case WEIGHT:
            setWeight((String)value);
            return;
        case SELFEVALVALUE:
            setSelfEvalValue((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for ActualValue, using the alias name ActualValue
     */
    public Number getActualValue() {
        return (Number)getAttributeInternal(ACTUALVALUE);
    }

    /**Sets <code>value</code> as the attribute value for ActualValue
     */
    public void setActualValue(Number value) {

        setAttributeInternal(ACTUALVALUE, value);
    }

    /**Gets the attribute value for ScoreValue, using the alias name ScoreValue
     */
    public Number getScoreValue() {
        return (Number)getAttributeInternal(SCOREVALUE);
    }

    /**Sets <code>value</code> as the attribute value for ScoreValue
     */
    public void setScoreValue(Number value) {
        //Validate the value of score value 2009.12.4 dl
        if (value != null) {
            double temp = value.doubleValue();
            if (temp < 0)
                throw // EO name
                    // EO PK
                    // Attribute Name
                    // Attribute value
                    // Message product short name
                    new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                           getEntityDef().getFullName(), 
                                           getPrimaryKey(), "ScoreValue", 
                                           value, "CUX", 
                                           "CUX_KPI_NOT_MINUS_VALUE"); // Message name
        }
        setAttributeInternal(SCOREVALUE, value);
    }

    /**Gets the attribute value for ParentContractId, using the alias name ParentContractId
     */
    public Number getParentContractId() {
        return (Number)getAttributeInternal(PARENTCONTRACTID);
    }

    /**Sets <code>value</code> as the attribute value for ParentContractId
     */
    public void setParentContractId(Number value) {
        setAttributeInternal(PARENTCONTRACTID, value);
    }

    /**Gets the attribute value for SelfEvalValue, using the alias name SelfEvalValue
     */
    public Number getSelfEvalValue() {
        return (Number)getAttributeInternal(SELFEVALVALUE);
    }

    /**Sets <code>value</code> as the attribute value for SelfEvalValue
     */
    public void setSelfEvalValue(Number value) {
        //Validate the value of score value 2009.12.4 dl
        if (value != null) {
            double temp = value.doubleValue();
            if (temp < 0)
                throw // EO name
                    // EO PK
                    // Attribute Name
                    // Attribute value
                    // Message product short name
                    new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                           getEntityDef().getFullName(), 
                                           getPrimaryKey(), "SelfEvalValue", 
                                           value, "CUX", 
                                           "CUX_KPI_NOT_MINUS_VALUE"); // Message name
        }
        setAttributeInternal(SELFEVALVALUE, value);
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number contractId) {
        return new Key(new Object[] { contractId });
    }
}