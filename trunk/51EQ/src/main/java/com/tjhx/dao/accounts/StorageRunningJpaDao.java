package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.accounts.StorageRunning;

public interface StorageRunningJpaDao extends CrudRepository<StorageRunning, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
