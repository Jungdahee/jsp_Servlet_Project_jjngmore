<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>

<title>JJNG MORE</title>

</head>
<% String userId = (String) session.getAttribute("userId"); %>
<% String joinResult = (String) request.getAttribute("joinResult"); %>

<script>
	$(document).ready(function() {
		var joinResult = "<%= joinResult %>";
		if(joinResult == "no"){
			alert("회원가입에 실패하셨습니다. 다시 시도해주세요!");
		}
		
		$(document).on('click', "#checkId", function() {
			alert("진입");
			var checkId = $("#userId").val();
			alert("중복 체크할 ID값은 :: " + checkId);

			if (checkId == "")
				alert("아이디를 입력해주세요.");
			else {
				$.ajax({
					url : "./CheckValidateId.do",
					type : "post",
					data : {
						"data" : checkId
					},
					datatype : "string",
					success : function(data) {
						if (data == "ok") {
							alert("사용할 수 있는 ID입니다!");
							$("#userPw").attr("disabled", false);
							$("#userName").attr("disabled", false);
							$("#userPhone").attr("disabled", false);
							$("#userCerti1").attr("disabled", false);
							$("#userCerti2").attr("disabled", false);
						} else if(data == "no"){
							alert("사용할 수 없는 ID입니다!");
							$("#userId").val("");
							$("#userPw").attr("disabled", true);
							$("#userName").attr("disabled", true);
							$("#userPhone").attr("disabled", true);
							$("#userCerti1").attr("disabled", true);
							$("#userCerti2").attr("disabled", true);
						}
					}
				});
			}
		})
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
						<section id="join">
							<div class="col-xs-8">
								<div class="form-wrap">
									<h2>Join</h2>
									<form role="form" action="JoinMember.do" method="post">
										<div class="form-group">
											<label>Email</label> <input
												type="email" name="userId" id="userId"
												class="form-control" placeholder="Enter Your EmailID">
												
											<br>
											<input type="button" class="btn btn-warning" id="checkId" value="ID 중복 체크">
										</div>
										<div class="form-group">
											<label>Password</label> <input
												type="password" name="userPw" id="userPw" disabled
												class="form-control" placeholder="Enter Your Password">
										</div>
										<div class="form-group">
											<label>Name</label> <input
												type="text" name="userName" id="userName" disabled
												class="form-control" placeholder="Enter Your Name">
										</div>
										<div class="form-group">
											<label>Phone Number</label> <input
												type="text" name="userPhone" id="userPhone" disabled
												class="form-control" placeholder="Enter Your Phone Number">
										</div>
										<div class="form-group">
											<label>Favorite Certification1</label> <input
												type="text" name="userCerti1" id="userCerti1" disabled
												class="form-control" placeholder="Enter Your Favorite Certification1">
										</div>
										<input type="submit" id="btn-join"
											class="btn btn-warning btn-lg" value="Join"
											name="Join">
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