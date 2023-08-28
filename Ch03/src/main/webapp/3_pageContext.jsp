<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>3_pageContext</title>
	<!-- 
		날짜 : 2023/07/25
		이름 : 박성용
		내용 : JSP pageContext 내장객체 실습하기
	 -->
	</head>
	<body>
		<h3>3.pageContext 내장객체</h3>
		
		<h4>include</h4>
		<%
			pageContext.include("./inc/_header.html");
			pageContext.include("./inc/_footer.html");
		
		%>
		
		<h4>forward</h4>
		<a href="./proc/forward1.jsp">포워드 페이지 요청1</a>
		<a href="./proc/forward2.jsp">포워드 페이지 요청2</a>
	</body>
</html>