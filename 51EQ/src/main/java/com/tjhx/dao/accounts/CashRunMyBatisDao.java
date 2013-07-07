package com.tjhx.dao.accounts;

import java.util.List;

import com.tjhx.entity.accounts.CashRun;

public interface CashRunMyBatisDao {

	public List<CashRun> getCashRunList(CashRun cashRun);

	public List<CashRun> getCashRunList_OptDate_Interval(CashRun cashRun);

}
