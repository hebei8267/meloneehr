package com.tjhx.entity.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.struct.Department;
import com.tjhx.entity.struct.Organization;

/**
 * 用户
 */
@Entity
@Table(name = "T_USER")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {

	private static final long serialVersionUID = -6992234020981066235L;

	/** 登录名称 */
	private String loginName;
	/** 登录密码 */
	private String passWord;
	/** 用户名称-汉字 */
	private String name;
	/** 用户详细描述 */
	private String descTxt;
	/** 第一次登录标记 */
	private Boolean firstLoginFlg = true;
	/** 用户关联角色 */
	private Role role;
	/** 用户关联机构 */
	private Organization organization;
	/** 用户关联部门 */
	private Department department;
	/** 上传用户相片名称 */
	private String photoName;

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
	public String getName() {
		return name;
	}

	/**
	 * 设置用户名称-汉字
	 * 
	 * @param name 用户名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得用户详细描述
	 * 
	 * @return 用户详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置用户详细描述
	 * 
	 * @param descTxt 用户详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得第一次登录标记
	 * 
	 * @return 第一次登录标记
	 */
	@Column(length = 1)
	public Boolean getFirstLoginFlg() {
		return firstLoginFlg;
	}

	/**
	 * 设置第一次登录标记
	 * 
	 * @param firstLoginFlg 第一次登录标记
	 */
	public void setFirstLoginFlg(Boolean firstLoginFlg) {
		this.firstLoginFlg = firstLoginFlg;
	}

	/**
	 * 取得用户关联角色
	 * 
	 * @return 用户关联角色
	 */
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_UUID")
	public Role getRole() {
		return role;
	}

	/**
	 * 设置用户关联角色
	 * 
	 * @param role 用户关联角色
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * 取得上传用户相片名称
	 * 
	 * @return 上传用户相片名称
	 */
	@Column(length = 32)
	public String getPhotoName() {
		return photoName;
	}

	/**
	 * 设置上传用户相片名称
	 * 
	 * @param photoName 上传用户相片名称
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	/**
	 * 取得用户关联机构
	 * 
	 * @return organization 用户关联机构
	 */
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_UUID")
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * 设置用户关联机构
	 * 
	 * @param organization 用户关联机构
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * 取得用户关联部门
	 * 
	 * @return department 用户关联部门
	 */
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEP_UUID")
	public Department getDepartment() {
		return department;
	}

	/**
	 * 设置用户关联部门
	 * 
	 * @param department 用户关联部门
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

}