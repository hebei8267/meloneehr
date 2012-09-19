package com.tjhx.common.utils;

import java.text.SimpleDateFormat;
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
}
