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

	/**
	 * 根据查询条件 取得指定门店备用金余额（指定时间段内）
	 * 
	 * @param orgId
	 * @param optDate_start
	 * @param optDate_end
	 * @return
	 */
	@Query("select p from PettyCash p where p.orgId = :orgId and p.optDate >= :optDate_start and p.optDate <= :optDate_end")
	public List<PettyCash> findByOrgIdAndOptDateInterval(@Param("orgId") String orgId,
			@Param("optDate_start") String optDate_start, @Param("optDate_end") String optDate_end, Sort sort);

}
