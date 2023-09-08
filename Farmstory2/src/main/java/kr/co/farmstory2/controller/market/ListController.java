package kr.co.farmstory2.controller.market;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/market/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 8543347364327031519L;
	ProductService pService = new ProductService();
	ArticleService aService = new ArticleService();

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//market 장바구니 페이지에 글 출력하도록 해보자!
		
		String type = req.getParameter("type");
		String pg   = req.getParameter("pg");
		
		logger.debug("type : " + type);
		logger.debug("pg : " + pg);
		
		if(type == null) {
			type = "0";
		}
		
		
		// 현재 페이지 번호
		int currentPage = aService.getCurrentPage(pg);
		logger.debug("currentPage : " + currentPage);
		
		// 전체 상품 갯수
		int total = pService.selectCountProductsTotal(type);
		logger.debug("total : " + total);
		
		// 마지막 페이지 번호
		int lastPageNum = aService.getLastPageNum(total);
		logger.debug("lastPageNum : " + lastPageNum);
		
		// 페이지 그룹 start, end 번호
		int[] result = aService.getPageGroupNum(currentPage, lastPageNum);

		
		// 페이지 시작번호
		int pageStartNum = aService.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = aService.getStartNum(currentPage);
		
		//상품 조회
		List<ProductDTO> products = pService.selectProducts(type, start);
		logger.debug("products : " + products);
		
		req.setAttribute("type", type);
		req.setAttribute("pg", pg);
		req.setAttribute("total", total);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("result", result);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("start", start);
		req.setAttribute("products", products);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/market/list.jsp");
		dispatcher.forward(req, resp);
	}
}	
