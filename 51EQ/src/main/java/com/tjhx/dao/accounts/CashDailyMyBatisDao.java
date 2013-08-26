package com.tjhx.dao.accounts;

import java.util.List;

import com.tjhx.entity.accounts.CashDaily;

public interface CashDailyMyBatisDao {

	public List<CashDaily> getCashDailyList(CashDaily cashDaily);

	public List<CashDaily> getCashDailyListByAllOrg(CashDaily cashDaily);

	public List<CashDaily> getCashDailyChartList(CashDaily cashDaily);

}
