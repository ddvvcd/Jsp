package kr.co.farmstory2.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;


@WebServlet("/admin/productRegister.do")
public class ProductRegisterController extends HttpServlet{

	private static final long serialVersionUID = 4988836274699503222L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	ArticleService aService = new ArticleService();
	ProductService fService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productRegister.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//업로드 경로 구하기
		String path = aService.getFilePath(req);
		
		//파일 업로드
		MultipartRequest mr = aService.uploadFile(req, path);
		
		logger.debug("mr : " + mr);
		
		logger.debug("path : " + path);
		
		//폼 데이터 수신
		String type        = mr.getParameter("type");
		String productName = mr.getParameter("productName");
		String price       = mr.getParameter("price");
		String delivery    = mr.getParameter("delivery");
		String stock       = mr.getParameter("stock");
		String thumb120    = mr.getOriginalFileName("thumb120");
		String thumb240    = mr.getOriginalFileName("thumb240");
		String thumb750    = mr.getOriginalFileName("thumb750");
		String seller      = mr.getParameter("seller");
		String etc         = mr.getParameter("etc");
		
		logger.debug("type : " + type);
		logger.debug("productName : " + productName);
		logger.debug("price : " + price);
		logger.debug("delivery : " + delivery);
		logger.debug("stock : " + stock);
		logger.debug("thumb120 : " + thumb120);
		logger.debug("thumb240 : " + thumb240);
		logger.debug("thumb750 : " + thumb750);
		logger.debug("seller : " + seller);
		logger.debug("etc : " + etc);
		
		//DTO 생성
		ProductDTO dto = new ProductDTO();
		
		dto.setType(type);
		dto.setProductName(productName);
		dto.setPrice(price);
		dto.setDelivery(delivery);
		dto.setStock(stock);
		dto.setThumb120(thumb120);
		dto.setThumb240(thumb240);
		dto.setThumb750(thumb750);
		dto.setSeller(seller);
		dto.setEtc(etc);
		
		logger.debug(dto.toString());
		
		//상품 삽입
		fService.insertProduct(dto);
		
		
		//리다이렉트
		resp.sendRedirect("/Farmstory2/admin/productList.do?success=200");
		
	}
}
