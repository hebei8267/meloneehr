package cn.hb.entity.hr.organization;

public class 职务 {
 
	private String 编号(PK);
	 
	private String 名称;
	 
	private String 简称(unique);
	 
	private String 详细描述;
	 
	private 职种(职务类型) 职种(职务类型);
	 
	private 组织-职务[] 组织-职务;
	 
}
 
