package com.tjhx.service.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.jpa.product.ProductTagJpaDao;
import com.tjhx.entity.product.ProductTag;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ProductTagManager {
	@Resource
	private ProductTagJpaDao productTagJpaDao;

	/**
	 * 取得所有商品标签信息
	 * 
	 * @return 商品标签信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProductTag> getAllProductTag() {
		return (List<ProductTag>) productTagJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得商品标签信息
	 * 
	 * @param uuid 商品标签编号
	 * @return 商品标签信息
	 */
	public ProductTag getProductTagByUuid(Integer uuid) {
		return productTagJpaDao.findOne(uuid);
	}

	/**
	 * 删除商品标签信息
	 * 
	 * @param uuid 商品标签编号
	 */
	@Transactional(readOnly = false)
	public void delProductTagByUuid(Integer uuid) {
		productTagJpaDao.delete(uuid);
	}

	/**
	 * 添加新商品标签信息
	 * 
	 * @param productTag 商品标签信息
	 */
	@Transactional(readOnly = false)
	public void addNewProductTag(ProductTag productTag) {
		productTagJpaDao.save(productTag);
	}

	/**
	 * 更新商品标签信息
	 * 
	 * @param productTag 商品标签信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateProductTag(ProductTag productTag) throws IllegalAccessException, InvocationTargetException {

		ProductTag _dbProductTag = productTagJpaDao.findOne(productTag.getUuid());
		if (null == _dbProductTag) {
			// 商品标签不存在!
			throw new ServiceException("ERR_MSG_PDU_001");
		}

		_dbProductTag.setName(productTag.getName());
		_dbProductTag.setDescTxt(productTag.getDescTxt());

		productTagJpaDao.save(_dbProductTag);
	}

}