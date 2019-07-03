package com.webinfo.web;

import java.io.IOException;
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
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.webinfo.model.BoardDB;
import com.webinfo.model.CommentDB;

@WebServlet("/ShowDetailScript.do")
public class ShowDetailScript extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowDetailScript() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		
		HashMap<String, Object> article = new HashMap<String, Object>();
		//ArrayList<HashMap<String, Object>> reviewList = new ArrayList<HashMap<String, Object>>();

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
		
		System.out.println("num :: " + num );
		
		new BoardDB().updateReadCount(conn, boardType, num);
		article = new BoardDB().showDetail(conn, boardType, num);
		//reviewList = new CommentDB().showCommentList(conn, boardType, num);
		
		request.setAttribute("article", article);
		request.setAttribute("boardType", boardType);
		
		dispatcher = request.getRequestDispatcher("showDetailPage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
