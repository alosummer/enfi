package cux.oracle.apps.pa.pmp.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PmpManHourVORowImpl extends OAViewRowImpl {
    public static final int PROJECTID = 0;
    public static final int VERSIONNUM = 1;
    public static final int SORTSEQ = 2;
    public static final int MAINCATEGORY = 3;
    public static final int SUBCATEGORY = 4;
    public static final int MANHOUR = 5;
    public static final int ESTIMATEEXPENSE = 6;

    /**This is the default constructor (do not remove)
     */
    public PmpManHourVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute ProjectId
     */
    public Number getProjectId() {
        return (Number)getAttributeInternal(PROJECTID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProjectId
     */
    public void setProjectId(Number value) {
        setAttributeInternal(PROJECTID, value);
    }

    /**Gets the attribute value for the calculated attribute SortSeq
     */
    public Number getSortSeq() {
        return (Number)getAttributeInternal(SORTSEQ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SortSeq
     */
    public void setSortSeq(Number value) {
        setAttributeInternal(SORTSEQ, value);
    }

    /**Gets the attribute value for the calculated attribute MainCategory
     */
    public String getMainCategory() {
        return (String)getAttributeInternal(MAINCATEGORY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MainCategory
     */
    public void setMainCategory(String value) {
        setAttributeInternal(MAINCATEGORY, value);
    }

    /**Gets the attribute value for the calculated attribute SubCategory
     */
    public String getSubCategory() {
        return (String)getAttributeInternal(SUBCATEGORY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SubCategory
     */
    public void setSubCategory(String value) {
        setAttributeInternal(SUBCATEGORY, value);
    }

    /**Gets the attribute value for the calculated attribute ManHour
     */
    public Number getManHour() {
        return (Number)getAttributeInternal(MANHOUR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ManHour
     */
    public void setManHour(Number value) {
        setAttributeInternal(MANHOUR, value);
    }

    /**Gets the attribute value for the calculated attribute EstimateExpense
     */
    public Number getEstimateExpense() {
        return (Number)getAttributeInternal(ESTIMATEEXPENSE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EstimateExpense
     */
    public void setEstimateExpense(Number value) {
        setAttributeInternal(ESTIMATEEXPENSE, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTID:
            return getProjectId();
        case VERSIONNUM:
            return getVersionNum();
        case SORTSEQ:
            return getSortSeq();
        case MAINCATEGORY:
            return getMainCategory();
        case SUBCATEGORY:
            return getSubCategory();
        case MANHOUR:
            return getManHour();
        case ESTIMATEEXPENSE:
            return getEstimateExpense();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTID:
            setProjectId((Number)value);
            return;
        case VERSIONNUM:
            setVersionNum((Number)value);
            return;
        case SORTSEQ:
            setSortSeq((Number)value);
            return;
        case MAINCATEGORY:
            setMainCategory((String)value);
            return;
        case SUBCATEGORY:
            setSubCategory((String)value);
            return;
        case MANHOUR:
            setManHour((Number)value);
            return;
        case ESTIMATEEXPENSE:
            setEstimateExpense((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }


    /**Gets the attribute value for the calculated attribute VersionNum
     */
    public Number getVersionNum() {
        return (Number)getAttributeInternal(VERSIONNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute VersionNum
     */
    public void setVersionNum(Number value) {
        setAttributeInternal(VERSIONNUM, value);
    }
}
