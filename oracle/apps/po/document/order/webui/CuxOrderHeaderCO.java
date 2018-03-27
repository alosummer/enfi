/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package cux.oracle.apps.po.document.order.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovTextInputBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

/**
 * Controller for ...
 */
public class CuxOrderHeaderCO extends oracle.apps.po.document.order.webui.OrderHeaderCO {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAApplicationModule am = pageContext.getApplicationModule(webBean);
        ViewObject vo = am.findViewObject("PoHeaderMergeVO");
        Row row = vo.getCurrentRow();
        String purTypeCode = (String)row.getAttribute("Attribute4");
        String poClassCode = (String)row.getAttribute("Attribute5");
        String poHeaderId = 
            ((oracle.jbo.domain.Number)row.getAttribute("PoHeaderId")).stringValue();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT flvv.meaning " + 
                  "FROM   fnd_lookup_values_vl flvv " + 
                  "WHERE  flvv.lookup_type = 'ENFI_PO_PURTYPE' " + 
                  "AND    flvv.lookup_code = '" + purTypeCode + "'");
        String purTypeName = this.getSqlValue(am, sb.toString());
        sb = new StringBuilder();
        sb.append("SELECT ffvl.flex_value " + 
                  "FROM   fnd_flex_values_vl  ffvl " + 
                  "      ,fnd_flex_value_sets ffvs " + 
                  "WHERE  ffvl.flex_value_set_id = ffvs.flex_value_set_id " + 
                  "AND    ffvs.flex_value_set_name = 'ENFI_PO_CLASS' " + 
                  "AND    ffvl.flex_value_id = " + poClassCode);
        String poClassName = this.getSqlValue(am, sb.toString());
        sb = new StringBuilder();
        sb.append("SELECT DISTINCT pph.centralized_purchasing\n" + 
                  "FROM   po_distributions_all     pda\n" + 
                  "      ,po_req_distributions_all prda\n" + 
                  "      ,cux_po_package_headers_t pph\n" + 
                  "      ,cux_po_package_lines_t   ppl\n" + 
                  "WHERE  pda.req_distribution_id = prda.distribution_id\n" + 
                  "AND    prda.requisition_line_id = ppl.requisition_line_id\n" + 
                  "AND    ppl.package_header_id = pph.package_id\n" + 
                  "AND    pda.po_header_id =" + poHeaderId);
        String cpFlag = this.getSqlValue(am, sb.toString());
        String deptName = "";
        if ("Y".equals(cpFlag)) {
            sb = new StringBuilder();
            sb.append("SELECT ou.name\n" + 
                      "FROM   per_all_assignments_f     paaf\n" + 
                      "      ,hr_all_organization_units ou\n" + 
                      "WHERE  trunc(SYSDATE) BETWEEN\n" + 
                      "       trunc(nvl(paaf.effective_start_date, SYSDATE)) AND\n" + 
                      "       trunc(nvl(paaf.effective_end_date, SYSDATE + 1))\n" + 
                      "AND    paaf.organization_id = ou.organization_id\n" + 
                      "AND    paaf.person_id =" + pageContext.getEmployeeId());
            deptName = this.getSqlValue(am, sb.toString());
        }
        if ("N".equals(cpFlag)) {
            sb = new StringBuilder();
            sb.append("SELECT DISTINCT cux_pa_util_pkg.get_project_dept(pda.project_id)\n" + 
                      "FROM   po_distributions_all      pda\n" + 
                      "WHERE  pda.po_header_id = " + poHeaderId);
            deptName = this.getSqlValue(am, sb.toString());
        }
        OAMessageLovInputBean purType = 
            (OAMessageLovInputBean)webBean.findChildRecursive("PurTypeName");
        purType.setText(purTypeName);
        OAMessageLovInputBean poClass = 
            (OAMessageLovInputBean)webBean.findChildRecursive("PoClassName");
        poClass.setText(poClassName);
        OAMessageChoiceBean cpFlagBean = 
            (OAMessageChoiceBean)webBean.findChildRecursive("CpFlag");
        cpFlagBean.setText(pageContext, cpFlag);
        OAMessageTextInputBean deptNameBean = 
            (OAMessageTextInputBean)webBean.findChildRecursive("DeptName");
        deptNameBean.setText(deptName);
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
    }

    public String getSqlValue(OAApplicationModule am, String sql) {
        ViewObject vo = am.findViewObject("QueryVO");
        String result = "";
        if (vo != null) {
            vo.remove();
        }
        vo = am.createViewObjectFromQueryStmt("QueryVO", sql);
        vo.setMaxFetchSize(-1);
        vo.executeQuery();
        if (vo.hasNext()) {
            result = (String)vo.next().getAttribute(0);
        }
        return result;
    }
}
