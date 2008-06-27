package cn.hb.entity.dictionary.financial;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

import cn.hb.core.bean.AbstractEntityBean;

/**
 * @author kaka
 * 
 * 薪水类型
 */
@Entity
@Table(name = "M_SALARY_TYPE")
@NamedQueries( {
        @NamedQuery(name = "SalaryType.getSalaryTypeByID", query = "select obj from SalaryType obj where obj.id = ? "),
        @NamedQuery(name = "SalaryType.getMaxSalaryTypeID", query = "select max(obj.id) from SalaryType obj "),
        @NamedQuery(name = "SalaryType.existLikenessSalaryType", query = "select obj from SalaryType obj where obj.name = ? ") })
public class SalaryType extends AbstractEntityBean {

    private static final long serialVersionUID = 1611734392235381211L;

    public SalaryType() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String note;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "WELFARE_TYPE_ID", nullable = false, length = 20)
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

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SalaryType)) {
            return false;
        }
        SalaryType rhs = (SalaryType) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(1405878799, 1537634053).append(this.id).toHashCode();
    }

}
