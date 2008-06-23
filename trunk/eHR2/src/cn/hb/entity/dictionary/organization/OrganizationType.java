package cn.hb.entity.dictionary.organization;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.hr.organization.Organization;

/** 组织 */
public class Organization extends AbstractEntityBean 类型 {
	public Organization() {
	}

/** 组织类型 */
public class OrganizationType extends AbstractEntityBean  {
 
    /** 编号 */
	private String id(PK);
	 
    /** 名称 */
	private String name;
	 
    /** 详细描述 */
	private String description;
	 
    /** 组织 */
	private Organization[] Organization;
	 
	/**
	 * 取得编号
	 * 
	 * @return 编号
	 */

	public String getId() {
		return id;
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
	 * 取得详细描述
	 * 
	 * @return 详细描述
	 */

	public String getDescription() {
		return description;
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
	 * 设置组织
	 * 
	 * @param Organization 组织
	 */

	public void setOrganization(String Organization) {
		this.Organization = Organization;
	}

}
 
