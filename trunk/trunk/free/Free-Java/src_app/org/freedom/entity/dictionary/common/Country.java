/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.entity.dictionary.common;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.freedom.core.entity.AbstractEntityBean;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 国家
 */
@Entity
@Table(name = "M_COUNTRY")
@NamedQueries( { @NamedQuery(name = "Country.getCountryByID", query = "select obj from Country obj where obj.id = ? ") })
public class Country extends AbstractEntityBean {

    private static final long serialVersionUID = -8552348640289521607L;

    public Country() {
    }

    /** 编号 */
    @NaturalId
    @Column(name = "COUNTRY_ID", nullable = false, length = 20)
    private String id;

    /** 名称 */
    @Basic
    @Column(name = "COUNTRY_NAME", nullable = false, length = 20)
    private String name;

    /** 描述 */
    @Basic
    @Column(name = "DESCRIPTION")
    private String detail;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    public String getId() {
        return id;
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
     * 取得名称
     * 
     * @return 名称
     */
    public String getName() {
        return name;
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
     * 取得描述
     * 
     * @return 描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置描述
     * 
     * @param detail 描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
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
