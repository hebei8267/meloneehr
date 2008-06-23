package cn.hb.entity.hr.personnel;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.personnel.MarriageState;
import cn.hb.entity.dictionary.personnel.学历信息;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;
import cn.hb.entity.dictionary.personnel.语言能力;
import cn.hb.entity.hr.employment.雇佣;

/** 个人基本信息 */
public class Person extends AbstractEntityBean  {
	public Person() {
	}

 
    /** 编号 */
	private String id(PK);
	 
	private String 姓名;
	 
	private String 英文名;
	 
	private Integer 性别;
	 
	private String 出生日期;
	 
    /** 婚姻状况 */
	private MarriageState MarriageState;
	 
	private 学历信息 最高学历;
	 
    /** 国家 */
	private Country 国籍;
	 
    /** 民族 */
	private Nation Nation;
	 
    /** 籍贯 */
	private Nativeplace Nativeplace;
	 
	private 证件[] 证件;
	 
    /** 地址 */
	private 联系address[] 联系address;
	 
	private 教育信息[] 教育信息;
	 
	private 培训信息[] 培训信息;
	 
	private 语言能力[] 语言能力;
	 
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
	 * 取得婚姻状况
	 * 
	 * @return 婚姻状况
	 */

	public String getMarriageState() {
		return MarriageState;
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
	 * 取得民族
	 * 
	 * @return 民族
	 */

	public String getNation() {
		return Nation;
	}

	/**
	 * 取得籍贯
	 * 
	 * @return 籍贯
	 */

	public String getNativeplace() {
		return Nativeplace;
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
	 * 设置编号
	 * 
	 * @param id 编号
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 设置婚姻状况
	 * 
	 * @param MarriageState 婚姻状况
	 */

	public void setMarriageState(String MarriageState) {
		this.MarriageState = MarriageState;
	}

	/**
	 * 设置国家
	 * 
	 * @param Country 国家
	 */

	public void setCountry(String Country) {
		this.Country = Country;
	}

	/**
	 * 设置民族
	 * 
	 * @param Nation 民族
	 */

	public void setNation(String Nation) {
		this.Nation = Nation;
	}

	/**
	 * 设置籍贯
	 * 
	 * @param Nativeplace 籍贯
	 */

	public void setNativeplace(String Nativeplace) {
		this.Nativeplace = Nativeplace;
	}

	/**
	 * 设置地址
	 * 
	 * @param address 地址
	 */

	public void setAddress(String address) {
		this.address = address;
	}

}
 
