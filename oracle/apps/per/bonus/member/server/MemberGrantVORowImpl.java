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
/*     */public class MemberGrantVORowImpl extends OAViewRowImpl {
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
    public static final int PROJECTNUMBER = 7;
    /*     */
    public static final int PROJECTNAME = 8;
    /*     */
    public static final int NOTE = 9;
    /*     */
    public static final int DISTRIBUTIONSTATUS = 10;
    /*     */
    public static final int IMPORTSTATUS = 11;
    /*     */
    public static final int ATTRIBUTE1 = 12;
    /*     */
    public static final int APPROVEDDATE = 13;
    /*     */
    public static final int TOTAL = 14;
    /*     */
    /*     */

    public Number getPersonDistributionId() {
        /*  39 */return (Number)getAttributeInternal(0);
        /*     */
    }
    /*     */
    /*     */

    public void setPersonDistributionId(Number value) {
        /*  44 */setAttributeInternal(0, value);
        /*     */
    }
    /*     */
    /*     */

    public String getEmployeeNumber() {
        /*  51 */return (String)getAttributeInternal(1);
        /*     */
    }
    /*     */
    /*     */

    public void setEmployeeNumber(String value) {
        /*  56 */setAttributeInternal(1, value);
        /*     */
    }
    /*     */
    /*     */

    public String getLastName() {
        /*  63 */return (String)getAttributeInternal(2);
        /*     */
    }
    /*     */
    /*     */

    public void setLastName(String value) {
        /*  68 */setAttributeInternal(2, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDepName() {
        /*  75 */return (String)getAttributeInternal(3);
        /*     */
    }
    /*     */
    /*     */

    public void setDepName(String value) {
        /*  80 */setAttributeInternal(3, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionAmount() {
        /*  87 */return (Number)getAttributeInternal(4);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionAmount(Number value) {
        /*  92 */setAttributeInternal(4, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getDistributionDate() {
        /*  99 */return (Date)getAttributeInternal(5);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionDate(Date value) {
        /* 104 */setAttributeInternal(5, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getProjectId() {
        /* 111 */return (Number)getAttributeInternal(6);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectId(Number value) {
        /* 116 */setAttributeInternal(6, value);
        /*     */
    }
    /*     */
    /*     */

    public String getNote() {
        /* 123 */return (String)getAttributeInternal(9);
        /*     */
    }
    /*     */
    /*     */

    public void setNote(String value) {
        /* 128 */setAttributeInternal(9, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDistributionStatus() {
        /* 135 */return (String)getAttributeInternal(10);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionStatus(String value) {
        /* 140 */setAttributeInternal(10, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getApprovedDate() {
        /* 147 */return (Date)getAttributeInternal(13);
        /*     */
    }
    /*     */
    /*     */

    public void setApprovedDate(Date value) {
        /* 152 */setAttributeInternal(13, value);
        /*     */
    }
    /*     */
    /*     */

    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 159 */switch (index)
        /*     */ {
            /*     */case 0:
            /* 193 */return getPersonDistributionId();
            /*     */case 1:
            /* 193 */return getEmployeeNumber();
            /*     */case 2:
            /* 193 */return getLastName();
            /*     */case 3:
            /* 193 */return getDepName();
            /*     */case 4:
            /* 193 */return getDistributionAmount();
            /*     */case 5:
            /* 193 */return getDistributionDate();
            /*     */case 6:
            /* 193 */return getProjectId();
            /*     */case 7:
            /* 193 */return getProjectNumber();
            /*     */case 8:
            /* 193 */return getProjectName();
            /*     */case 9:
            /* 193 */return getNote();
            /*     */case 10:
            /* 193 */return getDistributionStatus();
            /*     */case 11:
            /* 193 */return getImportStatus();
            /*     */case 12:
            /* 193 */return getAttribute1();
            /*     */case 13:
            /* 193 */return getApprovedDate();
            /*     */case 14:
            /* 193 */return getTotal();
            /*     */
        }
        /*     */
        /* 193 */return super.getAttrInvokeAccessor(index, attrDef);
        /*     */
    }
    /*     */
    /*     */

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 199 */switch (index) {
        case 0:
            /* 201 */setPersonDistributionId((Number)value);
            /*     */return;
            /*     */case 1:
            /* 204 */setEmployeeNumber((String)value);
            /*     */return;
            /*     */case 2:
            /* 207 */setLastName((String)value);
            /*     */return;
            /*     */case 3:
            /* 210 */setDepName((String)value);
            /*     */return;
            /*     */case 4:
            /* 213 */setDistributionAmount((Number)value);
            /*     */return;
            /*     */case 5:
            /* 216 */setDistributionDate((Date)value);
            /*     */return;
            /*     */case 6:
            /* 219 */setProjectId((Number)value);
            /*     */return;
            /*     */case 7:
            /* 222 */setProjectNumber((String)value);
            /*     */return;
            /*     */case 8:
            /* 225 */setProjectName((String)value);
            /*     */return;
            /*     */case 9:
            /* 228 */setNote((String)value);
            /*     */return;
            /*     */case 10:
            /* 231 */setDistributionStatus((String)value);
            /*     */return;
            /*     */case 11:
            /* 234 */setImportStatus((String)value);
            /*     */return;
            /*     */case 12:
            /* 237 */setAttribute1((String)value);
            /*     */return;
            /*     */case 13:
            /* 240 */setApprovedDate((Date)value);
            /*     */return;
            /*     */case 14:
            /* 243 */setTotal((Number)value);
            /*     */return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute1() {
        /* 255 */return (String)getAttributeInternal(12);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute1(String value) {
        /* 260 */setAttributeInternal(12, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getTotal() {
        /* 267 */return (Number)getAttributeInternal(14);
        /*     */
    }
    /*     */
    /*     */

    public void setTotal(Number value) {
        /* 272 */setAttributeInternal(14, value);
        /*     */
    }
    /*     */
    /*     */

    public String getImportStatus() {
        /* 279 */return (String)getAttributeInternal(11);
        /*     */
    }
    /*     */
    /*     */

    public void setImportStatus(String value) {
        /* 284 */setAttributeInternal(11, value);
        /*     */
    }
    /*     */
    /*     */

    public String getProjectNumber() {
        /* 291 */return (String)getAttributeInternal(7);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectNumber(String value) {
        /* 296 */setAttributeInternal(7, value);
        /*     */
    }
    /*     */
    /*     */

    public String getProjectName() {
        /* 303 */return (String)getAttributeInternal(8);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectName(String value) {
        /* 308 */setAttributeInternal(8, value);
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\member\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.member.server.MemberGrantVORowImpl
 * JD-Core Version:    0.6.1
 */