/*     */package cux.oracle.apps.per.bonus.reports.server;
/*     */
/*     */import java.io.PrintStream;
/*     */
import java.sql.CallableStatement;
/*     */
import java.sql.ResultSet;
/*     */
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
/*     */
import oracle.apps.fnd.framework.server.OADBTransaction;
/*     */
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
/*     */
/*     */public class BonusReportsAMImpl extends OAApplicationModuleImpl {
    /*     */

    public static void main(String[] args) {
        /*  30 */launchTester("cux.oracle.apps.per.bonus.reports.server", 
                              "BonusReportsAMLocal");
        /*     */
    }
    /*     */
    /*     */

    public YearListVOImpl getYearListVO1() {
        /*  39 */return (YearListVOImpl)findViewObject("YearListVO1");
        /*     */
    }
    /*     */
    /*     */

    public void searchDeptBonusVO(String year) {
        /*  93 */StringBuffer sbfSQLValue = new StringBuffer(200);
        /*  94 */sbfSQLValue.append("SELECT TEMP.DEP 计奖部门,\n       AVG(DECODE(MON, " + 
                                    year + "01, AMOUNT, NULL)) 一月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "02, AMOUNT, NULL)) 二月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "03, AMOUNT, NULL)) 三月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "04, AMOUNT, NULL)) 四月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "05, AMOUNT, NULL)) 五月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "06, AMOUNT, NULL)) 六月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "07, AMOUNT, NULL)) 七月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "08, AMOUNT, NULL)) 八月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "09, AMOUNT, NULL)) 九月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "10, AMOUNT, NULL)) 十月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "11, AMOUNT, NULL)) 十一月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "12, AMOUNT, NULL)) 十二月,\n" + 
                                    "       NVL((SELECT SUM(cbpd.distribution_amount)\n" + 
                                    "             FROM CUX_BONUS_PERSON_DISTRIBUTION cbpd, cux_bonus_type cbt\n" + 
                                    "            WHERE cbpd.type_id = cbt.type_id\n" + 
                                    "              and cbt.type_name = '年终奖金'\n" + 
                                    "              and cbpd.distribution_status in ('已批准','已提交') and cbpd.distribution_status not in ('已退回')\n" + 
                                    "              and cbpd.distribution_date between\n" + 
                                    "                  to_date('" + year + 
                                    "-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and\n" + 
                                    "                  to_date('" + year + 
                                    "-12-31 23:59:59', 'YYYY-MM-DD HH24:MI:SS')\n" + 
                                    "              AND CBPD.DEP_NAME IN\n" + 
                                    "                  (SELECT HAOU.NAME\n" + 
                                    "                     FROM HR_ALL_ORGANIZATION_UNITS HAOU, HR_LOOKUPS HL\n" + 
                                    "                    WHERE HL.LOOKUP_TYPE(+) = 'cux.PROJECT_AWARD_TYPE'\n" + 
                                    "                      AND ENABLED_FLAG(+) = 'Y'\n" + 
                                    "                      AND HAOU.ATTRIBUTE8 = HL.LOOKUP_CODE(+)\n" + 
                                    "                      AND haou.attribute7 = TEMP.DEP)),\n" + 
                                    "           0) 年终奖金,\n" + 
                                    "       SUM(AMOUNT) +\n" + 
                                    "       NVL((SELECT SUM(cbpd.distribution_amount)\n" + 
                                    "             FROM CUX_BONUS_PERSON_DISTRIBUTION cbpd, cux_bonus_type cbt\n" + 
                                    "            WHERE cbpd.type_id = cbt.type_id\n" + 
                                    "              and cbt.type_name = '年终奖金'\n" + 
                                    "              and cbpd.distribution_status in ('已批准','已提交') and cbpd.distribution_status not in ('已退回')\n" + 
                                    "              and cbpd.distribution_date between\n" + 
                                    "                  to_date('" + year + 
                                    "-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and\n" + 
                                    "                  to_date('" + year + 
                                    "-12-31 23:59:59', 'YYYY-MM-DD HH24:MI:SS')\n" + 
                                    "              AND CBPD.DEP_NAME IN\n" + 
                                    "                  (SELECT HAOU.NAME\n" + 
                                    "                     FROM HR_ALL_ORGANIZATION_UNITS HAOU, HR_LOOKUPS HL\n" + 
                                    "                    WHERE HL.LOOKUP_TYPE(+) = 'cux.PROJECT_AWARD_TYPE'\n" + 
                                    "                      AND ENABLED_FLAG(+) = 'Y'\n" + 
                                    "                      AND HAOU.ATTRIBUTE8 = HL.LOOKUP_CODE(+)\n" + 
                                    "                      AND haou.attribute7 = TEMP.DEP)),\n" + 
                                    "           0) 总金额\n" + 
                                    "  FROM (SELECT HAOU.attribute7 DEP,\n" + 
                                    "               CBDD.DISTRIBUTION_PERIOD MON,\n" + 
                                    "               SUM(CBDD.DISTRIBUTION_AMOUNT) AMOUNT\n" + 
                                    "          FROM HR_ALL_ORGANIZATION_UNITS  HAOU,\n" + 
                                    "               HR_LOOKUPS                 HL,\n" + 
                                    "               cux_bonus_dep_distribution CBDD\n" + 
                                    "         WHERE HL.LOOKUP_TYPE(+) = 'cux.PROJECT_AWARD_TYPE'\n" + 
                                    "           AND ENABLED_FLAG(+) = 'Y'\n" + 
                                    "           AND HAOU.ATTRIBUTE8 = HL.LOOKUP_CODE(+)\n" + 
                                    "           AND HAOU.attribute7 IS NOT NULL\n" + 
                                    "           AND CBDD.DEP_ID = HAOU.ORGANIZATION_ID\n" + 
                                    "           AND CBDD.DISTRIBUTION_PERIOD BETWEEN " + 
                                    year + "00 AND " + year + "13\n" + 
                                    "         GROUP BY CBDD.DISTRIBUTION_PERIOD, HAOU.ATTRIBUTE7) TEMP\n" + 
                                    "\n" + " GROUP BY DEP\n");
        /*     */
        /* 159 */OAViewObjectImpl vo = getBonusDistributionVO1();
        /* 160 */vo.cancelQuery();
        /* 161 */vo.setQuery(sbfSQLValue.toString());
        /* 162 */vo.executeQuery();
        /*     */
    }
    /*     */
    /*     */

    public void searchActualDistribution(String year) {
        /* 172 */StringBuffer sbfSQLValue = new StringBuffer(200);
        /* 173 */sbfSQLValue.append("SELECT TEMP.DEP 部门,\n       TEMP.DISTRDEP 计奖部门,\n       AVG(DECODE(MON, " + 
                                    year + "01, AMOUNT, NULL)) 一月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "02, AMOUNT, NULL)) 二月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "03, AMOUNT, NULL)) 三月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "04, AMOUNT, NULL)) 四月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "05, AMOUNT, NULL)) 五月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "06, AMOUNT, NULL)) 六月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "07, AMOUNT, NULL)) 七月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "08, AMOUNT, NULL)) 八月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "09, AMOUNT, NULL)) 九月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "10, AMOUNT, NULL)) 十月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "11, AMOUNT, NULL)) 十一月,\n" + 
                                    "       AVG(DECODE(MON, " + year + 
                                    "12, AMOUNT, NULL)) 十二月,\n" + 
                                    "       NVL((SELECT SUM(cbpd.distribution_amount)\n" + 
                                    "             FROM CUX_BONUS_PERSON_DISTRIBUTION cbpd, cux_bonus_type cbt\n" + 
                                    "            WHERE cbpd.dep_name = TEMP.DEP\n" + 
                                    "              and cbpd.type_id = cbt.type_id\n" + 
                                    "              and cbt.type_name = '年终奖金'\n" + 
                                    "              and cbpd.distribution_status in ('已批准','已提交') and cbpd.distribution_status not in ('已退回')\n" + 
                                    "              and cbpd.distribution_date between\n" + 
                                    "                  to_date('" + year + 
                                    "-01-01 00:00:00', 'YYYY-MM-DD HH24-:MI:SS') and\n" + 
                                    "                  to_date('" + year + 
                                    "-12-31 23:59:59', 'YYYY-MM-DD HH24-:MI:SS')),\n" + 
                                    "           0) 年终奖金,\n" + 
                                    "       NVL((SELECT SUM(cbpd.distribution_amount)\n" + 
                                    "             FROM CUX_BONUS_PERSON_DISTRIBUTION cbpd, cux_bonus_type cbt\n" + 
                                    "            WHERE cbpd.dep_name = TEMP.DEP\n" + 
                                    "              and cbpd.type_id = cbt.type_id\n" + 
                                    "              and cbt.type_name = '年终奖金'\n" + 
                                    "              and cbpd.distribution_status in ('已批准','已提交') and cbpd.distribution_status not in ('已退回')\n" + 
                                    "              and cbpd.distribution_date between\n" + 
                                    "                  to_date('" + year + 
                                    "-01-01 00:00:00', 'YYYY-MM-DD HH24-:MI:SS') and\n" + 
                                    "                  to_date('" + year + 
                                    "-12-31 23:59:59', 'YYYY-MM-DD HH24-:MI:SS')),\n" + 
                                    "           0) + SUM(AMOUNT) 总金额\n" + 
                                    "  FROM (SELECT HAOU.attribute7 DISTRDEP,\n" + 
                                    "               CBDD.Dep_Name DEP,\n" + 
                                    "               CBDD.DISTRIBUTION_PERIOD MON,\n" + 
                                    "               SUM(CBDD.DISTRIBUTION_AMOUNT) AMOUNT\n" + 
                                    "          FROM HR_ALL_ORGANIZATION_UNITS  HAOU,\n" + 
                                    "               HR_LOOKUPS                 HL,\n" + 
                                    "               cux_bonus_dep_distribution CBDD\n" + 
                                    "         WHERE HL.LOOKUP_TYPE(+) = 'cux.PROJECT_AWARD_TYPE'\n" + 
                                    "           AND ENABLED_FLAG(+) = 'Y'\n" + 
                                    "           AND HAOU.ATTRIBUTE8 = HL.LOOKUP_CODE(+)\n" + 
                                    "           AND CBDD.DEP_ID = HAOU.ORGANIZATION_ID\n" + 
                                    "           AND CBDD.DISTRIBUTION_PERIOD BETWEEN " + 
                                    year + "00 AND " + year + "13\n" + 
                                    "         GROUP BY CBDD.DISTRIBUTION_PERIOD, CBDD.Dep_Name, HAOU.ATTRIBUTE7) TEMP\n" + 
                                    " GROUP BY TEMP.DEP, TEMP.DISTRDEP\n");
        /*     */
        /* 225 */OAViewObjectImpl vo = getBonusActualDistributionVO1();
        /* 226 */vo.cancelQuery();
        /* 227 */vo.setQuery(sbfSQLValue.toString());
        /* 228 */vo.executeQuery();
        /*     */
    }
    /*     */
    /*     */

    public OAViewObjectImpl getBonusDistributionVO1() {
        /* 390 */return (OAViewObjectImpl)findViewObject("BonusDistributionVO1");
        /*     */
    }
    /*     */
    /*     */

    public BonusActualDistributionVOImpl getBonusActualDistributionVO1() {
        /* 396 */return (BonusActualDistributionVOImpl)findViewObject("BonusActualDistributionVO1");
        /*     */
    }
    /*     */
    /*     */

    public ProjectManagementBonusVOImpl getProjectManagementBonusVO1() {
        /* 402 */return (ProjectManagementBonusVOImpl)findViewObject("ProjectManagementBonusVO1");
        /*     */
    }
    /*     */
    /*     */

    public void searchDeptBonusDetail(String year, String month) {
        /* 406 */StringBuffer buff = new StringBuffer(200);
        /*     */
        /* 408 */buff.append("SELECT V1.年度,\n       V1.月份,\n       v1.部门,\n       SUM(V1.奖金额度) 奖金额度,\n       SUM(DECODE(COMPANY, 1, TOTALAMOUNT, NULL)) 股份发放,\n       SUM(DECODE(COMPANY, 2, TOTALAMOUNT, NULL)) 上海发放\n       \n  FROM (SELECT CAST(CBDD.DISTRIBUTION_PERIOD / 100 as decimal(18, 0)) 年度,\n               CBDD.DISTRIBUTION_PERIOD -\n               100 * CAST(CBDD.DISTRIBUTION_PERIOD / 100 as decimal(18, 0)) 月份,\n               CBDD.DISTRIBUTION_PERIOD PERIOD,\n               CBDD.DEP_NAME 部门,\n               CBDD.DEP_ID DEPID,\n               SUM(CBDD.DISTRIBUTION_AMOUNT) 奖金额度     \n          FROM cux_bonus_dep_distribution CBDD");
        /*     */
        /* 423 */if ((month == null) || (month.length() == 0))
        /*     */ {
            /* 426 */buff.append("         WHERE CBDD.DISTRIBUTION_PERIOD BETWEEN " + 
                                 year + "00 AND " + year + "13\n");
            /*     */
        }
        /*     */ else
        /*     */ {
            /* 430 */month = month.trim();
            /* 431 */if (month.length() == 1)
                /* 432 */month = "0" + month;
            /* 433 */buff.append("         WHERE CBDD.DISTRIBUTION_PERIOD = " + 
                                 year + month + "\n");
            /*     */
        }
        /* 435 */buff.append("AND CBDD.distribution_status in ('已批准','已提交') and CBDD.distribution_status not in ('已退回') GROUP BY CBDD.DISTRIBUTION_PERIOD, CBDD.DEP_NAME, CBDD.DEP_ID) V1,\n       (SELECT COMPANY,\n               SUM(AMOUNT) TOTALAMOUNT,\n               TEMP.DISDEP,\n               TO_NUMBER(TO_CHAR(DISTRIBUTION_DATE,'YYYYMM'),'999999') DISTRPERIOD\n          FROM (SELECT CBPD.DISTRIBUTION_AMOUNT AMOUNT,\n                       CBPD.DISTRIBUTION_DEP_ID DISDEP,\n                       CBPD.DISTRIBUTION_DATE,\n                       NVL(CUX_HR_BONUS.get_distribution_company(CBPD.DISTRIBUTION_DATE,\n                                                                 CBPD.DEP_ID,\n                                                                 '中冶赛迪工程技术股份有限公司',\n                                                                 '中冶赛迪上海工程技术有限公司'),\n                           0) COMPANY\n                  FROM CUX_BONUS_PERSON_DISTRIBUTION CBPD\n                 WHERE cbpd.distribution_status in ('已批准','已提交') and cbpd.distribution_status not in ('已退回')\n                   AND CBPD.DISTRIBUTION_DEP_ID IS NOT NULL\n                   AND CBPD.DISTRIBUTION_DATE BETWEEN");
        /*     */
        /* 454 */if ((month == null) || (month.length() == 0))
        /*     */ {
            /* 456 */buff.append("                       to_date('" + year + 
                                 "-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and\n" + 
                                 "                       to_date('" + year + 
                                 "-12-31 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) TEMP\n");
            /*     */
        }
        /*     */ else
        /*     */ {
            /* 462 */if (month.length() == 1)
                /* 463 */month = "0" + month;
            /* 464 */int m = Integer.parseInt(month);
            /* 465 */if (m < 12)
            /*     */ {
                /* 467 */String nextMonth = "" + (m + 1);
                /* 468 */buff.append("                       to_date('" + 
                                     year + "-" + month + 
                                     "-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and\n" + 
                                     "                       to_date('" + 
                                     year + "-" + nextMonth + 
                                     "-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')) TEMP\n");
                /*     */
            }
            /*     */ else
            /*     */ {
                /* 475 */buff.append("                       to_date('" + 
                                     year + "-" + month + 
                                     "-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and\n" + 
                                     "                       to_date('" + 
                                     year + 
                                     "-12-31 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) TEMP\n");
                /*     */
            }
            /*     */
            /*     */
        }
        /*     */
        /* 481 */buff.append(" WHERE COMPANY != 0\n         GROUP BY COMPANY, TEMP.DISDEP,DISTRIBUTION_DATE) V2\n WHERE V2.DISDEP(+)=V1.DEPID AND V2.DISTRPERIOD(+)=V1.PERIOD\n GROUP BY V1.年度, V1.月份, v1.部门\n ORDER BY V1.年度, V1.月份");
        /*     */
        /* 489 */System.out.println(buff.toString());
        /*     */
        /* 491 */OAViewObjectImpl vo = getDeptBonusDetailVO1();
        /* 492 */vo.cancelQuery();
        /* 493 */vo.setQuery(buff.toString());
        /* 494 */vo.executeQuery();
        /*     */
    }
    /*     */
    /*     */

    public void searchProjectManBonus(String year, String projectID) {
        /* 499 */StringBuffer sbfSQLValue = new StringBuffer(200);
        /* 500 */sbfSQLValue.append("select ppa.segment1 项目编号,\n       ppa.name 项目名称,\n       cbpd.distribution_date 下发日期,\n       cbpd.distribution_amount 下发金额,\n       NVL((select sum(cp.plan_cost)\n             from cux_bi_pa_cost_plan_v cp\n            where cux_bi_pa_util_pkg.is_pm_related_fbs_task(cp.fbs_proj_element_id) = 1\n              and cp.resource_class_code = 'PEOPLE'\n              and cp.current_flag = 'Y'\n              and cp.project_id = ppa.project_id),\n           0) 总额,\n       cux_hr_bonus.GET_PROJECT_MANAGER_NAME(ppa.project_id) 项目经理\n  from cux_bonus_person_distribution cbpd,\n       cux_bonus_type                cbt,\n       pa_projects_all               ppa\n where ppa.project_id = cbpd.project_id\n   and cbpd.type_id = cbt.type_id\n   and cbt.bonus_type = '项目奖'\n   and cbpd.distribution_status in ('已批准','已提交') and cbpd.distribution_status not in ('已退回')\n");
        /*     */
        /* 522 */if (year != null)
        /*     */ {
            /* 524 */sbfSQLValue.append("and cbpd.distribution_date between\n                  to_date('" + 
                                        year + 
                                        "-01-01 00:00:00', 'YYYY-MM-DD HH24-:MI:SS') and\n" + 
                                        "                  to_date('" + year + 
                                        "-12-31 23:59:59', 'YYYY-MM-DD HH24-:MI:SS') ");
            /*     */
        }
        /*     */
        /* 529 */if ((projectID != null) && (projectID.trim().length() > 0))
        /*     */ {
            /* 531 */sbfSQLValue.append("and ppa.segment1='" + projectID + 
                                        "'");
            /*     */
        }
        /*     */
        /* 534 */OAViewObjectImpl vo = getProjectManagementBonusVO1();
        /* 535 */vo.cancelQuery();
        /* 536 */vo.setQuery(sbfSQLValue.toString());
        /* 537 */vo.executeQuery();
        /*     */
    }
    /*     */
    /*     */

    public MonthListVOImpl getMonthListVO1() {
        /* 544 */return (MonthListVOImpl)findViewObject("MonthListVO1");
        /*     */
    }
    /*     */
    /*     */

    public DeptBonusDetailVOImpl getDeptBonusDetailVO1() {
        /* 550 */return (DeptBonusDetailVOImpl)findViewObject("DeptBonusDetailVO1");
        /*     */
    }
    /*     */
    /*     */

    public ProjectVOImpl getProjectVO1() {
        /* 556 */return (ProjectVOImpl)findViewObject("ProjectVO1");
        /*     */
    }
    /*     */
    /*     */

    public Boolean CheckProjectNum(String num) {
        /*     */try
        /*     */ {
            /* 566 */OADBTransaction transaction = getOADBTransaction();
            /* 567 */CallableStatement cs = 
                transaction.createCallableStatement("select t.segment1,t.name from pa_projects_all t where t.segment1='" + 
                                                    num + "'", 1);
            /*     */
            /* 570 */ResultSet rs = 
                cs.executeQuery("select t.segment1,t.name from pa_projects_all t where t.segment1='" + 
                                num + "'");
            /* 571 */if (rs.next()) {
                /* 572 */return Boolean.valueOf(true);
                /*     */
            }
            /*     */
            /* 575 */return Boolean.valueOf(false);
            /*     */
        }
        /*     */ catch (Exception e)
        /*     */ {
            /* 579 */return Boolean.valueOf(false);
            /*     */
        }
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\reports\server\
 * Qualified Name:     cux.oracle.apps.per.bonus.reports.server.BonusReportsAMImpl
 * JD-Core Version:    0.6.1
 */