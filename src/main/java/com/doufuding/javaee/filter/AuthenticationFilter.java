package com.doufuding.javaee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.doufuding.java.model.UserInfo;

/**
 * Servlet Filter implementation class AuthenticationFilter
 *权限过滤器。存在问题暂时不使用
 */
//@WebFilter(filterName="authenticationFilter", urlPatterns="/*")
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getServletPath();
		String login = "/jsp/user/login";
		String register = "/jsp/user/register";
		if (isNotMatch(uri, login) && isNotMatch(uri, register)) {
			HttpSession session = req.getSession(false);
			if (session == null) {
				session = req.getSession();
				session.setAttribute("authenticationResult", "用户未登录，请<a href=\"../user/login.jsp\">登录</a>");
				
				return ;
			}
			UserInfo userInfo = (UserInfo) req.getAttribute("userInfo");
			if (userInfo == null) {
				session.setAttribute("authenticationResult", "登录已过期，请重新<a href=\"../user/login.jsp\">登录</a>");
				return ;
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	
	/*
	 * 权限过滤器不匹配login和register
	 * 
	 */
	private boolean isNotMatch(String uri, String match) {
		if (uri.equals(match)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
