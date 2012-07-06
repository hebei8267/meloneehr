package com.tjhx.entity.account;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tjhx.entity.base.IdEntity;

/**
 * 角色
 */
@Entity
@Table(name = "T_ROLE")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends IdEntity {

	private static final long serialVersionUID = 7216626205866676484L;
	/** 角色名称 */
	private String name;
	/** 角色详细描述 */
	private String descTxt;
	/** 权限信息集合 */
	private Set<Permission> permissionSet = Sets.newHashSet();

	/**
	 * 取得角色名称
	 * 
	 * @return 角色名称
	 */
	@Column(nullable = false, unique = true, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置角色名称
	 * 
	 * @param name 角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得角色详细描述
	 * 
	 * @return 角色详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置角色详细描述
	 * 
	 * @param descTxt 角色详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得权限信息集合
	 * 
	 * @return 权限信息集合
	 */
	// cascade表示级联操作
	// CascadeType.MERGE级联更新：若items属性修改了那么order对象保存时同时修改items里的对象。对应EntityManager的merge方法
	// CascadeType.PERSIST级联刷新：获取order对象里也同时也重新获取最新的items时的对象。对应EntityManager的refresh(object)方法有效。即会重新查询数据库里的最新数据
	// CascadeType.REFRESH级联保存：对order对象保存时也对items里的对象也会保存。对应EntityManager的presist方法
	// CascadeType.REMOVE级联删除：对order对象删除也对items里的对象也会删除。对应EntityManager的remove方法
	// FetchType.LAZY表示懒加载。对于xxxtoMany时即获得多的一方fetch的默认值是FetchType.LAZY懒加载。对于xxxtoOne时即获得一的一方fetch的默认值是FetchType.EAGER立即加载
	// mappedBy表示关系统被维护端，它的值是关系维护端维护关系的属性
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "role")
	// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Permission> getPermissionSet() {
		return permissionSet;
	}

	/**
	 * 设置权限信息集合
	 * 
	 * @param permissionSet 权限信息集合
	 */
	public void setPermissionSet(Set<Permission> permissionSet) {
		this.permissionSet = permissionSet;
	}

	/**
	 * 添加permission
	 * 
	 * @param permission 权限信息
	 */
	public void addPermission(Permission permission) {
		permissionSet.add(permission);
	}

	/**
	 * 取得权限集合名称(##,##,##)
	 * 
	 * @return 权限集合名称
	 */
	@Transient
	public String getPermissionNames() {
		List<String> permissionNameList = Lists.newArrayList();
		for (Permission permission : getPermissionSet()) {
			permissionNameList.add(permission.getFunction().getDisplayName());
		}
		return StringUtils.join(permissionNameList, ",");
	}

}
