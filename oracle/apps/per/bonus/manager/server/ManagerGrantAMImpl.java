/*     */package cux.oracle.apps.per.bonus.manager.server;
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
/*     */public class ManagerGrantAMImpl extends OAApplicationModuleImpl {
    /*     */

    public static void main(String[] args) {
        /*  38 */launchTester("cux.oracle.apps.per.bonus.manager.server", 
                              "ManagerGrantAMLocal");
        /*     */
    }
    /*     */
    /*     */

    public ManagerGrantVOImpl getManagerGrantVO1() {
        /*  46 */return (ManagerGrantVOImpl)findViewObject("ManagerGrantVO1");
        /*     */
    }
    /*     */
    /*     */

    public void createVOInfo() {
        /*  49 */OAViewObject vo = (OAViewObject)getManagerGrantVO1();
        /*  50 */vo.executeQuery();
        /*     */
    }
    /*     */
    /*     */

    public void DeletePersonLine(String distId) {
        /*  55 */if (!"".equals(distId)) {
            /*  56 */int depDistId = Integer.parseInt(distId);
            /*  57 */CallableStatement cs = null;
            /*  58 */OADBTransaction transaction = getOADBTransaction();
            /*  59 */cs = 
                    transaction.createCallableStatement("call cux_hr_bonus.delete_person_distribution(?)", 
                                                        1);
            /*     */try
            /*     */ {
                /*  63 */cs.setInt(1, depDistId);
                /*  64 */cs.executeUpdate();
                /*     */
            } catch (Exception e) {
                /*  66 */System.out.println(e.toString());
                /*     */
            }
            /*  68 */OAViewObject vo = (OAViewObject)getManagerDistrVO1();
            /*  69 */ManagerDistrVORowImpl row = null;
            /*  70 */int fetchedRowCount = vo.getFetchedRowCount();
            /*  71 */RowSetIterator deleteIter = 
                vo.createRowSetIterator("deleteIter");
            /*  72 */if (fetchedRowCount > 0) {
                /*  73 */deleteIter.setRangeStart(0);
                /*  74 */deleteIter.setRangeSize(fetchedRowCount);
                /*  75 */for (int i = 0; i < fetchedRowCount; i++) {
                    /*  76 */row = 
                            (ManagerDistrVORowImpl)deleteIter.getRowAtRangeIndex(i);
                    /*     */
                    /*  78 */Integer primaryKey = 
                        Integer.valueOf(Integer.parseInt(row.getPersonDistributionId().toString()));
                    /*     */
                    /*  80 */if (primaryKey.compareTo(Integer.valueOf(depDistId)) == 
                                 0) {
                        /*  81 */row.remove();
                        /*  82 */getTransaction().commit();
                        /*  83 */break;
                        /*     */
                    }
                    /*     */
                }
                /*     */
            }
            /*  87 */deleteIter.closeRowSetIterator();
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public String SavePersonDist(OAApplicationModule am, 
                                 String bonusTypeName) {
        /*  94 */ManagerDistrVOImpl vo = getManagerDistrVO1();
        /*     */
        /*  97 */System.out.println("111");
        /*     */
        /* 114 */Row[] Rows = vo.getAllRowsInRange();
        /*     */
        /* 117 */for (int nn = 0; nn < Rows.length; nn++)
        /*     */ {
            /* 119 */Row row = Rows[nn];
            /* 120 */if (row.getAttribute("Manager") == null)
            /*     */ {
                /* 411 */return "1";
                /*     */
            }
            /* 125 */if (row.getAttribute("DepName") == null)
            /*     */ {
                /* 411 */return "1";
                /*     */
            }
            /*     */
            /* 131 */if (row.getAttribute("DistributionDate") == null)
            /*     */ {
                /* 411 */return "4";
                /*     */
            }
            /* 136 */Double personAmount = Double.valueOf(0.0D);
            /* 137 */if (row.getAttribute("DistributionAmount") != null) {
                /* 138 */personAmount = 
                        Double.valueOf(DoubleProcess.round(Double.parseDouble(row.getAttribute("DistributionAmount").toString()), 
                                                           4, 4));
                /*     */
            }
            /* 140 */if (personAmount.doubleValue() <= 0.0D)
            /*     */ {
                /* 411 */return "2";
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 146 */String Smessage = "";
        /* 147 */String Sproject_id = "";
        /* 148 */String Sproject_ids = "";
        /* 149 */String Sbudget = "";
        /*     */
        /* 152 */String Sdistr = "";
        /*     */
        /* 159 */for (int nn = 0; nn < Rows.length; nn++)
        /*     */ {
            /* 163 */Row row = Rows[nn];
            /* 164 */if (row.getAttribute("Segment1") != null)
            /*     */ {
                /* 166 */Sproject_id = row.getAttribute("Segment1").toString();
                /*     */
                /* 168 */if (Sproject_ids.indexOf(Sproject_id) < 0)
                /*     */ {
                    /* 171 */double Dbudget = 0.0D;
                    /* 172 */double Ddistr = 0.0D;
                    /* 173 */double Dsaved = 0.0D;
                    /*     */
                    /* 175 */Sbudget = 
                            get_budget_result(am, GetProjectID(am, Sproject_id));
                    /* 176 */Dbudget = Double.parseDouble(Sbudget);
                    /* 177 */Sdistr = 
                            get_project_distributed(am, GetProjectID(am, 
                                                                     Sproject_id), 
                                                    "6");
                    /* 178 */Ddistr = Double.parseDouble(Sdistr);
                    /*     */
                    /* 182 */Row[] checkRow = 
                        vo.getFilteredRows("Segment1", Sproject_id);
                    /*     */
                    /* 184 */for (int mm = 0; mm < checkRow.length; mm++) {
                        /* 185 */Row pRow = checkRow[mm];
                        /*     */
                        /* 187 */Dsaved += 
                                Double.parseDouble(pRow.getAttribute("DistributionAmount").toString());
                        /*     */
                    }
                    /* 189 */if (Dsaved + Ddistr > Dbudget)
                    /*     */ {
                        /* 191 */System.out.println("Dsaved=" + Dsaved);
                        /* 192 */System.out.println("Ddistr=" + Ddistr);
                        /* 193 */System.out.println("Dbudget=" + Dbudget);
                        /* 194 */Sproject_ids = 
                                Sproject_ids + Sproject_id + ";";
                        /* 195 */Smessage = 
                                Smessage + row.getAttribute("Segment1").toString() + 
                                row.getAttribute("Name").toString();
                        /*     */
                    }
                    /*     */
                }
                /*     */
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 203 */if (!"".equals(Smessage))
        /*     */ {
            /* 205 */Smessage = "提交失败！" + Smessage + ",项目发放金额超预算,请检查！";
            /*     */
            /* 207 */vo = getManagerDistrVO1();
            /*     */
            /* 411 */return Smessage;
            /*     */
        }
        /*     */
        /* 215 */System.out.println("222");
        /*     */
        /* 221 */Row[] oldRow = vo.getFilteredRows("Attribute1", "Y");
        /*     */
        /* 225 */for (int i = 0; i < oldRow.length; i++) {
            /* 226 */Row pRow = oldRow[i];
            /* 227 */Integer distId = 
                Integer.valueOf(Integer.parseInt(pRow.getAttribute("PersonDistributionId").toString()));
            /*     */
            /* 229 */String personNumber = "";
            /* 230 */if (pRow.getAttribute("Manager") != null) {
                /* 231 */personNumber = 
                        pRow.getAttribute("Manager").toString();
                /*     */
                /* 234 */personNumber = 
                        personNumber.substring(personNumber.length() - 6);
                /* 235 */System.out.println("personNumber=" + personNumber);
                /*     */
            }
            /* 237 */String personName = "";
            /* 238 */if (pRow.getAttribute("Manager") != null) {
                /* 239 */personName = pRow.getAttribute("Manager").toString();
                /*     */
                /* 241 */personName = 
                        personName.substring(0, personName.length() - 6);
                /* 242 */System.out.println("personName=" + personName);
                /*     */
            }
            /* 244 */String personDept = "";
            /* 245 */if (pRow.getAttribute("DepName") != null) {
                /* 246 */personDept = pRow.getAttribute("DepName").toString();
                /* 247 */System.out.println("personDept=" + personDept);
                /*     */
            }
            /*     */
            /* 250 */Double personAmount = Double.valueOf(0.0D);
            /* 251 */if (pRow.getAttribute("DistributionAmount") != null) {
                /* 252 */personAmount = 
                        Double.valueOf(DoubleProcess.round(Double.parseDouble(pRow.getAttribute("DistributionAmount").toString()), 
                                                           4, 4));
                /*     */
            }
            /*     */
            /* 256 */String personNote = "";
            /* 257 */if (pRow.getAttribute("Note") != null) {
                /* 258 */personNote = pRow.getAttribute("Note").toString();
                /*     */
            }
            /*     */
            /* 261 */String personDate = "";
            /* 262 */if (pRow.getAttribute("DistributionDate") != null) {
                /* 263 */personDate = 
                        pRow.getAttribute("DistributionDate").toString();
                /*     */
            }
            /*     */
            /* 267 */CallableStatement cs = null;
            /* 268 */OADBTransaction transaction = getOADBTransaction();
            /* 269 */cs = 
                    transaction.createCallableStatement("call cux_hr_bonus.update_project_distribution(?, ?, ?, ?, ?, ?, ?, ?)", 
                                                        1);
            /*     */try {
                /* 271 */System.out.println("begin");
                /* 272 */cs.registerOutParameter(1, 12);
                /* 273 */cs.setInt(2, distId.intValue());
                /* 274 */cs.setString(3, personNumber);
                /* 275 */cs.setString(4, personName);
                /* 276 */cs.setString(5, personDept);
                /* 277 */cs.setDouble(6, personAmount.doubleValue());
                /* 278 */cs.setString(7, personNote);
                /* 279 */cs.setString(8, personDate);
                /* 280 */cs.executeUpdate();
                /* 281 */String retcode = cs.getString(1);
                /* 282 */if ("success".equals(retcode)) {
                    /* 283 */transaction.commit();
                    /*     */
                } else {
                    /* 285 */if ("person_info error".equals(retcode)) {
                        /* 286 */return "1";
                        /*     */
                    }
                    /*     */
                    /* 289 */return "3";
                    /*     */
                }
                /*     */
            }
            /*     */ catch (Exception e) {
                /* 293 */System.out.println(e.toString());
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 300 */System.out.println("new");
        /* 301 */Row[] newRow = vo.getFilteredRows("Attribute1", "N");
        /*     */
        /* 303 */int jj = 0;
        /* 304 */jj = newRow.length;
        /* 305 */System.out.println("jj=" + jj);
        /* 306 */for (int i = 0; i < newRow.length; i++)
        /*     */ {
            /* 311 */System.out.println("new1");
            /* 312 */Row pRow = newRow[i];
            /* 313 */if (pRow.getAttribute("DistributionAmount") != null) {
                /* 314 */String personNumber = "";
                /*     */
                /* 318 */String distDate = "";
                /*     */
                /* 320 */if (pRow.getAttribute("DistributionDate") != null) {
                    /* 321 */distDate = 
                            pRow.getAttribute("DistributionDate").toString();
                    /* 322 */System.out.print("DistributionDate" + distDate);
                    /*     */
                }
                /*     */ else
                /*     */ {
                    /* 411 */return "4";
                    /*     */
                }
                /*     */
                /* 330 */if (pRow.getAttribute("Manager") != null) {
                    /* 331 */personNumber = 
                            pRow.getAttribute("Manager").toString();
                    /* 332 */personNumber = 
                            personNumber.substring(personNumber.length() - 6);
                    /* 333 */System.out.print("personNumber" + personNumber);
                    /*     */
                }
                /* 335 */String personName = "";
                /* 336 */if (pRow.getAttribute("Manager") != null) {
                    /* 337 */personName = 
                            pRow.getAttribute("Manager").toString();
                    /* 338 */personName = 
                            personName.substring(0, personName.length() - 6);
                    /* 339 */System.out.print("personName" + personName);
                    /*     */
                }
                /* 341 */String personDept = "";
                /* 342 */if (pRow.getAttribute("DepName") != null) {
                    /* 343 */personDept = 
                            pRow.getAttribute("DepName").toString();
                    /*     */
                }
                /* 345 */if ((personNumber == "") || (personName == "") || 
                             (personDept == ""))
                /*     */ {
                    /* 411 */return "1";
                    /*     */
                }
                /* 348 */Double personAmount = Double.valueOf(0.0D);
                /* 349 */if (pRow.getAttribute("DistributionAmount") != null) {
                    /* 350 */personAmount = 
                            Double.valueOf(DoubleProcess.round(Double.parseDouble(pRow.getAttribute("DistributionAmount").toString()), 
                                                               4, 4));
                    /*     */
                }
                /* 352 */if (personAmount.doubleValue() <= 0.0D)
                /*     */ {
                    /* 411 */return "2";
                    /*     */
                }
                /* 355 */String personNote = "";
                /* 356 */if (pRow.getAttribute("Note") != null) {
                    /* 357 */personNote = pRow.getAttribute("Note").toString();
                    /*     */
                }
                /*     */
                /* 361 */String projectid = "";
                /* 362 */System.out.println("new2");
                /* 363 */projectid = 
                        GetProjectID(am, pRow.getAttribute("Segment1").toString());
                /* 364 */System.out.print("ProjectId" + projectid);
                /*     */
                /* 367 */CallableStatement cs = null;
                /* 368 */OADBTransaction transaction = getOADBTransaction();
                /* 369 */cs = 
                        transaction.createCallableStatement("call cux_hr_bonus.insert_person_distribution(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)", 
                                                            1);
                /*     */try {
                    /* 371 */cs.registerOutParameter(1, 12);
                    /* 372 */cs.setString(2, personNumber);
                    /* 373 */cs.setString(3, personName);
                    /* 374 */cs.setString(4, "");
                    /* 375 */cs.setString(5, personDept);
                    /* 376 */cs.setDouble(6, personAmount.doubleValue());
                    /* 377 */cs.setString(7, personNote);
                    /* 378 */cs.setString(8, distDate);
                    /* 379 */cs.setString(9, bonusTypeName);
                    /* 380 */System.out.print("personNumber" + personNumber);
                    /* 381 */System.out.print("personName" + personName);
                    /* 382 */System.out.print("personDept" + personDept);
                    /* 383 */System.out.print("personAmount" + personAmount);
                    /* 384 */System.out.print("personNumber" + personNumber);
                    /* 385 */System.out.print("personNote" + personNote);
                    /* 386 */System.out.print("distDate" + distDate);
                    /* 387 */System.out.print("bonusTypeName" + bonusTypeName);
                    /* 388 */System.out.print("projectid" + projectid);
                    /* 389 */cs.setInt(10, Integer.parseInt(projectid));
                    /* 390 */cs.setString(11, "MA");
                    /* 391 */cs.setInt(12, 0);
                    /* 392 */cs.executeUpdate();
                    /* 393 */String retcode = cs.getString(1);
                    /* 394 */if ("success".equals(retcode)) {
                        /* 395 */transaction.commit();
                        /*     */
                    } else {
                        /* 397 */if ("person_info error".equals(retcode)) {
                            /* 398 */return "1";
                            /*     */
                        }
                        /*     */
                        /* 401 */return "3";
                        /*     */
                    }
                    /*     */
                }
                /*     */ catch (Exception e) {
                    /* 405 */System.out.println(e.toString());
                    /*     */
                }
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 411 */return "0";
        /*     */
    }
    /*     */
    /*     */

    public String get_budget_result(OAApplicationModule am, 
                                    String p_project_id) {
        /* 417 */String result = "";
        /* 418 */OADBTransaction transaction = am.getOADBTransaction();
        /* 419 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_budget(?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 423 */cs.setString(1, p_project_id);
            /* 424 */cs.registerOutParameter(2, 12);
            /* 425 */cs.executeQuery();
            /* 426 */result = cs.getString(2);
            /* 427 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 430 */System.out.println(e.toString());
            /*     */
        }
        /* 432 */if (cs != null) {
            /*     */try {
                /* 434 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 436 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 440 */return result;
        /*     */
    }
    /*     */
    /*     */

    public String get_project_distributed(OAApplicationModule am, 
                                          String p_project_id, 
                                          String p_type_id) {
        /* 446 */String result = "";
        /* 447 */OADBTransaction transaction = am.getOADBTransaction();
        /* 448 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_distributed(?,?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 452 */cs.setString(1, p_project_id);
            /* 453 */cs.setString(2, p_type_id);
            /* 454 */cs.registerOutParameter(3, 12);
            /* 455 */cs.executeQuery();
            /* 456 */result = cs.getString(3);
            /* 457 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 460 */System.out.println(e.toString());
            /*     */
        }
        /* 462 */if (cs != null) {
            /*     */try {
                /* 464 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 466 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 470 */return result;
        /*     */
    }
    /*     */
    /*     */

    public String GetProjectID(OAApplicationModule am, String num) {
        /*     */try
        /*     */ {
            /* 478 */OADBTransaction transaction = am.getOADBTransaction();
            /* 479 */CallableStatement cs = 
                transaction.createCallableStatement("select t.project_id from pa_projects_all t where t.segment1='" + 
                                                    num + "'", 1);
            /*     */
            /* 482 */ResultSet rs = 
                cs.executeQuery("select t.project_id from pa_projects_all t where t.segment1='" + 
                                num + "'");
            /* 483 */if (rs.next()) {
                /* 484 */return Integer.toString(rs.getInt(1));
                /*     */
            }
            /*     */
            /* 487 */return null;
            /*     */
        }
        /*     */ catch (Exception e)
        /*     */ {
            /* 491 */return null;
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public static void printViewObject(OAViewObject vo) {
        /* 496 */vo.executeQuery();
        /* 497 */while (vo.hasNext())
        /*     */ {
            /* 499 */Row row = vo.next();
            /* 500 */String rowDataStr = "";
            /* 501 */int numAttrs = vo.getAttributeCount();
            /* 502 */for (int columnNo = 0; columnNo < numAttrs; columnNo++)
            /*     */ {
                /* 505 */Object attrData = row.getAttribute(columnNo);
                /* 506 */rowDataStr = rowDataStr + attrData + "\t";
                /*     */
            }
            /* 508 */System.out.println(rowDataStr);
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public String GetProjectID(String num) {
        /*     */try {
            /* 515 */OADBTransaction transaction = getOADBTransaction();
            /* 516 */CallableStatement cs = 
                transaction.createCallableStatement("select t.project_id from pa_projects_all t where t.segment1='" + 
                                                    num + "'", 1);
            /*     */
            /* 519 */ResultSet rs = 
                cs.executeQuery("select t.project_id from pa_projects_all t where t.segment1='" + 
                                num + "'");
            /* 520 */if (rs.next()) {
                /* 521 */return Integer.toString(rs.getInt(1));
                /*     */
            }
            /*     */
            /* 524 */return null;
            /*     */
        }
        /*     */ catch (Exception e)
        /*     */ {
            /* 528 */return null;
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public LineStatusVOImpl getLineStatusVO1() {
        /* 535 */return (LineStatusVOImpl)findViewObject("LineStatusVO1");
        /*     */
    }
    /*     */
    /*     */

    public ProjectVOImpl getProjectVO1() {
        /* 541 */return (ProjectVOImpl)findViewObject("ProjectVO1");
        /*     */
    }
    /*     */
    /*     */

    public ManagerDistrVOImpl getManagerDistrVO1() {
        /* 547 */return (ManagerDistrVOImpl)findViewObject("ManagerDistrVO1");
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.server.ManagerGrantAMImpl
 * JD-Core Version:    0.6.1
 */