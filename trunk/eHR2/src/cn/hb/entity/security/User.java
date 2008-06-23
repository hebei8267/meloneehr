package cn.hb.entity.security;

/** 登录用户 */
public class User extends AbstractEntityBean  {
	public User() {
	}

 
    /** 编号 */
	private String id(PK);
	 
    /** 名称 */
	private String name;
	 
    /** 密码 */
	private String password;
	 
    /** 第一次登录标记 */
	private Boolean firstLoginFlag;
	 
    /** 详细描述 */
	private String description;
	 
	private 角色组 角色组;
	 
	private 角色 角色;
	 
	/**
	 * 取得编号
	 * 
	 * @return 编号
	 */

	public String getId() {
		return id;
	}

	/**
	 * 取得名称
	 * 
	 * @return 名称
	 */

	public String getName() {
		return name;
	}

	/**
	 * 取得密码
	 * 
	 * @return 密码
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * 取得第一次登录标记
	 * 
	 * @return 第一次登录标记
	 */

	public String getFirstLoginFlag() {
		return firstLoginFlag;
	}

	/**
	 * 取得详细描述
	 * 
	 * @return 详细描述
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * 设置编号
	 * 
	 * @param id 编号
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 设置名称
	 * 
	 * @param name 名称
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置密码
	 * 
	 * @param password 密码
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置第一次登录标记
	 * 
	 * @param firstLoginFlag 第一次登录标记
	 */

	public void setFirstLoginFlag(String firstLoginFlag) {
		this.firstLoginFlag = firstLoginFlag;
	}

	/**
	 * 设置详细描述
	 * 
	 * @param description 详细描述
	 */

	public void setDescription(String description) {
		this.description = description;
	}

}
 
