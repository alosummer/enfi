// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MemberGrantCO.java

package cux.oracle.apps.per.bonus.member.webui;

import cux.oracle.apps.per.bonus.member.server.MemberGrantAMImpl;
import cux.oracle.apps.per.bonus.member.server.MemberGrantVOImpl;

import com.sun.java.util.collections.HashMap;

import java.io.PrintStream;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.common.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

public class MemberGrantCO extends OAControllerImpl {

    public String SBudget;
    public String SDistributed;
    public String SDistributeble;
    public String Sproject_id;

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        Sproject_id = pageContext.getParameter("PROJECTID");
        am.invokeMethod("createVOInfo");
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        String project_id = 
            GetProjectID(am, pageContext.getParameter("ProjectNumber"));
        System.out.println((new StringBuilder()).append("project_id=").append(project_id).toString());
        if (pageContext.getParameter("Go") != null) {
            String whereStr1 = "";
            String distDate = pageContext.getParameter("DistributeDate1");
            if (!"".equals(distDate))
                whereStr1 = 
                        (new StringBuilder()).append("to_char(distribution_date,'YYYY-MM-DD')='").append(distDate).append("'").toString();
            String Sstatus = pageContext.getParameter("LineStatus");
            if (!"".equals(Sstatus))
                if (!"".equals(whereStr1))
                    whereStr1 = 
                            (new StringBuilder()).append(whereStr1).append("and distribution_status='").append(Sstatus).append("'").toString();
                else
                    whereStr1 = 
                            (new StringBuilder()).append("distribution_status='").append(Sstatus).append("'").toString();
            if (project_id != null)
                if (!"".equals(whereStr1))
                    whereStr1 = 
                            (new StringBuilder()).append(whereStr1).append("and project_id='").append(project_id).append("'").toString();
                else
                    whereStr1 = 
                            (new StringBuilder()).append("project_id='").append(project_id).append("'").toString();
            System.out.println((new StringBuilder()).append("whereStr1").append(whereStr1).toString());
            MemberGrantAMImpl amImpl = 
                (MemberGrantAMImpl)pageContext.getApplicationModule(webBean);
            MemberGrantVOImpl vo = amImpl.getMemberGrantVO1();
            vo.setWhereClause(whereStr1);
            vo.executeQuery();
        }
        if (pageContext.getParameter("Check") != null) {
            String creat_project_id = 
                GetProjectID(am, pageContext.getParameter("ProjectNumberCreat"));
            System.out.println((new StringBuilder()).append("creat_project_id=").append(creat_project_id).toString());
            if (creat_project_id != null) {
                HashMap params = new HashMap(2);
                params.put("PROJECTID", creat_project_id);
                params.put("SUCCESS", "N");
                pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/member/webui/MemberDistrPG", 
                                               null, (byte)0, null, params, 
                                               true, "Y");
            } else {
                OAException confirmMessage = 
                    new OAException("��ѡ�񴴽�����Ŀ��ţ�", (byte)0);
                pageContext.putDialogMessage(confirmMessage);
            }
        }
    }

    public String get_budget_result(OAApplicationModule am, 
                                    String p_project_id) {
        String result = "";
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_budget(?,?)", 
                                                1);
        try {
            cs.setString(1, p_project_id);
            cs.registerOutParameter(2, 12);
            cs.executeQuery();
            result = cs.getString(2);
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

    public String get_project_distributed(OAApplicationModule am, 
                                          String p_project_id, 
                                          String p_type_id) {
        String result = "";
        OADBTransaction transaction = am.getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_distributed(?,?,?)", 
                                                1);
        try {
            cs.setString(1, p_project_id);
            cs.setString(2, p_type_id);
            cs.registerOutParameter(3, 12);
            cs.executeQuery();
            result = cs.getString(3);
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

    public String GetProjectID(OAApplicationModule am, String num) {
        try {
            OADBTransaction transaction = am.getOADBTransaction();
            CallableStatement cs = 
                transaction.createCallableStatement((new StringBuilder()).append("select t.project_id from pa_projects_all t where t.segment1='").append(num).append("'").toString(), 
                                                    1);
            ResultSet rs = 
                cs.executeQuery((new StringBuilder()).append("select t.project_id from pa_projects_all t where t.segment1='").append(num).append("'").toString());
            if (rs.next()) {
                String s = Integer.toString(rs.getInt(1));
                return s;
            } else {
                String s1 = null;
                return s1;
            }
        } catch (Exception e) {
            String s2 = null;
            return s2;
        }
    }

    public MemberGrantCO() {
    }
}
