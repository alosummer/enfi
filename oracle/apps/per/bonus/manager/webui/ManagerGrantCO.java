/*     */package cux.oracle.apps.per.bonus.manager.webui;
/*     */
/*     */import cux.oracle.apps.per.bonus.manager.server.ManagerGrantAMImpl;
/*     */
import cux.oracle.apps.per.bonus.manager.server.ManagerGrantVOImpl;
/*     */
import com.sun.java.util.collections.HashMap;
/*     */
import java.io.PrintStream;
/*     */
import java.sql.CallableStatement;
/*     */
import java.sql.ResultSet;
/*     */
import oracle.apps.fnd.framework.server.OADBTransaction;
/*     */
import oracle.apps.fnd.framework.server.common.OAApplicationModule;
/*     */
import oracle.apps.fnd.framework.webui.OAControllerImpl;
/*     */
import oracle.apps.fnd.framework.webui.OAPageContext;
/*     */
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
/*     */
/*     */public class ManagerGrantCO extends OAControllerImpl {
    /*     */
    public String SBudget;
    /*     */
    public String SDistributed;
    /*     */
    public String SDistributeble;
    /*     */
    public String Sproject_id;
    /*     */
    /*     */

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        /*  47 */super.processRequest(pageContext, webBean);
        /*  48 */OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        /*  49 */this.Sproject_id = pageContext.getParameter("PROJECTID");
        /*     */
        /*  51 */am.invokeMethod("createVOInfo");
        /*     */
    }
    /*     */
    /*     */

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /*  56 */super.processFormRequest(pageContext, webBean);
        /*     */
        /*  59 */if (pageContext.getParameter("Go") != null)
        /*     */ {
            /*  62 */String whereStr1 = "";
            /*     */
            /*  64 */String Sstatus = pageContext.getParameter("LineStatus");
            /*     */
            /*  66 */if (!"".equals(Sstatus))
            /*     */ {
                /*  68 */whereStr1 = "distribution_status='" + Sstatus + "'";
                /*     */
            }
            /*     */
            /*  72 */String distDate = 
                pageContext.getParameter("DistributeDate1");
            /*  73 */System.out.println("distDate=" + distDate);
            /*  74 */if (!"".equals(distDate))
            /*     */ {
                /*  76 */if (!"".equals(whereStr1))
                /*     */ {
                    /*  78 */whereStr1 = 
                            whereStr1 + "and to_char(distribution_date,'YYYY-MM-DD')='" + 
                            distDate + "'";
                    /*     */
                }
                /*     */ else
                /*     */ {
                    /*  82 */whereStr1 = 
                            "to_char(distribution_date,'YYYY-MM-DD')='" + 
                            distDate + "'";
                    /*     */
                }
                /*     */
                /*     */
            }
            /*     */
            /*  89 */OAApplicationModule am = 
                (OAApplicationModule)pageContext.getApplicationModule(webBean);
            /*     */
            /*  91 */String SProject = 
                GetProjectID(am, pageContext.getParameter("ProjectNumber1"));
            /*     */
            /*  93 */if (SProject != null)
            /*     */ {
                /*  95 */if (!"".equals(whereStr1))
                /*     */ {
                    /*  97 */whereStr1 = 
                            whereStr1 + "and project_id='" + SProject + "'";
                    /*     */
                }
                /*     */ else
                /*     */ {
                    /* 101 */whereStr1 = "project_id='" + SProject + "'";
                    /*     */
                }
                /*     */
            }
            /*     */
            /* 105 */System.out.println("ProjectID1" + SProject);
            /* 106 */System.out.println("whereStr1" + whereStr1);
            /* 107 */ManagerGrantAMImpl amImpl = 
                (ManagerGrantAMImpl)pageContext.getApplicationModule(webBean);
            /* 108 */ManagerGrantVOImpl vo = amImpl.getManagerGrantVO1();
            /*     */
            /* 110 */vo.setWhereClause(whereStr1);
            /* 111 */vo.executeQuery();
            /*     */
        }
        /*     */
        /* 115 */if (pageContext.getParameter("SaveButton") != null)
        /*     */ {
            /* 117 */HashMap params = new HashMap(2);
            /* 118 */OAApplicationModule am = 
                (OAApplicationModule)pageContext.getApplicationModule(webBean);
            /*     */
            /* 120 */params.put("PROJECTID", 
                                GetProjectID(am, pageContext.getParameter("ProjectNumber1")));
            /* 121 */params.put("SUCCESS", "N");
            /* 122 */pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/manager/webui/ManagerDistrPG", 
                                                    null, (byte)0, null, 
                                                    params, true, "Y");
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public String get_budget_result(OAApplicationModule am, 
                                    String p_project_id) {
        /* 252 */String result = "";
        /* 253 */OADBTransaction transaction = am.getOADBTransaction();
        /* 254 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_budget(?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 258 */cs.setString(1, p_project_id);
            /* 259 */cs.registerOutParameter(2, 12);
            /* 260 */cs.executeQuery();
            /* 261 */result = cs.getString(2);
            /* 262 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 265 */System.out.println(e.toString());
            /*     */
        }
        /* 267 */if (cs != null) {
            /*     */try {
                /* 269 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 271 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 275 */return result;
        /*     */
    }
    /*     */
    /*     */

    public String GetProjectID(OAApplicationModule am, String num) {
        /*     */try
        /*     */ {
            /* 281 */OADBTransaction transaction = am.getOADBTransaction();
            /* 282 */CallableStatement cs = 
                transaction.createCallableStatement("select t.project_id from pa_projects_all t where t.segment1='" + 
                                                    num + "'", 1);
            /*     */
            /* 285 */ResultSet rs = 
                cs.executeQuery("select t.project_id from pa_projects_all t where t.segment1='" + 
                                num + "'");
            /* 286 */if (rs.next()) {
                /* 287 */return Integer.toString(rs.getInt(1));
                /*     */
            }
            /*     */
            /* 290 */return null;
            /*     */
        }
        /*     */ catch (Exception e)
        /*     */ {
            /* 294 */return null;
            /*     */
        }
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\webui\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.webui.ManagerGrantCO
 * JD-Core Version:    0.6.1
 */