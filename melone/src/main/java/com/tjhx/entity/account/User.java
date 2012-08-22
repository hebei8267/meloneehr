package com.tjhx.entity.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.shop.Shop;

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
	/** 电话号码 */
	private String telNum;
	/** 用户Email */
	private String email;
	/** 用户详细描述 */
	private String descTxt;
	/** 第一次登录标记 */
	private boolean firstLoginFlg = true;
	/** 用户关联角色 */
	private Role role;
	/** 用户所属门店 */
	private Shop shop;
	/** 用户所属门店编号 */
	private String shopId;
	// ----------------------------------
	/** 用户关联角色对象唯一标识 */
	private Integer roleUuid;
	/** 用户所属门店名称---mybatis查询用 */
	private String shopName;
	/** 用户关联角色名称---mybatis查询用 */
	private String roleName;

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
	 * 取得电话号码
	 * 
	 * @return 电话号码
	 */
	@Column(length = 32)
	public String getTelNum() {
		return telNum;
	}

	/**
	 * 设置电话号码
	 * 
	 * @param telNum 电话号码
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * 取得用户Email
	 * 
	 * @return 用户Email
	 */
	@Column(length = 32)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置用户Email
	 * 
	 * @param email 用户Email
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * 取得用户关联角色
	 * 
	 * @return 用户关联角色
	 */
	@OneToOne(cascade = CascadeType.ALL)
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
	 * 取得用户所属门店
	 * 
	 * @return 用户所属门店
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn表示外键的列
	@JoinColumn(name = "SHOP_UUID")
	public Shop getShop() {
		return shop;
	}

	/**
	 * 设置用户所属门店
	 * 
	 * @param shop 用户所属门店
	 */
	public void setShop(Shop shop) {
		if (null != shop) {
			setShopId(shop.getId());
		}
		this.shop = shop;
	}

	/**
	 * 取得用户所属门店编号
	 * 
	 * @return 用户所属门店编号
	 */
	@Column(length = 16)
	public String getShopId() {
		return shopId;
	}

	/**
	 * 设置用户所属门店编号
	 * 
	 * @param shopId 用户所属门店编号
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * 取得用户关联角色对象唯一标识
	 * 
	 * @return 用户关联角色对象唯一标识
	 */
	@Transient
	public Integer getRoleUuid() {
		if (null != roleUuid) {
			return roleUuid;
		}

		if (null != this.getRole()) {
			return this.getRole().getUuid();
		}

		return null;
	}

	/**
	 * 设置用户关联角色对象唯一标识
	 * 
	 * @param roleUuid 用户关联角色对象唯一标识
	 */
	public void setRoleUuid(Integer roleUuid) {
		this.roleUuid = roleUuid;
	}

	/**
	 * 取得用户所属门店名称---mybatis查询用
	 * 
	 * @return 用户所属门店名称
	 */
	@Transient
	public String getShopName() {
		return shopName;
	}

	/**
	 * 设置用户所属门店名称---mybatis查询用
	 * 
	 * @param shopName 用户所属门店名称
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * 取得用户关联角色名称---mybatis查询用
	 * 
	 * @return 用户关联角色名称
	 */
	@Transient
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置用户关联角色名称---mybatis查询用
	 * 
	 * @param roleName 用户关联角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}