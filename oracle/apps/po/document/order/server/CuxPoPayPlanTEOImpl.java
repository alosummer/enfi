package cux.oracle.apps.po.document.order.server;

import java.sql.CallableStatement;

import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxPoPayPlanTEOImpl extends OAEntityImpl {
    public static final int LINEID = 0;
    public static final int LINENUM = 1;
    public static final int POHEADERID = 2;
    public static final int VERSIONID = 3;
    public static final int PAYTYPE = 4;
    public static final int INITAMT = 5;
    public static final int INITPAYDATE = 6;
    public static final int NEWAMT = 7;
    public static final int NEWPAYDATE = 8;
    public static final int MEMO = 9;
    public static final int PAYAPPLYAMT = 10;
    public static final int PAYAPPLYNUM = 11;
    public static final int PAYAPPLYDATE = 12;
    public static final int PAYAPPLIERNAME = 13;
    public static final int PAIDAMT = 14;
    public static final int CREATEDBY = 15;
    public static final int CREATIONDATE = 16;
    public static final int LASTUPDATEDBY = 17;
    public static final int LASTUPDATEDATE = 18;
    public static final int LASTUPDATELOGIN = 19;
    public static final int ATTRIBUTECATEGORY = 20;
    public static final int ATTRIBUTE1 = 21;
    public static final int ATTRIBUTE2 = 22;
    public static final int ATTRIBUTE3 = 23;
    public static final int ATTRIBUTE4 = 24;
    public static final int ATTRIBUTE5 = 25;
    public static final int ATTRIBUTE6 = 26;
    public static final int ATTRIBUTE7 = 27;
    public static final int ATTRIBUTE8 = 28;
    public static final int ATTRIBUTE9 = 29;
    public static final int ATTRIBUTE10 = 30;
    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public CuxPoPayPlanTEOImpl() {
    }

    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("cux.oracle.apps.po.document.order.server.CuxPoPayPlanTEO");
        }
        return mDefinitionObject;
    }

    /**Gets the attribute value for LineId, using the alias name LineId
     */
    public Number getLineId() {
        return (Number)getAttributeInternal(LINEID);
    }

    /**Sets <code>value</code> as the attribute value for LineId
     */
    public void setLineId(Number value) {
        setAttributeInternal(LINEID, value);
    }

    /**Gets the attribute value for LineNum, using the alias name LineNum
     */
    public Number getLineNum() {
        return (Number)getAttributeInternal(LINENUM);
    }

    /**Sets <code>value</code> as the attribute value for LineNum
     */
    public void setLineNum(Number value) {
        setAttributeInternal(LINENUM, value);
    }

    /**Gets the attribute value for PoHeaderId, using the alias name PoHeaderId
     */
    public Number getPoHeaderId() {
        return (Number)getAttributeInternal(POHEADERID);
    }

    /**Sets <code>value</code> as the attribute value for PoHeaderId
     */
    public void setPoHeaderId(Number value) {
        setAttributeInternal(POHEADERID, value);
    }

    /**Gets the attribute value for VersionId, using the alias name VersionId
     */
    public Number getVersionId() {
        return (Number)getAttributeInternal(VERSIONID);
    }

    /**Sets <code>value</code> as the attribute value for VersionId
     */
    public void setVersionId(Number value) {
        setAttributeInternal(VERSIONID, value);
    }

    /**Gets the attribute value for PayType, using the alias name PayType
     */
    public String getPayType() {
        return (String)getAttributeInternal(PAYTYPE);
    }

    /**Sets <code>value</code> as the attribute value for PayType
     */
    public void setPayType(String value) {
        setAttributeInternal(PAYTYPE, value);
    }

    /**Gets the attribute value for InitAmt, using the alias name InitAmt
     */
    public Number getInitAmt() {
        return (Number)getAttributeInternal(INITAMT);
    }

    /**Sets <code>value</code> as the attribute value for InitAmt
     */
    public void setInitAmt(Number value) {
        setAttributeInternal(INITAMT, value);
    }

    /**Gets the attribute value for InitPayDate, using the alias name InitPayDate
     */
    public Date getInitPayDate() {
        return (Date)getAttributeInternal(INITPAYDATE);
    }

    /**Sets <code>value</code> as the attribute value for InitPayDate
     */
    public void setInitPayDate(Date value) {
        setAttributeInternal(INITPAYDATE, value);
    }

    /**Gets the attribute value for NewAmt, using the alias name NewAmt
     */
    public Number getNewAmt() {
        return (Number)getAttributeInternal(NEWAMT);
    }

    /**Sets <code>value</code> as the attribute value for NewAmt
     */
    public void setNewAmt(Number value) {
        setAttributeInternal(NEWAMT, value);
    }

    /**Gets the attribute value for NewPayDate, using the alias name NewPayDate
     */
    public Date getNewPayDate() {
        return (Date)getAttributeInternal(NEWPAYDATE);
    }

    /**Sets <code>value</code> as the attribute value for NewPayDate
     */
    public void setNewPayDate(Date value) {
        setAttributeInternal(NEWPAYDATE, value);
    }

    /**Gets the attribute value for Memo, using the alias name Memo
     */
    public String getMemo() {
        return (String)getAttributeInternal(MEMO);
    }

    /**Sets <code>value</code> as the attribute value for Memo
     */
    public void setMemo(String value) {
        setAttributeInternal(MEMO, value);
    }

    /**Gets the attribute value for PayApplyAmt, using the alias name PayApplyAmt
     */
    public Number getPayApplyAmt() {
        return (Number)getAttributeInternal(PAYAPPLYAMT);
    }

    /**Sets <code>value</code> as the attribute value for PayApplyAmt
     */
    public void setPayApplyAmt(Number value) {
        setAttributeInternal(PAYAPPLYAMT, value);
    }

    /**Gets the attribute value for PayApplyNum, using the alias name PayApplyNum
     */
    public String getPayApplyNum() {
        return (String)getAttributeInternal(PAYAPPLYNUM);
    }

    /**Sets <code>value</code> as the attribute value for PayApplyNum
     */
    public void setPayApplyNum(String value) {
        setAttributeInternal(PAYAPPLYNUM, value);
    }

    /**Gets the attribute value for PayApplyDate, using the alias name PayApplyDate
     */
    public Date getPayApplyDate() {
        return (Date)getAttributeInternal(PAYAPPLYDATE);
    }

    /**Sets <code>value</code> as the attribute value for PayApplyDate
     */
    public void setPayApplyDate(Date value) {
        setAttributeInternal(PAYAPPLYDATE, value);
    }

    /**Gets the attribute value for PayApplierName, using the alias name PayApplierName
     */
    public String getPayApplierName() {
        return (String)getAttributeInternal(PAYAPPLIERNAME);
    }

    /**Sets <code>value</code> as the attribute value for PayApplierName
     */
    public void setPayApplierName(String value) {
        setAttributeInternal(PAYAPPLIERNAME, value);
    }

    /**Gets the attribute value for PaidAmt, using the alias name PaidAmt
     */
    public Number getPaidAmt() {
        return (Number)getAttributeInternal(PAIDAMT);
    }

    /**Sets <code>value</code> as the attribute value for PaidAmt
     */
    public void setPaidAmt(Number value) {
        setAttributeInternal(PAIDAMT, value);
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

    /**Gets the attribute value for AttributeCategory, using the alias name AttributeCategory
     */
    public String getAttributeCategory() {
        return (String)getAttributeInternal(ATTRIBUTECATEGORY);
    }

    /**Sets <code>value</code> as the attribute value for AttributeCategory
     */
    public void setAttributeCategory(String value) {
        setAttributeInternal(ATTRIBUTECATEGORY, value);
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

    /**Gets the attribute value for Attribute6, using the alias name Attribute6
     */
    public String getAttribute6() {
        return (String)getAttributeInternal(ATTRIBUTE6);
    }

    /**Sets <code>value</code> as the attribute value for Attribute6
     */
    public void setAttribute6(String value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**Gets the attribute value for Attribute7, using the alias name Attribute7
     */
    public String getAttribute7() {
        return (String)getAttributeInternal(ATTRIBUTE7);
    }

    /**Sets <code>value</code> as the attribute value for Attribute7
     */
    public void setAttribute7(String value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**Gets the attribute value for Attribute8, using the alias name Attribute8
     */
    public String getAttribute8() {
        return (String)getAttributeInternal(ATTRIBUTE8);
    }

    /**Sets <code>value</code> as the attribute value for Attribute8
     */
    public void setAttribute8(String value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**Gets the attribute value for Attribute9, using the alias name Attribute9
     */
    public String getAttribute9() {
        return (String)getAttributeInternal(ATTRIBUTE9);
    }

    /**Sets <code>value</code> as the attribute value for Attribute9
     */
    public void setAttribute9(String value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**Gets the attribute value for Attribute10, using the alias name Attribute10
     */
    public String getAttribute10() {
        return (String)getAttributeInternal(ATTRIBUTE10);
    }

    /**Sets <code>value</code> as the attribute value for Attribute10
     */
    public void setAttribute10(String value) {
        setAttributeInternal(ATTRIBUTE10, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case LINEID:
            return getLineId();
        case LINENUM:
            return getLineNum();
        case POHEADERID:
            return getPoHeaderId();
        case VERSIONID:
            return getVersionId();
        case PAYTYPE:
            return getPayType();
        case INITAMT:
            return getInitAmt();
        case INITPAYDATE:
            return getInitPayDate();
        case NEWAMT:
            return getNewAmt();
        case NEWPAYDATE:
            return getNewPayDate();
        case MEMO:
            return getMemo();
        case PAYAPPLYAMT:
            return getPayApplyAmt();
        case PAYAPPLYNUM:
            return getPayApplyNum();
        case PAYAPPLYDATE:
            return getPayApplyDate();
        case PAYAPPLIERNAME:
            return getPayApplierName();
        case PAIDAMT:
            return getPaidAmt();
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
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case LINEID:
            setLineId((Number)value);
            return;
        case LINENUM:
            setLineNum((Number)value);
            return;
        case POHEADERID:
            setPoHeaderId((Number)value);
            return;
        case VERSIONID:
            setVersionId((Number)value);
            return;
        case PAYTYPE:
            setPayType((String)value);
            return;
        case INITAMT:
            setInitAmt((Number)value);
            return;
        case INITPAYDATE:
            setInitPayDate((Date)value);
            return;
        case NEWAMT:
            setNewAmt((Number)value);
            return;
        case NEWPAYDATE:
            setNewPayDate((Date)value);
            return;
        case MEMO:
            setMemo((String)value);
            return;
        case PAYAPPLYAMT:
            setPayApplyAmt((Number)value);
            return;
        case PAYAPPLYNUM:
            setPayApplyNum((String)value);
            return;
        case PAYAPPLYDATE:
            setPayApplyDate((Date)value);
            return;
        case PAYAPPLIERNAME:
            setPayApplierName((String)value);
            return;
        case PAIDAMT:
            setPaidAmt((Number)value);
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
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number lineId) {
        return new Key(new Object[] { lineId });
    }


}