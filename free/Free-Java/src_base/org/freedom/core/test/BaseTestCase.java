/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.test;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.AbstractAnnotationAwareTransactionalTests;
import org.springframework.test.context.ContextConfiguration;

/**
 * 公共Dao测试对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTestCase extends AbstractAnnotationAwareTransactionalTests {
    public BaseTestCase() {
        // 数据不回滚，提交到数据表中
        setDefaultRollback(false);
    }
}
