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
	String nick = request.getParameter("nick");
	
	// DB 조회
	int result = UserDAO.getInstance().selectCountNick(nick);
	
	// Json 생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	// Json 출력
	String jsonData = json.toString();
	out.print(jsonData);
	
	//브라우저 주소창에 입력 후 확인해볼 것
	//http://localhost:8080/Jboard1/user/checkNick.jsp?nick=김김김 -> result : 1 (중복)
	//http://localhost:8080/Jboard1/user/checkNIck.jsp?nick=님님님 -> result : 0 (사용가능)		
	
%>