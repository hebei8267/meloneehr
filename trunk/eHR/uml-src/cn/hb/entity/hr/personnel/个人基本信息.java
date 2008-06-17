package cn.hb.entity.hr.personnel;

import cn.hb.entity.common.国家;
import cn.hb.entity.common.民族;
import cn.hb.entity.common.籍贯;
import cn.hb.entity.hr.employment.雇佣;

public class 个人基本信息 {
 
	private String 编号(PK);
	 
	private String 姓名;
	 
	private String 英文名;
	 
	private Integer 性别;
	 
	private String 出生日期;
	 
	private 婚姻状况 婚姻状况;
	 
	private 学历名称 学历名称;
	 
	private 国家 国家;
	 
	private 民族 民族;
	 
	private 籍贯 籍贯;
	 
	private 证件[] 证件;
	 
	private 个人联系地址[] 个人联系地址;
	 
	private 教育信息[] 教育信息;
	 
	private 培训信息[] 培训信息;
	 
	private 语言能力[] 语言能力;
	 
	private 雇佣[] 雇佣;
	 
}
 
