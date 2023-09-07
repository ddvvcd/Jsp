package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.dto.ProductDTO;

public class ProductService {
	
	ProductDAO dao = new ProductDAO();
	
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	
	public List<ProductDTO> selectProducts(String type) {
		return dao.selectProducts(type);
	}
}
