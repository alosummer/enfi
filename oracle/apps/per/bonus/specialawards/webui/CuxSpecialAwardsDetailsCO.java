/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.per.bonus.specialawards.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

/**
 * Controller for ...
 */
public class CuxSpecialAwardsDetailsCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    private static int Flag = 0;

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);

        String LOT_ID; //批次号
        String TYPE_NAME; //奖金类型
        pageContext.putSessionValue("RequestUrl", pageContext.getRequestUrl());

        if (Flag == 0) {
            String RequestUrl = pageContext.getRequestUrl();
            OAApplicationModule am = pageContext.getApplicationModule(webBean);
            //获取批次号和奖金类型
            if (pageContext.getSessionValue("LOT_ID") == null && 
                pageContext.getSessionValue("TYPE_NAME") == null) {
                LOT_ID = get_RequestUrl_Parameter(RequestUrl, "LOT_ID");
                TYPE_NAME = get_RequestUrl_Parameter(RequestUrl, "TYPE_NAME");
                Serializable[] parameters = { "277", "预支奖" };
                /* 141 */am.invokeMethod("execQuery", parameters);
            }
        }
        Flag = 0;
    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        String event = pageContext.getParameter("event");
        if ("oaAddAttachment".equals(event)) {
            Flag = 1;
        }
    }

    public String get_RequestUrl_Parameter(String RequestUrl, String s) {
        /* 451 */int i_start = RequestUrl.indexOf(s);
        /*     */String K_ID;
        /* 452 */if (i_start > -1) {
            /* 453 */i_start = i_start + s.length() + 1;
            /* 454 */int i_end = RequestUrl.indexOf("&", i_start);
            /* 455 */if (i_end == -1) {
                /* 456 */i_end = RequestUrl.length();
            }
            /* 459 */K_ID = RequestUrl.substring(i_start, i_end);
        } else {
            /* 461 */K_ID = "-1";
        }
        /* 465 */return K_ID;
    }

}
