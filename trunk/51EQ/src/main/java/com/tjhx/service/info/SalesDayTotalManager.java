package com.tjhx.service.info;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class SalesDayTotalManager {

	/**
	 * 取得百威系统各门店日销售信息
	 * 
	 * @throws ParseException
	 */
	public void getOrgSalesDayTotal() throws ParseException {
		// 取得考勤计算-重计算天数
		List<String> optDateList = calOptDate();

	}

	/**
	 * 取得考勤计算-重计算天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurrentDateShortStr();

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getSalesDayTotalDays(); i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyyMMdd"));
		}

		_optDateList.add(_now);

		return _optDateList;
	}
}
