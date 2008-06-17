package cn.hb.entity.hr.organization;

public class 职种(职务类型) {
 
	private String 编号(PK);
	 
	private String 详细描述;
	 
	private String 名称;
	 
	private String 简称(unique);
	 
	private 职务[] 职务;
	 
	private 职种(职务类型)[] 职种(职务类型);
	 
	private 职种(职务类型) 职种(职务类型);
	 
}
 
