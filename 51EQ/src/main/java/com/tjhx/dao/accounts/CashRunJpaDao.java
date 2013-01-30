package com.tjhx.dao.accounts;

import java.math.BigDecimal;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.accounts.CashRun;

public interface CashRunJpaDao extends CrudRepository<CashRun, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	@SuppressWarnings("rawtypes")
	@Query("select c from CashRun c where c.orgId = :orgId and c.optDateY = :optDateY and c.optDateM = :optDateM")
	public Iterable findByOrgId_OptDateY_OptDateM(@Param("orgId") String orgId, @Param("optDateY") String optDateY,
			@Param("optDateM") String optDateM, Sort sort);

	@Query("select sum(c.initAmt) from CashRun c where c.orgId = :orgId and c.optDateShow = :optDateShow")
	public BigDecimal calInitAmt(@Param("orgId") String orgId, @Param("optDateShow") String optDateShow);

	@SuppressWarnings("rawtypes")
	@Query("select c from CashRun c where c.orgId = :orgId and c.optDate = :optDate")
	public Iterable findByOrgId_OptDate(@Param("orgId") String orgId, @Param("optDate") String optDate, Sort sort);

	@Query("select c from CashRun c where c.orgId = :orgId and c.optDate = :optDate and c.jobType = :jobType")
	public CashRun findByOrgId_OptDate_JobType(@Param("orgId") String orgId, @Param("optDate") String optDate,
			@Param("jobType") Integer jobType);
}
