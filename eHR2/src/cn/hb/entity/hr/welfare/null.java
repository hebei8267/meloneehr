package cn.hb.entity.hr.welfare;

import cn.hb.entity.dictionary.welfare.薪酬福利类型;

public class 薪酬福利 {
 
    /** 编号 */
	private String 薪酬福利id(PK);
	 
    /** 编号 */
	private String 等级id(PK);
	 
    /** 名称 */
	private String name;
	 
    /** 简称 */
	private String shortName(unique);
	 
    /** 设立时间 */
	private String startDate;
	 
    /** 撤销时间 */
	private String endDate;
	 
    /** 撤销原因 */
	private String endDescription;
	 
	private String 成本数额;
	 
	private 职位-薪酬福利[] 职位-薪酬福利;
	 
	private 薪酬福利类型 薪酬福利类型;
	 
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
	 * 取得设立时间
	 * 
	 * @return 设立时间
	 */

	public String getStartDate() {
		return startDate;
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
	 * 设置设立时间
	 * 
	 * @param startDate 设立时间
	 */

	public void setStartDate(String startDate) {
		this.startDate = startDate;
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

}
 
