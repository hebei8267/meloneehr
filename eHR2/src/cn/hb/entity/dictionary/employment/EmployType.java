package cn.hb.entity.dictionary.employment;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 雇佣类型
 */
@Entity
@Table(name = "M_EMPLOY_TYPE")
@NamedQueries( {
        @NamedQuery(name = "EmployType.getEmployTypeByID", query = "select obj from EmployType obj where obj.id = ? "),
        @NamedQuery(name = "EmployType.getMaxEmployTypeID", query = "select max(obj.id) from EmployType obj "),
        @NamedQuery(name = "EmployType.existLikenessEmployType", query = "select obj from EmployType obj where obj.name = ? ") })
public class EmployType extends AbstractEntityBean {

    private static final long serialVersionUID = 5318966405963384522L;

    public EmployType() {
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
    @Column(name = "EMPLOY_TYPE_ID", nullable = false, length = 20)
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
        if (!(object instanceof EmployType)) {
            return false;
        }
        EmployType rhs = (EmployType) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(1151489505, -1467542999).append(this.id).toHashCode();
    }

}
