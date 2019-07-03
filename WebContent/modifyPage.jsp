<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>JJNG MORE</title>

</head>

<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">
	<%
		String userId = (String) session.getAttribute("userId");
		String userPw = (String) session.getAttribute("userPw");
		String userName = (String) session.getAttribute("userName");
		String userPhone = (String) session.getAttribute("userPhone");
		String userCerti1 = (String) session.getAttribute("userCerti1");
		String userCerti2 = (String) session.getAttribute("userCerti2");	
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
						<section id="join">
							<div class="col-xs-8">
								<div class="form-wrap">
									<h2>내 정보 수정</h2>
									<form role="form" action="ModifyMemberInfo.do" method="post">
										<div class="form-group">
											<label>Email</label><br>
											<label>${userId}</label>
											<br>
										</div>
										<div class="form-group">
											<label>Password</label> <input
												type="password" name="userPw" id="userPw" 
												class="form-control" placeholder="Enter Your Password">
										</div>
										<div class="form-group">
											<label>Name</label> <input
												type="text" name="userName" id="userName" 
												class="form-control" placeholder="${userName}">
										</div>
										<div class="form-group">
											<label>Phone Number</label> <input
												type="text" name="userPhone" id="userPhone" 
												class="form-control" placeholder="${userPhone}">
										</div>
										<div class="form-group">
											<label>Favorite Certification1</label> <input
												type="text" name="userCerti1" id="userCerti1" 
												class="form-control" placeholder="${userCerti1}">
										</div>
										<div class="form-group">
											<label>Favorite Certification2</label> <input
												type="text" name="userCerti2" id="userCerti2" 
												class="form-control" placeholder="${userCerti2}">
										</div>
										
										
										<input type="submit" id="btn-join"
											class="btn btn-warning btn-lg" value="Modify"
											name="Modify">
									</form>
									<hr>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>