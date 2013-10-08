package com.tjhx.service.info;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.StoreDayTotalJpaDao;
import com.tjhx.dao.info.StoreDetailJpaDao;
import com.tjhx.dao.info.StoreDetailMyBatisDao;
import com.tjhx.daobw.StoreMyBatisDao;
import com.tjhx.entity.bw.Store;
import com.tjhx.entity.info.StoreDayTotal;
import com.tjhx.entity.info.StoreDetail;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class StoreDetailManager {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(StoreDetailManager.class);

	@Resource
	private OrganizationManager orgManager;
	@Resource
	private StoreMyBatisDao storeMyBatisDao;
	@Resource
	private StoreDetailJpaDao storeDetailJpaDao;
	@Resource
	private StoreDetailMyBatisDao storeDetailMyBatisDao;
	@Resource
	private StoreDayTotalJpaDao storeDayTotalJpaDao;

	/**
	 * 取得门店机构（不包含总部机构）
	 * 
	 * @return
	 */
	private List<Organization> getSubOrganization() {
		List<Organization> _orgList = orgManager.getAllOrganization();

		_orgList.remove(new Organization(Constants.ROOT_ORG_ID));

		return _orgList;
	}

	/**
	 * 取得门店库存信息
	 */
	@Transactional(readOnly = false)
	public synchronized void calOrgStoreDetail() {
		List<Organization> _orgList = getSubOrganization();
		// ----------------------------------------
		// for (int i = 0; i < 2; i++) {
		// Organization org = _orgList.get(i);
		// ----------------------------------------
		for (Organization org : _orgList) {
			List<Store> bwStoreList = storeMyBatisDao.getStoreInfoList(org.getBwBranchNo());

			int _index = 0;
			for (Store bwStore : bwStoreList) {
				StoreDetail storeDetail = new StoreDetail();

				// 机构编号
				storeDetail.setOrgId(org.getId());
				// 机构资金-百威
				storeDetail.setBwBranchNo(org.getBwBranchNo());
				// 日期
				String optDate = DateUtils.getCurrentDateShortStr();
				String optDateY = DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy");
				String optDateM = DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM");

				storeDetail.setOptDate(optDate);
				// 日期-年
				storeDetail.setOptDateY(optDateY);
				// 日期-月
				storeDetail.setOptDateM(optDateM);
				// 库存标记 0-正库存 1-负库存
				storeDetail.setStorageFlg(bwStore.getStockQty().compareTo(BigDecimal.ZERO) == 1 ? "0" : "1");
				// Index
				storeDetail.setIndex(++_index);
				// 货号
				storeDetail.setItemSubno(bwStore.getItemSubno());
				// 条形码
				storeDetail.setItemBarcode(bwStore.getItemBarcode());
				// 商品名称
				storeDetail.setItemName(bwStore.getItemName());
				// 库存数量
				storeDetail.setStockQty(bwStore.getStockQty());
				// 库存金额
				storeDetail.setStockAmt(bwStore.getStockAmt());
				// 售价金额
				storeDetail.setItemSaleAmt(bwStore.getItemSaleAmt());

				storeDetailJpaDao.save(storeDetail);
			}

		}

	}

	/**
	 * 计算门店库存合计信息
	 */
	@Transactional(readOnly = false)
	public void calOrgStoreDayTotal() {
		List<Organization> _orgList = getSubOrganization();
		for (Organization org : _orgList) {
			// 日期
			String optDate = DateUtils.getCurrentDateShortStr();
			StoreDetail _param = new StoreDetail();
			_param.setBwBranchNo(org.getBwBranchNo());
			_param.setOptDate(optDate);
			List<StoreDetail> _dbDayTotal = storeDetailMyBatisDao.getDayTotalList(_param);

			for (StoreDetail storeDetail : _dbDayTotal) {
				StoreDayTotal _storeDayTotal = new StoreDayTotal();

				// 机构编号
				_storeDayTotal.setOrgId(storeDetail.getOrgId());
				// 机构资金-百威
				_storeDayTotal.setBwBranchNo(storeDetail.getBwBranchNo());
				// 日期
				_storeDayTotal.setOptDate(storeDetail.getOptDate());
				// 日期-年
				_storeDayTotal.setOptDateY(storeDetail.getOptDateY());
				// 日期-月
				_storeDayTotal.setOptDateM(storeDetail.getOptDateM());
				// 库存标记 0-正库存 1-负库存
				_storeDayTotal.setStorageFlg(storeDetail.getStorageFlg());
				// 库存数量
				_storeDayTotal.setStockTotalQty(storeDetail.getStockTotalQty());
				// 库存金额
				_storeDayTotal.setStockTotalAmt(storeDetail.getStockTotalAmt());
				// 售价金额
				_storeDayTotal.setItemSaleTotalAmt(storeDetail.getItemSaleTotalAmt());

				storeDayTotalJpaDao.save(_storeDayTotal);
			}
		}

	}
}