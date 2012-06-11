package org.springside.examples.miniservice.service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.showcase.common.dao.UserJpaDao;
import org.springside.examples.showcase.common.entity.User;
import org.springside.modules.beanvalidator.BeanValidators;

/**
 * 帐号管理类.
 * 
 * 实现领域对象用户及其相关实体的所有业务管理函数. 使用Spring annotation定义事务管理.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional(readOnly = true)
public class MiniAccountManager {

	private UserJpaDao userJpaDao = null;

	private Validator validator = null;

	public User getUser(Long id) {
		Validate.notNull(id, "id参数为空");
		return userJpaDao.findOne(id);
	}

	public List<User> searchUser(String loginName, String name) {

		return userJpaDao.findByLoginNameAndName(loginName, name);
	}

	public List<User> searchUser(String name) {

		return userJpaDao.findByName(name);
	}

	@Transactional(readOnly = false)
	public Long saveUser(User user) throws ConstraintViolationException {
		Validate.notNull(user, "用户参数为空");
		// 使用Hibernate Validator校验请求参数
		BeanValidators.validateWithException(validator, user);

		return userJpaDao.save(user).getId();
	}

	@Autowired
	public void setUserJpaDao(UserJpaDao userJpaDao) {
		this.userJpaDao = userJpaDao;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
}
