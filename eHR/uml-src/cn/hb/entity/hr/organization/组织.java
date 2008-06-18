package cn.hb.entity.hr.organization;

import cn.hb.entity.assets.car.组织-车辆;
import cn.hb.entity.common.国家;

public class 组织 {
 
	private String 编号(PK);
	 
	private String 设立时间(PK);
	 
	private String 名称;
	 
	private String 简称(unique);
	 
	private String 地址;
	 
	private String 电话号码;
	 
	private String 传真号码;
	 
	private String 详细描述;
	 
	private String 撤销时间;
	 
	private String 撤销原因;
	 
	private 组织类型 组织类型;
	 
	private 组织[] 组织;
	 
	private 组织 组织;
	 
	private 组织-职务[] 组织-职务;
	 
	private 组织-车辆[] 组织-车辆;
	 
	private 国家 国家;
	 
}
 
