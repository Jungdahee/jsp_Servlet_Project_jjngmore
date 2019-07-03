<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>JJNG MORE</title>
</head>

<%
	ArrayList<HashMap<String, Object>> scheduleList1 = (ArrayList<HashMap<String, Object>>) request.getAttribute("scheduleList1");
	ArrayList<HashMap<String, Object>> scheduleList2 = (ArrayList<HashMap<String, Object>>) request.getAttribute("scheduleList2");
	ArrayList<HashMap<String, Object>> scheduleList3 = (ArrayList<HashMap<String, Object>>) request.getAttribute("scheduleList3");
	ArrayList<HashMap<String, Object>> scheduleList4 = (ArrayList<HashMap<String, Object>>) request.getAttribute("scheduleList4");
%>

<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">

	<% String userId = (String) session.getAttribute("userId"); %>
	<% String userCerti1 = (String) session.getAttribute("userCerti1"); %>
	<%
		if (userId == null) {
	%>
	<%@ include file="topNavC.jsp"%>
	<%
		} else {
	%>
	<%@ include file="topNavM.jsp"%>
	<%
		}
	%>
	
	<div id="service">
		<div class="container">
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-2">
						<div class="nav-side-menu" style="margin-top: 7%;">
							<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
								data-target="#menu-content"></i>
							<%@ include file="sideNav.jsp"%>
						</div>
					</div>
					<div class="col-sm-10">
						<h3>Message</h3>
						<div class="card">
							<div class="card-body">
								<h3><%= userCerti1 %></h3>
								<h4>필기 원서 접수 안내</h4>
								<p class="card-text">
								<% if(scheduleList1.size() == 0) { %>
								안내 메세지가 없습니다.
								<% } else {
									for(int i = 0; i < scheduleList1.size(); i++){ %>
										<%= scheduleList1.get(i).get("description") %> 원서 접수 마감 3일 전입니다.
										<%= scheduleList1.get(i).get("docRegStart") %> ~
										<%= scheduleList1.get(i).get("docRegEnd") %>
								<% }} %></p>
							</div>
							
							<div class="card-body">
								<h4>필기 시험 안내</h4>
								<p class="card-text">
								<% if(scheduleList2.size() == 0) { %>
								안내 메세지가 없습니다.
								<% } else {
									for(int i = 0; i < scheduleList2.size(); i++){ %>
										<%= scheduleList2.get(i).get("description") %> 필기 시험 3일 전입니다.
										<%= scheduleList2.get(i).get("docExam") %>
								<% }} %></p>
							</div>
							
							<div class="card-body">
								<h4>실기 원서 접수 안내</h4>
								<p class="card-text">
								<% if(scheduleList3.size() == 0) { %>
								안내 메세지가 없습니다.
								<% } else {
									for(int i = 0; i < scheduleList3.size(); i++){ %>
										<%= scheduleList3.get(i).get("description") %> 원서 접수 마감 3일 전입니다.
										<%= scheduleList3.get(i).get("pracRegStart") %> ~
										<%= scheduleList3.get(i).get("pracRegEnd") %>
								<% }} %></p>
							</div>
							
							<div class="card-body">
								<h4>실기 시험 안내</h4>
								<p class="card-text">
								<% if(scheduleList3.size() == 0) { %>
								안내 메세지가 없습니다.
								<% } else {
									for(int i = 0; i < scheduleList3.size(); i++){ %>
										<%= scheduleList3.get(i).get("description") %> 실기 시험 3일 전입니다.
										<%= scheduleList3.get(i).get("pracExamStart") %> ~
										<%= scheduleList3.get(i).get("pracExamEnd") %>
								<% }} %></p>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>