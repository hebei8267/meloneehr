package com.tjhx.service.accounts;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
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
	 * 取得刷卡流水信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyyMMdd)
	 * @return
	 * @throws ParseException
	 */
	public List<CardRun> getAllCardRunByOrgId_1(String orgId, String currentDate) throws ParseException {
		String optDateY = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "yyyy");

		String optDateM = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "MM");

		return getAllCardRunByOrgId(orgId, optDateY, optDateM);
	}

	/**
	 * 取得刷卡流水信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyy-MM)
	 * @return
	 * @throws ParseException
	 */
	public List<CardRun> getAllCardRunByOrgId_2(String orgId, String currentDate) throws ParseException {
		String optDateY = DateUtils.transDateFormat(currentDate, "yyyy-MM", "yyyy");

		String optDateM = DateUtils.transDateFormat(currentDate, "yyyy-MM", "MM");

		return getAllCardRunByOrgId(orgId, optDateY, optDateM);
	}

	@SuppressWarnings("unchecked")
	private List<CardRun> getAllCardRunByOrgId(String orgId, String optDateY, String optDateM) throws ParseException {

		List<CardRun> _list = (List<CardRun>) cardRunJpaDao.findByOrgId_OptDateY_OptDateM(orgId, optDateY, optDateM,
				new Sort(new Sort.Order(Sort.Direction.DESC, "optDate")));

		for (CardRun cardRun : _list) {
			cardRun.autoSetEditFlg();
		}

		return _list;
	}

	/**
	 * 合计计算
	 * 
	 * @param cardRunList
	 * @return
	 */
	public CardRun calTotal(List<CardRun> cardRunList) {
		CardRun _cardRun = new CardRun();
		for (CardRun cardRun : cardRunList) {
			// 单据统计
			_cardRun.setRecordStatisAmt(_cardRun.getRecordStatisAmt().add(cardRun.getRecordStatisAmt()));
			// 电脑统计
			_cardRun.setBwStatisAmt(_cardRun.getBwStatisAmt().add(cardRun.getBwStatisAmt()));
			// 刷卡笔数
			_cardRun.setOptNum(_cardRun.getOptNum() + cardRun.getOptNum());
		}
		return _cardRun;
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
	public void updateCardRun(CardRun cardRun, User user) throws IllegalAccessException, InvocationTargetException {

		CardRun _dbCardRun = cardRunJpaDao.findOne(cardRun.getUuid());
		if (null == _dbCardRun) {
			// 刷卡流水不存在!
			throw new ServiceException("ERR_MSG_CARD_RUN_002");
		}

		String _date = DateUtils.transDateFormat(cardRun.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");

		CardRun _tmp_dbCardRun = cardRunJpaDao.findByOrgIdAndOptDate(user.getOrganization().getId(), _date);
		// 该刷卡流水已存在!
		if (null != _tmp_dbCardRun && !_tmp_dbCardRun.getUuid().equals(_dbCardRun.getUuid())) {
			throw new ServiceException("ERR_MSG_CARD_RUN_001");
		}

		// 刷卡汇总日期
		_dbCardRun.setOptDate(_date);
		// 刷卡汇总日期-年
		_dbCardRun.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 刷卡汇总日期-月
		_dbCardRun.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));
		// 单据统计
		_dbCardRun.setRecordStatisAmt(cardRun.getRecordStatisAmt());
		// 电脑统计
		_dbCardRun.setBwStatisAmt(cardRun.getBwStatisAmt());
		// 刷卡笔数
		_dbCardRun.setOptNum(cardRun.getOptNum());
		// 凭证号
		_dbCardRun.setCertNo(cardRun.getCertNo());
		// 备注
		_dbCardRun.setDescTxt(cardRun.getDescTxt());
		// 盈亏金额=单据统计-电脑统计
		_dbCardRun.setProfitAmt(cardRun.getRecordStatisAmt().subtract(cardRun.getBwStatisAmt()));

		// ----------------------------------------------------------------------------
		cardRunJpaDao.save(_dbCardRun);
	}
}