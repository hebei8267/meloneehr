package cn.hb.core.util;

import java.util.Locale;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * @author 何 貝
 * 
 * Spring 上下文工具类
 * 
 */
public class SpringContextUtils {
    /**
     * 取得 ApplicationContext
     * 
     * @return ApplicationContext
     */
    public static ApplicationContext getSpringContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ApplicationContext applicationContext = FacesContextUtils.getWebApplicationContext(facesContext);
        return applicationContext;
    }

    /**
     * 取得 SpringBean
     * 
     * @param beanName bean名称
     * @return SpringBean
     */
    public static Object getSpringBean(String beanName) {
        return getSpringContext().getBean(beanName);
    }

    /**
     * 取得消息资源
     * 
     * @param msgKey 消息Key
     * @param args 消息Param
     * @param locale Locale
     * @return
     */
    public static String getMessage(String msgKey, Object[] args, Locale locale) {
        return getSpringContext().getMessage(msgKey, args, locale);
    }
}
