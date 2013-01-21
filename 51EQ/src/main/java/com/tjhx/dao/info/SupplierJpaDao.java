package com.tjhx.dao.info;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.Supplier;

public interface SupplierJpaDao extends CrudRepository<Supplier, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得货品供应商信息
	 * 
	 * @param name 名称
	 * @return 货品供应商信息
	 */
	public Supplier findByName(String name);

	/**
	 * 取得货品供应商信息
	 * 
	 * @param BwId 供应商编号-百威
	 * @return 货品供应商信息
	 */
	public Supplier findBySupplierBwId(String BwId);
}
