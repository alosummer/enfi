package cux.oracle.apps.ap.oie.entry.header.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxApExpRelatedAboardLineVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public CuxApExpRelatedAboardLineVOImpl() {
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
                CuxApExpRelatedAboardLineVORowImpl relateLineEOVORow = null;
                for (int i = fetchedRowCount - 1; i >= 0; i--) {
                    relateLineEOVORow = 
                            (CuxApExpRelatedAboardLineVORowImpl)maxLineNumberIter.getRowAtRangeIndex(i);
                    if (this.maxLineNumber.compareTo(relateLineEOVORow.getLineNum()) < 
                        0) {
                        this.maxLineNumber = relateLineEOVORow.getLineNum();
                    }
                }
                maxLineNumberIter.closeRowSetIterator();
            }

        }
        this.maxLineNumber = this.maxLineNumber.add(1);
        return this.maxLineNumber;
    }

}
