package cn.hb.entity.dictionary.organization;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

import cn.hb.core.bean.AbstractEntityBean;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * @author kaka
 * 
 * 职种(职务类型)
 */
@Entity
@Table(name = "M_JOB_POSITION_TYPE")
@NamedQueries( {
        @NamedQuery(name = "JobPositionType.getJobPositionTypeByID", query = "select obj from JobPositionType obj where obj.id = ? "),
        @NamedQuery(name = "JobPositionType.getMaxJobPositionTypeID", query = "select max(obj.id) from JobPositionType obj "),
        @NamedQuery(name = "JobPositionType.existLikenessJobPositionType", query = "select obj from JobPositionType obj where obj.name = ? ") })
public class JobPositionType extends AbstractEntityBean {

    private static final long serialVersionUID = 4944315812452496545L;

    public JobPositionType() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String note;

    /** Index */
    private Integer index;

    /** 子职种(职务类型) */
    private List<JobPositionType> subJobPositionTypeList = new ArrayList<JobPositionType>();

    /** 父职种(职务类型) */
    private JobPositionType parentJobPositionType;

    /** 父职种(职务类型) ID */
    private String parentJobPositionTypeID;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "JOB_POSITION_TYPE_ID", nullable = false, length = 20)
    public String getId() {
        return id;
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
     * 取得名称
     * 
     * @return 名称
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 20, unique = true)
    public String getName() {
        return name;
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
     * 设置详细描述
     * 
     * @param note 详细描述
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 设置名称
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-666804719, -1693772375).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof JobPositionType)) {
            return false;
        }
        JobPositionType rhs = (JobPositionType) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    public void addSubJobPositionType(JobPositionType jobPositionType) {
        int index = 1;
        for (JobPositionType _jobPositionType : subJobPositionTypeList) {
            if (_jobPositionType.getIndex() != null) {
                _jobPositionType.setIndex(index);
                index++;
            }
        }

        jobPositionType.setIndex(index);
        this.subJobPositionTypeList.add(jobPositionType);
    }

    public void removeSubJobPositionType(JobPositionType jobPositionType) {
        if (!subJobPositionTypeList.contains(jobPositionType)) {
            return;
        }
        subJobPositionTypeList.remove(jobPositionType);

        int index = 1;
        for (JobPositionType _jobPositionType : subJobPositionTypeList) {
            if (_jobPositionType.getIndex() != null) {
                _jobPositionType.setIndex(index);
                index++;
            }
        }
    }

    @Basic
    @Column(name = "_INDEX")
    public Integer getIndex() {
        return index;
    }

    @OneToMany(mappedBy = "parentJobPositionType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    public List<JobPositionType> getSubJobPositionTypeList() {
        return subJobPositionTypeList;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_JOB_POSITION_TYPE_H_ID")
    public JobPositionType getParentJobPositionType() {
        return parentJobPositionType;
    }

    @Basic
    @Column(name = "PARENT_JOB_POSITION_TYPE_ID", length = 20)
    public String getParentJobPositionTypeID() {
        return parentJobPositionTypeID;
    }

    protected void setIndex(Integer index) {
        this.index = index;
    }

    public void setSubJobPositionTypeList(List<JobPositionType> subJobPositionTypeList) {
        this.subJobPositionTypeList = subJobPositionTypeList;
    }

    public void setParentJobPositionType(JobPositionType parentJobPositionType) {
        if (parentJobPositionType != null) {
            this.parentJobPositionTypeID = parentJobPositionType.getId();
        }

        this.parentJobPositionType = parentJobPositionType;
    }

    protected void setParentJobPositionTypeID(String parentJobPositionTypeID) {
        this.parentJobPositionTypeID = parentJobPositionTypeID;
    }

}
