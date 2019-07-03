package com.webinfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ScheduleDB {

	public ScheduleDB(){}

	public ArrayList<HashMap<String, String>> showShedule(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> schedule = null;
		
		String sql = "select * from schedule";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				schedule = new HashMap<String, String>();
				schedule.put("seriedCode", rs.getString("seriesCode"));
				schedule.put("description", rs.getString("description"));
				schedule.put("docRegStart", rs.getString("docRegStart"));
				schedule.put("docRegEnd", rs.getString("docRegEnd"));
				schedule.put("docExam", rs.getString("docExam"));
				schedule.put("docPass", rs.getString("docPass"));
				schedule.put("pracRegStart", rs.getString("pracRegStart"));
				schedule.put("pracRegEnd", rs.getString("pracRegEnd"));
				schedule.put("pracExamStart", rs.getString("pracExamStart"));
				schedule.put("pracExamEnd", rs.getString("pracExamEnd"));
				schedule.put("pracPass", rs.getString("pracPass"));
				scheduleList.add(schedule);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return scheduleList;
	}
	
	public ArrayList<HashMap<String, String>> showSchedule(Connection conn, String filter){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> schedule = null;
		
		String sql = "select * from schedule where docRegStart <= ? and docRegEnd >= ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, filter);
			pstmt.setString(2, filter);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				schedule = new HashMap<String, String>();
				schedule.put("seriedCode", rs.getString("seriesCode"));
				schedule.put("description", rs.getString("description"));
				schedule.put("docRegStart", rs.getString("docRegStart"));
				schedule.put("docRegEnd", rs.getString("docRegEnd"));
				scheduleList.add(schedule);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return scheduleList;
	}
	
	public ArrayList<HashMap<String, String>> showMyShedule(Connection conn, String seriesCode){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> myScheduleList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> schedule = null;
		
		String sql = "select * from schedule where seriesCode = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seriesCode);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				schedule = new HashMap<String, String>();
				schedule.put("seriedCode", rs.getString("seriesCode"));
				schedule.put("description", rs.getString("description"));
				schedule.put("docRegStart", rs.getString("docRegStart"));
				schedule.put("docRegEnd", rs.getString("docRegEnd"));
				schedule.put("docExam", rs.getString("docExam"));
				schedule.put("docPass", rs.getString("docPass"));
				schedule.put("pracRegStart", rs.getString("pracRegStart"));
				schedule.put("pracRegEnd", rs.getString("pracRegEnd"));
				schedule.put("pracExamStart", rs.getString("pracExamStart"));
				schedule.put("pracExamEnd", rs.getString("pracExamEnd"));
				schedule.put("pracPass", rs.getString("pracPass"));
				myScheduleList.add(schedule);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return myScheduleList;
	}
	
	public ArrayList<HashMap<String, String>> notify1(Connection conn, String certiName, String dday3, String dday1){
		System.out.println("dday3 :: " + dday3);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> schedule = null;
		
		String sql = "select * from schedule where docRegEnd = ? or docRegEnd = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dday3);
			pstmt.setString(2, dday1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				schedule = new HashMap<String, String>();
				schedule.put("seriedCode", rs.getString("seriesCode"));
				schedule.put("description", rs.getString("description"));
				schedule.put("docRegStart", rs.getString("docRegStart"));
				schedule.put("docRegEnd", rs.getString("docRegEnd"));
				scheduleList.add(schedule);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return scheduleList;
	}
	
	public ArrayList<HashMap<String, String>> notify2(Connection conn, String certiName, String dday3, String dday1){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> schedule = null;
		
		String sql = "select * from schedule where docExam = ? or docExam = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dday3);
			pstmt.setString(2, dday1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				schedule = new HashMap<String, String>();
				schedule.put("seriedCode", rs.getString("seriesCode"));
				schedule.put("description", rs.getString("description"));
				schedule.put("docExam", rs.getString("docExam"));
				scheduleList.add(schedule);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return scheduleList;
	}
	
	public ArrayList<HashMap<String, String>> notify3(Connection conn, String certiName, String dday3, String dday1){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> schedule = null;
		
		String sql = "select * from schedule where pracRegEnd = ? or pracRegEnd = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dday3);
			pstmt.setString(2, dday1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				schedule = new HashMap<String, String>();
				schedule.put("seriedCode", rs.getString("seriesCode"));
				schedule.put("description", rs.getString("description"));
				schedule.put("pracRegStart", rs.getString("pracRegStart"));
				schedule.put("pracRegEnd", rs.getString("pracRegEnd"));
				scheduleList.add(schedule);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return scheduleList;
	}
	
	public ArrayList<HashMap<String, String>> notify4(Connection conn, String certiName, String dday3, String dday1){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> schedule = null;
		
		String sql = "select * from schedule where pracExamStart = ? or pracExamStart = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dday3);
			pstmt.setString(2, dday1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				schedule = new HashMap<String, String>();
				schedule.put("seriedCode", rs.getString("seriesCode"));
				schedule.put("description", rs.getString("description"));
				schedule.put("pracExamStart", rs.getString("pracExamStart"));
				schedule.put("pracExamEnd", rs.getString("pracExamEnd"));
				scheduleList.add(schedule);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return scheduleList;
	}
}
