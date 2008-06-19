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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.common.Country;

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
@NamedQueries( {
        @NamedQuery(name = "Organization.getOrganizationByID", query = "select obj from Organization obj where obj.id = ? "),
        @NamedQuery(name = "Organization.getMaxOrganizationID", query = "select max(obj.id) from Organization obj "),
        @NamedQuery(name = "Organization.existLikenessOrganization", query = "select obj from Organization obj where obj.name = ? ") })
public class Organization extends AbstractEntityBean {

    private static final long serialVersionUID = -3636000320615650194L;

    public Organization() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 设立时间 */
    private String startDate;

    /** 简称 */
    private String shortName;

    /** 地址 */
    private String address;

    /** 电话号码 */
    private String telephone;

    /** 传真号码 */
    private String fax;

    /** 详细描述 */
    private String description;

    /** 撤销时间 */
    private String endDate;

    /** 撤销原因 */
    private String endDesc;

    /** 组织类型 */
    private OrganizationType OrganizationType;

    /** 组织类型ID */
    private String organizationTypeID;

    /** 父组织 */
    private Organization parentOrganization;

    /** 父组织ID */
    private String parentOrganizationID;

    /** Index */
    private Integer index;

    /** 子组织 */
    private List<Organization> subOrganizationList = new ArrayList<Organization>();

    /** 地址所在地-国家 */
    private Country country;

    /** 地址所在地-国家ID */
    private String countryID;

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
    @Basic
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
     * 取得简称
     * 
     * @return 简称
     */
    @Basic
    @Column(name = "SHORT_NAME", length = 20)
    public String getShortName() {
        return shortName;
    }

    /**
     * 取得地址
     * 
     * @return 地址
     */
    @Basic
    @Column(name = "ADDRESS", length = 50)
    public String getAddress() {
        return address;
    }

    /**
     * 取得电话号码
     * 
     * @return 电话号码
     */
    @Basic
    @Column(name = "TELEPHONE", length = 20)
    public String getTelephone() {
        return telephone;
    }

    /**
     * 取得传真号码
     * 
     * @return 传真号码
     */
    @Basic
    @Column(name = "FAX", length = 20)
    public String getFax() {
        return fax;
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
    @Column(name = "END_DESCRIPTION")
    public String getEndDesc() {
        return endDesc;
    }

    /**
     * 取得组织类型
     * 
     * @return 组织类型
     */
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_TYPE_H_ID")
    public OrganizationType getOrganizationType() {
        return OrganizationType;
    }

    /**
     * 取得组织类型ID
     * 
     * @return 组织类型ID
     */
    @Basic
    @Column(name = "ORGANIZATION_TYPE_ID", length = 20)
    public String getOrganizationTypeID() {
        return organizationTypeID;
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
     * 取得父组织ID
     * 
     * @return 父组织ID
     */
    @Basic
    @Column(name = "PARENT_ORGANIZATION_ID", length = 20)
    public String getParentOrganizationID() {
        return parentOrganizationID;
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
     * 取得地址所在地-国家
     * 
     * @return 地址所在地-国家
     */
    @ManyToOne
    @JoinColumn(name = "COUNTRY_H_ID")
    public Country getCountry() {
        return country;
    }

    /**
     * 取得地址所在地-国家ID
     * 
     * @return 地址所在地-国家ID
     */
    @Basic
    @Column(name = "COUNTRY_ID", length = 20)
    public String getCountryID() {
        return countryID;
    }

    @Basic
    @Column(name = "_INDEX")
    public Integer getIndex() {
        return index;
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
     * 设置简称
     * 
     * @param shortName 简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
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
     * 设置详细描述
     * 
     * @param description 详细描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @param endDesc 撤销原因
     */
    public void setEndDesc(String endDesc) {
        this.endDesc = endDesc;
    }

    public void setOrganizationType(OrganizationType organizationType) {
        if (organizationType != null) {
            this.organizationTypeID = organizationType.getId();
        }
        this.OrganizationType = organizationType;
    }

    protected void setOrganizationTypeID(String organizationTypeID) {
        this.organizationTypeID = organizationTypeID;
    }

    public void setParentOrganization(Organization parentOrganization) {
        if (parentOrganization != null) {
            this.parentOrganizationID = parentOrganization.getId();
        }

        this.parentOrganization = parentOrganization;
    }

    protected void setParentOrganizationID(String parentOrganizationID) {
        this.parentOrganizationID = parentOrganizationID;
    }

    public void setSubOrganizationList(List<Organization> subOrganizationList) {
        this.subOrganizationList = subOrganizationList;
    }

    public void setCountry(Country country) {
        if (country != null) {
            this.countryID = country.getId();
        }
        this.country = country;
    }

    protected void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    protected void setIndex(Integer index) {
        this.index = index;
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

    public void removeSubOrganization(Organization organization) {
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
        return new HashCodeBuilder(-1546384673, 1913962557).append(this.startDate).append(this.id).toHashCode();
    }

}
