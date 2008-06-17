package cn.hb.entity.hr.organization;

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
 * 组织类型
 */
@Entity
@Table(name = "M_ORGANIZATION_TYPE")
@NamedQueries( {
        @NamedQuery(name = "OrganizationType.getOrganizationTypeByID", query = "select obj from OrganizationType obj where obj.id = ? "),
        @NamedQuery(name = "OrganizationType.getMaxOrganizationTypeID", query = "select max(obj.id) from OrganizationType obj "),
        @NamedQuery(name = "OrganizationType.existLikenessOrganizationType", query = "select obj from OrganizationType obj where obj.name = ? ") })
public class OrganizationType extends AbstractEntityBean {

    private static final long serialVersionUID = -4437854004187438527L;

    public OrganizationType() {
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
    @Column(name = "ORGANIZATION_TYPE_ID", nullable = false, length = 20)
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
        if (!(object instanceof OrganizationType)) {
            return false;
        }
        OrganizationType rhs = (OrganizationType) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(1517694915, 1431219095).append(this.id).toHashCode();
    }

}
