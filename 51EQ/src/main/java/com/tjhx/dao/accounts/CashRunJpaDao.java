package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.accounts.CashRun;

public interface CashRunJpaDao extends CrudRepository<CashRun, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
