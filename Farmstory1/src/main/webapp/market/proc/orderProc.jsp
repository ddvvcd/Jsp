<%@page import="kr.farmstory1.dao.OrderDAO"%>
<%@page import="kr.farmstory1.dto.OrderDTO"%>
<%@page import="kr.farmstory1.dto.productDTO"%>
<%@page import="kr.farmstory1.dao.productDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");	
	String orderProduct  = request.getParameter("orderProduct");
	String orderCount    = request.getParameter("orderCount");
	String orderDelivery = request.getParameter("orderDelivery");
	String orderPrice    = request.getParameter("orderPrice");
	String orderTotal    = request.getParameter("orderTotal");
	String orderUser     = request.getParameter("orderUser");
	String receiver      = request.getParameter("receiver");
	String hp            = request.getParameter("hp");
	String zip    		 = request.getParameter("zip");
	String addr1 		 = request.getParameter("addr1");
	String addr2 		 = request.getParameter("addr2");
	String etc 			 = request.getParameter("etc");
	
	System.out.println("orderProduct : " + orderProduct);
	System.out.println("orderCount : " + orderCount);
	System.out.println("orderDelivery : " + orderDelivery);
	System.out.println("orderPrice : " + orderPrice);
	System.out.println("orderTotal : " + orderTotal);
	System.out.println("orderUser : " + orderUser);
	System.out.println("receiver : " + receiver);
	System.out.println("hp : " + hp);
	System.out.println("zip : " + zip);
	System.out.println("addr1 : " + addr1);
	System.out.println("addr2 : " + addr2);
	System.out.println("etc : " + etc);
	
	
	OrderDTO dto = new OrderDTO();
	dto.setOrderProduct(orderProduct);
	dto.setOrderCount(orderCount);
	dto.setOrderDelivery(orderDelivery);
	dto.setOrderPrice(orderPrice);
	dto.setOrderTotal(orderTotal);
	dto.setOrderUser(orderUser);
	dto.setReceiver(receiver);
	dto.setHp(hp);
	dto.setZip(zip);
	dto.setAddr1(addr1);
	dto.setAddr2(addr2);
	dto.setOrderEtc(etc);
	
	OrderDAO dao = new OrderDAO();
	dao.insertOrder(dto);
	
	response.sendRedirect("/Farmstory1/market/list.jsp");
%>