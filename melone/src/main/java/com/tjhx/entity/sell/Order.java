package com.tjhx.entity.sell;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.google.common.collect.Lists;
import com.tjhx.entity.IdEntity;
import com.tjhx.globals.Constants;

/**
 * 购物单
 */
@Entity
@Table(name = "T_ORDER")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends IdEntity {

	private static final long serialVersionUID = 3814446669299310703L;
	/** 购物单流水号 */
	private String orderSerial;
	/** 购物单-子项(数量) */
	private Integer itemNum;
	/** 销售额 */
	private double sales;
	/** 销售类型 1(零售-默认) 2(会员) 4(批发) */
	private String sellType = Constants.SELL_TYPE_1;
	/** 交易日期YYYYMMDD */
	private String transDate;
	/** 购物单-子项集合 */
	private List<OrderItem> itemList = Lists.newArrayList();

	/**
	 * 取得购物单流水号
	 * 
	 * @return 购物单流水号
	 */
	@NaturalId
	@Column(length = 36)
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
	 * 取得购物单-子项(数量)
	 * 
	 * @return 购物单-子项(数量)
	 */
	public Integer getItemNum() {
		return itemNum;
	}

	/**
	 * 设置购物单-子项(数量)
	 * 
	 * @param itemNum 购物单-子项(数量)
	 */
	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	/**
	 * 取得销售额
	 * 
	 * @return 销售额
	 */
	public double getSales() {
		return sales;
	}

	/**
	 * 设置销售额
	 * 
	 * @param sales 销售额
	 */
	public void setSales(double sales) {
		this.sales = sales;
	}

	/**
	 * 取得销售类型 1(零售) 2(会员) 4(批发)
	 * 
	 * @return 销售类型 1(零售-默认) 2(会员) 4(批发)
	 */
	@Column(length = 1)
	public String getSellType() {
		return sellType;
	}

	/**
	 * 设置销售类型 1(零售) 2(会员) 4(批发)
	 * 
	 * @param sellType 销售类型 1(零售-默认) 2(会员) 4(批发)
	 */
	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	/**
	 * 取得交易日期YYYYMMDD
	 * 
	 * @return 交易日期YYYYMMDD
	 */
	@Column(length = 8)
	public String getTransDate() {
		return transDate;
	}

	/**
	 * 设置交易日期YYYYMMDD
	 * 
	 * @param transDate 交易日期YYYYMMDD
	 */
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	/**
	 * 取得购物单-子项集合
	 * 
	 * @return 购物单-子项集合
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
	public List<OrderItem> getItemList() {
		return itemList;
	}

	/**
	 * 设置购物单-子项集合
	 * 
	 * @param itemList 购物单-子项集合
	 */
	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	/**
	 * 添加购物单-子项
	 * 
	 * @param item 购物单-子项
	 */
	public void addItem(OrderItem orderItem) {
		if (null == orderItem) {
			return;
		}

		orderItem.setOrder(this);
		orderItem.set_index(itemList.size() + 1);

		itemList.add(orderItem);
	}
}
