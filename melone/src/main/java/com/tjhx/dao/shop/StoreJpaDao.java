package com.tjhx.dao.shop;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tjhx.entity.shop.Store;

public interface StoreJpaDao extends PagingAndSortingRepository<Store, Integer> {
	public Store findById(String id);
}
