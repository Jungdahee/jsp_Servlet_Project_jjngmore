package com.webinfo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webinfo.model.CertificateDB;
import com.webinfo.model.SearchCerti;

@WebServlet("/SearchCertiInfo.do")
public class SearchCertiInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchCertiInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

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

		String param = "";
		String searchParam = "";
		HttpSession session = request.getSession();
		
		if(request.getParameter("data") != null) {
			param = request.getParameter("data");
			searchParam = param.replaceAll(" ", "");
		}
		else {
			searchParam = (String) session.getAttribute("userCerti1");
		}

		System.out.println("searchParam :: " + searchParam);

		String certiCode = new CertificateDB().search(conn, searchParam);
		System.out.println("certiCode :: " + certiCode);

		
		String searchResult = "";
		try {
			searchResult = new SearchCerti().search(certiCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();
		out.print(searchResult);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


