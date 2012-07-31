package com.tjhx.service.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.product.ProductTypeJpaDao;
import com.tjhx.entity.product.ProductType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ProductTypeManager {
	private ProductTypeJpaDao productTypeJpaDao;

	/**
	 * 取得所有产品类型信息
	 * 
	 * @return 产品类型信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProductType> getAllProductType() {
		return (List<ProductType>) productTypeJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得产品类型信息
	 * 
	 * @param uuid 产品类型编号
	 * @return ProductType信息
	 */
	public ProductType getProductTypeByUuid(Integer uuid) {
		return productTypeJpaDao.findOne(uuid);
	}

	/**
	 * 删除产品类型信息
	 * 
	 * @param uuid 产品类型编号
	 */
	@Transactional(readOnly = false)
	public void delProductTypeByUuid(Integer uuid) {
		productTypeJpaDao.delete(uuid);
	}

	/**
	 * 添加新产品类型信息
	 * 
	 * @param productType 产品类型信息
	 */
	@Transactional(readOnly = false)
	public void addNewProductType(ProductType productType) {
		productTypeJpaDao.save(productType);
	}

	/**
	 * 更新产品类型信息
	 * 
	 * @param productType 产品类型信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateProductType(ProductType productType) throws IllegalAccessException, InvocationTargetException {
		ProductType _dbProductType = productTypeJpaDao.findOne(productType.getUuid());
		if (null == _dbProductType) {
			// 产品类型不存在!
			throw new ServiceException("ERR_MSG_PDU_008");
		}

		_dbProductType.setName(productType.getName());
		_dbProductType.setDescTxt(productType.getDescTxt());

		productTypeJpaDao.save(_dbProductType);
	}

	@Autowired
	public void setProductTypeJpaDao(ProductTypeJpaDao productTypeJpaDao) {
		this.productTypeJpaDao = productTypeJpaDao;
	}

}