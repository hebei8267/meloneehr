package cn.hb.dao.hr.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.personnel.CardKind;
import cn.hb.dao.hr.personnel.CardKindDao;
import file.CSVUtils;

/**
 * 证件类型 DaoTest
 */
public class CardKindDaoTest extends HibernateDaoTestCase {
    private CardKindDao cardKindDao;

    public CardKindDao getCardKindDao() {
        return cardKindDao;
    }

    public void setCardKindDao(CardKindDao cardKindDao) {
        this.cardKindDao = cardKindDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "CardKind.csv");

        for (List<String> fileLine : csvFileContent) {
            CardKind cardKind = new CardKind();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    cardKind.setMasterID(value);
                } else if (i == 1) {
                    cardKind.setSlaveID(value);
                } else if (i == 2) {
                    cardKind.setName(value);
                }
            }

            cardKindDao.save(cardKind);
        }
    }

}
