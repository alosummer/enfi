package cux.oracle.apps.per.review.server;

import com.sun.java.util.collections.Iterator;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAAttrValException;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
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
public class TemplateEOImpl extends OAEntityImpl {
    public static final int TMPLID = 0;
    public static final int TMPLNAME = 1;
    public static final int ORGID = 2;
    public static final int GROUPID = 3;
    public static final int ORGANIZATIONID = 4;
    public static final int JOBID = 5;
    public static final int ATTRIBUTE1 = 6;
    public static final int ATTRIBUTE2 = 7;
    public static final int ATTRIBUTE3 = 8;
    public static final int ATTRIBUTE4 = 9;
    public static final int ATTRIBUTE5 = 10;
    public static final int LASTUPDATEDATE = 11;
    public static final int LASTUPDATEDBY = 12;
    public static final int LASTUPDATELOGIN = 13;
    public static final int CREATEDBY = 14;
    public static final int CREATIONDATE = 15;
    private static TemplateEODefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public TemplateEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (TemplateEODefImpl)EntityDefImpl.findDefObject("cux.oracle.apps.per.review.server.TemplateEO");
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

    /**Gets the attribute value for TmplName, using the alias name TmplName
     */
    public String getTmplName() {
        return (String)getAttributeInternal(TMPLNAME);
    }

    /**Sets <code>value</code> as the attribute value for TmplName
     */
    public void setTmplName(String value) {
        if ((value != null) || (!("".equals(value.trim())))) {
            Iterator iter = 
                getDefinitionObject().getAllEntityInstancesIterator(getDBTransaction());
            Number currentId = getTmplId();
            while (iter.hasNext()) {
                TemplateEOImpl cachedTmpl = (TemplateEOImpl)iter.next();
                String cachedName = cachedTmpl.getTmplName();
                Number cachedId = cachedTmpl.getTmplId();

                if (cachedName != null && value.equalsIgnoreCase(cachedName) && 
                    cachedId.compareTo(currentId) != 0) {
                    throw // EO name
                        // EO PK
                        // Attribute Name
                        // Attribute value
                        // Message product short name
                        new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                               getEntityDef().getFullName(), 
                                               getPrimaryKey(), "TmplName", 
                                               value, "CUX", 
                                               "CUX_DUP_TMPL_NAME"); // Message name
                }
            }

            // Now we want to check the database for any occurrences of the Template name. 
            OADBTransaction transaction = getOADBTransaction();
            OAApplicationModule vam;
            // Look to see if the VAM has already been created in this transaction. If not, create it.
            vam = 
(OAApplicationModule)transaction.findApplicationModule("TmplManageAM");
            if (vam == null) {
                vam = 
(OAApplicationModule)transaction.createApplicationModule("TmplManageAM", 
                                                         "cux.oracle.apps.per.review.server.TmplManageAM");
            }

            // Now, we use a lightweight "validation" view object to see if a KPI exists
            // with the given name.
            TemplateVOImpl valNameVo = 
                (TemplateVOImpl)vam.findViewObject("TemplateVO1");
            valNameVo.initQuery(value);
            if (valNameVo.hasNext()) {
                throw // EO name
                    // EO PK
                    // Attribute Name
                    // Attribute value
                    // Message product short name
                    new OAAttrValException(OAException.TYP_ENTITY_OBJECT, 
                                           getEntityDef().getFullName(), 
                                           getPrimaryKey(), "TmplName", value, 
                                           "CUX", 
                                           "CUX_DUP_TMPL_NAME"); // Message name
            }
        }

        setAttributeInternal(TMPLNAME, value);
    }

    /**Gets the attribute value for OrgId, using the alias name OrgId
     */
    public Number getOrgId() {
        return (Number)getAttributeInternal(ORGID);
    }

    /**Sets <code>value</code> as the attribute value for OrgId
     */
    public void setOrgId(Number value) {
        setAttributeInternal(ORGID, value);
    }

    /**Gets the attribute value for GroupId, using the alias name GroupId
     */
    public Number getGroupId() {
        return (Number)getAttributeInternal(GROUPID);
    }

    /**Sets <code>value</code> as the attribute value for GroupId
     */
    public void setGroupId(Number value) {
        setAttributeInternal(GROUPID, value);
    }

    /**Gets the attribute value for OrganizationId, using the alias name OrganizationId
     */
    public Number getOrganizationId() {
        return (Number)getAttributeInternal(ORGANIZATIONID);
    }

    /**Sets <code>value</code> as the attribute value for OrganizationId
     */
    public void setOrganizationId(Number value) {
        setAttributeInternal(ORGANIZATIONID, value);
    }

    /**Gets the attribute value for JobId, using the alias name JobId
     */
    public Number getJobId() {
        return (Number)getAttributeInternal(JOBID);
    }

    /**Sets <code>value</code> as the attribute value for JobId
     */
    public void setJobId(Number value) {
        setAttributeInternal(JOBID, value);
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
        case TMPLID:
            return getTmplId();
        case TMPLNAME:
            return getTmplName();
        case ORGID:
            return getOrgId();
        case GROUPID:
            return getGroupId();
        case ORGANIZATIONID:
            return getOrganizationId();
        case JOBID:
            return getJobId();
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
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case TMPLID:
            setTmplId((Number)value);
            return;
        case TMPLNAME:
            setTmplName((String)value);
            return;
        case ORGID:
            setOrgId((Number)value);
            return;
        case GROUPID:
            setGroupId((Number)value);
            return;
        case ORGANIZATIONID:
            setOrganizationId((Number)value);
            return;
        case JOBID:
            setJobId((Number)value);
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
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number tmplId) {
        return new Key(new Object[] { tmplId });
    }
}
