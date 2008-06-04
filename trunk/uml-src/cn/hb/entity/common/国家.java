package cn.hb.entity.common;

import cn.hb.entity.hr.personnel.个人基本信息;
import cn.hb.entity.hr.organization.组织;

public class 国家 {
 
	private String 编号(PK);
	 
	private String 名称;
	 
	private String 简称(unique);
	 
	private String 详细描述;
	 
	private 个人基本信息[] 个人基本信息;
	 
	private 组织[] 组织;
	 
}
 
