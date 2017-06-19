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

import com.doufuding.java.model.Md5Util;
import com.doufuding.java.model.PostgresDriver;
import com.doufuding.java.model.UserInfo;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="register", urlPatterns="/jsp/user/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//public static String userName = "";

	//public static String userPassword = "";


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

		//判断表单已在过滤器checkNamePwdFilter中实现。
		userInfo.setLoginName(username);
		userInfo.setPassword(password);
		
		//打开数据库链接
		PostgresDriver postgresDriver = new PostgresDriver();
		
		String sql = "insert into bg_user(user_name, user_password) values ('"+username+"','"+Md5Util.getMd5(password)+"')";
		Statement statement = null;
		try {
			statement = postgresDriver.getConnection().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int row = 0;
		try {
			row = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQL语句执行错误。");
		}
		
		if (row == 0) {
			session.setAttribute("userCheckResult", "未知原因导致注册失败。可能该用户已注册。");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
			return ;
		}
		session.setAttribute("isRegister", true);
		session.setAttribute("userInfo", userInfo);
		request.getRequestDispatcher("../index.jsp").forward(request, response);
		//response.sendRedirect("../index.jsp");


	}

	public boolean isNotSet(String str) {
		if (str.isEmpty()) {
			return true;
		}
		return false;
	}

}
