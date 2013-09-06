package com.tjhx.service.affair;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.WorkSchedule_List_Show;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class WorkScheduleManager {
	/**
	 * 取得排班信息列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<WorkSchedule_List_Show> getWorkScheduleList() throws ParseException {
		List<WorkSchedule_List_Show> wsList = new ArrayList<WorkSchedule_List_Show>();

		// 取得计算可排班天数（含明天，列表第一个对象）
		List<String> optDateList = calOptDate();

		for (int i = 1; i < optDateList.size(); i++) {
			WorkSchedule_List_Show ws = new WorkSchedule_List_Show();
			ws.setScheduleDate(optDateList.get(i - 1));
			ws.setWeek(DateUtils.getWeekOfDate(optDateList.get(i - 1), "yyyy-MM-dd"));

			wsList.add(ws);
		}

		return wsList;
	}

	/**
	 * 计算可排班天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurFormatDate("yyyy-MM-dd");

		_optDateList.add(_now);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getWorkScheduleDays() + 1; i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, i, "yyyy-MM-dd"));
		}
		return _optDateList;
	}
}
