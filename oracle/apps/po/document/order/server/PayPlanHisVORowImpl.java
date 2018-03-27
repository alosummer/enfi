package cux.oracle.apps.po.document.order.server;

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
public class PayPlanHisVORowImpl extends OAViewRowImpl {
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
    public static final int PAYAPPLIERNAME = 12;
    public static final int CREATEDBY = 13;
    public static final int CREATIONDATE = 14;
    public static final int LASTUPDATEDBY = 15;
    public static final int LASTUPDATEDATE = 16;
    public static final int LASTUPDATELOGIN = 17;
    public static final int ROWID1 = 18;
    public static final int PAYAPPLYDATE = 19;
    public static final int PAIDAMT = 20;
    public static final int ATTRIBUTECATEGORY = 21;
    public static final int ATTRIBUTE1 = 22;
    public static final int ATTRIBUTE2 = 23;
    public static final int ATTRIBUTE3 = 24;
    public static final int ATTRIBUTE4 = 25;
    public static final int ATTRIBUTE5 = 26;
    public static final int ATTRIBUTE6 = 27;
    public static final int ATTRIBUTE7 = 28;
    public static final int ATTRIBUTE8 = 29;
    public static final int ATTRIBUTE9 = 30;
    public static final int ATTRIBUTE10 = 31;

    /**This is the default constructor (do not remove)
     */
    public PayPlanHisVORowImpl() {
    }

    /**Gets CuxPoPayPlanHisTEO entity object.
     */
    public CuxPoPayPlanHisTEOImpl getCuxPoPayPlanHisTEO() {
        return (CuxPoPayPlanHisTEOImpl)getEntity(0);
    }

    /**Gets the attribute value for LINE_ID using the alias name LineId
     */
    public Number getLineId() {
        return (Number)getAttributeInternal(LINEID);
    }

    /**Sets <code>value</code> as attribute value for LINE_ID using the alias name LineId
     */
    public void setLineId(Number value) {
        setAttributeInternal(LINEID, value);
    }

    /**Gets the attribute value for LINE_NUM using the alias name LineNum
     */
    public Number getLineNum() {
        return (Number)getAttributeInternal(LINENUM);
    }

    /**Sets <code>value</code> as attribute value for LINE_NUM using the alias name LineNum
     */
    public void setLineNum(Number value) {
        setAttributeInternal(LINENUM, value);
    }

    /**Gets the attribute value for PO_HEADER_ID using the alias name PoHeaderId
     */
    public Number getPoHeaderId() {
        return (Number)getAttributeInternal(POHEADERID);
    }

    /**Sets <code>value</code> as attribute value for PO_HEADER_ID using the alias name PoHeaderId
     */
    public void setPoHeaderId(Number value) {
        setAttributeInternal(POHEADERID, value);
    }

    /**Gets the attribute value for VERSION_ID using the alias name VersionId
     */
    public Number getVersionId() {
        return (Number)getAttributeInternal(VERSIONID);
    }

    /**Sets <code>value</code> as attribute value for VERSION_ID using the alias name VersionId
     */
    public void setVersionId(Number value) {
        setAttributeInternal(VERSIONID, value);
    }

    /**Gets the attribute value for PAY_TYPE using the alias name PayType
     */
    public String getPayType() {
        return (String)getAttributeInternal(PAYTYPE);
    }

    /**Sets <code>value</code> as attribute value for PAY_TYPE using the alias name PayType
     */
    public void setPayType(String value) {
        setAttributeInternal(PAYTYPE, value);
    }

    /**Gets the attribute value for INIT_AMT using the alias name InitAmt
     */
    public Number getInitAmt() {
        return (Number)getAttributeInternal(INITAMT);
    }

    /**Sets <code>value</code> as attribute value for INIT_AMT using the alias name InitAmt
     */
    public void setInitAmt(Number value) {
        setAttributeInternal(INITAMT, value);
    }

    /**Gets the attribute value for INIT_PAY_DATE using the alias name InitPayDate
     */
    public Date getInitPayDate() {
        return (Date)getAttributeInternal(INITPAYDATE);
    }

    /**Sets <code>value</code> as attribute value for INIT_PAY_DATE using the alias name InitPayDate
     */
    public void setInitPayDate(Date value) {
        setAttributeInternal(INITPAYDATE, value);
    }

    /**Gets the attribute value for NEW_AMT using the alias name NewAmt
     */
    public Number getNewAmt() {
        return (Number)getAttributeInternal(NEWAMT);
    }

    /**Sets <code>value</code> as attribute value for NEW_AMT using the alias name NewAmt
     */
    public void setNewAmt(Number value) {
        setAttributeInternal(NEWAMT, value);
    }

    /**Gets the attribute value for NEW_PAY_DATE using the alias name NewPayDate
     */
    public Date getNewPayDate() {
        return (Date)getAttributeInternal(NEWPAYDATE);
    }

    /**Sets <code>value</code> as attribute value for NEW_PAY_DATE using the alias name NewPayDate
     */
    public void setNewPayDate(Date value) {
        setAttributeInternal(NEWPAYDATE, value);
    }

    /**Gets the attribute value for MEMO using the alias name Memo
     */
    public String getMemo() {
        return (String)getAttributeInternal(MEMO);
    }

    /**Sets <code>value</code> as attribute value for MEMO using the alias name Memo
     */
    public void setMemo(String value) {
        setAttributeInternal(MEMO, value);
    }

    /**Gets the attribute value for PAY_APPLY_AMT using the alias name PayApplyAmt
     */
    public Number getPayApplyAmt() {
        return (Number)getAttributeInternal(PAYAPPLYAMT);
    }

    /**Sets <code>value</code> as attribute value for PAY_APPLY_AMT using the alias name PayApplyAmt
     */
    public void setPayApplyAmt(Number value) {
        setAttributeInternal(PAYAPPLYAMT, value);
    }

    /**Gets the attribute value for PAY_APPLY_NUM using the alias name PayApplyNum
     */
    public String getPayApplyNum() {
        return (String)getAttributeInternal(PAYAPPLYNUM);
    }

    /**Sets <code>value</code> as attribute value for PAY_APPLY_NUM using the alias name PayApplyNum
     */
    public void setPayApplyNum(String value) {
        setAttributeInternal(PAYAPPLYNUM, value);
    }

    /**Gets the attribute value for PAY_APPLIER_NAME using the alias name PayApplierName
     */
    public String getPayApplierName() {
        return (String)getAttributeInternal(PAYAPPLIERNAME);
    }

    /**Sets <code>value</code> as attribute value for PAY_APPLIER_NAME using the alias name PayApplierName
     */
    public void setPayApplierName(String value) {
        setAttributeInternal(PAYAPPLIERNAME, value);
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

    /**Gets the attribute value for the calculated attribute Rowid1
     */
    public RowID getRowid1() {
        return (RowID)getAttributeInternal(ROWID1);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Rowid1
     */
    public void setRowid1(RowID value) {
        setAttributeInternal(ROWID1, value);
    }

    /**Gets the attribute value for PAY_APPLY_DATE using the alias name PayApplyDate
     */
    public Date getPayApplyDate() {
        return (Date)getAttributeInternal(PAYAPPLYDATE);
    }

    /**Sets <code>value</code> as attribute value for PAY_APPLY_DATE using the alias name PayApplyDate
     */
    public void setPayApplyDate(Date value) {
        setAttributeInternal(PAYAPPLYDATE, value);
    }

    /**Gets the attribute value for PAID_AMT using the alias name PaidAmt
     */
    public Number getPaidAmt() {
        return (Number)getAttributeInternal(PAIDAMT);
    }

    /**Sets <code>value</code> as attribute value for PAID_AMT using the alias name PaidAmt
     */
    public void setPaidAmt(Number value) {
        setAttributeInternal(PAIDAMT, value);
    }

    /**Gets the attribute value for ATTRIBUTE_CATEGORY using the alias name AttributeCategory
     */
    public String getAttributeCategory() {
        return (String)getAttributeInternal(ATTRIBUTECATEGORY);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE_CATEGORY using the alias name AttributeCategory
     */
    public void setAttributeCategory(String value) {
        setAttributeInternal(ATTRIBUTECATEGORY, value);
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

    /**Gets the attribute value for ATTRIBUTE10 using the alias name Attribute10
     */
    public String getAttribute10() {
        return (String)getAttributeInternal(ATTRIBUTE10);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE10 using the alias name Attribute10
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
        case PAYAPPLIERNAME:
            return getPayApplierName();
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
        case ROWID1:
            return getRowid1();
        case PAYAPPLYDATE:
            return getPayApplyDate();
        case PAIDAMT:
            return getPaidAmt();
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
        case PAYAPPLIERNAME:
            setPayApplierName((String)value);
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
        case ROWID1:
            setRowid1((RowID)value);
            return;
        case PAYAPPLYDATE:
            setPayApplyDate((Date)value);
            return;
        case PAIDAMT:
            setPaidAmt((Number)value);
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
}
