// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MemberCheckCO.java

package cux.oracle.apps.per.bonus.member.webui;

import com.sun.java.util.collections.HashMap;

import java.io.PrintStream;
import java.io.Serializable;

import java.sql.CallableStatement;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.common.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.Row;

public class MemberCheckCO extends OAControllerImpl {

    public String SBudget;
    public String SDistributed;
    public String SDistributeble;
    public String Sproject_id;

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        Sproject_id = pageContext.getParameter("PROJECTID");
        Serializable paras[] = { Sproject_id };
        System.out.println((new StringBuilder()).append("Sproject_id").append(Sproject_id).toString());
        Serializable retPara = am.invokeMethod("createProjectInfo", paras);
        String retPara1 = (String)am.invokeMethod("createApproved", paras);
        String retPara2 = (String)am.invokeMethod("createSubmit", paras);
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        String Sproject_id = pageContext.getParameter("PROJECTID");
        if (pageContext.getParameter("Submit") != null) {
            System.out.println("111");
            OAApplicationModule am = 
                (OAApplicationModule)pageContext.getApplicationModule(webBean);
            OAViewObject processvo = 
                (OAViewObject)am.findViewObject("ProcesingVO1");
            int kk = 0;
            String whereStrk = 
                (new StringBuilder()).append("project_id = ").append(Sproject_id).toString();
            processvo.setWhereClause(whereStrk);
            processvo.executeQuery();
            kk = processvo.getRowCount();
            System.out.println((new StringBuilder()).append("kkkkkk").append(kk).toString());
            if (kk != 0) {
                OAException confirmMessage = 
                    new OAException("����Ŀ��������,��ȴ�����������ύ��", 
                                    (byte)0);
                pageContext.putDialogMessage(confirmMessage);
                return;
            }
            OAViewObject Approvedvo = 
                (OAViewObject)am.findViewObject("NewVO1");
            int tt = 0;
            tt = Approvedvo.getRowCount();
            System.out.println((new StringBuilder()).append("tttttttttt").append(tt).toString());
            if (tt == 0) {
                OAException confirmMessage = 
                    new OAException("�ύʧ�ܡ�ԭ������ݣ�", (byte)0);
                pageContext.putDialogMessage(confirmMessage);
                return;
            }
            System.out.println("222");
            OAViewObject vo = 
                (OAViewObject)am.findViewObject("MemberCheckBudgetVO1");
            String whereStr = 
                (new StringBuilder()).append("project_id = ").append(Sproject_id).toString();
            vo.setWhereClause(whereStr);
            vo.executeQuery();
            int ii = 0;
            if (ii != 0) {
                OAException confirmMessage = 
                    new OAException("�ύʧ�ܣ�����Ŀ���Ž�Ԥ��,���飡", (byte)0);
                pageContext.putDialogMessage(confirmMessage);
                return;
            }
            String plot_id = GetLotID(am);
            System.out.println((new StringBuilder()).append("plot_idplot_idplot_idplot_idplot_id").append(plot_id).toString());
            OAViewObject vo1 = (OAViewObject)am.findViewObject("NewVO1");
            int jj = 0;
            jj = vo1.getRowCount();
            if (jj != 0) {
                String Smessage = "";
                for (int nn = 0; nn < jj; nn++) {
                    Row row = vo1.getRowAtRangeIndex(nn);
                    String p_distr_id = 
                        row.getAttribute("PersonDistributionId").toString();
                    System.out.println((new StringBuilder()).append("p_distr_id").append(p_distr_id).toString());
                    CallableStatement cs = null;
                    OADBTransaction transaction = am.getOADBTransaction();
                    cs = 
 transaction.createCallableStatement("call cux_hr_bonus.update_person_distribution_lot(?,?, ?)", 
                                     1);
                    try {
                        cs.registerOutParameter(1, 12);
                        cs.setInt(2, Integer.parseInt(p_distr_id));
                        System.out.println((new StringBuilder()).append("p_distr_id").append(p_distr_id).toString());
                        cs.setInt(3, Integer.parseInt(plot_id));
                        System.out.println((new StringBuilder()).append("plot_id").append(plot_id).toString());
                        cs.executeUpdate();
                        String retcode = cs.getString(1);
                        if ("success".equals(retcode))
                            transaction.commit();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        OAException confirmok = 
                            new OAException("�ύʧ�ܣ�����ϵϵͳ����Ա��", (byte)3);
                        pageContext.putDialogMessage(confirmok);
                    }
                }

            }
            init_approver(am, Sproject_id, plot_id);
            start_wf_prc(am, Sproject_id, plot_id);
            HashMap params = new HashMap(1);
            params.put("SUCCESS", "Y");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/member/webui/MemberDistrPG", 
                                           null, (byte)0, null, params, true, 
                                           "Y");
        }
        if (pageContext.getParameter("Return") != null) {
            HashMap params = new HashMap(2);
            params.put("PROJECTID", pageContext.getParameter("PROJECTID"));
            params.put("SUCCESS", "N");
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/member/webui/MemberDistrPG", 
                                           null, (byte)0, null, params, true, 
                                           "Y");
        }
    }

    public void init_approver(OAApplicationModule am, String p_project_id, 
                              String p_lot_id) {
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call CUX_ASSGN_BONUS_WF_PKG.init_approver(?,?,?,?,?)", 
                                                1);
        try {
            cs.registerOutParameter(1, 12);
            cs.registerOutParameter(2, 12);
            cs.setString(3, "APPROVAL_BONUS_MEMBER");
            cs.setInt(4, Integer.parseInt(p_project_id));
            System.out.println((new StringBuilder()).append("p_project_id").append(p_project_id).toString());
            cs.setInt(5, Integer.parseInt(p_lot_id));
            System.out.println((new StringBuilder()).append("p_lot_id").append(p_lot_id).toString());
            cs.executeQuery();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("init_approver1");
        }
        if (cs != null)
            try {
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("init_approver2");
            }
    }

    public void start_wf_prc(OAApplicationModule am, String p_project_id, 
                             String p_lot_id) {
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call CUX_ASSGN_BONUS_WF_PKG.start_wf_prc(?,?,?,?,?,?,?)", 
                                                1);
        try {
            cs.setString(1, "BONUS_AS");
            cs.setString(2, "");
            cs.setString(3, "APPROVAL_BONUS_MEMBER");
            cs.setInt(4, Integer.parseInt(p_project_id));
            cs.setInt(5, Integer.parseInt(p_lot_id));
            cs.registerOutParameter(6, 2);
            cs.registerOutParameter(7, 12);
            cs.executeQuery();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("init_start_wf_prc1");
        }
        if (cs != null)
            try {
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("init_start_wf_prc2");
            }
    }

    public String Geytatal(OAViewObject vo, String columnName) {
        double amt = 0.0D;
        if (vo != null) {
            int ii = 0;
            ii = vo.getRowCount();
            System.out.println((new StringBuilder()).append("ii=").append(String.valueOf(ii)).toString());
            for (int nn = 0; nn < ii; nn++) {
                Row row = vo.getRowAtRangeIndex(nn);
                String a1 = "0";
                try {
                    a1 = row.getAttribute(columnName).toString();
                    System.out.println(a1.toString());
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                amt += Double.parseDouble(a1);
            }

        }
        return Double.toString(amt);
    }

    public String GetLotID(OAApplicationModule am) {
        String result = "";
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_next_lot_id(?)", 
                                                1);
        try {
            cs.registerOutParameter(1, 4);
            cs.executeQuery();
            result = cs.getString(1);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (cs != null)
            try {
                cs.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        return result;
    }

    public MemberCheckCO() {
    }
}
