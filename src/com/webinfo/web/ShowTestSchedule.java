package com.webinfo.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javax.servlet.http.HttpSession;

import com.webinfo.model.BoardDB;
import com.webinfo.model.CalcDate;
import com.webinfo.model.ScheduleDB;

@WebServlet("/ShowTestSchedule.do")
public class ShowTestSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowTestSchedule() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		
		ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
		
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
		
		HttpSession session = request.getSession();
		String userCerti1 = (String) session.getAttribute("userCerti1");
		String seriesCode = (String) session.getAttribute("seriesCode");
		System.out.println("seriesCode :: " + seriesCode);
		
        scheduleList = new ScheduleDB().showShedule(conn);
        
        if(seriesCode != null) {
    		ArrayList<HashMap<String, String>> myScheduleList = new ArrayList<HashMap<String, String>>();
        	myScheduleList = new ScheduleDB().showMyShedule(conn, seriesCode);
        	request.setAttribute("myScheduleList", myScheduleList);
        	request.setAttribute("scheduleList", scheduleList);
        	
        	dispatcher = request.getRequestDispatcher("testSchedulePage.jsp");
        	dispatcher.forward(request, response);
        }
        
        else if(seriesCode == null || seriesCode.length() == 0) {
        	request.setAttribute("scheduleList", scheduleList);
        	request.setAttribute("myScheduleList", null);
        	
        	dispatcher = request.getRequestDispatcher("testSchedulePage.jsp");
        	dispatcher.forward(request, response);
        }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
