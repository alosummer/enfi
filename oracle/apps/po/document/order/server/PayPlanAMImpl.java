package cux.oracle.apps.po.document.order.server;

import java.text.DecimalFormat;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;
import oracle.jbo.Transaction;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PayPlanAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public PayPlanAMImpl() {
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cisdi.oracle.apps.po.document.server", 
                                             "PayPlanAMLocal");
    }


    /**Container's getter for PayTypeVO1
     */
    public OAViewObjectImpl getPayTypeVO1() {
        return (OAViewObjectImpl)findViewObject("PayTypeVO1");
    }

    public void initPayPlan(String poHdrId) {
        PayPlanVOImpl vo = this.getPayPlanVO1();
        if (vo == null) {
            MessageToken[] errTokens = 
            { new MessageToken("OBJECT_NAME", "PayPlanVO1") };
            throw new OAException("AK", "FWK_TBX_OBJECT_NOT_FOUND", errTokens);
        }
        vo.initPayPlan(poHdrId);


        PayPlanHeaderDisVOImpl disvo = this.getPayPlanHeaderDisVO1();
        disvo.setWhereClauseParams(null);
        disvo.setWhereClauseParams(new Object[] { poHdrId });
        disvo.executeQuery();
    }

    public void initPayPlanHis(String poHdrId) {
        PayPlanHisVOImpl vo = this.getPayPlanHisVO1();
        if (vo == null) {
            MessageToken[] errTokens = 
            { new MessageToken("OBJECT_NAME", "PayPlanHisVO1") };
            throw new OAException("AK", "FWK_TBX_OBJECT_NOT_FOUND", errTokens);
        }
        vo.initPayPlanHis(poHdrId);


    }

    public void createPayPlan(String poHdrId) {
        PayPlanVOImpl vo = this.getPayPlanVO1();
        if (!vo.isPreparedForExecution()) {
            initPayPlan(poHdrId);
        }

        Row row = vo.createRow();
        //vo.insertRowAtRangeIndex(vo.getRowCount(),row);
        row.setAttribute("LineId", 
                         this.getSequenceValue("CUX_PO_PAY_PLAN_T_S"));
        vo.insertRow(row);
        vo.setCurrentRow(row);
        row.setNewRowState(Row.STATUS_INITIALIZED);

    }

    public void setLineNum() {
        Number maxLineNum = new Number(0);
        PayPlanVOImpl vo = this.getPayPlanVO1();
        PayPlanVORowImpl row = null;

        int fetchedRowCount = vo.getFetchedRowCount();
        RowSetIterator lineNumIter = vo.createRowSetIterator("lineNumIter");
        if (fetchedRowCount > 1) {
            lineNumIter.setRangeStart(0);
            lineNumIter.setRangeSize(fetchedRowCount);
            for (int i = 0; i < fetchedRowCount; i++) {
                row = (PayPlanVORowImpl)lineNumIter.getRowAtRangeIndex(i);
                if (row.getLineNum() != null) {
                    if (maxLineNum.compareTo(row.getLineNum()) < 0) {
                        maxLineNum = row.getLineNum();
                    }
                }
            }
        }
        maxLineNum = maxLineNum.add(1);
        Row row2 = vo.getCurrentRow();
        row2.setAttribute("LineNum", maxLineNum);

    }

    public void calculate(String strTotal) {
        Number maxLineNum = new Number(0);
        double total = Double.parseDouble(strTotal);
        double lastRowInitAmt = 0;
        double lastRowNewAmt = 0;
        double currRowInitPercent = 0;
        double currRowNewPercent = 0;
        double totalInitPercent = 0;
        double totalNewPercent = 0;
        PayPlanVOImpl vo = this.getPayPlanVO1();
        PayPlanVORowImpl row = null;
        DecimalFormat df = new DecimalFormat("0.00");

        int fetchedRowCount = vo.getRowCount();
        RowSetIterator lineNumIter = vo.createRowSetIterator("lineNumIter");
        if (fetchedRowCount > 0) {
            lineNumIter.setRangeStart(0);
            lineNumIter.setRangeSize(fetchedRowCount);
            for (int i = 0; i < fetchedRowCount; i++) {
                row = (PayPlanVORowImpl)lineNumIter.getRowAtRangeIndex(i);
                try {
                    if (!"null".equals(String.valueOf(row.getAttribute3()))) {
                        currRowInitPercent = 
                                Double.parseDouble(String.valueOf(row.getAttribute3()));
                        totalInitPercent = 
                                totalInitPercent + currRowInitPercent;
                        if (i == fetchedRowCount && totalInitPercent == 100) {
                            row.setInitAmt(new Number(total - lastRowInitAmt));
                        } else {
                            row.setInitAmt(new Number(df.format(total * 
                                                                currRowInitPercent / 
                                                                100)));
                        }
                        lastRowInitAmt = 
                                lastRowInitAmt + total * currRowInitPercent / 
                                100;
                    }
                    if (!"null".equals(String.valueOf(row.getAttribute4()))) {
                        currRowNewPercent = 
                                Double.parseDouble(String.valueOf(row.getAttribute4()));
                        totalNewPercent = totalNewPercent + currRowNewPercent;
                        if (i == fetchedRowCount && totalNewPercent == 100) {
                            row.setNewAmt(new Number(new Number(total - 
                                                                lastRowNewAmt)));
                        } else {
                            row.setNewAmt(new Number(df.format(total * 
                                                               currRowNewPercent / 
                                                               100)));
                        }
                        lastRowNewAmt = 
                                lastRowNewAmt + total * currRowNewPercent / 
                                100;
                    }
                } catch (Exception e) {
                    throw OAException.wrapperException(e);
                }
            }
        }
    }

    public void setVersionId() {
        Number versionId = new Number(1);
        PayPlanVOImpl vo = this.getPayPlanVO1();
        PayPlanVORowImpl row = null;

        int fetchedRowCount = vo.getFetchedRowCount();
        RowSetIterator lineNumIter = vo.createRowSetIterator("lineNumIter");
        if (fetchedRowCount > 1) {
            lineNumIter.setRangeStart(0);
            lineNumIter.setRangeSize(fetchedRowCount);
            for (int i = 0; i < fetchedRowCount; i++) {
                row = (PayPlanVORowImpl)lineNumIter.getRowAtRangeIndex(i);
                if (row.getVersionId() != null) {
                    versionId = row.getVersionId();
                    break;
                }
            }
        }
        Row row2 = vo.getCurrentRow();
        row2.setAttribute("VersionId", versionId);

    }

    public void apply(String strTotal) {
        this.calculate(strTotal);
        this.getTransaction().commit();
    }

    public void rollback() {
        Transaction txn = this.getTransaction();
        if (txn.isDirty()) {
            txn.rollback();
        }
    }

    public void setPoHdrId(String poHdrId) {
        Number headerId = null;
        try {
            headerId = new Number(poHdrId);
        } catch (Exception e) {
            System.out.println("poHdrId Error");
        }

        PayPlanVOImpl vo = this.getPayPlanVO1();
        Row row = vo.getCurrentRow();
        if (headerId != null) {
            row.setAttribute("PoHeaderId", headerId);
        }
    }


    public void createNewVersion() {
        PayPlanHisVOImpl payPlanHisVo = this.getPayPlanHisVO1();
        PayPlanVOImpl payPlanVo = this.getPayPlanVO1();
        PayPlanHisVORowImpl payPlanHisRow = null;
        PayPlanVORowImpl payPlanRow = null;
        Number versionId = null;

        int fetchedRowCount = payPlanVo.getFetchedRowCount();
        RowSetIterator payPlanIter = 
            payPlanVo.createRowSetIterator("payPlanIter");
        if (fetchedRowCount > 0) {
            payPlanIter.setRangeStart(0);
            payPlanIter.setRangeSize(fetchedRowCount);
            //payPlanHisVo.setMaxFetchSize(0);
            for (int i = 0; i < fetchedRowCount; i++) {
                //save old version
                payPlanRow = 
                        (PayPlanVORowImpl)payPlanIter.getRowAtRangeIndex(i);
                payPlanHisRow = (PayPlanHisVORowImpl)payPlanHisVo.createRow();
                payPlanHisRow.setVersionId(payPlanRow.getVersionId());
                payPlanHisRow.setLineId(payPlanRow.getLineId());
                payPlanHisRow.setLineNum(payPlanRow.getLineNum());
                payPlanHisRow.setPoHeaderId(payPlanRow.getPoHeaderId());
                payPlanHisRow.setPayType(payPlanRow.getPayType());
                payPlanHisRow.setInitAmt(payPlanRow.getInitAmt());
                payPlanHisRow.setInitPayDate(payPlanRow.getInitPayDate());
                payPlanHisRow.setNewAmt(payPlanRow.getNewAmt());
                payPlanHisRow.setNewPayDate(payPlanRow.getNewPayDate());
                payPlanHisRow.setMemo(payPlanRow.getMemo());
                payPlanHisRow.setPayApplyAmt(payPlanRow.getPayApplyAmt());
                payPlanHisRow.setPayApplyNum(payPlanRow.getPayApplyNum());
                payPlanHisRow.setPayApplierName(payPlanRow.getPayApplierName());
                payPlanHisRow.setPayApplyAmt(payPlanRow.getPayApplyAmt());
                payPlanHisRow.setPaidAmt(payPlanRow.getPaidAmt());
                payPlanHisRow.setAttributeCategory(payPlanRow.getAttributeCategory());
                payPlanHisRow.setAttribute1(payPlanRow.getAttribute1());
                payPlanHisRow.setAttribute2(payPlanRow.getAttribute2());
                payPlanHisRow.setAttribute3(payPlanRow.getAttribute3());
                payPlanHisRow.setAttribute4(payPlanRow.getAttribute4());
                payPlanHisRow.setAttribute5(payPlanRow.getAttribute5());
                payPlanHisRow.setAttribute6(payPlanRow.getAttribute6());
                payPlanHisRow.setAttribute7(payPlanRow.getAttribute7());
                payPlanHisRow.setAttribute8(payPlanRow.getAttribute8());
                payPlanHisRow.setAttribute9(payPlanRow.getAttribute9());
                payPlanHisRow.setAttribute10(payPlanRow.getAttribute10());
                //insert
                payPlanHisVo.insertRow(payPlanHisRow);
                //set new version id
                versionId = payPlanRow.getVersionId();
                payPlanRow.setVersionId(versionId.add(1));
            }
        }
    }

    public void deletePayPlanLine(String lineId) {
        PayPlanVOImpl vo = this.getPayPlanVO1();
        PayPlanVORowImpl row = null;
        int lineToDelete = Integer.parseInt(lineId);

        int fetchedRowCount = vo.getFetchedRowCount();
        RowSetIterator lineNumIter = vo.createRowSetIterator("lineNumIter");
        if (fetchedRowCount > 0) {
            lineNumIter.setRangeStart(0);
            lineNumIter.setRangeSize(fetchedRowCount);
            for (int i = 0; i < fetchedRowCount; i++) {
                row = (PayPlanVORowImpl)lineNumIter.getRowAtRangeIndex(i);
                Number primaryKey = row.getLineId();
                if (primaryKey.compareTo(lineToDelete) == 0) {
                    row.remove();
                    getTransaction().commit();
                    break;
                }
            }
        }
    }

    public double getPlannedTotal() {
        PayPlanVOImpl vo = this.getPayPlanVO1();
        PayPlanVORowImpl row = null;
        int fetchedRowCount = vo.getRowCount();
        double plannedTotal = 0;

        RowSetIterator lineNumIter = vo.createRowSetIterator("lineNumIter");
        if (fetchedRowCount > 0) {
            lineNumIter.setRangeStart(0);
            lineNumIter.setRangeSize(fetchedRowCount);
            for (int i = 0; i < fetchedRowCount; i++) {
                row = (PayPlanVORowImpl)lineNumIter.getRowAtRangeIndex(i);
                if (row.getNewAmt() != null) {
                    plannedTotal = 
                            plannedTotal + Double.parseDouble(row.getNewAmt().toString());
                }
            }
        }
        return plannedTotal;
    }

    /**Container's getter for PayPlanVO1
     */
    public PayPlanVOImpl getPayPlanVO1() {
        return (PayPlanVOImpl)findViewObject("PayPlanVO1");
    }

    /**Container's getter for PayPlanHisVO1
     */
    public PayPlanHisVOImpl getPayPlanHisVO1() {
        return (PayPlanHisVOImpl)findViewObject("PayPlanHisVO1");
    }

    /**Container's getter for PayPlanHeaderDisVO1
     */
    public PayPlanHeaderDisVOImpl getPayPlanHeaderDisVO1() {
        return (PayPlanHeaderDisVOImpl)findViewObject("PayPlanHeaderDisVO1");
    }
}
