<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>JJNG MORE</title>

</head>

<script>

	$(document).ready(function(e) {
		$('.search-panel .dropdown-menu').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#", "");
			var concept = $(this).text();
			$('.search-panel span#search_concept').text(concept);
			$('.input-group #search_param').val(param);
		});

			$.ajax({
				url : "./SearchCertiInfo.do",
				type : "post",
				datatype : "string",
				success : function(data) {
					if (data.length > 0) {
						var i1 = data.indexOf("{");
						var i2 = data.lastIndexOf("}");
						var data1 = data.substring(0, i1 - 5);
						var data2 = data.substring(i2 + 1);

						$("#msg1").html("<출제경향>");
				  		$("#info1").html(data1);
						$("#msg2").html("<취득방법>");
						$("#info2").html(data2);
					}
					else{
						alert('"' + searchParam + '"의 결과가 없습니다');
						$("#resultStr").html('"검색어"를 다시 입력해주세요.');
					}
				}
			});

		$("#searchBtn").click(function() {
			var searchParam = $("#searchParam").val();

			if (searchParam == "") {
				alert("검색어를 입력해주세요!");
			} 
			else {
			$.ajax({
				url : "./SearchCertiInfo.do",
				type : "post",
				data : {"data" : searchParam},
				datatype : "string",
				success : function(data) {
					if (data.length > 0) {
						var i1 = data.indexOf("{");
						var i2 = data.lastIndexOf("}");
						var data1 = data.substring(0, i1 - 5);
						var data2 = data.substring(i2 + 1);

						$("#resultStr").html('"' + searchParam + '"의 결과입니다');
						$("#msg1").html("<출제경향>");
				  		$("#info1").html(data1);
						$("#msg2").html("<취득방법>");
						$("#info2").html(data2);
					}
					else{
						alert('"' + searchParam + '"의 결과가 없습니다');
						$("#resultStr").html('"검색어"를 다시 입력해주세요.');
					}
				}
			});

		}
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
						<div class="container">
							<div class="searchBtn">
								<div class="input-group">
									<div class="input-group-btn search-panel">
										<button type="button" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown">
											<span id="search_concept">검색 조건</span> <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a href="#name">자격증 이름</a></li>
										</ul>
									</div>
									<input type="hidden" name="search_param" value="all"
										id="search_param"> <input type="text" id="searchParam"
										class="form-control" name="x" placeholder="Search term...">
									<span class="input-group-btn">
										<button id="searchBtn" class="btn btn-default" type="button">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</span>
								</div>
							</div>

							<div id="resultStr">"검색어"를 입력해주세요</div>

							<div class="searchRes">
								<div class="card-body">
									<div>
										<h4 id="msg1"></h4>
									<p id="info1"></p>
									</div>
									
									<div>
									<h4 id="msg2"></h4>
									<p class="card-text" id="info2"></p>
									</div>
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