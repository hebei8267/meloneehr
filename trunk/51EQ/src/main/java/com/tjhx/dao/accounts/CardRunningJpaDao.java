package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.accounts.CardRunning;

public interface CardRunningJpaDao extends CrudRepository<CardRunning, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
