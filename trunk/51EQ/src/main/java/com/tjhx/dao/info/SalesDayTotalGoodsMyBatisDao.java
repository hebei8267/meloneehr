package com.tjhx.dao.info;

public interface SalesDayTotalGoodsMyBatisDao {
	/**
	 * 删除指定日期的所有门店销售信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesDayTotalInfo(String optDate);

	// TODO
	// public List<SalesDayTotalGoods>
	// getSalesDayTotalGoodsList(SalesDayTotalGoods salesDayTotalGoods);

}
