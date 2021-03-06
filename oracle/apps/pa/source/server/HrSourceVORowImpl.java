package cux.oracle.apps.pa.source.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HrSourceVORowImpl extends OAViewRowImpl {

    public static final int HRSOURCEID = 0;
    public static final int PERSONID = 1;
    public static final int PERSONNAME = 2;
    public static final int PERSONNUM = 3;
    public static final int DEPTID = 4;
    public static final int DEPTNAME = 5;
    public static final int DATEOFBIRTH = 6;
    public static final int EDUCATION = 7;
    public static final int COLLEGE = 8;
    public static final int GRADUATIONDATE = 9;
    public static final int FIRSTWORKINGTIME = 10;
    public static final int WORKEXPERIENCE = 11;
    public static final int SPECIALITY = 12;
    public static final int ZC = 13;
    public static final int PRRQ = 14;
    public static final int JOBNAME = 15;
    public static final int POSITIONID = 16;
    public static final int POSITIONNAME = 17;
    public static final int EVALUATEJOBLEVEL = 18;
    public static final int ZYFZR = 19;
    public static final int ZTR = 20;
    public static final int SJR = 21;
    public static final int JHR = 22;
    public static final int SHR = 23;
    public static final int SDR = 24;
    public static final int YLGDZZ = 25;
    public static final int ZCLY = 26;
    public static final int HRSOURCEDATE = 27;
    public static final int ORGANIZATIONID = 28;
    public static final int JOBID = 29;
    public static final int HRSOURCETYPE = 30;
    public static final int CURRENTYEARGRADE = 31;
    public static final int LASTYEARGRADE = 32;
    public static final int YEARBEFORELASTGRADE = 33;
    public static final int ZG = 34;
    public static final int XMGLGW = 35;
    public static final int XMGLZJ = 36;
    public static final int ZZQZ = 37;

    /**This is the default constructor (do not remove)
     */
    public HrSourceVORowImpl() {
    }


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case HRSOURCEID:
            return getHrsourceId();
        case PERSONID:
            return getPersonId();
        case PERSONNAME:
            return getPersonName();
        case PERSONNUM:
            return getPersonNum();
        case DEPTID:
            return getDeptId();
        case DEPTNAME:
            return getDeptName();
        case DATEOFBIRTH:
            return getDateOfBirth();
        case EDUCATION:
            return getEducation();
        case COLLEGE:
            return getCollege();
        case GRADUATIONDATE:
            return getGraduationDate();
        case FIRSTWORKINGTIME:
            return getFirstWorkingTime();
        case WORKEXPERIENCE:
            return getWorkExperience();
        case SPECIALITY:
            return getSpeciality();
        case ZC:
            return getZc();
        case PRRQ:
            return getPrrq();
        case JOBNAME:
            return getJobName();
        case POSITIONID:
            return getPositionId();
        case POSITIONNAME:
            return getPositionName();
        case EVALUATEJOBLEVEL:
            return getEvaluateJobLevel();
        case ZYFZR:
            return getZyfzr();
        case ZTR:
            return getZtr();
        case SJR:
            return getSjr();
        case JHR:
            return getJhr();
        case SHR:
            return getShr();
        case SDR:
            return getSdr();
        case YLGDZZ:
            return getYlgdzz();
        case ZCLY:
            return getZcly();
        case HRSOURCEDATE:
            return getHrsourceDate();
        case ORGANIZATIONID:
            return getOrganizationId();
        case JOBID:
            return getJobId();
        case HRSOURCETYPE:
            return getHrsourceType();
        case CURRENTYEARGRADE:
            return getCurrentYearGrade();
        case LASTYEARGRADE:
            return getLastYearGrade();
        case YEARBEFORELASTGRADE:
            return getYearBeforeLastGrade();
        case ZG:
            return getZg();
        case XMGLGW:
            return getXmglGw();
        case XMGLZJ:
            return getXmglZj();
        case ZZQZ:
            return getZzQz();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case HRSOURCEID:
            setHrsourceId((Number)value);
            return;
        case PERSONID:
            setPersonId((Number)value);
            return;
        case PERSONNAME:
            setPersonName((String)value);
            return;
        case PERSONNUM:
            setPersonNum((String)value);
            return;
        case DEPTID:
            setDeptId((Number)value);
            return;
        case DEPTNAME:
            setDeptName((String)value);
            return;
        case DATEOFBIRTH:
            setDateOfBirth((Date)value);
            return;
        case EDUCATION:
            setEducation((String)value);
            return;
        case COLLEGE:
            setCollege((String)value);
            return;
        case GRADUATIONDATE:
            setGraduationDate((String)value);
            return;
        case FIRSTWORKINGTIME:
            setFirstWorkingTime((String)value);
            return;
        case WORKEXPERIENCE:
            setWorkExperience((Number)value);
            return;
        case SPECIALITY:
            setSpeciality((String)value);
            return;
        case ZC:
            setZc((String)value);
            return;
        case PRRQ:
            setPrrq((Date)value);
            return;
        case JOBNAME:
            setJobName((String)value);
            return;
        case POSITIONID:
            setPositionId((Number)value);
            return;
        case POSITIONNAME:
            setPositionName((String)value);
            return;
        case EVALUATEJOBLEVEL:
            setEvaluateJobLevel((String)value);
            return;
        case ZYFZR:
            setZyfzr((String)value);
            return;
        case ZTR:
            setZtr((String)value);
            return;
        case SJR:
            setSjr((String)value);
            return;
        case JHR:
            setJhr((String)value);
            return;
        case SHR:
            setShr((String)value);
            return;
        case SDR:
            setSdr((String)value);
            return;
        case YLGDZZ:
            setYlgdzz((String)value);
            return;
        case ZCLY:
            setZcly((String)value);
            return;
        case HRSOURCEDATE:
            setHrsourceDate((Date)value);
            return;
        case ORGANIZATIONID:
            setOrganizationId((Number)value);
            return;
        case JOBID:
            setJobId((Number)value);
            return;
        case HRSOURCETYPE:
            setHrsourceType((String)value);
            return;
        case CURRENTYEARGRADE:
            setCurrentYearGrade((String)value);
            return;
        case LASTYEARGRADE:
            setLastYearGrade((String)value);
            return;
        case YEARBEFORELASTGRADE:
            setYearBeforeLastGrade((String)value);
            return;
        case ZG:
            setZg((String)value);
            return;
        case XMGLGW:
            setXmglGw((String)value);
            return;
        case XMGLZJ:
            setXmglZj((String)value);
            return;
        case ZZQZ:
            setZzQz((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute PersonId
     */
    public Number getPersonId() {
        return (Number)getAttributeInternal(PERSONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PersonId
     */
    public void setPersonId(Number value) {
        setAttributeInternal(PERSONID, value);
    }

    /**Gets the attribute value for the calculated attribute PersonName
     */
    public String getPersonName() {
        return (String)getAttributeInternal(PERSONNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PersonName
     */
    public void setPersonName(String value) {
        setAttributeInternal(PERSONNAME, value);
    }

    /**Gets the attribute value for the calculated attribute PersonNum
     */
    public String getPersonNum() {
        return (String)getAttributeInternal(PERSONNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PersonNum
     */
    public void setPersonNum(String value) {
        setAttributeInternal(PERSONNUM, value);
    }

    /**Gets the attribute value for the calculated attribute DeptId
     */
    public Number getDeptId() {
        return (Number)getAttributeInternal(DEPTID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DeptId
     */
    public void setDeptId(Number value) {
        setAttributeInternal(DEPTID, value);
    }

    /**Gets the attribute value for the calculated attribute DeptName
     */
    public String getDeptName() {
        return (String)getAttributeInternal(DEPTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DeptName
     */
    public void setDeptName(String value) {
        setAttributeInternal(DEPTNAME, value);
    }

    /**Gets the attribute value for the calculated attribute WorkExperience
     */
    public Number getWorkExperience() {
        return (Number)getAttributeInternal(WORKEXPERIENCE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute WorkExperience
     */
    public void setWorkExperience(Number value) {
        setAttributeInternal(WORKEXPERIENCE, value);
    }

    /**Gets the attribute value for the calculated attribute Speciality
     */
    public String getSpeciality() {
        return (String)getAttributeInternal(SPECIALITY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Speciality
     */
    public void setSpeciality(String value) {
        setAttributeInternal(SPECIALITY, value);
    }

    /**Gets the attribute value for the calculated attribute JobName
     */
    public String getJobName() {
        return (String)getAttributeInternal(JOBNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute JobName
     */
    public void setJobName(String value) {
        setAttributeInternal(JOBNAME, value);
    }

    /**Gets the attribute value for the calculated attribute PositionId
     */
    public Number getPositionId() {
        return (Number)getAttributeInternal(POSITIONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PositionId
     */
    public void setPositionId(Number value) {
        setAttributeInternal(POSITIONID, value);
    }

    /**Gets the attribute value for the calculated attribute PositionName
     */
    public String getPositionName() {
        return (String)getAttributeInternal(POSITIONNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PositionName
     */
    public void setPositionName(String value) {
        setAttributeInternal(POSITIONNAME, value);
    }


    /**Gets the attribute value for the calculated attribute EvaluateJobLevel
     */
    public String getEvaluateJobLevel() {
        return (String)getAttributeInternal(EVALUATEJOBLEVEL);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EvaluateJobLevel
     */
    public void setEvaluateJobLevel(String value) {
        setAttributeInternal(EVALUATEJOBLEVEL, value);
    }

    /**Gets the attribute value for the calculated attribute HrsourceDate
     */
    public Date getHrsourceDate() {
        return (Date)getAttributeInternal(HRSOURCEDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HrsourceDate
     */
    public void setHrsourceDate(Date value) {
        setAttributeInternal(HRSOURCEDATE, value);
    }

    /**Gets the attribute value for the calculated attribute OrganizationId
     */
    public Number getOrganizationId() {
        return (Number)getAttributeInternal(ORGANIZATIONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrganizationId
     */
    public void setOrganizationId(Number value) {
        setAttributeInternal(ORGANIZATIONID, value);
    }

    /**Gets the attribute value for the calculated attribute JobId
     */
    public Number getJobId() {
        return (Number)getAttributeInternal(JOBID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute JobId
     */
    public void setJobId(Number value) {
        setAttributeInternal(JOBID, value);
    }

    /**Gets the attribute value for the calculated attribute HrsourceType
     */
    public String getHrsourceType() {
        return (String)getAttributeInternal(HRSOURCETYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HrsourceType
     */
    public void setHrsourceType(String value) {
        setAttributeInternal(HRSOURCETYPE, value);
    }

    /**Gets the attribute value for the calculated attribute CurrentYearGrade
     */
    public String getCurrentYearGrade() {
        return (String)getAttributeInternal(CURRENTYEARGRADE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CurrentYearGrade
     */
    public void setCurrentYearGrade(String value) {
        setAttributeInternal(CURRENTYEARGRADE, value);
    }

    /**Gets the attribute value for the calculated attribute LastYearGrade
     */
    public String getLastYearGrade() {
        return (String)getAttributeInternal(LASTYEARGRADE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastYearGrade
     */
    public void setLastYearGrade(String value) {
        setAttributeInternal(LASTYEARGRADE, value);
    }

    /**Gets the attribute value for the calculated attribute YearBeforeLastGrade
     */
    public String getYearBeforeLastGrade() {
        return (String)getAttributeInternal(YEARBEFORELASTGRADE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute YearBeforeLastGrade
     */
    public void setYearBeforeLastGrade(String value) {
        setAttributeInternal(YEARBEFORELASTGRADE, value);
    }

    /**Gets the attribute value for the calculated attribute DateOfBirth
     */
    public Date getDateOfBirth() {
        return (Date)getAttributeInternal(DATEOFBIRTH);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DateOfBirth
     */
    public void setDateOfBirth(Date value) {
        setAttributeInternal(DATEOFBIRTH, value);
    }

    /**Gets the attribute value for the calculated attribute Education
     */
    public String getEducation() {
        return (String)getAttributeInternal(EDUCATION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Education
     */
    public void setEducation(String value) {
        setAttributeInternal(EDUCATION, value);
    }

    /**Gets the attribute value for the calculated attribute College
     */
    public String getCollege() {
        return (String)getAttributeInternal(COLLEGE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute College
     */
    public void setCollege(String value) {
        setAttributeInternal(COLLEGE, value);
    }

    /**Gets the attribute value for the calculated attribute GraduationDate
     */
    public String getGraduationDate() {
        return (String)getAttributeInternal(GRADUATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute GraduationDate
     */
    public void setGraduationDate(String value) {
        setAttributeInternal(GRADUATIONDATE, value);
    }

    /**Gets the attribute value for the calculated attribute FirstWorkingTime
     */
    public String getFirstWorkingTime() {
        return (String)getAttributeInternal(FIRSTWORKINGTIME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FirstWorkingTime
     */
    public void setFirstWorkingTime(String value) {
        setAttributeInternal(FIRSTWORKINGTIME, value);
    }

    /**Gets the attribute value for the calculated attribute Zc
     */
    public String getZc() {
        return (String)getAttributeInternal(ZC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Zc
     */
    public void setZc(String value) {
        setAttributeInternal(ZC, value);
    }

    /**Gets the attribute value for the calculated attribute Prrq
     */
    public Date getPrrq() {
        return (Date)getAttributeInternal(PRRQ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Prrq
     */
    public void setPrrq(Date value) {
        setAttributeInternal(PRRQ, value);
    }

    /**Gets the attribute value for the calculated attribute Zyfzr
     */
    public String getZyfzr() {
        return (String)getAttributeInternal(ZYFZR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Zyfzr
     */
    public void setZyfzr(String value) {
        setAttributeInternal(ZYFZR, value);
    }

    /**Gets the attribute value for the calculated attribute Ztr
     */
    public String getZtr() {
        return (String)getAttributeInternal(ZTR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Ztr
     */
    public void setZtr(String value) {
        setAttributeInternal(ZTR, value);
    }

    /**Gets the attribute value for the calculated attribute Sjr
     */
    public String getSjr() {
        return (String)getAttributeInternal(SJR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Sjr
     */
    public void setSjr(String value) {
        setAttributeInternal(SJR, value);
    }

    /**Gets the attribute value for the calculated attribute Jhr
     */
    public String getJhr() {
        return (String)getAttributeInternal(JHR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Jhr
     */
    public void setJhr(String value) {
        setAttributeInternal(JHR, value);
    }

    /**Gets the attribute value for the calculated attribute Shr
     */
    public String getShr() {
        return (String)getAttributeInternal(SHR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Shr
     */
    public void setShr(String value) {
        setAttributeInternal(SHR, value);
    }

    /**Gets the attribute value for the calculated attribute Sdr
     */
    public String getSdr() {
        return (String)getAttributeInternal(SDR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Sdr
     */
    public void setSdr(String value) {
        setAttributeInternal(SDR, value);
    }

    /**Gets the attribute value for the calculated attribute Ylgdzz
     */
    public String getYlgdzz() {
        return (String)getAttributeInternal(YLGDZZ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Ylgdzz
     */
    public void setYlgdzz(String value) {
        setAttributeInternal(YLGDZZ, value);
    }

    /**Gets the attribute value for the calculated attribute Zcly
     */
    public String getZcly() {
        return (String)getAttributeInternal(ZCLY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Zcly
     */
    public void setZcly(String value) {
        setAttributeInternal(ZCLY, value);
    }

    /**Gets the attribute value for the calculated attribute Zg
     */
    public String getZg() {
        return (String)getAttributeInternal(ZG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Zg
     */
    public void setZg(String value) {
        setAttributeInternal(ZG, value);
    }

    /**Gets the attribute value for the calculated attribute XmglGw
     */
    public String getXmglGw() {
        return (String)getAttributeInternal(XMGLGW);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute XmglGw
     */
    public void setXmglGw(String value) {
        setAttributeInternal(XMGLGW, value);
    }

    /**Gets the attribute value for the calculated attribute XmglZj
     */
    public String getXmglZj() {
        return (String)getAttributeInternal(XMGLZJ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute XmglZj
     */
    public void setXmglZj(String value) {
        setAttributeInternal(XMGLZJ, value);
    }

    /**Gets the attribute value for the calculated attribute ZzQz
     */
    public String getZzQz() {
        return (String)getAttributeInternal(ZZQZ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ZzQz
     */
    public void setZzQz(String value) {
        setAttributeInternal(ZZQZ, value);
    }

    /**Gets PaHrsourceEO entity object.
     */
    public PaHrsourceEOImpl getPaHrsourceEO() {
        return (PaHrsourceEOImpl)getEntity(0);
    }

    /**Gets the attribute value for the calculated attribute HrsourceId
     */
    public Number getHrsourceId() {
        return (Number)getAttributeInternal(HRSOURCEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HrsourceId
     */
    public void setHrsourceId(Number value) {
        setAttributeInternal(HRSOURCEID, value);
    }
}
