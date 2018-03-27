/*     */package cux.oracle.apps.per.bonus.manager.webui;
/*     */
/*     */import cux.oracle.apps.per.bonus.manager.server.ManagerDistrVOImpl;
/*     */
import cux.oracle.apps.per.bonus.manager.server.ManagerGrantAMImpl;
/*     */
import com.sun.java.util.collections.HashMap;
/*     */
import java.io.PrintStream;
/*     */
import java.sql.CallableStatement;
/*     */
import java.text.SimpleDateFormat;
/*     */
import java.util.Date;
/*     */
import java.util.Hashtable;
/*     */
import oracle.apps.fnd.common.MessageToken;
/*     */
import oracle.apps.fnd.framework.OAException;
/*     */
import oracle.apps.fnd.framework.OAViewObject;
/*     */
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
/*     */
import oracle.apps.fnd.framework.server.OADBTransaction;
/*     */
import oracle.apps.fnd.framework.server.common.OAApplicationModule;
/*     */
import oracle.apps.fnd.framework.webui.OAControllerImpl;
/*     */
import oracle.apps.fnd.framework.webui.OADialogPage;
/*     */
import oracle.apps.fnd.framework.webui.OAPageContext;
/*     */
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
/*     */
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
/*     */
import oracle.jbo.Row;
/*     */
/*     */public class ManagerDistrCO extends OAControllerImpl {
    /*     */
    public String SBudget;
    /*     */
    public String SDistributed;
    /*     */
    public String SDistributeble;
    /*     */
    public String Sproject_id;
    /*     */
    /*     */

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        /*  52 */super.processRequest(pageContext, webBean);
        /*  53 */OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        /*  54 */this.Sproject_id = pageContext.getParameter("PROJECTID");
        /*     */
        /*  56 */ManagerGrantAMImpl amImpl = 
            (ManagerGrantAMImpl)pageContext.getApplicationModule(webBean);
        /*  57 */ManagerDistrVOImpl vo = amImpl.getManagerDistrVO1();
        /*  58 */vo.executeQuery();
        /*     */
        /*  60 */String Ssuccess = pageContext.getParameter("SUCCESS");
        /*  61 */if (Ssuccess == "Y")
        /*     */ {
            /*  63 */OAException confirmok = new OAException("提交成功！", (byte)3);
            /*  64 */pageContext.putDialogMessage(confirmok);
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /*  71 */super.processFormRequest(pageContext, webBean);
        /*  72 */OAApplicationModule am = 
            (OAApplicationModule)pageContext.getApplicationModule(webBean);
        /*     */
        /*  74 */if (pageContext.getParameter("Check") != null)
        /*     */ {
            /*  76 */save(pageContext, webBean, am);
            /*  77 */HashMap params = new HashMap(1);
            /*     */
            /*  80 */pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/manager/webui/ManagerCheckPG", 
                                                    null, (byte)0, null, 
                                                    params, true, "Y");
            /*     */
        }
        /*     */
        /*  92 */String event = pageContext.getParameter("event");
        /*     */
        /*  95 */OAAdvancedTableBean personDistTableBean = null;
        /*  96 */personDistTableBean = 
                (OAAdvancedTableBean)webBean.findIndexedChildRecursive("PersonDistTable");
        /*  97 */if ("addRows".equals(event))
        /*     */ {
            /*  99 */createAnotherPersonRow(pageContext, webBean, "APPEND", 
                                            null);
            /*     */
        }
        /*     */
        /* 105 */if ("personDelete".equals(event)) {
            /* 106 */String distId = pageContext.getParameter("personDistId");
            /* 107 */System.out.println("personDistId" + distId);
            /* 108 */String deletePerson = 
                pageContext.getParameter("deletePersonName");
            /* 109 */String message = "提示信息:是否确定删除 " + deletePerson + " 该条记录";
            /* 110 */MessageToken[] tokens = 
            { new MessageToken("MESSAGE", message) };
            /* 111 */OAException mainMessage = 
                new OAException("PA", "CUX_COMMON_MESSAGE", tokens);
            /* 112 */OADialogPage dialogPage = 
                new OADialogPage((byte)1, mainMessage, null, "", "");
            /*     */
            /* 117 */String yes = 
                pageContext.getMessage("AK", "FWK_TBX_T_YES", null);
            /* 118 */String no = 
                pageContext.getMessage("AK", "FWK_TBX_T_NO", null);
            /* 119 */String currentUrl = pageContext.getCurrentUrl();
            /*     */
            /* 121 */dialogPage.setOkButtonItemName("PersonDeleteYesButton");
            /*     */
            /* 123 */dialogPage.setOkButtonToPost(true);
            /* 124 */dialogPage.setNoButtonToPost(true);
            /* 125 */dialogPage.setPostToCallingPage(true);
            /* 126 */dialogPage.setOkButtonLabel(yes);
            /* 127 */dialogPage.setNoButtonLabel(no);
            /* 128 */dialogPage.setPageTitle("删除");
            /* 129 */dialogPage.setFormDestination(currentUrl + "&retainAM=Y");
            /*     */
            /* 131 */Hashtable formParams = new Hashtable(4);
            /* 132 */formParams.put("distPersonId", distId);
            /* 133 */dialogPage.setFormParameters(formParams);
            /* 134 */pageContext.redirectToDialogPage(dialogPage);
            /*     */
        }
        /* 136 */if (pageContext.getParameter("PersonDeleteYesButton") != 
                     null) {
            /* 137 */String distId = pageContext.getParameter("distPersonId");
            /*     */
            /* 139 */ManagerGrantAMImpl amImpl = 
                (ManagerGrantAMImpl)pageContext.getApplicationModule(webBean);
            /* 140 */amImpl.DeletePersonLine(distId);
            /*     */
            /* 142 */OAException confirmMessage = 
                new OAException("操作成功！", (byte)3);
            /* 143 */pageContext.putDialogMessage(confirmMessage);
            /*     */
        }
        /*     */
        /* 148 */if (pageContext.getParameter("SaveButton") != null)
        /*     */ {
            /* 150 */save(pageContext, webBean, am);
            /*     */
        }
        /*     */
        /* 156 */if (pageContext.getParameter("Return") != null)
        /*     */ {
            /* 159 */HashMap params = new HashMap(1);
            /*     */
            /* 162 */pageContext.forwardImmediately("OA.jsp?page=/cux/oracle/apps/per/bonus/manager/webui/ManagerGrantPG", 
                                                    null, (byte)0, null, 
                                                    params, true, "Y");
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public void createAnotherPersonRow(OAPageContext pageContext, 
                                       OAWebBean webBean, String mode, 
                                       Row row) {
        /* 174 */OAApplicationModuleImpl am = 
            (OAApplicationModuleImpl)pageContext.getApplicationModule(webBean);
        /* 175 */OAViewObject vo = 
            (OAViewObject)am.findViewObject("ManagerDistrVO1");
        /* 176 */if
            /* 177 */((vo != null) && ("APPEND".equals(mode))) {
            /* 178 */Row row1 = vo.createRow();
            /* 179 */int index = vo.getRangeIndexOf(vo.last());
            /* 180 */vo.insertRowAtRangeIndex(index + 1, row1);
            /* 181 */row1.setAttribute("Attribute1", "N");
            /* 182 */SimpleDateFormat tempDate = 
                new SimpleDateFormat("yyyy-MM-dd");
            /* 183 */String time = tempDate.format(new Date());
            /* 184 */System.out.println("time" + time);
            /* 185 */row1.setAttribute("DistributionDate", time);
            /* 186 */row = row1;
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public void save(OAPageContext pageContext, OAWebBean webBean, 
                     OAApplicationModule am) {
        /* 195 */String distDate = pageContext.getParameter("DistributeDate");
        /* 196 */String bonusTypeName = "项目管理奖";
        /* 197 */ManagerGrantAMImpl amImpl = 
            (ManagerGrantAMImpl)pageContext.getApplicationModule(webBean);
        /* 198 */System.out.println("distDate" + distDate);
        /*     */
        /* 200 */String result = amImpl.SavePersonDist(am, bonusTypeName);
        /*     */
        /* 202 */if (result.equals("0"))
        /*     */ {
            /* 205 */ManagerDistrVOImpl vo1 = amImpl.getManagerDistrVO1();
            /* 206 */vo1.executeQuery();
            /*     */
            /* 208 */if (pageContext.getParameter("SaveButton") != null)
            /*     */ {
                /* 210 */OAException confirmMessage = 
                    new OAException("操作成功！", (byte)3);
                /* 211 */pageContext.putDialogMessage(confirmMessage);
                /*     */
            }
            /*     */
        }
        /* 214 */ else if (result.equals("1")) {
            /* 215 */OAException errMessage = 
                new OAException("某条人员信息有错误，请检查后再保存！", (byte)0);
            /* 216 */pageContext.putDialogMessage(errMessage);
            /*     */
        }
        /* 219 */ else if (result.equals("2")) {
            /* 220 */OAException errMessage = 
                new OAException("奖金发放金额必须大于0，请检查后再保存", (byte)0);
            /* 221 */pageContext.putDialogMessage(errMessage);
            /*     */
        }
        /* 224 */ else if (result.equals("3")) {
            /* 225 */OAException errMessage = 
                new OAException("操作出现问题，请联系系统管理员！", (byte)0);
            /* 226 */pageContext.putDialogMessage(errMessage);
            /*     */
        }
        /* 229 */ else if (result.equals("4")) {
            /* 230 */OAException errMessage = 
                new OAException("请输入发放日期！", (byte)0);
            /* 231 */pageContext.putDialogMessage(errMessage);
            /*     */
        }
        /*     */ else
        /*     */ {
            /* 237 */OAException errMessage = new OAException(result, (byte)0);
            /* 238 */pageContext.putDialogMessage(errMessage);
            /*     */
            /* 242 */return;
            /*     */
        }
        /*     */
    }
    /*     */
    /* 246 */

    public String get_budget_result(OAApplicationModule am, 
                                    String p_project_id) {
        String result = "";
        /* 247 */OADBTransaction transaction = am.getOADBTransaction();
        /* 248 */CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.get_project_budget(?,?)", 
                                                1);
        /*     */try
        /*     */ {
            /* 252 */cs.setString(1, p_project_id);
            /* 253 */cs.registerOutParameter(2, 12);
            /* 254 */cs.executeQuery();
            /* 255 */result = cs.getString(2);
            /* 256 */System.out.println(result);
            /*     */
        }
        /*     */ catch (Exception e) {
            /* 259 */System.out.println(e.toString());
            /*     */
        }
        /* 261 */if (cs != null) {
            /*     */try {
                /* 263 */cs.close();
                /*     */
            } catch (Exception e) {
                /* 265 */System.out.println(e.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /* 269 */return result;
        /*     */
    }
    /*     */
}

/* Location:           E:\OAF-test\myprojects\cux.oracle\apps\per\bonus_原始\manager\webui\
 * Qualified Name:     cux.oracle.apps.per.bonus.manager.webui.ManagerDistrCO
 * JD-Core Version:    0.6.1
 */