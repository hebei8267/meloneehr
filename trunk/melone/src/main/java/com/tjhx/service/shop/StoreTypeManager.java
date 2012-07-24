package com.tjhx.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.shop.StoreTypeJpaDao;
import com.tjhx.entity.shop.StoreType;

@Service
@Transactional(readOnly = true)
public class StoreTypeManager {
	private StoreTypeJpaDao storeTypeJpaDao;

	/**
	 * 取得所有仓库类型信息
	 * 
	 * @return 仓库类型信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<StoreType> getAllStoreType() {
		return (List<StoreType>) storeTypeJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得仓库类型信息
	 * 
	 * @param uuid 仓库类型编号
	 * @return 仓库类型信息
	 */
	public StoreType getStoreTypeByUuid(Integer uuid) {

		return storeTypeJpaDao.findOne(uuid);
	}

	/**
	 * 删除仓库类型信息
	 * 
	 * @param uuid 仓库类型编号
	 */
	public void delStoreTypeByUuid(Integer uuid) {
		storeTypeJpaDao.delete(uuid);
	}

	@Autowired
	public void setStoreTypeJpaDao(StoreTypeJpaDao storeTypeJpaDao) {
		this.storeTypeJpaDao = storeTypeJpaDao;
	}

}
