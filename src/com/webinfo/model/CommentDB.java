package com.webinfo.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentDB {
	
	public CommentDB(){}
	
	public int getReviewListCount(Connection conn){
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from Comment";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return listCount;
	}
	
	
	public ArrayList<HashMap<String, Object>> showCommentList(Connection conn, String boardType, int boardNum){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> commentList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> comment = null;
		
		String sql = "select * from comment where boardType = ? and boardNum = ? order by date desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardType);
			pstmt.setInt(2, boardNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comment = new HashMap<String, Object>();
				comment.put("boardType", rs.getString("boardType"));
				comment.put("boardNum", rs.getString("boardNum"));
				comment.put("commentNum", rs.getInt("commentNum"));
				comment.put("userId", rs.getString("userId"));
				comment.put("content", rs.getString("content"));
				comment.put("date", rs.getString("date"));
				commentList.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return commentList;
	}
	
	
	public boolean insert(Connection conn, String boardType, int boardNum, String userId, String title, String content) {
		String sql = "select max(num) from comment where boardType = ? and boardNum = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardType);
			pstmt.setInt(2, boardNum);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			else {
				num = 1;
			}
			
			sql = "insert into comment values(?, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardType);
			pstmt.setInt(2, boardNum);
			pstmt.setInt(3, num);
			pstmt.setString(4, userId);
			pstmt.setString(5, content);
			
			int i = pstmt.executeUpdate();
			
			if(i > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public Boolean updateReadCount(Connection conn, String boardType, int num){
		int readCount = 0;
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select max(readCount) from board where boardType = ? and num = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardType);
			pstmt.setInt(2, num);
			
			rs = pstmt.executeQuery();		
			
			if(rs.next()) {
				readCount = rs.getInt(1) + 1;
			}
			else {
				readCount = 1;
			}
			
			System.out.println("readCount ::: " + readCount);
			
			sql = "update board set readCount = ? where boardType = ? and num = ?";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("updateCount ::: " + updateCount);
			pstmt.setInt(1, readCount);
			pstmt.setString(2, boardType);
			pstmt.setInt(3, num);
			updateCount = pstmt.executeUpdate();
			
			if(updateCount > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public HashMap<String, Object> showDetail(Connection conn, String boardType, int num){
		HashMap<String, Object> article = new HashMap<String, Object>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("boardType :::: " + boardType);
		System.out.println("num :::: " + num);
		String sql = "select * from board where boardType = ? and num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardType);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				article.put("num", rs.getInt("num"));
				article.put("userId", rs.getString("userId"));
				article.put("title", rs.getString("title"));
				article.put("content", rs.getString("content"));
				article.put("file", rs.getString("file"));
				article.put("pNum", rs.getInt("pNum"));
				System.out.println("pNum ::: " + rs.getInt("pNum"));
				article.put("date", rs.getString("date"));
				article.put("readCount", rs.getInt("readCount"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return article;
	}

}
