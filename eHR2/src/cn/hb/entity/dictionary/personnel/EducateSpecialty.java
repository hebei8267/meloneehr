package cn.hb.entity.dictionary.personnel;

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

import cn.hb.core.bean.AbstractEntityBean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 教育专业
 */
@Entity
@Table(name = "M_EDUCATE_SPECIALTY")
@NamedQueries( {
        @NamedQuery(name = "EducateSpecialty.getEducateSpecialtyByID", query = "select obj from EducateSpecialty obj where obj.id = ? "),
        @NamedQuery(name = "EducateSpecialty.getMaxEducateSpecialtyID", query = "select max(obj.id) from EducateSpecialty obj "),
        @NamedQuery(name = "EducateSpecialty.existLikenessEducateSpecialty", query = "select obj from EducateSpecialty obj where obj.name = ? ") })
public class EducateSpecialty extends AbstractEntityBean {

    private static final long serialVersionUID = 6593546269308271078L;

    public EducateSpecialty() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String note;

    /** Index */
    private Integer index;

    /** 子教育专业 */
    private List<EducateSpecialty> subEducateSpecialtyList = new ArrayList<EducateSpecialty>();

    /** 父教育专业 */
    private EducateSpecialty parentEducateSpecialty;

    /** 父教育专业 ID */
    private String parentEducateSpecialtyID;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "EDUCATE_SPECIALTY_ID", nullable = false, length = 20)
    public String getId() {
        return id;
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
     * 取得详细描述
     * 
     * @return 详细描述
     */
    @Basic
    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    @Basic
    @Column(name = "_INDEX")
    public Integer getIndex() {
        return index;
    }

    @OneToMany(mappedBy = "parentEducateSpecialty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    public List<EducateSpecialty> getSubEducateSpecialtyList() {
        return subEducateSpecialtyList;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_EDUCATE_SPECIALTY_H_ID")
    public EducateSpecialty getParentEducateSpecialty() {
        return parentEducateSpecialty;
    }

    @Basic
    @Column(name = "PARENT_EDUCATE_SPECIALTY_ID", length = 20)
    public String getParentEducateSpecialtyID() {
        return parentEducateSpecialtyID;
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
     * 设置名称
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置详细描述
     * 
     * @param note 详细描述
     */
    public void setNote(String note) {
        this.note = note;
    }

    protected void setIndex(Integer index) {
        this.index = index;
    }

    public void setSubEducateSpecialtyList(List<EducateSpecialty> subEducateSpecialtyList) {
        this.subEducateSpecialtyList = subEducateSpecialtyList;
    }

    public void setParentEducateSpecialty(EducateSpecialty parentEducateSpecialty) {
        if (parentEducateSpecialty != null) {
            this.parentEducateSpecialtyID = parentEducateSpecialty.getId();
        }
        this.parentEducateSpecialty = parentEducateSpecialty;
    }

    protected void setParentEducateSpecialtyID(String parentEducateSpecialtyID) {
        this.parentEducateSpecialtyID = parentEducateSpecialtyID;
    }

    public void addSubEducateSpecialty(EducateSpecialty educateSpecialty) {
        int index = 1;
        for (EducateSpecialty _educateSpecialty : subEducateSpecialtyList) {
            if (_educateSpecialty.getIndex() != null) {
                _educateSpecialty.setIndex(index);
                index++;
            }
        }

        educateSpecialty.setIndex(index);
        this.subEducateSpecialtyList.add(educateSpecialty);
    }

    public void removeSubEducateSpecialty(EducateSpecialty educateSpecialty) {
        if (!subEducateSpecialtyList.contains(educateSpecialty)) {
            return;
        }
        subEducateSpecialtyList.remove(educateSpecialty);

        int index = 1;
        for (EducateSpecialty _educateSpecialty : subEducateSpecialtyList) {
            if (_educateSpecialty.getIndex() != null) {
                _educateSpecialty.setIndex(index);
                index++;
            }
        }
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EducateSpecialty)) {
            return false;
        }
        EducateSpecialty rhs = (EducateSpecialty) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(902282959, -884604413).append(this.id).toHashCode();
    }

}
