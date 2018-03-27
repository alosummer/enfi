package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
// ---------------------------------------------------------------------
// ---    绩效管理_员工自助，绩效合同界面，显示选定绩效合同的信息
// ---     created by yang.gang,2016-4-22
// ---------------------------------------------------------------------
public class AprEmpSelfDetailVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprEmpSelfDetailVOImpl() {
    }


    public void initQuery(Integer iAprID) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, iAprID);
        executeQuery();
    }

    /* 获取VO中属性值
     * 返回 值
     *     no_data, vo没有记录
     * */

    public String GetAttrValue(String Attr) {
        int rowCount = this.getRowCount();
        if (rowCount == 0)
            return "NO_DATA";

        RowSetIterator deptPersonIter = 
            this.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);

        Row pRow = deptPersonIter.getRowAtRangeIndex(0);
        String strValue = this.getRowAttribute(pRow, Attr);
        deptPersonIter.closeRowSetIterator();

        return strValue;
    }

    private String getRowAttribute(Row pRow, String strAttrName) {
        if (pRow.getAttribute(strAttrName) != null) {
            return pRow.getAttribute(strAttrName).toString();
        } else
            return "";
    }
}
