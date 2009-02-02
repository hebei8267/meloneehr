/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.entity.dictionary.organization;

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
 * 组织类型
 */
@Entity
@Table(name = "M_ORGANIZATION_TYPE")
@NamedQueries( {
        @NamedQuery(name = "OrganizationType.getOrganizationTypeByID", query = "select obj from OrganizationType obj where obj.id = ? "),
        @NamedQuery(name = "OrganizationType.getMaxID", query = "select max(obj.id) from OrganizationType obj ") })
public class OrganizationType extends AbstractEntityBean {

    private static final long serialVersionUID = -4437854004187438527L;

    public OrganizationType() {
    }

    /** 编号 */
    @NaturalId
    @Column(name = "ORGANIZATION_TYPE_ID", nullable = false, length = 20)
    private String id;

    /** 名称 */
    @Basic
    @Column(name = "ORGANIZATION_TYPE_NAME", nullable = false, length = 20)
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
