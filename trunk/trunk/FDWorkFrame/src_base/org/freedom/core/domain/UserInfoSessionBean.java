/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.domain;

import org.freedom.core.bean.BaseBean;

/**
 * 登录用户信息
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class UserInfoSessionBean extends BaseBean {

	private static final long serialVersionUID = 6644616830389518518L;

	public UserInfoSessionBean() {

	}

	/** 登录用户ID */
	private String userId;
	/** 用户名称 */
	private String userName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
