package org.springside.examples.showcase.common.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;
import org.springside.examples.showcase.common.entity.User;

@Component
//注意此处不能使用@Repository、此处的MyBatis作为纯查询Dao无数据更新操作
public class UserMyBatisDao extends SqlSessionDaoSupport {

	public User getUser(Long id) {
		return (User) getSqlSession().selectOne("Account.getUser", id);
	}

}
