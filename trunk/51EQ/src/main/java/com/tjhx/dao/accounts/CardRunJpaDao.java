package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.accounts.CardRun;

public interface CardRunJpaDao extends CrudRepository<CardRun, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	public CardRun findByOrgIdAndOptDate(String orgId, String optDate);
}
