package cn.hb.entity.hr.personnel;

public class 专业名称 {
 
	private String 编号(PK);
	 
	private String 名称;
	 
	private String 简称(unique);
	 
	private String 详细描述;
	 
	private 学历名称 学历名称;
	 
	private 教育信息[] 教育信息;
	 
	private 专业名称 专业名称;
	 
	private 专业名称[] 专业名称;
	 
}
 
