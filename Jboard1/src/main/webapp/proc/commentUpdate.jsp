<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no	   = request.getParameter("no");
	String parent  = request.getParameter("parent"); //parent 추가
	String comment = request.getParameter("comment");
	
	ArticleDAO dao = new ArticleDAO();
	dao.updateComment(no, comment); //dao 클래스로 가서 updateComment() 메소드 작성하기
	
	//그냥 이동하면 에러뜸, parent를 들고 가야 함
	response.sendRedirect("/Jboard1/view.jsp?=no"+parent);
%>