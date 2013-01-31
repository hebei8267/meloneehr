package com.tjhx.service.info;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.info.BankCardJpaDao;
import com.tjhx.entity.info.BankCard;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class BankCardManager {
	private static Logger logger = LoggerFactory.getLogger(BankCardManager.class);
	@Resource
	private BankCardJpaDao bankCardJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;

	/**
	 * 取得所有银行卡信息
	 * 
	 * @return 银行卡信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<BankCard> getAllBankCard(String bankId) {

		List<BankCard> _bankCardList = spyMemcachedClient.get(MemcachedObjectType.BANK_CARD_LIST.getObjKey() + bankId);

		if (null == _bankCardList) {
			// 从数据库中取出全量供应商信息(List格式)
			_bankCardList = (List<BankCard>) bankCardJpaDao.findByBankId(bankId, new Sort(new Sort.Order(
					Sort.Direction.ASC, "uuid")));
			// 将供应商信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.BANK_CARD_LIST.getObjKey() + bankId,
					MemcachedObjectType.BANK_CARD_LIST.getExpiredTime(), _bankCardList);

			logger.debug("供应商信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出供应商信息");
		}
		return _bankCardList;
	}

	/**
	 * 根据编号取得银行卡信息
	 * 
	 * @param uuid 银行卡编号
	 * @return 银行卡信息
	 */
	public BankCard getBankCardByUuid(Integer uuid) {
		return bankCardJpaDao.findOne(uuid);
	}

	/**
	 * 删除银行卡信息
	 * 
	 * @param uuid 银行卡编号
	 */
	@Transactional(readOnly = false)
	public void delBankCardByUuid(Integer uuid) {
		bankCardJpaDao.delete(uuid);
	}

	/**
	 * 添加新银行卡信息
	 * 
	 * @param bankCard 银行卡信息
	 */
	@Transactional(readOnly = false)
	public void addNewBankCard(BankCard bankCard) {
		BankCard _dbBankCard = bankCardJpaDao.findByBankCardNo(bankCard.getBankCardNo());
		// 该银行卡已存在!
		if (null != _dbBankCard) {
			throw new ServiceException("ERR_MSG_BANK_CARD_001");
		}
		bankCardJpaDao.save(bankCard);
	}

	/**
	 * 更新银行卡信息
	 * 
	 * @param bankCard 银行卡信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateBankCard(BankCard bankCard) throws IllegalAccessException, InvocationTargetException {

		BankCard _dbBankCard = bankCardJpaDao.findOne(bankCard.getUuid());
		if (null == _dbBankCard) {
			// 银行卡不存在!
			throw new ServiceException("ERR_MSG_BANK_CARD_002");
		}

		_dbBankCard.setBankCardNo(bankCard.getBankCardNo());

		bankCardJpaDao.save(_dbBankCard);
	}
}