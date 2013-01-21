package com.tjhx.service.accounts;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.CashRunJpaDao;
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.entity.member.User;

@Service
@Transactional(readOnly = true)
public class CashRunManager {
	@Resource
	private CashRunJpaDao cashRunJpaDao;

	/**
	 * 取得所有现金流水信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyyMMdd)
	 * 
	 * @return 现金流水信息列表
	 * @throws ParseException
	 */
	public List<CashRun> getAllCashRunByOrgId_1(String orgId, String currentDate) throws ParseException {
		String optDateY = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "yyyy");

		String optDateM = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "MM");
		return getAllCashRunByOrgId(orgId, optDateY, optDateM);
	}

	/**
	 * 取得所有现金流水信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyy-MM)
	 * @return 现金流水信息列表
	 * @throws ParseException
	 */
	public List<CashRun> getAllCashRunByOrgId_2(String orgId, String currentDate) throws ParseException {
		String optDateY = DateUtils.transDateFormat(currentDate, "yyyy-MM", "yyyy");

		String optDateM = DateUtils.transDateFormat(currentDate, "yyyy-MM", "MM");
		return getAllCashRunByOrgId(orgId, optDateY, optDateM);
	}

	/**
	 * @param orgId
	 * @param optDateY
	 * @param optDateM
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	private List<CashRun> getAllCashRunByOrgId(String orgId, String optDateY, String optDateM) throws ParseException {
		List<CashRun> _list = (List<CashRun>) cashRunJpaDao.findByOrgId_OptDateY_OptDateM(orgId, optDateY, optDateM,
				new Sort(new Sort.Order(Sort.Direction.DESC, "optDate")));

		for (CashRun cashRun : _list) {
			cashRun.autoSetEditFlg();
		}

		return _list;
	}

	/**
	 * 根据编号取得现金流水信息
	 * 
	 * @param uuid 现金流水编号
	 * @return 现金流水信息
	 */
	public CashRun getCashRunByUuid(Integer uuid) {
		return cashRunJpaDao.findOne(uuid);
	}

	/**
	 * 删除现金流水信息
	 * 
	 * @param uuid 现金流水编号
	 */
	@Transactional(readOnly = false)
	public void delCashRunByUuid(Integer uuid) {
		cashRunJpaDao.delete(uuid);
	}

	/**
	 * 添加新现金流水信息
	 * 
	 * @param cashRun 现金流水信息
	 */
	@Transactional(readOnly = false)
	public void addNewCashRun(CashRun cashRun, User user) {
		// //----------------------------------------------------------------------------
		// // TODO 修改开始
		// CashRun _dbCashRun = findByName(cashRun.getName());
		// // 该现金流水已存在!
		// if (null != _dbCashRun) {
		// throw new ServiceException("?????????????????");
		// }
		// //----------------------------------------------------------------------------
		// cashRunJpaDao.save(cashRun);
	}

	/**
	 * 更新现金流水信息
	 * 
	 * @param cashRun 现金流水信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateCashRun(CashRun cashRun, User user) throws IllegalAccessException, InvocationTargetException {
		// //----------------------------------------------------------------------------
		// // TODO 修改开始
		// CashRun _dbCashRun = cashRunJpaDao.findOne(cashRun.getUuid());
		// if (null == _dbCashRun) {
		// // 现金流水不存在!
		// throw new ServiceException("?????????????????");
		// }
		//
		// _dbCashRun.setName(cashRun.getName());
		// _dbCashRun.setDescTxt(cashRun.getDescTxt());
		//
		// //----------------------------------------------------------------------------
		// cashRunJpaDao.save(_dbCashRun);
	}

	/**
	 * 计算班前余额
	 * 
	 * @param dateStr(yyyyMMdd)
	 * @return
	 */
	public BigDecimal calInitAmt(String orgId, String dateStr) {
		return cashRunJpaDao.calInitAmt(orgId, dateStr);
	}
}