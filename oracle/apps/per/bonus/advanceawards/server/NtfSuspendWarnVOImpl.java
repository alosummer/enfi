package cux.oracle.apps.per.bonus.advanceawards.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    暂停分配人员发放记录 - 部门奖金提醒
// ---    added by yang.gang, 2016-5-29
// ---------------------------------------------------------------------
public class NtfSuspendWarnVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public NtfSuspendWarnVOImpl() {
    }

    /**Gets the bind variable value for smonth
     */
    public String getsmonth() {
        return (String)getNamedWhereClauseParam("smonth");
    }

    /**Sets <code>value</code> for bind variable smonth
     */
    public void setsmonth(String value) {
        setNamedWhereClauseParam("smonth", value);
    }

    /**Gets the bind variable value for iorgid
     */
    public Integer getiorgid() {
        return (Integer)getNamedWhereClauseParam("iorgid");
    }

    /**Sets <code>value</code> for bind variable iorgid
     */
    public void setiorgid(Integer value) {
        setNamedWhereClauseParam("iorgid", value);
    }

    public void initQuery(String strMonth, Integer iOrgID) {
        setWhereClause(null);
        setWhereClauseParams(null);
        this.setsmonth(strMonth);
        this.setiorgid(iOrgID);
        setMaxFetchSize(-1);
        executeQuery();
    }
}
