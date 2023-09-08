package kr.co.farmstory2.service;

import kr.co.farmstory2.dao.OrderDAO;
import kr.co.farmstory2.dto.OrderDTO;

public class OrderService {
	
	OrderDAO dao = new OrderDAO();
	
	public void insertOrder(OrderDTO dto) {
		dao.insertOrder(dto);
	}
}
