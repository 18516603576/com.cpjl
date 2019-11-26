package com.cpjl.managecenter.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class CookieUtils {

	public static final String COOKIE_KEY_TICKET = "ticket";
	public static final String COOKIE_KEY_SESSIONID = "SESSIONID";

	/**
	 * 获取Ticket值，先查找request参数，再从Cookie中查找
	 * 
	 * @param request
	 * @return
	 */
	public static String getTicket(HttpServletRequest request) {
		String ticket = request.getParameter(COOKIE_KEY_TICKET);
		if (StringUtils.isBlank(ticket)) {
			ticket = getCookieValue(request, COOKIE_KEY_TICKET);
		}
		return ticket;
	}

	/**
	 * 获取Cookie中的会话标识
	 * 
	 * @param request
	 * @return
	 */
	public static String getSessionId(HttpServletRequest request) {
		return getCookieValue(request, COOKIE_KEY_SESSIONID);
	}

	/**
	 * 获取Cookie中的值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 新增ticket值
	 * 
	 * @param response
	 * @param ticket
	 * @param domain
	 */
	public static void addTicket(HttpServletResponse response, String ticket, String domain) {
		add(response, COOKIE_KEY_TICKET, ticket, domain);
	}

	/**
	 * 新增SESSIONID值
	 * 
	 * @param response
	 * @param sessionId
	 * @param domain
	 */
	public static void addSessionId(HttpServletResponse response, String sessionId, String domain) {
		add(response, COOKIE_KEY_SESSIONID, sessionId, domain);
	}

	/**
	 * 增加Cookie值
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		add(response, name, value, null);
	}

	/**
	 * 增加Cookie值。
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param domain
	 */
	public static void add(HttpServletResponse response, String name, String value, String domain) {
		add(response, name, value, domain, -1);
	}

	/**
	 * 增加Cookie值。
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param domain
	 * @param maxAge
	 */
	public static void add(HttpServletResponse response, String name, String value, String domain, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (domain != null) {
			cookie.setDomain(domain);
		}
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 删除Cookie值。
	 * 
	 * @param request
	 * @param response
	 * @param domain
	 * @param names
	 */
	public static void remove(HttpServletRequest request, HttpServletResponse response, String domain,
			String... names) {
		Cookie[] cookies = request.getCookies();
		List<String> nameList = Arrays.asList(names);
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (nameList.contains(cookies[i].getName())) {
					cookies[i].setValue(null);
					cookies[i].setMaxAge(0);
					cookies[i].setDomain(domain);
					cookies[i].setPath("/");
					response.addCookie(cookies[i]);
				}
			}
		}
	}

	/**
	 * 获取客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
