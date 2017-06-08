package com.doufuding.javaee.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.doufuding.java.BlogInfo;
import com.doufuding.java.PageInfo;
import com.doufuding.java.TagInfo;
import com.doufuding.java.UserInfo;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/blogs")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		UserInfo userInfo = null;
		boolean logined = false;
		List<BlogInfo> blogs = null;
		PageInfo pageInfo = null;
		List<TagInfo> tags = null;
		//使用跳转而不是直接转发的方式实现。
//		HttpSession session = request.getSession();
//		session.setAttribute("userInfo", userInfo);
//		session.setAttribute("logined", logined);
//		session.setAttribute("blogs", blogs);
//		session.setAttribute("pageInfo", pageInfo);
//		session.setAttribute("tags", tags);
//		response.sendRedirect("/jsp/index.jsp");
		
		//使用转发的方式实现。
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("logined", logined);
		request.setAttribute("blogs", blogs);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("tags", tags);
		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
