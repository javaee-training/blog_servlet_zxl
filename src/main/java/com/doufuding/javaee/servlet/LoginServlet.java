package com.doufuding.javaee.servlet;
//此servlet已不再使用
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doufuding.java.model.PageInfo;
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
		HttpSession session = request.getSession(false);
		if (session != null) {//重新登录时，使原来的session失效
			session.invalidate();
		}
		session = request.getSession();
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

		//计算文章总数
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(postgresDriver.getCount(PostgresDriver.COUNT_ARTICLE));
		pageInfo.setRowsPerPage(3);
		

		session.setAttribute("blogs", postgresDriver.getBlogInfos());
		session.setAttribute("userInfo", userInfo);
		session.setAttribute("tags", postgresDriver.geTagInfos());
		session.setAttribute("pageInfo", pageInfo);
		request.getRequestDispatcher("../index.jsp").forward(request, response);
	}

}
