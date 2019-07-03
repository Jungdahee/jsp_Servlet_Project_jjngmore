package com.webinfo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.webinfo.model.BoardDB;

@WebServlet("/AddBoardComment.do")
public class AddBoardComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBoardComment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		Boolean result = false;
		String viewPage = null;

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
		
		String data = request.getParameter("data");
		/*HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if(boardType.equals("N")) {
			result = new BoardDB().insert(conn, boardType, userId, title, content, null, 0);
			viewPage = "notifyBoardPage.jsp";
		}
		if(boardType.equals("F") || boardType.equals("D")) {
			String file = request.getParameter("file");
			result = new BoardDB().insert(conn, boardType, userId, title, content, file, 0);
			
			if(boardType.equals("F"))
				viewPage = "ShowBoardList.do?boardType=F";
			else
				viewPage = "ShowBoardList.do?boardType=D";
		}
		else if(boardType.equals("S")) {
			int pNum = Integer.parseInt(request.getParameter("pNum"));
			result = new BoardDB().insert(conn, boardType, userId, title, content, null, pNum);
			viewPage = "ShowBoardList.do?boardType=S";
		}
		
		if(result == true) 
			request.setAttribute("regResult", "ok");
		else 
			request.setAttribute("regResult", "no");
		
		dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
