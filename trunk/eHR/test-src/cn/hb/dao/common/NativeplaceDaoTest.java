package cn.hb.dao.common;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.common.Nativeplace;
import cn.hb.dao.common.NativeplaceDao;
import file.CSVUtils;

/**
 * 籍贯 DaoTest
 */
public class NativeplaceDaoTest extends HibernateDaoTestCase {
    private NativeplaceDao nativeplaceDao;

    public NativeplaceDao getNativeplaceDao() {
        return nativeplaceDao;
    }

    public void setNativeplaceDao(NativeplaceDao nativeplaceDao) {
        this.nativeplaceDao = nativeplaceDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Nativeplace.csv");

        for (List<String> fileLine : csvFileContent) {
            Nativeplace nativeplace = new Nativeplace();
            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    nativeplace.setId(value);
                } else if (i == 1) {
                    nativeplace.setName(value);
                } else if (i == 2) {

                    Nativeplace parentNativeplace = nativeplaceDao.getNativeplaceByID(value);

                    parentNativeplace.addSubNativeplace(nativeplace);

                    nativeplace.setParentNativeplace(parentNativeplace);
                }
            }

            nativeplaceDao.save(nativeplace);
        }
    }

}
