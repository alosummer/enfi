package cux.oracle.apps.per.bonus.advanceawards.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdvAwardsAttachmentVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AdvAwardsAttachmentVOImpl() {
    }

    public void initSQL(int deptOrgID, String distDate) {
        String searchDistDate = 
            distDate.substring(0, 4) + distDate.substring(5, 7);
        StringBuffer sbfSQLValue = new StringBuffer(1000);
        sbfSQLValue.append("SELECT DISTINCT (CBDD.DEP_DISTRIBUTION_ID)\n" + 
                           "  FROM CUX_BONUS_DEP_DISTRIBUTION CBDD\n" + 
                           " WHERE CBDD.Organization_Id = " + deptOrgID + 
                           "\n" + " AND CBDD.distribution_status = '已批准'\n" + 
                           " AND CBDD.distribution_period = " + 
                           searchDistDate + "\n" + 
                           " AND (SELECT ATTACHED_DOCUMENT_ID\n" + 
                           "          FROM FND_ATTACHED_DOCUMENTS\n" + 
                           "         WHERE ENTITY_NAME = 'DeptBonusAttachment'\n" + 
                           "           AND PK1_VALUE = CBDD.DEP_DISTRIBUTION_ID) IS NOT NULL");
        //        System.out.println(sbfSQLValue);
        cancelQuery();
        setQuery(sbfSQLValue.toString());
        executeQuery();
    }
}
