package cn.hb.entity.hr.organization;

import cn.hb.entity.hr.employment.员工-职位;
import cn.hb.entity.hr.welfare.职位-薪酬福利;

public class 职位 {
 
	private String 组织编号(PK);
	 
	private String 职务编号(PK);
	 
	private String 设立时间(PK);
	 
	private String 名称;
	 
	private String 简称(unique);
	 
	private Integer 编制人数;
	 
	private String 详细描述;
	 
	private Boolean 是否允许超编;
	 
	private String 撤销时间;
	 
	private String 撤销原因;
	 
	private Boolean 是否为该组织的负责人;
	 
	private 员工-职位[] 员工-职位;
	 
	private 职位-薪酬福利[] 职位-薪酬福利;
	 
}
 
