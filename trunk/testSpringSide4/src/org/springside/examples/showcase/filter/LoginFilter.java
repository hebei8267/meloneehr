package org.springside.examples.showcase.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	protected FilterConfig filterConfig;

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest) request;
		HttpSession _session = _request.getSession();
		String user = (String) _session.getAttribute("username");
		Cookie userCookie = getCookieValue(_request.getCookies(), "username");

		// 判断用户是否登录
		if (null == userCookie) {
			// 判断当前页面是否为login.jsp
			String url = _request.getRequestURL().toString();
			if (-1 == url.indexOf("security")) {
				ServletContext sc = filterConfig.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/");

				try {
					rd.forward(request, response);
					return;
				} catch (ServletException se) {
					filterConfig.getServletContext().log(se.getMessage());
				} catch (IOException e) {
					filterConfig.getServletContext().log(e.getMessage());
				}
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
	}

	protected Cookie getCookieValue(Cookie[] cookies, String name) {
		// 判断是否存在Cookie
		if (null != cookies) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}

		return null;
	}
}
