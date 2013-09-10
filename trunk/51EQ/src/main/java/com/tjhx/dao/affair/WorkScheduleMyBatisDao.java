package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.WorkSchedule;


public interface WorkScheduleMyBatisDao {

	public List<WorkSchedule> getWorkScheduleListByDate(String workScheduleDate);

}
