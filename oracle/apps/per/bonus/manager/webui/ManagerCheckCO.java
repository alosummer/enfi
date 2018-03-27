/*     */package cux.oracle.apps.per.bonus.manager.webui;
/*     */
/*     */import com.sun.java.util.collections.HashMap;
/*     */
import java.io.PrintStream;
/*     */
import java.sql.CallableStatement;
/*     */
import oracle.apps.fnd.framework.OAException;
/*     */
import oracle.apps.fnd.framework.OAViewObject;
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
import oracle.jbo.Row;
/*     */
/*     */public class ManagerCheckCO extends OAControllerImpl {
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
        /*  38 */super.processRequest(pageContext, webBean);
        /*  39 */OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        /*     */
        /*  57 */am.invokeMethod("createManagerVO");
        /*     */
    }
    /*     */
    /*     */

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /*  77 */super.processFormRequest(pageContext, webBean);
        /*     */
        /*  79 */if (pageContext.getParameter("Submit") != null)
        /*     */ {
            /*  81 */OAApplicationModule am = 
                (OAApplicationModule)pageContext.getApplicationModule(webBean);
            /*     */
            /*  83 */OAViewObject Approvedvo = 
                (OAViewObject)am.findViewObject("ManagerVO1");
            /*  84 */int tt = 0;
            /*  85 */tt = Approvedvo.getRowCount();
            /*  86 */if (tt == 0)
            /*     */ {
                /*  88 */OAException confirmMessage = 
                    new OAException("提交失败。原因：无数据可审批！", (byte)0);
                /*  89 */pageContext.putDialogMessage(confirmMessage);
                /*     */return;
                /*     */
            }
            /*     */
            /*  95 */OAViewObject vo = 
                (OAViewObject)am.findViewObject("ManagerCheckBudgetVO1");
            /*  96 */int ii = 0;
            /*  97 */ii = vo.getRowCount();
            /*     */
            /*  99 */if (ii != 0) {
                /* 100 */String Smessage = "";
                /* 101 */for (int nn = 0; nn < ii; nn++)
                /*     */ {
                    /* 103 */Row row = vo.getRowAtRangeIndex(nn);
                    /* 104 */Smessage = 
                            Smessage + row.getAttribute("ProjectNumber").toString() + 
                            row.getAttribute("ProjectName").toString();
                    /*     */
                }
                /*     */
                /* 106 */OAException confirmMessage = 
                    new OAException("提交失败！" + Smessage + ",项目发放金额超预算,请检查！", 
                                    (byte)0);
                /* 107 */pageContext.putDialogMessage(confirmMessage);
                /*     */return;
                /*     */
            }
            /*     */
            /* 115 */String plot_id = GetLotID(am);
            /* 116 */System.out.println("plot_id" + plot_id);
            /*     */
            /* 118 */OAViewObject vo1 = 
                (OAViewObject)am.findViewObject("ManagerVO1");
            /* 119 */int jj = 0;
            /* 120 */jj = vo1.getRowCount();
            /* 121 */if (jj != 0) {
                /* 122 */String Smessage = "";
                /* 123 */for (int nn = 0; nn < jj; nn++)
                /*     */ {
                    /* 125 */Row row = vo1.getRowAtRangeIndex(nn);
                    /* 126 */String p_distr_id = 
                        row.getAttribute("PersonDistributionId").toString();
                    /* 127 */System.out.println("p_distr_id" + p_distr_id);
                    /*     */
                    /* 129 */CallableStatement cs = null;
                    /* 130 */OADBTransaction transaction = 
                        am.getOADBTransaction();
                    /* 131 */cs = 
                            transaction.createCallableStatement("call cux_hr_bonus.update_person_distribution_lot(?,?, ?)", 
                                                                1);
                    /*     */try {
                        /* 133 */cs.registerOutParameter(1, 12);
                        /* 134 */cs.setInt(2, Integer.parseInt(p_distr_id));
                        /* 135 */cs.setInt(3, Integer.parseInt(plot_id));
                        /* 136 */cs.executeUpdate();
                        /* 137 */String retcode = cs.getString(1);
                        /* 138 */if ("success".equals(retcode))
                            /* 139 */transaction.commit();
                        /*     */
                    }
                    /*     */ catch (Exception e)
                    /*     */ {
                        /* 143 */System.out.println(e.toString());
                        /* 144 */OAException confirmok = 
                            new OAException("提交失败！请联系系统管理员！", (byte)3);
                        /* 145 */pageContext.putDialogMessage(confirmok);
                        /*     */
                    }
                    /*     */
                    /*     */
                }
                /*     */
                /*     */
            }
            /*     */
            /* 154 */init_approver(am, "0", plot_id);
            /* 155 */start_wf_prc(am, "0", plot_id);
            /*     */
            /* 157 */HashMap params = new HashMap(1);
            /* 158 */params.put("SUCCESS", "Y");
            /* 159 */pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/manager/webui/ManagerDistrPG", 
                                                    null, (byte)0, null, 
                                                    params, true, "Y");
            /*     */
        }
        /*     */
        /* 175 */if (pageContext.getParameter("Return") != null)
        /*     */ {
            /* 178 */HashMap params = new HashMap(1);
            /* 179 */params.put("SUCCESS", "N");
            /* 180 */pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/manager/webui/ManagerDistrPG", 
                                                    null, (byte)0, null, 
                                                    params, true, "Y");
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public void init_approver(OAApplicationModule am, String p_project_id, 
                              String p_lot_id) {
        /* 194 */OADBTransaction transaction = am.getOADBTransaction();
        /* 195 */CallableStatement cs = 
            transaction.createCallableStatement("call CUX_ASSGN_BONUS_WF_PKG.init_approver(?,?,?,?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 201 */cs.registerOutParameter(1, 12);
            /* 202 */cs.registerOutParameter(2, 12);
            /* 203 */cs.setString(3, "APPROVAL_BONUS_MANAGER");
            /*     */
            /* 205 */cs.setInt(4, Integer.parseInt(p_project_id));
            /*     */
            /* 209 */cs.setInt(5, Integer.parseInt(p_lot_id));
            /*     */
            /* 213 */cs.executeQuery();
            /*     */
        } catch (Exception e) {
            /* 215 */System.out.println(e.toString());
            /* 216 */System.out.println("init_approver1");
            /*     */
        }
        /* 218 */if (cs != null)
            /*     */try {
                /* 220 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 222 */System.out.println(e.toString());
                /* 223 */System.out.println("init_approver2");
                /*     */
            }
        /*     */
    }
    /*     */
    /*     */

    public void start_wf_prc(OAApplicationModule am, String p_project_id, 
                             String p_lot_id) {
        /* 231 */OADBTransaction transaction = am.getOADBTransaction();
        /* 232 */CallableStatement cs = 
            transaction.createCallableStatement("call CUX_ASSGN_BONUS_WF_PKG.start_wf_prc(?,?,?,?,?,?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 237 */cs.setString(1, "BONUS_AS");
            /* 238 */cs.setString(2, "");
            /* 239 */cs.setString(3, "APPROVAL_BONUS_MANAGER");
            /* 240 */cs.setInt(4, Integer.parseInt(p_project_id));
            /*     */
            /* 242 */cs.setInt(5, Integer.parseInt(p_lot_id));
            /*     */
            /* 244 */cs.registerOutParameter(6, 2);
            /* 245 */cs.registerOutParameter(7, 12);
            /* 246 */cs.executeQuery();
            /*     */
        } catch (Exception e) {
            /* 248 */System.out.println(e.toString());
            /* 249 */System.out.println("init_start_wf_prc1");
            /*     */
        }
        /*     */
        /* 252 */if (cs != null)
            /*     */try {
                /* 254 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 256 */System.out.println(e.toString());
                /* 257 */System.out.println("init_start_wf_prc2");
                /*     */
            }
        /*     */
    }
    /*     */
    /*     */

    public String Geytatal(OAViewObject vo, String columnName) {
        /* 267 */double amt = 0.0D;
        /* 268 */if (vo != null)
        /*     */ {
            /* 270 */int ii = 0;
            /* 271 */ii = vo.getRowCount();
            /* 272 */System.out.println("ii=" + String.valueOf(ii));
            /* 273 */for (int nn = 0; nn < ii; nn++)
            /*     */ {
                /* 275 */Row row = vo.getRowAtRangeIndex(nn);
                /* 276 */String a1 = "0";
                /*     */try {
                    /* 278 */a1 = row.getAttribute(columnName).toString();
                    /* 279 */System.out.println(a1.toString());
                    /*     */
                }
                /*     */ catch (Exception e) {
                    /* 282 */System.out.println(e.toString());
                    /*     */
                }
                /* 284 */amt += Double.parseDouble(a1);
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 290 */return Double.toString(amt);
        /*     */
    }
    /*     */
    /*     */

    public String get_budget_result(OAApplicationModule am, 
                                    String p_project_id) {
        /* 296 */String result = "";
        /* 297 */OADBTransaction transaction = am.getOADBTransaction();
        /* 298 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_budget(?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 302 */cs.setString(1, p_project_id);
            /* 303 */cs.registerOutParameter(2, 12);
            /* 304 */cs.executeQuery();
            /* 305 */result = cs.getString(2);
            /* 306 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 309 */System.out.println(e.toString());
            /*     */
        }
        /* 311 */if (cs != null) {
            /*     */try {
                /* 313 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 315 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 319 */return result;
        /*     */
    }
    /*     */
    /*     */

    public String get_project_distributed(OAApplicationModule am, 
                                          String p_project_id, 
                                          String p_type_id) {
        /* 324 */String result = "";
        /* 325 */OADBTransaction transaction = am.getOADBTransaction();
        /* 326 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_distributed(?,?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 330 */cs.setString(1, p_project_id);
            /* 331 */cs.setString(2, p_type_id);
            /* 332 */cs.registerOutParameter(3, 12);
            /* 333 */cs.executeQuery();
            /* 334 */result = cs.getString(3);
            /* 335 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 338 */System.out.println(e.toString());
            /*     */
        }
        /* 340 */if (cs != null) {
            /*     */try {
                /* 342 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 344 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 348 */return result;
        /*     */
    }
    /*     */
    /*     */

    public String GetLotID(OAApplicationModule am) {
        /* 352 */String result = "";
        /* 353 */OADBTransaction transaction = am.getOADBTransaction();
        /* 354 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_next_lot_id(?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 358 */cs.registerOutParameter(1, 4);
            /* 359 */cs.executeQuery();
            /* 360 */result = cs.getString(1);
            /* 361 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 364 */System.out.println(e.toString());
            /*     */
        }
        /* 366 */if (cs != null) {
            /*     */try {
                /* 368 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 370 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 374 */return result;
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\webui\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.webui.ManagerCheckCO
 * JD-Core Version:    0.6.1
 */