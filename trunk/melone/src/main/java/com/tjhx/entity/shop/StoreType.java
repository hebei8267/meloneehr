package com.tjhx.entity.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.base.IdEntity;

/**
 * 仓库类型
 */
@Entity
@Table(name = "T_STORE_TYPE")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StoreType extends IdEntity {

	private static final long serialVersionUID = -1866354006961467779L;
	/** 仓库类型名称-汉字 */
	private String name;
	/** 仓库类型详细描述 */
	private String descTxt;

	/**
	 * 取得仓库类型名称-汉字
	 * 
	 * @return 仓库类型名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置仓库类型名称-汉字
	 * 
	 * @param name 仓库类型名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得仓库类型详细描述
	 * 
	 * @return 仓库类型详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置仓库类型详细描述
	 * 
	 * @param descTxt 仓库类型详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

}
