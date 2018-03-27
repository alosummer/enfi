package cux.oracle.apps.ap.oie.entry.header.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxApExpRelAboardOtherFeeVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public CuxApExpRelAboardOtherFeeVOImpl() {
    }

    private Number maxLineNumber = new Number(0);

    public Number getMaxLineNumber() {
        if (this.isExecuted()) {
            int fetchedRowCount = this.getRowCount();
            if (fetchedRowCount > 0) {
                RowSetIterator maxLineNumberIter = 
                    this.createRowSetIterator("maxLineNumberIter");
                maxLineNumberIter.setRangeStart(0);
                maxLineNumberIter.setRangeSize(fetchedRowCount);
                CuxApExpRelAboardOtherFeeVORowImpl otherFeeRow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    otherFeeRow = 
                            (CuxApExpRelAboardOtherFeeVORowImpl)maxLineNumberIter.getRowAtRangeIndex(i);
                    if (this.maxLineNumber.compareTo(otherFeeRow.getLineNumber()) < 
                        0) {
                        this.maxLineNumber = otherFeeRow.getLineNumber();
                    }
                }
                maxLineNumberIter.closeRowSetIterator();
            }

        }
        this.maxLineNumber = this.maxLineNumber.add(1);
        return this.maxLineNumber;
    }
}
