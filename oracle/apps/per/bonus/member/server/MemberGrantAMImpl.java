/*     */package cux.oracle.apps.per.bonus.member.server;
/*     */
/*     */import cux.oracle.apps.per.bonus.member.webui.DoubleProcess;
/*     */
import java.io.PrintStream;
/*     */
import java.sql.CallableStatement;
/*     */
import java.sql.ResultSet;
/*     */
import oracle.apps.fnd.framework.OAViewObject;
/*     */
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
/*     */
import oracle.apps.fnd.framework.server.OADBTransaction;
/*     */
import oracle.apps.fnd.framework.server.common.OAApplicationModule;
/*     */
import oracle.jbo.Row;
/*     */
import oracle.jbo.RowSetIterator;
/*     */
import oracle.jbo.Transaction;
/*     */
import oracle.jbo.domain.Number;
/*     */
/*     */public class MemberGrantAMImpl extends OAApplicationModuleImpl {
    /*     */

    public static void main(String[] args) {
        /*  39 */launchTester("cux.oracle.apps.per.bonus.member.server", 
                              "MemberGrantAMLocal");
        /*     */
    }
    /*     */
    /*     */

    public ProjectVOImpl getProjectVO1() {
        /*  47 */return (ProjectVOImpl)findViewObject("ProjectVO1");
        /*     */
    }
    /*     */
    /*     */

    public void createVOInfo() {
        /*  52 */OAViewObject vo = (OAViewObject)getMemberGrantVO1();
        /*  53 */vo.executeQuery();
        /*     */
    }
    /*     */
    /*     */

    public void DeletePersonLine(String distId) {
        /*  57 */if (!"".equals(distId)) {
            /*  58 */int depDistId = Integer.parseInt(distId);
            /*  59 */CallableStatement cs = null;
            /*  60 */OADBTransaction transaction = getOADBTransaction();
            /*  61 */cs = 
                    transaction.createCallableStatement("call cux_hr_bonus.delete_person_distribution(?)", 
                                                        1);
            /*     */try
            /*     */ {
                /*  65 */cs.setInt(1, depDistId);
                /*  66 */cs.executeUpdate();
                /*     */
            } catch (Exception e) {
                /*  68 */System.out.println(e.toString());
                /*     */
            }
            /*  70 */OAViewObject vo = (OAViewObject)getMemberDistrVO1();
            /*  71 */MemberDistrVORowImpl row = null;
            /*  72 */int fetchedRowCount = vo.getFetchedRowCount();
            /*  73 */RowSetIterator deleteIter = 
                vo.createRowSetIterator("deleteIter");
            /*  74 */if (fetchedRowCount > 0) {
                /*  75 */deleteIter.setRangeStart(0);
                /*  76 */deleteIter.setRangeSize(fetchedRowCount);
                /*  77 */for (int i = 0; i < fetchedRowCount; i++) {
                    /*  78 */row = 
                            (MemberDistrVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    /*     */
                    /*  80 */Integer primaryKey = 
                        Integer.valueOf(Integer.parseInt(row.getPersonDistributionId().toString()));
                    /*     */
                    /*  82 */if (primaryKey.compareTo(Integer.valueOf(depDistId)) == 
                                 0) {
                        /*  83 */row.remove();
                        /*  84 */getTransaction().commit();
                        /*  85 */break;
                        /*     */
                    }
                    /*     */
                }
                /*     */
            }
            /*  89 */deleteIter.closeRowSetIterator();
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public MemberGrantVOImpl getMemberGrantVO1() {
        /*  98 */return (MemberGrantVOImpl)findViewObject("MemberGrantVO1");
        /*     */
    }
    /*     */
    /*     */

    public String SavePersonDist(OAApplicationModule am, String bonusTypeName, 
                                 String project_id) {
        /* 101 */MemberDistrVOImpl vo = getMemberDistrVO1();
        /*     */
        /* 106 */Row[] Rows = vo.getAllRowsInRange();
        /*     */
        /* 108 */for (int nn = 0; nn < Rows.length; nn++)
        /*     */ {
            /* 110 */Row row = Rows[nn];
            /* 111 */if (row.getAttribute("EmployeeNumber") == null)
            /*     */ {
                /* 347 */return "1";
                /*     */
            }
            /* 116 */if (row.getAttribute("LastName") == null)
            /*     */ {
                /* 347 */return "1";
                /*     */
            }
            /* 121 */if (row.getAttribute("DepName") == null)
            /*     */ {
                /* 347 */return "1";
                /*     */
            }
            /*     */
            /* 127 */if (row.getAttribute("DistributionDate") == null)
            /*     */ {
                /* 347 */return "4";
                /*     */
            }
            /* 132 */Double personAmount = Double.valueOf(0.0D);
            /* 133 */if (row.getAttribute("DistributionAmount") != null) {
                /* 134 */personAmount = 
                        Double.valueOf(DoubleProcess.round(Double.parseDouble(row.getAttribute("DistributionAmount").toString()), 
                                                           4, 4));
                /*     */
            }
            /* 136 */if (personAmount.doubleValue() <= 0.0D)
            /*     */ {
                /* 347 */return "2";
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 143 */String Smessage = "";
        /*     */
        /* 145 */String Sbudget = "";
        /*     */
        /* 147 */double Dbudget = 0.0D;
        /* 148 */Sbudget = get_budget_result(am, project_id);
        /* 149 */Dbudget = Double.parseDouble(Sbudget);
        /*     */
        /* 153 */String Sdistr = "";
        /*     */
        /* 155 */double Ddistr = 0.0D;
        /* 156 */Sdistr = get_project_distributed(am, project_id, "6");
        /* 157 */Ddistr = Double.parseDouble(Sdistr);
        /*     */
        /* 161 */double Dsaved = 0.0D;
        /* 162 */for (int nn = 0; nn < Rows.length; nn++)
        /*     */ {
            /* 165 */Row row = Rows[nn];
            /* 166 */if (row.getAttribute("EmployeeNumber") != null)
            /*     */ {
                /* 169 */Dsaved += 
                        Double.parseDouble(row.getAttribute("DistributionAmount").toString());
                /*     */
            }
            /* 171 */if (Dsaved + Ddistr > Dbudget)
            /*     */ {
                /* 173 */System.out.println("Dsaved=" + Dsaved);
                /* 174 */System.out.println("Ddistr=" + Ddistr);
                /* 175 */System.out.println("Dbudget=" + Dbudget);
                /*     */
                /* 177 */Smessage = "提交失败！该项目发放金额超预算,请检查！";
                /*     */
            }
            /*     */
        }
        /* 180 */if (!"".equals(Smessage))
        /*     */ {
            /* 182 */vo = getMemberDistrVO1();
            /*     */
            /* 347 */return Smessage;
            /*     */
        }
        /*     */
        /* 191 */Row[] oldRow = vo.getFilteredRows("Attribute1", "Y");
        /* 192 */for (int i = 0; i < oldRow.length; i++) {
            /* 193 */Row pRow = oldRow[i];
            /* 194 */Integer distId = 
                Integer.valueOf(Integer.parseInt(pRow.getAttribute("PersonDistributionId").toString()));
            /* 195 */String personNumber = "";
            /* 196 */if (pRow.getAttribute("EmployeeNumber") != null) {
                /* 197 */personNumber = 
                        pRow.getAttribute("EmployeeNumber").toString();
                /*     */
            }
            /* 199 */String personName = "";
            /* 200 */if (pRow.getAttribute("LastName") != null) {
                /* 201 */personName = pRow.getAttribute("LastName").toString();
                /*     */
            }
            /* 203 */String personDept = "";
            /* 204 */if (pRow.getAttribute("DepName") != null) {
                /* 205 */personDept = pRow.getAttribute("DepName").toString();
                /*     */
            }
            /*     */
            /* 208 */Double personAmount = Double.valueOf(0.0D);
            /* 209 */if (pRow.getAttribute("DistributionAmount") != null) {
                /* 210 */personAmount = 
                        Double.valueOf(DoubleProcess.round(Double.parseDouble(pRow.getAttribute("DistributionAmount").toString()), 
                                                           4, 4));
                /*     */
            }
            /*     */
            /* 213 */String personNote = "";
            /* 214 */if (pRow.getAttribute("Note") != null) {
                /* 215 */personNote = pRow.getAttribute("Note").toString();
                /*     */
            }
            /*     */
            /* 218 */String personDate = "";
            /* 219 */if (pRow.getAttribute("DistributionDate") != null) {
                /* 220 */personDate = 
                        pRow.getAttribute("DistributionDate").toString();
                /*     */
            }
            /*     */
            /* 223 */CallableStatement cs = null;
            /* 224 */OADBTransaction transaction = getOADBTransaction();
            /* 225 */cs = 
                    transaction.createCallableStatement("call cux_hr_bonus.update_project_distribution(?, ?, ?, ?, ?, ?, ?, ?)", 
                                                        1);
            /*     */try {
                /* 227 */cs.registerOutParameter(1, 12);
                /* 228 */cs.setInt(2, distId.intValue());
                /* 229 */cs.setString(3, personNumber);
                /* 230 */cs.setString(4, personName);
                /* 231 */cs.setString(5, personDept);
                /* 232 */cs.setDouble(6, personAmount.doubleValue());
                /* 233 */cs.setString(7, personNote);
                /* 234 */cs.setString(8, personDate);
                /* 235 */System.out.println("personDate" + personDate);
                /* 236 */cs.executeUpdate();
                /* 237 */String retcode = cs.getString(1);
                /* 238 */if ("success".equals(retcode)) {
                    /* 239 */transaction.commit();
                    /*     */
                } else {
                    /* 241 */if ("person_info error".equals(retcode)) {
                        /* 242 */return "1";
                        /*     */
                    }
                    /*     */
                    /* 245 */return "3";
                    /*     */
                }
                /*     */
            }
            /*     */ catch (Exception e) {
                /* 249 */System.out.println(e.toString());
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 254 */Row[] newRow = vo.getFilteredRows("Attribute1", "N");
        /*     */
        /* 257 */for (int i = 0; i < newRow.length; i++) {
            /* 258 */Row pRow = newRow[i];
            /* 259 */if (pRow.getAttribute("EmployeeNumber") != null)
            /*     */ {
                /* 263 */String distDate = "";
                /* 264 */if (pRow.getAttribute("DistributionDate") != null)
                /*     */ {
                    /* 266 */distDate = 
                            pRow.getAttribute("DistributionDate").toString();
                    /*     */
                }
                /*     */ else
                /*     */ {
                    /* 347 */return "5";
                    /*     */
                }
                /* 271 */String personNumber = "";
                /* 272 */if (pRow.getAttribute("EmployeeNumber") != null) {
                    /* 273 */personNumber = 
                            pRow.getAttribute("EmployeeNumber").toString();
                    /*     */
                }
                /*     */
                /* 276 */String personName = "";
                /* 277 */if (pRow.getAttribute("LastName") != null) {
                    /* 278 */personName = 
                            pRow.getAttribute("LastName").toString();
                    /*     */
                }
                /*     */
                /* 281 */String personDept = "";
                /* 282 */if (pRow.getAttribute("DepName") != null) {
                    /* 283 */personDept = 
                            pRow.getAttribute("DepName").toString();
                    /*     */
                }
                /*     */
                /* 286 */if ((personNumber == "") || (personName == "") || 
                             (personDept == ""))
                /*     */ {
                    /* 347 */return "1";
                    /*     */
                }
                /* 289 */Double personAmount = Double.valueOf(0.0D);
                /* 290 */if (pRow.getAttribute("DistributionAmount") != null) {
                    /* 291 */personAmount = 
                            Double.valueOf(DoubleProcess.round(Double.parseDouble(pRow.getAttribute("DistributionAmount").toString()), 
                                                               4, 4));
                    /*     */
                }
                /*     */
                /* 294 */if (personAmount.doubleValue() <= 0.0D)
                /*     */ {
                    /* 347 */return "2";
                    /*     */
                }
                /* 297 */String personNote = "";
                /* 298 */if (pRow.getAttribute("Note") != null) {
                    /* 299 */personNote = pRow.getAttribute("Note").toString();
                    /*     */
                }
                /* 301 */CallableStatement cs = null;
                /* 302 */OADBTransaction transaction = getOADBTransaction();
                /* 303 */cs = 
                        transaction.createCallableStatement("call cux_hr_bonus.insert_person_distribution(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)", 
                                                            1);
                /*     */try {
                    /* 305 */cs.registerOutParameter(1, 12);
                    /* 306 */cs.setString(2, personNumber);
                    /* 307 */System.out.println("personNumber" + personNumber);
                    /* 308 */cs.setString(3, personName);
                    /* 309 */System.out.println("personName" + personName);
                    /* 310 */cs.setString(4, "");
                    /* 311 */cs.setString(5, personDept);
                    /* 312 */System.out.println("personDept" + personDept);
                    /* 313 */cs.setDouble(6, personAmount.doubleValue());
                    /* 314 */System.out.println("personAmount" + personAmount);
                    /* 315 */cs.setString(7, personNote);
                    /* 316 */System.out.println("personNote" + personNote);
                    /* 317 */cs.setString(8, distDate);
                    /* 318 */System.out.println("distDate" + distDate);
                    /* 319 */cs.setString(9, bonusTypeName);
                    /* 320 */System.out.println("bonusTypeName" + 
                                                bonusTypeName);
                    /* 321 */cs.setInt(10, Integer.parseInt(project_id));
                    /* 322 */System.out.println("project_id" + project_id);
                    /* 323 */cs.setString(11, "ME");
                    /* 324 */cs.setInt(12, 0);
                    /*     */
                    /* 326 */cs.executeUpdate();
                    /* 327 */String retcode = cs.getString(1);
                    /* 328 */if ("success".equals(retcode)) {
                        /* 329 */transaction.commit();
                        /* 330 */System.out.println("success");
                        /*     */
                    } else {
                        /* 332 */if ("person_info error".equals(retcode)) {
                            /* 333 */return "1";
                            /*     */
                        }
                        /*     */
                        /* 336 */return "3";
                        /*     */
                    }
                    /*     */
                }
                /*     */ catch (Exception e) {
                    /* 340 */System.out.println(e.toString());
                    /* 341 */System.out.println("catch");
                    /*     */
                }
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 347 */return "0";
        /*     */
    }
    /*     */
    /*     */

    public String GetProjectID(String num) {
        /*     */try
        /*     */ {
            /* 369 */OADBTransaction transaction = getOADBTransaction();
            /* 370 */CallableStatement cs = 
                transaction.createCallableStatement("select t.project_id from pa_projects_all t where t.segment1='" + 
                                                    num + "'", 1);
            /*     */
            /* 373 */ResultSet rs = 
                cs.executeQuery("select t.project_id from pa_projects_all t where t.segment1='" + 
                                num + "'");
            /* 374 */if (rs.next()) {
                /* 375 */return Integer.toString(rs.getInt(1));
                /*     */
            }
            /*     */
            /* 378 */return null;
            /*     */
        }
        /*     */ catch (Exception e)
        /*     */ {
            /* 382 */return null;
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public String get_budget_result(OAApplicationModule am, 
                                    String p_project_id) {
        /* 388 */String result = "";
        /* 389 */OADBTransaction transaction = am.getOADBTransaction();
        /* 390 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_budget(?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 394 */cs.setString(1, p_project_id);
            /* 395 */cs.registerOutParameter(2, 12);
            /* 396 */cs.executeQuery();
            /* 397 */result = cs.getString(2);
            /* 398 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 401 */System.out.println(e.toString());
            /*     */
        }
        /* 403 */if (cs != null) {
            /*     */try {
                /* 405 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 407 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 411 */return result;
        /*     */
    }
    /*     */
    /*     */

    public String get_project_distributed(OAApplicationModule am, 
                                          String p_project_id, 
                                          String p_type_id) {
        /* 417 */String result = "";
        /* 418 */OADBTransaction transaction = am.getOADBTransaction();
        /* 419 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_distributed(?,?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 423 */cs.setString(1, p_project_id);
            /* 424 */cs.setString(2, p_type_id);
            /* 425 */cs.registerOutParameter(3, 12);
            /* 426 */cs.executeQuery();
            /* 427 */result = cs.getString(3);
            /* 428 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 431 */System.out.println(e.toString());
            /*     */
        }
        /* 433 */if (cs != null) {
            /*     */try {
                /* 435 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 437 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 441 */return result;
        /*     */
    }
    /*     */
    /*     */

    public SpecialAwardsPersonLovVOImpl getSpecialAwardsPersonLovVO1() {
        /* 450 */return (SpecialAwardsPersonLovVOImpl)findViewObject("SpecialAwardsPersonLovVO1");
        /*     */
    }
    /*     */
    /*     */

    public LineStatusVOImpl getLineStatusVO1() {
        /* 456 */return (LineStatusVOImpl)findViewObject("LineStatusVO1");
        /*     */
    }
    /*     */
    /*     */

    public MemberDistrVOImpl getMemberDistrVO1() {
        /* 462 */return (MemberDistrVOImpl)findViewObject("MemberDistrVO1");
        /*     */
    }
    /*     */
    /*     */

    public DistrProjectVOImpl getDistrProjectVO1() {
        /* 468 */return (DistrProjectVOImpl)findViewObject("DistrProjectVO1");
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\member\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.member.server.MemberGrantAMImpl
 * JD-Core Version:    0.6.1
 */