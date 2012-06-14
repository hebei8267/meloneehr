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

	/** Memcached Session 超时时间(单位：秒) */
	private int _sessionTimeout;

	// 数量不得超过60* 60* 24 *30（在30天的秒数）
	// 0代表永久有效
	void setSessionTimeout(String sessionTimeout) {
		if (null == sessionTimeout) {
			return;
		}
		_sessionTimeout = Integer.parseInt(sessionTimeout) * 60;
		System.out.println("#################################################_sessionTimeout=" + _sessionTimeout);
	}

	public static synchronized MemcachedSessionService getInstance(String sessionTimeout) {
		if (instance == null) {
			instance = new MemcachedSessionService();
		}
		instance.setSessionTimeout(sessionTimeout);
		return instance;
	}

	public static synchronized MemcachedSessionService getInstance() {
		return getInstance(null);
	}

	private MemcachedSessionService() {
		mc = SpringContextHolder.getBean(SpyMemcachedClient.class);
	}

	@SuppressWarnings("rawtypes")
	public Map getSession(String id) {
		long s1 = System.currentTimeMillis();

		if (null != mc.get(id)) {
			logger.debug(mc.get(id).getClass().toString());
			logger.debug(mc.get(id).toString());
		}
		Map session = mc.get(id);

		if (session == null) {
			session = new HashMap();
			// 单位：秒
			boolean r = mc.safeSet(id, _sessionTimeout, session);
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
		// 单位：秒
		mc.safeSet(id, _sessionTimeout, map);

		logger.debug("saveSession()" + (System.currentTimeMillis() - s1));

	}

	@SuppressWarnings("rawtypes")
	public void removeAttribute(String id, String key) {
		long s1 = System.currentTimeMillis();

		// 取得Session对象
		Map map = getSession(id);
		// 删除attribute
		map.remove(key);
		// 单位：秒
		mc.safeSet(id, _sessionTimeout, map);

		logger.debug("removeAttribute()" + (System.currentTimeMillis() - s1));

	}

	public void removeSession(String id) {
		long s1 = System.currentTimeMillis();

		mc.safeDelete(id);

		logger.debug("removeSession()" + (System.currentTimeMillis() - s1));
	}

}
