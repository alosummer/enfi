package cux.oracle.apps.per.bonus.advanceawards.server;

import java.sql.CallableStatement;

import java.sql.Types;

import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    部门奖金发放页面，其他人员VO
// ---    
// ---------------------------------------------------------------------
public class AdvAwardsOtherPerVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AdvAwardsOtherPerVOImpl() {
    }

    /* 部长操作页面VO显示 */

    public void initQuery(String searchBonusType, String searchDistDate, 
                          int deptOrgID) {
        int iCount = 
            this.getDistedCount(searchBonusType, searchDistDate, deptOrgID);
        String strSQL = "";
        if (iCount == 0)
            strSQL = 
                    this.getDistedSQL(searchBonusType, searchDistDate, deptOrgID, 
                                      "1");
        else
            strSQL = 
                    this.getDistedSQL(searchBonusType, searchDistDate, deptOrgID, 
                                      "0");

        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();
    }

    /* 部长提交后，刷新VO，只显示当月数据 */

    public void initQueryForSubmit(String searchBonusType, 
                                   String searchDistDate, int deptOrgID) {
        String strSQL = 
            this.getDistedSQL(searchBonusType, searchDistDate, deptOrgID, "0");
        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();
    }

    //  查询本月已经发放的数据

    private int getDistedCount(String strBonusType, String strDistDate, 
                               int deptOrgID) {
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        int iCount = 0;
        cs = 
 transaction.createCallableStatement("call CUX_HR_BONUS.GET_DESTED_OTHER_NUMBER(?, ?, ?, ?)", 
                                     1);
        try {
            cs.setInt(1, deptOrgID);
            cs.setString(2, strBonusType);
            cs.setString(3, strDistDate);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.executeUpdate();
            iCount = cs.getInt(4);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            iCount = 0;
        }
        return iCount;
    }

    /* 获取查询语句
     * strFlag: '0' 查询本月SQL,  '1' 查询上月
     * */

    private String getDistedSQL(String strBonusType, String strDistDate, 
                                int deptOrgID, String strFlag) {
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        String strSQL = "";
        cs = 
 transaction.createCallableStatement("call CUX_HR_BONUS.GET_DESTED_OTHER_SQL(?, ?, ?, ?,?)", 
                                     1);
        try {
            cs.setInt(1, deptOrgID);
            cs.setString(2, strBonusType);
            cs.setString(3, strDistDate);
            cs.setString(4, strFlag);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.executeUpdate();
            strSQL = cs.getString(5);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return strSQL;
    }
}
