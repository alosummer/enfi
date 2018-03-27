package cux.oracle.apps.pa.pmp.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.domain.RowID;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PmpDocManagementVORowImpl extends OAViewRowImpl {
    public static final int PROJECTID = 0;
    public static final int VERSIONNUM = 1;
    public static final int DOCTYPE = 2;
    public static final int REQUIRMENT = 3;
    public static final int DESIGNCONSULTATION = 4;
    public static final int EQUIPMENTSUPPLY = 5;
    public static final int CONTRACTED = 6;
    public static final int OTHER = 7;
    public static final int ATTRIBUTE1 = 8;
    public static final int ATTRIBUTE2 = 9;
    public static final int ATTRIBUTE3 = 10;
    public static final int ATTRIBUTE4 = 11;
    public static final int ATTRIBUTE5 = 12;
    public static final int ATTRIBUTE6 = 13;
    public static final int ATTRIBUTE7 = 14;
    public static final int ATTRIBUTE8 = 15;
    public static final int ATTRIBUTE9 = 16;
    public static final int CREATEDBY = 17;
    public static final int CREATIONDATE = 18;
    public static final int LASTUPDATEDBY = 19;
    public static final int LASTUPDATEDATE = 20;
    public static final int LASTUPDATELOGIN = 21;
    public static final int ROWID = 22;

    /**This is the default constructor (do not remove)
     */
    public PmpDocManagementVORowImpl() {
    }

    /**Gets PmpDocManagementEO entity object.
     */
    public PmpDocManagementEOImpl getPmpDocManagementEO() {
        return (PmpDocManagementEOImpl)getEntity(0);
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

    /**Gets the attribute value for VERSION_NUM using the alias name VersionNum
     */
    public Number getVersionNum() {
        return (Number)getAttributeInternal(VERSIONNUM);
    }

    /**Sets <code>value</code> as attribute value for VERSION_NUM using the alias name VersionNum
     */
    public void setVersionNum(Number value) {
        setAttributeInternal(VERSIONNUM, value);
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

    /**Gets the attribute value for REQUIRMENT using the alias name Requirment
     */
    public String getRequirment() {
        return (String)getAttributeInternal(REQUIRMENT);
    }

    /**Sets <code>value</code> as attribute value for REQUIRMENT using the alias name Requirment
     */
    public void setRequirment(String value) {
        setAttributeInternal(REQUIRMENT, value);
    }

    /**Gets the attribute value for DESIGN_CONSULTATION using the alias name DesignConsultation
     */
    public String getDesignConsultation() {
        return (String)getAttributeInternal(DESIGNCONSULTATION);
    }

    /**Sets <code>value</code> as attribute value for DESIGN_CONSULTATION using the alias name DesignConsultation
     */
    public void setDesignConsultation(String value) {
        setAttributeInternal(DESIGNCONSULTATION, value);
    }

    /**Gets the attribute value for EQUIPMENT_SUPPLY using the alias name EquipmentSupply
     */
    public String getEquipmentSupply() {
        return (String)getAttributeInternal(EQUIPMENTSUPPLY);
    }

    /**Sets <code>value</code> as attribute value for EQUIPMENT_SUPPLY using the alias name EquipmentSupply
     */
    public void setEquipmentSupply(String value) {
        setAttributeInternal(EQUIPMENTSUPPLY, value);
    }

    /**Gets the attribute value for CONTRACTED using the alias name Contracted
     */
    public String getContracted() {
        return (String)getAttributeInternal(CONTRACTED);
    }

    /**Sets <code>value</code> as attribute value for CONTRACTED using the alias name Contracted
     */
    public void setContracted(String value) {
        setAttributeInternal(CONTRACTED, value);
    }

    /**Gets the attribute value for OTHER using the alias name Other
     */
    public String getOther() {
        return (String)getAttributeInternal(OTHER);
    }

    /**Sets <code>value</code> as attribute value for OTHER using the alias name Other
     */
    public void setOther(String value) {
        setAttributeInternal(OTHER, value);
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

    /**Gets the attribute value for ROWID using the alias name RowID
     */
    public RowID getRowID() {
        return (RowID)getAttributeInternal(ROWID);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTID:
            return getProjectId();
        case VERSIONNUM:
            return getVersionNum();
        case DOCTYPE:
            return getDocType();
        case REQUIRMENT:
            return getRequirment();
        case DESIGNCONSULTATION:
            return getDesignConsultation();
        case EQUIPMENTSUPPLY:
            return getEquipmentSupply();
        case CONTRACTED:
            return getContracted();
        case OTHER:
            return getOther();
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
        case ROWID:
            return getRowID();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTID:
            setProjectId((Number)value);
            return;
        case VERSIONNUM:
            setVersionNum((Number)value);
            return;
        case DOCTYPE:
            setDocType((String)value);
            return;
        case REQUIRMENT:
            setRequirment((String)value);
            return;
        case DESIGNCONSULTATION:
            setDesignConsultation((String)value);
            return;
        case EQUIPMENTSUPPLY:
            setEquipmentSupply((String)value);
            return;
        case CONTRACTED:
            setContracted((String)value);
            return;
        case OTHER:
            setOther((String)value);
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
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
