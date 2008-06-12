package cn.hb.dao.common;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.common.Nativeplace;
import cn.hb.dao.common.NativeplaceDao;

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

	public void testCase() {
		Nativeplace nativeplace = new Nativeplace();
	}

}
