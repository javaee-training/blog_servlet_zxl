package com.doufuding.javaee.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doufuding.java.model.UserInfo;
import com.doufuding.java.util.PostgresDriver;

/**
 * Servlet implementation class BlogAddServlet
 */
@WebServlet(name="blogAdd", urlPatterns="/jsp/blog/blogAdd")
public class BlogAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null) {//此处应修改为使用权限过滤器。
			session = request.getSession();
			session.setAttribute("userCheckResultUnlogin", "用户未登录。请<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
			return ;
		}
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (userInfo == null) {
			session.setAttribute("userCheckResultRelogin", "登录已超时。请重新<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
			return ;
		}

		String title = request.getParameter("title").trim();
		String tag = request.getParameter("tagSelect").trim();
		String content = request.getParameter("content").trim();
		
		
		int tagId = Integer.parseInt(tag);

		if (title.isEmpty() || tagId == 0 || content.isEmpty()) {
			session.setAttribute("userCheckResultBlogAdd", "各项均为必填字段。");
			request.getRequestDispatcher("../blog/add.jsp").forward(request, response);
			return ;
		}
		
		PostgresDriver postgresDriver = new PostgresDriver();
		String sql = "insert into bg_article(article_title, article_content, tag_id, create_user_id) values('"+title+"','"+content+"','"+tagId+"','"+userInfo.getId()+"')";
		Statement statement = null;
		try {
			statement = postgresDriver.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int row = 0;
		try {
			row = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (row == 0) {
			session.setAttribute("userCheckResultBlogAdd", "数据库异常。文章添加失败。");
			request.getRequestDispatcher("../blog/add.jsp").forward(request, response);
			return ;
		}
		
		session.setAttribute("userInfo", userInfo);
		session.setAttribute("blogs", postgresDriver.getBlogInfos());
		request.getRequestDispatcher("../index.jsp").forward(request, response);

	}

}
