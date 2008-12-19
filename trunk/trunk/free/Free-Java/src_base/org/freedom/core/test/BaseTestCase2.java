/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.test;

import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 * 公共Dao测试对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class BaseTestCase2 extends AbstractTransactionalSpringContextTests {
    public BaseTestCase2() {
        // 数据不回滚，提交到数据表中
        setDefaultRollback(false);

    }

    protected String[] getConfigLocations() {
        return new String[] { "classpath:/applicationContext.xml" };
    }

}
