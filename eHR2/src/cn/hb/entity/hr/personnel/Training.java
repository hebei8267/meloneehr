package cn.hb.entity.hr.personnel;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 培训信息
 */
@Entity
@Table(name = "W_P_TRAINING")
public class Training extends AbstractEntityBean {

    private static final long serialVersionUID = -3136939615537738139L;

    public Training() {
    }

    /** 编号 */
    private String id;

    /** 课程名称 */
    private String trainingName;

    /** 开始日期 */
    private String startDate;

    /** 结束时间 */
    private String endDate;

    /** 培训机构名称 */
    private String trainingOrgName;

    /** 培训地点 */
    private String trainingSite;

    /** 详细描述 */
    private String note;

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
    @Column(name = "TRAINING_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得课程名称
     * 
     * @return 课程名称
     */
    @Basic
    @Column(name = "TRAINING_NAME", nullable = false, length = 40)
    public String getTrainingName() {
        return trainingName;
    }

    /**
     * 取得开始日期
     * 
     * @return 开始日期
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
     * 取得培训地点
     * 
     * @return 培训地点
     */
    @Basic
    @Column(name = "TRAINING_SITE", nullable = false, length = 40)
    public String getTrainingSite() {
        return trainingSite;
    }

    /**
     * 取得培训机构名称
     * 
     * @return 培训机构名称
     */
    @Basic
    @Column(name = "TRAINING_ORG_NAME", nullable = false, length = 40)
    public String getTrainingOrgName() {
        return trainingOrgName;
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
     * 取得个人基本信息
     * 
     * @return 个人基本信息
     */
    @ManyToOne(cascade = CascadeType.ALL)
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
     * 设置课程名称
     * 
     * @param trainingName 课程名称
     */
    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    /**
     * 设置开始日期
     * 
     * @param startDate 开始日期
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
     * 设置培训地点
     * 
     * @param trainingSite 培训地点
     */
    public void setTrainingSite(String trainingSite) {
        this.trainingSite = trainingSite;
    }

    /**
     * 设置培训机构名称
     * 
     * @param trainingSite 培训机构名称
     */
    public void setTrainingOrgName(String trainingOrgName) {
        this.trainingOrgName = trainingOrgName;
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
        if (!(object instanceof Training)) {
            return false;
        }
        Training rhs = (Training) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-2032745193, 933879965).append(this.id).toHashCode();
    }

}
