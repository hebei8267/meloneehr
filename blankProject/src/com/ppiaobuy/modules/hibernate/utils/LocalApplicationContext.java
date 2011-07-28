package com.ppiaobuy.modules.hibernate.utils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LocalApplicationContext {
	private static ClassPathXmlApplicationContext appContext = null;

	static {
		appContext = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
	}

	public static ClassPathXmlApplicationContext getAppContext() {
		return appContext;
	}
}
