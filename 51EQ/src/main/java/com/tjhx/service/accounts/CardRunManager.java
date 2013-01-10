package com.tjhx.service.accounts;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.CardRunJpaDao;
import com.tjhx.entity.accounts.CardRun;
import com.tjhx.entity.member.User;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class CardRunManager {
	@Resource
	private CardRunJpaDao cardRunJpaDao;

	/**
	 * 取得所有刷卡流水信息
	 * 
	 * @return 刷卡流水信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<CardRun> getAllCardRun() {
		return (List<CardRun>) cardRunJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "optDate")));
	}

	/**
	 * 根据编号取得刷卡流水信息
	 * 
	 * @param uuid 刷卡流水编号
	 * @return 刷卡流水信息
	 */
	public CardRun getCardRunByUuid(Integer uuid) {
		return cardRunJpaDao.findOne(uuid);
	}

	/**
	 * 删除刷卡流水信息
	 * 
	 * @param uuid 刷卡流水编号
	 */
	@Transactional(readOnly = false)
	public void delCardRunByUuid(Integer uuid) {
		cardRunJpaDao.delete(uuid);
	}

	/**
	 * 添加新刷卡流水信息
	 * 
	 * @param cardRun 刷卡流水信息
	 */
	@Transactional(readOnly = false)
	public void addNewCardRun(CardRun cardRun, User user) {

		String _date = DateUtils.transDateFormat(cardRun.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");

		CardRun _dbCardRun = cardRunJpaDao.findByOrgIdAndOptDate(user.getOrganization().getId(), _date);
		// 该刷卡流水已存在!
		if (null != _dbCardRun) {
			throw new ServiceException("ERR_MSG_CARD_RUN_001");
		}

		// 机构编号
		cardRun.setOrgId(user.getOrganization().getId());
		// 刷卡汇总日期
		cardRun.setOptDate(_date);
		// 刷卡汇总日期-年
		cardRun.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 刷卡汇总日期-月
		cardRun.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 盈亏金额=单据统计-电脑统计
		cardRun.setProfitAmt(cardRun.getRecordStatisAmt().subtract(cardRun.getBwStatisAmt()));
		
		cardRunJpaDao.save(cardRun);
	}

	/**
	 * 更新刷卡流水信息
	 * 
	 * @param cardRun 刷卡流水信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateCardRun(CardRun cardRun) throws IllegalAccessException, InvocationTargetException {
		// //----------------------------------------------------------------------------
		// // TODO 修改开始
		// CardRun _dbCardRun =
		// cardRunJpaDao.findOne(cardRun.getUuid());
		// if (null == _dbCardRun) {
		// // 刷卡流水不存在!
		// throw new ServiceException("?????????????????");
		// }
		//
		// _dbCardRun.setName(cardRun.getName());
		// _dbCardRun.setDescTxt(cardRun.getDescTxt());
		//
		// //----------------------------------------------------------------------------
		// cardRunJpaDao.save(_dbCardRun);
	}
}