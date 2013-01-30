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
import com.tjhx.dao.accounts.CashDailyJpaDao;
import com.tjhx.dao.accounts.CashRunJpaDao;
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.entity.member.User;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class CashRunManager {
	@Resource
	private CashRunJpaDao cashRunJpaDao;

	@Resource
	private CashDailyJpaDao cashDailyJpaDao;

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
				new Sort(new Sort.Order(Sort.Direction.DESC, "createDate")));

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
		String _date = DateUtils.transDateFormat(cashRun.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");

		CashRun _dbCashRun = cashRunJpaDao.findByOrgId_OptDate_JobType(user.getOrganization().getId(), _date,
				cashRun.getJobType());
		// 该现金流水已存在!
		if (null != _dbCashRun) {
			throw new ServiceException("ERR_MSG_CASH_RUN_001");
		}

		// 机构编号
		cashRun.setOrgId(user.getOrganization().getId());
		// 日期
		cashRun.setOptDate(_date);
		// 日期-年
		cashRun.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 日期-月
		cashRun.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 班前余额 TODO
		//cashRun.setInitAmt(getInitAmt(user.getOrganization().getId(), _date));
		// 留存金额-交班时=实际现金-存款银行 TODO
		//cashRun.setRetainedAmt(cashRun.getCashAmt().subtract(cashRun.getDepositAmt()));

		cashRunJpaDao.save(cashRun);
	}

	@SuppressWarnings("unchecked")
	public BigDecimal getInitAmt(String orgId, String optDate) {
		// 取本日前班次余额（现金）信息
		List<CashRun> _list = (List<CashRun>) cashRunJpaDao.findByOrgId_OptDate(orgId, optDate, new Sort(
				new Sort.Order(Sort.Direction.DESC, "createDate")));

		if (null != _list && _list.size() > 0) {// 返回本日前班次余额（现金）信息
			return ((CashRun) _list.get(0)).getRetainedAmt();
		} else {// 返回昨日日结余额（现金）信息
			return cashDailyJpaDao.findByOrgId_OptDate(orgId, optDate).getRetainedAmt();
		}
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
		
//		// 机构编号 
//				orgId;
//				// 日期 
//				optDate;
//				// 日期-显示 
//				optDateShow;
//				// 日期-年 
//				optDateY;
//				// 日期-月 
//				optDateM;
//				// 上班类型(1早班、2晚班、4全天班) 
//				jobType;
//				// 班前余额 
//				initAmt ;
//				// 当前销售 
//				saleAmt ;
//				// 实际现金-交班时 
//				cashAmt ;
//				// 刷卡金额-单据统计 
//				cardAmt ;
//				// 刷卡金额-电脑统计 
//				cardAmtBw ;
//				// 刷卡笔数 
//				cardNum = 0;
//				// 存款金额 
//				depositAmt ;
//				// 存款人 
//				depositor;
//				// 存款银行（选择） 
//				bankId;
//				// 卡号（选择） 
//				bankCardNo;
//				// 留存金额-交班时 
//				retainedAmt ;
//				// 备注 
//				descTxt;
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