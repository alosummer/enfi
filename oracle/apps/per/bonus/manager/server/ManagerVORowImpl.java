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
/*     */public class ManagerVORowImpl extends OAViewRowImpl {
    /*     */
    public static final int PERSONDISTRIBUTIONID = 0;
    /*     */
    public static final int PROJECTNUMBER = 1;
    /*     */
    public static final int PROJECTNAME = 2;
    /*     */
    public static final int DISTRIBUTEDAMT = 3;
    /*     */
    public static final int DISTRIBUTABLEAMT = 4;
    /*     */
    public static final int MANAGER = 5;
    /*     */
    public static final int DISTRIBUTIONAMOUNT = 6;
    /*     */
    public static final int DISTRIBUTIONDATE = 7;
    /*     */
    public static final int DEPNAME = 8;
    /*     */
    /*     */

    public Number getPersonDistributionId() {
        /*  33 */return (Number)getAttributeInternal(0);
        /*     */
    }
    /*     */
    /*     */

    public void setPersonDistributionId(Number value) {
        /*  38 */setAttributeInternal(0, value);
        /*     */
    }
    /*     */
    /*     */

    public String getProjectNumber() {
        /*  45 */return (String)getAttributeInternal(1);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectNumber(String value) {
        /*  50 */setAttributeInternal(1, value);
        /*     */
    }
    /*     */
    /*     */

    public String getProjectName() {
        /*  57 */return (String)getAttributeInternal(2);
        /*     */
    }
    /*     */
    /*     */

    public void setProjectName(String value) {
        /*  62 */setAttributeInternal(2, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributedAmt() {
        /*  69 */return (Number)getAttributeInternal(3);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributedAmt(Number value) {
        /*  74 */setAttributeInternal(3, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributableAmt() {
        /*  81 */return (Number)getAttributeInternal(4);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributableAmt(Number value) {
        /*  86 */setAttributeInternal(4, value);
        /*     */
    }
    /*     */
    /*     */

    public String getManager() {
        /*  93 */return (String)getAttributeInternal(5);
        /*     */
    }
    /*     */
    /*     */

    public void setManager(String value) {
        /*  98 */setAttributeInternal(5, value);
        /*     */
    }
    /*     */
    /*     */

    public Number getDistributionAmount() {
        /* 105 */return (Number)getAttributeInternal(6);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionAmount(Number value) {
        /* 110 */setAttributeInternal(6, value);
        /*     */
    }
    /*     */
    /*     */

    public Date getDistributionDate() {
        /* 117 */return (Date)getAttributeInternal(7);
        /*     */
    }
    /*     */
    /*     */

    public void setDistributionDate(Date value) {
        /* 122 */setAttributeInternal(7, value);
        /*     */
    }
    /*     */
    /*     */

    public String getDepName() {
        /* 129 */return (String)getAttributeInternal(8);
        /*     */
    }
    /*     */
    /*     */

    public void setDepName(String value) {
        /* 134 */setAttributeInternal(8, value);
        /*     */
    }
    /*     */
    /*     */

    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /* 141 */switch (index)
        /*     */ {
            /*     */case 0:
            /* 163 */return getPersonDistributionId();
            /*     */case 1:
            /* 163 */return getProjectNumber();
            /*     */case 2:
            /* 163 */return getProjectName();
            /*     */case 3:
            /* 163 */return getDistributedAmt();
            /*     */case 4:
            /* 163 */return getDistributableAmt();
            /*     */case 5:
            /* 163 */return getManager();
            /*     */case 6:
            /* 163 */return getDistributionAmount();
            /*     */case 7:
            /* 163 */return getDistributionDate();
            /*     */case 8:
            /* 163 */return getDepName();
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
            /* 174 */setProjectNumber((String)value);
            /*     */return;
            /*     */case 2:
            /* 177 */setProjectName((String)value);
            /*     */return;
            /*     */case 3:
            /* 180 */setDistributedAmt((Number)value);
            /*     */return;
            /*     */case 4:
            /* 183 */setDistributableAmt((Number)value);
            /*     */return;
            /*     */case 5:
            /* 186 */setManager((String)value);
            /*     */return;
            /*     */case 6:
            /* 189 */setDistributionAmount((Number)value);
            /*     */return;
            /*     */case 7:
            /* 192 */setDistributionDate((Date)value);
            /*     */return;
            /*     */case 8:
            /* 195 */setDepName((String)value);
            /*     */return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.server.ManagerVORowImpl
 * JD-Core Version:    0.6.1
 */