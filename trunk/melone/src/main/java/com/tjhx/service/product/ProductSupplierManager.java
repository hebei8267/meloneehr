package com.tjhx.service.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.jpa.product.ProductSupplierJpaDao;
import com.tjhx.dao.myBatis.product.ProductSupplierMyBatisDao;
import com.tjhx.entity.product.ProductSupplier;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ProductSupplierManager {
	@Resource
	private ProductSupplierJpaDao productSupplierJpaDao;
	@Resource
	private ProductSupplierMyBatisDao productSupplierMyBatisDao;

	/**
	 * 取得所有商品供应商信息
	 * 
	 * @return 商品供应商信息列表
	 */
	public List<ProductSupplier> getAllProductSupplier() {
		return (List<ProductSupplier>) productSupplierJpaDao.findAll(new Sort(
				new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 取得商品供应商信息(根据参数)
	 * 
	 * @param productSupplier
	 * @return
	 */
	public List<ProductSupplier> getProductSupplierList(ProductSupplier productSupplier) {
		return productSupplierMyBatisDao.getProductSupplierList(productSupplier);
	}

	/**
	 * 根据编号取得商品供应商信息
	 * 
	 * @param uuid 商品供应商编号
	 * @return 商品供应商信息
	 */
	public ProductSupplier getProductSupplierByUuid(Integer uuid) {
		return productSupplierJpaDao.findOne(uuid);
	}

	/**
	 * 删除商品供应商信息
	 * 
	 * @param uuid 商品供应商编号
	 */
	@Transactional(readOnly = false)
	public void delProductSupplierByUuid(Integer uuid) {
		productSupplierJpaDao.delete(uuid);
	}

	/**
	 * 添加新商品供应商信息
	 * 
	 * @param productSupplier 商品供应商信息
	 */
	@Transactional(readOnly = false)
	public void addNewProductSupplier(ProductSupplier productSupplier) {
		ProductSupplier _dbProductSupplier = productSupplierJpaDao.findById(productSupplier.getId());
		// 该商品供应商已存在!
		if (null != _dbProductSupplier) {
			throw new ServiceException("ERR_MSG_PDU_004");
		}

		productSupplierJpaDao.save(productSupplier);
	}

	/**
	 * 更新商品供应商信息
	 * 
	 * @param productSupplier 商品供应商信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateProductSupplier(ProductSupplier productSupplier) throws IllegalAccessException,
			InvocationTargetException {
		ProductSupplier _dbProductSupplier = productSupplierJpaDao.findOne(productSupplier.getUuid());
		if (null == _dbProductSupplier) {
			// 商品供应商不存在!
			throw new ServiceException("ERR_MSG_PDU_005");
		}

		// 供应商名称-汉字
		_dbProductSupplier.setName(productSupplier.getName());
		// 供应商联系人
		_dbProductSupplier.setContact(productSupplier.getContact());
		// 供应商电话号码
		_dbProductSupplier.setTelNum(productSupplier.getTelNum());
		// 供应商传真号码
		_dbProductSupplier.setFaxNum(productSupplier.getFaxNum());
		// 供应商Email
		_dbProductSupplier.setEmail(productSupplier.getEmail());
		// 供应商地址
		_dbProductSupplier.setAddr(productSupplier.getAddr());
		// 供应商邮编
		_dbProductSupplier.setPostCode(productSupplier.getPostCode());
		// 供应商详细描述
		_dbProductSupplier.setDescTxt(productSupplier.getDescTxt());
		// 税务登记号
		_dbProductSupplier.setTaxRegNum(productSupplier.getTaxRegNum());
		// 打款银行名称
		_dbProductSupplier.setBankName(productSupplier.getBankName());
		// 打款银行账号
		_dbProductSupplier.setBankAccount(productSupplier.getBankAccount());

		productSupplierJpaDao.save(_dbProductSupplier);
	}

}