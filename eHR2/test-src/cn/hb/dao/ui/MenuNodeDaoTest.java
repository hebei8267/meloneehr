package cn.hb.dao.ui;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.ui.MenuNode;
import cn.hb.dao.ui.MenuNodeDao;
import file.CSVUtils;

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

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "MenuNode.csv");

        for (List<String> fileLine : csvFileContent) {
            MenuNode menuNode = new MenuNode();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    menuNode.setId(value);
                } else if (i == 1) {
                    menuNode.setNodeTxt(value);
                } else if (i == 2) {
                    menuNode.setNodeType(value);
                } else if (i == 3) {
                    menuNode.setDefaultPermit(Boolean.valueOf(value));
                } else if (i == 4) {
                    menuNode.setActionContent(value);
                } else if (i == 5) {
                    MenuNode parentNode = menuNodeDao.getMenuNodeByID(value);

                    parentNode.addSubNode(menuNode);

                    menuNode.setParentNode(parentNode);
                }
            }

            menuNodeDao.save(menuNode);
        }
    }

}
