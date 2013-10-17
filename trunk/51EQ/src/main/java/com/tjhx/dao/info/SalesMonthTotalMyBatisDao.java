package com.tjhx.dao.info;

public interface SalesMonthTotalMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesMonthTotalInfo(String optYMDate);

}
