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
	 * 取得当前时间
	 * 
	 * @return 当前日期（String yyyy-MM）
	 */
	public static String getCurrentMonth() {
		return getCurFormatDate("yyyy-MM");
	}

	/**
	 * 取得当前时间的特定表示格式的字符串
	 * 
	 * @param style 时间格式（如：yyyy/MM/dd hh:mm:ss）
	 * @return 当前时间
	 */
	public static synchronized String getCurFormatDate(String style) {
		Date date = getCurrentDate();
		simpleDateFormat.applyPattern(style);
		return simpleDateFormat.format(date);
	}

	/**
	 * 取得指定天数后的日期（以当前时间为准）
	 * 
	 * @param addDay
	 * @param style
	 * @return
	 */
	public static synchronized String getNextDateFormatDate(int addDay, String style) {
		Calendar cal = Calendar.getInstance();
		Date _date = getCurrentDate();
		cal.setTime(_date);
		cal.add(Calendar.DATE, addDay);
		simpleDateFormat.applyPattern(style);
		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * 取得指定天数后的日期（以当前时间为准）
	 * 
	 * @param addMonth
	 * @param style
	 * @return
	 */
	public static synchronized String getNextMonthFormatDate(int addMonth, String style) {
		Calendar cal = Calendar.getInstance();
		Date _date = getCurrentDate();
		cal.setTime(_date);
		cal.add(Calendar.MONTH, addMonth);
		simpleDateFormat.applyPattern(style);
		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * 转换日历格式
	 * 
	 * @param date
	 * @param fromStyle
	 * @param toStyle
	 * @return
	 */
	public static String transDateFormat(String date, String fromStyle, String toStyle) {
		simpleDateFormat.applyPattern(fromStyle);
		try {
			Date _d = simpleDateFormat.parse(date);
			simpleDateFormat.applyPattern(toStyle);
			return simpleDateFormat.format(_d);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * String型 ---> Date型変換する
	 * 
	 * @param date 入力のString型
	 * @param style 入力のフォーマット
	 * @return Date Date型時間
	 * 
	 *         2007/01/26 新規作成 weifeng
	 * @throws ParseException 日期不满足格式要求时抛异常
	 */
	public static Date stringToDate(String date, String style) throws ParseException {

		Date da = null;
		simpleDateFormat.applyPattern(style);
		da = simpleDateFormat.parse(date);
		return da;
	}

	/**
	 * 取得dateTo与dateFrom的日期间隔
	 * 
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @param style yyyyMMdd
	 * @return 日期间隔
	 * @throws ParseException
	 */
	public static Long getDateSpanDay(String dateFrom, String dateTo) throws ParseException {
		return getDateSpanDay(dateFrom, dateTo, "yyyyMMdd");
	}

	/**
	 * 取得dateTo与dateFrom的日期间隔
	 * 
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @param style
	 * @return 日期间隔
	 * @throws ParseException
	 */
	public static long getDateSpanDay(String dateFrom, String dateTo, String style) throws ParseException {
		Date _dateFrom = stringToDate(dateFrom, style);
		Date _dateTo = stringToDate(dateTo, style);
		return (_dateTo.getTime() - _dateFrom.getTime()) / (1000 * 3600 * 24);
	}

	/**
	 * 取得dateTo与dateFrom的日期间隔
	 * 
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @return 日期间隔
	 */
	public static long getDateSpanDay(Date dateFrom, Date dateTo) {
		return (dateTo.getTime() - dateFrom.getTime()) / (1000 * 3600 * 24);
	}
}
