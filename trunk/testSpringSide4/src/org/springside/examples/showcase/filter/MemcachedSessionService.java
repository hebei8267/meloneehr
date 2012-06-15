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
	private static String USER_SESSION_PREFIX = "USER_SESSION_";

	// 数量不得超过60* 60* 24 *30（在30天的秒数）
	// 0代表永久有效
	void setSessionTimeout(String sessionTimeout) {
		if (null == sessionTimeout) {
			return;
		}
		_sessionTimeout = Integer.parseInt(sessionTimeout) * 60;
		// logger.debug("Set Session Timeout " + _sessionTimeout);
	}

	public static synchronized MemcachedSessionService getInstance(String sessionTimeout) {
		if (null == instance) {
			instance = new MemcachedSessionService();
			instance.setSessionTimeout(sessionTimeout);
		}

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
		// long s1 = System.currentTimeMillis();

		String _id = USER_SESSION_PREFIX + id;
		Map session = mc.get(_id);

		if (session == null) {
			session = new HashMap();
			// 单位：秒
			boolean r = mc.safeSet(_id, _sessionTimeout, session);

			logger.debug("Create new Session()" + r);
		}

		// logger.debug("getSession()" + (System.currentTimeMillis() - s1));

		return session;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveSession(String id, String key, Object value) {
		// long s1 = System.currentTimeMillis();

		// 取得Session对象
		Map map = getSession(id);
		// 设置key/value
		map.put(key, value);

		String _id = USER_SESSION_PREFIX + id;
		// 单位：秒
		mc.safeSet(_id, _sessionTimeout, map);

		// logger.debug("saveSession()" + (System.currentTimeMillis() - s1));

	}

	@SuppressWarnings("rawtypes")
	public void removeAttribute(String id, String key) {
		// long s1 = System.currentTimeMillis();

		// 取得Session对象
		Map map = getSession(id);
		// 删除attribute
		map.remove(key);

		String _id = USER_SESSION_PREFIX + id;
		// 单位：秒
		mc.safeSet(_id, _sessionTimeout, map);

		// logger.debug("removeAttribute()" + (System.currentTimeMillis() -
		// s1));

	}

	public void removeSession(String id) {
		// long s1 = System.currentTimeMillis();

		String _id = USER_SESSION_PREFIX + id;
		mc.safeDelete(_id);

		// logger.debug("removeSession()" + (System.currentTimeMillis() - s1));
	}

	/**
	 * 更新截止日期
	 */
	@SuppressWarnings("rawtypes")
	public void updateExpirationDate(String id) {
		// long s1 = System.currentTimeMillis();

		// 取得Session对象
		Map map = getSession(id);

		String _id = USER_SESSION_PREFIX + id;
		mc.replace(_id, _sessionTimeout, map);

		// logger.debug("removeAttribute()" + (System.currentTimeMillis() -
		// s1));
	}

}
