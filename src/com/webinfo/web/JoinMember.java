package com.webinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webinfo.model.MemberDB;

@WebServlet("/JoinMember.do")
public class JoinMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public JoinMember() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		
		Connection conn = null;
        ServletContext sc = this.getServletContext();
		String dbUrl = sc.getInitParameter("dburl");
		String dbUser = sc.getInitParameter("dbuser");
		String dbPasswd = sc.getInitParameter("dbpasswd");
		
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			if(conn != null)
				System.out.println("DB 연결");
		} catch(Exception e){
			e.printStackTrace();
		}
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userCerti1 = request.getParameter("userCerti1");
		
		System.out.println("userName :: " + userName);
		
		if(userId.length() != 0 && userPw.length() != 0 && userName.length() != 0) {
			result = new MemberDB().join(conn, userId, userPw, userName, userPhone, userCerti1, 0);
		}
		
		if(result == true) {
			request.setAttribute("joinResult", "ok");
			dispatcher = request.getRequestDispatcher("loginPage.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			request.setAttribute("joinResult", "no");
			dispatcher = request.getRequestDispatcher("joinPage.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
