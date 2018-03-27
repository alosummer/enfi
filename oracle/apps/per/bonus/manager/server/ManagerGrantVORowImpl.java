/*     */package cux.oracle.apps.per.bonus.manager.server;
/*     */
/*     */import oracle.apps.fnd.framework.server.OAViewRowImpl;
/*     */
import oracle.jbo.domain.Date;
/*     */
import oracle.jbo.domain.Number;
/*     */
import oracle.jbo.server.AttributeDefImpl;
/*     */
/*     */public class ManagerGrantVORowImpl extends OAViewRowImpl {
    /*     */
    public static final int PERSONDISTRIBUTIONID = 0;
    /*     */
    public static final int EMPLOYEENUMBER = 1;
    /*     */
    public static final int LASTNAME = 2;
    /*     */
    public static final int DEPNAME = 3;
    /*     */
    public static final int DISTRIBUTIONAMOUNT = 4;
    /*     */
    public static final int DISTRIBUTIONDATE = 5;
    /*     */
    public static final int PROJECTID = 6;
    /*     */
    public static final int SEGMENT1 = 7;
    /*     */
    public static final int NAME = 8;
    /*     */
    public static final int MANAGER = 9;
    /*     */
    public static final int NOTE = 10;
    /*     */
    public static final int DISTRIBUTIONSTATUS = 11;
    /*     */
    public static final int ATTRIBUTE1 = 12;
    /*     */
    public static final int DISTRIBUTABLEAMT = 13;
    /*     */
    public static final int APPROVEDDATE = 14;
    /*     */
    public static final int PROJECTNUMBER = 15;
    /*     */
    public static final int PROJECTNAME = 16;
    /*     */
    /*     */

    public Number getPersonDistributionId() {
        /*  41 */return (Number)getAttributeInternal(0);
        /*     */
    }
    /*     */
    /*     */

    public void setPersonDistributionId(Number value) {
        /*  46 */setAttributeInternal(0, value);
        /*     */
    }
    /*     */
    /*     */

    public String getProjectNumber() {
        /*  53 */return (String)getAttributeInternal(15);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectNumber(String value) {
        /*  58 */setAttributeInternal(15, value);
        /*     */
    }
    /*     */
    /*     */

    public String getProjectName() {
        /*  65 */return (String)getAttributeInternal(16);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectName(String value) {
        /*  70 */setAttributeInternal(16, value);
        /*     */
    }
    /*     */
    /*     */

    public String getManager() {
        /*  77 */return (String)getAttributeInternal(9);
        /*     */
    }
    /*     */
    /*     */

    public void setManager(String value) {
        /*  82 */setAttributeInternal(9, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionAmount() {
        /*  89 */return (Number)getAttributeInternal(4);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionAmount(Number value) {
        /*  94 */setAttributeInternal(4, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getDistributionDate() {
        /* 101 */return (Date)getAttributeInternal(5);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionDate(Date value) {
        /* 106 */setAttributeInternal(5, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDepName() {
        /* 113 */return (String)getAttributeInternal(3);
        /*     */
    }
    /*     */
    /*     */

    public void setDepName(String value) {
        /* 118 */setAttributeInternal(3, value);
        /*     */
    }
    /*     */
    /*     */

    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 125 */switch (index)
        /*     */ {
            /*     */case 0:
            /* 163 */return getPersonDistributionId();
            /*     */case 1:
            /* 163 */return getEmployeeNumber();
            /*     */case 2:
            /* 163 */return getLastName();
            /*     */case 3:
            /* 163 */return getDepName();
            /*     */case 4:
            /* 163 */return getDistributionAmount();
            /*     */case 5:
            /* 163 */return getDistributionDate();
            /*     */case 6:
            /* 163 */return getProjectId();
            /*     */case 7:
            /* 163 */return getSegment1();
            /*     */case 8:
            /* 163 */return getName();
            /*     */case 9:
            /* 163 */return getManager();
            /*     */case 10:
            /* 163 */return getNote();
            /*     */case 11:
            /* 163 */return getDistributionStatus();
            /*     */case 12:
            /* 163 */return getAttribute1();
            /*     */case 13:
            /* 163 */return getDistributableAmt();
            /*     */case 14:
            /* 163 */return getApprovedDate();
            /*     */case 15:
            /* 163 */return getProjectNumber();
            /*     */case 16:
            /* 163 */return getProjectName();
            /*     */
        }
        /*     */
        /* 163 */return super.getAttrInvokeAccessor(index, attrDef);
        /*     */
    }
    /*     */
    /*     */

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 169 */switch (index) {
        case 0:
            /* 171 */setPersonDistributionId((Number)value);
            /*     */return;
            /*     */case 1:
            /* 174 */setEmployeeNumber((String)value);
            /*     */return;
            /*     */case 2:
            /* 177 */setLastName((String)value);
            /*     */return;
            /*     */case 3:
            /* 180 */setDepName((String)value);
            /*     */return;
            /*     */case 4:
            /* 183 */setDistributionAmount((Number)value);
            /*     */return;
            /*     */case 5:
            /* 186 */setDistributionDate((Date)value);
            /*     */return;
            /*     */case 6:
            /* 189 */setProjectId((Number)value);
            /*     */return;
            /*     */case 7:
            /* 192 */setSegment1((String)value);
            /*     */return;
            /*     */case 8:
            /* 195 */setName((String)value);
            /*     */return;
            /*     */case 9:
            /* 198 */setManager((String)value);
            /*     */return;
            /*     */case 10:
            /* 201 */setNote((String)value);
            /*     */return;
            /*     */case 11:
            /* 204 */setDistributionStatus((String)value);
            /*     */return;
            /*     */case 12:
            /* 207 */setAttribute1((String)value);
            /*     */return;
            /*     */case 13:
            /* 210 */setDistributableAmt((Number)value);
            /*     */return;
            /*     */case 14:
            /* 213 */setApprovedDate((Date)value);
            /*     */return;
            /*     */case 15:
            /* 216 */setProjectNumber((String)value);
            /*     */return;
            /*     */case 16:
            /* 219 */setProjectName((String)value);
            /*     */return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
        /*     */
    }
    /*     */
    /*     */

    public String getEmployeeNumber() {
        /* 231 */return (String)getAttributeInternal(1);
        /*     */
    }
    /*     */
    /*     */

    public void setEmployeeNumber(String value) {
        /* 236 */setAttributeInternal(1, value);
        /*     */
    }
    /*     */
    /*     */

    public String getLastName() {
        /* 243 */return (String)getAttributeInternal(2);
        /*     */
    }
    /*     */
    /*     */

    public void setLastName(String value) {
        /* 248 */setAttributeInternal(2, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getProjectId() {
        /* 255 */return (Number)getAttributeInternal(6);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectId(Number value) {
        /* 260 */setAttributeInternal(6, value);
        /*     */
    }
    /*     */
    /*     */

    public String getNote() {
        /* 267 */return (String)getAttributeInternal(10);
        /*     */
    }
    /*     */
    /*     */

    public void setNote(String value) {
        /* 272 */setAttributeInternal(10, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDistributionStatus() {
        /* 279 */return (String)getAttributeInternal(11);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionStatus(String value) {
        /* 284 */setAttributeInternal(11, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getApprovedDate() {
        /* 291 */return (Date)getAttributeInternal(14);
        /*     */
    }
    /*     */
    /*     */

    public void setApprovedDate(Date value) {
        /* 296 */setAttributeInternal(14, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute1() {
        /* 303 */return (String)getAttributeInternal(12);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute1(String value) {
        /* 308 */setAttributeInternal(12, value);
        /*     */
    }
    /*     */
    /*     */

    public String getSegment1() {
        /* 315 */return (String)getAttributeInternal(7);
        /*     */
    }
    /*     */
    /*     */

    public void setSegment1(String value) {
        /* 320 */setAttributeInternal(7, value);
        /*     */
    }
    /*     */
    /*     */

    public String getName() {
        /* 327 */return (String)getAttributeInternal(8);
        /*     */
    }
    /*     */
    /*     */

    public void setName(String value) {
        /* 332 */setAttributeInternal(8, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributableAmt() {
        /* 339 */return (Number)getAttributeInternal(13);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributableAmt(Number value) {
        /* 344 */setAttributeInternal(13, value);
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.server.ManagerGrantVORowImpl
 * JD-Core Version:    0.6.1
 */