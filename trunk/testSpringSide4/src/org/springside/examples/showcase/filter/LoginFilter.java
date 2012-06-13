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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private static String SESSION_ID = "MY_TEST";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest _request = (HttpServletRequest) request;
		HttpServletResponse _response = (HttpServletResponse) response;

		// String url = _request.getRequestURL().toString();
		System.out.println(_request.getRequestURL().toString());

		Cookie cookies[] = _request.getCookies();
		Cookie sCookie = null;

		String sid = "";
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				sCookie = cookies[i];
				if (sCookie.getName().equals(SESSION_ID)) {
					sid = sCookie.getValue();
				}
			}
		}

		if (sid == null || sid.length() == 0) {
			sid = java.util.UUID.randomUUID().toString();
			Cookie mycookies = new Cookie(SESSION_ID, sid);
			//设置cookie经过多长秒后被删除。如果0，就说明立即删除。如果是负数就表明当浏览器关闭时自动删除。  
			mycookies.setMaxAge(-1);
			// if (this.cookieDomain != null && this.cookieDomain.length() > 0)
			// {
			//mycookies.setDomain("mytest");
			// }
			mycookies.setPath("/");
			_response.addCookie(mycookies);
		}

		chain.doFilter(_request, _response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
