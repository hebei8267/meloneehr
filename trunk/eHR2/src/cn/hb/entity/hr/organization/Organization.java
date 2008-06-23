package cn.hb.entity.hr.organization;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.organization.Organization类型;
import cn.hb.entity.dictionary.organization.OrganizationType;
import cn.hb.entity.assets.car.Organization-车辆;
import cn.hb.entity.dictionary.communal.Country;

/** 组织 */
public class Organization extends AbstractEntityBean  {
	public Organization() {
	}

 
    /** 编号 */
	private String id(PK);
	 
    /** 设立时间 */
	private String startDate;
	 
    /** 名称 */
	private String name;
	 
    /** 简称 */
	private String shortName;
	 
    /** 地址 */
	private String address;
	 
    /** 电话号码 */
	private String telephone;
	 
    /** 传真号码 */
	private String fax;
	 
    /** 组织 */
	private String Organization详细描述;
    /** 详细描述 */
	private String 组织description;
	 
    /** 撤销时间 */
	private String endDate;
	 
    /** 撤销原因 */
	private String endDescription;
	 
    /** 组织 */
	private Organization类型 Organization类型;
    /** 组织类型 */
	private OrganizationType OrganizationType;
	 
    /** 组织 */
	private Organization[] Organization;
	 
    /** 组织 */
	private Organization Organization;
	 
    /** 组织 */
	private Organization-职务（关联）[] Organization-职务（关联）;
	 
    /** 组织 */
	private Organization-车辆[] Organization-车辆;
	 
    /** 国家 */
	private Country 所在地Country;
	 
	/**
	 * 取得编号
	 * 
	 * @return 编号
	 */

	public String getId() {
		return id;
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
	 * 取得名称
	 * 
	 * @return 名称
	 */

	public String getName() {
		return name;
	}

	/**
	 * 取得简称
	 * 
	 * @return 简称
	 */

	public String getShortName() {
		return shortName;
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
	 * 取得电话号码
	 * 
	 * @return 电话号码
	 */

	public String getTelephone() {
		return telephone;
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
	 * 取得组织
	 * 
	 * @return 组织
	 */

	public String getOrganization() {
		return Organization;
	}

	/**
	 * 取得详细描述
	 * 
	 * @return 详细描述
	 */

	public String getDescription() {
		return description;
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
	 * 取得撤销原因
	 * 
	 * @return 撤销原因
	 */

	public String getEndDescription() {
		return endDescription;
	}

	/**
	 * 取得组织
	 * 
	 * @return 组织
	 */

	public String getOrganization() {
		return Organization;
	}

	/**
	 * 取得组织类型
	 * 
	 * @return 组织类型
	 */

	public String getOrganizationType() {
		return OrganizationType;
	}

	/**
	 * 取得组织
	 * 
	 * @return 组织
	 */

	public String getOrganization() {
		return Organization;
	}

	/**
	 * 取得组织
	 * 
	 * @return 组织
	 */

	public String getOrganization() {
		return Organization;
	}

	/**
	 * 取得组织
	 * 
	 * @return 组织
	 */

	public String getOrganization() {
		return Organization;
	}

	/**
	 * 取得组织
	 * 
	 * @return 组织
	 */

	public String getOrganization() {
		return Organization;
	}

	/**
	 * 取得国家
	 * 
	 * @return 国家
	 */

	public String getCountry() {
		return Country;
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
	 * 设置组织
	 * 
	 * @param Organization 组织
	 */

	public void setOrganization(String Organization) {
		this.Organization = Organization;
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
	 * @param endDescription 撤销原因
	 */

	public void setEndDescription(String endDescription) {
		this.endDescription = endDescription;
	}

	/**
	 * 设置组织
	 * 
	 * @param Organization 组织
	 */

	public void setOrganization(String Organization) {
		this.Organization = Organization;
	}

	/**
	 * 设置组织类型
	 * 
	 * @param OrganizationType 组织类型
	 */

	public void setOrganizationType(String OrganizationType) {
		this.OrganizationType = OrganizationType;
	}

	/**
	 * 设置组织
	 * 
	 * @param Organization 组织
	 */

	public void setOrganization(String Organization) {
		this.Organization = Organization;
	}

	/**
	 * 设置组织
	 * 
	 * @param Organization 组织
	 */

	public void setOrganization(String Organization) {
		this.Organization = Organization;
	}

	/**
	 * 设置组织
	 * 
	 * @param Organization 组织
	 */

	public void setOrganization(String Organization) {
		this.Organization = Organization;
	}

	/**
	 * 设置组织
	 * 
	 * @param Organization 组织
	 */

	public void setOrganization(String Organization) {
		this.Organization = Organization;
	}

	/**
	 * 设置国家
	 * 
	 * @param Country 国家
	 */

	public void setCountry(String Country) {
		this.Country = Country;
	}

}
 
