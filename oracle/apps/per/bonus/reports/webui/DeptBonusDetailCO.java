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
/*    */public class DeptBonusDetailCO extends OAControllerImpl {
    /*    */
    public static final String RCS_ID = "$Header$";
    /* 25 */
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");
    /*    */
    /*    */

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        /* 35 */super.processRequest(pageContext, webBean);
        /*    */
        /* 37 */BonusReportsAMImpl am = 
            (BonusReportsAMImpl)pageContext.getApplicationModule(webBean);
        /* 38 */OAMessageChoiceBean choicebean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("YearInput");
        /* 39 */String dateStr = Date.getCurrentDate().toString();
        /* 40 */String currentYear = dateStr.substring(0, 4);
        /*    */
        /* 43 */choicebean.setDefaultValue(currentYear);
        /*    */
    }
    /*    */
    /*    */

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /* 54 */super.processFormRequest(pageContext, webBean);
        /*    */
        /* 56 */String event = pageContext.getParameter("event");
        /*    */
        /* 58 */System.out.println(event);
        /* 59 */if (event.equals("search"))
        /*    */ {
            /* 61 */OAMessageChoiceBean bean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("YearInput");
            /* 62 */OAMessageChoiceBean monthbean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("MonthInput");
            /*    */
            /* 64 */String yearInput = (String)bean.getValue(pageContext);
            /* 65 */String monthInput = 
                (String)monthbean.getValue(pageContext);
            /* 66 */BonusReportsAMImpl am = 
                (BonusReportsAMImpl)pageContext.getApplicationModule(webBean);
            /* 67 */am.searchDeptBonusDetail(yearInput, monthInput);
            /*    */
        }
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\reports\webui\
 * Qualified Name:     cux.oracle.apps.per.bonus.reports.webui.DeptBonusDetailCO
 * JD-Core Version:    0.6.1
 */