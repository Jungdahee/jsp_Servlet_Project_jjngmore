<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>JJNG MORE</title>
</head>
<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">
	<% String emailId = (String) session.getAttribute("emailId"); %>
	<%
		if (emailId == null) {
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
					<div class="panel panel-default panel-table">
						<div class="panel-heading">
							<div class="row">
								<div class="col col-xs-6">
									<h3 class="panel-title">Manage Member</h3>
								</div>
								<div class="col col-xs-6 text-right">
									<button type="button" class="btn yellowBtn">Create
										New</button>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-striped table-bordered table-list">
								<thead>
									<tr>
										<th><em class="fa fa-cog"></em></th>
										<th class="hidden-xs">Num</th>
										<th>Name</th>
										<th>EmailID</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td align="center"> <a class="btn btn-danger"><em
												class="fa fa-trash"></em></a></td>
										<td class="hidden-xs">1</td>
										<td>정다희</td>
										<td>jungdahee010@naver.com</td>
									</tr>
								</tbody>
							</table>

						</div>
						<div class="panel-footer">
							<div class="row">
								<div class="col col-xs-8">
									<ul class="pagination hidden-xs pull-right center">
										<li><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
									</ul>
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

