package cux.oracle.apps.per.bonus.specialawards.webui;

import cux.oracle.apps.per.bonus.specialawards.server.SpecialAwardsAMImpl;
import cux.oracle.apps.per.bonus.specialawards.server.SpecialAwardsSummaryVOImpl;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Hashtable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADataBoundValueViewObject;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAMessageComponentLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;

import oracle.jbo.Row;

public class SpecialAwardsCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);

        String bonusType = "";
        String distDate = "";
        int queryFlag = 0;

        if (pageContext.getSessionValue("saQueryFlag") != null && 
            !"".equals(pageContext.getSessionValue("saQueryFlag").toString()))
            queryFlag = 
                    Integer.parseInt(pageContext.getSessionValue("saQueryFlag").toString());
        else
            pageContext.putSessionValue("saQueryFlag", 
                                        String.valueOf(queryFlag));

        //**设置页面缺省“期间”和“奖金类型”两个字段**
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        String strDate = "";
        if (pageContext.getSessionValue("saDistDate") != null && 
            !"".equals(pageContext.getSessionValue("saDistDate"))) {
            strDate = pageContext.getSessionValue("saDistDate").toString();
        }
        if (strDate == null || "".equals(strDate)) {
            strDate = df.format(today);
            pageContext.putSessionValue("saDistDate", strDate);
        }
        OAMessageChoiceBean yearBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
        if (yearBean.getValue(pageContext) == null || 
            "".equals(yearBean.getValue(pageContext).toString())) {
            yearBean.setDefaultValue(strDate);
        }

        //20130312刘禹孜修改
        String strBonusType = "";
        if (pageContext.getSessionValue("saBonusType") != null && 
            !"".equals(pageContext.getSessionValue("saBonusType"))) {
            strBonusType = 
                    pageContext.getSessionValue("saBonusType").toString();
        }
        if (strBonusType == null || "".equals(strBonusType)) {
            strBonusType = "绩效奖金";
            pageContext.putSessionValue("saBonusType", strBonusType);
        }
        OAMessageChoiceBean bonusBean = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("TypeName");
        if (bonusBean.getValue(pageContext) == null || 
            "".equals(bonusBean.getValue(pageContext).toString())) {
            bonusBean.setDefaultValue(strBonusType);
        }

        OAMessageTextInputBean flagBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("FlagItem");
        flagBean.setValue(pageContext, 
                          "更新标志：" + queryFlag + "---奖金类型：" + bonusType + 
                          "---发放日期：" + distDate);

        //首次进入页面，刷新页面VO数据，通过增加标志queryFlag，避免在页面“删除”操作中因执行页面刷新而清除了一些“未删除且未保存的数据”
        if (queryFlag == 0) {
            // 添加该段代码，避免在首次进入发放页面时，自动带出上月的数据，第2条及以后的记录状态位不能清空
            OAAdvancedTableBean table = 
                (OAAdvancedTableBean)webBean.findIndexedChildRecursive("DeptDistTable");
            table.queryData(pageContext);

            if (yearBean.getValue(pageContext) == null || 
                bonusBean.getValue(pageContext) == null || 
                "".equals(bonusType) || "".equals(distDate)) {
                bonusType = strBonusType;
                distDate = strDate;
            }
            searchDistData(pageContext, webBean, distDate, bonusType);
            flagBean.setValue(pageContext, 
                              "更新标志：" + queryFlag + "---奖金类型：" + bonusType + 
                              "---发放日期：" + distDate);

            table.queryData(pageContext);
        }
        //在页面初始化结束的时候设置标志为0，避免在queryFlag值为1时退出页面，等第二次再进入页面时，页面不刷新数据显示
        queryFlag = 0;
        pageContext.putSessionValue("saQueryFlag", String.valueOf(queryFlag));

        //部门其他人员，只读设置，已批准的记录不能再进行编辑, edit by yang.gang, 2015-5-18
        SetTableColumnReadOnly("Lov", "DeptName", "ReadOnlyFlag", 
                               "SpecialAwardsDeptVO1", webBean);
        SetTableColumnReadOnly("Text", "AwardsAmount", "ReadOnlyFlag", 
                               "SpecialAwardsDeptVO1", webBean);
        SetTableColumnReadOnly("Text", "Note", "ReadOnlyFlag", 
                               "SpecialAwardsDeptVO1", webBean);
    }

    //调用SpecialAwardsSummaryVO的initSQL方法实现依据当前奖金类型和发放日期对汇总VO的SQL进行初始化

    private void InitSummarySQL(OAPageContext pageContext, OAWebBean webBean, 
                                String sqlDate, String bonusType) {
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        SpecialAwardsSummaryVOImpl vo = 
            (SpecialAwardsSummaryVOImpl)am.findViewObject("SpecialAwardsSummaryVO1");
        vo.initSQL(sqlDate, bonusType);
    }

    //实现功能：部门发放表新增一行

    private void createAnotherDeptRow(OAPageContext pageContext, 
                                      OAWebBean webBean, Row row) {
        OAApplicationModuleImpl am = 
            (OAApplicationModuleImpl)pageContext.getApplicationModule(webBean);
        OAViewObject vo = 
            (OAViewObject)am.findViewObject("SpecialAwardsDeptVO1");
        if (vo != null) {
            Row row1 = vo.createRow();
            int index = vo.getRangeIndexOf(vo.last());
            vo.insertRowAtRangeIndex(index + 1, row1);
            row1.setAttribute("Attribute1", 
                              "N"); //将新增行的Attribute1属性初始化为N，标志改行数据为新增行，在保存操作时对数据库进行insert操作
            row1.setAttribute("Deletestatus", "dDeleteEnabled"); //新增行为“可删除”
            row = row1;
        }
    }
    //实现功能：人员发放表新增一行（与部门发放表功能一致）

    private void createAnotherPersonRow(OAPageContext pageContext, 
                                        OAWebBean webBean, Row row) {
        OAApplicationModuleImpl am = 
            (OAApplicationModuleImpl)pageContext.getApplicationModule(webBean);
        OAViewObject vo = 
            (OAViewObject)am.findViewObject("SpecialAwardsPersonVO1");
        if (vo != null) {
            Row row1 = vo.createRow();
            int index = vo.getRangeIndexOf(vo.last());
            vo.insertRowAtRangeIndex(index + 1, row1);
            row1.setAttribute("Attribute1", "N");
            row1.setAttribute("Deletestatus", "pDeleteEnabled");
            row = row1;
        }
    }
    //实现功能：实现刷新汇总表头部“当前发放年份”和“已发放总额（元）”的数值

    private void querySummaryData(OAPageContext pageContext, OAWebBean webBean, 
                                  String distDate, String bonusType) {
        SpecialAwardsAMImpl amImpl = 
            (SpecialAwardsAMImpl)pageContext.getApplicationModule(webBean);
        //当前发放年份直接取发放日期的年份
        String sqlDate = distDate.substring(0, 4);
        InitSummarySQL(pageContext, webBean, sqlDate, bonusType);

        //已发放总额取VO中的DistTotal字段
        OAViewObject vo = amImpl.getSpecialAwardsSummaryVO1();
        Row[] rows = vo.getAllRowsInRange();
        double sum = 0;
        if (rows.length > 0) {
            String s = rows[0].getAttribute("Disttotal").toString();
            sum = Double.parseDouble(s.trim());
        }

        OAMessageTextInputBean yearBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("DistYear");
        yearBean.setValue(pageContext, sqlDate);
        OAMessageTextInputBean totalBean = 
            (OAMessageTextInputBean)webBean.findIndexedChildRecursive("DistTotal");
        totalBean.setValue(pageContext, String.valueOf(sum));
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);

        String distDate = pageContext.getSessionValue("saDistDate").toString();
        String bonusType = 
            pageContext.getSessionValue("saBonusType").toString();
        String toPeopleFlag = 
            pageContext.getSessionValue("saToPeopleFlag").toString();
        String toDeptFlag = 
            pageContext.getSessionValue("saToDeptFlag").toString();
        int queryFlag = 
            Integer.parseInt(pageContext.getSessionValue("saQueryFlag").toString());

        SpecialAwardsAMImpl amImpl = 
            (SpecialAwardsAMImpl)pageContext.getApplicationModule(webBean);
        int deptORGID = amImpl.getLoginUserDept(); //获取当前操作用户的所属计奖部门

        String event = pageContext.getParameter("event"); //event表示本次操作的事件名称
        String source = pageContext.getParameter("source");

        OAAdvancedTableBean deptDistTableBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("DeptDistTable");
        OAAdvancedTableBean personDistTableBean = 
            (OAAdvancedTableBean)webBean.findIndexedChildRecursive("PersonDistTable");

        if ("oaAddAttachment".equals(event)) {
            queryFlag = 1;
            pageContext.putSessionValue("saQueryFlag", 
                                        String.valueOf(queryFlag));
        }
        //当前操作是部门发放表新增一行
        else if (source.equals(deptDistTableBean.getName()) && 
                 "addRows".equals(event)) {
            //检查当前奖金类型是否对部门发放，如Y，则实现；否则不予实现新增一行功能，并给出错误提示
            if ("Y".equals(toDeptFlag))
                createAnotherDeptRow(pageContext, webBean, null);
            else {
                this.ShowMessage(pageContext, 
                                 "该类奖金 " + bonusType + " 不能对部门发放，无法添加数据！", 
                                 "error");
            }
        }
        //当前操作是人员发放表新增一行（同部门发放表）
        else if (source.equals(personDistTableBean.getName()) && 
                 "addRows".equals(event)) {
            if ("Y".equals(toPeopleFlag))
                createAnotherPersonRow(pageContext, webBean, null);
            else {
                this.ShowMessage(pageContext, 
                                 "该类奖金 " + bonusType + " 不能对人员发放，无法添加数据！", 
                                 "error");
            }
        }
        //当前操作是部门发放表删除一行
        else if ("deptDelete".equals(event)) {
            queryFlag = 1;
            pageContext.putSessionValue("saQueryFlag", 
                                        String.valueOf(queryFlag));
            String flag = "delDept";
            pageContext.putSessionValue("saDelObj", flag);
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/specialawards/webui/SpecialAwardsWarningPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        }
        //点击删除提示页的“是”按钮，实现数据行删除
        else if (pageContext.getParameter("DeptDeleteYesButton") != null) {
            String distId = pageContext.getParameter("distDeptId");
            String deleteDeptName = pageContext.getParameter("deleteDeptName");
            amImpl.DeleteDeptLine(distId, deleteDeptName);
        }
        //当前操作是人员发放表删除一行（同部门发放表）
        else if ("personDelete".equals(event)) {
            queryFlag = 1;
            pageContext.putSessionValue("saQueryFlag", 
                                        String.valueOf(queryFlag));
            String flag = "delPerson";
            pageContext.putSessionValue("saDelObj", flag);
            pageContext.setForwardURL("OA.jsp?page=/cux/oracle/apps/per/bonus/specialawards/webui/SpecialAwardsWarningPG", 
                                      null, 
                                      OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                      null, null, true, 
                                      OAWebBeanConstants.ADD_BREAD_CRUMB_NO, 
                                      OAWebBeanConstants.IGNORE_MESSAGES);
        } else if (pageContext.getParameter("PersonDeleteYesButton") != null) {
            String distId = pageContext.getParameter("distPersonId");
            String distName = pageContext.getParameter("deletePerName");
            amImpl.DeletePersonLine(distId, distName);
        }
        //实现功能：发放页“保存”按钮，根据发放日期、奖金类型、当前操作人员计奖部门名实现当前发放页数据的保存
        else if (pageContext.getParameter("SaveButton") != null) {
            saveDistData(pageContext, webBean, distDate, bonusType, deptORGID);
        }
        //每一次选择发放日期和奖金类型都会出发search事件，刷新页面部门表和人员表的数据
        else if ("search".equals(event)) {
            OAMessageChoiceBean bonusTypeBean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("TypeName");
            bonusType = bonusTypeBean.getSelectionValue(pageContext);
            pageContext.putSessionValue("saBonusType", bonusType);
            OAMessageChoiceBean distDateBean = 
                (OAMessageChoiceBean)webBean.findIndexedChildRecursive("DistDate");
            distDate = distDateBean.getSelectionValue(pageContext);
            pageContext.putSessionValue("saDistDate", distDate);
            searchDistData(pageContext, webBean, distDate, bonusType);
        }
        //实现功能：发放页“提交”按钮，先实现数据保存，然后实现数据提交（更新数据状态为“已提交”），然后刷新页面表格数据
        else if (pageContext.getParameter("SubmitButton") != null) {
            int result = 
                saveDistData(pageContext, webBean, distDate, bonusType, 
                             deptORGID);
            //如果保存范围结果为0，则代表当前页数据都满足要求，可以提交；否则不予提交
            if (result != 0) {
                return;
            }
            deptDistTableBean.queryData(pageContext);
            personDistTableBean.queryData(pageContext);
            amImpl.submitDistData();

            //********添加审批工作流***********
            //空数据不能提交
            int dataNumber = amImpl.getValidDataNum();
            if (dataNumber == 0) {
                deptDistTableBean.queryData(pageContext);
                personDistTableBean.queryData(pageContext);
                this.ShowMessage(pageContext, "提交成功（无部门审批数据）！", "info");
                return;
            }
            int lotID = amImpl.getLotID();
            if (lotID == -1) {
                this.ShowMessage(pageContext, "提交失败。原因：审批流申请失败！", "error");
                return;
            }
            int updateResult = amImpl.updateDataLotID(lotID);
            if (updateResult == -1) {
                this.ShowMessage(pageContext, "提交失败。原因：审批编号错误！", "error");
                return;
            }
            int attachmentId = amImpl.getAttachmentId();
            amImpl.init_approver(lotID, attachmentId);
            String strCode = amImpl.start_wf_prc(lotID, attachmentId);
            if (!"success".equals(strCode)) {
                this.ShowMessage(pageContext, strCode, "error");
                return;
            }

            this.ShowMessage(pageContext, 
                             "提交成功（有" + dataNumber + "条部门奖金数据已提交审批）！", "info");

            deptDistTableBean.queryData(pageContext);
            personDistTableBean.queryData(pageContext);
        }
    }

    //实现功能：页面数据的有效性验证以及对数据库的插入更新等操作

    private int saveDistData(OAPageContext pageContext, OAWebBean webBean, 
                             String distDate, String bonusType, 
                             int deptORGID) {
        SpecialAwardsAMImpl amImpl = 
            (SpecialAwardsAMImpl)pageContext.getApplicationModule(webBean);
        OAAdvancedTableBean deptDistTableBean = null;
        deptDistTableBean = 
                (OAAdvancedTableBean)webBean.findIndexedChildRecursive("DeptDistTable");
        OAAdvancedTableBean personDistTableBean = null;
        personDistTableBean = 
                (OAAdvancedTableBean)webBean.findIndexedChildRecursive("PersonDistTable");
        int result1 = 0;
        int result2 = 0;

        String toDeptFlag = 
            pageContext.getSessionValue("saToDeptFlag").toString();
        String toPeopleFlag = 
            pageContext.getSessionValue("saToPeopleFlag").toString();
        String strDistDate = 
            distDate.substring(0, 4) + distDate.substring(5, 7);
        //如果当前奖金类型可以对部门发放，则保存部门发放表数据，返回值result1 = 0代表保存成功
        if ("Y".equals(toDeptFlag)) {
            result1 = amImpl.SaveDeptDist(strDistDate, bonusType);
        }
        //同部门发放表
        if ("Y".equals(toPeopleFlag)) {
            result2 = amImpl.SavePersonDist(distDate, bonusType, deptORGID);
        }
        //根据保存数据返回值来给予相应提示信息
        checkResult(result1, result2, pageContext);

        //如果部门数据保存成功，刷新部门发放表（主要是数据状态的刷新）
        if ((result1 == 0) && ("Y".equals(toDeptFlag))) {
            amImpl.queryDeptData(bonusType, strDistDate);
            querySummaryData(pageContext, webBean, distDate, bonusType);
        }
        //同部门表
        if ((result2 == 0) && ("Y".equals(toPeopleFlag))) {
            amImpl.queryPersonData(bonusType, distDate);
        }
        //返回两个表保存结果的或集，如都成功，则为0；否则非0
        return result1 | result2;
    }
    //实现功能：search事件

    private void searchDistData(OAPageContext pageContext, OAWebBean webBean, 
                                String distDate, String bonusType) {
        SpecialAwardsAMImpl amImpl = 
            (SpecialAwardsAMImpl)pageContext.getApplicationModule(webBean);
        //查询当前奖金类型是否对部门和人员发放，Y 可以；N 不可以。
        String toPeopleFlag = amImpl.bonusToPeopleFlag(bonusType);
        String toDeptFlag = amImpl.bonusToDeptFlag(bonusType);
        pageContext.putSessionValue("saToPeopleFlag", toPeopleFlag);
        pageContext.putSessionValue("saToDeptFlag", toDeptFlag);

        //如果范围标志为error，则出现程序级错误，需重新调试对应的存储过程
        if (("error".equals(toDeptFlag)) || ("error".equals(toPeopleFlag))) {
            OAException errMessage = 
                new OAException("奖金类型 " + bonusType + "-" + toDeptFlag + "-" + 
                                toPeopleFlag + " 出现问题，请联系系统管理员！", 
                                OAException.ERROR);
            pageContext.putDialogMessage(errMessage);
        }
        //根据发放日期和奖金类型更新部门表和人员表    
        String searchDistDate = 
            distDate.substring(0, 4) + distDate.substring(5, 7);
        amImpl.queryAttachment(bonusType, searchDistDate);
        amImpl.queryDeptData(bonusType, searchDistDate);
        amImpl.queryPersonData(bonusType, distDate);

        querySummaryData(pageContext, webBean, distDate, bonusType);
    }
    //实现功能：根据传入值选择提示信息

    private void checkResult(int result1, int result2, 
                             OAPageContext pageContext) {
        if ((result1 == 0) && (result2 == 0)) {
            OAException message = 
                new OAException("操作成功！", OAException.CONFIRMATION);
            pageContext.putDialogMessage(message);

            return;
        }
        if ((result1 == 3) || (result2 == 3)) {
            OAException message = 
                new OAException("系统出现非常规错误，请联系管理员！", OAException.ERROR);
            pageContext.putDialogMessage(message);

            return;
        }
        if (result1 == 1) {
            OAException errMessage = 
                new OAException("部门发放列表中某条记录部门名称有错误，请检查后再保存！", 
                                OAException.ERROR);
            pageContext.putDialogMessage(errMessage);
        } else if (result1 == 2) {
            OAException errMessage = 
                new OAException("部门发放表每一项奖金必须大于0，请检查后再保存", OAException.ERROR);
            pageContext.putDialogMessage(errMessage);
        } else if (result1 == 100) {
            OAException errMessage = 
                new OAException("部门发放表存在重复的部门，请检查后再保存", OAException.ERROR);
            pageContext.putDialogMessage(errMessage);
        }
        if (result2 == 1) {
            OAException errMessage = 
                new OAException("人员发放列表中某条记录人员信息有错误，请检查后再保存！", 
                                OAException.ERROR);
            pageContext.putDialogMessage(errMessage);
        } else if (result2 == 2) {
            OAException errMessage = 
                new OAException("人员发放表每一项奖金必须大于0，请检查后再保存", OAException.ERROR);
            pageContext.putDialogMessage(errMessage);
        } else if ((result1 == 4) && (result2 == 4)) {
            OAException message = 
                new OAException("已成功提交发放数据！", OAException.CONFIRMATION);
            pageContext.putDialogMessage(message);

            return;
        }
    } //end checkResult

    //  设置表的列只读（根据标识判读只读属性）

    private void SetTableColumnReadOnly(String ColStyle, String ColName, 
                                        String FlagName, String VoName, 
                                        OAWebBean webBean) {
        if (ColStyle.equals("Text")) {
            OAMessageTextInputBean obj = 
                (OAMessageTextInputBean)webBean.findChildRecursive(ColName);
            obj.setAttributeValue(oracle.cabo.ui.UIConstants.READ_ONLY_ATTR, 
                                  new OADataBoundValueViewObject(obj, FlagName, 
                                                                 VoName)); // 1, 只读, 0, 读写
        } else if (ColStyle.equals("Lov")) {
            OAMessageLovInputBean obj = 
                (OAMessageLovInputBean)webBean.findChildRecursive(ColName);
            obj.setAttributeValue(oracle.cabo.ui.UIConstants.READ_ONLY_ATTR, 
                                  new OADataBoundValueViewObject(obj, FlagName, 
                                                                 VoName));
        }
    }

    private void ShowMessage(OAPageContext pageContext, String strMessage, 
                             String strStyle) {
        if ("info".equals(strStyle)) {
            OAException tipMessage = 
                new OAException(strMessage, OAException.INFORMATION);
            pageContext.putDialogMessage(tipMessage);
        } else if ("error".equals(strStyle)) {
            OAException tipMessage = 
                new OAException(strMessage, OAException.ERROR);
            pageContext.putDialogMessage(tipMessage);
        }
    }

}
