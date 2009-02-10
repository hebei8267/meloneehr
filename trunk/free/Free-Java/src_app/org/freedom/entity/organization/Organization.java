/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.entity.organization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.freedom.core.entity.AbstractEntityBean;
import org.freedom.entity.dictionary.common.Country;
import org.freedom.entity.dictionary.organization.OrganizationType;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

/**
 * 组织
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Entity
@Table(name = "M_ORGANIZATION")
@NamedQueries( {})
public class Organization extends AbstractEntityBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3320276293615438515L;
    /** 编号 */
    @NaturalId
    @Column(name = "ORGANIZATION_ID", nullable = false, length = 20)
    private String id;

    /** 设立时间 */
    @NaturalId
    @Column(name = "START_DATE", nullable = false, length = 8)
    private String startDate;

    /** 名称 */
    @Basic
    @Column(name = "ORGANIZATION_NAME", nullable = false, length = 20)
    private String name;

    /** 地址 */
    @Basic
    @Column(name = "ADDRESS")
    private String address;

    /** 电话号码 */
    @Basic
    @Column(name = "TELEPHONE", length = 20)
    private String telephone;

    /** 传真号码 */
    @Basic
    @Column(name = "FAX", length = 20)
    private String fax;

    /** 组织详细描述 */
    @Basic
    @Column(name = "DESCRIPTION")
    private String detail;

    /** 撤销时间 */
    @Basic
    @Column(name = "END_DATE", nullable = false, length = 8)
    private String endDate;

    /** 撤销原因 */
    @Basic
    @Column(name = "END_NOTE")
    private String endNote;

    /** 组织类型ID */
    @Basic
    @Column(name = "ORGANIZATION_TYPE_ID", length = 20)
    private String organizationTypeID;

    /** 组织类型 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGANIZATION_TYPE_H_ID")
    private OrganizationType organizationType;

    /** 子组织 */
    @OneToMany(mappedBy = "parentOrganization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    @OrderBy("index")
    private List<Organization> childOrganizationList = new ArrayList<Organization>();

    /** Index */
    @Basic
    @Column(name = "_INDEX")
    private Integer index;

    /** 父组织ID */
    @Basic
    @Column(name = "PARENT_ORGANIZATION_ID", length = 20)
    private String parentOrganizationID;

    /** 父组织 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ORGANIZATION_H_ID")
    private Organization parentOrganization;

    /** 国家ID */
    @Basic
    @Column(name = "LOCAL_COUNTRY_ID", length = 20)
    private String localCountryID;

    /** 国家 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCAL_COUNTRY_H_ID")
    private Country localCountry;

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
     * 取得设立时间
     * 
     * @return 设立时间
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置设立时间
     * 
     * @param startDate 设立时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
     * 取得地址
     * 
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     * 
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 取得电话号码
     * 
     * @return 电话号码
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话号码
     * 
     * @param telephone 电话号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 取得传真号码
     * 
     * @return 传真号码
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真号码
     * 
     * @param fax 传真号码
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 取得组织详细描述
     * 
     * @return 组织详细描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置组织详细描述
     * 
     * @param detail 组织详细描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 取得撤销时间
     * 
     * @return 撤销时间
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置撤销时间
     * 
     * @param endDate 撤销时间
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得撤销原因
     * 
     * @return 撤销原因
     */
    public String getEndNote() {
        return endNote;
    }

    /**
     * 设置撤销原因
     * 
     * @param endNote 撤销原因
     */
    public void setEndNote(String endNote) {
        this.endNote = endNote;
    }

    /**
     * 取得组织类型ID
     * 
     * @return 组织类型ID
     */
    public String getOrganizationTypeID() {
        return organizationTypeID;
    }

    /**
     * 设置组织类型ID
     * 
     * @param organizationTypeID 组织类型ID
     */
    public void setOrganizationTypeID(String organizationTypeID) {
        this.organizationTypeID = organizationTypeID;
    }

    /**
     * 取得组织类型
     * 
     * @return 组织类型
     */
    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    /**
     * 设置组织类型
     * 
     * @param organizationType 组织类型
     */
    public void setOrganizationType(OrganizationType organizationType) {
        this.organizationType = organizationType;
    }

    /**
     * 取得子组织
     * 
     * @return 子组织
     */
    public List<Organization> getChildOrganizationList() {
        return childOrganizationList;
    }

    /**
     * 设置子组织
     * 
     * @param childOrganizationList 子组织
     */
    public void setChildOrganizationList(List<Organization> childOrganizationList) {
        this.childOrganizationList = childOrganizationList;
    }

    /**
     * 取得Index
     * 
     * @return Index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 设置Index
     * 
     * @param index Index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 取得父组织ID
     * 
     * @return 父组织ID
     */
    public String getParentOrganizationID() {
        return parentOrganizationID;
    }

    /**
     * 设置父组织ID
     * 
     * @param parentOrganizationID 父组织ID
     */
    public void setParentOrganizationID(String parentOrganizationID) {
        this.parentOrganizationID = parentOrganizationID;
    }

    /**
     * 取得父组织
     * 
     * @return 父组织
     */
    public Organization getParentOrganization() {
        return parentOrganization;
    }

    /**
     * 设置父组织
     * 
     * @param parentOrganization 父组织
     */
    public void setParentOrganization(Organization parentOrganization) {
        this.parentOrganization = parentOrganization;
    }

    /**
     * 取得国家ID
     * 
     * @return 国家ID
     */
    public String getLocalCountryID() {
        return localCountryID;
    }

    /**
     * 设置国家ID
     * 
     * @param localCountryID 国家ID
     */
    public void setLocalCountryID(String localCountryID) {
        this.localCountryID = localCountryID;
    }

    /**
     * 取得国家
     * 
     * @return 国家
     */
    public Country getLocalCountry() {
        return localCountry;
    }

    /**
     * 设置国家
     * 
     * @param localCountry 国家
     */
    public void setLocalCountry(Country localCountry) {
        this.localCountry = localCountry;
    }

    /**
     * 添加子组织
     * 
     * @param organization 子组织
     */
    public void addChildOrganization(Organization organization) {
        // 向列表尾部添加
        if (organization.getIndex() == null || organization.getIndex() == 0
                || organization.getIndex() > childOrganizationList.size()) {
            this.childOrganizationList.add(organization);
        } else {// 像列表中间插于
            this.childOrganizationList.add(organization.getIndex() - 1, organization);
        }

        int index = 1;
        if (childOrganizationList == null) {
            return;
        }
        for (Organization _organization : childOrganizationList) {
            if (_organization != null) {
                _organization.setIndex(index);
                index++;
            }
        }

    }

    /**
     * 删除子组织
     * 
     * @param menuNode 子组织
     */
    public void removeChildOrganization(Organization organization) {
        int index = 1;

        Organization _delOrganization = null;
        for (Iterator<Organization> iterator = childOrganizationList.iterator(); iterator.hasNext();) {
            Organization _organization = iterator.next();
            if (_organization.equals(organization)) {
                _delOrganization = _organization;
                continue;
            }
            if (_organization != null) {
                _organization.setIndex(index);
                index++;
            }

        }
        if (_delOrganization != null) {
            childOrganizationList.remove(_delOrganization);
        }

    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization rhs = (Organization) object;
        return new EqualsBuilder().append(this.startDate, rhs.startDate).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-121840015, 1103340929).append(this.startDate).append(this.id).toHashCode();
    }
}
