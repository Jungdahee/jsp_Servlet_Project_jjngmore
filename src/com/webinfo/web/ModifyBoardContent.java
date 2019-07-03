package com.webinfo.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webinfo.model.BoardDB;

@WebServlet("/ModifyBoardContent.do")
public class ModifyBoardContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyBoardContent() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		HashMap<String, Object> article = new HashMap<String, Object>();
		
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
		
		
		int maxSize = 1024 * 1024 * 10; 

		String uploadPath = sc.getRealPath("/upload");
		System.out.println("절대경로 : " + uploadPath);
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		String boardType = multi.getParameter("boardType");
		
		String title = "";
		String content = "";
		title = multi.getParameter("title");
		content = multi.getParameter("content");
		int num = Integer.parseInt(multi.getParameter("num"));

		if(boardType.equals("F") || boardType.equals("D")) {

			String file1 = "";
			String filename1 = "";
			String originalName1 = "";

			try{

				Enumeration files = multi.getFileNames();

				file1 = (String)files.nextElement();
				filename1 = multi.getFilesystemName(file1);
				originalName1 = multi.getOriginalFileName(file1);


				request.setAttribute("filename1", filename1);
				request.setAttribute("originalName1", originalName1);

			}catch(Exception e){
				e.printStackTrace();
			}
			
			article = new BoardDB().update(conn, boardType, num, title, content, filename1, 0);
		}
		
		if(boardType.equals("N")) {
			article = new BoardDB().update(conn, boardType, num, title, content, null, 0);
		}

		else if(boardType.equals("S")) {
			int pNum = Integer.parseInt(multi.getParameter("pNum"));
			article = new BoardDB().update(conn, boardType, num, title, content, null, pNum);
		}

		request.setAttribute("article", article);
		dispatcher = request.getRequestDispatcher("showDetailPage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
