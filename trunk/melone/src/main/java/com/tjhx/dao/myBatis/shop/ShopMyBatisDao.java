package com.tjhx.dao.myBatis.shop;

import java.util.List;

import com.tjhx.entity.shop.Shop;

public interface ShopMyBatisDao {
	public List<Shop> getShopList(Shop shop);
}
