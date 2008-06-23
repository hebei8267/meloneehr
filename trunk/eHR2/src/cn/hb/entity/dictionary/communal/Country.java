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
 * 国家
 */
@Entity
@Table(name = "M_COUNTRY")
@NamedQueries( {
        @NamedQuery(name = "Country.getCountryByID", query = "select obj from Country obj where obj.id = ? "),
        @NamedQuery(name = "Country.getMaxCountryID", query = "select max(obj.id) from Country obj "),
        @NamedQuery(name = "Country.existLikenessCountry", query = "select obj from Country obj where obj.name = ? or obj.shortName = ? ") })
public class Country extends AbstractEntityBean {

    private static final long serialVersionUID = -8552348640289521607L;

    public Country() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 简称 */
    private String shortName;

    /** 详细描述 */
    private String description;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "COUNTRY_ID", nullable = false, length = 20)
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
     * 取得简称
     * 
     * @return 简称
     */
    @Basic
    @Column(name = "SHORT_NAME", length = 20, unique = true)
    public String getShortName() {
        return shortName;
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
     * 设置简称
     * 
     * @param shortName 简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
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
        if (!(object instanceof Country)) {
            return false;
        }
        Country rhs = (Country) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(661786397, 2037344837).append(this.id).toHashCode();
    }

}
