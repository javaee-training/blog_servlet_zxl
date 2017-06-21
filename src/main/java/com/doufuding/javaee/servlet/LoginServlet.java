package com.doufuding.javaee.servlet;
//此servlet已不再使用
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doufuding.java.model.BlogInfo;
import com.doufuding.java.model.PageInfo;
import com.doufuding.java.model.TagInfo;
import com.doufuding.java.model.UserInfo;
import com.doufuding.java.util.Md5Util;
import com.doufuding.java.util.PostgresDriver;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="login", urlPatterns="/jsp/user/login")
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
		HttpSession session = request.getSession();
		UserInfo userInfo = new UserInfo();

		userInfo.setLoginName(username);
		userInfo.setPassword("");//不存储用户密码
		userInfo.setCreateTime(new Date());

		PostgresDriver postgresDriver = new PostgresDriver();
		//此处没有防止sql注入问题。
		String sql = "select user_id from bg_user where user_name='"+username+"' and user_password='"+Md5Util.getMd5(password)+"'";
		Statement statement = null;
		try {
			statement = postgresDriver.getConnection().createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int userId = 0;
		//System.out.println(resultSet);
		try {
			if (resultSet.next()) {
				userId = resultSet.getInt("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userId == 0) {
			//			System.out.println("查询失败。");
			session.setAttribute("userCheckResultLogin", "登录失败。请检查用户名和密码。");
			request.getRequestDispatcher("../user/login.jsp").forward(request, response);
			return ;
		}
		userInfo.setId(userId);

		//用户名密码验证成功后从bg_blog表中读数据，此处与RegisterServlet跳转到index.jsp相同，建议写到一起。
		sql = "select * from bg_article";//登录用户可以查看所有文章。
		ResultSet resultSet2 = null;
		try {
			resultSet2 = statement.executeQuery(sql);
			//System.out.println(resultSet2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//将从bg_blog表中查询到的数据放在集合类List<BlogInfo>中，传回index.jsp
		List<BlogInfo> blogInfos = new ArrayList<>();
		int count = 0;
		try {
			if (resultSet2 != null) {
				while (resultSet2.next()) {
					BlogInfo blogInfo = new BlogInfo();
					blogInfo.setId(resultSet2.getInt("article_id"));
					blogInfo.setTitle(resultSet2.getString("article_title"));
					blogInfo.setContent(resultSet2.getString("article_content"));
					blogInfo.setCreateTime(resultSet2.getTimestamp("create_time"));
					blogInfo.setCreateUserName(postgresDriver.getUserName(resultSet2.getInt("create_user_id")));
					blogInfo.setTags(postgresDriver.getTagName(resultSet2.getInt("tag_id")));
					blogInfo.setUpdateTime(resultSet2.getTimestamp("update_time"));
					count++;
					blogInfos.add(blogInfo);
				}
			} else {
				blogInfos = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(count);
		pageInfo.setRowsPerPage(3);
		

		session.setAttribute("blogs", blogInfos);
		session.setAttribute("userInfo", userInfo);
		session.setAttribute("tags", geTagInfos());
		session.setAttribute("pageInfo", pageInfo);
		request.getRequestDispatcher("../index.jsp").forward(request, response);
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
