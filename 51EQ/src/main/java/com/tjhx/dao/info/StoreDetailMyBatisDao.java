package com.tjhx.dao.info;

import java.util.List;

import com.tjhx.entity.info.StoreDetail;

public interface StoreDetailMyBatisDao {

	public List<StoreDetail> getDayTotalList(StoreDetail storeDetail);

}
