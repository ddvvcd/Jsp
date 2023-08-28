<%@page import="kr.co.jboard1.dto.UserDTO"%>
<%@page import="kr.co.jboard1.dao.UserDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//인코딩
	request.setCharacterEncoding("UTF-8");

	//register.jsp에서 전송되는 데이터 넘겨받기?
	String uid 	 = request.getParameter("uid");
	String pass1 = request.getParameter("pass1");
	String pass2 = request.getParameter("pass2");
	String name  = request.getParameter("name");
	String nick  = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp    = request.getParameter("hp");
	String zip   = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr(); //regip 수집하는 코드
	
	UserDTO dto = new UserDTO(); //vo 객체를 만들어서 넘겨받은 값들을 집어 넣음
	dto.setUid(uid);
	dto.setPass(pass1);
	dto.setName(name);
	dto.setNick(nick);
	dto.setEmail(email);
	dto.setHp(hp);
	dto.setZip(zip);
	dto.setAddr1(addr1);
	dto.setAddr2(addr2);
	dto.setRegip(regip);
	
	UserDAO.getInstance().insertUser(dto); //DAO 객체를 호출 -> 값을 넣은 vo 객체 집어 넣음
	
	response.sendRedirect("/Jboard1/user/login.jsp");
%>