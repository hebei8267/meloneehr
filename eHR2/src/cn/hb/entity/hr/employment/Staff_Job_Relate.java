package cn.hb.entity.hr.employment;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.employment.HoldingJobType;
import cn.hb.entity.hr.organization.Job;

/** 员工-职位 */
public class Staff_Job_Relate {

    /** 员工编号 */
    private String staffID;

    /** 职位编号 */
    private String jobID;

    /** 任职开始时间 */
    private String startDate;

    /** 任职结束时间 */
    private String endDate;

    /** 详细描述 */
    private String note;

    /** 员工 */
    private Staff Staff;

    /** 职位 */
    private Job Job;

    /** 任职类型ID */
    private HoldingJobType holdingJobTypeID;

    /** 任职类型 */
    private HoldingJobType holdingJobType;

}
