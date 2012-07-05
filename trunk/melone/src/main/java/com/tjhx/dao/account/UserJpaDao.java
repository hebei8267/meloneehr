package com.tjhx.dao.account;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tjhx.entity.account.User;

public interface UserJpaDao extends PagingAndSortingRepository<User, Integer> {
	/**
	 * 取得用户信息
	 * 
	 * @param loginName 登录名称
	 * @return 用户信息
	 */
	public User findByLoginName(String loginName);
}
