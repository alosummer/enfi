package cux.oracle.apps.per.review.server;

import com.sun.java.util.collections.Iterator;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAEntityDefImpl;
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
public class ReviewWorkflowEOImpl extends OAEntityImpl {
    public static final int ATTRIBUTE1 = 0;
    public static final int ATTRIBUTE2 = 1;
    public static final int ATTRIBUTE3 = 2;
    public static final int ATTRIBUTE4 = 3;
    public static final int ATTRIBUTE5 = 4;
    public static final int CREATEDBY = 5;
    public static final int CREATIONDATE = 6;
    public static final int EPMPERIODCODE = 7;
    public static final int EPMPHASECLASSCODE = 8;
    public static final int GROUPID = 9;
    public static final int ISATTENDED = 10;
    public static final int LASTUPDATEDBY = 11;
    public static final int LASTUPDATEDATE = 12;
    public static final int LASTUPDATELOGIN = 13;
    public static final int ORGID = 14;
    public static final int EPMWORKFLOWID = 15;


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public ReviewWorkflowEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("cux.oracle.apps.per.review.server.ReviewWorkflowEO");
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

    /**Gets the attribute value for EpmPeriodCode, using the alias name EpmPeriodCode
     */
    public String getEpmPeriodCode() {
        return (String)getAttributeInternal(EPMPERIODCODE);
    }

    /**Sets <code>value</code> as the attribute value for EpmPeriodCode
     */
    public void setEpmPeriodCode(String value) {
        setAttributeInternal(EPMPERIODCODE, value);
    }

    /**Gets the attribute value for EpmPhaseClassCode, using the alias name EpmPhaseClassCode
     */
    public String getEpmPhaseClassCode() {
        return (String)getAttributeInternal(EPMPHASECLASSCODE);
    }

    /**Sets <code>value</code> as the attribute value for EpmPhaseClassCode
     */
    public void setEpmPhaseClassCode(String value) {
        setAttributeInternal(EPMPHASECLASSCODE, value);
    }

    /**Gets the attribute value for EpmWorkflowId, using the alias name EpmWorkflowId
     */
    public Number getEpmWorkflowId() {
        return (Number)getAttributeInternal(EPMWORKFLOWID);
    }

    /**Sets <code>value</code> as the attribute value for EpmWorkflowId
     */
    public void setEpmWorkflowId(Number value) {
        setAttributeInternal(EPMWORKFLOWID, value);
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

    /**Gets the attribute value for IsAttended, using the alias name IsAttended
     */
    public String getIsAttended() {
        return (String)getAttributeInternal(ISATTENDED);
    }

    /**Sets <code>value</code> as the attribute value for IsAttended
     */
    public void setIsAttended(String value) {
        setAttributeInternal(ISATTENDED, value);
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


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
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
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case EPMPERIODCODE:
            return getEpmPeriodCode();
        case EPMPHASECLASSCODE:
            return getEpmPhaseClassCode();
        case GROUPID:
            return getGroupId();
        case ISATTENDED:
            return getIsAttended();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case ORGID:
            return getOrgId();
        case EPMWORKFLOWID:
            return getEpmWorkflowId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
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
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case EPMPERIODCODE:
            setEpmPeriodCode((String)value);
            return;
        case EPMPHASECLASSCODE:
            setEpmPhaseClassCode((String)value);
            return;
        case GROUPID:
            setGroupId((Number)value);
            return;
        case ISATTENDED:
            setIsAttended((String)value);
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
        case ORGID:
            setOrgId((Number)value);
            return;
        case EPMWORKFLOWID:
            setEpmWorkflowId((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Add Entity validation code in this method.
     */
    protected void validateEntity() {
        super.validateEntity();

        Iterator iter = 
            getDefinitionObject().getAllEntityInstancesIterator(getDBTransaction());
        String epmPeriodCode = this.getEpmPeriodCode();
        String epmPhaseClassCode = this.getEpmPhaseClassCode();
        Number groupId = this.getGroupId();
        Number orgId = this.getOrgId();
        Number epmWorkflowId = this.getEpmWorkflowId();
        String isAtended = this.getIsAttended(); /*20100205 zs*/

        while (iter.hasNext()) {
            ReviewWorkflowEOImpl cachedObj = (ReviewWorkflowEOImpl)iter.next();
            Number cachedEpmWorkflowId = cachedObj.getEpmWorkflowId();
            Number cachedGroupId = cachedObj.getGroupId();
            Number cachedOrgId = cachedObj.getOrgId();
            String cachedEpmPeriodCode = cachedObj.getEpmPeriodCode();
            String cachedEpmPhaseClassCode = cachedObj.getEpmPhaseClassCode();
            String cachedIsAtended = cachedObj.getIsAttended(); /*20100205 zs*/

            //validate the combination of groups and organizations!   
            if (cachedEpmWorkflowId.compareTo(epmWorkflowId) != 0 && 
                cachedOrgId.compareTo(orgId) == 0 && 
                cachedGroupId.compareTo(groupId) == 0 && 
                cachedEpmPeriodCode.equals(epmPeriodCode) && 
                cachedEpmPhaseClassCode.equals(epmPhaseClassCode) && 
                cachedIsAtended.equals(isAtended)) /*20100205 zs*/
            {
                throw new OAException("CUX", "CUX_DUP_REVIEW_WORKFLOW", null, 
                                      OAException.ERROR, null);
            }
        }

        /*��֤��ݿ��еļ�¼�Ƿ��뵱ǰ�û������ļ�¼�г�ͻ*/
        OADBTransaction transaction = getOADBTransaction();
        OAApplicationModule vam;
        vam = 
(OAApplicationModule)transaction.findApplicationModule("ReviewWorkflowAM");
        if (vam == null) {
            vam = 
(OAApplicationModule)transaction.createApplicationModule("ReviewWorkflowAM", 
                                                         "cux.oracle.apps.per.review.server.ReviewWorkflowAM");
        }

        ReviewWorkflowVOImpl vo = 
            (ReviewWorkflowVOImpl)vam.findViewObject("ReviewWorkflowVO1");
        vo.initQuery(epmPeriodCode, epmPhaseClassCode, groupId, orgId, 
                     isAtended);
        if (vo.hasNext()) {
            throw new OAException("CUX", "CUX_DUP_REVIEW_WORKFLOW", null, 
                                  OAException.ERROR, null);
        }
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number epmWorkflowId) {
        return new Key(new Object[] { epmWorkflowId });
    }
}