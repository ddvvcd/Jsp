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
	String uid = request.getParameter("uid");
	
	// DB 조회
	int result = UserDAO.getInstance().selectCountUid(uid);
	
	// Json 생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	// Json 출력
	String jsonData = json.toString();
	out.print(jsonData);
	
	//브라우저 주소창에 입력 후 확인해볼 것
	//http://localhost:8080/Jboard1/user/checkUid.jsp?uid=a102 -> result : 1 (있음/중복)
	//http://localhost:8080/Jboard1/user/checkUid.jsp?uid=a101 -> result : 0 (없음/사용가능)		
%>