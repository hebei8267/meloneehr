/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.util;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/**
 * JSF 上下文工具类
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FacesContextUtil {
	/**
	 * 取得ViewBean
	 * 
	 * @param beanName bean名称
	 * @return ViewBean
	 */
	@SuppressWarnings("unchecked")
	public static Object getViewBean(String beanName) {
		ExternalContext externalContext = getFacesContext().getExternalContext();
		Object managedBean = null;
		// get request map.
		Map<String, Object> requestMap = externalContext.getRequestMap();

		// Lookup the current bean instance in the request scope.
		managedBean = requestMap.get(beanName);
		if (managedBean != null) {
			return managedBean;
		}

		// Bean is not in the request scope. Get the session map then.
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		// Lookup the current bean instance in the session scope.
		managedBean = sessionMap.get(beanName);

		if (managedBean != null) {
			return managedBean;
		}

		// Bean is also not in the session scope. Get the application map then.
		Map<String, Object> applicationMap = externalContext.getApplicationMap();

		// Lookup the current bean instance in the application scope.
		managedBean = applicationMap.get(beanName);

		if (managedBean != null) {
			return managedBean;
		}

		// Create ManagedBean
		// JSF1.2 Implements

		return getFacesContext().getELContext().getELResolver().getValue(getFacesContext().getELContext(), null, beanName);

		// JSF1.1 Implements
		// return
		// getFacesContext().getApplication().getVariableResolver().resolveVariable(getFacesContext(),
		// beanName);
	}

	/**
	 * 取得 FacesContext
	 * 
	 * @return FacesContext
	 */
	public static FacesContext getFacesContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		return facesContext;
	}

	/**
	 * 取得 RequestParameterMap
	 * 
	 * @return RequestParameterMap
	 */
	@SuppressWarnings("unchecked")
	public static Map getRequestParameterMap() {
		return getFacesContext().getExternalContext().getRequestParameterMap();
	}

	/**
	 * 取得 HttpSession
	 * 
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
		return session;
	}

	/**
	 * 取得 HttpServletRequest
	 * 
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		return request;
	}

	/**
	 * 取得 HttpServletResponse
	 * 
	 * @return HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
		return response;
	}

	/**
	 * 取得根目录路径
	 * 
	 * @return 根目录路径
	 */
	public static String getRealPath() {
		return ((ServletContext) getFacesContext().getExternalContext().getContext()).getRealPath("");
	}
}
