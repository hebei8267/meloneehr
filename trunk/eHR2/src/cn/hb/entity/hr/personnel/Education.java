package cn.hb.entity.hr.personnel;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.personnel.EducateSpecialty;

/** 教育信息 */
public class Education {
	public Education() {
	}

 
    /** 编号 */
	private String id(PK);
	 
    /** 开始时间 */
	private String startDate;
	 
    /** 结束时间 */
	private String endDate;
	 
    /** 名称 */
	private String 学校name;
	 
    /** 详细描述 */
	private String note;
	 
    /** 教育专业 */
	private EducateSpecialty EducateSpecialty;
	 
    /** 个人基本信息 */
	private Person Person;
	 
	/**
	 * 取得编号
	 * 
	 * @return 编号
	 */

	public String getId() {
		return id;
	}

	/**
	 * 取得开始时间
	 * 
	 * @return 开始时间
	 */

	public String getStartDate() {
		return startDate;
	}

	/**
	 * 取得结束时间
	 * 
	 * @return 结束时间
	 */

	public String getEndDate() {
		return endDate;
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

	public String getNote() {
		return note;
	}

	/**
	 * 取得教育专业
	 * 
	 * @return 教育专业
	 */

	public String getEducateSpecialty() {
		return EducateSpecialty;
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
	 * 设置开始时间
	 * 
	 * @param startDate 开始时间
	 */

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 设置结束时间
	 * 
	 * @param endDate 结束时间
	 */

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	 * @param note 详细描述
	 */

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 设置教育专业
	 * 
	 * @param EducateSpecialty 教育专业
	 */

	public void setEducateSpecialty(String EducateSpecialty) {
		this.EducateSpecialty = EducateSpecialty;
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
 
