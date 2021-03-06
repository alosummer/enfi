package cux.oracle.apps.per.review.server;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;

import java.sql.ResultSetMetaData;

import java.sql.Types;

import java.util.ArrayList;

import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAViewDef;

import oracle.jbo.AttributeDef;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class KpiReportAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public KpiReportAMImpl() {
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cux.oracle.apps.per.review.server", 
                                             "KpiReportAMLocal");
    }

    public String getSQL(String arg1, String arg2, OADBTransactionImpl odbt) {
        String sqlStr = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            preparedStatement = 
                    odbt.createPreparedStatement("select cux_jxkh_common_pkg.get_kpi_report_sql(?,?) from dual ", 
                                                 1);

            preparedStatement.setString(1, arg1);
            preparedStatement.setString(2, arg2);

            resultset = preparedStatement.executeQuery();
            if (resultset != null && resultset.next()) {
                sqlStr = resultset.getString(1);
            }
        } catch (Exception e) {
            throw OAException.wrapperException(e);

        } finally {
            try {
                if (preparedStatement != null) {

                    preparedStatement.close();
                }
                return sqlStr;
            } catch (Exception e) {
                throw OAException.wrapperException(e);
            }
        }
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @return ArrayList
     * v0.1 query two times, once for metadata,twice for data
     */
    public ArrayList dynamicVO(String arg1, String arg2) {
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)getOADBTransaction();
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;
        ResultSetMetaData rsmd = null;
        ArrayList list = null;
        String sqlStr = getSQL(arg1, arg2, oadbtransactionimpl);

        if (sqlStr == null) // null return;
            return null;

        try {
            System.out.println(sqlStr);
            preparedstatement = 
                    oadbtransactionimpl.createPreparedStatement(sqlStr, 1);
            resultset = preparedstatement.executeQuery();
            if (resultset != null) {
                rsmd = resultset.getMetaData();
                //create view object
                OAViewDef viewDef = oadbtransactionimpl.createViewDef();
                viewDef.setSql(sqlStr);
                viewDef.setExpertMode(true);
                viewDef.setViewObjectClass("oracle.apps.fnd.framework.server.OAViewObjectImpl");
                viewDef.setViewRowClass("oracle.apps.fnd.framework.server.OAViewRowImpl");
                //insert view object attribute
                list = new ArrayList(); // return to UI,for column label
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    viewDef.addSqlDerivedAttrDef(rsmd.getColumnLabel(i), 
                                                 rsmd.getColumnLabel(i), 
                                                 "java.lang.String", 
                                                 Types.VARCHAR, true, true, 
                                                 AttributeDef.READONLY);
                    list.add(rsmd.getColumnLabel(i));
                }
                //insert end;
                OAViewObject vo = 
                    (OAViewObject)findViewObject("RefObjectLovVO");
                if (vo != null) {
                    vo.remove();
                }
                OAViewObject vo1 = 
                    (OAViewObject)createViewObject("RefObjectLovVO", viewDef);
                vo1.setMaxFetchSize(-1);
                vo1.executeQuery();


            }
        } catch (Exception e) {
            throw OAException.wrapperException(e);

        } finally {
            try {
                if (preparedstatement != null) {

                    preparedstatement.close();
                }
                return list;
            } catch (Exception e) {
                throw OAException.wrapperException(e);
            }
        }


    }
}
