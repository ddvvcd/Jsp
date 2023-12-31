<%@page import="kr.farmstory1.dto.ArticleDTO"%>
<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
 	String group   = request.getParameter("group"); //group 없어서 작성
 	String cate    = request.getParameter("cate"); //cate 없어서 작성
	String no      = request.getParameter("no");//no 없어서 작성
	String title   = request.getParameter("title");
	String content = request.getParameter("content");
	String file    = request.getParameter("file");
	
	ArticleDTO dto = new ArticleDTO();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setNo(no);
	
	ArticleDAO dao = new ArticleDAO();
	dao.updateArticle(dto); //위에 작성한 dto 객체 넣음
	
	response.sendRedirect("../view.jsp?group="+group+"&cate="+cate+"&no="+no); //group, cate가 없으니 위에 작성
	
%>