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

import com.webinfo.model.CalcDate;
import com.webinfo.model.ScheduleDB;

@WebServlet("/ShowMyMessage.do")
public class ShowMyMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowMyMessage() {
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

		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			if(conn != null)
				System.out.println("DB 연결");
		} catch(Exception e){
			e.printStackTrace();
		}

		HashMap<String, String> filter =  new CalcDate().calcDay();
		HttpSession session = request.getSession();
		String seriesCode = (String) session.getAttribute("seriesCode");
		System.out.println("seriesCode :: " + seriesCode);
		ArrayList<HashMap<String, String>> scheduleList1 = new ScheduleDB().notify1(conn, seriesCode, filter.get("dday3"), filter.get("dday1"));
		ArrayList<HashMap<String, String>> scheduleList2 = new ScheduleDB().notify2(conn, seriesCode, filter.get("dday3"), filter.get("dday1"));
		ArrayList<HashMap<String, String>> scheduleList3 = new ScheduleDB().notify3(conn, seriesCode, filter.get("dday3"), filter.get("dday1"));
		ArrayList<HashMap<String, String>> scheduleList4 = new ScheduleDB().notify4(conn, seriesCode, filter.get("dday3"), filter.get("dday1"));

		request.setAttribute("scheduleList1", scheduleList1);
		request.setAttribute("scheduleList2", scheduleList2);
		request.setAttribute("scheduleList3", scheduleList3);
		request.setAttribute("scheduleList4", scheduleList4);

		dispatcher = request.getRequestDispatcher("msgPage.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

