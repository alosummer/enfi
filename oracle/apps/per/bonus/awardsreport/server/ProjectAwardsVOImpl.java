package cux.oracle.apps.per.bonus.awardsreport.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ProjectAwardsVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public ProjectAwardsVOImpl() {
    }

    public void initSQL(String queryYear, String projectNumber) {
        if (projectNumber == null || "".equals(projectNumber)) {
            StringBuffer sbfSQLValue = new StringBuffer(1000);
            sbfSQLValue.append("SELECT distinct(PPA.SEGMENT1) AS PROJECT_NUMBER,\n" + 
                               "       PPA.NAME AS PROJECT_NAME,\n" + 
                               "       APPS.CUX_HR_BONUS.GET_PROJECT_MANAGER_NAME(PPA.PROJECT_ID) AS PROJECT_MANAGER,\n" + 
                               "       APPS.CUX_HR_BONUS.GET_PROJECT_BUDGET(PPA.PROJECT_ID) AS PROJECT_BUDGET,\n" + 
                               "       CBPD.DISTRIBUTION_DATE,\n" + 
                               "       NVL((SELECT SUM(CD.DISTRIBUTION_AMOUNT)\n" + 
                               "                      FROM APPS.CUX_BONUS_PERSON_DISTRIBUTION CD\n" + 
                               "                     WHERE CD.PROJECT_ID = PPA.PROJECT_ID\n" + 
                               "                       AND CD.DISTRIBUTION_DATE = CBPD.DISTRIBUTION_DATE),\n" + 
                               "                    0) AS DISTRIBUTION_AMOUNT\n" + 
                               "  FROM APPS.CUX_BONUS_PERSON_DISTRIBUTION CBPD,\n" + 
                               "       APPS.CUX_BONUS_TYPE                CBT,\n" + 
                               "       APPS.PA_PROJECTS_ALL               PPA\n" + 
                               " WHERE CBPD.TYPE_ID = CBT.TYPE_ID\n" + 
                               "   AND CBPD.PROJECT_ID = PPA.PROJECT_ID\n" + 
                               "   AND CBPD.DISTRIBUTION_DATE BETWEEN TO_DATE('" + 
                               queryYear + "-01-01', 'yyyy-mm-dd') AND\n" + 
                               "       TO_DATE('" + queryYear + 
                               "-12-31', 'yyyy-mm-dd')\n" + 
                               "   AND CBPD.DISTRIBUTION_STATUS = '已批准'\n" + 
                               "   AND CBPD.IMPORT_STATUS <> '已退回'\n" + 
                               " ORDER BY PPA.SEGMENT1");
            //            System.out.println(sbfSQLValue);
            cancelQuery();
            setQuery(sbfSQLValue.toString());
            executeQuery();
        } else {
            StringBuffer sbfSQLValue = new StringBuffer(1000);
            sbfSQLValue.append("SELECT distinct(PPA.SEGMENT1) AS PROJECT_NUMBER,\n" + 
                               "       PPA.NAME AS PROJECT_NAME,\n" + 
                               "       APPS.CUX_HR_BONUS.GET_PROJECT_MANAGER_NAME(PPA.PROJECT_ID) AS PROJECT_MANAGER,\n" + 
                               "       APPS.CUX_HR_BONUS.GET_PROJECT_BUDGET(PPA.PROJECT_ID) AS PROJECT_BUDGET,\n" + 
                               "       CBPD.DISTRIBUTION_DATE,\n" + 
                               "       NVL((SELECT SUM(CD.DISTRIBUTION_AMOUNT)\n" + 
                               "                      FROM APPS.CUX_BONUS_PERSON_DISTRIBUTION CD\n" + 
                               "                     WHERE CD.PROJECT_ID = PPA.PROJECT_ID\n" + 
                               "                       AND CD.DISTRIBUTION_DATE = CBPD.DISTRIBUTION_DATE),\n" + 
                               "                    0) AS DISTRIBUTION_AMOUNT\n" + 
                               "  FROM APPS.CUX_BONUS_PERSON_DISTRIBUTION CBPD,\n" + 
                               "       APPS.CUX_BONUS_TYPE                CBT,\n" + 
                               "       APPS.PA_PROJECTS_ALL               PPA\n" + 
                               " WHERE CBPD.TYPE_ID = CBT.TYPE_ID\n" + 
                               "   AND CBPD.PROJECT_ID = PPA.PROJECT_ID\n" + 
                               "   AND CBPD.DISTRIBUTION_DATE BETWEEN TO_DATE('" + 
                               queryYear + "-01-01', 'yyyy-mm-dd') AND\n" + 
                               "       TO_DATE('" + queryYear + 
                               "-12-31', 'yyyy-mm-dd')\n" + 
                               "   and ppa.segment1 = '" + projectNumber + 
                               "'\n" + 
                               "   AND CBPD.DISTRIBUTION_STATUS = '已批准'\n" + 
                               "   AND CBPD.IMPORT_STATUS <> '已退回'\n" + 
                               " ORDER BY PPA.SEGMENT1");
            //            System.out.println(sbfSQLValue);
            cancelQuery();
            setQuery(sbfSQLValue.toString());
            executeQuery();
        }
    }
}