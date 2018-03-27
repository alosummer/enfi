package cux.oracle.apps.per.aprprocess.server;

import cux.oracle.apps.per.bonus.project.server.ProjectAMImpl;

import java.io.Reader;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Types;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

// ---------------------------------------------------------------------
// ---    选择KPI Lov
// ---    created by yang.gang.2016-4-26
// ---------------------------------------------------------------------
public class KPILovVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public KPILovVOImpl() {
    }

    public boolean IsQuery = false;
    public Integer AppraisalId = 0;

    /* 根据 APPRAISAL_ID，初始化用户权限内的KPI指标 */

    public void initQuery(Integer iAprID) {
        String strSQL = this.getQuerySQL(iAprID);
        setFullSqlMode(this.FULLSQL_MODE_AUGMENTATION);
        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();

        this.AppraisalId = iAprID;
        this.IsQuery = true;
    }

    /* 获取查询SQL */

    private String getQuerySQL(Integer iAprID) {
        AprAMImpl amImpl = (AprAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        Clob cSQL = null;
        String strSQL = "";
        cs = 
 transaction.createCallableStatement("call APPS.CUX_JXKH_APRPROCESS_PKG.GET_KPI_LOV_SQL(?,?)", 
                                     1);
        try {
            cs.setInt(1, iAprID);
            cs.registerOutParameter(2, Types.CLOB);
            cs.executeUpdate();
            cSQL = cs.getClob(2);
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
