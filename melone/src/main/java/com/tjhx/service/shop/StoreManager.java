package com.tjhx.service.shop;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.shop.StoreJpaDao;
import com.tjhx.dao.shop.StoreTypeJpaDao;
import com.tjhx.entity.shop.Store;
import com.tjhx.entity.shop.StoreType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class StoreManager {
	private StoreJpaDao storeJpaDao;
	private StoreTypeJpaDao storeTypeJpaDao;

	/**
	 * 取得所有仓库信息
	 * 
	 * @return 仓库信息列表
	 */
	public List<Store> getAllStore() {
		return (List<Store>) storeJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得仓库信息
	 * 
	 * @param uuid 仓库编号
	 * @return 仓库信息
	 */
	public Store geStoreByUuid(Integer uuid) {
		return storeJpaDao.findOne(uuid);
	}

	/**
	 * 根据名称取得仓库信息
	 * 
	 * @param name 仓库名称
	 * @return 仓库信息
	 */
	public Store getStoreByName(String name) {
		return storeJpaDao.findByName(name);
	}

	/**
	 * 删除仓库信息
	 * 
	 * @param uuid 仓库编号
	 */
	@Transactional(readOnly = false)
	public void delStoreByUuid(Integer uuid) {
		storeJpaDao.delete(uuid);
	}

	/**
	 * 添加新仓库信息
	 * 
	 * @param store Store信息
	 */
	@Transactional(readOnly = false)
	public void addNewStore(Store store) {
		// 仓库所属类型
		StoreType _dbStoreType = storeTypeJpaDao.findOne(store.getStoreTypeUuid());
		store.setStoreType(_dbStoreType);
		storeJpaDao.save(store);
	}

	/**
	 * 更新Store信息
	 * 
	 * @param store Store信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateStore(Store store) throws IllegalAccessException, InvocationTargetException {

		Store _dbStore = storeJpaDao.findOne(store.getUuid());
		if (null == _dbStore) {
			// 仓库不存在!
			throw new ServiceException("ERR_MSG_CFG_002");
		}

		// 仓库名称-汉字
		_dbStore.setName(store.getName());
		// 仓库电话号码
		_dbStore.setTelNum(store.getTelNum());
		// 仓库地址
		_dbStore.setAddr(store.getAddr());
		// 仓库详细描述
		_dbStore.setDescTxt(store.getDescTxt());
		// 仓库所属类型
		StoreType _dbStoreType = storeTypeJpaDao.findOne(store.getStoreTypeUuid());
		_dbStore.setStoreType(_dbStoreType);

		storeJpaDao.save(_dbStore);
	}

	@Autowired
	public void setStoreJpaDao(StoreJpaDao storeJpaDao) {
		this.storeJpaDao = storeJpaDao;
	}

	@Autowired
	public void setStoreTypeJpaDao(StoreTypeJpaDao storeTypeJpaDao) {
		this.storeTypeJpaDao = storeTypeJpaDao;
	}

}