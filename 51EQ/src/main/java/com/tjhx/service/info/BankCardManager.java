package com.tjhx.service.info;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.info.BankCardJpaDao;
import com.tjhx.dao.info.BankJpaDao;
import com.tjhx.entity.info.BankCard;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class BankCardManager {
	@Resource
	private BankCardJpaDao bankCardJpaDao;
	@Resource
	private BankJpaDao bankJpaDao;

	/**
	 * 取得所有银行卡信息
	 * 
	 * @return 银行卡信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<BankCard> getAllBankCard(String bankId) {
		return (List<BankCard>) bankCardJpaDao.findByBankId(bankId, new Sort(new Sort.Order(Sort.Direction.ASC,
				"uuid")));
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