package com.tjhx.entity.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "T_ACCT_PERMISSION")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission extends IdEntity {

	private static final long serialVersionUID = -4090640196673810332L;
	/** 显示名称 */
	private String displayName;
	/** 操作URL */
	private String url;
	/** 角色 */
	private Role role;

	/**
	 * 取得显示名称
	 * 
	 * @return 显示名称
	 */
	@Column(nullable = false, length = 32)
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 设置显示名称
	 * 
	 * @param displayName 显示名称
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 取得操作URL
	 * 
	 * @return 操作URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置操作URL
	 * 
	 * @param url 操作URL
	 */
	public void setUrl(String url) {
		this.url = url;
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
