package cux.oracle.apps.per.bonus.advanceawards.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdvAwardsDeptLovVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AdvAwardsDeptLovVOImpl() {
    }

    /*  add by yang.gang, 2015-1-12
        筛除赵立明002880 通过 上海海外事业部部长 从而获取了股份海外和上海海外整个的奖金分配权限
        赵立明 集团合同商务 部长;  上海海外事业部 部长;  股份海外事业部 副部长
    */

    public void initQuery(int personId) {
        setWhereClause(null);
        setWhereClauseParams(null);
        setperson_id(personId);
        executeQuery();
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

    /**Gets the bind variable value for person_id
     */
    public Integer getperson_id() {
        return (Integer)getNamedWhereClauseParam("person_id");
    }

    /**Sets <code>value</code> for bind variable person_id
     */
    public void setperson_id(Integer value) {
        setNamedWhereClauseParam("person_id", value);
    }
}