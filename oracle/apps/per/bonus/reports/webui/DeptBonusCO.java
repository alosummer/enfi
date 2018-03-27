/*    */package cux.oracle.apps.per.bonus.reports.webui;
/*    */
/*    */import cux.oracle.apps.per.bonus.reports.server.BonusReportsAMImpl;
/*    */
import java.io.PrintStream;
/*    */
import oracle.apps.fnd.common.VersionInfo;
/*    */
import oracle.apps.fnd.framework.webui.OAControllerImpl;
/*    */
import oracle.apps.fnd.framework.webui.OAPageContext;
/*    */
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
/*    */
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
/*    */
import oracle.jbo.domain.Date;
/*    */
import oracle.sql.DATE;
/*    */
/*    */public class DeptBonusCO extends OAControllerImpl {
    /*    */
    public static final String RCS_ID = "$Header$";
    /* 50 */
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");
    /*    */
    /*    */

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        /* 60 */super.processRequest(pageContext, webBean);
        /* 61 */BonusReportsAMImpl am = 
            (BonusReportsAMImpl)pageContext.getApplicationModule(webBean);
        /* 62 */OAMessageChoiceBean choicebean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("YearInput");
        /* 63 */String currentYear = 
            Date.getCurrentDate().toString().substring(0, 4);
        /*    */
        /* 66 */choicebean.setDefaultValue(currentYear);
        /* 67 */am.searchDeptBonusVO(currentYear);
        /* 68 */am.searchActualDistribution(currentYear);
        /*    */
    }
    /*    */
    /*    */

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /* 80 */super.processFormRequest(pageContext, webBean);
        /*    */
        /* 82 */String event = pageContext.getParameter("event");
        /*    */
        /* 84 */System.out.println(event);
        /* 85 */if (event.equals("search"))
        /*    */ {
            /* 87 */OAMessageChoiceBean bean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("YearInput");
            /*    */
            /* 89 */String yearInput = (String)bean.getValue(pageContext);
            /* 90 */BonusReportsAMImpl am = 
                (BonusReportsAMImpl)pageContext.getApplicationModule(webBean);
            /* 91 */am.searchDeptBonusVO(yearInput);
            /* 92 */am.searchActualDistribution(yearInput);
            /*    */
        }
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\reports\webui\
 * Qualified Name:     cux.oracle.apps.per.bonus.reports.webui.DeptBonusCO
 * JD-Core Version:    0.6.1
 */