<%@page import="kr.farmstory1.dto.UserDTO"%>
<%@page import="kr.farmstory1.dao.UserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid   = request.getParameter("uid");
	String pass  = request.getParameter("pass");
	String target  = request.getParameter("target"); //최종적으로 target 선언 (2)
	String group = request.getParameter("group");
	String cate  = request.getParameter("cate");
	String no    = request.getParameter("no");
	
	System.out.println();
	
	UserDAO dao = UserDAO.getInstance();
	UserDTO user = dao.selectUser(uid, pass);
	
	if(user != null){
		session.setAttribute("sessUser", user);
		
		if(target.equals("write")){ //(추가)(1)
			response.sendRedirect("/Farmstory1/board/write.jsp?group="+group+"&cate="+cate); //파라미터 들고 가야 함(write.jsp 확인)
		}else if(target.equals("view")){
			response.sendRedirect("/Farmstory1/board/view.jsp?group="+group+"&cate="+cate+"&no="+no); //파라미터 들고 가야 함(view.jsp 확인)
		}else{
			response.sendRedirect("/Farmstory1");
		}
		
	}else{
		response.sendRedirect("/Farmstory1/user/login.jsp?success=100");		
	}
%>