/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package test.org.freedom.dao;

import org.freedom.core.test.dao.HibernateDaoTestCase;
import org.freedom.core.util.CreateTable;

/**
 * CreateTableTest
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class CreateTableTest extends HibernateDaoTestCase {
    public void testCreateTable() {
        CreateTable.createTable();
    }
}
