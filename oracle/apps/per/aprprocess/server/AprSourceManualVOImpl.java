package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
// ---------------------------------------------------------------------
// ---    绩效管理_员工自助，员工自评打分页面， 手工评分指标列表  
// ---    created by yang.gang,2016-11-7
// ---------------------------------------------------------------------
public class AprSourceManualVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprSourceManualVOImpl() {
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

    /* 获取分数自动合计 */

    public Float GetScoreTotal(Integer aprseq) {
        Float fTotal = 0f;
        int rowCount = this.getRowCount();
        RowSetIterator deptPersonIter = 
            this.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String strWeight = this.getRowAttribute(pRow, "Weight");
            String AprScoreValue1 = 
                this.getRowAttribute(pRow, "AprScoreValue1");
            String AprScoreValue2 = 
                this.getRowAttribute(pRow, "AprScoreValue2");
            String AprScoreValue3 = 
                this.getRowAttribute(pRow, "AprScoreValue3");

            Float fWeight = 0f;
            Float fScoreValue = 0f;
            Float fScoreValue1 = 0f;
            Float fScoreValue2 = 0f;
            Float fScoreValue3 = 0f;
            if (this.isFloat(strWeight))
                fWeight = Float.parseFloat(strWeight);
            if (this.isFloat(AprScoreValue1))
                fScoreValue1 = Float.parseFloat(AprScoreValue1);
            if (this.isFloat(AprScoreValue2))
                fScoreValue2 = Float.parseFloat(AprScoreValue2);
            if (this.isFloat(AprScoreValue3))
                fScoreValue3 = Float.parseFloat(AprScoreValue3);

            if (aprseq == 4) {
                if (fScoreValue3 > 0f)
                    fScoreValue = fScoreValue3;
                else if (fScoreValue2 > 0f)
                    fScoreValue = fScoreValue2;
                else if (fScoreValue1 > 0f)
                    fScoreValue = fScoreValue1;
            } else if (aprseq == 3) {
                if (fScoreValue2 > 0f)
                    fScoreValue = fScoreValue2;
                else if (fScoreValue1 > 0f)
                    fScoreValue = fScoreValue1;
            } else if (aprseq == 2) {
                if (fScoreValue1 > 0f)
                    fScoreValue = fScoreValue1;
            }

            if (fScoreValue > 0f)
                fTotal += ((fScoreValue * fWeight) / 100);
            ;

        }
        deptPersonIter.closeRowSetIterator();
        fTotal = (float)(Math.round(fTotal * 100)) / 100;
        return fTotal;
    }

    private String getRowAttribute(Row pRow, String strAttrName) {
        if (pRow.getAttribute(strAttrName) != null) {
            return pRow.getAttribute(strAttrName).toString();
        } else
            return "";
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
