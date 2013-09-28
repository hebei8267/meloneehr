package com.tjhx.service.affair;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.PettyCashJpaDao;
import com.tjhx.entity.affair.PettyCash;
import com.tjhx.entity.member.User;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class PettyCashManager {
	@Resource
	private PettyCashJpaDao pettyCashJpaDao;

	/**
	 * 取得 门店备用金
	 * 
	 * @param uuid
	 * @return
	 */
	public PettyCash getPettyCashByOptUid(String optUid) {
		return pettyCashJpaDao.findByOptUid(optUid);
	}

	/**
	 * 新增 门店备用金（支出类型）
	 * 
	 * @param pettyCash
	 * @param userInfo
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void addNewPettyCash_Pay(PettyCash pettyCash, User user) throws ParseException {

		checkNewPettyCash_Pay(pettyCash);

		String _date = DateUtils.transDateFormat(pettyCash.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		// 业务日期
		pettyCash.setOptDate(_date);
		// 业务日期-年
		pettyCash.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 业务日期-月
		pettyCash.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 业务日期-对应的星期
		pettyCash.setWeek(DateUtils.getWeekOfDate(_date, "yyyyMMdd"));
		// 操作金额-支出为负数，拨入为正数
		pettyCash.setOptAmt(pettyCash.getOptAmtShow().multiply(new BigDecimal(-1)));
		// 机构编号
		pettyCash.setOrgId(user.getOrganization().getId());

		pettyCashJpaDao.save(pettyCash);

		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());

	}

	/**
	 * 检查门店备用金信息是否存在
	 * 
	 * @param pettyCash
	 */
	private void checkNewPettyCash_Pay(PettyCash pettyCash) {
		PettyCash _dbPettyCash = getPettyCashByOptUid(pettyCash.getOptUid());
		// 该门店备用金信息已存在!
		if (null != _dbPettyCash) {
			throw new ServiceException("ERR_MSG_PETTY_CASH_001");
		}
	}

	/**
	 * 更新 门店备用金（支出类型）
	 * 
	 * @param pettyCash
	 * @param userInfo
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void updateNewPettyCash_Pay(PettyCash pettyCash, User user) throws ParseException {
		PettyCash _dbPettyCash = pettyCashJpaDao.findOne(pettyCash.getUuid());
		// 该门店备用金信息不存在
		if (null == _dbPettyCash) {
			throw new ServiceException("ERR_MSG_PETTY_CASH_002");
		}

		// 业务日期
		String _date = DateUtils.transDateFormat(pettyCash.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		_dbPettyCash.setOptDate(_date);
		// 业务日期-显示
		_dbPettyCash.setOptDateShow(pettyCash.getOptDateShow());
		// 业务日期-年
		_dbPettyCash.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 业务日期-月
		_dbPettyCash.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 业务日期-对应的星期
		_dbPettyCash.setWeek(DateUtils.getWeekOfDate(_date, "yyyyMMdd"));
		// 操作金额-支出为负数，拨入为正数
		_dbPettyCash.setOptAmt(pettyCash.getOptAmtShow().multiply(new BigDecimal(-1)));
		// 操作金额-全部为正数
		_dbPettyCash.setOptAmtShow(pettyCash.getOptAmtShow());
		// 支出事项
		_dbPettyCash.setExpReason(pettyCash.getExpReason());
		// 备注
		_dbPettyCash.setDescTxt(pettyCash.getDescTxt());

		pettyCashJpaDao.save(_dbPettyCash);

		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());
	}

	/**
	 * 取得门店备用金信息列表
	 * 
	 * @param orgId 机构编号
	 * @return
	 */
	public List<PettyCash> getPettyCashListByOrgId(String orgId) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		// 门店备用金可查看天数
		int editDays = sysConfig.getPettyCashEditDays();
		int viewDays = sysConfig.getPettyCashViewDays() * -1;
		Date beforeDate = DateUtils.getNextDateFormatDate(viewDays);

		List<PettyCash> _list = pettyCashJpaDao.findByOrgId(orgId, beforeDate, new Sort(new Sort.Order(
				Sort.Direction.DESC, "optDate"), new Sort.Order(Sort.Direction.DESC, "uuid")));

		setEditFlg_PettyCashList(_list, editDays);

		return _list;
	}

	/**
	 * 设置备用金的可编辑标记
	 * 
	 * @param pettyCashList
	 * @param editDays
	 */
	private void setEditFlg_PettyCashList(List<PettyCash> pettyCashList, int editDays) {
		for (PettyCash pettyCash : pettyCashList) {
			long spanDay = DateUtils.getDateSpanDay(pettyCash.getCreateDate(), DateUtils.getCurrentDate());
			if (spanDay <= editDays) {
				pettyCash.setEditFlg(true);
			}
		}

	}

	/**
	 * 删除门店备用金信息
	 * 
	 * @param parseInt
	 */
	@Transactional(readOnly = false)
	public void delPettyCashByUuid(String[] idArray, User user) {
		for (int i = 0; i < idArray.length; i++) {
			pettyCashJpaDao.delete(Integer.parseInt(idArray[i]));
		}
		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());
	}

	/**
	 * 取得门店备用金信息
	 * 
	 * @param uuid
	 * @return
	 */
	public PettyCash getPettyCashByUuid(Integer uuid) {
		return pettyCashJpaDao.findOne(uuid);
	}

	/**
	 * 新增 门店备用金（拨入类型）
	 * 
	 * @param pettyCash
	 * @param userInfo
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void addNewPettyCash_Income(PettyCash pettyCash, User user) throws ParseException {
		// 业务日期
		String _date = DateUtils.transDateFormat(pettyCash.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		// 业务日期
		pettyCash.setOptDate(_date);
		// 业务日期-年
		pettyCash.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 业务日期-月
		pettyCash.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 业务日期-对应的星期
		pettyCash.setWeek(DateUtils.getWeekOfDate(_date, "yyyyMMdd"));
		// 操作金额-支出为负数，拨入为正数
		pettyCash.setOptAmt(pettyCash.getOptAmtShow());
		// 机构编号
		pettyCash.setOrgId(user.getOrganization().getId());

		pettyCashJpaDao.save(pettyCash);

		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());
	}

	/**
	 * 更新 门店备用金（拨入类型）
	 * 
	 * @param pettyCash
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void updateNewPettyCash_Income(PettyCash pettyCash, User user) throws ParseException {
		PettyCash _dbPettyCash = pettyCashJpaDao.findOne(pettyCash.getUuid());
		// 该门店备用金信息不存在
		if (null == _dbPettyCash) {
			throw new ServiceException("ERR_MSG_PETTY_CASH_002");
		}

		// 业务日期
		String _date = DateUtils.transDateFormat(pettyCash.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		_dbPettyCash.setOptDate(_date);
		// 业务日期-显示
		_dbPettyCash.setOptDateShow(pettyCash.getOptDateShow());
		// 业务日期-年
		_dbPettyCash.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 业务日期-月
		_dbPettyCash.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 业务日期-对应的星期
		_dbPettyCash.setWeek(DateUtils.getWeekOfDate(_date, "yyyyMMdd"));
		// 操作金额-支出为负数，拨入为正数
		_dbPettyCash.setOptAmt(pettyCash.getOptAmtShow());
		// 操作金额-全部为正数
		_dbPettyCash.setOptAmtShow(pettyCash.getOptAmtShow());

		// 拨入来源 1-正常拨入 2-非常拨入 4-会计调帐
		_dbPettyCash.setIncomeSource(pettyCash.getIncomeSource());

		// 备注
		_dbPettyCash.setDescTxt(pettyCash.getDescTxt());

		pettyCashJpaDao.save(_dbPettyCash);

		// 计算备用金余额
		calBalanceAmt(user.getOrganization().getId());

	}

	/**
	 * 计算备用金余额
	 */
	@Transactional(readOnly = false)
	private void calBalanceAmt(String orgId) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		// 门店备用金重计算天数
		int calDays = sysConfig.getPettyCashCalculateDays();
		Date beforeDate = DateUtils.getNextDateFormatDate(calDays * -1);
		List<PettyCash> _list = pettyCashJpaDao.findByOrgId(orgId, beforeDate, new Sort(new Sort.Order(
				Sort.Direction.ASC, "optDate"), new Sort.Order(Sort.Direction.ASC, "uuid")));

		BigDecimal _tmpBalanceAmt = null;
		for (int i = 0; i < _list.size(); i++) {
			PettyCash pettyCash = _list.get(i);
			if (i == 0) {
				_tmpBalanceAmt = pettyCash.getBalanceAmt();
				continue;// 第一条记录不计算备用金余额
			}
			_tmpBalanceAmt = _tmpBalanceAmt.add(pettyCash.getOptAmt());
			pettyCash.setBalanceAmt(_tmpBalanceAmt);
			pettyCashJpaDao.save(pettyCash);

		}
	}

	/**
	 * 根据查询条件 取得指定门店备用金余额（指定时间段内）
	 * 
	 * @param orgId 门店编号
	 * @param optDate_start 开始时间(yyyyMMdd)
	 * @param optDate_end 结束时间(yyyyMMdd)
	 * @return
	 */
	public List<PettyCash> searchPettyCashList(String orgId, String optDate_start, String optDate_end) {
		List<PettyCash> _list = pettyCashJpaDao.findByOrgIdAndOptDateInterval(orgId, optDate_start, optDate_end,
				new Sort(new Sort.Order(Sort.Direction.DESC, "optDate"), new Sort.Order(Sort.Direction.ASC, "uuid")));
		return _list;
	}
}
