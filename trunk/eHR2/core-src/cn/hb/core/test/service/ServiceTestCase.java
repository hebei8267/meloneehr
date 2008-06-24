package cn.hb.core.test.service;

import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 * @author kaka
 * 
 */
public class ServiceTestCase extends AbstractTransactionalSpringContextTests {
    public ServiceTestCase() {
        // 数据不回滚，提交到数据表中
        setDefaultRollback(false);

    }

    protected String[] getConfigLocations() {
        return new String[] { "classpath:/ApplicationContext.xml" };
    }
}
