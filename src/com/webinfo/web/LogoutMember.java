package com.webinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webinfo.model.CheckLogin;
import com.webinfo.model.MemberDB;

@WebServlet("/LogoutMember.do")
public class LogoutMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher;

   public LogoutMember() {
	   super();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		//서블릿 역할
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userPw = (String) session.getAttribute("userPw");
		
		System.out.println("로그아웃이 요청된 아이디: " + userId);
		System.out.println("로그아웃이 요청된 패스워드: " + userPw);
		
		session.invalidate();
				
		PrintWriter out = response.getWriter();
		out.print("ok");

	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
