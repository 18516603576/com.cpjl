package com.cpjl.managecenter.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cpjl.managecenter.entity.User;
 

/**
 * 权限拦截
 * 
 * 
 */
 @Component
public class PrivilegeInterceptor implements HandlerInterceptor  {

	private static final Logger logger = LoggerFactory.getLogger(PrivilegeInterceptor.class);

	/*
	 * preHandle方法将在Controller处理之前调用
	 * 
	 * （非 Javadoc）
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getIsAble()) {
			logger.info("已授权");
			return true;
		} else { 
			logger.info("未授权");
			String errorMsg = java.net.URLEncoder.encode("登录状态已过期请重新登录", "UTF-8");
		 	response.sendRedirect(request.getContextPath() + "/login/index?errors.msg="+errorMsg);
		 	 
			return false;
		}
	}

	/*
	 *  postHandle 在Controller的方法调用之后执行
	 *  但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作。
	 *  
	 * （非 Javadoc）
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	 
}
