/*    */package cux.oracle.apps.per.bonus.manager.webui;
/*    */
/*    */import java.math.BigDecimal;
/*    */
/*    */public class DoubleProcess {
    /*    */

    public static double round(double value, int scale, int roundingMode) {
        /* 17 */BigDecimal bd = new BigDecimal(value);
        /* 18 */bd = bd.setScale(scale, roundingMode);
        /* 19 */double d = bd.doubleValue();
        /* 20 */bd = null;
        /*    */
        /* 22 */return d;
        /*    */
    }
    /*    */
    /*    */

    public static double sum(double d1, double d2) {
        /* 32 */BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        /* 33 */BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        /*    */
        /* 35 */return bd1.add(bd2).doubleValue();
        /*    */
    }
    /*    */
    /*    */

    public static double sub(double d1, double d2) {
        /* 45 */BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        /* 46 */BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        /*    */
        /* 48 */return bd1.subtract(bd2).doubleValue();
        /*    */
    }
    /*    */
    /*    */

    public static double mul(double d1, double d2) {
        /* 57 */BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        /* 58 */BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        /*    */
        /* 60 */return bd1.multiply(bd2).doubleValue();
        /*    */
    }
    /*    */
    /*    */

    public static double div(double d1, double d2, int scale) {
        /* 73 */BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        /* 74 */BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        /*    */
        /* 76 */return bd1.divide(bd2, scale, 4).doubleValue();
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\webui\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.webui.DoubleProcess
 * JD-Core Version:    0.6.1
 */