package com.webinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webinfo.model.AddCertiSchedule;
import com.webinfo.model.AddCertificateInfo;
import com.webinfo.model.CalcDate;
import com.webinfo.model.CertificateDB;
import com.webinfo.model.CheckLogin;
import com.webinfo.model.MemberDB;
import com.webinfo.model.ScheduleDB;

@WebServlet("/LoginMember.do")
public class LoginMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher;

    public LoginMember() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//DB 연결
        Connection conn = null;
        ServletContext sc = this.getServletContext();
		String dbUrl = sc.getInitParameter("dburl");
		String dbUser = sc.getInitParameter("dbuser");
		String dbPasswd = sc.getInitParameter("dbpasswd");
		
		PrintWriter out = response.getWriter();
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			if(conn != null)
				System.out.println("DB 연결");
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//서블릿 역할
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		System.out.println("로그인이 요청된 아이디: " + userId);
		System.out.println("로그인이 요청된 패스워드: " + userPw);
		
		//로직 실헹
		HashMap<String, String> result = new MemberDB().login(conn, userId, userPw);
		boolean data = new CheckLogin().check(userId, userPw, result);
		
		if(data == true) {			
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("userPw", userPw);
			session.setAttribute("userName", result.get("userName"));
			session.setAttribute("userPhone", result.get("userPhone"));
			session.setAttribute("userCerti1", result.get("userCerti1"));
			session.setAttribute("userCerti2", result.get("userCerti2"));
			session.setAttribute("isAdmin", result.get("isAdmin"));
			
			String seriesCode = new CertificateDB().searchSeries(conn, (String) session.getAttribute("userCerti1"));
			System.out.println("seriesCode ::" + seriesCode);
			session.setAttribute("seriesCode", seriesCode);
			
			System.out.println("세션에 저장된 userId :: " + session.getAttribute("userId"));
			
			request.setAttribute("loginResult", "ok");
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			request.setAttribute("loginResult", "no");
			dispatcher = request.getRequestDispatcher("loginPage.jsp");
			dispatcher.forward(request, response);

		}

	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
