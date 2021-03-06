package cux.oracle.apps.per.bonus.advanceawards.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdvAwardsOfficeLovVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AdvAwardsOfficeLovVOImpl() {
    }
    /* added by yang.gang, 2015-6-5
     * 若部长只有1个计奖部分，则返回部门ID和名称，用以直接跳到后续页面
     * return:  部门ID#=#部门名称
     * */

    public String GetDeptInfo() {
        int icount = this.getRowCount();
        if (icount == 0 || icount > 1)
            return "";
        Row pRow = this.getRowAtRangeIndex(0);
        String DeptName = pRow.getAttribute("DeptName").toString();
        String OrganizationId = pRow.getAttribute("OrganizationId").toString();
        return OrganizationId.concat("#=#").concat(DeptName);
    }

    public void initQuery(int personId) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setperson_id(personId);
        executeQuery();
    }

    /**Gets the bind variable value for person_id
     */
    public Integer getperson_id() {
        return (Integer)getNamedWhereClauseParam("person_id");
    }

    /**Sets <code>value</code> for bind variable person_id
     */
    public void setperson_id(Integer value) {
        this.setWhereClauseParams(new Object[] { value, value });
        //setNamedWhereClauseParam("person_id", value);
    }
}
