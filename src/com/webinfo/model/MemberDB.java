package com.webinfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MemberDB {

	public MemberDB(){}

	public HashMap login(Connection conn, String userId, String userPw) {

		System.out.println("MemberDB login()에 진입");
		System.out.println("login 서블릿에서 넘어온 ID: " + userId);
		System.out.println("login 서블릿에서 넘어온 PW: " + userPw);

		HashMap<String, String> result = new HashMap<>();
		String sql = "select userId, userPw, userName, userPhone, userCerti1, isAdmin from user where userId = ? and userPw = ?";

		PreparedStatement pstmt = null;
		ResultSet rs= null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);

			rs = pstmt.executeQuery();

			while(rs.next()){
				result.put("userId", rs.getString(1));
				result.put("userPw", rs.getString(2));
				result.put("userName", rs.getString(3));
				result.put("userPhone", rs.getString(4));
				result.put("userCerti1", rs.getString(5));
				result.put("isAdmin", rs.getString(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean join(Connection conn, String userId, String userPw, String userName, String userPhone, String userCerti1, int isAdmin) {
		String sql = "insert into user(userId, userPw, userName, userPhone, userCerti1, isAdmin) values(?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			pstmt.setString(3, userName);
			pstmt.setString(4, userPhone);
			pstmt.setString(5, userCerti1);
			pstmt.setInt(6, isAdmin);

			int i = pstmt.executeUpdate();
			
			if(i == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkId(Connection conn, String userId) {
		String sql = "select count(userId) from user where userId = ?";
		int result = 1;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
				if(result == 0)
					return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean modify(Connection conn, String userId, String userPw, String userName, String userPhone, String userCerti1) {
		String sql = "update user set userPw = ?, userName = ?, userPhone = ?, userCerti1 = ? where userId = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPw);
			pstmt.setString(2, userName);
			pstmt.setString(3, userPhone);
			pstmt.setString(4, userCerti1);
			pstmt.setString(5, userId);

			int i = pstmt.executeUpdate();
			
			if(i == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
