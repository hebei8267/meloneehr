package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.accounts.StorageRun;

public interface StorageRunJpaDao extends CrudRepository<StorageRun, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
