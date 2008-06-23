package cn.hb.entity.dictionary.employment;

import cn.hb.entity.hr.employment.雇佣;

public class 雇佣类型 {
 
    /** 编号 */
	private String id(PK);
	 
    /** 名称 */
	private String name;
	 
    /** 简称 */
	private String shortName(unique);
	 
    /** 详细描述 */
	private String description;
	 
	private 雇佣[] 雇佣;
	 
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

}
 
