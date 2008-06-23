package cn.hb.entity.assets.car;

public class 车辆维护 {
 
	private String 车牌号码(PK);
	 
	private String 维修开始日期;
	 
	private String 维修结束日期;
	 
	private String 维修原因;
	 
	private String 维修时公里数;
	 
	private String 维修公司;
	 
    /** 地址 */
	private String 维修公司address;
	 
    /** 详细描述 */
	private String description;
	 
	private 车辆 车辆;
	 
	/**
	 * 取得地址
	 * 
	 * @return 地址
	 */

	public String getAddress() {
		return address;
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
	 * 设置地址
	 * 
	 * @param address 地址
	 */

	public void setAddress(String address) {
		this.address = address;
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
 
