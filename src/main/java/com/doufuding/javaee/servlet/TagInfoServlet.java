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
 * Servlet implementation class TagInfoServlet
 */
@WebServlet(name="tagInfo", urlPatterns="/jsp/tag/tagInfo")
public class TagInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TagInfoServlet() {
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
			session.setAttribute("userCheckResultUnlogin", "用户未登录。请<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
			return ;
		}
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (userInfo == null) {
			session.setAttribute("userCheckResultRelogin", "登录已超时，请用户重新登录。请<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
			return ;
		}
		
		String tagId = request.getParameter("tagId").trim();
		PostgresDriver postgresDriver = new PostgresDriver();
		session.setAttribute("tagInfo", postgresDriver.getTagInfo(Integer.parseInt(tagId)));
		session.setAttribute("userInfo", userInfo);
		request.getRequestDispatcher("../tag/tag_info.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
