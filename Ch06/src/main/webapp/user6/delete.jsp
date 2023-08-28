<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="vo.User2VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.cj.jdbc.Driver"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩 설정
	request.setCharacterEncoding("UTF-8");
	
	//전송 데이터 수신
	String uid  = request.getParameter("uid");
	
	//데이터 베이스 처리
	User2VO vo = new User2VO();
	
	try{
		//JNDI 서비스 객체 생성 (Ch06의 Servers -> context.xml을 참조하는 API)
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env"); //JNDI 기본 환경이름
		
		//커넥션 풀에서 커넥션 가져오기
		DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		
	 	PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user6` WHERE `uid`=?");
	 	psmt.setString(1, uid);
	 	psmt.executeUpdate();
	
	 	psmt.close();
	 	conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	response.sendRedirect("./list.jsp");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user::delete</title>
	</head>
	<body>
		<h3>User2 삭제</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user6/list.jsp">User2 목록</a>
		
		<form action="/Ch06/user6/modifyProc.jsp" method="post">	
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