package cn.hb.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2008-1-27
 * 
 * @author 何 貝
 */
public class DateUtil {
    /**
     * 时间格式yyyyMMdd
     */
    public final static String YYYY_MM_DD_PATTERN_1 = "yyyyMMdd";
    /**
     * 时间格式yyyy/MM/dd
     */
    public final static String YYYY_MM_DD_PATTERN_2 = "yyyy/MM/dd";
    /**
     * 时间格式yyyy年MM月dd日
     */
    public final static String YYYY_MM_DD_PATTERN_3 = "yyyy年MM月dd日";

    /**
     * 取得当前时间
     * 
     * @return 当前时间
     */
    public static String getNowDate() {
        return getNowDate(YYYY_MM_DD_PATTERN_1);
    }

    /**
     * 取得当前时间
     * 
     * @param date_pattern 时间格式
     * @return 当前时间
     */
    public static String getNowDate(String date_pattern) {
        return new SimpleDateFormat(date_pattern).format(new Date());
    }

    /**
     * 将一个日期字符串转化成另一个日期字符串
     * 
     * @param dateStr 日期字符串
     * @param dateStrFormat 转换前的格式
     * @param toDateFormat 转换后的格式
     * @return 转换后期字符串
     */
    public static String dateStrToDateStr(String dateStr, String dateStrFormat, String toDateFormat) {

        try {
            SimpleDateFormat oldDf = new SimpleDateFormat(dateStrFormat);
            Date date = oldDf.parse(dateStr);
            SimpleDateFormat newDf = new SimpleDateFormat(toDateFormat);
            return newDf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间类型转化
     * 
     * @param stmpDate 要格式化的时间
     * @param date_pattern 时间格式
     * @return 格式化以后的结果
     */
    public static String formatDate(Timestamp stmpDate, String date_pattern) {
        SimpleDateFormat df = new SimpleDateFormat(date_pattern);
        return df.format(stmpDate);
    }

}
