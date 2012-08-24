package com.tjhx.service.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.jpa.product.ProductJpaDao;
import com.tjhx.entity.product.Product;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ProductManager {
	private ProductJpaDao productJpaDao;

	/**
	 * 取得所有商品信息
	 * 
	 * @return 商品信息列表
	 */
	public List<Product> getAllProduct() {
		return (List<Product>) productJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得商品信息
	 * 
	 * @param uuid 商品编号
	 * @return 商品信息
	 */
	public Product getProductByUuid(Integer uuid) {
		return productJpaDao.findOne(uuid);
	}

	/**
	 * 删除商品信息
	 * 
	 * @param uuid 商品编号
	 */
	@Transactional(readOnly = false)
	public void delProductByUuid(Integer uuid) {
		productJpaDao.delete(uuid);
	}

	/**
	 * 根据商品名称取得商品信息
	 * 
	 * @param name 商品名称
	 * @return 商品信息
	 */
	public Product findByName(String name) {
		return productJpaDao.findByName(name);
	}

	/**
	 * 添加新商品信息
	 * 
	 * @param product 商品信息
	 */
	@Transactional(readOnly = false)
	public void addNewProduct(Product product) {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		Product _dbProduct = findByName(product.getName());
		// 该商品已存在!
		if (null != _dbProduct) {
			throw new ServiceException("?????????????????");
		}
		// ----------------------------------------------------------------------------
		productJpaDao.save(product);
	}

	/**
	 * 更新商品信息
	 * 
	 * @param product 商品信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateProduct(Product product) throws IllegalAccessException, InvocationTargetException {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		Product _dbProduct = productJpaDao.findOne(product.getUuid());
		if (null == _dbProduct) {
			// Product不存在!
			throw new ServiceException("?????????????????");
		}

		_dbProduct.setName(product.getName());
		_dbProduct.setDescTxt(product.getDescTxt());

		// ----------------------------------------------------------------------------
		productJpaDao.save(_dbProduct);
	}

	@Autowired
	public void setProductJpaDao(ProductJpaDao productJpaDao) {
		this.productJpaDao = productJpaDao;
	}

}