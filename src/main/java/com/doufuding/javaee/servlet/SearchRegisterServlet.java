package com.doufuding.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class SearchRegisterServlet
 */
@WebServlet(name="searchRegisterServlet", urlPatterns="/jsp/searchRegisterServlet")
public class SearchRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchRegisterServlet() {
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
		returnJsonData(response);
	}

	public void returnJsonData(HttpServletResponse response) throws IOException {
		boolean isRegister = false;
		String userName = RegisterServlet.userName;
		String userPassword = RegisterServlet.userPassword;
		if (userName.isEmpty() && userPassword.isEmpty()) {
			isRegister = false;
		} else {
			isRegister = true;
		}
		JSONObject user = new JSONObject();
		try {
			user.put("isRegister", isRegister);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("JSON格式错误。");
		}
		System.out.println(isRegister);
		System.out.println(userName);
		PrintWriter out = response.getWriter();
		out.print(user.toString());
		out.close();
	}

}
