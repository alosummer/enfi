package cux.oracle.apps.cux.qhse.quality.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxQualityMagDetailForWFVORowImpl extends OAViewRowImpl {
    public static final int DETAILID = 0;
    public static final int TASKID = 1;
    public static final int DETAILNUMBER = 2;
    public static final int TARGETCLASSCODE = 3;
    public static final int TARGETCLASSNAME = 4;
    public static final int PLANCONTROLCODE = 5;
    public static final int PLANCONTROLNAME = 6;
    public static final int PLANCONTROLDESC = 7;
    public static final int ISDELIVERABLES = 8;
    public static final int ISDELIVERABLEDIS = 9;
    public static final int TARGETVALUE = 10;
    public static final int PLANDATE = 11;
    public static final int APPROVEDATE = 12;
    public static final int DUTEPERSON = 13;
    public static final int DUTEPERSONNAME = 14;
    public static final int RESULT = 15;
    public static final int ISCONFIRM = 16;
    public static final int REMARK = 17;
    public static final int CREATIONDATE = 18;
    public static final int CREATEDBY = 19;
    public static final int LASTUPDATEDATE = 20;
    public static final int LASTUPDATEDBY = 21;
    public static final int LASTUPDATELOGIN = 22;
    public static final int ATTRIBUTECATEGORY = 23;
    public static final int ATTRIBUTE1 = 24;
    public static final int ATTRIBUTE2 = 25;
    public static final int ATTRIBUTE3 = 26;
    public static final int ATTRIBUTE4 = 27;
    public static final int ATTRIBUTE5 = 28;
    public static final int CUXPROJECTWORKPLANFORWFVO = 29;

    /**This is the default constructor (do not remove)
     */
    public CuxQualityMagDetailForWFVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute DetailId
     */
    public Number getDetailId() {
        return (Number)getAttributeInternal(DETAILID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DetailId
     */
    public void setDetailId(Number value) {
        setAttributeInternal(DETAILID, value);
    }

    /**Gets the attribute value for the calculated attribute TaskId
     */
    public Number getTaskId() {
        return (Number)getAttributeInternal(TASKID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TaskId
     */
    public void setTaskId(Number value) {
        setAttributeInternal(TASKID, value);
    }

    /**Gets the attribute value for the calculated attribute DetailNumber
     */
    public Number getDetailNumber() {
        return (Number)getAttributeInternal(DETAILNUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DetailNumber
     */
    public void setDetailNumber(Number value) {
        setAttributeInternal(DETAILNUMBER, value);
    }

    /**Gets the attribute value for the calculated attribute TargetClassCode
     */
    public String getTargetClassCode() {
        return (String)getAttributeInternal(TARGETCLASSCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TargetClassCode
     */
    public void setTargetClassCode(String value) {
        setAttributeInternal(TARGETCLASSCODE, value);
    }

    /**Gets the attribute value for the calculated attribute TargetClassName
     */
    public String getTargetClassName() {
        return (String)getAttributeInternal(TARGETCLASSNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TargetClassName
     */
    public void setTargetClassName(String value) {
        setAttributeInternal(TARGETCLASSNAME, value);
    }

    /**Gets the attribute value for the calculated attribute PlanControlCode
     */
    public String getPlanControlCode() {
        return (String)getAttributeInternal(PLANCONTROLCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlanControlCode
     */
    public void setPlanControlCode(String value) {
        setAttributeInternal(PLANCONTROLCODE, value);
    }

    /**Gets the attribute value for the calculated attribute PlanControlName
     */
    public String getPlanControlName() {
        return (String)getAttributeInternal(PLANCONTROLNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlanControlName
     */
    public void setPlanControlName(String value) {
        setAttributeInternal(PLANCONTROLNAME, value);
    }

    /**Gets the attribute value for the calculated attribute PlanControlDesc
     */
    public String getPlanControlDesc() {
        return (String)getAttributeInternal(PLANCONTROLDESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlanControlDesc
     */
    public void setPlanControlDesc(String value) {
        setAttributeInternal(PLANCONTROLDESC, value);
    }

    /**Gets the attribute value for the calculated attribute IsDeliverables
     */
    public String getIsDeliverables() {
        return (String)getAttributeInternal(ISDELIVERABLES);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute IsDeliverables
     */
    public void setIsDeliverables(String value) {
        setAttributeInternal(ISDELIVERABLES, value);
    }

    /**Gets the attribute value for the calculated attribute IsDeliverableDis
     */
    public String getIsDeliverableDis() {
        return (String)getAttributeInternal(ISDELIVERABLEDIS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute IsDeliverableDis
     */
    public void setIsDeliverableDis(String value) {
        setAttributeInternal(ISDELIVERABLEDIS, value);
    }

    /**Gets the attribute value for the calculated attribute TargetValue
     */
    public String getTargetValue() {
        return (String)getAttributeInternal(TARGETVALUE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TargetValue
     */
    public void setTargetValue(String value) {
        setAttributeInternal(TARGETVALUE, value);
    }

    /**Gets the attribute value for the calculated attribute PlanDate
     */
    public Date getPlanDate() {
        return (Date)getAttributeInternal(PLANDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlanDate
     */
    public void setPlanDate(Date value) {
        setAttributeInternal(PLANDATE, value);
    }

    /**Gets the attribute value for the calculated attribute ApproveDate
     */
    public Date getApproveDate() {
        return (Date)getAttributeInternal(APPROVEDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproveDate
     */
    public void setApproveDate(Date value) {
        setAttributeInternal(APPROVEDATE, value);
    }

    /**Gets the attribute value for the calculated attribute DutePerson
     */
    public Number getDutePerson() {
        return (Number)getAttributeInternal(DUTEPERSON);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DutePerson
     */
    public void setDutePerson(Number value) {
        setAttributeInternal(DUTEPERSON, value);
    }

    /**Gets the attribute value for the calculated attribute DutePersonName
     */
    public String getDutePersonName() {
        return (String)getAttributeInternal(DUTEPERSONNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DutePersonName
     */
    public void setDutePersonName(String value) {
        setAttributeInternal(DUTEPERSONNAME, value);
    }

    /**Gets the attribute value for the calculated attribute Result
     */
    public String getResult() {
        return (String)getAttributeInternal(RESULT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Result
     */
    public void setResult(String value) {
        setAttributeInternal(RESULT, value);
    }

    /**Gets the attribute value for the calculated attribute IsConfirm
     */
    public String getIsConfirm() {
        return (String)getAttributeInternal(ISCONFIRM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute IsConfirm
     */
    public void setIsConfirm(String value) {
        setAttributeInternal(ISCONFIRM, value);
    }

    /**Gets the attribute value for the calculated attribute Remark
     */
    public String getRemark() {
        return (String)getAttributeInternal(REMARK);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Remark
     */
    public void setRemark(String value) {
        setAttributeInternal(REMARK, value);
    }

    /**Gets the attribute value for the calculated attribute CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**Gets the attribute value for the calculated attribute CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**Gets the attribute value for the calculated attribute AttributeCategory
     */
    public String getAttributeCategory() {
        return (String)getAttributeInternal(ATTRIBUTECATEGORY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AttributeCategory
     */
    public void setAttributeCategory(String value) {
        setAttributeInternal(ATTRIBUTECATEGORY, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute1
     */
    public String getAttribute1() {
        return (String)getAttributeInternal(ATTRIBUTE1);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute2
     */
    public String getAttribute2() {
        return (String)getAttributeInternal(ATTRIBUTE2);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute3
     */
    public String getAttribute3() {
        return (String)getAttributeInternal(ATTRIBUTE3);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute4
     */
    public String getAttribute4() {
        return (String)getAttributeInternal(ATTRIBUTE4);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**Gets the attribute value for the calculated attribute Attribute5
     */
    public String getAttribute5() {
        return (String)getAttributeInternal(ATTRIBUTE5);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case DETAILID:
            return getDetailId();
        case TASKID:
            return getTaskId();
        case DETAILNUMBER:
            return getDetailNumber();
        case TARGETCLASSCODE:
            return getTargetClassCode();
        case TARGETCLASSNAME:
            return getTargetClassName();
        case PLANCONTROLCODE:
            return getPlanControlCode();
        case PLANCONTROLNAME:
            return getPlanControlName();
        case PLANCONTROLDESC:
            return getPlanControlDesc();
        case ISDELIVERABLES:
            return getIsDeliverables();
        case ISDELIVERABLEDIS:
            return getIsDeliverableDis();
        case TARGETVALUE:
            return getTargetValue();
        case PLANDATE:
            return getPlanDate();
        case APPROVEDATE:
            return getApproveDate();
        case DUTEPERSON:
            return getDutePerson();
        case DUTEPERSONNAME:
            return getDutePersonName();
        case RESULT:
            return getResult();
        case ISCONFIRM:
            return getIsConfirm();
        case REMARK:
            return getRemark();
        case CREATIONDATE:
            return getCreationDate();
        case CREATEDBY:
            return getCreatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case ATTRIBUTECATEGORY:
            return getAttributeCategory();
        case ATTRIBUTE1:
            return getAttribute1();
        case ATTRIBUTE2:
            return getAttribute2();
        case ATTRIBUTE3:
            return getAttribute3();
        case ATTRIBUTE4:
            return getAttribute4();
        case ATTRIBUTE5:
            return getAttribute5();
        case CUXPROJECTWORKPLANFORWFVO:
            return getCuxProjectWorkplanForWFVO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case DETAILID:
            setDetailId((Number)value);
            return;
        case TASKID:
            setTaskId((Number)value);
            return;
        case DETAILNUMBER:
            setDetailNumber((Number)value);
            return;
        case TARGETCLASSCODE:
            setTargetClassCode((String)value);
            return;
        case TARGETCLASSNAME:
            setTargetClassName((String)value);
            return;
        case PLANCONTROLCODE:
            setPlanControlCode((String)value);
            return;
        case PLANCONTROLNAME:
            setPlanControlName((String)value);
            return;
        case PLANCONTROLDESC:
            setPlanControlDesc((String)value);
            return;
        case ISDELIVERABLES:
            setIsDeliverables((String)value);
            return;
        case ISDELIVERABLEDIS:
            setIsDeliverableDis((String)value);
            return;
        case TARGETVALUE:
            setTargetValue((String)value);
            return;
        case PLANDATE:
            setPlanDate((Date)value);
            return;
        case APPROVEDATE:
            setApproveDate((Date)value);
            return;
        case DUTEPERSON:
            setDutePerson((Number)value);
            return;
        case DUTEPERSONNAME:
            setDutePersonName((String)value);
            return;
        case RESULT:
            setResult((String)value);
            return;
        case ISCONFIRM:
            setIsConfirm((String)value);
            return;
        case REMARK:
            setRemark((String)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        case ATTRIBUTECATEGORY:
            setAttributeCategory((String)value);
            return;
        case ATTRIBUTE1:
            setAttribute1((String)value);
            return;
        case ATTRIBUTE2:
            setAttribute2((String)value);
            return;
        case ATTRIBUTE3:
            setAttribute3((String)value);
            return;
        case ATTRIBUTE4:
            setAttribute4((String)value);
            return;
        case ATTRIBUTE5:
            setAttribute5((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the associated <code>Row</code> using master-detail link CuxProjectWorkplanForWFVO
     */
    public Row getCuxProjectWorkplanForWFVO() {
        return (Row)getAttributeInternal(CUXPROJECTWORKPLANFORWFVO);
    }
}
