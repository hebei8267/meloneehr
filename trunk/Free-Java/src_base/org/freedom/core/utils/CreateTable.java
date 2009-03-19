/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.utils;

import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * 开发时创建数据库表
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class CreateTable {
    public static void main(String[] args) {
        CreateTable.createTable();
    }

    public static void createTable() {
        AnnotationSessionFactoryBean factory = (AnnotationSessionFactoryBean) LocalApplicationContext.getAppContext()
                .getBean("&sessionFactory");

        factory.dropDatabaseSchema();
        factory.createDatabaseSchema();
    }
}
