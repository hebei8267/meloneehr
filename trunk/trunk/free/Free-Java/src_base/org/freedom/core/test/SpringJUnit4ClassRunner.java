package org.freedom.core.test;

/*
 * 来源于http://thorndike.javaeye.com/?show_full=true
 * 
 * spring3以后可以删除
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.TestContextManager;

public class SpringJUnit4ClassRunner extends BlockJUnit4ClassRunner {
    private static final Log logger = LogFactory.getLog(SpringJUnit4ClassRunner.class);

    private final TestContextManager testContextManager;

    public SpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
        if (logger.isDebugEnabled()) {
            logger.debug("SpringJUnit45ClassRunner constructor called with [" + clazz + "].");
        }
        this.testContextManager = createTestContextManager(clazz);
    }

    protected Object createTest() throws Exception {
        Object testInstance = super.createTest();
        getTestContextManager().prepareTestInstance(testInstance);
        return testInstance;
    }

    protected TestContextManager createTestContextManager(Class<?> clazz) {
        return new TestContextManager(clazz);
    }

    protected final TestContextManager getTestContextManager() {
        return this.testContextManager;
    }
}
