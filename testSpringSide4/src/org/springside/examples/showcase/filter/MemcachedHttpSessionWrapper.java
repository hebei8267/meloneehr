package org.springside.examples.showcase.filter;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class MemcachedHttpSessionWrapper extends MemcachedHttpSession {
	private String sid = "";

	@SuppressWarnings("rawtypes")
	private Map map = null;

	public MemcachedHttpSessionWrapper(String sid, HttpSession session, String sessionTimeout) {
		super(session);
		this.sid = sid;
		this.map = MemcachedSessionService.getInstance(sessionTimeout).getSession(sid);
	}

	@Override
	public Object getAttribute(String s) {
		return this.map.get(s);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<String> getAttributeNames() {
		return (new Enumerator(this.map.keySet(), true));
	}

	@Override
	public void invalidate() {
		this.map.clear();
		MemcachedSessionService.getInstance().removeSession(this.sid);
	}

	@Override
	public void removeAttribute(String name) {
		this.map.remove(name);
		MemcachedSessionService.getInstance().removeAttribute(this.sid, name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setAttribute(String name, Object value) {
		this.map.put(name, value);
		MemcachedSessionService.getInstance().saveSession(this.sid, name, value);
	}

	/**
	 * 更新截止日期
	 */
	public void updateExpirationDate() {
		MemcachedSessionService.getInstance().updateExpirationDate(this.sid);
	}
}
