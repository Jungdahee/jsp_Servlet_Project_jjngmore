<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>JJNG MORE</title>
</head>

<%
	ArrayList<HashMap<String, Object>> articleList = (ArrayList<HashMap<String, Object>>) request.getAttribute("articleList");
	HashMap<String, Integer> pageInfo = (HashMap<String, Integer>) request.getAttribute("pageInfo");
	String regResult = (String) request.getAttribute("regResult");
	System.out.println("articleList ::: " + articleList.size());
	int listCount = pageInfo.get("listCount");
	int nowPage = pageInfo.get("page");
	int maxPage = pageInfo.get("maxPage");
	int startPage = pageInfo.get("startPage");
	int endPage = pageInfo.get("endPage");
%>

<script>
	$(document).ready(function(){
		var regResult = "<%= regResult %>";
		if(regResult.equals("ok")){
			alert("등록되었습니다!");
		}	
		else{
			alert("등록에 실패했습니다!다시 시도해주세요!");
		}
		
	})
</script>

<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">
	<% String userId = (String) session.getAttribute("userId");
	   String isAdmin = (String) session.getAttribute("isAdmin");
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
				<div class="col-sm-2">
					<div class="nav-side-menu" style="margin-top: 7%;">
						<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
							data-target="#menu-content"></i>
						<%@ include file="sideNav.jsp"%>
					</div>
				</div>
				<div class="col-sm-10">
					<div class="table">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th><h4>No</h4></th>
									<th><h4>Title</h4></th>
									<th><h4>Writer</h4></th>
									<th><h4>Date</h4></th>
								</tr>

							</thead>

							<tbody>
								<% for(int i = 0; i < articleList.size(); i++){ %>
									<tr>
										<td><%= articleList.get(i).get("num") %></td>
										<td><a href="ShowDetailScript.do?boardType=S&num=<%= articleList.get(i).get("num") %>">
											<%= articleList.get(i).get("title") %>
										</a></td>
										<td><%= articleList.get(i).get("userId") %></td>
										<td><%= articleList.get(i).get("date") %></td>
									</tr>
								<% } %>
							</tbody>

						</table>
						
						<% if(isAdmin == null){ %>
						<% } else if(isAdmin.equals("0") || isAdmin.equals("1")){ %>
							<button type="submit" class="btn btn-warning" onClick="location.href='makeStudyScript.jsp'">글쓰기</button>
						<% } %>

						<section id="pageList" style="margin-left: 45%; margin-top: 2%;">
						<% if(nowPage <= 1){ %>
							[이전]
						<% } else{ %>
							<a href="ShowBoardList.do?boardType=S&page=<%= nowPage - 1 %>">[이전]</a>
						<% } %>
						
						<% for(int i = startPage; i <= endPage; i++){ %>
							<% if(i == nowPage) { %>
								[<%= i %>]
							<% } else { %>
								<a href="ShowBoardList.do?boardType=S&page=<%= i %>">[<%= i %>]</a> &nbsp;
							<% } %>
						<% } %>
						
						<% if(nowPage >= maxPage) { %>
							[다음]
						<% } else{ %>
							<a href="ShowBoardList.do?boardType=S&page=<%= nowPage + 1 %>">[다음]</a>
						<% } %>
						
						</section>
					</div>
				</div>

			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>