package com.doufuding.javaee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class CheckNamePwdFilter
 */
//此处urlPatterns需要匹配多个Servlet，只能在web.xml中配置
//@WebFilter(filterName="checkNamePwdFilter", urlPatterns="/jsp/user/login")
public class CheckNamePwdFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckNamePwdFilter() {
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
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		
		//校验输入是否为空
		if (username.isEmpty() || password.isEmpty()) {
			request.setAttribute("userCheckResult", " 用户名或密码为空!");
			request.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
			return ;
		}
		
		//校验用户名是否合法
		for(int i = 0; i < username.length(); i++) {
			if (isNotLetter(username.charAt(i))) {
				request.setAttribute("userCheckResult", " 用户名只能包含英文字符");
				request.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
				return ;
			}
		}
		
		//校验密码长度是否合法
		if (password.length() < 3) {
			request.setAttribute("userCheckResult", " 密码最少包含三个字符");
			request.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
			return ;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	/*
	 * 判断传入的字符是否为字母
	 * 
	 */
	public boolean isNotLetter(char c) {
		if (c <= 'z' && c >= 'a') {
			return false;
		} else if (c <= 'Z' && c >= 'A') {
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
