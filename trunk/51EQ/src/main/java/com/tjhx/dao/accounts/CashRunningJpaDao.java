package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.accounts.CashRunning;

public interface CashRunningJpaDao extends CrudRepository<CashRunning, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
