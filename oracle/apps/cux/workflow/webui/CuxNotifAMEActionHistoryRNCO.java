package cux.oracle.apps.cux.workflow.webui;

import java.io.Serializable;

import oracle.apps.ap.oie.workflow.apexp.utility.NotifConstants;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jrad.tools.xml.importer.XMLImporter;

public class CuxNotifAMEActionHistoryRNCO extends OAControllerImpl implements NotifConstants {
    public static final String RCS_ID = 
        "$Header: NotifAMEActionHistoryCO.java 120.0 2006/03/11 01:05:00 skoukunt noship $";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header: NotifAMEActionHistoryCO.java 120.0 2006/03/11 01:05:00 skoukunt noship $", 
                                       "%packagename%");

    public void processRequest(OAPageContext paramOAPageContext, 
                               OAWebBean paramOAWebBean) {
        super.processRequest(paramOAPageContext, paramOAWebBean);
        OAApplicationModule localOAApplicationModule = 
            paramOAPageContext.getApplicationModule(paramOAWebBean);
        String ntfId = paramOAPageContext.getParameter("NtfId");
        Serializable[] arrayOfSerializable = new Serializable[] { ntfId };
        String[] ret = 
            (String[])localOAApplicationModule.invokeMethod("getItemKey", 
                                                            arrayOfSerializable);
        String ameTransType = 
            (String)localOAApplicationModule.invokeMethod("getAmeTransationType", 
                                                          ret);
        paramOAPageContext.putParameter("AMETxnType", ameTransType);
        paramOAPageContext.writeDiagnostics(this, 
                                            "AMETransactionType = " + ameTransType, 
                                            1);
        String[] ret1 = 
            (String[])localOAApplicationModule.invokeMethod("getApplicationId", 
                                                            arrayOfSerializable);

        paramOAPageContext.putParameter("AMEFndAppID", ret1[0]);
        paramOAPageContext.writeDiagnostics(this, 
                                            "FndApplicationId = " + ret[0], 1);
        String transactionid = ret[1].substring(0, ret[1].indexOf("_"));
        paramOAPageContext.putParameter("AMETxnID", transactionid);
        paramOAPageContext.writeDiagnostics(this, 
                                            "TransactionId = " + transactionid, 
                                            1);

    }


    public void processFormRequest(OAPageContext paramOAPageContext, 
                                   OAWebBean paramOAWebBean) {
        super.processFormRequest(paramOAPageContext, paramOAWebBean);
    }

    public static void main(String[] args) {
        XMLImporter aa = new XMLImporter();

        aa.main(new String[] { "E:\\jdevhome\\jdev\\myprojects\\oracle\\apps\\xxt\\mds\\regionMap.xml", 
                               "-username", "apps", "-password", "apps", 
                               "-dbconnection", 
                               "(DESCRIPTION =(ADDRESS_LIST=(ADDRESS = (PROTOCOL = TCP)(HOST = 80.44.40.197)(PORT =1521)))(CONNECT_DATA=(SERVICE_NAME = DEV)))", 
                               "-userId", "1", "-rootPackage", 
                               "/oracle/apps/xxt", "-rootdir", 
                               "/oracle/apps/xxt/mds" });
    }
}
