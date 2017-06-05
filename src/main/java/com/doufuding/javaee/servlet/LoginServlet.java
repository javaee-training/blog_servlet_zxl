package com.doufuding.javaee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="login", urlPatterns="/user/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		if (username.equals("zhangxile") && password.equals("123456")) {
			//System.out.println("登录成功");
			request.setAttribute("returnusername", "张希乐");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			//System.out.println("登录失败");
			request.setAttribute("returnusername", "");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
