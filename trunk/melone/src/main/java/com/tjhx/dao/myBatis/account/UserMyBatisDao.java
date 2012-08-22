package com.tjhx.dao.myBatis.account;

import java.util.List;

import com.tjhx.entity.account.User;

public interface UserMyBatisDao {

	public List<User> getUserList(User user);

}
