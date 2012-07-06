package com.tjhx.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.base.IdEntity;

/** 产品品牌 */
@Entity
@Table(name = "T_PRODUCT_BRAND")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductBrand extends IdEntity {

	private static final long serialVersionUID = 8143144086562344382L;

	/** 产品品牌-汉字 */
	private String name;
	/** 产品品牌详细描述 */
	private String descTxt;

	/**
	 * 取得产品品牌-汉字
	 * 
	 * @return 产品品牌-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置产品品牌-汉字
	 * 
	 * @param name 产品品牌-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得产品品牌详细描述
	 * 
	 * @return 产品品牌详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置产品品牌详细描述
	 * 
	 * @param descTxt 产品品牌详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

}
