package cn.hb.entity.dictionary.organization;

import cn.hb.entity.hr.organization.职务;

public class 职种(职务类型) {
 
    /** 编号 */
	private String id(PK);
	 
    /** 详细描述 */
	private String description;
	 
    /** 名称 */
	private String name;
	 
	private 职务[] 职务;
	 
	private 职种(职务类型)[] 职种(职务类型);
	 
	private 职种(职务类型) 职种(职务类型);
	 
	/**
	 * 取得编号
	 * 
	 * @return 编号
	 */

	public String getId() {
		return id;
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
	 * 取得名称
	 * 
	 * @return 名称
	 */

	public String getName() {
		return name;
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
	 * 设置详细描述
	 * 
	 * @param description 详细描述
	 */

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 设置名称
	 * 
	 * @param name 名称
	 */

	public void setName(String name) {
		this.name = name;
	}

}
 
