package com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.info.GoodsJpaDao;
import com.tjhx.daobw.ItemGoodsMyBatisDao;
import com.tjhx.entity.bw.ItemGoods;
import com.tjhx.entity.info.Goods;

@Service
@Transactional(readOnly = true)
public class GoodsManager {
	@Resource
	private ItemGoodsMyBatisDao itemGoodsMyBatisDao;
	@Resource
	private GoodsJpaDao goodsJpaDao;

	/**
	 * 取得商品数量
	 * 
	 * @return
	 */
	public Long getGoodsCount() {
		return goodsJpaDao.getGoodsCount();
	}

	/**
	 * 同步百威商品信息
	 */
	@Transactional(readOnly = false)
	public void bwDataSyn() {
		List<ItemGoods> _bwList = itemGoodsMyBatisDao.getItemGoodsList();
		List<Goods> _goodsList = (List<Goods>) goodsJpaDao.findAll();

		for (ItemGoods _itemGoods : _bwList) {
			// 见ItemGoods.java的equals实现
			if (_goodsList.contains(_itemGoods)) {
				// 更新商品信息
				updateGoodsInfo(_itemGoods);
			} else {
				// 新增商品信息
				addGoodsInfo(_itemGoods);
			}
		}
	}

	/**
	 * 新增商品信息
	 * 
	 * @param itemGoods
	 */
	@Transactional(readOnly = false)
	private void addGoodsInfo(ItemGoods _itemGoods) {
		Goods _goods = new Goods();

		// 短条码
		_goods.setSubno(_itemGoods.getItemSubno());
		// 长条码
		_goods.setBarcode(_itemGoods.getItemBarcode());
		// 商品名称
		_goods.setName(_itemGoods.getItemName());
		// 商品名称-拼音缩写
		_goods.setPyName(_itemGoods.getItemSubname());

		goodsJpaDao.save(_goods);

	}

	/**
	 * 更新商品信息
	 * 
	 * @param itemGoods
	 */
	@Transactional(readOnly = false)
	private void updateGoodsInfo(ItemGoods _itemGoods) {
		// 根据短条码查找商品信息
		Goods _goods = goodsJpaDao.findBySubno(_itemGoods.getItemSubno());
		// 长条码
		_goods.setBarcode(_itemGoods.getItemBarcode());
		// 商品名称
		_goods.setName(_itemGoods.getItemName());
		// 商品名称-拼音缩写
		_goods.setPyName(_itemGoods.getItemSubname());

		goodsJpaDao.save(_goods);

	}

}
