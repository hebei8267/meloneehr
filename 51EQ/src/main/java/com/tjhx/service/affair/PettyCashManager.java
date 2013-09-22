package com.tjhx.service.affair;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.PettyCashJpaDao;
import com.tjhx.entity.affair.PettyCash;
import com.tjhx.entity.member.User;
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
	public void updateNewPettyCash_Pay(PettyCash pettyCash) throws ParseException {
		PettyCash _dbPettyCash = getPettyCashByOptUid(pettyCash.getOptUid());
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
	}

	/**
	 * 取得门店备用金信息列表
	 * 
	 * @param orgId 机构编号
	 * @return
	 */
	public List<PettyCash> getPettyCashListByOrgId(String orgId) {
		// TODO ??????????????
		List<PettyCash> _list = pettyCashJpaDao.findByOrgId(orgId, new Sort(new Sort.Order(Sort.Direction.DESC,
				"optDate")));
		return _list;
	}

	/**
	 * 删除门店备用金信息
	 * 
	 * @param parseInt
	 */
	@Transactional(readOnly = false)
	public void delPettyCashByUuid(Integer uuid) {
		pettyCashJpaDao.delete(uuid);
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

}
