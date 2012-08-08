package com.tjhx.service.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.jpa.product.ProductBrandJpaDao;
import com.tjhx.entity.product.ProductBrand;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ProductBrandManager {
	private ProductBrandJpaDao productBrandJpaDao;

	/**
	 * 取得所有商品品牌信息
	 * 
	 * @return 商品品牌信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProductBrand> getAllProductBrand() {
		return (List<ProductBrand>) productBrandJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得商品品牌信息
	 * 
	 * @param uuid 商品品牌编号
	 * @return 商品品牌信息
	 */
	public ProductBrand getProductBrandByUuid(Integer uuid) {
		return productBrandJpaDao.findOne(uuid);
	}

	/**
	 * 删除商品品牌信息
	 * 
	 * @param uuid 商品品牌编号
	 */
	@Transactional(readOnly = false)
	public void delProductBrandByUuid(Integer uuid) {
		productBrandJpaDao.delete(uuid);
	}

	/**
	 * 添加新商品品牌信息
	 * 
	 * @param productBrand 商品品牌信息
	 */
	@Transactional(readOnly = false)
	public void addNewProductBrand(ProductBrand productBrand) {
		productBrandJpaDao.save(productBrand);
	}

	/**
	 * 更新商品品牌信息
	 * 
	 * @param productBrand 商品品牌信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateProductBrand(ProductBrand productBrand) throws IllegalAccessException, InvocationTargetException {

		ProductBrand _dbProductBrand = productBrandJpaDao.findOne(productBrand.getUuid());
		if (null == _dbProductBrand) {
			// 商品品牌不存在!
			throw new ServiceException("ERR_MSG_PDU_007");
		}

		_dbProductBrand.setName(productBrand.getName());
		_dbProductBrand.setDescTxt(productBrand.getDescTxt());

		productBrandJpaDao.save(_dbProductBrand);
	}

	@Autowired
	public void setProductBrandJpaDao(ProductBrandJpaDao productBrandJpaDao) {
		this.productBrandJpaDao = productBrandJpaDao;
	}

}