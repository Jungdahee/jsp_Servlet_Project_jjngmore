package com.webinfo.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webinfo.model.BoardDB;
import com.webinfo.model.ScheduleDB;

@WebServlet("/DeleteBoardContent.do")
public class DeleteBoardContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteBoardContent() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		Boolean result = false;
		
		System.out.println("요청 들어옴");
		
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
		
		String boardType = request.getParameter("boardType");
		int num = Integer.parseInt(request.getParameter("num"));
		
		result = new BoardDB().delete(conn, boardType, num);
		
		if(result == true) {
			dispatcher = request.getRequestDispatcher("ShowBoardList.do?boardType=" + boardType);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}