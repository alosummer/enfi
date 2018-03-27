package cux.oracle.apps.per.bonus.project.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;

/**
 * 项目管理奖 发放通知详细页面使用的VO
 * created by yang.gang, 2015-12-1
 * 每月初运行“cux.项目管理奖金发放通知”请求，通知股份、集团、上海、冶金有奖金职责的人员
 * 显示本月发放人员在各子公司，且项目归口部门与奖金被发放人员不在同一个公司、或者被发放人员未挂工资单的发放记录 
 */
public class PrjNtfOrgDisVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public PrjNtfOrgDisVOImpl() {
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
