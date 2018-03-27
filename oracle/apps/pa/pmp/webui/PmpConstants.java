package cux.oracle.apps.pa.pmp.webui;

public interface PmpConstants {
    public static final String SMT_NEW_VERSION = 
        "BEGIN " + " cux_pa_pmp_pkg.create_new_version(p_project_id => :1); " + 
        "END;";
    public static final String SMT_AME_STARTUP = 
        "BEGIN\n" + "  cux_ame_wkf_pkg.startup_workflow(transaction_type => :1,\n" + 
        "                                   transaction_id => :2,\n" + 
        "                                   transaction_number => :3,\n" + 
        "                                   mod_pkg_name => :4);\n" + "END;";
    public static final String SMT_AME_LISTQUERY = 
        "BEGIN\n" + "  cux_ame_wkf_pkg.ame_apprs_list_query(transaction_type => :1,\n" + 
        "                                       transaction_id => :2,\n" + 
        "                                       transaction_number => :3);\n" + 
        "END;";
    public static final String SMT_AME_UPDATELISTQUERY = 
        "BEGIN\n" + "  cux_ame_wkf_pkg.ame_apprs_list_query(transaction_type => :1,\n" + 
        "                                       transaction_id => :2,\n" + 
        "                                       transaction_number => :3);\n" + 
        "END;";
    public static final String TRANSACTION_TYPE_VERIFY = "CUXPMPINTERNAL";
    public static final String TRANSACTION_TYPE_APPROVE = "CUXPMPCOMPANY";
    public static final String MOD_PKG_NAME = "CUX_PA_PMP_PKG";
    public static final String SQL_PMP_STATUS_VAL = 
        "SELECT COUNT(0)\n" + "FROM   cux_pa_pmp_t ppt\n" + 
        "WHERE  ppt.project_id = :1\n" + 
        "AND    ppt.version_num = (SELECT MAX(version_num)\n" + 
        "                          FROM   cux_pa_pmp_t cp\n" + 
        "                          WHERE  cp.project_id = :2)\n" + 
//        "AND    ppt.VERIFY_STATUS <> 'MTREJECTED'\n" + 
        "AND    ppt.APPROVE_STATUS <> 'APPROVED'";
}
