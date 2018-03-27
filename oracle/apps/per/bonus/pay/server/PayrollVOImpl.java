package cux.oracle.apps.per.bonus.pay.server;

import java.io.Reader;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Types;

import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PayrollVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public PayrollVOImpl() {
    }

    public void initQuery(int iDistID) {
        String strSQL = this.getQuerySQL(iDistID);
        cancelQuery();
        setQuery(strSQL);
        setMaxFetchSize(-1);
        executeQuery();
    }


    /* 获取查询SQL */

    private String getQuerySQL(int iDistID) {
        PayAMImpl amImpl = (PayAMImpl)this.getApplicationModule();
        CallableStatement cs = null;
        OADBTransaction transaction = amImpl.getOADBTransaction();
        Clob cSQL = null;
        String strSQL = "";
        cs = 
 transaction.createCallableStatement("call CUX_HR_BONUS.GET_PAYROLL_SQL(?, ?)", 
                                     1);
        try {
            cs.setInt(1, iDistID);
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
    } //end getQuerySQL
}
