package cn.hb.dao.ui;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.ui.MenuNode;
import cn.hb.dao.ui.MenuNodeDao;

/**
 * 菜单树结点 DaoTest
 */
public class MenuNodeDaoTest extends HibernateDaoTestCase {
	private MenuNodeDao menuNodeDao;

	public MenuNodeDao getMenuNodeDao() {
		return menuNodeDao;
	}

	public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
		this.menuNodeDao = menuNodeDao;
	}

	public void testCase() {
		MenuNode menuNode = new MenuNode();
		//TODO
	}

}
