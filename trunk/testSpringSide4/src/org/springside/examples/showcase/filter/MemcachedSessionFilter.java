package org.springside.examples.showcase.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemcachedSessionFilter extends HttpServlet implements Filter {
	private static Logger logger = LoggerFactory.getLogger(MemcachedSessionFilter.class);
	private static final long serialVersionUID = -290541581567905229L;

	private String sessionId = "memcached_session_id";

	private String cookieDomain = "";

	private String cookiePath = "/";

	/** Memcached Session 超时时间(单位：分钟) */
	private String sessionTimeout;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest _request = (HttpServletRequest) request;
		HttpServletResponse _response = (HttpServletResponse) response;

		logger.debug(_request.getRequestURL().toString());

		// 从Cookie中取得Session的key
		String sid = getCookieValue(_request);

		// TODO:在此作身份验证

		// 判断当前时候否存在Cookie，如果没有则创建新的Cookie
		createCookie(sid, _response);

		HttpServletRequestWrapper _requestWrapper = new HttpServletRequestWrapper(sid, _request, sessionTimeout);
		// 更新截止日期
		_requestWrapper.updateExpirationDate();

		chain.doFilter(_requestWrapper, _response);

	}

	/**
	 * 判断当前时候否存在Cookie，如果没有则创建新的Cookie
	 * 
	 * @param sid Session的key
	 * @param response
	 */
	private void createCookie(String sid, HttpServletResponse response) {
		if (sid == null || sid.length() == 0) {
			sid = java.util.UUID.randomUUID().toString();

			logger.debug("Create Cookie sid value" + sid);

			Cookie mycookies = new Cookie(sessionId, sid);

			// 设置cookie经过多长秒后被删除。如果0，就说明立即删除。如果是负数就表明当浏览器关闭时自动删除。
			mycookies.setMaxAge(-1);
			if (this.cookieDomain != null && this.cookieDomain.length() > 0) {
				mycookies.setDomain(this.cookieDomain);
			}
			mycookies.setPath(this.cookiePath);

			response.addCookie(mycookies);
		}
	}

	/**
	 * 从Cookie中取得Session的key
	 * 
	 * @param request HttpServletRequest
	 * @return Session的key
	 */
	private String getCookieValue(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(sessionId)) {
					String sid = cookie.getValue();

					logger.debug("Get Cookie sid value" + sid);
					return sid;
				}
			}
		}

		return null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.sessionId = filterConfig.getInitParameter("sessionId");
		this.cookieDomain = filterConfig.getInitParameter("cookieDomain");
		this.sessionTimeout = filterConfig.getInitParameter("sessionTimeout");

		if (this.cookieDomain == null) {
			this.cookieDomain = "";
		}

		this.cookiePath = filterConfig.getInitParameter("cookiePath");
		if (this.cookiePath == null || this.cookiePath.length() == 0) {
			this.cookiePath = "/";
		}

		if (this.sessionTimeout == null) {// 默认10分钟
			this.sessionTimeout = "10";
		}
	}

}
