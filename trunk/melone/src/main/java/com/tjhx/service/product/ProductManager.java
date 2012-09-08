package com.tjhx.service.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.dao.jpa.product.ProductBrandJpaDao;
import com.tjhx.dao.jpa.product.ProductJpaDao;
import com.tjhx.dao.jpa.product.ProductSupplierJpaDao;
import com.tjhx.dao.jpa.product.ProductTypeJpaDao;
import com.tjhx.dao.myBatis.product.ProductMyBatisDao;
import com.tjhx.entity.product.Product;
import com.tjhx.entity.product.ProductBrand;
import com.tjhx.entity.product.ProductSupplier;
import com.tjhx.entity.product.ProductType;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.ServiceException;
import com.tjhx.service.io.FileManager;

@Service
@Transactional(readOnly = true)
public class ProductManager {
	private ProductJpaDao productJpaDao;
	private ProductTypeJpaDao productTypeJpaDao;
	private ProductBrandJpaDao productBrandJpaDao;
	private ProductSupplierJpaDao productSupplierJpaDao;
	private ProductMyBatisDao productMyBatisDao;
	private FileManager fileManager;
	private SysConfig sysConfig;

	/**
	 * 取得所有商品信息
	 * 
	 * @return 商品信息列表
	 */
	public List<Product> getAllProduct() {
		return (List<Product>) productJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 取得商品信息(根据参数)
	 * 
	 * @param product
	 * @return
	 */
	public List<Product> getProductList(Product product) {
		return productMyBatisDao.getProductList(product);
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
	 * 添加新商品信息
	 * 
	 * @param product 商品信息
	 * @param imgFile 上传文件信息
	 */
	@Transactional(readOnly = false)
	public void addNewProduct(Product product, MultipartFile imgFile) {

		Product _dbProduct = productJpaDao.findByBarCode(product.getBarCode());
		// 该商品编号已存在
		if (null != _dbProduct) {
			throw new ServiceException("ERR_MSG_PDU_006");
		}

		// 商品类型
		if (null != product.getProductTypeUuid()) {
			ProductType _dbProductType = productTypeJpaDao.findOne(product.getProductTypeUuid());
			product.setProductType(_dbProductType);
		}

		// 商品品牌
		if (null != product.getProductBrandUuid()) {
			ProductBrand _dbProductBrand = productBrandJpaDao.findOne(product.getProductBrandUuid());
			product.setProductBrand(_dbProductBrand);
		}

		// 商品供应商
		if (StringUtils.isNotBlank(product.getProductSupplierId())) {
			ProductSupplier _dbProductSupplier = productSupplierJpaDao.findById(product.getProductSupplierId());
			product.setProductSupplier(_dbProductSupplier);
		}

		// 上传商品相片名称
		if (StringUtils.isNotBlank(product.getBarCode())) {
			String _suffix = FileUtils.getSuffix(imgFile.getOriginalFilename());
			String _photoName = product.getBarCode() + _suffix;

			product.setPhotoName(_photoName);
		}

		productJpaDao.save(product);

		// 保存用户上传相片
		fileManager.saveUploadFile(imgFile, sysConfig.getProductPhotoPath(), product.getPhotoName());
	}

	/**
	 * 更新商品信息
	 * 
	 * @param product 商品信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateProduct(Product product, MultipartFile imgFile) {

		Product _dbProduct = productJpaDao.findOne(product.getUuid());
		if (null == _dbProduct) {
			// 商品不存在!
			throw new ServiceException("ERR_MSG_PDU_007");
		}

		// 商品名称-汉字
		_dbProduct.setName(product.getName());
		// 批发价
		_dbProduct.setWholeSalePrice(product.getWholeSalePrice());
		// 零售价
		_dbProduct.setRetailPrice(product.getRetailPrice());
		// 会员价
		_dbProduct.setMemberPrice(product.getMemberPrice());
		// 商品详细描述
		_dbProduct.setDescTxt(product.getDescTxt());

		// 商品类型
		if (null != product.getProductTypeUuid()
				&& (null == _dbProduct.getProductType() || !_dbProduct.getProductType().getUuid()
						.equals(product.getProductTypeUuid()))) {

			ProductType _dbProductType = productTypeJpaDao.findOne(product.getProductTypeUuid());
			_dbProduct.setProductType(_dbProductType);
		}

		// 商品品牌
		if (null != product.getProductBrandUuid()
				&& (null == _dbProduct.getProductBrand() || !_dbProduct.getProductBrand().getUuid()
						.equals(product.getProductBrandUuid()))) {
			ProductBrand _dbProductBrand = productBrandJpaDao.findOne(product.getProductBrandUuid());
			_dbProduct.setProductBrand(_dbProductBrand);
		}

		// 商品供应商
		if (StringUtils.isNotBlank(product.getProductSupplierId())
				&& (null == _dbProduct.getProductSupplier() || !_dbProduct.getProductSupplier().getId()
						.equals(product.getProductSupplierId()))) {
			ProductSupplier _dbProductSupplier = productSupplierJpaDao.findById(product.getProductSupplierId());
			_dbProduct.setProductSupplier(_dbProductSupplier);
		}

		productJpaDao.save(_dbProduct);

		// 保存用户上传相片
		fileManager.saveUploadFile(imgFile, sysConfig.getProductPhotoPath(), _dbProduct.getPhotoName());
	}

	@Autowired
	public void setProductJpaDao(ProductJpaDao productJpaDao) {
		this.productJpaDao = productJpaDao;
	}

	@Autowired
	public void setProductTypeJpaDao(ProductTypeJpaDao productTypeJpaDao) {
		this.productTypeJpaDao = productTypeJpaDao;
	}

	@Autowired
	public void setProductBrandJpaDao(ProductBrandJpaDao productBrandJpaDao) {
		this.productBrandJpaDao = productBrandJpaDao;
	}

	@Autowired
	public void setProductSupplierJpaDao(ProductSupplierJpaDao productSupplierJpaDao) {
		this.productSupplierJpaDao = productSupplierJpaDao;
	}

	@Autowired
	public void setProductMyBatisDao(ProductMyBatisDao productMyBatisDao) {
		this.productMyBatisDao = productMyBatisDao;
	}

	@Autowired
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Autowired
	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

}