<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user::modify</title>
	</head>
	<body>
		<h3>User4 수정</h3>
		<a href="/Ch10">메인</a>
		<a href="/Ch10/user4/list.do">User4 목록</a>
		
		<form action="/Ch10/user4/modify.do" method="post">	
			<table border="1">
				<tr>
					<td>시퀀스</td> <!-- 표현언어에서는 내장객체 생략(requestScope), 직접 참조 가능 -->
					<td><input type="number" name="seq" readonly value="${users.seq}"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${users.name}"></td>
				</tr>		
				<tr>
					<td>성볋</td>
					<td><input type="number" name="gender" value="${users.gender}"></td>
				</tr>		
				<tr>
					<td>나이</td>
					<td><input type="number" name="age" value="${users.age}"></td>
				</tr>		
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="${users.addr}"></td>
				</tr>	
				<tr>
					<td colspan="2" align="right"><input type="submit" value="수정"></td>
				</tr>				
			</table>
		</form>
	</body>
</html>