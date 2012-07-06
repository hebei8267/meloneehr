package com.tjhx.entity.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tjhx.entity.base.IdEntity;

/**
 * 资源访问权限
 */
@Entity
@Table(name = "T_ROLE_FUN_PERM")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission extends IdEntity {

	private static final long serialVersionUID = -4090640196673810332L;

	/** 功能资源 */
	private Function function;
	/** 角色 */
	private Role role;

	/**
	 * 取得功能资源
	 * 
	 * @return 功能资源
	 */
	// CascadeType.REFRESH级联刷新
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	// @JoinColumn表示外键的列
	@JoinColumn(name = "FUN_UUID")
	public Function getFunction() {
		return function;
	}

	/**
	 * 设置功能资源
	 * 
	 * @param function 功能资源
	 */
	public void setFunction(Function function) {
		this.function = function;
	}

	/**
	 * 取得角色
	 * 
	 * @return 角色
	 */
	// CascadeType.REFRESH级联刷新
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	// @JoinColumn表示外键的列
	@JoinColumn(name = "ROLE_UUID")
	public Role getRole() {
		return role;
	}

	/**
	 * 设置角色
	 * 
	 * @param role 角色
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}