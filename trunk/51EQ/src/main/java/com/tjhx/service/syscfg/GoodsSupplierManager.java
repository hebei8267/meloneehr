package com.tjhx.service.syscfg;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.syscfg.GoodsSupplierJpaDao;
import com.tjhx.entity.syscfg.GoodsSupplier;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class GoodsSupplierManager {
	@Resource
	private GoodsSupplierJpaDao goodsSupplierJpaDao;

	/**
	 * 取得所有货品供应商信息
	 * 
	 * @return 货品供应商信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsSupplier> getAllGoodsSupplier() {
		return (List<GoodsSupplier>) goodsSupplierJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得货品供应商信息
	 * 
	 * @param uuid 货品供应商编号
	 * @return 货品供应商信息
	 */
	public GoodsSupplier getGoodsSupplierByUuid(Integer uuid) {
		return goodsSupplierJpaDao.findOne(uuid);
	}

	/**
	 * 取得货品供应商信息
	 * 
	 * @param name 名称
	 * @return 货品供应商信息
	 */
	public GoodsSupplier findByName(String name) {
		return goodsSupplierJpaDao.findByName(name);
	}

	/**
	 * 删除货品供应商信息
	 * 
	 * @param uuid 货品供应商编号
	 */
	@Transactional(readOnly = false)
	public void delGoodsSupplierByUuid(Integer uuid) {
		goodsSupplierJpaDao.delete(uuid);
	}

	/**
	 * 添加新货品供应商信息
	 * 
	 * @param goodsSupplier 货品供应商信息
	 */
	@Transactional(readOnly = false)
	public void addNewGoodsSupplier(GoodsSupplier goodsSupplier) {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		GoodsSupplier _dbGoodsSupplier = findByName(goodsSupplier.getName());
		// 该货品供应商已存在!
		if (null != _dbGoodsSupplier) {
			throw new ServiceException("?????????????????");
		}
		// ----------------------------------------------------------------------------
		goodsSupplierJpaDao.save(goodsSupplier);
	}

	/**
	 * 更新货品供应商信息
	 * 
	 * @param goodsSupplier 货品供应商信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateGoodsSupplier(GoodsSupplier goodsSupplier) throws IllegalAccessException,
			InvocationTargetException {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		GoodsSupplier _dbGoodsSupplier = goodsSupplierJpaDao.findOne(goodsSupplier.getUuid());
		if (null == _dbGoodsSupplier) {
			// 货品供应商不存在!
			throw new ServiceException("ERR_MSG_USER_002");
		}

		_dbGoodsSupplier.setName(goodsSupplier.getName());
		_dbGoodsSupplier.setDescTxt(goodsSupplier.getDescTxt());

		// ----------------------------------------------------------------------------
		goodsSupplierJpaDao.save(_dbGoodsSupplier);
	}
}