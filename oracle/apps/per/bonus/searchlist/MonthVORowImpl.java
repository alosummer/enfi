package cux.oracle.apps.per.bonus.searchlist;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class MonthVORowImpl extends OAViewRowImpl {
    public static final int PERIODNAME = 0;
    public static final int DISTRIBUTIONDATE = 1;

    /**This is the default constructor (do not remove)
     */
    public MonthVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute PeriodName
     */
    public String getPeriodName() {
        return (String)getAttributeInternal(PERIODNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PeriodName
     */
    public void setPeriodName(String value) {
        setAttributeInternal(PERIODNAME, value);
    }

    /**Gets the attribute value for the calculated attribute DistributionDate
     */
    public Date getDistributionDate() {
        return (Date)getAttributeInternal(DISTRIBUTIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DistributionDate
     */
    public void setDistributionDate(Date value) {
        setAttributeInternal(DISTRIBUTIONDATE, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PERIODNAME:
            return getPeriodName();
        case DISTRIBUTIONDATE:
            return getDistributionDate();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PERIODNAME:
            setPeriodName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
