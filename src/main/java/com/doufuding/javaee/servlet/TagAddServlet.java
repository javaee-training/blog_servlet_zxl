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

import com.doufuding.java.model.PostgresDriver;
import com.doufuding.java.model.UserInfo;

/**
 * Servlet implementation class TagAddServlet
 */
@WebServlet(name="tagadd", urlPatterns="/jsp/tag/tagadd")
public class TagAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		String tagName = request.getParameter("tags").trim();
		HttpSession session = request.getSession(false);
		if (session == null) {
			System.out.println("用户未登录。");
			session = request.getSession(true);
			session.setAttribute("userCheckResultTag", "用户未登录。");
			request.getRequestDispatcher("../tag/add.jsp").forward(request, response);
			return ;
		}
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		if (userInfo == null) {
			session = request.getSession();
			session.setAttribute("userCheckResultTag", "用户未登录或登录已过期。<a href=\"../user/login.jsp\">登录</a>");
			request.getRequestDispatcher("../tag/add.jsp").forward(request, response);
			return ;
		}
		System.out.println(userInfo);
		if (tagName.isEmpty()) {//标签名为空
			session.setAttribute("userCheckResultTag", "标签名不能为空。");
			request.getRequestDispatcher("../tag/add.jsp").forward(request, response);
			return ;
		}
		
		PostgresDriver postgresDriver = new PostgresDriver();
		
		String sql = "insert into bg_tag(tag_name, create_user_id) values('"+tagName+"','"+userInfo.getId()+"')";
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
			session.setAttribute("userCheckResultTag", "未知原因导致标签添加失败。可能标签已存在。");
			request.getRequestDispatcher("../tag/add.jsp").forward(request, response);
			return ;
		}
		
		session.setAttribute("userInfo", userInfo);
		request.getRequestDispatcher("../index.jsp").forward(request, response);
	}

}
