package com.webinfo.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webinfo.model.MemberDB;

@WebServlet("/ModifyMemberInfo.do")
public class ModifyMemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher;
       
    public ModifyMemberInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
		
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
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userCerti1 = request.getParameter("userCerti1");
		
		System.out.println("userName :: " + userName);
		
		if(userPw.length() != 0 && userName.length() != 0) {
			result = new MemberDB().modify(conn, userId, userPw, userName, userPhone, userCerti1);
		}
		
		if(result == true) {
			session.setAttribute("userPw", userPw);
			session.setAttribute("userName", userName);
			session.setAttribute("userPhone", userPhone);
			session.setAttribute("userCerti1", userCerti1);
			request.setAttribute("modifyResult", "ok");
			dispatcher = request.getRequestDispatcher("myPage.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
