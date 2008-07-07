package cn.hb.entity.hr.organization;

import cn.hb.core.bean.AbstractEntityBean;

/** 职位 */
public class Job {
    public Job() {
    }

    /** 组织编号 */
    private String OrganizationID;

    /** 职务编号 */
    private String JobPositionID;

    /** 设立时间 */
    private String startDate;

    /** 名称 */
    private String name;

    /** 简称 */
    private String shortName;

    /** 编制人数 */
    private Integer assignmentSize;

    /** 详细描述 */
    private String note;

    /** 是否允许超编 */
    private Boolean allowExceed;

    /** 撤销时间 */
    private String endDate;

    /** 撤销原因 */
    private String endDescription;

    /** 是否为该组织的负责人 */
    private Boolean isManager;

}
