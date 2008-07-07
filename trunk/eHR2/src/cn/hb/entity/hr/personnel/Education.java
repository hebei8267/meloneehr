package cn.hb.entity.hr.personnel;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.personnel.EducateSpecialty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 教育信息
 */
@Entity
@Table(name = "W_P_EDUCATION")
public class Education extends AbstractEntityBean {

    private static final long serialVersionUID = 1680591870755944882L;

    public Education() {
    }

    /** 编号 */
    private String id;

    /** 开始时间 */
    private String startDate;

    /** 结束时间 */
    private String endDate;

    /** 学校名称 */
    private String schoolName;

    /** 详细描述 */
    private String note;

    /** 教育专业ID */
    private String educateSpecialtyID;

    /** 教育专业 */
    private EducateSpecialty educateSpecialty;

    /** Index */
    private Integer index;

    /** 个人基本信息ID */
    private String personID;

    /** 个人基本信息 */
    private Person person;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "EDUCATION_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得开始时间
     * 
     * @return 开始时间
     */
    @Basic
    @Column(name = "START_DATE", nullable = false, length = 8)
    public String getStartDate() {
        return startDate;
    }

    /**
     * 取得结束时间
     * 
     * @return 结束时间
     */
    @Basic
    @Column(name = "END_DATE", nullable = false, length = 8)
    public String getEndDate() {
        return endDate;
    }

    /**
     * 取得学校名称
     * 
     * @return 学校名称
     */
    @Basic
    @Column(name = "SCHOOL_NAME", nullable = false, length = 40)
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 取得详细描述
     * 
     * @return 详细描述
     */
    @Basic
    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    /**
     * 取得教育专业
     * 
     * @return 教育专业
     */
    @ManyToOne
    @JoinColumn(name = "EDUCATE_SPECIALTY_H_ID")
    public EducateSpecialty getEducateSpecialty() {
        return educateSpecialty;
    }

    @Basic
    @Column(name = "EDUCATE_SPECIALTY_ID", nullable = false, length = 20)
    public String getEducateSpecialtyID() {
        return educateSpecialtyID;
    }

    /**
     * 取得个人基本信息
     * 
     * @return 个人基本信息
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_H_ID")
    public Person getPerson() {
        return person;
    }

    @Basic
    @Column(name = "PERSON_ID", nullable = false, length = 20)
    public String getPersonID() {
        return personID;
    }

    @Basic
    @Column(name = "_INDEX")
    public Integer getIndex() {
        return index;
    }

    /**
     * 设置编号
     * 
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 设置开始时间
     * 
     * @param startDate 开始时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 设置结束时间
     * 
     * @param endDate 结束时间
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 设置学校名称
     * 
     * @param name 学校名称
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 设置详细描述
     * 
     * @param note 详细描述
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 设置教育专业
     * 
     * @param EducateSpecialty 教育专业
     */
    public void setEducateSpecialty(EducateSpecialty educateSpecialty) {
        if (educateSpecialty != null) {
            this.educateSpecialtyID = educateSpecialty.getId();
        }
        this.educateSpecialty = educateSpecialty;
    }

    protected void setEducateSpecialtyID(String educateSpecialtyID) {
        this.educateSpecialtyID = educateSpecialtyID;
    }

    /**
     * 设置个人基本信息
     * 
     * @param Person 个人基本信息
     */
    public void setPerson(Person person) {
        if (person != null) {
            this.personID = person.getId();
        }
        this.person = person;
    }

    protected void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Education)) {
            return false;
        }
        Education rhs = (Education) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-925508009, -1426887159).append(this.id).toHashCode();
    }
}
