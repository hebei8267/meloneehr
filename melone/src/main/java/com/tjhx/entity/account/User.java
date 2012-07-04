package com.tjhx.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

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
	private String passWord;
	/** 用户名称-汉字 */
	private String userName;
	/** 用户Email */
	private String userEmail;
	/** 用户详细描述 */
	private String userDesc;
	/** 第一次登录标记 */
	private boolean firstLoginFlg;

	/**
	 * 取得登录名称
	 * 
	 * @return 登录名称
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置登录名称
	 * 
	 * @param loginName 登录名称
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 取得登录密码
	 * 
	 * @return 登录密码
	 */
	@Column(nullable = false, length = 16)
	public String getPassWord() {
		return passWord;
	}

	/**
	 * 设置登录密码
	 * 
	 * @param passWord 登录密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * 取得用户名称-汉字
	 * 
	 * @return 用户名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名称-汉字
	 * 
	 * @param userName 用户名称-汉字
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 取得用户Email
	 * 
	 * @return 用户Email
	 */
	@Column(length = 32)
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * 设置用户Email
	 * 
	 * @param userEmail 用户Email
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * 取得用户详细描述
	 * 
	 * @return 用户详细描述
	 */
	public String getUserDesc() {
		return userDesc;
	}

	/**
	 * 设置用户详细描述
	 * 
	 * @param userDesc 用户详细描述
	 */
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	/**
	 * 取得第一次登录标记
	 * 
	 * @return 第一次登录标记
	 */
	@Column(length = 1)
	public boolean getFirstLoginFlg() {
		return firstLoginFlg;
	}

	/**
	 * 设置第一次登录标记
	 * 
	 * @param firstLoginFlg 第一次登录标记
	 */
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