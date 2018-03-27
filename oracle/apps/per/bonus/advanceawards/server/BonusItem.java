package cux.oracle.apps.per.bonus.advanceawards.server;

// -------------------------------
// --- 1次返回部门奖金信息，避免多次读取数据
// --- created by yang.gang, 2015-3-2
// -----------------------------
public class BonusItem {
    public BonusItem() {
    }
    public double totalBalance;
    public double monthBalance;
    public double deptPerNum;
    public double otherPerNum;
    public double totalDist;
}
