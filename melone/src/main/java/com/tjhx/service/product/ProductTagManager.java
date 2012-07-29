package com.tjhx.service.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.product.ProductTagJpaDao;
import com.tjhx.entity.product.ProductTag;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ProductTagManager {
	private ProductTagJpaDao productTagJpaDao;

	/**
	 * 取得所有产品标签信息
	 * 
	 * @return 产品标签信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProductTag> getAllProductTag() {
		return (List<ProductTag>) productTagJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得产品标签信息
	 * 
	 * @param uuid 产品标签编号
	 * @return 产品标签信息
	 */
	public ProductTag getProductTagByUuid(Integer uuid) {
		return productTagJpaDao.findOne(uuid);
	}

	/**
	 * 删除产品标签信息
	 * 
	 * @param uuid 产品标签编号
	 */
	@Transactional(readOnly = false)
	public void delProductTagByUuid(Integer uuid) {
		productTagJpaDao.delete(uuid);
	}

	/**
	 * 添加新产品标签信息
	 * 
	 * @param productTag 产品标签信息
	 */
	@Transactional(readOnly = false)
	public void addNewProductTag(ProductTag productTag) {
		productTagJpaDao.save(productTag);
	}

	/**
	 * 更新产品标签信息
	 * 
	 * @param productTag 产品标签信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateProductTag(ProductTag productTag) throws IllegalAccessException, InvocationTargetException {

		ProductTag _dbProductTag = productTagJpaDao.findOne(productTag.getUuid());
		if (null == _dbProductTag) {
			// 产品标签不存在!
			throw new ServiceException("ERR_MSG_CFG_006");
		}

		_dbProductTag.setName(productTag.getName());
		_dbProductTag.setDescTxt(productTag.getDescTxt());

		productTagJpaDao.save(_dbProductTag);
	}

	@Autowired
	public void setProductTagJpaDao(ProductTagJpaDao productTagJpaDao) {
		this.productTagJpaDao = productTagJpaDao;
	}

}