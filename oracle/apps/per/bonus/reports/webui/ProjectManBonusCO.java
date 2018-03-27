/*    */package cux.oracle.apps.per.bonus.reports.webui;
/*    */
/*    */import cux.oracle.apps.per.bonus.reports.server.BonusReportsAMImpl;
/*    */
import oracle.apps.fnd.common.VersionInfo;
/*    */
import oracle.apps.fnd.framework.OAException;
/*    */
import oracle.apps.fnd.framework.webui.OAControllerImpl;
/*    */
import oracle.apps.fnd.framework.webui.OAPageContext;
/*    */
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
/*    */
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
/*    */
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
/*    */
import oracle.jbo.domain.Date;
/*    */
import oracle.sql.DATE;
/*    */
/*    */public class ProjectManBonusCO extends OAControllerImpl {
    /*    */
    public static final String RCS_ID = "$Header$";
    /* 29 */
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");
    /*    */
    /*    */

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        /* 39 */super.processRequest(pageContext, webBean);
        /*    */
        /* 41 */BonusReportsAMImpl am = 
            (BonusReportsAMImpl)pageContext.getApplicationModule(webBean);
        /* 42 */OAMessageChoiceBean choicebean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("YearInput");
        /* 43 */String currentYear = 
            Date.getCurrentDate().toString().substring(0, 4);
        /*    */
        /* 46 */choicebean.setDefaultValue(currentYear);
        /* 47 */am.searchProjectManBonus(currentYear, null);
        /*    */
    }
    /*    */
    /*    */

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /* 58 */super.processFormRequest(pageContext, webBean);
        /*    */
        /* 60 */String event = pageContext.getParameter("event");
        /*    */
        /* 63 */if (event.equals("search"))
        /*    */ {
            /* 65 */OAMessageChoiceBean bean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("YearInput");
            /* 66 */OAMessageLovInputBean pro = 
                (OAMessageLovInputBean)webBean.findIndexedChildRecursive("ProjectIDInput");
            /* 67 */String yearInput = 
                ((String)bean.getValue(pageContext)).trim();
            /* 68 */String projectInput = (String)pro.getValue(pageContext);
            /* 69 */if (projectInput != null) {
                /* 70 */projectInput = projectInput.trim();
                /*    */
            }
            /*    */
            /* 73 */if ((projectInput != null) && 
                        (projectInput.indexOf("'") >= 0)) {
                /* 74 */OAException errMessage = 
                    new OAException("错误的项目编号，包敏感字符，可能是SQL注入！", (byte)0);
                /* 75 */pageContext.putDialogMessage(errMessage);
                /*    */
            }
            /*    */ else
            /*    */ {
                /* 79 */BonusReportsAMImpl am = 
                    (BonusReportsAMImpl)pageContext.getApplicationModule(webBean);
                /* 80 */if ((projectInput == null) || 
                            (projectInput.length() == 0) || 
                            (am.CheckProjectNum(projectInput).booleanValue()))
                /*    */ {
                    /* 84 */am.searchProjectManBonus(yearInput, projectInput);
                    /*    */
                }
                /*    */ else
                /*    */ {
                    /* 88 */OAException errMessage = 
                        new OAException("错误的项目编号，请重新输入或选择项目编号！", (byte)0);
                    /* 89 */pageContext.putDialogMessage(errMessage);
                    /*    */
                }
                /*    */
            }
            /*    */
        }
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\reports\webui\
 * Qualified Name:     cux.oracle.apps.per.bonus.reports.webui.ProjectManBonusCO
 * JD-Core Version:    0.6.1
 */