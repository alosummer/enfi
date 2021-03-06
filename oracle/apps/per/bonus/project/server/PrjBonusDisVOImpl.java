package cux.oracle.apps.per.bonus.project.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PrjBonusDisVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public PrjBonusDisVOImpl() {
    }

    /* 暂未使用该方法 */

    public void initQuery(int iPrjID) {
        String strSQL = 
            "SELECT CBP.LOT_ID,       \n" + "       CBP.EMP_NO,\n" + 
            "       CBP.EMP_NAME,\n" + "       CBP.ORG_NAME,\n" + 
            "       CBP.DISTRIBUTION_AMOUNT,\n" + "       CBP.NOTE,\n" + 
            "       CBP.APPROVED_STATUS,\n" + 
            "       to_char(CBP.APPROVED_DATE,'yyyy-mm-dd') as APPROVED_DATE,\n" + 
            "       CBP.Import_Status\n" + 
            "  FROM CUX_BONUS_PRJ_DISTRIBUTION CBP\n" + 
            " WHERE CBP.PROJECT_ID = " + String.valueOf(iPrjID) + "\n " + 
            " order by nlssort(CBP.EMP_NAME,'NLS_SORT=SCHINESE_PINYIN_M')";
        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();
    }
}
