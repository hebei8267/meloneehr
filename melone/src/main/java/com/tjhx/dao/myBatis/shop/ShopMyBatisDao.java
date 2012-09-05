package com.tjhx.dao.myBatis.shop;

import java.util.List;

import com.tjhx.entity.shop.Shop;

public interface ShopMyBatisDao {

	public List<Shop> getShopList(Shop shop);

	/**
	 * 删除原有已关联仓库
	 * 
	 * @param shopUuid
	 */
	public void delStoreByShopUuid(Integer shopUuid);
}
