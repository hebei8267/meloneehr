package com.tjhx.service.accounts;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.StorageRunJpaDao;
import com.tjhx.dao.info.SupplierJpaDao;
import com.tjhx.entity.accounts.StorageRun;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.member.User;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class StorageRunManager {
	@Resource
	private StorageRunJpaDao storageRunJpaDao;
	@Resource
	private SupplierJpaDao supplierJpaDao;

	/**
	 * 取得所有货物入库流水信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyyMMdd)
	 * @return 货物入库流水信息列表
	 * @throws ParseException
	 */
	public List<StorageRun> getAllStorageRunByOrgId_1(String orgId, String currentDate) throws ParseException {
		String recordDateY = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "yyyy");

		String recordDateM = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "MM");

		return getAllStorageRunByOrgId(orgId, recordDateY, recordDateM);
	}

	/**
	 * 取得所有货物入库流水信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyy-MM)
	 * @return 货物入库流水信息列表
	 * @throws ParseException
	 */
	public List<StorageRun> getAllStorageRunByOrgId_2(String orgId, String currentDate) throws ParseException {
		String recordDateY = DateUtils.transDateFormat(currentDate, "yyyy-MM", "yyyy");

		String recordDateM = DateUtils.transDateFormat(currentDate, "yyyy-MM", "MM");

		return getAllStorageRunByOrgId(orgId, recordDateY, recordDateM);
	}

	@SuppressWarnings("unchecked")
	private List<StorageRun> getAllStorageRunByOrgId(String orgId, String recordDateY, String recordDateM)
			throws ParseException {

		List<StorageRun> _list = (List<StorageRun>) storageRunJpaDao.findByOrgId_RecordDateY_RecordDateM(orgId,
				recordDateY, recordDateM, new Sort(new Sort.Order(Sort.Direction.DESC, "recordDate")));

		for (StorageRun storageRun : _list) {
			storageRun.autoSetEditFlg();
		}

		return _list;
	}

	/**
	 * 根据编号取得货物入库流水信息
	 * 
	 * @param uuid 货物入库流水编号
	 * @return 货物入库流水信息
	 */
	public StorageRun getStorageRunByUuid(Integer uuid) {
		return storageRunJpaDao.findOne(uuid);
	}

	/**
	 * 删除货物入库流水信息
	 * 
	 * @param uuid 货物入库流水编号
	 */
	@Transactional(readOnly = false)
	public void delStorageRunByUuid(Integer uuid) {
		storageRunJpaDao.delete(uuid);
	}

	/**
	 * 添加新货物入库流水信息
	 * 
	 * @param storageRun 货物入库流水信息
	 */
	@Transactional(readOnly = false)
	public void addNewStorageRun(StorageRun storageRun, User user) {

		StorageRun _dbStorageRun = storageRunJpaDao.findByOrgIdAndRecordNo(user.getOrganization().getId(),
				storageRun.getRecordNo());
		// 该货物入库流水已存在!
		if (null != _dbStorageRun) {
			throw new ServiceException("ERR_MSG_STORAGE_RUN_001");
		}

		// 机构编号
		storageRun.setOrgId(user.getOrganization().getId());
		// 供应商
		Supplier supplier = supplierJpaDao.findBySupplierBwId(storageRun.getSupplierBwId());
		storageRun.setSupplier(supplier);
		// 开单日期
		String recordDate = DateUtils.transDateFormat(storageRun.getRecordDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		storageRun.setRecordDate(recordDate);
		// 开单日期-年
		storageRun.setRecordDateY(DateUtils.transDateFormat(recordDate, "yyyyMMdd", "yyyy"));
		// 开单日期-月
		storageRun.setRecordDateM(DateUtils.transDateFormat(recordDate, "yyyyMMdd", "MM"));
		// 入货日期
		String intoDate = DateUtils.transDateFormat(storageRun.getIntoDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		storageRun.setIntoDate(intoDate);
		// 入货日期-年
		storageRun.setIntoDateY(DateUtils.transDateFormat(intoDate, "yyyyMMdd", "yyyy"));
		// 入货日期-月
		storageRun.setIntoDateM(DateUtils.transDateFormat(intoDate, "yyyyMMdd", "MM"));

		storageRunJpaDao.save(storageRun);
	}

	/**
	 * 更新货物入库流水信息
	 * 
	 * @param storageRun 货物入库流水信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateStorageRun(StorageRun storageRun) throws IllegalAccessException, InvocationTargetException {
		// //
		// ----------------------------------------------------------------------------
		// // TODO 修改开始
		// StorageRun _dbStorageRun =
		// storageRunJpaDao.findOne(storageRun.getUuid());
		// if (null == _dbStorageRun) {
		// // 货物入库流水不存在!
		// throw new ServiceException("?????????????????");
		// }
		//
		// _dbStorageRun.setName(storageRun.getName());
		// _dbStorageRun.setDescTxt(storageRun.getDescTxt());
		//
		// //
		// ----------------------------------------------------------------------------
		// storageRunJpaDao.save(_dbStorageRun);
	}
}