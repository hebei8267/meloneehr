package com.tjhx.entity.account;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Lists;
import com.tjhx.entity.base.IdEntity;

/**
 * 用户.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA未覆盖的部分.
 * 
 * @author calvin
 */
/**
 * @author hebei
 * 
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "t_acct_user")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {

	/** 登录名称 */
	private String loginName;
	/** 登录密码 */
	private String password;
	/** 用户名称-汉字 */
	private String name;
	/** 用户Email */
	private String email;
	/** 用户详细描述 */
	private String desc;
	/** 第一次登录标记 */
	private boolean loginFlg;

	/** 用户关联角色 */
	// private List<Group> groupList = Lists.newArrayList();// 有序的关联对象集合

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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