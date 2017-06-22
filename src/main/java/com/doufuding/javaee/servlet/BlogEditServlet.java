package com.doufuding.javaee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doufuding.java.model.UserInfo;
import com.doufuding.java.util.PostgresDriver;

/**
 * Servlet implementation class BlogEdit
 */
@WebServlet(name="blogEdit", urlPatterns="/blogEdit")
public class BlogEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
			session.setAttribute("userCheckResultBlogEdit", "用户未登录。请<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../blog/edit.jsp").forward(request, response);
			return ;
		}
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (userInfo == null) {
			session.setAttribute("userCheckResultBlogEdit", "登录已超时，请用户重新登录。请<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../blog/edit.jsp").forward(request, response);
			return ;
		}
		
		String blogId = request.getParameter("id").trim();
		PostgresDriver postgresDriver = new PostgresDriver();
		session.setAttribute("blogInfo", postgresDriver.getBlogInfo(Integer.parseInt(blogId)));
		session.setAttribute("userInfo", userInfo);
		request.getRequestDispatcher("../blog/edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
