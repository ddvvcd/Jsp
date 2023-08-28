<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String sessid = (String) session.getAttribute("sessid");
	
	//로그인을 안 했으면 로그인 화면으로 이동시킴
	if(sessid == null){
		response.sendRedirect("./loginForm.jsp");
		return; //메소드 종료
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>loginSucess</title>
	</head>
	<body>
		<h3>로그인 성공</h3>
		<p>
			<%= sessid %>님 반갑습니다.<br>
			<a href="./logout.jsp">로그아웃</a>		
		</p>
	</body>
</html>