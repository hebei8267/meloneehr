package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.CardRun;

public interface CardRunJpaDao extends CrudRepository<CardRun, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	public CardRun findByOrgIdAndOptDate(String orgId, String optDate);

	@SuppressWarnings("rawtypes")
	@Query("select c from CardRun c where c.orgId = :orgId and c.optDateY = :optDateY and c.optDateM = :optDateM")
	public Iterable findByOrgId_OptDateY_OptDateM(@Param("orgId") String orgId, @Param("optDateY") String optDateY,
			@Param("optDateM") String optDateM, Sort sort);

}
