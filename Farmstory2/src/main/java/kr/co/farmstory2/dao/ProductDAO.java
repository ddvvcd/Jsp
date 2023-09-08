package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.ProductDTO;

public class ProductDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertProduct(ProductDTO dto) {
		try {
			logger.info("ProductDAO insertProduct...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setInt(1, dto.getType());
			psmt.setString(2, dto.getProductName());
			psmt.setInt(3, dto.getPrice());
			psmt.setInt(4, dto.getDelivery());
			psmt.setInt(5, dto.getStock());
			psmt.setString(6, dto.getThumb120());
			psmt.setString(7, dto.getThumb240());
			psmt.setString(8, dto.getThumb750());
			psmt.setString(9, dto.getSeller());
			psmt.setString(10, dto.getEtc());
			
			psmt.executeUpdate();
			
			close();
			
			logger.info("ProductDAO insertProduct...2");
		} catch (Exception e) {
			logger.error("ProductDAO insertProduct error : " + e.getMessage());
		}
	}
	public ProductDTO selectProduct(String pNo) {
		
		ProductDTO dto = new ProductDTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCT);
			psmt.setString(1, pNo);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setProductName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb120(rs.getString(8));
				dto.setThumb240(rs.getString(9));
				dto.setThumb750(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
			}
			
		} catch (Exception e) {
			logger.error("ProductdAO selectProduct error : " + e.getMessage());
		}
		
		return dto;
	}
	public List<ProductDTO> selectProducts(int start) {
		
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			logger.info("ProductDAO selectProducts...1");

			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setProductName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb120(rs.getString(8));
				dto.setThumb240(rs.getString(9));
				dto.setThumb750(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
				products.add(dto);
			}
			
			logger.info("ProductDAO selectProducts...2");

		} catch (Exception e) {
			logger.error("ProductDAO selectProducts error : " + e.getMessage());
		}
		
		return products;
	}
	
	public List<ProductDTO> selectProducts(String type, int start) {
		
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			logger.info("ProductDAO selectProducts...1");

			conn = getConnection();
			
			if(type != null && type.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_ALL);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_TYPE);
				psmt.setString(1, type);
				psmt.setInt(2, start);
			}
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setProductName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb120(rs.getString(8));
				dto.setThumb240(rs.getString(9));
				dto.setThumb750(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
				products.add(dto);
			}
			
			logger.info("ProductDAO selectProducts...2");

		} catch (Exception e) {
			logger.error("ProductDAO selectProducts error : " + e.getMessage());
		}
		
		return products;
	}
	public void updateProduct() {}
	public void deleteProduct(int no) {}
	
	//장바구니 
	public int selectCountProductsTotal(String type) {
		int total = 0;
		
		try {
			logger.info("ArticleDAO selectCountProductsTotal...1");
			
			conn = getConnection();
			if(type.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_COUNT_PRODUCTS_ALL);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCT_COUNT_PRODUCTS_TYPE);
				psmt.setString(1, type);
			}
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
			
			logger.info("ArticleDAO selectCountProductsTotal...2");

		} catch (Exception e) {
			logger.error("ArticleDAO selectCountProductsTotal error : " + e.getMessage());
		}
		
		return total;
	}
}
