package com.tjhx.dao.syscfg;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.syscfg.GoodsSupplier;

public interface GoodsSupplierJpaDao extends CrudRepository<GoodsSupplier, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得货品供应商信息
	 * 
	 * @param name 名称
	 * @return 货品供应商信息
	 */
	public GoodsSupplier findByName(String name);
}
