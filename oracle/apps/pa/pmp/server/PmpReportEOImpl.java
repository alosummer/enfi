package cux.oracle.apps.pa.pmp.server;

import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.domain.RowID;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PmpReportEOImpl extends OAEntityImpl {
    public static final int PROJECTID = 0;
    public static final int VERSIONNUM = 1;
    public static final int SORTSEQ = 2;
    public static final int CATEGORYNAME = 3;
    public static final int REPORTNAME = 4;
    public static final int REPORTDATE = 5;
    public static final int REPORTOWNER = 6;
    public static final int PARTICIPANT = 7;
    public static final int CREATEDBY = 8;
    public static final int CREATIONDATE = 9;
    public static final int LASTUPDATEDBY = 10;
    public static final int LASTUPDATEDATE = 11;
    public static final int LASTUPDATELOGIN = 12;
    public static final int ROWID = 13;

    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public PmpReportEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("cux.oracle.apps.pa.pmp.server.PmpReportEO");
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
    }

    /**Gets the attribute value for ProjectId, using the alias name ProjectId
     */
    public Number getProjectId() {
        return (Number)getAttributeInternal(PROJECTID);
    }

    /**Sets <code>value</code> as the attribute value for ProjectId
     */
    public void setProjectId(Number value) {
        setAttributeInternal(PROJECTID, value);
    }

    /**Gets the attribute value for VersionNum, using the alias name VersionNum
     */
    public Number getVersionNum() {
        return (Number)getAttributeInternal(VERSIONNUM);
    }

    /**Sets <code>value</code> as the attribute value for VersionNum
     */
    public void setVersionNum(Number value) {
        setAttributeInternal(VERSIONNUM, value);
    }

    /**Gets the attribute value for SortSeq, using the alias name SortSeq
     */
    public Number getSortSeq() {
        return (Number)getAttributeInternal(SORTSEQ);
    }

    /**Sets <code>value</code> as the attribute value for SortSeq
     */
    public void setSortSeq(Number value) {
        setAttributeInternal(SORTSEQ, value);
    }

    /**Gets the attribute value for CategoryName, using the alias name CategoryName
     */
    public String getCategoryName() {
        return (String)getAttributeInternal(CATEGORYNAME);
    }

    /**Sets <code>value</code> as the attribute value for CategoryName
     */
    public void setCategoryName(String value) {
        setAttributeInternal(CATEGORYNAME, value);
    }

    /**Gets the attribute value for ReportName, using the alias name ReportName
     */
    public String getReportName() {
        return (String)getAttributeInternal(REPORTNAME);
    }

    /**Sets <code>value</code> as the attribute value for ReportName
     */
    public void setReportName(String value) {
        setAttributeInternal(REPORTNAME, value);
    }

    /**Gets the attribute value for ReportDate, using the alias name ReportDate
     */
    public Date getReportDate() {
        return (Date)getAttributeInternal(REPORTDATE);
    }

    /**Sets <code>value</code> as the attribute value for ReportDate
     */
    public void setReportDate(Date value) {
        setAttributeInternal(REPORTDATE, value);
    }

    /**Gets the attribute value for ReportOwner, using the alias name ReportOwner
     */
    public String getReportOwner() {
        return (String)getAttributeInternal(REPORTOWNER);
    }

    /**Sets <code>value</code> as the attribute value for ReportOwner
     */
    public void setReportOwner(String value) {
        setAttributeInternal(REPORTOWNER, value);
    }

    /**Gets the attribute value for Participant, using the alias name Participant
     */
    public String getParticipant() {
        return (String)getAttributeInternal(PARTICIPANT);
    }

    /**Sets <code>value</code> as the attribute value for Participant
     */
    public void setParticipant(String value) {
        setAttributeInternal(PARTICIPANT, value);
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

    /**Gets the attribute value for RowID, using the alias name RowID
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
        case SORTSEQ:
            return getSortSeq();
        case CATEGORYNAME:
            return getCategoryName();
        case REPORTNAME:
            return getReportName();
        case REPORTDATE:
            return getReportDate();
        case REPORTOWNER:
            return getReportOwner();
        case PARTICIPANT:
            return getParticipant();
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
        case SORTSEQ:
            setSortSeq((Number)value);
            return;
        case CATEGORYNAME:
            setCategoryName((String)value);
            return;
        case REPORTNAME:
            setReportName((String)value);
            return;
        case REPORTDATE:
            setReportDate((Date)value);
            return;
        case REPORTOWNER:
            setReportOwner((String)value);
            return;
        case PARTICIPANT:
            setParticipant((String)value);
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
