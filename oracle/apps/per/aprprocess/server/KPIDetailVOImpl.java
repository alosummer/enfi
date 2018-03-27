package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
// ---------------------------------------------------------------------
// ---    获取某个KPI指标的具体属性
// ---------------------------------------------------------------------
public class KPIDetailVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public KPIDetailVOImpl() {
    }

    public void initQuery(Integer iKpiId) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, iKpiId);
        executeQuery();
    }

    /* 获取某个属性的值 */

    public String GetKPIAttr(String strAttr) {
        String strValue = "";
        int rowCount = this.getRowCount();
        if (rowCount == 0)
            return "";
        RowSetIterator deptPersonIter = 
            this.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);

        Row pRow = deptPersonIter.getRowAtRangeIndex(0);

        if (pRow.getAttribute(strAttr) != null) {
            strValue = pRow.getAttribute(strAttr).toString();
        }
        deptPersonIter.closeRowSetIterator();
        return strValue;
    }
}
