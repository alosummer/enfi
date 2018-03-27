package cux.oracle.apps.per.bonus.project.server;

/*
 * 项目预算信息
 * */
public class PrjBudgetItem {
    public PrjBudgetItem() {
    }

    public double Budget; //预算总额(元)
    public double IssueBudget; //可发放预算总额（元）
    public double ReleaseTotal; //已发放总额(元)
    public double IssueTotal; //可发放总额(元)
}
