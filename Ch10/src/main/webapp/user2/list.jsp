<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- ctrl+space -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user2::list</title>
	</head>
	<body>
		<h3>User2 목록</h3>
		<a href="/Ch10">메인</a>
		<a href="/Ch10/user2/register.do">등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>나이</th>
				<th>관리</th>
			</tr>
			<c:forEach var="user22" items="${requestScope.user2}">
			<tr>
			    <td>${user22.getUid()}</td>
			    <td>${user22.getName()}</td>
			    <td>${user22.getHp()}</td>
			    <td>${user22.getAge()}</td>
				<td>
					<a href="/Ch10/user2/modify.do?uid=${user22.getUid()}">수정</a>
					<a href="/Ch10/user2/delete.do?uid=${user22.getUid()}">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>