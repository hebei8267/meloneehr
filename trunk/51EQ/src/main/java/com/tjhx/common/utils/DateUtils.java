package com.tjhx.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

	/**
	 * 取得当前时间
	 * 
	 * @return 当前日期（Date）
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 取得当前时间
	 * 
	 * @return 当前日期（String yyyyMMdd）
	 */
	public static String getCurrentDateShortStr() {
		return getCurFormatDate("yyyyMMdd");
	}

	/**
	 * 取得当前时间的特定表示格式的字符串
	 * 
	 * @param formatDate 时间格式（如：yyyy/MM/dd hh:mm:ss）
	 * @return 当前时间
	 */
	public static synchronized String getCurFormatDate(String formatDate) {
		Date date = getCurrentDate();
		simpleDateFormat.applyPattern(formatDate);
		return simpleDateFormat.format(date);
	}

	public static synchronized String getNextTimeFormatDate(int addDay, String formatDate) {
		Calendar cal = Calendar.getInstance();
		Date date = getCurrentDate();
		cal.setTime(date);
		cal.add(Calendar.DATE, addDay);
		simpleDateFormat.applyPattern(formatDate);
		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * 转换日历格式
	 * 
	 * @param date
	 * @param fromFormat
	 * @param toformat
	 * @return
	 */
	public static String transDateFormat(String date, String fromFormat, String toformat) {
		simpleDateFormat.applyPattern(fromFormat);
		try {
			Date _d = simpleDateFormat.parse(date);
			simpleDateFormat.applyPattern(toformat);
			return simpleDateFormat.format(_d);
		} catch (ParseException e) {
			return "";
		}
	}
}
