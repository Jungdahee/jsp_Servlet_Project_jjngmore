<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<body>
	<% if(nowPage <= 1){ %>
	[이전]
	<% } else{ %>
	<a href="ShowBoardList.do?boardType=D&page=<%= nowPage - 1 %>">[이전]</a>
	<% } %>

	<% for(int i = startPage; i <= endPage; i++){ %>
	<% if(i == nowPage) { %>
	[<%= i %>]
	<% } else { %>
	<a href="ShowBoardList.do?boardType=D&page=<%= i %>">[<%= i %>]
	</a> &nbsp;
	<% } %>
	<% } %>

	<% if(nowPage >= maxPage) { %>
	[다음]
	<% } else{ %>
	<a href="ShowBoardList.do?boardType=D&page=<%= nowPage + 1 %>">[다음]</a>
	<% } %>
</body>
</html>