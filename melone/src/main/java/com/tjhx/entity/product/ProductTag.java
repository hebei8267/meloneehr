package com.tjhx.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 商品标签
 */
@Entity
@Table(name = "T_PRODUCT_TAG")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductTag extends IdEntity {

	private static final long serialVersionUID = 8979465325997835303L;

	/** 商品标签名称-汉字 */
	private String name;
	/** 商品标签详细描述 */
	private String descTxt;

	/**
	 * 取得商品标签名称-汉字
	 * 
	 * @return 商品标签名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置商品标签名称-汉字
	 * 
	 * @param name 商品标签名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得商品标签详细描述
	 * 
	 * @return 商品标签详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置商品标签详细描述
	 * 
	 * @param descTxt 商品标签详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

}
