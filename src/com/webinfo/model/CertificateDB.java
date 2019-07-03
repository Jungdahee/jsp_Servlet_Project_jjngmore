package com.webinfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateDB {

	public CertificateDB(){}
	
	public String search(Connection conn, String certiName) {
		
		System.out.println("Certificate search()에 진입");
		
		String sql = "select certiCode from certificate where certiName = ?";
		
		String certiCode = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs= null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, certiName);

			rs = pstmt.executeQuery();

			while(rs.next()){
				certiCode = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return certiCode;

	}
	
	public String searchSeries(Connection conn, String certiName) {
		
		System.out.println("Certificate search()에 진입");
		
		String sql = "select seriesCode from certificate where certiName = ?";
		
		String seriesCode = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs= null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, certiName);

			rs = pstmt.executeQuery();

			while(rs.next()){
				seriesCode = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seriesCode;

	}
}
