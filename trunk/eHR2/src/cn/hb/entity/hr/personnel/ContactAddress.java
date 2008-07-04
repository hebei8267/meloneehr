package cn.hb.entity.hr.personnel;


/** 联系地址 */
public class ContactAddress {
 
    /** 编号 */
	private String id(PK);
	 
    /** 地址 */
	private String 联系address;
    /** 联系地址 */
	private String ContactAddress;
	 
    /** 编号 */
	private String 邮政id;
	 
    /** 电话号码 */
	private String 移动telephone;
	 
    /** 电话号码 */
	private String telephone;
	 
	private String Email;
	 
	private String QQ;
	 
	private String MSN;
	 
    /** 个人基本信息 */
	private Person Person;
	 
	/**
     * 取得编号
     * 
     * @return 编号
     */

	public String getId() {
		return id;
	}

	/**
     * 取得地址
     * 
     * @return 地址
     */

	public String getAddress() {
		return address;
	}

	/**
     * 取得联系地址
     * 
     * @return 联系地址
     */

	public String getContactAddress() {
		return ContactAddress;
	}

	/**
     * 取得编号
     * 
     * @return 编号
     */

	public String getId() {
		return id;
	}

	/**
     * 取得电话号码
     * 
     * @return 电话号码
     */

	public String getTelephone() {
		return telephone;
	}

	/**
     * 取得电话号码
     * 
     * @return 电话号码
     */

	public String getTelephone() {
		return telephone;
	}

	/**
     * 取得个人基本信息
     * 
     * @return 个人基本信息
     */

	public String getPerson() {
		return Person;
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
     * 设置地址
     * 
     * @param address 地址
     */

	public void setAddress(String address) {
		this.address = address;
	}

	/**
     * 设置联系地址
     * 
     * @param ContactAddress 联系地址
     */

	public void setContactAddress(String ContactAddress) {
		this.ContactAddress = ContactAddress;
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
     * 设置电话号码
     * 
     * @param telephone 电话号码
     */

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
     * 设置电话号码
     * 
     * @param telephone 电话号码
     */

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
     * 设置个人基本信息
     * 
     * @param Person 个人基本信息
     */

	public void setPerson(String Person) {
		this.Person = Person;
	}

}
 
