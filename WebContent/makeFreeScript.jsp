<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>JJNG MORE</title>
</head>
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
							<h3>글 작성하기</h3>
							<form method="post" action="AddBoardContent.do" enctype="Multipart/form-data">
								<input type="hidden" name="type" value="F">
								
								<div class="form-group">
									<label for="name">ID</label>
									<p id="userId">${userId}</p>
								</div>
								

								<div class="form-group">
									<label for="subject">TITLE</label> <input type="text"
										class="form-control" name="title" id="title"
										placeholder="Enter title">
								</div>

								<div class="form-group">
									<label for="content">CONTENT</label>
									<textarea class="form-control" rows="10" name="content"
										id="content"></textarea>
								</div>

								<div class="form-group">
									<label for="File">File input</label> <input type="file"
										name="fileName1" id="fileName1">
								</div>

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

