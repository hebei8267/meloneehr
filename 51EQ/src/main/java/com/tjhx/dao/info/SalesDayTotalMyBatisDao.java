package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.SalesDayTotal;

public interface SalesDayTotalMyBatisDao {

	/**
	 * 删除指定日期的所有门店销售信息
	 * 
	 * @param optDate 日期
	 */
	public void delSalesDayTotalInfo(String optDate);

	/**
	 * 取得合计实销金额（指定时间区间/机构）
	 * 
	 * @param salesDayTotal
	 * @return
	 */
	public List<SalesDayTotal> getSumSaleRamtList(SalesDayTotal salesDayTotal);

	/**
	 * 取得合计实销数量（指定时间区间/机构）
	 * 
	 * @param salesDayTotal
	 * @return
	 */
	public List<SalesDayTotal> getSumSaleRqtyList(SalesDayTotal salesDayTotal);
}
