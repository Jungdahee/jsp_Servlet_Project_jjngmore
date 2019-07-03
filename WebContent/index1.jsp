<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>JJNG MORE</title>

</head>
<% String userName = (String) session.getAttribute("userName"); %>
<% String loginResult = (String) request.getAttribute("loginResult"); %> 
<% String msg = (String) request.getAttribute("msg"); %>
			
<script>
$(document).ready(function(){
	var loginResult = "<%= loginResult %>";
	var userName = "<%= userName %>";
	if(loginResult == "ok"){
		alert(userName + "님 환영합니다!");
	
	}
})
	
</script>

<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">

	<% String userId = (String) session.getAttribute("userId"); %>
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
	
	<%
	ArrayList<HashMap<String, String>> registerList = (ArrayList<HashMap<String, String>>)request.getAttribute("registerList");
	ArrayList<HashMap<String, Object>> scriptList = (ArrayList<HashMap<String, Object>>) request.getAttribute("scriptList");
	ArrayList<HashMap<String, Object>> notifyList = (ArrayList<HashMap<String, Object>>) request.getAttribute("notifyList");
	ArrayList<HashMap<String, Object>> studyList = (ArrayList<HashMap<String, Object>>) request.getAttribute("studyList");
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
						<div class="col-md-6 col-sm-6">
							<div class="media">
								<div class="media-object media-left wow fadeIn"
									data-wow-delay="0.1s">
									<i class="fa fa-laptop"></i>
								</div>
								<div class="media-body wow fadeIn">
									<h3 class="media-heading">원서 접수 중인 시험</h3>
									<p id="p1"><% for(int i = 0; i < registerList.size(); i++){ %>
										<%= registerList.get(i).get("description") %>
										<%= registerList.get(i).get("docRegStart") %> ~
										<%= registerList.get(i).get("docRegEnd") %>
								<% } %></p>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6">
							<div class="media">
								<div class="media-object media-left wow fadeIn"
									data-wow-delay="0.3s">
									<i class="fa fa-star"></i>
								</div>
								<div class="media-body wow fadeIn">
									<h3 class="media-heading">실시간 인기글</h3>
									<p><% for(int i = 0; i < scriptList.size(); i++){ %>
										<% if(scriptList.get(i).get("boardType").equals("F")){ %>
										<a href="ShowDetailScript.do?boardType=F&num=<%= scriptList.get(i).get("num") %>">
											<%= scriptList.get(i).get("title") %>
										</a><span style="text-align: right"><%= scriptList.get(i).get("date") %></span><br>
										<% } %>
										<% if(scriptList.get(i).get("boardType").equals("S")){ %>
										<a href="ShowDetailScript.do?boardType=S&num=<%= scriptList.get(i).get("num") %>">
											<%= scriptList.get(i).get("title") %>
										</a><span style="text-align: right"><%= scriptList.get(i).get("date") %></span><br>
										<% } %>
										<% if(scriptList.get(i).get("boardType").equals("D")){ %>
										<a href="ShowDetailScript.do?boardType=D&num=<%= scriptList.get(i).get("num") %>">
											<%= scriptList.get(i).get("title") %>
										</a><span style="text-align: right"><%= scriptList.get(i).get("date") %></span><br>
										<% } %>
								<% } %></p>
								</div>
							</div>
						</div>

						<div class="col-md-6 col-sm-6">
							<div class="media">
								<div class="media-object media-left wow fadeIn"
									data-wow-delay="0.6s">
									<i class="fa fa-paper-plane"></i>
								</div>
								<div class="media-body wow fadeIn" data-wow-delay="0.3s">
									<h3 class="media-heading">공지사항</h3>
									<p><% for(int i = 0; i < notifyList.size(); i++){ %>
										<a href="ShowDetailScript.do?boardType=N&num=<%= notifyList.get(i).get("num") %>">
											<%= notifyList.get(i).get("title") %>
										</a>
								<% } %></p>
								</div>
							</div>
						</div>

						<div class="col-md-6 col-sm-6">
							<div class="media">
								<div class="media-object media-left wow fadeIn"
									data-wow-delay="0.9s">
									<i class="fa fa-pencil-alt"></i>
								</div>
								<div class="media-body wow fadeIn" data-wow-delay="0.6s">
									<h3 class="media-heading">실시간 스터디 모집글</h3>
									<p><% for(int i = 0; i < studyList.size(); i++){ %>
										<a href="ShowDetailScript.do?boardType=S&num=<%= studyList.get(i).get("num") %>">
											<%= studyList.get(i).get("title") %>
										</a> <span style="text-align: right"><%= studyList.get(i).get("date") %></span><br> 
										
								<% } %></p>
								</div>
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