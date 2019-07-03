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
	HashMap<String, Object> article = (HashMap<String, Object>) request.getAttribute("article");
	String boardType = (String) article.get("boardType");
	String boardUserId = (String) article.get("userId");
	System.out.println("userId ::: " + article.get("userId"));
%>

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
					<div class="row">
						<div class="form1">
							<h3>글 수정하기</h3>
							<form method="post" action="ModifyBoardContent.do" enctype="Multipart/form-data">
								<input type="hidden" name="boardType" value="<%= article.get("boardType") %>">
								<input type="hidden" name="num" value="<%= article.get("num") %>">
								
								<div class="form-group">
									<label for="name">ID</label>
									<p id="userId">${userId}</p>
								</div>
								

								<div class="form-group">
									<label for="subject">TITLE</label> 
									<input type="text"
										class="form-control" name="title" id="title"
										value="<%= article.get("title") %>">
								</div>
								
								<% if(boardType.equals("S")){ %>
								<div class="form-group">
									<label for="subject">인원 수</label> <input type="text"
										class="form-control" name="pNum" id="pNum"
										placeholder="Enter title">
								</div>
								<% } %>

								<div class="form-group">
									<label for="content">CONTENT</label>
									<textarea class="form-control" rows="10" name="content"
										id="content"><%= article.get("content") %></textarea>
								</div>

								<% if(boardType.equals("F") || boardType.equals("D")){ %>
								<div class="form-group">
									<label for="File">File input</label> <input type="file"
										name="fileName1" id="fileName1">
								</div>
								<% } %>
								
								<div class="center-block" style='width: 200px'>
									<input class="btn yBtn" type="submit" value="저장">
									<input class="btn yBtn" type="button" value="뒤로" onclick="history.back(1)">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>

ml>