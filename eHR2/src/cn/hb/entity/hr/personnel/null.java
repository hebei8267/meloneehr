package cn.hb.entity.hr.personnel;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.personnel.CardKind;

public class 证件 {
 
    /** 编号 */
	private String id(PK);
	 
    /** 名称 */
	private String name;
	 
    /** 详细描述 */
	private String description;
	 
	private String 证件号码;
	 
	private String 获得时间;
	 
	private String 失效时间;
	 
    /** 个人基本信息 */
	private Person Person;
	 
    /** 证件类型 */
	private CardKind CardKind;
	 
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
	 * 取得个人基本信息
	 * 
	 * @return 个人基本信息
	 */

	public String getPerson() {
		return Person;
	}

	/**
	 * 取得证件类型
	 * 
	 * @return 证件类型
	 */

	public String getCardKind() {
		return CardKind;
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
	 * 设置个人基本信息
	 * 
	 * @param Person 个人基本信息
	 */

	public void setPerson(String Person) {
		this.Person = Person;
	}

	/**
	 * 设置证件类型
	 * 
	 * @param CardKind 证件类型
	 */

	public void setCardKind(String CardKind) {
		this.CardKind = CardKind;
	}

}
 
