/*    */package cux.oracle.apps.per.bonus.member.server;
/*    */
/*    */import oracle.apps.fnd.framework.server.OAViewObjectImpl;
/*    */
/*    */public class SpecialAwardsPersonVOImpl extends OAViewObjectImpl {
    /*    */

    public void initQuery(String searchBonusType, String searchDistDate) {
        /* 20 */if ((searchBonusType != null) && 
                    (!"".equals(searchDistDate)) && (searchDistDate != null) && 
                    (!"".equals(searchDistDate))) {
            /* 21 */setWhereClause("TYPE_NAME = :1 AND DISTRIBUTION_DATE = TO_DATE(:2, 'yyyy-mm')");
            /* 22 */setWhereClauseParams(null);
            /* 23 */setWhereClauseParam(0, searchBonusType);
            /* 24 */setWhereClauseParam(1, searchDistDate);
            /* 25 */executeQuery();
            /*    */
        }
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\member\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.member.server.SpecialAwardsPersonVOImpl
 * JD-Core Version:    0.6.1
 */