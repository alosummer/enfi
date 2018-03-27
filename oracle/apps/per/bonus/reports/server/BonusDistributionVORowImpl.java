/*     */package cux.oracle.apps.per.bonus.reports.server;
/*     */
/*     */import oracle.apps.fnd.framework.server.OAViewRowImpl;
/*     */
import oracle.jbo.domain.Number;
/*     */
import oracle.jbo.server.AttributeDefImpl;
/*     */
/*     */public class BonusDistributionVORowImpl extends OAViewRowImpl {
    /*     */
    public static final int 计奖部门 = 0;
    /*     */
    public static final int 一月 = 1;
    /*     */
    public static final int 二月 = 2;
    /*     */
    public static final int 三月 = 3;
    /*     */
    public static final int 四月 = 4;
    /*     */
    public static final int 五月 = 5;
    /*     */
    public static final int 六月 = 6;
    /*     */
    public static final int 七月 = 7;
    /*     */
    public static final int 八月 = 8;
    /*     */
    public static final int 九月 = 9;
    /*     */
    public static final int 十月 = 10;
    /*     */
    public static final int 十一月 = 11;
    /*     */
    public static final int 十二月 = 12;
    /*     */
    public static final int 年终奖金 = 13;
    /*     */
    public static final int 总金额 = 14;
    /*     */
    /*     */

    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /*  39 */switch (index)
        /*     */ {
            /*     */case 0:
            /*  73 */return get计奖部门();
            /*     */case 1:
            /*  73 */return get一月();
            /*     */case 2:
            /*  73 */return get二月();
            /*     */case 3:
            /*  73 */return get三月();
            /*     */case 4:
            /*  73 */return get四月();
            /*     */case 5:
            /*  73 */return get五月();
            /*     */case 6:
            /*  73 */return get六月();
            /*     */case 7:
            /*  73 */return get七月();
            /*     */case 8:
            /*  73 */return get八月();
            /*     */case 9:
            /*  73 */return get九月();
            /*     */case 10:
            /*  73 */return get十月();
            /*     */case 11:
            /*  73 */return get十一月();
            /*     */case 12:
            /*  73 */return get十二月();
            /*     */case 13:
            /*  73 */return get年终奖金();
            /*     */case 14:
            /*  73 */return get总金额();
            /*     */
        }
        /*     */
        /*  73 */return super.getAttrInvokeAccessor(index, attrDef);
        /*     */
    }
    /*     */
    /*     */

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef)
        /*     */ throws Exception
    /*     */ {
        /*  79 */switch (index) {
        case 0:
            /*  81 */set计奖部门((String)value);
            /*     */return;
            /*     */case 1:
            /*  84 */set一月((Number)value);
            /*     */return;
            /*     */case 2:
            /*  87 */set二月((Number)value);
            /*     */return;
            /*     */case 3:
            /*  90 */set三月((Number)value);
            /*     */return;
            /*     */case 4:
            /*  93 */set四月((Number)value);
            /*     */return;
            /*     */case 5:
            /*  96 */set五月((Number)value);
            /*     */return;
            /*     */case 6:
            /*  99 */set六月((Number)value);
            /*     */return;
            /*     */case 7:
            /* 102 */set七月((Number)value);
            /*     */return;
            /*     */case 8:
            /* 105 */set八月((Number)value);
            /*     */return;
            /*     */case 9:
            /* 108 */set九月((Number)value);
            /*     */return;
            /*     */case 10:
            /* 111 */set十月((Number)value);
            /*     */return;
            /*     */case 11:
            /* 114 */set十一月((Number)value);
            /*     */return;
            /*     */case 12:
            /* 117 */set十二月((Number)value);
            /*     */return;
            /*     */case 13:
            /* 120 */set年终奖金((Number)value);
            /*     */return;
            /*     */case 14:
            /* 123 */set总金额((Number)value);
            /*     */return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
        /*     */
    }
    /*     */
    /*     */

    public String get计奖部门() {
        /* 135 */return (String)getAttributeInternal(0);
        /*     */
    }
    /*     */
    /*     */

    public void set计奖部门(String value) {
        /* 140 */setAttributeInternal(0, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get一月() {
        /* 147 */return (Number)getAttributeInternal(1);
        /*     */
    }
    /*     */
    /*     */

    public void set一月(Number value) {
        /* 152 */setAttributeInternal(1, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get二月() {
        /* 159 */return (Number)getAttributeInternal(2);
        /*     */
    }
    /*     */
    /*     */

    public void set二月(Number value) {
        /* 164 */setAttributeInternal(2, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get三月() {
        /* 171 */return (Number)getAttributeInternal(3);
        /*     */
    }
    /*     */
    /*     */

    public void set三月(Number value) {
        /* 176 */setAttributeInternal(3, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get四月() {
        /* 183 */return (Number)getAttributeInternal(4);
        /*     */
    }
    /*     */
    /*     */

    public void set四月(Number value) {
        /* 188 */setAttributeInternal(4, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get五月() {
        /* 195 */return (Number)getAttributeInternal(5);
        /*     */
    }
    /*     */
    /*     */

    public void set五月(Number value) {
        /* 200 */setAttributeInternal(5, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get六月() {
        /* 207 */return (Number)getAttributeInternal(6);
        /*     */
    }
    /*     */
    /*     */

    public void set六月(Number value) {
        /* 212 */setAttributeInternal(6, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get七月() {
        /* 219 */return (Number)getAttributeInternal(7);
        /*     */
    }
    /*     */
    /*     */

    public void set七月(Number value) {
        /* 224 */setAttributeInternal(7, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get八月() {
        /* 231 */return (Number)getAttributeInternal(8);
        /*     */
    }
    /*     */
    /*     */

    public void set八月(Number value) {
        /* 236 */setAttributeInternal(8, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get九月() {
        /* 243 */return (Number)getAttributeInternal(9);
        /*     */
    }
    /*     */
    /*     */

    public void set九月(Number value) {
        /* 248 */setAttributeInternal(9, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get十月() {
        /* 255 */return (Number)getAttributeInternal(10);
        /*     */
    }
    /*     */
    /*     */

    public void set十月(Number value) {
        /* 260 */setAttributeInternal(10, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get十一月() {
        /* 267 */return (Number)getAttributeInternal(11);
        /*     */
    }
    /*     */
    /*     */

    public void set十一月(Number value) {
        /* 272 */setAttributeInternal(11, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get十二月() {
        /* 279 */return (Number)getAttributeInternal(12);
        /*     */
    }
    /*     */
    /*     */

    public void set十二月(Number value) {
        /* 284 */setAttributeInternal(12, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get年终奖金() {
        /* 291 */return (Number)getAttributeInternal(13);
        /*     */
    }
    /*     */
    /*     */

    public void set年终奖金(Number value) {
        /* 296 */setAttributeInternal(13, value);
        /*     */
    }
    /*     */
    /*     */

    public Number get总金额() {
        /* 303 */return (Number)getAttributeInternal(14);
        /*     */
    }
    /*     */
    /*     */

    public void set总金额(Number value) {
        /* 308 */setAttributeInternal(14, value);
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\reports\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.reports.server.BonusDistributionVORowImpl
 * JD-Core Version:    0.6.1
 */