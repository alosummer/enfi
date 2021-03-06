package cux.oracle.apps.per.bonus.awardsreport.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DeptDetailsVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public DeptDetailsVOImpl() {
    }

    public void initSQL(String queryYear, String deptName) {
        StringBuffer sbfSQLValue = new StringBuffer(1000);
        sbfSQLValue.append("SELECT DISTINCT (CBT.TYPE_NAME),\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "01,\n" + 
                           "                                                 CBT.TYPE_NAME) AS JAN_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "02,\n" + 
                           "                                                 CBT.TYPE_NAME) AS FEB_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "03,\n" + 
                           "                                                 CBT.TYPE_NAME) AS MAR_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "04,\n" + 
                           "                                                 CBT.TYPE_NAME) AS APR_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "05,\n" + 
                           "                                                 CBT.TYPE_NAME) AS MAY_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "06,\n" + 
                           "                                                 CBT.TYPE_NAME) AS JUN_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "07,\n" + 
                           "                                                 CBT.TYPE_NAME) AS JUL_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "08,\n" + 
                           "                                                 CBT.TYPE_NAME) AS AUG_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "09,\n" + 
                           "                                                 CBT.TYPE_NAME) AS SEP_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "10,\n" + 
                           "                                                 CBT.TYPE_NAME) AS OCT_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "11,\n" + 
                           "                                                 CBT.TYPE_NAME) AS NOV_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "12,\n" + 
                           "                                                 CBT.TYPE_NAME) AS DEC_QUOTA,\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "01,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "02,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "03,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "04,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "05,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "06,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "07,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "08,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "09,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "10,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "11,\n" + 
                           "                                                 CBT.TYPE_NAME) +\n" + 
                           "                APPS.CUX_HR_BONUS.GET_DEPT_QUOTA(CBDD.DEP_NAME,\n" + 
                           "                                                 " + 
                           queryYear + "12,\n" + 
                           "                                                 CBT.TYPE_NAME) AS TOTAL\n" + 
                           "  FROM APPS.CUX_BONUS_DEP_DISTRIBUTION CBDD, APPS.CUX_BONUS_TYPE CBT\n" + 
                           " WHERE CBDD.TYPE_ID = CBT.TYPE_ID\n" + 
                           "   AND CBDD.DEP_NAME = '" + deptName + "'\n" + 
                           "   AND CBDD.DISTRIBUTION_PERIOD BETWEEN " + 
                           queryYear + "01 AND " + queryYear + "12\n" + 
                           "   AND CBDD.DISTRIBUTION_STATUS = '已批准'\n" + 
                           " ORDER BY NLSSORT(CBT.TYPE_NAME, 'NLS_SORT=SCHINESE_PINYIN_M')\n");
        //        System.out.println(sbfSQLValue);
        cancelQuery();
        setQuery(sbfSQLValue.toString());
        executeQuery();
    }
}
