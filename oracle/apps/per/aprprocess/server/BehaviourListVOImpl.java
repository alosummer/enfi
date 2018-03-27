package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    评分，审批页面，行为规范列表VO
// ---    created by yang.gang. 2016-11-8
// ---------------------------------------------------------------------
public class BehaviourListVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public BehaviourListVOImpl() {
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
        row.setAttribute("KpiClass", "BEHAVIOUR");
        this.setCurrentRow(row);
    }
}
