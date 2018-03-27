package cux.oracle.apps.per.aprprocess.server;

import java.util.Vector;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// 绩效管理_员工自助，入口界面，显示人员绩效合同列表VO
// created by yang.gang,2016-4-22
public class AprEmpSelfListVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprEmpSelfListVOImpl() {
    }

    //  默认显示本年的记录

    public void initQuery() {
        setWhereClause(null);
        setWhereClauseParams(null);
        setWhereClause(" APPRAISAL_YEAR = to_char(SYSDATE,'yyyy')");
        executeQuery();
    }

    //  查询年度范围数据

    public void initQuery(String strYearFrom, String strYearTo) {
        StringBuffer whereClause = new StringBuffer(500);
        int clauseCount = 0;

        setWhereClause(null);
        setWhereClauseParams(null);

        if (strYearFrom != null && !"".endsWith(strYearFrom.trim())) {
            clauseCount++;
            whereClause.append("APPRAISAL_YEAR >= '" + strYearFrom.trim() + 
                               "'");
        }

        if (strYearTo != null && !"".endsWith(strYearTo.trim())) {
            clauseCount++;
            if (clauseCount > 0)
                whereClause.append(" and");
            whereClause.append(" APPRAISAL_YEAR <= '" + strYearTo.trim() + 
                               "'");
        }

        if (clauseCount > 0)
            this.setWhereClause(whereClause.toString());
        this.executeQuery();
    }


}
