package cn.hb.dao;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.core.util.CreateTable;

public class CreateTableTest extends HibernateDaoTestCase {
	public void testCreateTable() {
		CreateTable.createTable();
	}
}
