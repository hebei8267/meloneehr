package com.tjhx.service.info;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.info.SupplierJpaDao;
import com.tjhx.entity.info.Supplier;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class SupplierManager {
	private static Logger logger = LoggerFactory.getLogger(SupplierManager.class);
	@Resource
	private SupplierJpaDao supplierJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;

	/**
	 * 取得所有货品供应商信息
	 * 
	 * @return 货品供应商信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Supplier> getAllSupplier() {
		List<Supplier> _supplierList = spyMemcachedClient.get(MemcachedObjectType.SUPPLIER_LIST.getObjKey());

		if (null == _supplierList) {
			// 从数据库中取出全量供应商信息(List格式)
			_supplierList = (List<Supplier>) supplierJpaDao
					.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
			// 将供应商信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.SUPPLIER_LIST.getObjKey(),
					MemcachedObjectType.SUPPLIER_LIST.getExpiredTime(), _supplierList);

			logger.debug("供应商信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出供应商信息");
		}
		return _supplierList;
	}

	/**
	 * 根据编号取得货品供应商信息
	 * 
	 * @param uuid 货品供应商编号
	 * @return 货品供应商信息
	 */
	public Supplier getSupplierByUuid(Integer uuid) {
		return supplierJpaDao.findOne(uuid);
	}

	/**
	 * 取得货品供应商信息
	 * 
	 * @param name 名称
	 * @return 货品供应商信息
	 */
	public Supplier findByName(String name) {
		return supplierJpaDao.findByName(name);
	}

	/**
	 * 删除货品供应商信息
	 * 
	 * @param uuid 货品供应商编号
	 */
	@Transactional(readOnly = false)
	public void delSupplierByUuid(Integer uuid) {
		supplierJpaDao.delete(uuid);
	}

	/**
	 * 添加新货品供应商信息
	 * 
	 * @param supplier 货品供应商信息
	 */
	@Transactional(readOnly = false)
	public void addNewSupplier(Supplier supplier) {
		Supplier _dbSupplier = findByName(supplier.getName());
		// 该货品供应商已存在!
		if (null != _dbSupplier) {
			throw new ServiceException("ERR_MSG_SUP_001");
		}
		supplierJpaDao.save(supplier);
	}

	/**
	 * 更新货品供应商信息
	 * 
	 * @param supplier 货品供应商信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateSupplier(Supplier supplier) throws IllegalAccessException, InvocationTargetException {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		Supplier _dbSupplier = supplierJpaDao.findOne(supplier.getUuid());
		if (null == _dbSupplier) {
			// 货品供应商不存在!
			throw new ServiceException("ERR_MSG_USER_002");
		}

		_dbSupplier.setName(supplier.getName());

		// ----------------------------------------------------------------------------
		supplierJpaDao.save(_dbSupplier);
	}
}