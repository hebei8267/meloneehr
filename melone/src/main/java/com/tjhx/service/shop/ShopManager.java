package com.tjhx.service.shop;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.jpa.shop.ShopJpaDao;
import com.tjhx.dao.jpa.shop.StoreJpaDao;
import com.tjhx.dao.myBatis.shop.ShopMyBatisDao;
import com.tjhx.entity.shop.Shop;
import com.tjhx.entity.shop.Store;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ShopManager {
	private ShopJpaDao shopJpaDao;
	private StoreJpaDao storeJpaDao;
	private ShopMyBatisDao shopMyBatisDao;

	/**
	 * 取得所有门店信息
	 * 
	 * @return 门店信息列表
	 */
	public List<Shop> getAllShop() {
		return (List<Shop>) shopJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 取得门店信息(根据参数)
	 * 
	 * @param shop
	 * @return
	 */
	public List<Shop> getShopList(Shop shop) {
		return shopMyBatisDao.getShopList(shop);
	}

	/**
	 * 根据编号取得门店信息
	 * 
	 * @param uuid 门店编号
	 * @return 门店信息
	 */
	public Shop getShopByUuid(Integer uuid) {
		return shopJpaDao.findOne(uuid);
	}

	/**
	 * 删除门店信息
	 * 
	 * @param uuid 门店编号
	 */
	@Transactional(readOnly = false)
	public void delShopByUuid(Integer uuid) {
		shopJpaDao.delete(uuid);
	}

	/**
	 * 添加新门店信息
	 * 
	 * @param shop 门店信息
	 */
	@Transactional(readOnly = false)
	public void addNewShop(Shop shop) {

		Shop _dbShop = shopJpaDao.findById(shop.getId());
		// 该门店已存在!
		if (null != _dbShop) {
			throw new ServiceException("ERR_MSG_CFG_004");
		}

		// 设置仓库信息
		Store _dbStore = storeJpaDao.findById(shop.getStoreId());
		shop.setStore(_dbStore);

		shopJpaDao.save(shop);
	}

	/**
	 * 更新门店信息
	 * 
	 * @param shop 门店信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateShop(Shop shop) throws IllegalAccessException, InvocationTargetException {

		Shop _dbShop = shopJpaDao.findOne(shop.getUuid());
		if (null == _dbShop) {
			// 门店不存在!
			throw new ServiceException("ERR_MSG_CFG_005");
		}

		// 门店名称-汉字
		_dbShop.setName(shop.getName());
		// 门店电话号码
		_dbShop.setTelNum(shop.getTelNum());
		// 门店地址
		_dbShop.setAddr(shop.getAddr());
		// 门店传真号码
		_dbShop.setFaxNum(shop.getFaxNum());
		// 门店详细描述
		_dbShop.setDescTxt(shop.getDescTxt());
		// 仓库信息
		Store _dbStore = storeJpaDao.findById(shop.getStoreId());
		_dbShop.setStore(_dbStore);

		shopJpaDao.save(_dbShop);
	}

	@Autowired
	public void setShopJpaDao(ShopJpaDao shopJpaDao) {
		this.shopJpaDao = shopJpaDao;
	}

	@Autowired
	public void setStoreJpaDao(StoreJpaDao storeJpaDao) {
		this.storeJpaDao = storeJpaDao;
	}

	@Autowired
	public void setShopMyBatisDao(ShopMyBatisDao shopMyBatisDao) {
		this.shopMyBatisDao = shopMyBatisDao;
	}

}