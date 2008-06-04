package cn.hb.entity.hr.employment;

import cn.hb.entity.hr.personnel.个人基本信息;

public class 雇佣 {
 
	private String 个人编号(PK);
	 
	private String 员工编号(PK);
	 
	private String 雇佣开始时间(PK);
	 
	private String 详细描述;
	 
	private 雇佣类型 雇佣类型;
	 
	private 个人基本信息 个人基本信息;
	 
	private 合同[] 合同;
	 
	private 员工 员工;
	 
}
 
