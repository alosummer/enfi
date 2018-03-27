package cux.oracle.apps.per.bonus.advanceawards.server;

import java.io.Reader;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Types;

import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    部门奖金发放页面，部门人员VO
// ---------------------------------------------------------------------
public class AdvAwardsDeptPersonVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AdvAwardsDeptPersonVOImpl() {
    }

    /* 部长操作页面VO显示 */

    public void initQuery(String bonusType, String distDate, int deptOrgID) {
        int iCount = getDistedCount(bonusType, distDate, deptOrgID);
        String strSQL = "";
        if (deptOrgID == -2 && 
            iCount == 0) //赛迪集团中管及以上（含集团、股份、上海）,如果本月人数为0，则自动带出上月的发放人员             
            strSQL = getDistedMidMgrSQL(bonusType, distDate, "1");
        else if (deptOrgID == -2 && iCount > 0)
            strSQL = getDistedMidMgrSQL(bonusType, distDate, "0");
        else if (iCount == 0) // -- 非 "赛迪集团中管及以上（含集团、股份、上海）" 部门                
            strSQL = getDistedDeptSQL(deptOrgID, bonusType, distDate, "1");
        else
            strSQL = getDistedDeptSQL(deptOrgID, bonusType, distDate, "0");

        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();
    }

    /* 部长提交后，刷新VO，只显示当月数据 */

    public void initQueryForSubmit(String bonusType, String distDate, 
                                   int deptOrgID) {
        String strSQL = "";
        if (deptOrgID == 
            -2) //赛迪集团中管及以上（含集团、股份、上海）,如果本月人数为0，则自动带出上月的发放人员        
            strSQL = getDistedMidMgrSQL(bonusType, distDate, "0");
        // -- 非 "赛迪集团中管及以上（含集团、股份、上海）" 部门        
        else
            strSQL = getDistedDeptSQL(deptOrgID, bonusType, distDate, "0");

        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();
    }

    private int getDistedCount(String strBonusType, String strDistDate, 
                               int deptOrgID) {
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        int iCount = 0;
        cs = 
 transaction.createCallableStatement("call CUX_HR_BONUS.GET_DESTED_DEPT_NUMBER(?, ?, ?, ?)", 
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

    /* 中管人员奖金发放情况SQL,strFlag: '0' 查询本月SQL,  '1' 查询上月 */

    private String getDistedMidMgrSQL(String strBonusType, String strDistDate, 
                                      String strFlag) {
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        Clob cSQL = null;
        String strSQL = "";
        cs = 
 transaction.createCallableStatement("call CUX_HR_BONUS.GET_DESTED_MIDMGR_SQL(?, ?, ?, ?)", 
                                     1);
        try {
            cs.setString(1, strBonusType);
            cs.setString(2, strDistDate);
            cs.setString(3, strFlag);
            cs.registerOutParameter(4, Types.CLOB);
            cs.executeUpdate();
            cSQL = cs.getClob(4);
            Reader inStream = cSQL.getCharacterStream();
            char[] c = new char[(int)cSQL.length()];
            inStream.read(c);
            strSQL = new String(c);
            inStream.close();
            cs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(strSQL);
        return strSQL;
    }

    /* 部门发放情况SQL,strFlag: '0' 查询本月SQL,  '1' 查询上月 */

    private String getDistedDeptSQL(int iDeptID, String strBonusType, 
                                    String strDistDate, String strFlag) {
        AdvanceAwardsAMImpl amImpl = 
            (AdvanceAwardsAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        Clob cSQL = null;
        String strSQL = "";
        cs = 
 transaction.createCallableStatement("call CUX_HR_BONUS.GET_DESTED_DEPT_SQL(?, ?, ?, ?, ?)", 
                                     1);
        try {
            cs.setInt(1, iDeptID);
            cs.setString(2, strBonusType);
            cs.setString(3, strDistDate);
            cs.setString(4, strFlag);
            cs.registerOutParameter(5, Types.CLOB);
            cs.executeUpdate();
            cSQL = cs.getClob(5);
            Reader inStream = cSQL.getCharacterStream();
            char[] c = new char[(int)cSQL.length()];
            inStream.read(c);
            strSQL = new String(c);
            inStream.close();
            cs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("AdvAwardsDeptPersonVOImpl:" + strSQL);
        return strSQL;
    }

}
