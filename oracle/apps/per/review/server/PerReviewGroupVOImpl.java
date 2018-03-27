package cux.oracle.apps.per.review.server;

import java.util.Vector;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PerReviewGroupVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public PerReviewGroupVOImpl() {
    }

    public void initQuery() {
        executeQuery();
    }

    public void initQuery(String grpName, String orgId, String jobId, 
                          Boolean executeQuery) {
        StringBuffer whereClause = new StringBuffer(500);
        Vector parameters = new Vector(3);
        int clauseCount = 0;
        int bindCount = 0;

        setWhereClauseParams(null);
        if ((grpName != null) && (!("".equals(grpName.trim())))) {
            whereClause.append(" GROUP_NAME like :");
            whereClause.append(++bindCount);
            parameters.addElement(grpName);
            clauseCount++;
        }

        if ((orgId != null) && (!("".equals(orgId.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }

            Number organizationId = null;
            try {
                organizationId = new Number(orgId);
            } catch (Exception e) {
                ;
            }

            whereClause.append(" ORG_ID = :");
            whereClause.append(++bindCount);
            parameters.addElement(organizationId);
            clauseCount++;
        }

        if ((jobId != null) && (!("".equals(jobId.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }

            Number assignmentId = null;
            try {
                assignmentId = new Number(jobId);
            } catch (Exception e) {
                ;
            }

            whereClause.append(" EXISTS( SELECT 1" + 
                               "           FROM CUX_GROUP_RANGE_T grt" + 
                               "          WHERE grt.group_id = QRSLT.group_id" + 
                               "            AND grt.job_id = :");
            whereClause.append(++bindCount);
            parameters.addElement(assignmentId);
            clauseCount++;
            whereClause.append(")");
        }

        setWhereClause(whereClause.toString());
        if (bindCount > 0) {
            Object[] params = new Object[bindCount];
            parameters.copyInto(params);
            setWhereClauseParams(params);
        }

        if ((executeQuery != null) && (executeQuery.booleanValue())) {
            executeQuery();
        }
    }
}
