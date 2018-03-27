package cux.oracle.apps.per.bonus.project.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PrjNtfGroupOrgVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public PrjNtfGroupOrgVOImpl() {
    }

    /**Gets the bind variable value for month
     */
    public String getmonth() {
        return (String)getNamedWhereClauseParam("month");
    }

    /**Sets <code>value</code> for bind variable month
     */
    public void setmonth(String value) {
        setNamedWhereClauseParam("month", value);
    }

    /**Gets the bind variable value for org_id
     */
    public Integer getorg_id() {
        return (Integer)getNamedWhereClauseParam("org_id");
    }

    /**Sets <code>value</code> for bind variable org_id
     */
    public void setorg_id(Integer value) {
        setNamedWhereClauseParam("org_id", value);
    }

    public void initQuery(String strMonth, Integer iOrgID) {
        setWhereClause(null);
        setWhereClauseParams(null);
        this.setmonth(strMonth);
        this.setorg_id(iOrgID);
        setMaxFetchSize(-1);
        executeQuery();
    }
}
