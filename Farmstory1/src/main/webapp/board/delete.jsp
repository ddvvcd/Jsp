<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");	
	String group = request.getParameter("group"); //아래쪽 group, cate 없어서 수신 받음
	String cate  = request.getParameter("cate"); //view 에서 전송?
	
	ArticleDAO dao = new ArticleDAO();
	dao.deleteArticle(no);
	
	response.sendRedirect("./list.jsp?group="+group+"&cate="+cate);
%>