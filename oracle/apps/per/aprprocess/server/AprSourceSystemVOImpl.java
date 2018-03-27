package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
// ---------------------------------------------------------------------
// ---    绩效管理_员工自助，员工自评打分页面， 系统来源指标列表  
// ---    created by yang.gang,2016-11-7
// ---------------------------------------------------------------------
public class AprSourceSystemVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprSourceSystemVOImpl() {
    }

    public void initQuery(Integer iAprID) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, iAprID);
        executeQuery();
    }

    /* 获取权重自动合计 */

    public Float GetWeightTotal() {
        Float fTotal = 0f;
        int rowCount = this.getRowCount();
        RowSetIterator deptPersonIter = 
            this.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String strWeight = this.getRowAttribute(pRow, "Weight");

            try {
                Float dWeight = Float.parseFloat(strWeight);
                fTotal = fTotal + dWeight;
            } catch (NumberFormatException e) {
                ;
            }
        }
        deptPersonIter.closeRowSetIterator();
        return fTotal;
    }

    private String getRowAttribute(Row pRow, String strAttrName) {
        if (pRow.getAttribute(strAttrName) != null) {
            return pRow.getAttribute(strAttrName).toString();
        } else
            return "";
    }

    /* 获取分数自动合计 */

    public Float GetScoreTotal() {
        Float fTotal = 0f;
        int rowCount = this.getRowCount();
        RowSetIterator deptPersonIter = 
            this.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String ScoreValue = this.getRowAttribute(pRow, "ScoreValue");
            String strWeight = this.getRowAttribute(pRow, "Weight");
            Float fScoreValue = 0f;
            Float fWeight = 0f;
            if (this.isFloat(ScoreValue))
                fScoreValue = Float.parseFloat(ScoreValue);
            if (this.isFloat(strWeight))
                fWeight = Float.parseFloat(strWeight);
            if (fScoreValue > 0f)
                fTotal += ((fScoreValue * fWeight) / 100);
        }
        deptPersonIter.closeRowSetIterator();
        fTotal = (float)(Math.round(fTotal * 100)) / 100;
        return fTotal;
    }

    /* 判断字符串是否是浮点数 */

    private boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
