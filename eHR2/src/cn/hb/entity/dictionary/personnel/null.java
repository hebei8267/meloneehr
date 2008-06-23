package cn.hb.entity.dictionary.personnel;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.hr.personnel.Person;

public class 语言能力 {
 
    /** 编号 */
	private String id(PK);
	 
    /** 名称 */
	private String 语言name;
	 
	private String 掌握程度;
	 
    /** 个人基本信息 */
	private Person Person;
	 
	private 语言能力-掌握程度 语言能力-掌握程度;
	 
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
	 * 设置名称
	 * 
	 * @param name 名称
	 */

	public void setName(String name) {
		this.name = name;
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
 
