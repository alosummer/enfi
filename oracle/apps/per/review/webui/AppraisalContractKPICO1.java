package cux.oracle.apps.per.review.webui;

import cux.oracle.apps.per.review.server.AppraisalAMImpl;
import cux.oracle.apps.per.review.server.AppraisalKPIVOImpl;

import java.sql.CallableStatement;
import java.sql.Types;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.Row;

public class AppraisalContractKPICO1 extends AppraisalContractKPICO {
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        if (pageContext.getParameter("Apply") != null) {
            String phaseId = (String)pageContext.getSessionValue("phaseId");
            String role = (String)pageContext.getSessionValue("role");
            String appraisalId = 
                (String)pageContext.getSessionValue("appraisalId");
            System.out.println("---huangbo--phaseId=" + phaseId + "--role=" + 
                               role + "--appraisalId=" + appraisalId);

            if (phaseId.equals("REVIEW") && role.equals("manager") && 
                "Y".equals(isFirstApprover(pageContext, webBean, 
                                           appraisalId))) {
                updateContractAttribute1(pageContext, webBean);
            }

            super.processFormRequest(pageContext, webBean);
        } else {
            System.out.println("@2222222222222222222222@");
            super.processFormRequest(pageContext, webBean);
        }
    }
    //updated by huangbo 2013-05-28

    private String isFirstApprover(OAPageContext pageContext, 
                                   OAWebBean webBean, String appraisalId) {
        String checkResult = "";
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        CallableStatement cs = null;
        OADBTransaction transaction = am.getOADBTransaction();
        cs = 
 transaction.createCallableStatement("CALL CUX_JX_HB.IS_FIRST_APPROVER(?, ?)", 
                                     1);
        try {
            cs.setObject(1, appraisalId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            checkResult = cs.getString(2);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("---huangbo Exception111---");
            checkResult = "N";
        }
        return checkResult;
    }
    //updated by huangbo 2013-05-28 -end-
    //updated by huangbo 2013-05-28

    private void updateContractAttribute1(OAPageContext pageContext, 
                                          OAWebBean webBean) {
        AppraisalAMImpl amImpl = 
            (AppraisalAMImpl)pageContext.getApplicationModule(webBean);
        AppraisalKPIVOImpl vo = amImpl.getAppraisalKPIVO1();
        int rowCount = vo.getRowCount();
        for (int i = 0; i < rowCount; ++i) {
            Row pRow = vo.getRowAtRangeIndex(i);
            if (pRow.getAttribute("ContractId") != null) {
                String contractId = pRow.getAttribute("ContractId").toString();
                System.out.println("---huangbo--contractId=" + contractId);
                CallableStatement cs = null;
                OADBTransaction transaction = amImpl.getOADBTransaction();
                cs = 
 transaction.createCallableStatement("CALL CUX_JX_HB.UPDATE_CONTRACT_ATTRIBUTE1(?)", 
                                     1);
                try {
                    cs.setString(1, contractId);
                    cs.execute();
                    cs.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("---huangbo apply end---");
    }
    //updated by huangbo 2013-05-28 -end-
}
