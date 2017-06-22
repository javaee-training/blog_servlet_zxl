package com.doufuding.javaee.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet(name="blogs", urlPatterns="/jsp/blog/blogs")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SESSION_LOGIN_USER = "login_user";
       
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
		UserInfo userInfo = getUserInSession(request);
		boolean logined = false;
		
		PageInfo pageInfo = null;
		List<TagInfo> tags = getTags();
		
		String strPage = request.getParameter("page");
		Integer page = null;
		if (strPage == null || strPage.trim().isEmpty()) {
			page = 1;
		} else {
			try {
				page = Integer.valueOf(strPage);
			} catch (NumberFormatException e) {
				page = -1;
			}
		}
		
		if (page < 1) {
			response.sendRedirect("/jsp/error/404.jsp");
			return ;
		}
		
		pageInfo = new PageInfo(page);
		List<BlogInfo> blogs = getBlogs(request, pageInfo);
		if (!pageInfo.isInvalidRange()) {
			response.sendRedirect("jsp/error/404.jsp");
			return ;
		}
		
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
	
	private static List<TagInfo> tags;
	
	static {
		tags = new ArrayList<>();
		TagInfo tag1 = new TagInfo(1, "技术文章");
		TagInfo tag2 = new TagInfo(2, "诗词歌赋");
		tags.add(tag1);
		tags.add(tag2);
	}

	private List<TagInfo> getTags() {
		// TODO Auto-generated method stub
		return tags;
	}
	
	private static final String APP_BLOG = "blog";
	
	private List<BlogInfo> getBlogs(HttpServletRequest request, PageInfo pageInfo) {
		@SuppressWarnings("unchecked")
		List<BlogInfo> blogs = (List<BlogInfo>) request.getServletContext().getAttribute(APP_BLOG);
		pageInfo.setRows(blogs.size());
		int endPosition = pageInfo.getStartPosition() + pageInfo.getRowsPerPage();
		if (endPosition > blogs.size()) {
			endPosition = blogs.size();
		}
		return blogs.subList(pageInfo.getStartPosition(), endPosition);
	}

	private UserInfo getUserInSession(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;			
		}
		UserInfo userInfo = (UserInfo) session.getAttribute(SESSION_LOGIN_USER);
		return userInfo;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
