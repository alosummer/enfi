/*     */package cux.oracle.apps.per.bonus.member.server;
/*     */
/*     */import oracle.apps.fnd.framework.server.OAViewRowImpl;
/*     */
import oracle.jbo.domain.Date;
/*     */
import oracle.jbo.domain.Number;
/*     */
import oracle.jbo.server.AttributeDefImpl;
/*     */
/*     */public class SpecialAwardsPersonVORowImpl extends OAViewRowImpl {
    /*     */
    public static final int PERSONDISTRIBUTIONID = 0;
    /*     */
    public static final int EMPLOYEENUMBER = 1;
    /*     */
    public static final int LASTNAME = 2;
    /*     */
    public static final int LOTID = 3;
    /*     */
    public static final int PERSONID = 4;
    /*     */
    public static final int TYPENAME = 5;
    /*     */
    public static final int TYPEID = 6;
    /*     */
    public static final int DISTRIBUTIONDEPID = 7;
    /*     */
    public static final int DISTRIBUTIONDEPNAME = 8;
    /*     */
    public static final int DEPID = 9;
    /*     */
    public static final int DEPNAME = 10;
    /*     */
    public static final int DISTRIBUTIONSTATUS = 11;
    /*     */
    public static final int SUBMITFLG = 12;
    /*     */
    public static final int IMPORTFLAG = 13;
    /*     */
    public static final int IMPORTSTATUS = 14;
    /*     */
    public static final int IMPORTERROR = 15;
    /*     */
    public static final int DISTRIBUTIONDATE = 16;
    /*     */
    public static final int DISTRIBUTIONAMOUNT = 17;
    /*     */
    public static final int PROJECTID = 18;
    /*     */
    public static final int NOTE = 19;
    /*     */
    public static final int APPROVEDNOTE = 20;
    /*     */
    public static final int LASTUPDATEDATE = 21;
    /*     */
    public static final int LASTUPDATEDBY = 22;
    /*     */
    public static final int CREATIONDATE = 23;
    /*     */
    public static final int CREATEDBY = 24;
    /*     */
    public static final int ATTRIBUTE1 = 25;
    /*     */
    public static final int ATTRIBUTE2 = 26;
    /*     */
    public static final int ATTRIBUTE3 = 27;
    /*     */
    public static final int ATTRIBUTE4 = 28;
    /*     */
    public static final int ATTRIBUTE5 = 29;
    /*     */
    public static final int ATTRIBUTE6 = 30;
    /*     */
    public static final int ATTRIBUTE7 = 31;
    /*     */
    public static final int ATTRIBUTE8 = 32;
    /*     */
    public static final int ATTRIBUTE9 = 33;
    /*     */
    public static final int ATTRIBUTE10 = 34;
    /*     */
    public static final int ATTRIBUTE11 = 35;
    /*     */
    public static final int ATTRIBUTE12 = 36;
    /*     */
    public static final int ATTRIBUTE13 = 37;
    /*     */
    public static final int ATTRIBUTE14 = 38;
    /*     */
    public static final int ATTRIBUTE15 = 39;
    /*     */
    /*     */

    public Number getPersonDistributionId() {
        /*  64 */return (Number)getAttributeInternal(0);
        /*     */
    }
    /*     */
    /*     */

    public void setPersonDistributionId(Number value) {
        /*  69 */setAttributeInternal(0, value);
        /*     */
    }
    /*     */
    /*     */

    public String getEmployeeNumber() {
        /*  76 */return (String)getAttributeInternal(1);
        /*     */
    }
    /*     */
    /*     */

    public void setEmployeeNumber(String value) {
        /*  81 */setAttributeInternal(1, value);
        /*     */
    }
    /*     */
    /*     */

    public String getLastName() {
        /*  88 */return (String)getAttributeInternal(2);
        /*     */
    }
    /*     */
    /*     */

    public void setLastName(String value) {
        /*  93 */setAttributeInternal(2, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getLotId() {
        /* 100 */return (Number)getAttributeInternal(3);
        /*     */
    }
    /*     */
    /*     */

    public void setLotId(Number value) {
        /* 105 */setAttributeInternal(3, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getPersonId() {
        /* 112 */return (Number)getAttributeInternal(4);
        /*     */
    }
    /*     */
    /*     */

    public void setPersonId(Number value) {
        /* 117 */setAttributeInternal(4, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getTypeId() {
        /* 124 */return (Number)getAttributeInternal(6);
        /*     */
    }
    /*     */
    /*     */

    public void setTypeId(Number value) {
        /* 129 */setAttributeInternal(6, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionDepId() {
        /* 136 */return (Number)getAttributeInternal(7);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionDepId(Number value) {
        /* 141 */setAttributeInternal(7, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDistributiondepName() {
        /* 148 */return (String)getAttributeInternal(8);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributiondepName(String value) {
        /* 153 */setAttributeInternal(8, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDepId() {
        /* 160 */return (Number)getAttributeInternal(9);
        /*     */
    }
    /*     */
    /*     */

    public void setDepId(Number value) {
        /* 165 */setAttributeInternal(9, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDepName() {
        /* 172 */return (String)getAttributeInternal(10);
        /*     */
    }
    /*     */
    /*     */

    public void setDepName(String value) {
        /* 177 */setAttributeInternal(10, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDistributionStatus() {
        /* 184 */return (String)getAttributeInternal(11);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionStatus(String value) {
        /* 189 */setAttributeInternal(11, value);
        /*     */
    }
    /*     */
    /*     */

    public String getSubmitFlg() {
        /* 196 */return (String)getAttributeInternal(12);
        /*     */
    }
    /*     */
    /*     */

    public void setSubmitFlg(String value) {
        /* 201 */setAttributeInternal(12, value);
        /*     */
    }
    /*     */
    /*     */

    public String getImportFlag() {
        /* 208 */return (String)getAttributeInternal(13);
        /*     */
    }
    /*     */
    /*     */

    public void setImportFlag(String value) {
        /* 213 */setAttributeInternal(13, value);
        /*     */
    }
    /*     */
    /*     */

    public String getImportStatus() {
        /* 220 */return (String)getAttributeInternal(14);
        /*     */
    }
    /*     */
    /*     */

    public void setImportStatus(String value) {
        /* 225 */setAttributeInternal(14, value);
        /*     */
    }
    /*     */
    /*     */

    public String getImportError() {
        /* 232 */return (String)getAttributeInternal(15);
        /*     */
    }
    /*     */
    /*     */

    public void setImportError(String value) {
        /* 237 */setAttributeInternal(15, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getDistributionDate() {
        /* 244 */return (Date)getAttributeInternal(16);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionDate(Date value) {
        /* 249 */setAttributeInternal(16, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionAmount() {
        /* 256 */return (Number)getAttributeInternal(17);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionAmount(Number value) {
        /* 261 */setAttributeInternal(17, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getProjectId() {
        /* 268 */return (Number)getAttributeInternal(18);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectId(Number value) {
        /* 273 */setAttributeInternal(18, value);
        /*     */
    }
    /*     */
    /*     */

    public String getNote() {
        /* 280 */return (String)getAttributeInternal(19);
        /*     */
    }
    /*     */
    /*     */

    public void setNote(String value) {
        /* 285 */setAttributeInternal(19, value);
        /*     */
    }
    /*     */
    /*     */

    public String getApprovedNote() {
        /* 292 */return (String)getAttributeInternal(20);
        /*     */
    }
    /*     */
    /*     */

    public void setApprovedNote(String value) {
        /* 297 */setAttributeInternal(20, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getLastUpdateDate() {
        /* 304 */return (Date)getAttributeInternal(21);
        /*     */
    }
    /*     */
    /*     */

    public void setLastUpdateDate(Date value) {
        /* 309 */setAttributeInternal(21, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getLastUpdatedBy() {
        /* 316 */return (Number)getAttributeInternal(22);
        /*     */
    }
    /*     */
    /*     */

    public void setLastUpdatedBy(Number value) {
        /* 321 */setAttributeInternal(22, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getCreationDate() {
        /* 328 */return (Date)getAttributeInternal(23);
        /*     */
    }
    /*     */
    /*     */

    public void setCreationDate(Date value) {
        /* 333 */setAttributeInternal(23, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getCreatedBy() {
        /* 340 */return (Number)getAttributeInternal(24);
        /*     */
    }
    /*     */
    /*     */

    public void setCreatedBy(Number value) {
        /* 345 */setAttributeInternal(24, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute1() {
        /* 352 */return (String)getAttributeInternal(25);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute1(String value) {
        /* 357 */setAttributeInternal(25, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute2() {
        /* 364 */return (String)getAttributeInternal(26);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute2(String value) {
        /* 369 */setAttributeInternal(26, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute3() {
        /* 376 */return (String)getAttributeInternal(27);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute3(String value) {
        /* 381 */setAttributeInternal(27, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute4() {
        /* 388 */return (String)getAttributeInternal(28);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute4(String value) {
        /* 393 */setAttributeInternal(28, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute5() {
        /* 400 */return (String)getAttributeInternal(29);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute5(String value) {
        /* 405 */setAttributeInternal(29, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute6() {
        /* 412 */return (String)getAttributeInternal(30);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute6(String value) {
        /* 417 */setAttributeInternal(30, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute7() {
        /* 424 */return (String)getAttributeInternal(31);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute7(String value) {
        /* 429 */setAttributeInternal(31, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute8() {
        /* 436 */return (String)getAttributeInternal(32);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute8(String value) {
        /* 441 */setAttributeInternal(32, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute9() {
        /* 448 */return (String)getAttributeInternal(33);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute9(String value) {
        /* 453 */setAttributeInternal(33, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getAttribute10() {
        /* 460 */return (Number)getAttributeInternal(34);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute10(Number value) {
        /* 465 */setAttributeInternal(34, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getAttribute11() {
        /* 472 */return (Number)getAttributeInternal(35);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute11(Number value) {
        /* 477 */setAttributeInternal(35, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getAttribute12() {
        /* 484 */return (Number)getAttributeInternal(36);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute12(Number value) {
        /* 489 */setAttributeInternal(36, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getAttribute13() {
        /* 496 */return (Number)getAttributeInternal(37);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute13(Number value) {
        /* 501 */setAttributeInternal(37, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getAttribute14() {
        /* 508 */return (Date)getAttributeInternal(38);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute14(Date value) {
        /* 513 */setAttributeInternal(38, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getAttribute15() {
        /* 520 */return (Date)getAttributeInternal(39);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute15(Date value) {
        /* 525 */setAttributeInternal(39, value);
        /*     */
    }
    /*     */
    /*     */

    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 532 */switch (index)
        /*     */ {
            /*     */case 0:
            /* 616 */return getPersonDistributionId();
            /*     */case 1:
            /* 616 */return getEmployeeNumber();
            /*     */case 2:
            /* 616 */return getLastName();
            /*     */case 3:
            /* 616 */return getLotId();
            /*     */case 4:
            /* 616 */return getPersonId();
            /*     */case 5:
            /* 616 */return getTypeName();
            /*     */case 6:
            /* 616 */return getTypeId();
            /*     */case 7:
            /* 616 */return getDistributionDepId();
            /*     */case 8:
            /* 616 */return getDistributiondepName();
            /*     */case 9:
            /* 616 */return getDepId();
            /*     */case 10:
            /* 616 */return getDepName();
            /*     */case 11:
            /* 616 */return getDistributionStatus();
            /*     */case 12:
            /* 616 */return getSubmitFlg();
            /*     */case 13:
            /* 616 */return getImportFlag();
            /*     */case 14:
            /* 616 */return getImportStatus();
            /*     */case 15:
            /* 616 */return getImportError();
            /*     */case 16:
            /* 616 */return getDistributionDate();
            /*     */case 17:
            /* 616 */return getDistributionAmount();
            /*     */case 18:
            /* 616 */return getProjectId();
            /*     */case 19:
            /* 616 */return getNote();
            /*     */case 20:
            /* 616 */return getApprovedNote();
            /*     */case 21:
            /* 616 */return getLastUpdateDate();
            /*     */case 22:
            /* 616 */return getLastUpdatedBy();
            /*     */case 23:
            /* 616 */return getCreationDate();
            /*     */case 24:
            /* 616 */return getCreatedBy();
            /*     */case 25:
            /* 616 */return getAttribute1();
            /*     */case 26:
            /* 616 */return getAttribute2();
            /*     */case 27:
            /* 616 */return getAttribute3();
            /*     */case 28:
            /* 616 */return getAttribute4();
            /*     */case 29:
            /* 616 */return getAttribute5();
            /*     */case 30:
            /* 616 */return getAttribute6();
            /*     */case 31:
            /* 616 */return getAttribute7();
            /*     */case 32:
            /* 616 */return getAttribute8();
            /*     */case 33:
            /* 616 */return getAttribute9();
            /*     */case 34:
            /* 616 */return getAttribute10();
            /*     */case 35:
            /* 616 */return getAttribute11();
            /*     */case 36:
            /* 616 */return getAttribute12();
            /*     */case 37:
            /* 616 */return getAttribute13();
            /*     */case 38:
            /* 616 */return getAttribute14();
            /*     */case 39:
            /* 616 */return getAttribute15();
            /*     */
        }
        /*     */
        /* 616 */return super.getAttrInvokeAccessor(index, attrDef);
        /*     */
    }
    /*     */
    /*     */

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 622 */switch (index) {
        case 0:
            /* 624 */setPersonDistributionId((Number)value);
            /*     */return;
            /*     */case 1:
            /* 627 */setEmployeeNumber((String)value);
            /*     */return;
            /*     */case 2:
            /* 630 */setLastName((String)value);
            /*     */return;
            /*     */case 3:
            /* 633 */setLotId((Number)value);
            /*     */return;
            /*     */case 4:
            /* 636 */setPersonId((Number)value);
            /*     */return;
            /*     */case 5:
            /* 639 */setTypeName((String)value);
            /*     */return;
            /*     */case 6:
            /* 642 */setTypeId((Number)value);
            /*     */return;
            /*     */case 7:
            /* 645 */setDistributionDepId((Number)value);
            /*     */return;
            /*     */case 8:
            /* 648 */setDistributiondepName((String)value);
            /*     */return;
            /*     */case 9:
            /* 651 */setDepId((Number)value);
            /*     */return;
            /*     */case 10:
            /* 654 */setDepName((String)value);
            /*     */return;
            /*     */case 11:
            /* 657 */setDistributionStatus((String)value);
            /*     */return;
            /*     */case 12:
            /* 660 */setSubmitFlg((String)value);
            /*     */return;
            /*     */case 13:
            /* 663 */setImportFlag((String)value);
            /*     */return;
            /*     */case 14:
            /* 666 */setImportStatus((String)value);
            /*     */return;
            /*     */case 15:
            /* 669 */setImportError((String)value);
            /*     */return;
            /*     */case 16:
            /* 672 */setDistributionDate((Date)value);
            /*     */return;
            /*     */case 17:
            /* 675 */setDistributionAmount((Number)value);
            /*     */return;
            /*     */case 18:
            /* 678 */setProjectId((Number)value);
            /*     */return;
            /*     */case 19:
            /* 681 */setNote((String)value);
            /*     */return;
            /*     */case 20:
            /* 684 */setApprovedNote((String)value);
            /*     */return;
            /*     */case 21:
            /* 687 */setLastUpdateDate((Date)value);
            /*     */return;
            /*     */case 22:
            /* 690 */setLastUpdatedBy((Number)value);
            /*     */return;
            /*     */case 23:
            /* 693 */setCreationDate((Date)value);
            /*     */return;
            /*     */case 24:
            /* 696 */setCreatedBy((Number)value);
            /*     */return;
            /*     */case 25:
            /* 699 */setAttribute1((String)value);
            /*     */return;
            /*     */case 26:
            /* 702 */setAttribute2((String)value);
            /*     */return;
            /*     */case 27:
            /* 705 */setAttribute3((String)value);
            /*     */return;
            /*     */case 28:
            /* 708 */setAttribute4((String)value);
            /*     */return;
            /*     */case 29:
            /* 711 */setAttribute5((String)value);
            /*     */return;
            /*     */case 30:
            /* 714 */setAttribute6((String)value);
            /*     */return;
            /*     */case 31:
            /* 717 */setAttribute7((String)value);
            /*     */return;
            /*     */case 32:
            /* 720 */setAttribute8((String)value);
            /*     */return;
            /*     */case 33:
            /* 723 */setAttribute9((String)value);
            /*     */return;
            /*     */case 34:
            /* 726 */setAttribute10((Number)value);
            /*     */return;
            /*     */case 35:
            /* 729 */setAttribute11((Number)value);
            /*     */return;
            /*     */case 36:
            /* 732 */setAttribute12((Number)value);
            /*     */return;
            /*     */case 37:
            /* 735 */setAttribute13((Number)value);
            /*     */return;
            /*     */case 38:
            /* 738 */setAttribute14((Date)value);
            /*     */return;
            /*     */case 39:
            /* 741 */setAttribute15((Date)value);
            /*     */return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
        /*     */
    }
    /*     */
    /*     */

    public String getTypeName() {
        /* 753 */return (String)getAttributeInternal(5);
        /*     */
    }
    /*     */
    /*     */

    public void setTypeName(String value) {
        /* 758 */setAttributeInternal(5, value);
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\member\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.member.server.SpecialAwardsPersonVORowImpl
 * JD-Core Version:    0.6.1
 */