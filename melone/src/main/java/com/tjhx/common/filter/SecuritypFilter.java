package com.tjhx.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class SecuritypFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) _request;
		
		//System.out.println(request.getContextPath());
		System.out.println(request.getRequestURI());
		chain.doFilter(request, _response);
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	
}
