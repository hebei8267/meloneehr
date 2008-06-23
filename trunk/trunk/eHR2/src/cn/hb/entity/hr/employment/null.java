package cn.hb.entity.hr.employment;

import cn.hb.entity.dictionary.employment.雇佣类型;
import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.hr.personnel.Person;

public class 雇佣 {
 
    /** 编号 */
	private String 个人id(PK);
	 
    /** 编号 */
	private String 员工id(PK);
	 
	private String 雇佣开始时间(PK);
	 
    /** 详细描述 */
	private String description;
	 
	private 雇佣类型 雇佣类型;
	 
    /** 个人基本信息 */
	private Person Person;
	 
	private 合同[] 合同;
	 
	private 员工 员工;
	 
	/**
	 * 取得编号
	 * 
	 * @return 编号
	 */

	public String getId() {
		return id;
	}

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
	 * 取得个人基本信息
	 * 
	 * @return 个人基本信息
	 */

	public String getPerson() {
		return Person;
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
	 * 设置个人基本信息
	 * 
	 * @param Person 个人基本信息
	 */

	public void setPerson(String Person) {
		this.Person = Person;
	}

}
 
