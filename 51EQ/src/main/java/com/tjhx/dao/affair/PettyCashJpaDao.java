package com.tjhx.dao.affair;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.PettyCash;

public interface PettyCashJpaDao extends CrudRepository<PettyCash, Integer> {
	@Query("select p from PettyCash p where p.optUid = :optUid")
	public PettyCash findByOptUid(@Param("optUid") String optUid);

	@Query("select p from PettyCash p where p.orgId = :orgId")
	public List<PettyCash> findByOrgId(@Param("orgId") String orgId, Sort sort);

}
