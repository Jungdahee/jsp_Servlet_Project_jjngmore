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
	HashMap<String, Object> article = (HashMap<String, Object>) request.getAttribute("article");
	String boardType = (String) article.get("boardType");
	String boardUserId = (String) article.get("userId");
	
	System.out.println("fileName ::: " + request.getParameter("fileName1"));
	System.out.println("userId ::: " + article.get("userId"));
	System.out.println("boardType아 나와라 ::: " + article.get("boardType"));
%>

<!-- <script>
$(document).ready(function() {
	
	$(document).on('click','#insertBtn',function(){
		var content = $("#reviewContents").val();
		var boardType = $("#boardType").val();
		var userId = $("#userId").val();
		alert("params :: " + content + " :: " + boardType + " :: " + userId);
		
		$.ajax({
			url : "./AddBoardComment.do",
			type : "post",
			data : {"data" : data},
			dataType: "string",
			success : function (data){
			}
			
		});
	});
});

</script> -->

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
									<label for="userId">ID</label>
									<p><%= article.get("userId") %></p>
								
									<label for="title">TITLE</label>
									<p><%= article.get("title") %></p>
								
									<label for="readCount">READ COUNT</label>
									<p><%= article.get("readCount") %></p>
								
								<% if(boardType.equals("S")) {%>
									<label for="pNum">PEOPLE NUMBER</label>
									<p><%= article.get("pNum") %></p>
								<% } %>

									<label for="name">TITLE</label>
									<p><%= article.get("content") %></p>
								
								<% if(boardType.equals("F") || boardType.equals("D")) {%>
									<% if(article.get("file") != null) {%>
									<label for="file">FILE</label>
									<a href="./FileDown.do?file=<%= article.get("file") %>"><%= article.get("file") %></a>
									<% } %>
								<% } %>
								
								<div class="center-block" style='width: 300px; padding-top: 2%;'>
									<input class="btn yBtn" type="button" value="목록" onclick="location.href='./ShowBoardList.do?boardType=<%= boardType %>'">
									<input class="btn yBtn" type="button" value="뒤로" onclick="javascript:history.go(-1)">
									<% if(boardUserId.equals(userId)) {%>
										<input class="btn yBtn" type="button" value="수정" onclick="location.href='./ShowModifyScript.do?boardType=<%= article.get("boardType") %>&num=<%= article.get("num") %>'">
										<input class="btn yBtn" type="button" value="삭제" onclick="location.href='./DeleteBoardContent.do?boardType=<%= article.get("boardType") %>&num=<%= article.get("num") %>'">
									<% } %>
								</div>
								
								<%-- <c:if test="${userId != null}">
								<div>
									<form name='reviewInsert' id='reviewInsert'>
										<input type="text" maxlength="300" placeholder="insert contents" id="reviewContents">
										<input value="${boardType}" id="boardType" name="boardType" type="hidden">
										<input value="<%= userId %>" id="userId" name = "userId" type="hidden">
										<input value='등록' type="button" id='insertBtn' name='insertBtn'>
									</form>
								</div>
								</c:if>
								<div id="reviewList">
									
								</div> --%>

								
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>