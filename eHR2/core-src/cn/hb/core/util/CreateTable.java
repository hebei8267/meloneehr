/**
 *	2007/11/17
 *	@version 
 *	@author
 */

package cn.hb.core.util;

import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

public class CreateTable {
	public static void main(String[] args) {
		CreateTable.createTable();
	}

	public static void createTable() {
		AnnotationSessionFactoryBean factory = (AnnotationSessionFactoryBean) LocalTestUtil.getAppContext().getBean(
				"&sessionFactory");

		factory.dropDatabaseSchema();
		factory.createDatabaseSchema();
	}
}
