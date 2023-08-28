<%@page import="kr.co.jboard1.dao.UserDAO"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");
	
	// DB 조회
	int result = UserDAO.getInstance().selectCountEmail(email);
	
	// Json 생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	// Json 출력
	String jsonData = json.toString();
	out.print(jsonData);
	
	//브라우저 주소창에 입력 후 확인해볼 것
	//http://localhost:8080/Jboard1/user/checkEmail.jsp?email=green@naver.com -> result : 1 (중복)
	//http://localhost:8080/Jboard1/user/checkEmail.jsp?email=naver@naver.com -> result : 0 (사용가능)		
	
%>