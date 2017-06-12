package com.doufuding.javaee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doufuding.java.model.UserInfo;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="register", urlPatterns="/jsp/user/register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("不接受Get请求").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		UserInfo userInfo = new UserInfo();
		HttpSession session = request.getSession();
		if (!(username.equals("") && password.equals(""))) {
			userInfo.setLoginName(username);
			userInfo.setPassword(password);  
			session.setAttribute("userInfo", userInfo);
			//只能一人注册的问题没有解决
			request.getRequestDispatcher("../index.jsp").forward(request, response);
			//response.sendRedirect("../index.jsp");
		} else {
			request.getRequestDispatcher("register.jsp").forward(request, response);
			//response.sendRedirect("register.jsp");
		}
	}
}
