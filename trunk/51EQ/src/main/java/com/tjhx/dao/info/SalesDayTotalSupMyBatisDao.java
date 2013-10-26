package com.tjhx.dao.info;

public interface SalesDayTotalSupMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售信息(按供应商分类)
	 * 
	 * @param optDate 日期
	 */
	public void delSalesDayTotalInfo(String optDate);
}
