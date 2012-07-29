package com.tjhx.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjhx.web.aspect.ControllerAdvice;

public class SecuritypFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) _request;

		logger.debug(request.getRequestURI());

		chain.doFilter(request, _response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
