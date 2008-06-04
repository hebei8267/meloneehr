package cn.hb.entity.hr.welfare;

public class 薪酬福利 {
 
	private String 薪酬福利编号(PK);
	 
	private String 等级编号(PK);
	 
	private String 名称;
	 
	private String 简称(unique);
	 
	private String 设立时间;
	 
	private String 撤销时间;
	 
	private String 撤销原因;
	 
	private String 成本数额;
	 
	private 职位-薪酬福利[] 职位-薪酬福利;
	 
	private 薪酬福利类型 薪酬福利类型;
	 
}
 
