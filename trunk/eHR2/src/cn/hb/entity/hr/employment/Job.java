package cn.hb.entity.hr.employment;

import cn.hb.core.bean.AbstractEntityBean;

/** 职位 */
public class Job {
    public Job() {
    }

    /** 组织编号 */
    private String organizationID;

    /** 职务编号 */
    private String jobPositionID;

    /** 设立时间 */
    private String startDate;

    /** 名称 */
    private String name;

    /** 编制人数 */
    private Integer assignmentSize;

    /** 详细描述 */
    private String note;

    /** 是否允许超编 */
    private Boolean allowExceed;

    /** 撤销时间 */
    private String endDate;

    /** 撤销原因 */
    private String endNote;

    /** 是否为该组织的负责人 */
    private Boolean isManager;

    /** 员工-职位 */
    private Staff_Job_Relate[] Staff_Job_Relate;

}
