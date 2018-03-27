// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MemberDistrCO.java

package cux.oracle.apps.per.bonus.member.webui;

import cux.oracle.apps.per.bonus.member.server.*;

import com.sun.java.util.collections.HashMap;

import java.io.PrintStream;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Hashtable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.common.OAApplicationModule;
import oracle.apps.fnd.framework.webui.*;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

import oracle.jbo.Row;

public class MemberDistrCO extends OAControllerImpl {

    public String SBudget;
    public String SDistributed;
    public String SDistributeble;
    public String Sproject_id;

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        Sproject_id = pageContext.getParameter("PROJECTID");
        MemberGrantAMImpl amImpl = 
            (MemberGrantAMImpl)pageContext.getApplicationModule(webBean);
        DistrProjectVOImpl vo = amImpl.getDistrProjectVO1();
        String whereStr = 
            (new StringBuilder()).append("project_id = ").append(Sproject_id).toString();
        vo.setWhereClause(whereStr);
        vo.executeQuery();
        MemberDistrVOImpl vo1 = amImpl.getMemberDistrVO1();
        String whereStr1 = 
            (new StringBuilder()).append("project_id = ").append(Sproject_id).toString();
        vo1.setWhereClause(whereStr1);
        vo1.executeQuery();
        String Ssuccess = pageContext.getParameter("SUCCESS");
        if (Ssuccess == "Y") {
            OAException confirmok = new OAException("�ύ�ɹ���", (byte)3);
            pageContext.putDialogMessage(confirmok);
        }
    }

    public void RefleshDistrVO(String s) {
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        String project_id = pageContext.getParameter("PROJECTID");
        System.out.println((new StringBuilder()).append("project_id++++project_id").append(project_id).toString());
        if (pageContext.getParameter("Check") != null && 
            save(pageContext, webBean, am, project_id) == 0) {
            HashMap params = new HashMap(1);
            params.put("PROJECTID", pageContext.getParameter("PROJECTID"));
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/member/webui/MemberCheckPG", 
                                           null, (byte)0, null, params, true, 
                                           "Y");
        }
        if (pageContext.getParameter("Return") != null) {
            HashMap params = new HashMap(1);
            params.put("PROJECTID", pageContext.getParameter("PROJECTID"));
            pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/member/webui/MemberGrantPG", 
                                           null, (byte)0, null, params, true, 
                                           "Y");
        }
        String event = pageContext.getParameter("event");
        OAAdvancedTableBean personDistTableBean = null;
        personDistTableBean = 
                (OAAdvancedTableBean)webBean.findIndexedChildRecursive("PersonDistTable");
        if ("addRows".equals(event))
            createAnotherPersonRow(pageContext, webBean, "APPEND", null);
        if ("personDelete".equals(event)) {
            String distId = pageContext.getParameter("personDistId");
            System.out.println((new StringBuilder()).append("distId1=").append(distId).toString());
            String deletePerson = pageContext.getParameter("deletePersonName");
            String message = 
                (new StringBuilder()).append("��ʾ��Ϣ:�Ƿ�ȷ��ɾ�� ").append(deletePerson).append(" ������¼").toString();
            MessageToken tokens[] = { new MessageToken("MESSAGE", message) };
            OAException mainMessage = 
                new OAException("PA", "CUX_COMMON_MESSAGE", tokens);
            OADialogPage dialogPage = 
                new OADialogPage((byte)1, mainMessage, null, "", "");
            String yes = pageContext.getMessage("AK", "FWK_TBX_T_YES", null);
            String no = pageContext.getMessage("AK", "FWK_TBX_T_NO", null);
            String currentUrl = pageContext.getCurrentUrl();
            dialogPage.setOkButtonItemName("PersonDeleteYesButton");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            dialogPage.setOkButtonLabel(yes);
            dialogPage.setNoButtonLabel(no);
            dialogPage.setPageTitle("ɾ��");
            dialogPage.setFormDestination((new StringBuilder()).append(currentUrl).append("&retainAM=Y").toString());
            Hashtable formParams = new Hashtable(4);
            formParams.put("distPersonId", distId);
            dialogPage.setFormParameters(formParams);
            pageContext.redirectToDialogPage(dialogPage);
        }
        if (pageContext.getParameter("PersonDeleteYesButton") != null) {
            String distId = pageContext.getParameter("distPersonId");
            System.out.println((new StringBuilder()).append("distId2=").append(distId).toString());
            MemberGrantAMImpl amImpl = 
                (MemberGrantAMImpl)pageContext.getApplicationModule(webBean);
            amImpl.DeletePersonLine(distId);
            OAException confirmMessage = new OAException("�����ɹ���", (byte)3);
            pageContext.putDialogMessage(confirmMessage);
        }
        if (pageContext.getParameter("SaveButton") != null)
            save(pageContext, webBean, am, project_id);
    }

    public void createAnotherPersonRow(OAPageContext pageContext, 
                                       OAWebBean webBean, String mode, 
                                       Row row) {
        OAApplicationModuleImpl am = 
            (OAApplicationModuleImpl)pageContext.getApplicationModule(webBean);
        OAViewObject vo = (OAViewObject)am.findViewObject("MemberDistrVO1");
        if (vo != null && "APPEND".equals(mode)) {
            Row row1 = vo.createRow();
            int index = vo.getRangeIndexOf(vo.last());
            vo.insertRowAtRangeIndex(index + 1, row1);
            row1.setAttribute("Attribute1", "N");
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
            String time = tempDate.format(new Date());
            System.out.println((new StringBuilder()).append("time").append(time).toString());
            row1.setAttribute("DistributionDate", time);
            row = row1;
        }
    }

    public int save(OAPageContext pageContext, OAWebBean webBean, 
                    OAApplicationModule am, String project_id) {
        int check = 0;
        String bonusTypeName = "��Ŀ���?";
        MemberGrantAMImpl amImpl = 
            (MemberGrantAMImpl)pageContext.getApplicationModule(webBean);
        System.out.println((new StringBuilder()).append("project_idproject_idproject_id").append(project_id).toString());
        String result = amImpl.SavePersonDist(am, bonusTypeName, project_id);
        if (result.equals("0")) {
            MemberDistrVOImpl vo1 = amImpl.getMemberDistrVO1();
            String whereStr1 = 
                (new StringBuilder()).append("project_id = ").append(Sproject_id).toString();
            vo1.setWhereClause(whereStr1);
            vo1.executeQuery();
            int kk = 0;
            kk = vo1.getRowCount();
            System.out.println((new StringBuilder()).append("kkkkkk").append(kk).toString());
            if (kk == 0) {
                check = -1;
                return check;
            }
            if (pageContext.getParameter("SaveButton") != null) {
                OAException confirmMessage = 
                    new OAException("�����ɹ���", (byte)3);
                pageContext.putDialogMessage(confirmMessage);
            }
            return check;
        }
        if (result.equals("1")) {
            OAException errMessage = 
                new OAException("ĳ����Ա��Ϣ�д���������ٱ��棡", (byte)0);
            pageContext.putDialogMessage(errMessage);
            check = -1;
            return check;
        }
        if (result.equals("2")) {
            OAException errMessage = 
                new OAException("���𷢷Ž��������0��������ٱ��棡", (byte)0);
            pageContext.putDialogMessage(errMessage);
            check = -1;
            return check;
        }
        if (result.equals("3")) {
            OAException errMessage = 
                new OAException("�����������⣬����ϵϵͳ����Ա��", (byte)0);
            pageContext.putDialogMessage(errMessage);
            check = -1;
            return check;
        }
        if (result.equals("4")) {
            OAException errMessage = new OAException("�����뷢�����ڣ�", (byte)0);
            pageContext.putDialogMessage(errMessage);
            check = -1;
            return check;
        } else {
            OAException errMessage = new OAException(result, (byte)0);
            pageContext.putDialogMessage(errMessage);
            check = -1;
            return check;
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

    public MemberDistrCO() {
    }
}
