<%@page import="vo.User1VO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 인코딩 설정
	request.setCharacterEncoding("UTF-8");
	
	// 전송 데이터 수신
	String uid  = request.getParameter("uid");
	
	//데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	User1VO vo = new User1VO();
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
	 	PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user1` WHERE `uid`=?");
	 	psmt.setString(1, uid);
	 	psmt.executeUpdate();
	
	 	psmt.close();
	 	conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	response.sendRedirect("/Ch06/user1/list.jsp");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user::delete</title>
	</head>
	<body>
		<h3>User1 삭제</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user1/list.jsp">User1 목록</a>
		
		<form action="/Ch06/user1/modifyProc.jsp" method="post">	
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly value="<%= vo.getUid() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%= vo.getName() %>"></td>
				</tr>		
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="<%= vo.getHp() %>"></td>
				</tr>		
				<tr>
					<td>나이</td>
					<td><input type="number" name="age" value="<%= vo.getAge() %>"></td>
				</tr>		
				<tr>
					<td colspan="2" align="right"><input type="submit" value="삭제"></td>
				</tr>				
			</table>
		</form>
	</body>
</html>