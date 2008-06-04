package cn.hb.entity.hr.organization;

public class 组织-职务 {
 
	private String 组织编号(PK);
	 
	private String 职务编号(PK);
	 
	private 组织 组织;
	 
	private 职务 职务;
	 
	private 职务类型(排斥_非排斥-关联) 职务类型(排斥_非排斥-关联);
	 
}
 
