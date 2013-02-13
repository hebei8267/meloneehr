package com.tjhx.entity.member;

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
import org.hibernate.annotations.NaturalId;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tjhx.entity.IdEntity;

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
	/** 拥有权限信息集合 */
	private Set<Permission> permissionSet = Sets.newHashSet();

	// ##########################################################
	/** 拥有资源菜单ID信息集合 */
	private List<String> permIdList = Lists.newArrayList();

	// /** 拥有资源菜单信息集合 */
	// private String[] funIds;
	// /** 非拥有资源菜单信息集合 */
	// private String[] noFunIds;
	// /** 全量功能资源信息集合 */
	// private List<Function> allFunList;

	/**
	 * 取得角色名称
	 * 
	 * @return 角色名称
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
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
	// CascadeType.PERSIST级联保存
	// CascadeType.MERGE级联更新
	// CascadeType.REMOVE级联删除
	// CascadeType.REFRESH级联刷新
	// FetchType.LAZY表示懒加载。对于xxxtoMany时即获得多的一方fetch的默认值是FetchType.LAZY懒加载。对于xxxtoOne时即获得一的一方fetch的默认值是FetchType.EAGER立即加载
	// mappedBy表示关系统被维护端，它的值是关系维护端维护关系的属性
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
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
	 * 添加权限
	 * 
	 * @param permission 权限信息
	 */
	public void addPermission(Permission permission) {
		if (!permissionSet.contains(permission)) {
			permissionSet.add(permission);
		}

	}

	/**
	 * 取得拥有权限集合名称(##,##,##)
	 * 
	 * @return 拥有权限集合名称
	 */
	@Transient
	public String getPermissionNames() {
		List<String> permissionNameList = Lists.newArrayList();
		for (Permission permission : getPermissionSet()) {
			permissionNameList.add(permission.getFunction().getDisplayName());
		}
		return StringUtils.join(permissionNameList, ",");
	}

	public void initPermIdList() {
		for (Permission permission : getPermissionSet()) {
			permIdList.add(permission.getFunction().getUuid().toString());
		}
	}

	/**
	 * 取得拥有资源菜单ID信息集合
	 * 
	 * @return permIdList 拥有资源菜单ID信息集合
	 */
	@Transient
	public List<String> getPermIdList() {
		return permIdList;
	}

	/**
	 * 设置拥有资源菜单ID信息集合
	 * 
	 * @param permIdList 拥有资源菜单ID信息集合
	 */
	public void setPermIdList(List<String> permIdList) {
		this.permIdList = permIdList;
	}

	// /**
	// * 取得拥有资源菜单信息集合ID
	// *
	// * @return 拥有资源菜单集合ID
	// */
	// @Transient
	// public String[] getFunIds() {
	// return funIds;
	// }
	//
	// /**
	// * 设置拥有资源菜单信息集合ID
	// *
	// * @param permissionSet 拥有资源菜单信息集合ID
	// */
	// public void setFunIds(String[] funIds) {
	// this.funIds = funIds;
	// }
	//
	// /**
	// * 取得非拥有资源菜单信息集合ID
	// *
	// * @return 非拥有资源菜单信息集合ID
	// */
	// @Transient
	// public String[] getNoFunIds() {
	// return noFunIds;
	// }
	//
	// /**
	// * 设置非拥有资源菜单信息集合ID
	// *
	// * @param noPermissionIds 非拥有资源菜单信息集合ID
	// */
	// public void setNoFunIds(String[] noFunIds) {
	// this.noFunIds = noFunIds;
	// }
	//
	// /**
	// * 取得该角色不包含的权限列表
	// *
	// * @return
	// */
	// @Transient
	// public List<Function> getNoFunList() {
	// if (null == permissionSet || permissionSet.size() == 0) {
	// return this.allFunList;
	// } else {
	// if (null != allFunList) {
	// List<Function> funList = Lists.newArrayList();
	// for (Permission permission : getPermissionSet()) {
	// funList.add(permission.getFunction());
	// }
	//
	// this.allFunList.removeAll(funList);
	// }
	// }
	// return allFunList;
	// }
	//
	// /**
	// * 取得该角色包含的权限列表
	// *
	// * @return
	// */
	// @Transient
	// public List<Function> getFunList() {
	// List<Function> funList = Lists.newArrayList();
	// for (Permission permission : getPermissionSet()) {
	// funList.add(permission.getFunction());
	// }
	// return funList;
	// }
	//
	// public void setAllFunList(List<Function> funList) {
	// this.allFunList = funList;
	// }

}
