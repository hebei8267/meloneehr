package com.tjhx.dao.shop;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.shop.StoreType;

public interface StoreTypeJpaDao extends CrudRepository<StoreType, Integer> {
	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	public StoreType findByName(String name);
}
