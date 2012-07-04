package com.tjhx.entity.account;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tjhx.entity.base.IdEntity;

/**
 * 用户
 * 
 * 使用JPA annotation定义ORM关系. 使用Hibernate annotation定义JPA未覆盖的部分.
 * 
 * @author
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "T_ACCT_USER")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {

	private static final long serialVersionUID = -6992234020981066235L;

	/** 登录名称 */
	private String loginName;
	/** 登录密码 */
	private String password;
	/** 用户名称-汉字 */
	private String userName;
	/** 用户Email */
	private String userEmail;
	/** 用户详细描述 */
	private String userDesc;
	/** 第一次登录标记 */
	private boolean firstLoginFlg;

	@Basic
	@Column(name = "LOGIN_NAME", nullable = false, length = 32)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Basic
	@Column(name = "PASSWORD", nullable = false, length = 16)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "USER_NAME", nullable = false, length = 32)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Basic
	@Column(name = "USER_EMAIL", length = 32)
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Basic
	@Column(name = "USER_DESC")
	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	@Basic
	@Column(name = "FIRST_LOGIN_FLG", length = 1)
	public boolean isFirstLoginFlg() {
		return firstLoginFlg;
	}

	public void setFirstLoginFlg(boolean firstLoginFlg) {
		this.firstLoginFlg = firstLoginFlg;
	}

	/** 用户关联角色 */
	// private List<Group> groupList = Lists.newArrayList();// 有序的关联对象集合

	// // 多对多定义
	// @ManyToMany
	// @JoinTable(name = "acct_user_group", joinColumns = { @JoinColumn(name =
	// "user_id") }, inverseJoinColumns = { @JoinColumn(name = "group_id") })
	// // Fecth策略定义
	// @Fetch(FetchMode.SUBSELECT)
	// // 集合按id排序.
	// @OrderBy("id")
	// // 集合中对象id的缓存.
	// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	// public List<Group> getGroupList() {
	// return groupList;
	// }
	//
	// public void setGroupList(List<Group> groupList) {
	// this.groupList = groupList;
	// }
	//
	// /**
	// * 用户拥有的权限组名称字符串, 多个权限组名称用','分隔.
	// */
	// // 非持久化属性.
	// @Transient
	// public String getGroupNames() {
	// return Collections3.extractToString(groupList, "name", ", ");
	// }

}