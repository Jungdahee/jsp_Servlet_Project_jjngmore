<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
	<div class="menu-list">
		<ul id="menu-content" class="menu-content collapse out">
			<li><a href="./ShowTestSchedule.do"> <i class="fas fa-calendar-alt"></i> 시험 일정
			</a></li>

			<li><a href="testInfoPage.jsp"> <i class="fas fa-info-circle"></i>
					시험/자격 정보
			</a></li>

			<li><a href="./ShowBoardList.do?boardType=N"> <i class="fas fa-bell"></i>
					공지 게시판
			</a></li>
			
			<li><a href="./ShowBoardList.do?boardType=F"> <i class="fas fa-comment-alt"></i>
					자유 게시판
			</a></li>

			<li><a href="./ShowBoardList.do?boardType=S"> <i class="fas fa-pen-nib"></i> 스터디 게시판
			</a></li>

			<li><a href="./ShowBoardList.do?boardType=D"> <i class="fas fa-share-alt"></i> 자료 게시판
			</a></li>
			
		</ul>
	</div>
</body>
</html>