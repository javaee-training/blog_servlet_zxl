package com.doufuding.javaee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//此servlet已不再使用。
/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(name="indexServlet", urlPatterns="/jsp/indexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		boolean isRegister = false;
//		String userName = RegisterServlet.userName;
//		String userPassword = RegisterServlet.userPassword;
//		if (userName.isEmpty() && userPassword.isEmpty()) {
//			isRegister = false;
//		} else {
//			isRegister = true;
//		}
		HttpSession session = request.getSession();
		session.setAttribute("isRegister", isRegister);
		request.getRequestDispatcher("../jsp/index.jsp").forward(request, response);
	}

}
