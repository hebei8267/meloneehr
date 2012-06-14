package org.springside.examples.showcase.filter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.cache.memcached.SpyMemcachedClient;
import org.springside.modules.utils.SpringContextHolder;

public class MemcachedSessionService {
	private static Logger logger = LoggerFactory.getLogger(MemcachedSessionService.class);
	private static MemcachedSessionService instance = null;

	private SpyMemcachedClient mc;

	public static synchronized MemcachedSessionService getInstance() {
		if (instance == null) {
			instance = new MemcachedSessionService();
		}
		return instance;
	}

	private MemcachedSessionService() {
		mc = SpringContextHolder.getBean(SpyMemcachedClient.class);
	}

	@SuppressWarnings("rawtypes")
	public Map getSession(String id) {
		long s1 = System.currentTimeMillis();

		Map session = null;

		session = (Map) mc.get(id);
		if (session == null) {
			session = new HashMap();
			// 单位：秒
			// 数量不得超过60* 60* 24 *30（在30天的秒数）
			// 0代表永久有效
			// 注意时间????????
			boolean r = mc.safeSet(id, 0, session);
			logger.debug("Create new Session()" + r);

		}

		logger.debug("getSession()" + (System.currentTimeMillis() - s1));

		return session;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveSession(String id, String key, Object value) {
		long s1 = System.currentTimeMillis();
		// 取得Session对象
		Map map = getSession(id);
		// 设置key/value
		map.put(key, value);
		// 注意时间????????
		mc.safeSet(id, 0, value);

		logger.debug("saveSession()" + (System.currentTimeMillis() - s1));

	}

	@SuppressWarnings("rawtypes")
	public void removeAttribute(String id, String key) {
		long s1 = System.currentTimeMillis();
		// 取得Session对象
		Map map = getSession(id);
		map.remove(key);
		// 此处是否要更新
		logger.debug("removeAttribute()" + (System.currentTimeMillis() - s1));

	}

	public void removeSession(String id) {
		long s1 = System.currentTimeMillis();

		mc.safeDelete(id);

		logger.debug("removeSession()" + (System.currentTimeMillis() - s1));
	}

}
