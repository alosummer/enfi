package cux.oracle.apps.per.bonus.offlinelist.server;

import cux.oracle.apps.per.bonus.specialawards.server.SpecialAwardsPersonVOImpl;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Types;

import cux.oracle.apps.per.aprprocess.comm.DoubleProcess;

import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;

import oracle.jbo.Row;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class OfflineListAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public OfflineListAMImpl() {
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) { /* package name */
        /* Configuration Name */launchTester("cux.oracle.apps.per.bonus.offlinelist.server", 
                                             "OfflineListAMLocal");
    }

    /**Container's getter for OfflineListVO1
     */
    public OfflineListVOImpl getOfflineListVO1() {
        return (OfflineListVOImpl)findViewObject("OfflineListVO1");
    }

    public int saveOfflineList() {
        OfflineListVOImpl vo = getOfflineListVO1();
        //更新已有的数据行，可能有修改
        Row[] oldRow = vo.getFilteredRows("Attribute1", "Y");
        for (int i = 0; i < oldRow.length; ++i) {
            Row pRow = oldRow[i];
            String employeeNumber = "";
            if (pRow.getAttribute("EmployeeNumber") != null) {
                employeeNumber = 
                        pRow.getAttribute("EmployeeNumber").toString();
            }
            String employeeName = "";
            if (pRow.getAttribute("LastName") != null) {
                employeeName = pRow.getAttribute("LastName").toString();
            }
            if (employeeName == "") {
                return 1;
            }
            String employeeNote = "";
            if (pRow.getAttribute("Note") != null) {
                employeeNote = pRow.getAttribute("Note").toString();
            }
            String employeeStatus = "";
            if (pRow.getAttribute("Status") != null) {
                employeeStatus = pRow.getAttribute("Status").toString();
            }
            if ("可修改".equals(employeeStatus)) {
                CallableStatement cs = null;
                OADBTransaction transaction = this.getOADBTransaction();
                cs = 
 transaction.createCallableStatement("call apps.cux_hr_bonus.update_offline_list(?, ?, ?)", 
                                     1);
                try {
                    cs.setString(1, employeeNumber);
                    cs.setString(2, employeeName);
                    cs.setString(3, employeeNote);
                    cs.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
        //添加新数据行
        Row[] newRow = vo.getFilteredRows("Attribute1", "N");
        for (int i = 0; i < newRow.length; ++i) {
            Row pRow = newRow[i];
            String employeeNumber = "";
            if (pRow.getAttribute("EmployeeNumber") != null) {
                employeeNumber = 
                        pRow.getAttribute("EmployeeNumber").toString();
            }
            String employeeName = "";
            if (pRow.getAttribute("LastName") != null) {
                employeeName = pRow.getAttribute("LastName").toString();
            }
            String employeeDept = "";
            if (pRow.getAttribute("DeptName") != null) {
                employeeDept = pRow.getAttribute("DeptName").toString();
            }
            if (employeeName == "") {
                return 1;
            }
            String employeeNote = "";
            if (pRow.getAttribute("Note") != null) {
                employeeNote = pRow.getAttribute("Note").toString();
            }
            CallableStatement cs = null;
            OADBTransaction transaction = this.getOADBTransaction();
            cs = 
 transaction.createCallableStatement("call apps.cux_hr_bonus.INSERT_INTO_OFFLINE_LIST(?, ?, ?, ?)", 
                                     1);
            try {
                cs.setString(1, employeeNumber);
                cs.setString(2, employeeName);
                cs.setString(3, employeeDept);
                cs.setString(4, employeeNote);
                cs.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            pRow.setAttribute("Attribute1", "Y");
        }
        return 0;
    }

    public void deleteOfflineList(String employeeNumber) {
        CallableStatement cs = null;
        OADBTransaction transaction = this.getOADBTransaction();
        cs = 
 transaction.createCallableStatement("call apps.cux_hr_bonus.delete_offline_list(?)", 
                                     1);
        try {
            cs.setString(1, employeeNumber);
            cs.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
