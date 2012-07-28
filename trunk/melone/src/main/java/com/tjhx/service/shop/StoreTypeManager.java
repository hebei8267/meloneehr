package com.tjhx.service.shop;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.shop.StoreTypeJpaDao;
import com.tjhx.entity.shop.StoreType;
import com.tjhx.service.ServiceException;

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
	 * 根据名称取得仓库类型信息
	 * 
	 * @param name 仓库类型名称
	 * @return 仓库类型信息
	 */
	public StoreType getStoreTypeByName(String name) {
		return storeTypeJpaDao.findByName(name);
	}

	/**
	 * 删除仓库类型信息
	 * 
	 * @param uuid 仓库类型编号
	 */
	@Transactional(readOnly = false)
	public void delStoreTypeByUuid(Integer uuid) {
		storeTypeJpaDao.delete(uuid);
	}

	/**
	 * 添加新仓库类型信息
	 * 
	 * @param storeType 仓库类型信息
	 */
	@Transactional(readOnly = false)
	public void addNewStoreType(StoreType storeType) {
		storeTypeJpaDao.save(storeType);
	}

	/**
	 * 更新仓库类型信息
	 * 
	 * @param storeType 仓库类型信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateStoreType(StoreType storeType) throws IllegalAccessException, InvocationTargetException {

		StoreType _dbStoreType = storeTypeJpaDao.findOne(storeType.getUuid());
		if (null == _dbStoreType) {
			// 仓库类型不存在!
			throw new ServiceException("ERR_MSG_CFG_001");
		}

		// 仓库类型名称-汉字
		_dbStoreType.setName(storeType.getName());
		// 仓库类型详细描述
		_dbStoreType.setDescTxt(storeType.getDescTxt());

		storeTypeJpaDao.save(_dbStoreType);
	}

	@Autowired
	public void setStoreTypeJpaDao(StoreTypeJpaDao storeTypeJpaDao) {
		this.storeTypeJpaDao = storeTypeJpaDao;
	}

}
