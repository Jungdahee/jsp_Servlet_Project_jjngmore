<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>

<title>JJNG MORE</title>

</head>
<% String userId = (String) session.getAttribute("userId"); %>
<% String joinResult = (String) request.getAttribute("joinResult"); %>
<% String loginResult = (String) request.getAttribute("loginResult"); %> 
<script>
	$(document).ready(function(){
		var joinResult = "<%= joinResult %>";
		if(joinResult == "ok"){
			alert("가입 성공하셨습니다! 로그인해주세요!");
		}	
		
		var loginResult = "<%= loginResult %>";
		if(loginResult == "no"){
			alert("아이디와 비밀번호를 확인해주세요!");
		}
		
	})
</script>

<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">
	<%@ include file="topNavC.jsp"%>
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
						<section id="login">
							<div class="col-xs-8">
								<div class="form-wrap">
									<h2>Login</h2>
									<form role="form" action="LoginMember.do" method="post">
										<div class="form-group">
											<label>Email</label> <input
												type="email" name="userId" id="userId"
												class="form-control" placeholder="Enter Your EmailID">
										</div>
										<div class="form-group">
											<label>Password</label> <input
												type="password" name="userPw" id="userPw"
												class="form-control" placeholder="Enter Your Password">
										</div>
										<input type="submit" id="btn-login"
											class="btn btn-warning btn-lg" value="Login"
											name="Login">
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