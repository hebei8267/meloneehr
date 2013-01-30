package com.tjhx.dao.accounts;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.CashDaily;

public interface CashDailyJpaDao extends CrudRepository<CashDaily, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@Query("select c from CashDaily c where c.orgId = :orgId and c.optDate = :optDate")
	public CashDaily findByOrgId_OptDate(@Param("orgId") String orgId, @Param("optDate") String optDate);

}
