package com.doufuding.javaee.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doufuding.java.model.BlogInfo;
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
		List<TagInfo> tagInfos = geTagInfos();

		if (title.isEmpty() || tag.isEmpty() || content.isEmpty()) {
			session.setAttribute("tagInfos", tagInfos);
			request.getRequestDispatcher("../blog/add.jsp").forward(request, response);
			return ;
		}
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
		List<BlogInfo> blogInfos = getBlogInfos();

		session.setAttribute("userInfo", userInfo);
		session.setAttribute("blogs", blogInfos);
		request.getRequestDispatcher("../index.jsp").forward(request, response);

	}

	/*
	 * 获得bg_article表中的数据传回index.jsp
	 * 此方法需在后期单独做成一个工具类，以减少不同页面返回index.jsp造成的冗余。
	 * 
	 */
	private List<BlogInfo> getBlogInfos() {
		PostgresDriver postgresDriver = new PostgresDriver();
		String sql = "select * from bg_article";
		Statement statement = null;
		try {
			statement = postgresDriver.getConnection().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet resultSet2 = null;
		try {
			resultSet2 = statement.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<BlogInfo> blogInfos = new ArrayList<>();
		try {
			if (resultSet2 != null) {
				while (resultSet2.next()) {
					BlogInfo blogInfo = new BlogInfo();
					blogInfo.setId(resultSet2.getInt("article_id"));
					blogInfo.setTitle(resultSet2.getString("article_id"));
					blogInfo.setContent(resultSet2.getString("article_content"));
					blogInfo.setCreateTime(resultSet2.getTimestamp("create_time"));
					blogInfo.setCreateUserName(postgresDriver.getUserName(resultSet2.getInt("create_user_id")));
					blogInfo.setTags(postgresDriver.getTagName(resultSet2.getInt("tag_id")));
					blogInfo.setUpdateTime(resultSet2.getTimestamp("update_time"));
					blogInfos.add(blogInfo);
				}
			} else {
				blogInfos = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blogInfos;
	}


	/*
	 * 将bg_tag表中的标签取出来等待使用
	 * 
	 */
	private List<TagInfo> geTagInfos() {
		PostgresDriver postgresDriver = new PostgresDriver();
		String sql = "select tag_id, tag_name from bg_tag";
		Statement statement = null;
		try {
			statement = postgresDriver.getConnection().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet tag = null;
		try {
			tag = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TagInfo> tags = new ArrayList<>();
		try {
			if (tag != null) {
				while (tag.next()) {
					int tagId = tag.getInt("tag_id");
					String tagName = tag.getString("tag_name");
					tags.add(new TagInfo(tagId, tagName));
				}
			} else {
				tags = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tags;
	}

}
