<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//session은 클라이언트 1명의 객체
	//수많은 클라이언트의 객체
	//세션 해제
	session.invalidate();

	//쿠키 해제
	Cookie cookie = new Cookie("cid", null);
	cookie.setMaxAge(0);//쿠키 유효기간: 0 -> 유통기한이 끝남
	response.addCookie(cookie); //cid 쿠키를 싣어서 보냄
	
	//리다이렉트
	response.sendRedirect("./loginForm.jsp");
%>
