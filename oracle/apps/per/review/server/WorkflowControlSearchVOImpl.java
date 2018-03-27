package cux.oracle.apps.per.review.server;

import java.util.Vector;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class WorkflowControlSearchVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public WorkflowControlSearchVOImpl() {
    }
    //initSearchQuery

    public void initSearchQuery(String pgroupName, String porgName, 
                                String pphaseName, String pperiodName, 
                                String pappraisalYear, String pstatusId) {
        StringBuffer whereClause = new StringBuffer(100);
        Vector parameters = new Vector(3);
        int clauseCount = 0;
        int bindCount = 0;
        setWhereClauseParams(null); // Always reset
        if ((pgroupName != null) && (!("".equals(pgroupName.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" Group_Name = :");
            whereClause.append(++bindCount);
            parameters.addElement(pgroupName);
            clauseCount++;
        }
        if ((porgName != null) && (!("".equals(porgName.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" Org_Name = :");
            whereClause.append(++bindCount);
            parameters.addElement(porgName);
            clauseCount++;
        }
        if ((pphaseName != null) && (!("".equals(pphaseName.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" Phase_Name = :");
            whereClause.append(++bindCount);
            parameters.addElement(pphaseName);
            clauseCount++;
        }
        if ((pperiodName != null) && (!("".equals(pperiodName.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" Period_Name = :");
            whereClause.append(++bindCount);
            parameters.addElement(pperiodName);
            clauseCount++;
        }
        if ((pappraisalYear != null) && 
            (!("".equals(pappraisalYear.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" Appraisal_Year = :");
            whereClause.append(++bindCount);
            parameters.addElement(pappraisalYear);
            clauseCount++;
        }
        if ((pstatusId != null) && (!("".equals(pstatusId.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" Status_Id = :");
            whereClause.append(++bindCount);
            parameters.addElement(pstatusId);
            clauseCount++;
        }

        setWhereClause(whereClause.toString());
        if (bindCount > 0) {
            Object[] params = new Object[bindCount];
            parameters.copyInto(params);
            setWhereClauseParams(params);
        }
        executeQuery();

    }

    //add by fl 20091116  

    public void initQuery(String groupName, String orgName, String phase, 
                          String period, String year, String endYear, 
                          String status, String empName, 
                          Boolean executeQuery) {
        StringBuffer whereClause = new StringBuffer(500);
        Vector parameters = new Vector(8);
        int clauseCount = 0;
        int bindCount = 0;

        setWhereClauseParams(null);
        if ((groupName != null) && (!("".equals(groupName.trim())))) {
            whereClause.append(" GROUP_NAME = :");
            whereClause.append(++bindCount);
            parameters.addElement(groupName);
            clauseCount++;
        }

        if ((orgName != null) && (!("".equals(orgName.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            //whereClause.append(" ORG_NAME = :");
            /*20100827 zs*/
            int bindCount_tmp = ++bindCount;
            whereClause.append(" ORG_ID IN ");
            whereClause.append("       (SELECT E.ORGANIZATION_ID_CHILD ");
            whereClause.append("               FROM PER_ORG_STRUCTURE_ELEMENTS E ");
            whereClause.append("              WHERE E.ORG_STRUCTURE_VERSION_ID = CUX_JXKH_COMMON_PKG.GET_ORG_STRUCTURE_VERSION(CUX_JXKH_COMMON_PKG.GET_CONTRACT_EFFECTIVE_DATE(APPRAISAL_ID)) ");
            whereClause.append("             CONNECT BY E.ORGANIZATION_ID_PARENT = PRIOR ");
            whereClause.append("                        E.ORGANIZATION_ID_CHILD ");
            whereClause.append("                    AND E.ORG_STRUCTURE_VERSION_ID = CUX_JXKH_COMMON_PKG.GET_ORG_STRUCTURE_VERSION(CUX_JXKH_COMMON_PKG.GET_CONTRACT_EFFECTIVE_DATE(APPRAISAL_ID)) ");
            whereClause.append("              START WITH E.ORGANIZATION_ID_PARENT = (SELECT haou.organization_id FROM hr_all_organization_units haou WHERE haou.name = :");
            whereClause.append(bindCount_tmp);
            whereClause.append("  AND ROWNUM = 1)  ");
            whereClause.append("                     AND E.ORG_STRUCTURE_VERSION_ID = CUX_JXKH_COMMON_PKG.GET_ORG_STRUCTURE_VERSION(CUX_JXKH_COMMON_PKG.GET_CONTRACT_EFFECTIVE_DATE(APPRAISAL_ID)) ");
            whereClause.append("             UNION ");
            whereClause.append(" SELECT haou.organization_id FROM hr_all_organization_units haou WHERE haou.name = :");
            whereClause.append(bindCount_tmp);
            whereClause.append("  AND ROWNUM = 1) ");
            /*20100827*/
            //whereClause.append(++bindCount); 
            parameters.addElement(orgName);
            clauseCount++;
        }

        if ((phase != null) && (!("".equals(phase.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" PHASE_NAME = :");
            whereClause.append(++bindCount);
            parameters.addElement(phase);
            clauseCount++;
        }

        if ((period != null) && (!("".equals(period.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" PERIOD_NAME = :");
            whereClause.append(++bindCount);
            parameters.addElement(period);
            clauseCount++;
        }

        if ((year != null) && (!("".equals(year.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" APPRAISAL_YEAR >= :");
            whereClause.append(++bindCount);
            parameters.addElement(year);
            clauseCount++;
        }

        if ((endYear != null) && (!("".equals(endYear.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" APPRAISAL_YEAR <= :");
            whereClause.append(++bindCount);
            parameters.addElement(endYear);
            clauseCount++;
        }

        if (((year == null) || ("".equals(year.trim()))) && 
            ((endYear == null) || ("".equals(endYear.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" APPRAISAL_YEAR = to_char(SYSDATE,'yyyy')");
            //whereClause.append(++bindCount);
            //parameters.addElement(yearFrom);
            clauseCount++;
        }

        if ((status != null) && (!("".equals(status.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }

            whereClause.append(" STATUS_ID = :");
            whereClause.append(++bindCount);
            parameters.addElement(status);
            clauseCount++;
        }

        if ((empName != null) && (!("".equals(empName.trim())))) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }

            whereClause.append(" EMP_NAME = :");
            whereClause.append(++bindCount);
            parameters.addElement(empName);
            clauseCount++;
        }

        setWhereClause(whereClause.toString());
        if (bindCount > 0) {
            Object[] params = new Object[bindCount];
            //  for(int z=0; z< parameters.size();z++)
            //  System.out.println(parameters.elementAt(z));  
            parameters.copyInto(params);
            setWhereClauseParams(params);
        }
        System.out.println("whereClause :��" + whereClause);


        if ((executeQuery != null) && (executeQuery.booleanValue())) {
            executeQuery();
        }
    }

}