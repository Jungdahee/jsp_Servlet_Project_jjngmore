package com.webinfo.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webinfo.model.BoardDB;

@WebServlet("/FileDown.do")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileDown() {
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

		String fileName = request.getParameter("file");
		String savePath = "upload";
		String sDownPath = sc.getRealPath(savePath);
		String sFilePath= sDownPath + "\\" + fileName;
		byte b[] = new byte[4096];
		FileInputStream in = new FileInputStream(sFilePath);
		String sMimeType = getServletContext().getMimeType(sFilePath);
		
		if(sMimeType == null) {
			sMimeType = "application/cetet-stream";
		}
		
		response.setContentType(sMimeType);
		String agent = request.getHeader("User-Agent");
		boolean ieBrowser = (agent.indexOf(("MSIE")) > -1) || (agent.indexOf("Trident") > -1);
		
		if(ieBrowser) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		}
		else {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		
		ServletOutputStream out1 = response.getOutputStream();
		int numRead;
		
		while((numRead = in.read(b, 0, b.length)) != -1) {
			out1.write(b, 0, numRead);
		}
		
		out1.flush();
		out1.close();
		in.close();
 		
		dispatcher = request.getRequestDispatcher("shwDetailPage.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
