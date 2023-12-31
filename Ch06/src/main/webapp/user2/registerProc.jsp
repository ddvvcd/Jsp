<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String age = request.getParameter("age");
	
	//데이터 베이스 처리 (DBCP 방식으로 처리)
	//String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	//String user = "root";
	//String pass = "1234";
	
	
	//데이터베이스 처리
	try{
	//JNDI 서비스 객체 생성 (Ch06의 Servers -> context.xml을 참조하는 API)
	Context initCtx = new InitialContext();
	Context ctx = (Context) initCtx.lookup("java:comp/env"); //JNDI 기본 환경이름
	
	//커넥션 풀에서 커넥션 가져오기
	DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
	Connection conn = ds.getConnection();
	
	PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user2` VALUES(?,?,?,?)");
	psmt.setString(1, uid);
	psmt.setString(2, name);
	psmt.setString(3, hp);
	psmt.setString(4, age);
	
	psmt.executeUpdate();
	
	psmt.close();
	conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	response.sendRedirect("/Ch06/user2/list.jsp");

%>