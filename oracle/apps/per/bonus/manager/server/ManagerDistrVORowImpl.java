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
/*     */public class ManagerDistrVORowImpl extends OAViewRowImpl {
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
    /*     */

    public Number getPersonDistributionId() {
        /*  38 */return (Number)getAttributeInternal(0);
        /*     */
    }
    /*     */
    /*     */

    public void setPersonDistributionId(Number value) {
        /*  43 */setAttributeInternal(0, value);
        /*     */
    }
    /*     */
    /*     */

    public String getEmployeeNumber() {
        /*  50 */return (String)getAttributeInternal(1);
        /*     */
    }
    /*     */
    /*     */

    public void setEmployeeNumber(String value) {
        /*  55 */setAttributeInternal(1, value);
        /*     */
    }
    /*     */
    /*     */

    public String getLastName() {
        /*  62 */return (String)getAttributeInternal(2);
        /*     */
    }
    /*     */
    /*     */

    public void setLastName(String value) {
        /*  67 */setAttributeInternal(2, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDepName() {
        /*  74 */return (String)getAttributeInternal(3);
        /*     */
    }
    /*     */
    /*     */

    public void setDepName(String value) {
        /*  79 */setAttributeInternal(3, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionAmount() {
        /*  86 */return (Number)getAttributeInternal(4);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionAmount(Number value) {
        /*  91 */setAttributeInternal(4, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getDistributionDate() {
        /*  98 */return (Date)getAttributeInternal(5);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionDate(Date value) {
        /* 103 */setAttributeInternal(5, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getProjectId() {
        /* 110 */return (Number)getAttributeInternal(6);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectId(Number value) {
        /* 115 */setAttributeInternal(6, value);
        /*     */
    }
    /*     */
    /*     */

    public String getSegment1() {
        /* 122 */return (String)getAttributeInternal(7);
        /*     */
    }
    /*     */
    /*     */

    public void setSegment1(String value) {
        /* 127 */setAttributeInternal(7, value);
        /*     */
    }
    /*     */
    /*     */

    public String getName() {
        /* 134 */return (String)getAttributeInternal(8);
        /*     */
    }
    /*     */
    /*     */

    public void setName(String value) {
        /* 139 */setAttributeInternal(8, value);
        /*     */
    }
    /*     */
    /*     */

    public String getManager() {
        /* 146 */return (String)getAttributeInternal(9);
        /*     */
    }
    /*     */
    /*     */

    public void setManager(String value) {
        /* 151 */setAttributeInternal(9, value);
        /*     */
    }
    /*     */
    /*     */

    public String getNote() {
        /* 158 */return (String)getAttributeInternal(10);
        /*     */
    }
    /*     */
    /*     */

    public void setNote(String value) {
        /* 163 */setAttributeInternal(10, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDistributionStatus() {
        /* 170 */return (String)getAttributeInternal(11);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionStatus(String value) {
        /* 175 */setAttributeInternal(11, value);
        /*     */
    }
    /*     */
    /*     */

    public String getAttribute1() {
        /* 182 */return (String)getAttributeInternal(12);
        /*     */
    }
    /*     */
    /*     */

    public void setAttribute1(String value) {
        /* 187 */setAttributeInternal(12, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributableAmt() {
        /* 194 */return (Number)getAttributeInternal(13);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributableAmt(Number value) {
        /* 199 */setAttributeInternal(13, value);
        /*     */
    }
    /*     */
    /*     */

    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 206 */switch (index)
        /*     */ {
            /*     */case 0:
            /* 238 */return getPersonDistributionId();
            /*     */case 1:
            /* 238 */return getEmployeeNumber();
            /*     */case 2:
            /* 238 */return getLastName();
            /*     */case 3:
            /* 238 */return getDepName();
            /*     */case 4:
            /* 238 */return getDistributionAmount();
            /*     */case 5:
            /* 238 */return getDistributionDate();
            /*     */case 6:
            /* 238 */return getProjectId();
            /*     */case 7:
            /* 238 */return getSegment1();
            /*     */case 8:
            /* 238 */return getName();
            /*     */case 9:
            /* 238 */return getManager();
            /*     */case 10:
            /* 238 */return getNote();
            /*     */case 11:
            /* 238 */return getDistributionStatus();
            /*     */case 12:
            /* 238 */return getAttribute1();
            /*     */case 13:
            /* 238 */return getDistributableAmt();
            /*     */
        }
        /*     */
        /* 238 */return super.getAttrInvokeAccessor(index, attrDef);
        /*     */
    }
    /*     */
    /*     */

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 244 */switch (index) {
        case 0:
            /* 246 */setPersonDistributionId((Number)value);
            /*     */return;
            /*     */case 1:
            /* 249 */setEmployeeNumber((String)value);
            /*     */return;
            /*     */case 2:
            /* 252 */setLastName((String)value);
            /*     */return;
            /*     */case 3:
            /* 255 */setDepName((String)value);
            /*     */return;
            /*     */case 4:
            /* 258 */setDistributionAmount((Number)value);
            /*     */return;
            /*     */case 5:
            /* 261 */setDistributionDate((Date)value);
            /*     */return;
            /*     */case 6:
            /* 264 */setProjectId((Number)value);
            /*     */return;
            /*     */case 7:
            /* 267 */setSegment1((String)value);
            /*     */return;
            /*     */case 8:
            /* 270 */setName((String)value);
            /*     */return;
            /*     */case 9:
            /* 273 */setManager((String)value);
            /*     */return;
            /*     */case 10:
            /* 276 */setNote((String)value);
            /*     */return;
            /*     */case 11:
            /* 279 */setDistributionStatus((String)value);
            /*     */return;
            /*     */case 12:
            /* 282 */setAttribute1((String)value);
            /*     */return;
            /*     */case 13:
            /* 285 */setDistributableAmt((Number)value);
            /*     */return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.server.ManagerDistrVORowImpl
 * JD-Core Version:    0.6.1
 */