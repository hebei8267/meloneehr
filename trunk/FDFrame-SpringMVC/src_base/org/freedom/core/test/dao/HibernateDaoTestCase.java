/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.test.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 * 公共Dao测试对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class HibernateDaoTestCase extends AbstractTransactionalSpringContextTests {
    public HibernateDaoTestCase() {
        // 数据不回滚，提交到数据表中
        setDefaultRollback(false);

    }

    protected String[] getConfigLocations() {
        return new String[] { "classpath:/applicationContext.xml" };
    }

    protected String getMaxID(int id) {

        if (id > -1) {
            return String.format("%1$08d", Integer.valueOf(id) + 1);
        }

        return null;
    }

    protected String getCurrentDate() {
        Date date = new Date();
        return dateFormat(date);

    }

    protected String getMaxDate() {
        return "99991231";

    }

    protected String dateFormat(Date date) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
        return sm.format(date);
    }
}
