package cux.oracle.apps.per.aprprocess.server;

import java.io.Reader;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Types;

import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---     绩效管理_经理自助，入口界面，显示人员绩效合同列表VO
// ---     created by yang.gang,2016-4-27
// ---------------------------------------------------------------------
public class AprManagerListVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprManagerListVOImpl() {
    }

    //  默认显示本年的记录

    public void initQuery() {
        String strSQL = this.getQuerySQL();
        setFullSqlMode(this.FULLSQL_MODE_AUGMENTATION);
        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();
    }

    public void initQuery(String Phase, String EmpName, String Org, 
                          String YearFrom, String YearTo, String Status, 
                          String Performer) {
        String strSQL = 
            this.getQuerySQL(Phase, EmpName, Org, YearFrom, YearTo, Status, 
                             Performer);
        setFullSqlMode(this.FULLSQL_MODE_AUGMENTATION);
        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();
    }

    /* 获取查询SQL */

    private String getQuerySQL() {
        AprAMImpl amImpl = (AprAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        Clob cSQL = null;
        String strSQL = "";
        cs = 
 transaction.createCallableStatement("call APPS.CUX_JXKH_APRPROCESS_PKG.GET_MANAGER_LIST_VO_SQL(?)", 
                                     1);
        try {
            cs.registerOutParameter(1, Types.CLOB);
            cs.executeUpdate();
            cSQL = cs.getClob(1);
            Reader inStream = cSQL.getCharacterStream();
            char[] c = new char[(int)cSQL.length()];
            inStream.read(c);
            strSQL = new String(c);
            inStream.close();
            cs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return strSQL;
    }

    /* 获取查询SQL */

    private String getQuerySQL(String Phase, String EmpName, String OrgName, 
                               String YearFrom, String YearTo, String Status, 
                               String Performer) {
        AprAMImpl amImpl = (AprAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        Clob cSQL = null;
        String strSQL = "";
        cs = 
 transaction.createCallableStatement("call APPS.CUX_JXKH_APRPROCESS_PKG.GET_MANAGER_LIST_VO_SQL(?,?,?,?,?,?,?,?)", 
                                     1);
        try {
            cs.setString(1, Phase);
            cs.setString(2, EmpName);
            cs.setString(3, OrgName);
            cs.setString(4, YearFrom);
            cs.setString(5, YearTo);
            cs.setString(6, Status);
            cs.setString(7, Performer);
            cs.registerOutParameter(8, Types.CLOB);
            cs.executeUpdate();
            cSQL = cs.getClob(8);
            Reader inStream = cSQL.getCharacterStream();
            char[] c = new char[(int)cSQL.length()];
            inStream.read(c);
            strSQL = new String(c);
            inStream.close();
            cs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return strSQL;
    }
}
