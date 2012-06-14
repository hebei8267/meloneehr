package org.springside.examples.showcase.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {
	private String sid = "";

	/** Memcached Session 超时时间(单位：分钟) */
	private String sessionTimeout;

	public HttpServletRequestWrapper(String sid, HttpServletRequest request, String sessionTimeout) {
		super(request);
		this.sid = sid;
		this.sessionTimeout = sessionTimeout;
	}

	@Override
	public HttpSession getSession(boolean create) {
		return new MemcachedHttpSessionWrapper(this.sid, super.getSession(create), sessionTimeout);
	}

	@Override
	public HttpSession getSession() {
		return new MemcachedHttpSessionWrapper(this.sid, super.getSession(), sessionTimeout);
	}
}
