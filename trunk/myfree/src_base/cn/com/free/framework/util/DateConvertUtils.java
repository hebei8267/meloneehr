package cn.com.free.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateConvertUtils {
	public DateConvertUtils() {
	}

	public static Date parse(String dateString, String dateFormat) {
		return parse(dateString, dateFormat, java.util.Date.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Date parse(String dateString, String dateFormat, Class targetResultType) {
		if (StringUtils.isEmpty(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			long time = df.parse(dateString).getTime();
			Date t = (Date) targetResultType.getConstructor(new Class[] { Long.TYPE }).newInstance(new Object[] { Long.valueOf(time) });
			return t;
		} catch (ParseException e) {
			String errorInfo = (new StringBuilder()).append("cannot use dateformat:").append(dateFormat).append(" parse datestring:")
					.append(dateString).toString();
			throw new IllegalArgumentException(errorInfo, e);
		} catch (Exception e) {
			throw new IllegalArgumentException((new StringBuilder()).append("error targetResultType:").append(targetResultType.getName())
					.toString(), e);
		}
	}

	public static String format(Date date, String dateFormat) {
		if (date == null) {
			return null;
		} else {
			DateFormat df = new SimpleDateFormat(dateFormat);
			return df.format(date);
		}
	}
}
