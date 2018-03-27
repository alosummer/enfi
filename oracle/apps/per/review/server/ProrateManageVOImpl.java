package cux.oracle.apps.per.review.server;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.util.Vector;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;

import oracle.jbo.domain.Number;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ProrateManageVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public ProrateManageVOImpl() {
    }

    public void initQuery(String pOrg, String flag, String groupId, 
                          Boolean executeQuery) {
        StringBuffer whereClause = new StringBuffer(5000);
        Vector parameters = new Vector(3);
        int clauseCount = 0;
        int bindCount = 0;

        setWhereClauseParams(null);

        if ((groupId != null) && (!("".equals(groupId.trim())))) {
            Number grpId = null;
            try {
                grpId = new Number(groupId);
            } catch (Exception e) {
                ;
            }

            whereClause.append(" GROUP_ID = :");
            whereClause.append(++bindCount);
            parameters.addElement(grpId);
            clauseCount++;
        }

        // 
        if (pOrg != null && !("".equals(pOrg.trim())) && flag != null && 
            flag.equals("N")) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" ORG_NAME = :");
            whereClause.append(++bindCount);
            parameters.addElement(pOrg);
            clauseCount++;
        }

        //
        if (pOrg != null && !("".equals(pOrg.trim())) && flag != null && 
            flag.equals("Y")) {
            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }

            OAApplicationModule am = 
                (OAApplicationModule)getApplicationModule();
            Serializable p[] = { pOrg };
            String parentOrgId = 
                (String)am.invokeMethod("getOrganizationId", p);
            if (parentOrgId != null && !parentOrgId.equals("-1")) {
                whereClause.append(" org_id IN (" + 
                                   "     SELECT E.ORGANIZATION_ID_CHILD org_id  " + 
                                   "       FROM PER_ORG_STRUCTURE_ELEMENTS E " + 
                                   "      WHERE E.ORG_STRUCTURE_VERSION_ID = cux_jxkh_common_pkg.get_org_structure_version(to_date(to_char(SYSDATE, 'YYYY') || '/09/25', 'YYYY/MM/DD'))" + 
                                   "     CONNECT BY E.ORGANIZATION_ID_PARENT = PRIOR E.ORGANIZATION_ID_CHILD" + 
                                   "       AND E.ORG_STRUCTURE_VERSION_ID = cux_jxkh_common_pkg.get_org_structure_version(to_date(to_char(SYSDATE, 'YYYY') || '/09/25', 'YYYY/MM/DD'))" + 
                                   "      START WITH E.ORGANIZATION_ID_PARENT = " + 
                                   parentOrgId + 
                                   "       AND E.ORG_STRUCTURE_VERSION_ID = cux_jxkh_common_pkg.get_org_structure_version(to_date(to_char(SYSDATE, 'YYYY') || '/09/25', 'YYYY/MM/DD'))" + 
                                   "    UNION ALL " + "    SELECT " + 
                                   parentOrgId + " org_id " + 
                                   "     FROM dual )");
            }
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

    public void initProrateIdQuery(Number PacketNum) {
        if (PacketNum != null) {
            setWhereClause(" PACKET_NUM = :1");
            setWhereClauseParams(null);
            setWhereClauseParam(0, PacketNum);
        }
        executeQuery();
    }

    public void initProQuery(String pOrg, String flag, String groupId) {
        setWhereClauseParams(null);

        if (pOrg != null) {
            //   setWhereClause("GROUP_ID = :3");
            setWhereClauseParam(0, pOrg);
            setWhereClauseParam(1, flag);
            setWhereClauseParam(3, groupId);
        }
        //  System.out.println("pOrg"+pOrg);
        //  System.out.println("flag"+flag);
        executeQuery();
    }

    //initProrateManageVO

    public void initProrateManageVO(String pgroupId, String porgId) {
        StringBuffer whereClause = new StringBuffer(100);
        Vector parameters = new Vector(2);
        int clauseCount = 0;
        int bindCount = 0;
        setWhereClauseParams(null); // Always reset
        if ((pgroupId != null) && (!("".equals(pgroupId.trim())))) {
            Number groupId = null;
            try {
                groupId = new Number(pgroupId);
            } catch (Exception e) {
                throw new OAException("z", "s");
            }
            whereClause.append(" GROUP_ID = :");
            whereClause.append(++bindCount);
            parameters.addElement(groupId);
            clauseCount++;
        }
        if ((porgId != null) && (!("".equals(porgId.trim())))) {
            Number orgId = null;
            try {
                orgId = new Number(porgId);
            } catch (Exception e) {
                throw new OAException("z", "s");
            }

            if (clauseCount > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(" ORG_ID = :");
            whereClause.append(++bindCount);
            parameters.addElement(orgId);
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
    //initProrateManageVO

    public void initProrateManageVOByPacketNum(String ppacketNum) {
        StringBuffer whereClause = new StringBuffer(100);
        Vector parameters = new Vector(2);
        int clauseCount = 0;
        int bindCount = 0;
        setWhereClauseParams(null); // Always reset
        if ((ppacketNum != null) && (!("".equals(ppacketNum.trim())))) {
            Number packetNum = null;
            try {
                packetNum = new Number(ppacketNum);
            } catch (Exception e) {
                throw new OAException("z", "s");
            }
            whereClause.append(" PACKET_NUM = :");
            whereClause.append(++bindCount);
            parameters.addElement(packetNum);
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
}