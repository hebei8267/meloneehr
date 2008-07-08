package cn.hb.entity.hr.organization;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.organization.OrganizationType;
import cn.hb.entity.dictionary.communal.Country;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 组织
 */
@Entity
@Table(name = "W_ORGANIZATION")
public class Organization extends AbstractEntityBean {

    private static final long serialVersionUID = -2591815652312982010L;

    public Organization() {
    }

    /** 编号 */
    private String id;

    /** 设立时间 */
    private String startDate;

    /** 名称 */
    private String name;

    /** 地址 */
    private String address;

    /** 电话号码 */
    private String telephone;

    /** 传真号码 */
    private String fax;

    /** 组织详细描述 */
    private String orgNote;

    /** 撤销时间 */
    private String endDate;

    /** 撤销原因 */
    private String endNote;

    /** 组织类型ID */
    private String organizationTypeID;

    /** 组织类型 */
    private OrganizationType organizationType;

    /** 子组织 */
    private List<Organization> subOrganizationList = new ArrayList<Organization>();

    /** Index */
    private Integer index;

    /** 父组织 */
    private String parentOrganizationID;

    /** 父组织 */
    private Organization parentOrganization;

    /** 国家ID */
    private String localCountryID;

    /** 国家 */
    private Country localCountry;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "ORGANIZATION_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得设立时间
     * 
     * @return 设立时间
     */
    @NaturalId
    @Column(name = "START_DATE", nullable = false, length = 8)
    public String getStartDate() {
        return startDate;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    /**
     * 取得地址
     * 
     * @return 地址
     */
    @Basic
    @Column(name = "ADDRESS", nullable = false, length = 40)
    public String getAddress() {
        return address;
    }

    /**
     * 取得电话号码
     * 
     * @return 电话号码
     */
    @Basic
    @Column(name = "TELEPHONE_NUM", nullable = false, length = 20)
    public String getTelephone() {
        return telephone;
    }

    /**
     * 取得传真号码
     * 
     * @return 传真号码
     */
    @Basic
    @Column(name = "FAX_NUM", length = 20)
    public String getFax() {
        return fax;
    }

    /**
     * 取得组织详细描述
     * 
     * @return 组织详细描述
     */
    @Basic
    @Column(name = "NOTE")
    public String getOrgNote() {
        return orgNote;
    }

    /**
     * 取得撤销时间
     * 
     * @return 撤销时间
     */
    @Basic
    @Column(name = "END_DATE", nullable = false, length = 8)
    public String getEndDate() {
        return endDate;
    }

    /**
     * 取得撤销原因
     * 
     * @return 撤销原因
     */
    @Basic
    @Column(name = "END_NOTE")
    public String getEndNote() {
        return endNote;
    }

    /**
     * 取得组织类型ID
     * 
     * @return 组织类型ID
     */
    @Basic
    @Column(name = "ORGANIZATION_TYPE_ID", nullable = false, length = 20)
    public String getOrganizationTypeID() {
        return organizationTypeID;
    }

    /**
     * 取得组织类型
     * 
     * @return 组织类型
     */
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_TYPE_H_ID")
    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    /**
     * 取得子组织
     * 
     * @return 子组织
     */
    @OneToMany(mappedBy = "parentOrganization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    public List<Organization> getSubOrganizationList() {
        return subOrganizationList;
    }

    /**
     * 取得Index
     * 
     * @return Index
     */
    @Basic
    @Column(name = "_INDEX")
    public Integer getIndex() {
        return index;
    }

    /**
     * 取得父组织
     * 
     * @return 父组织
     */
    @Basic
    @Column(name = "PARENT_ORGANIZATION_ID", length = 20)
    public String getParentOrganizationID() {
        return parentOrganizationID;
    }

    /**
     * 取得父组织
     * 
     * @return 父组织
     */
    @ManyToOne
    @JoinColumn(name = "PARENT_ORGANIZATION_H_ID")
    public Organization getParentOrganization() {
        return parentOrganization;
    }

    /**
     * 取得国家ID
     * 
     * @return 国家ID
     */
    @Basic
    @Column(name = "LOCAL_COUNTRY_ID", nullable = false, length = 20)
    public String getLocalCountryID() {
        return localCountryID;
    }

    /**
     * 取得国家
     * 
     * @return 国家
     */
    @ManyToOne
    @JoinColumn(name = "LOCAL_COUNTRY_H_ID")
    public Country getLocalCountry() {
        return localCountry;
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
     * 设置设立时间
     * 
     * @param startDate 设立时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
     * 设置地址
     * 
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
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
     * 设置传真号码
     * 
     * @param fax 传真号码
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 设置组织详细描述
     * 
     * @param orgNote 组织详细描述
     */
    public void setOrgNote(String orgNote) {
        this.orgNote = orgNote;
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
     * 设置撤销原因
     * 
     * @param endNote 撤销原因
     */
    public void setEndNote(String endNote) {
        this.endNote = endNote;
    }

    /**
     * 设置组织类型ID
     * 
     * @param organizationTypeID 组织类型ID
     */
    protected void setOrganizationTypeID(String organizationTypeID) {
        this.organizationTypeID = organizationTypeID;
    }

    /**
     * 设置组织类型
     * 
     * @param organizationType 组织类型
     */
    public void setOrganizationType(OrganizationType organizationType) {
        if (organizationType != null) {
            this.organizationTypeID = organizationType.getId();
        }
        this.organizationType = organizationType;
    }

    /**
     * 设置子组织
     * 
     * @param subOrganizationList 子组织
     */
    public void setSubOrganizationList(List<Organization> subOrganizationList) {
        this.subOrganizationList = subOrganizationList;
    }

    public void addSubOrganization(Organization organization) {
        int index = 1;
        for (Organization _organization : subOrganizationList) {
            if (_organization.getIndex() != null) {
                _organization.setIndex(index);
                index++;
            }
        }

        organization.setIndex(index);
        this.subOrganizationList.add(organization);
    }

    public void removeSubNativeplace(Organization organization) {
        if (!subOrganizationList.contains(organization)) {
            return;
        }
        subOrganizationList.remove(organization);

        int index = 1;
        for (Organization _organization : subOrganizationList) {
            if (_organization.getIndex() != null) {
                _organization.setIndex(index);
                index++;
            }
        }
    }

    /**
     * 设置Index
     * 
     * @param index Index
     */
    protected void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 设置父组织
     * 
     * @param parentOrganizationID 父组织
     */
    protected void setParentOrganizationID(String parentOrganizationID) {
        this.parentOrganizationID = parentOrganizationID;
    }

    /**
     * 设置父组织
     * 
     * @param parentOrganization 父组织
     */
    public void setParentOrganization(Organization parentOrganization) {
        if (parentOrganization != null) {
            this.parentOrganizationID = parentOrganization.getId();
        }
        this.parentOrganization = parentOrganization;
    }

    /**
     * 设置国家ID
     * 
     * @param localCountryID 国家ID
     */
    protected void setLocalCountryID(String localCountryID) {
        this.localCountryID = localCountryID;
    }

    /**
     * 设置国家
     * 
     * @param localCountry 国家
     */
    public void setLocalCountry(Country localCountry) {
        if (localCountry != null) {
            this.localCountryID = localCountry.getId();
        }
        this.localCountry = localCountry;
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
