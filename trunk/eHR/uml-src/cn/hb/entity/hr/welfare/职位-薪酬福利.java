package cn.hb.entity.hr.welfare;

import cn.hb.entity.hr.organization.职位;

public class 职位-薪酬福利 {
 
	private String 组织编号(PK);
	 
	private String 职务编号(PK);
	 
	private String 职位设立时间(PK);
	 
	private String 薪酬福利编号(PK);
	 
	private String 等级编号(PK);
	 
	private 薪酬福利 薪酬福利;
	 
	private 职位 职位;
	 
	private 职位-薪酬福利(排斥_非排斥-关联) 职位-薪酬福利(排斥_非排斥-关联);
	 
}
 
