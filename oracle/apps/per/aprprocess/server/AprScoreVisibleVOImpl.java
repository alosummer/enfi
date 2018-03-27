package cux.oracle.apps.per.aprprocess.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
// ---------------------------------------------------------------------
// ---    控制审批人界面，手工评分指标，审批人评分列的显示
// ---   created by yang.gang. 2016-11-7
// ---------------------------------------------------------------------
public class AprScoreVisibleVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public AprScoreVisibleVOImpl() {
    }

    /* 新增一行，审批过程中，根据当前人员所在的审批层级，控制分数列的显示 */

    public void AddRow(Integer AprSeq) {
        this.setMaxFetchSize(0);
        this.executeQuery();
        this.insertRow(this.createRow());
        Row row = this.first();

        if (AprSeq == 1) {
            row.setAttribute("AprScoregroupVisible", Boolean.FALSE);
        } else if (AprSeq == 2) {
            row.setAttribute("AprScoregroupVisible", Boolean.TRUE);
            row.setAttribute("AprScore1Visible", Boolean.TRUE);
            row.setAttribute("AprScore2Visible", Boolean.FALSE);
            row.setAttribute("AprScore3Visible", Boolean.FALSE);
        } else if (AprSeq == 3) {
            row.setAttribute("AprScoregroupVisible", Boolean.TRUE);
            row.setAttribute("AprScore1Visible", Boolean.TRUE);
            row.setAttribute("AprScore2Visible", Boolean.TRUE);
            row.setAttribute("AprScore3Visible", Boolean.FALSE);
        } else if (AprSeq == 4) {
            row.setAttribute("AprScoregroupVisible", Boolean.TRUE);
            row.setAttribute("AprScore1Visible", Boolean.TRUE);
            row.setAttribute("AprScore2Visible", Boolean.TRUE);
            row.setAttribute("AprScore3Visible", Boolean.TRUE);
        } else {
            row.setAttribute("AprScoregroupVisible", Boolean.FALSE);
            row.setAttribute("AprScore1Visible", Boolean.FALSE);
            row.setAttribute("AprScore2Visible", Boolean.FALSE);
            row.setAttribute("AprScore3Visible", Boolean.FALSE);
        }
    }


    /* 新增一行，审批后查看，根据当前人员所在的审批层级，控制分数列的显示 */

    public void AddRowReadOnly(Integer AprSeq) {
        this.setMaxFetchSize(0);
        this.executeQuery();
        this.insertRow(this.createRow());
        Row row = this.first();

        if (AprSeq == 1) {
            row.setAttribute("AprScoregroupVisible", Boolean.TRUE);
            row.setAttribute("AprScore1Visible", Boolean.TRUE);
            row.setAttribute("AprScore2Visible", Boolean.FALSE);
            row.setAttribute("AprScore3Visible", Boolean.FALSE);
        } else if (AprSeq == 2) {
            row.setAttribute("AprScoregroupVisible", Boolean.TRUE);
            row.setAttribute("AprScore1Visible", Boolean.TRUE);
            row.setAttribute("AprScore2Visible", Boolean.TRUE);
            row.setAttribute("AprScore3Visible", Boolean.FALSE);
        } else if (AprSeq == 3) {
            row.setAttribute("AprScoregroupVisible", Boolean.TRUE);
            row.setAttribute("AprScore1Visible", Boolean.TRUE);
            row.setAttribute("AprScore2Visible", Boolean.TRUE);
            row.setAttribute("AprScore3Visible", Boolean.TRUE);
        } else if (AprSeq == 4) {
            row.setAttribute("AprScoregroupVisible", Boolean.TRUE);
            row.setAttribute("AprScore1Visible", Boolean.TRUE);
            row.setAttribute("AprScore2Visible", Boolean.TRUE);
            row.setAttribute("AprScore3Visible", Boolean.TRUE);
        } else {
            row.setAttribute("AprScoregroupVisible", Boolean.FALSE);
            row.setAttribute("AprScore1Visible", Boolean.FALSE);
            row.setAttribute("AprScore2Visible", Boolean.FALSE);
            row.setAttribute("AprScore3Visible", Boolean.FALSE);
        }
    }

}
