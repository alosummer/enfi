package cux.oracle.apps.per.review.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TemplateKPIVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public TemplateKPIVOImpl() {
    }

    public void executeQuery(String tmplId, String kpiClass) {
        setWhereClauseParams(null);
        Number id = null;
        try {
            id = new Number(tmplId);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (id != null && kpiClass != null) {
            setWhereClause("TMPL_ID = :1 AND KPI_CLASS = :2");
            setWhereClauseParam(0, id);
            setWhereClauseParam(1, kpiClass);
        } else if (id != null) {
            setWhereClause("TMPL_ID = :1");
            setWhereClauseParam(0, id);
        } else {
            return;
        }
        executeQuery();
    }

}