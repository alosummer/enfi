package cux.oracle.apps.pa.project.lov.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class RoleVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public RoleVOImpl() {
    }

    public void initQuery(String partyClass, String projectId) {
        String str = "";
        //this.setProjectId(projectId);
        this.setWhereClauseParam(0, projectId);
        if ((partyClass == null) || (partyClass.equals(""))) {
            str = 
"role_party_class = 'PERSON' and sysdate between start_date_active and NVL(end_date_active, sysdate+1)";
        } else if (partyClass.equals("ORGANIZATION")) {
            str = 
"role_party_class <> 'PERSON' and sysdate between start_date_active and NVL(end_date_active, sysdate+1)";
        } else if (partyClass.equals("CUSTOMER")) {
            str = 
"role_party_class = 'CUSTOMER' and sysdate between start_date_active and NVL(end_date_active, sysdate+1)";
        }
        setWhereClause(str);
    }


}
