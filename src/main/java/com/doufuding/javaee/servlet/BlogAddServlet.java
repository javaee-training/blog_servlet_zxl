package com.doufuding.javaee.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doufuding.java.model.TagInfo;
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
			session.setAttribute("userCheckResultBlogAdd", "用户未登录。请<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../blog/add.jsp").forward(request, response);
			return ;
		}
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (userInfo == null) {
			session.setAttribute("userCheckResultBlogAdd", "登录已超时。请重新<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../blog/add.jsp").forward(request, response);
			return ;
		}

		String title = request.getParameter("title").trim();
		String tag = request.getParameter("tagSelect").trim();
		String content = request.getParameter("content").trim();
		
		PostgresDriver postgresDriver = new PostgresDriver();
		
		List<TagInfo> tagInfos = postgresDriver.geTagInfos();
		int tagId = 0;
		for(Iterator<TagInfo> tagIterator = tagInfos.iterator();tagIterator.hasNext();) {
			TagInfo tagInfo = (TagInfo) tagIterator.next();
			if (tagInfo.getName().equals(tag)) {
				tagId = tagInfo.getId();
				break;
			}
		}
		if (tagId == 0) {
			System.out.println("发生异常错误。");
			System.exit(0);
		}

		if (title.isEmpty() || tag.isEmpty() || content.isEmpty()) {
			session.setAttribute("tagInfos", tagInfos);
			request.getRequestDispatcher("../blog/add.jsp").forward(request, response);
			return ;
		}
		
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
