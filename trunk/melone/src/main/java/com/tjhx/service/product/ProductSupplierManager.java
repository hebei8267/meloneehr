package com.tjhx.service.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.product.ProductSupplierJpaDao;
import com.tjhx.entity.product.ProductSupplier;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ProductSupplierManager {
	private ProductSupplierJpaDao productSupplierJpaDao;

	/**
	 * 取得所有产品供应商信息
	 * 
	 * @return 产品供应商信息列表
	 */
	public List<ProductSupplier> getAllProductSupplier() {
		return (List<ProductSupplier>) productSupplierJpaDao.findAll(new Sort(
				new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得产品供应商信息
	 * 
	 * @param uuid 产品供应商编号
	 * @return 产品供应商信息
	 */
	public ProductSupplier getProductSupplierByUuid(Integer uuid) {
		return productSupplierJpaDao.findOne(uuid);
	}

	/**
	 * 删除产品供应商信息
	 * 
	 * @param uuid 产品供应商编号
	 */
	@Transactional(readOnly = false)
	public void delProductSupplierByUuid(Integer uuid) {
		productSupplierJpaDao.delete(uuid);
	}

	/**
	 * 添加新产品供应商信息
	 * 
	 * @param productSupplier 产品供应商信息
	 */
	@Transactional(readOnly = false)
	public void addNewProductSupplier(ProductSupplier productSupplier) {
		ProductSupplier _dbProductSupplier = productSupplierJpaDao.findById(productSupplier.getId());
		// 该产品供应商已存在!
		if (null != _dbProductSupplier) {
			throw new ServiceException("ERR_MSG_PDU_009");
		}

		productSupplierJpaDao.save(productSupplier);
	}

	/**
	 * 更新产品供应商信息
	 * 
	 * @param productSupplier 产品供应商信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateProductSupplier(ProductSupplier productSupplier) throws IllegalAccessException,
			InvocationTargetException {
		ProductSupplier _dbProductSupplier = productSupplierJpaDao.findOne(productSupplier.getUuid());
		if (null == _dbProductSupplier) {
			// 产品供应商不存在!
			throw new ServiceException("ERR_MSG_PDU_010");
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

	@Autowired
	public void setProductSupplierJpaDao(ProductSupplierJpaDao productSupplierJpaDao) {
		this.productSupplierJpaDao = productSupplierJpaDao;
	}

}