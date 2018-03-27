package cux.oracle.apps.per.review.server;

import java.util.Vector;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LevelProrateDetailsVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public LevelProrateDetailsVOImpl() {
    }
    //initLevelProrateDetailsQuery

    public void initLevelProrateDetailsQuery(String pgroupId, 
                                             String ppacketNum, 
                                             String pperiodTypeId, 
                                             String pappraisalYear) {
        StringBuffer whereClause = new StringBuffer(100);
        Vector parameters = new Vector(3);
        int clauseCount = 0;
        int bindCount = 0;
        setWhereClause(null); //fch
        setWhereClauseParams(null); // Always reset
        if ((pgroupId != null) && (!("".equals(pgroupId.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" APPRAISAL_GROUP_ID = :");
            whereClause.append(++bindCount);
            parameters.addElement(pgroupId);
            clauseCount++;
        }
        /*20090919 zs*/
        if ((ppacketNum != null) && (!("".equals(ppacketNum.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" PACKET_NUM = :");
            whereClause.append(++bindCount);
            parameters.addElement(ppacketNum);
            clauseCount++;
        }
        /*20090919 zs*/
        if ((pperiodTypeId != null) && (!("".equals(pperiodTypeId.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" PERIOD_TYPE_ID = :");
            whereClause.append(++bindCount);
            parameters.addElement(pperiodTypeId);
            clauseCount++;
        }
        if ((pappraisalYear != null) && 
            (!("".equals(pappraisalYear.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" APPRAISAL_YEAR = :");
            whereClause.append(++bindCount);
            parameters.addElement(pappraisalYear);
            clauseCount++;
        }

        setWhereClause(whereClause.toString());
        if (bindCount > 0) {
            Object[] params = new Object[bindCount];
            parameters.copyInto(params);
            setWhereClauseParams(params);
        }
        /*20091223 zs*/
        this.setMaxFetchSize(-1);
        /*20091223 zs*/
        executeQuery();
        //zs    setWhereClause(null);//fch
        //zs    setWhereClauseParams(null); // Always reset
    }
}

