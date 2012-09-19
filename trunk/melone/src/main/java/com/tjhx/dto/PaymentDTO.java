package com.tjhx.dto;

public class PaymentDTO {
	/** 购买商品条形码数组 */
	private String[] barCode;
	/** 购买商品数量数组 */
	private Integer[] amount;

	/**
	 * 取得购买商品条形码数组
	 * 
	 * @return 购买商品条形码数组
	 */
	public String[] getBarCode() {
		return barCode;
	}

	/**
	 * 设置购买商品条形码数组
	 * 
	 * @param barCode 购买商品条形码数组
	 */
	public void setBarCode(String[] barCode) {
		this.barCode = barCode;
	}

	/**
	 * 取得购买商品数量数组
	 * 
	 * @return 购买商品数量数组
	 */
	public Integer[] getAmount() {
		return amount;
	}

	/**
	 * 设置购买商品数量数组
	 * 
	 * @param amount 购买商品数量数组
	 */
	public void setAmount(Integer[] amount) {
		this.amount = amount;
	}

}
