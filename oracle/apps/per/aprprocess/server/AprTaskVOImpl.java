package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// --- 绩效管理_员工自助，目标设定页面， 工作任务列表  
// --- created by yang.gang,2016-4-25
// ---------------------------------------------------------------------
public class AprTaskVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprTaskVOImpl() {
    }

    public void initQuery(Integer iAprID) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, iAprID);
        executeQuery();
    }

    public void AddNewRow(Integer iAprID) {
        Row row = this.createRow();
        int index = this.getRangeIndexOf(this.last());
        this.insertRowAtRangeIndex(index + 1, row);
        row.setNewRowState(Row.STATUS_INITIALIZED);

        OAApplicationModule am = (OAApplicationModule)getApplicationModule();
        Number contractId = 
            am.getOADBTransaction().getSequenceValue("CUX_CONTRACT_S");

        row.setAttribute("ContractId", contractId);
        row.setAttribute("AppraisalId", iAprID);
        row.setAttribute("KpiClass", "TASK");
        row.setAttribute("KpiArea", "TASK");
        row.setAttribute("KpiAreaDesc", "工作任务");
        this.setCurrentRow(row);
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

}
