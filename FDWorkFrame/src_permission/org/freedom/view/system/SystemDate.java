/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 系统时间 （该定义在faces-config.xml文件中）
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class SystemDate {
    private final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    private Date nowDate = new Date();

    /**
     * 取得系统当前的日期
     * 
     * @return
     */
    public String getNowDate() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");

        return sf.format(nowDate);
    }

    /**
     * 取得系统当前的星期数
     * 
     * @return
     */
    public String getNowWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        return dayNames[(calendar.get(Calendar.DAY_OF_WEEK)) - 1];
    }
}
