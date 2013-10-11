package com.tjhx.daobw;

import java.util.List;

import com.tjhx.entity.bw.DailySaleTotal;

public interface DailySaleTotalMyBatisDao {

	public List<DailySaleTotal> getDailySaleTotalList(DailySaleTotal dailySaleTotal);

}
