package cn.com.free.model;

import cn.com.free.framework.model.BaseEntity;

/**
 * @author cn.com.free.framework.generator
 */
@SuppressWarnings("serial")
public class Tuser extends BaseEntity {

	// alias
	public static final String TABLE_ALIAS = "用户";
	public static final String ALIAS_ORG_CD = "机构代码";
	public static final String ALIAS_USR_ID = "用户ID";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_LOGIN_ID = "登录ID";
	public static final String ALIAS_LOGIN_PWD = "登录密码";
	public static final String ALIAS_USR_NM = "姓名";
	public static final String ALIAS_JOB_PSTN = "职务";
	public static final String ALIAS_USR_EMAIL = "电子邮箱";
	public static final String ALIAS_TEL_NO = "办公电话";
	public static final String ALIAS_MOBILE = "手机";
	public static final String ALIAS_DEL_FLG = "删除标志";
	public static final String ALIAS_SER_FLG = "服务部署标记";
	public static final String ALIAS_USR_STATE = "用户状态";
	public static final String ALIAS_REG_ACT = "激活验证码";
	public static final String ALIAS_CRT_USR_ID = "创建者ID";
	public static final String ALIAS_CRT_TIME = "创建时间";
	public static final String ALIAS_UPDT_USR_ID = "更新者ID";
	public static final String ALIAS_UPDT_TIME = "更新时间";

	// date formats
	public static final String FORMAT_CRT_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDT_TIME = DATE_TIME_FORMAT;

	// columns START
	/** 机构代码 */
	private String orgCd;
	/** 用户ID */
	private java.lang.Integer usrId;
	/** 角色ID */
	private java.lang.Integer roleId;
	/** 登录ID */
	private String loginId;
	/** 登录密码 */
	private String loginPwd;
	/** 姓名 */
	private String usrNm;
	/** 职务 */
	private String jobPstn;
	/** 电子邮箱 */
	private String usrEmail;
	/** 办公电话 */
	private String telNo;
	/** 手机 */
	private String mobile;
	/** 删除标志 */
	private String delFlg;
	/** 服务部署标记 */
	private String serFlg;
	/** 用户状态 */
	private String usrState;
	/** 激活验证码 */
	private String regAct;
	/** 创建者ID */
	private java.lang.Integer crtUsrId;
	/** 创建时间 */
	private java.sql.Timestamp crtTime;
	/** 更新者ID */
	private java.lang.Integer updtUsrId;
	/** 更新时间 */
	private java.sql.Timestamp updtTime;

	// columns END

	public Tuser() {
	}

	public Tuser(java.lang.Integer usrId) {
		this.usrId = usrId;
	}

	/**
	 * 取得机构代码
	 * 
	 * @return 机构代码
	 */
	public String getOrgCd() {
		return this.orgCd;
	}

	/**
	 * 设置机构代码
	 * 
	 * @param orgCd 机构代码
	 */
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	/**
	 * 取得用户ID
	 * 
	 * @return 用户ID
	 */
	public java.lang.Integer getUsrId() {
		return this.usrId;
	}

	/**
	 * 设置用户ID
	 * 
	 * @param usrId 用户ID
	 */
	public void setUsrId(java.lang.Integer usrId) {
		this.usrId = usrId;
	}

	/**
	 * 取得角色ID
	 * 
	 * @return 角色ID
	 */
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}

	/**
	 * 设置角色ID
	 * 
	 * @param roleId 角色ID
	 */
	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 取得登录ID
	 * 
	 * @return 登录ID
	 */
	public String getLoginId() {
		return this.loginId;
	}

	/**
	 * 设置登录ID
	 * 
	 * @param loginId 登录ID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * 取得登录密码
	 * 
	 * @return 登录密码
	 */
	public String getLoginPwd() {
		return this.loginPwd;
	}

	/**
	 * 设置登录密码
	 * 
	 * @param loginPwd 登录密码
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	/**
	 * 取得姓名
	 * 
	 * @return 姓名
	 */
	public String getUsrNm() {
		return this.usrNm;
	}

	/**
	 * 设置姓名
	 * 
	 * @param usrNm 姓名
	 */
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	/**
	 * 取得职务
	 * 
	 * @return 职务
	 */
	public String getJobPstn() {
		return this.jobPstn;
	}

	/**
	 * 设置职务
	 * 
	 * @param jobPstn 职务
	 */
	public void setJobPstn(String jobPstn) {
		this.jobPstn = jobPstn;
	}

	/**
	 * 取得电子邮箱
	 * 
	 * @return 电子邮箱
	 */
	public String getUsrEmail() {
		return this.usrEmail;
	}

	/**
	 * 设置电子邮箱
	 * 
	 * @param usrEmail 电子邮箱
	 */
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	/**
	 * 取得办公电话
	 * 
	 * @return 办公电话
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * 设置办公电话
	 * 
	 * @param telNo 办公电话
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/**
	 * 取得手机
	 * 
	 * @return 手机
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 设置手机
	 * 
	 * @param mobile 手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 取得删除标志
	 * 
	 * @return 删除标志
	 */
	public String getDelFlg() {
		return this.delFlg;
	}

	/**
	 * 设置删除标志
	 * 
	 * @param delFlg 删除标志
	 */
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * 取得服务部署标记
	 * 
	 * @return 服务部署标记
	 */
	public String getSerFlg() {
		return this.serFlg;
	}

	/**
	 * 设置服务部署标记
	 * 
	 * @param serFlg 服务部署标记
	 */
	public void setSerFlg(String serFlg) {
		this.serFlg = serFlg;
	}

	/**
	 * 取得用户状态
	 * 
	 * @return 用户状态
	 */
	public String getUsrState() {
		return this.usrState;
	}

	/**
	 * 设置用户状态
	 * 
	 * @param usrState 用户状态
	 */
	public void setUsrState(String usrState) {
		this.usrState = usrState;
	}

	/**
	 * 取得激活验证码
	 * 
	 * @return 激活验证码
	 */
	public String getRegAct() {
		return this.regAct;
	}

	/**
	 * 设置激活验证码
	 * 
	 * @param regAct 激活验证码
	 */
	public void setRegAct(String regAct) {
		this.regAct = regAct;
	}

	/**
	 * 取得创建者ID
	 * 
	 * @return 创建者ID
	 */
	public java.lang.Integer getCrtUsrId() {
		return this.crtUsrId;
	}

	/**
	 * 设置创建者ID
	 * 
	 * @param crtUsrId 创建者ID
	 */
	public void setCrtUsrId(java.lang.Integer crtUsrId) {
		this.crtUsrId = crtUsrId;
	}

	/**
	 * 取得创建时间
	 * 
	 * @return 创建时间
	 */
	public String getCrtTimeString() {
		return date2String(getCrtTime(), FORMAT_CRT_TIME);
	}

	/**
	 * 设置创建时间
	 * 
	 * @param crtTime 创建时间
	 */
	public void setCrtTimeString(String crtTime) {
		setCrtTime(string2Date(crtTime, FORMAT_CRT_TIME, java.sql.Timestamp.class));
	}

	/**
	 * 取得创建时间
	 * 
	 * @return 创建时间
	 */
	public java.sql.Timestamp getCrtTime() {
		return this.crtTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param crtTime 创建时间
	 */
	public void setCrtTime(java.sql.Timestamp crtTime) {
		this.crtTime = crtTime;
	}

	/**
	 * 取得更新者ID
	 * 
	 * @return 更新者ID
	 */
	public java.lang.Integer getUpdtUsrId() {
		return this.updtUsrId;
	}

	/**
	 * 设置更新者ID
	 * 
	 * @param updtUsrId 更新者ID
	 */
	public void setUpdtUsrId(java.lang.Integer updtUsrId) {
		this.updtUsrId = updtUsrId;
	}

	/**
	 * 取得更新时间
	 * 
	 * @return 更新时间
	 */
	public String getUpdtTimeString() {
		return date2String(getUpdtTime(), FORMAT_UPDT_TIME);
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updtTime 更新时间
	 */
	public void setUpdtTimeString(String updtTime) {
		setUpdtTime(string2Date(updtTime, FORMAT_UPDT_TIME, java.sql.Timestamp.class));
	}

	/**
	 * 取得更新时间
	 * 
	 * @return 更新时间
	 */
	public java.sql.Timestamp getUpdtTime() {
		return this.updtTime;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updtTime 更新时间
	 */
	public void setUpdtTime(java.sql.Timestamp updtTime) {
		this.updtTime = updtTime;
	}
}
