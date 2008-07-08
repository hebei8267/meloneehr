package cn.hb.entity.hr.employment;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.employment.EmployeeWorkState;

/** 员工 */
public class Staff {
    public Staff() {
    }

    /** 编号 */
    private String id;

    /** 雇佣 */
    private Employment[] Employment;

    /** 员工当前工作状态 */
    private EmployeeWorkState EmployeeWorkState;

    /** 员工-职位 */
    private Staff_Job_Relate[] Staff_Job_Relate;

}
