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
/*     */public class MemberDistrVORowImpl extends OAViewRowImpl {
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
    public static final int NOTE = 7;
    /*     */
    public static final int DISTRIBUTIONSTATUS = 8;
    /*     */
    public static final int IMPORTSTATUS = 9;
    /*     */
    public static final int ATTRIBUTE1 = 10;
    /*     */
    /*     */

    public Number getPersonDistributionId() {
        /*  35 */return (Number)getAttributeInternal(0);
        /*     */
    }
    /*     */
    /*     */

    public void setPersonDistributionId(Number value) {
        /*  40 */setAttributeInternal(0, value);
        /*     */
    }
    /*     */
    /*     */

    public String getEmployeeNumber() {
        /*  47 */return (String)getAttributeInternal(1);
        /*     */
    }
    /*     */
    /*     */

    public void setEmployeeNumber(String value) {
        /*  52 */setAttributeInternal(1, value);
        /*     */
    }
    /*     */
    /*     */

    public String getLastName() {
        /*  59 */return (String)getAttributeInternal(2);
        /*     */
    }
    /*     */
    /*     */

    public void setLastName(String value) {
        /*  64 */setAttributeInternal(2, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDepName() {
        /*  71 */return (String)getAttributeInternal(3);
        /*     */
    }
    /*     */
    /*     */

    public void setDepName(String value) {
        /*  76 */setAttributeInternal(3, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionAmount() {
        /*  83 */return (Number)getAttributeInternal(4);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionAmount(Number value) {
        /*  88 */setAttributeInternal(4, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getDistributionDate() {
        /*  95 */return (Date)getAttributeInternal(5);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionDate(Date value) {
        /* 100 */setAttributeInternal(5, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getProjectId() {
        /* 107 */return (Number)getAttributeInternal(6);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectId(Number value) {
        /* 112 */setAttributeInternal(6, value);
        /*     */
    }
    /*     */
    /*     */

    public String getNote() {
        /* 119 */return (String)getAttributeInternal(7);
        /*     */
    }
    /*     */
    /*     */

    public void setNote(String value) {
        /* 124 */setAttributeInternal(7, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDistributionStatus() {
        /* 131 */return (String)getAttributeInternal(8);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionStatus(String value) {
        /* 136 */setAttributeInternal(8, value);
        /*     */
    }
    /*     */
    /*     */

    public String getImportStatus() {
        /* 143 */return (String)getAttributeInternal(9);
        /*     */
    }
    /*     */
    /*     */

    public void setImportStatus(String value) {
        /* 148 */setAttributeInternal(9, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute1() {
        /* 155 */return (String)getAttributeInternal(10);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute1(String value) {
        /* 160 */setAttributeInternal(10, value);
        /*     */
    }
    /*     */
    /*     */

    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 167 */switch (index)
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
            /* 193 */return getNote();
            /*     */case 8:
            /* 193 */return getDistributionStatus();
            /*     */case 9:
            /* 193 */return getImportStatus();
            /*     */case 10:
            /* 193 */return getAttribute1();
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
            /* 222 */setNote((String)value);
            /*     */return;
            /*     */case 8:
            /* 225 */setDistributionStatus((String)value);
            /*     */return;
            /*     */case 9:
            /* 228 */setImportStatus((String)value);
            /*     */return;
            /*     */case 10:
            /* 231 */setAttribute1((String)value);
            /*     */return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\member\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.member.server.MemberDistrVORowImpl
 * JD-Core Version:    0.6.1
 */