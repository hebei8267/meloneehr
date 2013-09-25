package com.tjhx.dao.affair;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.PettyCash;

public interface PettyCashJpaDao extends CrudRepository<PettyCash, Integer> {
	@Query("select p from PettyCash p where p.optUid = :optUid")
	public PettyCash findByOptUid(@Param("optUid") String optUid);

	/**
	 * 查询指定机构/更新时间之后的备用金信息
	 * 
	 * @param orgId 机构编号
	 * @param updateDate 更新时间
	 * @param sort
	 * @return
	 */
	@Query("select p from PettyCash p where p.orgId = :orgId and p.updateDate >= :updateDate")
	public List<PettyCash> findByOrgId(@Param("orgId") String orgId, @Param("updateDate") Date updateDate, Sort sort);

}
