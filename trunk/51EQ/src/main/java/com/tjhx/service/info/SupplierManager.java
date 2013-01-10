package com.tjhx.service.info;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.info.SupplierJpaDao;
import com.tjhx.entity.info.Supplier;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class SupplierManager {
	@Resource
	private SupplierJpaDao supplierJpaDao;

	/**
	 * 取得所有货品供应商信息
	 * 
	 * @return 货品供应商信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Supplier> getAllGoodsSupplier() {
		return (List<Supplier>) supplierJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得货品供应商信息
	 * 
	 * @param uuid 货品供应商编号
	 * @return 货品供应商信息
	 */
	public Supplier getGoodsSupplierByUuid(Integer uuid) {
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
	public void delGoodsSupplierByUuid(Integer uuid) {
		supplierJpaDao.delete(uuid);
	}

	/**
	 * 添加新货品供应商信息
	 * 
	 * @param goodsSupplier 货品供应商信息
	 */
	@Transactional(readOnly = false)
	public void addNewGoodsSupplier(Supplier goodsSupplier) {
		Supplier _dbGoodsSupplier = findByName(goodsSupplier.getName());
		// 该货品供应商已存在!
		if (null != _dbGoodsSupplier) {
			throw new ServiceException("ERR_MSG_SUP_001");
		}
		supplierJpaDao.save(goodsSupplier);
	}

	/**
	 * 更新货品供应商信息
	 * 
	 * @param goodsSupplier 货品供应商信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateGoodsSupplier(Supplier goodsSupplier) throws IllegalAccessException,
			InvocationTargetException {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		Supplier _dbGoodsSupplier = supplierJpaDao.findOne(goodsSupplier.getUuid());
		if (null == _dbGoodsSupplier) {
			// 货品供应商不存在!
			throw new ServiceException("ERR_MSG_USER_002");
		}

		_dbGoodsSupplier.setName(goodsSupplier.getName());

		// ----------------------------------------------------------------------------
		supplierJpaDao.save(_dbGoodsSupplier);
	}
}