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

<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">

	<%
		String userId = (String) session.getAttribute("userId");
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
				<div class="col-sm-2">
					<div class="nav-side-menu" style="margin-top: 7%;">
						<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
							data-target="#menu-content"></i>
						<%@ include file="sideNav.jsp"%>
					</div>
				</div>
				<div class="col-sm-10">
					<div class="col-md-6">
						<div class="card">
							<div class="card-body">
								<h4>My Info</h4>
								<br>
								<table>
									<tr>
										<td>EmailID</td>
										<td id="emailID">${userId}</td>
									</tr>

									<tr>
										<td>Name</td>
										<td id="memberName">${userName}</td>
									</tr>
									<tr>
										<td>Phone Number</td>
										<td id="memberPhone">${userPhone}</td>
									</tr>

									<tr>
										<td>관심 자격증</td>
										<td id="favCerti1">${userCerti1}</td>
									</tr>
									<tr>
										<td>관심 자격증</td>
										<td id="favCerti2">${userCerti2}</td>
									</tr>
								</table>
							</div>

							<input type="button" class="btn btn-warning"
								onClick="location.href='modifyPage.jsp'" value="수정하기">
						</div>

					</div>
					<div class="col-md-6">
						<div class="card">
							<div class="card-body">
								<h4>내가 작성한 게시글</h4>
								<p>
									<%
										for (int i = 0; i < articleList.size(); i++) {
									%>
									<%
										if (articleList.get(i).get("boardType").equals("F")) {
									%>
									<a
										href="ShowDetailScript.do?boardType=F&num=<%=articleList.get(i).get("num")%>">
										<%=articleList.get(i).get("title")%>
									</a><span style="text-align: right"><%=articleList.get(i).get("date")%></span><br>
									<%
										}
									%>
									<%
										if (articleList.get(i).get("boardType").equals("S")) {
									%>
									<a
										href="ShowDetailScript.do?boardType=S&num=<%=articleList.get(i).get("num")%>">
										<%=articleList.get(i).get("title")%>
									</a><span style="text-align: right"><%=articleList.get(i).get("date")%></span><br>
									<%
										}
									%>
									<%
										if (articleList.get(i).get("boardType").equals("D")) {
									%>
									<a
										href="ShowDetailScript.do?boardType=D&num=<%=articleList.get(i).get("num")%>">
										<%=articleList.get(i).get("title")%>
									</a><span style="text-align: right"><%=articleList.get(i).get("date")%></span><br>
									<%
										}
									%>
									<%
										}
									%>
								</p>

								<section id="pageList" style="margin-left: 25%; margin-top: 2%;">
									<%
										if (nowPage <= 1) {
									%>
									[이전]
									<%
										} else {
									%>
									<a href="ShowMyInfo.do?page=<%= nowPage - 1 %>">[이전]</a>
									<%
										}
									%>

									<%
										for (int i = startPage; i <= endPage; i++) {
									%>
									<%
										if (i == nowPage) {
									%>
									[<%=i%>]
									<%
										} else {
									%>
									<a href="ShowMyInfo.do?page=<%= nowPage + 1 %>&page=<%=i%>">[<%=i%>]
									</a> &nbsp;
									<%
										}
									%>
									<%
										}
									%>

									<%
										if (nowPage >= maxPage) {
									%>
									[다음]
									<%
										} else {
									%>
									<a href="ShowBoardList.do?boardType=N&page=<%=nowPage + 1%>">[다음]</a>
									<%
										}
									%>

								</section>
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

