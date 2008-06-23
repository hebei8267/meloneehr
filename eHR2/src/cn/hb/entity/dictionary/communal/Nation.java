package cn.hb.entity.dictionary.communal;

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
 * 民族
 */
@Entity
@Table(name = "M_NATION")
@NamedQueries( { @NamedQuery(name = "Nation.getNationByID", query = "select obj from Nation obj where obj.id = ? "),
        @NamedQuery(name = "Nation.getMaxNationID", query = "select max(obj.id) from Nation obj "),
        @NamedQuery(name = "Nation.existLikenessNation", query = "select obj from Nation obj where obj.name = ? ") })
public class Nation extends AbstractEntityBean {

    private static final long serialVersionUID = -6836463854572247643L;

    public Nation() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String description;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "NATION_ID", nullable = false, length = 20)
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
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
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
     * @param description 详细描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Nation)) {
            return false;
        }
        Nation rhs = (Nation) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(1251339031, 1116860751).append(this.id).toHashCode();
    }

}
