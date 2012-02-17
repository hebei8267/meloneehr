package cn.com.free.framework.model;

import cn.com.free.framework.util.DateConvertUtils;

public abstract class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6021403122973742700L;

	/**
	 * 时间格式 yyyy-MM-dd
	 */
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 时间格式 yyyy-MM
	 */
	protected static final String DATE_FORMAT_MONTH = "yyyy-MM";
	/**
	 * 时间格式 HH:mm:ss
	 */
	protected static final String TIME_FORMAT = "HH:mm:ss";
	/**
	 * 时间格式 yyyy-MM-dd HH:mm:ss
	 */
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 时间格式 yyyy-MM-dd HH:mm:ss.S
	 */
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	/**
	 * 格式转换
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String date2String(final java.util.Date date, final String dateFormat) {
		return DateConvertUtils.format(date, dateFormat);
	}

	/**
	 * 格式转换
	 * 
	 * @param <T>
	 * @param dateString
	 * @param dateFormat
	 * @param targetResultType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends java.util.Date> T string2Date(final String dateString, final String dateFormat, final Class<T> targetResultType) {
		return (T) DateConvertUtils.parse(dateString, dateFormat, targetResultType);
	}
}
