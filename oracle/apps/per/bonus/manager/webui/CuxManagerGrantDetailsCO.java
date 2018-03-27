/*    */package cux.oracle.apps.per.bonus.manager.webui;
/*    */
/*    */import java.io.Serializable;
/*    */
import oracle.apps.fnd.common.VersionInfo;
/*    */
import oracle.apps.fnd.framework.OAApplicationModule;
/*    */
import oracle.apps.fnd.framework.webui.OAControllerImpl;
/*    */
import oracle.apps.fnd.framework.webui.OAPageContext;
/*    */
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
/*    */
/*    */public class CuxManagerGrantDetailsCO extends OAControllerImpl {
    /*    */
    public static final String RCS_ID = "$Header$";
    /* 22 */
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");
    /*    */
    /*    */

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        /* 31 */super.processRequest(pageContext, webBean);
        /*    */
        /* 33 */pageContext.putSessionValue("RequestUrl", 
                                            pageContext.getRequestUrl());
        /*    */
        /* 35 */String RequestUrl = pageContext.getRequestUrl();
        /* 36 */OAApplicationModule am = 
            pageContext.getApplicationModule(webBean);
        /*    */
        /* 38 */if (pageContext.getSessionValue("LOT_ID") == null) {
            /* 39 */String LOT_ID = 
                get_RequestUrl_Parameter(RequestUrl, "LOT_ID");
            /* 40 */Serializable[] parameters = { LOT_ID };
            /* 41 */am.invokeMethod("execQuery", parameters);
            /*    */
        }
        /*    */
    }
    /*    */
    /*    */

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /* 53 */super.processFormRequest(pageContext, webBean);
        /*    */
    }
    /*    */
    /*    */

    public String get_RequestUrl_Parameter(String RequestUrl, String s) {
        /* 57 */int i_start = RequestUrl.indexOf(s);
        /*    */String K_ID;
        /* 59 */if (i_start > -1) {
            /* 60 */i_start = i_start + s.length() + 1;
            /* 61 */int i_end = RequestUrl.indexOf("&", i_start);
            /* 62 */if (i_end == -1) {
                /* 63 */i_end = RequestUrl.length();
                /*    */
            }
            /* 65 */K_ID = RequestUrl.substring(i_start, i_end);
            /*    */
        } else {
            /* 67 */K_ID = "-1";
            /*    */
        }
        /*    */
        /* 70 */return K_ID;
        /*    */
    }
    /*    */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\webui\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.webui.CuxManagerGrantDetailsCO
 * JD-Core Version:    0.6.1
 */