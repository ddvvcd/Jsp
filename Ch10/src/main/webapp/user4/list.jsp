<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- ctrl+space -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user4::list</title>
	</head>
	<body>
		<h3>User4 목록</h3>
		<a href="/Ch10">메인</a>
		<a href="/Ch10/user4/register.do">등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>나이</th>
				<th>관리</th>
			</tr>
			<c:forEach var="users" items="${requestScope.user4}">
			<tr>
			    <td>${users.getUid()}</td>
			    <td>${users.getName()}</td>
			    <td>${users.getHp()}</td>
			    <td>${users.getAge()}</td>
				<td>
					<a href="/Ch10/user4/modify.do?uid=${users.getUid()}">수정</a>
					<a href="/Ch10/user4/delete.do?uid=${users.getUid()}">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>