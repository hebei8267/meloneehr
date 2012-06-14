package org.springside.examples.showcase.filter;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

@SuppressWarnings("deprecation")
public class MemcachedHttpSession implements HttpSession {
	private HttpSession session;

	public MemcachedHttpSession(HttpSession session) {
		this.session = session;
	}

	@Override
	public Object getAttribute(String s) {
		return this.session.getAttribute(s);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return this.session.getAttributeNames();
	}

	@Override
	public long getCreationTime() {
		return session.getCreationTime();
	}

	@Override
	public String getId() {
		return session.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return session.getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {
		return session.getMaxInactiveInterval();
	}

	@Override
	public ServletContext getServletContext() {
		return session.getServletContext();
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return session.getSessionContext();
	}

	@Override
	public Object getValue(String s) {
		return session.getValue(s);
	}

	@Override
	public String[] getValueNames() {
		return session.getValueNames();
	}

	@Override
	public void invalidate() {
		this.session.invalidate();
	}

	@Override
	public boolean isNew() {
		return session.isNew();
	}

	@Override
	public void putValue(String s, Object obj) {
		session.putValue(s, obj);
	}

	@Override
	public void removeAttribute(String s) {
		this.session.removeAttribute(s);
	}

	@Override
	public void removeValue(String s) {
		session.removeValue(s);
	}

	@Override
	public void setAttribute(String s, Object obj) {
		this.session.setAttribute(s, obj);
	}

	@Override
	public void setMaxInactiveInterval(int i) {
		session.setMaxInactiveInterval(i);
	}

}
