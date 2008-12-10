/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao;

import org.freedom.core.test.dao.HibernateDaoTestCase;
import org.freedom.core.util.CreateTable;
import org.junit.Test;

/**
 * CreateTableTest
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class CreateTableTest extends HibernateDaoTestCase {
    @Test
    public void createTable() {
        CreateTable.createTable();
    }
}
