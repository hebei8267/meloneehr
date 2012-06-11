package org.springside.examples.showcase.common.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.showcase.common.entity.User;

public interface UserJpaDao extends PagingAndSortingRepository<User, Long> {

	public List<User> findByName(String name);

	public User findByLoginName(String loginName);

	public List<User> findByLoginNameAndName(String loginName, String name);
}
