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
public class KeyPIVORowImpl extends OAViewRowImpl {
    public static final int ROWNUM = 0;
    public static final int KPIID = 1;
    public static final int KPICLASS = 2;
    public static final int KPINAME = 3;
    public static final int KPIDESC = 4;
    public static final int KPIDATASOURCE = 5;
    public static final int KPIDATADIMENSION = 6;
    public static final int STARTDATE = 7;
    public static final int ENDDATE = 8;
    public static final int ATTRIBUTE1 = 9;
    public static final int ATTRIBUTE2 = 10;
    public static final int ATTRIBUTE3 = 11;
    public static final int ATTRIBUTE4 = 12;
    public static final int ORGANIZATIONNAME = 13;
    public static final int ATTRIBUTE5 = 14;
    public static final int LASTUPDATEDATE = 15;
    public static final int LASTUPDATEDBY = 16;
    public static final int LASTUPDATELOGIN = 17;
    public static final int CREATEDBY = 18;
    public static final int CREATIONDATE = 19;
    public static final int KPIAREA = 20;
    public static final int KPISCORINGMETHOD = 21;
    public static final int CALTEXT = 22;
    public static final int CALTYPE = 23;
    public static final int REPORTTEXT = 24;
    public static final int REPORTTYPE = 25;
    public static final int UPDATABLE = 26;
    public static final int RENDERABLE = 27;
    public static final int KPICONSTRAINTVO = 28;

    /**This is the default constructor (do not remove)
     */
    public KeyPIVORowImpl() {
    }

    /**Gets KeyPIEO entity object.
     */
    public KeyPIEOImpl getKeyPIEO() {
        return (KeyPIEOImpl)getEntity(0);
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

    /**Gets the attribute value for START_DATE using the alias name StartDate
     */
    public Date getStartDate() {
        return (Date)getAttributeInternal(STARTDATE);
    }

    /**Sets <code>value</code> as attribute value for START_DATE using the alias name StartDate
     */
    public void setStartDate(Date value) {
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
        setAttributeInternal(ENDDATE, value);
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
        case ROWNUM:
            return getRownum();
        case KPIID:
            return getKpiId();
        case KPICLASS:
            return getKpiClass();
        case KPINAME:
            return getKpiName();
        case KPIDESC:
            return getKpiDesc();
        case KPIDATASOURCE:
            return getKpiDataSource();
        case KPIDATADIMENSION:
            return getKpiDataDimension();
        case STARTDATE:
            return getStartDate();
        case ENDDATE:
            return getEndDate();
        case ATTRIBUTE1:
            return getAttribute1();
        case ATTRIBUTE2:
            return getAttribute2();
        case ATTRIBUTE3:
            return getAttribute3();
        case ATTRIBUTE4:
            return getAttribute4();
        case ORGANIZATIONNAME:
            return getOrganizationName();
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
        case KPIAREA:
            return getKpiArea();
        case KPISCORINGMETHOD:
            return getKpiScoringMethod();
        case CALTEXT:
            return getCalText();
        case CALTYPE:
            return getCalType();
        case REPORTTEXT:
            return getReportText();
        case REPORTTYPE:
            return getReportType();
        case UPDATABLE:
            return getUpdatable();
        case RENDERABLE:
            return getRenderable();
        case KPICONSTRAINTVO:
            return getKPIConstraintVO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ROWNUM:
            setRownum((Number)value);
            return;
        case KPIID:
            setKpiId((Number)value);
            return;
        case KPICLASS:
            setKpiClass((String)value);
            return;
        case KPINAME:
            setKpiName((String)value);
            return;
        case KPIDESC:
            setKpiDesc((String)value);
            return;
        case KPIDATASOURCE:
            setKpiDataSource((String)value);
            return;
        case KPIDATADIMENSION:
            setKpiDataDimension((String)value);
            return;
        case STARTDATE:
            setStartDate((Date)value);
            return;
        case ENDDATE:
            setEndDate((Date)value);
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
        case ORGANIZATIONNAME:
            setOrganizationName((String)value);
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
        case KPIAREA:
            setKpiArea((String)value);
            return;
        case KPISCORINGMETHOD:
            setKpiScoringMethod((String)value);
            return;
        case CALTEXT:
            setCalText((String)value);
            return;
        case CALTYPE:
            setCalType((String)value);
            return;
        case REPORTTEXT:
            setReportText((String)value);
            return;
        case REPORTTYPE:
            setReportType((String)value);
            return;
        case UPDATABLE:
            setUpdatable((Boolean)value);
            return;
        case RENDERABLE:
            setRenderable((Boolean)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
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


    /**Gets the attribute value for CAL_TEXT using the alias name CalText
     */
    public String getCalText() {
        return (String)getAttributeInternal(CALTEXT);
    }

    /**Sets <code>value</code> as attribute value for CAL_TEXT using the alias name CalText
     */
    public void setCalText(String value) {
        setAttributeInternal(CALTEXT, value);
    }

    /**Gets the attribute value for CAL_TYPE using the alias name CalType
     */
    public String getCalType() {
        return (String)getAttributeInternal(CALTYPE);
    }

    /**Sets <code>value</code> as attribute value for CAL_TYPE using the alias name CalType
     */
    public void setCalType(String value) {
        setAttributeInternal(CALTYPE, value);
    }

    /**Gets the attribute value for REPORT_TEXT using the alias name ReportText
     */
    public String getReportText() {
        return (String)getAttributeInternal(REPORTTEXT);
    }

    /**Sets <code>value</code> as attribute value for REPORT_TEXT using the alias name ReportText
     */
    public void setReportText(String value) {
        setAttributeInternal(REPORTTEXT, value);
    }

    /**Gets the attribute value for REPORT_TYPE using the alias name ReportType
     */
    public String getReportType() {
        return (String)getAttributeInternal(REPORTTYPE);
    }

    /**Sets <code>value</code> as attribute value for REPORT_TYPE using the alias name ReportType
     */
    public void setReportType(String value) {
        setAttributeInternal(REPORTTYPE, value);
    }

    /**Gets the attribute value for the calculated attribute Rownum
     */
    public Number getRownum() {
        return (Number)getAttributeInternal(ROWNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Rownum
     */
    public void setRownum(Number value) {
        setAttributeInternal(ROWNUM, value);
    }

    /**Gets the associated <code>RowIterator</code> using master-detail link KPIConstraintVO
     */
    public RowIterator getKPIConstraintVO() {
        return (RowIterator)getAttributeInternal(KPICONSTRAINTVO);
    }

    /**Gets the attribute value for the calculated attribute Updatable
     */
    public Boolean getUpdatable() {
        return (Boolean)getAttributeInternal(UPDATABLE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Updatable
     */
    public void setUpdatable(Boolean value) {
        setAttributeInternal(UPDATABLE, value);
    }

    /**Gets the attribute value for the calculated attribute Renderable
     */
    public Boolean getRenderable() {
        return (Boolean)getAttributeInternal(RENDERABLE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Renderable
     */
    public void setRenderable(Boolean value) {
        setAttributeInternal(RENDERABLE, value);
    }

    /**Gets the attribute value for the calculated attribute OrganizationName
     */
    public String getOrganizationName() {
        return (String)getAttributeInternal(ORGANIZATIONNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrganizationName
     */
    public void setOrganizationName(String value) {
        setAttributeInternal(ORGANIZATIONNAME, value);
    }
}
