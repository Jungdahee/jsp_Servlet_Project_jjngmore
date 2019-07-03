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

import com.webinfo.model.BoardDB;
import com.webinfo.model.CommentDB;

@WebServlet("/ShowMyPage.do")
public class ShowMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowMyPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		
		ArrayList<HashMap<String, Object>> articleList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Integer> pageInfo = new HashMap<String, Integer>();
		int page = 1;
		int limit = 5;

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
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		int listCount = new BoardDB().getListCount(conn);
		articleList = new BoardDB().showMyArticle(conn, page, limit, userId);
		int maxPage = (int) ((double)listCount / limit + 0.95);
		int startPage = (((int)((double) page / 7 + 0.9)) - 1) * 7 + 1;
		int endPage = startPage + 7 - 1;
		
		
		System.out.println("listCount :: " + listCount);
		
		if(endPage > maxPage) 
			endPage = maxPage;
		
		pageInfo.put("listCount", listCount);
		pageInfo.put("page", page);
		pageInfo.put("maxPage", maxPage);
		pageInfo.put("startPage", startPage);
		pageInfo.put("endPage", endPage);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		dispatcher = request.getRequestDispatcher("myPage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
