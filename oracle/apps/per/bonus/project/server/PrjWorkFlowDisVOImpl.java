package cux.oracle.apps.per.bonus.project.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---    主管领导，股份董事长审批页面，点击详细信息时，显示本次审批的奖金数据
// ---------------------------------------------------------------------
public class PrjWorkFlowDisVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public PrjWorkFlowDisVOImpl() {
    }

    public void initQuery(String strGuid) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClauseParam(0, strGuid);
        setMaxFetchSize(-1);
        executeQuery();
    }
}
