package cux.oracle.apps.po.deliver.server;

import java.sql.CallableStatement;
import java.sql.Types;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

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
public class CuxPoDeliStatusTEOImpl extends OAEntityImpl {
    public static final int DELIID = 0;
    public static final int POHEADERID = 1;
    public static final int UNIQSEQ = 2;
    public static final int ORDERSEQ = 3;
    public static final int DELINAME = 4;
    public static final int ENABLEFLAG = 5;
    public static final int STATUS = 6;
    public static final int CREATEDBY = 7;
    public static final int CREATIONDATE = 8;
    public static final int LASTUPDATEDBY = 9;
    public static final int LASTUPDATEDATE = 10;
    public static final int LASTUPDATELOGIN = 11;
    public static final int ATTRIBUTECATEGORYCODE = 12;
    public static final int ATTRIBUTE1 = 13;
    public static final int ATTRIBUTE2 = 14;
    public static final int ATTRIBUTE3 = 15;
    public static final int ATTRIBUTE4 = 16;
    public static final int ATTRIBUTE5 = 17;
    public static final int ATTRIBUTE6 = 18;
    public static final int ATTRIBUTE7 = 19;
    public static final int ATTRIBUTE8 = 20;
    public static final int ATTRIBUTE9 = 21;
    public static final int ATTRIBUTE10 = 22;
    public static final int ATTRIBUTE11 = 23;
    public static final int ATTRIBUTE12 = 24;
    public static final int ATTRIBUTE13 = 25;
    public static final int ATTRIBUTE14 = 26;
    public static final int ATTRIBUTE15 = 27;
    public static final int ATTRIBUTE16 = 28;
    public static final int ATTRIBUTE17 = 29;
    public static final int ATTRIBUTE26 = 30;
    public static final int ATTRIBUTE27 = 31;
    public static final int ATTRIBUTE28 = 32;
    public static final int ATTRIBUTE29 = 33;
    public static final int ATTRIBUTE30 = 34;
    public static final int ATTRIBUTE18 = 35;
    public static final int ATTRIBUTE19 = 36;
    public static final int ATTRIBUTE20 = 37;
    public static final int ATTRIBUTE21 = 38;
    public static final int ATTRIBUTE22 = 39;
    public static final int ATTRIBUTE23 = 40;
    public static final int ATTRIBUTE24 = 41;
    public static final int ATTRIBUTE25 = 42;
    public static final int ONDUTYPERSON = 43;
    public static final int REMARK = 44;


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public CuxPoDeliStatusTEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("cux.oracle.apps.po.deliver.server.CuxPoDeliStatusTEO");
        }
        return mDefinitionObject;
    }

    /**Gets the attribute value for DeliId, using the alias name DeliId
     */
    public Number getDeliId() {
        return (Number)getAttributeInternal(DELIID);
    }

    /**Sets <code>value</code> as the attribute value for DeliId
     */
    public void setDeliId(Number value) {
        setAttributeInternal(DELIID, value);
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

    /**Gets the attribute value for UniqSeq, using the alias name UniqSeq
     */
    public Number getUniqSeq() {
        return (Number)getAttributeInternal(UNIQSEQ);
    }

    /**Sets <code>value</code> as the attribute value for UniqSeq
     */
    public void setUniqSeq(Number value) {
        setAttributeInternal(UNIQSEQ, value);
    }

    /**Gets the attribute value for OrderSeq, using the alias name OrderSeq
     */
    public Number getOrderSeq() {
        return (Number)getAttributeInternal(ORDERSEQ);
    }

    /**Sets <code>value</code> as the attribute value for OrderSeq
     */
    public void setOrderSeq(Number value) {
        setAttributeInternal(ORDERSEQ, value);
    }

    /**Gets the attribute value for DeliName, using the alias name DeliName
     */
    public String getDeliName() {
        return (String)getAttributeInternal(DELINAME);
    }

    /**Sets <code>value</code> as the attribute value for DeliName
     */
    public void setDeliName(String value) {
        setAttributeInternal(DELINAME, value);
    }

    /**Gets the attribute value for EnableFlag, using the alias name EnableFlag
     */
    public String getEnableFlag() {
        return (String)getAttributeInternal(ENABLEFLAG);
    }

    /**Sets <code>value</code> as the attribute value for EnableFlag
     */
    public void setEnableFlag(String value) {
        setAttributeInternal(ENABLEFLAG, value);
    }

    /**Gets the attribute value for Status, using the alias name Status
     */
    public String getStatus() {
        return (String)getAttributeInternal(STATUS);
    }

    /**Sets <code>value</code> as the attribute value for Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
        this.callWhenSetStatus(this.getDeliId(), this.getPoHeaderId(), 
                               this.getStatus(), this.getUniqSeq());
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

    /**Gets the attribute value for AttributeCategoryCode, using the alias name AttributeCategoryCode
     */
    public String getAttributeCategoryCode() {
        return (String)getAttributeInternal(ATTRIBUTECATEGORYCODE);
    }

    /**Sets <code>value</code> as the attribute value for AttributeCategoryCode
     */
    public void setAttributeCategoryCode(String value) {
        setAttributeInternal(ATTRIBUTECATEGORYCODE, value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
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
        //        this.callWhenSetAttribute(this.getDeliId(), this.getPoHeaderId(), 
        //                                  this.getStatus(), this.getUniqSeq(), value);
    }

    /**Gets the attribute value for Attribute11, using the alias name Attribute11
     */
    public String getAttribute11() {
        return (String)getAttributeInternal(ATTRIBUTE11);
    }

    /**Sets <code>value</code> as the attribute value for Attribute11
     */
    public void setAttribute11(String value) {
        setAttributeInternal(ATTRIBUTE11, value);
    }

    /**Gets the attribute value for Attribute12, using the alias name Attribute12
     */
    public String getAttribute12() {
        return (String)getAttributeInternal(ATTRIBUTE12);
    }

    /**Sets <code>value</code> as the attribute value for Attribute12
     */
    public void setAttribute12(String value) {
        setAttributeInternal(ATTRIBUTE12, value);
    }

    /**Gets the attribute value for Attribute13, using the alias name Attribute13
     */
    public String getAttribute13() {
        return (String)getAttributeInternal(ATTRIBUTE13);
    }

    /**Sets <code>value</code> as the attribute value for Attribute13
     */
    public void setAttribute13(String value) {
        setAttributeInternal(ATTRIBUTE13, value);
    }

    /**Gets the attribute value for Attribute14, using the alias name Attribute14
     */
    public String getAttribute14() {
        return (String)getAttributeInternal(ATTRIBUTE14);
    }

    /**Sets <code>value</code> as the attribute value for Attribute14
     */
    public void setAttribute14(String value) {
        setAttributeInternal(ATTRIBUTE14, value);
    }

    /**Gets the attribute value for Attribute15, using the alias name Attribute15
     */
    public String getAttribute15() {
        return (String)getAttributeInternal(ATTRIBUTE15);
    }

    /**Sets <code>value</code> as the attribute value for Attribute15
     */
    public void setAttribute15(String value) {
        setAttributeInternal(ATTRIBUTE15, value);
    }

    /**Gets the attribute value for Attribute16, using the alias name Attribute16
     */
    public String getAttribute16() {
        return (String)getAttributeInternal(ATTRIBUTE16);
    }

    /**Sets <code>value</code> as the attribute value for Attribute16
     */
    public void setAttribute16(String value) {
        setAttributeInternal(ATTRIBUTE16, value);
    }

    /**Gets the attribute value for Attribute17, using the alias name Attribute17
     */
    public String getAttribute17() {
        return (String)getAttributeInternal(ATTRIBUTE17);
    }

    /**Sets <code>value</code> as the attribute value for Attribute17
     */
    public void setAttribute17(String value) {
        setAttributeInternal(ATTRIBUTE17, value);
    }

    /**Gets the attribute value for Attribute26, using the alias name Attribute26
     */
    public String getAttribute26() {
        return (String)getAttributeInternal(ATTRIBUTE26);
    }

    /**Sets <code>value</code> as the attribute value for Attribute26
     */
    public void setAttribute26(String value) {
        setAttributeInternal(ATTRIBUTE26, value);
    }

    /**Gets the attribute value for Attribute27, using the alias name Attribute27
     */
    public String getAttribute27() {
        return (String)getAttributeInternal(ATTRIBUTE27);
    }

    /**Sets <code>value</code> as the attribute value for Attribute27
     */
    public void setAttribute27(String value) {
        setAttributeInternal(ATTRIBUTE27, value);
    }

    /**Gets the attribute value for Attribute28, using the alias name Attribute28
     */
    public String getAttribute28() {
        return (String)getAttributeInternal(ATTRIBUTE28);
    }

    /**Sets <code>value</code> as the attribute value for Attribute28
     */
    public void setAttribute28(String value) {
        setAttributeInternal(ATTRIBUTE28, value);
    }

    /**Gets the attribute value for Attribute29, using the alias name Attribute29
     */
    public String getAttribute29() {
        return (String)getAttributeInternal(ATTRIBUTE29);
    }

    /**Sets <code>value</code> as the attribute value for Attribute29
     */
    public void setAttribute29(String value) {
        setAttributeInternal(ATTRIBUTE29, value);
    }

    /**Gets the attribute value for Attribute30, using the alias name Attribute30
     */
    public String getAttribute30() {
        return (String)getAttributeInternal(ATTRIBUTE30);
    }

    /**Sets <code>value</code> as the attribute value for Attribute30
     */
    public void setAttribute30(String value) {
        setAttributeInternal(ATTRIBUTE30, value);
    }

    /**Gets the attribute value for Attribute18, using the alias name Attribute18
     */
    public String getAttribute18() {
        return (String)getAttributeInternal(ATTRIBUTE18);
    }

    /**Sets <code>value</code> as the attribute value for Attribute18
     */
    public void setAttribute18(String value) {
        setAttributeInternal(ATTRIBUTE18, value);
    }

    /**Gets the attribute value for Attribute19, using the alias name Attribute19
     */
    public String getAttribute19() {
        return (String)getAttributeInternal(ATTRIBUTE19);
    }

    /**Sets <code>value</code> as the attribute value for Attribute19
     */
    public void setAttribute19(String value) {
        setAttributeInternal(ATTRIBUTE19, value);
    }

    /**Gets the attribute value for Attribute20, using the alias name Attribute20
     */
    public String getAttribute20() {
        return (String)getAttributeInternal(ATTRIBUTE20);
    }

    /**Sets <code>value</code> as the attribute value for Attribute20
     */
    public void setAttribute20(String value) {
        setAttributeInternal(ATTRIBUTE20, value);
    }

    /**Gets the attribute value for Attribute21, using the alias name Attribute21
     */
    public String getAttribute21() {
        return (String)getAttributeInternal(ATTRIBUTE21);
    }

    /**Sets <code>value</code> as the attribute value for Attribute21
     */
    public void setAttribute21(String value) {
        setAttributeInternal(ATTRIBUTE21, value);
    }

    /**Gets the attribute value for Attribute22, using the alias name Attribute22
     */
    public String getAttribute22() {
        return (String)getAttributeInternal(ATTRIBUTE22);
    }

    /**Sets <code>value</code> as the attribute value for Attribute22
     */
    public void setAttribute22(String value) {
        setAttributeInternal(ATTRIBUTE22, value);
    }

    /**Gets the attribute value for Attribute23, using the alias name Attribute23
     */
    public String getAttribute23() {
        return (String)getAttributeInternal(ATTRIBUTE23);
    }

    /**Sets <code>value</code> as the attribute value for Attribute23
     */
    public void setAttribute23(String value) {
        setAttributeInternal(ATTRIBUTE23, value);
    }

    /**Gets the attribute value for Attribute24, using the alias name Attribute24
     */
    public String getAttribute24() {
        return (String)getAttributeInternal(ATTRIBUTE24);
    }

    /**Sets <code>value</code> as the attribute value for Attribute24
     */
    public void setAttribute24(String value) {
        setAttributeInternal(ATTRIBUTE24, value);
    }

    /**Gets the attribute value for Attribute25, using the alias name Attribute25
     */
    public String getAttribute25() {
        return (String)getAttributeInternal(ATTRIBUTE25);
    }

    /**Sets <code>value</code> as the attribute value for Attribute25
     */
    public void setAttribute25(String value) {
        setAttributeInternal(ATTRIBUTE25, value);
    }

    /**Gets the attribute value for OnDutyPerson, using the alias name OnDutyPerson
     */
    public Number getOnDutyPerson() {
        return (Number)getAttributeInternal(ONDUTYPERSON);
    }

    /**Sets <code>value</code> as the attribute value for OnDutyPerson
     */
    public void setOnDutyPerson(Number value) {
        setAttributeInternal(ONDUTYPERSON, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case DELIID:
            return getDeliId();
        case POHEADERID:
            return getPoHeaderId();
        case UNIQSEQ:
            return getUniqSeq();
        case ORDERSEQ:
            return getOrderSeq();
        case DELINAME:
            return getDeliName();
        case ENABLEFLAG:
            return getEnableFlag();
        case STATUS:
            return getStatus();
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
        case ATTRIBUTECATEGORYCODE:
            return getAttributeCategoryCode();
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
        case ATTRIBUTE11:
            return getAttribute11();
        case ATTRIBUTE12:
            return getAttribute12();
        case ATTRIBUTE13:
            return getAttribute13();
        case ATTRIBUTE14:
            return getAttribute14();
        case ATTRIBUTE15:
            return getAttribute15();
        case ATTRIBUTE16:
            return getAttribute16();
        case ATTRIBUTE17:
            return getAttribute17();
        case ATTRIBUTE26:
            return getAttribute26();
        case ATTRIBUTE27:
            return getAttribute27();
        case ATTRIBUTE28:
            return getAttribute28();
        case ATTRIBUTE29:
            return getAttribute29();
        case ATTRIBUTE30:
            return getAttribute30();
        case ATTRIBUTE18:
            return getAttribute18();
        case ATTRIBUTE19:
            return getAttribute19();
        case ATTRIBUTE20:
            return getAttribute20();
        case ATTRIBUTE21:
            return getAttribute21();
        case ATTRIBUTE22:
            return getAttribute22();
        case ATTRIBUTE23:
            return getAttribute23();
        case ATTRIBUTE24:
            return getAttribute24();
        case ATTRIBUTE25:
            return getAttribute25();
        case ONDUTYPERSON:
            return getOnDutyPerson();
        case REMARK:
            return getRemark();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case DELIID:
            setDeliId((Number)value);
            return;
        case POHEADERID:
            setPoHeaderId((Number)value);
            return;
        case UNIQSEQ:
            setUniqSeq((Number)value);
            return;
        case ORDERSEQ:
            setOrderSeq((Number)value);
            return;
        case DELINAME:
            setDeliName((String)value);
            return;
        case ENABLEFLAG:
            setEnableFlag((String)value);
            return;
        case STATUS:
            setStatus((String)value);
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
        case ATTRIBUTECATEGORYCODE:
            setAttributeCategoryCode((String)value);
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
        case ATTRIBUTE11:
            setAttribute11((String)value);
            return;
        case ATTRIBUTE12:
            setAttribute12((String)value);
            return;
        case ATTRIBUTE13:
            setAttribute13((String)value);
            return;
        case ATTRIBUTE14:
            setAttribute14((String)value);
            return;
        case ATTRIBUTE15:
            setAttribute15((String)value);
            return;
        case ATTRIBUTE16:
            setAttribute16((String)value);
            return;
        case ATTRIBUTE17:
            setAttribute17((String)value);
            return;
        case ATTRIBUTE26:
            setAttribute26((String)value);
            return;
        case ATTRIBUTE27:
            setAttribute27((String)value);
            return;
        case ATTRIBUTE28:
            setAttribute28((String)value);
            return;
        case ATTRIBUTE29:
            setAttribute29((String)value);
            return;
        case ATTRIBUTE30:
            setAttribute30((String)value);
            return;
        case ATTRIBUTE18:
            setAttribute18((String)value);
            return;
        case ATTRIBUTE19:
            setAttribute19((String)value);
            return;
        case ATTRIBUTE20:
            setAttribute20((String)value);
            return;
        case ATTRIBUTE21:
            setAttribute21((String)value);
            return;
        case ATTRIBUTE22:
            setAttribute22((String)value);
            return;
        case ATTRIBUTE23:
            setAttribute23((String)value);
            return;
        case ATTRIBUTE24:
            setAttribute24((String)value);
            return;
        case ATTRIBUTE25:
            setAttribute25((String)value);
            return;
        case ONDUTYPERSON:
            setOnDutyPerson((Number)value);
            return;
        case REMARK:
            setRemark((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for Remark, using the alias name Remark
     */
    public String getRemark() {
        return (String)getAttributeInternal(REMARK);
    }

    /**Sets <code>value</code> as the attribute value for Remark
     */
    public void setRemark(String value) {
        setAttributeInternal(REMARK, value);
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number deliId) {
        return new Key(new Object[] { deliId });
    }

    /**
     * 
     * @param deliId
     * @param poHeaderId
     * @param pStauts
     * @param uniqId
     */
    public void callWhenSetStatus(Number deliId, Number poHeaderId, 
                                  String pStauts, Number uniqId) {
        String retValue = null;
        OADBTransaction tx = this.getOADBTransaction();
        //        this.getOADBTransaction().putDialogMessage(new OAException("callWhenSetStatus",OAException.INFORMATION));
        //        this.getOADBTransaction().putDialogMessage(new OAException("deliId->"+deliId,OAException.INFORMATION));
        //
        //        this.getOADBTransaction().putDialogMessage(new OAException("poHeaderId->"+poHeaderId,OAException.INFORMATION));
        //
        //        this.getOADBTransaction().putDialogMessage(new OAException("pStauts->"+pStauts,OAException.INFORMATION));
        //        this.getOADBTransaction().putDialogMessage(new OAException("uniqId->"+uniqId,OAException.INFORMATION));

        CallableStatement cs = 
            tx.createCallableStatement("begin\n" + "  cux_po_deliver_pkg.update_equip_progress(p_deli_id => :1,\n" + 
                                       "                                           p_po_header_id => :2,\n" + 
                                       "                                           p_new_status => :3,\n" + 
                                       "                                           p_unique_id => :4);\n" + 
                                       "end;", 1);
        try {
            cs.setInt(1, deliId.intValue());
            cs.setInt(2, poHeaderId.intValue());
            cs.setString(3, pStauts);
            cs.setInt(4, uniqId.intValue());
            cs.execute();

            //                        this.getOADBTransaction().putDialogMessage(new OAException("execute->complete",OAException.INFORMATION));
        } catch (Exception e) {
            //            this.getOADBTransaction().putDialogMessage(new OAException("e->"+e,OAException.INFORMATION));

            //            this.getOADBTransaction().putDialogMessage(new OAException("e->"+e.getStackTrace(),OAException.INFORMATION));
            e.printStackTrace();
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //    /**
    //     * 
    //     * @param deliId
    //     * @param poHeaderId
    //     * @param pStauts
    //     * @param uniqId
    //     */
    //    public void callWhenSetAttribute(Number deliId, Number poHeaderId, 
    //                                     String pStauts, Number uniqId, 
    //                                     String attirbute) {
    //        String retValue = null;
    //        OADBTransaction tx = this.getOADBTransaction();
    //        CallableStatement cs = 
    //            tx.createCallableStatement("begin\n" + "  cux_po_deliver_pkg.update_construnt_progress(p_deli_id => :1,\n" + 
    //                                       "                                               p_po_header_id => :2,\n" + 
    //                                       "                                               p_new_status => :3,\n" + 
    //                                       "                                               p_unique_id => :4,\n" + 
    //                                       "                                               p_percent => :5);\n" + 
    //                                       "end;", 1);
    //        try {
    //            cs.setInt(1, deliId.intValue());
    //            cs.setInt(2, poHeaderId.intValue());
    //            cs.setString(3, pStauts);
    //            cs.setInt(4, uniqId.intValue());
    //            cs.setString(5, attirbute);
    //            cs.execute();
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        if (cs != null) {
    //            try {
    //                cs.close();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    }
}
