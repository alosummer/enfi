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
public class TemplateVORowImpl extends OAViewRowImpl {
    public static final int ROWNUM = 0;
    public static final int TMPLID = 1;
    public static final int TMPLNAME = 2;
    public static final int ORGID = 3;
    public static final int GROUPID = 4;
    public static final int ORGANIZATIONID = 5;
    public static final int JOBID = 6;
    public static final int ATTRIBUTE1 = 7;
    public static final int ATTRIBUTE2 = 8;
    public static final int ATTRIBUTE3 = 9;
    public static final int ATTRIBUTE4 = 10;
    public static final int ATTRIBUTE5 = 11;
    public static final int LASTUPDATEDATE = 12;
    public static final int LASTUPDATEDBY = 13;
    public static final int LASTUPDATELOGIN = 14;
    public static final int CREATEDBY = 15;
    public static final int CREATIONDATE = 16;
    public static final int SELECTED = 17;
    public static final int TEMPLATECONTENTVO = 18;

    /**This is the default constructor (do not remove)
     */
    public TemplateVORowImpl() {
    }

    /**Gets TemplateEO entity object.
     */
    public TemplateEOImpl getTemplateEO() {
        return (TemplateEOImpl)getEntity(0);
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

    /**Gets the attribute value for TMPL_NAME using the alias name TmplName
     */
    public String getTmplName() {
        return (String)getAttributeInternal(TMPLNAME);
    }

    /**Sets <code>value</code> as attribute value for TMPL_NAME using the alias name TmplName
     */
    public void setTmplName(String value) {
        setAttributeInternal(TMPLNAME, value);
    }

    /**Gets the attribute value for ORG_ID using the alias name OrgId
     */
    public Number getOrgId() {
        return (Number)getAttributeInternal(ORGID);
    }

    /**Sets <code>value</code> as attribute value for ORG_ID using the alias name OrgId
     */
    public void setOrgId(Number value) {
        setAttributeInternal(ORGID, value);
    }

    /**Gets the attribute value for GROUP_ID using the alias name GroupId
     */
    public Number getGroupId() {
        return (Number)getAttributeInternal(GROUPID);
    }

    /**Sets <code>value</code> as attribute value for GROUP_ID using the alias name GroupId
     */
    public void setGroupId(Number value) {
        setAttributeInternal(GROUPID, value);
    }

    /**Gets the attribute value for ORGANIZATION_ID using the alias name OrganizationId
     */
    public Number getOrganizationId() {
        return (Number)getAttributeInternal(ORGANIZATIONID);
    }

    /**Sets <code>value</code> as attribute value for ORGANIZATION_ID using the alias name OrganizationId
     */
    public void setOrganizationId(Number value) {
        setAttributeInternal(ORGANIZATIONID, value);
    }

    /**Gets the attribute value for JOB_ID using the alias name JobId
     */
    public Number getJobId() {
        return (Number)getAttributeInternal(JOBID);
    }

    /**Sets <code>value</code> as attribute value for JOB_ID using the alias name JobId
     */
    public void setJobId(Number value) {
        setAttributeInternal(JOBID, value);
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
        case SELECTED:
            return getSelected();
        case TEMPLATECONTENTVO:
            return getTemplateContentVO();
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
        case SELECTED:
            setSelected((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
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

    /**Gets the attribute value for the calculated attribute Selected
     */
    public String getSelected() {
        return (String)getAttributeInternal(SELECTED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Selected
     */
    public void setSelected(String value) {
        setAttributeInternal(SELECTED, value);
    }

    /**Gets the associated <code>RowIterator</code> using master-detail link TemplateContentVO
     */
    public RowIterator getTemplateContentVO() {
        return (RowIterator)getAttributeInternal(TEMPLATECONTENTVO);
    }
}
