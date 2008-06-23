package cn.hb.entity.hr.organization;

import cn.hb.entity.dictionary.organization.职种(职务类型);

public class 职务 {
 
    /** 编号 */
	private String id(PK);
	 
    /** 名称 */
	private String name;
	 
    /** 简称 */
	private String shortName;
	 
    /** 详细描述 */
	private String description;
	 
	private 职种(职务类型) 职种(职务类型);
	 
    /** 组织 */
	private Organization-职务（关联）[] Organization-职务（关联）;
	 
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
	 * 取得简称
	 * 
	 * @return 简称
	 */

	public String getShortName() {
		return shortName;
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
	 * 设置组织
	 * 
	 * @param Organization 组织
	 */

	public void setOrganization(String Organization) {
		this.Organization = Organization;
	}

}
 
