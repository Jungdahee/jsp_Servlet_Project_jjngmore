package com.webinfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class BoardDB {
	
	public BoardDB(){}
	
	public int getListCount(Connection conn){
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from board";

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
	
	public ArrayList<HashMap<String, Object>> showArticle(Connection conn, int page, int limit, String boardType){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> articleList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> article = null;
		int start = (page - 1) * 10;
		
		String sql = "select * from board where boardType = ? order by num desc limit ?, 10";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardType);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new HashMap<String, Object>();
				article.put("num", rs.getInt("num"));
				article.put("userId", rs.getString("userId"));
				article.put("title", rs.getString("title"));
				article.put("content", rs.getString("content"));
				article.put("file", rs.getString("file"));
				article.put("pNum", rs.getInt("pNum"));
				article.put("date", rs.getString("date"));
				article.put("readCount", rs.getInt("readCount"));
				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return articleList;
	}
	
	public ArrayList<HashMap<String, Object>> showMyArticle(Connection conn, int page, int limit, String userId){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> articleList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> article = null;
		int start = (page - 1) * 7;
		
		String sql = "select * from board where userId = ? order by date desc limit ?, 5";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new HashMap<String, Object>();
				article.put("boardType", rs.getString("boardType"));
				article.put("num", rs.getInt("num"));
				article.put("userId", rs.getString("userId"));
				article.put("title", rs.getString("title"));
				article.put("content", rs.getString("content"));
				article.put("file", rs.getString("file"));
				article.put("pNum", rs.getInt("pNum"));
				article.put("date", rs.getString("date"));
				article.put("readCount", rs.getInt("readCount"));
				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return articleList;
	}
	
	public ArrayList<HashMap<String, Object>> showArticle(Connection conn, String boardType){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> scriptList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> article = null;
		
		String sql = "select * from board where boardType = ? order by date desc limit 5";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardType);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new HashMap<String, Object>();
				article.put("boardType", rs.getString("boardType"));
				article.put("num", rs.getInt("num"));
				article.put("userId", rs.getString("userId"));
				article.put("title", rs.getString("title"));
				article.put("content", rs.getString("content"));
				article.put("file", rs.getString("file"));
				article.put("pNum", rs.getInt("pNum"));
				article.put("date", rs.getString("date"));
				article.put("readCount", rs.getInt("readCount"));
				scriptList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return scriptList;
	}
	
	
	public ArrayList<HashMap<String, Object>> showBestArticle(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> articleList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> article = null;
		
		String sql = "select * from board order by readCount desc limit 5";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				article = new HashMap<String, Object>();
				article.put("boardType", rs.getString("boardType"));
				article.put("num", rs.getInt("num"));
				article.put("userId", rs.getString("userId"));
				article.put("title", rs.getString("title"));
				article.put("content", rs.getString("content"));
				article.put("file", rs.getString("file"));
				article.put("pNum", rs.getInt("pNum"));
				article.put("date", rs.getString("date"));
				article.put("readCount", rs.getInt("readCount"));
				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return articleList;
	}
	
	
	public int insert(Connection conn, String boardType, String userId, String title, String content, String file, int pNum) {
		String sql = "select max(num) from board where boardType = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardType);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			else {
				num = 1;
			}
			
			sql = "insert into board values(?, ?, ?, ?, ? ,? ,? , now(), ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardType);
			pstmt.setInt(2, num);
			pstmt.setString(3, userId);
			pstmt.setString(4, title);
			pstmt.setString(5, content);
			pstmt.setString(6, file);
			pstmt.setInt(7, pNum);
			pstmt.setInt(8, 0);
			
			int i = pstmt.executeUpdate();
			
			if(i > 0) {
				return num;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return num;
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
				article.put("boardType", rs.getString("boardType"));
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
	
	public Boolean delete(Connection conn, String boardType, int num){
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		String sql = "delete from board where boardType = ? and num = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardType);
			pstmt.setInt(2, num);
			
			updateCount = pstmt.executeUpdate();
			
			if(updateCount > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		
		return false;
	}
	
	public HashMap<String, Object> update(Connection conn, String boardType, int num, String title, String content, String file, int pNum) {
		String sql = "";
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		HashMap<String, Object> article = new HashMap<String, Object>();
		
		sql = "update board set title =?, content =?, file =?, pNum =? where boardType = ? and num = ?";
		

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, file);
			pstmt.setInt(4, pNum);
			pstmt.setString(5, boardType);
			pstmt.setInt(6, num);

			updateCount = pstmt.executeUpdate();
			
			if(updateCount > 0) {
				article = showDetail(conn, boardType, num);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return article;
		
	}

}
