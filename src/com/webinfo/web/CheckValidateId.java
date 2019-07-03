package com.webinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webinfo.model.MemberDB;

@WebServlet("/CheckValidateId.do")
public class CheckValidateId extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckValidateId() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        ServletContext sc = this.getServletContext();
		String dbUrl = sc.getInitParameter("dburl");
		String dbUser = sc.getInitParameter("dbuser");
		String dbPasswd = sc.getInitParameter("dbpasswd");
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			if(conn != null)
				System.out.println("DB 연결");
		} catch(Exception e){
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		String checkId = request.getParameter("data");
		
		boolean isOk = new MemberDB().checkId(conn, checkId);
		
		if(isOk == true) 
			out.write("ok"); //Ajax 요청한 곳에 다시 응답하기
		else 
			out.write("no");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
