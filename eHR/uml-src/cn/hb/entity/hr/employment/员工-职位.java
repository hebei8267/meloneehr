package cn.hb.entity.hr.employment;

import cn.hb.entity.hr.organization.职位;

public class 员工-职位 {
 
	private String 员工编号(PK);
	 
	private String 职位编号(PK);
	 
	private String 任职开始时间(PK);
	 
	private String 任职结束时间;
	 
	private String 详细描述;
	 
	private 员工 员工;
	 
	private 职位 职位;
	 
	private 任职类型 任职类型;
	 
}
 
