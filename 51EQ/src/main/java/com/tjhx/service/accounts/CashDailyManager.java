package com.tjhx.service.accounts;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.CashDailyJpaDao;
import com.tjhx.dao.accounts.CashDailyMyBatisDao;
import com.tjhx.dao.accounts.CashRunJpaDao;
import com.tjhx.dao.accounts.CashRunMyBatisDao;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.entity.accounts.CashRun;

@Service
@Transactional(readOnly = true)
public class CashDailyManager {
	@Resource
	private CashRunJpaDao cashRunJpaDao;
	@Resource
	private CashRunMyBatisDao cashRunMyBatisDao;
	@Resource
	private CashDailyJpaDao cashDailyJpaDao;
	@Resource
	private CashDailyMyBatisDao cashDailyMyBatisDao;

	/**
	 * 取得未日结销售流水日结信息
	 * 
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CashDaily> getAllNotCashDailyByOrgId(String orgId) {
		List<CashRun> _list = (List<CashRun>) cashRunJpaDao.getAllNotCashDailyByOrgId(orgId, new Sort(new Sort.Order(
				Sort.Direction.DESC, "optDate"), new Sort.Order(Sort.Direction.ASC, "jobType")));

		List<CashDaily> _noCashDailyList = new ArrayList<CashDaily>();
		String _tmpOptDate = "";
		CashDaily _tmpCashDaily = null;
		for (CashRun cashRun : _list) {
			if (!_tmpOptDate.equals(cashRun.getOptDate())) {// 新合计对象
				_tmpOptDate = cashRun.getOptDate();
				_tmpCashDaily = new CashDaily();
				_noCashDailyList.add(_tmpCashDaily);

				// 日期
				_tmpCashDaily.setOptDate(cashRun.getOptDate());
				_tmpCashDaily.setOptDateShow(cashRun.getOptDateShow());
				// 昨日余额
				_tmpCashDaily.setInitAmt(cashRun.getInitAmt());

			}
			// 当日销售（合计）
			_tmpCashDaily.setSaleAmt(_tmpCashDaily.getSaleAmt().add(cashRun.getSaleAmt()));
			// 实际现金-当日（合计）
			_tmpCashDaily.setCashAmt(_tmpCashDaily.getCashAmt().add(cashRun.getCashAmt()));
			// 刷卡金额-单据统计-当日（合计）
			_tmpCashDaily.setCardAmt(_tmpCashDaily.getCardAmt().add(cashRun.getCardAmt()));
			// 刷卡金额-电脑统计-当日（合计）
			_tmpCashDaily.setCardAmtBw(_tmpCashDaily.getCardAmtBw().add(cashRun.getCardAmtBw()));
			// 刷卡笔数-当日（合计）
			_tmpCashDaily.setCardNum(_tmpCashDaily.getCardNum() + cashRun.getCardNum());
			// 存款金额-当日（合计）
			_tmpCashDaily.setDepositAmt(_tmpCashDaily.getDepositAmt().add(cashRun.getDepositAmt()));
			// 留存金额-当日
			_tmpCashDaily.setRetainedAmt(cashRun.getRetainedAmt());
		}
		return _noCashDailyList;
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyyMMdd)
	 * @return
	 * @throws ParseException
	 */
	public List<CashDaily> getAllCashDailyByOrgId_1(String orgId, String currentDate) throws ParseException {
		String optDateY = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "yyyy");

		String optDateM = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "MM");

		return getAllCashDailyByOrgId(orgId, optDateY, optDateM);
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param orgId
	 * @param optDate(yyyy-MM)
	 * @return
	 * @throws ParseException
	 */
	public List<CashDaily> getAllCashDailyByOrgId_2(String orgId, String optDate) throws ParseException {
		String optDateY = DateUtils.transDateFormat(optDate, "yyyy-MM", "yyyy");

		String optDateM = DateUtils.transDateFormat(optDate, "yyyy-MM", "MM");

		return getAllCashDailyByOrgId(orgId, optDateY, optDateM);
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param orgId
	 * @param optDateY
	 * @param optDateM
	 * @return
	 * @throws ParseException
	 */
	private List<CashDaily> getAllCashDailyByOrgId(String orgId, String optDateY, String optDateM)
			throws ParseException {
		List<CashDaily> _list = (List<CashDaily>) cashDailyJpaDao.findByOrgId_OptDateY_OptDateM(orgId, optDateY,
				optDateM, new Sort(new Sort.Order(Sort.Direction.DESC, "optDate")));

		return _list;
	}

	/**
	 * 销售流水日结
	 * 
	 * @param optDate
	 * @param orgId
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public void cashDailyConfirm(String optDate, String orgId) {
		List<CashRun> _list = (List<CashRun>) cashRunJpaDao.getNotCashDailyByOrgId_OptDate(orgId, optDate, new Sort(
				new Sort.Order(Sort.Direction.ASC, "jobType")));

		CashDaily _cashDaily = null;
		boolean firstFlg = true;
		for (CashRun cashRun : _list) {
			if (firstFlg) {
				firstFlg = false;
				_cashDaily = new CashDaily();

				// 机构编号
				_cashDaily.setOrgId(orgId);
				// 日期
				_cashDaily.setOptDate(cashRun.getOptDate());
				// 日期-显示
				_cashDaily.setOptDateShow(cashRun.getOptDateShow());
				// 日期-年
				_cashDaily.setOptDateY(cashRun.getOptDateY());
				// 日期-月
				_cashDaily.setOptDateM(cashRun.getOptDateM());
				// 昨日余额
				_cashDaily.setInitAmt(cashRun.getInitAmt());

			}
			// 当日销售（合计）
			_cashDaily.setSaleAmt(_cashDaily.getSaleAmt().add(cashRun.getSaleAmt()));
			// 实际现金-当日（合计）
			_cashDaily.setCashAmt(_cashDaily.getCashAmt().add(cashRun.getCashAmt()));
			// 刷卡金额-单据统计-当日（合计）
			_cashDaily.setCardAmt(_cashDaily.getCardAmt().add(cashRun.getCardAmt()));
			// 刷卡金额-电脑统计-当日（合计）
			_cashDaily.setCardAmtBw(_cashDaily.getCardAmtBw().add(cashRun.getCardAmtBw()));
			// 刷卡笔数-当日（合计）
			_cashDaily.setCardNum(_cashDaily.getCardNum() + cashRun.getCardNum());
			// 存款金额-当日（合计）
			_cashDaily.setDepositAmt(_cashDaily.getDepositAmt().add(cashRun.getDepositAmt()));
			// 留存金额-当日
			_cashDaily.setRetainedAmt(cashRun.getRetainedAmt());

			// 修改销售流水标记
			cashRun.setDailyFlg(true);
			cashRunJpaDao.save(cashRun);
		}
		// 生成销售日结信息
		cashDailyJpaDao.save(_cashDaily);
	}

	/**
	 * 日结销售流水明细查看
	 * 
	 * @param optDate
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CashRun> cashDailyDetail(String optDate, String orgId) {
		return (List<CashRun>) cashRunJpaDao.findByOrgId_OptDate(orgId, optDate, new Sort(new Sort.Order(
				Sort.Direction.ASC, "jobType")));
	}

	/**
	 * 日结销售流水明细查看
	 * 
	 * @param cashRun
	 * @return
	 */
	public List<CashRun> cashDailyDetail(CashRun cashRun) {
		return cashRunMyBatisDao.getCashRunList(cashRun);
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param cashDaily
	 * @return
	 */
	public List<CashDaily> searchReportList(CashDaily cashDaily) {
		return cashDailyMyBatisDao.getCashDailyList(cashDaily);
	}
}
