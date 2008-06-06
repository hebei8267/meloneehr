package cn.hb.core.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class AbstractViewBean {
    /** Bean生命周期中初始化方法 */
    @PostConstruct
    protected void _create() {
        create();
    }

    /** Bean生命周期中初始化方法 */
    public abstract void create();

    /** Bean生命周期中销毁方法 */
    @PreDestroy
    protected void _destroy() {
        destroy();
    }

    /** Bean生命周期中销毁方法 */
    public abstract void destroy();

    public abstract void init();
}
