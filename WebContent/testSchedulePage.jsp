<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>

<title>JJNG MORE</title>

</head>

<%
	ArrayList<HashMap<String, String>> scheduleList = (ArrayList<HashMap<String, String>>) request.getAttribute("scheduleList");
	ArrayList<HashMap<String, String>> myScheduleList = (ArrayList<HashMap<String, String>>) request.getAttribute("myScheduleList");
	String userCerti1 = (String) session.getAttribute("userCerti1");
%>

<script>
	$(function() {
		
		$('#calendar').fullCalendar({
			
			events : [ 
				<c:forEach items="${scheduleList}" var="it">
				{
					title : '<c:out value="${it.description}"/>' + '필기 원서 접수 일자',
					start : '<c:out value="${it.docRegStart}"/>',
					end : '<c:out value="${it.docRegEnd}"/>',
					color : 'lightslategrey'
				},
				{
					title : '<c:out value="${it.description}"/>' + '필기 시험 일자',
					start : '<c:out value="${it.docExam}"/>',
					end : '<c:out value="${it.docExam}"/>',
					color : 'lightsalmon'
				},
				{
					title : '<c:out value="${it.description}"/>' + '원서 합격 발표 일자',
					start : '<c:out value="${it.docPass}"/>',
					end : '<c:out value="${it.docPass}"/>',
					color : 'indianred'
				},
				{
					title : '<c:out value="${it.description}"/>' + '실기 원수 접수 일자',
					start : '<c:out value="${it.pracRegStart}"/>',
					end : '<c:out value="${it.pracRegEnd}"/>',
					color : 'grey'
				},
				{
					title : '<c:out value="${it.description}"/>' + '실기 시험 일자',
					start : '<c:out value="${it.pracExamStart}"/>',
					end : '<c:out value="${it.pracExamEnd}"/>',
					color : 'rosybrown'
				},
				{
					title : '<c:out value="${it.description}"/>' + '최종 합격 발표 일자',
					start : '<c:out value="${it.pracPass}"/>',
					end : '<c:out value="${it.pracPass}"/>',
					color : 'darkred'
				},
				</c:forEach> 
			],
			
		})  

	});	
	
</script>

<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">
	<%
		String userId = (String) session.getAttribute("userId");
	%>
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
						<div id="calendar"></div>
						
						<% if(myScheduleList != null) { %>
						<div class="card">
							<div class="card-body">
								<h4>${userCerti1}</h4>
								<div class="card-text">
									<% for(int i = 0; i < myScheduleList.size(); i++) {%>
									<h4><%= myScheduleList.get(i).get("description") %></h4>
									<p>필기 원서 접수 기간 <%= myScheduleList.get(i).get("docRegStart") %> ~
									<%= myScheduleList.get(i).get("docRegEnd") %></p>
									<p>필기 시험 <%= myScheduleList.get(i).get("docExam") %></p>
									<p>필기 합격자 발표 <%= myScheduleList.get(i).get("docPass") %>
									<p>실기 원서 접수 기간 <%= myScheduleList.get(i).get("pracRegStart") %> ~
									<%= myScheduleList.get(i).get("pracRegEnd") %></p>
									<p>실시 시험 기간 <%= myScheduleList.get(i).get("pracExamStart") %> ~
									<%= myScheduleList.get(i).get("pracExamEnd") %></p>
									<p>최종 합격자 발표 <%= myScheduleList.get(i).get("pracPass") %></p>
									<% } %><br>
								</div>
							</div>
						</div>
						<% } else if(myScheduleList == null){ %>
						<div class="card">
							<div class="card-body">
							<div class="card-text">
							<h4>관심자격증이 없습니다.</h4>
							<p>이용하시려면 로그인을 해주세요.</p>
							</div>
							</div>
						</div>
						<% } %>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>