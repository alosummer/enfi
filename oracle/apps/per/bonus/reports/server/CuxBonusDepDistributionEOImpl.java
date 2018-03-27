/*     */package cux.oracle.apps.per.bonus.reports.server;
/*     */
/*     */import oracle.apps.fnd.framework.server.OAEntityDefImpl;
/*     */
import oracle.apps.fnd.framework.server.OAEntityImpl;
/*     */
import oracle.jbo.AttributeList;
/*     */
import oracle.jbo.Key;
/*     */
import oracle.jbo.domain.Date;
/*     */
import oracle.jbo.domain.Number;
/*     */
import oracle.jbo.server.AttributeDefImpl;
/*     */
import oracle.jbo.server.EntityDefImpl;
/*     */
/*     */public class CuxBonusDepDistributionEOImpl extends OAEntityImpl {
    /*     */
    public static final int DEPDISTRIBUTIONID = 0;
    /*     */
    public static final int DEPID = 1;
    /*     */
    public static final int DEPNAME = 2;
    /*     */
    public static final int TYPEID = 3;
    /*     */
    public static final int DISTRIBUTIONPERIOD = 4;
    /*     */
    public static final int DISTRIBUTIONAMOUNT = 5;
    /*     */
    public static final int NOTE = 6;
    /*     */
    public static final int DISTRIBUTIONSTATUS = 7;
    /*     */
    public static final int LASTUPDATEDATE = 8;
    /*     */
    public static final int LASTUPDATEDBY = 9;
    /*     */
    public static final int CREATIONDATE = 10;
    /*     */
    public static final int CREATEDBY = 11;
    /*     */
    public static final int ATTRIBUTE1 = 12;
    /*     */
    public static final int ATTRIBUTE2 = 13;
    /*     */
    public static final int ATTRIBUTE3 = 14;
    /*     */
    public static final int ATTRIBUTE4 = 15;
    /*     */
    public static final int ATTRIBUTE5 = 16;
    /*     */
    public static final int ATTRIBUTE6 = 17;
    /*     */
    public static final int ATTRIBUTE7 = 18;
    /*     */
    public static final int ATTRIBUTE8 = 19;
    /*     */
    public static final int ATTRIBUTE9 = 20;
    /*     */
    public static final int ATTRIBUTE10 = 21;
    /*     */
    public static final int ATTRIBUTE11 = 22;
    /*     */
    public static final int ATTRIBUTE12 = 23;
    /*     */
    public static final int ATTRIBUTE13 = 24;
    /*     */
    public static final int ATTRIBUTE14 = 25;
    /*     */
    public static final int ATTRIBUTE15 = 26;
    /*     */
    private static OAEntityDefImpl mDefinitionObject;
    /*     */
    /*     */

    public static synchronized EntityDefImpl getDefinitionObject() {
        /*  55 */if (mDefinitionObject == null) {
            /*  56 */mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("cux.oracle.apps.per.bonus.reports.server.CuxBonusDepDistributionEO");
            /*     */
        }
        /*     */
        /*  60 */return mDefinitionObject;
        /*     */
    }
    /*     */
    /*     */

    public void create(AttributeList attributeList) {
        /*  65 */super.create(attributeList);
        /*     */
    }
    /*     */
    /*     */

    public void remove() {
        /*  71 */super.remove();
        /*     */
    }
    /*     */
    /*     */

    protected void validateEntity() {
        /*  77 */super.validateEntity();
        /*     */
    }
    /*     */
    /*     */

    public Number getDepDistributionId() {
        /*  84 */return (Number)getAttributeInternal(0);
        /*     */
    }
    /*     */
    /*     */

    public void setDepDistributionId(Number value) {
        /*  89 */setAttributeInternal(0, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDepId() {
        /*  96 */return (Number)getAttributeInternal(1);
        /*     */
    }
    /*     */
    /*     */

    public void setDepId(Number value) {
        /* 101 */setAttributeInternal(1, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDepName() {
        /* 108 */return (String)getAttributeInternal(2);
        /*     */
    }
    /*     */
    /*     */

    public void setDepName(String value) {
        /* 113 */setAttributeInternal(2, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getTypeId() {
        /* 120 */return (Number)getAttributeInternal(3);
        /*     */
    }
    /*     */
    /*     */

    public void setTypeId(Number value) {
        /* 125 */setAttributeInternal(3, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionPeriod() {
        /* 132 */return (Number)getAttributeInternal(4);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionPeriod(Number value) {
        /* 137 */setAttributeInternal(4, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionAmount() {
        /* 144 */return (Number)getAttributeInternal(5);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionAmount(Number value) {
        /* 149 */setAttributeInternal(5, value);
        /*     */
    }
    /*     */
    /*     */

    public String getNote() {
        /* 156 */return (String)getAttributeInternal(6);
        /*     */
    }
    /*     */
    /*     */

    public void setNote(String value) {
        /* 161 */setAttributeInternal(6, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDistributionStatus() {
        /* 168 */return (String)getAttributeInternal(7);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionStatus(String value) {
        /* 173 */setAttributeInternal(7, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getLastUpdateDate() {
        /* 180 */return (Date)getAttributeInternal(8);
        /*     */
    }
    /*     */
    /*     */

    public void setLastUpdateDate(Date value) {
        /* 185 */setAttributeInternal(8, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getLastUpdatedBy() {
        /* 192 */return (Number)getAttributeInternal(9);
        /*     */
    }
    /*     */
    /*     */

    public void setLastUpdatedBy(Number value) {
        /* 197 */setAttributeInternal(9, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getCreationDate() {
        /* 204 */return (Date)getAttributeInternal(10);
        /*     */
    }
    /*     */
    /*     */

    public void setCreationDate(Date value) {
        /* 209 */setAttributeInternal(10, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getCreatedBy() {
        /* 216 */return (Number)getAttributeInternal(11);
        /*     */
    }
    /*     */
    /*     */

    public void setCreatedBy(Number value) {
        /* 221 */setAttributeInternal(11, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute1() {
        /* 228 */return (String)getAttributeInternal(12);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute1(String value) {
        /* 233 */setAttributeInternal(12, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute2() {
        /* 240 */return (String)getAttributeInternal(13);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute2(String value) {
        /* 245 */setAttributeInternal(13, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute3() {
        /* 252 */return (String)getAttributeInternal(14);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute3(String value) {
        /* 257 */setAttributeInternal(14, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute4() {
        /* 264 */return (String)getAttributeInternal(15);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute4(String value) {
        /* 269 */setAttributeInternal(15, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute5() {
        /* 276 */return (String)getAttributeInternal(16);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute5(String value) {
        /* 281 */setAttributeInternal(16, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute6() {
        /* 288 */return (String)getAttributeInternal(17);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute6(String value) {
        /* 293 */setAttributeInternal(17, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute7() {
        /* 300 */return (String)getAttributeInternal(18);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute7(String value) {
        /* 305 */setAttributeInternal(18, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute8() {
        /* 312 */return (String)getAttributeInternal(19);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute8(String value) {
        /* 317 */setAttributeInternal(19, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute9() {
        /* 324 */return (String)getAttributeInternal(20);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute9(String value) {
        /* 329 */setAttributeInternal(20, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getAttribute10() {
        /* 336 */return (Number)getAttributeInternal(21);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute10(Number value) {
        /* 341 */setAttributeInternal(21, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getAttribute11() {
        /* 348 */return (Number)getAttributeInternal(22);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute11(Number value) {
        /* 353 */setAttributeInternal(22, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getAttribute12() {
        /* 360 */return (Number)getAttributeInternal(23);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute12(Number value) {
        /* 365 */setAttributeInternal(23, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getAttribute13() {
        /* 372 */return (Number)getAttributeInternal(24);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute13(Number value) {
        /* 377 */setAttributeInternal(24, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getAttribute14() {
        /* 384 */return (Date)getAttributeInternal(25);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute14(Date value) {
        /* 389 */setAttributeInternal(25, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getAttribute15() {
        /* 396 */return (Date)getAttributeInternal(26);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute15(Date value) {
        /* 401 */setAttributeInternal(26, value);
        /*     */
    }
    /*     */
    /*     */

    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 408 */switch (index)
        /*     */ {
            /*     */case 0:
            /* 466 */return getDepDistributionId();
            /*     */case 1:
            /* 466 */return getDepId();
            /*     */case 2:
            /* 466 */return getDepName();
            /*     */case 3:
            /* 466 */return getTypeId();
            /*     */case 4:
            /* 466 */return getDistributionPeriod();
            /*     */case 5:
            /* 466 */return getDistributionAmount();
            /*     */case 6:
            /* 466 */return getNote();
            /*     */case 7:
            /* 466 */return getDistributionStatus();
            /*     */case 8:
            /* 466 */return getLastUpdateDate();
            /*     */case 9:
            /* 466 */return getLastUpdatedBy();
            /*     */case 10:
            /* 466 */return getCreationDate();
            /*     */case 11:
            /* 466 */return getCreatedBy();
            /*     */case 12:
            /* 466 */return getAttribute1();
            /*     */case 13:
            /* 466 */return getAttribute2();
            /*     */case 14:
            /* 466 */return getAttribute3();
            /*     */case 15:
            /* 466 */return getAttribute4();
            /*     */case 16:
            /* 466 */return getAttribute5();
            /*     */case 17:
            /* 466 */return getAttribute6();
            /*     */case 18:
            /* 466 */return getAttribute7();
            /*     */case 19:
            /* 466 */return getAttribute8();
            /*     */case 20:
            /* 466 */return getAttribute9();
            /*     */case 21:
            /* 466 */return getAttribute10();
            /*     */case 22:
            /* 466 */return getAttribute11();
            /*     */case 23:
            /* 466 */return getAttribute12();
            /*     */case 24:
            /* 466 */return getAttribute13();
            /*     */case 25:
            /* 466 */return getAttribute14();
            /*     */case 26:
            /* 466 */return getAttribute15();
            /*     */
        }
        /*     */
        /* 466 */return super.getAttrInvokeAccessor(index, attrDef);
        /*     */
    }
    /*     */
    /*     */

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 472 */switch (index) {
        case 0:
            /* 474 */setDepDistributionId((Number)value);
            /*     */return;
            /*     */case 1:
            /* 477 */setDepId((Number)value);
            /*     */return;
            /*     */case 2:
            /* 480 */setDepName((String)value);
            /*     */return;
            /*     */case 3:
            /* 483 */setTypeId((Number)value);
            /*     */return;
            /*     */case 4:
            /* 486 */setDistributionPeriod((Number)value);
            /*     */return;
            /*     */case 5:
            /* 489 */setDistributionAmount((Number)value);
            /*     */return;
            /*     */case 6:
            /* 492 */setNote((String)value);
            /*     */return;
            /*     */case 7:
            /* 495 */setDistributionStatus((String)value);
            /*     */return;
            /*     */case 8:
            /* 498 */setLastUpdateDate((Date)value);
            /*     */return;
            /*     */case 9:
            /* 501 */setLastUpdatedBy((Number)value);
            /*     */return;
            /*     */case 10:
            /* 504 */setCreationDate((Date)value);
            /*     */return;
            /*     */case 11:
            /* 507 */setCreatedBy((Number)value);
            /*     */return;
            /*     */case 12:
            /* 510 */setAttribute1((String)value);
            /*     */return;
            /*     */case 13:
            /* 513 */setAttribute2((String)value);
            /*     */return;
            /*     */case 14:
            /* 516 */setAttribute3((String)value);
            /*     */return;
            /*     */case 15:
            /* 519 */setAttribute4((String)value);
            /*     */return;
            /*     */case 16:
            /* 522 */setAttribute5((String)value);
            /*     */return;
            /*     */case 17:
            /* 525 */setAttribute6((String)value);
            /*     */return;
            /*     */case 18:
            /* 528 */setAttribute7((String)value);
            /*     */return;
            /*     */case 19:
            /* 531 */setAttribute8((String)value);
            /*     */return;
            /*     */case 20:
            /* 534 */setAttribute9((String)value);
            /*     */return;
            /*     */case 21:
            /* 537 */setAttribute10((Number)value);
            /*     */return;
            /*     */case 22:
            /* 540 */setAttribute11((Number)value);
            /*     */return;
            /*     */case 23:
            /* 543 */setAttribute12((Number)value);
            /*     */return;
            /*     */case 24:
            /* 546 */setAttribute13((Number)value);
            /*     */return;
            /*     */case 25:
            /* 549 */setAttribute14((Date)value);
            /*     */return;
            /*     */case 26:
            /* 552 */setAttribute15((Date)value);
            /*     */return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
        /*     */
    }
    /*     */
    /*     */

    public void setLastUpdateLogin(Number n) {
        /*     */
    }
    /*     */
    /*     */

    public static Key createPrimaryKey(Number depDistributionId) {
        /* 568 */return new Key(new Object[] { depDistributionId });
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\reports\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.reports.server.CuxBonusDepDistributionEOImpl
 * JD-Core Version:    0.6.1
 */