package cux.oracle.apps.pa.project.servvver;

import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuxPaRepSepcTImpl extends OAEntityImpl {
    public static final int PROJECTPARTYID = 0;
    public static final int SPECIALTYCODE = 1;
    public static final int ATRRIBUTE1 = 2;
    public static final int ATRRIBUTE2 = 3;
    public static final int ATRRIBUTE3 = 4;
    public static final int ATRRIBUTE4 = 5;
    public static final int ATRRIBUTE5 = 6;
    public static final int ATRRIBUTE6 = 7;
    public static final int ATRRIBUTE7 = 8;
    public static final int ATRRIBUTE8 = 9;
    public static final int ATRRIBUTE9 = 10;
    public static final int CREATEDBY = 11;
    public static final int CREATIONDATE = 12;
    public static final int LASTUPDATEDBY = 13;
    public static final int LASTUPDATEDATE = 14;
    public static final int LASTUPDATELOGIN = 15;
    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public CuxPaRepSepcTImpl() {
    }

    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("cux.oracle.apps.pa.project.servvver.CuxPaRepSepcT");
        }
        return mDefinitionObject;
    }

    /**Add attribute defaulting logic in this method.
     */
    public void create(AttributeList attributeList) {
        super.create(attributeList);
    }

    /**Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }

    /**Add Entity validation code in this method.
     */
    protected void validateEntity() {
        super.validateEntity();
    }

    /**Gets the attribute value for ProjectPartyId, using the alias name ProjectPartyId
     */
    public Number getProjectPartyId() {
        return (Number)getAttributeInternal(PROJECTPARTYID);
    }

    /**Sets <code>value</code> as the attribute value for ProjectPartyId
     */
    public void setProjectPartyId(Number value) {
        setAttributeInternal(PROJECTPARTYID, value);
    }

    /**Gets the attribute value for SpecialtyCode, using the alias name SpecialtyCode
     */
    public String getSpecialtyCode() {
        return (String)getAttributeInternal(SPECIALTYCODE);
    }

    /**Sets <code>value</code> as the attribute value for SpecialtyCode
     */
    public void setSpecialtyCode(String value) {
        setAttributeInternal(SPECIALTYCODE, value);
    }

    /**Gets the attribute value for Atrribute1, using the alias name Atrribute1
     */
    public String getAtrribute1() {
        return (String)getAttributeInternal(ATRRIBUTE1);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute1
     */
    public void setAtrribute1(String value) {
        setAttributeInternal(ATRRIBUTE1, value);
    }

    /**Gets the attribute value for Atrribute2, using the alias name Atrribute2
     */
    public String getAtrribute2() {
        return (String)getAttributeInternal(ATRRIBUTE2);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute2
     */
    public void setAtrribute2(String value) {
        setAttributeInternal(ATRRIBUTE2, value);
    }

    /**Gets the attribute value for Atrribute3, using the alias name Atrribute3
     */
    public String getAtrribute3() {
        return (String)getAttributeInternal(ATRRIBUTE3);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute3
     */
    public void setAtrribute3(String value) {
        setAttributeInternal(ATRRIBUTE3, value);
    }

    /**Gets the attribute value for Atrribute4, using the alias name Atrribute4
     */
    public String getAtrribute4() {
        return (String)getAttributeInternal(ATRRIBUTE4);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute4
     */
    public void setAtrribute4(String value) {
        setAttributeInternal(ATRRIBUTE4, value);
    }

    /**Gets the attribute value for Atrribute5, using the alias name Atrribute5
     */
    public String getAtrribute5() {
        return (String)getAttributeInternal(ATRRIBUTE5);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute5
     */
    public void setAtrribute5(String value) {
        setAttributeInternal(ATRRIBUTE5, value);
    }

    /**Gets the attribute value for Atrribute6, using the alias name Atrribute6
     */
    public String getAtrribute6() {
        return (String)getAttributeInternal(ATRRIBUTE6);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute6
     */
    public void setAtrribute6(String value) {
        setAttributeInternal(ATRRIBUTE6, value);
    }

    /**Gets the attribute value for Atrribute7, using the alias name Atrribute7
     */
    public String getAtrribute7() {
        return (String)getAttributeInternal(ATRRIBUTE7);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute7
     */
    public void setAtrribute7(String value) {
        setAttributeInternal(ATRRIBUTE7, value);
    }

    /**Gets the attribute value for Atrribute8, using the alias name Atrribute8
     */
    public String getAtrribute8() {
        return (String)getAttributeInternal(ATRRIBUTE8);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute8
     */
    public void setAtrribute8(String value) {
        setAttributeInternal(ATRRIBUTE8, value);
    }

    /**Gets the attribute value for Atrribute9, using the alias name Atrribute9
     */
    public String getAtrribute9() {
        return (String)getAttributeInternal(ATRRIBUTE9);
    }

    /**Sets <code>value</code> as the attribute value for Atrribute9
     */
    public void setAtrribute9(String value) {
        setAttributeInternal(ATRRIBUTE9, value);
    }

    /**Gets the attribute value for CreatedBy, using the alias name CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for CreationDate, using the alias name CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for LastUpdateLogin, using the alias name LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTPARTYID:
            return getProjectPartyId();
        case SPECIALTYCODE:
            return getSpecialtyCode();
        case ATRRIBUTE1:
            return getAtrribute1();
        case ATRRIBUTE2:
            return getAtrribute2();
        case ATRRIBUTE3:
            return getAtrribute3();
        case ATRRIBUTE4:
            return getAtrribute4();
        case ATRRIBUTE5:
            return getAtrribute5();
        case ATRRIBUTE6:
            return getAtrribute6();
        case ATRRIBUTE7:
            return getAtrribute7();
        case ATRRIBUTE8:
            return getAtrribute8();
        case ATRRIBUTE9:
            return getAtrribute9();
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PROJECTPARTYID:
            setProjectPartyId((Number)value);
            return;
        case SPECIALTYCODE:
            setSpecialtyCode((String)value);
            return;
        case ATRRIBUTE1:
            setAtrribute1((String)value);
            return;
        case ATRRIBUTE2:
            setAtrribute2((String)value);
            return;
        case ATRRIBUTE3:
            setAtrribute3((String)value);
            return;
        case ATRRIBUTE4:
            setAtrribute4((String)value);
            return;
        case ATRRIBUTE5:
            setAtrribute5((String)value);
            return;
        case ATRRIBUTE6:
            setAtrribute6((String)value);
            return;
        case ATRRIBUTE7:
            setAtrribute7((String)value);
            return;
        case ATRRIBUTE8:
            setAtrribute8((String)value);
            return;
        case ATRRIBUTE9:
            setAtrribute9((String)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number projectPartyId) {
        return new Key(new Object[] { projectPartyId });
    }
}
