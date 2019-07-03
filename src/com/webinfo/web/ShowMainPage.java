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

@WebServlet("/index.jsp")
public class ShowMainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowMainPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		
		System.out.println("요청 들어옴");
		
		ArrayList<HashMap<String, Object>> articleList = new ArrayList<HashMap<String, Object>>();

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
		
		//원서 접수 중인 시험
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String filter = df.format(new Date());
	    
	    System.out.println("filter :: " + filter);
	    ArrayList<HashMap<String, String>> registerList = new ScheduleDB().showSchedule(conn, filter);
	    if(registerList.size() == 0) {
	    	HashMap<String, String>  list = new HashMap<String, String>();
			list.put("description", "원서 접수 중인 시험이 없습니다.");
			registerList.add(list);
	    	request.setAttribute("registerList", registerList);
	    }
	    else {
	    	request.setAttribute("registerList", registerList);
	    }
		
		//실시간 인기글
		ArrayList<HashMap<String, Object>> scriptList = new BoardDB().showBestArticle(conn);
		request.setAttribute("scriptList", scriptList);
		
		System.out.println("scriptList.size :: " + scriptList.size());
		
		//공지사항
		ArrayList<HashMap<String, Object>> notifyList = new BoardDB().showArticle(conn, "N");
		request.setAttribute("notifyList", notifyList);
		
		System.out.println("notifyList.size :: " + notifyList.size());
		//실시간 스터디 모집글
		ArrayList<HashMap<String, Object>> studyList = new BoardDB().showArticle(conn, "S");
		request.setAttribute("studyList", studyList);
		
		System.out.println("studyList.size :: " + studyList.size());
		dispatcher = request.getRequestDispatcher("index1.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}