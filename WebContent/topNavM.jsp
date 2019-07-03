<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<% String  id= (String) session.getAttribute("emailId"); %>

<script>
	function logout() {
		alert("로그아웃 합니다!");
		$.ajax({
			url : "./LogoutMember.do",
			type : "post",
			datatype : "string",
			success : function(data) {
				if (data == "ok") {
					alert("로그아웃 되셨습니다!");
					location.href = "loginPage.jsp";
				}
			}
		});
	}
</script>
<body>
	<br>
	<div class="navbar navbar-fixed-top navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle" data-toggle=".navbar-collapse"
					data-target=".navbar-collapse">
					<span class="icon icon-bar"></span> <span class="icon icon-bar"></span>
					<span class="icon icon-bar"></span>
				</button>
				<a href="index.jsp" class="navbar-brand"> <img
					src="images/logo11.png" width="auto" height="60" /></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a onClick="logout()" class="smoothScroll">LOGOUT 
					<i class="fas fa-sign-out-alt"></i></a>
					</li>
					<li><a class="nav-link" href="./ShowMyPage.do">MY PAGE 
					<i class="fas fa-user-alt"></i></a></li>
					<li><a class="nav-link" href="./ShowMyMessage.do">
                        MESSAGE
            	        <span class="badge badge-danger"></span>
        			</a>
      				</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
