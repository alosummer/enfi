package cux.oracle.apps.po.deliver.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxPoSupEvlTVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public CuxPoSupEvlTVOImpl() {
    }

    /**
     * 
     * @param poHeaderId
     */
    public void queryByPoHeaderId(String poHeaderId) {
        this.setWhereClause(null);
        this.setWhereClauseParams(null);
        this.setWhereClause("po_header_id = :1");
        this.setWhereClauseParams(new Object[] { poHeaderId });
        this.executeQuery();
    }
}
