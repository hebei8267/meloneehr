/**
 *	2007/11/17
 *	@version 
 *	@author
 */

package cn.hb.core.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LocalTestUtil {
	private static ClassPathXmlApplicationContext appContext = null;

	static {
		appContext = new ClassPathXmlApplicationContext(
				new String[] { "ApplicationContext.xml" });
	}

	public static ClassPathXmlApplicationContext getAppContext() {
		return appContext;
	}
}