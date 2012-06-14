package org.springside.examples.showcase.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {
	private String sid = "";

	public HttpServletRequestWrapper(String sid, HttpServletRequest request) {
		super(request);
		this.sid = sid;
	}

	public HttpSession getSession(boolean create) {
		return new MemcachedHttpSessionWrapper(this.sid, super.getSession(create));
	}

	public HttpSession getSession() {
		return new MemcachedHttpSessionWrapper(this.sid, super.getSession());
	}
}
