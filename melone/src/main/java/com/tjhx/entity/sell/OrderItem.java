package com.tjhx.entity.sell;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 购物单-子项
 */
@Entity
@Table(name = "T_ORDER_ITEM")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderItem extends IdEntity {

	private static final long serialVersionUID = -232667289821344511L;

	/** 购物单流水号 */
	private String orderSerial;
	/** 购物单-子项(序列) */
	private Integer _index;
	/** 商品编号/条形码 */
	private String barCode;
	/** 商品价格(原始) */
	private double price;
	/** 折扣(0.01~1.0[默认]) */
	private double discount = 1;
	/** 售价=商品价格(原始)*折扣 */
	// private double SellPrice;
	/** 购物单 */
	private Order order;

	/**
	 * 取得购物单流水号
	 * 
	 * @return 购物单流水号
	 */
	@NaturalId
	@Column(length = 16)
	public String getOrderSerial() {
		return orderSerial;
	}

	/**
	 * 设置购物单流水号
	 * 
	 * @param orderSerial 购物单流水号
	 */
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	/**
	 * 取得购物单-子项(序列)
	 * 
	 * @return 购物单-子项(序列)
	 */
	@NaturalId
	public Integer get_index() {
		return _index;
	}

	/**
	 * 设置购物单-子项(序列)
	 * 
	 * @param _index 购物单-子项(序列)
	 */
	public void set_index(Integer _index) {
		this._index = _index;
	}

	/**
	 * 取得商品编号/条形码
	 * 
	 * @return 商品编号/条形码
	 */
	@Column(length = 16)
	public String getBarCode() {
		return barCode;
	}

	/**
	 * 设置商品编号/条形码
	 * 
	 * @param barCode 商品编号/条形码
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * 取得商品价格(原始)
	 * 
	 * @return 商品价格(原始)
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 设置商品价格(原始)
	 * 
	 * @param price 商品价格(原始)
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 取得售价
	 * 
	 * @return 售价
	 */
	public double getSellPrice() {
		return price * discount;
	}

	/**
	 * 设置售价=商品价格(原始)*折扣
	 * 
	 * @param SellPrice 售价=商品价格(原始)*折扣
	 */
	protected void setSellPrice(double SellPrice) {

	}

	/**
	 * 取得折扣(0.01~1.0[默认])
	 * 
	 * @return 折扣(0.01~1.0[默认])
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * 设置折扣(0.01~1.0[默认])
	 * 
	 * @param discount 折扣(0.01~1.0[默认])
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * 取得购物单
	 * 
	 * @return 购物单
	 */
	@ManyToOne
	// @JoinColumn表示外键的列
	@JoinColumn(name = "ORDER_UUID")
	public Order getOrder() {
		return order;
	}

	/**
	 * 设置购物单
	 * 
	 * @param order 购物单
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

}
