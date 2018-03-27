package cux.oracle.apps.per.bonus.pay.server;

import cux.oracle.apps.per.review.server.AppraisalEmpVOImpl;

import java.io.PrintStream;
import java.io.Reader;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.Types;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
import oracle.apps.fnd.framework.server.common.OAApplicationModule;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

public class PayAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public PayAMImpl() {
    }

    public PersonDisVoImpl getPersonDisVo1() {
        return ((PersonDisVoImpl)findViewObject("PersonDisVo1"));
    }

    public static void main(String[] args) {
        launchTester("cux.oracle.apps.per.bonus.pay.server", "PayAMLocal");
    }

    public OAViewObjectImpl getPeriodVO1() {
        return ((OAViewObjectImpl)findViewObject("PeriodVO1"));
    }

    public OAViewObjectImpl getDisOrgVO1() {
        return ((OAViewObjectImpl)findViewObject("DisOrgVO1"));
    }

    public OAViewObjectImpl getBonusTypeVO1() {
        return ((OAViewObjectImpl)findViewObject("BonusTypeVO1"));
    }

    public OAViewObjectImpl getEmpVO1() {
        return ((OAViewObjectImpl)findViewObject("EmpVO1"));
    }

    /**Container's getter for OtherPersonDisVO1
     */
    public OAViewObjectImpl getOtherPersonDisVO1() {
        return (OAViewObjectImpl)findViewObject("OtherPersonDisVO1");
    }

    public void initQuery() {
        OAViewObject vo = getPersonDisVo1();
        vo.executeQuery();
    }

    public void initQuery(String period, int org, int iEmpOrgID, 
                          String employee, String status) {
        PersonDisVoImpl vo = getPersonDisVo1();

        if (vo == null) {
            MessageToken[] tokens = 
            { new MessageToken("OBJECT_NAME", "PersonDisVo1") };
            throw new OAException("AK", "FWK_TBX_OBJECT_NOT_FOUND", tokens);
        }
        vo.initQuery(period, org, iEmpOrgID, employee, status);
    }

    /* 查询本部门的人员
   * org:  发放组织ID
   * iEmpOrgID:  人员部门组织ID
   * */

    public void initOtherQuery(String period, int org, int iEmpOrgID) {
        OtherPersonDisVOImpl vo = (OtherPersonDisVOImpl)getOtherPersonDisVO1();
        if (vo == null) {
            MessageToken[] tokens = 
            { new MessageToken("OBJECT_NAME", "OtherPersonDisVO1") };
            throw new OAException("AK", "FWK_TBX_OBJECT_NOT_FOUND", tokens);
        }
        vo.initOtherQuery(org, iEmpOrgID, period);
    }

    /* 提交奖金记录 */

    public String submitForApproval(String PersonDistributionId) {
        String result = "";
        OADBTransaction transaction = getOADBTransaction();

        CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.element_entry_main_prc(?,?)", 
                                                1);
        try {
            cs.setString(1, PersonDistributionId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeUpdate();
            result = cs.getString(2);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (cs != null)
                try {
                    cs.close();
                } catch (Exception localException1) {
                    System.out.println(localException1.toString());
                }
        }
        return result;
    }

    public String saveForReturn(String PersonDistributionId, String Note) {
        String result = "";
        OADBTransaction transaction = getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.save_for_return(?,?,?)", 
                                                1);
        try {
            Float pid = Float.parseFloat(PersonDistributionId);
            cs.setFloat(1, pid);
            cs.setString(2, Note);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeUpdate();
            result = cs.getString(3);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (cs != null)
                try {
                    cs.close();
                } catch (Exception localException1) {
                    System.out.println(localException1.toString());
                }
        }

        return result;
    }

    public String submitReject() {
        String result = "";
        OADBTransaction transaction = getOADBTransaction();
        CallableStatement cs = 
            transaction.createCallableStatement("call cux_hr_bonus.submit_reject(?)", 
                                                1);
        try {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.executeUpdate();
            result = cs.getString(1);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (cs != null)
                try {
                    cs.close();
                } catch (Exception localException1) {
                    System.out.println(localException1.toString());
                }
        }

        return result;
    }

    /* 获取VO用户选中的行数 */

    public int GetSelectRowCount() {
        PersonDisVoImpl vo = getPersonDisVo1();
        int fetchedRowCount = vo.getFetchedRowCount();
        if (fetchedRowCount == 0)
            return 0;

        RowSetIterator selectIter = vo.createRowSetIterator("selectIter");
        selectIter.setRangeStart(0);
        selectIter.setRangeSize(fetchedRowCount);

        PersonDisVoRowImpl row = null;
        int select_count = 0;

        for (int i = 0; i < fetchedRowCount; ++i) {
            row = (PersonDisVoRowImpl)selectIter.getRowAtRangeIndex(i);
            if ((row.getSelectFlag() != null) && 
                (row.getSelectFlag().equals("Y"))) {
                ++select_count;
            } //end if
        } //end for

        return select_count;
    }

    /* 获取VO用户选中的行中，是否包含需要选择工资单的行（员工对于多个工资单）
     * created by yang.gang, 2016-3-15
     * */

    public String ValidateSelectRowPayroll() {
        PersonDisVoImpl vo = getPersonDisVo1();
        int fetchedRowCount = vo.getFetchedRowCount();
        RowSetIterator selectIter = vo.createRowSetIterator("selectIter");
        selectIter.setRangeStart(0);
        selectIter.setRangeSize(fetchedRowCount);
        PersonDisVoRowImpl row = null;
        String strRtn = "S";

        for (int i = 0; i < fetchedRowCount; ++i) {
            row = (PersonDisVoRowImpl)selectIter.getRowAtRangeIndex(i);
            if (row.getSelectFlag() == null)
                continue;
            else if (!row.getSelectFlag().equals("Y"))
                continue;

            String strPayrollFlag = row.getAttribute("PayrollFlag").toString();
            String strEmpName = row.getAttribute("LastName").toString();

            if ("N".equals(strPayrollFlag)) {
                strRtn = strEmpName + "存在多个工资单，请指定导入的工资单";
                break;
            }
        } //end for

        return strRtn;
    }

    public String batchApprove() {
        PersonDisVoImpl vo = getPersonDisVo1();
        int fetchedRowCount = vo.getFetchedRowCount();
        Number PersonDistributionId = null;
        PersonDisVoRowImpl row = null;
        int succ_count = 0;
        int err_count = 0;
        String lReturn = "";

        RowSetIterator selectIter = vo.createRowSetIterator("selectIter");
        selectIter.setRangeStart(0);
        selectIter.setRangeSize(fetchedRowCount);
        for (int i = 0; i < fetchedRowCount; ++i) {
            row = (PersonDisVoRowImpl)selectIter.getRowAtRangeIndex(i);
            if (row.getSelectFlag() == null)
                continue;
            else if (!row.getSelectFlag().equals("Y"))
                continue;
            String strImportStatus = 
                row.getAttribute("ImportStatus").toString();
            // 线外,无工资单 的不需要提交
            if (strImportStatus.equals("线外") || strImportStatus.equals("无工资单"))
                continue;

            PersonDistributionId = row.getPersonDistributionId();
            lReturn = submitForApproval(PersonDistributionId.toString());
            row.setSelectFlag(null);
            if (lReturn.equals("Y"))
                ++succ_count;
            else
                err_count++;
        } //end for

        vo.executeQuery();

        if (err_count > 0)
            return "CUX_ELEMENT_IMPORT_ERROR";
        else if (succ_count > 0)
            return "CUX_ELEMENT_IMPORT_SUCCESS";
        else
            return "CUX_ELEMENT_NO_SELECTED";
    }

    public String batchReject(String Note) {
        PersonDisVoImpl vo = getPersonDisVo1();
        int fetchedRowCount = vo.getFetchedRowCount();
        String lReturn = "";

        RowSetIterator selectIter = null;
        selectIter = vo.createRowSetIterator("selectIter");
        selectIter.setRangeStart(0);
        selectIter.setRangeSize(fetchedRowCount);
        for (int i = 0; i < fetchedRowCount; ++i) {
            PersonDisVoRowImpl row = 
                (PersonDisVoRowImpl)selectIter.getRowAtRangeIndex(i);
            if (row.getSelectFlag() == null)
                continue;
            if (!row.getSelectFlag().equals("Y"))
                continue;

            Number PersonDistributionId = row.getPersonDistributionId();
            lReturn = saveForReturn(PersonDistributionId.toString(), Note);
            if (lReturn.equals("Y")) {
                row.setSelectFlag(null);
            } else {
                vo.executeQuery();
                return "CUX_ELEMENT_REJECT_ERROR";
            }
        } //end for

        lReturn = submitReject();
        if (lReturn.equals("Y")) {
            vo.executeQuery();
            return "CUX_ELEMENT_REJECT_SUCCESS";
        }
        vo.executeQuery();
        return "CUX_ELEMENT_REJECT_ERROR";

    }

    /**Container's getter for InternalDisOrgVO1
     */
    public InternalDisOrgVOImpl getInternalDisOrgVO1() {
        return (InternalDisOrgVOImpl)findViewObject("InternalDisOrgVO1");
    }

    /**Container's getter for ExternalEmpOrgVO1
     */
    public ExternalEmpOrgVOImpl getExternalEmpOrgVO1() {
        return (ExternalEmpOrgVOImpl)findViewObject("ExternalEmpOrgVO1");
    }

    /**Container's getter for InternalEmpOrgVO1
     */
    public InternalEmpOrgVOImpl getInternalEmpOrgVO1() {
        return (InternalEmpOrgVOImpl)findViewObject("InternalEmpOrgVO1");
    }

    /* 获取当前用户对于职责 的公司id */

    public int GetCurrentRespOrgID() {
        try {
            OADBTransaction transaction = getOADBTransaction();
            CallableStatement cs = 
                transaction.createCallableStatement("select APPS.CUX_HR_BONUS.GET_COMP_ID_BYRESP from dual", 
                                                    1);
            ResultSet rs = 
                cs.executeQuery("select APPS.CUX_HR_BONUS.GET_COMP_ID_BYRESP from dual");
            if (rs.next()) {
                return rs.getInt(1);
            } else
                return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    /**Container's getter for PayrollVO1
     */
    public PayrollVOImpl getPayrollVO1() {
        return (PayrollVOImpl)findViewObject("PayrollVO1");
    }

    /* 查询人员工资单
     * iPerDistID:  发放ID
     * */

    public void initPayrollQuery(int iPerDistID) {
        PayrollVOImpl vo = (PayrollVOImpl)this.getPayrollVO1();
        vo.initQuery(iPerDistID);
    }

    /* 获取VO用户选中的行数, 工资单选择VO */

    public int GetPayrollVOSelectRowCount() {
        PayrollVOImpl vo = this.getPayrollVO1();

        int RowCount = vo.getRowCount();
        RowSetIterator deptPersonIter = 
            vo.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(RowCount);

        for (int i = 0; i < RowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String selectFlag = "";
            if (pRow.getAttribute("SelectFlag") != null) {
                selectFlag = pRow.getAttribute("SelectFlag").toString();
            }
            if ("Y".equals(selectFlag))
                return 1;
        }
        return 0;
    }

    /* 工资单选择VO，保存用户选择的工资单 */

    public String SavePayrollSelected() {
        PayrollVOImpl vo = this.getPayrollVO1();

        int RowCount = vo.getRowCount();
        RowSetIterator deptPersonIter = 
            vo.createRowSetIterator("deptPersonIter");
        deptPersonIter.setRangeStart(0);
        deptPersonIter.setRangeSize(RowCount);

        for (int i = 0; i < RowCount; ++i) {
            Row pRow = deptPersonIter.getRowAtRangeIndex(i);
            String selectFlag = "";
            if (pRow.getAttribute("SelectFlag") != null) {
                selectFlag = pRow.getAttribute("SelectFlag").toString();
            }
            if ("Y".equals(selectFlag)) {
                String strPersonID = pRow.getAttribute("PersonId").toString();
                String strPayrollID = 
                    pRow.getAttribute("PayrollId").toString();
                int iPersonID = Integer.valueOf(strPersonID);
                int iPayrollID = Integer.valueOf(strPayrollID);
                return this.SavePayrollSelected(iPersonID, iPayrollID);
            }
        }
        return "NOSELECTED";
    }

    /* 工资单选择VO，保存用户选择的工资单 */

    public String SavePayrollSelected(int iPersonID, int iPayrollID) {
        CallableStatement cs = null;
        OADBTransaction transaction = this.getOADBTransaction();
        String strRtn = "";
        cs = 
 transaction.createCallableStatement("call CUX_HR_BONUS.SAVE_ASSIGN_PAYROLL(?, ?, ?)", 
                                     1);
        try {
            cs.setInt(1, iPersonID);
            cs.setInt(2, iPayrollID);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeUpdate();
            strRtn = cs.getString(3);
            cs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return strRtn;
    }

}
